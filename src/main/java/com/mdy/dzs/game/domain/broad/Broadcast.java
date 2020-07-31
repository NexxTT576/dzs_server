package com.mdy.dzs.game.domain.broad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 广播模型
 * @author 房曈
 *
 */
public class Broadcast implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
	//广播类型 
	public static final int BROAD_TYPE_SYSTEM_TIME   = 1;//系统定时播放
	public static final int BROAD_TYPE_PLAYER_TOUCH  = 2;//玩家触发
	public static final int BROAD_TYPE_SYSTEM_RANDOM = 3;//系统随机播放
	public static final int BROAD_TYPE_UI_MID		 = 4;//界面播放
	
	//字符串id
	public static final int WINE_SHOP				= 1;//恭喜%s在侠客招募中获得%s侠客！称霸武林指日可待！
	public static final int CARD_CLS				= 2;//恭喜%s将侠客%s进阶到了+%s，战力得到大幅提升！
	public static final int GET_EQUIP_GONG			= 3;//恭喜%s获得%s，战力得到大幅提升！
	public static final int ACTIVITY_GET_LIMIT_CARD = 4;//恭喜%s鸿星高照，在限时豪杰活动中获得了限时侠客%s！
	public static final int ACTIVITY_EGT_CARD		= 5;//恭喜%s在限时豪杰活动中获得了%s侠客！称霸武林指日可待！

	/***/
	private int id;
	/**发送时间*/
	private Date time;
	/**账号*/
	private String account;
	/**广播字符串id*/
	private int strId;
	/**类型：系统定时，玩家触发，随机，界面*/
	private int type;
	/**参数列表*/
	private List<BroadData> paralist;
	/**渠道号*/
	private String pf;
	/**color*/
	private List<Integer> color;
	/**para1*/
	private String para1;
	/**para2*/
	private String para2;
	/**para3*/
	private String para3;
	/**para4*/
	private String para4;
	/**para5*/
	private String para5;
	/**para6*/
	private String para6;
	/**para7*/
	private String para7;
	/**para8*/
	private String para8;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public Date getTime(){
		return this.time;
	}
	public void setTime(Date time){
		this.time=time;
	}
	public String getAccount(){
		return this.account;
	}
	public void setAccount(String account){
		this.account=account;
	}
	public int getStrId(){
		return this.strId;
	}
	public void setStrId(int strId){
		this.strId=strId;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public List<Integer> getColor(){
		return this.color;
	}
	public void setColor(List<Integer> color){
		this.color=color;
	}
	public String getPara1(){
		return this.para1;
	}
	public void setPara1(String para1){
		this.para1=para1;
	}
	public String getPara2(){
		return this.para2;
	}
	public void setPara2(String para2){
		this.para2=para2;
	}
	public String getPara3(){
		return this.para3;
	}
	public void setPara3(String para3){
		this.para3=para3;
	}
	public String getPara4(){
		return this.para4;
	}
	public void setPara4(String para4){
		this.para4=para4;
	}
	public String getPara5(){
		return this.para5;
	}
	public void setPara5(String para5){
		this.para5=para5;
	}
	public String getPara6(){
		return this.para6;
	}
	public void setPara6(String para6){
		this.para6=para6;
	}
	public String getPara7(){
		return this.para7;
	}
	public void setPara7(String para7){
		this.para7=para7;
	}
	public String getPara8(){
		return this.para8;
	}
	public void setPara8(String para8){
		this.para8=para8;
	}
	public List<BroadData> getParalist() {
		return paralist;
	}
	public void setParalist(List<BroadData> paralist) {
		this.paralist = paralist;
	}
	public String getPf() {
		return pf;
	}
	public void setPf(String pf) {
		this.pf = pf;
	}
}