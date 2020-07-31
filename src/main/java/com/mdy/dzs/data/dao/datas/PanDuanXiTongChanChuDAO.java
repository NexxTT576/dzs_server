package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.packet.PanDuanXiTongChanChu;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 系统产出DAO
 * 
 * @author 房曈
 *
 */
public class PanDuanXiTongChanChuDAO extends ConnectionResourceDAO implements QueryDateInterface<PanDuanXiTongChanChu> {

	private static ResultSetRowHandler<PanDuanXiTongChanChu> HANDLER = new ResultSetRowHandler<PanDuanXiTongChanChu>() {
		@Override
		public PanDuanXiTongChanChu handleRow(ResultSetRow row) throws Exception {
			PanDuanXiTongChanChu pdxtcc = new PanDuanXiTongChanChu();
			pdxtcc.setId(row.getInt("id"));
			pdxtcc.setSystem(row.getString("system"));
			pdxtcc.setDecide(row.getInt("decide"));
			pdxtcc.setCond(row.getString("cond"));
			pdxtcc.setBag(DAOUtil.stringConvIntList(row.getString("bag")));
			return pdxtcc;
		}
	};

	/**
	 * 添加
	 * 
	 * @param PanDuanXiTongChanChu
	 */
	public void add(PanDuanXiTongChanChu pdxtcc) {
		String sql = "insert into t_panduanxitongchanchu(" + "system," + "decide," + "cond," + "bag"
				+ ")values(?,?,?,?)";
		execute(sql, pdxtcc.getSystem(), pdxtcc.getDecide(), pdxtcc.getCond(),
				DAOUtil.intListConvString(pdxtcc.getBag()));
	}

	/**
	 * 更新
	 * 
	 * @param PanDuanXiTongChanChu
	 */
	public void update(PanDuanXiTongChanChu pdxtcc) {
		String sql = "update t_panduanxitongchanchu set " + " system=?," + " decide=?," + " cond=?," + " bag=? "
				+ " where id=?";
		executeUpdate(sql, pdxtcc.getSystem(), pdxtcc.getDecide(), pdxtcc.getCond(),
				DAOUtil.intListConvString(pdxtcc.getBag()), pdxtcc.getId());
	}

	/**
	 * 查询
	 */
	public PanDuanXiTongChanChu query(int id) {
		return queryForObject("select * from t_panduanxitongchanchu where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<PanDuanXiTongChanChu> queryList() {
		return queryForList("select * from t_panduanxitongchanchu", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_panduanxitongchanchu where id=?", HANDLER, id);
	}
}