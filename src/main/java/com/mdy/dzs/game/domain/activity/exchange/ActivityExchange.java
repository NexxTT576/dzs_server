package com.mdy.dzs.game.domain.activity.exchange;

import java.io.Serializable;
import java.util.List;


/**
 * 限时兑换模型
 * @author zhou
 *
 */
public class ActivityExchange implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/**类型（1-3换1；2-4换1；3-5换1）*/
	private int type;
	/**是否开启（0-关闭；1-开启）*/
	private int isOpen;
	/**标签名*/
	private String tagName;
	/**兑换类型（1-每日；2-活动期间内总共）*/
	private int exchType;
	/**可兑换次数*/
	private int exchNum;
	/**兑换公式*/
	private List<Integer> exchExpId;
	/**概率*/
	private List<Integer> exchExpProb;
	/**是否能刷新（0-不能；1-能）*/
	private int isRefresh;
	/**刷新消耗初始元宝数*/
	private int refGold;
	/**刷新元宝类型（1-固定；2-叠加）*/
	private int refType;
	/**叠加值*/
	private int accumNum;
	/**叠加上限*/
	private int accumLimit;
	/**兑换预览*/
	private String view;
	/**免费刷新次数*/
	private int refFree;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getIsOpen(){
		return this.isOpen;
	}
	public void setIsOpen(int isOpen){
		this.isOpen=isOpen;
	}
	public String getTagName(){
		return this.tagName;
	}
	public void setTagName(String tagName){
		this.tagName=tagName;
	}
	public int getExchType(){
		return this.exchType;
	}
	public void setExchType(int exchType){
		this.exchType=exchType;
	}
	public int getExchNum(){
		return this.exchNum;
	}
	public void setExchNum(int exchNum){
		this.exchNum=exchNum;
	}
	public int getIsRefresh(){
		return this.isRefresh;
	}
	public void setIsRefresh(int isRefresh){
		this.isRefresh=isRefresh;
	}
	public int getRefGold(){
		return this.refGold;
	}
	public void setRefGold(int refGold){
		this.refGold=refGold;
	}
	public int getRefType(){
		return this.refType;
	}
	public void setRefType(int refType){
		this.refType=refType;
	}
	public int getAccumNum(){
		return this.accumNum;
	}
	public void setAccumNum(int accumNum){
		this.accumNum=accumNum;
	}
	public int getAccumLimit(){
		return this.accumLimit;
	}
	public void setAccumLimit(int accumLimit){
		this.accumLimit=accumLimit;
	}
	public String getView(){
		return this.view;
	}
	public void setView(String view){
		this.view=view;
	}
	public List<Integer> getExchExpId() {
		return exchExpId;
	}
	public void setExchExpId(List<Integer> exchExpId) {
		this.exchExpId = exchExpId;
	}
	public List<Integer> getExchExpProb() {
		return exchExpProb;
	}
	public void setExchExpProb(List<Integer> exchExpProb) {
		this.exchExpProb = exchExpProb;
	}
	public int getRefFree() {
		return refFree;
	}
	public void setRefFree(int refFree) {
		this.refFree = refFree;
	}
}


















