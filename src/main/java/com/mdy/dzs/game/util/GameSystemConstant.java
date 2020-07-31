package com.mdy.dzs.game.util;

/**
 * 常量类，存放公用常量
 */
public final class GameSystemConstant {
	private GameSystemConstant() {
	}

	/** 道具小 */
	public static final int TYPE_小 = 1;
	/** 道具中 */
	public static final int TYPE_中 = 2;
	/** 道具大 */
	public static final int TYPE_大 = 3;
	//
	/** Boss后台发放 */
	public static final int BUY_TYPE_BOSS = 0;
	/** 饭票支付 */
	public static final int BUY_TYPE_FP = 1;
	/** 饭卡支付 */
	public static final int BUY_TYPE_FK = 2;
	/** 游戏奖励 */
	public static final int BUY_TYPE_GAME = 3;
	
	/** 饭票兑换 */
	public static final int EXCHANGE_TYPE_FP = 1;
	/** 饭卡兑换 */
	public static final int EXCHANGE_TYPE_FK = 2;
	
}