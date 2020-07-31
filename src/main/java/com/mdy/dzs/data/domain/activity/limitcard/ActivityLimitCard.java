package com.mdy.dzs.data.domain.activity.limitcard;

import java.io.Serializable;

public class ActivityLimitCard implements Serializable
{
	private static final long serialVersionUID = -5561241219167511052L;
	private int id;
	
	//积分奖励条件
	private int scoreCond;
	
	//额外幸运值
	private int extLucky;
	
	//积分奖励
	private String scoreReward;
	
	//幸运值最大值
	private int maxLucky;
	
	//活动排行榜人数
	private int maxPlayer;
	
	//元宝抽取花费
	private int goldCost;
	
	//奖励4 发奖内容
	private String reward4;
	
	//排名统计人数
	private int maxRank;
	
	//card2概率
	private int card2Prop;
	
	//card3概率
	private int card3Prop;

	public int getCard2Prop() {
		return card2Prop;
	}

	public void setCard2Prop(int card2Prop) {
		this.card2Prop = card2Prop;
	}

	public int getCard3Prop() {
		return card3Prop;
	}

	public void setCard3Prop(int card3Prop) {
		this.card3Prop = card3Prop;
	}

	public int getMaxRank() {
		return maxRank;
	}

	public void setMaxRank(int maxRank) {
		this.maxRank = maxRank;
	}

	public String getReward4() {
		return reward4;
	}

	public void setReward4(String reward4) {
		this.reward4 = reward4;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExtLucky() {
		return extLucky;
	}

	public void setExtLucky(int extLucky) {
		this.extLucky = extLucky;
	}

	public int getScoreCond() {
		return scoreCond;
	}

	public void setScoreCond(int scoreCond) {
		this.scoreCond = scoreCond;
	}

	public String getScoreReward() {
		return scoreReward;
	}

	public void setScoreReward(String scoreReward) {
		this.scoreReward = scoreReward;
	}

	public int getMaxLucky() {
		return maxLucky;
	}

	public void setMaxLucky(int maxLucky) {
		this.maxLucky = maxLucky;
	}

	public int getMaxPlayer() {
		return maxPlayer;
	}

	public void setMaxPlayer(int maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	public int getGoldCost() {
		return goldCost;
	}

	public void setGoldCost(int goldCost) {
		this.goldCost = goldCost;
	}
}
