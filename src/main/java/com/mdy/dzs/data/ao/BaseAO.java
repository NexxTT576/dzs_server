package com.mdy.dzs.data.ao;

import com.mdy.dzs.data.DAOFactory;
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
 * @author <a href="mailto:guooscar@gmail.com">yama</a> Feb 14, 2011
 * 
 */
public abstract class BaseAO {
	protected DAOFactory daoFactory;

	/**
	 * @return the daoFactory
	 */
	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	/**
	 * @param daoFactory the daoFactory to set
	 */
	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public ServerInfoDAO serverInfoDAO() {
		return daoFactory.serverInfoDAO();
	}

	public ServicePartnerDAO servicePartnerDAO() {
		return daoFactory.servicePartnerDAO();
	}

	public ChargeOrderDAO chargeOrderDAO() {
		return daoFactory.chargeOrderDAO();
	}

	public AccountDAO accountDAO() {
		return daoFactory.accountDAO();
	}

	public VersionDAO versionDAO() {
		return daoFactory.versionDAO();
	}

	//
	protected LevelDAO levelDAO() {
		return daoFactory.levelDAO();
	}

	protected CardDAO cardDAO() {
		return daoFactory.cardDAO();
	}

	protected BuffDAO buffDAO() {
		return daoFactory.buffDAO();
	}

	protected BattleDAO battleDAO() {
		return daoFactory.battleDAO();
	}

	protected BattleSkillDAO battleSkillDAO() {
		return daoFactory.battleSkillDAO();
	}

	protected JiBanDAO jiBanDAO() {
		return daoFactory.jiBanDAO();
	}

	protected JingMaiDAO jingMaiDAO() {
		return daoFactory.jingMaiDAO();
	}

	protected ShengJiDAO shengJiDAO() {
		return daoFactory.shengJiDAO();
	}

	protected NpcDAO npcDAO() {
		return daoFactory.npcDAO();
	}

	protected NameDAO nameDAO() {
		return daoFactory.nameDAO();
	}

	protected ItemDAO itemDAO() {
		return daoFactory.itemDAO();
	}

	protected MoneyDAO moneyDAO() {
		return daoFactory.moneyDAO();
	}

	protected ProbDAO probDAO() {
		return daoFactory.probDAO();
	}

	protected MoBanDAO moBanDAO() {
		return daoFactory.moBanDAO();
	}

	protected ChuShiDAO chuShiDAO() {
		return daoFactory.chuShiDAO();
	}

	protected ShenTongDAO shenTongDAO() {
		return daoFactory.shenTongDAO();
	}

	protected TalentDAO talentDAO() {
		return daoFactory.talentDAO();
	}

	protected CardenDAO cardenDAO() {
		return daoFactory.cardenDAO();
	}

	protected EquipenDAO equipenDAO() {
		return daoFactory.equipenDAO();
	}

	protected CritDAO critDAO() {
		return daoFactory.critDAO();
	}

	protected BaptizeDAO baptizeDAO() {
		return daoFactory.baptizeDAO();
	}

	protected RefineDAO refineDAO() {
		return daoFactory.refineDAO();
	}

	protected KongFuDAO kongFuDAO() {
		return daoFactory.kongFuDAO();
	}

	protected BagDAO bagDAO() {
		return daoFactory.bagDAO();
	}

	protected PanDuanXiTongChanChuDAO panDuanXiTongChanChuDAO() {
		return daoFactory.panDuanXiTongChanChuDAO();
	}

	protected VipDAO vipDAO() {
		return daoFactory.vipDAO();
	}

	protected WorldDAO worldDAO() {
		return daoFactory.worldDAO();
	}

	protected FieldDAO fieldDAO() {
		return daoFactory.fieldDAO();
	}

	protected XuNiWanJiaDAO xuNiWanJiaDAO() {
		return daoFactory.xuNiWanJiaDAO();
	}

	protected CollectDAO collectDAO() {
		return daoFactory.collectDAO();
	}

	protected SoulDAO soulDAO() {
		return daoFactory.soulDAO();
	}

	protected GuangBoDAO guangBoDAO() {
		return daoFactory.guangBoDAO();
	}

	protected OpenDAO openDAO() {
		return daoFactory.openDAO();
	}

	protected HotelDAO hotelDAO() {
		return daoFactory.hotelDAO();
	}

	protected ShangXianSheDingDAO shangXianSheDingDAO() {
		return daoFactory.shangXianSheDingDAO();
	}

	protected EliteBattleDAO eliteBattleDAO() {
		return daoFactory.eliteBattleDAO();
	}

	protected ActivityBattleDAO activityBattleDAO() {
		return daoFactory.activityBattleDAO();
	}

	protected ArenaDAO arenaDAO() {
		return daoFactory.arenaDAO();
	}

	protected SignGiftDAO signGiftDAO() {
		return daoFactory.signGiftDAO();
	}

	protected OnlineGiftDAO onlineGiftDAO() {
		return daoFactory.onlineGiftDAO();
	}

	protected LevelGiftDAO levelGiftDAO() {
		return daoFactory.levelGiftDAO();
	}

	protected LoginDayGiftDAO loginDayGiftDAO() {
		return daoFactory.loginDayGiftDAO();
	}

	protected MingJiangDAO mingJiangDAO() {
		return daoFactory.mingJiangDAO();
	}

	protected LunjianDAO lunjianDAO() {
		return daoFactory.lunjianDAO();
	}

	protected LunjianProbDAO lunjianProbDAO() {
		return daoFactory.lunjianProbDAO();
	}

	protected ChongzhiDAO chongzhiDAO() {
		return daoFactory.chongzhiDAO();
	}

	protected ViplevelDAO viplevelDAO() {
		return daoFactory.viplevelDAO();
	}

	protected YueKaShouChongGiftDAO yuekashouchonggiftDAO() {
		return daoFactory.yuekashouchonggiftDAO();
	}

	protected ActivityConfigsDAO activityConfigsDAO() {
		return daoFactory.activityConfigsDAO();
	}

	protected ShenMiDAO shenmiDAO() {
		return daoFactory.shenmiDAO();
	}

	protected NoticeDAO noticeDAO() {
		return daoFactory.noticeDAO();
	}

	protected BossguwuDAO bossguwuDAO() {
		return daoFactory.bossguwuDAO();
	}

	protected BossDAO bossDAO() {
		return daoFactory.bossDAO();
	}

	protected PseudoDAO pseudoDAO() {
		return daoFactory.pseudoDAO();
	}

	protected EquipSuitDAO equipSuitDAO() {
		return daoFactory.equipSuitDAO();
	}

	protected YueQianDAO yueQianDAO() {
		return daoFactory.yueQianDAO();
	}

	protected TouZiDAO touZiDAO() {
		return daoFactory.touZiDAO();
	}

	protected RewardCenterDAO rewardCenterDAO() {
		return daoFactory.rewardCenterDAO();
	}

	protected MissionDefineDAO missionDefineDAO() {
		return daoFactory.missionDefineDAO();
	}

	protected UnionConfigDAO getUnionConfig() {
		return daoFactory.loadUnionConfigDAO();
	}

	protected QingLongBossDAO qingLongBossDAO() {
		return daoFactory.qingLongBossDAO();
	}

	protected DailyMissionRewardDAO dailyMissionRewardDAO() {
		return daoFactory.dailyMissionRewardDAO();
	}

	protected BiwuJiangliDAO biwuJiangliDAO() {
		return daoFactory.biwuJiangliDAO();
	}

	protected BiwuShopDAO biwuShopDAO() {
		return daoFactory.biwuShopDAO();
	}

	protected TournamentDAO tournamentDAO() {
		return daoFactory.tournamentDAO();
	}

	protected ConfigBiwuDAO configBiwuDAO() {
		return daoFactory.configBiwuDAO();
	}

	protected ConfigYaBiaoDAO configYaBiaoDAO() {
		return daoFactory.configYaBiaoDAO();
	}

	protected YabiaoJiangliDAO yabiaoJiangliDAO() {
		return daoFactory.yabiaoJiangliDAO();
	}

	protected RouletteItemDAO rouletteItemDAO() {
		return daoFactory.rouletteItemDAO();
	}

	protected RouletteProbDAO rouletteProbDAO() {
		return daoFactory.rouletteProbDAO();
	}

	protected ActivityExchangeExpDAO activityExchangeExpDAO() {
		return daoFactory.activityExchangeExpDAO();
	}

	protected ActivityLimitShopDataDAO activityLimitShopDAO() {
		return daoFactory.activityLimitShopDAO();
	}

	protected SuperBattleDAO superBattleDAO() {
		return daoFactory.superBattleDAO();
	}
}
