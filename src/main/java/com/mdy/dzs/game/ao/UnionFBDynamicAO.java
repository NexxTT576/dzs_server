package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.union.UnionFBDynamic;

public class UnionFBDynamicAO extends BaseAO {
	
	
	public List<UnionFBDynamic> queryUnionFBDynamicByFBId(int unionId,int type,int FBId){
		
		return unionFBDynamicDAO().queryUnionFBDynamicByFBId(unionId,type,FBId);
	}

}
