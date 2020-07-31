package com.mdy.dzs.data.ao;

import java.util.List;

import com.mdy.dzs.data.domain.server.ServerInfo;
import com.mdy.dzs.data.exception.AccountException;
import com.mdy.sharp.container.biz.BizException;

/**
 * 服务器信息表
 * 
 * @author yanfeng.wang
 * 
 */
public class ServerInfoAO extends BaseAO {

	/**
	 * 查询
	 */
	public ServerInfo query(String serverId) {
		return serverInfoDAO().query(serverId);
	}

	public ServerInfo queryExistedServerInfo(String serverId) throws BizException {
		ServerInfo si = serverInfoDAO().query(serverId);
		if (si == null) {
			throw AccountException.getException(AccountException.EXCE_SERVER_NOT_EXISTS, serverId);
		}
		return si;
	}

	/**
	 * 查询列表
	 */
	public List<ServerInfo> queryList() {
		return serverInfoDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param ServerInfo
	 */
	public void add(ServerInfo si) {
		serverInfoDAO().add(si);
	}

	/**
	 * 更新
	 * 
	 * @param ServerInfo
	 */
	public void update(ServerInfo si) {
		serverInfoDAO().update(si);
	}

	/**
	 * 批量更新服务器
	 * 
	 * @param ids
	 * @param status
	 */
	public void batchUpdateServer(String ids, int status) {
		serverInfoDAO().batchUpdateServer(ids, status);
	}
}