package com.mdy.dzs.game.dao;

import com.mdy.dzs.game.domain.union.UnionRoleShop;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class UnionRoleShopDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<UnionRoleShop> HANDLER = new ResultSetRowHandler<UnionRoleShop>() {
		@Override
		public UnionRoleShop handleRow(ResultSetRow row) throws Exception {
			UnionRoleShop us = new UnionRoleShop();
			us.setId(row.getInt("id"));
			us.setGoodsInfo(row.getString("goods_info"));
			us.setRefreshTime(row.getTimestamp("refresh_time"));
			us.setRoleId(row.getInt("role_id"));
			return us;
		}
	};
	/**
	 * 添加帮派个人商店信息
	 * @param union
	 * @return
	 */
	public void createUnionRoleShopInfo(UnionRoleShop unionRoleShop) {
		String sql = "insert into t_role_union_shop(" + "goods_info,"
				+ "refresh_time,"
				+ "role_id " +")values(?,?,?)";
		 execute(sql,unionRoleShop.getGoodsInfo(),unionRoleShop.getRefreshTime(),unionRoleShop.getRoleId());
	}
	/**
	 * 更新个人商店信息
	 * @param union
	 * @return
	 */
	public void updateUnionRoleShopInfo(UnionRoleShop unionRoleShop) {
		String sql="update t_role_union_shop set goods_info =?,refresh_time=?  where role_id =?";
		 executeUpdate(sql,unionRoleShop.getGoodsInfo(),unionRoleShop.getRefreshTime(),unionRoleShop.getRoleId());
	}
	/**
	 * 查询帮派个人商店信息
	 */
	public UnionRoleShop queryShopInfoByRoleId(int roleId){
		return queryForObject("select * from t_role_union_shop  where role_id =?",HANDLER,roleId);
	}
}
