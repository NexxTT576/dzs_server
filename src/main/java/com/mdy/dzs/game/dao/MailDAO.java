package com.mdy.dzs.game.dao;

import com.mdy.dzs.game.domain.mail.Mail;
import com.mdy.dzs.game.domain.mail.MailParaVO;
import com.mdy.dzs.game.filter.MailFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 邮件DAO
 * 
 * @author 白雪林
 *
 */
public class MailDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<Mail> HANDLER = new ResultSetRowHandler<Mail>() {

		@Override
		public Mail handleRow(ResultSetRow row) throws Exception {
			Mail m = new Mail();
			m.setId(row.getInt("id"));
			m.setRoleId(row.getInt("role_id"));
			m.setType(row.getInt("type"));
			m.setStrId(row.getInt("strId"));
			m.setTime(row.getTimestamp("time"));
			List<MailParaVO> arr = JSONUtil.fromJsonList(row.getString("paras"), MailParaVO.class);
			m.setParas(arr);
			return m;
		}
	};

	/**
	 * 添加
	 * 
	 * @param Mail
	 */
	public void add(Mail m) {
		String sql = "insert into t_mail(" + "role_id," + "type," + "strId," + "time," + "paras" + ")values(?,?,?,?,?)";

		String paras = JSONUtil.toJson(m.getParas());
		execute(sql, m.getRoleId(), m.getType(), m.getStrId(), m.getTime(), paras);
	}

	/**
	 * 查询列表
	 * 
	 * @param filter
	 * @param mailId
	 * @param roleId
	 * @param type
	 */
	public List<Mail> queryListByFilter(MailFilter filter) {
		List<Object> val = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from t_mail ");
		sql.append(" where 1=1 ");

		if (filter.getRoleId() != null) {
			sql.append(" and role_id = ? ");
			val.add(filter.getRoleId());
		}
		if (filter.getType() != null) {
			sql.append(" and type = ? ");
			val.add(filter.getType());
		}
		if (filter.getId() != null) {
			sql.append(" and id < ? ");
			val.add(filter.getId());
		}
		if (filter.getLimitDate() != null) {
			sql.append(" and time > ?");
			val.add(filter.getLimitDate());
		}
		if (filter.getOrderType() != null) {
			sql.append(" order by id desc ");
		}

		sql.append(" limit ");
		sql.append(filter.getNum());

		return queryForList(sql.toString(), HANDLER, val.toArray());
	}

	public int queryCount(MailFilter filter) {
		if (filter.getId() != null) {
			return 0;
		}

		List<Object> val = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT  count(*)  FROM  t_mail ");
		sql.append(" where 1=1 ");

		if (filter.getRoleId() != null) {
			sql.append(" and role_id = ? ");
			val.add(filter.getRoleId());
		}
		if (filter.getType() != null) {
			sql.append(" and type = ? ");
			val.add(filter.getType());
		}
		if (filter.getLimitDate() != null) {
			sql.append(" and time > ?");
			val.add(filter.getLimitDate());
		}

		return queryForInteger(sql.toString(), val.toArray());
	}

	public void tidyTable(Date tidyDate) {
		execute("delete from t_mail where time < ?", tidyDate);
	}

	public int checkNewMail(Date time, int mailType, int role_id) {
		String sql = "select count(0) from t_mail where type=? and role_id=? and time> ?";
		return queryForInteger(sql, mailType, role_id, time);
	}
}