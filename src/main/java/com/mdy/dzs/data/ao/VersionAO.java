package com.mdy.dzs.data.ao;

import java.util.List;

import com.mdy.dzs.data.domain.version.Version;

/**
 * 版本
 * 
 * @author 房曈
 *
 */
public class VersionAO extends BaseAO {
	//
	/**
	 * 查询列表
	 */
	public List<Version> queryList() {
		return versionDAO().queryList();
	}
}