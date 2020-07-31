package com.mdy.dzs.game.domain.mail;

import java.io.Serializable;

public class MailParaVO implements Serializable{
	/*{	 paraType:		1/2/3(1物品/2人物名/3数值日期)
		 item_type：		物品类型
		 item_id:		物品id
		 item_num:		物品数量
		 str:			字符串
		 cls:			阶数
	}*/	

	private static final long serialVersionUID = 1L;
	
	//1/2/3(1物品/2人物名/3数值日期)
	private int paraType;
	//物品类型
	private int item_type;
	//物品id
	private int item_id;
	//物品数量
	private int item_num;
	//字符串
	private String str;
	//阶数
	private int cls;
	
	public MailParaVO() {
		// TODO 自动生成的构造函数存根
	}
	//人物名字
	public MailParaVO(int paraType, String str, int cls) {
		this.paraType = paraType; 
		this.str = str;
		this.cls = cls;
	}
	//获得物品
	public MailParaVO(int paraType, int item_type, int item_id, int item_num) {
		this.paraType = paraType; 
		this.item_type = item_type;
		this.item_id = item_id;
		this.item_num = item_num;
	}
	//数值日期
	public MailParaVO(int paraType, String str) {
		this.paraType = paraType; 
		this.str = str;
	}
	
	public int getParaType() {
		return paraType;
	}
	public void setParaType(int paraType) {
		this.paraType = paraType;
	}
	public int getItem_type() {
		return item_type;
	}
	public void setItem_type(int item_type) {
		this.item_type = item_type;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
}
