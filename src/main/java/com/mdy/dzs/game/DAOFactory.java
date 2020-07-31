package com.mdy.dzs.game;

import com.mdy.dzs.game.dao.ActivityConfigDAO;
import com.mdy.dzs.game.dao.ActivityExchangeDAO;
import com.mdy.dzs.game.dao.ActivityLimitShopDAO;
import com.mdy.dzs.game.dao.BossBattleDAO;
import com.mdy.dzs.game.dao.BossBattlePlayerDAO;
import com.mdy.dzs.game.dao.BroadcastDAO;
import com.mdy.dzs.game.dao.ChatDAO;
import com.mdy.dzs.game.dao.RoleActivityLimitShopDAO;
import com.mdy.dzs.game.dao.RoleActivityMazeDAO;
import com.mdy.dzs.game.dao.RoleActivityRouletteDAO;
import com.mdy.dzs.game.dao.RoleDartDAO;
import com.mdy.dzs.game.dao.MailDAO;
import com.mdy.dzs.game.dao.MissionDAO;
import com.mdy.dzs.game.dao.PacketDAO;
import com.mdy.dzs.game.dao.RoleActivityExchDAO;
import com.mdy.dzs.game.dao.RoleActivityGuessDAO;
import com.mdy.dzs.game.dao.RoleActivityHappyDAO;
import com.mdy.dzs.game.dao.RoleActivityLimitCardDAO;
import com.mdy.dzs.game.dao.RoleActivityMonthSignDAO;
import com.mdy.dzs.game.dao.RoleActivityTouziDAO;
import com.mdy.dzs.game.dao.RoleArenaDAO;
import com.mdy.dzs.game.dao.RoleBattleDAO;
import com.mdy.dzs.game.dao.RoleCardDAO;
import com.mdy.dzs.game.dao.RoleCardEffectDAO;
import com.mdy.dzs.game.dao.RoleChallengeBattleDAO;
import com.mdy.dzs.game.dao.RoleChannelDAO;
import com.mdy.dzs.game.dao.RoleDAO;
import com.mdy.dzs.game.dao.RoleDailyMissionDAO;
import com.mdy.dzs.game.dao.RoleEquipDAO;
import com.mdy.dzs.game.dao.RoleFriendDAO;
import com.mdy.dzs.game.dao.RoleGiftCenterDAO;
import com.mdy.dzs.game.dao.RoleGiftStatusDAO;
import com.mdy.dzs.game.dao.RoleGongDAO;
import com.mdy.dzs.game.dao.RoleItemLogDAO;
import com.mdy.dzs.game.dao.RoleLineupAidDAO;
import com.mdy.dzs.game.dao.RoleLineupCardAidDAO;
import com.mdy.dzs.game.dao.RoleMonthCardDAO;
import com.mdy.dzs.game.dao.RolePseudoDAO;
import com.mdy.dzs.game.dao.RoleRankDAO;
import com.mdy.dzs.game.dao.RoleRoadDAO;
import com.mdy.dzs.game.dao.RoleShenMiShopDAO;
import com.mdy.dzs.game.dao.RoleShopDAO;
import com.mdy.dzs.game.dao.RoleSnatchDAO;
import com.mdy.dzs.game.dao.RoleStatisticsDAO;
import com.mdy.dzs.game.dao.RoleTournamentEnemyDAO;
import com.mdy.dzs.game.dao.RoleTournamentShopDAO;
import com.mdy.dzs.game.dao.RoleUnionDAO;
import com.mdy.dzs.game.dao.RoleUnionFBDAO;
import com.mdy.dzs.game.dao.RoleUnionFBStateDAO;
import com.mdy.dzs.game.dao.RoleVipDAO;
import com.mdy.dzs.game.dao.RoleYuanDAO;
import com.mdy.dzs.game.dao.ShopDAO;
import com.mdy.dzs.game.dao.SwordRoleCardDAO;
import com.mdy.dzs.game.dao.SwordRoleDAO;
import com.mdy.dzs.game.dao.UnionApplyDAO;
import com.mdy.dzs.game.dao.UnionBossDAO;
import com.mdy.dzs.game.dao.UnionBossPlayerDAO;
import com.mdy.dzs.game.dao.UnionDAO;
import com.mdy.dzs.game.dao.UnionDynamicDAO;
import com.mdy.dzs.game.dao.UnionFBDAO;
import com.mdy.dzs.game.dao.UnionFBDynamicDAO;
import com.mdy.dzs.game.dao.UnionLogDAO;
import com.mdy.dzs.game.dao.UnionRoleShopDAO;
import com.mdy.dzs.game.dao.UnionShopDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleTournamentDAO;

public interface DAOFactory {

	RoleEquipDAO equipDAO();

	RoleDAO roleDAO();

	RoleCardDAO roleCardDAO();

	BroadcastDAO broadcastDAO();

	RoleGongDAO roleGongDAO();

	RoleYuanDAO roleYuanDAO();

	PacketDAO packetDAO();

	ShopDAO shopDAO();

	RoleChannelDAO roleChannelDAO();

	RoleChallengeBattleDAO roleChallengeBattleDAO();

	ChatDAO chatDAO();

	RoleArenaDAO roleArenaDAO();

	RoleGiftCenterDAO roleGiftCenterDAO();

	RoleGiftStatusDAO roleGiftStatusDAO();

	RoleRoadDAO roleRoadDAO();

	RoleVipDAO roleVipDAO();

	RoleCardEffectDAO roleCardEffectDAO();

	SwordRoleDAO swordRoleDAO();

	SwordRoleCardDAO swordRoleCardDAO();

	RoleActivityGuessDAO roleActivityGuessDAO();

	RoleMonthCardDAO roleMonthCardDAO();

	RoleItemLogDAO roleItemLogDAO();

	ActivityConfigDAO activityConfigDAO();

	RoleBattleDAO roleBattleDAO();

	RoleShenMiShopDAO roleShenMiShopDAO();

	RoleShopDAO roleShopDAO();

	RoleSnatchDAO roleSnatchDAO();

	BossBattleDAO bossBattleDAO();

	BossBattlePlayerDAO bossBattlePlayerDAO();

	RoleActivityHappyDAO roleActivityHappyDAO();

	RoleActivityLimitCardDAO roleActivityLimitCardDAO();

	RoleStatisticsDAO roleStatisticsDAO();

	RoleActivityMonthSignDAO roleActivityMonthSignDAO();

	RoleActivityTouziDAO roleActivityTouziDAO();

	MailDAO mailDAO();

	MissionDAO missionDAO();

	UnionApplyDAO unionApplyDAO();

	UnionDAO unionDAO();

	RoleUnionDAO roleUnoionDAO();

	UnionDynamicDAO unionDynamicDAO();

	UnionFBDAO unionFBDAO();

	RoleUnionFBDAO roleUnionFBDAO();

	RoleUnionFBStateDAO roleUnionFBStateDAO();

	UnionFBDynamicDAO unionFBDynamicDAO();

	UnionBossDAO unionBossDAO();

	UnionBossPlayerDAO unionBossPlayerDAO();

	RolePseudoDAO rolePseudoDAO();

	RoleFriendDAO roleFriendDAO();

	RoleDailyMissionDAO roleDailyMissionDAO();

	UnionShopDAO unionShopDAO();

	UnionLogDAO unionLogDAO();

	UnionRoleShopDAO unionRoleShopDAO();

	CacheRoleTournamentDAO roleTournamentDAO();

	RoleTournamentEnemyDAO roleTournamentEnemyDAO();

	RoleTournamentShopDAO roleTournamentShopDAO();

	RoleRankDAO roleRankDAO();

	RoleDartDAO dartDAO();

	RoleActivityRouletteDAO roleActivityRouletteDAO();

	RoleActivityExchDAO roleActivityExchDAO();

	ActivityExchangeDAO activityExchangeDAO();

	ActivityLimitShopDAO activityLimitShopDAO();

	RoleActivityLimitShopDAO roleActivityLimitShopDAO();

	RoleActivityMazeDAO roleActivityMazeDAO();

	RoleLineupAidDAO roleLineupAidDAO();

	RoleLineupCardAidDAO roleLineupCardAidDAO();
}
