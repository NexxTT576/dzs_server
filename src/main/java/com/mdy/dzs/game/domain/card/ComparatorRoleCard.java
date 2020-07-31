package com.mdy.dzs.game.domain.card;

import java.util.Comparator;
import java.util.List;

public class ComparatorRoleCard implements Comparator<Object> {

	private int mainCid;
	private List<Integer> fmtCardAry;
	public ComparatorRoleCard(int mid,List<Integer> fmtCardAry) {
		mainCid = mid;
		this.fmtCardAry = fmtCardAry;
	}
	
	@Override
	public int compare(Object o1,Object o2) {
		RoleCard item1 = (RoleCard)o1;
		RoleCard item2 = (RoleCard)o2;
		if(item1.id==mainCid)
			return -1;
		int index1 = fmtCardAry.indexOf(item1.id);
		int index2 = fmtCardAry.indexOf(item2.id);
		if(index1 == -1) index1 = Integer.MAX_VALUE;
		if(index2 == -1) index2 = Integer.MAX_VALUE;
		if(index1!=index2){
			return index1-index2;
		}
		if(item1.pos!=item2.pos){
			if(item1.pos>0 && item2.pos>0)
				return (item1.pos-item2.pos);
			else
				return -(item1.pos-item2.pos);
		} 
		else if((item1.relation.size() != item2.relation.size())){
			return item2.relation.size() - item1.relation.size();
		}else if(item1.star != item2.star){
			return item2.star-item1.star;
		}else if( item1.level != item2.level ){
			return item2.level - item1.level;
		}else if( item1.getResId() != item2.getResId() ){
			return item2.getResId() - item1.getResId();
		}
		return 0;
	}


}
