package com.mdy.dzs.game.domain.activity.limitcard;

import java.io.Serializable;
import java.util.List;

public class LimitCardConfigBean implements Serializable
{
	private static final long serialVersionUID = -2351705310594105589L;
	
	//主卡
	private int card1;
	
	//副卡1
	private int card2;
	
	//副卡2
	private int card3;
	
	//第1名奖励
	private List<Integer> reward1;
	
	//第2-3名奖励
	private List<Integer> reward2;
	
	//第4-20名奖励
	private List<Integer> reward3;
	
	//第21-50名奖励
	private List<Integer> reward4;
	
	//基础幸运值prob
	private String baseLuckProb;
	
	//额外幸运值prob
	private String extLuckyProb;
	
	//1--100次抽取对应主卡随机率
	private List<Integer> roadm;

	public List<Integer> getRoadm() {
		return roadm;
	}

	public void setRoadm(List<Integer> roadm) {
		this.roadm = roadm;
	}

	public int getCard1() {
		return card1;
	}

	public void setCard1(int card1) {
		this.card1 = card1;
	}

	public int getCard2() {
		return card2;
	}

	public void setCard2(int card2) {
		this.card2 = card2;
	}

	public int getCard3() {
		return card3;
	}

	public void setCard3(int card3) {
		this.card3 = card3;
	}

	public List<Integer> getReward1() {
		return reward1;
	}

	public void setReward1(List<Integer> reward1) {
		this.reward1 = reward1;
	}

	public List<Integer> getReward2() {
		return reward2;
	}

	public void setReward2(List<Integer> reward2) {
		this.reward2 = reward2;
	}

	public List<Integer> getReward3() {
		return reward3;
	}

	public void setReward3(List<Integer> reward3) {
		this.reward3 = reward3;
	}

	public List<Integer> getReward4() {
		return reward4;
	}

	public void setReward4(List<Integer> reward4) {
		this.reward4 = reward4;
	}

	public String getBaseLuckProb() {
		return baseLuckProb;
	}

	public void setBaseLuckProb(String baseLuckProb) {
		this.baseLuckProb = baseLuckProb;
	}

	public String getExtLuckyProb() {
		return extLuckyProb;
	}

	public void setExtLuckyProb(String extLuckyProb) {
		this.extLuckyProb = extLuckyProb;
	}
}
