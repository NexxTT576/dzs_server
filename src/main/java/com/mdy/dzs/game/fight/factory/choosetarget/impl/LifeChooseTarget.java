package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.List;

import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * 15 16
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月26日  下午9:06:24
 */
public class LifeChooseTarget extends DefaultChooseTarget {

	final static Logger logger=LoggerFactory.get(LifeChooseTarget.class);
	
	private boolean higher;
	public LifeChooseTarget(boolean higher) {
		this.higher = higher;
	}
	/**
	 * 比较生命比例
	 */
	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter, int param) {
		preChoose(main, fighter,param);
		double val = higher?0:1000000;
		int curLife = higher?0:1000000;
		Fighter select = null;
		for (Fighter tgt : target.values()) {
			if(tgt == null || tgt.isDead()) continue;
//			logger.error("比例："+tgt.getPos()+":  "+tgt.getLife() +" / "+ tgt.getInitLife());
//			logger.error(val);
			double lifeRate = (tgt.getLife()*1.0)/(tgt.getInitLife()*1.0);
			if(higher){
				if(lifeRate>val){
					select = tgt;
					val = lifeRate;
					curLife = tgt.getLife();
				}else if(lifeRate == val){
					if( tgt.getLife()>curLife ){
						select = tgt;
						curLife = tgt.getLife();
					}
				}
			}else{
				if(lifeRate<val){
					select = tgt;
					val = lifeRate;
					curLife = tgt.getLife();
				}else if(lifeRate == val){
					if( tgt.getLife()<curLife ){
						select = tgt;
						curLife = tgt.getLife();
					}
				}
			}
		}
//		logger.error(select.getPos());
		if(select != null) res.add(select);
		return endChoose();
	}
}
