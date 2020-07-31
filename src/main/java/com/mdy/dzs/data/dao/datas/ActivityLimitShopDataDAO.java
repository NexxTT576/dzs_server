package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.activity.limitshop.LimitShopData;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class ActivityLimitShopDataDAO extends ConnectionResourceDAO implements QueryDateInterface<LimitShopData> {
	private static ResultSetRowHandler<LimitShopData> HANDLER = new ResultSetRowHandler<LimitShopData>() {
		@Override
		public LimitShopData handleRow(ResultSetRow row) throws Exception {
			LimitShopData Lshop = new LimitShopData();
			Lshop.setId(row.getInt("id"));
			Lshop.setItemid(row.getInt("itemid"));
			Lshop.setNum(row.getInt("num"));
			Lshop.setSale(row.getInt("sale"));
			Lshop.setSum1(row.getInt("sum1"));
			Lshop.setType(row.getInt("type"));
			Lshop.setVip(row.getInt("vip"));
			Lshop.setCointype(row.getInt("cointype"));
			Lshop.setPrice(row.getInt("price"));
			Lshop.setArr_sum2(DAOUtil.stringConvIntList(row.getString("arr_sum2")));
			return Lshop;
		}
	};

	@Override
	public List<LimitShopData> queryList() {
		return queryForList("select * from t_xianshishangdian", HANDLER);
	}

}
