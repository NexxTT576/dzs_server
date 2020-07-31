package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;

//动态
public class UnionLog implements Serializable
{
	private static final long serialVersionUID = -4678889296863466923L;

	public static final int SYS_帮派_建筑升级			= 100001;
	public static final int SYS_帮派_捐献	            = 100002;
	public static final int SYS_帮派_创建帮派      	= 100003;
	public static final int SYS_帮派_修改公告宣言   	= 100004;
	public static final int SYS_帮派_禅让帮主		    = 100005;
	public static final int SYS_帮派_帮主自荐			= 100006;
	public static final int SYS_帮派_开启烧烤大会		= 100007;
	public static final int SYS_帮派_定时修改帮派信息	= 100008;
	public static final int SYS_帮派_开启青龙堂	    = 100009;
	public static final int SYS_帮派_青龙等级提升	    = 100010;
	/**主键ID*/
	private int id;
	/**帮派id*/
	private int unionId;
	/**帮派等级*/
	private int unionLevel;
	/**所属功能*/
	private int systemType;
	/**类型*/
	private int itemPos;
	/***/
	private int itemId;
	/**变更数量数目*/
	private int itemNum;
	/**变更后*/
	private int newNum;
	/**变更批注*/
	private String comments;
	/**记录创建时间*/
	private Date createTime;
	
	public static UnionLog valueOf(Union union,int itemPos,int changeNum,int afterNum,String comments){
		UnionLog unionLog = new UnionLog();
		unionLog.setUnionId(union.getId());
		unionLog.setUnionLevel(union.getLevel());
		unionLog.setItemPos(itemPos);
		unionLog.setItemNum(changeNum);
		unionLog.setNewNum(afterNum);
		unionLog.setComments(comments);
		return unionLog;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUnionId() {
		return unionId;
	}
	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}
	public int getUnionLevel() {
		return unionLevel;
	}
	public void setUnionLevel(int unionLevel) {
		this.unionLevel = unionLevel;
	}
	public int getSystemType() {
		return systemType;
	}
	public void setSystemType(int systemType) {
		this.systemType = systemType;
	}
	public int getItemPos() {
		return itemPos;
	}
	public void setItemPos(int itemPos) {
		this.itemPos = itemPos;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public int getNewNum() {
		return newNum;
	}
	public void setNewNum(int newNum) {
		this.newNum = newNum;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
