package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.data.util.DateUtil;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.ShenMiShopAction;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.shenmishop.ShenMiItemVO;
import com.mdy.dzs.game.domain.shenmishop.ShenMiShop;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.ShenMiShopException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

public class ShenMiShopActionImpl extends ApplicationAwareAction implements ShenMiShopAction {

	@Override
	public List<Serializable> cmdList(String acc, int refresh) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		ShenMiShop shenMiDoc = roleShenMiShopAO().query(doc.getId());// 角色神秘商店数据
		// 开启等级检查
		Open openData = cacheManager().getExistValueByKey(Open.class, 25);// key:system
		if (doc.getLevel() < openData.getLevel().get(0)) {
			throw BaseException.getException(ShenMiShopException.EXCE_SHENMI_LEVEL_LIMIT);
		}

		Vip vipData = cacheManager().getExistValueByKey(Vip.class, Vip.SYSTEM_神秘商店免费次数上限);// 神秘商店VIP等级免费刷新次数一行
		int vipFreeLimt = vipData.getVipByLevel(doc.getVip());// 免费刷新次数上限

		Date now = new Date();
		// 刷列表条件 -- 减
		// doc.meysteryAry.length == 0,第一次进神秘商店
		// 当前与上次刷新不在同一天，即：0点刷新
		// refreshType == 1，即：操作刷新

		Boolean isRefresh = new Boolean("false");
		Boolean isMinus = new Boolean("false");
		Date lastTime = shenMiDoc.getMeysteryRefreshTime();
		if (shenMiDoc.getMeysteryMap().size() == 0) { // 第一次进到神秘商店 要刷新
			isRefresh = true;
		}
		if (!DateUtil.isSameDay(now, lastTime)) {// 与上次刷新不同天，即隔天需重新刷新
			isRefresh = true;
			shenMiDoc.setMeysteryDayRefreshCnt(0);// 每日刷新次数归0
			int dayFreeNum = shenMiDoc.getMeysteryFreeCnt() + 2 > vipFreeLimt ? vipFreeLimt
					: shenMiDoc.getMeysteryFreeCnt() + 2;
			shenMiDoc.setMeysteryFreeCnt(dayFreeNum);// 免费刷新次数

			Vip vipGoldData = cacheManager().getExistValueByKey(Vip.class, Vip.SYSTEM_神秘商店元宝刷新次数上限);// 神秘商店VIP等级金币刷新次数一行
			int vipGoldLimt = vipGoldData.getVipByLevel(doc.getVip());// 金币刷新次数上限

			shenMiDoc.setMeysteryDayGoldRefreshCnt(vipGoldLimt);
		}

		if (refresh == 1) {// 刷新操作
			isRefresh = true;
			isMinus = true;// 有刷新消耗
			missionExecAO().refreshShenMiShop(doc);
		}

		// 时间 倒记 当前距下个n-倍数整点数之秒差
		int disSeconds = roleShenMiShopAO().disSeconds();

		// 更新免费刷新次数--meysteryFreeTime 每次重计免费次数时都要更新
		roleShenMiShopAO().refreshFreeCnt(shenMiDoc, vipFreeLimt);

		// 更新消耗
		if (isMinus) {
			RoleItem ritem = packetAO().queryByAccItemId(doc.getId(), 55, 7);
			if (shenMiDoc.getMeysteryFreeCnt() > 0) {
				if (DateUtil.isSameDay(now, shenMiDoc.getMeysteryRefreshTime())) {
					shenMiDoc.setMeysteryFreeCnt(shenMiDoc.getMeysteryFreeCnt() - 1);
					shenMiDoc.getMeysteryFreeCnt();
				}
			} else if (ritem != null && ritem.getItemCnt() > 0) {
				packetAO().removeItemMustEnough(doc, 12, 55, 1, RoleItemLog.SYS_精彩活动_神秘商店列表, "" + ritem.getId());
			} else if (shenMiDoc.getMeysteryDayGoldRefreshCnt() > 0) {
				packetAO().removeItemMustEnough(doc, 0, 1, Constants.mysteryRefreshCost, RoleItemLog.SYS_精彩活动_神秘商店, "");
				shenMiDoc.setMeysteryDayGoldRefreshCnt(shenMiDoc.getMeysteryDayGoldRefreshCnt() - 1);
			}
		}

		// 刷新列表
		if (isRefresh) {
			shenMiDoc = roleShenMiShopAO().refreshList(shenMiDoc, doc);
		}

		// 可刷新次数及数量
		int refreshNum = 0;
		int refreshType = 0;
		RoleItem item = packetAO().queryByAccItemId(doc.getId(), 55, 7);
		if (shenMiDoc.getMeysteryFreeCnt() > 0) {
			refreshNum = shenMiDoc.getMeysteryFreeCnt();
			refreshType = 1;
		} else if (item != null && item.getItemCnt() > 0) {
			refreshNum = item.getItemCnt();
			refreshType = 2;
		} else {
			refreshNum = doc.getGold();
			refreshType = 3;
		}
		shenMiDoc.setMeysteryFreeTime(now);// 记下本次刷新时间戳
		roleShenMiShopAO().update(shenMiDoc);

		List<ShenMiItemVO> itemList = new ArrayList<ShenMiItemVO>();

		for (Entry<String, ShenMiItemVO> entry : shenMiDoc.getMeysteryMap().entrySet()) {
			itemList.add(entry.getValue());
		}

		return Arrays.asList((Serializable) itemList, disSeconds, (Serializable) doc.getHunYuVal(), refreshNum,
				refreshType, (Serializable) Constants.mysteryRefreshCost, vipFreeLimt,
				(Serializable) shenMiDoc.getMeysteryDayGoldRefreshCnt());
	}

	@Override
	public List<Serializable> cmdExchange(String acc, String id) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		ShenMiShop shenMiDoc = roleShenMiShopAO().queryExistRoleId(doc.getId());// 角色神秘商店数据

		ShenMiItemVO buyData = shenMiDoc.getMeysteryMap().get(id); // 购买的物品数据
		if (buyData == null) {
			throw BaseException.getException(ShenMiShopException.EXCE_SHENMI_ITEM_NOT_EXIST);
		}
		if (buyData.getUpLimit() <= 0) {
			throw BaseException.getException(ShenMiShopException.EXCE_SHENMI_UP_LIMIT);
		}
		// 背包检查
		List<Integer> bagAry = new ArrayList<Integer>();
		if (buyData.getType() != 8) {
			// Item itemData = cacheManager().getExistValueByKey(Item.class,
			// buyData.getItemId());
			// bagAry.add(itemData.getBag());
		} else {
			bagAry.add(8);
		}
		List<PacketExtend> bageData = new ArrayList<PacketExtend>();
		bageData = packetAO().checkBag(doc, bagAry);

		if (bageData.size() > 0) {
			return Arrays.asList((Serializable) bageData, 0, 0);
		}
		// 添加物品及消耗
		packetAO().removeItemMustEnough(doc, 0, buyData.getMoney(), buyData.getPrice(), RoleItemLog.SYS_精彩活动_神秘商店兑换,
				"");
		packetAO().addItem(doc, buyData.getType(), buyData.getItemId(), buyData.getNum(), RoleItemLog.SYS_精彩活动_神秘商店兑换,
				"");
		buyData.setUpLimit(buyData.getUpLimit() - 1);

		roleShenMiShopAO().update(shenMiDoc);
		return Arrays.asList((Serializable) bageData, doc.getHunYuVal(), buyData.getUpLimit());
	}

	@Override
	public List<Serializable> cmdVerify(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		ShenMiShop shenMiDoc = roleShenMiShopAO().query(doc.getId());// 角色神秘商店数据

		Vip vipData = cacheManager().getExistValueByKey(Vip.class, Vip.SYSTEM_神秘商店免费次数上限);// 神秘商店VIP等级免费刷新次数一行
		int vipFreeLimt = vipData.getVipByLevel(doc.getVip());// 免费刷新次数上限

		Date now = new Date();
		Date lastTime = shenMiDoc.getMeysteryRefreshTime();

		if (!DateUtil.isSameDay(now, lastTime)) {// 与上次刷新不同天，即隔天需重新刷新
			shenMiDoc.setMeysteryDayRefreshCnt(0);// 每日刷新次数归0
			int dayFreeNum = shenMiDoc.getMeysteryFreeCnt() + 2 > vipFreeLimt ? vipFreeLimt
					: shenMiDoc.getMeysteryFreeCnt() + 2;
			shenMiDoc.setMeysteryFreeCnt(dayFreeNum);// 免费刷新次数

			Vip vipGoldData = cacheManager().getExistValueByKey(Vip.class, Vip.SYSTEM_神秘商店元宝刷新次数上限);// 神秘商店VIP等级金币刷新次数一行
			int vipGoldLimt = vipGoldData.getVipByLevel(doc.getVip());// 金币刷新次数上限
			shenMiDoc.setMeysteryDayGoldRefreshCnt(vipGoldLimt);
		}
		long disSeconds = roleShenMiShopAO().disSeconds();
		// 更新免费刷新次数--meysteryFreeTime 每次重计免费次数时都要更新
		roleShenMiShopAO().refreshFreeCnt(shenMiDoc, vipFreeLimt);

		shenMiDoc.setMeysteryFreeTime(now);// 记下本次刷新时间戳
		roleShenMiShopAO().update(shenMiDoc);

		return Arrays.asList((Serializable) disSeconds, shenMiDoc.getMeysteryFreeCnt());
	}

}
