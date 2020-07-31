package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.sword.LunjianProb;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 论剑奖励表DAO
 * 
 * @author zhou
 *
 */
public class LunjianProbDAO extends ConnectionResourceDAO implements QueryDateInterface<LunjianProb> {

	private static ResultSetRowHandler<LunjianProb> HANDLER = new ResultSetRowHandler<LunjianProb>() {
		@Override
		public LunjianProb handleRow(ResultSetRow row) throws Exception {
			LunjianProb lp = new LunjianProb();
			lp.setLevel(row.getInt("level"));
			lp.setProb1(row.getInt("prob1"));
			lp.setProb2(row.getInt("prob2"));
			lp.setProb3(row.getInt("prob3"));
			lp.setProb4(row.getInt("prob4"));
			lp.setProb5(row.getInt("prob5"));
			lp.setProb6(row.getInt("prob6"));
			lp.setProb7(row.getInt("prob7"));
			lp.setProb8(row.getInt("prob8"));
			lp.setProb9(row.getInt("prob9"));
			lp.setProb10(row.getInt("prob10"));
			lp.setProb11(row.getInt("prob11"));
			lp.setProb12(row.getInt("prob12"));
			lp.setProb13(row.getInt("prob13"));
			lp.setProb14(row.getInt("prob14"));
			lp.setProb15(row.getInt("prob15"));
			return lp;
		}
	};

	/**
	 * 添加
	 * 
	 * @param LunjianProb
	 */
	public void add(LunjianProb lp) {
		String sql = "insert into t_lunjianprob(" + "level," + "prob1," + "prob2," + "prob3," + "prob4," + "prob5,"
				+ "prob6," + "prob7," + "prob8," + "prob9," + "prob10," + "prob11," + "prob12," + "prob13," + "prob14,"
				+ "prob15" + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		execute(sql, lp.getLevel(), lp.getProb1(), lp.getProb2(), lp.getProb3(), lp.getProb4(), lp.getProb5(),
				lp.getProb6(), lp.getProb7(), lp.getProb8(), lp.getProb9(), lp.getProb10(), lp.getProb11(),
				lp.getProb12(), lp.getProb13(), lp.getProb14(), lp.getProb15());
	}

	/**
	 * 更新
	 * 
	 * @param LunjianProb
	 */
	public void update(LunjianProb lp) {
		String sql = "update t_lunjianprob set " +
		// " level=?," +
				" prob1=?," + " prob2=?," + " prob3=?," + " prob4=?," + " prob5=?," + " prob6=?," + " prob7=?,"
				+ " prob8=?," + " prob9=?," + " prob10=?," + " prob11=?," + " prob12=?," + " prob13=?," + " prob14=?,"
				+ " prob15=? " + " where level=?";
		executeUpdate(sql, lp.getLevel(), lp.getProb1(), lp.getProb2(), lp.getProb3(), lp.getProb4(), lp.getProb5(),
				lp.getProb6(), lp.getProb7(), lp.getProb8(), lp.getProb9(), lp.getProb10(), lp.getProb11(),
				lp.getProb12(), lp.getProb13(), lp.getProb14(), lp.getProb15()
		// lp.getId()
		);
	}

	/**
	 * 查询
	 */
	public LunjianProb query(int id) {
		return queryForObject("select * from t_lunjianprob where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<LunjianProb> queryList() {
		return queryForList("select * from t_lunjianprob", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_lunjianprob where level=?", HANDLER, id);
	}
}