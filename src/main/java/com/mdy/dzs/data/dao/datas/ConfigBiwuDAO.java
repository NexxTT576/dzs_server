package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.biwu.ConfigBiwu;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 比武配置DAO
 * 
 * @author zhou
 *
 */
public class ConfigBiwuDAO extends ConnectionResourceDAO implements QueryDateInterface<ConfigBiwu> {

	private static ResultSetRowHandler<ConfigBiwu> HANDLER = new ResultSetRowHandler<ConfigBiwu>() {
		@Override
		public ConfigBiwu handleRow(ResultSetRow row) throws Exception {
			ConfigBiwu cb = new ConfigBiwu();
			cb.setId(row.getInt("id"));
			cb.setName(row.getString("name"));
			cb.setValue(DAOUtil.stringConvIntList(row.getString("value")));
			cb.setContent(row.getString("content"));
			return cb;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ConfigBiwu
	 */
	public void add(ConfigBiwu cb) {
		String sql = "insert into t_config_biwu(" + "name," + "value," + "content" + ")values(?,?,?)";
		execute(sql, cb.getName(), cb.getValue(), cb.getContent());
	}

	/**
	 * 更新
	 * 
	 * @param ConfigBiwu
	 */
	public void update(ConfigBiwu cb) {
		String sql = "update t_config_biwu set " + " name=?," + " value=?," + " content=? " + " where id=?";
		executeUpdate(sql, cb.getName(), cb.getValue(), cb.getContent(), cb.getId());
	}

	/**
	 * 查询
	 */
	public ConfigBiwu query(int id) {
		return queryForObject("select * from t_config_biwu where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ConfigBiwu> queryList() {
		return queryForList("select * from t_config_biwu", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_config_biwu where id=?", HANDLER, id);
	}
}
