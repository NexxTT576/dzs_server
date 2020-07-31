package com.mdy.dzs.data.domain.equipment;

import java.io.Serializable;
import java.util.List;

/**
 * 装备套装模型
 * 
 * @author 房曈
 *
 */
public class EquipSuit implements Serializable {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	/** 套装id */
	private int id;
	/** 成员id组 */
	private List<Integer> member;
	/** 2件属性id组 */
	private List<Integer> nature1;
	/** 2件属性值数组 */
	private List<Integer> num1;
	/** 3件属性id组 */
	private List<Integer> nature2;
	/** 3件属性值数组 */
	private List<Integer> num2;
	/** 4件属性id组 */
	private List<Integer> nature3;
	/** 4件属性值数组 */
	private List<Integer> num3;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getMember() {
		return this.member;
	}

	public void setMember(List<Integer> member) {
		this.member = member;
	}

	public List<Integer> getNature1() {
		return this.nature1;
	}

	public void setNature1(List<Integer> nature1) {
		this.nature1 = nature1;
	}

	public List<Integer> getNum1() {
		return this.num1;
	}

	public void setNum1(List<Integer> num1) {
		this.num1 = num1;
	}

	public List<Integer> getNature2() {
		return this.nature2;
	}

	public void setNature2(List<Integer> nature2) {
		this.nature2 = nature2;
	}

	public List<Integer> getNum2() {
		return this.num2;
	}

	public void setNum2(List<Integer> num2) {
		this.num2 = num2;
	}

	public List<Integer> getNature3() {
		return this.nature3;
	}

	public void setNature3(List<Integer> nature3) {
		this.nature3 = nature3;
	}

	public List<Integer> getNum3() {
		return this.num3;
	}

	public void setNum3(List<Integer> num3) {
		this.num3 = num3;
	}
}