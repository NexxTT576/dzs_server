package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.card.ShangXianSheDing;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 上限设定DAO
 * 
 * @author 房曈
 *
 */
public class ShangXianSheDingDAO extends ConnectionResourceDAO implements QueryDateInterface<ShangXianSheDing> {

	private static ResultSetRowHandler<ShangXianSheDing> HANDLER = new ResultSetRowHandler<ShangXianSheDing>() {
		@Override
		public ShangXianSheDing handleRow(ResultSetRow row) throws Exception {
			ShangXianSheDing sxsd = new ShangXianSheDing();
			sxsd.setZhujuedengji(row.getInt("zhujuedengji"));
			sxsd.setZhujuejinjie(row.getInt("zhujuejinjie"));
			sxsd.setKapaidengji(row.getInt("kapaidengji"));
			sxsd.setKapaijinjiecishu(row.getInt("kapaijinjiecishu"));
			sxsd.setZhuangbeidengji(row.getInt("zhuangbeidengji"));
			sxsd.setWuxuedengji(row.getInt("wuxuedengji"));
			sxsd.setZhenqidengji(row.getInt("zhenqidengji"));
			sxsd.setShentongdengji(row.getInt("shentongdengji"));
			sxsd.setJianghulu(row.getInt("jianghulu"));
			sxsd.setJingmaidengji(row.getInt("jingmaidengji"));
			sxsd.setJingLian(row.getInt("jinglian"));
			return sxsd;
		}
	};

	/**
	 * 查询
	 */
	public ShangXianSheDing query(int id) {
		return queryForObject("select * from t_shangxiansheding where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ShangXianSheDing> queryList() {
		return queryForList("select * from t_shangxiansheding", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_shangxiansheding where id=?", HANDLER, id);
	}
}