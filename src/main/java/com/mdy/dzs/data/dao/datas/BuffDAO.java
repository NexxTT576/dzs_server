package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.battle.Buff;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * buffDAO
 * 
 * @author 房曈
 *
 */
public class BuffDAO extends ConnectionResourceDAO implements QueryDateInterface<Buff> {

	private static ResultSetRowHandler<Buff> HANDLER = new ResultSetRowHandler<Buff>() {
		@Override
		public Buff handleRow(ResultSetRow row) throws Exception {
			Buff b = new Buff();
			b.setId(row.getInt("id"));
			b.setName(row.getString("name"));
			b.setGroup(row.getInt("group"));
			b.setCoexist(row.getInt("coexist"));
			b.setEffect(row.getInt("effect"));
			b.setSide(row.getInt("side"));
			b.setTarget(row.getInt("target"));
			b.setProb(row.getInt("prob"));
			b.setRound(row.getInt("round"));
			b.setAttCalc(row.getInt("attCalc"));
			b.setParam(DAOUtil.stringConvIntList(row.getString("param")));
			b.setBase(DAOUtil.stringConvIntList(row.getString("base")));
			b.setBaseRate(DAOUtil.stringConvIntList(row.getString("baseRate")));
			b.setBaseMod(DAOUtil.stringConvIntList(row.getString("baseMod")));
			b.setRare(DAOUtil.stringConvIntList(row.getString("rare")));
			b.setRareRate(DAOUtil.stringConvIntList(row.getString("rareRate")));
			b.setValue6(row.getInt("value6"));
			b.setNature7(DAOUtil.stringConvIntList(row.getString("nature7")));
			b.setDefBuffId(DAOUtil.stringConvIntList(row.getString("defBuffId")));
			b.setIsNow(row.getInt("isNow"));
			return b;
		}
	};

	/**
	 * 查询
	 */
	public Buff query(int id) {
		return queryForObject("select * from t_buff where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Buff> queryList() {
		return queryForList("select * from t_buff", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_buff where id=?", HANDLER, id);
	}
}