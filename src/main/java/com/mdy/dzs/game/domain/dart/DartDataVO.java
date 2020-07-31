package com.mdy.dzs.game.domain.dart;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.gift.GiftItem;

public class DartDataVO implements Serializable{
	/*
		{	
				dartState: 查看的镖车状态 	【1-正常 2-已押镖完毕】
				dartData:
				{
					lv:			玩家等级
					name：		玩家名字
					attack：	玩家战力
					quality：	镖车品质
					beRobTimes：被抢次数
					getCoin：	获得奖励
							[
							{type:*, id:*, num:*}
							,...
							]	
					cardData：[
								{resId:*,
								 cls:*
								},...
							]
				}
			}
	*/
	private static final long serialVersionUID = 1L;
	
	private int lv;
	private String name;
	private int attack;
	private int quality;
	private int beRobTimes;
	private List<GiftItem> getCoin;
	private List<DartLineUpVO> cardData;
	
	public DartDataVO(){
		
	}
	
	public DartDataVO(int lv, String name, int attack, int quality,int beRobTimes, List<GiftItem> getCoin, List<DartLineUpVO> cardData){
		this.lv = lv;
		this.name = name;
		this.attack = attack;
		this.quality = quality;
		this.beRobTimes = beRobTimes;
		this.getCoin = getCoin;
		this.cardData = cardData;
	}
	
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getBeRobTimes() {
		return beRobTimes;
	}
	public void setBeRobTimes(int beRobTimes) {
		this.beRobTimes = beRobTimes;
	}
	public List<DartLineUpVO> getCardData() {
		return cardData;
	}
	public void setCardData(List<DartLineUpVO> cardData) {
		this.cardData = cardData;
	}

	public List<GiftItem> getGetCoin() {
		return getCoin;
	}

	public void setGetCoin(List<GiftItem> getCoin) {
		this.getCoin = getCoin;
	}
	
}
