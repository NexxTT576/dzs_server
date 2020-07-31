package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * vipDAO
 * 
 * @author 房曈
 *
 */
public class VipDAO extends ConnectionResourceDAO implements QueryDateInterface<Vip> {

	private static ResultSetRowHandler<Vip> HANDLER = new ResultSetRowHandler<Vip>() {
		@Override
		public Vip handleRow(ResultSetRow row) throws Exception {
			Vip v = new Vip();
			v.setId(row.getInt("id"));
			v.setSystem(row.getInt("system"));
			v.setShopid(row.getInt("shopid"));
			v.setVip0(row.getInt("vip0"));
			v.setVip1(row.getInt("vip1"));
			v.setVip2(row.getInt("vip2"));
			v.setVip3(row.getInt("vip3"));
			v.setVip4(row.getInt("vip4"));
			v.setVip5(row.getInt("vip5"));
			v.setVip6(row.getInt("vip6"));
			v.setVip7(row.getInt("vip7"));
			v.setVip8(row.getInt("vip8"));
			v.setVip9(row.getInt("vip9"));
			v.setVip10(row.getInt("vip10"));
			v.setVip11(row.getInt("vip11"));
			v.setVip12(row.getInt("vip12"));
			v.setVip13(row.getInt("vip13"));
			v.setVip14(row.getInt("vip14"));
			v.setVip15(row.getInt("vip15"));
			return v;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Vip
	 */
	public void add(Vip v) {
		String sql = "insert into t_vip(" + "system," + "shopid," + "vip0," + "vip1," + "vip2," + "vip3," + "vip4,"
				+ "vip5," + "vip6," + "vip7," + "vip8," + "vip9," + "vip10," + "vip11," + "vip12," + "vip13," + "vip14,"
				+ "vip15" + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		execute(sql, v.getSystem(), v.getShopid(), v.getVip0(), v.getVip1(), v.getVip2(), v.getVip3(), v.getVip4(),
				v.getVip5(), v.getVip6(), v.getVip7(), v.getVip8(), v.getVip9(), v.getVip10(), v.getVip11(),
				v.getVip12(), v.getVip13(), v.getVip14(), v.getVip15());
	}

	/**
	 * 更新
	 * 
	 * @param Vip
	 */
	public void update(Vip v) {
		String sql = "update t_vip set " + " system=?," + " shopid=?," + " vip0=?," + " vip1=?," + " vip2=?,"
				+ " vip3=?," + " vip4=?," + " vip5=?," + " vip6=?," + " vip7=?," + " vip8=?," + " vip9=?," + " vip10=?,"
				+ " vip11=?," + " vip12=?," + " vip13=?," + " vip14=?," + " vip15=? " + " where id=?";
		executeUpdate(sql, v.getSystem(), v.getShopid(), v.getVip0(), v.getVip1(), v.getVip2(), v.getVip3(),
				v.getVip4(), v.getVip5(), v.getVip6(), v.getVip7(), v.getVip8(), v.getVip9(), v.getVip10(),
				v.getVip11(), v.getVip12(), v.getVip13(), v.getVip14(), v.getVip15(), v.getId());
	}

	/**
	 * 查询
	 */
	public Vip query(int id) {
		return queryForObject("select * from t_vip where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Vip> queryList() {
		return queryForList("select * from t_vip", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_vip where id=?", HANDLER, id);
	}
}