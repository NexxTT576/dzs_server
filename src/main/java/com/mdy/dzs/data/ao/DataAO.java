/**
 * 
 */
package com.mdy.dzs.data.ao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.dao.datas.QueryDateInterface;
import com.mdy.dzs.data.domain.activity.exchange.ActivityExchangeExp;
import com.mdy.dzs.data.domain.activity.guess.ActivityGuess;
import com.mdy.dzs.data.domain.activity.limitcard.ActivityLimitCard;
import com.mdy.dzs.data.domain.activity.limitshop.LimitShopData;
import com.mdy.dzs.data.domain.activity.roulette.RouletteItem;
import com.mdy.dzs.data.domain.activity.roulette.RouletteProb;
import com.mdy.dzs.data.domain.arena.Arena;
import com.mdy.dzs.data.domain.arena.ArenaShop;
import com.mdy.dzs.data.domain.battle.Battle;
import com.mdy.dzs.data.domain.battle.BattleSkill;
import com.mdy.dzs.data.domain.battle.Buff;
import com.mdy.dzs.data.domain.battle.Npc;
import com.mdy.dzs.data.domain.biwu.BiwuJiangli;
import com.mdy.dzs.data.domain.biwu.BiwuShop;
import com.mdy.dzs.data.domain.biwu.ConfigBiwu;
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
import com.mdy.dzs.data.domain.dart.ConfigYaBiao;
import com.mdy.dzs.data.domain.dart.YabiaoJiangli;
import com.mdy.dzs.data.domain.equipment.Baptize;
import com.mdy.dzs.data.domain.equipment.EquipSuit;
import com.mdy.dzs.data.domain.equipment.Equipen;
import com.mdy.dzs.data.domain.gift.LevelGift;
import com.mdy.dzs.data.domain.gift.LoginDayGift;
import com.mdy.dzs.data.domain.gift.OnlineGift;
import com.mdy.dzs.data.domain.gift.SignGift;
import com.mdy.dzs.data.domain.gift.YueKaShouChongGift;
import com.mdy.dzs.data.domain.gong.KongFu;
import com.mdy.dzs.data.domain.gong.Refine;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.Money;
import com.mdy.dzs.data.domain.item.Prob;
import com.mdy.dzs.data.domain.mission.DailyMissionReward;
import com.mdy.dzs.data.domain.mission.MissionDefine;
import com.mdy.dzs.data.domain.packet.Bag;
import com.mdy.dzs.data.domain.packet.PanDuanXiTongChanChu;
import com.mdy.dzs.data.domain.road.MingJiang;
import com.mdy.dzs.data.domain.road.RoadCardAchieve;
import com.mdy.dzs.data.domain.road.RoadCardLevel;
import com.mdy.dzs.data.domain.robot.MoBan;
import com.mdy.dzs.data.domain.robot.Name;
import com.mdy.dzs.data.domain.robot.XuNiWanJia;
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
import com.mdy.dzs.data.domain.union.UnionFBData;
import com.mdy.dzs.data.domain.union.UnionShop1;
import com.mdy.dzs.data.domain.union.UnionShop2;
import com.mdy.dzs.data.domain.union.UnionWorkShop;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.data.domain.viplevel.VipCrit;
import com.mdy.dzs.data.domain.viplevel.Viplevel;
import com.mdy.dzs.data.domain.world.Field;
import com.mdy.dzs.data.domain.world.World;
import com.mdy.dzs.data.domain.yuan.Collect;
import com.mdy.dzs.data.domain.yuan.Soul;
import com.mdy.dzs.data.domain.yueqian.YueQian;

/**
 */
public class DataAO extends BaseAO {

	private Map<String, Object> caches = Collections.synchronizedMap(new HashMap<String, Object>());

	public List<Level> queryLevelDatas() {
		return queryList(levelDAO(), "level");
	}

	public List<Card> queryCardDatas() {
		return queryList(cardDAO(), "card");
	}

	public List<Buff> queryBuffDatas() {
		return queryList(buffDAO(), "buff");
	}

	public List<Battle> queryBattleDatas() {
		return queryList(battleDAO(), "battle");
	}

	public List<BattleSkill> queryBattleSkillDatas() {
		return queryList(battleSkillDAO(), "battleskill");
	}

	public List<JiBan> queryJiBanDatas() {
		return queryList(jiBanDAO(), "jiban");
	}

	public List<JingMai> queryJingMaiDatas() {
		return queryList(jingMaiDAO(), "jingmai");
	}

	public List<ShengJi> queryShengJiDatas() {
		return queryList(shengJiDAO(), "shengji");
	}

	public List<Npc> queryNpcDatas() {
		return queryList(npcDAO(), "npc");
	}

	public List<Name> queryNameDatas() {
		return queryList(nameDAO(), "name");
	}

	public List<Item> queryItemDatas() {
		return queryList(itemDAO(), "item");
	}

	public List<Money> queryMoneyDatas() {
		return queryList(moneyDAO(), "money");
	}

	public List<Prob> queryProbDatas() {
		return queryList(probDAO(), "prob");
	}

	public List<MoBan> queryMoBanDatas() {
		return queryList(moBanDAO(), "moban");
	}

	public List<ChuShi> queryChuShiDatas() {
		return queryList(chuShiDAO(), "chushi");
	}

	public List<ShenTong> queryShenTongDatas() {
		return queryList(shenTongDAO(), "shentong");
	}

	public List<Talent> queryTalentDatas() {
		return queryList(talentDAO(), "talent");
	}

	public List<Carden> queryCardenDatas() {
		return queryList(cardenDAO(), "carden");
	}

	public List<Equipen> queryEquipenDatas() {
		return queryList(equipenDAO(), "Equipen");
	}

	public List<VipCrit> queryCritDatas() {
		return queryList(critDAO(), "VipCrit");
	}

	public List<Baptize> queryBaptizeDatas() {
		return queryList(baptizeDAO(), "Baptize");
	}

	public List<Refine> queryRefineDatas() {
		return queryList(refineDAO(), "Refine");
	}

	public List<KongFu> queryKongFuDatas() {
		return queryList(kongFuDAO(), "KongFu");
	}

	public List<Bag> queryBagDatas() {
		return queryList(bagDAO(), "Bag");
	}

	public List<PanDuanXiTongChanChu> queryPanDuanXiTongChanChuDatas() {
		return queryList(panDuanXiTongChanChuDAO(), "pdxtcc");
	}

	public List<Vip> queryVipDatas() {
		return queryList(vipDAO(), "vip");
	}

	public List<World> queryWorldDatas() {
		return queryList(worldDAO(), "world");
	}

	public List<Field> queryFieldDatas() {
		return queryList(fieldDAO(), "field");
	}

	public List<XuNiWanJia> queryXuNiWanJiaDatas() {
		return queryList(xuNiWanJiaDAO(), "xnwj");
	}

	public List<Collect> queryCollectDatas() {
		return queryList(collectDAO(), "collect");
	}

	public List<Soul> querySoulDatas() {
		return queryList(soulDAO(), "soul");
	}

	public List<GuangBo> queryGuangBoDatas() {
		return queryList(guangBoDAO(), "guangbo");
	}

	public List<Open> queryOpenDatas() {
		return queryList(openDAO(), "open");
	}

	public List<Hotel> queryHotelDatas() {
		return queryList(hotelDAO(), "hotel");
	}

	public List<ShangXianSheDing> queryShangXianSheDingDatas() {
		return queryList(shangXianSheDingDAO(), "sxsd");
	}

	public List<EliteBattle> queryEliteBattleDatas() {
		return queryList(eliteBattleDAO(), "eilteBattle");
	}

	public List<ActivityBattle> queryActivityBattleDatas() {
		return queryList(activityBattleDAO(), "activityBattle");
	}

	public List<ActivityBattleJFJP> queryActivityBattleJFJPDatas() {
		return activityBattleDAO().queryJFJPList();
	}

	public List<Arena> queryArenaDatas() {
		return queryList(arenaDAO(), "arena");
	}

	public List<SignGift> querySignGiftDatas() {
		return queryList(signGiftDAO(), "signGift");
	}

	public List<OnlineGift> queryOnlineGiftDatas() {
		return queryList(onlineGiftDAO(), "onlineGift");
	}

	public List<LevelGift> queryLevelGiftDatas() {
		return queryList(levelGiftDAO(), "levelGift");
	}

	public List<LoginDayGift> queryLoginDayGiftDatas() {
		return queryList(loginDayGiftDAO(), "loginDayGift");
	}

	public List<MingJiang> queryMingJiangDatas() {
		return queryList(mingJiangDAO(), "mingJiang");
	}

	public List<RoadCardLevel> queryRoadCardLevelList() {
		return mingJiangDAO().queryRoadCardLevelList();
	}

	public List<RoadCardAchieve> queryRoadCardAchieveList() {
		return mingJiangDAO().queryRoadCardAchieveList();
	}

	public List<ArenaShop> queryArenaShopList() {
		return arenaDAO().queryArenaShopList();
	}

	public List<Lunjian> queryLunjian() {
		return queryList(lunjianDAO(), "lunjian");
	}

	public List<LunjianProb> queryLunjianProb() {
		return queryList(lunjianProbDAO(), "lunjianProb");
	}

	public List<Chongzhi> queryChongzhiDatas() {
		return queryList(chongzhiDAO(), "chongzhi");
	}

	public List<Viplevel> queryViplevelDatas() {
		return queryList(viplevelDAO(), "viplevel");
	}

	public List<YueKaShouChongGift> queryYueKaShouChongGiftDatas() {
		return queryList(yuekashouchonggiftDAO(), "ykscGift");
	}

	public List<ActivityGuess> queryActivityGuessList() {
		return activityConfigsDAO().queryActivityGuessList();
	}

	public List<ActivityLimitCard> queryActivityLimitCard() {
		return activityConfigsDAO().queryActivityLimitCardList();
	}

	public List<ShenMi> queryShenMiDatas() {
		return queryList(shenmiDAO(), "shenmi");
	}

	public List<Bossguwu> queryBossguwuDatas() {
		return queryList(bossguwuDAO(), "bossguwu");
	}

	public List<CardClsLimit> queryCardClsLimitDatas() {
		return cardDAO().queryClsLimitList();
	}

	public List<Boss> queryBossDatas() {
		return queryList(bossDAO(), "boss");
	}

	public List<Pseudo> queryPseudoDatas() {
		return queryList(pseudoDAO(), "pseudo");
	}

	public List<EquipSuit> querEquipSuitDatas() {
		return queryList(equipSuitDAO(), "equipSuit");
	}

	public List<YueQian> queryYueQianDatas() {
		return queryList(yueQianDAO(), "yueQian");
	}

	public List<TouZi> queryTouZiDatas() {
		return queryList(touZiDAO(), "touZi");
	}

	public List<MissionDefine> queryMissionDefineDatas() {
		return queryList(missionDefineDAO(), "missionDefine");
	}

	public Map<String, String> loadUnionConfig() {
		return getUnionConfig().queryUnionConfig();
	}

	public List<UnionAttr> loadUnion() {
		return getUnionConfig().queryUnion();
	}

	public List<UnionShop1> loadUnionShop1() {
		return getUnionConfig().queryUnionShop1();
	}

	public List<UnionShop2> loadUnionShop2() {
		return getUnionConfig().queryUnionShop2();
	}

	public List<UnionFBData> loadUnionFB() {
		return getUnionConfig().queryUnionFB();
	}

	public List<QingLongBoss> queryQingLongBossDatas() {
		return queryList(qingLongBossDAO(), "qingLongBoss");
	}

	public List<DailyMissionReward> queryDailyMissionRewardDatas() {
		return queryList(dailyMissionRewardDAO(), "dailyMissionReward");
	}

	public List<BiwuJiangli> queryBiwuJiangliDatas() {
		return queryList(biwuJiangliDAO(), "biwuJiangli");
	}

	public List<BiwuShop> queryBiwuShopsDatas() {
		return queryList(biwuShopDAO(), "biwuShop");
	}

	public List<Tournament> queryTournamentDatas() {
		return queryList(tournamentDAO(), "tournament");
	}

	public List<ConfigBiwu> queryConfigBiquDatas() {
		return queryList(configBiwuDAO(), "configBiwu");
	}

	public List<UnionWorkShop> queryWorkShop() {
		return getUnionConfig().queryWorkShop();
		// return queryList( getUnionConfig(),"UnionWorkShop");
	}

	public List<ConfigYaBiao> queryConfigYaBiaoDatas() {
		return queryList(configYaBiaoDAO(), "ConfigYaBiao");
	}

	public List<YabiaoJiangli> queryYabiaoJiangliDatas() {
		return queryList(yabiaoJiangliDAO(), "YabiaoJiangli");
	}

	public List<RouletteItem> queryRouletteItemDatas() {
		return queryList(rouletteItemDAO(), "RouletteItem");
	}

	public List<RouletteProb> queryRouletteProbDatas() {
		return queryList(rouletteProbDAO(), "RouletteProb");
	}

	public List<ActivityExchangeExp> queryActivityExchangeExpDatas() {
		return queryList(activityExchangeExpDAO(), "ActivityExchangeExp");
	}

	public List<LimitShopData> queryLimitShopDatas() {
		return queryList(activityLimitShopDAO(), "activityLimitShop");
	}

	public Map<String, Integer> queryAppOpenDatas() {
		return openDAO().queryAppOpenList();
	}

	public List<SuperBattle> querySuperBattleDatas() {
		return queryList(superBattleDAO(), "SuperBattle");
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> queryList(QueryDateInterface<T> dao, String key) {
		List<T> list = (List<T>) caches.get(key);
		if (list == null) {
			list = dao.queryList();
			caches.put(key, list);
		}
		return list;
	}

	public void clearCache() {
		caches.clear();
	}
}
