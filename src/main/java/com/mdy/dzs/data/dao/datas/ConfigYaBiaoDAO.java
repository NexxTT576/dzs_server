package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.dart.ConfigYaBiao;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 押镖数值设定表DAO
 * 
 * @author 白雪林
 *
 */
public class ConfigYaBiaoDAO extends ConnectionResourceDAO implements QueryDateInterface<ConfigYaBiao> {

	private static ResultSetRowHandler<ConfigYaBiao> HANDLER = new ResultSetRowHandler<ConfigYaBiao>() {
		@Override
		public ConfigYaBiao handleRow(ResultSetRow row) throws Exception {
			ConfigYaBiao cyb = new ConfigYaBiao();
			cyb.setId(row.getString("id"));
			cyb.setName(row.getString("name"));
			cyb.setValue(DAOUtil.stringConvIntList(row.getString("value")));
			cyb.setContent(row.getString("content"));
			return cyb;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ConfigYaBiao
	 */
	public void add(ConfigYaBiao cyb) {
		String sql = "insert into t_config_yabiao(" + "name," + "value," + "content" + ")values(?,?,?)";
		execute(sql, cyb.getName(), cyb.getValue(), cyb.getContent());
	}

	/**
	 * 更新
	 * 
	 * @param ConfigYaBiao
	 */
	public void update(ConfigYaBiao cyb) {
		String sql = "update t_config_yabiao set " + " name=?," + " value=?," + " content=? " + " where id=?";
		executeUpdate(sql, cyb.getName(), cyb.getValue(), cyb.getContent(), cyb.getId());
	}

	/**
	 * 查询
	 */
	public ConfigYaBiao query(int id) {
		return queryForObject("select * from t_config_yabiao where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ConfigYaBiao> queryList() {
		return queryForList("select * from t_config_yabiao", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_config_yabiao where id=?", HANDLER, id);
	}
}