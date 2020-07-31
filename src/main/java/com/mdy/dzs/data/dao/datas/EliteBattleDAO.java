package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.challengebattle.EliteBattle;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 精英副本配置DAO
 * 
 * @author 房曈
 *
 */
public class EliteBattleDAO extends ConnectionResourceDAO implements QueryDateInterface<EliteBattle> {

	private static ResultSetRowHandler<EliteBattle> HANDLER = new ResultSetRowHandler<EliteBattle>() {
		@Override
		public EliteBattle handleRow(ResultSetRow row) throws Exception {
			EliteBattle eb = new EliteBattle();
			eb.setId(row.getInt("id"));
			eb.setName(row.getString("name"));
			eb.setPreid(row.getInt("preid"));
			eb.setPrebattle(row.getInt("prebattle"));
			eb.setArrNum(DAOUtil.stringConvIntList(row.getString("arr_num")));
			eb.setPrice(row.getInt("price"));
			eb.setAddprice(row.getInt("addprice"));
			eb.setBoss(row.getString("boss"));
			eb.setArrIcon(row.getString("arr_icon"));
			eb.setProb(row.getInt("prob"));
			eb.setSilver(row.getInt("silver"));
			eb.setXiahun(row.getInt("xiahun"));
			eb.setNpc(DAOUtil.stringConvIntList(row.getString("npc")));
			return eb;
		}
	};

	/**
	 * 查询
	 */
	public EliteBattle query(int id) {
		return queryForObject("select * from t_jingyingfuben where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<EliteBattle> queryList() {
		return queryForList("select * from t_jingyingfuben", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_jingyingfuben where id=?", HANDLER, id);
	}
}