package com.mdy.dzs.game.ao;

import com.mdy.dzs.game.DAOFactory;
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
import com.mdy.dzs.game.dao.RoleActivityExchDAO;
import com.mdy.dzs.game.dao.RoleDartDAO;
import com.mdy.dzs.game.dao.MailDAO;
import com.mdy.dzs.game.dao.MissionDAO;
import com.mdy.dzs.game.dao.PacketDAO;
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

/**
 */
public abstract class BaseAO {
	private DAOFactory daoFactory;

	/**
	 * @return the daoFactory
	 */
	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	//
	public void start() {
	}

	/**
	 * @param daoFactory the daoFactory to set
	 */
	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public RoleDAO roleDAO() {
		return daoFactory.roleDAO();
	}

	/**
	 * DAO
	 * 
	 * @return
	 */
	public RoleEquipDAO equipDAO() {
		return daoFactory.equipDAO();
	}

	public RoleCardDAO roleCardDAO() {
		return daoFactory.roleCardDAO();
	}

	public RoleGongDAO roleGongDAO() {
		return daoFactory.roleGongDAO();
	}

	public RoleYuanDAO roleYuanDAO() {
		return daoFactory.roleYuanDAO();
	}

	public PacketDAO packetDAO() {
		return daoFactory.packetDAO();
	}

	public BroadcastDAO broadcastDAO() {
		return daoFactory.broadcastDAO();
	}

	public ShopDAO shopDAO() {
		return daoFactory.shopDAO();
	}

	public RoleChannelDAO roleChannelDAO() {
		return daoFactory.roleChannelDAO();
	}

	public RoleChallengeBattleDAO roleChallengeBattleDAO() {
		return daoFactory.roleChallengeBattleDAO();
	}

	public ChatDAO chatDAO() {
		return daoFactory.chatDAO();
	}

	public RoleArenaDAO roleArenaDAO() {
		return daoFactory.roleArenaDAO();
	}

	public RoleGiftCenterDAO roleGiftCenterDAO() {
		return daoFactory.roleGiftCenterDAO();
	}

	public RoleGiftStatusDAO roleGiftStatusDAO() {
		return daoFactory.roleGiftStatusDAO();
	}

	public RoleRoadDAO roleRoadDAO() {
		return daoFactory.roleRoadDAO();
	}

	public RoleVipDAO roleVipDAO() {
		return daoFactory.roleVipDAO();
	}

	public RoleCardEffectDAO roleCardEffectDAO() {
		return daoFactory.roleCardEffectDAO();
	}

	public SwordRoleDAO swordRoleDAO() {
		return daoFactory.swordRoleDAO();
	}

	public SwordRoleCardDAO swordRoleCardDAO() {
		return daoFactory.swordRoleCardDAO();
	}

	public RoleActivityGuessDAO roleActivityGuessDAO() {
		return daoFactory.roleActivityGuessDAO();
	}

	public RoleMonthCardDAO roleMonthCardDAO() {
		return daoFactory.roleMonthCardDAO();
	}

	public RoleItemLogDAO roleItemLogDAO() {
		return daoFactory.roleItemLogDAO();
	}

	public ActivityConfigDAO activityConfigDAO() {
		return daoFactory.activityConfigDAO();
	}

	public RoleBattleDAO roleBattleDAO() {
		return daoFactory.roleBattleDAO();
	}

	public RoleShenMiShopDAO roleShenMiShopDAO() {
		return daoFactory.roleShenMiShopDAO();
	}

	public RoleShopDAO roleShopDAO() {
		return daoFactory.roleShopDAO();
	}

	public RoleSnatchDAO roleSnatchDAO() {
		return daoFactory.roleSnatchDAO();
	}

	public BossBattleDAO bossBattleDAO() {
		return daoFactory.bossBattleDAO();
	}

	public BossBattlePlayerDAO bossBattlePlayerDAO() {
		return daoFactory.bossBattlePlayerDAO();
	}

	public RoleActivityHappyDAO roleActivityHappyDAO() {
		return daoFactory.roleActivityHappyDAO();
	}

	public RoleStatisticsDAO roleStatisticsDAO() {
		return daoFactory.roleStatisticsDAO();
	}

	public RoleActivityMonthSignDAO roleActivityMonthSignDAO() {
		return daoFactory.roleActivityMonthSignDAO();
	}

	public RoleActivityTouziDAO roleActivityTouziDAO() {
		return daoFactory.roleActivityTouziDAO();
	}

	public MailDAO mailDAO() {
		return daoFactory.mailDAO();
	}

	public MissionDAO missionDAO() {
		return daoFactory.missionDAO();
	}

	public UnionDAO unionDAO() {
		return daoFactory.unionDAO();
	}

	public RoleUnionDAO roleUnionDAO() {
		return daoFactory.roleUnoionDAO();
	}

	public UnionApplyDAO unionApplyDAO() {
		return daoFactory.unionApplyDAO();
	}

	public UnionDynamicDAO unionDynamicDAO() {
		return daoFactory.unionDynamicDAO();
	}

	public UnionFBDAO unionFBDAO() {
		return daoFactory.unionFBDAO();
	}

	public RoleUnionFBDAO roleUnionFBDAO() {
		return daoFactory.roleUnionFBDAO();
	}

	public RoleUnionFBStateDAO roleUnionFBStateDAO() {
		return daoFactory.roleUnionFBStateDAO();
	}

	public UnionFBDynamicDAO unionFBDynamicDAO() {
		return daoFactory.unionFBDynamicDAO();
	}

	public UnionBossDAO unionBossDAO() {
		return daoFactory.unionBossDAO();
	}

	public UnionBossPlayerDAO unionBossPlayerDAO() {
		return daoFactory.unionBossPlayerDAO();
	}

	public RolePseudoDAO rolePseudoDAO() {
		return daoFactory.rolePseudoDAO();
	}

	public RoleActivityLimitCardDAO roleActivityLimitCardDAO() {
		return daoFactory.roleActivityLimitCardDAO();
	}

	public RoleFriendDAO roleFriendDAO() {
		return daoFactory.roleFriendDAO();
	}

	public RoleDailyMissionDAO roleDailyMissionDAO() {
		return daoFactory.roleDailyMissionDAO();
	}

	public UnionShopDAO unionShopDAO() {
		return daoFactory.unionShopDAO();
	}

	public UnionRoleShopDAO unionRoleShopDAO() {
		return daoFactory.unionRoleShopDAO();
	}

	public UnionLogDAO unionLogDAO() {
		return daoFactory.unionLogDAO();
	}

	public CacheRoleTournamentDAO roleTournamentDAO() {
		return daoFactory.roleTournamentDAO();
	}

	public RoleTournamentEnemyDAO roleTournamentEnemyDAO() {
		return daoFactory.roleTournamentEnemyDAO();
	}

	public RoleTournamentShopDAO roleTournamentShopDAO() {
		return daoFactory.roleTournamentShopDAO();
	}

	public RoleRankDAO roleRankDAO() {
		return daoFactory.roleRankDAO();
	}

	public RoleDartDAO roleDartDAO() {
		return daoFactory.dartDAO();
	}

	public RoleActivityRouletteDAO roleActivityRouletteDAO() {
		return daoFactory.roleActivityRouletteDAO();
	}

	public RoleActivityExchDAO roleActivityExchDAO() {
		return daoFactory.roleActivityExchDAO();
	}

	public ActivityExchangeDAO activityExchangeDAO() {
		return daoFactory.activityExchangeDAO();
	}

	public ActivityLimitShopDAO activityLimitShopDAO() {
		return daoFactory.activityLimitShopDAO();
	}

	public RoleActivityLimitShopDAO roleActivityLimitShopDAO() {
		return daoFactory.roleActivityLimitShopDAO();
	}

	public RoleLineupAidDAO roleLineupAidDAO() {
		return daoFactory.roleLineupAidDAO();
	}

	public RoleLineupCardAidDAO roleLineupCardAidDAO() {
		return daoFactory.roleLineupCardAidDAO();
	}

	public RoleActivityMazeDAO roleActivityMazeDAO() {
		return daoFactory.roleActivityMazeDAO();
	}
}
