package com.mdy.dzs.data.domain.world;

import java.io.Serializable;
import java.util.List;


/**
 * 副本模型
 * @author 房曈
 *
 */
public class Field implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**副本id*/
	private int id;
	/**名称*/
	private String name;
	/**图标id*/
	private String icon;
	/**所属地图*/
	private int world;
	/**x坐标*/
	private int xAxis;
	/**y坐标*/
	private int yAxis;
	/**副本类型（0：普通；1：精英；2：困难）*/
	private int type;
	/**前置副本*/
	private int prefield;
	/**副本星星总数*/
	private int star;
	/**奖励1星星数*/
	private int star1;
	/**奖励1物品组*/
	private List<Integer> arrReward1;
	/**奖励1物品组*/
	private List<Integer> arrNum1;
	/**奖励2星星数*/
	private int star2;
	/**奖励2物品组*/
	private List<Integer> arrReward2;
	/**奖励2物品数量*/
	private List<Integer> arrNum2;
	/**奖励3星星数*/
	private int star3;
	/**奖励3物品组*/
	private List<Integer> arrReward3;
	/**奖励3物品数量*/
	private List<Integer> arrNum3;
	/**战役*/
	private List<Integer> arrBattle;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon=icon;
	}
	public int getWorld(){
		return this.world;
	}
	public void setWorld(int world){
		this.world=world;
	}
	public int getXAxis(){
		return this.xAxis;
	}
	public void setXAxis(int xAxis){
		this.xAxis=xAxis;
	}
	public int getYAxis(){
		return this.yAxis;
	}
	public void setYAxis(int yAxis){
		this.yAxis=yAxis;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getPrefield(){
		return this.prefield;
	}
	public void setPrefield(int prefield){
		this.prefield=prefield;
	}
	public int getStar(){
		return this.star;
	}
	public void setStar(int star){
		this.star=star;
	}
	public int getStar1(){
		return this.star1;
	}
	public void setStar1(int star1){
		this.star1=star1;
	}
	public List<Integer> getArrReward1(){
		return this.arrReward1;
	}
	public void setArrReward1(List<Integer> arrReward1){
		this.arrReward1=arrReward1;
	}
	public List<Integer> getArrNum1(){
		return this.arrNum1;
	}
	public void setArrNum1(List<Integer> arrNum1){
		this.arrNum1=arrNum1;
	}
	public int getStar2(){
		return this.star2;
	}
	public void setStar2(int star2){
		this.star2=star2;
	}
	public List<Integer> getArrReward2(){
		return this.arrReward2;
	}
	public void setArrReward2(List<Integer> arrReward2){
		this.arrReward2=arrReward2;
	}
	public List<Integer> getArrNum2(){
		return this.arrNum2;
	}
	public void setArrNum2(List<Integer> arrNum2){
		this.arrNum2=arrNum2;
	}
	public int getStar3(){
		return this.star3;
	}
	public void setStar3(int star3){
		this.star3=star3;
	}
	public List<Integer> getArrReward3(){
		return this.arrReward3;
	}
	public void setArrReward3(List<Integer> arrReward3){
		this.arrReward3=arrReward3;
	}
	public List<Integer> getArrNum3(){
		return this.arrNum3;
	}
	public void setArrNum3(List<Integer> arrNum3){
		this.arrNum3=arrNum3;
	}
	public List<Integer> getArrBattle(){
		return this.arrBattle;
	}
	public void setArrBattle(List<Integer> arrBattle){
		this.arrBattle=arrBattle;
	}
}