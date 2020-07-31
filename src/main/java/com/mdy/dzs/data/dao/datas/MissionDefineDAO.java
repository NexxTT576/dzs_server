package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.mission.MissionDefine;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 任务定义DAO
 * 
 * @author fangtong
 *
 */
public class MissionDefineDAO extends ConnectionResourceDAO implements QueryDateInterface<MissionDefine> {

	/*
	 * 转换MissionDefine对象的RowHandler
	 */
	public static final ResultSetRowHandler<MissionDefine> MISSION_DEFINE_ROW_HANDLER = new ResultSetRowHandler<MissionDefine>() {
		@Override
		public MissionDefine handleRow(ResultSetRow row) throws Exception {
			MissionDefine md = new MissionDefine();
			md.setId(row.getInt("id"));
			md.setCategory(row.getInt("category"));
			md.setLevelLimit(row.getInt("levelLimit"));
			md.setProperty(row.getInt("property"));
			md.setRewardIds(DAOUtil.stringConvIntList(row.getString("rewardIds")));
			md.setRewardTypes(DAOUtil.stringConvIntList(row.getString("rewardTypes")));
			md.setRewardNums(DAOUtil.stringConvIntList(row.getString("rewardNums")));
			md.setName(row.getString("name"));
			md.setDescription(row.getString("description"));
			md.setPurpose(row.getString("purpose"));
			md.setNavigation(row.getString("navigation"));
			md.setPrams(createMissionPramsFromJson(row.getString("prams")));
			md.setInherit(row.getInt("inherit"));
			md.setLimitMissionId(row.getInt("limitMissionId"));
			md.setRefreshDaily(row.getInt("refreshDaily"));
			md.setStartTime(row.getTimestamp("startTime"));
			md.setRenWuPaiXu(row.getInt("renwupaixu"));
			md.setEndTime(row.getTimestamp("endTime"));
			md.setAutoReward(row.getInt("auto_Reward"));
			md.setJifen(row.getInt("jifen"));
			return md;
		}
	};

	/**
	 * 查询所有任务
	 * 
	 * @return 任务定义列表
	 */
	public List<MissionDefine> queryList() {
		String sql = "select * from t_missiondefine ";
		return queryForList(sql, MISSION_DEFINE_ROW_HANDLER);
	}

	//
	@SuppressWarnings({ "unchecked" })
	private static List<Integer> createMissionPramsFromJson(String json) {
		if (json == null || json.trim().length() == 0) {
			return null;
		}
		return JSONUtil.fromJson(json, List.class);
	}

}
