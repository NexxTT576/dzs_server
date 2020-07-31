/**
 * 
 */
package com.mdy.dzs.data.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.ApplicationAwareAction;
import com.mdy.dzs.data.action.DataAction;
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

public class DataActionImpl extends ApplicationAwareAction implements DataAction {

	@Override
	public List<Level> queryLevelDatas() {
		return dataAO().queryLevelDatas();
	}

	@Override
	public List<Card> queryCardDatas() {
		return dataAO().queryCardDatas();
	}

	@Override
	public List<Buff> queryBuffDatas() {
		return dataAO().queryBuffDatas();
	}

	@Override
	public List<Battle> queryBattleDatas() {
		return dataAO().queryBattleDatas();
	}

	@Override
	public List<BattleSkill> queryBattleSkillDatas() {
		return dataAO().queryBattleSkillDatas();
	}

	@Override
	public List<JiBan> queryJiBanDatas() {
		return dataAO().queryJiBanDatas();
	}

	@Override
	public List<JingMai> queryJingMaiDatas() {
		return dataAO().queryJingMaiDatas();
	}

	@Override
	public List<ShengJi> queryShengJiDatas() {
		return dataAO().queryShengJiDatas();
	}

	@Override
	public List<Npc> queryNpcDatas() {
		return dataAO().queryNpcDatas();
	}

	@Override
	public List<Name> queryNameDatas() {
		return dataAO().queryNameDatas();
	}

	@Override
	public List<Item> queryItemDatas() {
		return dataAO().queryItemDatas();
	}

	@Override
	public List<Money> queryMoneyDatas() {
		return dataAO().queryMoneyDatas();
	}

	@Override
	public List<Prob> queryProbDatas() {
		return dataAO().queryProbDatas();
	}

	@Override
	public List<MoBan> queryMoBanDatas() {
		return dataAO().queryMoBanDatas();
	}

	@Override
	public List<ChuShi> queryChuShiDatas() {
		return dataAO().queryChuShiDatas();
	}

	@Override
	public List<ShenTong> queryShenTongDatas() {
		return dataAO().queryShenTongDatas();
	}

	@Override
	public List<Talent> queryTalentDatas() {
		return dataAO().queryTalentDatas();
	}

	@Override
	public List<Carden> queryCardenDatas() {
		return dataAO().queryCardenDatas();
	}

	@Override
	public List<Equipen> queryEquipenDatas() {
		return dataAO().queryEquipenDatas();
	}

	@Override
	public List<VipCrit> queryCritDatas() {
		return dataAO().queryCritDatas();
	}

	@Override
	public List<Baptize> queryBaptizeDatas() {
		return dataAO().queryBaptizeDatas();
	}

	@Override
	public List<Refine> queryRefineDatas() {
		return dataAO().queryRefineDatas();
	}

	@Override
	public List<KongFu> queryKongFuDatas() {
		return dataAO().queryKongFuDatas();
	}

	@Override
	public List<Bag> queryBagDatas() {
		return dataAO().queryBagDatas();
	}

	@Override
	public List<PanDuanXiTongChanChu> queryPanDuanXiTongChanChuDatas() {
		return dataAO().queryPanDuanXiTongChanChuDatas();
	}

	@Override
	public List<Vip> queryVipDatas() {
		return dataAO().queryVipDatas();
	}

	@Override
	public List<World> queryWorldDatas() {
		return dataAO().queryWorldDatas();
	}

	@Override
	public List<Field> queryFieldDatas() {
		return dataAO().queryFieldDatas();
	}

	@Override
	public List<XuNiWanJia> queryXuNiWanJiaDatas() {
		return dataAO().queryXuNiWanJiaDatas();
	}

	@Override
	public List<Collect> queryCollectDatas() {
		return dataAO().queryCollectDatas();
	}

	@Override
	public List<Soul> querySoulDatas() {
		return dataAO().querySoulDatas();
	}

	@Override
	public List<GuangBo> queryGuangBoDatas() {
		return dataAO().queryGuangBoDatas();
	}

	@Override
	public List<Open> queryOpenDatas() {
		return dataAO().queryOpenDatas();
	}

	@Override
	public List<Hotel> queryHotelDatas() {
		return dataAO().queryHotelDatas();
	}

	@Override
	public List<ShangXianSheDing> queryShangXianSheDingDatas() {
		return dataAO().queryShangXianSheDingDatas();
	}

	@Override
	public List<EliteBattle> queryEliteBattleDatas() {
		return dataAO().queryEliteBattleDatas();
	}

	@Override
	public List<ActivityBattle> queryActivityBattleDatas() {
		return dataAO().queryActivityBattleDatas();
	}

	@Override
	public List<ActivityBattleJFJP> queryActivityBattleJFJPDatas() {
		return dataAO().queryActivityBattleJFJPDatas();
	}

	@Override
	public List<Arena> queryArenaDatas() {
		return dataAO().queryArenaDatas();
	}

	@Override
	public List<SignGift> querySignGiftDatas() {
		return dataAO().querySignGiftDatas();
	}

	@Override
	public List<LevelGift> queryLevelGiftDatas() {
		return dataAO().queryLevelGiftDatas();
	}

	@Override
	public List<OnlineGift> queryOnlineGiftDatas() {
		return dataAO().queryOnlineGiftDatas();
	}

	@Override
	public List<LoginDayGift> queryLoginDayGiftDatas() {
		return dataAO().queryLoginDayGiftDatas();
	}

	@Override
	public List<MingJiang> queryMingJiangDatas() {
		return dataAO().queryMingJiangDatas();
	}

	@Override
	public List<RoadCardLevel> queryRoadCardLevelDatas() {
		return dataAO().queryRoadCardLevelList();
	}

	@Override
	public List<RoadCardAchieve> queryRoadCardAchieveDatas() {
		return dataAO().queryRoadCardAchieveList();
	}

	@Override
	public List<ArenaShop> queryArenaShopDatas() {
		return dataAO().queryArenaShopList();
	}

	@Override
	public List<Lunjian> queryLunjianDatas() {
		return dataAO().queryLunjian();
	}

	@Override
	public List<LunjianProb> queryLunjianProbDatas() {
		return dataAO().queryLunjianProb();
	}

	@Override
	public List<Chongzhi> queryChongzhiDatas() {
		return dataAO().queryChongzhiDatas();
	}

	@Override
	public List<Viplevel> queryViplevelDatas() {
		return dataAO().queryViplevelDatas();
	}

	@Override
	public List<YueKaShouChongGift> queryYueKaShouChongGiftDatas() {
		return dataAO().queryYueKaShouChongGiftDatas();
	}

	@Override
	public List<ActivityGuess> queryActivityGuessDatas() {
		return dataAO().queryActivityGuessList();
	}

	@Override
	public List<ActivityLimitCard> queryActivityLimitCard() {
		return dataAO().queryActivityLimitCard();
	}

	@Override
	public List<ShenMi> queryShenMiDatas() {
		return dataAO().queryShenMiDatas();
	}

	@Override
	public List<Bossguwu> queryBossguwuDatas() {
		return dataAO().queryBossguwuDatas();
	}

	@Override
	public List<CardClsLimit> queryCardClsLimitDatas() {
		return dataAO().queryCardClsLimitDatas();
	}

	@Override
	public List<Boss> queryBossDatas() {
		return dataAO().queryBossDatas();
	}

	@Override
	public List<Pseudo> queryPseudoDatas() {
		return dataAO().queryPseudoDatas();
	}

	@Override
	public List<EquipSuit> queryEquipSuitDatas() {
		return dataAO().querEquipSuitDatas();
	}

	@Override
	public List<YueQian> queryYueQiansDatas() {
		return dataAO().queryYueQianDatas();
	}

	@Override
	public List<TouZi> queryTouZiDatas() {
		return dataAO().queryTouZiDatas();
	}

	@Override
	public List<MissionDefine> queryMissionDefineDatas() {
		return dataAO().queryMissionDefineDatas();
	}

	@Override
	public Map<String, String> queryUnionConfig() {
		return dataAO().loadUnionConfig();
	}

	@Override
	public List<UnionAttr> queryUnion() {
		return dataAO().loadUnion();
	}

	@Override
	public List<UnionShop1> queryUnionShop1() {
		return dataAO().loadUnionShop1();
	}

	@Override
	public List<UnionShop2> queryUnionShop2() {
		return dataAO().loadUnionShop2();
	}

	@Override
	public List<UnionFBData> queryUnionFB() {
		return dataAO().loadUnionFB();
	}

	@Override
	public List<QingLongBoss> queryQingLongBossDatas() {
		return dataAO().queryQingLongBossDatas();
	}

	@Override
	public List<DailyMissionReward> queryDailyMissionRewardDatas() {
		return dataAO().queryDailyMissionRewardDatas();
	}

	@Override
	public List<BiwuJiangli> queryBiwuJiangliDatas() {
		return dataAO().queryBiwuJiangliDatas();
	}

	@Override
	public List<BiwuShop> queryBiwuShopDatas() {
		return dataAO().queryBiwuShopsDatas();
	}

	@Override
	public List<Tournament> queryTournamentDatas() {
		return dataAO().queryTournamentDatas();
	}

	@Override
	public Map<String, List<Integer>> queryConfigBiwuDatas() {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		List<ConfigBiwu> list = dataAO().queryConfigBiquDatas();
		for (ConfigBiwu configBiwu : list) {
			map.put(configBiwu.getName(), configBiwu.getValue());
		}
		return map;
	}

	@Override
	public List<UnionWorkShop> queryWorkShop() {
		return dataAO().queryWorkShop();
	}

	@Override
	public Map<String, List<Integer>> queryConfigYaBiaoDatas() {
		List<ConfigYaBiao> yaBiaoConfig = dataAO().queryConfigYaBiaoDatas();
		Map<String, List<Integer>> yaBiaoMap = new HashMap<String, List<Integer>>();
		for (ConfigYaBiao configData : yaBiaoConfig) {
			yaBiaoMap.put(configData.getName(), configData.getValue());
		}
		return yaBiaoMap;
	}

	@Override
	public List<YabiaoJiangli> queryYabiaoJiangliDatas() {
		return dataAO().queryYabiaoJiangliDatas();
	}

	@Override
	public List<RouletteItem> queryRouletteItemDatas() {
		return dataAO().queryRouletteItemDatas();
	}

	@Override
	public List<RouletteProb> queryRouletteProbDatas() {
		return dataAO().queryRouletteProbDatas();
	}

	@Override
	public List<ActivityExchangeExp> queryExchangeExpDatas() {
		return dataAO().queryActivityExchangeExpDatas();
	}

	@Override
	public List<LimitShopData> queryLimitShopDatas() {
		return dataAO().queryLimitShopDatas();
	}

	@Override
	public Map<String, Integer> queryAppOpenDatas() {
		return dataAO().queryAppOpenDatas();
	}

	@Override
	public List<SuperBattle> querySuperBattleDatas() {
		return dataAO().querySuperBattleDatas();
	}

	@Override
	public List<Notice> queryNotice(String serverId) {
		return noticeAO().queryNowList(serverId);
	}
}
