/**
 * 
 */
package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.arena.ArenaShop;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.robot.MoBan;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.ArenaAction;
import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.arena.RoleArenaRoleVO;
import com.mdy.dzs.game.domain.arena.RoleArenaShop;
import com.mdy.dzs.game.domain.arena.RoleArenaShopItemVO;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.ArenaException;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.fight.domain.FightResult;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.fight.main.FightCfg;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.util.JSONUtil;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月10日 下午2:16:57
 */
public class ArenaActionImpl extends ApplicationAwareAction implements ArenaAction {

	private static final Logger logger = LoggerFactory.get(ArenaActionImpl.class);

	@Override
	public List<Serializable> queryChallengeList(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleArena ra = roleArenaAO().queryByRoleId(doc.getId());
		List<Integer> cRankList = roleArenaAO().queryChallengeRanks(ra.getRank());
		List<RoleArenaRoleVO> listVO = new ArrayList<RoleArenaRoleVO>();
		for (int i = 0; i < cRankList.size(); i++) {
			RoleArena toRa = roleArenaAO().queryByRank(cRankList.get(i));
			if (toRa == null)
				continue;
			Role toDoc = roleAO().queryExistId(toRa.getRoleId());
			List<RoleCard> fmtCards = roleCardAO().queryLineupListByRoldId(toDoc.getId());
			listVO.add(new RoleArenaRoleVO(toDoc, doc, fmtCards, toRa));
		}

		Calendar now = Calendar.getInstance();
		int time = 0;
		int tType = 1;
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		if (hour == 22) {
			tType = 1; // 发奖
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			time = (int) ((calendar.getTimeInMillis() - now.getTimeInMillis()) / 1000);
		} else {
			tType = 2; // 挑战
			if (hour > 22) {
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				time = (int) ((calendar.getTimeInMillis() - now.getTimeInMillis()) / 1000) + 22 * 60 * 60 + 1;
			} else {
				calendar.set(Calendar.HOUR_OF_DAY, 22);
				time = (int) ((calendar.getTimeInMillis() - now.getTimeInMillis()) / 1000);
			}
		}
		return Arrays.asList((Serializable) listVO, ra.getRank(), doc.getPopual(), time, tType);
	}

	@Override
	public List<Serializable> queryChallengeCheck(String acc, String acc2, int rank2) throws BizException {
		Role self = roleAO().queryExistAccount(acc);
		List<PacketExtend> checkBag = packetAO().checkBag(self, 12);

		Role challenger = roleAO().queryExistAccount(acc2);
		RoleArena challengerArena = roleArenaAO().queryByRoleId(challenger.getId());
		int res = 1;
		if (rank2 != challengerArena.getRank()) {
			res = 2;
		}
		if (checkBag.size() != 0) {
			res = 3;
		}
		return Arrays.asList(res, (Serializable) checkBag);
	}

	@Override
	public List<Serializable> challenge(String acc, int challengeRank) throws BizException {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour == 22) {// 发奖中
			return Arrays.asList((Serializable) "", "", "", "", "", "", 3);
		}
		Role doc = roleAO().queryExistAccount(acc);
		packetAO().checkBagException(doc, 12);
		RoleArena arena = roleArenaAO().queryByRoleId(doc.getId());
		RoleArena challengerArena = roleArenaAO().queryByRank(challengeRank);
		if (doc.getId() == challengerArena.getRoleId()) {
			throw BaseException.getException(ArenaException.EXCE_ARENA_NOT_CHALLENGE_SELF);
		}
		Role challenger = roleAO().queryExistId(challengerArena.getRoleId());

		// 扣除耐力
		packetAO().removeItemMustEnough(doc, 0, 4, 2, RoleItemLog.SYS_竞技场_挑战, "");

		FightCfg cfg = new FightCfg();
		cfg.setCaclAttrack(true);
		// init care
		FighterInfo srcInfo = fightAO().createFighterInfoByRole(doc);
		FighterInfo tgtInfo = fightAO().createFighterInfoByRole(challenger);
		if (cfg.isCaclAttrack()) {
			srcInfo.setAttrack(doc.getAttack());
			tgtInfo.setAttrack(challenger.getAttack());
		}

		// start battle
		FightMain main = new FightMain(srcInfo, tgtInfo, cfg);
		FightResult result = main.fight();
		Map<String, Object> rst = result.getMsg();

		int addPopual;
		int getSilver;
		int getExp;
		int rankType = 0;
		List<ProbItem> coinAry = new ArrayList<ProbItem>();
		List<ProbItem> rtnAry = new ArrayList<ProbItem>();
		int oldLv = doc.getLevel();
		int oldExp = doc.getExp();
		if (result.getWin() == 1) {// 当前玩家获胜
			int srcOldRank = arena.getRank();
			int tgtOldRank = challengerArena.getRank();
			roleArenaAO().swapUpdate(arena, challengerArena);
			if (srcOldRank > tgtOldRank) {// 排名晋升 对方防守失败 名次下降
				rankType = 1;
				mailAO().arenaDefendFailRankDescMail(challenger.getId(), doc, srcOldRank);
			} else {// 对方防守失败 名次不变
				mailAO().arenaDefendFailRankHoldMail(challenger.getId(), doc);
			}
			// 排名低于自己，双方名次均无变化，声望+20
			addPopual = 20;
			getSilver = doc.getLevel() * 25;
			getExp = doc.getLevel() * 2;
			packetAO().addItem(doc, 0, 5, addPopual, RoleItemLog.SYS_竞技场_挑战, "");
			packetAO().addItem(doc, 0, 2, getSilver, RoleItemLog.SYS_竞技场_挑战, "");
			packetAO().addItem(doc, 0, 6, getExp, RoleItemLog.SYS_竞技场_挑战, "");

			MoBan lvData = cacheManager().getExistValueByKey(MoBan.class, doc.getLevel());
			rtnAry.addAll(cacheManager().probGot(lvData.getProb1()));// 获得掉落
			rtnAry.addAll(cacheManager().probGot(lvData.getProb1()));
			rtnAry.addAll(cacheManager().probGot(lvData.getProb1()));
			ProbItem award = rtnAry.get(0);
			packetAO().addItem(doc, award.getT(), award.getId(), award.getN(), RoleItemLog.SYS_竞技场_挑战, "");
			coinAry.add(new ProbItem(0, 2, getSilver));
		} else {
			mailAO().arenaDefendWinMail(challenger.getId(), doc);// 对方防守获胜邮件通知
			addPopual = 10;
			getExp = doc.getLevel() * 1;
			packetAO().addItem(doc, 0, 5, addPopual, RoleItemLog.SYS_竞技场_挑战, "");
			packetAO().addItem(doc, 0, 6, getExp, RoleItemLog.SYS_竞技场_挑战, "");

		}
		coinAry.add(new ProbItem(0, 5, addPopual));
		if (oldExp != doc.getExp() || oldLv != doc.getLevel()) {
			coinAry.add(new ProbItem(0, 6, getExp));
		}

		// TASK
		missionExecAO().changeRoleArena(doc, result.getWin() == 1);

		Map<String, Object> infos = new HashMap<String, Object>();
		infos.put("resisVal", doc.getResisVal());
		infos.put("attack1", doc.getAttack());
		infos.put("attack2", challenger.getAttack());
		infos.put("name1", doc.getName());
		infos.put("name2", challenger.getName());
		infos.put("rankType", rankType);
		infos.put("newRank", arena.getRank());

		return Arrays.asList((Serializable) Arrays.asList(result.getWin()), (Serializable) Arrays.asList(rst),
				(Serializable) rtnAry, (Serializable) coinAry, (Serializable) infos, 1, (Serializable) doc.getLevel(),
				doc.getExp(), doc.getPopual());
	}

	@Override
	public List<RoleArenaRoleVO> queryRankList(String acc) throws BizException {
		Role self = roleAO().queryExistAccount(acc);
		List<Integer> seeRanks = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<RoleArena> ranks = roleArenaAO().queryListByRanks(seeRanks);

		List<RoleArenaRoleVO> listVO = new ArrayList<RoleArenaRoleVO>();
		for (int i = 0; i < ranks.size(); i++) {
			RoleArena data = ranks.get(i);
			Role role = roleAO().queryExistId(data.getRoleId());
			List<RoleCard> fmtCards = roleCardAO().queryLineupListByRoldId(data.getRoleId());
			listVO.add(new RoleArenaRoleVO(role, self, fmtCards, data));
		}
		return listVO;
	}

	@Override
	public void sendRankAward() throws BizException {
		List<RoleArena> list = roleArenaAO().queryAwardRank();
		Map<Integer, Integer> roles = roleAO().queryArenaRewardRole(list);
		List<RoleArena> failed = new ArrayList<RoleArena>();
		Date now = new Date();
		for (RoleArena roleArena : list) {
			Integer level = roles.get(roleArena.getRoleId());
			if (level == null) {
				failed.add(roleArena);
				continue;
			}

			roleArena.setAwardPopual(roleArena.getAwardPopual());
			roleArena.setAwardSilver(roleArena.getAwardSilver() * level);
			giftCenterAO().sendArenaGift(roleArena, now);
			// 发奖邮件通知
			mailAO().arenaRankAwardMail(roleArena.getRoleId(), roleArena);
		}
		if (failed.size() > 0) {
			logger.error("now send arena award:" + JSONUtil.toJson(failed));
		}
	}

	@Override
	public List<Serializable> queryShopList(String acc, int shoptype) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleArenaShop rShop = roleArenaAO().queryShopByRoleId(doc.getId());// 角色商城购买记录

		// 清除每日购买列表
		if (!DateUtil.isToday(rShop.getUpdateTime())) {
			rShop.getDayPurchased().clear();
			roleArenaAO().updateShop(rShop);
		}
		List<RoleArenaShopItemVO> listAry = new ArrayList<RoleArenaShopItemVO>();
		List<ArenaShop> arenaShops = roleArenaAO().getArenaShops(); // 物品列表
		for (int i = 0; i < arenaShops.size(); i++) {
			if (arenaShops.get(i).getShoptype() == shoptype) {// 物品所售商店类型==商店类型
				ArenaShop ele = arenaShops.get(i);
				int num1 = 0;
				Integer perchasedData = roleArenaAO().getPurchased(rShop, ele);
				if (perchasedData >= ele.getNum1() && ele.getType1() == ArenaShop.TYPE_总次数限定) {
					continue;
				} else {
					num1 = ele.getNum1() - perchasedData;
				}
				int had = packetAO().getNumberByTypeId(doc, ele.getType(), ele.getItem());
				listAry.add(new RoleArenaShopItemVO(ele.getId(), num1, had));// {id:编号,num1:兑换上限,had:现有数量}
			}
		}
		int curNum = 0;
		if (shoptype == ArenaShop.TYPE_竞技场商店) {
			curNum = doc.getPopual();
		} else {
			curNum = doc.getLingShi();
		}

		return Arrays.asList((Serializable) listAry, curNum);
	}

	@Override
	public List<Serializable> exchange(String acc, int id, int num, int shoptype) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleArenaShop rShop = roleArenaAO().queryShopByRoleId(doc.getId());

		ArenaShop buyData = roleArenaAO().getArenaShopById(id);
		if (doc.getLevel() < buyData.getLevel()) {
			throw BaseException.getException(ArenaException.EXCE_ARENA_SHOP_LIMIT_LEVEL);
		}
		// 背包检查
		List<Integer> bagAry = Arrays.asList(8);
		if (buyData.getType() != 8) {
			Item itemData = cacheManager().getExistValueByKey(Item.class, buyData.getItem());
			bagAry = Arrays.asList(itemData.getBag());
		}
		// 检查
		List<PacketExtend> checkData = packetAO().checkBag(doc, bagAry);
		if (checkData.size() > 0) {
			return Arrays.asList(null, (Serializable) checkData, 0);
		}

		// 购买数，上限检查
		int num1 = buyData.getNum1() - roleArenaAO().getPurchased(rShop, buyData);
		if (num > num1) {
			throw BaseException.getException(ArenaException.EXCE_ARENA_SHOP_LIMIT_NUM);
		}

		int totalPrice = buyData.getPrice() * num;
		// 消耗 和 道具物品更新
		if (shoptype == ArenaShop.TYPE_竞技场商店) {
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_声望, totalPrice, RoleItemLog.SYS_竞技场_兑换,
					"" + buyData.getItem());

			packetAO().addItem(doc, buyData.getType(), buyData.getItem(), num * buyData.getNum(),
					RoleItemLog.SYS_竞技场_兑换, "" + buyData.getItem());
		} else {
			packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_灵石, totalPrice, RoleItemLog.SYS_论剑_商店兑换,
					"" + buyData.getItem());

			packetAO().addItem(doc, buyData.getType(), buyData.getItem(), num * buyData.getNum(),
					RoleItemLog.SYS_论剑_商店兑换, "" + buyData.getItem());
		}

		roleArenaAO().addPurchased(rShop, buyData, num);
		roleArenaAO().updateShop(rShop);
		num1 = buyData.getNum1() - roleArenaAO().getPurchased(rShop, buyData);

		int had = packetAO().getNumberByTypeId(doc, buyData.getType(), buyData.getItem());
		ProbItem item = new ProbItem(buyData.getType(), buyData.getItem(), num);

		int curNum = 0;
		if (shoptype == ArenaShop.TYPE_竞技场商店) {
			curNum = doc.getPopual();
		} else {
			curNum = doc.getLingShi();
		}
		return Arrays.asList(new RoleArenaShopItemVO(id, num1, had), curNum, (Serializable) Arrays.asList(item));
	}

}
