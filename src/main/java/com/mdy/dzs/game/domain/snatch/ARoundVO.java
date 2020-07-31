package com.mdy.dzs.game.domain.snatch;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;

public class ARoundVO implements Serializable{
/*	{
		probItem：	掉落物品
					{type:*,id:*,num:*}
		coinAry:	奖励货币类型
					[
						{t:*, id:*, n:*}
						...
					]
	}*/
	
	private static final long serialVersionUID = 1L;
	
	private ProbItem probItem;
	private List<ProbItem> coinAry;
	
	public ARoundVO(ProbItem probItem, List<ProbItem> coinAry){
		this.probItem = probItem;
		this.coinAry  = coinAry;
	}
	
	public ProbItem getProbItem() {
		return probItem;
	}
	public void setProbItem(ProbItem probItem) {
		this.probItem = probItem;
	}
	public List<ProbItem> getCoinAry() {
		return coinAry;
	}
	public void setCoinAry(List<ProbItem> coinAry) {
		this.coinAry = coinAry;
	}
	
}
