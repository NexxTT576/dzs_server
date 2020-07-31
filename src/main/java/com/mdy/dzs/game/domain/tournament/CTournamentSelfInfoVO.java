package com.mdy.dzs.game.domain.tournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 个人信息
 * @author Administrator
 *
 */
public class CTournamentSelfInfoVO implements Serializable{
	/**序列化id*/
	private static final long serialVersionUID = 1L;
	public CTournamentSelfInfoVO(){
		opponents = new ArrayList<COpponentInfo>();
		top3 = new ArrayList<COpponentInfo>();
	}
	/**耐力值*/
	private int resisVal;
	/**挑战次数*/
	private int challengeTimes;
	/**排名*/
	private int rank;
	/**下次刷新时间*/
	private Date nextFleshTime;
	/**积分*/
	private int score;
	/**购买消耗*/
	private int cost;
	/**可购买次数*/
	private int buy_num;
	/**对手列表*/
	private List<COpponentInfo> opponents;
	/**对手列表*/
	private List<COpponentInfo> top3;
	
	public int getResisVal() {
		return resisVal;
	}
	public void setResisVal(int resisVal) {
		this.resisVal = resisVal;
	}
	public int getChallengeTimes() {
		return challengeTimes;
	}
	public void setChallengeTimes(int challengeTimes) {
		this.challengeTimes = challengeTimes;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Date getNextFleshTime() {
		return nextFleshTime;
	}
	public void setNextFleshTime(Date nextFleshTime) {
		this.nextFleshTime = nextFleshTime;
	}
	public List<COpponentInfo> getOpponents() {
		return opponents;
	}
	public void setOpponents(List<COpponentInfo> opponents) {
		this.opponents = opponents;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}
	public List<COpponentInfo> getTop3() {
		return top3;
	}
	public void setTop3(List<COpponentInfo> top3) {
		this.top3 = top3;
	}
}
