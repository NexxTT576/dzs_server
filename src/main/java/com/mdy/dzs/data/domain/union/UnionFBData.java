package com.mdy.dzs.data.domain.union;

import java.io.Serializable;
import java.util.List;

public class UnionFBData  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -493967594557235767L;
	
	private int id;
	private int fbId;
	private int type;
	private String chapterName;
	private String bossName;
	private int preField;
	private int limitLevle;
	private int npc;
	private int num;
	private List<Integer> rewardIds;
	private List<Integer> rewardType;
	private List<Integer> rewardNum;
	private int drop;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFbId() {
		return fbId;
	}
	public void setFbId(int fbId) {
		this.fbId = fbId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getBossName() {
		return bossName;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	public int getPreField() {
		return preField;
	}
	public void setPreField(int preField) {
		this.preField = preField;
	}
	public int getLimitLevle() {
		return limitLevle;
	}
	public void setLimitLevle(int limitLevle) {
		this.limitLevle = limitLevle;
	}
	public int getNpc() {
		return npc;
	}
	public void setNpc(int npc) {
		this.npc = npc;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List<Integer> getRewardIds() {
		return rewardIds;
	}
	public void setRewardIds(List<Integer> rewardIds) {
		this.rewardIds = rewardIds;
	}
	public List<Integer> getRewardType() {
		return rewardType;
	}
	public void setRewardType(List<Integer> rewardType) {
		this.rewardType = rewardType;
	}
	public List<Integer> getRewardNum() {
		return rewardNum;
	}
	public void setRewardNum(List<Integer> rewardNum) {
		this.rewardNum = rewardNum;
	}
	public int getDrop() {
		return drop;
	}
	public void setDrop(int drop) {
		this.drop = drop;
	}
	
}
