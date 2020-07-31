package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.battle.Npc;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 战斗的怪物站位模型DAO
 * 
 * @author 房曈
 *
 */
public class NpcDAO extends ConnectionResourceDAO implements QueryDateInterface<Npc> {

	private static ResultSetRowHandler<Npc> HANDLER = new ResultSetRowHandler<Npc>() {
		@Override
		public Npc handleRow(ResultSetRow row) throws Exception {
			Npc n = new Npc();
			n.setId(row.getInt("id"));
			n.setName(row.getString("name"));
			n.setCondition(row.getInt("condition"));
			n.setPara(row.getInt("para"));
			n.setNpc1(row.getInt("npc1"));
			n.setNpc2(row.getInt("npc2"));
			n.setNpc3(row.getInt("npc3"));
			n.setNpc4(row.getInt("npc4"));
			n.setNpc5(row.getInt("npc5"));
			n.setNpc6(row.getInt("npc6"));
			n.setPos(DAOUtil.stringConvIntList(row.getString("pos")));
			n.setScale(DAOUtil.stringConvFloatList(row.getString("scale")));
			return n;
		}
	};

	/**
	 * 查询
	 */
	public Npc query(int id) {
		return queryForObject("select * from t_npc where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Npc> queryList() {
		return queryForList("select * from t_npc", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_npc where id=?", HANDLER, id);
	}
}