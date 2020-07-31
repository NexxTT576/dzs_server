package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.superbattle.SuperBattle;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 系统布阵条件DAO
 * 
 * @author zhou
 *
 */
public class SuperBattleDAO extends ConnectionResourceDAO implements QueryDateInterface<SuperBattle> {

	private static ResultSetRowHandler<SuperBattle> HANDLER = new ResultSetRowHandler<SuperBattle>() {
		@Override
		public SuperBattle handleRow(ResultSetRow row) throws Exception {
			SuperBattle sb = new SuperBattle();
			sb.setId(row.getInt("id"));
			sb.setSysId(row.getInt("sysId"));
			sb.setSysName(row.getString("sysName"));
			sb.setSex(row.getInt("sex"));
			sb.setLevel(row.getInt("level"));
			sb.setRemHealth(row.getInt("remHealth"));
			sb.setRemUsed(row.getInt("remUsed"));
			sb.setRemDead(row.getInt("remDead"));
			sb.setRemBattle(row.getInt("remBattle"));
			sb.setInitBattle(row.getInt("initBattle"));
			sb.setResetCond(row.getInt("resetCond"));
			sb.setResetPara(row.getString("resetPara"));
			return sb;
		}
	};

	/**
	 * 添加
	 * 
	 * @param SuperBattle
	 */
	public void add(SuperBattle sb) {
		String sql = "insert into t_superbattle(" + "sysId," + "sysName," + "sex," + "level," + "remHealth,"
				+ "remUsed," + "remDead," + "remBattle," + "initBattle," + "resetCond," + "resetPara"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?)";
		execute(sql, sb.getSysId(), sb.getSysName(), sb.getSex(), sb.getLevel(), sb.getRemHealth(), sb.getRemUsed(),
				sb.getRemDead(), sb.getRemBattle(), sb.getInitBattle(), sb.getResetCond(), sb.getResetPara());
	}

	/**
	 * 更新
	 * 
	 * @param SuperBattle
	 */
	public void update(SuperBattle sb) {
		String sql = "update t_superbattle set " + " sysId=?," + " sysName=?," + " sex=?," + " level=?,"
				+ " remHealth=?," + " remUsed=?," + " remDead=?," + " remBattle=?," + " initBattle=?," + " resetCond=?,"
				+ " resetPara=? " + " where id=?";
		executeUpdate(sql, sb.getSysId(), sb.getSysName(), sb.getSex(), sb.getLevel(), sb.getRemHealth(),
				sb.getRemUsed(), sb.getRemDead(), sb.getRemBattle(), sb.getInitBattle(), sb.getResetCond(),
				sb.getResetPara(), sb.getId());
	}

	/**
	 * 查询
	 */
	public SuperBattle query(int id) {
		return queryForObject("select * from t_superbattle where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<SuperBattle> queryList() {
		return queryForList("select * from t_superbattle", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_superbattle where id=?", HANDLER, id);
	}
}