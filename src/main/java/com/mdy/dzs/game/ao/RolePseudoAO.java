package com.mdy.dzs.game.ao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.Prob;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.item.ProbVal;
import com.mdy.dzs.data.domain.shop.ProbMutex;
import com.mdy.dzs.data.domain.shop.Pseudo;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.prob.RolePseudo;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.util.JSONUtil;

/**
 * 概率
 * 
 * @author 房曈
 *
 */
public class RolePseudoAO extends BaseAO {
	//
	private static final Logger logger = LoggerFactory.get(RolePseudoAO.class);

	/** type 对应 start值 */
	private Map<Integer, Integer> startValues;
	private CacheManager cacheManager;
	private PacketAO packetAO;
	private RoleAO roleAO;

	/**
	 * 
	 */
	public RolePseudoAO(CacheManager cacheManager, PacketAO packetAO, RoleAO roleAO) {
		this.cacheManager = cacheManager;
		this.packetAO = packetAO;
		this.roleAO = roleAO;
	}

	@Override
	public void start() {
		startValues = new HashMap<Integer, Integer>();
		Map<Integer, Pseudo> map = cacheManager.getValues(Pseudo.class);
		for (Pseudo pseudo : map.values()) {
			if (pseudo.getIsStart() == 1) {
				startValues.put(pseudo.getType(), pseudo.getId());
			}
		}
		super.start();
	}

	/**
	 * 查询 没有则添加
	 */
	public RolePseudo query(int roleId, int pseudoType) {
		RolePseudo rp = rolePseudoDAO().query(roleId, pseudoType);
		if (rp == null) {
			rp = new RolePseudo();
			rp.setRoleId(roleId);
			rp.setPseudoType(pseudoType);
			rp.setPseudoId(startValues.get(pseudoType));
			rolePseudoDAO().add(rp);
		}
		return rp;
	}

	/**
	 * 根据为概率类型获取奖励
	 * 
	 * @param roleId
	 * @param pseudoType
	 * @param useOther
	 * @param falg       0直接调用probGot方法，其他需要走流程
	 * @param probId     直接调用需要用自己pid
	 * @return
	 * @throws BizException
	 */
	public List<ProbItem> getRewards(RolePseudo rp, boolean useOther, List<Object> oldequips, List<Object> oldcards,
			List<ProbItem> rtn) throws BizException {

		logger.info("start=>" + rp);
		Pseudo pseudo = cacheManager.getExistValueByKey(Pseudo.class, rp.getPseudoId());

		int probIda = useOther ? pseudo.getTenProb() : pseudo.getProb();
		if (probIda == 0)
			probIda = pseudo.getProb();

		// 加入排斥
		List<Integer> cardlist = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		boolean bol = true;// 是否进行二次随机
		Prob prob = cacheManager.getExistValueByKey(Prob.class, probIda);
		// 进入互斥流程

		List<Object> equips = new ArrayList<Object>(oldequips);
		List<Object> cards = new ArrayList<Object>(oldcards);
		if (rtn != null && rtn.size() > 0) // 应对批量开箱，把自己数据装入
		{
			for (int b = 0; b < rtn.size(); b++) {
				int tt = rtn.get(b).getT();
				int id = rtn.get(b).getId();
				if (tt == Packet.POS_EQUIP) {
					Equip ep = new Equip();
					ep.setResId(id);
					equips.add(ep);
				}
				if (tt == Packet.POS_CARD) {
					RoleCard rc = new RoleCard();
					rc.setResId(id);
					cards.add(rc);
				}
			}
		}

		int sumdata = 0; // 限制数量
		for (int i = 0; i < pseudo.getMutex().size(); i++) {
			ProbMutex mutex = pseudo.getMutex().get(i);
			sumdata += mutex.getSum();

			switch (mutex.getType()) {
			case 1:
				// 排斥用户装备
				mutexUserDes(equips, mutex, map, cardlist);
				break;
			case 4:
				sumdata += mutexUserDesType4(rtn, mutex, rp.getRoleId(), cardlist);
				break;
			case 8:
				// 排斥用户卡牌
				mutexUserDes(cards, mutex, map, cardlist);
				break;
			}
		}

		Prob probbak = (Prob) cloneObject(prob);// 备份一次，防止移除缓存数据
		List<ProbVal> items = probbak.getItems();

		boolean bollean = true;
		if (cardlist.size() == sumdata)// 表示不走规则了
		{
			bollean = false;
			cardlist.clear();
			logger.info("mutexUserDes clear");
		}
		// 去掉prob中被排斥的数据
		if (cardlist.size() > 0 && bollean) {
			for (int i = items.size() - 1; i >= 0; i--) {
				ProbVal pv = items.get(i);
				int count = pv.getMaxId() - pv.getMinId() + 1;
				for (int desid : cardlist) {
					if (pv.getMinId() <= desid && desid <= pv.getMaxId()) {
						count--;
					}
				}
				if (count <= 0)
					items.remove(i);
			}
		}
		// System.out.println("============================================" +
		// prob.getItems() == null ? "prob == null" : prob.getItems().size());
		List<ProbItem> res = cacheManager.probGot_n(probbak, cardlist, bol);

		rp.setCount(rp.getCount() + 1);
		rp.setAllCount(rp.getAllCount() + 1);

		// 验证是否在重置产出内
		if (pseudo.getResetID() != 0) {
			for (ProbItem probItem : res) {
				if (isInItems(probItem.getT(), probItem.getId(), pseudo.getOutput())) {
					rp.setPseudoId(pseudo.getResetID());
					rp.setCount(0);
				}
			}
		}

		// 验证是否超过跳转次数
		if (rp.getCount() >= pseudo.getNumber() && pseudo.getJumpID() != 0) {
			rp.setPseudoId(pseudo.getJumpID());
		}
		logger.info("reward=>" + res);
		logger.info("end=>" + rp);
		rolePseudoDAO().update(rp);
		return res;
	}

	// 备份一次，防止移除缓存数据
	private Object cloneObject(Object obj) {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(byteOut);
			out.writeObject(obj);

			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			ObjectInputStream in = new ObjectInputStream(byteIn);
			return in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 单独校验武功碎片及武功
	private int mutexUserDesType4(List<ProbItem> rtn, ProbMutex mutex, int roleid, List<Integer> cardlist) {
		// 排斥内外功(获取用户)
		int res = 0;
		int minid = Integer.valueOf(mutex.getMin());
		int maxid = Integer.valueOf(mutex.getMax());
		for (int a = minid; a <= maxid; a++) {
			Item item = cacheManager.getValueByKey(Item.class, a);
			List<Integer> itemlist = item.getPara1();
			res += itemlist.size();
			// String sql1 = "";
			// for(int ilist : itemlist)
			// {
			// sql1 += ilist + ",";
			// }
			// sql1 = sql1.substring(0, sql1.length() - 1);
			//
			// Map<Integer,Integer> itmap = packetAO.getMaxGongItem(sql1,roleid);
			int gongnum = roleAO.queryCountByRidRestId(roleid, a);

			if (rtn != null && rtn.size() > 0) // 应对批量开箱，把自己数据装入(独立校验)
			{
				for (int b = 0; b < rtn.size(); b++) {
					ProbItem pi = rtn.get(b);
					// if(pi.getT() == 9 ||pi.getT() == 10)//碎片加入到碎片中
					// {
					// if(itmap.containsKey(pi.getId()))
					// {
					// itmap.put(pi.getId(), itmap.get(pi.getId()) + pi.getN());
					// }
					// }
					if (pi.getT() == 4) {
						if (a == pi.getId()) {
							gongnum++;
						}
					}
				}
			}

			// List<Map.Entry<Integer,Integer>> mappingList = new
			// ArrayList<Map.Entry<Integer,Integer>>(itmap.entrySet());
			// Collections.sort(mappingList, new Comparator<Map.Entry<Integer, Integer>>() {
			// public int compare(Map.Entry<Integer, Integer> obj1 , Map.Entry<Integer,
			// Integer> obj2) {
			// return obj2.getValue() - obj1.getValue();
			// }
			// });
			// int gongitemnum = mappingList.size() > 0 ? mappingList.get(0).getValue() : 0;
			if (gongnum >= mutex.getNum()) {
				cardlist.add(a);
				cardlist.addAll(itemlist);
				logger.info("mutexUserDes Gong=>" + a);
				logger.info("mutexUserDes GongItems=>" + JSONUtil.toJson(itemlist));
			}
		}
		return res;
	}

	// 用户数据进行互斥
	private void mutexUserDes(List<Object> rtnAry, ProbMutex mutex, Map<Integer, Integer> map, List<Integer> cardlist) {
		if (rtnAry == null || rtnAry.size() == 0)
			return;
		int type = mutex.getType();
		for (int a = 0; a < rtnAry.size(); a++) {
			switch (type) {
			case 1:
				Equip ep = (Equip) rtnAry.get(a);
				if (mutex.getMin() <= ep.getResId() && ep.getResId() <= mutex.getMax()) {
					int num = 1;
					if (map.get(ep.getResId()) == null) {
						map.put(ep.getResId(), num);
					} else {
						num += map.get(ep.getResId());
						map.put(ep.getResId(), num);
					}
					if (num >= mutex.getNum() && !cardlist.contains(ep.getResId())) {
						cardlist.add(ep.getResId());
						logger.info("mutexUserDes RoleEquip=>" + ep.getResId());
					}
				}
				break;
			case 8:
				RoleCard rc = (RoleCard) rtnAry.get(a);
				if (mutex.getMin() <= rc.getResId() && rc.getResId() <= mutex.getMax()) {
					int num = ((rc.getCls() - 3) >= 0 ? (rc.getCls() - 3) : 1);
					if (map.get(rc.getResId()) == null) {
						map.put(rc.getResId(), num);
					} else {
						num += map.get(rc.getResId());
						map.put(rc.getResId(), num);
					}
					if (num >= mutex.getNum() && !cardlist.contains(rc.getResId())) {
						cardlist.add(rc.getResId());
						logger.info("mutexUserDes RoleCard=>" + rc.getResId());
					}
				}
				break;
			}
		}
	}

	/**
	 * 检验存在区间
	 * 
	 * @param id
	 * @param ids
	 * @return
	 */

	public boolean isInItems(int type, int itemId, List<Integer> items) {
		boolean isHave = false;
		int len = items.size() - items.size() % 3;
		for (int i = 0; i < len; i += 3) {
			int iType = items.get(i);
			int min = items.get(i + 1);
			int max = items.get(i + 2);
			if (iType == type && itemId >= min && itemId <= max) {
				isHave = true;
				return isHave;
			}
		}
		return isHave;
	}

	// 真假金箱子(用户id)
	public RoleItem checkBoxPro(int roleId) {
		try {
			RoleItem statItem = packetDAO().queryByAccItemId(roleId, 4001, 7);// 只拿到金箱子
			return statItem;
		} catch (BizException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param itemData
	 * @return
	 */
	public int getPseudoType(Item itemData) {
		int res = 0;
		switch (itemData.getId()) {
		case 4001:
		case 4002:
			res = Pseudo.TYPE_使用道具_金箱子;
			break;

		case 4003:
		case 4004:
			res = Pseudo.TYPE_使用道具_银箱子;
			break;

		case 4005:
		case 4006:
			res = Pseudo.TYPE_使用道具_铜箱子;
			break;
		}
		return res;
	}
}