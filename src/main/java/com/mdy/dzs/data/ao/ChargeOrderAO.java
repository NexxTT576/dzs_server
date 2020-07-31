package com.mdy.dzs.data.ao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.data.dao.filter.ChargeOrderFilter;
import com.mdy.dzs.data.domain.charge.ChargeOrder;

/**
 * 充值AO
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月19日 下午4:56:29
 */
public class ChargeOrderAO extends BaseAO {
	/**
	 * 生成订单
	 */
	public void generateOrder(ChargeOrder chargeOrder) {
		chargeOrderDAO().addChargeOrder(chargeOrder);
	}

	/**
	 * 查询未支付订单
	 * 
	 * @param partnerOrderNo
	 * @return
	 */
	public ChargeOrder getPayOrder(String partnerOrderNo) {
		return chargeOrderDAO().getPayOrder(partnerOrderNo);
	}

	/**
	 * 查询订单
	 * 
	 * @param payId
	 * @return
	 */
	public ChargeOrder queryChargeOrder(int payId) {
		return chargeOrderDAO().queryChargeOrder(payId);
	}

	/**
	 * 充值成功
	 * 
	 * @param chargeOrder
	 */
	public void chargeSuccess(ChargeOrder chargeOrder) {
		chargeOrder.setPayTime(new Date());
		chargeOrder.setNotifyTime(new Date());
		chargeOrder.setPayStatus(ChargeOrder.STATUS_CHARGE_NOTIFY);
		chargeOrderDAO().updateChargeOrder(chargeOrder);
		// getNotificationManager().sendNotification(DataConstant.CHARGE_SUCCESS_EVENT,
		// chargeOrder);
	}

	/**
	 * 服务器处理成功
	 */
	public void finishSuccess(ChargeOrder chargeOrder) {
		chargeOrderDAO().finishSuccess(chargeOrder);
	}

	/**
	 * 获取用户充值总额
	 * 
	 * @param openId
	 */
	public Integer getSumPrice(int teamId) {
		return chargeOrderDAO().getSumPrice(teamId);
	}

	public List<ChargeOrder> query(ChargeOrderFilter filter) {
		return chargeOrderDAO().query(filter);
	}
}
