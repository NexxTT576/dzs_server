/**
 * 
 */
package com.mdy.dzs.game.domain.battle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 世界副本信息
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日 上午11:37:27
 */
public class BattleWorldVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 当前最大地图id
	 */
	private int battleWorldID;
	/**
	 * 当前最大副本id
	 */
	private int battleFieldID;
	/**
	 * 当前最大关卡id
	 */
	private int battleLvID;
	/**
	 * 当前副本id,已获得总星数
	 */
	private Map<Integer, Integer> fieldStarAry;

	public int getBattleWorldID() {
		return battleWorldID;
	}

	public void setBattleWorldID(int battleWorldID) {
		this.battleWorldID = battleWorldID;
	}

	public int getBattleFieldID() {
		return battleFieldID;
	}

	public void setBattleFieldID(int battleFieldID) {
		this.battleFieldID = battleFieldID;
	}

	public int getBattleLvID() {
		return battleLvID;
	}

	public void setBattleLvID(int battleLvID) {
		this.battleLvID = battleLvID;
	}

	public Map<Integer, Integer> getFieldStarAry() {
		return fieldStarAry;
	}

	public void setFieldStarAry(Map<Integer, Integer> fieldStarAry) {
		this.fieldStarAry = fieldStarAry;
	}

	public List<Serializable> toSerializable() {
		return Arrays.asList(battleWorldID, battleFieldID, battleLvID, (Serializable) fieldStarAry);
	}
}
