package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 15 16
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月26日  下午9:06:24
 */
public class AngerChooseTarget extends DefaultChooseTarget {

	private boolean higher;
	public AngerChooseTarget(boolean higher) {
		this.higher = higher;
	}
	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter, int param) {
		preChoose(main, fighter,param);
		int val = higher?0:100;
		Fighter select = null;
		for (Fighter tgt : target.values()) {
			if(tgt == null || tgt.isDead()) continue;
			if(higher){
				if(tgt.getAngerVal()>val){
					select = tgt;
					val = tgt.getAngerVal();
				}
			}else{
				if(tgt.getAngerVal()<val){
					select = tgt;
					val = tgt.getAngerVal();
				}
			}
		}
		if(select != null) res.add(select);
		return endChoose();
	}
}
