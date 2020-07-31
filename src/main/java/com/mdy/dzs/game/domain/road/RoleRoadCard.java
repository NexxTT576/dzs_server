package com.mdy.dzs.game.domain.road;

import java.io.Serializable;

/**
 * 名将
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月15日  下午2:36:33
 */
public class RoleRoadCard implements Serializable {

	public static final int TYPE_豪杰 = 1;
	public static final int TYPE_高手 = 2;
	public static final int TYPE_新秀 = 3;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**名将id*/
	private int resId;
	/**好感度级别*/
	private int level;
	/**好感经验*/
	private int curExp;
	
	private int addExp;
	
	private int quality;
	
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getCurExp() {
		return curExp;
	}
	public void setCurExp(int curExp) {
		this.curExp = curExp;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getAddExp() {
		return addExp;
	}
	public void setAddExp(int addExp) {
		this.addExp = addExp;
	}
	
	
	
}
