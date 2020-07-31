package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mdy.dzs.data.domain.account.IOSDeviceInfo;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.card.ShangXianSheDing;
import com.mdy.dzs.data.domain.challengebattle.ActivityBattle;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.mission.MissionDefine;
import com.mdy.dzs.data.domain.packet.Bag;
import com.mdy.dzs.data.domain.role.ChuShi;
import com.mdy.dzs.data.domain.role.JiBan;
import com.mdy.dzs.data.domain.role.Level;
import com.mdy.dzs.data.domain.role.ShengJi;
import com.mdy.dzs.data.domain.shentong.Talent;
import com.mdy.dzs.game.dao.filter.RoleUpdateFilter;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.card.RoleCardEffect;
import com.mdy.dzs.game.domain.challenge.RoleActivityBattle;
import com.mdy.dzs.game.domain.challenge.RoleEliteBattle;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.dzs.game.domain.giftcenter.RoleGiftStatus;
import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.road.RoleRoad;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.RoleIdName;
import com.mdy.dzs.game.domain.shop.RoleShop;
import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.dzs.game.domain.yuan.RoleYuan;
import com.mdy.dzs.game.exception.RoleException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.util.JSONUtil;

/**
 * 角色
 * 
 * @author 房曈
 *
 */
public class RoleAO extends BaseAO {

	private static final Logger logger = LoggerFactory.get(RoleAO.class);

	private CacheManager cacheManager;
	private RoleAttackCalcManager calcManager;
	private RoleCardAO roleCardAO;
	private RoleChannelAO roleChannelAO;
	private RoleChallengeBattleAO roleChallengeBattleAO;
	private RoleGiftCenterAO giftCenterAO;
	private PacketAO packetAO;
	private MissionExecAO missionExecAO;

	public RoleAO(CacheManager cacheManager, RoleAttackCalcManager calcManager) {
		this.cacheManager = cacheManager;
		this.calcManager = calcManager;
	}

	//
	/**
	 * 查询
	 */
	public Role queryByAccount(String account) {
		return roleDAO().queryByAccount(account);
	}

	/**
	 * 查询
	 */
	public Role queryById(int roleId) {
		return roleDAO().queryById(roleId);
	}

	public Role queryByIdFromDB(int roleId) {
		return roleDAO().queryByIdFromDB(roleId);
	}

	public List<Object> check(Role doc) throws BizException {
		List<Object> checkArr = new ArrayList<Object>();

		Calendar nowTime = Calendar.getInstance();
		// 商店 ，获得免费抽卡机会
		RoleShop rShop = roleShopDAO().query(doc.getId());
		int shopCnt = 0;
		int now = (int) (nowTime.getTimeInMillis() / 1000);
		int t2 = rShop.getWineFreeNextTimeAry().get(1) - now;
		int t3 = rShop.getWineFreeNextTimeAry().get(2) - now;
		if (t2 <= 0)
			shopCnt++;
		if (t3 <= 0)
			shopCnt++;
		checkArr.add(shopCnt);

		// 挑战 精英副本和活动副本次数
		RoleEliteBattle elite = roleChallengeBattleAO.queryEliteBattleByRoleId(doc);
		int battleCnt = roleChallengeBattleAO.getEliteBattleSurplusCnt(elite); // 精英副本次数
		checkArr.add(battleCnt);

		// 活动副本次数 是总和
		int actCnt = 0;
		RoleActivityBattle act = roleChallengeBattleAO.queryActivityBattleByRoleId(doc);
		Map<Integer, ActivityBattle> activityBattles = cacheManager.getValues(ActivityBattle.class);
		for (ActivityBattle actBattle : activityBattles.values()) {
			actCnt += roleChallengeBattleAO.getActivityBattleSurplusCnt(doc, act, actBattle.getId());
		}
		checkArr.add(actCnt);

		RoleGiftStatus giftStatus = giftCenterAO.queryGiftStatus(doc.getId());
		// 签到 今日如果未签到
		int day = giftCenterAO.getBeSignDay(giftStatus);
		checkArr.add(day == 0 ? 0 : 1);

		// TODO 开服礼包
		int canGetGiftDays = giftStatus.getGiftLogindayCnt() > 30 ? 30 : giftStatus.getGiftLogindayCnt();
		int loginDays = canGetGiftDays - giftStatus.getGiftLogindayGot().size();
		int loginView = 1;
		if (giftStatus.getGiftLogindayGot().size() == giftCenterAO.getLoginDayGiftMap().size()) {
			loginView = 0;
		}
		checkArr.add(Arrays.asList(loginView, loginDays));

		// 等级礼包 index 5
		int levelGifts = giftCenterAO.getBeGetLevelCount(doc, giftStatus);
		int levelView = 1;
		if (giftStatus.getGiftLvGot().size() == giftCenterAO.getLevelGiftMap().size()) {
			levelView = 0;
		}
		checkArr.add(Arrays.asList(levelView, levelGifts));

		// 领奖中心
		int giftCount = giftCenterAO.queryCountByRoleId(doc.getId());
		checkArr.add(Arrays.asList(giftCount > 0 ? 1 : 0, giftCount));

		int missionCount = missionDAO().queryMissionCompleteCount(doc.getId());
		checkArr.add(missionCount);

		return checkArr;
	}

	public Role addRole(String acc, String lac, String name, String platformID, int rid, String version,
			String serverId) throws BizException {
		Role chkDocs = queryIsExist(acc, name);

		if (chkDocs != null) {
			if (chkDocs.getName().equals(name))
				throw RoleException.getException(RoleException.EXCE_NAME_IS_EXIST);
			if (chkDocs.getAccount().equals(acc))
				throw RoleException.getException(RoleException.EXCE_ACCOUNT_IS_EXIST);
		}
		Date now = new Date();
		Role me = new Role();
		// me.account = platformID+'##'+queryObj['acc'];
		me.setAccount(acc);
		me.setName(name);
		me.setVersion(version);
		// 设置平台id
		String platAry[] = platformID.split("_");
		me.setType(platAry[platAry.length - 1]);

		me.setHeartLastTime((int) (new Date().getTime() / 1000));
		// me.setBroadcastViewTime(now);
		// me.setDayLoginTime(now);

		// 初始化包
		List<Integer> bagLimitAry = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			Bag tempData = cacheManager.getValueByKey(Bag.class, i);
			if (tempData != null) {
				bagLimitAry.add(tempData.getNum());
			} else {
				bagLimitAry.add(0);
			}
		}
		me.setBagLimitAry(bagLimitAry);

		// 初始属性
		// /*
		// [0,0,0,0, //基础三围（统帅；武力；智力）
		// 0,0,0,0, //基础属性组（生命；攻击；物防；法防）
		// 0,0,0,0, //基础比例组（生命比例；攻击比例；物防比例；法防比例）
		// 0,0,0,0,0,0,0,0,//稀有属性组（物理伤害加成；法术伤害加成；中毒伤害加成；火焰伤害加成；
		// // 物理伤害减免；法术伤害减免；中毒伤害减免；火焰伤害减免）
		// 0,0,0,0,0,0,0,0,//稀有比例组（物理伤害加成比例；法术伤害加成比例；中毒伤害加成比例；火焰伤害加成比例；
		// // 物理伤害减免比例；法术伤害减免比例；中毒伤害减免比例；火焰伤害减免比例）
		// 0,0,0,0,0,0, //修正属性组（命中；闪避；暴击；抗暴；破击；格挡）
		// 0,0,0,0, //治疗效果组（治疗效果加成数值；治疗效果加成比例；被治疗效果加成数值；被治疗效果加成比例）
		// 0,0,0,0,0 //最终(暴伤倍率加成;暴伤倍率减免;最终伤害加成;最终伤害加成;怒气值)
		// ]}, //上阵属性加成统计数组（装备,）
		// */
		List<Integer> fmtPropTemplete = new ArrayList<Integer>();
		for (int j = 0; j < 50; j++) {
			fmtPropTemplete.add(0);
		}
		// me.setFmtPropPos1(fmtPropTemplete);
		// me.setFmtPropPos2(fmtPropTemplete);
		// me.setFmtPropPos3(fmtPropTemplete);
		// me.setFmtPropPos4(fmtPropTemplete);
		// me.setFmtPropPos5(fmtPropTemplete);
		// me.setFmtPropPos6(fmtPropTemplete);

		// -------------------------------------
		// 初始值
		ChuShi chushiData = cacheManager.getValueByKey(ChuShi.class, 1);
		Card cardData = cacheManager.getValueByKey(Card.class, rid);
		if (cardData == null) {
			throw RoleException.getException(RoleException.EXCE_RID_ERROR);
		}

		me.setNikeName(lac);
		me.setServerId(serverId);
		me.setLevel(chushiData.getLevel());
		me.setVip(chushiData.getVip());
		me.setCls(0);
		me.setLevel(1);
		me.setSilver(100000);
		me.setGold(chushiData.getCoin());
		me.setExp(chushiData.getExp());
		me.setPhysVal(chushiData.getPower());
		me.setResisVal(chushiData.getNaili());
		me.setCreateTime(now);

		List<Integer> propLimitAry = new ArrayList<Integer>();
		propLimitAry.add(chushiData.getPmax());
		propLimitAry.add(chushiData.getNmax());
		Level level = cacheManager.getLevel(me.getLevel());
		propLimitAry.add(level.getExp());
		propLimitAry.add(0);
		me.setPropLimitAry(propLimitAry);

		me.setResId(cardData.getId());// 资源id
		me.setSex(cardData.getSex());
		// TODO me.setLoginDayCnt(1);

		// init main card
		RoleCard curCard = roleCardAO.addNewCard(me, cardData, 2);
		List<RoleCard> cardAry = Arrays.asList(curCard);
		me.setFmtMainCardID(curCard.getId());
		me.setFmtCardAry(Arrays.asList(0, curCard.getId(), 0, 0, 0, 0, 0));

		roleDAO().add(me);
		me = roleDAO().queryByAccount(acc);

		curCard.setRoleId(me.getId());
		curCard.setName(me.getName());
		roleCardAO.update(curCard);

		fmtPropUpdate(me);
		roleChannelAO.addRoleChannel(me.getId());
		roleChallengeBattleAO.addChallenges(me);
		roleShopDAO().add(me.getId());
		return me;
	}

	/**
	 * 查询
	 */
	public Role queryExistAccount(String account) throws BizException {
		Role role = roleDAO().queryByAccount(account);
		if (role == null) {
			throw RoleException.getException(RoleException.EXCE_ACCOUNT_NOT_EXIST);
		}
		return role;
	}

	public Role queryExistId(int roleId) throws BizException {
		Role role = roleDAO().queryById(roleId);
		if (role == null) {
			throw RoleException.getException(RoleException.EXCE_ACCOUNT_NOT_EXIST);
		}
		return role;
	}

	/**
	 * 查询
	 */
	public Role queryIsExist(String account, String name) {
		return roleDAO().queryIsExist(account, name);
	}

	/**
	 * 查询列表
	 */
	public List<Role> queryList() {
		return roleDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param Role
	 */
	public void add(Role r) {
		roleDAO().add(r);
	}

	/**
	 * 更新gold
	 * 
	 * @param r
	 * @param add
	 * @throws BizException
	 */
	public int updateGold(Role r, int val) throws BizException {
		int gold = r.getGold();
		gold += val;

		if (gold < 0) {
			throw RoleException.getException(RoleException.EXCE_GOLD_NOT_ENOUGH, r.getGold(), val);
		}
		r.setGold(gold);
		roleDAO().updateGold(r, val);
		return gold;
	}

	/**
	 * 更新gold
	 * 
	 * @param r
	 * @param add
	 * @throws BizException
	 */
	public int updateSilver(Role r, int val) throws BizException {
		int silver = r.getSilver();
		silver += val;
		if (silver < 0) {
			throw RoleException.getException(RoleException.EXCE_SILVER_NOT_ENOUGH, r.getSilver(), val);
		}
		r.setSilver(silver);
		roleDAO().updateSilver(r, val);
		return silver;
	}

	public int updateAddPhysVal(Role r, int add) throws BizException {
		return updatePhysVal(r, add, false);
	}

	/**
	 * 更新体力值
	 * 
	 * @param r
	 * @param add
	 * @throws BizException
	 */
	public int updatePhysVal(Role r, int val, boolean reset) throws BizException {
		Role doc = roleDAO().queryByIdFromDBForUpdate(r.getId());
		int phyval = r.getPhysVal();
		if (reset) {
			phyval = val;
		} else {
			phyval += val;
		}
		if (phyval < 0) {
			throw RoleException.getException(RoleException.EXCE_PHYVAL_NOT_ENOUGH, r.getPhysVal(), val);
		}
		doc.setPhysVal(phyval);
		checkPhyRss(doc);
		r.setPhysVal(doc.getPhysVal());
		return r.getPhysVal();
	}

	public int updateAddResisVal(Role r, int add) throws BizException {
		return updateResisVal(r, add, false);
	}

	/**
	 * 更新体力值
	 * 
	 * @param r
	 * @param add
	 * @throws BizException
	 */
	public int updateResisVal(Role r, int val, boolean reset) throws BizException {
		Role doc = roleDAO().queryByIdFromDBForUpdate(r.getId());
		int resisVal = doc.getResisVal();
		if (reset) {
			resisVal = val;
		} else {
			resisVal += val;
		}
		if (resisVal < 0) {
			throw RoleException.getException(RoleException.EXCE_RESISVAL_NOT_ENOUGH, r.getResisVal(), val);
		}
		doc.setResisVal(resisVal);
		checkPhyRss(doc);
		r.setResisVal(doc.getResisVal());
		return r.getResisVal();
	}

	/**
	 * 更新侠魂
	 * 
	 * @param r
	 * @param val
	 * @param add
	 * @return
	 * @throws BizException
	 */
	public int updateSoul(Role r, int val) throws BizException {
		int soul = r.getSoul();
		soul += val;

		if (soul < 0) {
			throw RoleException.getException(RoleException.EXCE_SOUL_NOT_ENOUGH, r.getSoul(), val);
		}
		r.setSoul(soul);
		roleDAO().updateSoul(r, val);
		return soul;
	};

	// 更新灵石
	public int updateLingShi(Role r, int val) throws BizException {
		int lingShi = r.getLingShi();
		lingShi += val;

		if (lingShi < 0) {
			throw RoleException.getException(RoleException.EXCE_LINGSHI_NOT_ENOUGH, r.getLingShi(), val);
		}
		r.setLingShi(lingShi);
		roleDAO().updateLingShi(r, val);
		return lingShi;
	}

	// 更新魅力
	public int updateCharm(Role r, int val) throws BizException {
		int charm = r.getCharm();
		charm += val;
		if (charm < 0) {
			throw RoleException.getException(RoleException.EXCE_CHARM_NOT_ENOUGH, r.getCharm(), val);
		}
		r.setCharm(charm);
		roleDAO().updateCharm(r, val);
		return charm;
	}

	/**
	 * 更新荣誉
	 * 
	 * @param r
	 * @param val
	 * @return
	 * @throws BizException
	 */
	public int updateHonor(Role r, int val) throws BizException {
		int honor = r.getHonor();
		honor += val;
		if (honor < 0) {
			throw RoleException.getException(RoleException.EXCE_HONOR_NOT_ENOUGH, r.getHonor(), val);
		}
		r.setHonor(honor);
		roleDAO().updateHonor(r, val);
		return honor;
	}

	/**
	 * 更新vip等级
	 * 
	 * @param r
	 * @param val
	 * @param add
	 * @return
	 * @throws BizException
	 */
	public int updateVip(Role r, int val) throws BizException {
		int vip = r.getVip();
		vip += val;
		r.setVip(vip);
		roleDAO().updateVip(r, val);
		return vip;
	};

	/**
	 * 更新客栈休息时间
	 * 
	 * @param r
	 * @param date
	 */
	public void updateSleepLastTime(Role r, Date date) {
		r.setSleepLastTime(date);
		roleDAO().updateSleepLastTime(r);
	}

	public void updateBroadcastViewTime(Role r) {
		roleDAO().updateBroadcastViewTime(r);
	}

	public int battleCalc(Role doc, List<RoleCard> cardAry) throws BizException {
		int sumVal = 0;
		for (int i = 0; i < cardAry.size(); i++) {
			RoleCard curCard = cardAry.get(i);
			if (curCard.getPos() <= 0)
				continue;
			List<List<Integer>> calcRes = battleCalcCard(doc, curCard);
			sumVal += getCardFightVal(curCard, calcRes);
		}
		return Math.round(sumVal);
	}

	public int battleCalcSword(Role doc, List<SwordCard> cardAry) throws BizException {
		int sumVal = 0;
		for (int i = 0; i < cardAry.size(); i++) {
			SwordCard swordCard = cardAry.get(i);
			RoleCard curCard = swordCard.getRc();
			// if(curCard.getPos()<=0) continue;
			List<List<Integer>> calcRes = battleCalcCard(doc, curCard, swordCard.getOrder());

			sumVal += getCardFightVal(curCard, calcRes);
		}
		return Math.round(sumVal);
	}

	private int getCardFightVal(RoleCard curCard, List<List<Integer>> calcRes) {
		List<Integer> baseRes = calcRes.get(0);
		List<Integer> leadRes = calcRes.get(1);

		double talentVal = 1.0;
		for (int j = 0; j < curCard.getShenIDAry().size(); j++) {
			if (curCard.getShenLvAry().get(j) == 0)
				continue;
			Talent t = cacheManager.getValueByKey(Talent.class, curCard.getShenIDAry().get(j));
			if (t == null)
				continue;
			talentVal *= (1 + t.getFactor() * 0.0001);
		}

		return (int) ((baseRes.get(0) * 0.2 + baseRes.get(1) + baseRes.get(2) + baseRes.get(3)
				+ (leadRes.get(0) + leadRes.get(1) + leadRes.get(2)) * 10) * talentVal);
	}

	public int battleCalc(List<Card> cardAry) {
		// List<Integer> fmtCardAry = doc.getFmtCardAry();
		int sumVal = 0;
		for (int i = 0; i < cardAry.size(); i++) {
			Card curCard = cardAry.get(i);
			List<List<Integer>> calcRes = calcManager.getCardViewBase(curCard.getBase(), curCard.getLead(), null);
			List<Integer> baseRes = calcRes.get(0);
			List<Integer> leadRes = calcRes.get(1);
			int cardVal = (int) ((baseRes.get(0) * 0.2 + baseRes.get(1) + baseRes.get(2) + baseRes.get(3)
					+ (leadRes.get(0) + leadRes.get(1) + leadRes.get(2)) * 10));
			sumVal += cardVal;
		}
		return Math.round(sumVal);
	}

	public Map<Integer, Integer> calcCardProps(Role doc, RoleCard rCard) {
		int index = doc.getFmtCardAry().indexOf(rCard.getId());
		return calcCardProps(doc, rCard, index);
	}

	public Map<Integer, Integer> calcCardProps(Role doc, RoleCard rCard, int index) {
		if (doc.getFmtMainCardID() == rCard.getId()) {
			rCard.setLevel(doc.getLevel());
		}
		Map<Integer, Integer> res = null;
		int roleId = doc.getId();
		String acc = doc.getAccount();
		RoleRoad road = roleRoadDAO().query(roleId);
		int cardId = rCard.getResId();
		List<Equip> rEquips = null;
		List<RoleGong> rGongs = null;
		List<RoleYuan> rYuans = null;
		List<Prop> channels = null;
		if (index != -1) {
			rEquips = equipDAO().queryListByAccPos(roleId, index);
			rGongs = roleGongDAO().queryListByAccPos(roleId, index);
			rYuans = roleYuanDAO().queryListByAccPos(roleId, index);
			channels = rCard.getProps();
		}
		RoleCardEffect effect = roleCardEffectDAO().queryByRoleIdCardId(roleId, rCard.getResId());
		Set<Integer> relation = new HashSet<Integer>();
		relation.addAll(rCard.getRelation());
		if (effect != null)
			relation.addAll(effect.getEffectCardRelation());
		res = calcManager.getFmtCardProps(rCard, relation, channels, rEquips, rGongs, rYuans,
				road == null ? null : road.getRoadCards().get(cardId + ""));
		return res;
	}

	public List<List<Integer>> battleCalcCard(Role doc, RoleCard curCard) {
		Map<Integer, Integer> props = calcCardProps(doc, curCard);
		Card card = cacheManager.getValueByKey(Card.class, curCard.getResId());
		return calcManager.getCardViewBase(card.getBase(), card.getLead(), props);
	};

	public List<List<Integer>> battleCalcCard(Role doc, RoleCard curCard, int index) {
		Map<Integer, Integer> props = calcCardProps(doc, curCard, index);
		Card card = cacheManager.getValueByKey(Card.class, curCard.getResId());
		return calcManager.getCardViewBase(card.getBase(), card.getLead(), props);
	};

	public void updateDayReset(Role role) {
		Date now = new Date();
		if (role.getDayLoginTime() == null || !DateUtil.isYesterday(role.getDayLoginTime())) {
			role.setDaysContinue(1);
		} else {
			role.setDaysContinue(role.getDaysContinue() + 1);
		}
		role.setDayLoginTime(now);
		role.setBroadcastViewTime(now);
		// ---------------------------------
		// 每日登录后重置
		roleDAO().updateDayLogin(role);
	}

	public String[] checkPhyRss(Role doc, boolean forupdate) {
		Role r = roleDAO().queryByIdFromDBForUpdate(doc.getId());
		String[] res = checkPhyRss(r);
		doc.setPhysVal(r.getPhysVal());
		doc.setResisVal(r.getResisVal());
		return res;
	}

	/**
	 * 检查体力耐力
	 * 
	 * @param doc
	 * @return
	 */
	public String[] checkPhyRss(Role doc) {
		String phyStr = "";
		String rssStr = "";
		int phyTimeVal = 6 * 60;
		int rssTimeVal = 15 * 60;
		int t1 = 0;
		int t2 = 0;

		int propLimit0 = doc.getPropLimitAry().get(0);
		int propLimit1 = doc.getPropLimitAry().get(1);
		int physVal = doc.getPhysVal();
		int resisVal = doc.getResisVal();
		int addTime0 = doc.getAddTimeAry().get(0);
		int addTime1 = doc.getAddTimeAry().get(1);
		int now = Math.round(new Date().getTime() / 1000);
		if (physVal >= propLimit0) {
			phyStr = "0,0";
		} else {
			if (addTime0 == 0) {
				addTime0 = now;
			}
			t1 = (phyTimeVal - (now - addTime0) % phyTimeVal);
			if ((now - addTime0) >= phyTimeVal) {
				int phyCnt = (int) Math.floor((now - addTime0) / phyTimeVal);
				addTime0 = now - ((now - addTime0) % phyTimeVal);
				physVal += phyCnt;
			}
			if (physVal >= propLimit0) {
				t1 = 0;
				t2 = 0;
				physVal = propLimit0;
				addTime0 = 0;
			} else {
				t2 = (propLimit0 - physVal) * phyTimeVal - (now - addTime0);
			}
			phyStr = t1 + "," + t2;
			doc.getAddTimeAry().set(0, addTime0);
			doc.setPhysVal(physVal);
		}
		if (resisVal >= propLimit1) {
			rssStr = "0,0";
		} else {
			if (addTime1 == 0) {
				addTime1 = now;
			}
			t1 = (rssTimeVal - (now - addTime1) % rssTimeVal);
			if ((now - addTime1) >= rssTimeVal) {
				int rssCnt = (int) Math.floor((now - addTime1) / rssTimeVal);
				addTime1 = now - ((now - addTime1) % rssTimeVal);
				resisVal += rssCnt;
			}
			if (resisVal >= propLimit1) {
				t1 = 0;
				t2 = 0;
				resisVal = propLimit1;
				addTime1 = 0;
			} else
				t2 = (propLimit1 - resisVal) * rssTimeVal - (now - addTime1);
			rssStr = t1 + "," + t2;
			doc.getAddTimeAry().set(1, addTime1);
			doc.setResisVal(resisVal);
		}
		roleDAO().updatePhyRss(doc);
		return new String[] { phyStr, rssStr };
	}

	public void addExp(Role doc, int addExp) throws BizException {
		// var mainCard = doc.cardAry[0];
		int sumExp = addExp + doc.getExp();
		int endLv = doc.getLevel();
		int oldLv = doc.getLevel();
		Map<Integer, Level> levelList = cacheManager.getValues(Level.class);
		Level lvData = levelList.get(endLv);
		while (sumExp >= lvData.getExp()) {
			sumExp -= lvData.getExp();
			endLv += 1;
			lvData = levelList.get(endLv);
		}
		ShangXianSheDing maxLimit = cacheManager.getShangXian();
		if (endLv >= maxLimit.getZhujuedengji()) {
			endLv = maxLimit.getZhujuedengji();
			sumExp = 0;
		}
		if (endLv > doc.getLevel()) {

			ShengJi levelItem = cacheManager.getExistValueByKey(ShengJi.class, doc.getLevel());
			packetAO.addItem(doc, Packet.POS_ATTR, Packet.ATTR_金币, levelItem.getCoin(), RoleItemLog.SYS_主角_升级,
					doc.getLevel() + "=>" + endLv);
			packetAO.addItem(doc, Packet.POS_ATTR, Packet.ATTR_耐力, levelItem.getNaili(), RoleItemLog.SYS_主角_升级,
					doc.getLevel() + "=>" + endLv);
			if (levelItem.getItem() != 0) {
				packetAO.addItem(doc, levelItem.getType(), levelItem.getItem(), levelItem.getNum2(),
						RoleItemLog.SYS_主角_升级, doc.getLevel() + "=>" + endLv);
			}

			doc.setLevel(endLv);
			doc.setExp(sumExp);
			doc.setLevelUpLastTime(new Date());
			fmtPropUpdate(doc);

			// mainCard.level = endLv;
			// mainCard.curExp = sumExp;
			doc.getPropLimitAry().set(2, lvData.getExp());
		} else {
			doc.setExp(sumExp);
			// mainCard.curExp = sumExp;
		}
		roleDAO().updateExp(doc);
		roleCardDAO().updateMainExp(doc.getId(), doc.getFmtMainCardID(), doc.getExp(), doc.getLevel());

		if (endLv > oldLv) {
			// TASK
			missionExecAO.lvUpRole(doc);
			missionExecAO.changeRoleProperty(doc, MissionDefine.PROPERTY_主角_等级达到_MISSION);
		}
	};

	// fmtPropStr:"fmtPropPos1/fmtPropPos2/.../fmtPropPos6"
	// valAry: [val,val,...]
	// attIndAry: option, [ind, ind,...], ind=itemProc.attInd
	public void fmtPropUpdate(Role doc) throws BizException {
		List<RoleCard> cardAry = roleCardDAO().queryLineupListByRoldId(doc.getId());
		doc.setAttack(battleCalc(doc, cardAry));
		roleDAO().updateAttackAndFmtPropPos(doc);
		// TASK
		missionExecAO.changeRoleProperty(doc, MissionDefine.PROPERTY_主角_战斗力达到_MISSION);
	}

	public void updateRoleAttacks() throws BizException {
		List<Role> role = roleDAO().queryList();
		int size = role.size();
		int index = 0;
		for (Role doc : role) {
			index++;
			if (doc.getType().equals(Role.TYPE_ROBOT))
				continue;
			updateRoleAttack(doc);
			logger.info("update roleid:" + doc.getId() + " " + index + "/" + size);
		}
	}

	public void updateRoleAttack(Role doc) throws BizException {
		List<RoleCard> cardAry = roleCardDAO().queryLineupListByRoldId(doc.getId());
		doc.setAttack(battleCalc(doc, cardAry));
		roleDAO().updateAttackAndFmtPropPos(doc);
	}

	public void updateHeartTimes(Role rDoc) {
		roleDAO().updateHeartTimes(rDoc);
	}

	/**
	 * 更新包限制
	 * 
	 * @param doc
	 * @param val
	 * @param type
	 * @param add
	 */
	public void updateBagLimit(Role doc, int val, int type, boolean add) {
		int limit = doc.getBagLimit(type);
		doc.getBagLimitAry().set(type - 1, add ? limit + val : val);
		roleDAO().updateBagLimitAry(doc);
	}

	/**
	 * 更新阵容 fmtCardAry
	 * 
	 * @param r
	 */
	public void updateLineup(Role r) {
		roleDAO().updateLineup(r);
	}

	// /**
	// * 刷新羁绊数组
	// * @param acc
	// * @param roleCardAry
	// * @param equipAry
	// * @throws BizException
	// */
	// public void updateCardsRelation(Role doc,
	// List<RoleCard> roleCardAry) throws BizException{
	// List<Integer> fmtCardAry = doc.getFmtCardAry();
	// for (RoleCard curCard : roleCardAry) {
	// int index = fmtCardAry.indexOf(curCard.getId());
	// updateCardRelation(doc, curCard,index);
	// }
	// }

	/**
	 * 刷新羁绊数组
	 * 
	 * @param acc
	 * @param roleCardAry
	 * @param equipAry
	 * @throws BizException
	 */
	public void updateCardRelation(Role doc, RoleCard curCard, int fmtpos) throws BizException {
		if (curCard == null)
			return;
		String acc = doc.getAccount();
		int roleId = doc.getId();

		Card cardData = cacheManager.getExistValueByKey(Card.class, curCard.getResId());
		List<Integer> jbAry = cardData.getFate1();
		List<Integer> relation = new ArrayList<Integer>();
		List<Integer> oldRelation = curCard.getRelation();
		// console.log('jbAry:',curCard.resId, jbAry);
		boolean isChange = false;
		for (int i = 0; i < jbAry.size(); i++) {
			int jbId = jbAry.get(i);
			JiBan jbItem = cacheManager.getExistValueByKey(JiBan.class, jbId);
			boolean bExist = true;
			for (int j = 1; j <= 6; j++) {
				int rid = jbItem.getCond(j);
				if (rid == 0)
					continue;
				boolean itemFind = false;
				if (jbItem.getType() != 1 && fmtpos > 0) {
					Item item = cacheManager.getExistValueByKey(Item.class, rid);
					Object find = null;
					if (item.getType() == Packet.POS_EQUIP) {
						find = equipDAO().queryEquipByResIdPos(roleId, rid, fmtpos);
					} else {
						find = roleGongDAO().queryGongByResIdPos(roleId, rid, fmtpos);
					}
					if (find != null)
						itemFind = true;

				}
				if (!itemFind) {
					bExist = false;
					break;
				}
			}

			if (bExist) {
				relation.add(jbItem.getId());
			}
			if (fmtpos > 0) {
				boolean bOldExist = oldRelation.indexOf(jbItem.getId()) != -1;
				if (bOldExist != bExist) {
					isChange = true;
				}

			}
		}
		curCard.setRelation(relation);
		roleCardDAO().update(curCard);
		if (isChange) {
			fmtPropUpdate(doc);
		}
	}

	public void updateMainCardValues(Role doc) {
		roleDAO().updateMainCardValues(doc);
	}

	/**
	 * 更新新手引导
	 * 
	 * @param role
	 */
	public void updateGuideStep(Role role) {
		roleDAO().updateGuideStep(role);
	}

	public void updateCurCollLv(Role doc) {
		roleDAO().updateCurCollLv(doc);
	}

	public void updateSetParam(Role doc) {
		roleDAO().updateSetParam(doc);
	}

	public void setRoleCardAO(RoleCardAO roleCardAO) {
		this.roleCardAO = roleCardAO;
	}

	public void setRoleChannelAO(RoleChannelAO roleChannelAO) {
		this.roleChannelAO = roleChannelAO;
	}

	public void setRoleChallengeBattleAO(RoleChallengeBattleAO roleChallengeBattleAO) {
		this.roleChallengeBattleAO = roleChallengeBattleAO;
	}

	public void setGiftCenterAO(RoleGiftCenterAO giftCenterAO) {
		this.giftCenterAO = giftCenterAO;
	}

	public void setPacketAO(PacketAO packetAO) {
		this.packetAO = packetAO;
	}

	/**
	 * 查询可抢夺玩家
	 * 
	 * @param acc
	 */
	public List<Role> querySnatchRoles(String acc, int minLv, int maxLV, int itemId) {
		return roleDAO().querySnatchRoles(acc, minLv, maxLV, itemId);
	}

	public void updateChatViewTime(Role doc) {
		roleDAO().updateChat(doc);
	}

	public void updateMailLastTime(Role doc) {
		roleDAO().updateMailLastTime(doc);
	}

	public void updateRoad(Role doc) {
		RoleUpdateFilter filter = new RoleUpdateFilter();
		filter.addFieldValue("attack", doc.getAttack());
		filter.addFieldValue("propLimitAry", JSONUtil.toJson(doc.getPropLimitAry()));
		filter.setAccount(doc.getAccount());
		filter.setRoleId(doc.getId());
		roleDAO().updateByUpdateFilter(filter);
	}

	public RoleIdName queryIdNameByAcc(String acc) throws BizException {
		RoleIdName role = roleDAO().queryIdNameByAcc(acc);
		if (role == null) {
			throw RoleException.getException(RoleException.EXCE_ACCOUNT_NOT_EXIST);
		}
		return role;
	}

	public String queryServerKeyByAccount(String acc) {
		return roleDAO().queryServerKeyByAccount(acc);
	}

	public void updateLoginInfoByAccount(Role role, String serverId, String serverKey, IOSDeviceInfo info) {
		role.setServerId(serverId);
		if (role.getLastDeviceUUID() == null || role.getLastDeviceUUID().length() == 0) {
			if (info != null) {
				role.setLastDeviceType(info.getDeviceType());
				role.setLastDeviceUUID(info.getDeviceUUID());
				role.setLastDeviceVersion(info.getDeviceVersion());
			} else {
				role.setLastDeviceType("");
				role.setLastDeviceUUID("");
				role.setLastDeviceVersion("");
			}
		}
		roleDAO().updateLoginInfoByAccount(role, serverKey);
	}

	public int updateAddPopualVal(Role r, int val) throws BizException {
		int popualVal = r.getPopual();
		popualVal += val;

		if (popualVal < 0) {
			throw RoleException.getException(RoleException.EXCE_POPUAL_NOT_ENOUGH, r.getPopual(), val);
		}
		r.setPopual(popualVal);
		roleDAO().updateAddPopualVal(r, val);
		return popualVal;
	}

	public int updateAddHunYuVal(Role r, int val) throws BizException {
		int hunYuVal = r.getHunYuVal();
		hunYuVal += val;

		if (hunYuVal < 0) {
			throw RoleException.getException(RoleException.EXCE_HUNYU_NOT_ENOUGH, r.getHunYuVal(), val);
		}
		r.setHunYuVal(hunYuVal);
		roleDAO().updateAddHunYuVal(r, val);
		return hunYuVal;
	}

	public void addLoginLog(int roldId, IOSDeviceInfo device) {
		roleDAO().addLoginLog(roldId, device);
	}

	public int queryRobotSize() {
		return roleDAO().queryRobotSize();
	}

	public void clearRobot() {
		roleDAO().clearRobot();
	}

	public int queryNumByAccPos(int roleId, int pos, int subpos) {

		return equipDAO().queryNumByAccPos(roleId, pos, subpos).size();
	}

	public List<Equip> queryEquipByAccPos(int roleId, int pos, int subpos) {

		return equipDAO().queryNumByAccPos(roleId, pos, subpos);
	}

	public int queryequiplevel(int roleId, int pos, int subpos) {
		int level = 0;
		List<Equip> list = equipDAO().queryNumByAccPos(roleId, pos, subpos);
		if (list.size() > 0) {

			level = list.get(0).getLevel();
		}

		return level;
	}

	public String queryVersionByAccount(String acc) {
		return roleDAO().queryVersionByAccount(acc);
	}

	/**
	 * @param role
	 * @throws BizException
	 */
	public void checkVersion(Role role) throws BizException {
		String version = cacheManager.getCurServerVersion();
		if (!role.getVersion().equals(version)) {
			Set<Integer> cardIds = cacheManager.getVerChangeInfo(role.getVersion());
			boolean updateAttack = false;
			for (Integer cardId : cardIds) {
				List<RoleCard> cards = roleCardAO.querySameResCard(role.getId(), cardId);

				for (RoleCard mCard : cards) {
					roleCardAO.refreshTalentIdByLV(mCard);
					roleCardAO.update(mCard);
					if (role.getFmtCardAry().indexOf(mCard.getId()) != -1) {
						updateAttack = true;
					}
				}
				roleCardAO.checkCardRelation(role.getId(), cardId, 0);
			}
			role.setVersion(version);
			if (updateAttack)
				fmtPropUpdate(role);
			roleDAO().updateVersionByAccount(role.getAccount(), version);
			missionExecAO.AcceptBeStartMission(role);
		}
	}

	/**
	 * 
	 */
	public void kickRoles() {
		roleDAO().kickRoles();
	}

	/**
	 * @return
	 */
	public int queryRoleSize() {
		return roleDAO().queryRoleSize();
	}

	public MissionExecAO getMissionExecAO() {
		return missionExecAO;
	}

	public void setMissionExecAO(MissionExecAO missionExecAO) {
		this.missionExecAO = missionExecAO;
	}

	/**
	 * @param list
	 * @return
	 */
	public Map<Integer, Integer> queryArenaRewardRole(List<RoleArena> list) {
		return roleDAO().queryArenaRewardRole(list);
	}

	/**
	 * 修改玩家帮派名称
	 */
	public void updateRoleFaction(Role role, String unionName) {
		roleDAO().updateRoleFaction(role, unionName);
	}

	public int queryCountByRidRestId(int roleid, int resid) {
		return roleGongDAO().queryCountByRidRestId(roleid, resid);
	}
}