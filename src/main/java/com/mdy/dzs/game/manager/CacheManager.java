/**
 * 
 */
package com.mdy.dzs.game.manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.DataAction;
import com.mdy.dzs.data.action.VersionAction;
import com.mdy.dzs.data.dao.datas.ProbDAO;
import com.mdy.dzs.data.domain.activity.limitshop.LimitShopData;
import com.mdy.dzs.data.domain.activity.roulette.RouletteItem;
import com.mdy.dzs.data.domain.activity.roulette.RouletteProb;
import com.mdy.dzs.data.domain.activity.exchange.ActivityExchangeExp;
import com.mdy.dzs.data.domain.battle.Battle;
import com.mdy.dzs.data.domain.battle.BattleSkill;
import com.mdy.dzs.data.domain.battle.Buff;
import com.mdy.dzs.data.domain.battle.Npc;
import com.mdy.dzs.data.domain.biwu.BiwuJiangli;
import com.mdy.dzs.data.domain.biwu.BiwuShop;
import com.mdy.dzs.data.domain.biwu.Tournament;
import com.mdy.dzs.data.domain.boss.Boss;
import com.mdy.dzs.data.domain.boss.Bossguwu;
import com.mdy.dzs.data.domain.broad.GuangBo;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.card.CardClsLimit;
import com.mdy.dzs.data.domain.card.Carden;
import com.mdy.dzs.data.domain.card.ShangXianSheDing;
import com.mdy.dzs.data.domain.challengebattle.ActivityBattle;
import com.mdy.dzs.data.domain.challengebattle.ActivityBattleJFJP;
import com.mdy.dzs.data.domain.challengebattle.EliteBattle;
import com.mdy.dzs.data.domain.chongzhi.Chongzhi;
import com.mdy.dzs.data.domain.dart.YabiaoJiangli;
import com.mdy.dzs.data.domain.equipment.Baptize;
import com.mdy.dzs.data.domain.equipment.EquipSuit;
import com.mdy.dzs.data.domain.equipment.Equipen;
import com.mdy.dzs.data.domain.gift.YueKaShouChongGift;
import com.mdy.dzs.data.domain.gong.KongFu;
import com.mdy.dzs.data.domain.gong.Refine;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.Money;
import com.mdy.dzs.data.domain.item.Prob;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.item.ProbVal;
import com.mdy.dzs.data.domain.notice.Notice;
import com.mdy.dzs.data.domain.packet.Bag;
import com.mdy.dzs.data.domain.packet.PanDuanXiTongChanChu;
import com.mdy.dzs.data.domain.road.MingJiang;
import com.mdy.dzs.data.domain.road.RoadCardAchieve;
import com.mdy.dzs.data.domain.road.RoadCardLevel;
import com.mdy.dzs.data.domain.robot.MoBan;
import com.mdy.dzs.data.domain.robot.Name;
import com.mdy.dzs.data.domain.role.ChuShi;
import com.mdy.dzs.data.domain.role.JiBan;
import com.mdy.dzs.data.domain.role.JingMai;
import com.mdy.dzs.data.domain.role.Level;
import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.data.domain.role.ShengJi;
import com.mdy.dzs.data.domain.shenmi.ShenMi;
import com.mdy.dzs.data.domain.shentong.ShenTong;
import com.mdy.dzs.data.domain.shentong.Talent;
import com.mdy.dzs.data.domain.shop.Hotel;
import com.mdy.dzs.data.domain.shop.Pseudo;
import com.mdy.dzs.data.domain.superbattle.SuperBattle;
import com.mdy.dzs.data.domain.sword.Lunjian;
import com.mdy.dzs.data.domain.sword.LunjianProb;
import com.mdy.dzs.data.domain.touzi.TouZi;
import com.mdy.dzs.data.domain.union.QingLongBoss;
import com.mdy.dzs.data.domain.union.UnionAttr;
import com.mdy.dzs.data.domain.union.UnionShop1;
import com.mdy.dzs.data.domain.union.UnionShop2;
import com.mdy.dzs.data.domain.union.UnionWorkShop;
import com.mdy.dzs.data.domain.version.Version;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.data.domain.viplevel.VipCrit;
import com.mdy.dzs.data.domain.viplevel.Viplevel;
import com.mdy.dzs.data.domain.world.Field;
import com.mdy.dzs.data.domain.world.World;
import com.mdy.dzs.data.domain.yuan.Collect;
import com.mdy.dzs.data.domain.yuan.Soul;
import com.mdy.dzs.game.SystemApplication;
import com.mdy.dzs.game.action.RoleActivityAction;
import com.mdy.dzs.game.ao.RoleActivityAO;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.activity.ActivityConfig;
import com.mdy.dzs.game.domain.battle.LvNum;
import com.mdy.dzs.game.domain.system.SystemStatus;
import com.mdy.dzs.game.exception.ActivityException;
import com.mdy.dzs.game.exception.DataException;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.container.res.cache.CacheException;
import com.mdy.sharp.container.res.cache.CacheResource;
import com.mdy.sharp.container.res.cache.RedisResource;
import com.mdy.sharp.container.res.ds.ConnectionResource;

/**
 * @author Administrator
 * @param <yuekashouchonggiftMap>
 *
 */
public class CacheManager {

	//
	final static Logger logger = LoggerFactory.get(CacheManager.class);

	public static Boolean loadDataByGame = false;

	//
	private CacheResource cache;

	protected DataAction dataAction;

	protected VersionAction versionAction;
	//////////////////////////////////////////////////////////////////////////////////

	private ProbDAO probDAO;
	//////////////////////////////////////////////////////////////////////////////////
	private Map<Object, Object> mapping = new HashMap<Object, Object>();
	//
	private Map<Integer, Level> levelList = new HashMap<Integer, Level>();
	private Map<Integer, Card> cardMap = new HashMap<Integer, Card>();
	private Map<Integer, Buff> buffMap = new HashMap<Integer, Buff>();
	private Map<Integer, Battle> battleMap = new HashMap<Integer, Battle>();
	private Map<Integer, BattleSkill> battleSkillMap = new HashMap<Integer, BattleSkill>();
	private Map<Integer, JiBan> jiBanMap = new HashMap<Integer, JiBan>();
	private Map<Integer, JingMai> jingMaiMap = new HashMap<Integer, JingMai>();
	private Map<Integer, ShengJi> shengJiMap = new HashMap<Integer, ShengJi>();
	private Map<Integer, Npc> npcMap = new HashMap<Integer, Npc>();
	private Map<Integer, Name> nameMap = new HashMap<Integer, Name>();
	private Map<Integer, Item> itemMap = new HashMap<Integer, Item>();
	private Map<Integer, Money> moneyMap = new HashMap<Integer, Money>();

	private Map<Integer, MoBan> moBanMap = new HashMap<Integer, MoBan>();
	private Map<Integer, ChuShi> chuShiMap = new HashMap<Integer, ChuShi>();
	private Map<Integer, ShenTong> shenTongMap = new HashMap<Integer, ShenTong>();
	private Map<Integer, Talent> talentMap = new HashMap<Integer, Talent>();
	private Map<Integer, Carden> cardenMap = new HashMap<Integer, Carden>();

	private Map<Integer, Equipen> equipenMap = new HashMap<Integer, Equipen>();
	private Map<Integer, VipCrit> critMap = new HashMap<Integer, VipCrit>();
	private Map<Integer, Baptize> baptizeMap = new HashMap<Integer, Baptize>();
	private Map<Integer, Refine> refineMap = new HashMap<Integer, Refine>();
	private Map<Integer, KongFu> kongFuMap = new HashMap<Integer, KongFu>();
	private Map<Integer, Bag> bagMap = new HashMap<Integer, Bag>();
	private Map<Integer, PanDuanXiTongChanChu> panDuanXiTongChanChuMap = new HashMap<Integer, PanDuanXiTongChanChu>();
	private Map<Integer, Vip> vipMap = new HashMap<Integer, Vip>();
	private Map<Integer, World> worldMap = new HashMap<Integer, World>();
	private Map<Integer, Field> fieldMap = new HashMap<Integer, Field>();
	private Map<Integer, Collect> collectMap = new HashMap<Integer, Collect>();
	private Map<Integer, Soul> soulMap = new HashMap<Integer, Soul>();
	private Map<Integer, GuangBo> guangBoMap = new HashMap<Integer, GuangBo>();
	private Map<Integer, Open> openMap = new HashMap<Integer, Open>();
	private Map<Integer, Hotel> hotelMap = new HashMap<Integer, Hotel>();
	private Map<Integer, EliteBattle> eliteBattleMap = new HashMap<Integer, EliteBattle>();
	private Map<Integer, ActivityBattle> activityBattleMap = new HashMap<Integer, ActivityBattle>();
	private Map<Integer, MingJiang> mingJiangMap = new HashMap<Integer, MingJiang>();
	private Map<Integer, RoadCardLevel> roadCardLevelMap = new LinkedHashMap<Integer, RoadCardLevel>();
	private Map<Integer, RoadCardAchieve> roadCardAchieveMap = new LinkedHashMap<Integer, RoadCardAchieve>();
	private Map<Integer, LunjianProb> lunjianProbMap = new LinkedHashMap<Integer, LunjianProb>();
	private Map<Integer, Chongzhi> chongzhiHashMap = new HashMap<Integer, Chongzhi>();
	private Map<Integer, YueKaShouChongGift> yuekashouchonggiftMap = new HashMap<Integer, YueKaShouChongGift>();
	private Map<Integer, Viplevel> viplevelMap = new HashMap<Integer, Viplevel>();

	private Map<Integer, Bossguwu> bossguwuMap = new HashMap<Integer, Bossguwu>();
	private Map<Integer, Boss> bossMap = new HashMap<Integer, Boss>();
	private Map<Integer, CardClsLimit> cardClsLimitMap = new HashMap<Integer, CardClsLimit>();
	private Map<Integer, EquipSuit> equipSuitMap = new HashMap<Integer, EquipSuit>();
	private Map<Integer, TouZi> touziMap = new HashMap<Integer, TouZi>();
	private Map<Integer, UnionAttr> unionMap = new HashMap<Integer, UnionAttr>();
	private Map<Integer, UnionShop1> unionShop1Map = new HashMap<Integer, UnionShop1>();
	private Map<Integer, LimitShopData> LimitShopMap = new HashMap<Integer, LimitShopData>();
	private Map<Integer, UnionWorkShop> UnionWorkShopMap = new HashMap<Integer, UnionWorkShop>();
	private Map<Integer, QingLongBoss> qingLongBossMap = new HashMap<Integer, QingLongBoss>();
	private Map<Integer, BiwuShop> biwuShopMap = new HashMap<Integer, BiwuShop>();
	private Map<Integer, Tournament> tournamentMap = new HashMap<Integer, Tournament>();
	private Map<String, List<Integer>> configBiwuMap = new HashMap<String, List<Integer>>();
	private Map<String, List<Integer>> configYaBiaoMap = new HashMap<String, List<Integer>>();
	private Map<Integer, YabiaoJiangli> yabiaoJiangliMap = new HashMap<Integer, YabiaoJiangli>();
	private Map<Integer, RouletteItem> rouletteItemMap = new HashMap<Integer, RouletteItem>();
	private Map<Integer, RouletteProb> rouletteProbMap = new HashMap<Integer, RouletteProb>();
	private Map<Integer, SuperBattle> superBattleMap = new HashMap<Integer, SuperBattle>();

	private List<ShangXianSheDing> shangxianList = new ArrayList<ShangXianSheDing>();
	private List<ActivityBattleJFJP> jiefujipinList = new ArrayList<ActivityBattleJFJP>();
	private List<Lunjian> lunjianList = new ArrayList<Lunjian>();
	private List<BiwuJiangli> biwuJiangli = new ArrayList<BiwuJiangli>();
	private List<BiwuShop> biwuShopList = new ArrayList<BiwuShop>();
	private List<UnionShop2> unionShop2List = new ArrayList<UnionShop2>();

	private List<GuangBo> guangBoList = Collections.synchronizedList(new ArrayList<GuangBo>());
	private Map<Integer, Integer> activityStatus = Collections.synchronizedMap(new HashMap<Integer, Integer>());
	private List<Notice> noticeList = Collections.synchronizedList(new ArrayList<Notice>());

	// private List<UnionFBData> unionFBList = new ArrayList<UnionFBData>();
	private List<LimitShopData> limitShopList = new ArrayList<LimitShopData>();
	// sync
	private Map<Integer, Pseudo> pseudoMap = Collections.synchronizedMap(new HashMap<Integer, Pseudo>());
	private Map<String, String> unionConfig = Collections.synchronizedMap(new HashMap<String, String>());
	private Map<Integer, ShenMi> shenmiMap = Collections.synchronizedMap(new HashMap<Integer, ShenMi>());
	private Map<Integer, Prob> probMap = Collections.synchronizedMap(new HashMap<Integer, Prob>());

	private Map<Integer, ActivityExchangeExp> exchExpMap = Collections
			.synchronizedMap(new HashMap<Integer, ActivityExchangeExp>());

	///////////////////////////////////////////////////////////////////////////////////////////////
	private List<Version> serverVersions = null;
	private String curServerVersion = "";

	public CacheManager() {
		dataAction = Container.get().createRemote(DataAction.class, DataApplication.CLUSTER_SDATA_SYSTEM);
		versionAction = Container.get().createRemote(VersionAction.class, DataApplication.CLUSTER_SDATA_SYSTEM);
		init();
	}

	//
	private void init() {
		cache = (CacheResource) new RedisResource();

		ConnectionResource cr = (ConnectionResource) Container.get().getResource(SystemApplication.DATA_SOURCE);
		if (loadDataByGame) {
			probDAO = new ProbDAO();
			probDAO.setConnectionResource(cr);
		}
		FightMain.dataManager = this;
		initData();
	}

	private void add(String key, int expireTime, Object o) {
		try {
			cache.add(key, expireTime, o);
		} catch (CacheException e) {
			logger.error("cache add failed.key:" + key);
		}
	}

	public void delete(String key) {
		try {
			cache.delete(key);
		} catch (CacheException e) {
			logger.error("cache add failed.key:" + key);
		}
	}

	public void initData() {
		Date start = new Date();
		try {
			generateDatas(dataAction.queryLevelDatas(), levelList);
			generateDatas(dataAction.queryCardDatas(), cardMap);
			generateDatas(dataAction.queryBuffDatas(), buffMap);
			generateDatas(dataAction.queryBattleDatas(), battleMap);
			generateDatas(dataAction.queryBattleSkillDatas(), battleSkillMap);
			generateDatas(dataAction.queryJiBanDatas(), jiBanMap);
			generateDatas(dataAction.queryJingMaiDatas(), jingMaiMap);
			generateDatas(dataAction.queryShengJiDatas(), shengJiMap);
			generateDatas(dataAction.queryNpcDatas(), npcMap);
			generateDatas(dataAction.queryNameDatas(), nameMap);
			generateDatas(dataAction.queryItemDatas(), itemMap);
			generateDatas(dataAction.queryMoneyDatas(), moneyMap);
			generateDatas(dataAction.queryMoBanDatas(), moBanMap, "Level");
			generateDatas(dataAction.queryChuShiDatas(), chuShiMap);
			generateDatas(dataAction.queryShenTongDatas(), shenTongMap);
			generateDatas(dataAction.queryTalentDatas(), talentMap);
			generateDatas(dataAction.queryCardenDatas(), cardenMap, "Strengthenlevel");
			generateDatas(dataAction.queryEquipenDatas(), equipenMap);
			generateDatas(dataAction.queryCritDatas(), critMap, "Vip");
			generateDatas(dataAction.queryBaptizeDatas(), baptizeMap, "Type");
			generateDatas(dataAction.queryRefineDatas(), refineMap, "Kungfu");
			generateDatas(dataAction.queryKongFuDatas(), kongFuMap, "Strengthenlevel");
			generateDatas(dataAction.queryBagDatas(), bagMap);
			generateDatas(dataAction.queryPanDuanXiTongChanChuDatas(), panDuanXiTongChanChuMap);
			generateDatas(dataAction.queryVipDatas(), vipMap, "System");
			generateDatas(dataAction.queryWorldDatas(), worldMap);
			generateDatas(dataAction.queryFieldDatas(), fieldMap);
			generateDatas(dataAction.queryCollectDatas(), collectMap, "Position");
			generateDatas(dataAction.querySoulDatas(), soulMap, "Level");
			guangBoList = dataAction.queryGuangBoDatas();
			generateDatas(guangBoList, guangBoMap);
			noticeList = dataAction.queryNotice("");
			generateDatas(dataAction.queryOpenDatas(), openMap, "System");
			generateDatas(dataAction.queryHotelDatas(), hotelMap, "Type");
			generateDatas(dataAction.queryEliteBattleDatas(), eliteBattleMap);
			generateDatas(dataAction.queryActivityBattleDatas(), activityBattleMap);
			generateDatas(dataAction.queryMingJiangDatas(), mingJiangMap, "Level");
			generateDatas(dataAction.queryRoadCardLevelDatas(), roadCardLevelMap, "Card");
			generateDatas(dataAction.queryRoadCardAchieveDatas(), roadCardAchieveMap, "Good");
			generateDatas(dataAction.queryLunjianProbDatas(), lunjianProbMap, "Level");
			generateDatas(dataAction.queryChongzhiDatas(), chongzhiHashMap, "Index");
			generateDatas(dataAction.queryYueKaShouChongGiftDatas(), yuekashouchonggiftMap, "Index");
			generateDatas(dataAction.queryViplevelDatas(), viplevelMap, "Vip");
			generateDatas(dataAction.queryShenMiDatas(), shenmiMap);
			generateDatas(dataAction.queryBossguwuDatas(), bossguwuMap);
			generateDatas(dataAction.queryBossDatas(), bossMap);
			generateDatas(dataAction.queryCardClsLimitDatas(), cardClsLimitMap, "Advanced");
			generateDatas(dataAction.queryPseudoDatas(), pseudoMap);
			generateDatas(dataAction.queryEquipSuitDatas(), equipSuitMap);
			generateDatas(dataAction.queryTouZiDatas(), touziMap, "Level");
			unionConfig = dataAction.queryUnionConfig();//
			generateDatas(dataAction.queryUnion(), unionMap);
			generateDatas(dataAction.queryUnionShop1(), unionShop1Map);
			generateDatas(dataAction.queryLimitShopDatas(), LimitShopMap);
			generateDatas(dataAction.queryWorkShop(), UnionWorkShopMap);
			generateDatas(dataAction.queryQingLongBossDatas(), qingLongBossMap);
			generateDatas(dataAction.queryBiwuShopDatas(), biwuShopMap);
			generateDatas(dataAction.queryTournamentDatas(), tournamentMap);
			configBiwuMap = dataAction.queryConfigBiwuDatas();
			configYaBiaoMap = dataAction.queryConfigYaBiaoDatas();
			generateDatas(dataAction.queryYabiaoJiangliDatas(), yabiaoJiangliMap);
			generateDatas(dataAction.queryRouletteItemDatas(), rouletteItemMap);
			generateDatas(dataAction.queryRouletteProbDatas(), rouletteProbMap);
			generateDatas(dataAction.queryExchangeExpDatas(), exchExpMap);
			generateDatas(dataAction.querySuperBattleDatas(), superBattleMap, "SysId");

			lunjianList = dataAction.queryLunjianDatas();
			shangxianList = dataAction.queryShangXianSheDingDatas();
			jiefujipinList = dataAction.queryActivityBattleJFJPDatas();
			biwuJiangli = dataAction.queryBiwuJiangliDatas();
			biwuShopList = dataAction.queryBiwuShopDatas();
			unionShop2List = dataAction.queryUnionShop2();

			// unionFBList = dataAction.queryUnionFB();
			limitShopList = dataAction.queryLimitShopDatas();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("init datas is done:" + (new Date().getTime() - start.getTime()));
	}

	public void reloadData() {
		try {
			List<Prob> probList = null;
			if (probDAO != null) {
				ConnectionResource.startTransaction(true);
				probList = probDAO.queryList();
				ConnectionResource.endTransaction();
			} else {
				probList = dataAction.queryProbDatas();
			}
			generateDatas(probList, probMap);
			generateDatas(dataAction.queryPseudoDatas(), pseudoMap);
			generateDatas(dataAction.queryShenMiDatas(), shenmiMap);
			unionConfig = dataAction.queryUnionConfig();

		} catch (Exception e) {
			e.printStackTrace();
		}

		serverVersions = versionAction.getVersions();
		curServerVersion = serverVersions.get(serverVersions.size() - 1).getVersion();
	}

	public void reloadActivity() {
		RoleActivityAction roleActivityAction = Container.get().createRemote(RoleActivityAction.class,
				DataApplication.CLUSTER_GAME_SYSTEM);
		List<ActivityConfig> configs = roleActivityAction.queryActivityConfigs();
		boolean first = activityStatus.size() == 0;
		for (ActivityConfig config : configs) {
			Integer aid = config.getActivityId();
			Integer oldStatus = activityStatus.get(aid);
			oldStatus = oldStatus == null ? 0 : oldStatus;
			Integer newStatus = RoleActivityAO.checkOpen(config);
			newStatus = newStatus == null ? 0 : newStatus;
			if (!first) {
				if (oldStatus == 0 && newStatus == 1) {
					roleActivityAction.openActivity(aid);
				}
				if (oldStatus == 1 && newStatus == 0) {
					if (aid == ActivityConfig.ACTIVITY_LIMITCARD && !checkSystemOpen(SystemStatus.SYSTEM_限时豪杰)) {
					} else {
						roleActivityAction.closeActivity(aid);
					}
				}
			}
			activityStatus.put(aid, newStatus);
		}

	}

	/**
	 * 检测功能是否开放
	 * 
	 * @param systemId
	 * @throws BizException
	 */
	public boolean checkSystemOpen(int systemId) {
		RoleActivityAction roleActivityAction = Container.get().createRemote(RoleActivityAction.class,
				DataApplication.CLUSTER_GAME_SYSTEM);
		List<SystemStatus> ss = roleActivityAction.querySystemStatusList();
		Map<Integer, Integer> systemStatusMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		for (SystemStatus systemStatus : ss) {
			systemStatusMap.put(systemStatus.getSystemId(), systemStatus.getOpen());
		}
		Integer open = systemStatusMap.get(systemId);
		return open != null && open == 1;
	}

	public void checkSystemOpenExcept(int systemId) throws BizException {
		Map<Integer, Integer> systemStatusMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		Integer open = systemStatusMap.get(systemId);
		if (open == null || open != 1) {
			throw new BizException(2800001, "system close");
		}
	}

	private <T> void generateDatas(List<T> list, Map<Integer, T> map) throws Exception {
		generateDatas(list, map, "Id");
	}

	private <T> void generateDatas(List<T> list, Map<Integer, T> map, String key) throws Exception {

		Map<Integer, T> newMap = new LinkedHashMap<Integer, T>();
		Method method = null;
		Class<? extends Object> cls = null;
		for (T tmp : list) {
			if (method == null) {
				cls = tmp.getClass();
				method = tmp.getClass().getMethod("get" + key);
			}
			newMap.put((Integer) method.invoke(tmp), tmp);
		}
		map.clear();
		map.putAll(newMap);
		mapping.put(cls, map);
		logger.info("generate datas:" + cls.getName());
	}

	@SuppressWarnings("unchecked")
	public <T> T getValueByKey(Class<?> clazz, int key) {
		T res = null;
		do {
			if (key == 0)
				return res;
			Map<Integer, T> list = (Map<Integer, T>) mapping.get(clazz);
			if (list == null)
				break;
			res = list.get(key);
		} while (false);

		return res;
	}

	@SuppressWarnings("unchecked")
	public <T> T getExistValueByKey(Class<?> clazz, int key) throws BizException {
		T res = null;
		Map<Integer, T> list = (Map<Integer, T>) mapping.get(clazz);
		if (list == null) {
			throw DataException.getException(DataException.EXCE_DATAS_IS_NOT_EXIST, clazz.getName());
		}
		res = list.get(key);
		if (res == null) {
			throw DataException.getException(DataException.EXCE_DATA_IS_NOT_EXIST, clazz.getName(), key);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public <T> Map<Integer, T> getValues(Class<?> clazz) {
		return (Map<Integer, T>) mapping.get(clazz);
	}

	public String getUnionConfigValue(String key) {
		return unionConfig.get(key);
	}

	public ShangXianSheDing getShangXian() {
		return shangxianList.get(0);
	}

	public Level getLevel(int id) {
		return levelList.get(id);
	}

	public List<Integer> getConfigBiwu(String key) {
		return configBiwuMap.get(key);
	}

	public List<Integer> getYaBiaoConfig(String configKey) {
		return configYaBiaoMap.get(configKey);
	}

	public LvNum getLvNumBylv(List<LvNum> list, int lv) {
		LvNum res = null;
		for (LvNum lvNum : list) {
			if (lvNum.getLv() == lv) {
				res = lvNum;
				break;
			}
		}
		return res;
	}

	/**
	 * 根据概率判断产出 随机类型（1-均概率选1；2-均概率选多）
	 * 
	 * @param propId
	 * @return
	 */
	public List<ProbItem> probGot(int probId) {
		List<ProbItem> selItems = new ArrayList<ProbItem>();
		Prob probData = probMap.get(probId);
		if (probData == null)
			return selItems;
		if (probData.getIsJson() == 1) {
			return probGot(probData.getItems(), probData.getType(), probId);
		}
		return proGot_(probData);
	}

	public List<ProbItem> probGot(List<ProbVal> probs, int type) {
		return probGot(probs, type, 0);
	}

	/**
	 * 
	 * @param probs
	 * @param type  1= 选1 2= 选多
	 * @return
	 */
	public List<ProbItem> probGot(List<ProbVal> probs, int type, int probId) {
		List<ProbItem> selItems = new ArrayList<ProbItem>();

		ProbVal itemData;
		int curProb = (int) Math.round(Math.random() * 10000);
		// console.log('curProb:', curProb);
		int sumProb = 0;
		int gotType = 0;
		int gotId = 0;
		for (int i = 0; i < probs.size(); i++) {
			if (type == 2) {
				curProb = (int) Math.round(Math.random() * 10000);
			}
			itemData = probs.get(i);
			if (type == 1) {
				sumProb += itemData.getProb();
			} else {
				sumProb = itemData.getProb();
			}
			;
			if (curProb <= sumProb) {
				gotType = 0;
				gotId = random(itemData.getMinId(), itemData.getMaxId());
				if (itemData.getT() == 1) {
					// itme data
					Item gotData = itemMap.get(gotId);
					if (gotData == null)
						continue;
					gotType = gotData.getType();
				} else if (itemData.getT() == 2) {
					// card data
					gotType = 8;
				} else
					continue;

				selItems.add(new ProbItem(gotType, gotId, random(itemData.getMinNum(), itemData.getMaxNum())));
				logger.info("[PROP_JSON]:" + probId + "=>random:" + curProb + ",cur" + sumProb + "=>got:" + gotId);
				if (type == 1)
					break;
			}
		}
		return selItems;
	}

	public int random(int a, int b) {
		if (a == b)
			return a;
		Random rnd = new Random();
		return rnd.nextInt(b + 1 - a) + a;
	}

	/**
	 * 查找prop根据idx
	 * 
	 * @param props
	 * @param idx
	 * @return
	 */
	public Prop findPropByIdx(List<Prop> props, int idx) {
		Prop res = null;
		for (Prop prop : props) {
			if (prop.getIdx() == idx) {
				res = prop;
				break;
			}
		}
		return res;
	}

	public List<ActivityBattleJFJP> getJiefujipinList() {
		return jiefujipinList;
	}

	public List<Lunjian> getLunjianList() {
		return lunjianList;
	}

	public String getCurServerVersion() {
		return curServerVersion;
	}

	public Set<Integer> getVerChangeInfo(String ver) {
		if (ver == curServerVersion)
			return null;

		Set<Integer> cardIds = new HashSet<Integer>();
		boolean inCheck = true;
		for (int i = 0; i < serverVersions.size(); i++) {
			Version version = serverVersions.get(i);
			if (inCheck && version.getVersion().equals(ver)) {
				inCheck = false;
				continue;
			}
			cardIds.addAll(version.getUpdateCards());
		}
		return cardIds;
	}

	public List<BiwuJiangli> getBiwuJiangliList() {
		return biwuJiangli;
	}

	public List<BiwuShop> getBiwuShopList() {
		return biwuShopList;
	}

	public List<UnionShop2> getUnionShop2List() {
		return unionShop2List;
	}

	// public List<UnionFBData> getUnionFBList(){
	// return unionFBList;
	// }
	public List<LimitShopData> getLimitShopList() {
		return limitShopList;
	}

	private List<ProbItem> proGot_(Prob probData) {
		List<ProbItem> selItems = new ArrayList<ProbItem>();

		List<List<Integer>> items = Arrays.asList(probData.getItem1(), probData.getItem2(), probData.getItem3(),
				probData.getItem4(), probData.getItem5(), probData.getItem6(), probData.getItem7(),
				probData.getItem8());
		// itemType;idStart;idEnd;numStart,numEnd;prob
		// continue,

		List<Integer> itemData;
		int curProb = (int) Math.round(Math.random() * 10000);

		// console.log('curProb:', curProb);
		int sumProb = 0;
		int gotType = 0;
		int gotId = 0;
		for (int i = 0; i < items.size(); i++) {
			if (probData.getType() == 2) {
				curProb = (int) Math.round(Math.random() * 10000);
			}
			itemData = items.get(i);
			if (itemData.size() == 0)
				continue;
			if (probData.getType() == 1) {
				sumProb += itemData.get(5);
			} else {
				sumProb = itemData.get(5);
			}
			;
			if (curProb <= sumProb) {
				gotType = 0;
				gotId = random(itemData.get(1), itemData.get(2));
				if (itemData.get(0) == 1) {
					// itme data
					Item gotData = itemMap.get(gotId);
					if (gotData == null)
						continue;
					gotType = gotData.getType();
				} else if (itemData.get(0) == 2) {
					// card data
					gotType = 8;
				} else
					continue;

				selItems.add(new ProbItem(gotType, gotId, random(itemData.get(3), itemData.get(4))));
				logger.info("[PROP]:" + probData.getId() + "=>random:" + curProb + ",cur" + sumProb + "=>got:" + gotId);
				if (probData.getType() == 1)
					break;
			}
		}
		return selItems;
	}

	/**
	 * 根据概率判断产出 随机类型（1-均概率选1；2-均概率选多） 为新概率提取
	 * 
	 * @param probData 直接传数据
	 * @param cardlist 需要剔除的数组
	 * @param bol      是否进行剔除 true:需要 false:不需要
	 * @return
	 */
	public List<ProbItem> probGot_n(Prob probData, List<Integer> cardlist, boolean bol) {
		List<ProbItem> selItems = new ArrayList<ProbItem>();
		if (probData == null)
			return selItems;
		if (probData.getIsJson() == 1) {
			return probGot_n(probData.getItems(), probData.getType(), probData.getId(), cardlist, bol);
		}
		return proGot_(probData);
	}

	private int maxRandom(List<ProbVal> probs) {
		int max = 0;
		if (probs.size() > 0) {
			for (ProbVal pv : probs) {
				max += pv.getProb();
			}
		}
		return max;
	}

	/**
	 * 
	 * @param probs
	 * @param type  1= 选1 2= 选多
	 * @return
	 */
	public List<ProbItem> probGot_n(List<ProbVal> probs, int type, int probId, List<Integer> cardlist, boolean bol) {
		List<ProbItem> selItems = new ArrayList<ProbItem>();

		ProbVal itemData;
		int curProb = (int) Math.round(Math.random() * maxRandom(probs));
		// console.log('curProb:', curProb);
		int sumProb = 0;
		int gotType = 0;
		int gotId = 0;
		for (int i = 0; i < probs.size(); i++) {
			if (type == 2) {
				curProb = (int) Math.round(Math.random() * maxRandom(probs));
			}
			itemData = probs.get(i);
			if (type == 1) {
				sumProb += itemData.getProb();
			} else {
				sumProb = itemData.getProb();
			}
			;
			if (curProb <= sumProb) {
				gotType = 0;
				gotId = random_n(itemData.getMinId(), itemData.getMaxId(), cardlist, bol);
				if (itemData.getT() == 1) {
					// itme data
					Item gotData = itemMap.get(gotId);
					if (gotData == null)
						continue;
					gotType = gotData.getType();
				} else if (itemData.getT() == 2) {
					// card data
					gotType = 8;
				} else
					continue;

				selItems.add(new ProbItem(gotType, gotId,
						random_n(itemData.getMinNum(), itemData.getMaxNum(), cardlist, bol)));
				logger.info("[PROP_JSON]:" + probId + "=>random:" + curProb + ",cur" + sumProb + "=>got:" + gotId);
				if (type == 1)
					break;
			}
		}
		return selItems;
	}

	// 包含排斥的随机
	public int random_n(int minNum, int maxNum, List<Integer> cardlist, boolean bol) {
		List<Integer> randlist = new ArrayList<Integer>();
		if (bol) {
			for (int x = minNum; x <= maxNum; x++) {
				if (!cardlist.contains(x)) {
					randlist.add(x);
				}
			}
		} else {
			for (int x = minNum; x <= maxNum; x++) {
				randlist.add(x);
			}
		}
		Collections.shuffle(randlist);
		return randlist.get(0);
	}

	public List<GuangBo> getGuangBoList() {
		return guangBoList;
	}

	public List<Notice> getNotices() {
		return noticeList;
	}

	public Map<Integer, Integer> getActivityStatus() {
		return activityStatus;
	}

	public Map<String, Integer> openData;

	public Map<String, Integer> getOpenData() {
		if (openData == null) {
			openData = new HashMap<String, Integer>();
			Map<String, Integer> tempOpen = dataAction.queryAppOpenDatas();
			for (Entry<String, Integer> open : tempOpen.entrySet()) {
				openData.put(open.getKey(), open.getValue());
			}
		}
		return openData;
	}

	public void activityVerifyException(int activityId) throws BizException {
		Integer status = activityStatus.get(activityId);
		if (status == null) {
			throw ActivityException.getException(ActivityException.EXCE_ACTIVITYCENTER_活动不存在, activityId);
		}
		if (status == 0) {
			throw ActivityException.getException(ActivityException.EXCE_ACTIVITYCENTER_活动未开启, activityId);
		}
	}
}
