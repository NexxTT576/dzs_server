package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.biwu.BiwuJiangli;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 比武奖励DAO
 * 
 * @author zhou
 *
 */
public class BiwuJiangliDAO extends ConnectionResourceDAO implements QueryDateInterface<BiwuJiangli> {

	private static ResultSetRowHandler<BiwuJiangli> HANDLER = new ResultSetRowHandler<BiwuJiangli>() {
		@Override
		public BiwuJiangli handleRow(ResultSetRow row) throws Exception {
			BiwuJiangli bj = new BiwuJiangli();
			bj.setId(row.getInt("id"));
			bj.setMin(row.getInt("min"));
			bj.setMax(row.getInt("max"));
			bj.setRatio(row.getFloat("ratio"));
			bj.setFix(row.getInt("fix"));
			bj.setRewardIds(DAOUtil.stringConvIntList(row.getString("rewardIds")));
			bj.setRewardTypes(DAOUtil.stringConvIntList(row.getString("rewardTypes")));
			bj.setRewardNums(DAOUtil.stringConvIntList(row.getString("rewardNums")));
			return bj;
		}
	};

	/**
	 * 添加
	 * 
	 * @param BiwuJiangli
	 */
	public void add(BiwuJiangli bj) {
		String sql = "insert into t_biwu_jiangli(" + "min," + "max," + "ratio," + "fix," + "yuanbao," + "rongyu"
				+ ")values(?,?,?,?,?,?)";
		execute(sql, bj.getMin(), bj.getMax(), bj.getRatio(), bj.getFix(), bj.getRewardIds(), bj.getRewardNums(),
				bj.getRewardTypes());
	}

	/**
	 * 更新
	 * 
	 * @param BiwuJiangli
	 */
	public void update(BiwuJiangli bj) {
		String sql = "update t_biwu_jiangli set " + " min=?," + " max=?," + " ratio=?," + " fix=?," + " yuanbao=?,"
				+ " rongyu=? " + " where id=?";
		executeUpdate(sql, bj.getMin(), bj.getMax(), bj.getRatio(), bj.getFix(), bj.getRewardIds(), bj.getRewardNums(),
				bj.getRewardTypes());
	}

	/**
	 * 查询
	 */
	public BiwuJiangli query(int id) {
		return queryForObject("select * from t_biwu_jiangli where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<BiwuJiangli> queryList() {
		return queryForList("select * from t_biwu_jiangli", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_biwu_jiangli where id=?", HANDLER, id);
	}
}