package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.card.ShangXianSheDing;
import com.mdy.dzs.data.domain.equipment.Baptize;
import com.mdy.dzs.data.domain.equipment.Equipen;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.viplevel.VipCrit;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleEquipAction;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.calcattack.CalcRoleEquip;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.dzs.game.domain.equip.EquipLevelUPItemVO;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.PacketException;
import com.mdy.dzs.game.exception.RoleEquipException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

public class RoleEquipActionImpl extends ApplicationAwareAction implements RoleEquipAction {

	private List<Integer> propIndAry = Arrays.asList(21, 22, 23, 24, 77, 78);

	@Override
	public List<Serializable> sellEquip(String acc, List<Integer> idAry) throws BizException {
		if (idAry.size() == 0) {
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_SELL_NULL);
		}
		Role role = roleAO().queryExistAccount(acc);

		int tSilver = 0;
		for (int ind = 0; ind < idAry.size(); ind++) {
			int cid = idAry.get(ind);
			Equip re = roleEquipAO().queryExistEquipById(role.getId(), cid);
			Item data = cacheManager().getExistValueByKey(Item.class, re.getResId());
			if (data.getSale() == 0) {
				throw PacketException.getException(PacketException.EXCE_ITEM_NOT_SELL, data.getId());
			}
			packetAO().removeCardEquipGongYuanById(role, Packet.POS_EQUIP, cid, RoleItemLog.SYS_装备_出售);
			tSilver += re.getSilver();
		}
		packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_银币, tSilver, RoleItemLog.SYS_装备_出售, "");
		return Arrays.asList((Serializable) Arrays.asList(tSilver, role.getSilver()));
	}

	@Override
	public List<Serializable> levelUp(String acc, int id, int auto) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Equip curEquip = roleEquipAO().queryExistEquipById(doc.getId(), id);
		Item equipData = cacheManager().getExistValueByKey(Item.class, curEquip.getResId());
		// console.log('lv:',curEquip.level, (cardData.level*2));
		if (curEquip.getLevel() > (doc.getLevel() * 2)) {
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_LEVEL_MAX);
		}
		int sumCoin = 0;
		int sumLv = 0;
		List<EquipLevelUPItemVO> lvAry = new ArrayList<EquipLevelUPItemVO>();
		int endLv = auto == 1 ? (doc.getLevel() * 2) : (curEquip.getLevel() + 1);
		Map<Integer, Equipen> equipens = cacheManager().getValues(Equipen.class);
		ShangXianSheDing shangxian = cacheManager().getShangXian();
		for (int j = curEquip.getLevel(); j < endLv; j++) {
			double dcostSilver = equipens.get(j + 1).getCoin().get(curEquip.getStar() - 1);
			dcostSilver *= (equipData.getRatio() / 10000.0);
			int costSilver = (int) Math.round(dcostSilver);
			if ((sumCoin + costSilver) <= doc.getSilver()) {
				sumCoin += costSilver;
				// if(Math.random()<designCfg.equipLvUpCrit){
				VipCrit critData = cacheManager().getExistValueByKey(VipCrit.class, doc.getVip());
				List<Integer> nocrits = critData.getNocrit();
				int randNum = cacheManager().random(1, 10000);
				int critNumCnt = nocrits.get(0);
				int critLv = 1;
				for (int ind = 0; ind < nocrits.size(); ind++) {
					if (randNum <= critNumCnt)
						break;
					critNumCnt += nocrits.get(ind + 1);
					critLv++;
				}
				sumLv += critLv;
				lvAry.add(new EquipLevelUPItemVO(critLv, costSilver));
				j += (critLv - 1);
				// }else{
				// sumLv++;
				// lvAry.push({lv:1, coin:costSilver});
				// }
			}
			if ((sumLv + curEquip.getLevel()) > (doc.getLevel() * 2)
					|| (curEquip.getLevel() + sumLv) > shangxian.getZhuangbeidengji())
				break;
		}

		// var updateObj = {'$set':{},'$inc':{}};
		if (sumLv > 0) {

			curEquip.setSilver(curEquip.getSilver() + sumCoin);
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_银币, sumCoin, RoleItemLog.SYS_装备_强化,
					"" + equipData.getId());
			curEquip.setLevel(curEquip.getLevel() + sumLv);

			if (curEquip.getPos() > 0) {
				roleAO().fmtPropUpdate(doc);
			}
			roleEquipAO().update(curEquip);
			// TASK
			missionExecAO().lvUpRoleEquip(doc, curEquip.getLevel());
		}
		// console.log(updateObj);
		return Arrays.asList((Serializable) lvAry, curEquip.getLevel(), doc.getSilver(), curEquip.getSilver());
	}

	@Override
	public List<Serializable> queryPropState(String acc, int id) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Equip curEquip = roleEquipAO().queryExistEquipById(doc.getId(), id);
		Item equipData = cacheManager().getExistValueByKey(Item.class, curEquip.getResId());
		if (equipData.getPolish() == 0) {
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_NOT_POLISH);
		}
		int leftCnt = 0;
		RoleItem itemData = packetAO().queryByAccItemId(doc.getId(), Constants.equipPropItem, Packet.POS_BAG);
		if (itemData != null)
			leftCnt = itemData.getItemCnt();
		List<Serializable> res = new ArrayList<Serializable>();
		res.addAll(createPropVO(doc, curEquip));
		res.add(leftCnt);
		return res;
	}

	@Override
	public List<Serializable> changeProp(String acc, int id, int type, int propCnt) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Equip curEquip = roleEquipAO().queryExistEquipById(doc.getId(), id);
		Item equipData = cacheManager().getExistValueByKey(Item.class, curEquip.getResId());
		if (equipData.getPolish() == 0) {
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_NOT_POLISH);
		}

		// 验证属性是否已打最大
		boolean allReachMax = true;
		Map<Integer, Integer> maxList = new HashMap<Integer, Integer>();
		boolean check = true;
		for (int i = 0; i < propIndAry.size(); i++) {// 遍历六个属性
			int maxVal = (int) Math.floor(equipData.getArrBeginning().get(i) * (1 + curEquip.getLevel() / 10.0));
			maxList.put(propIndAry.get(i), maxVal);
			if (equipData.getArrBeginning().get(i) == 0 || !check)
				continue;
			Prop propData = cacheManager().findPropByIdx(curEquip.getProps(), propIndAry.get(i));
			int propVal = propData != null ? propData.getVal() : 0;
			if (propVal < maxVal) {
				allReachMax = false;
				check = false;
				;
			}
		}
		if (allReachMax) {
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_PROPS_MAX);
		}

		// 1.check enough item
		if (propCnt != 1 && propCnt != 10) {
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_PROPS_CNT_ERROR);
		}
		Baptize propObj = cacheManager().getExistValueByKey(Baptize.class, type);
		if (propObj == null) {
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_PROPS_TYPE_ERROR);
		}
		List<Integer> numAry = propObj.getArrSilver();
		if (numAry.get(2) > 0) {
			packetAO().removeItemMustEnough(doc, 7, Constants.equipPropItem, numAry.get(2) * propCnt,
					RoleItemLog.SYS_装备_属性洗练, "" + equipData.getId());
		}
		if (numAry.get(0) > 0) {
			packetAO().removeItemMustEnough(doc, 0, 2, numAry.get(0) * propCnt, RoleItemLog.SYS_装备_属性洗练,
					"" + equipData.getId());
		}
		if (numAry.get(1) > 0) {
			packetAO().removeItemMustEnough(doc, 0, 1, numAry.get(1) * propCnt, RoleItemLog.SYS_装备_属性洗练,
					"" + equipData.getId());
		}
		//
		// 2.random get prop
		// AttInd.Life, AttInd.Attack, AttInd.Defense,
		// AttInd.DefenM, AttInd.FHarmV,AttInd.FHarmDV];
		// console.log('propObj:', propObj);
		List<Integer> rangeAry = null;
		curEquip.setPropsWait(new ArrayList<Prop>());
		Map<Integer, Prop> newWaits = new HashMap<Integer, Prop>();
		for (int m = 0; m < propCnt; m++) {
			for (int i = 0; i < propIndAry.size(); i++) {
				if (equipData.getArrBeginning().get(i) == 0)
					continue;
				;
				List<Integer> probAry = propObj.getPro();
				int probVal = (int) Math.round(Math.random() * 10000);
				int sumProb = 0;
				for (int j = 0; j < probAry.size(); j++) {
					sumProb += probAry.get(j);
					if (probVal < sumProb) {
						rangeAry = propObj.getExtend(j + 1);
						break;
					}
				}
				if (rangeAry == null || rangeAry.size() < 2)
					continue;
				// console.log(probVal, sumProb);
				int propInd = propIndAry.get(i);
				int propVal = cacheManager().random(rangeAry.get(0), rangeAry.get(1));
				propVal = (int) Math.floor(propVal * propObj.getFactro().get(i) * 0.0001f);
				Prop prop = newWaits.get(propInd);
				if (prop == null) {
					prop = new Prop(propInd, 0);
					newWaits.put(propInd, prop);
				}
				prop.setVal(prop.getVal() + propVal);
			}
		}

		for (Entry<Integer, Prop> entry : newWaits.entrySet()) {
			int propInd = entry.getKey();
			int propVal = entry.getValue().getVal();
			Prop propSelItem = cacheManager().findPropByIdx(curEquip.getProps(), propInd);
			// console.log('rangeAry:', rangeAry, propVal);
			int newVal = propVal;
			boolean bAdd = false;
			if (propSelItem != null || equipData.getArrNature().indexOf(propInd) >= 0) {
				int oldVal = propSelItem != null ? propSelItem.getVal() : 0;
				if ((oldVal + propVal) < 0)
					newVal = -oldVal;
				else if (oldVal + propVal > maxList.get(propInd)) {
					newVal = maxList.get(propInd) - oldVal;
				} else {
					newVal = propVal;
				}
				bAdd = true;
			} else if (propVal > 0) {
				newVal = propVal;
				bAdd = true;
			}
			if (bAdd) {
				curEquip.getPropsWait().add(new Prop(propInd, newVal));
			}
		}
		roleEquipAO().updateProps(curEquip);
		// TASK
		missionExecAO().changeRoleEquipProp(doc, propCnt);

		int leftCnt = 0;
		RoleItem itemData = packetAO().queryByAccItemId(doc.getId(), Constants.equipPropItem, Packet.POS_BAG);
		if (itemData != null)
			leftCnt = itemData.getItemCnt();
		List<Serializable> res = new ArrayList<Serializable>();
		res.addAll(createPropVO(doc, curEquip));
		res.add(doc.getSilver());
		res.add(doc.getGold());
		res.add(leftCnt);
		return res;
	}

	@Override
	public List<Serializable> replaceProp(String acc, int id) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Equip curEquip = roleEquipAO().queryExistEquipById(doc.getId(), id);
		Item equipData = cacheManager().getExistValueByKey(Item.class, curEquip.getResId());
		if (equipData.getPolish() == 0) {
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_NOT_POLISH);
		}

		// var updateObj = {'$set':{}};
		List<Integer> attAry = new ArrayList<Integer>();
		List<Integer> addValAry = new ArrayList<Integer>();
		List<Prop> propsWait = curEquip.getPropsWait();
		List<Prop> props = curEquip.getProps();
		for (Prop item : propsWait) {
			Prop propItem = cacheManager().findPropByIdx(curEquip.getProps(), item.getIdx());
			int limitVal = equipData.getArrBeginning().get(propIndAry.indexOf(item.getIdx()));
			limitVal = (int) Math.round(limitVal * (1 + curEquip.getLevel() / 10.0));
			boolean bAdd = false;
			int bAddVal = 0;
			if (propItem == null) {
				props.add(new Prop(item.getIdx(), item.getVal()));
				bAdd = true;
				bAddVal = item.getVal();
			} else if ((propItem.getVal() + item.getVal()) < limitVal && (propItem.getVal() + item.getVal()) >= 0) {
				propItem.setVal(propItem.getVal() + item.getVal());
				bAdd = true;
				bAddVal = item.getVal();
			} else if ((propItem.getVal() + item.getVal()) < 0) {
				bAdd = true;
				bAddVal = -propItem.getVal();
				propItem.setVal(0);
			} else if ((propItem.getVal() + item.getVal()) >= limitVal) {
				bAdd = true;
				bAddVal = limitVal - propItem.getVal();
				propItem.setVal(limitVal);
			}

			if (curEquip.getPos() > 0 && bAdd) {
				attAry.add(item.getIdx());
				addValAry.add(bAddVal);
			}
		}
		;
		if (curEquip.getPos() > 0) {
			roleAO().fmtPropUpdate(doc);
		}
		curEquip.setPropsWait(new ArrayList<Prop>());
		roleEquipAO().updateProps(curEquip);

		List<Serializable> res = new ArrayList<Serializable>();
		res.addAll(createPropVO(doc, curEquip));
		res.add(doc.getSilver());
		res.add(doc.getGold());
		return res;
	}

	/**
	 * 创建打包属性
	 * 
	 * @param doc
	 * @param curEquip
	 * @return
	 * @throws BizException
	 */
	private List<Serializable> createPropVO(Role doc, Equip curEquip) throws BizException {

		int propIndAry[] = { 21, 22, 23, 24, 77, 78 };
		List<Integer> pIndAry = new ArrayList<Integer>();
		List<Integer> pBaseAry = new ArrayList<Integer>();
		List<Integer> pPropAry = new ArrayList<Integer>();
		List<Integer> pWaitAry = new ArrayList<Integer>();
		CalcRoleEquip calc = calcManager().getCalcEquip(curEquip.getResId());
		Map<Integer, Integer> base = calc.getValues(curEquip.getLevel(), null);
		for (int i = 0; i < propIndAry.length; i++) {
			int curProp = propIndAry[i];
			Prop propData = cacheManager().findPropByIdx(curEquip.getProps(), curProp);
			Prop propsWaitData = cacheManager().findPropByIdx(curEquip.getPropsWait(), curProp);
			Integer ibase = base.get(curProp);
			if (ibase != null || propData != null || propsWaitData != null) {
				pIndAry.add(curProp);
				pBaseAry.add(ibase != null ? ibase : 0);
				pPropAry.add(propData != null ? propData.getVal() : 0);
				pWaitAry.add(propsWaitData != null ? propsWaitData.getVal() : 0);
			}
		}
		return Arrays.asList((Serializable) pIndAry, (Serializable) pBaseAry, (Serializable) pPropAry,
				(Serializable) pWaitAry);
	}
}
