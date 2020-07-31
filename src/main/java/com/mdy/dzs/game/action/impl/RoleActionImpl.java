package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.ChargeOrderAction;
import com.mdy.dzs.data.domain.account.IOSDeviceInfo;
import com.mdy.dzs.data.domain.charge.ChargeOrder;
import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleAction;
import com.mdy.dzs.game.domain.broad.BroadInfo;
import com.mdy.dzs.game.domain.broad.Broadcast;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.card.UserInfoVO;
import com.mdy.dzs.game.domain.giftcenter.RoleGiftStatus;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.role.HeartOtherObjVO;
import com.mdy.dzs.game.domain.role.PlayerInfoVO;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.RoleIdName;
import com.mdy.dzs.game.domain.role.RoleLoginInfoVO;
import com.mdy.dzs.game.domain.union.RoleUnion;
import com.mdy.dzs.game.domain.vip.RoleVip;
import com.mdy.dzs.game.exception.ActivityException;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleException;
import com.mdy.dzs.game.util.BehaviorLogger;
import com.mdy.dzs.game.util.BehaviorLogger.Action;
import com.mdy.dzs.game.util.BehaviorLogger.Type;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.dzs.game.util.EmojiStringUtil;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

public class RoleActionImpl extends ApplicationAwareAction implements RoleAction {

	final static Logger logger = LoggerFactory.get(RoleActionImpl.class);

	private ChargeOrderAction chargeOrderAction = Container.get().createRemote(ChargeOrderAction.class,
			DataApplication.CLUSTER_SDATA_SYSTEM);

	@Override
	public RoleLoginInfoVO roleLogin(String accountName, String serverId, IOSDeviceInfo device, String serverKey)
			throws BizException {
		RoleLoginInfoVO res = null;
		do {
			Role role = roleAO().queryByAccount(accountName);
			if (role == null)
				break;

			//
			roleAO().updateLoginInfoByAccount(role, serverId, serverKey, device);
			dayLogin(role);

			// 验证版本
			roleAO().checkVersion(role);
			RoleCard mainCard = roleCardAO().queryById(role.getFmtMainCardID());
			res = new RoleLoginInfoVO(role, mainCard);
			List<Integer> ary = res.getPropLimitAry();
			ary.set(2, cacheManager().getLevel(role.getLevel()).getExp());
			lineupAO().verifyLineup(role);
			roleAO().addLoginLog(role.getId(), device);

		} while (false);

		return res;
	}

	@Override
	public RoleLoginInfoVO roleLogin(int roleId) throws BizException {
		RoleLoginInfoVO res = null;
		Date start = new Date();
		do {
			Role role = roleAO().queryById(roleId);
			if (role == null)
				break;
			dayLogin(role);
			// 验证版本
			roleAO().checkVersion(role);
			RoleCard mainCard = roleCardAO().queryById(role.getFmtMainCardID());
			res = new RoleLoginInfoVO(role, mainCard);
			List<Integer> ary = res.getPropLimitAry();
			ary.set(2, cacheManager().getLevel(role.getLevel()).getExp());
			lineupAO().verifyLineup(role);
			roleAO().addLoginLog(role.getId(), null);
		} while (false);
		logger.info("init datas is done:" + (new Date().getTime() - start.getTime()));
		return res;
	}

	@Override
	public RoleLoginInfoVO roleLogin(String acc) throws BizException {
		RoleLoginInfoVO res = null;
		Date start = new Date();
		do {
			Role role = roleAO().queryByAccount(acc);
			if (role == null)
				break;
			dayLogin(role);
			// 验证版本
			roleAO().checkVersion(role);
			RoleCard mainCard = roleCardAO().queryById(role.getFmtMainCardID());
			res = new RoleLoginInfoVO(role, mainCard);
			List<Integer> ary = res.getPropLimitAry();
			ary.set(2, cacheManager().getLevel(role.getLevel()).getExp());
			lineupAO().verifyLineup(role);
			roleAO().addLoginLog(role.getId(), null);
		} while (false);
		logger.info("init datas is done:" + (new Date().getTime() - start.getTime()));
		return res;
	}

	@Override
	public List<Serializable> getPlayerInfo(String accountName) throws BizException {
		Role role = roleAO().queryExistAccount(accountName);
		dayLogin(role);
		List<Object> check = roleAO().check(role);
		int isBuy = 0;
		RoleVip vip = roleVipAO().queryRtnNull(role.getId());// role的VIP信息
		if (vip != null) {
			Map<String, Integer> getBuyCountMap = vip.getBuyCountMap();
			isBuy = getBuyCountMap.size() > 0 ? 1 : 0;
		}

		return Arrays.asList(new PlayerInfoVO(role), (Serializable) check, isBuy);
	}

	@Override
	public RoleLoginInfoVO register(String acc, String lac, String name, String platformID, int rid,
			IOSDeviceInfo deviceInfo, String serverId) throws BizException {
		if (EmojiStringUtil.containsEmoji(name)) {
			logger.error("ERROR:" + EmojiStringUtil.utf8ToUnicode(name));
			throw BaseException.getException(RoleException.EXCE_NAME_NOT_HAVE_EMOJI);
		}
		String version = cacheManager().getCurServerVersion();
		Role me = roleAO().addRole(acc, lac, name, platformID, rid, version, serverId);
		roleAO().updateLoginInfoByAccount(me, serverId, "", deviceInfo);
		roleBattleAO().add(me.getId());
		roleStatisticsAO().add(me);
		// packetAO().addItem(me,1,1003,1,RoleItemLog.SYS_注册_初始化,"");
		packetAO().addItem(me, 7, 4012, 50, RoleItemLog.SYS_注册_初始化, "");

		packetAO().addItem(me, 9, 5307, 1, RoleItemLog.SYS_注册_初始化, "");
		packetAO().addItem(me, 9, 5309, 1, RoleItemLog.SYS_注册_初始化, "");
		packetAO().addItem(me, 8, 3, 1, RoleItemLog.SYS_注册_初始化, "");

		dayLogin(me);
		RoleCard mainCard = roleCardAO().queryById(me.getFmtMainCardID());
		RoleLoginInfoVO res = new RoleLoginInfoVO(me, mainCard);
		List<Integer> ary = res.getPropLimitAry();
		ary.set(2, cacheManager().getLevel(me.getLevel()).getExp());
		return res;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int sleep(String accountName) throws BizException {
		int res = 0;
		Date now = new Date();
		int hNow = now.getHours();
		double dNow = Math.floor((now.getTime() / 1000 - now.getTimezoneOffset() * 60) / (24 * 3600));
		if (hNow != 12 && hNow != 13 && hNow != 18 && hNow != 19)
			return res;
		Role doc = roleAO().queryByAccount(accountName);
		Date last = doc.getSleepLastTime();
		if (last == null)
			return 1;
		int hLast = last.getHours();
		double dLast = Math.floor((last.getTime() / 1000 - last.getTimezoneOffset() * 60) / (24 * 3600));
		if (dNow != dLast)
			res = 1;
		else {
			res = ((hNow - hLast) < 2) ? 0 : 1;
		}

		return res;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int sleepOp(String acc) throws BizException {

		Date now = new Date();
		int hNow = now.getHours();
		double dNow = Math.floor((now.getTime() / 1000 - now.getTimezoneOffset() * 60) / (24 * 3600));
		if (hNow != 12 && hNow != 13 && hNow != 18 && hNow != 19)
			throw RoleException.getException(ActivityException.EXCE_SLEEPOP_NOT_ALLOW);

		Role doc = roleAO().queryByAccount(acc);
		Date last = doc.getSleepLastTime();
		if (last != null) {
			int hLast = last.getHours();
			double dLast = Math.floor((last.getTime() / 1000 - last.getTimezoneOffset() * 60) / (24 * 3600));
			int bOp = 0;
			if (dNow != dLast) {
				bOp = 1;
			} else {
				bOp = ((hNow - hLast) < 2) ? 0 : 1;
			}
			if (bOp == 0) {
				throw RoleException.getException(ActivityException.EXCE_SLEEPOP_HAS_GOT);
			}
		}
		roleAO().updateSleepLastTime(doc, now);
		roleAO().updateAddPhysVal(doc, 50);
		missionExecAO().roleSleep(doc);
		return doc.getPhysVal();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> heart(String acc) throws BizException {
		Role rDoc = roleAO().queryExistAccount(acc);
		Map<String, List<BroadInfo>> broadMap = new HashMap<String, List<BroadInfo>>();
		int now = (int) (new Date().getTime() / 1000);
		if (now - rDoc.getHeartLastTime() < Constants.outLineTime * 60) {
			RoleGiftStatus giftStatus = giftCenterAO().queryGiftStatus(rDoc.getId());
			giftStatus.setGiftOnlineTimeAcc(giftStatus.getGiftOnlineTimeAcc() + now - rDoc.getHeartLastTime());
			giftCenterAO().updateOnLineGift(giftStatus);
		}
		rDoc.setHeartLastTime(now);
		roleAO().updateHeartTimes(rDoc);
		// 更新体力
		roleAO().checkPhyRss(rDoc, true);

		List<Broadcast> docs = broadcastAO().queryViewList(rDoc);

		if (docs.size() != 0) {
			try {
				broadMap = broadcastAO().refList(rDoc, docs, acc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		;
		// {"battle":战斗新邮件数,"friend":好友新邮件数,"system":系统新邮件数}
		Map<String, Integer> mailMap = mailAO().checkNewMail(rDoc);
		HeartOtherObjVO otherObj = new HeartOtherObjVO();
		otherObj.setServerTime(new Date().getTime());
		otherObj.setFriend(roleFriendAO().checkHasNewData(rDoc));
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(rDoc.getId());
		if (roleUnion != null && roleUnion.getJopType() != RoleUnion.member) {
			int num = unionApplyAO().queryApplyNum(roleUnion.getUnionId());
			otherObj.setApplyNum(num);
		} else {
			otherObj.setApplyNum(0);
		}
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("playerInfoAry", new ArrayList(Arrays.asList(rDoc.getPhysVal(), rDoc.getResisVal())));
		rtnMap.put("broadMap", broadMap);
		rtnMap.put("mailMap", mailMap);
		rtnMap.put("otherObj", otherObj);

		return rtnMap;
	}

	@Override
	public UserInfoVO uinfo(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		List<Integer> cardAry = doc.getFmtCardAry();

		Open openData = cacheManager().getValueByKey(Open.class, 14);
		int maxCnt = 0;
		int onCnt = 0;
		for (int i = 0; i < openData.getLevel().size(); i++) {
			if (openData.getLevel().get(i) <= doc.getLevel())
				maxCnt = (i + 1);
			else
				break;
		}
		for (int j = 0; j < cardAry.size(); j++) {
			if (cardAry.get(j) != 0)
				onCnt++;
		}
		String[] rstAry = roleAO().checkPhyRss(doc, true);

		UserInfoVO vo = new UserInfoVO();
		vo.setId(doc.getId());
		vo.setFmtCnt(Arrays.asList(onCnt, maxCnt)); // 上阵武将,可上阵武将
		vo.setGold(doc.getGold()); // 元宝数
		vo.setSilver(doc.getSilver()); // 银币
		vo.setSoul(doc.getSoul()); // 侠魂
		vo.setHunYuVal(doc.getHunYuVal()); // 魂玉
		vo.setPhysVal(doc.getPhysVal()); // 体力值
		vo.setPhysValLimit(doc.getPropLimitAry().get(0)); // 体力上限
		vo.setPhysValTime(rstAry[0].split(",")); // 体力回复时间秒数，体力回满时间秒数
		vo.setResisVal(doc.getResisVal()); // 耐力值
		vo.setResisValLimit(doc.getPropLimitAry().get(1)); // 耐力上限
		vo.setResisValTime(rstAry[1].split(",")); // 耐力回复时间秒数，耐力回满时间秒数
		return vo;
	}

	private boolean dayLogin(Role role) throws BizException {
		role = roleAO().queryByIdFromDB(role.getId());
		if (role.getDayLoginTime() != null) {
			if (DateUtil.isToday(role.getDayLoginTime()))
				return false;
			// double dNow =
			// Math.floor((now.getTime()/1000-now.getTimezoneOffset()*60)/(24*3600));
			// double dLast =
			// Math.floor((role.getDayLoginTime().getTime()/1000-now.getTimezoneOffset()*60)/(24*3600));
			// if(dNow==dLast) return;
		}
		roleAO().updateDayReset(role);
		if (roleActivityAO().checkHappyIsOpen() == 1) {
			roleActivityHappyAO().updateLoginCnt(role.getId());
		}
		// 每日任务

		missionAO().dailyRefreshMissions(role.getId());
		missionExecAO().AcceptBeStartMission(role);
		// TASK
		missionExecAO().roleLogin(role);
		// 角色好友聊天记录
		roleFriendAO().deletechatDataByDay(role);
		BehaviorLogger.log4Platform(role, Type.GAME, Action.LOGIN_GAME);
		return true;
	}

	@Override
	public void chargeSuccess(ChargeOrder chargeOrder) throws BizException {
		Role doc = roleAO().queryExistAccount(chargeOrder.getAccount());// 取到role数据
		// update info
		chargeOrder.setRoleId(doc.getId());
		chargeOrder.setNickName(doc.getNikeName());
		chargeOrder.setRoleName(doc.getName());
		chargeOrder.setRoleLevel(doc.getLevel());
		chargeOrder.setRoleVipLevel(doc.getVip());
		chargeOrder.setRoleDeviceUUID(doc.getLastDeviceUUID());

		int oldGold = doc.getGold();
		// roleStatisticsAO().updateChargeInfo(doc, chargeOrder);
		roleVipAO().update(doc, chargeOrder);
		chargeOrder.setRoleAddGold(doc.getGold() - oldGold);

		chargeOrderAction.finishSuccess(chargeOrder);
	}

	@Override
	public RoleIdName queryIdNameByAcc(String acc) throws BizException {
		return roleAO().queryIdNameByAcc(acc);
	}

	@Override
	public String getServerKey(String acc) {
		return roleAO().queryServerKeyByAccount(acc);
	}

	@Override
	public void tidyBroadcastTable() {
		broadcastAO().deleteDatas();
	}

	@Override
	public String getVersion(String acc) {
		return roleAO().queryVersionByAccount(acc);
	}

	@Override
	public void kickRoles() {
		roleAO().kickRoles();
	}

	@Override
	public void updateRoleAttacks() throws BizException {
		roleAO().updateRoleAttacks();
	}

	@Override
	public void updateRoleAttack(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		roleAO().updateRoleAttack(doc);
	}
}
