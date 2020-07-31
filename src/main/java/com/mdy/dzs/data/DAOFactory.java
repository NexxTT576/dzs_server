package com.mdy.dzs.data;

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

/**
 * 
 * @author fidel
 * 
 */
public interface DAOFactory {

    LevelDAO levelDAO();

    CardDAO cardDAO();

    BuffDAO buffDAO();

    BattleDAO battleDAO();

    BattleSkillDAO battleSkillDAO();

    JiBanDAO jiBanDAO();

    JingMaiDAO jingMaiDAO();

    ShengJiDAO shengJiDAO();

    NpcDAO npcDAO();

    NameDAO nameDAO();

    ItemDAO itemDAO();

    MoneyDAO moneyDAO();

    ProbDAO probDAO();

    MoBanDAO moBanDAO();

    ChuShiDAO chuShiDAO();

    ShenTongDAO shenTongDAO();

    TalentDAO talentDAO();

    CardenDAO cardenDAO();

    EquipenDAO equipenDAO();

    CritDAO critDAO();

    BaptizeDAO baptizeDAO();

    RefineDAO refineDAO();

    KongFuDAO kongFuDAO();

    BagDAO bagDAO();

    PanDuanXiTongChanChuDAO panDuanXiTongChanChuDAO();

    VipDAO vipDAO();

    WorldDAO worldDAO();

    FieldDAO fieldDAO();

    XuNiWanJiaDAO xuNiWanJiaDAO();

    CollectDAO collectDAO();

    SoulDAO soulDAO();

    GuangBoDAO guangBoDAO();

    OpenDAO openDAO();

    HotelDAO hotelDAO();

    ShangXianSheDingDAO shangXianSheDingDAO();

    EliteBattleDAO eliteBattleDAO();

    ActivityBattleDAO activityBattleDAO();

    ArenaDAO arenaDAO();

    SignGiftDAO signGiftDAO();

    OnlineGiftDAO onlineGiftDAO();

    LevelGiftDAO levelGiftDAO();

    LoginDayGiftDAO loginDayGiftDAO();

    MingJiangDAO mingJiangDAO();

    ServerInfoDAO serverInfoDAO();

    ChongzhiDAO chongzhiDAO();

    ChargeOrderDAO chargeOrderDAO();

    LunjianDAO lunjianDAO();

    LunjianProbDAO lunjianProbDAO();

    ViplevelDAO viplevelDAO();

    YueKaShouChongGiftDAO yuekashouchonggiftDAO();

    ActivityConfigsDAO activityConfigsDAO();

    ShenMiDAO shenmiDAO();

    NoticeDAO noticeDAO();

    BossguwuDAO bossguwuDAO();

    BossDAO bossDAO();

    ServicePartnerDAO servicePartnerDAO();

    PseudoDAO pseudoDAO();

    AccountDAO accountDAO();

    EquipSuitDAO equipSuitDAO();

    YueQianDAO yueQianDAO();

    TouZiDAO touZiDAO();

    VersionDAO versionDAO();

    RewardCenterDAO rewardCenterDAO();

    MissionDefineDAO missionDefineDAO();

    UnionConfigDAO loadUnionConfigDAO();

    QingLongBossDAO qingLongBossDAO();

    DailyMissionRewardDAO dailyMissionRewardDAO();

    BiwuJiangliDAO biwuJiangliDAO();

    BiwuShopDAO biwuShopDAO();

    TournamentDAO tournamentDAO();

    ConfigBiwuDAO configBiwuDAO();

    ConfigYaBiaoDAO configYaBiaoDAO();

    YabiaoJiangliDAO yabiaoJiangliDAO();

    RouletteItemDAO rouletteItemDAO();

    RouletteProbDAO rouletteProbDAO();

    ActivityExchangeExpDAO activityExchangeExpDAO();

    ActivityLimitShopDataDAO activityLimitShopDAO();

    SuperBattleDAO superBattleDAO();

}
