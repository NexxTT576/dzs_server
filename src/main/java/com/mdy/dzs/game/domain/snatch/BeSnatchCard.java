/**
 * 
 */
package com.mdy.dzs.game.domain.snatch;

import java.io.Serializable;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.game.domain.card.RoleCard;

/**
 * 被挑战人的卡片
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月13日  下午2:41:38
 */
public class BeSnatchCard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int resId;
	private int star;
	private Integer sex;
	
	public BeSnatchCard() {
		
	}
	public BeSnatchCard(Card card) {
		sex = card.getSex();
		resId = card.getId();
		star = card.getStar().get(0);
	}
	
	public BeSnatchCard(RoleCard rcard) {
		name = rcard.getName();
		resId = rcard.getResId();
		star = rcard.getStar();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	
}
