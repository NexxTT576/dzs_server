package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.boss.Bossguwu;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.union.QingLongBoss;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.UnionBossAction;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.boss.BossBubbleVO;
import com.mdy.dzs.game.domain.boss.BossStateVO;
import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.dzs.game.domain.boss.SelfStateVO;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.union.BossHistoryVO;
import com.mdy.dzs.game.domain.union.RoleUnion;
import com.mdy.dzs.game.domain.union.Union;
import com.mdy.dzs.game.domain.union.UnionBoss;
import com.mdy.dzs.game.domain.union.UnionBossPayVO;
import com.mdy.dzs.game.domain.union.UnionBossPlayer;
import com.mdy.dzs.game.domain.union.UnionBossPveLastResultVO;
import com.mdy.dzs.game.domain.union.UnionBossResultVO;
import com.mdy.dzs.game.domain.union.UnionBossStatVO;
import com.mdy.dzs.game.domain.union.UnionBossTopTenVO;
import com.mdy.dzs.game.domain.union.UnionConfig;
import com.mdy.dzs.game.domain.union.UnionDynamic;
import com.mdy.dzs.game.domain.union.UnionLog;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.BossBattleException;
import com.mdy.dzs.game.exception.UnionException;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.fight.main.FightCfg;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.util.JSONUtil;

public class UnionBossActionImpl extends ApplicationAwareAction implements UnionBossAction {
	/**
	 * 进入青龙堂
	 */
	@Override
	public BossHistoryVO bossHistory(String acc, int unionId) throws BizException {
		Role doc = roleAO().queryByAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(doc.getId());
		Union unionData = unionAO().queryUinonById(unionId);
		int createBossCost = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.dragonBossCreateMoney));
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion || roleUnion.getUnionId() != unionId) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);// 玩家不在帮派中
		}
		UnionBoss lastData = unionBossAO().queryLast(unionId);
		int stateVal = 1;// 未开启
		Date now = new Date();
		if (lastData != null && DateUtil.isSameDay(lastData.getCreateTime(), now)) {// 有boss记录 && 与现在是同一天
			long start = new Date(lastData.getCreateTime().getTime()).getTime();
			if (now.getTime() - start < 15 * 60 * 1000 && lastData.getEndTime() == null) {// 进行中
				stateVal = 2;
			} else {// 已结束
				stateVal = 3;
			}
		}
		return new BossHistoryVO(stateVal, roleUnion.getJopType(), unionData.getGreenDragonTempleLevel(),
				unionData.getCurrentUnionMoney(), createBossCost, unionData.getGreenDragonLevel());
	}

	/**
	 * 青龙殿开启
	 */
	@Override
	public Map<String, Integer> bossCreate(String acc, int unionId) throws BizException {
		Map<String, Integer> rtnObj = new HashMap<String, Integer>();
		UnionBoss lastData = unionBossAO().queryLast(unionId);
		Union unionData = unionAO().queryUinonById(unionId);
		Date now = new Date();
		// 青龙堂1级以上可以开启挑战
		if (unionData.getGreenDragonTempleLevel() < 1) {
			throw BaseException.getException(UnionException.EXCE_GREEN_DRAGON_TEMPLE_LEVEL_CONTROL);
		}
		// 已经创建 每天一次
		if (lastData != null && DateUtil.isSameDay(lastData.getCreateTime(), now)) {
			rtnObj.put("result", 2);
			rtnObj.put("curUnionMoney", unionData.getCurrentUnionMoney());
			return rtnObj;
		}
		// 开启权限检查--帮主副帮主
		Role role = roleAO().queryExistAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(role.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion || roleUnion.getUnionId() != unionId) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);// 玩家不在帮派中
		}
		if (roleUnion.getJopType() != RoleUnion.vice && roleUnion.getJopType() != RoleUnion.leader) {
			throw BaseException.getException(UnionException.EXCE_ROLE_JUR_lESS);// 权限不足
		}
		// 清除该帮派上次活动参与玩家数据
		unionBossPlayerAO().delete(unionId);
		// 删除缓存数据
		unionBossPlayerAO().deleteBossCache(unionId);

		int costMoney = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.dragonBossCreateMoney));
		if (unionData.getCurrentUnionMoney() < costMoney) {
			throw BaseException.getException(UnionException.EXCE_UNION_NOT_ENOUGH_MONEY_FOR_CREATE_DRAGON);// 资金不足不能开启
		}
		int life = unionData.getGreenDragonLevel() * 500000 + 8000000;
		if (life > Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.dragonBossLifeMax))) {
			life = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.dragonBossLifeMax));
		}
		int dragonBossID = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.dragonBossID));
		Card cardData = cacheManager().getExistValueByKey(Card.class, dragonBossID);
		UnionBoss newbattle = new UnionBoss();
		newbattle.setUnionId(unionId);
		newbattle.setName(cardData.getName());
		newbattle.setLevel(unionData.getGreenDragonLevel());
		newbattle.setLifeTotal(life);
		newbattle.setLife(life);
		unionBossAO().add(newbattle);
		// 帮派资金消耗
		unionAO().reduceUnionMoney(unionData, costMoney);
		unionData.setCurrentUnionMoney(unionData.getCurrentUnionMoney() - costMoney);

		List<Object> plist = new ArrayList<Object>();
		plist.add(role.getName());
		int type = 0;
		if (roleUnion.getJopType() == RoleUnion.leader) {
			type = UnionDynamic.OPEN_DRGON;
		} else {
			type = UnionDynamic.OPEN_DRGON_BY_VICE;
		}
		unionDynamicAO().createDynamic(UnionDynamic.valueOf(unionId, role.getId(), type, plist));

		rtnObj.put("result", 1);
		rtnObj.put("curUnionMoney", unionData.getCurrentUnionMoney());
		unionLogAO().add(UnionLog.valueOf(unionData, UnionLog.SYS_帮派_开启青龙堂, costMoney, unionData.getCurrentUnionMoney(),
				role.getId() + "," + DateUtil.getDateString(DateUtil.GetNowDateTime()) + unionData.getTotalUnionMoney()
						+ ""));
		return rtnObj;
	}

	@Override
	public UnionBossTopTenVO bossTop(String acc, int unionId) throws BizException {
		Role doc = roleAO().queryByAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(doc.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion || roleUnion.getUnionId() != unionId) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);// 玩家不在帮派中
		}
		List<BossTopTenVO> topTenList = new ArrayList<BossTopTenVO>();
		UnionBoss lastData = unionBossAO().queryLast(unionId);
		if (lastData != null) {
			topTenList = unionBossPlayerAO().getTopTenList(unionId);
			if (topTenList.size() == 0) {// 缓存中已无数据，则取数据库中数据
				topTenList = lastData.getTopTen();
			} else if (lastData.getKiller() != 0) {// 缓存中topTen，添加第一个位置-击杀者
				Role killerDoc = roleAO().queryById(lastData.getKiller());
				topTenList.add(0, new BossTopTenVO(0, killerDoc.getAccount(), killerDoc.getName(), 0,
						killerDoc.getLevel(), killerDoc.getId(), killerDoc.getFaction()));
			} else {
				topTenList.add(0, new BossTopTenVO(0, "", "", 0, 0, 0, ""));
			}
		}
		if (topTenList.size() == 0) {// 无活动记录
			topTenList.add(0, new BossTopTenVO(0, "", "", 0, 0, 0, ""));
		}
		return new UnionBossTopTenVO(topTenList);
	}

	@Override
	public UnionBossStatVO bossState(String acc, int unionId) throws BizException {
		Role doc = roleAO().queryByAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(doc.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion || roleUnion.getUnionId() != unionId) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);// 玩家不在帮派中
		}

		UnionBoss lastData = unionBossAO().queryLast(unionId); // 最后一条记录
		Date now = new Date();
		long start = new Date(lastData.getCreateTime().getTime()).getTime();
		int endTime = 0;
		if (now.getTime() - start < 15 * 60 * 1000 && lastData.getEndTime() == null) {// 进行中
			Date overTime = new Date(lastData.getCreateTime().getTime() + 15 * 60 * 1000);
			endTime = (int) (overTime.getTime() - now.getTime()) / 1000;
		} else if (lastData.getEndTime() == null) { // 时间结束但还未设置endtime
			unionBossAO().updateEndTime(now, lastData.getActId());
		}
		BossStateVO bossState = new BossStateVO(lastData.getName(), lastData.getLevel(), lastData.getLife(),
				lastData.getLifeTotal(), endTime);
		// 当等待时间已过，当前时间即为允许操作时间
		UnionBossPlayer player = unionBossPlayerAO().queryAdd(acc, doc.getId(), doc.getName(), unionId);// 没有则新建条记录
		if (player.getBattleWait() == null || player.getBattleWait().getTime() < now.getTime()) {
			player.setBattleWait(now);
		}
		if (player.getSilverWait() == null || player.getSilverWait().getTime() < now.getTime()) {
			player.setSilverWait(now);
		}
		SelfStateVO selfState = unionBossAO().getSelfState(player, lastData, doc.getId(), unionId);
		unionBossPlayerAO().update(player);
		// 冒字
		List<BossBubbleVO> bubbleList = unionBossPlayerAO().getBubbleList(doc.getId(), unionId,
				Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.dragonBossBattleRefreshDis)));

		return new UnionBossStatVO(bossState, selfState, bubbleList);
	}

	@Override
	public UnionBossPayVO bossPay(String acc, int unionId, int use) throws BizException {
		Role doc = roleAO().queryByAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(doc.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion || roleUnion.getUnionId() != unionId) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);// 玩家不在帮派中
		}

		UnionBoss lastData = unionBossAO().queryLast(unionId);
		int isFinish = 1; // 没死
		if (lastData.getEndTime() != null) { // 已死
			isFinish = 2;
			return new UnionBossPayVO(new SelfStateVO(), isFinish, doc.getGold(), doc.getSilver(), 1);
		}

		Date now = new Date();
		long nowTime = now.getTime();
		UnionBossPlayer player = unionBossPlayerAO().queryAdd(acc, doc.getId(), doc.getName(), unionId);// 没有则新建条记录
		Bossguwu guwuData = cacheManager().getExistValueByKey(Bossguwu.class, 2);// 青龙
		Date waitTime = new Date(player.getBattleWait().getTime());
		int isSuccess = 2; // 默认鼓舞成功
		boolean isRevive = false;// 未复活

		if ((use == 1 || use == 2) && player.getHurtAdd() >= guwuData.getLimit()) {
			throw BaseException.getException(BossBattleException.EXCE_BOSS_GUWU_LIMIT); // 鼓舞已经达到上限，无法继续鼓舞
		}

		if (use == 3 && nowTime - waitTime.getTime() > 0) {
			isRevive = true; // 已经复活，请继续战斗吧
		}

		if (use == 1) {// 银币鼓舞
			if (nowTime < player.getSilverWait().getTime()) {
				throw BaseException.getException(BossBattleException.EXCE_BOSS_GUWU_COUNT); // 银币鼓舞倒计时中
			}
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_银币, guwuData.getSilver(),
					RoleItemLog.SYS_帮派_boss_银币鼓舞, "");
			Boolean isAdd = false;
			if (player.getSilverCnt() <= 3) {
				isAdd = true;
			} else if (player.getSilverCnt() <= 6) {
				if (cacheManager().random(1, 100) <= 60) {
					isAdd = true;
				}
			} else if (player.getSilverCnt() <= 10) {
				if (cacheManager().random(1, 100) <= 30) {
					isAdd = true;
				}
			} else {
				if (cacheManager().random(1, 100) <= 10) {
					isAdd = true;
				}
			}

			if (isAdd) {
				player.setHurtAdd(player.getHurtAdd() + guwuData.getAdd());
			} else {
				isSuccess = 1; // 银币鼓舞失败
			}
			player.setSilverWait(new Date(nowTime + 60 * 1000));
			player.setSilverCnt(player.getSilverCnt() + 1);
		} else if (use == 2) {// 元宝鼓舞
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, guwuData.getCoin(),
					RoleItemLog.SYS_帮派_boss_元宝鼓舞, "");
			player.setHurtAdd(player.getHurtAdd() + guwuData.getAdd());
		} else if (use == 3 && !isRevive) {// 元宝复活
			int nextLiveGold = bossBattleAO().nextLiveGold(player.getLiveCnt());
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, nextLiveGold,
					RoleItemLog.SYS_帮派_boss_元宝复活, "");
			player.setBattleWait(now);
			player.setLiveCnt(player.getLiveCnt() + 1);
		}
		SelfStateVO selfState = unionBossAO().getSelfState(player, lastData, doc.getId(), unionId);

		if (isRevive) {// 已经复活再请求复活== 复活操作失败
			isSuccess = 1;
			selfState.setBattleWait(0);
		} else {
			unionBossPlayerAO().update(player);
		}
		return new UnionBossPayVO(selfState, isFinish, doc.getGold(), doc.getSilver(), isSuccess);
	}

	@Override
	public List<Serializable> bossPve(String acc, int unionId) throws BizException {
		Role doc = roleAO().queryByAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(doc.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion || roleUnion.getUnionId() != unionId) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);// 玩家不在帮派中
		}
		UnionBoss lastData = unionBossAO().queryLast(unionId);
		int isFinish = 1;
		if (lastData.getEndTime() != null) { // 已结束
			isFinish = 2;
			return Arrays.asList((Serializable) null, null, null, null, 0, isFinish);
		}

		Date now = new Date();
		long nowTime = now.getTime();
		UnionBossPlayer player = unionBossPlayerAO().queryAdd(acc, doc.getId(), doc.getName(), unionId);
		Date waitTime = new Date(player.getBattleWait().getTime());

		if (waitTime.getTime() - nowTime > 3000) {
			int disTime = (int) (waitTime.getTime() - nowTime) / 1000;
			return Arrays.asList((Serializable) null, null, null, null, disTime, isFinish);
		}
		// 准备战斗数据
		List<Integer> cardIds = new ArrayList<Integer>();
		List<Integer> poss = new ArrayList<Integer>();
		int dragonBossID = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.dragonBossID));
		cardIds.add(dragonBossID);
		poss.add(2);
		// boss禁用神通，禁被上buff
		FighterInfo tgt = fightAO().createFighterInfoByCardIds(cardIds, poss, true, true);
		Card bossCard = (Card) tgt.getCards().get(1); // 取到boss卡
		bossCard.getBase().set(0, lastData.getLife()); // 重置生命值

		// 玩家禁用神通，可被上buff
		FighterInfo src = fightAO().createFighterInfoByRole(doc, new ArrayList<Integer>(), true, false);
		List<Prop> probs = bossBattleAO().probsAdd(player.getHurtAdd()); // 添加伤害加成
		List<List<Prop>> props = src.getProps(); // 所有卡属性数组
		for (int i = 0; i < props.size(); i++) { // 每张卡进行加成
			if (props.get(i) != null) {
				List<Prop> ps = props.get(i);
				ps.addAll(probs);
			}
		}
		FightCfg cfg = new FightCfg(); // 获取数值
		cfg.setCalcDSum(true);

		FightMain main = new FightMain(src, tgt, cfg);
		FightResult result = main.fight();
		// 战斗结束
		int damageTotal = result.getStatistic().getdSum(); // 本次攻击对boss的伤害
		if (lastData.getLife() > damageTotal) { // 减血
			unionBossAO().updateLife(damageTotal, lastData.getActId());
		} else { // boss被打死
			damageTotal = lastData.getLife();
			unionBossAO().updateLife(damageTotal, lastData.getActId());
			unionBossAO().updateKiller(doc.getId(), lastData.getActId());
			unionBossAO().updateEndTime(now, lastData.getActId());
		}
		// 缓存数据更新
		int rank = unionBossPlayerAO().updateRankList(doc.getAccount(), doc.getName(), damageTotal, doc.getLevel(),
				doc.getId(), unionId, doc.getFaction());
		int rtnBubbleNum = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.dragonBossBattleBubbleNum));
		int bubbleDis = Integer.valueOf(cacheManager().getUnionConfigValue(UnionConfig.dragonBossBattleRefreshDis));
		unionBossPlayerAO().updateBubbleList(doc.getName(), damageTotal, doc.getId(), unionId, rtnBubbleNum, bubbleDis);

		// 设置更新player
		player.setBattleWait(new Date(nowTime + 45 * 1000));
		player.setTotalHurt(player.getTotalHurt() + damageTotal);
		unionBossPlayerAO().update(player);
		unionBossPlayerAO().updateBattleCnt(doc.getId(), unionId);

		float hurtR = (float) player.getTotalHurt() / lastData.getLifeTotal() * 100;// 总伤害比例
		int silverWait = (int) (player.getSilverWait().getTime() - now.getTime()) / 1000;// 银两鼓舞等待时间
		int nextLiveGold = bossBattleAO().nextLiveGold(player.getLiveCnt());
		if (silverWait < 0) {
			silverWait = 0;
		}
		SelfStateVO selfState = new SelfStateVO(player.getBattleCnt(), // 攻击次数
				player.getTotalHurt(), // 总伤害
				(int) hurtR, // 总伤害比例
				rank, // 当前排行
				player.getHurtAdd(), // 伤害加成
				silverWait, // 银两鼓舞等待时间
				nextLiveGold, // 下次复活元宝数目
				45, // 攻击等待时间
				damageTotal); // 本次伤害

		// 攻击获取声望
		int dragonBossBattleAward = Integer
				.valueOf(cacheManager().getUnionConfigValue(UnionConfig.dragonBossBattleAward));
		packetAO().addItem(doc, Packet.POS_ATTR, Packet.ATTR_声望, dragonBossBattleAward, RoleItemLog.SYS_帮派_boss_攻击boss,
				"");

		List<ProbItem> coinAry = new ArrayList<ProbItem>();
		coinAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_声望, dragonBossBattleAward));

		return Arrays.asList((Serializable) Arrays.asList(result.getWin()),
				(Serializable) Arrays.asList(result.getMsg()), (Serializable) coinAry, selfState, 0, isFinish);
	}

	@Override
	public UnionBossResultVO bossResult(String acc, int unionId) throws BizException {
		Role doc = roleAO().queryByAccount(acc);
		RoleUnion roleUnion = roleUnionAO().queryRoleUnionById(doc.getId());
		if (roleUnion == null || roleUnion.getState() == RoleUnion.roleoutUnion || roleUnion.getUnionId() != unionId) {
			throw BaseException.getException(UnionException.EXCE_ROLE_NOT_IN_UNION);// 玩家不在帮派中
		}
		UnionBoss lastData = unionBossAO().queryLast(unionId);
		UnionBossPlayer player = unionBossPlayerAO().queryAdd(acc, doc.getId(), doc.getName(), unionId);// 没有则新建条记录
		List<ProbItem> coinAry = new ArrayList<ProbItem>();
		Date now = new Date();
		long start = new Date(lastData.getCreateTime().getTime()).getTime();
		int isFinish = 2; // 结束
		int endTime = 0;
		if (lastData.getEndTime() == null && now.getTime() - start + 2000 < 15 * 60 * 1000) { // 未结束 2秒偏移
			isFinish = 1; // 未结束
		} else if (lastData.getEndTime() == null) { // 时间结束但还未设置endtime
			unionBossAO().updateEndTime(now, lastData.getActId());
		}
		if (isFinish == 2) {
			long nowTime = (long) Math.floor(new Date().getTime() / 1000);
			long actEndTime = (long) Math.floor((lastData.getCreateTime().getTime() + 15 * 60 * 1000) / 1000);
			endTime = (int) (actEndTime - nowTime > 0 ? actEndTime - nowTime : 0);
		}

		int rank = 0;
		String killer = "";
		if (player.getTotalHurt() > 0) {// 有排名
			List<RankList> rankList = unionBossPlayerAO().getRankList(unionId);
			rank = bossBattleAO().getRank(doc.getId(), rankList);
		}
		if (lastData.getKiller() != 0) {
			Role killdoc = roleAO().queryById(lastData.getKiller());
			killer = killdoc.getName();
		}
		UnionBossPveLastResultVO result = new UnionBossPveLastResultVO(killer, player.getTotalHurt(), rank, endTime,
				lastData.getLife(), lastData.getLifeTotal());

		int coinNum = 0;
		int popualNum = 0;
		int bangGong = 0;
		if (rank > 0) {
			Map<Integer, QingLongBoss> cDatas = cacheManager().getValues(QingLongBoss.class);// 充值表

			for (Entry<Integer, QingLongBoss> entry : cDatas.entrySet()) {
				QingLongBoss bossItem = entry.getValue();
				if (rank >= bossItem.getMin() && rank <= bossItem.getMax()) {
					coinNum = (int) Math.floor(bossItem.getFix1() + lastData.getLevel() * bossItem.getRatio1());
					popualNum = Math.round(bossItem.getFix2() - (bossItem.getRatio2() * rank));
					bangGong = Math.round(bossItem.getFix3() - (bossItem.getRatio3() * rank));
					break;
				}
			}
		}
		coinAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_银币, coinNum));
		coinAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_声望, popualNum));
		coinAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_贡献, bangGong));

		return new UnionBossResultVO(result, coinAry, isFinish);
	}

	/**
	 * 帮派boss发奖
	 */
	@Override
	public void sendAward() throws BizException {
		final Logger logger = LoggerFactory.get(UnionActionImpl.class);
		// 检出未发奖的活动-award=0 and create_time-now()>900秒
		List<UnionBoss> unAwardList = unionBossAO().queryUnawardList();
		Date now = new Date();

		for (int i = 0; i < unAwardList.size(); i++) {
			// 活动结束 或者时间到-距离开始时间到15分钟
			UnionBoss curData = unAwardList.get(i);
			if (curData.getEndTime() == null) {
				unionBossAO().updateEndTime(now, curData.getActId()); // 设置EndTime
			}
			List<BossTopTenVO> topTenList = unionBossPlayerAO().getTopTenList(curData.getUnionId());
			// unionBossAO().updateTopTen(topTenList,curData.getActId());
			// curData.setTopTen(topTenList);
			// 发奖 取缓存中rankList 数据
			List<RankList> rankList = unionBossPlayerAO().getRankList(curData.getUnionId());
			giftCenterAO().sendUnionDragonGift(rankList, curData.getLevel());
			// 击杀奖
			if (curData.getKiller() != 0) {
				Role doc = roleAO().queryById(curData.getKiller());
				if (doc == null) {
					topTenList.add(0, new BossTopTenVO(0, "", "", 0, 0, 0, ""));
					logger.error("now send qingLongBossKiller award:" + JSONUtil.toJson(curData.getKiller()));
				} else {
					topTenList.add(0, new BossTopTenVO(0, doc.getAccount(), doc.getName(), 0, doc.getLevel(),
							doc.getId(), doc.getFaction()));
					giftCenterAO().sendUnionDragonKillerGift(curData);
				}
			} else {
				topTenList.add(0, new BossTopTenVO(0, "", "", 0, 0, 0, ""));
			}
			unionBossAO().updateTopTen(topTenList, curData.getActId());
			curData.setTopTen(topTenList);
		}
	}

}
