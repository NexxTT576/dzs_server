/**
 * 
 */
package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.battle.Npc;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.challengebattle.ActivityBattle;
import com.mdy.dzs.data.domain.challengebattle.EliteBattle;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.ChallengeBattleAction;
import com.mdy.dzs.game.domain.BuyCntVO;
import com.mdy.dzs.game.domain.RoleLineupAid.RLineupVO;
import com.mdy.dzs.game.domain.RoleLineupAid.RoleLineupCardAid;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.challenge.ActDetailVO;
import com.mdy.dzs.game.domain.challenge.RoleActivityBattle;
import com.mdy.dzs.game.domain.challenge.RoleEliteBattle;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.ChallengeBattleException;
import com.mdy.dzs.game.fight.domain.FightEndCardVO;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.fight.main.FightCfg;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月8日 上午11:26:04
 */
public class ChallengeBattleActionImpl extends ApplicationAwareAction implements ChallengeBattleAction {
	private static final Logger logger = LoggerFactory.get(ChallengeBattleActionImpl.class);

	@Override
	public List<Serializable> queryEliteBattleStatus(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleEliteBattle elite = roleChallengeBattleAO().queryEliteBattleByRoleId(doc);

		List<PacketExtend> list = packetAO().checkBag(doc, Packet.CHECK_精英副本);

		int surplusCnt = roleChallengeBattleAO().getEliteBattleSurplusCnt(elite);
		int spend = roleChallengeBattleAO().getEliteBattleBuySpend(elite);
		Vip vip = cacheManager().getExistValueByKey(Vip.class, Vip.SYSTEM_精英副本购买次数);
		int limit = vip.getVipByLevel(doc.getVip());
		BuyCntVO vo = new BuyCntVO(surplusCnt, elite.getEliteBuyCnt(), limit, 0, 0, spend, doc.getGold());

		return Arrays.asList((Serializable) elite.getEliteBattleLv(), vo, elite.getEliteNxtLvState(),
				elite.getEliteNxtLvRevive(), (Serializable) list);
	}

	@Override
	public BuyCntVO buyEliteBattleCnt(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleEliteBattle elite = roleChallengeBattleAO().queryEliteBattleByRoleId(doc);
		int surplusCnt = roleChallengeBattleAO().getEliteBattleSurplusCnt(elite);
		int spend = roleChallengeBattleAO().getEliteBattleBuySpend(elite);
		Vip vip = cacheManager().getExistValueByKey(Vip.class, Vip.SYSTEM_精英副本购买次数);
		int limit = vip.getVipByLevel(doc.getVip());
		if (surplusCnt != 0 || elite.getEliteBuyCnt() >= limit) {
			throw BaseException.getException(ChallengeBattleException.EXCE_ACTIVITY_BATTLE_NOT_BUY_CNT);
		}
		elite.setEliteBuyCnt(elite.getEliteBuyCnt() + 1);
		packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, spend, RoleItemLog.SYS_挑战_精英副本_购买次数, "");
		spend = roleChallengeBattleAO().getEliteBattleBuySpend(elite);
		roleChallengeBattleAO().updateEliteBattle(elite);

		return new BuyCntVO(surplusCnt + 1, elite.getEliteBuyCnt(), limit, 0, 0, spend, doc.getGold());
	}

	@Override
	public List<Serializable> eliteBattle(String acc, int curLvId, int npcInd, boolean revive, boolean clear)
			throws BizException {
		npcInd -= 1;

		Role doc = roleAO().queryExistAccount(acc);
		RoleEliteBattle elite = roleChallengeBattleAO().queryEliteBattleByRoleId(doc);
		if (curLvId > (elite.getEliteBattleLv() + 1)) {
			throw BaseException.getException(ChallengeBattleException.EXCE_ELITE_BATTLE_ERROR_ID, curLvId);
		}

		EliteBattle eliteData = cacheManager().getExistValueByKey(EliteBattle.class, curLvId);

		if (elite.getEliteNxtLv() == 0) {
			elite.setEliteNxtLv(curLvId);
		}
		if (elite.getEliteNxtLv() != curLvId || clear) {
			roleChallengeBattleAO().clearEliteBattleStatus(elite, curLvId);
		}
		if (npcInd != elite.getEliteNxtLvState()) {
			// npc state
			// 1 0
			// 2 1
			// 3 2
			if (npcInd != 0) {
				throw BaseException.getException(ChallengeBattleException.EXCE_ELITE_BATTLE_ERROR_NPC, npcInd);
			} else {
				roleChallengeBattleAO().clearEliteBattleStatus(elite, curLvId);
			}
		}
		// day limit
		int surplusCnt = roleChallengeBattleAO().getEliteBattleSurplusCnt(elite);
		if (surplusCnt <= 0) {
			throw BaseException.getException(ChallengeBattleException.EXCE_ELITE_BATTLE_CNT_NOTENOUGH);
		}
		// init player
		List<Integer> eliteNxtLvDPos = elite.getEliteNxtLvDPos();

		// init npc
		List<Integer> npcGrpAry = eliteData.getNpc();
		int npcId = npcGrpAry.get(npcInd);
		List<Object> npcAry = Arrays.asList(null, null, null, null, null, null);
		Npc npcData = cacheManager().getExistValueByKey(Npc.class, npcId);
		int npcs[] = { 0, npcData.getNpc1(), npcData.getNpc2(), npcData.getNpc3(), npcData.getNpc4(), npcData.getNpc5(),
				npcData.getNpc6() };
		int dPos = 0;
		for (int i = 1; i <= 6; i++) {
			// npc
			if (npcs[i] != 0) {
				Card cardData = cacheManager().getValueByKey(Card.class, npcs[i]);
				if (cardData != null) {
					npcAry.set(i - 1, cardData);
					if (dPos != 0)
						dPos = i;
				}
			}
		}
		FighterInfo srcInfo = fightAO().createFighterInfoByRole(doc, eliteNxtLvDPos, false, false);

		// init cfg
		FightCfg cfg = new FightCfg();
		cfg.setdPos(dPos);
		cfg.setNpcid(npcId);
		cfg.setCalcDiePos(true);
		// init fmt
		// battle
		FightMain main = new FightMain(srcInfo, npcAry, cfg);
		FightResult result = main.fight();

		// result
		List<Integer> rstAry = Arrays.asList(result.getWin());
		List<Map<String, Object>> recAry = Arrays.asList(result.getMsg());
		List<Integer> diePosAry = result.getStatistic().getDiePos()[0];
		// 有死亡位置
		if (diePosAry.size() > 0) {
			elite.setEliteNxtLvRevive(1);
			eliteNxtLvDPos.addAll(diePosAry);
			elite.setEliteNxtLvDPos(eliteNxtLvDPos);
		} else {
			elite.setEliteNxtLvRevive(0);
		}

		List<ProbItem> coinAry = new ArrayList<ProbItem>();
		List<ProbItem> awardAry = new ArrayList<ProbItem>();
		int awardSilver = 0;
		int awardSoul = 0;
		if (result.getWin() == 1) {
			if (npcInd == 2) {
				awardSilver = eliteData.getSilver();
				awardSoul = eliteData.getXiahun();
				awardAry = cacheManager().probGot(eliteData.getProb());
				List<ProbItem> extra = missionExecAO().probGotExtra(doc, eliteData.getProb());
				if (extra != null)
					awardAry.addAll(extra);
				for (ProbItem item : awardAry) {
					packetAO().addItem(doc, item.getT(), item.getId(), item.getN(), RoleItemLog.SYS_挑战_精英副本,
							String.valueOf(eliteData.getId()));
				}

				packetAO().addItem(doc, Packet.POS_ATTR, Packet.ATTR_银币, awardSilver, RoleItemLog.SYS_挑战_精英副本,
						String.valueOf(eliteData.getId()));
				packetAO().addItem(doc, Packet.POS_ATTR, Packet.ATTR_侠魂, awardSoul, RoleItemLog.SYS_挑战_精英副本,
						String.valueOf(eliteData.getId()));

				coinAry.add(new ProbItem(0, 2, awardSilver));
				coinAry.add(new ProbItem(0, 7, awardSoul));

				if (elite.getEliteBattleLv() < curLvId) {
					elite.setEliteBattleLv(curLvId);
				}
				roleChallengeBattleAO().useEliteBattleCnt(elite);
				roleChallengeBattleAO().clearEliteBattleStatus(elite, 0);

			} else {
				elite.setEliteNxtLvState(elite.getEliteNxtLvState() + 1);
			}
		} else {
			elite.setEliteNxtLvState(0);
			elite.setEliteNxtLvDPos(new ArrayList<Integer>());
		}
		roleChallengeBattleAO().updateEliteBattle(elite);

		// 更新任务
		if (result.getWin() == 1) {
			if (npcInd == 2) {
				// TASK
				missionExecAO().eliteBattle(doc);
			}
		}

		surplusCnt = roleChallengeBattleAO().getEliteBattleSurplusCnt(elite);

		int lvStat[] = { elite.getEliteBattleLv(), curLvId, elite.getEliteNxtLvState(), elite.getEliteNxtLvRevive(),
				surplusCnt };
		return Arrays.asList((Serializable) rstAry, (Serializable) recAry, (Serializable) awardAry,
				(Serializable) coinAry, lvStat, doc.getLevel(), doc.getExp());
	}

	@Override
	public List<Serializable> queryActivityBattleStatus(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityBattle actBattle = roleChallengeBattleAO().queryActivityBattleByRoleId(doc);
		Map<String, BuyCntVO> surplusCnts = roleChallengeBattleAO().getActivityBattleBuyCnts(doc, actBattle);
		Map<String, List<PacketExtend>> checkBags = roleChallengeBattleAO().checkActivityBattleBags(doc);

		return Arrays.asList((Serializable) surplusCnts, (Serializable) checkBags);
	}

	@Override
	public BuyCntVO buyActivityBattleCnt(String acc, int aid) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		String strAid = aid + "";
		RoleActivityBattle actBattle = roleChallengeBattleAO().queryActivityBattleByRoleId(doc);
		int itemId = Constants.activityBattleSpendItem[aid];
		int num = itemId == 0 ? 0 : packetAO().getNumberByTypeId(doc, Packet.POS_BAG, itemId);
		int surplusCnt = roleChallengeBattleAO().getActivityBattleSurplusCnt(doc, actBattle, aid);
		int limit = roleChallengeBattleAO().getActivityBattleLimit(doc, aid);
		Integer buyCnt = actBattle.getActBuyCnts().get(strAid);
		buyCnt = buyCnt == null ? 0 : buyCnt;

		ActivityBattle hdData = cacheManager().getExistValueByKey(ActivityBattle.class, aid);
		if (hdData.getIsbuy() == 0) {
			throw BaseException.getException(ChallengeBattleException.EXCE_ACTIVITY_CAN_NOT_BUY);
		}
		if (surplusCnt != 0 || buyCnt >= limit) {
			throw BaseException.getException(ChallengeBattleException.EXCE_ACTIVITY_BATTLE_NOT_BUY_CNT);
		}
		int spend = 0;
		if (aid == 1) {
			spend = (buyCnt + 1) * Constants.activityBattleSpendGold[aid];
		} else if (aid != 1 && hdData.getIsbuy() == 1) {
			spend = hdData.getBuyPrice().get(buyCnt);
		}

		actBattle.getActBuyCnts().put(strAid, buyCnt);
		buyCnt += 1;
		packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, spend, RoleItemLog.SYS_挑战_活动副本_购买次数, "");
		actBattle.getActBuyCnts().put(strAid, buyCnt);
		// spend = 0;
		// if(aid == 1){
		// spend = (buyCnt+1)*Constants.activityBattleSpendGold[aid];
		// }else if( aid != 1 && buyCnt < limit){
		// spend = hdData.getBuyPrice().get(buyCnt);
		// }
		roleChallengeBattleAO().updateActivityBattle(actBattle);

		BuyCntVO cntInfo = roleChallengeBattleAO().packetCntInfo(doc, actBattle, aid);
		cntInfo.setNum(num);
		return cntInfo;
	}

	@Override
	public List<PacketExtend> check(String acc, int aid) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		ActivityBattle hdData = cacheManager().getExistValueByKey(ActivityBattle.class, aid);
		// 检查背包
		List<PacketExtend> checkBag = packetAO().checkBag(doc, hdData.getBagCheckId());
		return checkBag;
	}

	@Override
	public List<Serializable> activityBattle(String acc, int aid, int npcInd, int sysId, int npcLv, List<SwordCard> fmt)
			throws BizException {
		// 判断等级 报异常
		npcInd -= 1;
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityBattle actBattle = roleChallengeBattleAO().queryActivityBattleByRoleId(doc);

		ActivityBattle hdData = cacheManager().getExistValueByKey(ActivityBattle.class, aid);
		if (aid != 1 && doc.getLevel() < hdData.getArrPrebattle().get(npcLv - 1)) {
			// 等级不足
			throw BaseException.getException(ChallengeBattleException.EXCE_ACTIVITY_LEVEL_NOT_ENOUGH);
		}
		if (fmt != null && fmt.size() <= 0) {
			throw BaseException.getException(ChallengeBattleException.EXCE_ACTIVITY_NO_PERSON);
		}
		packetAO().checkBagException(doc, hdData.getBagCheckId());
		String strAid = aid + "";
		int surplusCnt = roleChallengeBattleAO().getActivityBattleSurplusCnt(doc, actBattle, aid);
		int itemId = Constants.activityBattleSpendItem[aid];
		int num = itemId == 0 ? 0 : packetAO().getNumberByTypeId(doc, Packet.POS_BAG, itemId);
		if (0 == npcInd) {
			actBattle.getActPveSteps().put(strAid, 0);
			actBattle.setActNxtLvDPos(new ArrayList<Integer>());
			// 第一波验证次数
			if (surplusCnt == 0 && num == 0) {
				throw BaseException.getException(ChallengeBattleException.EXCE_ACTIVITY_BATTLE_CNT_NOTENOUGH);
			}

			if (surplusCnt == 0 && num > 0) {
				Integer useCnt = actBattle.getActUseItemCnts().get(strAid);
				useCnt = useCnt == null ? 0 : useCnt;
				num -= 1;
				packetAO().removeItemMustEnough(doc, Packet.POS_BAG, itemId, 1, RoleItemLog.SYS_挑战_活动副本_购买次数, "");
				actBattle.getActUseItemCnts().put(strAid, useCnt + 1);
			}
		}

		int step = actBattle.getActPveSteps().get(strAid);
		if (npcInd > step) {
			throw BaseException.getException(ChallengeBattleException.EXCE_ACTIVITY_BATTLE_ERROR_NPC, npcInd);
		}

		List<Integer> actNxtLvDPos = actBattle.getActNxtLvDPos();

		// init npc
		int curNpcId = 0;
		int npcLen = 0;
		if (aid == 1) {
			curNpcId = hdData.getNpc().get(npcInd);
		} else {
			curNpcId = hdData.getNpcid().get(npcLv - 1).get(npcInd);
			npcLen = hdData.getNpcid().get(npcLv - 1).size() - 1;
		}
		Npc npcData = cacheManager().getExistValueByKey(Npc.class, curNpcId);
		List<Integer> cardIds = new ArrayList<Integer>();
		List<Integer> poss = new ArrayList<Integer>();
		cardIds.add(npcData.getNpc1());
		cardIds.add(npcData.getNpc2());
		cardIds.add(npcData.getNpc3());
		cardIds.add(npcData.getNpc4());
		cardIds.add(npcData.getNpc5());
		cardIds.add(npcData.getNpc6());
		for (int i = 0; i < 6; i++) {
			poss.add(i + 1);
		}
		FighterInfo tgt;
		// init fmt
		FighterInfo src;
		List<RoleLineupCardAid> cards = null;
		if (aid == 1) {
			// 劫富济贫
			tgt = fightAO().createFighterInfoByCardIds(cardIds, poss, false, true);
			src = fightAO().createFighterInfoByRole(doc, actNxtLvDPos, true, false);
		} else {
			// 其他活动
			// npc加神通
			tgt = fightAO().createFighterInfoByCardIds(cardIds, poss, false, false);
			cards = roleLineupAidAO().getCards(doc, sysId, fmt);
			src = fightAO().createFighterInfoByRoleLineupCardAid(cards, actNxtLvDPos);
		}
		// init cfg
		FightCfg cfg = new FightCfg();
		cfg.setdPos(tgt.getPos());
		cfg.setNpcid(curNpcId);
		cfg.setCalcDiePos(true);
		cfg.setCalcCard(true);
		if (aid == 1) {
			cfg.setCalcDSum(true);
			cfg.setMaxBout(5);
		}
		// battle
		FightMain main = new FightMain(src, tgt, cfg);
		FightResult result = main.fight();

		List<Integer> diePosAry = result.getStatistic().getDiePos()[0];
		// 设置进度
		if (result.getWin() == 1) {
			step += 1;
		} else {
			step = 0;
		}
		if (aid == 1) {
			if (step >= hdData.getNpc().size()) {
				step = 0;
			}

		} else {
			if (step >= hdData.getNpcid().get(npcLv - 1).size()) {
				step = 0;
			}
		}
		// 还需继续
		if (step != 0) {
			// 有死亡位置
			if (diePosAry.size() > 0) {
				actNxtLvDPos.addAll(diePosAry);
			}
		}
		actBattle.getActPveSteps().put(strAid, step);
		actBattle.setActNxtLvDPos(actNxtLvDPos);
		// 发放奖励
		List<List<ProbItem>> award_coin = roleChallengeBattleAO().activityBattleAwards(doc, result, hdData, npcInd,
				npcLv, npcLen);
		roleChallengeBattleAO().updateActivityBattle(actBattle);

		// 任务
		switch (aid) {
			case ActivityBattle.AID_劫富济贫:
				missionExecAO().jfjpDamage(doc, result.getStatistic().getdSum());
				break;
		}

		if (result.getWin() == 1 && npcInd == npcLen || aid == 1) {
			roleChallengeBattleAO().useActivityBattleCnt(doc, strAid);
			missionExecAO().activityBattleCnt(doc, aid);
			surplusCnt = surplusCnt > 1 ? surplusCnt - 1 : 0;
			// 战斗结束
			if (aid != ActivityBattle.AID_劫富济贫) {
				List<FightEndCardVO> selfEndList = result.getStatistic().getSelfList();
				List<FightEndCardVO> tgtEndList = result.getStatistic().getTgtList();
				roleLineupAidAO().saveLineup(doc, sysId, selfEndList, tgtEndList, cards);
			}
		}

		int lvStat[] = { actBattle.getActPveSteps().get(strAid), surplusCnt };
		List<Integer> rstAry = Arrays.asList(result.getWin());
		List<Map<String, Object>> recAry = Arrays.asList(result.getMsg());
		return Arrays.asList((Serializable) rstAry, (Serializable) recAry, (Serializable) award_coin.get(0),
				(Serializable) award_coin.get(1), (Serializable) lvStat, (Serializable) result.getStatistic().getdSum(),
				num);

	}

	@Override
	public ActDetailVO actDetail(String acc, int aid, int sysId) throws BizException {
		Role doc = roleAO().queryByAccount(acc);
		ActDetailVO actDetailVO = new ActDetailVO();
		RLineupVO rlVO = roleLineupAidAO().getLineup(doc, sysId);
		actDetailVO.setCards(rlVO.getCards());
		actDetailVO.setLevel(doc.getLevel());
		List<SwordCard> fmt = rlVO.getFmt();
		int attrack = roleAO().battleCalcSword(doc, fmt);
		actDetailVO.setAttrack(attrack);
		return actDetailVO;
	}

	@Override
	public int save(String acc, int sysId, List<SwordCard> fmt) throws BizException {
		if (fmt.size() <= 0) {
			throw BaseException.getException(ChallengeBattleException.EXCE_ACTIVITY_NO_PERSON);
		}
		// 根据当前属性 计算战斗力
		Role doc = roleAO().queryByAccount(acc);
		for (int i = 0; i < fmt.size(); i++) {
			SwordCard sc = fmt.get(i);
			RoleCard rc = roleCardAO().queryById(sc.getId());
			sc.setRc(rc);
		}
		roleLineupAidAO().saveCurLineup(fmt, doc, sysId);
		int attrack = roleAO().battleCalcSword(doc, fmt);
		return attrack;
	}
}
