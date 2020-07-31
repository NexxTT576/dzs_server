package com.mdy.dzs.data.domain.role;

import java.io.Serializable;


/**
 * 主角升级模型
 * @author 房曈
 *
 */
public class ShengJi implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**当前等级*/
	private int level;
	/**升级后等级*/
	private int uplevel;
	/**升级后耐力提升*/
	private int naili;
	/**金币奖励*/
	private int coin;
	/**上阵人数*/
	private int num;
	/**系统开放*/
	private String arrSystem;
	
	/**物品类型*/
	private int type;
	/**物品id*/
	private int item;
	/**物品数量*/
	private int num2;


	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getUplevel(){
		return this.uplevel;
	}
	public void setUplevel(int uplevel){
		this.uplevel=uplevel;
	}
	public int getNaili(){
		return this.naili;
	}
	public void setNaili(int naili){
		this.naili=naili;
	}
	public int getCoin(){
		return this.coin;
	}
	public void setCoin(int coin){
		this.coin=coin;
	}
	public int getNum(){
		return this.num;
	}
	public void setNum(int num){
		this.num=num;
	}
	public String getArrSystem(){
		return this.arrSystem;
	}
	public void setArrSystem(String arrSystem){
		this.arrSystem=arrSystem;
	}
}