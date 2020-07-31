/**
 * 
 */
package com.mdy.dzs.game.fight.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mdy.dzs.game.domain.Prop;

/**
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月14日 上午11:59:08
 */
public class FighterInfo {

	/** 卡片/基础卡列表 */
	private List<Object> cards;
	/** 增加属性列表 */
	private List<List<Prop>> props;
	/** 主角位置 */
	private int pos;
	/** 禁止别人给自己上buff */
	private boolean forbitBuff = false;
	/** 战斗力 */
	private int attrack;

	public FighterInfo() {
		cards = Arrays.asList(null, null, null, null, null, null);
		props = Arrays.asList(null, null, null, null, null, null);
		pos = 0;
	}

	public List<Object> getCards() {
		return cards;
	}

	public void setCard(int pos, Object card) {
		cards.set(pos, card);
	}

	public List<List<Prop>> getProps() {
		return props;
	}

	public void addProp(int pos, Prop prop) {
		if (props.get(pos) == null) {
			props.set(pos, new ArrayList<Prop>());
		}
		List<Prop> list = props.get(pos);
		list.add(prop);
	}

	public void addProps(int pos, List<Prop> vals) {
		if (props.get(pos) == null) {
			props.set(pos, new ArrayList<Prop>());
		}
		List<Prop> list = props.get(pos);
		list.addAll(vals);
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public boolean isForbitBuff() {
		return forbitBuff;
	}

	public void setForbitBuff(boolean forbitBuff) {
		this.forbitBuff = forbitBuff;
	}

	public int getAttrack() {
		return attrack;
	}

	public void setAttrack(int attrack) {
		this.attrack = attrack;
	}

}
