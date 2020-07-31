package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.viplevel.VipCrit;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 强化暴击概率DAO
 * 
 * @author 房曈
 *
 */
public class CritDAO extends ConnectionResourceDAO implements QueryDateInterface<VipCrit> {

	private static ResultSetRowHandler<VipCrit> HANDLER = new ResultSetRowHandler<VipCrit>() {
		@Override
		public VipCrit handleRow(ResultSetRow row) throws Exception {
			VipCrit c = new VipCrit();
			c.setVip(row.getInt("vip"));
			c.setNocrit(DAOUtil.stringConvIntList(row.getString("nocrit")));
			return c;
		}
	};

	/**
	 * 查询
	 */
	public VipCrit query(int id) {
		return queryForObject("select * from t_crit where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<VipCrit> queryList() {
		return queryForList("select * from t_crit", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_crit where id=?", HANDLER, id);
	}
}