package com.mdy.dzs.data.action;

import java.util.List;

import com.mdy.dzs.data.domain.server.ServicePartner;

public interface ServicePartnerAction {

	List<ServicePartner> getPartners();

	ServicePartner getPartnerById(int id);
}
