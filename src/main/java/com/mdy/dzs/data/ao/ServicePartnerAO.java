package com.mdy.dzs.data.ao;

import java.util.List;

import com.mdy.dzs.data.domain.server.ServicePartner;

/**
 * 合作方
 * 
 * @author 房曈
 *
 */
public class ServicePartnerAO extends BaseAO {
	//
	/**
	 * 查询
	 */
	public ServicePartner getPartner(int id) {
		return servicePartnerDAO().query(id);
	}

	/**
	 * 查询列表
	 */
	public List<ServicePartner> getPartners() {
		return servicePartnerDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param ServicePartner
	 */
	public void add(ServicePartner sp) {
		servicePartnerDAO().add(sp);
	}

	/**
	 * 更新
	 * 
	 * @param ServicePartner
	 */
	public void update(ServicePartner sp) {
		servicePartnerDAO().update(sp);
	}
}