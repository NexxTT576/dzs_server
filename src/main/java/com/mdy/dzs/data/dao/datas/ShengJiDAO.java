package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.role.ShengJi;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 主角升级DAO
 * 
 * @author 房曈
 *
 */
public class ShengJiDAO extends ConnectionResourceDAO implements QueryDateInterface<ShengJi> {

	private static ResultSetRowHandler<ShengJi> HANDLER = new ResultSetRowHandler<ShengJi>() {
		@Override
		public ShengJi handleRow(ResultSetRow row) throws Exception {
			ShengJi sj = new ShengJi();
			sj.setId(row.getInt("id"));
			sj.setLevel(row.getInt("level"));
			sj.setUplevel(row.getInt("uplevel"));
			sj.setNaili(row.getInt("naili"));
			sj.setCoin(row.getInt("coin"));
			sj.setNum(row.getInt("num"));
			sj.setArrSystem(row.getString("arr_system"));
			sj.setType(row.getInt("type"));
			sj.setItem(row.getInt("item"));
			sj.setNum2(row.getInt("num2"));
			return sj;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ShengJi
	 */
	public void add(ShengJi sj) {
		String sql = "insert into t_shengji(" + "level," + "uplevel," + "naili," + "coin," + "num," + "arr_system"
				+ ")values(?,?,?,?,?,?)";
		execute(sql, sj.getLevel(), sj.getUplevel(), sj.getNaili(), sj.getCoin(), sj.getNum(), sj.getArrSystem());
	}

	/**
	 * 更新
	 * 
	 * @param ShengJi
	 */
	public void update(ShengJi sj) {
		String sql = "update t_shengji set " + " level=?," + " uplevel=?," + " naili=?," + " coin=?," + " num=?,"
				+ " arr_system=? " + " where id=?";
		executeUpdate(sql, sj.getLevel(), sj.getUplevel(), sj.getNaili(), sj.getCoin(), sj.getNum(), sj.getArrSystem(),
				sj.getId());
	}

	/**
	 * 查询
	 */
	public ShengJi query(int id) {
		return queryForObject("select * from t_shengji where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ShengJi> queryList() {
		return queryForList("select * from t_shengji", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_shengji where id=?", HANDLER, id);
	}
}