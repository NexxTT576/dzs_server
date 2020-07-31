package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.challengebattle.ActivityBattle;
import com.mdy.dzs.data.domain.challengebattle.ActivityBattleJFJP;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 活动副本配置DAO
 * 
 * @author 房曈
 *
 */
public class ActivityBattleDAO extends ConnectionResourceDAO implements QueryDateInterface<ActivityBattle> {

	@SuppressWarnings("unchecked")
	private static ResultSetRowHandler<ActivityBattle> HANDLER = new ResultSetRowHandler<ActivityBattle>() {
		@Override
		public ActivityBattle handleRow(ResultSetRow row) throws Exception {
			ActivityBattle ab = new ActivityBattle();
			ab.setId(row.getInt("id"));
			ab.setName(row.getString("name"));
			ab.setPrebattle(row.getInt("prebattle"));
			ab.setArrNum(DAOUtil.stringConvIntList(row.getString("arr_num")));
			ab.setPrice(row.getInt("price"));
			ab.setAddprice(row.getInt("addprice"));
			ab.setNpc(DAOUtil.stringConvIntList(row.getString("npc")));
			ab.setWave(row.getInt("wave"));
			ab.setBagCheckId(row.getInt("bag"));
			ab.setOpenSysId(row.getInt("openid"));
			ab.setOrder(row.getInt("order"));
			ab.setArrPrebattle(DAOUtil.stringConvIntList(row.getString("arr_prebattle")));
			ab.setProbid(DAOUtil.stringConvIntList(row.getString("probid")));
			ab.setNpcid(JSONUtil.fromJson(row.getString("npcid"), List.class));
			ab.setIsbuy(row.getInt("isbuy"));
			ab.setBuyPrice(DAOUtil.stringConvIntList(row.getString("buy_price")));
			return ab;
		}
	};

	/**
	 * 查询
	 */
	public ActivityBattle query(int id) {
		return queryForObject("select * from t_huodongfuben where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ActivityBattle> queryList() {
		return queryForList("select * from t_huodongfuben", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_huodongfuben where id=?", HANDLER, id);
	}

	////////////////////////////////////////////////////////////////////////
	private static ResultSetRowHandler<ActivityBattleJFJP> JFJP_HANDLER = new ResultSetRowHandler<ActivityBattleJFJP>() {
		@Override
		public ActivityBattleJFJP handleRow(ResultSetRow row) throws Exception {
			ActivityBattleJFJP abjfjp = new ActivityBattleJFJP();
			abjfjp.setId(row.getInt("id"));
			abjfjp.setDamage(row.getInt("damage"));
			abjfjp.setSilver(row.getInt("silver"));
			abjfjp.setSumsilver(row.getInt("sumsilver"));
			abjfjp.setPer(row.getFloat("per"));
			return abjfjp;
		}
	};

	/**
	 * 查询列表
	 */
	public List<ActivityBattleJFJP> queryJFJPList() {
		return queryForList("select * from t_jiefujipin", JFJP_HANDLER);
	}
}