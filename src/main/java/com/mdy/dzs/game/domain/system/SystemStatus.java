/**
 * 
 */
package com.mdy.dzs.game.domain.system;

import java.io.Serializable;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年3月2日  下午5:14:00
 */
public class SystemStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final int SYSTEM_限时豪杰 	= 101;
	public static final int SYSTEM_成就 		= 201;
	public static final int SYSTEM_帮派商店 	= 301;
	public static final int SYSTEM_青龙堂 		= 302;
	
	private int systemId;
	private int open;
	
	public int getSystemId() {
		return systemId;
	}
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	
	
}
