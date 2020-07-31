package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.biwu.BiwuShop;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.robot.MoBan;
import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.TournamentAction;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.mail.Mail;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.tournament.CEnemyVO;
import com.mdy.dzs.game.domain.tournament.CExchangeList;
import com.mdy.dzs.game.domain.tournament.CExchangeListVO;
import com.mdy.dzs.game.domain.tournament.CExchangeVO;
import com.mdy.dzs.game.domain.tournament.COpponentInfo;
import com.mdy.dzs.game.domain.tournament.CRankVO;
import com.mdy.dzs.game.domain.tournament.CTournamentCardVO;
import com.mdy.dzs.game.domain.tournament.CTournamentEnemyVO;
import com.mdy.dzs.game.domain.tournament.CTournamentRankVO;
import com.mdy.dzs.game.domain.tournament.CTournamentSelfInfoVO;
import com.mdy.dzs.game.domain.tournament.OppVO;
import com.mdy.dzs.game.domain.tournament.RoleTourAwardVO;
import com.mdy.dzs.game.domain.tournament.RoleTourRankVO;
import com.mdy.dzs.game.domain.tournament.RoleTournament;
import com.mdy.dzs.game.domain.tournament.RoleTournamentEnemy;
import com.mdy.dzs.game.domain.tournament.RoleTournamentShop;
import com.mdy.dzs.game.domain.tournament.RoleTournamentSimple;
import com.mdy.dzs.game.domain.tournament.TournamentBuyVO;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleException;
import com.mdy.dzs.game.exception.TournamentException;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.fight.main.FightCfg;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.sharp.container.biz.BizException;

/**
 * 比武
 * 
 * @author Administrator
 *
 */
public class TournamentActionImpl extends ApplicationAwareAction implements TournamentAction {
	public static final int 比武挑战 = 1;
	public static final int 复仇 = 2;
	public static final int 天榜挑战 = 3;

	@Override
	public CTournamentSelfInfoVO getInfo(String acc) throws BizException {
		// 判断等级
		Role doc = roleAO().queryExistAccount(acc);
		Open openData = cacheManager().getExistValueByKey(Open.class, 42);// key:system
		if (doc.getLevel() < openData.getLevel().get(0)) {
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_LEVEL_LIMIT,
					openData.getLevel().get(0));// 等级未开启
		}
		// 获取比武列表中个人信息
		RoleTournament roleTour = roleTournamentAO().query(doc.getId());
		// 刷新当天的数据
		roleTournamentAO().freshData(roleTour);
		// ============================================================================
		CTournamentSelfInfoVO selfInfo = new CTournamentSelfInfoVO();
		// 耐力值
		int resisVal = doc.getResisVal();
		// 排名
		int rank;
		// 下次刷新时间
		Date nextFleshTime = null;

		if (roleTour == null) {
			// 未进入过比武
			// 添加到列表中
			roleTour = new RoleTournament();
			roleTour.setRoleId(doc.getId());
			roleTour.setChallengeTimes(0);
			roleTour.setScore(cacheManager().getConfigBiwu("score").get(0));
			List<OppVO> list = new ArrayList<OppVO>();
			roleTour.setEnemyIds(list);
			roleTournamentAO().add(roleTour);
		}
		// 挑战次数
		int challengeTimes = roleTournamentAO().caclLeftTimes(roleTour);

		rank = roleTournamentAO().queryRank(doc.getId());

		// 对手列表
		List<COpponentInfo> opponents = null;

		// 对手列表
		List<OppVO> opps = new ArrayList<OppVO>();
		roleTour.setEnemyIds(opps);
		opponents = roleTournamentAO().getOpps(doc, roleTour);
		for (COpponentInfo cOpponentInfo : opponents) {
			OppVO oppVO = new OppVO();
			oppVO.setPos(cOpponentInfo.getPos());
			oppVO.setQuality(cOpponentInfo.getQuality());
			oppVO.setRoleId(cOpponentInfo.getRoleId());
			oppVO.setType(cOpponentInfo.getType());
			roleTour.getEnemyIds().add(oppVO);
		}
		Date date = new Date();
		roleTour.setNextFleshTime(new Date(date.getTime() + cacheManager().getConfigBiwu("refresh").get(0) * 1000));
		// 进入一次更新一次时间
		roleTournamentAO().update(roleTour);

		if (!roleTournamentAO().isActivity()) {
			selfInfo.setTop3(roleTournamentAO().getTop3());
		}

		nextFleshTime = roleTour.getNextFleshTime();

		selfInfo.setResisVal(resisVal);
		selfInfo.setChallengeTimes(challengeTimes);
		selfInfo.setNextFleshTime(nextFleshTime);
		selfInfo.setRank(rank);
		selfInfo.setOpponents(opponents);
		selfInfo.setScore(roleTour.getScore());
		int buyNum = roleTournamentAO().caclBuyTime(roleTour, doc);
		selfInfo.setBuy_num(buyNum);
		int cost = cacheManager().getConfigBiwu("buy_gold").get(0);
		selfInfo.setCost(cost);
		return selfInfo;
	}

	@Override
	public CRankVO rankList(String acc) throws BizException {
		Role role = roleAO().queryByAccount(acc);
		RoleTournament roleTour = roleTournamentAO().query(role.getId());
		// 刷新当天的数据
		roleTournamentAO().freshData(roleTour);
		CRankVO crankVO = new CRankVO();
		List<CTournamentRankVO> rankList = roleTournamentAO().getTop(10);
		crankVO.setList(rankList);
		RoleTournamentSimple rtSimple = new RoleTournamentSimple();
		int rank = roleTournamentAO().queryRank(role.getId());
		rtSimple.setRank(rank);
		rtSimple.setRoleId(role.getId());
		rtSimple.setScore(roleTour.getScore());
		rtSimple.setChallengeTimes(roleTournamentAO().caclLeftTimes(roleTour));
		rtSimple.setFaction(role.getFaction());
		crankVO.setSelf(rtSimple);
		return crankVO;
	}

	@Override
	public CEnemyVO enemyList(String acc) throws BizException {
		CEnemyVO enemyVO = new CEnemyVO();
		List<CTournamentEnemyVO> list = new ArrayList<CTournamentEnemyVO>();
		Role role = roleAO().queryByAccount(acc);
		RoleTournament roleTour = roleTournamentAO().query(role.getId());
		// 刷新当天的数据
		roleTournamentAO().freshData(roleTour);
		List<RoleTournamentEnemy> enemies = roleTournamentEnemyAO().queryListByRoleId(role.getId(),
				cacheManager().getConfigBiwu("enemy_num").get(0));
		for (RoleTournamentEnemy roleTournamentEnemy : enemies) {
			int enemyId = roleTournamentEnemy.getEnemyId();
			Role enemy = roleAO().queryById(enemyId);
			CTournamentEnemyVO ctEnemy = new CTournamentEnemyVO();
			ctEnemy.setRole_id(enemyId);
			ctEnemy.setAttack(enemy.getAttack());
			ctEnemy.setLevel(enemy.getLevel());
			ctEnemy.setName(enemy.getName());
			// 设置卡牌
			List<Integer> cardIds = enemy.getFmtCardAry();
			for (int j = 1; j < 4; j++) {
				RoleCard rcard = roleCardAO().queryById(cardIds.get(j));
				if (rcard == null)
					continue;
				CTournamentCardVO cardVO = new CTournamentCardVO();
				cardVO.setCardId(cardIds.get(j));
				cardVO.setCls(rcard.getCls());
				cardVO.setLevel(rcard.getLevel());
				cardVO.setResId(rcard.getResId());
				ctEnemy.getCards().add(cardVO);
			}
			list.add(ctEnemy);
		}
		enemyVO.setList(list);
		enemyVO.setChallengeTimes(roleTournamentAO().caclLeftTimes(roleTour));
		return enemyVO;
	}

	@Override
	public CExchangeList exchangeList(String acc) throws BizException {
		CExchangeList cex = new CExchangeList();
		List<CExchangeListVO> list = new ArrayList<CExchangeListVO>();
		Role role = roleAO().queryByAccount(acc);
		RoleTournament roleTour = roleTournamentAO().query(role.getId());
		// 刷新当天的数据
		roleTournamentAO().freshData(roleTour);
		List<RoleTournamentShop> shops = roleTournamentShopAO().queryListByRoleId(role.getId());
		List<BiwuShop> shopList = cacheManager().getBiwuShopList();
		Map<Integer, RoleTournamentShop> shopMap = new HashMap<Integer, RoleTournamentShop>();
		for (RoleTournamentShop roleTournamentShop : shops) {
			shopMap.put(roleTournamentShop.getItemId(), roleTournamentShop);
		}
		for (BiwuShop item : shopList) {
			RoleTournamentShop rtShop = shopMap.get(item.getId());
			int number = item.getNum1();
			if (rtShop != null) {
				number = item.getNum1() - rtShop.getExchangeTimes();
				if (number == 0 && rtShop.getType() == 1) {
					// 永久购买的类型
					continue;
				}
			}
			CExchangeListVO ceVO = new CExchangeListVO();
			int had = packetAO().getNumberByTypeId(role, item.getType(), item.getItem());
			ceVO.setItemId(item.getId());
			ceVO.setNumber(number);
			ceVO.setHad(had);
			list.add(ceVO);
		}
		cex.setList(list);
		cex.setHonor(role.getHonor());
		cex.setRoleId(role.getId());
		return cex;
	}

	@Override
	public CExchangeVO exchange(String acc, int itemId, int num) throws BizException {
		Role role = roleAO().queryByAccount(acc);
		RoleTournament roleTour = roleTournamentAO().query(role.getId());
		// 刷新当天的数据
		roleTournamentAO().freshData(roleTour);
		RoleTournamentShop rtShop = roleTournamentShopAO().query(role.getId(), itemId);
		BiwuShop item = cacheManager().getExistValueByKey(BiwuShop.class, itemId);
		// 等级检查
		if (role.getLevel() < item.getLevel()) {
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_NOT_LEVEL);
		}
		// 背包检查
		List<Integer> bagAry = Arrays.asList(8);
		if (item.getType() != 8) {
			Item itemData = cacheManager().getExistValueByKey(Item.class, item.getItem());
			bagAry = Arrays.asList(itemData.getBag());
		}
		// 检查
		List<PacketExtend> checkData = packetAO().checkBag(role, bagAry);
		CExchangeVO ceVO = new CExchangeVO();
		if (checkData.size() > 0) {
			ceVO.setGold(role.getGold());
			ceVO.setSilver(role.getSilver());
			ceVO.setPacketOut(checkData);
			return ceVO;
		}
		if (rtShop == null) {
			rtShop = new RoleTournamentShop();
			rtShop.setRoleId(role.getId());
			rtShop.setItemId(itemId);
			rtShop.setExchangeTimes(0);
			rtShop.setType(item.getType1());
			roleTournamentShopAO().add(rtShop);
		}
		// 判断次数
		if ((rtShop.getExchangeTimes() + num) > item.getNum1()) {
			// 抛出异常 超出购买次数
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_OUT_TIMES);
		}
		int price = item.getPrice() * num;
		packetAO().removeItemMustEnough(role, Packet.POS_ATTR, Packet.ATTR_荣誉, price, RoleItemLog.SYS_比武商店_购买, "");
		// 次数增加
		rtShop.setExchangeTimes(rtShop.getExchangeTimes() + num);
		// 更新数据库
		roleTournamentShopAO().update(rtShop);
		// 物品增加
		// 更新数据库
		packetAO().addItem(role, item.getType(), item.getItem(), item.getNum() * num, RoleItemLog.SYS_比武商店_购买, "");

		int number = item.getNum1() - rtShop.getExchangeTimes();
		int honor = role.getHonor();
		ceVO.setRoleId(role.getId());
		ceVO.setNumber(number);
		ceVO.setHonor(honor);
		ceVO.setItemId(itemId);
		ceVO.setGold(role.getGold());
		ceVO.setSilver(role.getSilver());
		return ceVO;
	}

	@Override
	public List<Serializable> check(String acc, int roleId, int type) throws BizException {
		Role self = roleAO().queryExistAccount(acc);
		List<PacketExtend> checkBag = packetAO().checkBag(self, 12);
		int res = 1;
		if (type == 天榜挑战) {
			int rank = roleTournamentAO().queryRank(self.getId());
			if (rank > cacheManager().getConfigBiwu("pk_rank").get(0)) {
				// 你的排名发生变化，请重新挑战
				res = 2;
			}
			int rankEnemy = roleTournamentAO().queryRank(roleId);
			if (rankEnemy > 10) {
				// 对方排名变化，请重新挑战
				res = 2;
			}
			if (rank < rankEnemy) {
				// 只能攻打比自己排名高的
				res = 2;
			}
		}
		if (checkBag.size() != 0) {
			res = 3;
		}
		return Arrays.asList(res, (Serializable) checkBag);
	}

	@Override
	public List<Serializable> challenge(String acc, int roleId, int type) throws BizException {
		// 判断是否是比武开启的时间段
		if (!roleTournamentAO().isActivity()) {// 活动未开启
			// 周六23点开始 到 周一8点
			// 抛出异常 活动未开启
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_NOT_OPEN);
		}
		// 挑战 type 为1 是比武挑战 2 是复仇 3是天榜挑战
		Role doc = roleAO().queryByAccount(acc);
		// 背包检查
		packetAO().checkBagException(doc, 12);
		if (doc.getId() == roleId) {
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_IS_SELF);
		}
		RoleTournament self = roleTournamentAO().query(doc.getId());
		// 刷新当天的数据
		roleTournamentAO().freshData(self);
		Role enemy = roleAO().queryById(roleId);
		int enemyType = OppVO.玩家;
		OppVO enemyOpp = null;
		if (type == 比武挑战) {
			// 判断是不是自己的对手
			// boolean isOpp = false;
			List<OppVO> opps = self.getEnemyIds();
			for (OppVO oppVO : opps) {
				if (roleId == oppVO.getRoleId()) {
					enemyType = oppVO.getType();
					enemyOpp = oppVO;
				}
			}
			if (enemyOpp == null) {
				throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_NOT_OPP);
			}
		}

		RoleTournament renemy = roleTournamentAO().query(enemy.getId());
		if (type == 复仇 && renemy != null && renemy.getScore() < 1000) {
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_SCORE_LOW);
		}
		// 判断挑战次数
		int challengeNum = roleTournamentAO().caclLeftTimes(self);
		if (challengeNum <= 0) {
			// 挑战次数已经用完
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_NO_TIMES);
		}
		if (type == 天榜挑战) {
			int rank = roleTournamentAO().queryRank(doc.getId());
			if (rank > cacheManager().getConfigBiwu("pk_rank").get(0)) {
				// 你的排名发生变化，请重新挑战
				throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_RANK_CHANGE);
			}
			int rankEnemy = roleTournamentAO().queryRank(renemy.getRoleId());
			if (rankEnemy > 10) {
				// 你的排名发生变化，请重新挑战
				throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_RANK_CHANGE);
			}
			if (rank < rankEnemy) {
				// 只能攻打比自己排名高的
				throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_RANK_ERROR);
			}
		}

		// 扣除耐力
		packetAO().removeItemMustEnough(doc, 0, 4, 2, RoleItemLog.SYS_比武挑战, "");

		FightCfg cfg = new FightCfg();
		cfg.setCaclAttrack(true);
		// init care
		FighterInfo srcInfo = fightAO().createFighterInfoByRole(doc);
		FighterInfo tgtInfo = fightAO().createFighterInfoByRole(enemy);
		if (cfg.isCaclAttrack()) {
			srcInfo.setAttrack(doc.getAttack());
			tgtInfo.setAttrack(enemy.getAttack());
		}

		// start battle
		FightMain main = new FightMain(srcInfo, tgtInfo, cfg);
		FightResult result = main.fight();
		Map<String, Object> rst = result.getMsg();
		// =============比武完的奖励=====================//
		int addHonor;
		int getExp;
		int rankType = 0;
		List<ProbItem> coinAry = new ArrayList<ProbItem>();
		List<ProbItem> rtnAry = new ArrayList<ProbItem>();
		int oldLv = doc.getLevel();
		int oldExp = doc.getExp();
		// ==============比武的邮件=====================//
		int textType = 0;
		// ===========================================//
		int score = 0;
		int reduceScore = 0;
		if (result.getWin() == 1) {// 当前玩家获胜
			// 耐力 次数 荣誉 积分
			// 攻击方
			if (null != self) {
				if (type == 比武挑战) {
					textType = Mail.MAIL_FUNCID_比武_挑战_DEFEND_FAIL;
					switch (enemyOpp.getQuality()) {
						case 3: {
							score = cacheManager().random(cacheManager().getConfigBiwu("high_win").get(0),
									cacheManager().getConfigBiwu("high_win").get(1));
							reduceScore = cacheManager().random(cacheManager().getConfigBiwu("high_fail").get(0),
									cacheManager().getConfigBiwu("high_fail").get(1));
							break;
						}
						case 2: {
							score = cacheManager().random(cacheManager().getConfigBiwu("mid_win").get(0),
									cacheManager().getConfigBiwu("mid_win").get(1));
							reduceScore = cacheManager().random(cacheManager().getConfigBiwu("mid_fail").get(0),
									cacheManager().getConfigBiwu("mid_fail").get(1));
							break;
						}
						case 1: {
							score = cacheManager().random(cacheManager().getConfigBiwu("low_win").get(0),
									cacheManager().getConfigBiwu("low_win").get(1));
							reduceScore = cacheManager().random(cacheManager().getConfigBiwu("low_fail").get(0),
									cacheManager().getConfigBiwu("low_fail").get(1));
							break;
						}
						default:
							break;
					}

				} else if (type == 复仇) {
					textType = Mail.MAIL_FUNCID_比武_复仇_DEFEND_FAIL;
					score = cacheManager().random(cacheManager().getConfigBiwu("revenge_win").get(0),
							cacheManager().getConfigBiwu("revenge_win").get(1));
					reduceScore = score;
				} else if (type == 天榜挑战) {
					textType = Mail.MAIL_FUNCID_比武_天榜_DEFEND_FAIL;
					score = cacheManager().random(cacheManager().getConfigBiwu("rank_win").get(0),
							cacheManager().getConfigBiwu("rank_win").get(1));
					reduceScore = score;
				}
				self.setScore(self.getScore() + score);
			}
			// ==============================翻牌子===================================//
			addHonor = cacheManager().getConfigBiwu("honor").get(0);
			getExp = doc.getLevel() * 2;
			packetAO().addItem(doc, 0, 13, addHonor, RoleItemLog.SYS_比武挑战, "");
			packetAO().addItem(doc, 0, 6, getExp, RoleItemLog.SYS_比武挑战, "");

			MoBan lvData = cacheManager().getExistValueByKey(MoBan.class, doc.getLevel());
			rtnAry.addAll(cacheManager().probGot(lvData.getProb1()));// 获得掉落
			rtnAry.addAll(cacheManager().probGot(lvData.getProb1()));
			rtnAry.addAll(cacheManager().probGot(lvData.getProb1()));
			ProbItem award = rtnAry.get(0);
			packetAO().addItem(doc, award.getT(), award.getId(), award.getN(), RoleItemLog.SYS_比武挑战, "");
			coinAry.add(new ProbItem(0, 13, addHonor));
			self.setWinTimes(self.getWinTimes() + 1);
			// ======================================================================//
			// 防守方
			if (renemy != null && enemyType == OppVO.玩家) {
				renemy.setScore(renemy.getScore() - reduceScore);
			}
			if (type == 复仇) {
				roleTournamentEnemyAO().delete(self.getRoleId(), enemy.getId());
			}
		} else {
			getExp = doc.getLevel() * 1;
			packetAO().addItem(doc, 0, 6, getExp, RoleItemLog.SYS_比武挑战, "");
			switch (type) {
				case 1:
					textType = Mail.MAIL_FUNCID_比武_挑战_DEFEND_WIN;
					break;
				case 2:
					textType = Mail.MAIL_FUNCID_比武_复仇_DEFEND_WIN;
					break;
				case 3:
					textType = Mail.MAIL_FUNCID_比武_天榜_DEFEND_WIN;
					break;
			}
		}
		if (oldExp != doc.getExp() || oldLv != doc.getLevel()) {
			coinAry.add(new ProbItem(0, 6, getExp));
		}
		self.setChallengeTimes(self.getChallengeTimes() + 1);
		self.setTotalTimes(self.getTotalTimes() + 1);

		if ((result.getWin() == 1) && (type == 比武挑战 || type == 天榜挑战)) {
			// 添加都仇人列表中
			// 敌人的仇人列表是否有自己
			if (self.getScore() >= 1000 && renemy != null && enemyType == OppVO.玩家) {
				//
				RoleTournamentEnemy roleTournamentEnemy = roleTournamentEnemyAO().queryByEnemy(enemy.getId(),
						doc.getId());
				if (roleTournamentEnemy != null) {
					// 更新时间
					roleTournamentEnemyAO().update(roleTournamentEnemy);
				} else {
					roleTournamentEnemy = new RoleTournamentEnemy();
					roleTournamentEnemy.setRoleId(enemy.getId());
					roleTournamentEnemy.setEnemyId(doc.getId());
					roleTournamentEnemyAO().add(roleTournamentEnemy);
				}
				// 判断敌人个数 如果超过50 删掉*
				roleTournamentEnemyAO().removeOverEnemy(enemy.getId(), cacheManager().getConfigBiwu("enemy_num").get(0),
						self);
			}
		}
		// 保存玩家 保存比武玩家 保存比武敌人
		int rank = 0;
		int enemyRank = 0;
		if (null != self) {
			rank = roleTournamentAO().updateScore(self.getRoleId(), self.getScore(), self.getChallengeTimes(),
					self.getWinTimes(), self.getTotalTimes());
		}
		if (null != renemy && enemyType == OppVO.玩家) {
			enemyRank = roleTournamentAO().updateScore(renemy.getRoleId(), renemy.getScore(),
					renemy.getChallengeTimes(), renemy.getWinTimes(), renemy.getTotalTimes());
			if (result.getWin() == 1) {
				// 发邮件
				mailAO().tournamentDefendFailScoreMail(renemy.getRoleId(), doc, textType, enemyRank, reduceScore);
			} else {
				mailAO().tournamentDefendWinMail(renemy.getRoleId(), doc, textType);
			}
		}
		Map<String, Object> infos = new HashMap<String, Object>();
		infos.put("resisVal", doc.getResisVal());
		infos.put("attack1", doc.getAttack());
		infos.put("attack2", enemy.getAttack());
		infos.put("name1", doc.getName());
		infos.put("name2", enemy.getName());
		infos.put("rankType", rankType);
		infos.put("newRank", rank);

		// ====================生成前端的战斗结果===============================//
		return Arrays.asList((Serializable) Arrays.asList(result.getWin()), (Serializable) Arrays.asList(rst),
				(Serializable) rtnAry, (Serializable) coinAry, (Serializable) infos, 1, (Serializable) doc.getLevel(),
				doc.getExp(), doc.getPopual(), score);
	}

	@Override
	public TournamentBuyVO buy(String acc, int num) throws BizException {
		if (!roleTournamentAO().isActivity()) {// 活动未开启
			// 周六23点开始 到 周一8点
			// 抛出异常 活动未开启
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_NOT_OPEN);
		}
		TournamentBuyVO tbVO = new TournamentBuyVO();
		Role doc = roleAO().queryByAccount(acc);
		RoleTournament roleTour = roleTournamentAO().query(doc.getId());
		// 刷新当天的数据
		roleTournamentAO().freshData(roleTour);
		// 判断次数
		// 一天可购买次数
		int buy_num = cacheManager().getConfigBiwu("buy_num").get(0);
		Vip vip = cacheManager().getExistValueByKey(Vip.class, Vip.SYSTEM_比武挑战购买次数);
		int vipCnt = vip.getVipByLevel(doc.getVip());
		if ((roleTour.getBuyTimes() + num) > (vipCnt + buy_num)) {
			// 抛出异常购买次数已经用完
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_BUYTIMES_OVER);
		}
		// 够买花费
		int cost = cacheManager().getConfigBiwu("buy_gold").get(0) * num;
		// 判断钱是否充足
		if (doc.getGold() < cost) {
			// 抛出异常 金币不足
			throw BaseException.getException(RoleException.EXCE_GOLD_NOT_ENOUGH, doc.getGold(), cost);
		}
		// 减掉金币
		packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, cost, RoleItemLog.SYS_比武_购买次数, "");
		// 次数加一
		roleTour.setBuyTimes(roleTour.getBuyTimes() + num);
		// 更新到数据库
		roleTournamentAO().update(roleTour);
		// 剩余次数
		int leftTimes = roleTournamentAO().caclLeftTimes(roleTour);
		tbVO.setRoleId(doc.getId());
		tbVO.setGold(doc.getGold());
		tbVO.setTimes(leftTimes);
		return tbVO;
	}

	@Override
	public CTournamentSelfInfoVO refresh(String acc) throws BizException {
		if (!roleTournamentAO().isActivity()) {// 活动未开启
			// 周六23点开始 到 周一8点
			// 抛出异常 活动未开启
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_NOT_OPEN);
		}
		// 判断等级
		Role doc = roleAO().queryExistAccount(acc);
		CTournamentSelfInfoVO selfInfo = new CTournamentSelfInfoVO();
		// 获取比武列表中个人信息
		RoleTournament roleTour = roleTournamentAO().query(doc.getId());
		// 刷新当天的数据
		roleTournamentAO().freshData(roleTour);
		// 下次刷新时间
		Date nextFleshTime = roleTour.getNextFleshTime();
		Date date = new Date();
		if ((date.getTime() + cacheManager().getConfigBiwu("refresh").get(0) * 1000) < nextFleshTime.getTime()) {
			// 抛出异常 刷新时间未到
			throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_NOT_REFRESH);
		}
		// 对手列表
		List<COpponentInfo> opponents = roleTournamentAO().getOpps(doc, roleTour);
		List<OppVO> opps = new ArrayList<OppVO>();
		roleTour.setEnemyIds(opps);
		for (COpponentInfo cOpponentInfo : opponents) {
			OppVO oppVO = new OppVO();
			oppVO.setPos(cOpponentInfo.getPos());
			oppVO.setQuality(cOpponentInfo.getQuality());
			oppVO.setRoleId(cOpponentInfo.getRoleId());
			oppVO.setType(cOpponentInfo.getType());
			roleTour.getEnemyIds().add(oppVO);
		}
		date = new Date();
		roleTour.setNextFleshTime(new Date(date.getTime() + cacheManager().getConfigBiwu("refresh").get(0) * 1000));
		roleTournamentAO().update(roleTour);

		selfInfo.setNextFleshTime(nextFleshTime);
		selfInfo.setOpponents(opponents);
		return selfInfo;
	}

	@Override
	public void award() throws BizException {
		List<RoleTourRankVO> list = roleTournamentAO().queryListByScore();
		List<RoleTourAwardVO> roleList = roleTournamentAO().award(list);
		for (RoleTourAwardVO roleTourAwardVO : roleList) {
			giftCenterAO().sendTournamentGift(roleTourAwardVO);
			mailAO().tournamentRankAwardMail(roleTourAwardVO.getRoleId(), roleTourAwardVO);
		}
	}

	@Override
	public void resetRank() {
		// 清空积分到某个数据
		int number = cacheManager().getConfigBiwu("score").get(0);
		roleTournamentAO().clearScore(number);
	}

	@Override
	public void resetExchange() {
		List<Integer> types = new ArrayList<Integer>();
		types.add(2);
		types.add(3);
		roleTournamentShopAO().deleteAll(types);
	}

	@Override
	public void resetEnemy() {
		roleTournamentEnemyAO().deleteAll();
	}

	@Override
	public void insertData() {
		roleTournamentAO().insertData();
	}
}
