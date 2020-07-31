/**
 * 
 */
package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.packet.Bag;
import com.mdy.dzs.data.domain.yuan.Collect;
import com.mdy.dzs.data.domain.yuan.Soul;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleYuanAction;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.yuan.RoleYuan;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.PacketException;
import com.mdy.dzs.game.exception.RoleYuanException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月23日 上午11:26:49
 */
public class RoleYuanActionImpl extends ApplicationAwareAction implements RoleYuanAction {

	@Override
	public List<Serializable> collect(String acc, int t) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		List<RoleYuan> rtnAry = new ArrayList<RoleYuan>();
		int cnt = 0;
		int itemLogType = RoleItemLog.SYS_真气_练气_1次;
		switch (t) {
			case 1:
				cnt = 1;
				break;
			case 3:
				cnt = 10;
				itemLogType = RoleItemLog.SYS_真气_练气_10次;
				break;
		}
		if (cnt != 0) {
			Bag bagData = cacheManager().getValueByKey(Bag.class, 6);
			int num = roleYuanAO().getRoleYuanListNumByAcc(doc.getId());
			if (num + cnt > bagData.getMax()) {
				throw BaseException.getException(PacketException.EXCE_YUAN_PACKET_FULL);
			}
			for (int i = 0; i < cnt; i++) {

				Collect collData = cacheManager().getExistValueByKey(Collect.class, doc.getCurCollLv());
				packetAO().removeItemMustEnough(doc, 0, 2, collData.getPrice(), itemLogType, "");
				List<ProbItem> itemObj = cacheManager().probGot(collData.getSoul());// 获得精元
				List<Object> adds = packetAO().addItem(doc, 6, itemObj.get(0).getId(), 1, itemLogType, "");

				int collLv = doc.getCurCollLv();
				if (cacheManager().random(1, 10000) <= collData.getProb()) {// 升级概率
					if (++collLv > 5)
						collLv = 1;
				} else {
					collLv = 1;
				}
				doc.setCurCollLv(collLv);

				rtnAry.add((RoleYuan) adds.get(0));
			}
			roleAO().updateCurCollLv(doc);
			missionExecAO().collectRoleYuan(doc, cnt);
		}
		return getCollectInfo(doc, rtnAry);
	}

	/**
	 * 获取聚元信息
	 * 
	 * @param doc
	 * @return
	 * @throws BizException
	 */
	private List<Serializable> getCollectInfo(Role doc, List<RoleYuan> ary) throws BizException {
		int itemNum = packetAO().getNumberByTypeId(doc, 7, Constants.juYuanItemId);
		return Arrays.asList((Serializable) ary, doc.getCurCollLv(), doc.getSilver(), itemNum, doc.getGold());
	}

	@Override
	public List<Serializable> levelUp(String acc, int id, List<Integer> idAry) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleYuan devourData = roleYuanAO().queryExistYuanById(doc.getId(), id);
		if (devourData.getLevel() >= Constants.jingYuanLvLimit) {
			// throw BaseException.getGlobalException("reach max Level");
			throw BaseException.getException(RoleYuanException.EXCE_ROLEYUAN_LEVEL_MAX);
		}
		if (devourData.getLevel() >= doc.getLevel()) {
			// throw BaseException.getGlobalException("reach role Level 1");
			throw BaseException.getException(RoleYuanException.EXCE_ROLEYUAN_REACH_LEVEL, 1);
		}
		Item devourItemData = cacheManager().getExistValueByKey(Item.class, devourData.getResId());

		int oldLv = devourData.getLevel();
		List<Prop> props = devourData.getProps();

		for (int i = 0; i < idAry.size(); i++) {
			if (devourData.getLevel() >= doc.getLevel()) {
				// throw BaseException.getGlobalException(
				// "reach role Level 2");
				throw BaseException.getException(RoleYuanException.EXCE_ROLEYUAN_REACH_LEVEL, 2);
			}
			if (devourData.getLevel() < Constants.jingYuanLvLimit) {
				Soul expSumData = null;
				RoleYuan curYuanData = roleYuanAO().queryExistYuanById(doc.getId(), idAry.get(i));
				Item curItemData = cacheManager().getExistValueByKey(Item.class, curYuanData.getResId());
				if (curItemData.getQuality() > devourData.getQuality() || curYuanData.getPos() != 0) {
					// throw BaseException.getGlobalException(
					// "ids error 2");
					throw BaseException.getException(RoleYuanException.EXCE_ROLEYUAN_IDS_ERROR);
				}
				if (curYuanData.getLevel() > 0) {
					expSumData = cacheManager().getExistValueByKey(Soul.class, curYuanData.getLevel() - 1);
				}

				devourData.setCurExp(devourData.getCurExp() + curYuanData.getCurExp() + curItemData.getPrice());
				if (expSumData != null) {
					devourData.setCurExp(
							devourData.getCurExp() + expSumData.getArrSumexp().get(curItemData.getQuality() - 1));
				}

				for (int j = devourData.getLevel(); j < Constants.jingYuanLvLimit; j++) {
					Soul tempData = cacheManager().getExistValueByKey(Soul.class, devourData.getLevel());
					int texp = tempData.getArrExp().get(devourData.getQuality() - 1);
					if (devourData.getCurExp() >= texp && devourData.getLevel() < Constants.jingYuanLvLimit) {// 升级
						if (devourData.getLevel() >= doc.getLevel()) {
							// throw BaseException.getGlobalException(
							// "reach role Level 3");
							throw BaseException.getException(RoleYuanException.EXCE_ROLEYUAN_REACH_LEVEL, 3);
						}
						devourData.setLevel(devourData.getLevel() + 1);
						devourData.setCurExp(devourData.getCurExp() - texp);
						if (props.size() > 0)
							for (int k = 0; k < props.size(); k++) {
								props.get(k).setVal(props.get(k).getVal() + devourItemData.getArrAddition().get(k));
							}
					} else {
						break;
					}
				}
			}
			packetAO().removeCardEquipGongYuanById(doc, Packet.POS_YUAN, idAry.get(i), RoleItemLog.SYS_真气_真气升级);
		}
		if (devourData.getLevel() >= Constants.jingYuanLvLimit) {
			devourData.setLevel(Constants.jingYuanLvLimit);
			devourData.setCurExp(0);
		}
		roleYuanAO().update(devourData);
		if (devourData.getLevel() > oldLv && devourData.getPos() > 0) {
			roleAO().fmtPropUpdate(doc);
		}
		if (devourData.getLevel() > oldLv) {
			missionExecAO().lvUpRoleYuan(doc, devourData.getLevel());
		}

		Map<String, Object> info = new HashMap<String, Object>();
		info.put("resId", devourData.getResId());
		info.put("level", devourData.getLevel());
		info.put("curExp", devourData.getCurExp());
		info.put("props", devourData.getProps());
		return Arrays.asList((Serializable) info);
	}

	@Override
	public List<Serializable> addCollectLV(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		if (doc.getCurCollLv() >= Constants.CollectMaxLevel) {
			// throw BaseException.getGlobalException(
			// "curCollLv is four");
			throw BaseException.getException(RoleYuanException.EXCE_ROLEYUAN_聚元_LEVEL_ERROR);
		}
		int num1 = packetAO().getNumberByTypeId(doc, 7, Constants.juYuanItemId);
		int num2 = packetAO().getNumberByTypeId(doc, 0, 1);
		if (num1 == 0 && num2 < Constants.juYuanGold) {
			throw BaseException.getException(PacketException.EXCE_ITEM_NOT_ENOUGH);
		}
		if (num1 > 0) {
			packetAO().removeItemMustEnough(doc, Packet.POS_BAG, Constants.juYuanItemId, 1, RoleItemLog.SYS_真气_聚元_金币或道具,
					"");
		} else {
			packetAO().removeItemMustEnough(doc, 0, 1, Constants.juYuanGold, RoleItemLog.SYS_真气_聚元_金币或道具, "");
		}

		doc.setCurCollLv(4);
		roleAO().updateCurCollLv(doc);
		int itemNum = packetAO().getNumberByTypeId(doc, 7, Constants.juYuanItemId);
		return Arrays.asList((Serializable) doc.getCurCollLv(), doc.getGold(), itemNum);

	}

}
