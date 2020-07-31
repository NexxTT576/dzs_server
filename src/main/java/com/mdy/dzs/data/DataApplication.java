package com.mdy.dzs.data;

import com.mdy.dzs.data.ao.AccountAO;
import com.mdy.dzs.data.ao.ChargeOrderAO;
import com.mdy.dzs.data.ao.DataAO;
import com.mdy.dzs.data.ao.NoticeAO;
import com.mdy.dzs.data.ao.RewardCenterAO;
import com.mdy.dzs.data.ao.ServerInfoAO;
import com.mdy.dzs.data.ao.ServicePartnerAO;
import com.mdy.dzs.data.ao.VersionAO;
import com.mdy.dzs.data.dao.AccountDAO;
import com.mdy.dzs.data.dao.ChargeOrderDAO;
import com.mdy.dzs.data.dao.RewardCenterDAO;
import com.mdy.dzs.data.dao.ServerInfoDAO;
import com.mdy.dzs.data.dao.ServicePartnerDAO;
import com.mdy.dzs.data.dao.VersionDAO;
import com.mdy.dzs.data.dao.datas.ActivityBattleDAO;
import com.mdy.dzs.data.dao.datas.ActivityConfigsDAO;
import com.mdy.dzs.data.dao.datas.ActivityExchangeExpDAO;
import com.mdy.dzs.data.dao.datas.ActivityLimitShopDataDAO;
import com.mdy.dzs.data.dao.datas.ArenaDAO;
import com.mdy.dzs.data.dao.datas.BagDAO;
import com.mdy.dzs.data.dao.datas.BaptizeDAO;
import com.mdy.dzs.data.dao.datas.BattleDAO;
import com.mdy.dzs.data.dao.datas.BattleSkillDAO;
import com.mdy.dzs.data.dao.datas.BiwuJiangliDAO;
import com.mdy.dzs.data.dao.datas.BiwuShopDAO;
import com.mdy.dzs.data.dao.datas.BossDAO;
import com.mdy.dzs.data.dao.datas.BossguwuDAO;
import com.mdy.dzs.data.dao.datas.BuffDAO;
import com.mdy.dzs.data.dao.datas.CardDAO;
import com.mdy.dzs.data.dao.datas.CardenDAO;
import com.mdy.dzs.data.dao.datas.ChongzhiDAO;
import com.mdy.dzs.data.dao.datas.ChuShiDAO;
import com.mdy.dzs.data.dao.datas.CollectDAO;
import com.mdy.dzs.data.dao.datas.ConfigBiwuDAO;
import com.mdy.dzs.data.dao.datas.ConfigYaBiaoDAO;
import com.mdy.dzs.data.dao.datas.CritDAO;
import com.mdy.dzs.data.dao.datas.DailyMissionRewardDAO;
import com.mdy.dzs.data.dao.datas.EliteBattleDAO;
import com.mdy.dzs.data.dao.datas.EquipSuitDAO;
import com.mdy.dzs.data.dao.datas.EquipenDAO;
import com.mdy.dzs.data.dao.datas.FieldDAO;
import com.mdy.dzs.data.dao.datas.GuangBoDAO;
import com.mdy.dzs.data.dao.datas.HotelDAO;
import com.mdy.dzs.data.dao.datas.ItemDAO;
import com.mdy.dzs.data.dao.datas.JiBanDAO;
import com.mdy.dzs.data.dao.datas.JingMaiDAO;
import com.mdy.dzs.data.dao.datas.KongFuDAO;
import com.mdy.dzs.data.dao.datas.LevelDAO;
import com.mdy.dzs.data.dao.datas.LevelGiftDAO;
import com.mdy.dzs.data.dao.datas.LoginDayGiftDAO;
import com.mdy.dzs.data.dao.datas.LunjianDAO;
import com.mdy.dzs.data.dao.datas.LunjianProbDAO;
import com.mdy.dzs.data.dao.datas.MingJiangDAO;
import com.mdy.dzs.data.dao.datas.MissionDefineDAO;
import com.mdy.dzs.data.dao.datas.MoBanDAO;
import com.mdy.dzs.data.dao.datas.MoneyDAO;
import com.mdy.dzs.data.dao.datas.NameDAO;
import com.mdy.dzs.data.dao.datas.NoticeDAO;
import com.mdy.dzs.data.dao.datas.NpcDAO;
import com.mdy.dzs.data.dao.datas.OnlineGiftDAO;
import com.mdy.dzs.data.dao.datas.OpenDAO;
import com.mdy.dzs.data.dao.datas.PanDuanXiTongChanChuDAO;
import com.mdy.dzs.data.dao.datas.ProbDAO;
import com.mdy.dzs.data.dao.datas.PseudoDAO;
import com.mdy.dzs.data.dao.datas.QingLongBossDAO;
import com.mdy.dzs.data.dao.datas.RefineDAO;
import com.mdy.dzs.data.dao.datas.RouletteItemDAO;
import com.mdy.dzs.data.dao.datas.RouletteProbDAO;
import com.mdy.dzs.data.dao.datas.ShangXianSheDingDAO;
import com.mdy.dzs.data.dao.datas.ShenMiDAO;
import com.mdy.dzs.data.dao.datas.ShenTongDAO;
import com.mdy.dzs.data.dao.datas.ShengJiDAO;
import com.mdy.dzs.data.dao.datas.SignGiftDAO;
import com.mdy.dzs.data.dao.datas.SoulDAO;
import com.mdy.dzs.data.dao.datas.SuperBattleDAO;
import com.mdy.dzs.data.dao.datas.TalentDAO;
import com.mdy.dzs.data.dao.datas.TouZiDAO;
import com.mdy.dzs.data.dao.datas.TournamentDAO;
import com.mdy.dzs.data.dao.datas.UnionConfigDAO;
import com.mdy.dzs.data.dao.datas.VipDAO;
import com.mdy.dzs.data.dao.datas.ViplevelDAO;
import com.mdy.dzs.data.dao.datas.WorldDAO;
import com.mdy.dzs.data.dao.datas.XuNiWanJiaDAO;
import com.mdy.dzs.data.dao.datas.YabiaoJiangliDAO;
import com.mdy.dzs.data.dao.datas.YueKaShouChongGiftDAO;
import com.mdy.dzs.data.dao.datas.YueQianDAO;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.Application;
import com.mdy.sharp.container.res.ds.ConnectionResource;

/**
 * data服务器
 * 
 * @author fidel
 * 
 */
public class DataApplication extends Application {

	private static DataApplication ins = null;

	public static final String DATA_SOURCE = "dbResource";

	public static final String CLUSTER_DATA_SYSTEM = "DataSystem";
	/**
	 * 账号用data
	 */
	public static String CLUSTER_SDATA_SYSTEM = "SDataSystem";
	public static final String CLUSTER_GAME_SYSTEM = "GameSystem";
	//
	private DAOFactory daoFactory;

	public static DataApplication Instance() {
		if (ins == null) {
			ins = new DataApplication();
		}
		return ins;
	}

	/**
	 * 此函数在服务器启动过程中调用
	 */
	@Override
	public void startUp() {
		setupDAOFactory();
		setupAO();
		setupJob();
	}

	/**
	 * 此函数在服务器停止过程中调用
	 */
	@Override
	public void shutDown() {
	}

	// --------------------------------------------------------------------------
	private void setupJob() {
	}

	// --------------------------------------------------------------------------
	private void setupDAOFactory() {
		ConnectionResource cr = (ConnectionResource) Container.get().getResource(DATA_SOURCE);

		final ServerInfoDAO serverInfoDAO = new ServerInfoDAO();
		serverInfoDAO.setConnectionResource(cr);

		final ChargeOrderDAO chargeOrderDAO = new ChargeOrderDAO();
		chargeOrderDAO.setConnectionResource(cr);

		final NoticeDAO noticeDAO = new NoticeDAO();
		noticeDAO.setConnectionResource(cr);

		final ServicePartnerDAO servicePartnerDAO = new ServicePartnerDAO();
		servicePartnerDAO.setConnectionResource(cr);

		final AccountDAO accountDAO = new AccountDAO();
		accountDAO.setConnectionResource(cr);

		final VersionDAO versionDAO = new VersionDAO();
		versionDAO.setConnectionResource(cr);

		final RewardCenterDAO rewardCenterDAO = new RewardCenterDAO();
		rewardCenterDAO.setConnectionResource(cr);

		final UnionConfigDAO unionConfigDAO = new UnionConfigDAO();
		unionConfigDAO.setConnectionResource(cr);
		/////////////////////////////////////////////////
		final LevelDAO levelDAO = new LevelDAO();
		levelDAO.setConnectionResource(cr);

		final CardDAO cardDAO = new CardDAO();
		cardDAO.setConnectionResource(cr);

		final BuffDAO buffDAO = new BuffDAO();
		buffDAO.setConnectionResource(cr);

		final BattleDAO battleDAO = new BattleDAO();
		battleDAO.setConnectionResource(cr);

		final BattleSkillDAO battleSkillDAO = new BattleSkillDAO();
		battleSkillDAO.setConnectionResource(cr);

		final JiBanDAO jiBanDAO = new JiBanDAO();
		jiBanDAO.setConnectionResource(cr);

		final JingMaiDAO jingMaiDAO = new JingMaiDAO();
		jingMaiDAO.setConnectionResource(cr);

		final ShengJiDAO shengJiDAO = new ShengJiDAO();
		shengJiDAO.setConnectionResource(cr);

		final NpcDAO npcDAO = new NpcDAO();
		npcDAO.setConnectionResource(cr);

		final NameDAO nameDAO = new NameDAO();
		nameDAO.setConnectionResource(cr);

		final ItemDAO itemDAO = new ItemDAO();
		itemDAO.setConnectionResource(cr);

		final MoneyDAO moneyDAO = new MoneyDAO();
		moneyDAO.setConnectionResource(cr);

		final ProbDAO probDAO = new ProbDAO();
		probDAO.setConnectionResource(cr);

		final MoBanDAO moBanDAO = new MoBanDAO();
		moBanDAO.setConnectionResource(cr);

		final ChuShiDAO chuShiDAO = new ChuShiDAO();
		chuShiDAO.setConnectionResource(cr);

		final ShenTongDAO shenTongDAO = new ShenTongDAO();
		shenTongDAO.setConnectionResource(cr);

		final TalentDAO talentDAO = new TalentDAO();
		talentDAO.setConnectionResource(cr);

		final CardenDAO cardenDAO = new CardenDAO();
		cardenDAO.setConnectionResource(cr);

		final EquipenDAO equipenDAO = new EquipenDAO();
		equipenDAO.setConnectionResource(cr);
		final CritDAO critDAO = new CritDAO();
		critDAO.setConnectionResource(cr);
		final BaptizeDAO baptizeDAO = new BaptizeDAO();
		baptizeDAO.setConnectionResource(cr);
		final RefineDAO refineDAO = new RefineDAO();
		refineDAO.setConnectionResource(cr);
		final KongFuDAO kongFuDAO = new KongFuDAO();
		kongFuDAO.setConnectionResource(cr);
		final BagDAO bagDAO = new BagDAO();
		bagDAO.setConnectionResource(cr);
		final PanDuanXiTongChanChuDAO panDuanXiTongChanChuDAO = new PanDuanXiTongChanChuDAO();
		panDuanXiTongChanChuDAO.setConnectionResource(cr);
		final VipDAO vipDAO = new VipDAO();
		vipDAO.setConnectionResource(cr);
		final WorldDAO worldDAO = new WorldDAO();
		worldDAO.setConnectionResource(cr);
		final FieldDAO fieldDAO = new FieldDAO();
		fieldDAO.setConnectionResource(cr);
		final XuNiWanJiaDAO xuNiWanJiaDAO = new XuNiWanJiaDAO();
		xuNiWanJiaDAO.setConnectionResource(cr);
		final CollectDAO collectDAO = new CollectDAO();
		collectDAO.setConnectionResource(cr);
		final SoulDAO soulDAO = new SoulDAO();
		soulDAO.setConnectionResource(cr);

		final GuangBoDAO guangBoDAO = new GuangBoDAO();
		guangBoDAO.setConnectionResource(cr);
		final OpenDAO openDAO = new OpenDAO();
		openDAO.setConnectionResource(cr);
		final HotelDAO hotelDAO = new HotelDAO();
		hotelDAO.setConnectionResource(cr);
		final ShangXianSheDingDAO shangXianSheDingDAO = new ShangXianSheDingDAO();
		shangXianSheDingDAO.setConnectionResource(cr);
		final EliteBattleDAO eliteBattleDAO = new EliteBattleDAO();
		eliteBattleDAO.setConnectionResource(cr);
		final ActivityBattleDAO activityBattleDAO = new ActivityBattleDAO();
		activityBattleDAO.setConnectionResource(cr);
		final ArenaDAO arenaDAO = new ArenaDAO();
		arenaDAO.setConnectionResource(cr);
		final SignGiftDAO signGiftDAO = new SignGiftDAO();
		signGiftDAO.setConnectionResource(cr);
		final OnlineGiftDAO onlineGiftDAO = new OnlineGiftDAO();
		onlineGiftDAO.setConnectionResource(cr);
		final LevelGiftDAO levelGiftDAO = new LevelGiftDAO();
		levelGiftDAO.setConnectionResource(cr);
		final LoginDayGiftDAO loginDayGiftDAO = new LoginDayGiftDAO();
		loginDayGiftDAO.setConnectionResource(cr);
		final MingJiangDAO mingJiangDAO = new MingJiangDAO();
		mingJiangDAO.setConnectionResource(cr);
		final LunjianDAO lunjianDAO = new LunjianDAO();
		lunjianDAO.setConnectionResource(cr);
		final LunjianProbDAO lunjianProbDAO = new LunjianProbDAO();
		lunjianProbDAO.setConnectionResource(cr);
		final ChongzhiDAO chongzhiDAO = new ChongzhiDAO();
		chongzhiDAO.setConnectionResource(cr);
		final ViplevelDAO vipLevelDAO = new ViplevelDAO();
		vipLevelDAO.setConnectionResource(cr);
		final YueKaShouChongGiftDAO yuekashouchonggiftDAO = new YueKaShouChongGiftDAO();
		yuekashouchonggiftDAO.setConnectionResource(cr);
		final ActivityConfigsDAO activityConfigsDAO = new ActivityConfigsDAO();
		activityConfigsDAO.setConnectionResource(cr);
		final ShenMiDAO shenmiDAO = new ShenMiDAO();
		shenmiDAO.setConnectionResource(cr);
		final BossguwuDAO bossguwuDAO = new BossguwuDAO();
		bossguwuDAO.setConnectionResource(cr);
		final BossDAO bossDAO = new BossDAO();
		bossDAO.setConnectionResource(cr);
		final PseudoDAO pseudoDAO = new PseudoDAO();
		pseudoDAO.setConnectionResource(cr);
		final EquipSuitDAO equipSuitDAO = new EquipSuitDAO();
		equipSuitDAO.setConnectionResource(cr);
		final YueQianDAO yueQianDAO = new YueQianDAO();
		yueQianDAO.setConnectionResource(cr);
		final TouZiDAO touZiDAO = new TouZiDAO();
		touZiDAO.setConnectionResource(cr);
		final MissionDefineDAO missionDefineDAO = new MissionDefineDAO();
		missionDefineDAO.setConnectionResource(cr);
		final QingLongBossDAO qingLongBossDAO = new QingLongBossDAO();
		qingLongBossDAO.setConnectionResource(cr);
		final DailyMissionRewardDAO dailyMissionRewardDAO = new DailyMissionRewardDAO();
		dailyMissionRewardDAO.setConnectionResource(cr);

		final BiwuJiangliDAO biwuJiangliDAO = new BiwuJiangliDAO();
		biwuJiangliDAO.setConnectionResource(cr);

		final BiwuShopDAO biwuShopDAO = new BiwuShopDAO();
		biwuShopDAO.setConnectionResource(cr);

		final TournamentDAO tournamentDAO = new TournamentDAO();
		tournamentDAO.setConnectionResource(cr);

		final ConfigBiwuDAO configBiwuDAO = new ConfigBiwuDAO();
		configBiwuDAO.setConnectionResource(cr);

		final ConfigYaBiaoDAO configYaBiaoDAO = new ConfigYaBiaoDAO();
		configYaBiaoDAO.setConnectionResource(cr);

		final YabiaoJiangliDAO yabiaoJiangliDAO = new YabiaoJiangliDAO();
		yabiaoJiangliDAO.setConnectionResource(cr);

		final RouletteItemDAO rouletteItemDAO = new RouletteItemDAO();
		rouletteItemDAO.setConnectionResource(cr);

		final RouletteProbDAO rouletteProbDAO = new RouletteProbDAO();
		rouletteProbDAO.setConnectionResource(cr);

		final ActivityExchangeExpDAO activityExchangeExpDAO = new ActivityExchangeExpDAO();
		activityExchangeExpDAO.setConnectionResource(cr);

		final ActivityLimitShopDataDAO activityLimitShopDAO = new ActivityLimitShopDataDAO();
		activityLimitShopDAO.setConnectionResource(cr);

		final SuperBattleDAO superBattleDAO = new SuperBattleDAO();
		superBattleDAO.setConnectionResource(cr);
		//
		daoFactory = new DAOFactory() {
			@Override
			public ServerInfoDAO serverInfoDAO() {
				return serverInfoDAO;
			}

			@Override
			public LevelDAO levelDAO() {
				return levelDAO;
			}

			@Override
			public CardDAO cardDAO() {
				return cardDAO;
			}

			@Override
			public BuffDAO buffDAO() {
				return buffDAO;
			}

			@Override
			public BattleDAO battleDAO() {
				return battleDAO;
			}

			@Override
			public BattleSkillDAO battleSkillDAO() {
				return battleSkillDAO;
			}

			@Override
			public JiBanDAO jiBanDAO() {
				return jiBanDAO;
			}

			@Override
			public JingMaiDAO jingMaiDAO() {
				return jingMaiDAO;
			}

			@Override
			public ShengJiDAO shengJiDAO() {
				return shengJiDAO;
			}

			@Override
			public NpcDAO npcDAO() {
				return npcDAO;
			}

			@Override
			public NameDAO nameDAO() {
				return nameDAO;
			}

			@Override
			public ItemDAO itemDAO() {
				return itemDAO;
			}

			@Override
			public MoneyDAO moneyDAO() {
				return moneyDAO;
			}

			@Override
			public ProbDAO probDAO() {
				return probDAO;
			}

			@Override
			public MoBanDAO moBanDAO() {
				return moBanDAO;
			}

			@Override
			public ChuShiDAO chuShiDAO() {
				return chuShiDAO;
			}

			@Override
			public ShenTongDAO shenTongDAO() {
				return shenTongDAO;
			}

			@Override
			public TalentDAO talentDAO() {
				return talentDAO;
			}

			@Override
			public CardenDAO cardenDAO() {
				return cardenDAO;
			}

			@Override
			public EquipenDAO equipenDAO() {
				return equipenDAO;
			}

			@Override
			public CritDAO critDAO() {
				return critDAO;
			}

			@Override
			public BaptizeDAO baptizeDAO() {
				return baptizeDAO;
			}

			@Override
			public RefineDAO refineDAO() {
				return refineDAO;
			}

			@Override
			public KongFuDAO kongFuDAO() {
				return kongFuDAO;
			}

			@Override
			public BagDAO bagDAO() {
				return bagDAO;
			}

			@Override
			public PanDuanXiTongChanChuDAO panDuanXiTongChanChuDAO() {
				return panDuanXiTongChanChuDAO;
			}

			@Override
			public VipDAO vipDAO() {
				return vipDAO;
			}

			@Override
			public WorldDAO worldDAO() {
				return worldDAO;
			}

			@Override
			public FieldDAO fieldDAO() {
				return fieldDAO;
			}

			@Override
			public XuNiWanJiaDAO xuNiWanJiaDAO() {
				return xuNiWanJiaDAO;
			}

			@Override
			public CollectDAO collectDAO() {
				return collectDAO;
			}

			@Override
			public SoulDAO soulDAO() {
				return soulDAO;
			}

			@Override
			public GuangBoDAO guangBoDAO() {
				return guangBoDAO;
			}

			@Override
			public OpenDAO openDAO() {
				return openDAO;
			}

			@Override
			public HotelDAO hotelDAO() {
				return hotelDAO;
			}

			@Override
			public ShangXianSheDingDAO shangXianSheDingDAO() {
				return shangXianSheDingDAO;
			}

			@Override
			public EliteBattleDAO eliteBattleDAO() {
				return eliteBattleDAO;
			}

			@Override
			public ActivityBattleDAO activityBattleDAO() {
				return activityBattleDAO;
			}

			@Override
			public ArenaDAO arenaDAO() {
				return arenaDAO;
			}

			@Override
			public SignGiftDAO signGiftDAO() {
				return signGiftDAO;
			}

			@Override
			public OnlineGiftDAO onlineGiftDAO() {
				return onlineGiftDAO;
			}

			@Override
			public LevelGiftDAO levelGiftDAO() {
				return levelGiftDAO;
			}

			@Override
			public LoginDayGiftDAO loginDayGiftDAO() {
				return loginDayGiftDAO;
			}

			@Override
			public MingJiangDAO mingJiangDAO() {
				return mingJiangDAO;
			}

			@Override
			public ChongzhiDAO chongzhiDAO() {
				return chongzhiDAO;
			}

			@Override
			public ChargeOrderDAO chargeOrderDAO() {
				return chargeOrderDAO;
			}

			@Override
			public LunjianDAO lunjianDAO() {
				return lunjianDAO;
			}

			@Override
			public LunjianProbDAO lunjianProbDAO() {
				return lunjianProbDAO;
			}

			@Override
			public ViplevelDAO viplevelDAO() {
				return vipLevelDAO;
			}

			@Override
			public YueKaShouChongGiftDAO yuekashouchonggiftDAO() {
				return yuekashouchonggiftDAO;
			}

			@Override
			public ActivityConfigsDAO activityConfigsDAO() {
				return activityConfigsDAO;
			}

			@Override
			public NoticeDAO noticeDAO() {
				return noticeDAO;
			}

			@Override
			public ShenMiDAO shenmiDAO() {
				return shenmiDAO;
			}

			@Override
			public BossguwuDAO bossguwuDAO() {
				return bossguwuDAO;
			}

			@Override
			public BossDAO bossDAO() {
				return bossDAO;
			}

			@Override
			public PseudoDAO pseudoDAO() {
				return pseudoDAO;
			}

			@Override
			public ServicePartnerDAO servicePartnerDAO() {
				return servicePartnerDAO;
			}

			@Override
			public AccountDAO accountDAO() {
				return accountDAO;
			}

			@Override
			public EquipSuitDAO equipSuitDAO() {
				return equipSuitDAO;
			}

			@Override
			public YueQianDAO yueQianDAO() {
				return yueQianDAO;
			}

			@Override
			public TouZiDAO touZiDAO() {
				return touZiDAO;
			}

			@Override
			public VersionDAO versionDAO() {
				return versionDAO;
			}

			@Override
			public RewardCenterDAO rewardCenterDAO() {
				return rewardCenterDAO;
			}

			@Override
			public MissionDefineDAO missionDefineDAO() {
				return missionDefineDAO;
			}

			@Override
			public UnionConfigDAO loadUnionConfigDAO() {
				return unionConfigDAO;
			}

			@Override
			public QingLongBossDAO qingLongBossDAO() {
				return qingLongBossDAO;
			}

			@Override
			public DailyMissionRewardDAO dailyMissionRewardDAO() {
				return dailyMissionRewardDAO;
			}

			@Override
			public BiwuJiangliDAO biwuJiangliDAO() {
				return biwuJiangliDAO;
			}

			@Override
			public BiwuShopDAO biwuShopDAO() {
				return biwuShopDAO;
			}

			@Override
			public TournamentDAO tournamentDAO() {
				return tournamentDAO;
			}

			@Override
			public ConfigBiwuDAO configBiwuDAO() {
				return configBiwuDAO;
			}

			@Override
			public ConfigYaBiaoDAO configYaBiaoDAO() {
				return configYaBiaoDAO;
			}

			@Override
			public YabiaoJiangliDAO yabiaoJiangliDAO() {
				return yabiaoJiangliDAO;
			}

			@Override
			public RouletteItemDAO rouletteItemDAO() {
				return rouletteItemDAO;
			}

			@Override
			public RouletteProbDAO rouletteProbDAO() {
				return rouletteProbDAO;
			}

			@Override
			public ActivityExchangeExpDAO activityExchangeExpDAO() {
				return activityExchangeExpDAO;
			}

			@Override
			public ActivityLimitShopDataDAO activityLimitShopDAO() {
				return activityLimitShopDAO;
			}

			@Override
			public SuperBattleDAO superBattleDAO() {
				return superBattleDAO;
			}

		};
		//
	}

	// --------------------------------------------------------------------------
	//
	private DataAO dataAO;
	private ServerInfoAO serverInfoAO;
	private ChargeOrderAO chargeOrderAO;
	private NoticeAO noticeAO;
	private ServicePartnerAO servicePartnerAO;
	private AccountAO accountAO;
	private VersionAO versionAO;
	private RewardCenterAO rewardCenterAO;

	//
	private void setupAO() {

		dataAO = new DataAO();
		dataAO.setDaoFactory(daoFactory);
		// dataAO.setNotificationManager(notificationManager);

		serverInfoAO = new ServerInfoAO();
		serverInfoAO.setDaoFactory(daoFactory);

		chargeOrderAO = new ChargeOrderAO();
		chargeOrderAO.setDaoFactory(daoFactory);
		// chargeOrderAO.setNotificationManager(notificationManager);

		noticeAO = new NoticeAO();
		noticeAO.setDaoFactory(daoFactory);

		servicePartnerAO = new ServicePartnerAO();
		servicePartnerAO.setDaoFactory(daoFactory);

		accountAO = new AccountAO();
		accountAO.setDaoFactory(daoFactory);

		versionAO = new VersionAO();
		versionAO.setDaoFactory(daoFactory);

		rewardCenterAO = new RewardCenterAO();
		rewardCenterAO.setDaoFactory(daoFactory);

	}

	// --------------------------------------------------------------------------

	public DataAO dataAO() {
		return dataAO;
	}

	public ServerInfoAO serverInfoAO() {
		return serverInfoAO;
	}

	public ChargeOrderAO chargeOrderAO() {
		return chargeOrderAO;
	}

	public NoticeAO noticeAO() {
		return noticeAO;
	}

	public ServicePartnerAO servicePartnerAO() {
		return servicePartnerAO;
	}

	public AccountAO accountAO() {
		return accountAO;
	}

	public VersionAO versionAO() {
		return versionAO;
	}

	public RewardCenterAO rewardCenterAO() {
		return rewardCenterAO;
	}

}
