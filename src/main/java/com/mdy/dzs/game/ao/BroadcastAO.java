package com.mdy.dzs.game.ao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.broad.GuangBo;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.game.domain.broad.BroadData;
import com.mdy.dzs.game.domain.broad.BroadInfo;
import com.mdy.dzs.game.domain.broad.Broadcast;
import com.mdy.dzs.game.domain.broad.ComparatorBroadcast;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 广播
 * 
 * @author 房曈
 *
 */
public class BroadcastAO extends BaseAO {

	private RoleAO roleAO;
	private CacheManager cacheManager;

	public BroadcastAO(CacheManager cacheManager, RoleAO roleAO) {
		this.cacheManager = cacheManager;
		this.roleAO = roleAO;
	}

	//
	/**
	 * 查询
	 */
	public Broadcast query(int id) {
		return broadcastDAO().query(id);
	}

	/**
	 * 查询列表
	 */
	public List<Broadcast> queryList() {
		return broadcastDAO().queryList();
	}

	/**
	 * 查询列表
	 * 
	 * @param acc
	 */
	public List<Broadcast> queryViewList(Role doc) {
		List<Broadcast> player = broadcastDAO().queryViewList(doc.getBroadcastViewTime(), doc.getAccount());
		List<Broadcast> system = broadcastDAO().querySystemSet();
		List<Broadcast> close = broadcastDAO().queryGameClose(doc.getType());
		if (system.size() > 0) {
			player.addAll(system);
		}
		if (close.size() > 0) {
			player.addAll(close);
		}
		return player;
	}

	/**
	 * 添加
	 * 
	 * @param Broadcast
	 */
	public void add(Broadcast b) {
		broadcastDAO().add(b);
	}

	/**
	 * 更新
	 * 
	 * @param Broadcast
	 */
	public void update(Broadcast b) {
		broadcastDAO().update(b);
	}

	/**
	 * strId: 广播字符串id account: 角色账号 colorlist: 颜色数组 paralist: 参数数组
	 */
	public void sendBroadcast(int strId, String account, List<Integer> colorlist, List<BroadData> paralist)
			throws BizException {
		Broadcast bData = new Broadcast();
		bData.setAccount(account);
		bData.setStrId(strId);
		bData.setType(Broadcast.BROAD_TYPE_PLAYER_TOUCH);
		bData.setTime(new Date());
		bData.setParalist(paralist);
		bData.setColor(colorlist);
		bData.setPara1("");
		bData.setPara2("");
		bData.setPara3("");
		bData.setPara4("");
		bData.setPara5("");
		bData.setPara6("");
		bData.setPara7("");
		bData.setPara8("");
		broadcastDAO().add(bData);
	}

	// 恭喜%s在侠客招募中获得%s侠客！称霸武林指日可待！
	public void wineShopGetCardBroad(Role doc, Card cardData) throws BizException {
		GuangBo broadData = cacheManager.getValueByKey(GuangBo.class, Broadcast.WINE_SHOP);
		List<BroadData> paralist = new ArrayList<BroadData>();
		paralist.add(new BroadData(doc.getName(), doc.getCls(), -1));// '-1'与前端约定
		paralist.add(new BroadData(cardData.getName(), cardData.getStar().get(0), 8));// 获得卡牌
		sendBroadcast(Broadcast.WINE_SHOP, doc.getAccount(), broadData.getColor(), paralist);
	}

	// 恭喜%s将侠客%s进阶到了+%s，战力得到大幅提升！
	public void cardClsBroad(Role doc, String name, int star, int cls) throws BizException {
		GuangBo broadData = cacheManager.getValueByKey(GuangBo.class, Broadcast.CARD_CLS);
		List<BroadData> paralist = new ArrayList<BroadData>();
		paralist.add(new BroadData(doc.getName(), doc.getCls(), -1));// '-1'与前端约定
		paralist.add(new BroadData(name, star, 8));// 获得卡牌
		paralist.add(new BroadData(cls + "", 0, broadData.getArrColor()));
		sendBroadcast(Broadcast.CARD_CLS, doc.getAccount(), broadData.getColor(), paralist);
	}

	// 恭喜%s获得%s，战力得到大幅提升！
	public void equipGetBroad(Role doc, Item ItemData) throws BizException {
		GuangBo broadData = cacheManager.getValueByKey(GuangBo.class, Broadcast.GET_EQUIP_GONG);
		List<BroadData> paralist = new ArrayList<BroadData>();
		paralist.add(new BroadData(doc.getName(), doc.getCls(), -1));// '-1'与前端约定
		paralist.add(new BroadData(ItemData.getName(), ItemData.getQuality(), ItemData.getType()));// 获得装备内外功
		sendBroadcast(Broadcast.GET_EQUIP_GONG, doc.getAccount(), broadData.getColor(), paralist);
	}

	// 恭喜%s鸿星高照，在限时豪杰活动中获得了限时侠客%s！
	public void activityGetLimitCardBroad(Role doc, Card cardData) throws BizException {
		GuangBo broadData = cacheManager.getValueByKey(GuangBo.class, Broadcast.WINE_SHOP);
		List<BroadData> paralist = new ArrayList<BroadData>();
		paralist.add(new BroadData(doc.getName(), doc.getCls(), -1));// '-1'与前端约定
		paralist.add(new BroadData(cardData.getName(), cardData.getStar().get(0), 8));// 获得卡牌
		sendBroadcast(Broadcast.ACTIVITY_GET_LIMIT_CARD, doc.getAccount(), broadData.getColor(), paralist);
	}

	// 恭喜%s在限时豪杰活动中获得了%s侠客！称霸武林指日可待！
	public void activityGetCardBroad(Role doc, Card cardData) throws BizException {
		GuangBo broadData = cacheManager.getValueByKey(GuangBo.class, Broadcast.WINE_SHOP);
		List<BroadData> paralist = new ArrayList<BroadData>();
		paralist.add(new BroadData(doc.getName(), doc.getCls(), -1));// '-1'与前端约定
		paralist.add(new BroadData(cardData.getName(), cardData.getStar().get(0), 8));// 获得卡牌
		sendBroadcast(Broadcast.ACTIVITY_EGT_CARD, doc.getAccount(), broadData.getColor(), paralist);
	}

	@SuppressWarnings("deprecation")
	public Map<String, List<BroadInfo>> refList(Role rDoc, List<Broadcast> docs, String acc) throws Exception {
		List<BroadInfo> topAry = new ArrayList<BroadInfo>();
		List<BroadInfo> midAry = new ArrayList<BroadInfo>();

		ComparatorBroadcast comparator = new ComparatorBroadcast();
		Collections.sort(docs, comparator);

		for (int i = 0; i < docs.size(); i++) {
			Broadcast doc = docs.get(i);
			String account = doc.getAccount();
			int strId = doc.getStrId();
			int type = doc.getType();
			if (account != null && account == acc && (strId == 1 || strId == 2))
				continue;

			if (type == 2) {// 玩家触发
				// GuangBo broadData = cacheManager.getValueByKey(GuangBo.class, strId);
				topAry.add(new BroadInfo(doc, 0, "", doc.getParalist(), 1));
			} else if (type == 1) {// 系统定时
				Date d = new Date();
				String dDate = d.getYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate();
				if (Integer.parseInt(doc.getPara3()) == 1) {// 特定时间类型
					// para1:startDate YYYY/M/DD
					// para2:endDate YYYY/M/DD
					// para3:ttype 1：特定时间类型；2：每周固定时间；
					// para4:12:00:00; 12:05:00; 12:10:00
					// para5:str
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if (sdf.parse(dDate).getTime() - sdf.parse(doc.getPara1()).getTime() != 0)
						continue;// 日期不相等
					// new Date('2014/8/21 8:10:0').getTime()
					String[] timeAry = doc.getPara4().split(";");
					for (String str : timeAry) {
						int time = Integer.parseInt(str);

						int disTime = (int) (sdf1.parse(doc.getPara1() + " " + time).getTime() / 1000)
								- (int) (d.getTime() / 1000);
						if (disTime > 0 && disTime <= Constants.onLineTime * 60) {
							// {id:*,type:1 time:*, data:"*"}
							// topAry.push({id:doc.strId,type:doc.type,color:doc.color,time:disTime,string:doc.para5,data:[]});
							topAry.add(new BroadInfo(doc, disTime, doc.getPara5(), new ArrayList<BroadData>(), 1));
						}
					}

				} // TODO else if(parseInt(doc.para3) == 2){//每周固定时间
					// para1:startDate YYYY/M/DD
					// para2:endDate YYYY/M/DD
					// para3:ttype 1：特定时间类型；2：每周固定时间；
					// para4:星期 1;3;5;7
					// para5:18:00:00; 18:05:00
					// para6:str
					// if(new Date(dDate) - new Date(doc.para1) < 0 && new Date(dDate) - new
					// Date(doc.para2) > 0) continue;//时间段外
					// var weekAry = doc.para4.split(';');
					// var timeAry = doc.para5.split(';');
					// if(_.indexOf(weekAry, d.getDay()+'') < 0 && (d.getDay() == 0 &&
					// _.indexOf(weekAry, '7') < 0)) continue;
					// _.each(timeAry, function(time){
					// var disTime = parseInt(new Date(dDate +' '+ time).getTime()/1000) -
					// parseInt(d.getTime()/1000);
					// if(disTime > 0 && disTime <= designCfg.onLineTime * 60){
					// //{id:*,type:1 time:*, data:"*"}
					// topAry.push({id:doc.strId,type:doc.type,color:doc.color,time:disTime,string:doc.para6,data:[]});
					// }
					// });
					// }
			} else if (type == 4) {// 停服广播
				boolean isBroad = DateUtil.isNowInTimePot(doc.getPara1(), doc.getPara2());
				if (isBroad) {// 在播放时段内
					int disTime = 0;
					int now = (int) (new Date().getTime() / 1000);
					Date start = DateUtil.getDateTimeByString(doc.getPara1());
					Date end = DateUtil.getDateTimeByString(doc.getPara2());

					int startTime = Math.round(start.getTime() / 1000);
					int endTime = Math.round(end.getTime() / 1000);
					int addTime = Integer.parseInt(doc.getPara7()) * 60;

					for (; startTime <= endTime; startTime += addTime) {
						if (now <= startTime) {
							disTime = startTime - now > 0 ? startTime - now : 0;
							break;
						}
					}
					int rtn = (int) disTime;
					if (rtn <= Constants.onLineTime * 60) {
						midAry.add(new BroadInfo(doc, rtn, doc.getPara5(), new ArrayList<BroadData>(),
								Integer.parseInt(doc.getPara4())));
					}
				}
			}
		}
		rDoc.setBroadcastViewTime(new Date());
		roleAO.updateBroadcastViewTime(rDoc);
		Map<String, List<BroadInfo>> rtnMap = new HashMap<String, List<BroadInfo>>();
		rtnMap.put("topAry", topAry);
		rtnMap.put("midAry", midAry);

		return rtnMap;
	}

	public void deleteDatas() {
		// 清空玩家触发；清除定时的结束时间早于当前
		broadcastDAO().deleteViewList(); // type==2
		// broadcastDAO().deleteSystemSet(); //type==1
		// List<Broadcast> systemList = broadcastDAO().querySystemSet();

		List<Broadcast> closeList = broadcastDAO().queryAllGameClose();
		List<Integer> deleteClose = new ArrayList<Integer>();
		for (int i = 0; i < closeList.size(); i++) {
			Broadcast doc = closeList.get(i);
			boolean isBroad = DateUtil.isNowInTimePot(doc.getPara1(), doc.getPara2());
			if (!isBroad) {
				deleteClose.add(doc.getId());
			}
		}
		if (deleteClose.size() > 0) {
			broadcastDAO().deleteGameClose(deleteClose); // type==4
		}

	}
}