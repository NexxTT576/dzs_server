package com.mdy.dzs.data;

import com.mdy.dzs.data.ao.AccountAO;
import com.mdy.dzs.data.ao.ChargeOrderAO;
import com.mdy.dzs.data.ao.DataAO;
import com.mdy.dzs.data.ao.NoticeAO;
import com.mdy.dzs.data.ao.RewardCenterAO;
import com.mdy.dzs.data.ao.ServerInfoAO;
import com.mdy.dzs.data.ao.ServicePartnerAO;
import com.mdy.dzs.data.ao.VersionAO;

/**
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月19日 下午5:09:37
 */
public class ApplicationAwareAction {
    public DataApplication dataApplication() {
        return DataApplication.Instance();
    }

    //
    public DataAO dataAO() {
        return dataApplication().dataAO();
    }

    public ServerInfoAO serverInfoAO() {
        return dataApplication().serverInfoAO();
    }

    public ServicePartnerAO servicePartnerAO() {
        return dataApplication().servicePartnerAO();
    }

    public ChargeOrderAO chargeOrderAO() {
        return dataApplication().chargeOrderAO();
    }

    public AccountAO accountAO() {
        return dataApplication().accountAO();
    }

    public NoticeAO noticeAO() {
        return dataApplication().noticeAO();
    }

    public VersionAO versionAO() {
        return dataApplication().versionAO();
    }

    public RewardCenterAO rewardCenterAO() {
        return dataApplication().rewardCenterAO();
    }
}