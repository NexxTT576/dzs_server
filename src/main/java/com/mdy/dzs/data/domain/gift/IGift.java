/**
 * 
 */
package com.mdy.dzs.data.domain.gift;

import java.util.List;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月12日  上午10:50:41
 */
public interface IGift {

	List<Integer> getArrType();
	List<Integer> getArrItem();
	List<Integer> getArrNum();
	void setItem(List<GiftItem> item);
}
