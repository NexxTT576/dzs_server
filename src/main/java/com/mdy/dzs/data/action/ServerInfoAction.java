package com.mdy.dzs.data.action;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.data.dao.filter.SampleDataFilter;
import com.mdy.dzs.data.domain.server.ServerInfo;
import com.mdy.dzs.data.domain.server.ServerSampleData;
import com.mdy.dzs.data.domain.server.ServerSampleStat;
import com.mdy.sharp.container.biz.BizException;

/**
 * 服务器信息
 */
public interface ServerInfoAction {
	/**
	 * 查询
	 * 
	 * @return
	 */
	List<ServerInfo> serverInfos();

	/**
	 * 通过区Id获取区信息
	 * 
	 * @param serverId
	 * @return
	 */
	ServerInfo getServerInfo(String serverId) throws BizException;

	/**
	 * 添加区
	 * 
	 * @param serverInfo
	 * @return
	 */
	void addServerInfo(ServerInfo serverInfo) throws BizException;

	/**
	 * 更新区
	 * 
	 * @param serverInfo
	 * @return
	 */
	void updateServerInfo(ServerInfo serverInfo) throws BizException;

	/**
	 * 批量修改服务器状态
	 * 
	 * @param ids
	 * @param status
	 */
	void batchUpdateServer(String ids, int status);

	/**
	 * 
	 * @param filter
	 * @return
	 */
	List<ServerSampleStat> getSampleDataStat(SampleDataFilter filter);

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ServerSampleData> queryServerSampleData(Date startDate, Date endDate, String serverId);

	/**
	 * 
	 * @param sampleData
	 * @throws BizException
	 */
	void sample(ServerSampleData sampleData) throws BizException;

	/**
	 * 
	 * @param serverId
	 * @return
	 * @throws BizException
	 */
	ServerInfo start(String serverId) throws BizException;

	/**
	 * 
	 * @param serverId
	 * @throws BizException
	 */
	void stop(String serverId) throws BizException;

}