package com.mdy.dzs.game;

import com.mdy.dzs.game.ao.ActivityLimitShopAO;
import com.mdy.dzs.game.ao.BossBattleAO;
import com.mdy.dzs.game.ao.BossBattlePlayerAO;
import com.mdy.dzs.game.ao.BroadcastAO;
import com.mdy.dzs.game.ao.ChatAO;
import com.mdy.dzs.game.ao.FightAO;
import com.mdy.dzs.game.ao.LineupAO;
import com.mdy.dzs.game.ao.MailAO;
import com.mdy.dzs.game.ao.MissionAO;
import com.mdy.dzs.game.ao.MissionExecAO;
import com.mdy.dzs.game.ao.PacketAO;
import com.mdy.dzs.game.ao.RoleAO;
import com.mdy.dzs.game.ao.RoleActivityAO;
import com.mdy.dzs.game.ao.RoleActivityExchAO;
import com.mdy.dzs.game.ao.RoleActivityHappyAO;
import com.mdy.dzs.game.ao.RoleActivityLimitShopAO;
import com.mdy.dzs.game.ao.RoleActivityMazeAO;
import com.mdy.dzs.game.ao.RoleActivityMonthSignAO;
import com.mdy.dzs.game.ao.RoleActivityRouletteAO;
import com.mdy.dzs.game.ao.RoleActivityTouziAO;
import com.mdy.dzs.game.ao.RoleArenaAO;
import com.mdy.dzs.game.ao.RoleBattleAO;
import com.mdy.dzs.game.ao.RoleCardAO;
import com.mdy.dzs.game.ao.RoleChallengeBattleAO;
import com.mdy.dzs.game.ao.RoleChannelAO;
import com.mdy.dzs.game.ao.RoleDartAO;
import com.mdy.dzs.game.ao.RoleEquipAO;
import com.mdy.dzs.game.ao.RoleFriendAO;
import com.mdy.dzs.game.ao.RoleGiftCenterAO;
import com.mdy.dzs.game.ao.RoleGongAO;
import com.mdy.dzs.game.ao.RoleLineupAidAO;
import com.mdy.dzs.game.ao.RoleLineupCardAidAO;
import com.mdy.dzs.game.ao.RoleMonthCardAO;
import com.mdy.dzs.game.ao.RolePseudoAO;
import com.mdy.dzs.game.ao.RoleRankAO;
import com.mdy.dzs.game.ao.RoleRoadAO;
import com.mdy.dzs.game.ao.RoleShenMiShopAO;
import com.mdy.dzs.game.ao.RoleSnatchAO;
import com.mdy.dzs.game.ao.RoleStatisticsAO;
import com.mdy.dzs.game.ao.RoleTournamentAO;
import com.mdy.dzs.game.ao.RoleTournamentEnemyAO;
import com.mdy.dzs.game.ao.RoleTournamentShopAO;
import com.mdy.dzs.game.ao.RoleUnionAO;
import com.mdy.dzs.game.ao.RoleUnionFBAO;
import com.mdy.dzs.game.ao.RoleUnionFBStateAO;
import com.mdy.dzs.game.ao.RoleVipAO;
import com.mdy.dzs.game.ao.RoleYuanAO;
import com.mdy.dzs.game.ao.ShopAO;
import com.mdy.dzs.game.ao.SnatchAO;
import com.mdy.dzs.game.ao.SwordRoleAO;
import com.mdy.dzs.game.ao.SwordRoleCardAO;
import com.mdy.dzs.game.ao.UnionAO;
import com.mdy.dzs.game.ao.UnionApplyAO;
import com.mdy.dzs.game.ao.UnionBossAO;
import com.mdy.dzs.game.ao.UnionBossPlayerAO;
import com.mdy.dzs.game.ao.UnionDynamicAO;
import com.mdy.dzs.game.ao.UnionFBAO;
import com.mdy.dzs.game.ao.UnionFBDynamicAO;
import com.mdy.dzs.game.ao.UnionLogAO;
import com.mdy.dzs.game.ao.UnionRoleShopAO;
import com.mdy.dzs.game.ao.UnionShopAO;
import com.mdy.dzs.game.dao.ActivityConfigDAO;
import com.mdy.dzs.game.dao.ActivityExchangeDAO;
import com.mdy.dzs.game.dao.ActivityLimitShopDAO;
import com.mdy.dzs.game.dao.BossBattleDAO;
import com.mdy.dzs.game.dao.BossBattlePlayerDAO;
import com.mdy.dzs.game.dao.BroadcastDAO;
import com.mdy.dzs.game.dao.ChatDAO;
import com.mdy.dzs.game.dao.MailDAO;
import com.mdy.dzs.game.dao.MissionDAO;
import com.mdy.dzs.game.dao.PacketDAO;
import com.mdy.dzs.game.dao.RoleActivityExchDAO;
import com.mdy.dzs.game.dao.RoleActivityGuessDAO;
import com.mdy.dzs.game.dao.RoleActivityHappyDAO;
import com.mdy.dzs.game.dao.RoleActivityLimitCardDAO;
import com.mdy.dzs.game.dao.RoleActivityLimitShopDAO;
import com.mdy.dzs.game.dao.RoleActivityMazeDAO;
import com.mdy.dzs.game.dao.RoleActivityMonthSignDAO;
import com.mdy.dzs.game.dao.RoleActivityRouletteDAO;
import com.mdy.dzs.game.dao.RoleActivityTouziDAO;
import com.mdy.dzs.game.dao.RoleArenaDAO;
import com.mdy.dzs.game.dao.RoleBattleDAO;
import com.mdy.dzs.game.dao.RoleCardDAO;
import com.mdy.dzs.game.dao.RoleCardEffectDAO;
import com.mdy.dzs.game.dao.RoleChallengeBattleDAO;
import com.mdy.dzs.game.dao.RoleChannelDAO;
import com.mdy.dzs.game.dao.RoleDAO;
import com.mdy.dzs.game.dao.RoleDailyMissionDAO;
import com.mdy.dzs.game.dao.RoleDartDAO;
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
import com.mdy.dzs.game.dao.cache.CacheBossBattlePlayerDAO;
import com.mdy.dzs.game.dao.cache.CacheMissionDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleBattleDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleCardDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleCardEffectDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleChallengeBattleDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleChannelDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleEquipDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleFriendDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleGongDAO;
import com.mdy.dzs.game.dao.cache.CacheRolePacketDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleRoadDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleTournamentDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleUnionDAO;
import com.mdy.dzs.game.dao.cache.CacheRoleYuanDAO;
import com.mdy.dzs.game.dao.cache.CacheSwordRoleCardDAO;
import com.mdy.dzs.game.dao.cache.CacheSwordRoleDAO;
import com.mdy.dzs.game.dao.cache.CacheUnionApplyDAO;
import com.mdy.dzs.game.dao.cache.CacheUnionBossPlayerDAO;
import com.mdy.dzs.game.dao.cache.CacheUnionDAO;
import com.mdy.dzs.game.dao.cache.CacheUnionDynamicDAO;
import com.mdy.dzs.game.job.ReloadMonthSignDataJob;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.Application;

import com.mdy.sharp.container.res.cache.CacheResource;
import com.mdy.sharp.container.res.cache.RedisResource;
import com.mdy.sharp.container.res.ds.ConnectionResource;

/**
 * app服务器
 */
public class SystemApplication extends Application {
    private static SystemApplication ins = null;
    public static final String DATA_SOURCE = "dbResource";

    public static SystemApplication Instance() {
        if (ins == null) {
            ins = new SystemApplication();
        }
        return ins;
    }

    private DAOFactory daoFactory;

    private CacheManager cacheManager;
    private RoleAttackCalcManager calcManager;
    // AO
    private RoleAO roleAO;
    private RoleCardAO roleCardAO;
    private BroadcastAO broadcastAO;
    private PacketAO packetAO;
    private ShopAO shopAO;
    private LineupAO lineupAO;
    private RoleEquipAO roleEquipAO;
    private RoleGongAO roleGongAO;
    private RoleYuanAO roleYuanAO;
    private RoleChannelAO roleChannelAO;
    private RoleChallengeBattleAO roleChallengeBattleAO;
    private ChatAO chatAO;
    private RoleArenaAO roleArenaAO;
    private RoleGiftCenterAO roleGiftCenterAO;
    private SnatchAO snatchAO;
    private FightAO fightAO;
    private RoleRoadAO roleRoadAO;
    private SwordRoleAO swordRoleAO;
    private SwordRoleCardAO swordRoleCardAO;
    private RoleVipAO roleVipAO;
    private RoleActivityAO roleActivityAO;
    private RoleMonthCardAO roleMonthCardAO;
    private RoleBattleAO roleBattleAO;
    private RoleShenMiShopAO roleShenMiShopAO;
    private BossBattleAO bossBattleAO;
    private BossBattlePlayerAO bossBattlePlayerAO;
    private RoleSnatchAO roleSnatchAO;
    private RoleActivityHappyAO roleActivityHappyAO;
    private RoleStatisticsAO roleStatisticsAO;
    private RoleActivityMonthSignAO roleActivityMonthSignAO;
    private RoleActivityTouziAO roleActivityTouziAO;
    private MailAO mailAO;
    private MissionAO missionAO;
    private MissionExecAO missionExecAO;
    private UnionAO unionAO;
    private RoleUnionAO roleUnionAO;
    private UnionApplyAO unionApplyAO;
    private UnionDynamicAO unionDynamicAO;
    private UnionFBAO unionFBAO;
    private UnionFBDynamicAO unionFBDynamicAO;
    private RoleUnionFBAO roleUnionFBAO;
    private RoleUnionFBStateAO roleUnionFBStateAO;
    private UnionBossAO unionBossAO;
    private UnionBossPlayerAO unionBossPlayerAO;
    private RolePseudoAO rolePseudoAO;
    private RoleFriendAO roleFriendAO;
    private UnionShopAO unionShopAO;
    private UnionRoleShopAO unionRoleShopAO;
    private UnionLogAO unionLogAO;
    private RoleTournamentAO roleTournamentAO;
    private RoleTournamentEnemyAO roleTournamentEnemyAO;
    private RoleTournamentShopAO roleTournamentShopAO;
    private RoleRankAO roleRankAO;
    private RoleDartAO roleDartAO;
    private RoleActivityRouletteAO roleActivityRouletteAO;
    private RoleActivityExchAO roleActivityExchAO;
    private RoleActivityLimitShopAO roleActivityLimitShopAO;
    private ActivityLimitShopAO activityLimitShopAO;
    private RoleLineupAidAO roleLineupAidAO;
    private RoleLineupCardAidAO roleLineupCardAidAO;
    private RoleActivityMazeAO roleActivityMazeAO;

    /**
     * 此函数在服务器启动过程中调用
     */
    @Override
    public void startUp() {
        initConfig();
        setupDAOFactory();
        initManager();
        setupAO();
        initJob();
        initSensitiveWord();
    }

    /**
     * 初始配置
     */
    private void initConfig() {
        // 没有配置专用login、data 则用原始的
        // if
        // (Container.get().getRemoteProxyFactoryMap().get(DataApplication.CLUSTER_SDATA_SYSTEM)
        // == null) {
        // DataApplication.CLUSTER_SDATA_SYSTEM = DataApplication.CLUSTER_DATA_SYSTEM;
        // }

        // ConfigResource config = (ConfigResource)
        // Container.get().getResource("config");
        // if (config == null) {
        // return;
        // }
        // Boolean loadDataByGame = config.getAsBoolean("loadData");
        // if (loadDataByGame != null) {
        // CacheManager.loadDataByGame = loadDataByGame;
        // }
    }

    private void initSensitiveWord() {
        // SensitiveWordFilterUtil.get().addKeywords(SensitiveWord.get().getSensitiveWord());
    }

    /**
     * 此函数在服务器停止过程中调用
     */
    @Override
    public void shutDown() {
    }

    // --------------------------------------------------------------------------
    private void setupDAOFactory() {
        ConnectionResource cr = (ConnectionResource) Container.get().getResource(DATA_SOURCE);
        // cached
        final CacheRoleCardDAO roleCardDAO = new CacheRoleCardDAO();
        roleCardDAO.setConnectionResource(cr);

        final CacheRolePacketDAO packetDAO = new CacheRolePacketDAO();
        packetDAO.setConnectionResource(cr);

        final CacheRoleEquipDAO equipDAO = new CacheRoleEquipDAO();
        equipDAO.setConnectionResource(cr);

        final CacheRoleCardEffectDAO roleCardEffectDAO = new CacheRoleCardEffectDAO();
        roleCardEffectDAO.setConnectionResource(cr);

        final CacheRoleGongDAO roleGongDAO = new CacheRoleGongDAO();
        roleGongDAO.setConnectionResource(cr);

        final CacheRoleYuanDAO roleYuanDAO = new CacheRoleYuanDAO();
        roleYuanDAO.setConnectionResource(cr);

        final CacheBossBattlePlayerDAO bossBattlePlayerDAO = new CacheBossBattlePlayerDAO();
        bossBattlePlayerDAO.setConnectionResource(cr);

        final CacheRoleDAO roleDAO = new CacheRoleDAO();
        roleDAO.setConnectionResource(cr);

        final CacheRoleBattleDAO roleBattleDAO = new CacheRoleBattleDAO();
        roleBattleDAO.setConnectionResource(cr);

        final CacheRoleChannelDAO roleChannelDAO = new CacheRoleChannelDAO();
        roleChannelDAO.setConnectionResource(cr);

        final CacheRoleRoadDAO roleRoadDAO = new CacheRoleRoadDAO();
        roleRoadDAO.setConnectionResource(cr);

        final CacheSwordRoleDAO swordRoleDAO = new CacheSwordRoleDAO();
        swordRoleDAO.setConnectionResource(cr);

        final CacheSwordRoleCardDAO swordRoleCardDAO = new CacheSwordRoleCardDAO();
        swordRoleCardDAO.setConnectionResource(cr);

        final CacheRoleChallengeBattleDAO roleChallengeBattleDAO = new CacheRoleChallengeBattleDAO();
        roleChallengeBattleDAO.setConnectionResource(cr);

        final CacheRoleUnionDAO roleUnionDAO = new CacheRoleUnionDAO();
        roleUnionDAO.setConnectionResource(cr);

        final CacheUnionBossPlayerDAO unionBossPlayerDAO = new CacheUnionBossPlayerDAO();
        unionBossPlayerDAO.setConnectionResource(cr);

        final CacheUnionDAO unionDAO = new CacheUnionDAO();
        unionDAO.setConnectionResource(cr);

        final CacheUnionDynamicDAO unionDynamicDAO = new CacheUnionDynamicDAO();
        unionDynamicDAO.setConnectionResource(cr);

        final CacheUnionApplyDAO unionApplyDAO = new CacheUnionApplyDAO();
        unionApplyDAO.setConnectionResource(cr);

        final CacheMissionDAO missionDAO = new CacheMissionDAO();
        missionDAO.setConnectionResource(cr);

        final CacheRoleFriendDAO roleFriendDAO = new CacheRoleFriendDAO();
        roleFriendDAO.setConnectionResource(cr);
        // nocache

        final BroadcastDAO broadcastDAO = new BroadcastDAO();
        broadcastDAO.setConnectionResource(cr);

        final ShopDAO shopDAO = new ShopDAO();
        shopDAO.setConnectionResource(cr);

        final ChatDAO chatDAO = new ChatDAO();
        chatDAO.setConnectionResource(cr);

        final RoleArenaDAO roleArenaDAO = new RoleArenaDAO();
        roleArenaDAO.setConnectionResource(cr);

        final RoleGiftCenterDAO roleGiftCenterDAO = new RoleGiftCenterDAO();
        roleGiftCenterDAO.setConnectionResource(cr);

        final RoleGiftStatusDAO roleGiftStatusDAO = new RoleGiftStatusDAO();
        roleGiftStatusDAO.setConnectionResource(cr);

        final RoleVipDAO roleVipDAO = new RoleVipDAO();
        roleVipDAO.setConnectionResource(cr);

        final RoleActivityGuessDAO roleActivityGuessDAO = new RoleActivityGuessDAO();
        roleActivityGuessDAO.setConnectionResource(cr);

        final RoleMonthCardDAO roleMonthCardDAO = new RoleMonthCardDAO();
        roleMonthCardDAO.setConnectionResource(cr);

        final RoleItemLogDAO roleItemLogDAO = new RoleItemLogDAO();
        roleItemLogDAO.setConnectionResource(cr);

        final ActivityConfigDAO activityConfigDAO = new ActivityConfigDAO();
        activityConfigDAO.setConnectionResource(cr);

        final RoleShenMiShopDAO roleShenMiShopDAO = new RoleShenMiShopDAO();
        roleShenMiShopDAO.setConnectionResource(cr);

        final RoleShopDAO roleShopDAO = new RoleShopDAO();
        roleShopDAO.setConnectionResource(cr);

        final BossBattleDAO bossBattleDAO = new BossBattleDAO();
        bossBattleDAO.setConnectionResource(cr);

        final RoleSnatchDAO roleSnatchDAO = new RoleSnatchDAO();
        roleSnatchDAO.setConnectionResource(cr);

        final RoleActivityHappyDAO roleActivityHappyDAO = new RoleActivityHappyDAO();
        roleActivityHappyDAO.setConnectionResource(cr);

        final RoleStatisticsDAO roleStatisticsDAO = new RoleStatisticsDAO();
        roleStatisticsDAO.setConnectionResource(cr);

        final RoleActivityMonthSignDAO roleActivityMonthSignDAO = new RoleActivityMonthSignDAO();
        roleActivityMonthSignDAO.setConnectionResource(cr);

        final RoleActivityTouziDAO roleActivityTouziDAO = new RoleActivityTouziDAO();
        roleActivityTouziDAO.setConnectionResource(cr);

        final MailDAO mailDAO = new MailDAO();
        mailDAO.setConnectionResource(cr);

        final RoleActivityLimitCardDAO limitcarddao = new RoleActivityLimitCardDAO();
        limitcarddao.setConnectionResource(cr);

        final RolePseudoDAO rolePseudoDAO = new RolePseudoDAO();
        rolePseudoDAO.setConnectionResource(cr);

        final UnionBossDAO unionBossDAO = new UnionBossDAO();
        unionBossDAO.setConnectionResource(cr);

        final RoleDailyMissionDAO roleDailyMissionDAO = new RoleDailyMissionDAO();
        roleDailyMissionDAO.setConnectionResource(cr);

        final UnionShopDAO unionShopDAO = new UnionShopDAO();
        unionShopDAO.setConnectionResource(cr);

        final UnionRoleShopDAO unionRoleShopDAO = new UnionRoleShopDAO();
        unionRoleShopDAO.setConnectionResource(cr);

        final UnionFBDAO unionFBDAO = new UnionFBDAO();
        unionFBDAO.setConnectionResource(cr);

        final UnionFBDynamicDAO unionFBDynamicDAO = new UnionFBDynamicDAO();
        unionFBDynamicDAO.setConnectionResource(cr);

        final UnionLogDAO unionLogDAO = new UnionLogDAO();
        unionLogDAO.setConnectionResource(cr);

        final RoleUnionFBDAO roleUnionFBDAO = new RoleUnionFBDAO();
        roleUnionFBDAO.setConnectionResource(cr);

        final RoleUnionFBStateDAO roleUnionFBStateDAO = new RoleUnionFBStateDAO();
        roleUnionFBStateDAO.setConnectionResource(cr);

        final CacheRoleTournamentDAO roleTournamentDAO = new CacheRoleTournamentDAO();
        roleTournamentDAO.setConnectionResource(cr);

        final RoleTournamentEnemyDAO roleTournamentEnemyDAO = new RoleTournamentEnemyDAO();
        roleTournamentEnemyDAO.setConnectionResource(cr);

        final RoleTournamentShopDAO roleTournamentShopDAO = new RoleTournamentShopDAO();
        roleTournamentShopDAO.setConnectionResource(cr);

        final RoleRankDAO roleRankDAO = new RoleRankDAO();
        roleRankDAO.setConnectionResource(cr);

        final RoleDartDAO roleDartDAO = new RoleDartDAO();
        roleDartDAO.setConnectionResource(cr);

        final RoleActivityRouletteDAO roleActivityRouletteDAO = new RoleActivityRouletteDAO();
        roleActivityRouletteDAO.setConnectionResource(cr);

        final RoleActivityExchDAO roleActivityExchDAO = new RoleActivityExchDAO();
        roleActivityExchDAO.setConnectionResource(cr);

        final ActivityExchangeDAO activityExchangeDAO = new ActivityExchangeDAO();
        activityExchangeDAO.setConnectionResource(cr);

        final ActivityLimitShopDAO activityLimitShopDAO = new ActivityLimitShopDAO();
        activityLimitShopDAO.setConnectionResource(cr);

        final RoleLineupAidDAO roleLineupAidDAO = new RoleLineupAidDAO();
        roleLineupAidDAO.setConnectionResource(cr);

        final RoleActivityLimitShopDAO roleActivityLimitShopDAO = new RoleActivityLimitShopDAO();
        roleActivityLimitShopDAO.setConnectionResource(cr);

        final RoleActivityMazeDAO roleActivityMazeDAO = new RoleActivityMazeDAO();
        roleActivityMazeDAO.setConnectionResource(cr);

        final RoleLineupCardAidDAO roleLineupCardAidDAO = new RoleLineupCardAidDAO();
        roleLineupCardAidDAO.setConnectionResource(cr);

        // final RoleUnionDAO roleUnionDAO = new RoleUnionDAO();
        // roleUnionDAO.setConnectionResource(cr);

        // ----------------------------------------------------------------------
        // cache
        CacheResource cacheResource = (CacheResource) new RedisResource();
        // 玩家相关缓存
        roleDAO.setCacheResource(cacheResource);
        roleCardDAO.setCacheResource(cacheResource);
        packetDAO.setCacheResource(cacheResource);
        equipDAO.setCacheResource(cacheResource);
        roleGongDAO.setCacheResource(cacheResource);
        roleYuanDAO.setCacheResource(cacheResource);
        roleCardEffectDAO.setCacheResource(cacheResource);
        roleBattleDAO.setCacheResource(cacheResource);
        roleChannelDAO.setCacheResource(cacheResource);
        roleRoadDAO.setCacheResource(cacheResource);
        roleChannelDAO.setCacheResource(cacheResource);
        roleChallengeBattleDAO.setCacheResource(cacheResource);
        missionDAO.setCacheResource(cacheResource);
        roleFriendDAO.setCacheResource(cacheResource);

        // 大型功能相关缓存
        bossBattlePlayerDAO.setCacheResource(cacheResource);
        swordRoleCardDAO.setCacheResource(cacheResource);
        swordRoleDAO.setCacheResource(cacheResource);
        unionBossPlayerDAO.setCacheResource(cacheResource);

        unionDAO.setCacheResource(cacheResource);
        roleUnionDAO.setCacheResource(cacheResource);
        unionDynamicDAO.setCacheResource(cacheResource);
        unionApplyDAO.setCacheResource(cacheResource);
        roleTournamentDAO.setCacheResource(cacheResource);
        // equipDAO.setCacheResource(cacheResource);

        daoFactory = new DAOFactory() {

            @Override
            public RoleEquipDAO equipDAO() {
                return equipDAO;
            }

            @Override
            public RoleDAO roleDAO() {
                return roleDAO;
            }

            @Override
            public RoleCardDAO roleCardDAO() {
                return roleCardDAO;
            }

            @Override
            public BroadcastDAO broadcastDAO() {
                return broadcastDAO;
            }

            @Override
            public RoleGongDAO roleGongDAO() {
                return roleGongDAO;
            }

            @Override
            public RoleYuanDAO roleYuanDAO() {
                return roleYuanDAO;
            }

            @Override
            public PacketDAO packetDAO() {
                return packetDAO;
            }

            @Override
            public ShopDAO shopDAO() {
                return shopDAO;
            }

            @Override
            public RoleChannelDAO roleChannelDAO() {
                return roleChannelDAO;
            }

            @Override
            public RoleChallengeBattleDAO roleChallengeBattleDAO() {
                return roleChallengeBattleDAO;
            }

            @Override
            public ChatDAO chatDAO() {
                return chatDAO;
            }

            @Override
            public RoleArenaDAO roleArenaDAO() {
                return roleArenaDAO;
            }

            @Override
            public RoleGiftCenterDAO roleGiftCenterDAO() {
                return roleGiftCenterDAO;
            }

            @Override
            public RoleGiftStatusDAO roleGiftStatusDAO() {
                return roleGiftStatusDAO;
            }

            @Override
            public RoleRoadDAO roleRoadDAO() {
                return roleRoadDAO;
            }

            @Override
            public RoleVipDAO roleVipDAO() {
                return roleVipDAO;
            }

            @Override
            public RoleCardEffectDAO roleCardEffectDAO() {
                return roleCardEffectDAO;
            }

            @Override
            public SwordRoleDAO swordRoleDAO() {
                return swordRoleDAO;
            }

            @Override
            public SwordRoleCardDAO swordRoleCardDAO() {
                return swordRoleCardDAO;
            }

            @Override
            public RoleActivityGuessDAO roleActivityGuessDAO() {
                return roleActivityGuessDAO;
            }

            @Override
            public RoleMonthCardDAO roleMonthCardDAO() {
                return roleMonthCardDAO;
            }

            @Override
            public RoleItemLogDAO roleItemLogDAO() {
                return roleItemLogDAO;
            }

            @Override
            public ActivityConfigDAO activityConfigDAO() {
                return activityConfigDAO;
            }

            @Override
            public RoleBattleDAO roleBattleDAO() {
                return roleBattleDAO;
            }

            @Override
            public RoleShenMiShopDAO roleShenMiShopDAO() {
                return roleShenMiShopDAO;
            }

            @Override
            public RoleShopDAO roleShopDAO() {
                return roleShopDAO;
            }

            @Override
            public BossBattleDAO bossBattleDAO() {
                return bossBattleDAO;
            }

            @Override
            public BossBattlePlayerDAO bossBattlePlayerDAO() {
                return bossBattlePlayerDAO;
            }

            @Override
            public RoleSnatchDAO roleSnatchDAO() {
                return roleSnatchDAO;
            }

            @Override
            public RoleActivityHappyDAO roleActivityHappyDAO() {
                return roleActivityHappyDAO;
            }

            @Override
            public RoleStatisticsDAO roleStatisticsDAO() {
                return roleStatisticsDAO;
            }

            @Override
            public RoleActivityMonthSignDAO roleActivityMonthSignDAO() {
                return roleActivityMonthSignDAO;
            }

            @Override
            public RoleActivityTouziDAO roleActivityTouziDAO() {
                return roleActivityTouziDAO;
            }

            @Override
            public UnionDAO unionDAO() {
                return unionDAO;
            }

            @Override
            public UnionApplyDAO unionApplyDAO() {
                return unionApplyDAO;
            }

            @Override
            public RoleUnionDAO roleUnoionDAO() {
                return roleUnionDAO;
            }

            @Override
            public UnionDynamicDAO unionDynamicDAO() {
                return unionDynamicDAO;
            }

            @Override
            public UnionFBDAO unionFBDAO() {
                return unionFBDAO;
            }

            @Override
            public RoleUnionFBStateDAO roleUnionFBStateDAO() {
                return roleUnionFBStateDAO;
            }

            @Override
            public RoleUnionFBDAO roleUnionFBDAO() {
                return roleUnionFBDAO;
            }

            @Override
            public UnionFBDynamicDAO unionFBDynamicDAO() {
                return unionFBDynamicDAO;
            }

            @Override
            public MailDAO mailDAO() {
                return mailDAO;
            }

            @Override
            public MissionDAO missionDAO() {
                return missionDAO;
            }

            @Override
            public UnionBossDAO unionBossDAO() {
                return unionBossDAO;
            }

            @Override
            public UnionBossPlayerDAO unionBossPlayerDAO() {
                return unionBossPlayerDAO;
            }

            @Override
            public RoleActivityLimitCardDAO roleActivityLimitCardDAO() {
                return limitcarddao;
            }

            @Override
            public RolePseudoDAO rolePseudoDAO() {
                return rolePseudoDAO;
            }

            @Override
            public RoleFriendDAO roleFriendDAO() {
                return roleFriendDAO;
            }

            @Override
            public RoleDailyMissionDAO roleDailyMissionDAO() {
                return roleDailyMissionDAO;
            }

            @Override
            public UnionShopDAO unionShopDAO() {
                return unionShopDAO;
            }

            @Override
            public UnionRoleShopDAO unionRoleShopDAO() {
                return unionRoleShopDAO;
            }

            @Override
            public UnionLogDAO unionLogDAO() {
                return unionLogDAO;
            }

            @Override
            public CacheRoleTournamentDAO roleTournamentDAO() {
                return roleTournamentDAO;
            }

            @Override
            public RoleTournamentEnemyDAO roleTournamentEnemyDAO() {
                return roleTournamentEnemyDAO;
            }

            @Override
            public RoleTournamentShopDAO roleTournamentShopDAO() {
                return roleTournamentShopDAO;
            }

            @Override
            public RoleRankDAO roleRankDAO() {
                return roleRankDAO;
            }

            @Override
            public RoleDartDAO dartDAO() {
                return roleDartDAO;
            }

            @Override
            public RoleActivityRouletteDAO roleActivityRouletteDAO() {
                return roleActivityRouletteDAO;
            }

            @Override
            public RoleActivityExchDAO roleActivityExchDAO() {
                return roleActivityExchDAO;
            }

            @Override
            public ActivityExchangeDAO activityExchangeDAO() {
                return activityExchangeDAO;
            }

            @Override
            public ActivityLimitShopDAO activityLimitShopDAO() {
                return activityLimitShopDAO;
            }

            @Override
            public RoleActivityLimitShopDAO roleActivityLimitShopDAO() {
                return roleActivityLimitShopDAO;
            }

            @Override
            public RoleActivityMazeDAO roleActivityMazeDAO() {
                return roleActivityMazeDAO;
            }

            @Override
            public RoleLineupAidDAO roleLineupAidDAO() {
                return roleLineupAidDAO;
            }

            @Override
            public RoleLineupCardAidDAO roleLineupCardAidDAO() {
                return roleLineupCardAidDAO;
            }

        };
    }

    // --------------------------------------------------------------------------
    //
    private void setupAO() {
        // NotificationManager notificationManager =
        // Container.get().getNotificationManager();

        roleAO = new RoleAO(cacheManager, calcManager);
        roleAO.setDaoFactory(daoFactory);

        roleRoadAO = new RoleRoadAO(cacheManager);
        roleRoadAO.setDaoFactory(daoFactory);

        roleCardAO = new RoleCardAO(cacheManager, roleRoadAO);
        roleCardAO.setDaoFactory(daoFactory);
        roleAO.setRoleCardAO(roleCardAO);

        broadcastAO = new BroadcastAO(cacheManager, roleAO);
        broadcastAO.setDaoFactory(daoFactory);

        roleUnionAO = new RoleUnionAO();
        roleUnionAO.setDaoFactory(daoFactory);

        roleActivityAO = new RoleActivityAO(cacheManager);
        roleActivityAO.setDaoFactory(daoFactory);

        roleActivityRouletteAO = new RoleActivityRouletteAO(cacheManager, roleActivityAO);
        roleActivityRouletteAO.setDaoFactory(daoFactory);
        roleActivityAO.setRoleActivityRouletteAO(roleActivityRouletteAO);

        packetAO = new PacketAO(cacheManager, roleAO, broadcastAO, roleCardAO, roleUnionAO, roleActivityRouletteAO);
        packetAO.setDaoFactory(daoFactory);
        roleAO.setPacketAO(packetAO);

        shopAO = new ShopAO();
        shopAO.setDaoFactory(daoFactory);

        lineupAO = new LineupAO(cacheManager, calcManager);
        lineupAO.setDaoFactory(daoFactory);

        roleEquipAO = new RoleEquipAO(cacheManager, calcManager);
        roleEquipAO.setDaoFactory(daoFactory);

        roleGongAO = new RoleGongAO(cacheManager, packetAO);
        roleGongAO.setDaoFactory(daoFactory);

        roleYuanAO = new RoleYuanAO(cacheManager);
        roleYuanAO.setDaoFactory(daoFactory);

        roleChannelAO = new RoleChannelAO();
        roleChannelAO.setDaoFactory(daoFactory);
        roleAO.setRoleChannelAO(roleChannelAO);

        chatAO = new ChatAO();
        chatAO.setDaoFactory(daoFactory);

        roleArenaAO = new RoleArenaAO();
        roleArenaAO.setDaoFactory(daoFactory);

        snatchAO = new SnatchAO(cacheManager, packetAO);
        snatchAO.setDaoFactory(daoFactory);

        fightAO = new FightAO(cacheManager, roleAO);
        fightAO.setDaoFactory(daoFactory);

        swordRoleCardAO = new SwordRoleCardAO();
        swordRoleCardAO.setDaoFactory(daoFactory);

        swordRoleAO = new SwordRoleAO(cacheManager, roleAO, swordRoleCardAO);
        swordRoleAO.setDaoFactory(daoFactory);

        roleMonthCardAO = new RoleMonthCardAO();
        roleMonthCardAO.setDaoFactory(daoFactory);

        roleShenMiShopAO = new RoleShenMiShopAO(cacheManager);
        roleShenMiShopAO.setDaoFactory(daoFactory);

        missionAO = new MissionAO();
        missionAO.setDaoFactory(daoFactory);
        missionExecAO = new MissionExecAO(missionAO, packetAO, cacheManager);
        missionExecAO.setDaoFactory(daoFactory);
        packetAO.setMissionExecAO(missionExecAO);
        roleCardAO.setMissionExecAO(missionExecAO);
        roleAO.setMissionExecAO(missionExecAO);

        roleChallengeBattleAO = new RoleChallengeBattleAO(cacheManager, packetAO, missionAO, missionExecAO);
        roleChallengeBattleAO.setDaoFactory(daoFactory);
        roleAO.setRoleChallengeBattleAO(roleChallengeBattleAO);

        roleGiftCenterAO = new RoleGiftCenterAO(packetAO, missionAO, cacheManager);
        roleGiftCenterAO.setDaoFactory(daoFactory);
        roleAO.setGiftCenterAO(roleGiftCenterAO);

        roleVipAO = new RoleVipAO(roleAO, cacheManager, packetAO, roleGiftCenterAO, roleMonthCardAO, roleShenMiShopAO,
                missionExecAO, roleActivityRouletteAO);
        roleVipAO.setDaoFactory(daoFactory);

        roleBattleAO = new RoleBattleAO();
        roleBattleAO.setDaoFactory(daoFactory);

        bossBattlePlayerAO = new BossBattlePlayerAO(missionExecAO);
        bossBattlePlayerAO.setDaoFactory(daoFactory);

        bossBattleAO = new BossBattleAO(bossBattlePlayerAO);
        bossBattleAO.setDaoFactory(daoFactory);

        roleSnatchAO = new RoleSnatchAO();
        roleSnatchAO.setDaoFactory(daoFactory);

        roleActivityHappyAO = new RoleActivityHappyAO();
        roleActivityHappyAO.setDaoFactory(daoFactory);

        roleStatisticsAO = new RoleStatisticsAO();
        roleStatisticsAO.setDaoFactory(daoFactory);

        roleActivityMonthSignAO = new RoleActivityMonthSignAO();
        roleActivityMonthSignAO.setDaoFactory(daoFactory);

        roleActivityTouziAO = new RoleActivityTouziAO();
        roleActivityTouziAO.setDaoFactory(daoFactory);

        mailAO = new MailAO();
        mailAO.setDaoFactory(daoFactory);

        unionAO = new UnionAO(cacheManager);
        unionAO.setDaoFactory(daoFactory);

        unionApplyAO = new UnionApplyAO();
        unionApplyAO.setDaoFactory(daoFactory);

        unionDynamicAO = new UnionDynamicAO();
        unionDynamicAO.setDaoFactory(daoFactory);

        unionBossPlayerAO = new UnionBossPlayerAO();
        unionBossPlayerAO.setDaoFactory(daoFactory);

        rolePseudoAO = new RolePseudoAO(cacheManager, packetAO, roleAO);
        rolePseudoAO.setDaoFactory(daoFactory);

        unionBossAO = new UnionBossAO(unionBossPlayerAO, bossBattleAO, cacheManager);
        unionBossAO.setDaoFactory(daoFactory);

        roleFriendAO = new RoleFriendAO(roleAO, packetAO, missionExecAO, cacheManager);
        roleFriendAO.setDaoFactory(daoFactory);

        unionShopAO = new UnionShopAO();
        unionShopAO.setDaoFactory(daoFactory);

        unionRoleShopAO = new UnionRoleShopAO();
        unionRoleShopAO.setDaoFactory(daoFactory);

        unionLogAO = new UnionLogAO();
        unionLogAO.setDaoFactory(daoFactory);

        unionFBAO = new UnionFBAO();
        unionFBAO.setDaoFactory(daoFactory);

        unionFBDynamicAO = new UnionFBDynamicAO();
        unionFBDynamicAO.setDaoFactory(daoFactory);

        roleUnionFBAO = new RoleUnionFBAO();
        roleUnionFBAO.setDaoFactory(daoFactory);

        roleUnionFBStateAO = new RoleUnionFBStateAO();
        roleUnionFBStateAO.setDaoFactory(daoFactory);

        roleTournamentAO = new RoleTournamentAO(cacheManager);
        roleTournamentAO.setDaoFactory(daoFactory);

        roleTournamentEnemyAO = new RoleTournamentEnemyAO(cacheManager);
        roleTournamentEnemyAO.setDaoFactory(daoFactory);

        roleTournamentShopAO = new RoleTournamentShopAO(cacheManager);
        roleTournamentShopAO.setDaoFactory(daoFactory);
        // teamAO = new TeamAO(notificationManager);
        // teamAO.setDaoFactory(daoFactory);

        roleRankAO = new RoleRankAO();
        roleRankAO.setDaoFactory(daoFactory);

        roleDartAO = new RoleDartAO(cacheManager, roleAO, packetAO);
        roleDartAO.setDaoFactory(daoFactory);

        roleActivityExchAO = new RoleActivityExchAO(packetAO, cacheManager);
        roleActivityExchAO.setDaoFactory(daoFactory);
        roleActivityAO.setRoleActivityExchAO(roleActivityExchAO);

        roleActivityLimitShopAO = new RoleActivityLimitShopAO();
        roleActivityLimitShopAO.setDaoFactory(daoFactory);

        activityLimitShopAO = new ActivityLimitShopAO();
        activityLimitShopAO.setDaoFactory(daoFactory);

        roleLineupAidAO = new RoleLineupAidAO(cacheManager, roleAO);
        roleLineupAidAO.setDaoFactory(daoFactory);

        roleLineupCardAidAO = new RoleLineupCardAidAO();
        roleLineupCardAidAO.setDaoFactory(daoFactory);

        roleActivityMazeAO = new RoleActivityMazeAO(cacheManager, packetAO);
        roleActivityMazeAO.setDaoFactory(daoFactory);

        ConnectionResource.startTransaction(true);
        // teamAO.start();
        shopAO.start();
        roleArenaAO.start();
        roleGiftCenterAO.start();
        snatchAO.start();
        roleRoadAO.start();
        roleCardAO.start();
        roleActivityAO.start();
        roleActivityMonthSignAO.start();
        rolePseudoAO.start();
        missionAO.start();
        roleActivityRouletteAO.start();
        roleActivityExchAO.start();
        // roleRankAO.start();
        ConnectionResource.endTransaction();
    }

    // 初始化Job
    private void initJob() {
        Container.get().registerJob(new ReloadMonthSignDataJob()); // 重新设置月签数据
    }

    // 初始化manager
    private void initManager() {
        cacheManager = new CacheManager();
        cacheManager.reloadData();
        // cacheManager.reloadActivity();

        calcManager = new RoleAttackCalcManager(cacheManager);
        calcManager.reload();
    }

    public CacheManager cacheManager() {
        return cacheManager;
    }

    public RoleAttackCalcManager calcManager() {
        return calcManager;
    }

    // --------------------------------------------------------------------------
    public RoleAO roleAO() {
        return roleAO;
    }

    public RoleCardAO roleCardAO() {
        return roleCardAO;
    }

    public BroadcastAO broadcastAO() {
        return broadcastAO;
    }

    public PacketAO packetAO() {
        return packetAO;
    }

    public ShopAO shopAO() {
        return shopAO;
    }

    public LineupAO lineupAO() {
        return lineupAO;
    }

    public RoleEquipAO roleEquipAO() {
        return roleEquipAO;
    }

    public RoleGongAO roleGongAO() {
        return roleGongAO;
    }

    public RoleYuanAO roleYuanAO() {
        return roleYuanAO;
    }

    public RoleChannelAO roleChannelAO() {
        return roleChannelAO;
    }

    public RoleChallengeBattleAO roleChallengeBattleAO() {
        return roleChallengeBattleAO;
    }

    public ChatAO chatAO() {
        return chatAO;
    }

    public RoleArenaAO roleArenaAO() {
        return roleArenaAO;
    }

    public RoleGiftCenterAO roleGiftCenterAO() {
        return roleGiftCenterAO;
    }

    public SnatchAO snatchAO() {
        return snatchAO;
    }

    public FightAO fightAO() {
        return fightAO;
    }

    public RoleRoadAO roleRoadAO() {
        return roleRoadAO;
    }

    public SwordRoleAO swordRoleAO() {
        return swordRoleAO;
    }

    public SwordRoleCardAO swordRoleCardAO() {
        return swordRoleCardAO;
    }

    public RoleVipAO roleVipAO() {
        return roleVipAO;
    }

    public RoleActivityAO roleActivityAO() {
        return roleActivityAO;
    }

    public RoleMonthCardAO roleMonthCardAO() {
        return roleMonthCardAO;
    }

    public RoleBattleAO roleBattleAO() {
        return roleBattleAO;
    }

    public RoleShenMiShopAO roleShenMiShopAO() {
        return roleShenMiShopAO;
    }

    public BossBattleAO bossBattleAO() {
        return bossBattleAO;
    }

    public BossBattlePlayerAO bossBattlePlayerAO() {
        return bossBattlePlayerAO;
    }

    public RoleSnatchAO roleSnatchAO() {
        return roleSnatchAO;
    }

    public RoleActivityHappyAO roleActivityHappyAO() {
        return roleActivityHappyAO;
    }

    public RoleStatisticsAO roleStatisticsAO() {
        return roleStatisticsAO;
    }

    public RoleActivityMonthSignAO roleActivityMonthSignAO() {
        return roleActivityMonthSignAO;
    }

    public RoleActivityTouziAO roleActivityTouziAO() {
        return roleActivityTouziAO;
    }

    public MailAO mailAO() {
        return mailAO;
    }

    public UnionAO unionAO() {
        return unionAO;
    }

    public RoleUnionAO roleUnionAO() {
        return roleUnionAO;
    }

    public UnionApplyAO unionApplyAO() {
        return unionApplyAO;
    }

    public UnionDynamicAO unionDynamicAO() {
        return unionDynamicAO;
    }

    public UnionFBAO unionFBAO() {
        return unionFBAO;
    }

    public RoleUnionFBStateAO roleUnionFBStateAO() {
        return roleUnionFBStateAO;
    }

    public RoleUnionFBAO roleUnionFBAO() {
        return roleUnionFBAO;
    }

    public UnionFBDynamicAO unionFBDynamicAO() {
        return unionFBDynamicAO;
    }

    public MissionAO missionAO() {
        return missionAO;
    }

    public MissionExecAO missionExecAO() {
        return missionExecAO;
    }

    public UnionBossAO unionBossAO() {
        return unionBossAO;
    }

    public UnionBossPlayerAO unionBossPlayerAO() {
        return unionBossPlayerAO;
    }

    public RolePseudoAO rolePseudoAO() {
        return rolePseudoAO;
    }

    public RoleFriendAO roleFriendAO() {
        return roleFriendAO;
    }

    public UnionShopAO unionShopAO() {
        return unionShopAO;
    }

    public UnionRoleShopAO unionRoleShopAO() {
        return unionRoleShopAO;
    }

    public UnionLogAO unionLogAO() {
        return unionLogAO;
    }

    public RoleTournamentAO roleTournamentAO() {
        return roleTournamentAO;
    }

    public RoleTournamentEnemyAO roleTournamentEnemyAO() {
        return roleTournamentEnemyAO;
    }

    public RoleTournamentShopAO roleTournamentShopAO() {
        return roleTournamentShopAO;
    }

    public RoleRankAO roleRankAO() {
        return roleRankAO;
    }

    public RoleDartAO roleDartAO() {
        return roleDartAO;
    }

    public RoleActivityRouletteAO roleActivityRouletteAO() {
        return roleActivityRouletteAO;
    }

    public RoleActivityExchAO roleActivityExchAO() {
        return roleActivityExchAO;
    }

    public RoleActivityLimitShopAO roleActivityLimitShopAO() {
        return roleActivityLimitShopAO;
    }

    public ActivityLimitShopAO activityLimitShopAO() {
        return activityLimitShopAO;
    }

    public RoleLineupAidAO roleLineupAidAO() {
        return roleLineupAidAO;
    }

    public RoleLineupCardAidAO roleLineupCardAidAO() {
        return roleLineupCardAidAO;
    }

    public RoleActivityMazeAO roleActivityMazeAO() {
        return roleActivityMazeAO;
    }
}
