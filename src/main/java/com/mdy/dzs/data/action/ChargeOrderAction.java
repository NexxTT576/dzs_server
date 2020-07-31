package com.mdy.dzs.data.action;

import java.util.List;

import com.mdy.dzs.data.dao.filter.ChargeOrderFilter;
import com.mdy.dzs.data.domain.charge.ChargeOrder;

/**
 * 充值Action
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月19日 下午5:07:04
 */
public interface ChargeOrderAction {
	/**
	 * 生成订单
	 */
	void generateOrder(ChargeOrder chargeOrder);

	/**
	 * 查询未支付订单
	 * 
	 * @param partnerOrderNo
	 * @return
	 */
	ChargeOrder getPayOrder(String partnerOrderNo);

	/**
	 * 充值成功
	 * 
	 * @param chargeOrder
	 */
	void chargeSuccess(ChargeOrder chargeOrder);

	/**
	 * 服务器处理成功
	 */
	void finishSuccess(ChargeOrder chargeOrder);

	/**
	 * 获取用户充值总额
	 * 
	 * @param openId
	 */
	Integer getSumPrice(int teamId);

	List<ChargeOrder> query(ChargeOrderFilter filter);
}
