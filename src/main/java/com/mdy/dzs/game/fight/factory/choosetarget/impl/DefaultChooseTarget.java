/**
 * 
 */
package com.mdy.dzs.game.fight.factory.choosetarget.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.fight.factory.choosetarget.ChooseTarget;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

/**
 * 返回默认目标
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月25日 下午8:46:05
 */
public class DefaultChooseTarget implements ChooseTarget {

	protected List<Fighter> res;
	protected int curSide;
	protected int curPos;
	protected Map<Integer, Fighter> self;
	protected Map<Integer, Fighter> target;
	/** 前排对应pos */
	protected int side1pos;
	/** 后排对应pos */
	protected int side2pos;

	@Override
	public List<Fighter> chooseTarget(FightMain main, Fighter fighter, int param) {
		preChoose(main, fighter, param);
		int pos = defaultPos();
		res.add(target.get(pos));
		return res;
	}

	/**
	 * 预处理
	 * 
	 * @param main
	 * @param fighter
	 */
	protected void preChoose(FightMain main, Fighter fighter, int side) {
		res = new ArrayList<Fighter>();
		curSide = fighter.getSide();
		curPos = fighter.getPos();
		self = (curSide == 1) ? main.getF1() : main.getF2();
		target = (curSide == 1) ? main.getF2() : main.getF1();
		if (side == 1)
			target = self;
		side1pos = (curPos - 1) % 3 + 1;
		side2pos = (curPos - 1) % 3 + 4;
	}

	protected List<Fighter> endChoose() {
		if (res.size() == 0) {
			res.add(target.get(defaultPos()));
		}
		return res;
	}

	/**
	 * 选出默认pos
	 * 
	 * @return
	 */
	protected int defaultPos() {
		// samePos
		int tPos = -1;
		if (!isTargetDead(side1pos)) {
			tPos = side1pos;
		} else {
			int[] lineSeq = { 1, 2, 3 };
			// 从前排找目标
			for (int i : lineSeq) {
				if (isTargetDead(i))
					continue;
				tPos = i;
				break;
			}
			// 如果前排没人 找后排对应位置的
			if (isTargetDead(tPos)) {
				tPos = side2pos;
			}
			// 如果后排对应位置没有人 那么从编号小的开始找
			if (isTargetDead(tPos)) {
				int[] lineSeqN = { 4, 5, 6 };
				for (int i : lineSeqN) {
					if (isTargetDead(i))
						continue;
					tPos = i;
					break;
				}
			}
		}
		return tPos;
	}

	protected boolean isTargetDead(int pos) {
		return target.get(pos) == null || target.get(pos).isDead();
	}

	protected int getBackSingle() {
		int tPos = -1;
		int[] lineSeqN = { 4, 5, 6 };
		for (int i : lineSeqN) {
			if (isTargetDead(i))
				continue;
			tPos = i;
			break;
		}
		// 如果后排没人 找前排对应位置的
		if (isTargetDead(tPos)) {
			tPos = side1pos;
		}

		int[] lineSeq = { 1, 2, 3 };
		// 从前排找目标
		if (isTargetDead(tPos)) {
			for (int i : lineSeq) {
				if (isTargetDead(i))
					continue;
				tPos = i;
				break;
			}
		}
		return tPos;
	}
}
