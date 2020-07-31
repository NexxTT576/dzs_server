package com.mdy.dzs.game.fight.main;

/**
 * 战斗位置
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日  下午6:31:34
 */
public enum FightSide {
	发起方(1),
	应战方(2);
	
	public int code;
	private FightSide(int code) {
		this.code = code;
	}
}
