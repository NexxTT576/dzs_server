package com.mdy.dzs.data.action.impl;

import java.util.List;

import com.mdy.dzs.data.ApplicationAwareAction;
import com.mdy.dzs.data.action.ServicePartnerAction;
import com.mdy.dzs.data.domain.server.ServicePartner;

public class ServicePartnerActionImpl extends ApplicationAwareAction implements ServicePartnerAction {

	@Override
	public List<ServicePartner> getPartners() {
		return servicePartnerAO().getPartners();
	}

	@Override
	public ServicePartner getPartnerById(int id) {
		return servicePartnerAO().getPartner(id);
	}

}
