package com.mdy.dzs.data.dao;

import java.util.List;

import com.mdy.dzs.data.domain.version.Version;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 版本DAO
 * 
 * @author 房曈
 *
 */
public class VersionDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<Version> HANDLER = new ResultSetRowHandler<Version>() {
		@SuppressWarnings("unchecked")
		@Override
		public Version handleRow(ResultSetRow row) throws Exception {
			Version v = new Version();
			v.setVersion(row.getString("version"));
			v.setDesc(row.getString("desc"));
			v.setUpdateCards(JSONUtil.fromJson(row.getString("update_cards"), List.class));
			v.setCreateTime(row.getTimestamp("create_time"));
			return v;
		}
	};

	/**
	 * 查询列表
	 */
	public List<Version> queryList() {
		return queryForList("select * from t_version ORDER BY create_time", HANDLER);
	}
}