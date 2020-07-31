package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.dart.YabiaoJiangli;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 押镖奖励数值表DAO
 * 
 * @author 白雪林
 *
 */
public class YabiaoJiangliDAO extends ConnectionResourceDAO implements QueryDateInterface<YabiaoJiangli> {

	private static ResultSetRowHandler<YabiaoJiangli> HANDLER = new ResultSetRowHandler<YabiaoJiangli>() {
		@Override
		public YabiaoJiangli handleRow(ResultSetRow row) throws Exception {
			YabiaoJiangli yj = new YabiaoJiangli();
			yj.setId(row.getInt("id"));
			yj.setMax(row.getInt("max"));
			yj.setRewardIds(DAOUtil.stringConvIntList(row.getString("rewardIds")));
			yj.setRewardTypes(DAOUtil.stringConvIntList(row.getString("rewardTypes")));
			yj.setRatio(DAOUtil.stringConvFloatList(row.getString("ratio")));
			yj.setFix(DAOUtil.stringConvIntList(row.getString("fix")));
			yj.setProb1(row.getInt("prob1"));
			yj.setProb2(row.getInt("prob2"));
			return yj;
		}
	};

	/**
	 * 查询
	 */
	public YabiaoJiangli query(int id) {
		return queryForObject("select * from t_yabiao_jiangli where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<YabiaoJiangli> queryList() {
		return queryForList("select * from t_yabiao_jiangli", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_yabiao_jiangli where id=?", HANDLER, id);
	}
}