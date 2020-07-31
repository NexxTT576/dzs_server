package com.mdy.dzs.data.domain.server;

import java.io.Serializable;
import java.util.Date;


/**
 * 合作方模型
 * @author 房曈
 *
 */
public class ServicePartner implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**渠道id*/
	private int id;
	/**渠道名*/
	private String strChannelName;
	/**账号头*/
	private String accountHead;
	/**平台*/
	private String strPlatform;
	/**登陆服务器url*/
	private String loginUrl;
	/**版本更新服务器url*/
	private String versionUrl;
	/**appid*/
	private String appid;
	/**appkey*/
	private String appkey;
	/**extendKey 1*/
	private String extendKey1;
	/**extendKey 2*/
	private String extendKey2;
	
	public String getExtendKey1() {
		return extendKey1;
	}
	public void setExtendKey1(String extendKey1) {
		this.extendKey1 = extendKey1;
	}
	public String getExtendKey2() {
		return extendKey2;
	}
	public void setExtendKey2(String extendKey2) {
		this.extendKey2 = extendKey2;
	}
	/***/
	private Date updateTime;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getStrChannelName(){
		return this.strChannelName;
	}
	public void setStrChannelName(String strChannelName){
		this.strChannelName=strChannelName;
	}
	public String getAccountHead(){
		return this.accountHead;
	}
	public void setAccountHead(String accountHead){
		this.accountHead=accountHead;
	}
	public String getStrPlatform(){
		return this.strPlatform;
	}
	public void setStrPlatform(String strPlatform){
		this.strPlatform=strPlatform;
	}
	public String getLoginUrl(){
		return this.loginUrl;
	}
	public void setLoginUrl(String loginUrl){
		this.loginUrl=loginUrl;
	}
	public String getVersionUrl(){
		return this.versionUrl;
	}
	public void setVersionUrl(String versionUrl){
		this.versionUrl=versionUrl;
	}
	public String getAppid(){
		return this.appid;
	}
	public void setAppid(String appid){
		this.appid=appid;
	}
	public String getAppkey(){
		return this.appkey;
	}
	public void setAppkey(String appkey){
		this.appkey=appkey;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
}