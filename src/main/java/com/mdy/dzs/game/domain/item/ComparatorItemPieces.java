package com.mdy.dzs.game.domain.item;

import java.util.Comparator;
import java.util.Map;

import com.mdy.dzs.data.domain.item.Item;

public class ComparatorItemPieces implements Comparator<RoleItemVO> {

	private Map<Integer, Item> map;

	public ComparatorItemPieces(Map<Integer, Item> map) {
		this.map = map;
	}

	@Override
	public int compare(RoleItemVO item1, RoleItemVO item2) {

		Item item1Data = map.get(item1.getItemId());
		Item item2Data = map.get(item2.getItemId());

		if (item1Data.getQuality() == item2Data.getQuality()) {
			return item2.getItemCnt() - item1.getItemCnt();
		} else
			return item2Data.getQuality() - item1Data.getQuality();

	}

}
