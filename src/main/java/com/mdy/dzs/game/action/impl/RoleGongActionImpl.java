package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.card.ShangXianSheDing;
import com.mdy.dzs.data.domain.gong.KongFu;
import com.mdy.dzs.data.domain.item.ConsumesItem;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleGongAction;
import com.mdy.dzs.game.domain.calcattack.CalcRoleGong;
import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.dzs.game.domain.gong.vo.RoleGongRefineVO;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.UpdateInfoVO;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleGongException;
import com.mdy.dzs.game.exception.SystemException;
import com.mdy.sharp.container.biz.BizException;

public class RoleGongActionImpl extends ApplicationAwareAction implements RoleGongAction {

	@Override
	public List<Serializable> levelUp(String acc, List<Integer> idAry, int opAct) throws BizException {

		if (opAct != 1 && opAct != 2) {
			throw BaseException.getException(SystemException.EXCE_PARAM_ERROR, "op val error");
		}
		Role doc = roleAO().queryExistAccount(acc);

		int expSum = 0; // 被吞卡的经验总和
		RoleGong mCard = null; // 当前卡
		int mcStar = 0; // 当前卡品质
		int mcExp = 0; // 当前卡的经验
		int mcLv = 0; // 当前卡的等级
		int leftExp = 0; // 剩余经验

		ShangXianSheDing shangxian = cacheManager().getShangXian();

		for (int i = 0; i < idAry.size(); i++) {
			int id = idAry.get(i);
			RoleGong rg = roleGongAO().queryExistGongById(doc.getId(), id);
			if (rg.getPos() > 0 && i != 0) {
				throw BaseException.getException(RoleGongException.EXCE_GONG_LEVEL_NOT_IN_LINEUP);
			}
			KongFu kongfu = cacheManager().getExistValueByKey(KongFu.class, rg.getLevel());
			List<Integer> levelExp = kongfu.getSumexp(); // 卡的当前等级的最低经验值
			if (i > 0) {
				Item itemData = cacheManager().getExistValueByKey(Item.class, rg.getResId());
				int initExp = itemData.getExp(); // 被吞卡的初始经验
				int cardExp = levelExp.get(rg.getStar() - 1) + rg.getCurExp() + initExp; // 被吞卡的总经验值
				expSum += cardExp; // 所有被吞卡的总经验
			} else {
				mCard = rg;
				mcStar = rg.getStar();
				mcLv = rg.getLevel();
				mcExp = levelExp.get(rg.getStar() - 1) + rg.getCurExp();
				leftExp = rg.getCurExp();
			}
		}
		idAry.remove(0);

		if (mcLv >= shangxian.getWuxuedengji()) {
			throw BaseException.getException(RoleGongException.EXCE_GONG_LEVEL_MAX);
		}
		// console.log('expSum:', expSum,mcStar,mcLv,mcExp);
		mcExp += expSum; // 加上副卡经验之后的全部经验
		int silver = (mcStar - 1) * 5 * expSum; // 公式 银币花费=（内外功品质-1）*5*可提升经验
		if (opAct == 2) {
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_银币, silver, RoleItemLog.SYS_武学_升级, "");
			// 经验增加完成 ，将副卡从库中删除
			for (Integer cid : idAry) {
				packetAO().removeCardEquipGongYuanById(doc, Packet.POS_GONG, cid, RoleItemLog.SYS_武学_升级);
			}

		}
		Item mcardData = cacheManager().getExistValueByKey(Item.class, mCard.getResId()); // 当前卡的配置数据
		int addAry[] = { 0, 0, 0, 0 };
		int startLv = mcLv;// 开始等级
		int endLv = mcLv;
		Map<Integer, KongFu> kongfus = cacheManager().getValues(KongFu.class);
		int curLimit = kongfus.get(mcLv).getExp().get(mcStar - 1); // 当前等级的经验上限值
		// console.log('limit:', mcLv,mcStar, designData['kongfu'][mcLv].exp, curLimit);
		for (int j = mcLv; j < kongfus.size(); j++) { // 循环配置的所有等级
			// lvSumExp = designData['kongfu'][j-1].sumexp[mcStar-1]; //达到目前等级的总经验值
			// nxLvExp= designData['kongfu'][j-1].exp[mcStar-1]; //当前等级升级的所需的curExp经验值
			int toLevel = j + 1;
			KongFu kongfu = kongfus.get(toLevel - 1);
			int lvSumExp = kongfu.getSumexp().get(mcStar - 1); // 达到目前等级的总经验值
			int nxLvExp = kongfu.getExp().get(mcStar - 1); // 当前等级升级的所需的curExp经验值
			// console.log('lvSumExp/nxLvExp:',lvSumExp,nxLvExp,mcExp,j);
			if (toLevel > shangxian.getWuxuedengji()) {
				break;
			}
			if ((lvSumExp + nxLvExp) <= mcExp) { // 能升级的最大等级
				List<Integer> addDataAry = mcardData.getArrAddition();
				// console.log('nx lv:',j, mcardData, addDataAry);
				for (int i = 0; i < 4; i++) {
					if (addDataAry.size() < (i + 1) || addDataAry.get(i) == 0)
						continue;
					addAry[i] += addDataAry.get(i) * (j + 1 - mCard.getLevel());
				}
				KongFu kongfu2 = kongfus.get(toLevel);
				curLimit = kongfu2.getExp().get(mcStar - 1);// 当前等级的经验上限值
				endLv = j + 1;
				leftExp = mcExp - lvSumExp - nxLvExp;
				if (opAct == 2) {
					mCard.setLevel(toLevel);
					mCard.setCurExp(leftExp);
				}
			} else {// 不升级，加经验
				leftExp = mcExp - lvSumExp;
				if (opAct == 2) {
					mCard.setCurExp(leftExp);
					roleGongAO().update(mCard);
				}
				break;
			}
		}
		if (opAct == 2) {
			roleGongAO().update(mCard);
			// TASK
			missionExecAO().lvUpRoleGong(doc, mCard.getLevel());
			if (mCard.getPos() > 0)
				roleAO().fmtPropUpdate(doc);
		}

		Map<String, Object> info = new HashMap<String, Object>();
		CalcRoleGong calc = calcManager().getCalcGong(mCard.getResId());

		info.put("resId", mcardData.getId());
		info.put("lv", endLv);
		info.put("star", mCard.getStar());
		info.put("baseRate", calc.getBaseRate(mCard.getLevel()));
		info.put("exp", (leftExp > 0) ? leftExp : 0);
		info.put("limit", curLimit);
		info.put("add", addAry);
		info.put("cost", silver);

		return Arrays.asList((Serializable) info, doc.getSilver());
	}

	@Override
	public RoleGongRefineVO propUp(String acc, int id, int op) throws BizException {
		RoleGongRefineVO vo = new RoleGongRefineVO();
		do {
			Role role = roleAO().queryExistAccount(acc);
			RoleGong rgong = roleGongAO().queryExistGongById(role.getId(), id);
			CalcRoleGong calcRG = calcManager().getCalcGong(rgong.getResId());
			roleGongAO().updateRefineInfo(role, rgong, calcRG, vo);

			if (op == 1) {
				break;
			}

			if (vo.getAllow() == 0) {// 不能精炼
				throw BaseException.getException(RoleGongException.EXCE_GONG_REFINE_NOTALLOW, rgong.getId(),
						rgong.getResId());
			}
			if (vo.getMax() == 1) {// 最大上限
				throw BaseException.getException(RoleGongException.EXCE_GONG_REFINE_MAX, rgong.getId(),
						rgong.getPropsN());
			}
			// 扣除
			String comments = rgong.getId() + ":" + rgong.getPropsN() + "=>" + (rgong.getPropsN() + 1);
			for (ConsumesItem citem : vo.getItems()) {
				packetAO().removeItemMustEnough(role, citem.getT(), citem.getId(), citem.getN2(), RoleItemLog.SYS_武学_精炼,
						comments);
			}
			packetAO().removeItemMustEnough(role, Packet.POS_ATTR, Packet.ATTR_银币, vo.getSilver(),
					RoleItemLog.SYS_武学_精炼, comments);

			rgong.setPropsN(rgong.getPropsN() + 1);
			roleGongAO().update(rgong);
			roleAO().fmtPropUpdate(role);

			roleGongAO().updateRefineInfo(role, rgong, calcRG, vo);
			vo.setUpdate(new UpdateInfoVO(role));
		} while (false);
		return vo;
	}

	@Override
	public List<Serializable> sellGong(String acc, List<Integer> idAry) throws BizException {
		if (idAry.size() == 0) {
			throw BaseException.getException(RoleGongException.EXCE_GONG_SELL_NULL);
		}
		Role role = roleAO().queryExistAccount(acc);

		int tSilver = 0;
		for (int ind = 0; ind < idAry.size(); ind++) {
			int cid = idAry.get(ind);
			RoleGong rg = roleGongAO().queryExistGongById(role.getId(), cid);
			if (rg.getPos() > 0) {
				throw BaseException.getException(RoleGongException.EXCE_GONG_SELL_NOT_IN_LINEUP);
			}
			packetAO().removeCardEquipGongYuanById(role, Packet.POS_GONG, cid, RoleItemLog.SYS_武学_出售);
			Item itemData = cacheManager().getExistValueByKey(Item.class, rg.getResId());
			KongFu kongfu = cacheManager().getExistValueByKey(KongFu.class, rg.getLevel());
			int exp = kongfu.getSumexp().get(rg.getStar() - 1);
			tSilver += itemData.getPrice() + (rg.getStar() - 1) * 5 * exp;
			;
		}
		packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_银币, tSilver, RoleItemLog.SYS_武学_出售, "");
		return Arrays.asList(tSilver, (Serializable) role.getSilver());

	}

}
