/**
 * 
 */
package com.mdy.dzs.data.dao.datas;

import java.util.List;

/**
 * 查询数据接口
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年3月3日  下午8:39:30
 */
public interface QueryDateInterface<T> {
	List<T> queryList();
}
