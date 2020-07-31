/**
 * 
 */
package com.mdy.dzs.game.domain.activity.guessgame;

import java.io.Serializable;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月24日  下午2:53:25
 */
public class ActivityGuessingGameVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**胜负*/
	private int win;
	/**可挑战次数*/
	private int guessCount;
	/**可翻牌次数*/
	private int chooseCount;
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getGuessCount() {
		return guessCount;
	}
	public void setGuessCount(int guessCount) {
		this.guessCount = guessCount;
	}
	public int getChooseCount() {
		return chooseCount;
	}
	public void setChooseCount(int chooseCount) {
		this.chooseCount = chooseCount;
	}
	
	
}
