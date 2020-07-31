package com.mdy.dzs.data.dao;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.dao.filter.ChargeOrderFilter;
import com.mdy.dzs.data.domain.charge.ChargeOrder;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 充值订单DAO
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月19日 下午5:05:44
 */
public class ChargeOrderDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<ChargeOrder> HANDLER = new ResultSetRowHandler<ChargeOrder>() {
		@Override
		public ChargeOrder handleRow(ResultSetRow row) throws Exception {
			ChargeOrder co = new ChargeOrder();
			co.setPayId(row.getInt("pay_id"));
			co.setToken(row.getString("token"));
			co.setAccount(row.getString("account"));
			co.setUserIp(row.getString("user_ip"));
			co.setPf(row.getString("pf"));
			co.setPayItem(row.getString("pay_item"));
			co.setBillno(row.getString("billno"));
			co.setProvideType(row.getString("provide_type"));
			co.setProvideErrno(row.getString("provide_errno"));
			co.setProvideErrmsg(row.getString("provide_errmsg"));
			co.setServiceId(row.getInt("service_id"));
			co.setServerId(row.getString("server_id"));
			co.setRoleId(row.getInt("role_id"));
			co.setRoleName(row.getString("role_name"));
			co.setRoleLevel(row.getInt("role_level"));
			co.setRoleVipLevel(row.getInt("role_vip_level"));
			co.setRoleDeviceUUID(row.getString("role_device_uuid"));
			co.setRoleAddGold(row.getInt("role_add_gold"));
			co.setRoleFirstCharge(row.getInt("role_first_charge"));
			co.setPayPrice(row.getFloat("pay_price"));
			co.setOldPrice(row.getFloat("old_price"));
			co.setPayStatus(row.getInt("pay_status"));
			co.setSummary(row.getString("summary"));
			co.setNotifyTime(row.getTimestamp("notify_time"));
			co.setFinishTime(row.getTimestamp("finish_time"));
			co.setPayTime(row.getTimestamp("pay_time"));
			co.setCreateTime(row.getTimestamp("create_time"));
			co.setUpdateTime(row.getTimestamp("update_time"));
			return co;
		}
	};

	/**
	 * 添加
	 * 
	 * @param ChargeOrder
	 */
	public void addChargeOrder(ChargeOrder chargeOrder) {
		String sql = "insert into t_charge_order(" + "token," + "account," + "user_ip," + "pf," + "pay_item,"
				+ "billno," + "provide_type," + "provide_errno," + "provide_errmsg," + "service_id," + "server_id,"
				+ "role_id," + "role_name," + "nick_name," + "pay_price," + "old_price," + "pay_status," + "summary,"
				+ "notify_time," + "finish_time," + "pay_time," + "create_time," + "update_time"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now())";
		execute(sql, chargeOrder.getToken(), chargeOrder.getAccount(), chargeOrder.getUserIp(), chargeOrder.getPf(),
				chargeOrder.getPayItem(), chargeOrder.getBillno(), chargeOrder.getProvideType(),
				chargeOrder.getProvideErrno(), chargeOrder.getProvideErrmsg(), chargeOrder.getServiceId(),
				chargeOrder.getServerId(), chargeOrder.getRoleId(), chargeOrder.getRoleName(),
				chargeOrder.getNickName(), chargeOrder.getPayPrice(), chargeOrder.getOldPrice(),
				chargeOrder.getPayStatus(), chargeOrder.getSummary(), chargeOrder.getNotifyTime(),
				chargeOrder.getFinishTime(), chargeOrder.getPayTime());
	}

	/**
	 * 更新
	 * 
	 * @param ChargeOrder
	 */
	public void updateChargeOrder(ChargeOrder chargeOrder) {
		String sql = "update t_charge_order set " + " update_time=now(), " + " token=?," + " account=?," + " user_ip=?,"
				+ " pf=?," + " pay_item=?," + " billno=?," + " provide_type=?," + " provide_errno=?,"
				+ " provide_errmsg=?," + " service_id=?," + " server_id=?," + " role_id=?," + " role_name=?,"
				+ " pay_price=?," + " old_price=?," + " pay_status=?," + " summary=?," + " notify_time=?,"
				+ " finish_time=?," + " pay_time=? " + " where pay_id=?";
		executeUpdate(sql, chargeOrder.getToken(), chargeOrder.getAccount(), chargeOrder.getUserIp(),
				chargeOrder.getPf(), chargeOrder.getPayItem(), chargeOrder.getBillno(), chargeOrder.getProvideType(),
				chargeOrder.getProvideErrno(), chargeOrder.getProvideErrmsg(), chargeOrder.getServiceId(),
				chargeOrder.getServerId(), chargeOrder.getRoleId(), chargeOrder.getRoleName(),
				chargeOrder.getPayPrice(), chargeOrder.getOldPrice(), chargeOrder.getPayStatus(),
				chargeOrder.getSummary(), chargeOrder.getNotifyTime(), chargeOrder.getFinishTime(),
				chargeOrder.getPayTime(), chargeOrder.getPayId());
	}

	/**
	 * 查询订单
	 * 
	 * @param payId
	 * @return
	 */
	public ChargeOrder queryChargeOrder(int payId) {
		return queryForObject("select * from t_charge_order where pay_id=?", HANDLER, payId);
	}

	/**
	 * 查询未支付订单
	 * 
	 * @param partnerOrderNo
	 * @return
	 */
	public ChargeOrder getPayOrder(String partnerOrderNo) {
		return queryForObject("select * from t_charge_order where token=?", HANDLER, partnerOrderNo);
	}

	/**
	 * 查询总充值额
	 * 
	 * @param partnerOrderNo
	 * @return
	 */
	public Integer getSumPrice(int teamId) {
		return queryForInteger("select sum(pay_price) from t_charge_order where role_id=? and pay_status = 2", teamId);
	}

	/**
	 * 条件查询
	 * 
	 * @param filter
	 * @return
	 */
	public List<ChargeOrder> query(ChargeOrderFilter filter) {
		StringBuffer sql = new StringBuffer("select * from t_charge_order where 1=1 ");
		List<Object> val = new ArrayList<Object>();
		appendCondition(filter, val, sql);
		sql.append(" limit ? , ? ");
		val.add(filter.getStartOfPage());
		val.add(filter.getPageSize());
		return queryForList(sql.toString(), HANDLER, val.toArray());
	}

	private void appendCondition(ChargeOrderFilter filter, List<Object> val, StringBuffer sql) {

		if (filter.getAccountName() != null) {
			sql.append(" and account_name = ? ");
			val.add(filter.getAccountName());
		}

		if (filter.getToken() != null) {
			sql.append(" and token = ? ");
			val.add(filter.getToken());
		}
		if (filter.getPayStatus() != null) {
			sql.append(" and pay_status = ? ");
			val.add(filter.getPayStatus());
		}

		if (filter.getStartTime() != null) {
			sql.append(" and create_time >= ? ");
			val.add(filter.getStartTime());
		}

		if (filter.getEndTime() != null) {
			sql.append(" and create_time <= ? ");
			val.add(filter.getEndTime());
		}

		if (filter.getServiceId() != null) {
			sql.append(" and service_id = ? ");
			val.add(filter.getServiceId());
		}

		if (filter.getServerId() != null) {
			sql.append(" and server_id=?");
			val.add(filter.getServerId());
		}

	}

	/**
	 * @param chargeOrder
	 */
	public void finishSuccess(ChargeOrder chargeOrder) {

		String sql = "update t_charge_order set " + " update_time=now(), " + " role_id=?," + " role_name=?,"
				+ " nick_name=?," + " role_level=?," + " role_vip_level=?," + " role_device_uuid=?,"
				+ " role_add_gold=?," + " role_first_charge=?," + " pay_status=?," + " finish_time=?"
				+ " where pay_id=?";
		executeUpdate(sql, chargeOrder.getRoleId(), chargeOrder.getRoleName(), chargeOrder.getNickName(),
				chargeOrder.getRoleLevel(), chargeOrder.getRoleVipLevel(), chargeOrder.getRoleDeviceUUID(),
				chargeOrder.getRoleAddGold(), chargeOrder.getRoleFirstCharge(), chargeOrder.getPayStatus(),
				chargeOrder.getFinishTime(), chargeOrder.getPayId());
	}
}