package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.biwu.BiwuJiangli;
import com.mdy.dzs.data.domain.biwu.Tournament;
import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.tournament.COpponentInfo;
import com.mdy.dzs.game.domain.tournament.CTournamentCardVO;
import com.mdy.dzs.game.domain.tournament.CTournamentRankVO;
import com.mdy.dzs.game.domain.tournament.OppVO;
import com.mdy.dzs.game.domain.tournament.RoleTourAwardVO;
import com.mdy.dzs.game.domain.tournament.RoleTourRankVO;
import com.mdy.dzs.game.domain.tournament.RoleTournament;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.TournamentException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

public class RoleTournamentAO extends BaseAO {

	private static int TYPE_VALUE = 1;
	private static int TYPE_PROB = 2;

	private CacheManager cacheManager;

	public RoleTournamentAO(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * 查询
	 */
	public RoleTournament query(int role_id) {
		return roleTournamentDAO().query(role_id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleTournament> queryList() {
		return roleTournamentDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param RoleTournament
	 */
	public void add(RoleTournament rt) {
		roleTournamentDAO().add(rt);
	}

	/**
	 * 更新
	 * 
	 * @param RoleTournament
	 */
	public void update(RoleTournament rt) {
		roleTournamentDAO().update(rt);
	}

	/**
	 * 更新分数 挑战次数
	 * 
	 * @param roleId
	 * @param score
	 * @param challengeTimes
	 */
	public int updateScore(int roleId, int score, int challengeTimes, int winTimes, int totalTimes) {
		return roleTournamentDAO().updateScore(roleId, score, challengeTimes, winTimes, totalTimes);
	}

	/**
	 * 删除
	 * 
	 * @param roleId
	 */
	public void delete(int roleId) {
		roleTournamentDAO().delete(roleId);
	}

	/**
	 * 清空所有积分
	 * 
	 * @param number
	 */
	public void clearScore(int number) {
		roleTournamentDAO().clearScore(number);
	}

	/**
	 * 查询排行
	 * 
	 * @param role_id
	 */
	public int queryRank(int role_id) {
		int rank = 0;
		// 根据积分获得倒序列表
		List<RoleTourRankVO> list = roleTournamentDAO().getRankList();
		// 排行就是位置(算法有待优化 TODO)
		for (int i = 0; i < list.size(); i++) {
			if (role_id == list.get(i).getRoleId()) {
				rank = i + 1;
			}
		}
		return rank;
	}

	/**
	 * 按照积分倒序查询
	 * 
	 * @return
	 */
	public List<RoleTourRankVO> queryListByScore() {
		return roleTournamentDAO().getRankList();
	}

	/**
	 * 取前topNumber名 数据
	 * 
	 * @param topNumber
	 * @return
	 */
	public List<CTournamentRankVO> getTop(int topNumber) {
		List<CTournamentRankVO> list = new ArrayList<CTournamentRankVO>();
		List<RoleTourRankVO> rtList = roleTournamentDAO().getRankList();
		int len = rtList.size() > 10 ? 10 : rtList.size();
		for (int i = 0; i < len; i++) {
			RoleTourRankVO roleTournament = rtList.get(i);
			Role role = roleDAO().queryById(roleTournament.getRoleId());
			CTournamentRankVO ctRankVO = new CTournamentRankVO();
			ctRankVO.setAttack(role.getAttack());
			ctRankVO.setLevel(role.getLevel());
			ctRankVO.setName(role.getName());
			ctRankVO.setScore(roleTournament.getScore());
			ctRankVO.setRole_id(roleTournament.getRoleId());
			ctRankVO.setRank(i + 1);
			ctRankVO.setFaction(role.getFaction());
			ctRankVO.setAcc(role.getAccount());
			// 设置卡牌
			List<Integer> cardIds = role.getFmtCardAry();
			for (int j = 1; j < 4; j++) {
				if (cardIds.get(j) == 0) {
					continue;
				}
				RoleCard rcard = roleCardDAO().query(cardIds.get(j));
				CTournamentCardVO cardVO = new CTournamentCardVO();
				cardVO.setCardId(cardIds.get(j));
				cardVO.setCls(rcard.getCls());
				cardVO.setLevel(rcard.getLevel());
				cardVO.setResId(rcard.getResId());
				ctRankVO.getCards().add(cardVO);
			}
			list.add(ctRankVO);
		}
		return list;
	}

	/**
	 * 获取对手列表
	 * 
	 * @param role_int
	 * @return
	 * @throws BizException
	 */
	public List<COpponentInfo> getOpps(Role doc, RoleTournament roleTour) throws BizException {
		List<COpponentInfo> list = new ArrayList<COpponentInfo>();
		// 真是玩家list
		List<RoleTournament> playerList = new ArrayList<RoleTournament>();
		// 机器人list
		List<Role> robotList = new ArrayList<Role>();
		Map<Integer, Object> posMap = new HashMap<Integer, Object>();
		Map<Integer, Integer> qualityMap = new HashMap<Integer, Integer>();

		// 生成对手
		for (int i = 0; i < 3; i++) {
			Tournament tData = cacheManager.getExistValueByKey(Tournament.class, (i + 1));
			Boolean isGetRobot = false;
			int quality = 1;
			// type 为1是固定值 2是概率
			// 根据积分匹配对手
			List<RoleTournament> rtList = getOpponentByScore(tData.getScore1(), roleTour.getScore(), playerList,
					roleTour, robotList);
			if (rtList.size() == 0) {
				rtList = getOpponentByScore(tData.getScore2(), roleTour.getScore(), playerList, roleTour, robotList);
				quality = tData.getScore2().get(4);
			} else {
				quality = tData.getScore1().get(4);
			}
			if (rtList.size() == 0) {
				rtList = getOpponentByScore(tData.getScore3(), roleTour.getScore(), playerList, roleTour, robotList);
				quality = tData.getScore3().get(4);
			}
			List<RoleTournament> cList = null;
			if (rtList.size() > 0) {
				// 根据战力匹配
				cList = getOpponentByAttack(rtList, tData.getAttack1(), doc.getMaxAttack());
				if (cList.size() == 0) {
					cList = getOpponentByAttack(rtList, tData.getAttack2(), doc.getMaxAttack());
				}
				if (cList.size() == 0) {
					cList = getOpponentByAttack(rtList, tData.getAttack3(), doc.getMaxAttack());
				}
				if (cList.size() > 0) {
					rtList = cList;
				}
				if (rtList.size() > 0) {
					// 随机一个对手
					int pos = cacheManager.random(0, rtList.size() - 1);
					RoleTournament roleTourRandom = rtList.get(pos);
					playerList.add(roleTourRandom);
					posMap.put(i, roleTourRandom);
				} else {
					// 根据战力匹配机器人
					isGetRobot = true;
				}
			} else {
				// 根据战力匹配机器人
				isGetRobot = true;
			}

			if (isGetRobot) {
				quality = 1;
				List<Role> oppRobotList = getOpponentByAttackRobot(tData.getAttack1(), doc.getMaxAttack(), robotList,
						doc, playerList);
				if (oppRobotList.size() == 0) {
					oppRobotList = getOpponentByAttackRobot(tData.getAttack2(), doc.getMaxAttack(), robotList, doc,
							playerList);
				}
				if (oppRobotList.size() == 0) {
					oppRobotList = getOpponentByAttackRobot(tData.getAttack3(), doc.getMaxAttack(), robotList, doc,
							playerList);
				}
				if (oppRobotList.size() > 0) {
					int random = cacheManager.random(0, oppRobotList.size() - 1);
					robotList.add(oppRobotList.get(random));
					posMap.put(i, oppRobotList.get(random));
				} else {
					// 抛出异常 没有随机到玩家
					throw BaseException.getException(TournamentException.EXCE_TOURNAMENT_NO_PLAYER);
				}
			}
			qualityMap.put(i, quality);
		}
		for (int j = 0; j < 3; j++) {
			Object player = posMap.get(j);
			if (player instanceof RoleTournament) {
				// 玩家
				Role role = roleDAO().queryById(((RoleTournament) player).getRoleId());
				COpponentInfo copp = createOpponent((RoleTournament) player, role);
				copp.setPos(j + 1);
				copp.setQuality(qualityMap.get(j));
				copp.setType(OppVO.玩家);
				copp.setFaction(role.getFaction());
				list.add(copp);
			} else if (player instanceof Role) {
				// 机器人
				COpponentInfo copp = createOpponentByRole((Role) player);
				copp.setPos(j + 1);
				copp.setQuality(1);
				copp.setType(OppVO.机器人);
				list.add(copp);
			}
		}
		return list;
	}

	/**
	 * 根据积分匹配真实玩家对手
	 * 
	 * @param scoreList
	 * @param score
	 * @param roles
	 * @param self
	 * @return
	 */
	private List<RoleTournament> getOpponentByScore(List<Integer> scoreList, int score, List<RoleTournament> roles,
			RoleTournament self, List<Role> robotList) {
		List<RoleTournament> list = new ArrayList<RoleTournament>();
		// type 为1是固定值 2是概率
		int minType = scoreList.get(0);
		int min = scoreList.get(1);
		int maxType = scoreList.get(2);
		int max = scoreList.get(3);
		int minValue = 0;
		int maxValue = 0;
		if (minType == TYPE_PROB) {
			minValue = (int) (score * 0.0001 * min);
			if (minValue < 1000)
				minValue = 1000;
		} else {
			minValue = min;
		}
		if (maxType == TYPE_PROB) {
			maxValue = (int) (score * 0.0001 * max);
			if (maxValue <= 1000)
				maxValue = 1001;
		} else {
			maxValue = max;
		}
		list = roleTournamentDAO().queryListByScore(roles, self, minValue, maxValue, robotList);
		return list;
	}

	/**
	 * 根据战力匹配真实玩家对手
	 * 
	 * @param rtList
	 * @param attackList
	 * @param attack
	 * @return
	 */
	private List<RoleTournament> getOpponentByAttack(List<RoleTournament> rtList, List<Integer> attackList,
			int attack) {
		List<RoleTournament> list = new ArrayList<RoleTournament>();
		// type 为1是固定值 2是概率
		int minType = attackList.get(0);
		int min = attackList.get(1);
		int maxType = attackList.get(2);
		int max = attackList.get(3);
		int minValue = 0;
		int maxValue = 0;
		if (minType == TYPE_PROB) {
			minValue = (int) (attack * 0.0001 * min);
		} else {
			minValue = min;
		}
		if (maxType == TYPE_PROB) {
			maxValue = (int) (attack * 0.0001 * max);
		} else {
			maxValue = max;
		}
		// 进行数据库查询
		if (max == 0) {
			for (RoleTournament roleTournament : rtList) {
				// 玩家信息
				Role role = roleDAO().queryById(roleTournament.getRoleId());
				if (role.getMaxAttack() > minValue) {
					list.add(roleTournament);
				}
			}
		} else {
			for (RoleTournament roleTournament : rtList) {
				// 玩家信息
				Role role = roleDAO().queryById(roleTournament.getRoleId());
				if (role.getMaxAttack() > minValue && role.getMaxAttack() <= maxValue) {
					list.add(roleTournament);
				}
			}

		}
		return list;
	}

	/**
	 * 生成对手信息
	 * 
	 * @param roleTour
	 * @param role
	 * @return
	 */
	private COpponentInfo createOpponent(RoleTournament roleTour, Role role) {
		COpponentInfo copp = new COpponentInfo();
		copp.setRoleId(role.getId());
		copp.setName(role.getName());
		copp.setLevel(role.getLevel());
		// 主卡的resId
		int cardId = role.getFmtMainCardID();
		RoleCard roleCard = roleCardDAO().query(cardId);
		copp.setCls(roleCard.getCls());
		copp.setLeadId(roleCard.getResId());
		return copp;
	}

	/**
	 * 用机器人生成对手
	 * 
	 * @param role
	 * @return
	 */
	public COpponentInfo createOpponentByRole(Role role) {
		COpponentInfo copp = new COpponentInfo();
		copp.setRoleId(role.getId());
		copp.setName(role.getName());
		copp.setLevel(role.getLevel());
		// 主卡的resId
		int cardId = role.getFmtMainCardID();
		RoleCard roleCard = roleCardDAO().query(cardId);
		copp.setLeadId(roleCard.getResId());
		copp.setCls(roleCard.getCls());
		copp.setFaction(role.getFaction());
		return copp;
	}

	/**
	 * 根据战力匹配机器人对手
	 * 
	 * @param attackList
	 * @param attack
	 * @param roles
	 * @param role
	 * @return
	 */
	private List<Role> getOpponentByAttackRobot(List<Integer> attackList, int attack, List<Role> roles, Role role,
			List<RoleTournament> players) {
		List<Role> list = new ArrayList<Role>();
		// type 为1是固定值 2是概率
		int minType = attackList.get(0);
		int min = attackList.get(1);
		int maxType = attackList.get(2);
		int max = attackList.get(3);
		int minValue = 0;
		int maxValue = 0;
		if (minType == TYPE_PROB) {
			minValue = (int) (attack * 0.0001 * min);
		} else {
			minValue = min;
		}
		if (maxType == TYPE_PROB) {
			maxValue = (int) (attack * 0.0001 * max);
		} else {
			maxValue = max;
		}
		list = roleDAO().queryByAttrack(roles, role, minValue, maxValue, players);
		return list;
	}

	/**
	 * 根据排行获得奖励
	 * 
	 * @param rank
	 * @return
	 */
	public List<RoleTourAwardVO> award(List<RoleTourRankVO> list) {

		List<RoleTourAwardVO> roleList = new ArrayList<RoleTourAwardVO>();
		List<BiwuJiangli> jiangli = cacheManager.getBiwuJiangliList();
		for (int i = 0; i < jiangli.size(); i++) {
			BiwuJiangli biwu = jiangli.get(i);
			int min, max;
			min = biwu.getMin() - 1;
			if (biwu.getMax() == 0) {
				max = list.size();
			} else {
				max = biwu.getMax();
			}
			for (int j = min; j < max; j++) {
				if (j >= list.size()) {
					continue;
				}
				List<GiftItem> giftList = new ArrayList<GiftItem>();
				RoleTourRankVO roleRankVO = list.get(j);
				Role role = roleDAO().queryById(roleRankVO.getRoleId());
				int silver = (int) (biwu.getFix() + biwu.getRatio() * role.getLevel());
				GiftItem item = new GiftItem(Packet.POS_BAG, Packet.ATTR_银币, silver);
				giftList.add(item);
				for (int k = 0; k < biwu.getRewardIds().size(); k++) {
					GiftItem gItem = new GiftItem(biwu.getRewardTypes().get(k), biwu.getRewardIds().get(k),
							biwu.getRewardNums().get(k));
					giftList.add(gItem);
				}
				RoleTourAwardVO roleAward = new RoleTourAwardVO();
				roleAward.setRoleId(roleRankVO.getRoleId());
				roleAward.setRank(j + 1);
				roleAward.setAwards(giftList);
				roleList.add(roleAward);
			}
		}
		return roleList;
	}

	public int caclLeftTimes(RoleTournament roleTour) {
		int leftTimes = 0;
		int free_num = cacheManager.getConfigBiwu("free_num").get(0);
		leftTimes = roleTour.getBuyTimes() + free_num - roleTour.getChallengeTimes();
		return leftTimes;
	}

	public int caclBuyTime(RoleTournament roleTour, Role role) throws BizException {
		int buyNum = 0;
		int buy_num = cacheManager.getConfigBiwu("buy_num").get(0);
		Vip vip = cacheManager.getExistValueByKey(Vip.class, Vip.SYSTEM_比武挑战购买次数);
		int vipCnt = vip.getVipByLevel(role.getVip());
		buyNum = vipCnt + buy_num - roleTour.getBuyTimes();
		return buyNum;
	}

	public Boolean isActivity() {
		boolean isAct = false;
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int day = getWeek(new Date());
		int startDay = cacheManager.getConfigBiwu("star_time").get(0);
		int startHour = cacheManager.getConfigBiwu("star_time").get(1);
		int endDay = cacheManager.getConfigBiwu("end_time").get(0);
		int endHour = cacheManager.getConfigBiwu("end_time").get(1);
		// if( day > 6 || (day == 1 && hour < 8) || (day == 6 && hour >= 23)){//活动未开启
		if (day > endDay || day < startDay || (day == startDay && hour < startHour)
				|| (day == endDay && hour >= endHour)) {// 活动未开启
			isAct = false;
		} else {
			isAct = true;
		}
		return isAct;
	}

	public List<COpponentInfo> getTop3() {
		List<COpponentInfo> opponents = new ArrayList<COpponentInfo>();
		List<RoleTourRankVO> rtList = roleTournamentDAO().getRankList();
		int len = rtList.size() > 3 ? 3 : rtList.size();
		for (int i = 0; i < len; i++) {
			RoleTourRankVO roleTournament = rtList.get(i);
			Role role = roleDAO().queryById(roleTournament.getRoleId());
			COpponentInfo copp = createOpponentByRole(role);
			copp.setPos(i + 1);
			copp.setQuality(i + 1);
			copp.setAcc(role.getAccount());
			copp.setFaction(role.getFaction());
			opponents.add(copp);
		}
		return opponents;
	}

	private int getWeek(Date date) {
		int[] weeks = { 7, 1, 2, 3, 4, 5, 6 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}

	/**
	 * 插入测试数据
	 */
	public void insertData() {
		List<RoleTournament> rtList = roleTournamentDAO().queryList();
		for (int i = 0; i < rtList.size(); i++) {
			RoleTournament rt = rtList.get(i);
			rt.setScore(1000 + i * 20);
			roleTournamentDAO().update(rt);
		}

		// String s = "(";
		// for (Iterator iterator = rtList.iterator(); iterator.hasNext();) {
		// RoleTournament rt = (RoleTournament)iterator.next();
		// s += rt.getRoleId() +",";
		// }
		// s = s.substring(0, s.length()-1);
		// s += ")";
		// List<Role> roleList = roleDAO().queryBetweenLevel(s, 30, 50);
		// for (Role role : roleList) {
		// RoleTournament rtNew = new RoleTournament();
		// rtNew.setTotalTimes(1);
		// rtNew.setRoleId(role.getId());
		// rtNew.setScore(1010);
		// rtNew.setWinTimes(1);
		// rtNew.setEnemyIds(rtList.get(0).getEnemyIds());
		// rtNew.setNextFleshTime(new Date());
		// roleTournamentDAO().add(rtNew);
		// }
	}

	public void freshData(RoleTournament roleTour) {
		if (roleTour == null)
			return;
		if (roleTour.getFleshTime() == null || !DateUtil.isToday(roleTour.getFleshTime())) {
			// 刷新当天的挑战次数 和 当天的购买次数
			roleTour.setBuyTimes(0);
			roleTour.setChallengeTimes(0);
			roleTour.setFleshTime(new Date());
			roleTournamentDAO().update(roleTour);
			// 刷新当前的兑换数据
			List<Integer> types = new ArrayList<Integer>();
			types.add(3);
			roleTournamentShopDAO().deleteOne(types, roleTour.getRoleId());
		}
	}
}
