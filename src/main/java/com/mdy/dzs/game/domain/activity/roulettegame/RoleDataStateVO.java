package com.mdy.dzs.game.domain.activity.roulettegame;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoleDataStateVO implements Serializable{
	/*roleDataState:
	{	
		startT:	开始时间
		endT：	结束时间
		countDown:  倒计时秒
		credit：	当前积分
		surTimes:	剩余次数
		getBox:		已领取的箱子	[1,2,3]
		freeTimes：	免费次数
		dayAdd；	今日累积
		rouletteTimes：已获得的探宝次数
	}*/
	
	private static final long serialVersionUID = 1L;
	private Date startT;
	private Date endT;
	private long countDown;
	private int credit;
	private int surTimes;
	private List<Integer> getBox;
	private int freeTimes;
	private int dayAdd;
	private int rouletteTimes;
	
	public RoleDataStateVO(Date startT,Date endT,long countDown,int credit,int surTimes,List<Integer> getBox,int freeTimes
			,int dayAdd,int rouletteTimes){
		this.startT			= startT;
		this.endT			= endT;
		this.countDown 		= countDown;
		this.credit 		= credit;
		this.surTimes 		= surTimes;
		this.getBox 		= getBox;
		this.freeTimes 		= freeTimes;
		this.dayAdd 		= dayAdd;
		this.rouletteTimes 	= rouletteTimes;
	}
	
	public long getCountDown() {
		return countDown;
	}
	public void setCountDown(long countDown) {
		this.countDown = countDown;
	}
	public List<Integer> getGetBox() {
		return getBox;
	}
	public void setGetBox(List<Integer> getBox) {
		this.getBox = getBox;
	}
	public int getSurTimes() {
		return surTimes;
	}
	public void setSurTimes(int surTimes) {
		this.surTimes = surTimes;
	}
	public int getRouletteTimes() {
		return rouletteTimes;
	}
	public void setRouletteTimes(int rouletteTimes) {
		this.rouletteTimes = rouletteTimes;
	}
	public int getFreeTimes() {
		return freeTimes;
	}
	public void setFreeTimes(int freeTimes) {
		this.freeTimes = freeTimes;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getDayAdd() {
		return dayAdd;
	}
	public void setDayAdd(int dayAdd) {
		this.dayAdd = dayAdd;
	}

	public Date getStartT() {
		return startT;
	}

	public void setStartT(Date startT) {
		this.startT = startT;
	}

	public Date getEndT() {
		return endT;
	}

	public void setEndT(Date endT) {
		this.endT = endT;
	}
	
	
}
