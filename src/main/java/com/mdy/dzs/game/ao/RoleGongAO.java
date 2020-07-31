package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.ConsumesItem;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.role.JiBan;
import com.mdy.dzs.game.domain.calcattack.CalcRoleGong;
import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.dzs.game.domain.gong.vo.RoleGongRefineVO;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.PacketException;
import com.mdy.dzs.game.exception.RoleGongException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.sharp.container.biz.BizException;

/**
 * 内外功复用层
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月22日 下午2:44:35
 */
public class RoleGongAO extends BaseAO {
	private CacheManager cacheManager;
	private PacketAO packetAO;

	public RoleGongAO(CacheManager cacheManager, PacketAO packetAO) {
		this.cacheManager = cacheManager;
		this.packetAO = packetAO;
	}

	public RoleGong queryExistGongById(int roleId, int cid) throws BizException {
		RoleGong res = roleGongDAO().query(cid);
		if (res == null) {
			throw BaseException.getException(PacketException.EXCE_GONG_NOT_ENOUGH);
		}
		if (res.getRoleId() != roleId) {
			throw BaseException.getException(RoleGongException.EXCE_GONG_NOT_SELF, cid, roleId);
		}
		return res;
	}

	public void update(RoleGong curGong) {
		roleGongDAO().update(curGong);
	}

	public void updateRefineInfo(Role doc, RoleGong rg, CalcRoleGong calc, RoleGongRefineVO info) throws BizException {
		do {
			int propCnt = rg.getPropsN();
			info.setCnt(propCnt);
			if (!calc.hasRefine()) {// 不能精炼
				info.setAllow(0);
				break;
			}
			info.setAllow(1);
			if (propCnt >= calc.getMaxRefineCount()) {// 最大上限
				info.setMax(1);
				break;
			}

			List<ProbItem> items = calc.getRefineConsumeByCnt(propCnt);
			List<ConsumesItem> cItems = new ArrayList<ConsumesItem>();
			for (ProbItem probItem : items) {
				ConsumesItem cItem = new ConsumesItem(probItem);
				int cnt = packetAO.getNumberByTypeIdLevel(doc, probItem.getT(), probItem.getId());
				cItem.setN1(cnt);
				cItems.add(cItem);
			}
			info.setItems(cItems);

			int lv = propCnt / calc.getRefineNum();
			int silver = calc.getRefine().getArrSilver().get(lv);
			info.setSilver(silver);
		} while (false);
	}

	/**
	 * 获取最好的内外功
	 * 
	 * @param roleid
	 * @param subpos1 -6
	 * @param pos
	 * @return equip || null null 表示没有
	 * @throws BizException
	 * 
	 */
	public RoleGong getBetterGong(int roleId, int subpos, int pos, int itemId) throws BizException {
		RoleGong betterGong = null;
		RoleGong thisGong = null;
		Role role = roleDAO().queryById(roleId);
		if (role.getLevel() < 10) {
			return betterGong;
		}
		// 获取所有功
		List<RoleGong> Gonglist = roleGongDAO().queryListByAccount(roleId);
		// 当前位置的功
		List<RoleGong> thisGonglist = roleGongDAO().queryListByAccPosSubpos(roleId, pos, subpos);
		if (thisGonglist.size() > 0) {
			thisGong = thisGonglist.get(0);
		}
		// 获取最好的功
		for (RoleGong Gong : Gonglist) {
			if (Gong.getPos() == 0 && Gong.getSubpos() == subpos) {
				if (chooseBetterGong(Gong, thisGong, pos, itemId) > 0) {
					betterGong = Gong;
					thisGong = Gong;
				}
			}

		}
		return betterGong;
	}

	// 比较两个功
	public int chooseBetterGong(RoleGong otherGong, RoleGong thisGong, int pos, int itemId) throws BizException {
		if (otherGong == null) {

			return -1;
		}
		if (thisGong == null) {

			return 1;
		}
		// 拥有缘分的内外功优先
		Card cardData = cacheManager.getExistValueByKey(Card.class, itemId);
		List<Integer> jbAry = cardData.getFate1();// 可能为空
		if (jbAry.size() > 0) {
			for (int i = 0; i < jbAry.size(); i++) {
				int jid = jbAry.get(i);
				JiBan jiBan = cacheManager.getExistValueByKey(JiBan.class, jid);
				int resId = jiBan.getCond1();
				if (thisGong.getResId() == otherGong.getResId() && thisGong.getLevel() < otherGong.getLevel()) {

					return 1;
				}
				if (thisGong.getResId() == resId) {

					return -1;
				} else if (otherGong.getResId() == resId) {

					return 1;
				}
			}
		}

		if (thisGong.getStar() < otherGong.getStar()) {

			return 1;
		}
		if (thisGong.getStar() == otherGong.getStar() && thisGong.getLevel() < otherGong.getLevel()) {

			return 1;
		}
		return -1;
	}

	public List<RoleGong> queryGongByaccpos(int roleId, int pos, int subpos) {

		return roleGongDAO().queryListByAccPosSubpos(roleId, pos, subpos);
	}

	public List<Integer> queryRecordGongId(int roleId) {
		return roleGongDAO().queryRecordGongId(roleId);
	}

}
