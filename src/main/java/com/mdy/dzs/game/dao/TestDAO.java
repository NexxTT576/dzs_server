package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.Test;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;


/**
 * 帐号DAO
 *
 */
public class TestDAO extends ConnectionResourceDAO{


	private static ResultSetRowHandler<Test>HANDLER=
		new ResultSetRowHandler<Test>() {
			@Override
			public Test handleRow(ResultSetRow row) throws Exception {
				Test a=new Test();
				a.setId(row.getInt("id"));
				a.setCreateTime(row.getTimestamp("create_time"));
				a.setUpdateTime(row.getTimestamp("update_time"));
				return a;
			}
		};
		
		
	/**
	 * 添加
	 * @param Test
	 */
	public void add(Test a){
		String sql="insert into t_test(" +
			"create_time," +
			"update_time" +
			")values(now(),now())";
		execute(sql);
	}
	/**
	 * 更新
	 * @param Test
	 */
	public void update(Test a){
		String sql="update t_test set " +
			" update_time=now()" +
			" where id=?";
		executeUpdate(sql,
			a.getId());
	}
	/**
	 * 查询
	 */
	public Test query(int id){
		return queryForObject(
			"select * from t_test where id=?",
			HANDLER, id);
	}
	
	
	/**
	 * 查询列表
	 */
	public List<Test> queryList(){
		return queryForList("select * from t_test",HANDLER);
	}
	/**
	 * 删除
	 */
	public void delete(int id){
		execute("delete  from t_test where id=?",HANDLER,id);
	}
	
}