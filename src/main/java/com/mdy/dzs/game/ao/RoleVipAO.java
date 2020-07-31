package com.mdy.dzs.game.ao;

import java.util.Date;
import java.util.Map;

import com.mdy.dzs.data.domain.charge.ChargeOrder;
import com.mdy.dzs.data.domain.chongzhi.Chongzhi;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.data.domain.viplevel.Viplevel;
import com.mdy.dzs.game.domain.activity.roulettegame.Roulette;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.monthcard.RoleMonthCard;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.shenmishop.ShenMiShop;
import com.mdy.dzs.game.domain.vip.GameVip;
import com.mdy.dzs.game.domain.vip.RoleFirstRecharge;
import com.mdy.dzs.game.domain.vip.RoleVip;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

public class RoleVipAO extends BaseAO {

	private RoleAO roleAO;
	private CacheManager cacheManager;
	private RoleGiftCenterAO giftCenterAO;
	private PacketAO packetAO;
	private RoleMonthCardAO roleMonthCardAO;
	private RoleShenMiShopAO roleShenMiShopAO;
	private MissionExecAO missionExecAO;
	private RoleActivityRouletteAO roleActivityRouletteAO;

	public RoleVipAO(RoleAO roleAO, CacheManager cacheManager, PacketAO packetAO, RoleGiftCenterAO giftCenterAO,
			RoleMonthCardAO roleMonthCardAO, RoleShenMiShopAO roleShenMiShopAO, MissionExecAO missionExecAO,
			RoleActivityRouletteAO roleActivityRouletteAO) {
		this.roleAO = roleAO;
		this.cacheManager = cacheManager;
		this.giftCenterAO = giftCenterAO;
		this.packetAO = packetAO;
		this.roleMonthCardAO = roleMonthCardAO;
		this.roleShenMiShopAO = roleShenMiShopAO;
		this.missionExecAO = missionExecAO;
		this.roleActivityRouletteAO = roleActivityRouletteAO;
	}

	public void update(Role doc, ChargeOrder chargeOrder) throws BizException {
		RoleVip vip = query(doc.getId());// role的VIP信息
		Map<Integer, Viplevel> vipDatas = cacheManager.getValues(Viplevel.class);// vip列表
		Date now = new Date();
		String payItem = chargeOrder.getPayItem();
		String[] payItemAry = payItem.split("_");
		int payType = Integer.parseInt(payItemAry[0]);
		int goldAdd = 0;

		if (payType == GameVip.YUEKA) {// 月卡
			goldAdd = updateVipYueKa(doc, vip);
			packetAO.addItem(doc, Packet.POS_ATTR, Packet.ATTR_金币, goldAdd, RoleItemLog.SYS_充值_月卡获得, payItem);
		} else if (payType == GameVip.CHONGZHI) {// 充值
			// 首充，发奖
			if (vip.getFirstGift() == 0) {
				RoleFirstRecharge first = new RoleFirstRecharge();
				first.setRoleId(doc.getId());
				giftCenterAO.sendFirstRechargeGift(first, now);
				vip.setFirstGift(1);
				chargeOrder.setRoleFirstCharge(1);
			}
			int index = Integer.parseInt(payItemAry[1]);
			updateVipChongZhi(doc, vip, index);
		}

		// 加VIP经验
		// 是否升级，下级是否开启，若开启则升级，升级到的等级记录（多级）
		int vipLvAdd = 0;
		for (int i = doc.getVip(); i < vipDatas.size(); i++) {
			Viplevel nextLvData = (Viplevel) cacheManager.getExistValueByKey(Viplevel.class, i + 1);
			if (nextLvData != null && nextLvData.getOpen() != 0 && vip.getCurExp() >= nextLvData.getExp()) {
				vipLvAdd++;
			} else {
				// 未开启
				break;
			}
		}
		if (vipLvAdd > 0) {
			updateShenMiRefreshCnt(doc.getId(), doc.getVip(), vipLvAdd);// 神秘商店VIP等级金币购买刷新次数更新
			roleAO.updateVip(doc, vipLvAdd);// 角色VIP升级
		}

		// vip表数据存储
		resetVipData(vip);
		//
		roleStatisticsDAO().updateChargeInfo(doc.getId(), chargeOrder.getPayPrice());
	}

	// 充值更新
	public void updateVipChongZhi(Role doc, RoleVip vip, int index) throws BizException {
		// 加金币，加vip经验,更新购买价位计数
		Chongzhi data = cacheManager.getExistValueByKey(Chongzhi.class, index);
		int expAdd = data.getBasegold();
		String k = String.valueOf(data.getIndex());
		int v = 1;
		packetAO.addItem(doc, Packet.POS_ATTR, Packet.ATTR_金币, data.getBasegold(), RoleItemLog.SYS_充值_基础获得, k);
		Map<String, Integer> getBuyCountMap = vip.getBuyCountMap();
		if (getBuyCountMap.containsKey(String.valueOf(data.getIndex()))
				&& getBuyCountMap.get(String.valueOf(data.getIndex())) > 0) {
			// 购买过该价位
			packetAO.addItem(doc, Packet.POS_ATTR, Packet.ATTR_金币, data.getChixugold(), RoleItemLog.SYS_充值_赠送获得, k);
			v += getBuyCountMap.get(String.valueOf(data.getIndex()));
		} else {
			// 首次购买该价位
			packetAO.addItem(doc, Packet.POS_ATTR, Packet.ATTR_金币, data.getFirstgold(), RoleItemLog.SYS_充值_首冲赠送获得, k);
		}
		getBuyCountMap.put(k, v);
		vip.setCurExp(vip.getCurExp() + expAdd);
		missionExecAO.roleChargeRMB(doc, data.getCoinnum());
		// 探宝
		roleActivityRouletteAO.updateDayAdd(data.getBasegold(), doc, Roulette.充值返利);
	}

	// 月卡更新
	public int updateVipYueKa(Role doc, RoleVip vip) throws BizException {
		RoleMonthCard roleMCardDoc = roleMonthCardAO.query(doc.getId());
		Date now = new Date();

		if (now.after(DateUtil.getEndTimeOfDay(roleMCardDoc.getOverTime()))) {// 当前时间晚于结束时间
			// 设置结束时间为30天后
			Date overTime = DateUtil.getInternalDateByDay(now, 30 - 1);
			// Date overTime = DateUtil.getInternalDateByDay(now, 5-1);
			roleMCardDoc.setOverTime(overTime);
		} else {
			// 结束时间的后一天开始累加30天
			Date overTime = DateUtil.getBackOrForwardDay(roleMCardDoc.getOverTime(), 30);
			// Date overTime = DateUtil.getBackOrForwardDay(roleMCardDoc.getOverTime(),5);
			roleMCardDoc.setOverTime(overTime);
		}
		roleMonthCardAO.updateOverTime(roleMCardDoc);
		vip.setCurExp(vip.getCurExp() + Constants.yuekaGoldGet);
		missionExecAO.roleChargeRMB(doc, 25);
		// 探宝
		Roulette activeData = roleActivityRouletteAO.getCurActivityData();// 活动配置
		if (activeData != null) {// 探宝活动有开启的配置
			roleActivityRouletteAO.updateDayAdd(activeData.getYueka(), doc, Roulette.充值返利);
		}
		return Constants.yuekaGoldGet;
	}

	// 更新VIP升级对神秘商店元宝刷新次数的提升
	private void updateShenMiRefreshCnt(int roleId, int vip, int vipLvAdd) throws BizException {
		Vip vipGoldData = cacheManager.getExistValueByKey(Vip.class, Vip.SYSTEM_神秘商店元宝刷新次数上限);// 神秘商店VIP等级金币刷新次数一行
		int bfVipGold = vipGoldData.getVipByLevel(vip);// 充值前
		int aftVipGold = vipGoldData.getVipByLevel(vip + vipLvAdd);// 充值后
		int goldFreeCnt = aftVipGold - bfVipGold;
		ShenMiShop shenMiDoc = roleShenMiShopAO.query(roleId);// 角色神秘商店数据
		// 更新神秘商店数据
		shenMiDoc.setMeysteryDayGoldRefreshCnt(shenMiDoc.getMeysteryDayGoldRefreshCnt() + goldFreeCnt);
		roleShenMiShopAO.update(shenMiDoc);
	}

	/**
	 * 更新
	 */
	private void resetVipData(RoleVip vip) {
		roleVipDAO().update(vip);
	}

	/**
	 * 查询 add
	 */
	public RoleVip query(int id) {
		RoleVip vip = roleVipDAO().query(id);
		if (vip == null) {
			roleVipDAO().add(id);
			vip = roleVipDAO().query(id);
		}
		return vip;
	}

	/**
	 * 查询 无此玩家return null
	 */
	public RoleVip queryRtnNull(int id) {
		RoleVip vip = roleVipDAO().query(id);
		if (vip == null) {
			return null;
		}
		return vip;
	}

	/**
	 * 查询抛异常
	 */
	public RoleVip queryExistAccount(int id) throws BizException {
		RoleVip vip = roleVipDAO().queryByAccount(id);
		if (vip == null) {
			roleVipDAO().add(id);
			// throw RoleException.getException(RoleException.EXCE_ACCOUNT_NOT_EXIST);
		}
		return vip;
	}

	/**
	 * 更新已经领取vip等级礼包List
	 */
	public void updateGetlevelgift(RoleVip vip) {
		roleVipDAO().updateGetlevelgift(vip);
	}

	/**
	 * 更新VIP每日福利领取时间
	 */
	public void updateVipDayGiftTime(RoleVip vip) {
		roleVipDAO().updateVipDayGiftTime(vip);
	}
}
