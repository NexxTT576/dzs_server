package com.mdy.dzs.data.action;

import java.util.List;
import java.util.Map;

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
import com.mdy.dzs.data.domain.notice.Notice;
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

public interface DataAction {

	/**
	 * 
	 * @return
	 */
	List<Level> queryLevelDatas();

	/**
	 * 
	 * @return
	 */
	List<Card> queryCardDatas();

	/**
	 * 
	 * @return
	 */
	List<Buff> queryBuffDatas();

	/**
	 * 
	 * @return
	 */
	List<Battle> queryBattleDatas();

	/**
	 * 
	 * @return
	 */
	List<BattleSkill> queryBattleSkillDatas();

	List<JiBan> queryJiBanDatas();

	List<JingMai> queryJingMaiDatas();

	List<ShengJi> queryShengJiDatas();

	List<Npc> queryNpcDatas();

	List<Name> queryNameDatas();

	List<Item> queryItemDatas();

	List<Money> queryMoneyDatas();

	List<Prob> queryProbDatas();

	List<MoBan> queryMoBanDatas();

	List<ChuShi> queryChuShiDatas();

	List<ShenTong> queryShenTongDatas();

	List<Talent> queryTalentDatas();

	List<Carden> queryCardenDatas();

	List<Equipen> queryEquipenDatas();

	List<VipCrit> queryCritDatas();

	List<Baptize> queryBaptizeDatas();

	List<Refine> queryRefineDatas();

	List<KongFu> queryKongFuDatas();

	List<Bag> queryBagDatas();

	List<PanDuanXiTongChanChu> queryPanDuanXiTongChanChuDatas();

	List<Vip> queryVipDatas();

	List<World> queryWorldDatas();

	List<Field> queryFieldDatas();

	List<XuNiWanJia> queryXuNiWanJiaDatas();

	List<Collect> queryCollectDatas();

	List<Soul> querySoulDatas();

	List<GuangBo> queryGuangBoDatas();

	List<Open> queryOpenDatas();

	List<Hotel> queryHotelDatas();

	List<Lunjian> queryLunjianDatas();

	List<LunjianProb> queryLunjianProbDatas();
	//
	// void update(Test test);

	List<ShangXianSheDing> queryShangXianSheDingDatas();

	List<EliteBattle> queryEliteBattleDatas();

	List<ActivityBattle> queryActivityBattleDatas();

	List<ActivityBattleJFJP> queryActivityBattleJFJPDatas();

	List<Arena> queryArenaDatas();

	List<SignGift> querySignGiftDatas();

	List<LevelGift> queryLevelGiftDatas();

	List<OnlineGift> queryOnlineGiftDatas();

	List<LoginDayGift> queryLoginDayGiftDatas();

	List<MingJiang> queryMingJiangDatas();

	List<RoadCardLevel> queryRoadCardLevelDatas();

	List<RoadCardAchieve> queryRoadCardAchieveDatas();

	List<ArenaShop> queryArenaShopDatas();

	List<Chongzhi> queryChongzhiDatas();

	List<Viplevel> queryViplevelDatas();

	List<YueKaShouChongGift> queryYueKaShouChongGiftDatas();

	List<ActivityGuess> queryActivityGuessDatas();

	List<ShenMi> queryShenMiDatas();

	List<Bossguwu> queryBossguwuDatas();

	List<Boss> queryBossDatas();

	List<CardClsLimit> queryCardClsLimitDatas();

	List<Pseudo> queryPseudoDatas();

	List<EquipSuit> queryEquipSuitDatas();

	List<YueQian> queryYueQiansDatas();

	List<TouZi> queryTouZiDatas();

	List<MissionDefine> queryMissionDefineDatas();

	Map<String, String> queryUnionConfig();

	List<UnionAttr> queryUnion();

	List<UnionWorkShop> queryWorkShop();

	List<UnionShop1> queryUnionShop1();

	List<UnionShop2> queryUnionShop2();

	List<UnionFBData> queryUnionFB();

	List<QingLongBoss> queryQingLongBossDatas();

	List<ActivityLimitCard> queryActivityLimitCard();

	List<DailyMissionReward> queryDailyMissionRewardDatas();

	List<LimitShopData> queryLimitShopDatas();

	List<BiwuJiangli> queryBiwuJiangliDatas();

	List<BiwuShop> queryBiwuShopDatas();

	List<Tournament> queryTournamentDatas();

	Map<String, List<Integer>> queryConfigBiwuDatas();

	Map<String, List<Integer>> queryConfigYaBiaoDatas();

	List<YabiaoJiangli> queryYabiaoJiangliDatas();

	List<RouletteItem> queryRouletteItemDatas();

	List<RouletteProb> queryRouletteProbDatas();

	List<ActivityExchangeExp> queryExchangeExpDatas();

	Map<String, Integer> queryAppOpenDatas();

	List<SuperBattle> querySuperBattleDatas();

	List<Notice> queryNotice(String servername);
}
