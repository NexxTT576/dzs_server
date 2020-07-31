package com.mdy.dzs.game.action.impl;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.data.domain.activity.exchange.ActivityExchangeExp;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.ActivityExchAction;
import com.mdy.dzs.game.domain.activity.ActivityConfig;
import com.mdy.dzs.game.domain.activity.exchange.ActivityExchange;
import com.mdy.dzs.game.domain.activity.exchange.CActExch;
import com.mdy.dzs.game.domain.activity.exchange.CActExchAward;
import com.mdy.dzs.game.domain.activity.exchange.CActExchAwardItem;
import com.mdy.dzs.game.domain.activity.exchange.CActExchExp;
import com.mdy.dzs.game.domain.activity.exchange.CActExchList;
import com.mdy.dzs.game.domain.activity.exchange.CActExchListItem;
import com.mdy.dzs.game.domain.activity.exchange.CActExchRefresh;
import com.mdy.dzs.game.domain.activity.exchange.RoleActivityExch;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.ActivityException;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

public class ActivityExchActionImpl extends ApplicationAwareAction implements ActivityExchAction {

	@Override
	public CActExchList list(String acc) throws BizException {
		// roleActivityAO().activityVerifyException(ActivityConfig.ACTIVITY_限时兑换);
		Role doc = roleAO().queryByAccount(acc);
		CActExchList listVO = new CActExchList();
		ActivityConfig config = roleActivityAO().getMapActivitys().get(ActivityConfig.ACTIVITY_限时兑换);
		String timeDis_[] = config.getTimeParam().split("_");
		Date startT = DateUtil.getDateTimeByString(timeDis_[0]);
		Date endT = DateUtil.getDateTimeByString(timeDis_[1]);
		listVO.setStart(startT);
		listVO.setEnd(endT);
		listVO.setNow(new Date());

		List<CActExchListItem> list = listVO.getList();
		List<ActivityExchange> aeList = roleActivityExchAO().getExchangeList();
		for (ActivityExchange activityExchange : aeList) {
			CActExchListItem item = new CActExchListItem();
			item.setId(activityExchange.getId());
			item.setTagName(activityExchange.getTagName());
			item.setExchType(activityExchange.getExchType());
			item.setIsRefresh(activityExchange.getIsRefresh());
			item.setType(activityExchange.getType());
			item.setTotalNum(activityExchange.getExchNum());

			int expId = 0;
			RoleActivityExch roleAExch = roleActivityExchAO().queryByExchId(doc.getId(), activityExchange.getId());
			roleActivityExchAO().refreshDay(roleAExch);
			if (roleAExch != null) {
				item.setExchNum(activityExchange.getExchNum() - roleAExch.getExchNum());
				item.setRefFreeNum(activityExchange.getRefFree() - roleAExch.getRefFreeNum());
				expId = roleAExch.getExpId();
				if (expId == 0) {
					expId = roleActivityExchAO().randomExpId(activityExchange);
					roleActivityExchAO().updateExpId(roleAExch.getId(), roleAExch.getRefreshNum(), expId,
							roleAExch.getRefFreeNum());
				}
				int gold = 0;
				if (activityExchange.getRefType() == 1) {
					item.setRefGold(activityExchange.getRefGold());
				} else if (activityExchange.getRefType() == 2) {
					gold = activityExchange.getRefGold() + roleAExch.getRefreshNum() * activityExchange.getAccumNum();
					if (gold > activityExchange.getAccumLimit()) {
						gold = activityExchange.getAccumLimit();
					}
					item.setRefGold(gold);
				}
			} else {
				item.setExchNum(activityExchange.getExchNum());
				item.setRefFreeNum(activityExchange.getRefFree());
				item.setRefGold(activityExchange.getRefGold());

				expId = roleActivityExchAO().randomExpId(activityExchange);
				// 将数据存入数据库
				roleAExch = new RoleActivityExch(doc.getId(), expId, activityExchange.getId(), 0,
						activityExchange.getExchType(), 0);
				roleActivityExchAO().add(roleAExch);
			}
			ActivityExchangeExp exExp = cacheManager().getExistValueByKey(ActivityExchangeExp.class, expId);
			CActExchExp cee = roleActivityExchAO().getExp(doc, exExp);
			item.setExchExp(cee);
			list.add(item);
		}
		return listVO;
	}

	@Override
	public CActExchAward award(String acc) throws BizException {
		// roleActivityAO().activityVerifyException(ActivityConfig.ACTIVITY_限时兑换);
		CActExchAward awardVO = new CActExchAward();
		List<CActExchAwardItem> list = awardVO.getList();
		List<ActivityExchange> aeList = roleActivityExchAO().getExchangeList();
		for (ActivityExchange activityExchange : aeList) {
			if (activityExchange.getIsOpen() == 1) {
				CActExchAwardItem ceaItem = new CActExchAwardItem(activityExchange.getId(),
						activityExchange.getTagName(), activityExchange.getView());
				list.add(ceaItem);
			}
		}
		return awardVO;
	}

	@Override
	public CActExchRefresh refresh(String acc, int exchId) throws BizException {
		// roleActivityAO().activityVerifyException(ActivityConfig.ACTIVITY_限时兑换);
		ActivityExchange activityExchange = roleActivityExchAO().getExchangeMap().get(exchId);
		if (activityExchange.getIsRefresh() == 0) {
			throw BaseException.getException(ActivityException.EXCE_EXCH_NOT_REFRESH);
		}
		Role doc = roleAO().queryByAccount(acc);
		RoleActivityExch roleAExch = roleActivityExchAO().queryByExchId(doc.getId(), exchId);
		roleActivityExchAO().refreshDay(roleAExch);
		// 刷新所需元宝
		int gold = 0;
		// 下次刷新元宝
		int refGold = 0;
		// 免费刷新次数
		int freeNum = activityExchange.getRefFree() - roleAExch.getRefFreeNum();
		if (freeNum <= 0) {// 免费次数小于等于0 的时候 计算下次需要的钱
			if (activityExchange.getRefType() == 1) {// 固定
				gold = activityExchange.getRefGold();
				refGold = activityExchange.getRefGold();
			} else if (activityExchange.getRefType() == 2) {// 叠加
				gold = activityExchange.getRefGold() + roleAExch.getRefreshNum() * activityExchange.getAccumNum();
				refGold = activityExchange.getRefGold()
						+ (roleAExch.getRefreshNum() + 1) * activityExchange.getAccumNum();
				if (gold > activityExchange.getAccumLimit()) {
					gold = activityExchange.getAccumLimit();
				}
				if (refGold > activityExchange.getAccumLimit()) {
					refGold = activityExchange.getAccumLimit();
				}
			}
		} else if (freeNum == 1) {// 等于1 时候 计算需要元宝刷新的元宝数
			if (activityExchange.getRefType() == 1) {// 固定
				refGold = activityExchange.getRefGold();
			} else if (activityExchange.getRefType() == 2) {// 叠加
				refGold = activityExchange.getRefGold() + roleAExch.getRefreshNum() * activityExchange.getAccumNum();
				if (refGold > activityExchange.getAccumLimit()) {
					refGold = activityExchange.getAccumLimit();
				}
			}
		}
		// 元宝刷新
		int refreshNum = roleAExch.getRefreshNum();
		// 免费刷新次数
		int refFreeNum = roleAExch.getRefFreeNum();
		if (freeNum > 0) {
			gold = 0;
			refFreeNum = roleAExch.getRefFreeNum() + 1;
		} else {
			// 扣除元宝（里面包含不足异常提示）
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, gold, RoleItemLog.SYS_限时兑换_刷新扣除, "");
			refreshNum = roleAExch.getRefreshNum() + 1;
		}

		int expId = roleActivityExchAO().randomExpId(activityExchange);

		// 刷新完存上
		roleActivityExchAO().updateExpId(roleAExch.getId(), refreshNum, expId, refFreeNum);

		CActExchRefresh refreshVO = new CActExchRefresh();

		ActivityExchangeExp exExp = cacheManager().getExistValueByKey(ActivityExchangeExp.class, expId);
		CActExchExp cee = roleActivityExchAO().getExp(doc, exExp);
		refreshVO.setExchExp(cee);
		refreshVO.setRefGold(refGold);
		refreshVO.setExchangeId(exchId);
		refreshVO.setFreeNum(activityExchange.getRefFree() - refFreeNum);
		return refreshVO;
	}

	@Override
	public CActExch exch(String acc, int exchId) throws BizException {
		// roleActivityAO().activityVerifyException(ActivityConfig.ACTIVITY_限时兑换);
		CActExch exchVO = new CActExch();
		Role doc = roleAO().queryByAccount(acc);

		RoleActivityExch roleAExch = roleActivityExchAO().queryByExchId(doc.getId(), exchId);
		roleActivityExchAO().refreshDay(roleAExch);

		ActivityExchange activityExchange = roleActivityExchAO().getExchangeMap().get(exchId);
		// 判断数量
		if (roleAExch.getExchNum() >= activityExchange.getExchNum()) {
			// 兑换数量已经用完
			throw BaseException.getException(ActivityException.EXCE_EXCH_NO_EXCHNUM);
		}
		// 检查背包
		List<PacketExtend> checkBag = packetAO().checkBag(doc, 21);
		if (checkBag.size() > 0) {
			exchVO.setCheckBag(checkBag);
			return exchVO;
		}
		ActivityExchangeExp aeExp = cacheManager().getExistValueByKey(ActivityExchangeExp.class, roleAExch.getExpId());
		// 减掉 兑换消耗
		for (int i = 0; i < aeExp.getExchItemType().size(); i++) {
			int aeType = aeExp.getExchItemType().get(i);
			if (aeType != 4 && aeType != 1 && aeType != 8) {// 武学 侠客 装备
				packetAO().removeItemMustEnough(doc, aeType, aeExp.getExchItemId().get(i),
						aeExp.getExchItemCnt().get(i), RoleItemLog.SYS_限时兑换_兑换扣除, "");
			} else {
				packetAO().removeCardEquipGongYuanByResId(doc, aeType, aeExp.getExchItemId().get(i),
						aeExp.getExchItemCnt().get(i), RoleItemLog.SYS_限时兑换_兑换扣除);
			}
		}
		// 加上兑换所得
		for (int j = 0; j < aeExp.getExchRstType().size(); j++) {
			packetAO().addItem(doc, aeExp.getExchRstType().get(j), aeExp.getExchRstId().get(j),
					aeExp.getExchRstCnt().get(j), RoleItemLog.SYS_限时兑换_兑换添加, "");
		}
		// 兑换完的数量存上
		roleAExch.setExchNum(roleAExch.getExchNum() + 1);
		roleActivityExchAO().updateExchNum(roleAExch.getId(), roleAExch.getExchNum());
		// 剩余次数
		int num = activityExchange.getExchNum() - roleAExch.getExchNum();
		exchVO.setExchId(exchId);
		exchVO.setExchNum(num);

		CActExchExp cee = roleActivityExchAO().getExp(doc, aeExp);
		exchVO.setExchExp(cee);
		return exchVO;
	}

	@Override
	public void reloadActivitys() {
		roleActivityExchAO().reloadActivitys();
	}
}
