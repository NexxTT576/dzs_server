package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.notice.Notice;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 公告DAO
 * 
 * @author 房曈
 *
 */
public class NoticeDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<Notice> HANDLER = new ResultSetRowHandler<Notice>() {
		@Override
		public Notice handleRow(ResultSetRow row) throws Exception {
			Notice n = new Notice();
			n.setId(row.getInt("id"));
			n.setServerId(row.getString("server_id"));
			n.setTitle(row.getString("title"));
			n.setTcolor(row.getString("tcolor"));
			n.setTfont(row.getString("tfont"));
			n.setTeffect(row.getString("teffect"));
			n.setTbold(row.getString("tbold"));
			n.setContent(row.getString("content"));
			n.setCcolor(row.getString("ccolor"));
			n.setCfont(row.getString("cfont"));
			n.setStartTime(row.getTimestamp("start_time"));
			n.setEndTime(row.getTimestamp("end_time"));
			n.setCreateTime(row.getTimestamp("create_time"));
			n.setShowStatus(row.getInt("show_status"));
			return n;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Notice
	 */
	public void add(Notice n) {
		String sql = "insert into t_notice(" + "server_id," + "title," + "tcolor," + "tfont," + "teffect," + "tbold,"
				+ "content," + "ccolor," + "cfont," + "start_time," + "end_time," + "create_time," + "show_status"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,now())";
		execute(sql, n.getServerId(), n.getTitle(), n.getTcolor(), n.getTfont(), n.getTeffect(), n.getTbold(),
				n.getContent(), n.getCcolor(), n.getCfont(), n.getStartTime(), n.getEndTime(), n.getShowStatus());
	}

	/**
	 * 更新
	 * 
	 * @param Notice
	 */
	public void update(Notice n) {
		String sql = "update t_notice set " + " server_id=?," + " title=?," + " tcolor=?," + " tfont=?," + " teffect=?,"
				+ " tbold=?," + " content=?," + " ccolor=?," + " cfont=?," + " start_time=?," + " end_time=?,"
				+ " show_status=? " + " where id=?";
		executeUpdate(sql, n.getServerId(), n.getTitle(), n.getTcolor(), n.getTfont(), n.getTeffect(), n.getTbold(),
				n.getContent(), n.getCcolor(), n.getCfont(), n.getStartTime(), n.getEndTime(), n.getShowStatus(),
				n.getId());
	}

	/**
	 * 查询列表
	 */
	public List<Notice> queryNowList(String serverId) {
		return queryForList(
				"select * from t_notice where start_time <= now() and end_time > now() and (server_id IS NULL or server_id=?) order by sort",
				HANDLER, serverId);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_notice where id=?", id);
	}
}