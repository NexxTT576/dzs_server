package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.activity.limitcard.LimitCardBean;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class RoleActivityLimitCardDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<LimitCardBean> HANDLER = new ResultSetRowHandler<LimitCardBean>() {
		@Override
		public LimitCardBean handleRow(ResultSetRow row) throws Exception {
			LimitCardBean ah = new LimitCardBean();
			ah.setId(row.getInt("id"));
			ah.setRole_id(row.getInt("role_id"));
			ah.setRolename(row.getString("rolename"));
			ah.setGetcount(row.getInt("getcount"));
			ah.setLucknum(row.getInt("lucknum"));
			ah.setFreegettime(row.getTimestamp("freegettime"));
			ah.setLastgettime(row.getTimestamp("lastgettime"));
			ah.setScore(row.getInt("score"));
			ah.setProblucknum(row.getInt("problucknum"));
			return ah;
		}
	};

	public void clearLimitCard() {
		execute("delete from t_role_activity_limitcard");
	}

	public List<LimitCardBean> query(int limitcount) {
		return queryForList(
				"select * from t_role_activity_limitcard where score != 0 order by  score desc,lastgettime limit ?",
				HANDLER, limitcount);
	}

	public List<LimitCardBean> queryLimitCardSocre(int score) {
		return queryForList("select * from t_role_activity_limitcard where score >= ?", HANDLER, score);
	}

	public LimitCardBean queryByRoleId(int roleid) {
		LimitCardBean lcb = queryForObject("select * from t_role_activity_limitcard where role_id = ?", HANDLER,
				roleid);
		return lcb;
	}

	public void addLimitCard(LimitCardBean lcb) {
		String sql = "insert into t_role_activity_limitcard(role_id,rolename,getcount,lucknum,score,problucknum,freegettime,lastgettime)values(?,?,?,?,?,?,?,?)";
		execute(sql, lcb.getRole_id(), lcb.getRolename(), lcb.getGetcount(), lcb.getLucknum(), lcb.getScore(),
				lcb.getProblucknum(), lcb.getFreegettime(), lcb.getLastgettime());
	}

	public void updateLimitDraw(LimitCardBean lcb, boolean islunk, boolean upfree) {
		if (islunk) {
			if (upfree) {
				String sql = "update t_role_activity_limitcard set lucknum = 0,score = score + ? , getcount = getcount + ?,lastgettime = ?,problucknum = ? where role_id = ?";
				execute(sql, lcb.getScore(), lcb.getGetcount(), lcb.getLastgettime(), lcb.getProblucknum(),
						lcb.getRole_id());
			} else {
				String sql = "update t_role_activity_limitcard set lucknum = 0,score = score + ? , getcount = getcount + ?,lastgettime = ?,problucknum = ?,freegettime = ? where role_id = ?";
				execute(sql, lcb.getScore(), lcb.getGetcount(), lcb.getLastgettime(), lcb.getProblucknum(),
						lcb.getFreegettime(), lcb.getRole_id());
			}
		} else {
			String sql = "";
			if (upfree) {
				sql = "update t_role_activity_limitcard set lucknum = lucknum + ?,score = score + ?,getcount = getcount + ?,lastgettime = ?,problucknum = problucknum + ?,freegettime = ? where role_id = ?";
				execute(sql, lcb.getLucknum(), lcb.getScore(), lcb.getGetcount(), lcb.getLastgettime(),
						lcb.getProblucknum(), lcb.getFreegettime(), lcb.getRole_id());
			} else {
				sql = "update t_role_activity_limitcard set lucknum = lucknum + ?,score = score + ?,getcount = getcount + ?,lastgettime = ?,problucknum = problucknum + ? where role_id = ?";
				execute(sql, lcb.getLucknum(), lcb.getScore(), lcb.getGetcount(), lcb.getLastgettime(),
						lcb.getProblucknum(), lcb.getRole_id());
			}

		}
	}
}
