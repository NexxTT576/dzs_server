package com.mdy.dzs.data.domain.activity.exchange;


import java.io.Serializable;
import java.util.List;


/**
 * 限时兑换公式模型
 * @author zhou
 *
 */
public class ActivityExchangeExp implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/**备注*/
	private String mark;
	/**兑换材料类型*/
	private List<Integer> exchItemType;
	/**兑换材料id*/
	private List<Integer> exchItemId;
	/**兑换材料数量*/
	private List<Integer> exchItemCnt;
	/**兑换结果类型*/
	private List<Integer> exchRstType;
	/**兑换结构id*/
	private List<Integer> exchRstId;
	/**兑换结果数量*/
	private List<Integer> exchRstCnt;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getMark(){
		return this.mark;
	}
	public void setMark(String mark){
		this.mark=mark;
	}
	public List<Integer> getExchItemType() {
		return exchItemType;
	}
	public void setExchItemType(List<Integer> exchItemType) {
		this.exchItemType = exchItemType;
	}
	public List<Integer> getExchItemId() {
		return exchItemId;
	}
	public void setExchItemId(List<Integer> exchItemId) {
		this.exchItemId = exchItemId;
	}
	public List<Integer> getExchItemCnt() {
		return exchItemCnt;
	}
	public void setExchItemCnt(List<Integer> exchItemCnt) {
		this.exchItemCnt = exchItemCnt;
	}
	public List<Integer> getExchRstType() {
		return exchRstType;
	}
	public void setExchRstType(List<Integer> exchRstType) {
		this.exchRstType = exchRstType;
	}
	public List<Integer> getExchRstId() {
		return exchRstId;
	}
	public void setExchRstId(List<Integer> exchRstId) {
		this.exchRstId = exchRstId;
	}
	public List<Integer> getExchRstCnt() {
		return exchRstCnt;
	}
	public void setExchRstCnt(List<Integer> exchRstCnt) {
		this.exchRstCnt = exchRstCnt;
	}
}
