package com.mdy.dzs.game.filter;


/**
 * 排名过滤器
 * @author fangtong
 * 2012-12-11
 */
public class RankFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** 竞技场排名降序*/
	public final static int ORDER_BY_ARENA_DESC 	= 1;
	/** 等级排名降序 */
	public final static int ORDER_BY_GRADE_DESC 	= 2;
	/** 战斗力排名降序  */
	public final static int ORDER_BY_ATTACK_DESC 	= 3;
	/**副本星星排名降序 */
	public final static int ORDER_BY_STARS_DESC		= 4;
	
	/** 竞技场起始值 */
	private int startArenaRank;
	/** 等级排名起始值 */
	private int startGradeRank;
	/** 战斗力排名起始值 */
	private int startAttackRank;
	/** 副本星星排名起始值 */
	private int startStarsRank;
	
	
	public int getStartArenaRank() {
		return startArenaRank;
	}

	public void setStartArenaRank(int startArenaRank) {
		this.startArenaRank = startArenaRank;
	}

	public int getStartGradeRank() {
		return startGradeRank;
	}

	public void setStartGradeRank(int startGradeRank) {
		this.startGradeRank = startGradeRank;
	}

	public int getStartAttackRank() {
		return startAttackRank;
	}

	public void setStartAttackRank(int startAttackRank) {
		this.startAttackRank = startAttackRank;
	}

	public int getStartStarsRank() {
		return startStarsRank;
	}

	public void setStartStarsRank(int startStarsRank) {
		this.startStarsRank = startStarsRank;
	}

	@Override
	public String toString() {
		return "RankFilter ["+
			" startArenaRank=" + startArenaRank +
			" startGradeRank=" + startGradeRank +
			" startAttackRank=" + startAttackRank +
			" startStarsRank=" + startStarsRank +
		"]";
	}
}
