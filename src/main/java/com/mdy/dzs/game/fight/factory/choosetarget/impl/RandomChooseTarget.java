/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**5	随机单体
 * 10	随机3个
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月28日  下午5:13:10
 */
public class RandomChooseTarget extends DefaultChooseTarget {
	private int num = 1;
	public RandomChooseTarget(int num) {
		this.num = num;
	}
	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter,int param) {
		preChoose(main, fighter,param);
		List<Fighter> list = new ArrayList<Fighter>();
		for (Fighter tgt : target.values()) {
			if(tgt != null & !tgt.isDead()){
				list.add(tgt);
			}
		}
		while(res.size()<num && list.size() != 0){
			int index= (int)Math.round(Math.random()*(list.size()-1));
			res.add(list.get(index));
			list.remove(index);
		}
		return res;
	}
}
