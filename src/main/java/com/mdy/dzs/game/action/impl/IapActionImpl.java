package com.mdy.dzs.game.action.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.charge.ChargeOrder;
import com.mdy.dzs.data.domain.chongzhi.Chongzhi;
import com.mdy.dzs.data.domain.mission.ActivityMissionProperty;
import com.mdy.dzs.data.domain.viplevel.Viplevel;
import com.mdy.dzs.data.util.DateUtil;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.IapAction;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.monthcard.RoleMonthCard;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.vip.ComparatorVipChongzhiIndex;
import com.mdy.dzs.game.domain.vip.GameVip;
import com.mdy.dzs.game.domain.vip.RoleVip;
import com.mdy.dzs.game.domain.vip.VipDataVO;
import com.mdy.dzs.game.domain.vip.VipInfoVO;
import com.mdy.dzs.game.domain.vip.VipListVO;
import com.mdy.dzs.game.domain.vip.YueDataVO;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.IapException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

public class IapActionImpl extends ApplicationAwareAction implements IapAction {

	// VIP充值界面
	public VipInfoVO iap(String acc) throws BizException {
		Map<Integer, Chongzhi> cDatas = cacheManager().getValues(Chongzhi.class);// 充值表
		Map<Integer, Viplevel> vipDatas = cacheManager().getValues(Viplevel.class);// vip等级表

		Role doc = roleAO().queryExistAccount(acc);// 取到t_role的数据
		RoleVip vip = roleVipAO().query(doc.getId());// role的VIP信息
		List<VipListVO> retAry = new ArrayList<VipListVO>();
		Map<String, Integer> getBuyCountMap = vip.getBuyCountMap();
		for (Entry<Integer, Chongzhi> entry : cDatas.entrySet()) {
			boolean isShow = true;
			if (entry.getValue().getPreIndex() != 0
					&& !getBuyCountMap.containsKey(entry.getValue().getPreIndex() + "")) {
				isShow = false;
			}
			// 当前价位购买是否开启
			if (entry.getValue().getOpen() == 1 && isShow) {
				// 检查当前价位购买次数
				int cnt = 0;
				if (getBuyCountMap.containsKey(entry.getValue().getIndex() + "")
						&& getBuyCountMap.get(entry.getValue().getIndex() + "") > 0) {
					cnt = getBuyCountMap.get(entry.getValue().getIndex() + "");
				}
				retAry.add(new VipListVO(entry.getValue().getIndex(), cnt, GameVip.CHONGZHI,
						entry.getValue().getCoinnum()));
			}
		}

		ComparatorVipChongzhiIndex compar = new ComparatorVipChongzhiIndex();
		Collections.sort(retAry, compar);
		// vipData
		int expLimit = 0;
		if (vipDatas.containsKey(doc.getVip() + 1) && vipDatas.get(doc.getVip() + 1).getOpen() == 1) {// 数据存在并且开放
			expLimit = vipDatas.get(doc.getVip() + 1).getExp();
		} else {
			expLimit = vip.getCurExp();
		}

		VipDataVO vipData = new VipDataVO(doc.getVip(), vip.getCurExp(), expLimit);
		// config中读出月卡售价及立得金币数,计算剩余天数,确定可否购买
		RoleMonthCard roleMCardDoc = roleMonthCardAO().query(doc.getId());
		// 剩余天数
		int days = roleMonthCardAO().disDays(roleMCardDoc.getOverTime());
		int isCanBuy = 2;
		if (days <= Constants.yuekaSurplusDays) {
			isCanBuy = 1;
		}

		if (days < 0) {
			days = 0;
		}

		YueDataVO yueData = new YueDataVO(Constants.yuekaCost, Constants.yuekaGoldGet, GameVip.YUEKA, isCanBuy, days);

		VipInfoVO rtnObj = new VipInfoVO();
		rtnObj.setList(retAry);
		rtnObj.setVipData(vipData);
		rtnObj.setYueData(yueData);
		rtnObj.setCurGold(doc.getGold());

		return rtnObj;
	}

	// vip等级礼包列表-界面
	@Override
	public Map<String, Object> vipLvGiftList(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);// 取到t_role的数据
		RoleVip vip = roleVipAO().query(doc.getId());// role的VIP信息
		int curVipLevel = doc.getVip();
		List<Integer> getLevelGiftAry = vip.getGetlevelgift();

		Map<String, Object> rtnObj = new HashMap<String, Object>();
		rtnObj.put("curVipLevel", curVipLevel);
		rtnObj.put("getLevelGiftAry", getLevelGiftAry);

		return rtnObj;
	}

	// vip等级礼包购买
	public Map<String, Object> vipLvGiftGet(String acc, int vipLv) throws BizException {
		Role doc = roleAO().queryExistAccount(acc); // 取到t_role的数据
		RoleVip vip = roleVipAO().queryExistAccount(doc.getId()); // role的VIP信息
		List<Integer> getLevelGiftAry = vip.getGetlevelgift(); // 已买过的等级

		if (getLevelGiftAry.contains(vipLv)) { // 已买过 -- 不可以买
			throw BaseException.getException(IapException.EXCE_IAP_VIP_HAVE_GOT);
		}
		if (vipLv > doc.getVip()) { // 未达到等级 -- 不可以买
			throw BaseException.getException(IapException.EXCE_IAP_NOT_ALLOW);
		}
		// viplevelData
		Viplevel vipLvData = cacheManager().getExistValueByKey(Viplevel.class, vipLv);
		packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, vipLvData.getNewPrice(),
				RoleItemLog.SYS_VIP_v等级礼包购买, "");
		// 背包添加物品
		List<Integer> type = vipLvData.getArrType1();
		List<Integer> item = vipLvData.getArrItem1();
		List<Integer> num = vipLvData.getArrNum1();

		for (int i = 0; i < type.size(); i++) {
			packetAO().addItem(doc, type.get(i), item.get(i), num.get(i), RoleItemLog.SYS_礼包_VIP礼包, "");
		}
		// 更新已买等级字段
		vip.getGetlevelgift().add(vipLv);
		roleVipAO().updateGetlevelgift(vip);

		Map<String, Object> rtnObj = new HashMap<String, Object>();
		rtnObj.put("result", 1);
		rtnObj.put("gold", doc.getGold());
		rtnObj.put("silver", doc.getSilver());
		return rtnObj;
	}

	@Override
	// vip每日福利-界面
	public Map<String, Object> vipDayGift(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc); // 取到t_role的数据
		RoleVip vip = roleVipAO().queryExistAccount(doc.getId()); // role的VIP信息
		if (doc.getVip() == 0) {
			throw BaseException.getException(IapException.EXCE_IAP_VIP_LEVEL_LIMIT);
		}

		Map<String, Object> rtnObj = new HashMap<String, Object>();
		Date now = new Date();
		if (DateUtil.isSameDay(now, vip.getVipdaygift())) { // 同一天领取
			rtnObj.put("isGet", 1);
		} else {
			rtnObj.put("isGet", 2);
		}

		Map<Integer, Viplevel> vipDatas = cacheManager().getValues(Viplevel.class);// vip等级表
		int expLimit = 0;
		if (vipDatas.containsKey(doc.getVip() + 1)) {
			expLimit = vipDatas.get(doc.getVip() + 1).getExp();
		}
		rtnObj.put("curVipLevel", doc.getVip());
		rtnObj.put("curExp", vip.getCurExp());
		rtnObj.put("curExpLimit", expLimit);

		return rtnObj;
	}

	@Override
	// 每日福利领取
	public Map<String, Object> getVipDayGift(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc); // 取到t_role的数据
		RoleVip vip = roleVipAO().queryExistAccount(doc.getId()); // role的VIP信息
		if (doc.getVip() == 0) {
			throw BaseException.getException(IapException.EXCE_IAP_VIP_LEVEL_LIMIT);
		}

		Date now = new Date();
		if (DateUtil.isSameDay(now, vip.getVipdaygift())) {
			throw BaseException.getException(IapException.EXCE_IAP_VIP_REPEAT_REQ);
		}

		// 背包添加每日物品
		Viplevel vipLvData = cacheManager().getExistValueByKey(Viplevel.class, doc.getVip());

		List<Integer> type = vipLvData.getArrType2();
		List<Integer> item = vipLvData.getArrItem2();
		List<Integer> num = vipLvData.getArrNum2();

		int cnt = missionAO().getActivityMissionCntByProperty(doc.getId(), ActivityMissionProperty.VIP福利_声望翻倍.value());

		for (int i = 0; i < type.size(); i++) {
			packetAO().addItem(doc, type.get(i), item.get(i), num.get(i) * cnt, RoleItemLog.SYS_礼包_VIP每日礼包, "");
		}
		// 更新已领取等级字段
		vip.setVipdaygift(now);
		roleVipAO().updateVipDayGiftTime(vip);
		// 返回数据
		Map<String, Object> rtnObj = new HashMap<String, Object>();
		rtnObj.put("result", 1);
		return rtnObj;
	}

	@Override
	public void test(String acc, String index) throws BizException {
		ChargeOrder testData = new ChargeOrder();
		testData.setPayItem(index);
		Role doc = roleAO().queryExistAccount(acc);
		testData.setRoleId(doc.getId());
		roleVipAO().update(doc, testData);
	}

}
