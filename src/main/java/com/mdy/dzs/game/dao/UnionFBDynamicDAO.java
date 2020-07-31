package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.union.UnionFBDynamic;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class UnionFBDynamicDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<UnionFBDynamic> HANDLER = new ResultSetRowHandler<UnionFBDynamic>() {
		@Override
		public UnionFBDynamic handleRow(ResultSetRow row) throws Exception {
			UnionFBDynamic UFBD = new UnionFBDynamic();
			UFBD.setId(row.getInt("id"));
			UFBD.setType(row.getInt("type"));
			UFBD.setDes(row.getString("des"));
			UFBD.setUnionId(row.getInt("union_id"));
			UFBD.setFbType(row.getInt("fb_type"));
			UFBD.setFbId(row.getInt("fb_id"));
			UFBD.setCreatTime(row.getTimestamp("create_time"));
			return UFBD;
		}
	};
	/**
	 * 查询副本动态
	 * @param unionId
	 * @param type
	 * @param FBId
	 * @return
	 */
	public List<UnionFBDynamic> queryUnionFBDynamicByFBId(int unionId,int type,int FBId){
		return queryForList("select * from t_union_fb_dynamic where union_id =? and fb_type =? and fb_id =? ", HANDLER, unionId,type,FBId);
	}
}
