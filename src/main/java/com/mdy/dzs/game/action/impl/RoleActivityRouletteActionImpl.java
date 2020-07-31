package com.mdy.dzs.game.action.impl;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.domain.activity.roulette.RouletteItem;
import com.mdy.dzs.data.domain.activity.roulette.RouletteProb;
import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleActivityRouletteAction;
import com.mdy.dzs.game.domain.activity.roulettegame.RoleActivityRoulette;
import com.mdy.dzs.game.domain.activity.roulettegame.GetCreditVO;
import com.mdy.dzs.game.domain.activity.roulettegame.PreviewVO;
import com.mdy.dzs.game.domain.activity.roulettegame.RoleDataStateVO;
import com.mdy.dzs.game.domain.activity.roulettegame.Roulette;
import com.mdy.dzs.game.domain.activity.roulettegame.RouletteEnterVO;
import com.mdy.dzs.game.domain.activity.roulettegame.RouletteOpVO;
import com.mdy.dzs.game.domain.activity.roulettegame.RouletteStateVO;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.ActivityException;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.sharp.container.biz.BizException;

public class RoleActivityRouletteActionImpl extends ApplicationAwareAction implements RoleActivityRouletteAction {

	@Override
	public RouletteEnterVO rouletteEnter(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		// 开启等级检查
		Open openData = cacheManager().getExistValueByKey(Open.class, 46);// key:system
		if (doc.getLevel() < openData.getLevel().get(0)) {
			throw BaseException.getException(ActivityException.EXCE_ROULETTE_LEVEL_CONTROL);
		}

		Roulette activeData = roleActivityRouletteAO().getCurActivityData();// 活动配置
		if (activeData == null) {
			throw BaseException.getException(ActivityException.EXCE_ROULETTE_NOT_OPEN);
		}
		RoleActivityRoulette rRoleDoc = roleActivityRouletteAO().queryAndAdd(doc.getId(), activeData);
		RoleDataStateVO roleDataState = roleActivityRouletteAO().tidyRoleState(rRoleDoc, activeData);
		List<RouletteStateVO> rouletteState = roleActivityRouletteAO().tidyRouletteState();
		return new RouletteEnterVO(roleDataState, rouletteState, activeData);
	}

	@Override
	public PreviewVO roulettePreview(String acc, int id) throws BizException {
		return roleActivityRouletteAO().getPreviewData(id);
	}

	@Override
	public RouletteOpVO rouletteOp(String acc, int num) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		List<PacketExtend> checkBag = packetAO().checkBag(doc, 22);
		if (checkBag.size() > 0) {
			return new RouletteOpVO(new ArrayList<GiftItem>(), 0, 0, 0, 0, checkBag);
		}
		Roulette activeData = roleActivityRouletteAO().getCurActivityData();// 活动配置
		if (activeData == null) {
			throw BaseException.getException(ActivityException.EXCE_ROULETTE_NOT_OPEN);
		}
		RoleActivityRoulette rRoleDoc = roleActivityRouletteAO().queryAndAdd(doc.getId(), activeData);

		if (num > rRoleDoc.getSurTimes()) {
			throw BaseException.getException(ActivityException.EXCE_ROULETTE_TIMES_NOT_ENOUGH);
		}
		if (num != 1 && num != 10) {
			throw BaseException.getException(ActivityException.EXCE_ROULETTE_PARA_ERROR);
		}
		if (activeData.getType() == Roulette.直接购买次数) {
			int needGold = num * activeData.getPrice();
			if (rRoleDoc.getFreeTimes() > 0) {
				needGold -= rRoleDoc.getFreeTimes() * activeData.getPrice();
			}
			if (doc.getGold() < needGold) {
				throw BaseException.getException(ActivityException.EXCE_ROULETTE_GOLD_NOTENOUGH);
			}
		}

		List<GiftItem> itemAry = new ArrayList<GiftItem>();
		int position = 0;
		int gold = 0;
		for (int i = 0; i < num; i++) {
			if (rRoleDoc.getSurTimes() <= 0) {
				break;
			}
			RouletteProb probData = roleActivityRouletteAO().getProbData(rRoleDoc);
			int probNum = cacheManager().random(1, 10000);
			int index = 0;
			int probCnt = 0;
			for (int ind = 0; ind < probData.getProb().size(); ind++) {
				probCnt += probData.getProb().get(ind);
				if (probNum <= probCnt) {
					index = ind;
					break;
				}
			}
			position = probData.getPosition().get(index); // 圆盘位置

			RouletteItem itemData = cacheManager().getExistValueByKey(RouletteItem.class,
					(roleActivityRouletteAO().getShowDatas()).get(position + ""));
			int itemInd = roleActivityRouletteAO().getProbItemInd(itemData); // 概率获得物品

			itemAry.add(new GiftItem(itemData.getItemType().get(itemInd), itemData.getItemId().get(itemInd),
					itemData.getItemCnt().get(itemInd)));
			packetAO().addItem(doc, itemData.getItemType().get(itemInd), itemData.getItemId().get(itemInd),
					itemData.getItemCnt().get(itemInd), RoleItemLog.SYS_精彩活动_皇宫探宝, "");

			if (activeData.getType() == Roulette.直接购买次数 && rRoleDoc.getFreeTimes() <= 0) {
				gold += activeData.getPrice();
			}
			roleActivityRouletteAO().udpateRouletteRoleDoc(rRoleDoc, probData, position, activeData);
		}
		roleActivityRouletteAO().updateRouletteData(rRoleDoc);
		if (gold > 0) {
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, gold, RoleItemLog.SYS_精彩活动_皇宫探宝_探宝花费,
					"");
		}

		return new RouletteOpVO(itemAry, rRoleDoc.getSurTimes(), position, rRoleDoc.getFreeTimes(),
				rRoleDoc.getCredit(), new ArrayList<PacketExtend>());
	}

	@Override
	public GetCreditVO rouletteGetCredit(String acc, int index) throws BizException {
		if (index > 3 || index < 1) {// 箱子不存在
			throw BaseException.getException(ActivityException.EXCE_ROULETTE_REWARD_NOT_EXIST);
		}
		Role doc = roleAO().queryExistAccount(acc);
		List<PacketExtend> checkBag = packetAO().checkBag(doc, 22);
		if (checkBag.size() > 0) {
			return new GetCreditVO(new ArrayList<Integer>(), checkBag);
		}

		Roulette activeData = roleActivityRouletteAO().getCurActivityData();// 活动配置
		if (activeData == null) {
			throw BaseException.getException(ActivityException.EXCE_ROULETTE_NOT_OPEN);
		}
		RoleActivityRoulette rRoleDoc = roleActivityRouletteAO().queryAndAdd(doc.getId(), activeData);

		if (rRoleDoc.getGetBox().contains(index)) {// 领取过
			throw BaseException.getException(ActivityException.EXCE_ROULETTE_HAS_GOT);
		}
		int score = activeData.getScore().get(index - 1);
		if (rRoleDoc.getCredit() < score) {// 积分不够
			throw BaseException.getException(ActivityException.EXCE_ROULETTE_CREDIT_NOT_ENOUGH);
		}
		// 发奖
		List<Integer> typeList = activeData.getRewardTypeByIndex(index);
		List<Integer> idList = activeData.getRewardIdByIndex(index);
		List<Integer> cntList = activeData.getRewardCntByIndex(index);
		for (int i = 0; i < typeList.size(); i++) {
			packetAO().addItem(doc, typeList.get(i), idList.get(i), cntList.get(i), RoleItemLog.SYS_精彩活动_皇宫探宝_积分奖, "");
		}
		// 数据更新
		rRoleDoc.getGetBox().add(index);
		roleActivityRouletteAO().updateGetBox(rRoleDoc);

		return new GetCreditVO(rRoleDoc.getGetBox(), new ArrayList<PacketExtend>());
	}
}
