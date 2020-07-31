package com.mdy.dzs.game.domain.activity.mazegame;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;

/**
 * 迷宫寻宝表结构模型
 * 
 * @author 白雪林
 *
 */
public class RoleActivityMaze implements Serializable {

	public static final int refresh = 1;
	public static final int notRefresh = 2;

	public static final int digOne = 1;
	public static final int digAll = 2;

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	/** 角色id */
	private int roleId;
	/** 当前轮使用免费挖宝次数 */
	private int digFreeCnt;
	/** 挖宝次数 */
	private int digCnt;
	/** 宝库物品 */
	private Map<String, ProbItem> treasuryMap;
	/** 免费次数 */
	private int freeTimes;
	/** 剩余元宝探宝次数 */
	private int surGoldTimes;
	/** 已挖到宝库物品key */
	private List<Integer> getTreasuryList;
	/** 下次读取行数 */
	private int nextLine;
	/** position list */
	private List<Integer> positionList;
	/** prob list */
	private List<Integer> probList;
	/** 刷新时间 */
	private Date refreshTime;
	/** 创建时间 */
	private Date createTime;
	/** 刷新时间 */
	private Date updateTime;

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Map<String, ProbItem> getTreasuryMap() {
		return this.treasuryMap;
	}

	public void setTreasuryMap(Map<String, ProbItem> treasuryMap) {
		this.treasuryMap = treasuryMap;
	}

	public int getFreeTimes() {
		return this.freeTimes;
	}

	public void setFreeTimes(int freeTimes) {
		this.freeTimes = freeTimes;
	}

	public int getSurGoldTimes() {
		return this.surGoldTimes;
	}

	public void setSurGoldTimes(int surGoldTimes) {
		this.surGoldTimes = surGoldTimes;
	}

	public List<Integer> getGetTreasuryList() {
		return this.getTreasuryList;
	}

	public void setGetTreasuryList(List<Integer> arrayList) {
		this.getTreasuryList = arrayList;
	}

	public Date getRefreshTime() {
		return this.refreshTime;
	}

	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getDigCnt() {
		return digCnt;
	}

	public void setDigCnt(int digCnt) {
		this.digCnt = digCnt;
	}

	public int getDigFreeCnt() {
		return digFreeCnt;
	}

	public void setDigFreeCnt(int digFreeCnt) {
		this.digFreeCnt = digFreeCnt;
	}

	public int getNextLine() {
		return nextLine;
	}

	public void setNextLine(int nextLine) {
		this.nextLine = nextLine;
	}

	public List<Integer> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<Integer> positionList) {
		this.positionList = positionList;
	}

	public List<Integer> getProbList() {
		return probList;
	}

	public void setProbList(List<Integer> probList) {
		this.probList = probList;
	}
}