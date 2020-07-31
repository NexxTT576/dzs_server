package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.union.UnionFB;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class UnionFBDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<UnionFB> HANDLER = new ResultSetRowHandler<UnionFB>() {
		@Override
		public UnionFB handleRow(ResultSetRow row) throws Exception {
			UnionFB uFB = new UnionFB();
			uFB.setId(row.getInt("id"));
			uFB.setCreateTime(row.getTimestamp("create_time"));
			uFB.setFbId(row.getInt("fb_id"));
			uFB.setLeftHp(row.getInt("left_hp"));
			uFB.setState(row.getInt("state"));
			uFB.setType(row.getInt("type"));
			uFB.setUnionId(row.getInt("union_id"));
			uFB.setUpdateTime(row.getTimestamp("update_time"));
			return uFB;
		}
	};
	/**
	 * 根据帮派id查询帮派副本
	 * @param unionId
	 * @return
	 */
	public List<UnionFB> queryUnionFBById(int unionId){
		 return queryForList("select * from t_union_fb where union_id=? order by create_time asc", HANDLER,unionId);
	}
	/**
	 * 创建帮派副本
	 * @param unionFB
	 */
	public void createUnionFB(UnionFB unionFB){
		String sql = "insert into t_union_fb (" + "create_time," + "fb_id,"
				+ "left_hp," + "state,"+ "type," + "union_id," +"update_time"+")values(?,?,?,?,?,?,now())";
		execute(sql, unionFB.getCreateTime(), unionFB.getFbId(),
				unionFB.getLeftHp(), unionFB.getState(),unionFB.getType(),
				unionFB.getUnionId());
	}
	/**
	 * 查询帮派单个副本的信息
	 * @param unionId
	 * @param type
	 * @param FBId
	 * @return
	 */
    public UnionFB queryUnionFBByFBId(int unionId,int type,int FBId){
    	return queryForObject("select * from t_union_fb where union_id =? and type =? and fb_id =? ", HANDLER,unionId,type,FBId);
    }
    /**
     * 修改单个帮派副本的状态
     * @param unionId
     * @param type
     * @param FBId
     */
    public void updateUnionFBState(int unionId,int type,int FBId){
    	String sql = "update t_union_fb set " + " state = 1 "
				+ " where union_id=? and type =? and fb_id =?";
		executeUpdate(sql,unionId, type, FBId);
    }
}
