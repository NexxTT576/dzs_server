package com.mdy.dzs.game.domain.activity.guessgame;

import java.io.Serializable;

import com.mdy.dzs.data.domain.item.ProbItem;

/**
 * 猜拳抽奖
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月24日  下午5:49:42
 */
public class ActivityGuessChooseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pos;
	private ProbItem item;
	private int index;
	private int chooseCnt;
	
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public ProbItem getItem() {
		return item;
	}
	public void setItem(ProbItem item) {
		this.item = item;
	}
	public int getChooseCnt() {
		return chooseCnt;
	}
	public void setChooseCnt(int chooseCnt) {
		this.chooseCnt = chooseCnt;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
