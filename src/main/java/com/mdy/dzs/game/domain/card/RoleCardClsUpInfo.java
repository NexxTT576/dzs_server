package com.mdy.dzs.game.domain.card;

import java.util.List;

import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.sharp.container.biz.BizException;

/**
 * 卡片升阶信息
 * {
 * 'err':reErr ,'itemInfos':gItemInfoAry , 
 * 'cost':gCostSilver ,'items':gItems , 
 * 'gongs':gGongs , 'cards':gCards
 * };
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月21日  上午11:42:36
 */
public class RoleCardClsUpInfo {
	private BizException err;
	private List<RoleCardClsUpItemInfo> itemInfos;
	private int cost;
	private List<RoleCardClsUpItem> items;
	private List<RoleGong> gongs;
	private List<Integer> cards;
	
	public BizException getErr() {
		return err;
	}
	public void setErr(BizException err) {
		this.err = err;
	}
	public List<RoleCardClsUpItemInfo> getItemInfos() {
		return itemInfos;
	}
	public void setItemInfos(List<RoleCardClsUpItemInfo> itemInfos) {
		this.itemInfos = itemInfos;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public List<RoleCardClsUpItem> getItems() {
		return items;
	}
	public void setItems(List<RoleCardClsUpItem> items) {
		this.items = items;
	}
	public List<RoleGong> getGongs() {
		return gongs;
	}
	public void setGongs(List<RoleGong> gongs) {
		this.gongs = gongs;
	}
	public List<Integer> getCards() {
		return cards;
	}
	public void setCards(List<Integer> cards) {
		this.cards = cards;
	}
	
	
	
}
