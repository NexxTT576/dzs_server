package com.mdy.dzs.game.domain.card;

import java.io.Serializable;
import java.util.List;

public class UserInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**唯一id*/
	private int id;
	/**
	 * //上阵武将,可上阵武将
	 */
	private List<Integer> fmtCnt;		
	/**
	 * //元宝数
	 */
	private int gold;	
	/**
	 * //银币
	 */
	private int silver;	 	
	/**
	 * //侠魂
	 */
	private int soul;		 
	/**
	 * //魂玉
	 */
	private int hunYuVal;		
	/**
	 * //体力值
	 */
	private int physVal;	
	/**
	 * //体力上限
	 */
	private int physValLimit;	
	/**
	 * //体力回复时间秒数，体力回满时间秒数
	 */
	private String[] physValTime;
	/**
	 * //耐力值
	 */
	private int resisVal;	 	
	/**
	 * //耐力上限
	 */
	private int resisValLimit;	
	/**
	 * //耐力回复时间秒数，耐力回满时间秒数
	 */
	private String[] resisValTime;
	public List<Integer> getFmtCnt() {
		return fmtCnt;
	}
	public void setFmtCnt(List<Integer> pfmtCnt) {
		this.fmtCnt = pfmtCnt;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getSilver() {
		return silver;
	}
	public void setSilver(int silver) {
		this.silver = silver;
	}
	public int getSoul() {
		return soul;
	}
	public void setSoul(int soul) {
		this.soul = soul;
	}
	public int getHunYuVal() {
		return hunYuVal;
	}
	public void setHunYuVal(int hunYuVal) {
		this.hunYuVal = hunYuVal;
	}
	public int getPhysVal() {
		return physVal;
	}
	public void setPhysVal(int physVal) {
		this.physVal = physVal;
	}
	public int getPhysValLimit() {
		return physValLimit;
	}
	public void setPhysValLimit(int physValLimit) {
		this.physValLimit = physValLimit;
	}
	public String[] getPhysValTime() {
		return physValTime;
	}
	public void setPhysValTime(String[] physValTime) {
		this.physValTime = physValTime;
	}
	public int getResisVal() {
		return resisVal;
	}
	public void setResisVal(int resisVal) {
		this.resisVal = resisVal;
	}
	public int getResisValLimit() {
		return resisValLimit;
	}
	public void setResisValLimit(int resisValLimit) {
		this.resisValLimit = resisValLimit;
	}
	public String[] getResisValTime() {
		return resisValTime;
	}
	public void setResisValTime(String[] resisValTime) {
		this.resisValTime = resisValTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
