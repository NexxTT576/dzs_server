package com.mdy.dzs.data.domain.biwu;
import java.io.Serializable;


/**
 * 比武商店模型
 * @author zhou
 *
 */
public class BiwuShop implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**编号*/
	private int id;
	/**物品类型（1：装备2：时装3：装备碎片4：内外功5：武将碎片6：精元7：可使用物品8：武将9：内功碎片10：外功碎片11：礼品12：材料）*/
	private int type;
	/**物品id*/
	private int item;
	/**物品数量*/
	private int num;
	/**兑换类型（1-总次数限定，2-本周次数限定,3-今日次数限定）*/
	private int type1;
	/**兑换数量*/
	private int num1;
	/**单价/声望*/
	private int price;
	/**等级需求*/
	private int level;

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
	public int getItem(){
		return this.item;
	}
	public void setItem(int item){
		this.item=item;
	}
	public int getNum(){
		return this.num;
	}
	public void setNum(int num){
		this.num=num;
	}
	public int getType1(){
		return this.type1;
	}
	public void setType1(int type1){
		this.type1=type1;
	}
	public int getNum1(){
		return this.num1;
	}
	public void setNum1(int num1){
		this.num1=num1;
	}
	public int getPrice(){
		return this.price;
	}
	public void setPrice(int price){
		this.price=price;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
}