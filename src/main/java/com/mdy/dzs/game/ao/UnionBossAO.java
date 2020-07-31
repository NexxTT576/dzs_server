package com.mdy.dzs.game.ao;

import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.dzs.game.domain.boss.SelfStateVO;
import com.mdy.dzs.game.domain.union.Union;
import com.mdy.dzs.game.domain.union.UnionBoss;
import com.mdy.dzs.game.domain.union.UnionBossPlayer;
import com.mdy.dzs.game.domain.union.UnionConfig;
import com.mdy.dzs.game.domain.union.UnionDynamic;
import com.mdy.dzs.game.domain.union.UnionLog;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.sharp.container.biz.BizException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 青龙殿boss战
 * 
 * @author 白雪林
 *
 */
public class UnionBossAO extends BaseAO {

	private UnionBossPlayerAO unionBossPlayerAO;
	private BossBattleAO bossBattleAO;
	private CacheManager cacheManager;

	public UnionBossAO(UnionBossPlayerAO unionBossPlayerAO, BossBattleAO bossBattleAO, CacheManager cacheManager) {

		this.unionBossPlayerAO = unionBossPlayerAO;
		this.bossBattleAO = bossBattleAO;
		this.cacheManager = cacheManager;
	}

	// 查询某帮派最后一条数据
	public UnionBoss queryLast(int unionId) {
		return unionBossDAO().queryLast(unionId);
	}

	// 更新结束时间
	public void updateEndTime(Date now, int actId) throws BizException {
		UnionBoss bossActData = unionBossDAO().query(actId);
		unionBossDAO().updateEndTime(now, bossActData.getActId());
		// 发送动态
		int type = 0;
		List<Object> params = new ArrayList<Object>();
		List<Object> paras = new ArrayList<Object>();
		Union unionData = unionDAO().queryUnionById(bossActData.getUnionId());
		int dragonBossID = Integer.valueOf(cacheManager.getUnionConfigValue(UnionConfig.dragonBossID));
		paras.add(bossActData.getLevel());// 青龙等级
		paras.add(0);// 阶数

		if (bossActData.getKiller() != 0) {// 战胜青龙
			if (bossActData.getLevel() < (unionData.getGreenDragonTempleLevel() * 10 + 9)) {// 16 成功战胜了青龙，青龙等级提升到y级
				type = UnionDynamic.WIN_DRGON;
				params.add(bossActData.getLevel() + 1);
				paras.set(0, bossActData.getLevel() + 1);
				// 升级青龙
				unionDAO().updateGreenDragonLevel(unionData);
				unionLogDAO().add(UnionLog.valueOf(unionData, UnionLog.SYS_帮派_青龙等级提升, 1,
						unionData.getGreenDragonLevel() + 1, ""));
			} else {// 36 成功战胜青龙，青龙等级已达到满级，不再升级。
				type = UnionDynamic.WIN_DRAGON_LEVEL_FULL;
			}
		} else {// 17 未能及时战胜青龙，青龙重新进入了沉眠
			type = UnionDynamic.LOSE_DRGON;
		}

		unionDynamicDAO()
				.createDynmicDragon(UnionDynamic.dragonDy(bossActData.getUnionId(), dragonBossID, type, params, paras));

	}

	// 查询未发奖列表
	public List<UnionBoss> queryUnawardList() {
		return unionBossDAO().queryUnawardList();
	}

	/**
	 * 查询
	 */
	public UnionBoss query(int id) {
		return unionBossDAO().query(id);
	}

	/**
	 * 添加
	 * 
	 * @param UnionBoss
	 */
	public void add(UnionBoss ub) {
		unionBossDAO().add(ub);
	}

	/**
	 * 更新
	 * 
	 * @param UnionBoss
	 */
	public void update(UnionBoss ub) {
		unionBossDAO().update(ub);
	}

	// 返回玩家当前状态-for state pay
	public SelfStateVO getSelfState(UnionBossPlayer player, UnionBoss lastData, int roleId, int unionId) {
		Date now = new Date();
		int rank = 0;
		float hurtR = (float) player.getTotalHurt() / lastData.getLifeTotal() * 100;
		int battleWait = (int) (player.getBattleWait().getTime() - now.getTime()) / 1000;// 攻击等待时间
		int silverWait = (int) (player.getSilverWait().getTime() - now.getTime()) / 1000;// 银两鼓舞等待时间
		int nextLiveGold = bossBattleAO.nextLiveGold(player.getLiveCnt());
		if (player.getTotalHurt() > 0) {// 有排名
			List<RankList> rankList = new ArrayList<RankList>();
			rankList = unionBossPlayerAO.getRankList(unionId);
			rank = bossBattleAO.getRank(roleId, rankList);
		}
		if (battleWait < 0) {
			battleWait = 0;
		}
		if (silverWait < 0) {
			silverWait = 0;
		}

		return new SelfStateVO(player.getBattleCnt(), player.getTotalHurt(), (int) hurtR, rank, player.getHurtAdd(),
				silverWait, nextLiveGold, battleWait);
	}

	// 更新当前血量
	public void updateLife(int damageTotal, int actId) {
		unionBossDAO().updateLife(damageTotal, actId);
	}

	// 设置击杀者
	public void updateKiller(int roleId, int actId) {
		unionBossDAO().updateKiller(roleId, actId);
	}

	// 设置前十名
	public void updateTopTen(List<BossTopTenVO> topTenList, int actId) {
		unionBossDAO().updateTopTen(topTenList, actId);
	}

}