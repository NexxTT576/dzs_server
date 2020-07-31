/**
 * 
 */
package com.mdy.dzs.data.domain.account;

import java.io.Serializable;
import java.util.Map;

/**
 * IOS设备信息
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年11月13日  下午4:21:17
 */
public class IOSDeviceInfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**设备类型*/
	private String deviceType;
	/**设备版本号*/
	private String deviceVersion;
	/**设备id*/
	private String deviceUUID;
	
	public IOSDeviceInfo() {
	}
	
	public IOSDeviceInfo(Map<String, Object> map) {
		setDeviceType(map.get("deviceType")+"");
		setDeviceVersion(map.get("deviceVersion")+"");
		setDeviceUUID(map.get("deviceUUID")+"");
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceVersion() {
		return deviceVersion;
	}
	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}
	public String getDeviceUUID() {
		return deviceUUID;
	}
	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}
	
	
}
