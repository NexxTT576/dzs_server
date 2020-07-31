package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.shop.Hotel;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 酒馆DAO
 * 
 * @author 房曈
 *
 */
public class HotelDAO extends ConnectionResourceDAO implements QueryDateInterface<Hotel> {

	private static ResultSetRowHandler<Hotel> HANDLER = new ResultSetRowHandler<Hotel>() {
		@Override
		public Hotel handleRow(ResultSetRow row) throws Exception {
			Hotel h = new Hotel();
			h.setType(row.getInt("type"));
			h.setCoin(row.getInt("coin"));
			h.setItemid(row.getInt("itemid"));
			h.setNumber(row.getInt("number"));
			h.setTime(row.getInt("time"));
			h.setFirst(row.getInt("first"));
			h.setProbid1(row.getInt("probid1"));
			h.setProbid2(row.getInt("probid2"));
			return h;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Hotel
	 */
	public void add(Hotel h) {
		String sql = "insert into t_hotel(" + "type," + "coin," + "itemid," + "number," + "time," + "first,"
				+ "probid1," + "probid2" + ")values(?,?,?,?,?,?,?,?)";
		execute(sql, h.getType(), h.getCoin(), h.getItemid(), h.getNumber(), h.getTime(), h.getFirst(), h.getProbid1(),
				h.getProbid2());
	}

	/**
	 * 更新
	 * 
	 * @param Hotel
	 */
	public void update(Hotel h) {
		String sql = "update t_hotel set " + " type=?," + " coin=?," + " itemid=?," + " number=?," + " time=?,"
				+ " first=?," + " probid1=?," + " probid2=? " + " where type=?";
		executeUpdate(sql, h.getType(), h.getCoin(), h.getItemid(), h.getNumber(), h.getTime(), h.getFirst(),
				h.getProbid1(), h.getProbid2(), h.getType());
	}

	/**
	 * 查询
	 */
	public Hotel query(int id) {
		return queryForObject("select * from t_hotel where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Hotel> queryList() {
		return queryForList("select * from t_hotel", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_hotel where id=?", HANDLER, id);
	}
}