/**
 * 
 */
package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.RewardCenterAction;
import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.data.domain.gift.LevelGift;
import com.mdy.dzs.data.domain.gift.LoginDayGift;
import com.mdy.dzs.data.domain.gift.OnlineGift;
import com.mdy.dzs.data.domain.gift.SignGift;
import com.mdy.dzs.data.domain.reward.RewardCenter;
import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.GiftCenterAction;
import com.mdy.dzs.game.domain.giftcenter.RoleGift;
import com.mdy.dzs.game.domain.giftcenter.RoleGiftStatus;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.DataException;
import com.mdy.dzs.game.exception.GiftCenterException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月11日 下午5:35:19
 */
public class GiftCenterActionImpl extends ApplicationAwareAction implements GiftCenterAction {

	private static RewardCenterAction rewardCenterAction = Container.get().createRemote(RewardCenterAction.class,
			DataApplication.CLUSTER_SDATA_SYSTEM);

	@Override
	public List<RoleGift> queryGiftCenterList(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		return giftCenterAO().queryListByRoleId(doc.getId());
	}

	@Override
	public List<Serializable> getGift(String acc, int type, int giftId) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		List<RoleGift> list = new ArrayList<RoleGift>();
		if (type == RoleGift.GIFT_GETTYPE_ONE) {
			RoleGift gift = giftCenterAO().queryRoleGiftById(giftId);
			if (gift != null)
				list.add(gift);
		}
		if (type == RoleGift.GIFT_GETTYPE_ALL) {
			list.addAll(giftCenterAO().queryListByRoleId(doc.getId()));
		}
		giftCenterAO().getGift(doc, list);

		int count = giftCenterAO().queryCountByRoleId(doc.getId());

		Map<String, Object> infos = new HashMap<String, Object>();
		infos.put("gold", doc.getGold());
		infos.put("silver", doc.getSilver());
		return Arrays.asList((Serializable) infos, count > 0);
	}

	@Override
	public List<Serializable> queryLevelGiftList(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleGiftStatus status = giftCenterAO().queryGiftStatus(role.getId());
		List<LevelGift> list = new ArrayList<LevelGift>(giftCenterAO().getLevelGiftMap().values());
		return Arrays.asList((Serializable) status.getGiftLvGot(), false, (Serializable) list);
	}

	@Override
	public List<Serializable> getLevelGift(String acc, int lv) throws BizException {

		Role doc = roleAO().queryExistAccount(acc);
		//
		// List<PacketExtend> bageData = new ArrayList<PacketExtend>();
		//
		// List<LevelGift> list = new
		// ArrayList<LevelGift>(giftCenterAO().getLevelGiftMap().values());
		//
		// for(LevelGift item: list)
		// {
		// bageData = packetAO().checkBag(doc, item.getArrItem());
		// if(bageData.size() > 0)
		// {
		// return null;
		// }
		// }
		//
		//
		Open openData = cacheManager().getExistValueByKey(Open.class, 1);
		if (doc.getLevel() < openData.getLevel().get(0)) {
			// throw BaseException.getGlobalException(
			// "level control");
			throw BaseException.getException(GiftCenterException.EXCE_GIFTCENTER_LEVEL_LIMIT,
					openData.getLevel().get(0));
		}
		if (lv > doc.getLevel()) {
			// throw BaseException.getGlobalException(
			// "lv deficiency");
			throw BaseException.getException(GiftCenterException.EXCE_GIFTCENTER_LEVEL_ERROR, lv);
		}

		RoleGiftStatus status = giftCenterAO().queryGiftStatus(doc.getId());
		if (status.getGiftLvGot().indexOf(lv) != -1) {
			// throw BaseException.getGlobalException(
			// "lv gift has got");
			throw BaseException.getException(GiftCenterException.EXCE_GIFTCENTER_HAVE_GOT);

		}
		giftCenterAO().getLevelGift(doc, status, lv);

		Map<String, Object> infos = new HashMap<String, Object>();
		infos.put("gold", doc.getGold());
		infos.put("silver", doc.getSilver());
		return Arrays.asList((Serializable) infos);
	}

	@Override
	public List<Serializable> queryOnLineGiftList(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleGiftStatus status = giftCenterAO().queryGiftStatus(role.getId());
		Map<Integer, OnlineGift> map = giftCenterAO().getOnlineGiftMap();
		List<GiftItem> list = null;

		boolean isShow = true;
		int giftOnlineGot = status.getGiftOnlineGot() + 1;
		int rtnTime = (int) (status.getGiftOnlineTime() == null ? role.getCreateTime().getTime() * 0.001
				: status.getGiftOnlineTime().getTime() * 0.001);

		do {
			if (giftOnlineGot > giftCenterAO().getMaxOnlineCnt()) {
				giftOnlineGot = status.getGiftOnlineGot();
				isShow = false;
				break;
			}
			OnlineGift onlineData = giftCenterAO().getOnlineGiftMap().get(giftOnlineGot);
			if (onlineData == null) {
				throw DataException.getException(DataException.EXCE_DATA_IS_NOT_EXIST, OnlineGift.class.getName(),
						giftOnlineGot);
			}
			int parseNow = (int) (new Date().getTime() / 1000);
			if (parseNow - role.getHeartLastTime() < Constants.outLineTime * 60) {
				int addTime = status.getGiftOnlineTimeAcc()
						+ (role.getHeartLastTime() - rtnTime > 0 ? parseNow - rtnTime
								: parseNow - role.getHeartLastTime());
				rtnTime = onlineData.getTime() * 60 - addTime;
			} else {
				rtnTime = onlineData.getTime() * 60 - status.getGiftOnlineTimeAcc()
						- (Math.abs(role.getHeartLastTime() - rtnTime));
			}
			list = onlineData.getItem();
			if (rtnTime < 0)
				rtnTime = 0;
		} while (false);
		return Arrays.asList(giftOnlineGot, false, rtnTime, isShow, (Serializable) list);
	}

	@Override
	public List<Serializable> getOnLineGift(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleGiftStatus status = giftCenterAO().queryGiftStatus(role.getId());
		Map<Integer, OnlineGift> map = giftCenterAO().getOnlineGiftMap();
		if (status.getGiftOnlineGot() >= giftCenterAO().getMaxOnlineCnt()) {
			// throw BaseException.getGlobalException(
			// "id reach max");
			throw BaseException.getException(GiftCenterException.EXCE_GIFTCENTER_ID_MAX);
		}
		int giftOnlineGot = status.getGiftOnlineGot() + 1;
		OnlineGift onlineData = giftCenterAO().getOnlineGiftMap().get(giftOnlineGot);
		if (onlineData == null) {
			throw DataException.getException(DataException.EXCE_DATA_IS_NOT_EXIST, OnlineGift.class.getName(),
					giftOnlineGot);
		}
		Date now = new Date();
		int parseNow = (int) (now.getTime() / 1000);
		if (giftOnlineGot >= 4) {
			int rtnTime = (int) (status.getGiftOnlineTime() == null ? role.getCreateTime().getTime() * 0.001
					: status.getGiftOnlineTime().getTime() * 0.001);
			if (parseNow - role.getHeartLastTime() < Constants.outLineTime * 60) {
				int addTime = status.getGiftOnlineTimeAcc()
						+ (role.getHeartLastTime() - rtnTime > 0 ? parseNow - rtnTime
								: parseNow - role.getHeartLastTime());
				rtnTime = onlineData.getTime() * 60 - addTime;
			} else {
				rtnTime = onlineData.getTime() * 60 - status.getGiftOnlineTimeAcc()
						- (Math.abs(role.getHeartLastTime() - rtnTime));
			}
			if (rtnTime > 0)
				// throw BaseException.getGlobalException(
				// "time not enough");
				throw BaseException.getException(GiftCenterException.EXCE_GIFTCENTER_TIME_NOT_ENOUGH);
		}

		role.setHeartLastTime(parseNow);
		roleAO().updateHeartTimes(role);

		giftCenterAO().getOnlineGift(role, status, onlineData);

		boolean isShow = false;
		int time = 0;
		OnlineGift zaixianData = giftCenterAO().getOnlineGiftMap().get(giftOnlineGot + 1);
		if (zaixianData != null) {
			isShow = true;
			time = zaixianData.getTime() * 60;
		}

		Map<String, Object> infos = new HashMap<String, Object>();
		infos.put("gold", role.getGold());
		infos.put("silver", role.getSilver());
		return Arrays.asList((Serializable) infos, isShow, time);
	}

	@Override
	public List<Serializable> querySignGiftList(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleGiftStatus status = giftCenterAO().queryGiftStatus(role.getId());
		List<SignGift> list = new ArrayList<SignGift>(giftCenterAO().getSignGiftMap().values());
		boolean isSign = false;
		int day = giftCenterAO().getBeSignDay(status);
		if (day == 0) {
			isSign = true;
			day = status.getGiftSignCnt();
		}
		return Arrays.asList(day, false, isSign, (Serializable) list);
	}

	@Override
	public List<Serializable> getSignGift(String acc, int signDay) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Open openData = cacheManager().getExistValueByKey(Open.class, 2);
		if (doc.getLevel() < openData.getLevel().get(0)) {
			// throw BaseException.getGlobalException(
			// "level control");
			throw BaseException.getException(GiftCenterException.EXCE_GIFTCENTER_LEVEL_LIMIT,
					openData.getLevel().get(0));
		}
		RoleGiftStatus status = giftCenterAO().queryGiftStatus(doc.getId());
		int day = giftCenterAO().getBeSignDay(status);
		if (signDay != day) {
			// throw BaseException.getGlobalException(
			// "day error");
			throw BaseException.getException(GiftCenterException.EXCE_GIFTCENTER_DAY_ERROR);
		}
		giftCenterAO().getSignGift(doc, status, day);

		Map<String, Object> infos = new HashMap<String, Object>();
		infos.put("gold", doc.getGold());
		infos.put("silver", doc.getSilver());
		return Arrays.asList((Serializable) infos);
	}

	@Override
	public List<Serializable> queryLoginDayGiftList(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleGiftStatus status = giftCenterAO().queryGiftStatus(role.getId());
		int day = status.getGiftLogindayCnt() > 30 ? 30 : status.getGiftLogindayCnt();
		List<LoginDayGift> list = new ArrayList<LoginDayGift>(giftCenterAO().getLoginDayGiftMap().values());

		return Arrays.asList(day, (Serializable) status.getGiftLogindayGot(), (Serializable) list);
	}

	@Override
	public List<Serializable> getLoginDayGift(String acc, int day) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleGiftStatus status = giftCenterAO().queryGiftStatus(doc.getId());
		if (day > status.getGiftLogindayCnt()) {
			// throw BaseException.getGlobalException(
			// "day is err");
			throw BaseException.getException(GiftCenterException.EXCE_GIFTCENTER_DAY_ERROR);
		}
		if (status.getGiftLogindayGot().indexOf(day) != -1) {
			// throw BaseException.getGlobalException(
			// "have get gift");
			throw BaseException.getException(GiftCenterException.EXCE_GIFTCENTER_HAVE_GOT);
		}
		giftCenterAO().getLoginDayGift(doc, status, day);

		Map<String, Object> infos = new HashMap<String, Object>();
		infos.put("gold", doc.getGold());
		infos.put("silver", doc.getSilver());
		return Arrays.asList((Serializable) infos);
	}

	@Override
	public List<Integer> sendCDKeyGift(String acc, String cdKey, List<GiftItem> items) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		giftCenterAO().sendGift(doc, items, RoleItemLog.SYS_礼包_CDKEY, cdKey);
		return Arrays.asList(doc.getGold(), doc.getSilver());
	}

	@Override
	public void clearExpiredGift() {
		giftCenterAO().clearExpiredGift();
	}

	@Override
	public void sendRewardCenterGifts(String serverName) throws BizException {
		List<RewardCenter> rewards = rewardCenterAction.queryListInRewardTime(serverName);
		for (RewardCenter rc : rewards) {
			rc.setSendRoleIds(giftCenterAO().getRewardRoleIds(rc));
		}
		for (RewardCenter rc : rewards) {
			for (int rid : rc.getSendRoleIds()) {
				giftCenterAO().sendRewardCenterGift(rid, rc);
			}
		}
	}

}
