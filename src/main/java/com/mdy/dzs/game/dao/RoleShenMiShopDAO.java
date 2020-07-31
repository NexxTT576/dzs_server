package com.mdy.dzs.game.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.shenmishop.ShenMiItemVO;
import com.mdy.dzs.game.domain.shenmishop.ShenMiShop;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

import flexjson.JSONDeserializer;

/**
 * 角色神秘商店数据DAO
 * 
 * @author 白雪林
 *
 */
public class RoleShenMiShopDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<ShenMiShop> HANDLER = new ResultSetRowHandler<ShenMiShop>() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public ShenMiShop handleRow(ResultSetRow row) throws Exception {
			ShenMiShop sms = new ShenMiShop();
			sms.setRoleId(row.getInt("role_id"));
			JSONDeserializer deser = new JSONDeserializer().use(null, HashMap.class).use("values", ShenMiItemVO.class);
			Map<String, ShenMiItemVO> map = (Map<String, ShenMiItemVO>) deser
					.deserialize(row.getString("meystery_map"));
			sms.setMeysteryMap(map);
			sms.setMeysteryRefreshTime(row.getTimestamp("meystery_refresh_time"));
			sms.setMeysteryFreeCnt(row.getInt("meystery_free_cnt"));
			sms.setMeysteryFreeTime(row.getTimestamp("meystery_free_time"));
			sms.setMeysteryDayRefreshCnt(row.getInt("meystery_day_refresh_cnt"));
			sms.setMeysteryDayGoldRefreshCnt(row.getInt("meystery_day_gold_refresh_cnt"));
			sms.setCreatTime(row.getTimestamp("creat_time"));
			sms.setUpdateTime(row.getTimestamp("update_time"));
			return sms;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ShenMiShop
	 */
	public void add(int roleid) {
		String sql = "insert into t_role_shenmi(" + "role_id," + "meystery_map," + "meystery_refresh_time,"
				+ "meystery_free_cnt," + "meystery_day_refresh_cnt," + "meystery_day_gold_refresh_cnt," + "creat_time,"
				+ "update_time" + ")values(?,'{}',0,0,0,0,now(),now())";
		execute(sql, roleid);
	}

	/**
	 * 更新
	 * 
	 * @param ShenMiShop
	 */
	public void update(ShenMiShop sms) {
		String sql = "update t_role_shenmi set " + " update_time=now(), " + " meystery_map=?,"
				+ " meystery_refresh_time=?," + " meystery_free_cnt=?," + " meystery_free_time=?,"
				+ " meystery_day_refresh_cnt=?," + " meystery_day_gold_refresh_cnt=?" + " where role_id=?";

		executeUpdate(sql, JSONUtil.toJson(sms.getMeysteryMap()), sms.getMeysteryRefreshTime(),
				sms.getMeysteryFreeCnt(), sms.getMeysteryFreeTime(), sms.getMeysteryDayRefreshCnt(),
				sms.getMeysteryDayGoldRefreshCnt(), sms.getRoleId());
	}

	/**
	 * 查询
	 */
	public ShenMiShop query(int id) {
		return queryForObject("select * from t_role_shenmi where role_id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<ShenMiShop> queryList() {
		return queryForList("select * from t_role_shenmi", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role_shenmi where id=?", HANDLER, id);
	}
}