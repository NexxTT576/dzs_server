package com.mdy.dzs.data.domain.account;

import java.io.Serializable;
import java.util.Date;


/**
 * 账号白名单模型
 * @author 房曈
 *
 */
public class WhiteAccount implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**账号*/
	private String account;
	/**注释*/
	private String desc;
	/***/
	private Date createTime;

	public String getAccount(){
		return this.account;
	}
	public void setAccount(String account){
		this.account=account;
	}
	public String getDesc(){
		return this.desc;
	}
	public void setDesc(String desc){
		this.desc=desc;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
}