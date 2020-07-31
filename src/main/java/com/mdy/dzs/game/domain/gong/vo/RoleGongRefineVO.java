/**
 * 
 */
package com.mdy.dzs.game.domain.gong.vo;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.item.ConsumesItem;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.role.UpdateInfoVO;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年3月16日  下午3:53:35
 */
public class RoleGongRefineVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**是否允许*/
	private int allow;
	private int cnt;
	/***/
	private int max;
	/**花费道具*/
	private List<ConsumesItem> items;
	/**花费银币*/
	private int silver;
	/**增加属性*/
	private Prop addProperty;
	/**更新信息*/
	private UpdateInfoVO update;

	public int getAllow() {
		return allow;
	}

	public void setAllow(int allow) {
		this.allow = allow;
	}

	public List<ConsumesItem> getItems() {
		return items;
	}

	public void setItems(List<ConsumesItem> items) {
		this.items = items;
	}

	public int getSilver() {
		return silver;
	}

	public void setSilver(int silver) {
		this.silver = silver;
	}

	public Prop getAddProperty() {
		return addProperty;
	}

	public void setAddProperty(Prop addProperty) {
		this.addProperty = addProperty;
	}

	public UpdateInfoVO getUpdate() {
		return update;
	}

	public void setUpdate(UpdateInfoVO update) {
		this.update = update;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
