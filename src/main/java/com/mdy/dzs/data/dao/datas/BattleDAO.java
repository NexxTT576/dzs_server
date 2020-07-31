package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.battle.Battle;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 战斗DAO
 * 
 * @author 房曈
 *
 */
public class BattleDAO extends ConnectionResourceDAO implements QueryDateInterface<Battle> {

	private static ResultSetRowHandler<Battle> HANDLER = new ResultSetRowHandler<Battle>() {
		@Override
		public Battle handleRow(ResultSetRow row) throws Exception {
			Battle b = new Battle();
			b.setId(row.getInt("id"));
			b.setName(row.getString("name"));
			b.setField(row.getInt("field"));
			b.setNumber(row.getInt("number"));
			b.setLianzhan(row.getInt("lianzhan"));
			b.setPower(row.getInt("power"));
			b.setReborn(row.getInt("reborn"));
			b.setArrDropid(DAOUtil.stringConvIntList(row.getString("arr_dropid")));
			b.setStar(row.getInt("star"));

			b.setNpc1(row.getInt("npc1"));
			b.setCoin1(DAOUtil.stringConvIntList(row.getString("coin1")));
			b.setNum1(DAOUtil.stringConvIntList(row.getString("num1")));
			b.setNpc2(row.getInt("npc2"));
			b.setCoin2(DAOUtil.stringConvIntList(row.getString("coin2")));
			b.setNum2(DAOUtil.stringConvIntList(row.getString("num2")));
			b.setNpc3(row.getInt("npc3"));
			b.setCoin3(DAOUtil.stringConvIntList(row.getString("coin3")));
			b.setNum3(DAOUtil.stringConvIntList(row.getString("num3")));
			b.setDrop(row.getInt("drop"));
			b.setSbattle(row.getInt("sbattle"));
			b.setArrCard(DAOUtil.stringConvIntList(row.getString("arr_card")));
			b.setArrPos(DAOUtil.stringConvIntList(row.getString("arr_pos")));
			b.setArrNature(DAOUtil.stringConvIntList(row.getString("arr_nature")));
			b.setArrNum(DAOUtil.stringConvIntList(row.getString("arr_num")));
			b.setNpc4(row.getInt("npc4"));

			return b;
		}
	};

	/**
	 * 查询
	 */
	public Battle query(int id) {
		return queryForObject("select * from t_battle where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Battle> queryList() {
		return queryForList("select * from t_battle", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_battle where id=?", HANDLER, id);
	}
}