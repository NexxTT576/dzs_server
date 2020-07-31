package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.role.ChuShi;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 初始值DAO
 * 
 * @author 房曈
 *
 */
public class ChuShiDAO extends ConnectionResourceDAO implements QueryDateInterface<ChuShi> {

	private static ResultSetRowHandler<ChuShi> HANDLER = new ResultSetRowHandler<ChuShi>() {
		@Override
		public ChuShi handleRow(ResultSetRow row) throws Exception {
			ChuShi cs = new ChuShi();
			cs.setId(row.getInt("id"));
			cs.setLevel(row.getInt("level"));
			cs.setVip(row.getInt("vip"));
			cs.setSilver(row.getInt("silver"));
			cs.setCoin(row.getInt("coin"));
			cs.setExp(row.getInt("exp"));
			cs.setPower(row.getInt("power"));
			cs.setPmax(row.getInt("pmax"));
			cs.setNaili(row.getInt("naili"));
			cs.setNmax(row.getInt("nmax"));
			return cs;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ChuShi
	 */
	public void add(ChuShi cs) {
		String sql = "insert into t_chushi(" + "level," + "vip," + "silver," + "coin," + "exp," + "power," + "pmax,"
				+ "naili," + "nmax" + ")values(?,?,?,?,?,?,?,?,?)";
		execute(sql, cs.getLevel(), cs.getVip(), cs.getSilver(), cs.getCoin(), cs.getExp(), cs.getPower(), cs.getPmax(),
				cs.getNaili(), cs.getNmax());
	}

	/**
	 * 更新
	 * 
	 * @param ChuShi
	 */
	public void update(ChuShi cs) {
		String sql = "update t_chushi set " + " level=?," + " vip=?," + " silver=?," + " coin=?," + " exp=?,"
				+ " power=?," + " pmax=?," + " naili=?," + " nmax=? " + " where id=?";
		executeUpdate(sql, cs.getLevel(), cs.getVip(), cs.getSilver(), cs.getCoin(), cs.getExp(), cs.getPower(),
				cs.getPmax(), cs.getNaili(), cs.getNmax(), cs.getId());
	}

	/**
	 * 查询
	 */
	public ChuShi query(int id) {
		return queryForObject("select * from t_chushi where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ChuShi> queryList() {
		return queryForList("select * from t_chushi", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_chushi where id=?", HANDLER, id);
	}
}