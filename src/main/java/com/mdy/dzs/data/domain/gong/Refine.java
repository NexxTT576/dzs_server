package com.mdy.dzs.data.domain.gong;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;

/**
 * 内外功模型
 * 
 * @author 房曈
 *
 */
public class Refine implements Serializable {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	/** id */
	private int id;
	/** 内外功id */
	private int Kungfu;
	/** 解锁等级组 */
	private List<Integer> arrLevel;
	/** 解锁属性id组 */
	private List<Integer> arrNature1;
	/** 解锁属性值 */
	private List<Integer> arrValue1;
	/** 是否可精炼 */
	private int Refine;
	/** 精炼消耗物品 */
	private List<List<ProbItem>> arrItem;
	/** 消耗银币组（第1层；第2层；。。第5层） */
	private List<Integer> arrSilver;
	/** 提升属性id组(位置1；位置2；。。位置5） */
	private List<Integer> arrNature2;
	/** 属性数值组(位置1；位置2；。。位置5） */
	private List<Integer> arrValue2;
	/** 羁绊侠客id（1-主角，其他对应card表id） */
	private List<Integer> arrCard;
	/** 羁绊id */
	private List<Integer> arrJiban;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKungfu() {
		return this.Kungfu;
	}

	public void setKungfu(int Kungfu) {
		this.Kungfu = Kungfu;
	}

	public List<Integer> getArrLevel() {
		return this.arrLevel;
	}

	public void setArrLevel(List<Integer> arrLevel) {
		this.arrLevel = arrLevel;
	}

	public List<Integer> getArrNature1() {
		return this.arrNature1;
	}

	public void setArrNature1(List<Integer> arrNature1) {
		this.arrNature1 = arrNature1;
	}

	public List<Integer> getArrValue1() {
		return this.arrValue1;
	}

	public void setArrValue1(List<Integer> arrValue1) {
		this.arrValue1 = arrValue1;
	}

	public int getRefine() {
		return this.Refine;
	}

	public void setRefine(int Refine) {
		this.Refine = Refine;
	}

	public List<List<ProbItem>> getArrItem() {
		return this.arrItem;
	}

	public void setArrItem(List<List<ProbItem>> arrItem) {
		this.arrItem = arrItem;
	}

	public List<Integer> getArrSilver() {
		return this.arrSilver;
	}

	public void setArrSilver(List<Integer> arrSilver) {
		this.arrSilver = arrSilver;
	}

	public List<Integer> getArrNature2() {
		return this.arrNature2;
	}

	public void setArrNature2(List<Integer> arrNature2) {
		this.arrNature2 = arrNature2;
	}

	public List<Integer> getArrValue2() {
		return this.arrValue2;
	}

	public void setArrValue2(List<Integer> arrValue2) {
		this.arrValue2 = arrValue2;
	}

	public List<Integer> getArrCard() {
		return this.arrCard;
	}

	public void setArrCard(List<Integer> arrCard) {
		this.arrCard = arrCard;
	}

	public List<Integer> getArrJiban() {
		return this.arrJiban;
	}

	public void setArrJiban(List<Integer> arrJiban) {
		this.arrJiban = arrJiban;
	}
}