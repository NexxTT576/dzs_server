package com.mdy.dzs.data.action.impl;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.data.ApplicationAwareAction;
import com.mdy.dzs.data.action.ServerInfoAction;
import com.mdy.dzs.data.dao.filter.SampleDataFilter;
import com.mdy.dzs.data.domain.server.ServerInfo;
import com.mdy.dzs.data.domain.server.ServerSampleData;
import com.mdy.dzs.data.domain.server.ServerSampleStat;
import com.mdy.sharp.container.biz.BizException;

/**
 * 服务器信息
 * 
 */
public class ServerInfoActionImpl extends ApplicationAwareAction implements ServerInfoAction {
	/**
	 * 查询
	 * 
	 * @return
	 */
	@Override
	public List<ServerInfo> serverInfos() {
		return serverInfoAO().queryList();
	}

	/**
	 * 通过区ID,查询区信息
	 */
	@Override
	public ServerInfo getServerInfo(String serverId) throws BizException {
		return serverInfoAO().query(serverId);
	}

	/**
	 * 添加服务器
	 */
	@Override
	public void addServerInfo(ServerInfo serverInfo) throws BizException {
		serverInfoAO().add(serverInfo);
	}

	/**
	 * 更新服务器信息
	 */
	@Override
	public void updateServerInfo(ServerInfo serverInfo) throws BizException {
		serverInfoAO().update(serverInfo);
	}

	/**
	 * 批量更新服务器信息
	 */
	@Override
	public void batchUpdateServer(String ids, int status) {
		serverInfoAO().batchUpdateServer(ids, status);
	}

	/**
	 * 统计在线数据
	 */
	@Override
	public List<ServerSampleStat> getSampleDataStat(SampleDataFilter filter) {
		// return serverSampleAO().getSampleDataStat(filter);
		return null;
	}

	/**
	 * 更新数据
	 */
	@Override
	public void sample(ServerSampleData sampleData) throws BizException {
		// sampleData.setSampleTime(new Date());
		// Calendar cc = Calendar.getInstance();
		// cc.setTime(sampleData.getSampleTime());
		// int min = cc.get(Calendar.MINUTE);
		// int theMin = (min / 5) * 5;
		// cc.set(Calendar.MINUTE, theMin);
		// cc.set(Calendar.SECOND, 0);
		// Date theTime = cc.getTime();
		// sampleData.setSampleTime(theTime);
		//
		// ServerSampleData sd = serverSampleAO().getServerSampleData(theTime);
		// if (sd != null) {
		// sd.setDisConnSessionCount(sd.getDisConnSessionCount()
		// + sampleData.getDisConnSessionCount());
		// sd.setSessionCount(sd.getSessionCount()
		// + sampleData.getSessionCount());
		// serverSampleAO().updateSampleData(sd);
		// } else {
		// serverSampleAO().addSampleData(sampleData);
		// }
	}

	/**
	 * 服务器状态采样的数据列表
	 * 
	 * @param startDate
	 * @param endDate
	 * @param serverId
	 * @return
	 */
	@Override
	public List<ServerSampleData> queryServerSampleData(Date startDate, Date endDate, String serverId) {
		// SampleDataFilter filter = new SampleDataFilter();
		// filter.setServerId(serverId);
		// filter.setStartTime(startDate);
		// filter.setEndTime(endDate);
		// return serverSampleAO().querySampleData(filter);
		return null;
	}

	@Override
	public ServerInfo start(String serverId) throws BizException {
		ServerInfo info = serverInfoAO().queryExistedServerInfo(serverId);
		info.setStatus(ServerInfo.RUN_STATUS_STARTED);
		serverInfoAO().update(info);
		return info;
	}

	@Override
	public void stop(String serverId) throws BizException {
		ServerInfo info = serverInfoAO().queryExistedServerInfo(serverId);
		info.setStatus(ServerInfo.RUN_STATUS_STOPPED);
		serverInfoAO().update(info);
	}
}
