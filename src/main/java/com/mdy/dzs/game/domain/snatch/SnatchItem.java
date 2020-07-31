/**
 * 
 */
package com.mdy.dzs.game.domain.snatch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.mdy.dzs.data.domain.item.Item;

/**
 * 可争夺内/外功
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月13日  上午11:25:24
 */
public class SnatchItem implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**可合成出的内外功id*/
	private int id;
	/**可合成出的内外功*/
	private Item item;
	/**碎片id，已有数量*/
	private Map<Integer, Integer> items;
	
	public SnatchItem(Item item) {
		id = item.getId();
		this.item = item;
		items = new HashMap<Integer, Integer>();
		for (Integer id : item.getPara1()) {
			items.put(id, 0);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@JsonIgnore
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Map<Integer, Integer> getItems() {
		return items;
	}

	public void setItemCnt(int itemId,int cnt) {
		Integer oldCnt = items.get(itemId);
		if(oldCnt != null){
			items.put(itemId, cnt);
		}
	}
	
	
}
