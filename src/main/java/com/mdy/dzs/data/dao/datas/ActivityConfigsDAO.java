package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.activity.guess.ActivityGuess;
import com.mdy.dzs.data.domain.activity.limitcard.ActivityLimitCard;
import com.mdy.dzs.data.domain.item.ProbVal;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 活动配置dao
 * 
 * @author 房曈
 *
 */
public class ActivityConfigsDAO extends ConnectionResourceDAO {

	//////////////////////////////////////////////////////////////////////////////////////////////
	private static ResultSetRowHandler<ActivityGuess> GUESS_HANDLER = new ResultSetRowHandler<ActivityGuess>() {
		@Override
		public ActivityGuess handleRow(ResultSetRow row) throws Exception {
			ActivityGuess ag = new ActivityGuess();
			ag.setId(row.getInt("id"));
			ag.setLevel(row.getInt("level"));
			List<ProbVal> item1 = JSONUtil.fromJsonList(row.getString("item1"), ProbVal.class);
			ag.setItem1(item1);
			ag.setProb1(row.getInt("prob1"));
			List<ProbVal> item2 = JSONUtil.fromJsonList(row.getString("item2"), ProbVal.class);
			ag.setItem2(item2);
			ag.setProb2(row.getInt("prob2"));
			List<ProbVal> item3 = JSONUtil.fromJsonList(row.getString("item3"), ProbVal.class);
			ag.setItem3(item3);
			ag.setProb3(row.getInt("prob3"));
			ag.setWinProb(DAOUtil.stringConvIntList(row.getString("win_prob")));
			ag.setUpdateTime(row.getTimestamp("update_time"));
			return ag;
		}
	};

	private static ResultSetRowHandler<ActivityLimitCard> LIMITCARD_HANDLER = new ResultSetRowHandler<ActivityLimitCard>() {
		@Override
		public ActivityLimitCard handleRow(ResultSetRow row) throws Exception {
			ActivityLimitCard alc = new ActivityLimitCard();
			alc.setId(row.getInt("id"));
			alc.setScoreCond(row.getInt("scoreCond"));
			alc.setExtLucky(row.getInt("extLucky"));
			alc.setScoreReward(row.getString("scoreReward"));
			alc.setMaxLucky(row.getInt("maxLucky"));// 幸运值
			alc.setMaxPlayer(row.getInt("maxPlayer"));
			alc.setGoldCost(row.getInt("goldCost"));
			alc.setReward4(row.getString("reward4"));
			alc.setMaxRank(row.getInt("maxRank"));
			alc.setCard2Prop(row.getInt("card2Prop"));
			alc.setCard3Prop(row.getInt("card3Prop"));
			return alc;
		}
	};

	/**
	 * 查询列表
	 */
	public List<ActivityGuess> queryActivityGuessList() {
		return queryForList("select * from t_caiquan", GUESS_HANDLER);
	}

	public List<ActivityLimitCard> queryActivityLimitCardList() {
		return queryForList("select * from t_limitcard", LIMITCARD_HANDLER);
	}
}