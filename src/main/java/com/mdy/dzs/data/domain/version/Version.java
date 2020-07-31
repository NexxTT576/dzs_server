package com.mdy.dzs.data.domain.version;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 版本模型
 * @author 房曈
 *
 */
public class Version implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**版本号*/
	private String version;
	/**描述*/
	private String desc;
	/**更新卡片*/
	private List<Integer> updateCards;
	/**创建时间*/
	private Date createTime;

	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version=version;
	}
	public String getDesc(){
		return this.desc;
	}
	public void setDesc(String desc){
		this.desc=desc;
	}
	public List<Integer> getUpdateCards(){
		return this.updateCards;
	}
	public void setUpdateCards(List<Integer> updateCards){
		this.updateCards=updateCards;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
}