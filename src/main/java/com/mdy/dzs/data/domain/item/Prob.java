package com.mdy.dzs.data.domain.item;

import java.io.Serializable;
import java.util.List;


/**
 * 概率包模型
 * @author 房曈
 *
 */
public class Prob implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**概率组id*/
	private int id;
	/**说明*/
	private String explain;
	/**随机类型（1-均概率选1；2-均概率选多）*/
	private int type;
	private int isJson;
	private List<ProbVal> items;
	
	/**类型1（1-item；2-card）;id范围;数量;概率*/
	private List<Integer> item1;
	/**类型2;id范围;数量;概率*/
	private List<Integer> item2;
	/**类型3;id范围;数量;概率*/
	private List<Integer> item3;
	/**类型4;id范围;数量;概率*/
	private List<Integer> item4;
	/**类型5;id范围;数量;概率*/
	private List<Integer> item5;
	/**类型6;id范围;数量;概率*/
	private List<Integer> item6;
	/**类型7;id范围;数量;概率*/
	private List<Integer> item7;
	/**类型8;id范围;数量;概率*/
	private List<Integer> item8;

	private List<ProbVal> itemExtra; 
	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getExplain(){
		return this.explain;
	}
	public void setExplain(String explain){
		this.explain=explain;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public List<Integer> getItem1(){
		return this.item1;
	}
	public void setItem1(List<Integer> item1){
		this.item1=item1;
	}
	public List<Integer> getItem2(){
		return this.item2;
	}
	public void setItem2(List<Integer> item2){
		this.item2=item2;
	}
	public List<Integer> getItem3(){
		return this.item3;
	}
	public void setItem3(List<Integer> item3){
		this.item3=item3;
	}
	public List<Integer> getItem4(){
		return this.item4;
	}
	public void setItem4(List<Integer> item4){
		this.item4=item4;
	}
	public List<Integer> getItem5(){
		return this.item5;
	}
	public void setItem5(List<Integer> item5){
		this.item5=item5;
	}
	public List<Integer> getItem6(){
		return this.item6;
	}
	public void setItem6(List<Integer> item6){
		this.item6=item6;
	}
	public List<Integer> getItem7(){
		return this.item7;
	}
	public void setItem7(List<Integer> item7){
		this.item7=item7;
	}
	public List<Integer> getItem8(){
		return this.item8;
	}
	public void setItem8(List<Integer> item8){
		this.item8=item8;
	}
	public int getIsJson() {
		return isJson;
	}
	public void setIsJson(int isJson) {
		this.isJson = isJson;
	}
	public List<ProbVal> getItems() {
		return items;
	}
	public void setItems(List<ProbVal> items) {
		this.items = items;
	}
	public List<ProbVal> getItemExtra() {
		return itemExtra;
	}
	public void setItemExtra(List<ProbVal> itemExtra) {
		this.itemExtra = itemExtra;
	}
	
}