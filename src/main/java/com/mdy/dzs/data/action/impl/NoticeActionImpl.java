package com.mdy.dzs.data.action.impl;

import java.util.List;

import com.mdy.dzs.data.ApplicationAwareAction;
import com.mdy.dzs.data.action.NoticeAction;
import com.mdy.dzs.data.domain.notice.Notice;

/**
 * 公告
 */
public class NoticeActionImpl extends ApplicationAwareAction implements NoticeAction {

	@Override
	public List<Notice> queryNowList(String serverId) {
		return noticeAO().queryNowList(serverId);
	}

}
