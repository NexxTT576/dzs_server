package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.mission.DailyMissionReward;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 每日任务奖励DAO
 * 
 * @author 房曈
 *
 */
public class DailyMissionRewardDAO extends ConnectionResourceDAO implements QueryDateInterface<DailyMissionReward> {

	private static ResultSetRowHandler<DailyMissionReward> HANDLER = new ResultSetRowHandler<DailyMissionReward>() {
		@Override
		public DailyMissionReward handleRow(ResultSetRow row) throws Exception {
			DailyMissionReward dmr = new DailyMissionReward();
			dmr.setId(row.getInt("id"));
			dmr.setJifen(row.getInt("jifen"));
			dmr.setRewardIds(DAOUtil.stringConvIntList(row.getString("rewardIds")));
			dmr.setRewardTypes(DAOUtil.stringConvIntList(row.getString("rewardTypes")));
			dmr.setRewardNums(DAOUtil.stringConvIntList(row.getString("rewardNums")));
			return dmr;
		}
	};

	/**
	 * 查询列表
	 */
	public List<DailyMissionReward> queryList() {
		return queryForList("select * from t_jifenjiangli", HANDLER);
	}

}