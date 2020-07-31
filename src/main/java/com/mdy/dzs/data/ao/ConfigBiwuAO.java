package com.mdy.dzs.data.ao;

import java.util.List;

import com.mdy.dzs.data.domain.biwu.ConfigBiwu;

/**
 * 比武配置
 * 
 * @author zhou
 *
 */
public class ConfigBiwuAO extends BaseAO {
	//
	/**
	 * 查询
	 */
	public ConfigBiwu query(int id) {
		return configBiwuDAO().query(id);
	}

	/**
	 * 查询列表
	 */
	public List<ConfigBiwu> queryList() {
		return configBiwuDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param ConfigBiwu
	 */
	public void add(ConfigBiwu cb) {
		configBiwuDAO().add(cb);
	}

	/**
	 * 更新
	 * 
	 * @param ConfigBiwu
	 */
	public void update(ConfigBiwu cb) {
		configBiwuDAO().update(cb);
	}
}