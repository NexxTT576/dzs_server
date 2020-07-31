package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.gift.YueKaShouChongGift;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 首充，月卡赠送的物品DAO
 * 
 * @author 白雪林
 *
 */
public class YueKaShouChongGiftDAO extends ConnectionResourceDAO implements QueryDateInterface<YueKaShouChongGift> {

	private static ResultSetRowHandler<YueKaShouChongGift> HANDLER = new ResultSetRowHandler<YueKaShouChongGift>() {
		@Override
		public YueKaShouChongGift handleRow(ResultSetRow row) throws Exception {
			YueKaShouChongGift ykscg = new YueKaShouChongGift();
			ykscg.setIndex(row.getInt("index"));
			ykscg.setArrType(DAOUtil.stringConvIntList(row.getString("arr_type")));
			ykscg.setArrItem(DAOUtil.stringConvIntList(row.getString("arr_item")));
			ykscg.setArrNum(DAOUtil.stringConvIntList(row.getString("arr_num")));
			return ykscg;
		}
	};

	/**
	 * 添加
	 * 
	 * @param YueKaShouChongGift
	 */
	public void add(YueKaShouChongGift ykscg) {
		String sql = "insert into t_yueka(" + "index," + "arr_type," + "arr_item," + "arr_num" + ")values(?,?,?,?)";
		execute(sql, ykscg.getIndex(), ykscg.getArrType(), ykscg.getArrItem(), ykscg.getArrNum());
	}

	/**
	 * 更新
	 * 
	 * @param YueKaShouChongGift
	 */
	public void update(YueKaShouChongGift ykscg) {
		String sql = "update t_yueka set " + " index=?," + " arr_type=?," + " arr_item=?," + " arr_num=? "
				+ " where id=?";
		executeUpdate(sql, ykscg.getIndex(), ykscg.getArrType(), ykscg.getArrItem(), ykscg.getArrNum());
	}

	/**
	 * 查询
	 */
	public YueKaShouChongGift query(int id) {
		return queryForObject("select * from t_yueka where index=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<YueKaShouChongGift> queryList() {
		return queryForList("select * from t_yueka", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_yueka where index=?", HANDLER, id);
	}
}