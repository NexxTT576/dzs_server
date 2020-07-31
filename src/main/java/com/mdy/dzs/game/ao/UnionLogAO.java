package com.mdy.dzs.game.ao;

import com.mdy.dzs.game.domain.union.UnionLog;

public class UnionLogAO extends BaseAO {
	//添加帮派log日志
	public void add(UnionLog unionLog){
		unionLogDAO().add(unionLog);
	}
}
