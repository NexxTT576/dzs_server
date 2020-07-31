package com.mdy.dzs.game.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.chat.Chat;
import com.mdy.dzs.game.filter.ChatFilter;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 聊天DAO
 * 
 * @author 房曈
 *
 */
public class ChatDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<Chat> HANDLER = new ResultSetRowHandler<Chat>() {
		@Override
		public Chat handleRow(ResultSetRow row) throws Exception {
			Chat c = new Chat();
			c.setId(row.getInt("id"));
			c.setType(row.getInt("type"));
			c.setSendRoleId(row.getInt("send_role_id"));
			c.setReceiveRoleId(row.getInt("receive_role_id"));
			c.setAccount(row.getString("account"));
			c.setSendRoleName(row.getString("send_role_name"));
			c.setReceiveRoleName(row.getString("receive_role_name"));
			c.setSendRoleVip(row.getInt("send_role_vip"));
			c.setSendRoleSex(row.getInt("send_role_sex"));
			c.setSendMsg(row.getString("send_msg"));
			c.setSendRoleFaction(row.getString("send_role_faction"));
			c.setSendPara1(row.getString("send_para1"));
			c.setSendPara2(row.getString("send_para2"));
			c.setSendPara3(row.getString("send_para3"));
			c.setCreateTime(row.getTimestamp("create_time"));
			return c;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Chat
	 */
	public void add(Chat c) {
		String sql = "insert into t_chat(" + "type," + "send_role_id," + "receive_role_id," + "account,"
				+ "send_role_name," + "receive_role_name," + "send_role_vip," + "send_role_sex," + "send_role_faction,"
				+ "send_msg," + "send_para1," + "send_para2," + "send_para3," + "create_time"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
		execute(sql, c.getType(), c.getSendRoleId(), c.getReceiveRoleId(), c.getAccount(), c.getSendRoleName(),
				c.getReceiveRoleName(), c.getSendRoleVip(), c.getSendRoleSex(), c.getSendRoleFaction(), c.getSendMsg(),
				c.getSendPara1(), c.getSendPara2(), c.getSendPara3());
	}

	/**
	 * 更新
	 * 
	 * @param Chat
	 */
	public void update(Chat c) {
		String sql = "update t_chat set " + " type=?," + " send_role_id=?," + " receive_role_id=?," + " account=?,"
				+ " send_role_name=?," + " receive_role_name=?," + " send_role_vip=?," + " send_role_sex=?,"
				+ " send_msg=?," + " send_para1=?," + " send_para2=?," + " send_para3=? " + " where id=?";
		executeUpdate(sql, c.getType(), c.getSendRoleId(), c.getReceiveRoleId(), c.getAccount(), c.getSendRoleName(),
				c.getReceiveRoleName(), c.getSendRoleVip(), c.getSendRoleSex(), c.getSendMsg(), c.getSendPara1(),
				c.getSendPara2(), c.getSendPara3(), c.getId());
	}

	/**
	 * 查询
	 */
	public Chat query(int id) {
		return queryForObject("select * from t_chat where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Chat> queryList() {
		return queryForList("select * from t_chat", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_chat where id=?", HANDLER, id);
	}

	public List<Chat> queryListByFilter(ChatFilter filter) {
		List<Object> val = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from t_chat ");
		sql.append(" where 1=1 ");

		if (filter.getReciveName() != null && filter.getSendName() != null) {
			sql.append(" and (receive_role_name = ?");
			val.add(filter.getReciveName());
			sql.append(" or send_role_name = ?) ");
			val.add(filter.getSendName());
		} else if (filter.getReciveName() != null) {
			sql.append(" and receive_role_name = ? ");
			val.add(filter.getReciveName());
		} else if (filter.getSendName() != null) {
			sql.append(" and send_role_name = ? ");
			val.add(filter.getReciveName());
		}

		if (filter.getChatType() != null) {
			sql.append(" and type = ? ");
			val.add(filter.getChatType());
		}

		if (filter.getLastSeeTime() != null) {
			sql.append(" and create_time > ? ");
			val.add(filter.getLastSeeTime());
		}
		// 排序方式
		if (ChatFilter.ORDER_BY_CRETAE_TIME_DESC == filter.getOrderType()) {
			sql.append(" order by create_time desc ");
		} else if (ChatFilter.ORDER_BY_CRETAE_TIME_ASC == filter.getOrderType()) {
			sql.append(" order by create_time asc ");
		}
		sql.append(" limit ");
		sql.append(filter.getStartOfPage());
		sql.append(" ,");
		sql.append(filter.getPageSize());
		return queryForList(sql.toString(), HANDLER, val.toArray());
	}

	// 查询好友聊天新计数
	public int checkNewFriendChat(Date time, int roleId, int friendId) {
		return queryForInteger("select count(0) from t_chat where " + " type=? and send_role_id=? "
				+ " and receive_role_id=? " + " and create_time> ?", Chat.TYPE_好友, friendId, roleId, time);
	}

	// 好友断交，清除好友间聊天记录
	public void clearChatData(int roleId, int friendId) {
		execute("delete From t_chat WHERE (type=?) " + "and ((send_role_id = ? and receive_role_id=?) "
				+ "or (send_role_id = ? and receive_role_id=?))", Chat.TYPE_好友, roleId, friendId, friendId, roleId);
	}

	// 清除好友聊天超过7天数据
	public void clearFriendChatSevenData(Date delDate) {
		execute("delete from t_chat where type=? and " + "create_time <= ?", Chat.TYPE_好友, delDate);
	}

	// 查询好友间聊天记录
	public List<Chat> queryFriendChatData(int roleId, int friendId, Date time) {
		String sql = "select * from t_chat where "
				+ "type=? and create_time>? and ((send_role_id=? and receive_role_id=?) "
				+ "or (receive_role_id=? and send_role_id=?))" + "order by create_time";
		return queryForList(sql.toString(), HANDLER, Chat.TYPE_好友, time, friendId, roleId, friendId, roleId);
	}

	// 删除世界聊天旧数据
	public void tidyChatTable(Date tidyDate) {
		execute("delete from t_chat where create_time < ?", tidyDate);
	}
}