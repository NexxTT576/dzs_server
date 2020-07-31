package com.mdy.dzs.data.action.impl;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.data.ApplicationAwareAction;
import com.mdy.dzs.data.action.ChargeOrderAction;
import com.mdy.dzs.data.dao.filter.ChargeOrderFilter;
import com.mdy.dzs.data.domain.charge.ChargeOrder;

/**
 * 充值Action
 */
public class ChargeOrderActionImpl extends ApplicationAwareAction implements ChargeOrderAction {
	/**
	 * 生成订单
	 */
	@Override
	public void generateOrder(ChargeOrder chargeOrder) {
		chargeOrderAO().generateOrder(chargeOrder);
	}

	/**
	 * 查询未支付订单
	 */
	@Override
	public ChargeOrder getPayOrder(String partnerOrderNo) {
		return chargeOrderAO().getPayOrder(partnerOrderNo);
	}

	/**
	 * 充值成功
	 */
	@Override
	public void chargeSuccess(ChargeOrder chargeOrder) {
		chargeOrderAO().chargeSuccess(chargeOrder);
	}

	/**
	 * 服务器更新成功
	 */
	@Override
	public void finishSuccess(ChargeOrder chargeOrder) {
		chargeOrder.setPayStatus(ChargeOrder.STATUS_CHARGE_FINISH);
		chargeOrder.setFinishTime(new Date());
		chargeOrderAO().finishSuccess(chargeOrder);
	}

	@Override
	public Integer getSumPrice(int teamId) {
		return chargeOrderAO().getSumPrice(teamId);
	}

	public List<ChargeOrder> query(ChargeOrderFilter filter) {
		return chargeOrderAO().query(filter);
	}
}