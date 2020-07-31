package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.friend.RoleFriend;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 好友表结构DAO
 * 
 * @author 白雪林
 *
 */
public class RoleFriendDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleFriend> HANDLER = new ResultSetRowHandler<RoleFriend>() {
		@Override
		public RoleFriend handleRow(ResultSetRow row) throws Exception {
			RoleFriend rf = new RoleFriend();
			rf.setId(row.getInt("id"));
			rf.setRoleId(row.getInt("role_id"));
			rf.setFriendId(row.getInt("friend_id"));
			rf.setStatus(row.getInt("status"));
			rf.setContent(row.getString("content"));
			rf.setSendNailiTime(row.getTimestamp("send_naili_time"));
			rf.setBeSendNailiTime(row.getTimestamp("be_send_naili_time"));
			rf.setIsGetNaili(row.getInt("is_get_naili"));
			rf.setSeeChatTime(row.getTimestamp("see_chat_time"));
			rf.setCreateTime(row.getTimestamp("create_time"));
			rf.setUpdateTime(row.getTimestamp("update_time"));
			return rf;
		}
	};
	private static ResultSetRowHandler<Integer> FRIEND_ID_HANDLER = new ResultSetRowHandler<Integer>() {
		@Override
		public Integer handleRow(ResultSetRow row) throws Exception {
			return row.getInt("friend_id");
		}
	};

	private static ResultSetRowHandler<Integer> FRIEND_ROLEID_HANDLER = new ResultSetRowHandler<Integer>() {
		@Override
		public Integer handleRow(ResultSetRow row) throws Exception {
			return row.getInt("role_id");
		}
	};

	// 查询好友列表
	public List<RoleFriend> queryFriendList(int roleId) {
		return queryForList("select * from t_role_friend where role_id=? and status=?", HANDLER, roleId,
				RoleFriend.FRIEND_STATUS_AGREE);
	}

	// 查询待领取列表
	public List<RoleFriend> queryNailiList(int roleId) {
		return queryForList("select * from t_role_friend where role_id=? and is_get_naili=?", HANDLER, roleId,
				RoleFriend.FRIEND_NAILI_NOT_GET);
	}

	// 查询一条好友数据
	public RoleFriend queryOneFriend(int roleId, int friendId) {
		return queryForObject("select * from t_role_friend where role_id=? and friend_id=?", HANDLER, roleId, friendId);
	}

	// 查询领取全部耐力好友列表
	public List<RoleFriend> queryGetAllNailiFriend(int roleId) {
		String sql = "SELECT * FROM t_role_friend WHERE role_id=? and is_get_naili=? and status in (?,?) ORDER BY be_send_naili_time ";
		return queryForList(sql, HANDLER, roleId, RoleFriend.FRIEND_NAILI_NOT_GET, RoleFriend.FRIEND_STATUS_AGREE,
				RoleFriend.FRIEND_STATUS_BREAK);
	}

	// 查询好友id
	public List<Integer> queryFriendId(int roleId, int status) {
		return queryForList("SELECT friend_id FROM t_role_friend WHERE role_id = ? and status=? ", FRIEND_ID_HANDLER,
				roleId, status);
	}

	// 查询已发送过申请的id列表
	public List<Integer> queryApplyId(int roleId, int status) {
		return queryForList("SELECT role_id FROM t_role_friend WHERE friend_id = ? and status=? ",
				FRIEND_ROLEID_HANDLER, roleId, status);
	}

	// 查询申请列表
	public List<RoleFriend> queryApplyList(int roleId) {
		return queryForList("select * from t_role_friend where role_id=? and status=?", HANDLER, roleId,
				RoleFriend.FRIEND_STATUS_APPLY);
	}

	// 添加
	public void add(int roleId, int friendId, String content, int status) {
		String sql = "insert into t_role_friend(" + "role_id," + "friend_id," + "content," + "status,"
				+ "see_chat_time," + "create_time," + "update_time" + ")values(?,?,?,?,now(),now(),now())";
		execute(sql, roleId, friendId, content, status);
	}

	// 更新为已领取状态
	public void updateIsGetNaili(int id, int status) {
		String sql = "update t_role_friend set update_time = now(), is_get_naili=? where id=?";
		execute(sql, status, id);
	}

	// 更新数据为已赠送耐力
	public void updateSendNaili(RoleFriend friendDoc) {
		String sql = "update t_role_friend set update_time = now(), be_send_naili_time = now(), is_get_naili=? where id=?";
		execute(sql, RoleFriend.FRIEND_NAILI_NOT_GET, friendDoc.getId());
	}

	// 统计好友数
	public int queryCountByRoelId(int roleId) {
		return queryForInteger("select count(0) from t_role_friend where role_id=? and status=? ", roleId,
				RoleFriend.FRIEND_STATUS_AGREE);
	}

	// 更新以赠送时间
	public void updataSendNailiTime(RoleFriend curData) {
		String sql = "update t_role_friend set update_time = now(), send_naili_time=now() where role_id=? and friend_id=?";
		execute(sql, curData.getRoleId(), curData.getFriendId());
	}

	// 设置断交状态
	public void updateFriendStatus(int roleId, int friendId, String content, int status) {
		if (content.length() > 0) {
			String sql = "update t_role_friend set update_time = now(), status=?, content=? where role_id=? and friend_id=?";
			execute(sql, status, content, roleId, friendId);
		} else {
			String sql = "update t_role_friend set update_time = now(), status=? where role_id=? and friend_id=?";
			execute(sql, status, roleId, friendId);
		}
	}

	// 聊天查看到的时间
	public void updateSeeChatTime(int roleId, int friendId) {
		String sql = "update t_role_friend set update_time = now(), see_chat_time=now() where role_id=? and friend_id=?";
		execute(sql, roleId, friendId);
	}

	/**
	 * 更新
	 * 
	 * @param RoleFriend
	 */
	public void update(RoleFriend rf) {
		String sql = "update t_role_friend set " + " update_time=now(), " + " role_id=?," + " friend_id=?,"
				+ " status=?," + " content=?," + " send_naili_time=?," + " be_send_naili_time=?," + " is_get_naili=? "
				+ " where id=?";
		executeUpdate(sql, rf.getRoleId(), rf.getFriendId(), rf.getStatus(), rf.getContent(), rf.getSendNailiTime(),
				rf.getBeSendNailiTime(), rf.getIsGetNaili(), rf.getId());
	}

}