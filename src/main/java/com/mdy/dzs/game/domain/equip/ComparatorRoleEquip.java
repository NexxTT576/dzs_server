package com.mdy.dzs.game.domain.equip;

import java.util.Comparator;

public class ComparatorRoleEquip implements Comparator<Object> {
	
	private int sortAry[] = {5,3,1,4,2};
	
	@Override
	public int compare(Object o1, Object o2) {
		Equip item1 = (Equip)o1;
		Equip item2 = (Equip)o2;
	
		if((item1.getPos()!=item2.getPos()) && (item1.getPos() == 0 || item2.getPos() ==0)){
			return -(item1.getPos()-item2.getPos());
		} 
		else if(item1.getStar() != item2.getStar())
			return item2.getStar()-item1.getStar();
		else if(sortAry[item1.getSubpos()] != sortAry[item2.getSubpos()])
			return sortAry[item1.getSubpos()]-sortAry[item2.getSubpos()];
		else if( item1.getLevel() != item2.getLevel() )
			return item2.getLevel() - item1.getLevel();
		else if( (item1.getPos()!=item2.getPos()) && (item1.getPos() > 0 || item2.getPos() > 0) )
			return (item1.getPos()-item2.getPos());
		
		return 0;
	}


}
