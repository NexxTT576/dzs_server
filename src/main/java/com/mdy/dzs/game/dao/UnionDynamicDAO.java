package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.union.UnionDynamic;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class UnionDynamicDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<UnionDynamic> HANDLER = new ResultSetRowHandler<UnionDynamic>() {
		@Override
		public UnionDynamic handleRow(ResultSetRow row) throws Exception {
			UnionDynamic udv = new UnionDynamic();
			udv.setId(row.getInt("id"));
			udv.setGoldNum(row.getInt("gold_num"));
			udv.setSilverNum(row.getInt("silver_num"));
			udv.setCreateTime(row.getTimestamp("create_time"));
			udv.setType(row.getInt("type"));
			udv.setDes(row.getString("des"));
			udv.setRoleId(row.getInt("role_id"));
			udv.setUnionId(row.getInt("union_id"));
			udv.setParas(row.getString("paras"));
			return udv;
		}
	};
	/**
	 * 添加帮派动态
	 */
	public void createDynmic(UnionDynamic unionDynamic){
		String sql = "insert into t_union_dynamic " + "(gold_num," + "silver_num,"
				+ "create_time," + "type," +"des,"+"role_id,"+"union_id"+")values(?,?,?,?,?,?,?)";
		execute(sql, unionDynamic.getGoldNum(), unionDynamic.getSilverNum(),
				unionDynamic.getCreateTime(), unionDynamic.getType(),unionDynamic.getDes(),unionDynamic.getRoleId(),unionDynamic.getUnionId());
	}
	//青龙堂结束动态
	public void createDynmicDragon(UnionDynamic unionDynamic) {
		String sql = "insert into t_union_dynamic " + "(gold_num," + "silver_num,"
				+ "create_time," + "type," +"des,"+"role_id,"+"union_id,"+"paras"+")values(?,?,?,?,?,?,?,?)";
		execute(sql, unionDynamic.getGoldNum(), unionDynamic.getSilverNum(),
				unionDynamic.getCreateTime(), unionDynamic.getType(),unionDynamic.getDes(),unionDynamic.getRoleId(),unionDynamic.getUnionId()
				,unionDynamic.getParas());
	}
	
	/**
	 * 查询帮派动态列表
	 */
	public List<UnionDynamic> queryDynamicList(int unionId){
		return queryForList("select * from t_union_dynamic where union_id =? ORDER BY create_time desc ",HANDLER,unionId);
	}
	/**
	 * 删除帮派动态
	 */
	public void deleteDynamic(UnionDynamic unionDynamic){
		execute("delete  from t_union_dynamic where id = ?",unionDynamic.getId());
	}
}
