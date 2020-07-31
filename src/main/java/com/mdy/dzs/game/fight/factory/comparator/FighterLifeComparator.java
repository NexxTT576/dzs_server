/**
 * 
 */
package com.mdy.dzs.game.fight.factory.comparator;

import java.util.Comparator;

import com.mdy.dzs.game.fight.main.Fighter;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月30日  下午5:51:51
 */
public class FighterLifeComparator implements Comparator<Fighter> {

	@Override
	public int compare(Fighter arg0, Fighter arg1) {
		return arg0.getLife() - arg1.getLife();
	}

}
