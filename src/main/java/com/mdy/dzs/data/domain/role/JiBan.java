package com.mdy.dzs.data.domain.role;

import java.io.Serializable;
import java.util.List;


/**
 * 羁绊模型
 * @author 房曈
 *
 */
public class JiBan implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**羁绊id*/
	private int id;
	/**羁绊原名*/
	private String old;
	/**羁绊名称*/
	private String name;
	/**羁绊类型（1-人人羁绊，2-人装备羁绊）*/
	private int type;
	/**羁绊描述*/
	private String describe;
	/**条件1（人人羁绊配置cardid；人装备配置itemid）*/
	private int cond1;
	/**条件2*/
	private int cond2;
	/**条件3*/
	private int cond3;
	/**条件4*/
	private int cond4;
	/**条件5*/
	private int cond5;
	/**条件6*/
	private int cond6;
	/**条件7*/
	private int cond7;
	/**增加属性1id*/
	private int nature1;
	/**属性1值*/
	private int value1;
	/**属性1递增值*/
	private List<Integer> arrAdd1;
	/**增加属性2id*/
	private int nature2;
	/**属性2值*/
	private int value2;
	/**属性2递增值*/
	private List<Integer> arrAdd2;
	/**增加属性3id*/
	private int nature3;
	/**属性3值*/
	private int value3;
	/**属性3递增值*/
	private List<Integer> arrAdd3;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getOld(){
		return this.old;
	}
	public void setOld(String old){
		this.old=old;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public String getDescribe(){
		return this.describe;
	}
	public void setDescribe(String describe){
		this.describe=describe;
	}
	public int getCond1(){
		return this.cond1;
	}
	public void setCond1(int cond1){
		this.cond1=cond1;
	}
	public int getCond2(){
		return this.cond2;
	}
	public void setCond2(int cond2){
		this.cond2=cond2;
	}
	public int getCond3(){
		return this.cond3;
	}
	public void setCond3(int cond3){
		this.cond3=cond3;
	}
	public int getCond4(){
		return this.cond4;
	}
	public void setCond4(int cond4){
		this.cond4=cond4;
	}
	public int getCond5(){
		return this.cond5;
	}
	public void setCond5(int cond5){
		this.cond5=cond5;
	}
	public int getCond6(){
		return this.cond6;
	}
	public void setCond6(int cond6){
		this.cond6=cond6;
	}
	public int getCond7(){
		return this.cond7;
	}
	public void setCond7(int cond7){
		this.cond7=cond7;
	}
	public int getNature1(){
		return this.nature1;
	}
	public void setNature1(int nature1){
		this.nature1=nature1;
	}
	public int getValue1(){
		return this.value1;
	}
	public void setValue1(int value1){
		this.value1=value1;
	}
	public List<Integer> getArrAdd1(){
		return this.arrAdd1;
	}
	public void setArrAdd1( List<Integer> arrAdd1){
		this.arrAdd1=arrAdd1;
	}
	public int getNature2(){
		return this.nature2;
	}
	public void setNature2(int nature2){
		this.nature2=nature2;
	}
	public int getValue2(){
		return this.value2;
	}
	public void setValue2(int value2){
		this.value2=value2;
	}
	public  List<Integer> getArrAdd2(){
		return this.arrAdd2;
	}
	public void setArrAdd2( List<Integer> arrAdd2){
		this.arrAdd2=arrAdd2;
	}
	public int getNature3(){
		return this.nature3;
	}
	public void setNature3(int nature3){
		this.nature3=nature3;
	}
	public int getValue3(){
		return this.value3;
	}
	public void setValue3(int value3){
		this.value3=value3;
	}
	public  List<Integer> getArrAdd3(){
		return this.arrAdd3;
	}
	public void setArrAdd3( List<Integer> arrAdd3){
		this.arrAdd3=arrAdd3;
	}
	
	public int getCond(int pos){
		int res = 0;
		switch (pos) {
		case 1:
			res = cond1;
			break;
		case 2:
			res = cond2;
			break;
		case 3:
			res = cond3;
			break;
		case 4:
			res = cond4;
			break;
		case 5:
			res = cond5;
			break;
		case 6:
			res = cond6;
			break;
		case 7:
			res = cond7;
			break;
		default:
			break;
		}
		return res;
	}
}