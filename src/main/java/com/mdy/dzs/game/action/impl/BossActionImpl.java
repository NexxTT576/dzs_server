package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.boss.Boss;
import com.mdy.dzs.data.domain.boss.Bossguwu;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.BossAction;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.boss.BattleHistoryVO;
import com.mdy.dzs.game.domain.boss.BattleResultVO;
import com.mdy.dzs.game.domain.boss.BossBattle;
import com.mdy.dzs.game.domain.boss.BossBattlePlayer;
import com.mdy.dzs.game.domain.boss.BossBubbleVO;
import com.mdy.dzs.game.domain.boss.BossStateVO;
import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.dzs.game.domain.boss.SelfStateVO;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.BossBattleException;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.fight.main.FightCfg;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.util.JSONUtil;

public class BossActionImpl extends ApplicationAwareAction implements BossAction {
	// 获取伤害排行
	@Override
	public List<Serializable> cmdtop(String acc) {
		// find last data
		BossBattle lastData = bossBattleAO().queryLast();
		List<BossTopTenVO> topTenList = new ArrayList<BossTopTenVO>();
		if (lastData != null) {
			// 优先取缓存，之后取库表
			topTenList = bossBattlePlayerAO().getTopTenList();
			if (topTenList.size() == 0) {
				topTenList = lastData.getTopTen();
			}
		}
		return Arrays.asList((Serializable) topTenList);
	}

	// 上次活动结果
	@Override
	public List<Serializable> history(String acc) throws BizException {
		BossBattle lastData = bossBattleAO().queryLast();
		BattleHistoryVO history = new BattleHistoryVO();
		int waitSecs = 0;
		Card cardData = cacheManager().getExistValueByKey(Card.class, Constants.bossID);
		if (lastData != null && lastData.getEndTime() != null) {// 或动已结束
			waitSecs = bossBattleAO().difSecs();
			List<String> names = new ArrayList<String>();
			List<BossTopTenVO> topTenList = new ArrayList<BossTopTenVO>();
			topTenList = bossBattlePlayerAO().getTopTenList();
			if (topTenList.size() == 0) {
				topTenList = lastData.getTopTen();
			}
			for (int i = 0; i < topTenList.size(); i++) {
				if (i >= 3) {
					break;
				}
				names.add(topTenList.get(i).getName());
			}

			history.setKillName("");
			history.setName(cardData.getName());
			history.setLevel(lastData.getLevel());
			history.setTop3Name(names);
			if (lastData.getKillPlayer() > 0) {
				Role doc = roleAO().queryExistId(lastData.getKillPlayer());
				history.setKillName(doc.getName());
			}
		} else if (lastData == null) {// 无活动记录
			waitSecs = bossBattleAO().difSecs();
			history.setKillName("");
			history.setName(cardData.getName());
			history.setLevel(1);
			history.setTop3Name(new ArrayList<String>());
		}

		return Arrays.asList((Serializable) waitSecs, history);
	}

	// boss状态 自己状态 别人伤害数值
	@Override
	public List<Serializable> state(String acc) throws BizException {
		BossBattle lastData = bossBattleAO().queryLast(); // 最后一条记录
		Date now = new Date();
		BossStateVO bossState = new BossStateVO();
		SelfStateVO selfState = new SelfStateVO();
		long start = new Date(lastData.getCreateTime().getTime()).getTime();

		int endTime = 0;
		if (now.getTime() - start < 15 * 60 * 1000 && lastData.getEndTime() == null) {// 进行中
			Date overTime = new Date(lastData.getCreateTime().getTime() + 15 * 60 * 1000);
			endTime = (int) (overTime.getTime() - now.getTime()) / 1000;
		} else if (lastData.getEndTime() == null) { // 时间结束但还未设置endtime
			bossBattleAO().updateEndTime(now, lastData.getActId());
		}

		bossState.setName(lastData.getName());
		bossState.setLevel(lastData.getLevel());
		bossState.setLife(lastData.getLife());
		bossState.setLifeTotal(lastData.getLifeTotal());
		bossState.setEndTime(endTime);

		// 当等待时间已过，当前时间即为允许操作时间
		Role doc = roleAO().queryByAccount(acc);
		BossBattlePlayer player = bossBattlePlayerAO().queryAdd(doc);// 没有则新建条记录

		if (player.getBattleWait() == null || player.getBattleWait().getTime() < now.getTime()) {
			player.setBattleWait(now);
		}
		if (player.getSilverWait() == null || player.getSilverWait().getTime() < now.getTime()) {
			player.setSilverWait(now);
		}
		selfState = bossBattleAO().getSelfState(player, lastData, doc.getId());
		bossBattlePlayerAO().update(player);
		// 冒字
		List<BossBubbleVO> bubbleList = new ArrayList<BossBubbleVO>();
		if (bossBattlePlayerAO().getBubbleList(doc.getId()) != null) {
			bubbleList = bossBattlePlayerAO().getBubbleList(doc.getId());
		}

		return Arrays.asList((Serializable) bossState, (Serializable) selfState, (Serializable) bubbleList);
	}

	// 鼓舞 复活 use-1-银币鼓舞 use-2-元宝鼓舞 use-3-复活
	@Override
	public List<Serializable> pay(String acc, int use) throws BizException {
		BossBattle lastData = bossBattleAO().queryLast();
		int isFinish = 1; // 没死
		if (lastData.getEndTime() != null) { // 已死
			isFinish = 2;
			return Arrays.asList((Serializable) new SelfStateVO(), isFinish);
		}

		Date now = new Date();
		long nowTime = now.getTime();
		Role doc = roleAO().queryByAccount(acc);
		BossBattlePlayer player = bossBattlePlayerAO().queryAdd(doc);
		Bossguwu guwuData = cacheManager().getExistValueByKey(Bossguwu.class, 1);
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
					RoleItemLog.SYS_boss_银币鼓舞, "");
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
					RoleItemLog.SYS_boss_元宝鼓舞, "");
			player.setHurtAdd(player.getHurtAdd() + guwuData.getAdd());
		} else if (use == 3 && !isRevive) {// 元宝复活
			int nextLiveGold = bossBattleAO().nextLiveGold(player.getLiveCnt());
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, nextLiveGold,
					RoleItemLog.SYS_boss_元宝复活, "");
			player.setBattleWait(now);
			player.setLiveCnt(player.getLiveCnt() + 1);
		}
		SelfStateVO selfState = bossBattleAO().getSelfState(player, lastData, doc.getId());

		if (isRevive) {// 已经复活再请求复活== 复活操作失败
			isSuccess = 1;
			selfState.setBattleWait(0);
		} else {
			bossBattlePlayerAO().update(player);
		}

		return Arrays.asList((Serializable) selfState, isFinish, doc.getGold(), doc.getSilver(), isSuccess);
	}

	// 战斗
	@Override
	public List<Serializable> pve(String acc) throws BizException {
		BossBattle lastData = bossBattleAO().queryLast();
		int isFinish = 1;
		if (lastData.getEndTime() != null) { // 已结束
			isFinish = 2;
			return Arrays.asList((Serializable) null, null, null, null, 0, isFinish);
		}

		Date now = new Date();
		long nowTime = now.getTime();
		Role doc = roleAO().queryByAccount(acc);
		BossBattlePlayer player = bossBattlePlayerAO().queryAdd(doc);
		Date waitTime = new Date(player.getBattleWait().getTime());

		if (waitTime.getTime() - nowTime > 3000) {
			int disTime = (int) (waitTime.getTime() - nowTime) / 1000;
			return Arrays.asList((Serializable) null, null, null, null, disTime, isFinish);
		}
		// 准备战斗数据
		List<Integer> cardIds = new ArrayList<Integer>();
		List<Integer> poss = new ArrayList<Integer>();
		cardIds.add(Constants.bossID);
		poss.add(2);
		// boss禁用神通，禁被上buff
		FighterInfo tgt = fightAO().createFighterInfoByCardIds(cardIds, poss, true, true);

		Card bossCard = (Card) tgt.getCards().get(1); // 取到boss卡
		bossCard.setBase(Constants.bossBase);
		bossCard.setLead(Constants.bossLead);
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
			bossBattleAO().updateLife(damageTotal, lastData.getActId());
		} else { // boss被打死
			damageTotal = lastData.getLife();
			bossBattleAO().updateLife(damageTotal, lastData.getActId());
			bossBattleAO().updateEndTime(now, lastData.getActId());
			bossBattleAO().updateKiller(doc.getId(), lastData.getActId());
		}
		// 缓存数据更新
		int rank = bossBattlePlayerAO().updateRankList(doc.getAccount(), doc.getName(), damageTotal, doc.getLevel(),
				doc.getId(), doc.getFaction());
		bossBattlePlayerAO().updateBubbleList(doc.getName(), damageTotal, doc.getId());

		// 设置更新player
		player.setBattleWait(new Date(nowTime + 45 * 1000));
		player.setTotalHurt(player.getTotalHurt() + damageTotal);

		bossBattlePlayerAO().update(player);
		bossBattlePlayerAO().updateBattleCnt(doc.getId());

		SelfStateVO selfState = new SelfStateVO();
		float hurtR = (float) player.getTotalHurt() / lastData.getLifeTotal() * 100;// 总伤害比例
		int silverWait = (int) (player.getSilverWait().getTime() - now.getTime()) / 1000;// 银两鼓舞等待时间
		int nextLiveGold = bossBattleAO().nextLiveGold(player.getLiveCnt());

		if (silverWait < 0) {
			silverWait = 0;
		}
		selfState.setCurHurt(damageTotal); // 当前伤害值
		selfState.setNum(player.getBattleCnt()); // 攻击次数
		selfState.setHurt(player.getTotalHurt()); // 总伤害
		selfState.setHurtR((int) hurtR); // 总伤害比例
		selfState.setHurtAdd(player.getHurtAdd()); // 伤害加成
		selfState.setNxtLiveGold(nextLiveGold); // 下次复活元宝数目
		selfState.setBattleWait(45); // 攻击等待时间
		selfState.setSilverWait(silverWait); // 银两鼓舞等待时间
		selfState.setRank(rank); // 当前排行
		// 攻击获取声望
		packetAO().addItem(doc, Packet.POS_ATTR, Packet.ATTR_声望, Constants.bossBattleAward, RoleItemLog.SYS_boss_攻击boss,
				"");

		List<ProbItem> coinAry = new ArrayList<ProbItem>();
		coinAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_声望, Constants.bossBattleAward));
		return Arrays.asList((Serializable) Arrays.asList(result.getWin()),
				(Serializable) Arrays.asList(result.getMsg()), (Serializable) coinAry, selfState, isFinish);
	}

	// 活动结束，最终结果
	@Override
	public List<Serializable> result(String acc) throws BizException {
		BossBattle lastData = bossBattleAO().queryLast();
		Role doc = roleAO().queryByAccount(acc);
		BossBattlePlayer player = bossBattlePlayerAO().queryAdd(doc);// 没有则新建条记录
		List<ProbItem> coinAry = new ArrayList<ProbItem>();
		Date now = new Date();
		long start = new Date(lastData.getCreateTime().getTime()).getTime();
		int isFinish = 2; // 结束
		if (lastData.getEndTime() == null && now.getTime() - start + 2000 < 15 * 60 * 1000) { // 未结束 2秒偏移
			isFinish = 1;
		} else if (lastData.getEndTime() == null) { // 时间结束但还未设置endtime
			bossBattleAO().updateEndTime(now, lastData.getActId());
		}

		BattleResultVO result = new BattleResultVO();
		int endTime = 0;
		if (isFinish == 2) {// 结束 整理距活动结束时间
			long nowTime = (long) Math.floor(new Date().getTime() / 1000);
			long actEndTime = (long) Math.floor((lastData.getCreateTime().getTime() + 15 * 60 * 1000) / 1000);
			endTime = (int) (actEndTime - nowTime > 0 ? actEndTime - nowTime : 0);
		}

		int rank = 0;
		if (player.getTotalHurt() > 0) {// 有排名
			List<RankList> rankList = new ArrayList<RankList>();
			if (bossBattlePlayerAO().getRankList() != null) {
				rankList = bossBattlePlayerAO().getRankList();
			}
			rank = bossBattleAO().getRank(doc.getId(), rankList);
		}

		result.setKill("");
		result.setEndTime(endTime);
		result.setHurt(player.getTotalHurt());
		result.setRank(rank);
		result.setBossLife(lastData.getLife());
		result.setLifeTotal(lastData.getLifeTotal());
		if (lastData.getKillPlayer() != 0) {
			Role killdoc = roleAO().queryById(lastData.getKillPlayer());
			result.setKill(killdoc.getName());
		}

		int coinNum = 0;
		int popualNum = 0;
		if (rank > 0) {
			Map<Integer, Boss> cDatas = cacheManager().getValues(Boss.class);// 充值表

			for (Entry<Integer, Boss> entry : cDatas.entrySet()) {
				Boss bossItem = entry.getValue();
				if (rank >= bossItem.getMin() && rank <= bossItem.getMax()) {// --
					coinNum = (int) Math.floor((bossItem.getFix1() - (bossItem.getRatio1() * rank)) * doc.getLevel());
					popualNum = Math.round(bossItem.getFix2() - (bossItem.getRatio2() * rank));
					break;
				}
			}
		}
		coinAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_银币, coinNum));
		coinAry.add(new ProbItem(Packet.POS_BAG, Packet.ATTR_声望, popualNum));

		return Arrays.asList((Serializable) result, (Serializable) coinAry, isFinish);
	}

	// 新建启动一条boss战活动
	@Override
	public void createBattle() throws BizException {
		// 清除上次活动参与玩家数据
		bossBattlePlayerAO().delete();
		// 删除缓存数据
		bossBattlePlayerAO().deleteBossCache();

		BossBattle lastData = bossBattleAO().queryLast(); // 最后一条记录
		int nxtLv = 1;
		int life = 0;
		if (lastData != null && !(lastData.getLevel() == 1 && lastData.getLife() == lastData.getLifeTotal())) {// 已有过记录
																												// 并已打过
			nxtLv = lastData.getLife() <= 0 ? lastData.getLevel() + 1 : lastData.getLevel();
			// 世界boss血量=昨日血量/昨日打死所用时间*（13-4/世界boss今日等级）
			// 其中：若昨日未打死，则昨日打死所用时间为15分钟
			double minute = Math.ceil(
					((lastData.getEndTime().getTime() * 1.0 - lastData.getCreateTime().getTime() * 1.0) / (1000 * 60)));
			minute = minute > 15 ? 15 : minute;
			life = (int) (lastData.getLifeTotal() / minute * (13 - 4 * 1.0 / nxtLv));

		} else {
			// 第一次开启活动
			// 第1日血量=竞技场前10名平均战力*2000
			List<Integer> seeRanks = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
			List<RoleArena> ranks = roleArenaAO().queryListByRanks(seeRanks);
			int totalAttack = 0;
			int aveAttack = 0;

			for (int i = 0; i < ranks.size(); i++) {
				RoleArena data = ranks.get(i);
				Role role = roleAO().queryExistId(data.getRoleId());
				totalAttack += role.getAttack();
			}
			aveAttack = (int) Math.floor(totalAttack * 0.012);
			life = aveAttack * roleAO().queryRoleSize();
		}
		if (life < Constants.bossLifeMin) {
			life = Constants.bossLifeMin;
		}
		if (life > Constants.bossLifeMax) {
			life = Constants.bossLifeMax;
		}
		// 取boss卡牌信息
		Card cardData = cacheManager().getExistValueByKey(Card.class, Constants.bossID);
		BossBattle newbattle = new BossBattle();
		newbattle.setName(cardData.getName());
		newbattle.setLevel(nxtLv);
		newbattle.setLifeTotal(life);
		newbattle.setLife(life);
		bossBattleAO().add(newbattle);
	}

	// boss战发奖
	@Override
	public void sendAward() throws BizException {
		final Logger logger = LoggerFactory.get(ArenaActionImpl.class);
		// 活动结束 或者时间到-距离开始时间到15分钟
		BossBattle lastData = bossBattleAO().queryLast();
		if (lastData == null)
			return; // 未曾开启过boss

		Date now = new Date();
		long nowTime = now.getTime();
		long start = new Date(lastData.getCreateTime().getTime()).getTime();
		// 活动未发奖 并且 （活动已结束 或 活动时间已持续15分钟）
		if (lastData.getAward() == 0 && (lastData.getEndTime() != null || nowTime - start >= 15 * 60 * 1000)) {
			// 更新库表
			List<BossTopTenVO> topTenList = new ArrayList<BossTopTenVO>();
			topTenList = bossBattlePlayerAO().getTopTenList();
			bossBattleAO().updateTopTen(topTenList, lastData.getActId());
			if (lastData.getEndTime() == null) {
				bossBattleAO().updateEndTime(now, lastData.getActId()); // 设置EndTime
			}

			// 发奖 取缓存中rankList 数据
			List<RankList> rankList = new ArrayList<RankList>();
			if (bossBattlePlayerAO().getRankList() != null) {
				rankList = bossBattlePlayerAO().getRankList();
			}
			giftCenterAO().sendBossGift(rankList);
			// 击杀奖
			if (lastData.getKillPlayer() != 0) {
				Role doc = roleAO().queryById(lastData.getKillPlayer());
				if (doc == null) {
					logger.error("now send bossKiller award:" + JSONUtil.toJson(lastData.getKillPlayer()));
				} else {
					giftCenterAO().sendBossKillerGift(lastData.getKillPlayer(), doc.getLevel());
					missionExecAO().killBoss(doc);
				}
			}
		}
	}
}
