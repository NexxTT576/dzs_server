/**
 * 
 */
package com.mdy.dzs.game.domain.arena;

import java.io.Serializable;

import com.mdy.dzs.game.domain.card.RoleCard;

/**
 * 竞技场 人物卡牌vo
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月10日  上午10:36:37
 */
public class RoleArenaCardVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int resId;
	private int cls;
	
	public RoleArenaCardVO(RoleCard card) {
		this.resId = card.getResId();
		this.cls = card.getCls();
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public int getCls() {
		return cls;
	}

	public void setCls(int cls) {
		this.cls = cls;
	}
	
	
}
