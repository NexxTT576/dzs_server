package com.mdy.dzs.game.domain.mail;

import java.io.Serializable;
import java.util.List;

public class MailVO implements Serializable {
	/*{
		type:	邮件所属类型(1战斗/2好友/3系统)
		strId:	消息字符串id，详见策划配置表
		mailId:	邮件id
		disDay: 日差
		paras:	[
				{paraType:		1/2/3(1物品/2人物名/3数值日期)
				 item_type：	物品类型
				 item_id:		物品id
				 item_num:		物品数量
				 str:			字符串
				 cls:			阶数
				 },
				 ......
				]				
		}*/
	
	private static final long serialVersionUID = 1L;

	//邮件所属类型(1战斗/2好友/3系统)
	private int type;
	//消息字符串id，详见策划配置表
	private int strId;
	//邮件生成时间
	private int mailId;
	//日差
	private int disDay;
	//paras
	private List<MailParaVO> paras;
	
	public MailVO(int type, int strId, int mailId, int disDay, List<MailParaVO> paras){
		this.type = type;
		this.strId = strId;
		this.mailId = mailId;
		this.disDay = disDay;
		this.paras = paras;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStrId() {
		return strId;
	}
	public void setStrId(int strId) {
		this.strId = strId;
	}
	public int getDisDay() {
		return disDay;
	}
	public void setDisDay(int disDay) {
		this.disDay = disDay;
	}
	public List<MailParaVO> getParas() {
		return paras;
	}
	public void setParas(List<MailParaVO> paras) {
		this.paras = paras;
	}
	public int getMailId() {
		return mailId;
	}
	public void setMailId(int mailId) {
		this.mailId = mailId;
	}

}
