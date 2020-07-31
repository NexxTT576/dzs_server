package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.battle.BattleSkill;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 战斗技能DAO
 * 
 * @author 房曈
 *
 */
public class BattleSkillDAO extends ConnectionResourceDAO implements QueryDateInterface<BattleSkill> {

	private static ResultSetRowHandler<BattleSkill> HANDLER = new ResultSetRowHandler<BattleSkill>() {
		@Override
		public BattleSkill handleRow(ResultSetRow row) throws Exception {
			BattleSkill bs = new BattleSkill();
			bs.setId(row.getInt("id"));
			bs.setOld(row.getString("old"));
			bs.setName(row.getString("name"));
			bs.setEffect(row.getString("effect"));
			bs.setType(row.getInt("type"));
			bs.setStyle(row.getInt("style"));
			bs.setPosition(row.getInt("position"));
			bs.setAnger(row.getInt("anger"));
			bs.setNature1(row.getInt("nature1"));
			bs.setValue1(row.getInt("value1"));
			bs.setDType1(row.getInt("dType1"));
			bs.setDAttr1(DAOUtil.stringConvIntList(row.getString("dAttr1")));
			bs.setDType2(row.getInt("dType2"));
			bs.setDAttr2(DAOUtil.stringConvIntList(row.getString("dAttr2")));
			bs.setDType3(row.getInt("dType3"));
			bs.setDAttr3(DAOUtil.stringConvIntList(row.getString("dAttr3")));
			bs.setProb(row.getInt("prob"));
			bs.setBuff(DAOUtil.stringConvIntList(row.getString("buff")));
			bs.setdCount(row.getInt("dCount"));
			bs.setbSide(row.getInt("bSide"));
			bs.setbTarget(row.getInt("bTarget"));
			return bs;
		}
	};

	/**
	 * 查询
	 */
	public BattleSkill query(int id) {
		return queryForObject("select * from t_battleskill where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<BattleSkill> queryList() {
		return queryForList("select * from t_battleskill", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_battleskill where id=?", HANDLER, id);
	}
}