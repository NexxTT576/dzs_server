package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.robot.MoBan;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 机器人模板DAO
 * 
 * @author 房曈
 *
 */
public class MoBanDAO extends ConnectionResourceDAO implements QueryDateInterface<MoBan> {

	private static ResultSetRowHandler<MoBan> HANDLER = new ResultSetRowHandler<MoBan>() {
		@Override
		public MoBan handleRow(ResultSetRow row) throws Exception {
			MoBan mb = new MoBan();
			mb.setLevel(row.getInt("level"));
			mb.setArrProb(DAOUtil.stringConvIntList(row.getString("arr_prob")));
			mb.setWhite(DAOUtil.stringConvIntList(row.getString("white")));
			mb.setGreen(DAOUtil.stringConvIntList(row.getString("green")));
			mb.setBlue(DAOUtil.stringConvIntList(row.getString("blue")));
			mb.setPurple(DAOUtil.stringConvIntList(row.getString("purple")));
			mb.setGold(DAOUtil.stringConvIntList(row.getString("gold")));
			mb.setAnger(row.getInt("anger"));
			mb.setLead(DAOUtil.stringConvIntList(row.getString("lead")));
			mb.setBase(DAOUtil.stringConvIntList(row.getString("base")));
			mb.setBaseRate(DAOUtil.stringConvIntList(row.getString("baseRate")));
			mb.setBaseMod(DAOUtil.stringConvIntList(row.getString("baseMod")));
			mb.setRare(DAOUtil.stringConvIntList(row.getString("rare")));
			mb.setRareRate(DAOUtil.stringConvIntList(row.getString("rareRate")));
			mb.setHeal(DAOUtil.stringConvIntList(row.getString("heal")));
			mb.setRate(row.getInt("rate"));
			mb.setResist(row.getInt("resist"));
			mb.setCrit(DAOUtil.stringConvIntList(row.getString("crit")));
			mb.setDrop(DAOUtil.stringConvIntList(row.getString("drop")));
			mb.setDedrop(DAOUtil.stringConvIntList(row.getString("dedrop")));
			mb.setMax(DAOUtil.stringConvIntList(row.getString("max")));
			mb.setProb(row.getInt("prob"));
			mb.setProb1(row.getInt("prob1"));
			return mb;
		}
	};

	/**
	 * 查询
	 */
	public MoBan query(int id) {
		return queryForObject("select * from t_moban where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<MoBan> queryList() {
		return queryForList("select * from t_moban", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_moban where id=?", HANDLER, id);
	}
}