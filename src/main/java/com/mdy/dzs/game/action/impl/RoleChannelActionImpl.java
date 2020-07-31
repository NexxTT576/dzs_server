package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.card.ShangXianSheDing;
import com.mdy.dzs.data.domain.role.JingMai;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleChannelAction;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.battle.RoleBattle;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.RoleChannel;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleChannelException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

public class RoleChannelActionImpl extends ApplicationAwareAction implements RoleChannelAction {

	@Override
	public List<Serializable> queryRoleChannelInfo(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleBattle rb = roleBattleAO().queryByRoleId(doc.getId());
		RoleChannel channel = roleChannelAO().queryExistAccount(doc.getId());

		int star = rb.getBattleTotalStars() - channel.getChanStarCnt();
		int itemCnt = packetAO().getNumberByTypeId(doc, 7, Constants.channelItemId);
		int level = 0;
		int point = 0;

		if (channel.getChanType() != 0) {
			ShangXianSheDing shangxian = cacheManager().getShangXian();
			int channelLvLimit = shangxian.getJingmaidengji();
			if (!(channel.getChanLv() >= channelLvLimit && channel.getChanPt() == 8)) {
				if (channel.getChanPt() < 8) {
					level = channel.getChanLv();
					point = channel.getChanPt() + 1;
				} else {
					level = channel.getChanLv() + 1;
					point = 1;
				}
			}
		} else {
			level = 1;
			point = 1;
		}
		return Arrays.asList(star, point, doc.getGold(), doc.getSilver(), level, (Serializable) channel.getChanType(),
				itemCnt, channel.getChanReSetCnt());
	}

	@Override
	public List<Serializable> levelUp(String acc, int t) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleChannel channel = roleChannelAO().queryExistAccount(doc.getId());
		RoleBattle rb = roleBattleAO().queryByRoleId(doc.getId());

		if (t == 0 && channel.getChanType() == 0) {
			// throw BaseException.getGlobalException("t not exist");
			throw BaseException.getException(RoleChannelException.EXCE_ROLECHANNEL_T_NOT_EXIST);
		}
		if (t != 0 && channel.getChanType() != 0 && t != channel.getChanType()) {
			// throw BaseException.getGlobalException("t error");
			throw BaseException.getException(RoleChannelException.EXCE_ROLECHANNEL_T_ERROR);
		}
		if (t != 0 && channel.getChanType() == 0) {
			channel.setChanType(t);
			;
		}

		ShangXianSheDing shangxian = cacheManager().getShangXian();
		int channelLvLimit = shangxian.getJingmaidengji();
		if (channel.getChanLv() >= channelLvLimit && channel.getChanPt() == 8) {
			// throw BaseException.getGlobalException("level reach limit");
			throw BaseException.getException(RoleChannelException.EXCE_ROLECHANNEL_LEVEL_LIMIT);
		}

		if (channel.getChanPt() == 0) {
			channel.setChanPt(1);
			channel.setChanLv(1);
		} else if (channel.getChanPt() == 8) {
			channel.setChanPt(1);
			channel.setChanLv(channel.getChanLv() + 1);
		} else {
			channel.setChanPt(channel.getChanPt() + 1);
		}

		JingMai xueData = null;
		Map<Integer, JingMai> jingmais = cacheManager().getValues(JingMai.class);
		for (Entry<Integer, JingMai> entry : jingmais.entrySet()) {
			JingMai temp = entry.getValue();
			if (temp.getType() == channel.getChanType() && temp.getOrder() == channel.getChanPt()) {
				xueData = temp;
				break;
			}
		}

		if (xueData == null) {
			// throw BaseException.getGlobalException("data not exist");
			throw BaseException.getException(RoleChannelException.EXCE_ROLECHANNEL_DATA_NOT_EXIST);
		}
		packetAO().removeItemMustEnough(doc, 0, 2, xueData.getArrCoin().get(channel.getChanLv() - 1),
				RoleItemLog.SYS_经脉_提升, "" + xueData.getId());

		int addStar = xueData.getArrStar().get(channel.getChanLv() - 1);
		if (rb.getBattleTotalStars() - channel.getChanStarCnt() - addStar >= 0) {
			channel.setChanStarCnt(channel.getChanStarCnt() + addStar);
		} else {
			// throw BaseException.getGlobalException("star not enough");
			throw BaseException.getException(RoleChannelException.EXCE_ROLECHANNEL_STAR_NOT_ENOUGH);
		}

		// 更新属性数组 chanAttrAry
		List<Integer> attIndAry = new ArrayList<Integer>();
		List<Integer> attValAry = new ArrayList<Integer>();
		int nature = xueData.getNature();
		int addVal = xueData.getArrValue().get(channel.getChanLv() - 1);
		Prop attrData = cacheManager().findPropByIdx(channel.getChanAttrAry(), nature);
		if (attrData == null) {
			channel.getChanAttrAry().add(new Prop(nature, addVal));
		} else {
			attrData.setVal(attrData.getVal() + addVal);
		}
		attIndAry.add(nature);
		attValAry.add(addVal);
		// 更新属性到主角 TODO 会影响战斗
		RoleCard mainCard = roleCardAO().queryExistCardById(doc, doc.getFmtMainCardID());
		Prop indProp = cacheManager().findPropByIdx(mainCard.getProps(), nature);
		if (indProp != null) {
			indProp.setVal(indProp.getVal() + addVal);
		} else {
			mainCard.getProps().add(new Prop(nature, addVal));
		}
		int star = rb.getBattleTotalStars() - channel.getChanStarCnt();
		int rtLevel = 0;
		int rtPoint = 0;
		if (!(channel.getChanLv() == channelLvLimit && channel.getChanPt() == 8)) {
			if (channel.getChanPt() < 8) {
				rtLevel = channel.getChanLv();
				rtPoint = channel.getChanPt() + 1;
			} else {
				rtLevel = channel.getChanLv() + 1;
				rtPoint = 1;
			}
		}
		// save battle star
		roleBattleAO().update(rb);
		roleChannelAO().update(channel);
		roleCardAO().update(mainCard);
		roleAO().fmtPropUpdate(doc);
		// TASK
		missionExecAO().changeRoleChennel(doc, channel);
		return Arrays.asList(star, (Serializable) doc.getSilver(), rtLevel, rtPoint);

	}

	@Override
	public List<Serializable> reset(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleChannel channel = roleChannelAO().queryExistAccount(doc.getId());
		RoleBattle rb = roleBattleAO().queryByRoleId(doc.getId());

		int resetCnt = channel.getChanReSetCnt();
		if (resetCnt < 3) {
			resetCnt++;
		} else {
			if (packetAO().getNumberByTypeId(doc, 7, Constants.channelItemId) == 0) {
				packetAO().removeItemMustEnough(doc, 0, 1, Constants.channelGold, RoleItemLog.SYS_经脉_洗经伐脉, "");
			} else {
				packetAO().removeItemMustEnough(doc, 7, Constants.channelItemId, 1, RoleItemLog.SYS_经脉_洗经伐脉, "");
			}
		}

		RoleCard mainCard = roleCardAO().queryExistCardById(doc, doc.getFmtMainCardID());
		Map<Integer, Integer> chanAttrMap = new HashMap<Integer, Integer>();
		for (Prop p : channel.getChanAttrAry()) {
			chanAttrMap.put(p.getIdx(), p.getVal());
		}
		int le = mainCard.getProps().size() - 1;

		while (le >= 0) {
			Prop p = mainCard.getProps().get(le);
			Integer val = chanAttrMap.get(p.getIdx());
			if (val != null)
				p.setVal(p.getVal() - val);
			if (p.getVal() <= 0) {
				mainCard.getProps().remove(le);
			}
			le--;
		}
		;
		channel.setChanType(0);
		channel.setChanLv(0);
		channel.setChanPt(0);
		channel.setChanAttrAry(new ArrayList<Prop>());
		channel.setChanStarCnt(0);
		channel.setChanReSetCnt(resetCnt);
		roleCardAO().update(mainCard);
		roleChannelAO().update(channel);

		roleAO().fmtPropUpdate(doc);

		int itemCnt = packetAO().getNumberByTypeId(doc, 7, Constants.channelItemId);

		return Arrays.asList((Serializable) rb.getBattleTotalStars(), doc.getGold(), itemCnt);
	}

}
