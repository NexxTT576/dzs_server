package com.mdy.dzs.game;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.ao.ActivityLimitShopAO;
import com.mdy.dzs.game.ao.BossBattleAO;
import com.mdy.dzs.game.ao.BossBattlePlayerAO;
import com.mdy.dzs.game.ao.BroadcastAO;
import com.mdy.dzs.game.ao.ChatAO;
import com.mdy.dzs.game.ao.RoleActivityLimitShopAO;
import com.mdy.dzs.game.ao.RoleActivityMazeAO;
import com.mdy.dzs.game.ao.RoleActivityRouletteAO;
import com.mdy.dzs.game.ao.RoleActivityExchAO;
import com.mdy.dzs.game.ao.RoleDartAO;
import com.mdy.dzs.game.ao.FightAO;
import com.mdy.dzs.game.ao.LineupAO;
import com.mdy.dzs.game.ao.MailAO;
import com.mdy.dzs.game.ao.MissionAO;
import com.mdy.dzs.game.ao.MissionExecAO;
import com.mdy.dzs.game.ao.PacketAO;
import com.mdy.dzs.game.ao.RoleAO;
import com.mdy.dzs.game.ao.RoleActivityAO;
import com.mdy.dzs.game.ao.RoleActivityHappyAO;
import com.mdy.dzs.game.ao.RoleActivityMonthSignAO;
import com.mdy.dzs.game.ao.RoleActivityTouziAO;
import com.mdy.dzs.game.ao.RoleArenaAO;
import com.mdy.dzs.game.ao.RoleBattleAO;
import com.mdy.dzs.game.ao.RoleCardAO;
import com.mdy.dzs.game.ao.RoleChallengeBattleAO;
import com.mdy.dzs.game.ao.RoleChannelAO;
import com.mdy.dzs.game.ao.RoleEquipAO;
import com.mdy.dzs.game.ao.RoleFriendAO;
import com.mdy.dzs.game.ao.RoleGiftCenterAO;
import com.mdy.dzs.game.ao.RoleGongAO;
import com.mdy.dzs.game.ao.RoleLineupAidAO;
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
import com.mdy.dzs.game.ao.UnionFBAO;
import com.mdy.dzs.game.ao.UnionFBDynamicAO;
import com.mdy.dzs.game.ao.UnionLogAO;
import com.mdy.dzs.game.ao.UnionRoleShopAO;
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
import com.mdy.dzs.game.ao.UnionShopAO;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;

public class ApplicationAwareAction {

	public SystemApplication application() {
		return SystemApplication.Instance();
	}

	//
	// public TeamAO teamAO(){
	// return application().teamAO();
	// }

	public RoleAO roleAO() {
		return application().roleAO();
	}

	public RoleCardAO roleCardAO() {
		return application().roleCardAO();
	}

	public BroadcastAO broadcastAO() {
		return application().broadcastAO();
	}

	public PacketAO packetAO() {
		return application().packetAO();
	}

	public ShopAO shopAO() {
		return application().shopAO();
	}

	public LineupAO lineupAO() {
		return application().lineupAO();
	}

	public RoleEquipAO roleEquipAO() {
		return application().roleEquipAO();
	}

	public RoleGongAO roleGongAO() {
		return application().roleGongAO();
	}

	public RoleYuanAO roleYuanAO() {
		return application().roleYuanAO();
	}

	public RoleChannelAO roleChannelAO() {
		return application().roleChannelAO();
	}

	public RoleChallengeBattleAO roleChallengeBattleAO() {
		return application().roleChallengeBattleAO();
	}

	public ChatAO chatAO() {
		return application().chatAO();
	}

	public RoleArenaAO roleArenaAO() {
		return application().roleArenaAO();
	}

	public RoleGiftCenterAO giftCenterAO() {
		return application().roleGiftCenterAO();
	}

	public SnatchAO snatchAO() {
		return application().snatchAO();
	}

	public FightAO fightAO() {
		return application().fightAO();
	}

	public RoleRoadAO roleRoadAO() {
		return application().roleRoadAO();
	}

	public SwordRoleAO swordRoleAO() {
		return application().swordRoleAO();
	}

	public SwordRoleCardAO swordRoleCardAO() {
		return application().swordRoleCardAO();
	}

	public RoleVipAO roleVipAO() {
		return application().roleVipAO();
	}

	public RoleActivityAO roleActivityAO() {
		return application().roleActivityAO();
	}

	public RoleMonthCardAO roleMonthCardAO() {
		return application().roleMonthCardAO();
	}

	public RoleBattleAO roleBattleAO() {
		return application().roleBattleAO();
	}

	public RoleShenMiShopAO roleShenMiShopAO() {
		return application().roleShenMiShopAO();
	}

	public RoleSnatchAO roleSnatchAO() {
		return application().roleSnatchAO();
	}

	public BossBattleAO bossBattleAO() {
		return application().bossBattleAO();
	}

	public BossBattlePlayerAO bossBattlePlayerAO() {
		return application().bossBattlePlayerAO();
	}

	public RoleActivityHappyAO roleActivityHappyAO() {
		return application().roleActivityHappyAO();
	}

	public RoleStatisticsAO roleStatisticsAO() {
		return application().roleStatisticsAO();
	}

	public RoleActivityMonthSignAO roleActivityMonthSignAO() {
		return application().roleActivityMonthSignAO();
	}

	public RoleActivityTouziAO roleActivityTouziAO() {
		return application().roleActivityTouziAO();
	}

	public MailAO mailAO() {
		return application().mailAO();
	}

	public MissionAO missionAO() {
		return application().missionAO();
	}

	public MissionExecAO missionExecAO() {
		return application().missionExecAO();
	}

	public UnionAO unionAO() {
		return application().unionAO();
	}

	public RoleUnionAO roleUnionAO() {
		return application().roleUnionAO();
	}

	public UnionApplyAO unionApplyAO() {
		return application().unionApplyAO();
	}

	public UnionDynamicAO unionDynamicAO() {
		return application().unionDynamicAO();
	}

	public UnionFBAO unionFBAO() {
		return application().unionFBAO();
	}

	public RoleUnionFBAO roleUnionFBAO() {
		return application().roleUnionFBAO();
	}

	public RoleUnionFBStateAO roleUnionFBStateAO() {
		return application().roleUnionFBStateAO();
	}

	public UnionFBDynamicAO unionFBDynamicAO() {
		return application().unionFBDynamicAO();
	}

	public UnionBossAO unionBossAO() {
		return application().unionBossAO();
	}

	public UnionBossPlayerAO unionBossPlayerAO() {
		return application().unionBossPlayerAO();
	}

	public RolePseudoAO rolePseudoAO() {
		return application().rolePseudoAO();
	}

	public RoleFriendAO roleFriendAO() {
		return application().roleFriendAO();
	}

	public UnionShopAO unionShopAO() {
		return application().unionShopAO();
	}

	public UnionRoleShopAO unionRoleShopAO() {
		return application().unionRoleShopAO();
	}

	public UnionLogAO unionLogAO() {
		return application().unionLogAO();
	}

	public RoleTournamentAO roleTournamentAO() {
		return application().roleTournamentAO();
	}

	public RoleTournamentShopAO roleTournamentShopAO() {
		return application().roleTournamentShopAO();
	}

	public RoleTournamentEnemyAO roleTournamentEnemyAO() {
		return application().roleTournamentEnemyAO();
	}

	public RoleRankAO roleRankAO() {
		return application().roleRankAO();
	}

	public RoleDartAO roleDartAO() {
		return application().roleDartAO();
	}

	public RoleActivityRouletteAO roleActivityRouletteAO() {
		return application().roleActivityRouletteAO();
	}

	public RoleActivityExchAO roleActivityExchAO() {
		return application().roleActivityExchAO();
	}

	public RoleActivityLimitShopAO roleActivityLimitShopAO() {
		return application().roleActivityLimitShopAO();
	}

	public ActivityLimitShopAO activityLimitShopAO() {
		return application().activityLimitShopAO();
	}

	public RoleLineupAidAO roleLineupAidAO() {
		return application().roleLineupAidAO();
	}

	public RoleActivityMazeAO roleActivityMazeAO() {
		return application().roleActivityMazeAO();
	}
	///////////////////////////
	// 公用方法

	public CacheManager cacheManager() {
		return application().cacheManager();
	}

	public RoleAttackCalcManager calcManager() {
		return application().calcManager();
	}

	public List<Integer> parseIntToInteger(int[] nums) {
		List<Integer> in = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			in.add(Integer.valueOf(nums[i]));
		}
		return in;
	}

	public int[] parseIntegerToInt(List<Integer> nums) {
		int[] in = new int[nums.size()];
		for (int i = 0; i < nums.size(); i++) {
			in[i] = nums.get(i).intValue();
		}
		return in;
	}
}
