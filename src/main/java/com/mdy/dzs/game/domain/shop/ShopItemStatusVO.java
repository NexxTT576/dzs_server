/**
 * 
 */
package com.mdy.dzs.game.domain.shop;

import java.io.Serializable;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月9日  下午3:45:17
 */
public class ShopItemStatusVO implements Serializable {

	/**item id*/
	private int id;
	/**可购买数量*/
	private int cnt;
	/**钱数*/
	private int coin;
	/**购买类型*/
	private int coinType;
	/**已有金钱*/
	private int gold;
	/**已有银币*/
	private int silver;
	

	public ShopItemStatusVO(int id, int cnt, int coin, int coinType, int gold, int silver) {
		this.id = id;
		this.cnt = cnt;
		this.coin = coin;
		this.coinType = coinType;
		this.gold = gold;
		this.silver = silver;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getCoinType() {
		return coinType;
	}

	public void setCoinType(int coinType) {
		this.coinType = coinType;
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


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
