package com.mdy.dzs.game.domain.item;

import java.util.Comparator;
import java.util.Map;

import com.mdy.dzs.data.domain.item.Item;

public class ComparatorBagItem implements Comparator<Object> {
	
	
	private Map<Integer, Item> map;
	
	public ComparatorBagItem(Map<Integer, Item> map){
		this.map = map;
	}
	@Override
	public int compare(Object o1, Object o2) {
		RoleItem item1 = (RoleItem)o1;
		RoleItem item2 = (RoleItem)o2;
		return map.get(item1.getItemId()).getOrder() - map.get(item2.getItemId()).getOrder();
	}


}
