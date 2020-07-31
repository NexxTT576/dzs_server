package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.broad.BroadData;
import com.mdy.dzs.game.domain.broad.Broadcast;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 广播DAO
 * 
 * @author 房曈
 *
 */
public class BroadcastDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<Broadcast> HANDLER = new ResultSetRowHandler<Broadcast>() {
		@SuppressWarnings("unchecked")
		@Override
		public Broadcast handleRow(ResultSetRow row) throws Exception {
			Broadcast b = new Broadcast();
			b.setId(row.getInt("id"));
			b.setTime(row.getTimestamp("time"));
			b.setAccount(row.getString("account"));
			b.setStrId(row.getInt("strId"));
			b.setType(row.getInt("type"));
			b.setColor(JSONUtil.fromJson(row.getString("color"), List.class));
			List<BroadData> paralist = JSONUtil.fromJsonList(row.getString("paralist"), BroadData.class);
			b.setParalist(paralist);
			b.setPf(row.getString("pf"));
			b.setPara1(row.getString("para1"));
			b.setPara2(row.getString("para2"));
			b.setPara3(row.getString("para3"));
			b.setPara4(row.getString("para4"));
			b.setPara5(row.getString("para5"));
			b.setPara6(row.getString("para6"));
			b.setPara7(row.getString("para7"));
			b.setPara8(row.getString("para8"));
			return b;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Broadcast
	 */
	public void add(Broadcast b) {
		String sql = "insert into t_broadcast(" + "time," + "account," + "strId," + "type," + "paralist," + "color,"
				+ "para1," + "para2," + "para3," + "para4," + "para5," + "para6," + "para7," + "para8"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		execute(sql, b.getTime(), b.getAccount(), b.getStrId(), b.getType(), JSONUtil.toJson(b.getParalist()),
				JSONUtil.toJson(b.getColor()), b.getPara1(), b.getPara2(), b.getPara3(), b.getPara4(), b.getPara5(),
				b.getPara6(), b.getPara7(), b.getPara8());
	}

	/**
	 * 更新
	 * 
	 * @param Broadcast
	 */
	public void update(Broadcast b) {
		String sql = "update t_broadcast set " + " time=?," + " account=?," + " strId=?," + " type=?," + " paralist=?,"
				+ " color=?," + " para1=?," + " para2=?," + " para3=?," + " para4=?," + " para5=?," + " para6=?,"
				+ " para7=?," + " para8=? " + " where id=?";
		executeUpdate(sql, b.getTime(), b.getAccount(), b.getStrId(), b.getType(), JSONUtil.toJson(b.getParalist()),
				JSONUtil.toJson(b.getColor()), b.getPara1(), b.getPara2(), b.getPara3(), b.getPara4(), b.getPara5(),
				b.getPara6(), b.getPara7(), b.getPara8(), b.getId());
	}

	/**
	 * 查询
	 */
	public Broadcast query(int id) {
		return queryForObject("select * from t_broadcast where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Broadcast> queryList() {
		return queryForList("select * from t_broadcast", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_broadcast where id=?", HANDLER, id);
	}

	public List<Broadcast> queryViewList(Date lastTime, String acc) {
		return queryForList("select * from t_broadcast where time > ? and type=2 and account != ? limit 0,30", HANDLER,
				lastTime, acc);
	}

	public List<Broadcast> querySystemSet() {// 系统定时
		return queryForList("select * from t_broadcast where type=1", HANDLER);
	}

	public List<Broadcast> queryGameClose(String pf) {// 关服
		return queryForList("select * from t_broadcast where type=4 and (pf='0' or pf=?)", HANDLER, pf);
	}

	public List<Broadcast> queryAllGameClose() {// 关服
		return queryForList("select * from t_broadcast where type=4", HANDLER);
	}

	public void deleteViewList() {
		execute("delete from t_broadcast where type=2");
	}

	public void deleteSystemSet() {
		// TODO 自动生成的方法存根

	}

	public void deleteGameClose(List<Integer> deleteClose) {
		String inString = "(";
		for (int i = 0; i < deleteClose.size(); i++) {
			inString += deleteClose.get(i) + ",";
		}
		inString = inString.substring(0, inString.length() - 1);
		inString += ")";

		execute("delete from t_broadcast where id in " + inString);
	}

}