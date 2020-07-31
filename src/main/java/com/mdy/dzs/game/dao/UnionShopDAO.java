package com.mdy.dzs.game.dao;


import com.mdy.dzs.game.domain.union.UnionShop;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class UnionShopDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<UnionShop> HANDLER = new ResultSetRowHandler<UnionShop>() {
		@Override
		public UnionShop handleRow(ResultSetRow row) throws Exception {
			UnionShop us = new UnionShop();
			us.setId(row.getInt("id"));
			us.setGoodsInfo(row.getString("goods_info"));
			us.setRefreshTime(row.getTimestamp("refresh_time"));
			us.setUnionId(row.getInt("union_id"));
			return us;
		}
	};
	/**
	 * 添加帮派商店信息
	 * @param union
	 * @return
	 */
	public void createShopInfo(UnionShop unionShop) {
		String sql = "insert into t_union_shop(" + "goods_info,"
				+ "refresh_time,"
				+ "union_id " +")values(?,?,?)";
		 execute(sql,unionShop.getGoodsInfo(),unionShop.getRefreshTime(),unionShop.getUnionId());
	}
	/**
	 * 更新帮派商店信息
	 * @param union
	 * @return
	 */
	public void updateShopInfo(UnionShop unionShop) {
		String sql="update t_union_shop set goods_info =?,refresh_time=?  where union_id =?";
		 executeUpdate(sql,unionShop.getGoodsInfo(),unionShop.getRefreshTime(),unionShop.getUnionId());
	}
	/**
	 * 查询帮派商店信息
	 */
	public UnionShop queryShopInfoByUnionId(int unionId){
		return queryForObject("select * from t_union_shop  where union_id =?",HANDLER,unionId);
	}

}
