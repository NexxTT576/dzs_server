package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.boss.BossBattle;
import com.mdy.dzs.game.domain.boss.BossBattlePlayer;
import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.dzs.game.domain.boss.SelfStateVO;

/**
 * boss表结构
 * 
 * @author 白雪林
 *
 */
public class BossBattleAO extends BaseAO {

	private BossBattlePlayerAO bossBattlePlayerAO;

	public BossBattleAO(BossBattlePlayerAO bossBattlePlayerAO) {

		this.bossBattlePlayerAO = bossBattlePlayerAO;
	}

	/**
	 * 查询最后一条
	 */
	public BossBattle queryLast() {
		return bossBattleDAO().queryLast();
	}

	/**
	 * 查询
	 */
	public BossBattle query(int id) {
		return bossBattleDAO().query(id);
	}

	/**
	 * 添加
	 * 
	 * @param BossBattle
	 */
	public void add(BossBattle bb) {
		bossBattleDAO().add(bb);
	}

	/**
	 * 更新
	 * 
	 * @param BossBattle
	 */
	public void update(BossBattle bb) {
		bossBattleDAO().update(bb);
	}

	public int nextLiveGold(int liveCnt) {
		return (liveCnt + 1) * 5;
	}

	public int getRank(int roleId, List<RankList> rankList) {
		int rank = 0;
		for (int i = 0; i < rankList.size(); i++) {
			if (rankList.get(i).getId() == roleId) {
				rank = i + 1;
				break;
			}
		}

		return rank;
	}

	public void updateEndTime(Date now, int actId) {
		bossBattleDAO().updateEndTime(now, actId);
	}

	public void updateTopTen(List<BossTopTenVO> topTenList, int actId) {
		bossBattleDAO().updateTopTen(topTenList, actId);
	}

	public void updateLife(int damageTotal, int actId) {
		bossBattleDAO().updateLife(damageTotal, actId);
	}

	public void updateKiller(int id, int actId) {
		bossBattleDAO().updateKiller(id, actId);
	}

	// 距boss占开始时间 秒差
	public int difSecs() {
		int waitSecs = 0;
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		Calendar todayStart = Calendar.getInstance(TimeZone.getDefault());

		todayStart.set(Calendar.HOUR_OF_DAY, 21);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);

		waitSecs = (int) (todayStart.getTimeInMillis() - now.getTimeInMillis()) / 1000;
		if (waitSecs < 0) {
			waitSecs += 24 * 3600;
		}
		return waitSecs;
	}

	// 返回玩家当前状态-for state pay
	public SelfStateVO getSelfState(BossBattlePlayer player, BossBattle lastData, int roleId) {
		Date now = new Date();
		SelfStateVO selfState = new SelfStateVO();
		int rank = 0;
		float hurtR = (float) player.getTotalHurt() / lastData.getLifeTotal() * 100;
		int battleWait = (int) (player.getBattleWait().getTime() - now.getTime()) / 1000;// 攻击等待时间
		int silverWait = (int) (player.getSilverWait().getTime() - now.getTime()) / 1000;// 银两鼓舞等待时间
		int nextLiveGold = nextLiveGold(player.getLiveCnt());
		if (player.getTotalHurt() > 0) {// 有排名
			List<RankList> rankList = new ArrayList<RankList>();
			if (bossBattlePlayerAO.getRankList() != null) {
				rankList = bossBattlePlayerAO.getRankList();
			}
			rank = getRank(roleId, rankList);
		}
		if (battleWait < 0) {
			battleWait = 0;
		}
		if (silverWait < 0) {
			silverWait = 0;
		}
		selfState.setNum(player.getBattleCnt()); // 攻击次数
		selfState.setHurt(player.getTotalHurt()); // 总伤害
		selfState.setHurtR((int) hurtR); // 总伤害比例
		selfState.setHurtAdd(player.getHurtAdd()); // 伤害加成
		selfState.setNxtLiveGold(nextLiveGold); // 下次复活元宝数目
		selfState.setBattleWait(battleWait); // 攻击等待时间
		selfState.setSilverWait(silverWait); // 银两鼓舞等待时间
		selfState.setRank(rank); // 当前排行
		return selfState;
	}

	// 法术伤害加成
	public List<Prop> probsAdd(int hurtAdd) {
		List<Prop> probs = new ArrayList<Prop>();
		Prop physics = new Prop(); // 物理伤害加成
		physics.setIdx(51);
		;
		physics.setVal(hurtAdd * 100);
		probs.add(physics);

		Prop magic = new Prop(); // 法术伤害加成
		magic.setIdx(52);
		;
		magic.setVal(hurtAdd * 100);
		probs.add(magic);
		return probs;
	}

}