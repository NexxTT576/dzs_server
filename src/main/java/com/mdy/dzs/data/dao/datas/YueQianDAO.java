package com.mdy.dzs.data.dao.datas;

import com.mdy.dzs.data.domain.yueqian.YueQian;
import java.util.List;

import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 月签DAO
 * 
 * @author 白雪林
 *
 */
public class YueQianDAO extends ConnectionResourceDAO implements QueryDateInterface<YueQian> {

	private static ResultSetRowHandler<YueQian> HANDLER = new ResultSetRowHandler<YueQian>() {
		@Override
		public YueQian handleRow(ResultSetRow row) throws Exception {
			YueQian yq = new YueQian();
			yq.setId(row.getInt("id"));
			yq.setMonth(row.getInt("month"));
			yq.setTime(row.getInt("time"));
			yq.setType(row.getInt("type"));
			yq.setItemid(row.getInt("itemid"));
			yq.setNum(row.getInt("num"));
			yq.setVip(row.getInt("vip"));
			return yq;
		}
	};

	/**
	 * 添加
	 * 
	 * @param YueQian
	 */
	public void add(YueQian yq) {
		String sql = "insert into t_yueqian(" + "month," + "time," + "type," + "itemid," + "num," + "vip"
				+ ")values(?,?,?,?,?,?)";
		execute(sql, yq.getMonth(), yq.getTime(), yq.getType(), yq.getItemid(), yq.getNum(), yq.getVip());
	}

	/**
	 * 更新
	 * 
	 * @param YueQian
	 */
	public void update(YueQian yq) {
		String sql = "update t_yueqian set " + " month=?," + " time=?," + " type=?," + " itemid=?," + " num=?,"
				+ " vip=? " + " where id=?";
		executeUpdate(sql, yq.getMonth(), yq.getTime(), yq.getType(), yq.getItemid(), yq.getNum(), yq.getVip(),
				yq.getId());
	}

	/**
	 * 查询
	 */
	public YueQian query(int id) {
		return queryForObject("select * from t_yueqian where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<YueQian> queryList() {
		return queryForList("select * from t_yueqian", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_yueqian where id=?", HANDLER, id);
	}
}