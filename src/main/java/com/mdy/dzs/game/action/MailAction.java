package com.mdy.dzs.game.action;

import java.util.Map;

import com.mdy.sharp.container.biz.BizException;

public interface MailAction {

	/*
	 * 获取邮件列表 client->server: Get: /mail/mlist?acc=*&type=*&mailId=* Post:
	 * {"m":"mail", "a":"mlist", "acc":"*", "type":"1/2/3", "mailId":"*" } acc: 账号
	 * type: 发信类型(1战斗/2好友/3系统) mailId: 邮件id
	 * 
	 * client<-server: {err: 错误信息 rtnObj: { mailList: [ { type: 邮件所属类型(1战斗/2好友/3系统)
	 * strId: 消息字符串id，详见策划配置表 mailId: 邮件id disDay: 日差 paras: [ {paraType:
	 * 1/2/3(1物品/2人物名/3数值日期) item_type： 物品类型 item_id: 物品id item_num: 物品数量 str: 字符串
	 * cls: 阶数 }, ...... ] }, ...... ] mailCnt: num //邮件总数 }
	 */

	Map<String, Object> mlist(String acc, int mailId, int type) throws BizException;

	void tidyTabel();

}
