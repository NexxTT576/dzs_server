package com.mdy.dzs.game.domain.snatch;

import java.io.Serializable;
import java.util.List;

public class SnatchTenVO implements Serializable {
	/*
	 * { isGetItem: 是否夺到碎片 【1-夺到 2-未夺到】 getIndex: 第*次夺得碎片 level: 当前等级 exp: 当前经验值
	 * snatchMap：{ 1：{ probItem： 掉落物品 {type:*,id:*,num:*} coinAry: 奖励货币类型 [ {t:*,
	 * id:*, n:*} ... ] } } }
	 */

	private static final long serialVersionUID = 1L;

	private int isGetItem;
	private int getIndex;
	private int level;
	private int exp;
	private List<ARoundVO> rtnAry;
	private int naili;

	public SnatchTenVO(int isGetItem, int getIndex, int level, int exp, List<ARoundVO> rtnAry, int naili) {
		this.isGetItem = isGetItem;
		this.getIndex = getIndex;
		this.level = level;
		this.exp = exp;
		this.rtnAry = rtnAry;
		this.naili = naili;
	}

	public int getIsGetItem() {
		return isGetItem;
	}

	public void setIsGetItem(int isGetItem) {
		this.isGetItem = isGetItem;
	}

	public int getGetIndex() {
		return getIndex;
	}

	public void setGetIndex(int getIndex) {
		this.getIndex = getIndex;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public List<ARoundVO> getRtnAry() {
		return rtnAry;
	}

	public void setRtnAry(List<ARoundVO> rtnAry) {
		this.rtnAry = rtnAry;
	}

	public int getNaili() {
		return naili;
	}

	public void setNaili(int naili) {
		this.naili = naili;
	}

}
