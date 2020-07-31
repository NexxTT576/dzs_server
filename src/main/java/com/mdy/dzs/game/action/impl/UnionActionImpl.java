package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import com.mdy.dzs.data.domain.battle.Npc;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.union.UnionAttr;
import com.mdy.dzs.data.domain.union.UnionShop1;
import com.mdy.dzs.data.domain.union.UnionShop2;
import com.mdy.dzs.data.domain.union.UnionWorkShop;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.UnionAction;
import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.dzs.game.domain.union.BuildUpVO;
import com.mdy.dzs.game.domain.union.CardsVO;
import com.mdy.dzs.game.domain.union.CheckShopTimeVO;
import com.mdy.dzs.game.domain.union.CheckTimeVO;
import com.mdy.dzs.game.domain.union.CheckWorkTimeVO;
import com.mdy.dzs.game.domain.union.ChooseCardsVO;
import com.mdy.dzs.game.domain.union.CoverVO;
import com.mdy.dzs.game.domain.union.DonateVO;
import com.mdy.dzs.game.domain.union.DynamicVO;
import com.mdy.dzs.game.domain.union.EnterMainBuildingVO;
import com.mdy.dzs.game.domain.union.EnterShopVO;
import com.mdy.dzs.game.domain.union.EnterSingleCopyVO;
import com.mdy.dzs.game.domain.union.EnterUnionFBVO;
import com.mdy.dzs.game.domain.union.EnterUnionVO;
import com.mdy.dzs.game.domain.union.EnterWVO;
import com.mdy.dzs.game.domain.union.EnterWelfareVO;
import com.mdy.dzs.game.domain.union.EnterWorkShopVO;
import com.mdy.dzs.game.domain.union.ExchangeVO;
import com.mdy.dzs.game.domain.union.FBList;
import com.mdy.dzs.game.domain.union.HurtVO;
import com.mdy.dzs.game.domain.union.MemberVO;
import com.mdy.dzs.game.domain.union.OpenActivityVO;
import com.mdy.dzs.game.domain.union.PropInfo;
import com.mdy.dzs.game.domain.union.RoleUnion;
import com.mdy.dzs.game.domain.union.RoleUnionFB;
import com.mdy.dzs.game.domain.union.RoleUnionFBState;
import com.mdy.dzs.game.domain.union.ShopInfo;
import com.mdy.dzs.game.domain.union.ShowAllMemberVO;
import com.mdy.dzs.game.domain.union.ShowDynamicVO;
import com.mdy.dzs.game.domain.union.ShowHurtListVO;
import com.mdy.dzs.game.domain.union.Union;
import com.mdy.dzs.game.domain.union.UnionApply;
import com.mdy.dzs.game.domain.union.UnionConfig;
import com.mdy.dzs.game.domain.union.UnionDataVO;
import com.mdy.dzs.game.domain.union.UnionDynamic;
import com.mdy.dzs.game.domain.union.UnionFB;
import com.mdy.dzs.game.domain.union.UnionFBDynamic;
import com.mdy.dzs.game.domain.union.UnionFBDynamicVO;
import com.mdy.dzs.game.domain.union.UnionGetRewardVO;
import com.mdy.dzs.game.domain.union.UnionLog;
import com.mdy.dzs.game.domain.union.UnionRoleShop;
import com.mdy.dzs.game.domain.union.UnionRoleShopInfo;
import com.mdy.dzs.game.domain.union.UnionShop;
import com.mdy.dzs.game.domain.union.UnionSuccessVO;
import com.mdy.dzs.game.domain.union.WorkShopFinishVO;
import com.mdy.dzs.game.domain.union.WorkShopProductVO;
import com.mdy.dzs.game.domain.union.WorkShopVO;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleException;
import com.mdy.dzs.game.exception.UnionException;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.fight.main.FightCfg;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.dzs.game.util.EmojiStringUtil;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.util.JSONUtil;

public class UnionActionImpl extends ApplicationAwareAction implements UnionAction {
	/** 
	 * 
	 */
	private static final Logger logger = LoggerFactory.get(UnionActionImpl.class);

	/**
	 * 建筑升级
	 */
	@Override
	public BuildUpVO unionLevelUp(String acc, int unionid, int buildtype) throws BizException {
		// buildtype: 1大殿，2作坊，3商店，4地洞，5青龙堂，6白虎堂，7帮派副本
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		// 判断玩家是否在帮派中
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion || roleUnion.getUnionId() != unionid) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(unionid);
		// 判断权限
		if (roleUnion.getJopType() != RoleUnion.vice && roleUnion.getJopType() != RoleUnion.leader) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);// 权限不足
		}
		Map<Integer, UnionAttr> udata = cacheManager().getValues(UnionAttr.class); // 帮派升级金钱配置表
		int curBuildTypeLv = unionAO().getBuildTypeLv(union, buildtype); // 当前建筑的当前等级
		int curBuildTypeLvMaX = unionAO().getBuildTypeLvMax(udata, buildtype); // 当前建筑的最大等级
		if (curBuildTypeLv >= curBuildTypeLvMaX) {
			if (buildtype == Union.UNION_TYPE_MAIN_PALACE) {
				// 等级达到上限，无法继续升级
				throw BaseException.getException(UnionException.EXCE_UNION_LEVEL_MAX);
			}
		}
		UnionAttr curUpData = null;
		for (Entry<Integer, UnionAttr> entry : udata.entrySet()) {
			UnionAttr uniona = entry.getValue();
			if (uniona.getType() == buildtype && uniona.getLevel() == curBuildTypeLv) {// 当前建筑的当前等级对应的Data
				curUpData = uniona;
				break;
			}
		}
		// 前置建筑当前等级
		int frontBuildTypeLv = unionAO().getBuildTypeLv(union, curUpData.getRequirements().get(0));
		if (curBuildTypeLv >= frontBuildTypeLv) {
			// 此建筑等级达到上限，请先提升大殿等级
			throw BaseException.getException(UnionException.EXCE_UNION_BUILD_TYPE_LEVEL_LIMIT);
		}
		int upmoney = curUpData.getUsemoney();
		if (union.getCurrentUnionMoney() < upmoney) {// 货币不足
			switch (buildtype) {
			case 1:
				throw BaseException.getException(UnionException.EXCE_UNION_MONEY_NOTENOUGH);
			case 2:
				throw BaseException.getException(UnionException.EXCE_UP_WORKERSHOP_MONEY_NOT_ENOUGH);
			case 3:
				throw BaseException.getException(UnionException.EXCE_UP_SHOP_MONEY_NOT_ENOUGH);
			case 5:
				throw BaseException.getException(UnionException.EXCE_UP_DRAGON_MONEY_NOT_ENOUGH);
			case 7:
				throw BaseException.getException(UnionException.EXCE_UP_UNIONFB_MONEY_NOT_ENOUGH);
			}

		}
		int roleNumAdd = 0;
		int unionmaxNum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.unionmaxNum));
		if (buildtype == Union.UNION_TYPE_MAIN_PALACE && union.getRoleMaxNum() < unionmaxNum) {
			roleNumAdd = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.unionRoleNumAdd));
		}
		unionAO().upUnioncenter(union, buildtype, upmoney, roleNumAdd);
		// 添加动态
		List<Object> plist = new ArrayList<Object>();
		plist.add(role.getName());
		plist.add(upmoney);
		int level = 0;
		int type = 0;
		if (roleUnion.getJopType() == RoleUnion.leader) {
			switch (buildtype) {
			case 1:
				type = UnionDynamic.BUILDING_UP;
				level = union.getLevel() + 1;
				break;
			case 2:
				type = UnionDynamic.UP_WORKSHOP;
				level = union.getWorkShopLevel() + 1;
				break;
			case 3:
				type = UnionDynamic.UP_SHOP;
				level = union.getShopLevel() + 1;
				break;
			case 4:
				type = UnionDynamic.UP_SECRET_PATH;
				break;
			case 5:
				type = UnionDynamic.UP_DRGON;
				level = union.getGreenDragonTempleLevel() + 1;
				break;
			case 7:
				type = UnionDynamic.UP_UNION_CARBON;
				level = union.getFbLevel() + 1;
				break;
			}
		} else {
			switch (buildtype) {
			case 1:
				type = UnionDynamic.UP_MIAN_BUILD_BY_VICE;
				level = union.getLevel() + 1;
				break;
			case 2:
				type = UnionDynamic.UP_WORKSHOP_BY_VICE;
				level = union.getWorkShopLevel() + 1;
				break;
			case 3:
				type = UnionDynamic.UP_SHOP_BY_VICE;
				level = union.getShopLevel() + 1;
				break;
			case 4:
				type = UnionDynamic.UP_SECRET_PATH_BY_VICE;
				break;
			case 5:
				type = UnionDynamic.UP_DRGON_BY_VICE;
				level = union.getGreenDragonTempleLevel() + 1;
				break;
			case 7:
				type = UnionDynamic.UP_UNION_CARBON_BY_VICE;
				level = union.getFbLevel() + 1;
				break;
			}
		}
		plist.add(level);
		unionDynamicAO().createDynamic(UnionDynamic.valueOf(roleUnion.getUnionId(), role.getId(), type, plist));
		BuildUpVO us = new BuildUpVO();
		if (buildtype == Union.UNION_TYPE_WORK_SHOP) {// 作坊升级
			List<WorkShopVO> wslist = new ArrayList<WorkShopVO>();
			Map<Integer, UnionWorkShop> workShop = cacheManager().getValues(UnionWorkShop.class);// 工坊配置
			for (Entry<Integer, UnionWorkShop> entry : workShop.entrySet()) {
				int isopen = 1;
				UnionWorkShop unionshop = entry.getValue();
				if (union.getWorkShopLevel() + 1 >= unionshop.getOpenLevel()) {
					isopen = 0;
				}
				// 普通侠魂生产数量公式=玩家等级*200+作坊等级*2000，
				// 加班侠魂生产数量公式=玩家等级*400+作坊等级*4000，
				int norNum = role.getLevel() * unionshop.getNorWorkOne()
						+ (union.getWorkShopLevel() + 1) * unionshop.getNorWorkTwo();
				int extNum = role.getLevel() * unionshop.getExtWorkOne()
						+ (union.getWorkShopLevel() + 1) * unionshop.getExtWorkTwo();
				long leftTime = 0;
				wslist.add(WorkShopVO.valueOf(unionshop, isopen, norNum, extNum, leftTime));
			}
			us.setTypeList(wslist);
		}
		// if(buildtype == Union.UNION_TYPE_FB){
		// //判断升级后是否需要开启新的副本
		// List<UnionFBData> FBlist = cacheManager().getUnionFBList();
		// unionFBAO().judgeUpadteUnionFBState(level,FBlist,union.getId());
		// }
		us.setBuildLevel(curBuildTypeLv + 1);
		us.setCurrentUnionMoney(union.getCurrentUnionMoney() - upmoney);
		us.setRoleMaxNum(union.getRoleMaxNum() + roleNumAdd);
		unionLogAO().add(UnionLog.valueOf(union, UnionLog.SYS_帮派_建筑升级, 1, union.getLevel() + 1,
				buildtype + "," + upmoney + "," + role.getId() + ""));
		unionLogAO().add(UnionLog.valueOf(union, UnionLog.SYS_帮派_建筑升级, upmoney, union.getCurrentUnionMoney() - upmoney,
				union.getTotalUnionMoney() + ""));
		return us;
	}

	/**
	 * 帮派捐献
	 */
	@Override
	public DonateVO unionDonate(String acc, int unionId, int donatetype) throws BizException {
		Union union = unionAO().queryUinonById(unionId);
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_UNION_IS_NOT_EXIST);
		}
		if (DateUtil.isToday(roleUnion.getLastContributionTime()) && roleUnion.getConType() != 0) {
			throw BaseException.getException(UnionException.EXCE_ROLE_HAS_CONTRIBUTE);
		}
		// 判断捐献上限
		List<RoleUnion> ruList = roleUnionAO().queryAllMember(union.getId());
		int contributed = 0;
		for (RoleUnion roleU : ruList) {
			if (DateUtil.isToday(roleU.getLastContributionTime()) && roleU.getConType() != 0) {
				contributed++;
			}
		}
		if (contributed >= union.getRoleMaxNum()) {
			throw BaseException.getException(UnionException.EXCE_TODAY_CON_IS_FULL);
		}
		int minussilver = 0;
		int minusgold = 0;
		int upuserget = 0;
		int upunionget = 0;
		int type = 0;

		List<Object> plist = new ArrayList<Object>();
		plist.add(role.getName());
		switch (donatetype) {
		case 1:// 低级捐款
			int lowvip = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.lowvip));
			if (role.getVip() < lowvip) {
				throw BaseException.getException(UnionException.EXCE_ROLE_VIP_NOT_ENOUGH);
			}
			minussilver = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.downdonateminussilver));
			upuserget = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.downdonateuserget));
			upunionget = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.downdonateunionget));
			if (role.getSilver() < minussilver) {
				throw BaseException.getException(UnionException.EXCE_UNION_SLIVER_NOTENOUGH);
			}
			plist.add(minussilver);
			type = UnionDynamic.CONTRIBUTION_SLIVER_TYPE;
			break;
		case 2:// 中级捐款
			int middlevip = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.middlevip));
			if (role.getVip() < middlevip) {
				throw BaseException.getException(UnionException.EXCE_ROLE_VIP_NOT_ENOUGH);
			}
			minusgold = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.middledonateminusgold));
			upuserget = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.middledonateuserget));
			upunionget = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.middledonateunionget));
			if (role.getGold() < minusgold) {
				throw BaseException.getException(UnionException.EXCE_UNION_DONATE_NOTENOUGH);
			}
			plist.add(minusgold);
			type = UnionDynamic.CONTRIBUTION_GOLD_TYPE;
			break;
		case 3:// 高级捐款
			int seniorvip = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.seniorvip));
			if (role.getVip() < seniorvip) {
				throw BaseException.getException(UnionException.EXCE_ROLE_VIP_NOT_ENOUGH);
			}
			minusgold = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.updonateminusgold));
			upuserget = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.updonateuserget));
			upunionget = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.updonateunionget));
			if (role.getGold() < minusgold) {
				throw BaseException.getException(UnionException.EXCE_UNION_DONATE_NOTENOUGH);
			}
			plist.add(minusgold);
			type = UnionDynamic.CONTRIBUTION_GOLD_TYPE;
			break;
		}

		switch (donatetype) {
		case 1:// 低级捐款
			packetAO().removeItemMustEnough(role, Packet.POS_ATTR, Packet.ATTR_银币, minussilver, RoleItemLog.SYS_帮派_捐献,
					"");
			packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_贡献, upuserget, RoleItemLog.SYS_帮派_捐献,
					String.valueOf(Packet.ATTR_贡献));
			roleUnionAO().updateUnionRoleTLContribute(0, union.getId(), role.getId(), donatetype, minussilver);
			break;
		case 2:
			packetAO().removeItemMustEnough(role, Packet.POS_ATTR, Packet.ATTR_金币, minusgold, RoleItemLog.SYS_帮派_捐献,
					"");
			packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_贡献, upuserget, RoleItemLog.SYS_帮派_捐献,
					String.valueOf(Packet.ATTR_贡献));
			roleUnionAO().updateUnionRoleTLContribute(0, union.getId(), role.getId(), donatetype, minusgold);
			break;
		case 3:
			packetAO().removeItemMustEnough(role, Packet.POS_ATTR, Packet.ATTR_金币, minusgold, RoleItemLog.SYS_帮派_捐献,
					"");
			packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_贡献, upuserget, RoleItemLog.SYS_帮派_捐献,
					String.valueOf(Packet.ATTR_贡献));
			roleUnionAO().updateUnionRoleTLContribute(0, union.getId(), role.getId(), donatetype, minusgold);
			break;
		}
		unionAO().updateUnionTotalAndCurrentMoney(upunionget, union);
		// task
		missionExecAO().unionDonate(role);
		plist.add(upuserget);
		plist.add(upunionget);
		unionDynamicAO().createDynamic(UnionDynamic.valueOf(unionId, role.getId(), type, plist));
		DonateVO us = new DonateVO();
		us.setSurplusGod(role.getGold());
		us.setSurplusSliver(role.getSilver());
		unionLogAO().add(UnionLog.valueOf(union, UnionLog.SYS_帮派_捐献, upunionget,
				union.getCurrentUnionMoney() + upunionget, union.getTotalUnionMoney() + upunionget + ""));
		return us;
	}

	/**
	 * 工坊生产
	 */
	@Override
	public WorkShopProductVO unionWorkShopProduct(String acc, int unionid, int overtimeflag, int worktype)
			throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		Union union = unionAO().queryUnionByUnionId(unionid);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_UNION_IS_NOT_EXIST);
		}
		// 是否需要购买次数，购买次数是否到达上限
		int bMaxNum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.buyprodectnum));
		if (DateUtil.isToday(roleUnion.getStarworktime()) && roleUnion.getBuynum() == bMaxNum) {
			throw BaseException.getException(UnionException.EXCE_UNION_PRODUCTNUM_NOTENOUGH);
		}
		// 判断是否正在生产
		int workShopLastTime = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.workShopLastTime));
		if (roleUnion.getIsOver() == 1) {
			throw BaseException.getException(UnionException.EXCE_UNION_PRODUCT_NOTOVER);
		}
		// 判断当前等级是否可以生产
		Map<Integer, UnionWorkShop> workShop = cacheManager().getValues(UnionWorkShop.class);// 工坊配置
		for (Entry<Integer, UnionWorkShop> entry : workShop.entrySet()) {
			UnionWorkShop unionshop = entry.getValue();
			if (unionshop.getId() == worktype && union.getWorkShopLevel() < unionshop.getOpenLevel()) {
				if (worktype == 1) {
					throw BaseException.getException(UnionException.EXCE_UNION_WORKSHOP_PRODUCT_XH);
				} else {
					throw BaseException.getException(UnionException.EXCE_UNION_WORKSHOP_PRODUCT_YB);
				}
			}
		}
		// 生成类型1侠魂2银币
		switch (worktype) {
		case 1:
			int xiahunOpen = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.xiahunOpen));
			if (union.getWorkShopLevel() < xiahunOpen) {
				// 校验工坊等级是否满足配置
				throw BaseException.getException(UnionException.EXCE_UNION_WORKSHOP_PRODUCT_XH);
			}
			break;
		case 2:
			int yinbiOpen = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.yinbiOpen));
			if (union.getWorkShopLevel() < yinbiOpen) {
				// 校验工坊等级是否满足配置
				throw BaseException.getException(UnionException.EXCE_UNION_WORKSHOP_PRODUCT_YB);
			}
			break;
		}
		// 判断是否加班
		// 加班生产1普通0加班 voertimegoldnum
		int costGold = 0;
		switch (overtimeflag) {
		case 0:
			costGold = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.voertimegoldnum));
			break;
		}
		int extCostGold = 0;
		switch (roleUnion.getBuynum() + 1) {
		case 1:
			if (roleUnion.getStarworktime() == null) {
				extCostGold = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.onebuygoldnum));
			} else {
				extCostGold = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.twobuygoldnum));
			}
			break;
		case 2:
			extCostGold = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.threebuygoldnum));
			break;
		case 4:
			extCostGold = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.onebuygoldnum));
			break;
		}
		String type = UnionConfig.onebuygoldnum;
		int buyNum = 0;
		// 判断免费次数是否足够
		if (DateUtil.isToday(roleUnion.getStarworktime())) {
			if (roleUnion.getBuynum() == 0) {
				type = UnionConfig.onebuygoldnum;
			} else if (roleUnion.getBuynum() == 1) {
				type = UnionConfig.twobuygoldnum;
			} else if (roleUnion.getBuynum() == 2) {
				type = UnionConfig.threebuygoldnum;
			}
			costGold = costGold + Integer.valueOf(cacheManager().getUnionConfigValue(type));
			buyNum = roleUnion.getBuynum() + 1;
		} else {
			extCostGold = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.onebuygoldnum));
		}
		if (role.getGold() < costGold) {
			throw BaseException.getException(UnionException.EXCE_UNION_OVERTIMEGOLD_NOTENOUGH);
		}
		packetAO().removeItemMustEnough(role, Packet.POS_ATTR, Packet.ATTR_金币, costGold, RoleItemLog.SYS_帮派_工坊生产, "");
		WorkShopProductVO ws = new WorkShopProductVO();
		// 免费次数有没有，是否消耗购买次数 0 有免费次数
		ws.setFreeCount(0);
		RoleUnion rubean = new RoleUnion();
		rubean.setRoleId(role.getId());
		if (buyNum > bMaxNum) {
			rubean.setBuynum(0);
		} else {
			rubean.setBuynum(buyNum);
		}
		rubean.setStarworktime(DateUtil.GetNowDateTime());
		rubean.setWorktype(Integer.valueOf(worktype));
		rubean.setOvertimeflag(Integer.valueOf(overtimeflag));
		rubean.setShopWorkrdReward(1);
		rubean.setIsOver(1);
		roleUnionAO().updateRoleProduct(rubean);
		ws.setExtCostGold(extCostGold);
		if (DateUtil.isToday(roleUnion.getStarworktime())) {
			ws.setExtCount(bMaxNum - buyNum);
		} else {
			ws.setExtCount(3);
		}
		ws.setSurplusGold(role.getGold());
		ws.setSurplusTime(workShopLastTime * 60 * 60);
		return ws;
	}

	/**
	 * 立即结束&领取奖励
	 */
	@Override
	public WorkShopFinishVO unionWorkShopGetReward(String acc, int unionid) throws BizException {
		Role role = roleAO().queryByAccount(acc);
		RoleUnion ru = roleUnionAO().queryRoleUnionById(role.getId());
		if (ru == null || ru.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_UNION_IS_NOT_EXIST);
		}
		int endglod = 0;
		endglod = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.fastendgoldnum));
		/** 生产是否结束0结束1未结束 */
		if (ru.getIsOver() == 0) {
			throw BaseException.getException(UnionException.EXCE_WORK_SHOP_IS_OVER);
		}
		if (role.getGold() < endglod) {
			throw BaseException.getException(UnionException.EXCE_UNION_FASTEND_GOLDNOTENOUGH);
		}
		Union adv = unionAO().queryUnionByUnionId(unionid);
		// 计算玩家得到的奖励
		int sumxh = 0;
		int sumyb = 0;
		switch (ru.getOvertimeflag()) {
		case 0:
			if (ru.getWorktype() == 1) {
				// 加班生产侠魂
				int userlevelnum = role.getLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectxhstylebyfast).split("_")[0]);
				int unionlevelnum = adv.getWorkShopLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectxhstylebyfast).split("_")[1]);
				sumxh = userlevelnum + unionlevelnum;
			} else {
				// 加班生产银币
				int userlevelnum = role.getLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectybstylebyfast).split("_")[0]);
				int unionlevelnum = adv.getWorkShopLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectybstylebyfast).split("_")[1]);
				sumyb = userlevelnum + unionlevelnum;
			}
			break;
		case 1:
			if (ru.getWorktype() == 1) {
				int userlevelnum = role.getLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectxhstylebycommon).split("_")[0]);
				int unionlevelnum = adv.getWorkShopLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectxhstylebycommon).split("_")[1]);
				sumxh = userlevelnum + unionlevelnum;
			} else {
				int userlevelnum = role.getLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectybstylebycommon).split("_")[0]);
				int unionlevelnum = adv.getWorkShopLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectybstylebycommon).split("_")[1]);
				sumyb = userlevelnum + unionlevelnum;
			}
			break;
		}
		// 扣除金币
		packetAO().removeItemMustEnough(role, Packet.POS_ATTR, Packet.ATTR_金币, endglod, RoleItemLog.SYS_帮派_工坊立即结束, "");
		// 状态改为已领取 状态改为一结束
		ru.setIsOver(0);
		ru.setShopWorkrdReward(0);
		roleUnionAO().updateRoleProduct(ru);
		WorkShopFinishVO wsf = new WorkShopFinishVO();
		wsf.setGold(role.getGold());
		List<ProbItem> rstAry = new ArrayList<ProbItem>();
		if (sumxh != 0) {
			rstAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_侠魂, sumxh));
			packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_侠魂, sumxh, RoleItemLog.SYS_帮派_工坊立即结束,
					String.valueOf(Packet.ATTR_侠魂));
		} else {
			rstAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_银币, sumyb));
			packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_银币, sumyb, RoleItemLog.SYS_帮派_工坊立即结束,
					String.valueOf(Packet.ATTR_银币));
		}
		wsf.setRewardList(rstAry);
		return wsf;
	}

	/**
	 * 进入商店，获取商品列表
	 */
	@Override
	public EnterShopVO unionShopList(String acc, int unionId, int endflag) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		if (roleUnion.getUnionId() != unionId) {
			throw BaseException.getException(UnionException.EXCE_UNION_IS_NOT_EXIST);
		}
		Union union = unionAO().queryUnionByUnionId(unionId);
		List<UnionShop1> gemList = new ArrayList<UnionShop1>();
		List<ShopInfo> gList = new ArrayList<ShopInfo>();
		List<PropInfo> pList = new ArrayList<PropInfo>();
		List<UnionRoleShopInfo> URSIList = new ArrayList<UnionRoleShopInfo>();
		List<UnionShop2> propList = new ArrayList<UnionShop2>();
		UnionShop unionShop = unionShopAO().queryShopInfoByUnionId(union.getId());
		int addTime = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.unionShopTime));
		// 刷新时间，存起来
		Date rTime = DateUtil.addTime(DateUtil.getNowDayTime(), addTime);
		// 上次刷新时间
		Date yTime = DateUtil.getDateAfter(rTime, -1);
		List<Integer> idsList = new ArrayList<Integer>();
		gemList = getGoodSInfo(union);
		Date now = DateUtil.GetNowDateTime();
		if (unionShop == null || (now.after(rTime) && rTime.after(unionShop.getRefreshTime()))
				|| (unionShop.getRefreshTime().before(yTime))) {
			// 刷新
			if (unionShop == null) {

				unionShopAO().createShopInfo(UnionShop.valueOf(union.getId(), gemList, now));
			} else {
				unionShopAO().updateShopInfo(UnionShop.valueOf(union.getId(), gemList, now));
			}
			roleUnion.setExchangeTime(now);
			roleUnionAO().updateExchangeShopInfo(roleUnion, idsList);
			for (UnionShop1 unionShop1 : gemList) {
				RoleItem ritemData = packetAO().queryByAccItemId(role.getId(), unionShop1.getItemId(),
						unionShop1.getType());
				int count = 0;
				if (ritemData != null) {
					count = ritemData.getItemCnt();
				}
				gList.add(ShopInfo.valeOf(unionShop1, 1, count));
			}
		} else {
			// 不刷新列表
			gemList = JSONUtil.fromJsonList(unionShop.getGoodsInfo(), UnionShop1.class);
			if (roleUnion.getShopIds() != null) {
				idsList = JSONUtil.fromJsonList(roleUnion.getShopIds(), Integer.class);
			}
			if (roleUnion.getExchangeTime() != null) {
				if (roleUnion.getExchangeTime().before(yTime) || (roleUnion.getExchangeTime().after(yTime)
						&& roleUnion.getExchangeTime().before(rTime) && now.after(rTime))) {
					idsList.clear();
					roleUnionAO().updateExchangeShopInfo(roleUnion, idsList);
				}
			}
			for (UnionShop1 unionShop1 : gemList) {
				RoleItem ritemData = packetAO().queryByAccItemId(role.getId(), unionShop1.getItemId(),
						unionShop1.getType());
				int count = 0;
				if (ritemData != null) {
					count = ritemData.getItemCnt();
				}
				if (idsList.contains(unionShop1.getId())) {
					gList.add(ShopInfo.valeOf(unionShop1, 0, count));
				} else {
					gList.add(ShopInfo.valeOf(unionShop1, 1, count));
				}
			}
			roleUnionAO().updateExchangeShopInfo(roleUnion, idsList);
		}
		// 个人商店刷新时间
		int add = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.roleUnionShopTime));
		// 刷新时间，存起来
		Date reTime = DateUtil.addTime(DateUtil.getNowDayTime(), add);
		// 上次刷新时间
		Date ryTime = DateUtil.getDateAfter(reTime, -1);
		UnionRoleShop unionRoleShop = unionRoleShopAO().queryShopInfoByRoleId(role.getId());
		propList = getPropGoodsInfo(union);
		// 刷新时间相等 不用刷新
		if (unionRoleShop == null || (now.after(reTime) && reTime.after(unionRoleShop.getRefreshTime()))
				|| (unionRoleShop.getRefreshTime().before(ryTime))) {

			// 获取新的道具list
			for (UnionShop2 US2 : propList) {
				URSIList.add(UnionRoleShopInfo.valueOf(US2.getId(), US2.getExchange()));
			}
			if (unionRoleShop == null) {
				unionRoleShopAO().createUnionRoleShopInfo(UnionRoleShop.valueOf(role.getId(), URSIList, now));
			} else {
				unionRoleShopAO().updateUnionRoleShopInfo(UnionRoleShop.valueOf(role.getId(), URSIList, now));
			}
		} else {
			URSIList = JSONUtil.fromJsonList(unionRoleShop.getGoodsInfo(), UnionRoleShopInfo.class);
		}

		for (UnionRoleShopInfo unionSI : URSIList) {
			for (UnionShop2 unionsi : propList) {
				if (unionsi.getId() == unionSI.getId()) {
					RoleItem ritemData = packetAO().queryByAccItemId(role.getId(), unionsi.getItemId(),
							unionsi.getType());
					int count = 0;
					if (ritemData != null) {
						count = ritemData.getItemCnt();
					}
					pList.add(PropInfo.valueOf(unionsi, count, unionSI.getExchange()));
				}
			}
		}
		// 获取商品道具list
		EnterShopVO enterS = new EnterShopVO();
		enterS.setJopType(roleUnion.getJopType());
		enterS.setUnionCurrentMoney(union.getCurrentUnionMoney());
		enterS.setLastContribute(roleUnion.getLastContribute());
		if (endflag == 1) {
			enterS.setShopListA(gList);
		} else if (endflag == 2) {
			enterS.setShopListB(pList);
		} else {
			enterS.setShopListA(gList);
			enterS.setShopListB(pList);
		}
		enterS.setShopLevel(union.getShopLevel());
		if (DateUtil.GetNowDateTime().before(rTime)) {
			enterS.setSurplusTime(DateUtil.howLongBetween2Time(DateUtil.GetNowDateTime(), rTime, 4));
		} else if (roleUnion.getExchangeTime() == null || DateUtil.GetNowDateTime().after(rTime)) {
			Date edate = DateUtil.getInternalDateByDay(rTime, 1);
			enterS.setSurplusTime(DateUtil.howLongBetween2Time(DateUtil.GetNowDateTime(), edate, 4));
		}
		return enterS;
	}

	/**
	 * 获取配置表的所有商品信息 珍品
	 */
	public List<UnionShop1> getGoodSInfo(Union union) throws BizException {
		List<UnionShop1> glist = new ArrayList<UnionShop1>();
		int maxweight = 0;
		Map<Integer, UnionShop1> ushop1 = cacheManager().getValues(UnionShop1.class);// 帮派升级金钱配置
		Map<Integer, UnionShop1> map = new TreeMap<Integer, UnionShop1>();
		for (Entry<Integer, UnionShop1> entry : ushop1.entrySet()) {
			UnionShop1 unionshop = entry.getValue();
			if (unionshop.getOpenLevel() == union.getShopLevel()) {
				glist.add(unionshop);
				maxweight += unionshop.getWeight();
				map.put(unionshop.getId(), unionshop);
			}
		}
		int maxshopnum = 0;
		String unionshopforlevel = cacheManager().getUnionConfigValue(UnionConfig.unionshopforlevel);
		for (int i = 0; i < unionshopforlevel.split(",").length; i++) {
			String[] levelnum = unionshopforlevel.split(",")[i].split("_");
			int level = Integer.valueOf(levelnum[0]);
			if (union.getShopLevel() == level) {
				maxshopnum = Integer.valueOf(levelnum[1]);
			}
		}
		if (map.size() == 0 || maxshopnum == 0) {
			throw BaseException.getException(UnionException.EXCE_UNION_SHOPCONFIG_ERROR);
		}
		List<UnionShop1> rList = new ArrayList<UnionShop1>();
		glist = getRandomNum(map, maxweight, maxshopnum, glist, union, rList);
		return glist;
	}

	// 递归获取随机数工具类_特定商店使用
	public static List<UnionShop1> getRandomNum(Map<Integer, UnionShop1> map, int maxweight, int size,
			List<UnionShop1> retlist, Union union, List<UnionShop1> rList) {
		if (size <= 0) {
			return retlist;
		}
		if (size >= retlist.size()) {
			return retlist;
		}
		Random random = new Random();
		int v = random.nextInt(maxweight);
		int total = 0;
		for (Integer key : map.keySet()) {
			UnionShop1 unionShop = map.get(key);
			total += unionShop.getWeight();
			if (union.getShopLevel() == unionShop.getOpenLevel() && unionShop.getWeight() == 0) {
				rList.add(unionShop);
				map.remove(key);
				break;
			}
			if (v < total) {
				rList.add(unionShop);
				map.remove(key);
				break;
			}
		}
		if (rList.size() < size && map.size() > 0) {
			int max = 0;
			for (UnionShop1 key : map.values()) {
				max += key.getWeight();
			}
			getRandomNum(map, max, size, retlist, union, rList);
		}
		return rList;
	}

	/**
	 * 获取商品道具列表
	 */
	public List<UnionShop2> getPropGoodsInfo(Union union) throws BizException {
		List<UnionShop2> retlist2 = cacheManager().getUnionShop2List();// 帮派升级金钱配置
		return retlist2;
	}

	/**
	 * 创建帮派
	 */
	@Override
	public UnionDataVO createUnion(String acc, String name, int type) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		// 判断玩家等级是否满足
		int limitLevel = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.roleUnionLevel));
		if (role.getLevel() < limitLevel) {
			throw BaseException.getException(UnionException.EXCE_ROLE_LEVEL_NOT_ENOUGH);
		}
		// 查询玩家是否在其他帮派
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion != null) {
			if (roleUnion.getState() == RoleUnion.roleinUnion) {
				throw BaseException.getException(UnionException.EXCE_ROLE_IN_UNION);
			}
			// 查询玩家退出帮派的时间是否大于24小时，
			if (roleUnion.getOutTime() != null) {
				if (DateUtil.judgmentDate(roleUnion.getOutTime())) {
					throw BaseException.getException(UnionException.EXCE_ROLE_ONT_ENOUGH_24HOURS);
				}
			}
		}
		// 名称不允许使用表情符
		if (EmojiStringUtil.containsEmoji(name)) {
			logger.error("ERROR:" + EmojiStringUtil.utf8ToUnicode(name));
			throw BaseException.getException(RoleException.EXCE_NAME_NOT_HAVE_EMOJI);
		}
		// 判断帮派名称是否存在
		Union union = unionAO().queryUinonByName(name);
		if (union != null) {
			throw BaseException.getException(UnionException.EXCE_UNION_NAME_IS_EXIST);
		}
		int createGold = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.createGold));
		if (role.getGold() < createGold) {
			throw BaseException.getException(UnionException.EXCE_ROLE_ONT_ENOUGH_GOLD);
		}
		int rank = 1;
		List<UnionDataVO> list = unionAO().queryUinonListByName("", role.getId());
		if (!list.isEmpty()) {
			rank = list.size();
		}
		int uid = unionAO().createUion(role.getId(), name, role.getAttack(), rank + 1);
		RoleArena roleArena = roleArenaAO().queryByRoleId(role.getId());

		// 在帮派人员表中创建帮主信息
		if (roleUnion == null) {
			roleUnionAO().createRoleUnion(uid, role.getId(), RoleUnion.leader, roleArena.getRank());
		} else {
			roleUnionAO().updateRoleUnion(RoleUnion.ValueOf(uid, role.getId(), RoleUnion.leader, roleArena.getRank()));
		}
		// 修改玩家副本状态
		roleUnionFBStateAO().updateRoleUnionFB(role.getId(), uid);
		// 删除玩家申请信息
		List<UnionApply> applylist = unionApplyAO().queryRoleApply(role.getId());
		for (UnionApply unionApply : applylist) {
			unionApplyAO().deleteRoleApply(role.getId(), unionApply.getUnionId());
		}
		packetAO().removeItemMustEnough(role, Packet.POS_ATTR, Packet.ATTR_金币, createGold, RoleItemLog.SYS_帮派_创建帮派,
				String.valueOf(uid));
		Union newUnion = unionAO().queryUinonById(uid);
		roleAO().updateRoleFaction(role, newUnion.getName());
		RoleUnion newroleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		UnionDataVO unionVO = newUnion.valueData(newroleUnion);
		unionVO.setNowRoleNum(1);
		unionVO.setSurplusGold(role.getGold());
		unionLogAO().add(
				UnionLog.valueOf(newUnion, UnionLog.SYS_帮派_创建帮派, newUnion.getId(), role.getId(), newUnion.getName()));
		return unionVO;
	}

	/**
	 * 申请/取消申请加入帮派
	 */
	@Override
	public UnionSuccessVO applyUnion(String acc, int uid, int type) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		Union union = unionAO().queryUinonById(uid);
		if (union == null) {
			throw BaseException.getException(UnionException.EXCE_UNION_IS_NOT_EXIST);
		}
		if (type == 0) {
			// 查询玩家是否在其他帮派
			RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
			if (roleUnion != null) {
				if (roleUnion.getState() == RoleUnion.roleinUnion) {
					throw BaseException.getException(UnionException.EXCE_ROLE_IN_UNION);
				}
				// 查询玩家退出帮派的时间是否大于24小时，
				if (roleUnion.getOutTime() != null) {
					if (DateUtil.judgmentDate(roleUnion.getOutTime())) {
						throw BaseException.getException(UnionException.EXCE_ROLE_ONT_ENOUGH_24HOURS);
					}
				}
			}
			// 申请该帮派的人数是否到上限
			int applyNum = unionApplyAO().queryApplyNum(uid);
			int subNum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.subNum));
			if (applyNum >= subNum) {
				throw BaseException.getException(UnionException.EXCE_UNION_NUM_IS_FULL);
			}
			// 判断帮派人数是否已满
			int roleNum = roleUnionAO().queryAllMemberNum(uid);
			if (roleNum >= union.getRoleMaxNum()) {
				throw BaseException.getException(UnionException.EXCE_UNION_IS_FULL);
			}
			// 查询玩家是否到达申请上限
			List<UnionApply> applyList = unionApplyAO().queryRoleApply(role.getId());
			int maxNum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.applyUnionMaxNum));
			if (applyList != null) {
				if (applyList.size() >= maxNum) {
					throw BaseException.getException(UnionException.EXCE_UNION_APPLY_IS_FULL);
				}
				// 判断是否申请过该帮派
				for (UnionApply uniona : applyList) {
					if (uniona.getUnionId() == union.getId()) {
						throw BaseException.getException(UnionException.EXCE_UNION_APPLY_ISEXIST);
					}
				}
			}
			// 创建新的申请
			RoleArena roleArena = roleArenaAO().queryByRoleId(role.getId());
			unionApplyAO().createUnionApply(uid, role.getId(), role.getLevel(), roleArena.getRank());
		} else {
			// 取消申请
			List<UnionApply> applyList = unionApplyAO().queryRoleApply(role.getId());
			for (UnionApply uniona : applyList) {
				if (uniona.getUnionId() == union.getId()) {
					// 删除申请信息
					unionApplyAO().deleteRoleApply(role.getId(), uid);
				}
			}
		}
		UnionSuccessVO unionSuccess = new UnionSuccessVO();
		unionSuccess.setSuccess(0);
		return unionSuccess;
	}

	/**
	 * 同意/拒绝
	 */
	@Override
	public UnionSuccessVO handleApply(int type, String acc, int unionId, int applyRoleId) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		Role appRoe = roleAO().queryById(applyRoleId);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		RoleUnion applyRoleUnion = roleUnionAO().queryRoleUnionById(applyRoleId);
		Union union = unionAO().queryUinonById(unionId);
		// 判断权限是否足够
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		if (roleUnion.getJopType() == RoleUnion.member) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		if (roleUnion.getUnionId() != unionId) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		int limitLevel = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.roleUnionLevel));
		// 判断等级是否满足
		if (appRoe.getLevel() < limitLevel) {
			throw BaseException.getException(UnionException.EXCE_ROLE_LEVEL_NOT_ENOUGH);
		}
		// 判断是否申请该帮派
		UnionApply oneApply = unionApplyAO().queryOneUnionApply(applyRoleId, unionId);
		if (oneApply == null) {
			throw BaseException.getException(UnionException.EXCE_UNION_APPLY_IS_NOT_EXIST);
		}
		// 0同意或1拒绝
		if (type == 0) {
			// 判断是否已加入其它帮派
			if (applyRoleUnion != null && applyRoleUnion.getState() == RoleUnion.roleinUnion) {
				throw BaseException.getException(UnionException.EXCE_ROLE_IN_OTHER_UNION);
			}
			// 判断帮派人数是否已满 根据帮派等级查询最大人数 比较
			int unionMemberNum = roleUnionAO().queryAllMemberNum(unionId);
			if (unionMemberNum >= union.getRoleMaxNum()) {
				throw BaseException.getException(UnionException.EXCE_UNION_IS_FULL_FOR_AGREE);
			}
			// 判断时间限制
			if (roleUnion != null) {
				// 查询玩家退出帮派的时间是否大于24小时，
				if (roleUnion.getOutTime() != null) {
					if (DateUtil.judgmentDate(roleUnion.getOutTime())) {
						throw BaseException.getException(UnionException.EXCE_ROLE_ONT_ENOUGH_24HOURS);
					}
				}
			}
			RoleArena roleArena = roleArenaAO().queryByRoleId(appRoe.getId());
			// 查询玩家帮派信息有就修改，没有就创建
			if (applyRoleUnion != null) {
				applyRoleUnion.setJopType(RoleUnion.member);
				applyRoleUnion.setState(RoleUnion.roleinUnion);
				applyRoleUnion.setUnionId(unionId);
				roleUnionAO().updateRoleUnion(
						RoleUnion.ValueOf(unionId, appRoe.getId(), RoleUnion.member, roleArena.getRank()));
			} else {
				roleUnionAO().createRoleUnion(unionId, applyRoleId, RoleUnion.member, roleArena.getRank());
			}
			// 修改玩家副本状态
			roleUnionFBStateAO().updateRoleUnionFB(role.getId(), unionId);
			// 删除玩家申请信息
			List<UnionApply> applylist = unionApplyAO().queryRoleApply(applyRoleId);
			for (UnionApply unionApply : applylist) {
				unionApplyAO().deleteRoleApply(applyRoleId, unionApply.getUnionId());
			}
			mailAO().unionApplyAgree(applyRoleId, union.getName());
			unionAO().updateUnionSumAttack(union, appRoe.getAttack());
			List<Object> plist = new ArrayList<Object>();
			plist.add(appRoe.getName());
			unionDynamicAO()
					.createDynamic(UnionDynamic.valueOf(unionId, appRoe.getId(), UnionDynamic.JOIN_UNION_TYPE, plist));
			roleAO().updateRoleFaction(appRoe, union.getName());
		} else {
			// 发通知邮件
			unionApplyAO().deleteRoleApply(applyRoleId, unionId);
			mailAO().unionApplyRefuse(applyRoleId, union.getName());
		}
		UnionSuccessVO unionSuccess = new UnionSuccessVO();
		unionSuccess.setSuccess(0);
		return unionSuccess;
	}

	/**
	 * 查询帮派
	 */
	@Override
	public UnionDataVO searchUnion(String acc, String unionName, int start, int total) throws BizException {
		// 判断玩家是否存在
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		List<UnionDataVO> unionList = unionAO().queryUinonListByName(unionName, role.getId());
		List<UnionDataVO> relist = new ArrayList<UnionDataVO>();
		if (unionList.isEmpty()) {
			throw BaseException.getException(UnionException.EXCE_SEARCH_UNION_IS_NOT_EXIST);
		}
		UnionDataVO unionData = new UnionDataVO();
		if (unionList.size() > total) {
			for (int i = start; i < start + total; i++) {
				if (i == unionList.size()) {
					break;
				}
				relist.add(unionList.get(i));
			}
			unionData.setUnionList(relist);
		} else {
			unionData.setUnionList(unionList);
		}
		if (roleUnion != null) {
			unionData.setJopType(roleUnion.getJopType());
		}
		unionData.setTotalNum(unionList.size());
		return unionData;
	}

	/**
	 * 0修改公告1修改宣言
	 */
	@Override
	public UnionSuccessVO updateUnionIndes(String acc, String msg, int type) throws BizException {
		// 判断权限是否足够
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		if (type == 0) {
			// 修改公告
			if (roleUnion.getJopType() == RoleUnion.member || roleUnion.getJopType() == RoleUnion.elders) {
				throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
			}
		} else {
			// 修改宣言
			if (roleUnion.getJopType() != RoleUnion.leader && roleUnion.getState() == RoleUnion.roleinUnion) {
				throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
			}
		}
		unionAO().updateUnionIndes(union, msg, type);
		UnionSuccessVO unionSuccess = new UnionSuccessVO();
		unionSuccess.setSuccess(0);
		unionLogAO()
				.add(UnionLog.valueOf(union, UnionLog.SYS_帮派_修改公告宣言, union.getId(), role.getId(), type + "," + msg));
		return unionSuccess;
	}

	/**
	 * 禅让帮主
	 */
	@Override
	public UnionSuccessVO abdication(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		List<RoleUnion> roleUnionList = roleUnionAO().queryAllMember(roleUnion.getUnionId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		// 权限是否足够
		if (roleUnion.getJopType() != RoleUnion.leader && roleUnion.getState() != RoleUnion.roleinUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		// 是否存在副帮主
		int viceRoleId = 0;
		for (RoleUnion roleU : roleUnionList) {
			if (roleU.getJopType() == RoleUnion.vice) {
				viceRoleId = roleU.getRoleId();
			}
		}
		if (viceRoleId == 0) {
			throw BaseException.getException(UnionException.EXCE_VICE_NOT_EXIST);
		}
		// 修改帮主职务为成员
		roleUnionAO().updateRoleJop(roleUnion, RoleUnion.member);
		RoleUnion vicerole = roleUnionAO().queryRoleUnionById(viceRoleId);
		Role viceRole = roleAO().queryExistId(viceRoleId);
		// 修改副帮主为帮主
		roleUnionAO().updateRoleJop(vicerole, RoleUnion.leader);
		union.setBossId(viceRoleId);
		unionAO().updateBossInfo(union);
		List<Object> plist = new ArrayList<Object>();
		plist.add(role.getName());
		plist.add(viceRole.getName());
		unionDynamicAO().createDynamic(
				UnionDynamic.valueOf(roleUnion.getUnionId(), role.getId(), UnionDynamic.TRANSFER_UNION, plist));
		UnionSuccessVO unionSuccess = new UnionSuccessVO();
		unionSuccess.setSuccess(0);
		unionLogAO()
				.add(UnionLog.valueOf(union, UnionLog.SYS_帮派_禅让帮主, union.getId(), role.getId(), viceRole.getId() + ""));
		return unionSuccess;
	}

	/**
	 * 帮主自荐
	 * 
	 * @param acc
	 * @param leaderId
	 * @return
	 * @throws BizException
	 */
	@Override
	public CoverVO coverLeader(String acc, int leaderId) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		// 判断权限
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		Role leader = roleAO().queryExistId(union.getBossId());
		if (roleUnion.getJopType() == RoleUnion.leader || roleUnion.getJopType() == RoleUnion.member) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		// 判断帮主是否七天未登陆
		String hertDate = DateUtil.TimeStamp2Date(String.valueOf(leader.getHeartLastTime()));
		// leader.getHeartLastTime()
		if (DateUtil.GetNowDateTime().before(DateUtil.getBackOrForwardDay(DateUtil.getDayTime(hertDate), 7))) {
			throw BaseException.getException(UnionException.EXCE_LEADER_LEAVE_NOT_IN_SDAYS);// 未到七天
		}
		// 添加自荐信息
		roleUnionAO().updatecoverLeader(role.getId(), DateUtil.GetNowDateTime(), roleUnion.getUnionId());
		if (union.getCoverTime() == null) {
			unionAO().updateUnionCover(union, DateUtil.GetNowDateTime());
		}
		CoverVO cover = new CoverVO();
		List<RoleUnion> roleUnionlist = roleUnionAO().queryCoverRoleUnion(union.getId());
		List<String> namelist = new ArrayList<String>();
		for (RoleUnion roleU : roleUnionlist) {
			Role roleN = roleAO().queryById(roleU.getRoleId());
			namelist.add(roleN.getName());
		}
		int size = roleUnionlist.size();
		switch (size) {
		case 1:
			cover.setFirstName(namelist.get(0));
			break;
		case 2:
			cover.setFirstName(namelist.get(0));
			cover.setSecondName(namelist.get(1));
			break;
		case 3:
			cover.setFirstName(namelist.get(0));
			cover.setSecondName(namelist.get(1));
			cover.setThreeName(namelist.get(2));
			break;
		}
		if (union.getCoverTime() == null) {
			cover.setTime(2 * 24 * 60 * 60);
		} else {
			cover.setTime(DateUtil.howLongBetween2Time(union.getCoverTime(), DateUtil.GetNowDateTime(), 2));
		}
		unionLogAO()
				.add(UnionLog.valueOf(union, UnionLog.SYS_帮派_帮主自荐, union.getId(), role.getId(), leader.getId() + ""));
		return cover;
	}

	/**
	 * 
	 * 进入帮派
	 * 
	 * @throws BizException
	 */
	@Override
	public EnterUnionVO enterUnion(String acc, int num) throws BizException {
		List<UnionDataVO> unionList = null;
		EnterUnionVO unionVO = new EnterUnionVO();
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		List<UnionDataVO> relist = new ArrayList<UnionDataVO>();
		// 判断等级是否满足
		int limitLevel = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.roleUnionLevel));
		if (role.getLevel() < limitLevel) {
			throw BaseException.getException(UnionException.EXCE_ROLE_LEVEL_NOT_ENOUGH);
		}
		// 判断是否在帮派中
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			// 申请过和未申请过的 申请过的加标示
			unionList = unionAO().queryUnionBypage(1, role.getId());
			unionVO.setInUnion(false);
			unionVO.setChangeCover(false);
			if (num > unionList.size()) {
				unionVO.setUnionList(unionList);
			} else {
				for (int i = 0; i < num; i++) {
					relist.add(unionList.get(i));
				}
				unionVO.setUnionList(relist);
			}
			unionVO.setTotalNum(unionList.size());
		} else {// 在帮派中
			Union union = unionAO().queryUinonById(roleUnion.getUnionId());
			int roleNum = roleUnionAO().queryAllMemberNum(union.getId());
			List<RoleUnion> roleUnionlist = roleUnionAO().queryCoverRoleUnion(union.getId());
			// 判断帮派是否有自荐信息
			if (union.getCoverTime() != null && DateUtil.isInDays(union.getCoverTime(), 2)) {
				// 判断是否在自荐的2天之内 帮主登录去除自荐信息
				if (roleUnion.getJopType() == RoleUnion.leader) {
					// 在两天之内登陆状态修改
					unionAO().updateUnionCover(union, DateUtil.getDayTime(""));
					roleUnionAO().updateRoleCover(union.getId());
				} else {// 不是帮主 显示自荐人信息 剩余时间
					List<String> namelist = new ArrayList<String>();
					for (RoleUnion roleU : roleUnionlist) {
						Role roleN = roleAO().queryById(roleU.getRoleId());
						namelist.add(roleN.getName());
					}
					int size = roleUnionlist.size();
					CoverVO coverVO = new CoverVO();
					switch (size) {
					case 1:
						coverVO.setFirstName(namelist.get(0));
						break;
					case 2:
						coverVO.setFirstName(namelist.get(0));
						coverVO.setSecondName(namelist.get(1));
						break;
					case 3:
						coverVO.setFirstName(namelist.get(0));
						coverVO.setSecondName(namelist.get(1));
						coverVO.setThreeName(namelist.get(2));
						break;
					}
					unionVO.setCoverVO(coverVO);
					unionVO.setChangeCover(true);
					long surplusTime = DateUtil.howLongBetween2Time(DateUtil.getStringByDate(union.getCoverTime()),
							DateUtil.getStringByDate(DateUtil.GetNowDateTime()), 2);
					unionVO.setSurplusTime(surplusTime);
				}
			} else {// 不在自荐时间内，判断是否已经满足自荐，若满足，修改bangz
				if (union.getCoverTime() != null
						&& DateUtil.GetNowDateTime().after(DateUtil.getBackOrForwardDay(union.getCoverTime(), 2))) {
					RoleUnion boss = roleUnionAO().queryRoleUnionById(union.getBossId());
					RoleUnion newleader = new RoleUnion();
					flag: for (RoleUnion roleU : roleUnionlist) {
						switch (roleU.getJopType()) {
						case 1:
							if (newleader != null) {
								if (roleU.getTotalContribute() > newleader.getTotalContribute()) {
									newleader = roleU;
								}
							}
						case 2:
							newleader = roleU;
							break flag;
						}
					}
					union.setBossId(newleader.getRoleId());
					union.setCoverTime(DateUtil.getDayTime(""));
					unionAO().updateBossInfo(union);
					roleUnionAO().updateRoleJop(boss, RoleUnion.member);
					roleUnionAO().updateRoleJop(newleader, RoleUnion.leader);
					roleUnionAO().updateRoleCover(union.getId());
				}
			}
			// t添加帮派副本等级
			unionVO.setUnion(union.valueData(roleUnion));
			unionVO.setInUnion(true);
			unionVO.getUnion().setNowRoleNum(roleNum);
		}
		return unionVO;
	}

	/**
	 * 一键拒绝
	 */
	@Override
	public UnionSuccessVO refuseAll(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		List<UnionApply> applyList = unionApplyAO().queryApplyList(roleUnion.getUnionId());
		// 判断是否有权限
		if (roleUnion.getJopType() == 0) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		// 删除该帮派的所有申请
		for (UnionApply unionApply : applyList) {
			unionApplyAO().deleteRoleApply(unionApply.getRoleId(), roleUnion.getUnionId());
			mailAO().unionApplyRefuse(unionApply.getRoleId(), union.getName());
		}
		UnionSuccessVO unionSuccess = new UnionSuccessVO();
		unionSuccess.setSuccess(0);
		return unionSuccess;
	}

	/**
	 * 领取每周福利
	 */
	public UnionGetRewardVO getBenefits(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		// 判断天数是否足够
		int joinUnionTime = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.joinUnionTime));
		if (DateUtil.isInDays(roleUnion.getIntoTime(), joinUnionTime)) {
			throw BaseException.getException(UnionException.EXCE_ROLE_IN_UNION_NOT_FIVEDAYS);
		}
		// 玩家等级是否满足
		int levelLimit = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.levelLimit));
		if (role.getLevel() < levelLimit) {
			throw BaseException.getException(UnionException.EXCE_ROLE_LEVEL_IN_FORTY);
		}

		Integer userule = Integer.parseInt(cacheManager().getUnionConfigValue(UnionConfig.userule));
		// 贡献点是否足够
		if (roleUnion.getLastContribute() < userule) {
			throw BaseException.getException(UnionException.EXCE_ROLE_CONTRBUTE_NOT_ENOUGH);
		}
		Date sunday = DateUtil.getCurrentSunday();
		Date mon = DateUtil.getCurrentMonDay();
		// 判断是否已经领取过 周六
		if (roleUnion.getWeeklyBenefits() != null && (sunday.getTime() - roleUnion.getWeeklyBenefits().getTime() >= 0)
				&& (roleUnion.getWeeklyBenefits().getTime() - mon.getTime() >= 0)) {
			throw BaseException.getException(UnionException.EXCE_ROLE_HAVE_RECEIVED);// 已经领取过
		}
		// 更改领取时间 扣除贡献
		packetAO().removeItemMustEnough(role, Packet.POS_ATTR, 8, userule, RoleItemLog.SYS_帮派_领取每周福利, "");
		roleUnionAO().updateBenefits(role, DateUtil.GetNowDateTime(), 0, roleUnion);

		// 领取福利
		UnionGetRewardVO getReward = new UnionGetRewardVO();
		getReward.setGift(roleUnion.getLastContribute() - userule);
		List<ProbItem> rstAry = new ArrayList<ProbItem>();
		String weeklyGod = cacheManager().getUnionConfigValue(UnionConfig.weeklyGod);
		String weeklySliver = cacheManager().getUnionConfigValue(UnionConfig.weeklySliver);
		String weeklySoul = cacheManager().getUnionConfigValue(UnionConfig.weeklySoul);
		String weeklyAdvanced = cacheManager().getUnionConfigValue(UnionConfig.weeklyAdvanced);
		int getGod = Integer.valueOf(weeklyGod.split("_")[0]) * union.getLevel()
				+ Integer.valueOf(weeklyGod.split("_")[1]);
		int getSliver = Integer.valueOf(weeklySliver.split("_")[0]) * role.getLevel()
				+ Integer.valueOf(weeklySliver.split("_")[1]) * union.getLevel()
				+ Integer.valueOf(weeklySliver.split("_")[2]);
		int getSoul = Integer.valueOf(weeklySoul.split("_")[0]) * union.getLevel()
				+ Integer.valueOf(weeklySoul.split("_")[1]);
		int getAdvanced = Integer.valueOf(weeklyAdvanced.split("_")[0]) * union.getLevel()
				+ Integer.valueOf(weeklyAdvanced.split("_")[1]);
		packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_金币, getGod, RoleItemLog.SYS_帮派_领取每周福利,
				String.valueOf(Packet.ATTR_金币));
		packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_银币, getSliver, RoleItemLog.SYS_帮派_领取每周福利,
				String.valueOf(Packet.ATTR_银币));
		packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_侠魂, getSoul, RoleItemLog.SYS_帮派_领取每周福利,
				String.valueOf(Packet.ATTR_侠魂));
		packetAO().addItem(role, Packet.POS_BAG, 4012, getAdvanced, RoleItemLog.SYS_帮派_领取每周福利, String.valueOf(4012));
		rstAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_金币, getGod));
		rstAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_银币, getSliver));
		rstAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_侠魂, getSoul));
		rstAry.add(new ProbItem(Packet.POS_BAG, 4012, getAdvanced));
		List<Object> plist = new ArrayList<Object>();
		getReward.setRewardList(rstAry);
		plist.add(role.getName());
		plist.add(getGod);
		plist.add(getSliver);
		plist.add(getSoul);
		plist.add(getAdvanced);
		unionDynamicAO().createDynamic(
				UnionDynamic.valueOf(roleUnion.getUnionId(), role.getId(), UnionDynamic.GET_WEEKLY_WELFARE, plist));
		return getReward;
	}

	/**
	 * 开启烧烤大会
	 */
	@Override
	public OpenActivityVO openBarbecue(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		// 判断权限是否足够
		if (roleUnion.getJopType() == RoleUnion.elders || roleUnion.getJopType() == RoleUnion.member) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		// 判断是否已开启
		if (union.getBarbecueTime() != null && DateUtil.isToday(union.getBarbecueTime())) {
			throw BaseException.getException(UnionException.EXCE_UNION_BARBECUE_IS_OPEN);
		}
		// 判断资金是否足够
		int costMoney = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.baCostUnionMoney));
		if (union.getCurrentUnionMoney() < costMoney) {
			throw BaseException.getException(UnionException.EXCE_UNION_MONEY_NOT_ENOUGH_OPEN_BAR);
		}
		// 判断帮派等级是否足够
		int limitLevel = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.baLimitLel));
		if (union.getLevel() < limitLevel) {
			throw BaseException.getException(UnionException.EXCE_UNION_LEVLE_IS_NOT_ENOUGH);
		}
		// 开启烧烤 扣除帮派资金
		union.setOpenBarRole(roleUnion.getRoleId());
		union.setBarbecueTime(DateUtil.GetNowDateTime());
		unionAO().reduceUnionMoney(union, costMoney);
		List<Object> plist = new ArrayList<Object>();
		plist.add(role.getName());
		plist.add(costMoney);
		int type = 0;
		if (roleUnion.getJopType() == RoleUnion.leader) {
			type = UnionDynamic.OPEN_BARB;
		} else {
			type = UnionDynamic.OPEN_BARB_BY_VICE;
		}
		unionDynamicAO().createDynamic(UnionDynamic.valueOf(roleUnion.getUnionId(), role.getId(), type, plist));
		OpenActivityVO unionSuccess = new OpenActivityVO();
		unionSuccess.setUnionCurrentMoney(union.getCurrentUnionMoney() - costMoney);
		int shaokaotime = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.shaokaotime));
		unionSuccess.setSurplusTime(shaokaotime * 60);
		unionLogAO().add(UnionLog.valueOf(union, UnionLog.SYS_帮派_开启烧烤大会, costMoney,
				union.getCurrentUnionMoney() + costMoney, role.getId() + ","
						+ DateUtil.getDateString(DateUtil.GetNowDateTime()) + "," + union.getTotalUnionMoney() + ""));
		return unionSuccess;
	}

	/**
	 * 领取烧烤大会福利
	 */
	public UnionGetRewardVO getBarbecue(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		// 判断是否开启union.getBarbecueTime()
		String endTime = DateUtil.addTime(DateUtil.getDateString(union.getBarbecueTime()), 2);
		if (DateUtil.GetNowDateTime().after(DateUtil.getDateTimeByString(endTime))) {
			throw BaseException.getException(UnionException.EXCE_UNION_BARBECUE_IS_CLOSE);
		}
		// 判断是否领取
		if (DateUtil.isToday(roleUnion.getBarbecue())) {
			throw BaseException.getException(UnionException.EXCE_ROLE_HAVE_RECEIVED);
		}
		int addPhy = getaddPhyByUnionLevel(union.getLevel());
		int addr = getaddResisValByUnionLevel(union.getLevel());
		List<ProbItem> rstAry = new ArrayList<ProbItem>();
		roleUnionAO().openBarbecue(roleUnion, DateUtil.GetNowDateTime(), 0);
		// 领取福利
		roleAO().updateResisVal(role, addr, false);// 耐力
		roleAO().updatePhysVal(role, addPhy, false);// 体力
		UnionGetRewardVO getReward = new UnionGetRewardVO();
		List<Object> plist = new ArrayList<Object>();
		plist.add(role.getName());
		plist.add(addPhy);
		plist.add(addr);
		unionDynamicAO().createDynamic(
				UnionDynamic.valueOf(roleUnion.getUnionId(), role.getId(), UnionDynamic.GET_BARB_WELARE, plist));
		getReward.setGift(roleUnion.getLastContribute());
		rstAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_体力, addPhy));
		rstAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_耐力, addr));
		getReward.setRewardList(rstAry);
		return getReward;
	}

	/**
	 * 进入福利页面
	 */
	@Override
	public EnterWVO enterWelfare(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		int isOpen = 1;
		int isBarbGet = 1;
		int isWeekGet = 1;
		long leftTime = 0;
		List<EnterWelfareVO> eList = new ArrayList<EnterWelfareVO>();
		Integer userule = Integer.parseInt(cacheManager().getUnionConfigValue(UnionConfig.userule));
		int costMoney = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.baCostUnionMoney));
		Date sunday = DateUtil.getCurrentSunday();
		Date mon = DateUtil.getCurrentMonDay();
		if (roleUnion.getWeeklyBenefits() != null && (sunday.getTime() - roleUnion.getWeeklyBenefits().getTime() >= 0)
				&& (roleUnion.getWeeklyBenefits().getTime() - mon.getTime() >= 0)) {
			isWeekGet = 0;
		}
		eList.add(new EnterWelfareVO(1, 0, isWeekGet, 0, userule));
		int shaokaotime = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.shaokaotime));
		if (DateUtil.isToday(union.getBarbecueTime())) {
			Date endTime = DateUtil.getInternalDateByMintues(union.getBarbecueTime(), shaokaotime);
			if (DateUtil.howLongBetween2Time(DateUtil.GetNowDateTime(), endTime, 4) > 0) {
				isOpen = 0;
				leftTime = (DateUtil.howLongBetween2Time(DateUtil.GetNowDateTime(), endTime, 4));
			} else {
				isOpen = 2;
			}
			if (DateUtil.isToday(roleUnion.getBarbecue())) {
				isBarbGet = 0;
			}
		}
		EnterWelfareVO enterWe = new EnterWelfareVO(2, isOpen, isBarbGet, leftTime, costMoney);
		int addPhy = getaddPhyByUnionLevel(union.getLevel());
		int addRes = getaddResisValByUnionLevel(union.getLevel());
		enterWe.setAddPhy(addPhy);
		enterWe.setAddRes(addRes);
		eList.add(enterWe);
		// 返回职务，两个福利是否领取，烧烤是否开启
		EnterWVO enterWelfareVO = new EnterWVO();
		enterWelfareVO.setJopType(roleUnion.getJopType());
		enterWelfareVO.setWelfList(eList);
		enterWelfareVO.setUnionCurrentMoney(union.getCurrentUnionMoney());
		enterWelfareVO.setLastContribute(roleUnion.getLastContribute());
		return enterWelfareVO;
	}

	/**
	 * 切磋
	 */
	@SuppressWarnings("unused")
	@Override
	public UnionSuccessVO discussion(String acc, int fightRoleId) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Role fightRole = roleAO().queryById(fightRoleId);
		RoleUnion fightroleUnion = roleUnionAO().queryRoleUnionById(fightRole.getId());
		if (fightRole == null) {
			throw BaseException.getException(UnionException.EXCE_FIGHTER_IS_NOT_EXIST);
		}
		// 判断次数是否足够
		if (roleUnion.getDekaronNum() <= 0) {
			throw BaseException.getException(UnionException.EXCE_ROLE_DEKARONNUM_IS_NULL);
		}
		if (fightroleUnion.getDefenseNum() <= 0) {
			throw BaseException.getException(UnionException.EXCE_ROLE_DEFENSENUM_IS_NULL);
		}

		FightCfg cfg = new FightCfg();
		cfg.setCaclAttrack(true);
		// init care
		FighterInfo srcInfo = fightAO().createFighterInfoByRole(role);
		FighterInfo tgtInfo = fightAO().createFighterInfoByRole(fightRole);
		if (cfg.isCaclAttrack()) {
			srcInfo.setAttrack(role.getAttack());
			tgtInfo.setAttrack(fightRole.getAttack());
		}
		// start battle
		FightMain main = new FightMain(srcInfo, tgtInfo, cfg);
		FightResult result = main.fight();
		Map<String, Object> rst = result.getMsg();
		UnionSuccessVO unionSuccess = new UnionSuccessVO();
		if (result.getWin() == 1) {// 当前玩家获胜
			unionSuccess.setSuccess(0);
		} else {
			unionSuccess.setSuccess(1);
		}
		return unionSuccess;
	}

	/**
	 * 退出帮派
	 */
	@Override
	public UnionSuccessVO exitUnion(String acc, int uid) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		if (roleUnion.getJopType() == 3) {
			// 查找帮派继承人
			RoleUnion heir = roleUnionAO().queryHeir(roleUnion.getUnionId());
			if (heir.getRoleId() == 0) {
				// 解散帮派
				unionAO().deleteUnion(union);
				// 删除该帮派的所有申请 并发送拒绝邮件
				List<UnionApply> applyList = unionApplyAO().queryApplyList(roleUnion.getUnionId());
				for (UnionApply unionApply : applyList) {
					unionApplyAO().deleteRoleApply(unionApply.getRoleId(), unionApply.getUnionId());
					mailAO().unionApplyRefuse(unionApply.getRoleId(), union.getName());
				}
			} else {
				// 继承人成为帮主
				roleUnionAO().updateRoleJop(heir, RoleUnion.leader);
				union.setBossId(heir.getRoleId());
				unionAO().updateBossInfo(union);
			}
		}
		List<Object> plist = new ArrayList<Object>();
		plist.add(role.getName());
		unionDynamicAO().createDynamic(
				UnionDynamic.valueOf(roleUnion.getUnionId(), role.getId(), UnionDynamic.EXIT_UNION_TYPE, plist));
		roleUnionAO().clearRoleUnion(roleUnion, union.getId());
		// 退出帮派时清空帮派副本信息
		roleUnionFBAO().clearRoleUnionFB(role.getId());
		// 退出帮派时清空个人副本状态
		RoleUnionFBState roleUnionFBState = roleUnionFBStateAO().queryRoleUnionFBStateByRoleIdUId(role.getId(),
				union.getId());
		if (roleUnionFBState != null) {
			roleUnionFBStateAO().clearRoleUnionFBState(role.getId(), union.getId());
		}
		roleAO().updateRoleFaction(role, "");
		List<RoleUnion> roleUnionList = roleUnionAO().queryAllMember(union.getId());
		StringBuffer ids = new StringBuffer("");
		for (RoleUnion roleU : roleUnionList) {
			// 更新战斗力
			ids.append(String.valueOf(roleU.getRoleId()) + ",");
			ids.append("0");
		}
		if (roleUnionList.size() > 0) {
			unionAO().updateUnionAtt(ids, union.getId());
		}
		UnionSuccessVO unionSuccess = new UnionSuccessVO();
		unionSuccess.setSuccess(0);
		return unionSuccess;
	}

	/**
	 * 展示申请列表
	 */
	@Override
	public ShowAllMemberVO showApplyList(String acc, int unionId) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		// 判断权限
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		if (roleUnion.getJopType() == 0) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		// 查询返回列表
		List<MemberVO> applyList = unionApplyAO().createRetrunList(unionId, 0);
		ShowAllMemberVO unionData = new ShowAllMemberVO();
		unionData.setApplyList(applyList);
		unionData.setNowTime(DateUtil.GetNowDateTime());
		unionData.setJopType(roleUnion.getJopType());
		return unionData;
	}

	/**
	 * 任命/取消任命
	 */
	@Override
	public UnionSuccessVO setposition(String acc, int appRoleId, int jopType) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		RoleUnion approleUnion = roleUnionAO().queryRoleUnionById(appRoleId);
		Role appRole = roleAO().queryExistId(appRoleId);
		// 判断数据是否存在
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_EXIST);
		}
		if (approleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_EXIST);
		}
		if (roleUnion.getUnionId() != approleUnion.getUnionId()) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		// 判断权限
		if (roleUnion.getJopType() != RoleUnion.leader) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		if (role.getId() == appRoleId) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		// 查询当前职务
		if (approleUnion.getJopType() == jopType) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JOPTYPE_IS_SAME);
		}
		int jopNum = roleUnionAO().queryJopNumByJopType(roleUnion.getUnionId(), jopType);

		int bossnum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.bossnum));
		int managernum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.managernum));
		if (jopType == RoleUnion.vice && jopNum == bossnum) {
			throw BaseException.getException(UnionException.EXCE_UNION_VICE_IS_EXIST);
		}
		if (jopType == RoleUnion.elders && jopNum == managernum) {
			throw BaseException.getException(UnionException.EXCE_UNION_ELDER_IS_FULL);
		}
		List<Object> plist = new ArrayList<Object>();
		plist.add(role.getName());
		plist.add(appRole.getName());
		int type = 0;
		if (jopType == RoleUnion.vice) {
			// 副帮主
			type = UnionDynamic.APPOINTMENT_TYPE;
		} else if (jopType == RoleUnion.elders) {
			// 长老
			type = UnionDynamic.APPOINTMENT_ELDER_TYPE;
		} else {
			type = UnionDynamic.CANCEL_APPOINTMENT_TYPE;
		}
		unionDynamicAO().createDynamic(UnionDynamic.valueOf(roleUnion.getUnionId(), appRole.getId(), type, plist));
		roleUnionAO().updateRoleJop(approleUnion, jopType);
		UnionSuccessVO unionSuccess = new UnionSuccessVO();
		unionSuccess.setSuccess(0);
		return unionSuccess;
	}

	/**
	 * 踢出帮派
	 */
	@Override
	public UnionSuccessVO kcikRole(String acc, int appRoleId) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		Role appRole = roleAO().queryById(appRoleId);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		RoleUnion approleUnion = roleUnionAO().queryRoleUnionById(appRoleId);
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		// 判断处理人物是否存在
		if (appRole == null) {
			throw RoleException.getException(RoleException.EXCE_ACCOUNT_NOT_EXIST);
		}
		// 判断权限是否足够
		if (roleUnion.getJopType() == RoleUnion.member || roleUnion.getJopType() == RoleUnion.elders) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		// 帮主不能被踢出
		if (approleUnion != null && approleUnion.getJopType() == RoleUnion.leader) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);
		}
		// 判断是否在帮派中
		if (approleUnion.getUnionId() != roleUnion.getUnionId() || approleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		mailAO().unionPleaseLeave(approleUnion.getRoleId(), role);
		roleUnionAO().clearRoleUnion(approleUnion, union.getId());
		// 退出帮派时清空帮派副本信息
		roleUnionFBAO().clearRoleUnionFB(role.getId());
		// 退出帮派时清空个人副本状态
		RoleUnionFBState roleUnionFBState = roleUnionFBStateAO().queryRoleUnionFBStateByRoleIdUId(role.getId(),
				union.getId());
		if (roleUnionFBState != null) {
			roleUnionFBStateAO().clearRoleUnionFBState(role.getId(), union.getId());
		}
		roleAO().updateRoleFaction(appRole, "");
		List<RoleUnion> roleUnionList = roleUnionAO().queryAllMember(union.getId());
		StringBuffer ids = new StringBuffer("");
		for (RoleUnion roleU : roleUnionList) {
			// 更新战斗力
			ids.append(String.valueOf(roleU.getRoleId()) + ",");
			ids.append("0");
		}
		if (roleUnionList.size() > 0) {
			unionAO().updateUnionAtt(ids, union.getId());
		}
		UnionSuccessVO unionSuccess = new UnionSuccessVO();
		unionSuccess.setSuccess(0);
		return unionSuccess;
	}

	/**
	 * 创建动态
	 * 
	 * @param msg
	 */
	public void createDynamic(UnionDynamic unionDynamic) {
		unionDynamicAO().createDynamic(unionDynamic);
	}

	/**
	 * 查看帮派动态
	 * 
	 * @throws BizException
	 */
	public ShowDynamicVO showDynamicList(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		ShowDynamicVO unionDynamicVO = new ShowDynamicVO();
		int unionDynamicNumLimit = Integer
				.valueOf(cacheManager().getUnionConfigValue(UnionConfig.unionDynamicNumLimit));
		List<DynamicVO> dynamicList = unionDynamicAO().queryAllConDynamicList(roleUnion.getUnionId(),
				unionDynamicNumLimit);
		unionDynamicVO.setDynamicList(dynamicList);
		unionDynamicVO.setJopType(roleUnion.getJopType());
		return unionDynamicVO;
	}

	/**
	 * 展示排名列表
	 */
	@Override
	public UnionDataVO showUnionRank(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		List<UnionDataVO> unionList = unionAO().showUnionRank();
		if (unionList.isEmpty()) {
			throw BaseException.getException(UnionException.EXCE_UNION_IS_NOT_EXIST);
		}
		UnionDataVO unionData = new UnionDataVO();
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			unionData.setIsInUnion(1);
		} else {
			Union union = unionAO().queryUinonById(roleUnion.getUnionId());
			unionData.setMyrank(union.getRank());
			unionData.setMysumAttack(union.getSumAttack());
			unionData.setIsInUnion(0);
		}

		unionData.setUnionList(unionList);
		if (roleUnion == null) {
			unionData.setJopType(0);
		} else {
			unionData.setJopType(roleUnion.getJopType());
		}

		return unionData;
	}

	public Integer getaddPhyByUnionLevel(int level) {
		int totalAddphy = 0;
		int initPhysVal = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.initPhysVal));
		int addPhysVal = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.addPhysVal));
		totalAddphy = initPhysVal + addPhysVal * level;
		return totalAddphy;
	}

	public Integer getaddResisValByUnionLevel(int level) {
		int totalResisVal = 0;
		int initResisVal = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.initResisVal));
		int addResisVal = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.addResisVal));
		totalResisVal = initResisVal + addResisVal * level;
		return totalResisVal;
	}

	/**
	 * 修改帮派帮主
	 */
	@Override
	public CoverVO updateUnionLeader(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		CoverVO unionVO = new CoverVO();
		// 判断自荐时间是否截止
		if (DateUtil.isInDays(union.getCoverTime(), 2)) {
			long surplusTime = DateUtil.howLongBetween2Time(DateUtil.getStringByDate(union.getCoverTime()),
					DateUtil.getStringByDate(DateUtil.GetNowDateTime()), 4);
			unionVO.setTime(surplusTime);
		}
		unionVO.setLeaderId(union.getBossId());
		return unionVO;
	}

	// 定时修改帮信息
	@Override
	public void updateUnionInfo() {
		List<Union> unionList = unionAO().queryAllUnion();
		for (Union union : unionList) {
			List<RoleUnion> roleUnionList = roleUnionAO().queryAllMember(union.getId());
			StringBuffer ids = new StringBuffer("");
			for (RoleUnion roleUnion : roleUnionList) {
				// 更新排名
				roleUnionAO().updateRoleUnionAtt(roleUnion);
				ids.append(String.valueOf(roleUnion.getRoleId()) + ",");

			}
			ids.append("0");
			// 帮派战斗力
			unionAO().updateUnionAtt(ids, union.getId());
			unionLogAO().add(UnionLog.valueOf(union, UnionLog.SYS_帮派_定时修改帮派信息, union.getId(), 0, ""));
		}
		// 修改帮派排名
		List<Union> newList = unionAO().queryAllUnion();
		Collections.sort(newList, new Comparator<Union>() {
			@Override
			public int compare(Union b1, Union b2) {
				return b2.getSumAttack() - b1.getSumAttack();
			}
		});
		for (int i = 0; i < newList.size(); i++) {
			Union union = newList.get(i);
			// 修改帮派排行
			unionAO().updateUnionRank(union, i + 1);
			unionLogAO().add(UnionLog.valueOf(union, UnionLog.SYS_帮派_定时修改帮派信息, union.getId(), 0, i + 1 + ""));
		}

	}

	/**
	 * 查询所有成员
	 */
	@Override
	public ShowAllMemberVO showAllMember(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		List<MemberVO> memberList = unionApplyAO().createMemberList(roleUnion.getUnionId(), role.getId());
		ShowAllMemberVO unionData = new ShowAllMemberVO();
		unionData.setRoleUnionList(memberList);
		unionData.setNowTime(DateUtil.GetNowDateTime());
		unionData.setJopType(roleUnion.getJopType());
		return unionData;
	}

	/**
	 * 进入帮派大殿
	 */
	@Override
	public EnterMainBuildingVO EnterMainBuilding(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		List<Object> dList = unionDynamicAO().queryConDynamicList(union.getId());
		List<RoleUnion> ruList = roleUnionAO().queryAllMember(union.getId());
		int contributed = 0;
		for (RoleUnion roleU : ruList) {
			if (DateUtil.isToday(roleU.getLastContributionTime()) && roleU.getConType() != 0) {
				contributed++;
			}
		}
		EnterMainBuildingVO enterM = new EnterMainBuildingVO();
		enterM.setCurrentUnionMoney(union.getCurrentUnionMoney());
		enterM.setUnionLevel(union.getLevel());
		enterM.setDynamciList(dList);
		if (DateUtil.isToday(roleUnion.getLastContributionTime()) && roleUnion.getConType() == 0) {
			enterM.setIsCon(0);
		} else if (DateUtil.isToday(roleUnion.getLastContributionTime()) && roleUnion.getConType() != 0) {
			enterM.setIsCon(1);
		} else {
			enterM.setIsCon(0);
		}
		enterM.setContributed(contributed);
		enterM.setViplevel(role.getVip());
		enterM.setRoleMaxNum(union.getRoleMaxNum());
		return enterM;
	}

	/**
	 * 进入帮派作坊
	 */
	@Override
	public EnterWorkShopVO enterWorkShop(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		// 返回前端数据
		EnterWorkShopVO enterW = new EnterWorkShopVO();
		enterW.setCurrentUnionMoney(union.getCurrentUnionMoney());
		if (DateUtil.isToday(roleUnion.getStarworktime())) {
			enterW.setFreeCount(0);
		} else {
			enterW.setFreeCount(1);
		}
		enterW.setWorkShopLevel(union.getWorkShopLevel());
		enterW.setGold(role.getGold());
		enterW.setWorkType(roleUnion.getWorktype());
		// 是否加班
		enterW.setOvertimeflag(roleUnion.getOvertimeflag());
		int costNum = 0;
		switch (roleUnion.getBuynum()) {
		case 0:
			costNum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.onebuygoldnum));
			break;
		case 1:
			costNum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.twobuygoldnum));
			break;
		case 2:
			costNum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.threebuygoldnum));
			break;
		}
		enterW.setExtGoldNum(costNum);
		int allNum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.buyprodectnum));
		enterW.setExtNum(allNum - roleUnion.getBuynum());
		enterW.setIsWork(1);
		enterW.setSurplusTime(0);

		List<WorkShopVO> wslist = new ArrayList<WorkShopVO>();
		Map<Integer, UnionWorkShop> workShop = cacheManager().getValues(UnionWorkShop.class);// 工坊配置
		for (Entry<Integer, UnionWorkShop> entry : workShop.entrySet()) {
			int isopen = 1;
			UnionWorkShop unionshop = entry.getValue();
			if (union.getWorkShopLevel() >= unionshop.getOpenLevel()) {
				isopen = 0;
			}
			// 普通侠魂生产数量公式=玩家50等级*200+作坊2等级*2000，
			// 加班侠魂生产数量公式=玩家等级*400+作坊2等级*4000，
			int norNum = role.getLevel() * unionshop.getNorWorkOne()
					+ union.getWorkShopLevel() * unionshop.getNorWorkTwo();
			int extNum = role.getLevel() * unionshop.getExtWorkOne()
					+ union.getWorkShopLevel() * unionshop.getExtWorkTwo();
			int leftTime = 0;
			wslist.add(WorkShopVO.valueOf(unionshop, isopen, norNum, extNum, leftTime));
		}
		enterW.setTypeList(wslist);
		int workShopLastTime = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.workShopLastTime));
		List<ProbItem> rstAry = new ArrayList<ProbItem>();
		// 判断奖励是否领取
		if (roleUnion.getIsOver() == 1) {
			String overTime = DateUtil.addTime(DateUtil.getDateString(roleUnion.getStarworktime()), workShopLastTime);
			// 时间到了，未领奖
			if (DateUtil.GetNowDateTime().after(DateUtil.getDateTimeByString(overTime))
					&& roleUnion.getShopWorkrdReward() == 1) {
				// 已结束并且未发奖 0已领取1未领取
				for (WorkShopVO workShopVO : wslist) {
					if (workShopVO.getId() == roleUnion.getWorktype()) {
						int num = 0;
						if (roleUnion.getOvertimeflag() == 0) {
							num = workShopVO.getFastNum();
						} else {
							num = workShopVO.getNormalNum();
						}
						// 发奖
						packetAO().addItem(role, Packet.POS_ATTR, workShopVO.getItemId(), num,
								RoleItemLog.SYS_帮派_工坊获得生产奖励, String.valueOf(workShopVO.getItemId()));
						rstAry.add(new ProbItem(workShopVO.getItemType(), workShopVO.getItemId(), num));
						// 修改状态
						roleUnion.setIsOver(0);
						roleUnion.setShopWorkrdReward(0);
						roleUnionAO().updateRoleProduct(roleUnion);
					}
				}
			} else {
				// 时间未到
				enterW.setIsWork(0);
				enterW.setSurplusTime(
						DateUtil.howLongBetween2Time(DateUtil.GetNowDateTime(), DateUtil.getDayTime(overTime), 4));
			}
		}
		enterW.setRewardList(rstAry);
		return enterW;
	}

	/**
	 * 领取奖励
	 * 
	 * @param acc
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@Override
	public UnionGetRewardVO getReward(String acc, int id) throws BizException {
		UnionGetRewardVO getReward = new UnionGetRewardVO();
		if (id == 1) {
			getReward = getBenefits(acc);
		} else if (id == 2) {
			getReward = getBarbecue(acc);
		}
		return getReward;
	}

	/**
	 * 开启帮派活动
	 */
	@Override
	public OpenActivityVO openActivities(String acc, int id) throws BizException {
		OpenActivityVO openA = new OpenActivityVO();
		if (id == 2) {
			openA = openBarbecue(acc);
		}
		return openA;
	}

	/**
	 * 检测活动时间
	 */
	@Override
	public CheckTimeVO checkTime(String acc, int id) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		CheckTimeVO checkTime = new CheckTimeVO();
		Date bdate = DateUtil.getInternalDateByMintues(union.getBarbecueTime(), 60);
		if (DateUtil.GetNowDateTime().after(bdate)) {
			checkTime.setIsOver(0);
		} else {
			checkTime.setIsOver(1);
			checkTime.setLeftTime(DateUtil.howLongBetween2Time(DateUtil.getDateString(union.getBarbecueTime()),
					DateUtil.getDateString(bdate), 2));
		}
		return checkTime;
	}

	/**
	 * 商店兑换商品
	 */
	@Override
	public ExchangeVO exchangeGoods(String acc, int id, int type, int count) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		List<ShopInfo> slist = new ArrayList<ShopInfo>();
		List<UnionShop2> plist = new ArrayList<UnionShop2>();
		List<PropInfo> prolist = new ArrayList<PropInfo>();
		List<UnionRoleShopInfo> URSIList = new ArrayList<UnionRoleShopInfo>();
		List<ProbItem> rstAry = new ArrayList<ProbItem>();
		plist = getPropGoodsInfo(union);
		ExchangeVO exchange = new ExchangeVO();
		if (type == 1) {
			UnionShop unionShop = unionShopAO().queryShopInfoByUnionId(roleUnion.getUnionId());
			slist = JSONUtil.fromJsonList(unionShop.getGoodsInfo(), ShopInfo.class);
			ShopInfo shopIfno = null;
			int index = 0;
			for (int i = 0; i < slist.size(); i++) {
				if (slist.get(i).getId() == id) {
					shopIfno = slist.get(i);
					index = i;
				}
			}
			if (shopIfno == null) {
				throw BaseException.getException(UnionException.EXCE_GOODS_NOT_EXIST);
			}
			// 判断剩余数量是否足够
			if (shopIfno.getExchange() <= 0) {
				throw BaseException.getException(UnionException.EXCE_SHOP_GOODS_IS_NOT_ENOUGH);
			}
			// 判断是否可兑换多个
			if (count > shopIfno.getExchange()) {
				throw BaseException.getException(UnionException.EXCE_TODAY_BUY_NOT_ENOUGH);
			}
			// 判断当前等级是否可兑换
			if (union.getShopLevel() < shopIfno.getOpenLevel()) {
				throw BaseException.getException(UnionException.EXCE_SHOP_LEVEL_NOT_ENOUGH);
			}
			// 判断贡献值是否足够
			if (roleUnion.getLastContribute() < shopIfno.getCost() * count) {
				throw BaseException.getException(UnionException.EXCE_ROLE_CONTRBUTE_NOT_ENOUGH);
			}
			// 判断今天是否兑换过
			List<Integer> idsList = JSONUtil.fromJsonList(roleUnion.getShopIds(), Integer.class);
			if (DateUtil.isToday(roleUnion.getExchangeTime()) && !idsList.isEmpty()) {
				if (idsList.contains(id)) {
					throw BaseException.getException(UnionException.EXCE_TODAY_HAS_BUY);
				}
			}
			// 判断背包是否已满
			List<Integer> bagAry = new ArrayList<Integer>();
			if (shopIfno.getType() != 8) {
				Item itemData = cacheManager().getExistValueByKey(Item.class, shopIfno.getItemId());
				bagAry.add(itemData.getBag());
			} else {
				bagAry.add(8);
			}
			List<PacketExtend> bageData = new ArrayList<PacketExtend>();
			bageData = packetAO().checkBag(role, bagAry);
			if (bageData.size() > 0) {
				exchange.setCheckBagList(bageData);
				return exchange;
			}
			shopIfno.setExchange(shopIfno.getExchange() - 1);
			slist.set(index, shopIfno);
			unionShopAO().updateShopInfo(UnionShop.valueOfShopInfo(union.getId(), slist, unionShop.getRefreshTime()));

			// 扣除贡献
			packetAO().removeItemMustEnough(role, Packet.POS_ATTR, shopIfno.getMoney(), shopIfno.getCost() * count,
					RoleItemLog.SYS_帮派_商店兑换, "");
			exchange.setLastContribute(roleUnion.getLastContribute() - shopIfno.getCost() * count);
			// 修改领奖时间和领奖物品id
			List<Integer> ids = new ArrayList<Integer>();
			if (roleUnion.getShopIds() != "") {
				ids = JSONUtil.fromJsonList(roleUnion.getShopIds(), Integer.class);
			}
			ids.add(id);
			for (int i = 0; i < slist.size(); i++) {
				ShopInfo shopIn = slist.get(i);
				RoleItem ritemData = packetAO().queryByAccItemId(role.getId(), shopIn.getItemId(), shopIn.getType());
				int cou = 0;
				if (ritemData != null) {
					cou = ritemData.getItemCnt();
				}
				shopIn.setHad(cou);
				if (shopIn.getId() == id || ids.contains(shopIn.getId())) {
					shopIn.setHasBuy(0);
				} else {
					shopIn.setHasBuy(1);
				}

				slist.set(i, shopIn);
			}
			// 修改商品ids
			roleUnion.setExchangeTime(DateUtil.GetNowDateTime());
			roleUnionAO().updateExchangeShopInfo(roleUnion, ids);
			// 发奖
			rstAry.add(new ProbItem(shopIfno.getType(), shopIfno.getItemId(), shopIfno.getNum()));
			packetAO().addItem(role, shopIfno.getType(), shopIfno.getItemId(), shopIfno.getNum() * count,
					RoleItemLog.SYS_帮派_商店兑换, "");
			List<Object> dlist = new ArrayList<Object>();
			dlist.add(role.getName());
			Item itemData = cacheManager().getExistValueByKey(Item.class, shopIfno.getItemId());
			dlist.add(itemData.getName());
			unionDynamicAO().createDynamic(UnionDynamic.valueOf(roleUnion.getUnionId(), role.getId(),
					UnionDynamic.UNION_SHOP_EXCHANGE, dlist));
		} else if (type == 2) {
			UnionRoleShop unionRoleShop = unionRoleShopAO().queryShopInfoByRoleId(role.getId());
			int add = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.roleUnionShopTime));
			// 刷新时间，存起来
			Date reTime = DateUtil.addTime(DateUtil.getNowDayTime(), add);
			// 上次刷新时间
			Date ryTime = DateUtil.getDateAfter(reTime, -1);
			Date now = DateUtil.GetNowDateTime();
			List<UnionShop2> propList = new ArrayList<UnionShop2>();
			if (unionRoleShop == null || (now.after(reTime) && reTime.after(unionRoleShop.getRefreshTime()))
					|| (unionRoleShop.getRefreshTime().before(ryTime))) {
				// 刷新
				List<PropInfo> pList = new ArrayList<PropInfo>();
				propList = getPropGoodsInfo(union);
				for (UnionShop2 US2 : propList) {
					URSIList.add(UnionRoleShopInfo.valueOf(US2.getId(), US2.getExchange()));
				}
				unionRoleShopAO().updateUnionRoleShopInfo(UnionRoleShop.valueOf(role.getId(), URSIList, now));
				for (UnionShop2 unionS : propList) {

					RoleItem ritemData = packetAO().queryByAccItemId(role.getId(), unionS.getItemId(),
							unionS.getType());
					int cou = 0;
					if (ritemData != null) {
						cou = ritemData.getItemCnt();
					}
					pList.add(PropInfo.valueOf(unionS, cou, unionS.getExchange()));
				}
				exchange.setShopListB(pList);
				exchange.setIsrefresh(1);
				return exchange;
			}

			URSIList = JSONUtil.fromJsonList(unionRoleShop.getGoodsInfo(), UnionRoleShopInfo.class);
			UnionShop2 propIfno = null;
			UnionRoleShopInfo URSI = null;
			int index = 0;
			for (int i = 0; i < URSIList.size(); i++) {
				if (URSIList.get(i).getId() == id) {
					URSI = URSIList.get(i);
					index = i;
					break;
				}
			}
			for (UnionShop2 pro : plist) {
				if (pro.getId() == id) {
					propIfno = pro;
					break;
				}
			}

			// 判断是否可兑换多个
			if (count > URSI.getExchange()) {
				throw BaseException.getException(UnionException.EXCE_TODAY_BUY_NOT_ENOUGH);
			}
			// 判断剩余数量是否足够
			if (URSI.getExchange() <= 0) {
				throw BaseException.getException(UnionException.EXCE_SHOP_GOODS_IS_NOT_ENOUGH);
			}
			// 判断当前等级是否可兑换
			if (union.getShopLevel() < propIfno.getOpenLevel()) {
				throw BaseException.getException(UnionException.EXCE_SHOP_LEVEL_NOT_ENOUGH);
			}
			// 判断贡献值是否足够
			if (roleUnion.getLastContribute() < propIfno.getCost() * count) {
				throw BaseException.getException(UnionException.EXCE_ROLE_CONTRBUTE_NOT_ENOUGH);
			}
			URSI.setExchange(URSI.getExchange() - count);
			URSIList.set(index, URSI);
			unionRoleShopAO().updateUnionRoleShopInfo(
					UnionRoleShop.valueOfPropInfo(role.getId(), URSIList, unionRoleShop.getRefreshTime()));
			// 扣除贡献
			packetAO().removeItemMustEnough(role, Packet.POS_ATTR, propIfno.getMoney(), propIfno.getCost() * count,
					RoleItemLog.SYS_帮派_商店兑换, "");
			exchange.setLastContribute(roleUnion.getLastContribute() - propIfno.getCost() * count);
			packetAO().addItem(role, propIfno.getType(), propIfno.getItemId(), propIfno.getNum() * count,
					RoleItemLog.SYS_帮派_商店兑换, "");
			RoleItem ritemData = packetAO().queryByAccItemId(role.getId(), propIfno.getItemId(), propIfno.getType());
			int cou = 0;
			if (ritemData != null) {
				cou = ritemData.getItemCnt();
			}
			exchange.setLeftNum(URSI.getExchange());
			exchange.setHadNum(cou);
			exchange.setLastContribute(roleUnion.getLastContribute() - propIfno.getCost() * count);
		}
		// 扣除贡献
		exchange.setShopListA(slist);
		exchange.setShopListB(prolist);
		return exchange;
	}

	/**
	 * 校验时间
	 */
	@Override
	public CheckWorkTimeVO checkWorkShopTime(String acc, int id) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		CheckWorkTimeVO checkTime = new CheckWorkTimeVO();
		int workShopLastTime = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.workShopLastTime));
		Date bdate = DateUtil.addTime(roleUnion.getStarworktime(), workShopLastTime);
		int sumxh = 0;
		int sumyb = 0;
		switch (roleUnion.getOvertimeflag()) {
		case 0:
			if (roleUnion.getWorktype() == 1) {
				// 加班生产侠魂
				int userlevelnum = role.getLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectxhstylebyfast).split("_")[0]);
				int unionlevelnum = union.getWorkShopLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectxhstylebyfast).split("_")[1]);
				sumxh = userlevelnum + unionlevelnum;
			} else {
				// 加班生产银币
				int userlevelnum = role.getLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectybstylebyfast).split("_")[0]);
				int unionlevelnum = union.getWorkShopLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectybstylebyfast).split("_")[1]);
				sumyb = userlevelnum + unionlevelnum;
			}
			break;
		case 1:
			if (roleUnion.getWorktype() == 1) {
				// 普通生产侠魂
				int userlevelnum = role.getLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectxhstylebycommon).split("_")[0]);
				int unionlevelnum = union.getWorkShopLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectxhstylebycommon).split("_")[1]);
				sumxh = userlevelnum + unionlevelnum;
			} else {
				int userlevelnum = role.getLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectybstylebycommon).split("_")[0]);
				int unionlevelnum = union.getWorkShopLevel() * Integer
						.valueOf(cacheManager().getUnionConfigValue(UnionConfig.prodectybstylebycommon).split("_")[1]);
				sumyb = userlevelnum + unionlevelnum;
			}
			break;
		}
		Date sdate = DateUtil.getInternalDateBySecond(DateUtil.GetNowDateTime(), 2);
		if (sdate.after(bdate)) {
			// 结束
			checkTime.setIsOver(0);
			roleUnion.setIsOver(0);
			roleUnion.setShopWorkrdReward(0);
			roleUnionAO().updateRoleProduct(roleUnion);
			List<ProbItem> rstAry = new ArrayList<ProbItem>();
			if (roleUnion.getWorktype() == 1) {
				rstAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_侠魂, sumxh));
				packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_侠魂, sumxh, RoleItemLog.SYS_帮派_工坊获得生产奖励,
						String.valueOf(Packet.ATTR_侠魂));
			} else {
				rstAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_银币, sumyb));
				packetAO().addItem(role, Packet.POS_ATTR, Packet.ATTR_银币, sumyb, RoleItemLog.SYS_帮派_工坊获得生产奖励,
						String.valueOf(Packet.ATTR_银币));
			}
			checkTime.setRewardList(rstAry);
		} else {
			// 未结束
			checkTime.setIsOver(1);
			checkTime.setLeftTime(DateUtil.howLongBetween2Time(DateUtil.getDateString(roleUnion.getStarworktime()),
					DateUtil.getDateString(bdate), 2));
		}
		return checkTime;
	}

	/**
	 * 检测是否在帮派中
	 */
	@Override
	public UnionSuccessVO checkInUnion(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		UnionSuccessVO succ = new UnionSuccessVO();
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			succ.setSuccess(1);
		} else {
			succ.setSuccess(0);
		}
		return succ;
	}

	/**
	 * 检测帮派商店时间
	 */
	@Override
	public CheckShopTimeVO checkUnionShopTime(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		long leftTime = 0;
		int addTime = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.unionShopTime));
		// 刷新时间，存起来
		Date rTime = DateUtil.addTime(DateUtil.getNowDayTime(), addTime);
		// 上次刷新时间
		Date yTime = DateUtil.getDateAfter(rTime, -1);
		// rTime = DateUtil.GetNowDateTime();
		Date now = DateUtil.GetNowDateTime();
		if (DateUtil.GetNowDateTime().before(rTime) || DateUtil.compare_dateIsEqual(rTime, now)) {
			leftTime = DateUtil.howLongBetween2Time(DateUtil.GetNowDateTime(), rTime, 4);
		} else {
			Date edate = DateUtil.getInternalDateByDay(rTime, 1);
			leftTime = DateUtil.howLongBetween2Time(DateUtil.GetNowDateTime(), edate, 4);
		}
		List<UnionShop1> gemList = new ArrayList<UnionShop1>();
		List<ShopInfo> gList = new ArrayList<ShopInfo>();
		CheckShopTimeVO checkTime = new CheckShopTimeVO();
		UnionShop unionShop = unionShopAO().queryShopInfoByUnionId(union.getId());
		List<Integer> idsList = new ArrayList<Integer>();
		// 剩余时间小于两秒刷新，并且只能刷新一次，
		if ((now.after(rTime) && rTime.after(unionShop.getRefreshTime()))
				|| (unionShop.getRefreshTime().before(yTime))) {
			// 刷新列表
			gemList = getGoodSInfo(union);
			unionShopAO().updateShopInfo(UnionShop.valueOf(union.getId(), gemList, now));
			for (UnionShop1 unionShop1 : gemList) {
				RoleItem ritemData = packetAO().queryByAccItemId(role.getId(), unionShop1.getItemId(),
						unionShop1.getType());
				int count = 0;
				if (ritemData != null) {
					count = ritemData.getItemCnt();
				}
				gList.add(ShopInfo.valeOf(unionShop1, 1, count));
			}
		} else {
			List<UnionShop1> gmList = JSONUtil.fromJsonList(unionShop.getGoodsInfo(), UnionShop1.class);
			if (roleUnion.getShopIds() != null) {
				idsList = JSONUtil.fromJsonList(roleUnion.getShopIds(), Integer.class);
			}
			for (UnionShop1 unionShop1 : gmList) {
				RoleItem ritemData = packetAO().queryByAccItemId(role.getId(), unionShop1.getItemId(),
						unionShop1.getType());
				int count = 0;
				if (ritemData != null) {
					count = ritemData.getItemCnt();
				}
				if (idsList.contains(unionShop1.getId())) {
					gList.add(ShopInfo.valeOf(unionShop1, 0, count));
				} else {
					gList.add(ShopInfo.valeOf(unionShop1, 1, count));
				}
			}
		}
		checkTime.setLeftTime(leftTime);
		checkTime.setShopListA(gList);
		return checkTime;
	}

	/**
	 * 进入帮派副本
	 */
	@Override
	public EnterUnionFBVO enterUnionCopy(String acc, int type) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		EnterUnionFBVO enterUnionFB = new EnterUnionFBVO();
		List<UnionFB> unionFBList = unionFBAO().queryUnionFBById(roleUnion.getUnionId());
		// List<UnionFBData> FBlist = cacheManager().getUnionFBList();

		// if(unionFBList.isEmpty()){
		// for(UnionFBData unionFB:FBlist){
		// if(unionFB.getType() == 1){
		// int totalHp = 0;
		// Npc npcData = cacheManager().getExistValueByKey(Npc.class, unionFB.getNpc());
		// List<Integer> cardIds = new ArrayList<Integer>();
		// cardIds.add(npcData.getNpc1());
		// cardIds.add(npcData.getNpc2());
		// cardIds.add(npcData.getNpc3());
		// cardIds.add(npcData.getNpc4());
		// cardIds.add(npcData.getNpc5());
		// cardIds.add(npcData.getNpc6());
		// for(int i=0;i<cardIds.size();i++){
		// if(cardIds.get(i)!=0){
		// Card cardObj = cacheManager().getExistValueByKey(Card.class, cardIds.get(i));
		// totalHp = totalHp+cardObj.getBase().get(0);
		// }
		// }
		// unionFBAO().createUnionFB(UnionFB.ValueOf(unionFB, totalHp, union.getId()));
		// unionFBList.add(UnionFB.ValueOf(unionFB, totalHp, union.getId()));
		// }
		//
		// }
		// }
		List<RoleUnionFB> roleUnionFBList = roleUnionFBAO().queryRoleUnionFBByRoleId(role.getId());

		RoleUnionFBState unionRoleFBState = roleUnionFBStateAO().queryRoleUnionFBStateByRoleIdUId(role.getId(),
				union.getId());
		int fubenfreenum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.fubenfreenum));
		if (unionRoleFBState == null) {
			roleUnionFBStateAO().createRoleUnionFBState(RoleUnionFBState.ValueOf(roleUnion, fubenfreenum));
			enterUnionFB.setLeftCount(fubenfreenum);
		} else {
			if (DateUtil.isToday(unionRoleFBState.getRefreshTime())) {
				enterUnionFB.setLeftCount(unionRoleFBState.getLeftAttackNum());
			} else {
				enterUnionFB.setLeftCount(fubenfreenum);
				roleUnionFBStateAO().updateRoleUnionFBCount(fubenfreenum, role.getId(), union.getId());
			}
		}
		enterUnionFB.setCopyLevel(union.getFbLevel());
		enterUnionFB.setJopType(roleUnion.getJopType());
		enterUnionFB.setCurrentUnionMoney(union.getCurrentUnionMoney());
		if (type == 0) {
			if (unionFBList.isEmpty()) {
				enterUnionFB.setType(1);
			} else {
				enterUnionFB.setType(unionFBList.get(0).getType());
			}
		}
		List<FBList> fbList = new ArrayList<FBList>();
		for (UnionFB unionFB : unionFBList) {
			if (roleUnionFBList.isEmpty()) {
				fbList.add(FBList.ValueOf(unionFB, 0));
			}
			for (RoleUnionFB roleUnionFB : roleUnionFBList) {
				if (roleUnionFB.getFbId() == unionFB.getFbId()) {
					fbList.add(FBList.ValueOf(unionFB, roleUnionFB.getRewardState()));
				} else {
					fbList.add(FBList.ValueOf(unionFB, 0));
				}
			}
		}
		enterUnionFB.setFbList(fbList);
		return enterUnionFB;
	}

	/**
	 * 进入单个副本
	 */
	@Override
	public EnterSingleCopyVO enterSingleCopy(String acc, int type, int id) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		// 判断是否开启
		EnterSingleCopyVO enterSingleCopy = new EnterSingleCopyVO();
		// 玩家单个副本信息
		RoleUnionFB roleUnionFB = roleUnionFBAO().queryRoleUnionFBByFBId(role.getId(), type, id);
		// 玩家帮派副本信息
		UnionFB unionFB = unionFBAO().queryUnionFBByFBId(union.getId(), type, id);
		// 玩家该帮派副本动态
		List<UnionFBDynamic> UFBDList = unionFBDynamicAO().queryUnionFBDynamicByFBId(union.getId(), type, id);
		List<UnionFBDynamicVO> dList = new ArrayList<UnionFBDynamicVO>();
		if (roleUnionFB == null) {
			enterSingleCopy.setAttackNum(0);
			enterSingleCopy.setAllDamage(0);
		}
		enterSingleCopy.setLeftHp(unionFB.getLeftHp());
		if (unionFB.getState() == 2) {
			enterSingleCopy.setIsDead(0);
		} else {
			enterSingleCopy.setIsDead(1);
		}
		for (UnionFBDynamic unionFBDynamic : UFBDList) {
			dList.add(JSONUtil.fromJson(unionFBDynamic.getDes(), UnionFBDynamicVO.class));
		}
		enterSingleCopy.setUFBDList(dList);
		return enterSingleCopy;
	}

	/**
	 * 帮派副本伤害列表
	 */
	@Override
	public ShowHurtListVO showHurtList(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Union union = unionAO().queryUinonById(roleUnion.getUnionId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		// 获取帮派副本的个人总伤害
		List<RoleUnionFBState> RoleUnionFBList = roleUnionFBStateAO().queryRoleUnionFBStateByUnionId(union.getId());
		ShowHurtListVO showList = new ShowHurtListVO();
		List<HurtVO> hurtList = new ArrayList<HurtVO>();
		for (RoleUnionFBState roleUnionFBState : RoleUnionFBList) {
			Role r = roleAO().queryExistId(roleUnionFBState.getRoleId());
			hurtList.add(HurtVO.ValueOf(roleUnionFBState, r));
		}
		showList.setHurtList(hurtList);
		return showList;
	}

	/**
	 * 选择上阵卡牌
	 */
	@Override
	public ChooseCardsVO chooseCard(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		ChooseCardsVO chooseCrads = new ChooseCardsVO();
		List<CardsVO> clist = new ArrayList<CardsVO>();
		List<RoleCard> cardsList = roleCardAO().queryListByRoleId(role.getId());
		for (RoleCard RoleCard : cardsList) {
			if (RoleCard.getLevel() > 20) {
				clist.add(CardsVO.ValueOf(RoleCard, 0));
			}
		}
		chooseCrads.setCardsList(clist);
		int statrh = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.fubenstarhour));
		int startm = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.fubenstarmin));
		Date statrTime = DateUtil.addTime(DateUtil.getCurrentMonDay(), statrh, startm);
		int endh = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.fubenendhour));
		int endm = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.fubenendmin));
		Date endTime = DateUtil.addTime(DateUtil.getCurrentSundayZero(), endh, endm);
		Date now = DateUtil.GetNowDateTime();
		chooseCrads.setState(0);
		if (now.after(endTime) || now.before(statrTime)) {
			chooseCrads.setState(1);
		}
		return chooseCrads;
	}

	/**
	 * 领取副本通关奖励
	 */
	@Override
	public UnionSuccessVO receiveRewards(String acc, int type, int fbId) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		UnionFB unionFB = unionFBAO().queryUnionFBByFBId(roleUnion.getUnionId(), type, fbId);
		// 判断是否可以领取
		if (unionFB.getState() != 2) {
			throw BaseException.getException(UnionException.EXCE_UNION_FB_IS_NOT_PASS);
		}
		// 判断是否领取过
		RoleUnionFB roleUnionFB = roleUnionFBAO().queryRoleUnionFBByFBId(role.getId(), type, fbId);
		// 判断是否在该帮派中攻打过副本
		if (roleUnionFB != null && roleUnionFB.getRewardState() == 1) {
			throw BaseException.getException(UnionException.EXCE_UNION_FB_REWARD_HAS_GET);
		}
		// //发放奖励
		// List<UnionFBData> unionDataList = cacheManager().getUnionFBList();
		// for(UnionFBData unionFBData :unionDataList){
		// if(unionFBData.getType() == type&&unionFBData.getFbId() == fbId){
		// for(int i =0;i< unionFBData.getRewardIds().size();i++){
		// packetAO().addItem(role, unionFBData.getRewardType().get(i),
		// unionFBData.getRewardIds().get(i), unionFBData.getRewardNum().get(i),
		// RoleItemLog.SYS_帮派_领取帮派副本通关奖励, "");
		// }
		//
		// }
		// }
		// 修改领奖状态
		if (roleUnionFB == null) {
			roleUnionFBAO().createRoleUnionFB(RoleUnionFB.CreateRUFB(roleUnion, type, fbId));
		} else {
			roleUnionFBAO().updateUnionFBState(role.getId(), type, fbId);
		}
		UnionSuccessVO success = new UnionSuccessVO();
		success.setSuccess(0);
		return success;
	}

	/**
	 * 帮派副本战斗
	 */
	@Override
	public List<Serializable> unionFBfight(String acc, int type, int fbId, List<SwordCard> fmt) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		Union union = unionAO().queryUinonById(roleUnion.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);
		}
		// 判断背包
		packetAO().checkBagException(role, 7);
		// List<UnionFBData> FBlist = cacheManager().getUnionFBList();
		List<UnionFB> unionFBList = unionFBAO().queryUnionFBById(roleUnion.getUnionId());
		// boss数据
		List<Integer> cardIds = new ArrayList<Integer>();
		List<Integer> poss = new ArrayList<Integer>();
		Npc npcData = null;
		// UnionFBData unionFBD = null;
		// for(UnionFBData unionFB:FBlist){
		// if(unionFB.getType() == 1){
		// // int totalHp = 0;
		// unionFBD =unionFB;
		// npcData = cacheManager().getExistValueByKey(Npc.class, unionFB.getNpc());
		// cardIds.add(npcData.getNpc1());
		// cardIds.add(npcData.getNpc2());
		// cardIds.add(npcData.getNpc3());
		// cardIds.add(npcData.getNpc4());
		// cardIds.add(npcData.getNpc5());
		// cardIds.add(npcData.getNpc6());
		// for (int i = 0; i < 6; i++) {
		// poss.add(i+1);
		// }
		// }
		// }
		// // 是否禁神通技能等..
		// FighterInfo tgt = fightAO().createFighterInfoByCardIds(cardIds,poss, false,
		// true);
		// FightCfg cfg = new FightCfg();
		// cfg.setdPos(tgt.getPos());
		// // cfg.setNpcid(unionFB.g);
		// cfg.setCalcDiePos(true);
		// cfg.setCalcDSum(true);
		// cfg.setMaxBout(5);
		// //玩家数据
		// List<SwordRoleCard> selfList= new ArrayList<SwordRoleCard>();
		////
		// //判断自己的卡牌
		// if( selfList.size() <=0 ){
		// throw BaseException.getException(
		// SwordfightException.EXCE_SWORD_CARDS_ALL_DEAD);
		// }
		// //战斗
		// FighterInfo srcInfo = fightAO().createFighterInfoBySwordRoleCard(selfList);
		// //FighterInfo tgtInfo =
		// fightAO().createFighterInfoBySwordRoleCard(enemyList);
		// //init cfg
		// cfg.setCalcCard(true);
		// FightMain main = new FightMain(srcInfo, tgt, cfg);
		// FightResult result = main.fight();
		// //计算伤害，发放奖励
		// missionExecAO().jfjpDamage(role, result.getStatistic().getdSum());
		// //发放奖励
		// List<ProbItem> rtnItemAry = null;
		// rtnItemAry = cacheManager().probGot(unionFBD.getDrop());
		// //限时掉落
		// List<ProbItem> extra = missionExecAO().probGotExtra(role,unionFBD.getDrop());
		// if(extra != null) rtnItemAry.addAll(extra);
		//
		// //console.log('pve:', rtnItemAry);
		// for (ProbItem item : rtnItemAry) {
		// packetAO().addItem(role, item.getT(),item.getId(),item.getN(),1,"");
		// }
		//
		// List<Integer> rstAry = Arrays.asList(result.getWin());
		// List<Map<String, Object>> recAry = Arrays.asList(result.getMsg());
		// result.getStatistic().getdSum();
		//
		return null;
	}

}