package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.world.World;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 世界地图DAO
 * 
 * @author 房曈
 *
 */
public class WorldDAO extends ConnectionResourceDAO implements QueryDateInterface<World> {

	private static ResultSetRowHandler<World> HANDLER = new ResultSetRowHandler<World>() {
		@Override
		public World handleRow(ResultSetRow row) throws Exception {
			World w = new World();
			w.setId(row.getInt("id"));
			w.setName(row.getString("name"));
			w.setIcon(row.getString("icon"));
			w.setBackground(row.getString("background"));
			w.setBgm(row.getString("bgm"));
			w.setPreworld(row.getString("preworld"));
			w.setLevel(row.getInt("level"));
			w.setArrField(DAOUtil.stringConvIntList(row.getString("arr_field")));
			return w;
		}
	};

	/**
	 * 查询
	 */
	public World query(int id) {
		return queryForObject("select * from t_world where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<World> queryList() {
		return queryForList("select * from t_world", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_world where id=?", HANDLER, id);
	}
}