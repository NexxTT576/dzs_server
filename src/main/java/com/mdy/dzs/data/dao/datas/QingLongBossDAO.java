package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.union.QingLongBoss;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 帮派青龙堂排名奖励表DAO
 * 
 * @author 白雪林
 *
 */
public class QingLongBossDAO extends ConnectionResourceDAO implements QueryDateInterface<QingLongBoss> {

	private static ResultSetRowHandler<QingLongBoss> HANDLER = new ResultSetRowHandler<QingLongBoss>() {
		@Override
		public QingLongBoss handleRow(ResultSetRow row) throws Exception {
			QingLongBoss qlb = new QingLongBoss();
			qlb.setId(row.getInt("id"));
			qlb.setMin(row.getInt("min"));
			qlb.setMax(row.getInt("max"));
			qlb.setRatio1(row.getInt("ratio1"));
			qlb.setFix1(row.getInt("fix1"));
			qlb.setRatio2(row.getInt("ratio2"));
			qlb.setFix2(row.getInt("fix2"));
			qlb.setRatio3(row.getInt("ratio3"));
			qlb.setFix3(row.getInt("fix3"));
			qlb.setProb(row.getInt("prob"));
			return qlb;
		}
	};

	/**
	 * 添加
	 * 
	 * @param QingLongBoss
	 */
	public void add(QingLongBoss qlb) {
		String sql = "insert into t_boss_qinglong(" + "min," + "max," + "ratio1," + "fix1," + "ratio2," + "fix2,"
				+ "ratio3," + "fix3," + "prob" + ")values(?,?,?,?,?,?,?,?,?)";
		execute(sql, qlb.getMin(), qlb.getMax(), qlb.getRatio1(), qlb.getFix1(), qlb.getRatio2(), qlb.getFix2(),
				qlb.getRatio3(), qlb.getFix3(), qlb.getProb());
	}

	/**
	 * 更新
	 * 
	 * @param QingLongBoss
	 */
	public void update(QingLongBoss qlb) {
		String sql = "update t_boss_qinglong set " + " min=?," + " max=?," + " ratio1=?," + " fix1=?," + " ratio2=?,"
				+ " fix2=?," + " ratio3=?," + " fix3=?," + " prob=? " + " where id=?";
		executeUpdate(sql, qlb.getMin(), qlb.getMax(), qlb.getRatio1(), qlb.getFix1(), qlb.getRatio2(), qlb.getFix2(),
				qlb.getRatio3(), qlb.getFix3(), qlb.getProb(), qlb.getId());
	}

	/**
	 * 查询
	 */
	public QingLongBoss query(int id) {
		return queryForObject("select * from t_boss_qinglong where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<QingLongBoss> queryList() {
		return queryForList("select * from t_boss_qinglong", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_boss_qinglong where id=?", HANDLER, id);
	}
}