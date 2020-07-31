package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.mission.ActivityMissionProperty;
import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.data.domain.sword.Lunjian;
import com.mdy.dzs.data.domain.sword.LunjianProb;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.SwordfightAction;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.swordfight.ClientAwardVO;
import com.mdy.dzs.game.domain.swordfight.ClientEnterSwordVO;
import com.mdy.dzs.game.domain.swordfight.ClientSwordCardVO;
import com.mdy.dzs.game.domain.swordfight.ClientSwordRoleVO;
import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.dzs.game.domain.swordfight.SwordRole;
import com.mdy.dzs.game.domain.swordfight.SwordRoleCard;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.SwordfightException;
import com.mdy.dzs.game.fight.domain.FightEndCardVO;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.fight.main.FightCfg;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

public class SwordfightActionImpl extends ApplicationAwareAction implements SwordfightAction {

	final static Logger logger = LoggerFactory.get(SwordfightActionImpl.class);

	@Override
	/**
	 * 需要更新数据库
	 */
	public ClientEnterSwordVO enterSword(String acc) throws BizException {
		/* 30级开启 */
		Role doc = roleAO().queryExistAccount(acc);
		Open openData = cacheManager().getExistValueByKey(Open.class, 37);// key:system
		if (doc.getLevel() < openData.getLevel().get(0)) {
			throw BaseException.getException(SwordfightException.EXCE_SWORD_LEVEL_LIMIT, openData.getLevel().get(0));// 等级未开启
		}

		// 先查找数据库是否有本人 从数据库中查找到此人
		Date now = new Date();
		SwordRole selfRole = swordRoleAO().queryBySelfAccount(acc, acc);
		if (selfRole == null) {
			selfRole = swordRoleAO().getSwordRole(doc, doc, -1);
			selfRole.setCombat(doc.getMaxAttack());
			// 将本人的数据加入到数据库
			swordRoleAO().add(selfRole);
		}
		if (selfRole.getEnterTime() != null) {
			if (!DateUtil.isToday(selfRole.getEnterTime())) {
				// 当天第一次进入 更新重置的次数
				selfRole.setEnterTime(now);
				selfRole.setResetCnt(0);
				selfRole.setGoldResetCnt(0);
				swordRoleAO().update(selfRole);
			}

		}
		// ============================处理本人20级以上的卡=====================================//
		List<ClientSwordCardVO> cards = new ArrayList<ClientSwordCardVO>();
		List<RoleCard> rcList = roleCardAO().queryListByLevel(doc.getId(), 20);
		for (int k = 0; k < rcList.size(); k++) {
			RoleCard rc = rcList.get(k);
			ClientSwordCardVO cscVO = swordRoleAO().getClentSwordCard(rc, doc);
			cards.add(cscVO);
		}
		// 根据库中已有的这些人的卡牌 设置血量
		List<SwordRoleCard> srcList = swordRoleCardAO().queryByAccount(acc, 0);
		List<SwordRoleCard> srcHave = new ArrayList<SwordRoleCard>();// 保存已存在的卡 ，将不存在的卡移除
		for (int i = 0; i < srcList.size(); i++) {
			SwordRoleCard src = srcList.get(i);
			for (Iterator<ClientSwordCardVO> iterator = cards.iterator(); iterator.hasNext();) {
				ClientSwordCardVO cscVO = (ClientSwordCardVO) iterator.next();
				if (src.getRole_card_id() == cscVO.getId()) {
					int initLife = cscVO.getInitLife();
					cscVO.setLife((int) (src.getLifeRate() * 0.0001 * initLife));
					srcHave.add(src);
				} else if (src.getResId() == cscVO.getCardId()) {
					// 去掉相同cardId的卡牌
					iterator.remove();
				}
			}
		}
		// 移除数据库不存在的卡
		// for (int m = 0; m < srcList.size(); m++) {
		// SwordRoleCard removeSrc = srcList.get(m);
		// if( srcHave.indexOf(removeSrc) == -1){
		// //如果不存在 那么从数据库中移除
		// swordRoleCardAO().delete(removeSrc);
		// }
		// }
		// ==============================================================================//

		// ==============================处理15个敌人的数据==================================//
		List<ClientSwordRoleVO> enemies = new ArrayList<ClientSwordRoleVO>();
		List<SwordRole> enemySRList = swordRoleAO().queryEmemies(acc);
		if (enemySRList != null && enemySRList.size() > 0) {
			for (int n = 0; n < enemySRList.size(); n++) {
				SwordRole nsr = enemySRList.get(n);
				ClientSwordRoleVO ncsrVO = swordRoleAO().getClientSwordRole(nsr);
				// 查询敌人卡牌
				List<SwordRoleCard> nsrcList = swordRoleCardAO().queryBySwordRoleId(nsr.getId());
				// 给客户端的卡牌list
				List<ClientSwordCardVO> ncscList = new ArrayList<ClientSwordCardVO>();
				for (int p = 0; p < nsrcList.size(); p++) {
					ClientSwordCardVO cscVO = swordRoleCardAO().getClientSwordCard(nsrcList.get(p));
					ncscList.add(cscVO);
				}
				ncsrVO.setCards(ncscList);
				enemies.add(ncsrVO);
			}
		} else {
			// 查询数据库 生成15个敌人 根据设定表找到符合条件的数据
			List<Lunjian> lunjians = cacheManager().getLunjianList();
			enemies = swordRoleAO().getEnemies(lunjians, doc);
		}
		if (enemies.size() < 15) {
			throw BaseException.getException(SwordfightException.EXCE_SWORD_CARDS_NOT_ENOUGH);// 人数不足
		}
		// ==============================================================================//
		ClientEnterSwordVO cesVO = swordRoleAO().getClientEnterSword(doc, selfRole, enemies, cards);
		return cesVO;
	}

	@Override
	public ClientEnterSwordVO reset(String acc, int gold) throws BizException {
		Role doc = roleAO().queryByAccount(acc);
		SwordRole sr = swordRoleAO().queryBySelfAccount(acc, acc);
		if (!DateUtil.isToday(sr.getEnterTime())) {
			// 当天第一次进入 更新重置的次数
			sr.setEnterTime(new Date());
			sr.setResetCnt(0);
			sr.setGoldResetCnt(0);
			swordRoleAO().update(sr);
		}
		if (sr.getResetCnt() == 0) {
			gold = 0;
		}
		if (sr.getFloor() == -1) {
			// 抛出异常 已经是最新的玩家
			throw BaseException.getException(SwordfightException.EXCE_SWORD_RESET_ERROR);
		}
		// 判断次数
		Vip vip = cacheManager().getExistValueByKey(Vip.class, Vip.SYSTEM_论剑每日购买次数);
		int vipCnt = vip.getVipByLevel(doc.getVip());
		if (gold > 0) {
			if (Constants.swordFreeResetCnt - sr.getResetCnt() > 0) {
				// 抛出异常 可以免费重置 不必元宝重置
				throw BaseException.getException(SwordfightException.EXCE_SWORD_FREE_RESET_ERROR);
			} else {
				if (vipCnt - sr.getGoldResetCnt() <= 0) {
					// 抛出异常 元宝重置次数已经用完
					throw BaseException.getException(SwordfightException.EXCE_SWORD_GOLD_RESET_ERROR);
				}
			}
		} else if (sr.getResetCnt() >= Constants.swordFreeResetCnt) {
			// 抛出异常 免费重置已经用完
			throw BaseException.getGlobalException("resetcnt err");
		}
		boolean canReset = true;
		// 验证钱数
		if (gold > 0) {
			int goldSvr = (sr.getGoldResetCnt() + 1) * Constants.swordVipBuyBaseGold;
			if (gold != goldSvr) {
				// 抛出异常 元宝数错误
				throw BaseException.getException(SwordfightException.EXCE_SWORD_GOLD_NUM_ERROR, goldSvr);
			}
			int goldSelf = doc.getGold();
			if (gold > goldSelf) {
				// 抛出异常 元宝不足 不能进行重置
				canReset = false;
				throw BaseException.getException(SwordfightException.EXCE_SWORD_GOLD_NOT_ENOUGH, goldSvr);
			}
		}
		// ======================================================开始重置
		if (canReset) {
			// 可以进行重置
			List<SwordRole> srList = swordRoleAO().queryEmemies(acc);
			for (int j = 0; j < srList.size(); j++) {
				SwordRole srVO = srList.get(j);
				// 先删除论剑的卡
				swordRoleCardAO().deleteBySwordRoleId(srVO.getId());
				// 然后删除论剑的敌人
				swordRoleAO().delete(srVO);
			}
		}
		// 重新生成敌人和卡 存入数据库
		List<ClientSwordRoleVO> enemies = new ArrayList<ClientSwordRoleVO>();
		List<Lunjian> lunjians = cacheManager().getLunjianList();
		enemies = swordRoleAO().getEnemies(lunjians, doc);
		if (enemies.size() < 15) {
			throw BaseException.getException(SwordfightException.EXCE_SWORD_CARDS_NOT_ENOUGH);// 人数不足
		}
		// 查找本人20级以上的所有卡
		List<ClientSwordCardVO> cards = new ArrayList<ClientSwordCardVO>();
		List<RoleCard> rcList = roleCardAO().queryListByLevel(doc.getId(), 20);
		for (int k = 0; k < rcList.size(); k++) {
			RoleCard rc = rcList.get(k);
			Card rcData = cacheManager().getExistValueByKey(Card.class, rc.getResId());
			ClientSwordCardVO cscVO = new ClientSwordCardVO();
			cscVO.setCardId(rc.getResId());
			cscVO.setCls(rc.getCls());
			cscVO.setLevel(rc.getLevel());
			Map<Integer, Integer> propMaps = roleAO().calcCardProps(doc, rc);
			int initLife = swordRoleAO().caclLife(rcData.getBase(), rcData.getLead(), propMaps);
			cscVO.setInitLife(initLife);
			cscVO.setLife(initLife);
			cscVO.setPos(rc.getPos());
			cscVO.setStar(rc.getStar());
			cscVO.setId(rc.getId());
			cards.add(cscVO);
		}
		// 重置自己卡牌的血量 和 怒气值 移除论剑卡库中自己的数据
		// swordRoleCardAO().deleteBySwordRoleId(sr.getId());
		List<SwordRoleCard> srcList = swordRoleCardAO().queryByAccount(acc, 0);
		for (int m = 0; m < srcList.size(); m++) {
			SwordRoleCard removeSrc = srcList.get(m);
			swordRoleCardAO().delete(removeSrc);
		}

		// 重置论剑角色的数据
		if (gold > 0) {
			sr.setGoldResetCnt(sr.getGoldResetCnt() + 1);
			sr.setScale(0.5f);
		} else {
			sr.setResetCnt(sr.getResetCnt() + 1);
			sr.setScale(1.0f);
		}
		sr.setName(doc.getName());
		sr.setFloor(-1);
		List<Integer> resetAwards = new ArrayList<Integer>();
		sr.setAwards(resetAwards);
		swordRoleAO().update(sr);
		// ======================================================重置完成
		// 进行扣钱的操作
		if (gold > 0) {
			packetAO().removeItemMustEnough(doc, 0, 1, gold, RoleItemLog.SYS_论剑_重置, "");
		}
		// 任务
		missionExecAO().resetSword(doc);
		ClientEnterSwordVO cesVO = swordRoleAO().getClientEnterSword(doc, sr, enemies, cards);
		return cesVO;
	}

	@Override
	public List<Serializable> fight(String acc, int floor, List<SwordCard> fmt) throws BizException {
		Role doc = roleAO().queryByAccount(acc);
		SwordRole srSelf = swordRoleAO().queryBySelfAccount(doc.getAccount(), doc.getAccount());
		if (floor <= 0) {
			throw BaseException.getException(SwordfightException.EXCE_SWORD_FLOOR_ERROR);
		} else if (srSelf.getFloor() == -1 && floor != 1) {
			throw BaseException.getException(SwordfightException.EXCE_SWORD_FLOOR_ERROR);
		} else if (srSelf.getFloor() != -1 && floor != (srSelf.getFloor() + 1)) {
			throw BaseException.getException(SwordfightException.EXCE_SWORD_FLOOR_ERROR);
		}
		// 敌人卡牌的属性 生命 怒气等值 都要取库中的值
		SwordRole enemy = swordRoleAO().queryByFloor(acc, floor);
		List<SwordRoleCard> enemyList = swordRoleCardAO().queryBySwordRoleId(enemy.getId());
		// 判断敌人的卡牌 如果全部已死 则抛出异常
		boolean idDead = true;
		for (Iterator<SwordRoleCard> iterator = enemyList.iterator(); iterator.hasNext();) {
			SwordRoleCard swordRoleCard = (SwordRoleCard) iterator.next();
			if (swordRoleCard.getLifeRate() > 0) {
				idDead = false;
			} else {
				iterator.remove();
			}
		}
		if (idDead) {
			throw BaseException.getException(SwordfightException.EXCE_SWORD_CARDS_ALL_DEAD);
		}
		// 玩家准备战斗的卡牌
		List<SwordRoleCard> selfList = new ArrayList<SwordRoleCard>();
		for (int i = 0; i < fmt.size(); i++) {
			SwordCard sc = fmt.get(i);
			RoleCard rc = roleCardAO().queryById(sc.getId());
			// 查询论剑卡库中 选定的卡 获得卡库中的生命和怒气。根据当前卡的属性 计算战斗
			SwordRoleCard srcOne = swordRoleCardAO().queryByRoleCardId(rc.getId(), 0);
			if (null == srcOne) {
				srcOne = swordRoleAO().getSwordRoleCard(rc, doc, 0, srSelf.getId());
				srcOne.setPos(sc.getPos());
				swordRoleCardAO().add(srcOne);
			} else {
				// 库中已有的卡 将库中卡的血和怒气赋值给原卡 ，将卡片现在的属性、star和cls 赋给现在的卡
				srcOne = swordRoleAO().setLifeAndAnger(rc, doc, 0, srcOne);
				srcOne.setPos(sc.getPos());
			}
			// 计算附加属性值
			List<Prop> props = new ArrayList<Prop>();
			swordRoleAO().updateCardRelation(doc, rc);
			Map<Integer, Integer> propMaps = roleAO().calcCardProps(doc, rc, sc.getOrder());
			props = RoleAttackCalcManager.convProps(propMaps);
			srcOne.setProps(props);

			if (srcOne.getLifeRate() > 0) {
				selfList.add(srcOne);
			}
		}
		// 判断自己的卡牌
		if (selfList.size() <= 0) {
			throw BaseException.getException(SwordfightException.EXCE_SWORD_CARDS_ALL_DEAD);
		}
		// ==================战斗=======================//
		FighterInfo srcInfo = fightAO().createFighterInfoBySwordRoleCard(selfList);
		FighterInfo tgtInfo = fightAO().createFighterInfoBySwordRoleCard(enemyList);
		// init cfg
		FightCfg cfg = new FightCfg();
		cfg.setCalcCard(true);
		FightMain main = new FightMain(srcInfo, tgtInfo, cfg);
		FightResult result = main.fight();
		// ============================================//

		// 将战斗卡牌剩余的血量 怒气 存入数据库中
		List<FightEndCardVO> selfEndList = result.getStatistic().getSelfList();
		List<FightEndCardVO> tgtEndList = result.getStatistic().getTgtList();
		for (int i = 0; i < selfEndList.size(); i++) {
			FightEndCardVO fecVO = selfEndList.get(i);
			SwordRoleCard src = swordRoleCardAO().getSwordRoleCard(fecVO.getPos(), selfList);
			if (null != src) {
				src.setLifeRate(fecVO.getLifeRate());
				src.setAnger(fecVO.getAnger());
				swordRoleCardAO().updateLifeAnger(src);
			}
		}
		for (int i = 0; i < tgtEndList.size(); i++) {
			FightEndCardVO fecVO = tgtEndList.get(i);
			SwordRoleCard src = swordRoleCardAO().getSwordRoleCard(fecVO.getPos(), enemyList);
			if (src != null) {
				src.setLifeRate(fecVO.getLifeRate());
				src.setAnger(fecVO.getAnger());
				swordRoleCardAO().updateLifeAnger(src);
			}
		}
		// 30 回合 无胜负
		if (result.getWinState() != null && result.getWinState() == 3) {
			result.setWin(1);
		}
		// 1 赢 2 输
		if (result.getWin() == 1) {
			srSelf.setFloor(floor);
			// 任务
			missionExecAO().swordFight(doc, floor);
		} else if (result.getWin() == 2 && srSelf.getFloor() == -1) {
			srSelf.setFloor(0);
		}
		swordRoleAO().updateFloor(srSelf);
		// ====================生成前端的战斗结果===============================//
		int aa[] = { result.getWin() };
		Map<String, Object> rst = result.getMsg();
		List<ProbItem> rtnItemAry = new ArrayList<ProbItem>();
		List<ProbItem> rstAry = new ArrayList<ProbItem>();
		int[] lv = { 0 };
		int[] dsum = { 0 };
		return Arrays.asList(aa, (Serializable) Arrays.asList(rst), (Serializable) rtnItemAry, (Serializable) rstAry,
				(Serializable) lv, (Serializable) dsum);
	}

	@Override
	public ClientAwardVO award(String acc, int floor) throws BizException {
		ClientAwardVO caVO = new ClientAwardVO();
		SwordRole sr = swordRoleAO().queryBySelfAccount(acc, acc);
		if (sr.getFloor() < floor) {
			throw BaseException.getException(SwordfightException.EXCE_SWORD_AWARD_FLOOR_ERROR);
		}
		if (sr.getAwards().indexOf(floor) != -1) {
			throw BaseException.getException(SwordfightException.EXCE_SWORD_AWARD_HAVE_GOT);
		}
		Role doc = roleAO().queryExistAccount(acc);
		// 背包检查
		List<Integer> bagAry = Arrays.asList(1, 7);
		// 检查
		List<PacketExtend> checkData = packetAO().checkBag(doc, bagAry);
		caVO.setPacketOut(checkData);
		caVO.setSilver(doc.getSilver());
		caVO.setGold(doc.getGold());
		if (checkData.size() > 0) {
			return caVO;
		}
		// 如果空间充足 发放奖励
		LunjianProb lp = cacheManager().getExistValueByKey(LunjianProb.class, doc.getLevel());
		int probId = lp.getProbId((int) (sr.getFloor()));
		List<ProbItem> itemAry = cacheManager().probGot(probId);
		// 限时掉落
		List<ProbItem> extra = missionExecAO().probGotExtra(doc, probId);
		if (extra != null)
			itemAry.addAll(extra);

		// =======================================银币奖励单独添加
		LunjianProb slp1 = cacheManager().getExistValueByKey(LunjianProb.class, 1);
		LunjianProb slp2 = cacheManager().getExistValueByKey(LunjianProb.class, 2);
		// 系数1
		int sliver1 = slp1.getProbId((int) (sr.getFloor()));
		// 系数2
		int sliver2 = slp2.getProbId((int) (sr.getFloor()));
		// 公式 玩家等级*系数1+系数2

		int sliverNum = doc.getLevel() * sliver1 + sliver2;
		ProbItem sliverItem = new ProbItem(0, 2, sliverNum);
		itemAry.add(sliverItem);
		// ====================================================

		int silvers = missionAO().getActivityMissionCntByProperty(doc.getId(), ActivityMissionProperty.论剑_银币翻倍.value());
		int linshis = missionAO().getActivityMissionCntByProperty(doc.getId(), ActivityMissionProperty.论剑_灵石翻倍.value());
		// 货币类型的获得比例
		float scale = sr.getScale();

		for (ProbItem item : itemAry) {
			// if(item.getT()==Packet.POS_ATTR)
			switch (item.getId()) {
				case Packet.ATTR_银币:
					item.setN((int) (item.getN() * silvers * scale));
					break;
				case Packet.ATTR_灵石:
					item.setN((int) (item.getN() * linshis * scale));
					break;
				default:
					break;
			}
			packetAO().addItem(doc, item, RoleItemLog.SYS_论剑_领奖, "");
		}
		// 更新奖励
		sr.getAwards().add(floor);
		swordRoleAO().updateAwards(sr);

		RoleCard rc = roleCardAO().queryById(doc.getFmtMainCardID());
		caVO.setGold(doc.getGold());
		caVO.setSilver(doc.getSilver());
		caVO.setItemAry(itemAry);
		caVO.setName(doc.getName());
		caVO.setCls(rc.getCls());
		caVO.setStar(rc.getStar());
		return caVO;
	}

	@Override
	public int combat(String acc, List<SwordCard> fmt) throws BizException {
		// 根据当前属性 计算战斗力
		Role doc = roleAO().queryByAccount(acc);
		for (int i = 0; i < fmt.size(); i++) {
			SwordCard sc = fmt.get(i);
			RoleCard rc = roleCardAO().queryById(sc.getId());
			sc.setRc(rc);
		}
		int attrack = roleAO().battleCalcSword(doc, fmt);
		return attrack;
	}
}
