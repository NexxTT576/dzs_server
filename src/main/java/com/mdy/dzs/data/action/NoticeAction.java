package com.mdy.dzs.data.action;

import java.util.List;

import com.mdy.dzs.data.domain.notice.Notice;

public interface NoticeAction {

	/**
	 * 查询当前公告
	 * 
	 * @return
	 */
	List<Notice> queryNowList(String serverId);
}
