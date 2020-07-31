package com.mdy.dzs.game.domain.activity.roulettegame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 皇宫探宝活动设置模型
 * @author 白雪林
 *
 */
public class Roulette implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;

	public static final int 配置开启	= 1;
	public static final int 配置关闭 	= 0;
	
	public static final int 充值返利		= 1;
	public static final int 消费返利 		= 2;
	public static final int 直接购买次数	= 3;
	
	public static final int 已获得次数无上限	= 0;

	/**活动类型（1-充值返利；2-消费返利；3-直接购买次数）*/
	private int type;
	/**单价*/
	private int price;
	/**次数上限*/
	private int limitCnt;
	/**每日免费次数*/
	private int freeCnt;
	/**积分奖励分值*/
	private List<Integer> score;
	/**第一档积分奖励类型*/
	private List<Integer> rewardType1;
	/**第一档积分奖励Id*/
	private List<Integer> rewardId1;
	/**第一档积分奖励数量*/
	private List<Integer> rewardCnt1;
	/**第二档积分奖励类型*/
	private List<Integer> rewardType2;
	/**第二档积分奖励Id*/
	private List<Integer> rewardId2;
	/**第二档积分奖励数量*/
	private List<Integer> rewardCnt2;
	/**第三档积分奖励类型*/
	private List<Integer> rewardType3;
	/**第三档积分奖励Id*/
	private List<Integer> rewardId3;
	/**第三档积分奖励数量*/
	private List<Integer> rewardCnt3;
	/**月卡获得元宝*/
	private int yueka;
	/**圆盘position map*/
	private Map<String, Integer> positionMap;
	
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getPrice(){
		return this.price;
	}
	public void setPrice(int price){
		this.price=price;
	}
	public int getLimitCnt(){
		return this.limitCnt;
	}
	public void setLimitCnt(int limitCnt){
		this.limitCnt=limitCnt;
	}
	public int getFreeCnt(){
		return this.freeCnt;
	}
	public void setFreeCnt(int freeCnt){
		this.freeCnt=freeCnt;
	}
	public List<Integer> getRewardType1(){
		return this.rewardType1;
	}
	public void setRewardType1(List<Integer> rewardType1){
		this.rewardType1=rewardType1;
	}
	public List<Integer> getRewardId1(){
		return this.rewardId1;
	}
	public void setRewardId1(List<Integer> rewardId1){
		this.rewardId1=rewardId1;
	}
	public List<Integer> getRewardCnt1(){
		return this.rewardCnt1;
	}
	public void setRewardCnt1(List<Integer> rewardCnt1){
		this.rewardCnt1=rewardCnt1;
	}
	public List<Integer> getRewardType2(){
		return this.rewardType2;
	}
	public void setRewardType2(List<Integer> rewardType2){
		this.rewardType2=rewardType2;
	}
	public List<Integer> getRewardId2(){
		return this.rewardId2;
	}
	public void setRewardId2(List<Integer> rewardId2){
		this.rewardId2=rewardId2;
	}
	public List<Integer> getRewardCnt2(){
		return this.rewardCnt2;
	}
	public void setRewardCnt2(List<Integer> rewardCnt2){
		this.rewardCnt2=rewardCnt2;
	}
	public List<Integer> getRewardType3(){
		return this.rewardType3;
	}
	public void setRewardType3(List<Integer> rewardType3){
		this.rewardType3=rewardType3;
		
	}
	public List<Integer> getRewardId3(){
		return this.rewardId3;
	}
	public void setRewardId3(List<Integer> rewardId3){
		this.rewardId3=rewardId3;
	}
	public List<Integer> getRewardCnt3(){
		return this.rewardCnt3;
	}
	public void setRewardCnt3(List<Integer> rewardCnt3){
		this.rewardCnt3=rewardCnt3;
	}
	public List<Integer> getScore() {
		return score;
	}
	public void setScore(List<Integer> score) {
		this.score = score;
	}	
	public List<Integer> getRewardTypeByIndex(int i){
		List<Integer> typeList = new ArrayList<Integer>();
		switch(i){
			case 1:
				typeList = this.rewardType1;
				break;
			case 2:
				typeList = this.rewardType2;
				break;
			case 3:
				typeList = this.rewardType3;
				break;
		}
		return typeList;
	}
	public List<Integer> getRewardIdByIndex(int i){
		List<Integer> idList = new ArrayList<Integer>();
		switch(i){
			case 1:
				idList = this.rewardId1;
				break;
			case 2:
				idList = this.rewardId2;
				break;
			case 3:
				idList = this.rewardId3;
				break;
		}
		return idList;
	}
	public List<Integer> getRewardCntByIndex(int i){
		List<Integer> cntList = new ArrayList<Integer>();
		switch(i){
			case 1:
				cntList = this.rewardCnt1;
				break;
			case 2:
				cntList = this.rewardCnt2;
				break;
			case 3:
				cntList = this.rewardCnt3;
				break;
		}
		return cntList;
	}
	public int getYueka() {
		return yueka;
	}
	public void setYueka(int yueka) {
		this.yueka = yueka;
	}
	public Map<String, Integer> getPositionMap() {
		return positionMap;
	}
	public void setPositionMap(Map<String, Integer> positionMap) {
		this.positionMap = positionMap;
	}
}

