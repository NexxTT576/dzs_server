package com.mdy.dzs.data.ao;

import java.util.List;

import com.mdy.dzs.data.domain.notice.Notice;

/**
 * 公告
 * 
 * @author 房曈
 *
 */
public class NoticeAO extends BaseAO {
	//
	/**
	 * 查询列表
	 */
	public List<Notice> queryNowList(String serverId) {
		return noticeDAO().queryNowList(serverId);
	}

	/**
	 * 添加
	 * 
	 * @param Notice
	 */
	public void add(Notice n) {
		noticeDAO().add(n);
	}

	/**
	 * 更新
	 * 
	 * @param Notice
	 */
	public void update(Notice n) {
		noticeDAO().update(n);
	}
}