package com.mdy.dzs.data.domain.notice;

import java.io.Serializable;
import java.util.Date;


/**
 * 公告模型
 * @author 房曈
 *
 */
public class Notice implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**公告id*/
	private int id;
	/**服务器id*/
	private String serverId;
	/**标题*/
	private String title;
	/**标题颜色*/
	private String tcolor;
	/**标题字体*/
	private String tfont;
	/**标题效果*/
	private String teffect;
	/**是否加粗*/
	private String tbold;
	/**正文内容*/
	private String content;
	/**正文颜色*/
	private String ccolor;
	/**正文字号*/
	private String cfont;
	/**开始时间*/
	private Date startTime;
	/**结束时间*/
	private Date endTime;
	/**更新时间*/
	private Date createTime;
	/**展示类型*/
	private int showStatus;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTcolor(){
		return this.tcolor;
	}
	public void setTcolor(String tcolor){
		this.tcolor=tcolor;
	}
	public String getTfont(){
		return this.tfont;
	}
	public void setTfont(String tfont){
		this.tfont=tfont;
	}
	public String getTeffect(){
		return this.teffect;
	}
	public void setTeffect(String teffect){
		this.teffect=teffect;
	}
	public String getTbold(){
		return this.tbold;
	}
	public void setTbold(String tbold){
		this.tbold=tbold;
	}
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getCcolor(){
		return this.ccolor;
	}
	public void setCcolor(String ccolor){
		this.ccolor=ccolor;
	}
	public String getCfont(){
		return this.cfont;
	}
	public void setCfont(String cfont){
		this.cfont=cfont;
	}
	public Date getStartTime(){
		return this.startTime;
	}
	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}
	public Date getEndTime(){
		return this.endTime;
	}
	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public int getShowStatus() {
		return showStatus;
	}
	public void setShowStatus(int showStatus) {
		this.showStatus = showStatus;
	}
}