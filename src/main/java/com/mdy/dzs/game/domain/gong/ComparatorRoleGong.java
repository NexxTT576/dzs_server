package com.mdy.dzs.game.domain.gong;

import java.util.Comparator;

public class ComparatorRoleGong implements Comparator<Object> {
	
	
	@Override
	public int compare(Object o1, Object o2) {
		RoleGong item1 = (RoleGong)o1;
		RoleGong item2 = (RoleGong)o2;
		
		if((item1.getPos()!=item2.getPos()) && (item1.getPos() == 0 || item2.getPos() ==0)){
			return -(item1.getPos()-item2.getPos());
		} 
		else if(item1.getOrder() != item2.getOrder() && (item1.getOrder()>100||item2.getOrder()>100))
			return item1.getOrder()-item2.getOrder();
		else if(item1.getStar() != item2.getStar())
			return item2.getStar()-item1.getStar();
		else if(item1.getSubpos() != item2.getSubpos())
			return item1.getSubpos()-item2.getSubpos();
		else if(item1.getOrder() != item2.getOrder())
			return item1.getOrder()-item2.getOrder();
//		else if(item1.getLevel() != item2.getLevel())
//			return item2.getLevel()- item1.getLevel();
		else if( (item1.getPos()!=item2.getPos()) && (item1.getPos() > 0 || item2.getPos() > 0) )
			return (item1.getPos()-item2.getPos());
		return 0;
	}


	public static void main(String[] args) {
		RoleGong test1 = new RoleGong();
		test1.setPos(2);
		test1.setSubpos(5);
		
		RoleGong test2 = new RoleGong();
		test2.setPos(3);
		test2.setSubpos(5);
		
		new ComparatorRoleGong().compare(test1, test2);
	}
}
