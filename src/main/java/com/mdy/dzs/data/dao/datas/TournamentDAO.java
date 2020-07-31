package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.biwu.Tournament;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 比武选人DAO
 * 
 * @author zhou
 *
 */
public class TournamentDAO extends ConnectionResourceDAO implements QueryDateInterface<Tournament> {

	private static ResultSetRowHandler<Tournament> HANDLER = new ResultSetRowHandler<Tournament>() {
		@Override
		public Tournament handleRow(ResultSetRow row) throws Exception {
			Tournament t = new Tournament();
			t.setId(row.getInt("id"));
			t.setPosition(row.getInt("position"));
			t.setScore1(DAOUtil.stringConvIntList(row.getString("score1")));
			t.setScore2(DAOUtil.stringConvIntList(row.getString("score2")));
			t.setScore3(DAOUtil.stringConvIntList(row.getString("score3")));
			t.setAttack1(DAOUtil.stringConvIntList(row.getString("attack1")));
			t.setAttack2(DAOUtil.stringConvIntList(row.getString("attack2")));
			t.setAttack3(DAOUtil.stringConvIntList(row.getString("attack3")));
			return t;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Tournament
	 */
	public void add(Tournament t) {
		String sql = "insert into t_tournament(" + "position," + "score1," + "score2," + "score3," + "attack1,"
				+ "attack2," + "attack3" + ")values(?,?,?,?,?,?,?)";
		execute(sql, t.getPosition(), t.getScore1(), t.getScore2(), t.getScore3(), t.getAttack1(), t.getAttack2(),
				t.getAttack3());
	}

	/**
	 * 更新
	 * 
	 * @param Tournament
	 */
	public void update(Tournament t) {
		String sql = "update t_tournament set " + " position=?," + " score1=?," + " score2=?," + " score3=?,"
				+ " attack1=?," + " attack2=?," + " attack3=? " + " where id=?";
		executeUpdate(sql, t.getPosition(), t.getScore1(), t.getScore2(), t.getScore3(), t.getAttack1(), t.getAttack2(),
				t.getAttack3(), t.getId());
	}

	/**
	 * 查询
	 */
	public Tournament query(int id) {
		return queryForObject("select * from t_tournament where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Tournament> queryList() {
		return queryForList("select * from t_tournament", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_tournament where id=?", HANDLER, id);
	}
}