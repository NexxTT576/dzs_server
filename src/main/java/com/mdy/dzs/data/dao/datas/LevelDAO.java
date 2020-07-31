package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.role.Level;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 等级DAO
 * 
 * @author 房曈
 *
 */
public class LevelDAO extends ConnectionResourceDAO implements QueryDateInterface<Level> {

	private static ResultSetRowHandler<Level> HANDLER = new ResultSetRowHandler<Level>() {
		@Override
		public Level handleRow(ResultSetRow row) throws Exception {
			Level l = new Level();
			l.setId(row.getInt("id"));
			l.setLevel(row.getInt("level"));
			l.setExp(row.getInt("exp"));
			return l;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Level
	 */
	public void add(Level l) {
		String sql = "insert into t_level(" + "level," + "exp" + ")values(?,?)";
		execute(sql, l.getLevel(), l.getExp());
	}

	/**
	 * 更新
	 * 
	 * @param Level
	 */
	public void update(Level l) {
		String sql = "update t_level set " + " level=?," + " exp=? " + " where id=?";
		executeUpdate(sql, l.getLevel(), l.getExp(), l.getId());
	}

	/**
	 * 查询
	 */
	public Level query(int id) {
		return queryForObject("select * from t_level where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Level> queryList() {
		return queryForList("select * from t_level", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_level where id=?", HANDLER, id);
	}
}