package com.mdy.dzs.game.domain.item;

import java.io.Serializable;
import java.util.Date;

import com.mdy.dzs.game.domain.role.Role;


/**
 * 道具日志模型
 * @author 房曈
 *
 */
public class RoleItemLog implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
															//comments
	public static final int SYS_背包_扩展			= 10001;
	public static final int SYS_背包_出售			= 10002;
	public static final int SYS_背包_使用道具		= 10003;//道具id
	
	public static final int SYS_礼包_在线礼包		= 20001;
	public static final int SYS_礼包_等级礼包		= 20002;
	public static final int SYS_礼包_开服礼包		= 20003;
	public static final int SYS_礼包_签到礼包		= 20004;
	public static final int SYS_礼包_领奖中心		= 20005;//领奖类型+otherdata
	public static final int SYS_礼包_VIP礼包		= 20006;
	public static final int SYS_礼包_VIP每日礼包	= 20007;
	public static final int SYS_礼包_月卡礼包		= 20008;
	public static final int SYS_礼包_CDKEY			= 20009;
	
	public static final int SYS_副本_简单副本_1次	= 30001;//副本id
	public static final int SYS_副本_简单副本_5次	= 30002;//副本id
	public static final int SYS_副本_普通副本_1次	= 30003;//副本id
	public static final int SYS_副本_普通副本_5次	= 30004;//副本id
	public static final int SYS_副本_困难副本_1次	= 30005;//副本id
	public static final int SYS_副本_困难副本_5次	= 30006;//副本id
	public static final int SYS_副本_星级奖励		= 30007;
	public static final int SYS_副本_购买次数		= 30008;//副本id
	public static final int SYS_副本_清除CD		= 30009;
	
	
	public static final int SYS_炼化炉_炼化_侠客	= 40001;
	public static final int SYS_炼化炉_炼化_装备	= 40002;
	public static final int SYS_炼化炉_重生_侠客	= 40003;
	public static final int SYS_炼化炉_重生_装备	= 40004;
	
	public static final int SYS_经脉_洗经伐脉		= 50001;
	public static final int SYS_经脉_提升			= 50002;
	
	public static final int SYS_真气_练气_1次		= 60001;
	public static final int SYS_真气_练气_10次		= 60002;
	public static final int SYS_真气_召唤三花聚顶	= 60003;
	public static final int SYS_真气_真气升级		= 60004;
	public static final int SYS_真气_聚元_金币或道具		= 60005;
	
	public static final int SYS_挑战_精英副本		= 70001;//精英副本id
	public static final int SYS_挑战_活动副本		= 70002;//活动副本id
	public static final int SYS_挑战_精英副本_购买次数		= 70003;//活动副本id
	public static final int SYS_挑战_活动副本_购买次数		= 70004;//活动副本id
	
	public static final int SYS_装备_扩展			= 80001;//装备id
	public static final int SYS_装备_出售			= 80002;//
	public static final int SYS_装备_强化			= 80003;//装备id
	public static final int SYS_装备_属性洗练		= 80004;//装备id

	public static final int SYS_侠客_进阶			= 90001;//侠客id
	public static final int SYS_侠客_强化			= 90002;//侠客id
	public static final int SYS_侠客_出售			= 90003;//侠客id
	public static final int SYS_侠客_扩展			= 90004;//侠客id
	public static final int SYS_侠客_侠魂强化		= 90005;//侠客id
	public static final int SYS_侠客_神通洗点		= 90006;//侠客id
	public static final int SYS_侠客_侠魂强化5次	= 90007;//侠客id
	
	public static final int SYS_精彩活动_客栈			= 100001;//
	public static final int SYS_精彩活动_神秘商店		= 100002;//
	public static final int SYS_精彩活动_酒剑仙_翻牌	= 100003;//
	public static final int SYS_精彩活动_酒剑仙_猜拳	= 100004;//
	public static final int SYS_精彩活动_神秘商店列表	= 100005;//
	public static final int SYS_精彩活动_神秘商店兑换	= 100006;//
	public static final int SYS_精彩活动_累积登录礼包	= 100007;//
	public static final int SYS_精彩活动_月签奖励		= 100008;//
	public static final int SYS_精彩活动_投资计划		= 100009;//
	public static final int SYS_精彩活动_皇宫探宝			= 100010;//
	public static final int SYS_精彩活动_皇宫探宝_积分奖		= 100011;//
	public static final int SYS_精彩活动_皇宫探宝_探宝花费	= 100012;//
	public static final int SYS_精彩活动_限时商店_兑换花费	= 100013;//
	public static final int SYS_精彩活动_限时商店_兑换道具	= 100014;//
	
	public static final int SYS_精彩活动_迷宫寻宝_刷新宝库	= 100021;
	public static final int SYS_精彩活动_迷宫寻宝_挖宝消耗	= 100022;
	public static final int SYS_精彩活动_迷宫寻宝_挖宝获得	= 100023;
	
	public static final int SYS_商城_购买道具		= 110001;//
	public static final int SYS_商城_江湖新秀		= 110002;//
	public static final int SYS_商城_武林高手		= 110003;//
	public static final int SYS_商城_武林高手_免费	= 110004;//
	public static final int SYS_商城_名震江湖_1次	= 110005;//
	public static final int SYS_商城_名震江湖_免费	= 110006;//
	public static final int SYS_商城_名震江湖_10次	= 110007;//
	public static final int SYS_限时豪杰	= 110008;//
	
	public static final int SYS_名将_赠送			= 120001;//侠客resid
	public static final int SYS_名将_一键赠送		= 120002;//侠客resid
	
	public static final int SYS_竞技场_挑战 		= 130001;
	public static final int SYS_竞技场_兑换 		= 130002;
	
	public static final int SYS_武学争夺_合成 		= 140001;
	public static final int SYS_武学争夺_争夺 		= 140002;
	public static final int SYS_武学争夺_免战牌 	= 140003;
	public static final int SYS_武学争夺_夺十次 	= 140004;
	
	public static final int SYS_武学_升级			= 150001;
	public static final int SYS_武学_出售			= 150002;
	public static final int SYS_武学_精炼			= 150003;
	
	public static final int SYS_主角_升级 			= 160001;
	
	public static final int SYS_充值_基础获得		= 170001;
	public static final int SYS_充值_赠送获得 		= 170002;
	public static final int SYS_充值_首冲赠送获得 		= 170003;
	public static final int SYS_充值_月卡获得		= 170004;
	
	public static final int SYS_注册_初始化 		= 180001;
	public static final int SYS_机器人_初始化 		= 180002;
	public static final int SYS_GM_操作 			= 190001;

	public static final int SYS_论剑_重置			= 200001;
	public static final int SYS_论剑_领奖			= 200002;
	public static final int SYS_论剑_商店兑换	    = 200003;
	
	public static final int SYS_boss_攻击boss	= 210001;
	public static final int SYS_boss_元宝鼓舞		= 210002;
	public static final int SYS_boss_元宝复活		= 210003;
	public static final int SYS_boss_银币鼓舞		= 210004;

	public static final int SYS_VIP_v等级礼包购买	= 220001;
	
	public static final int SYS_任务_领取奖励		= 230001;
	public static final int SYS_任务积分_领取奖励		= 230002;
	
	public static final int SYS_帮派_创建帮派			= 240001;
	public static final int SYS_帮派_boss_攻击boss	= 240002;
	public static final int SYS_帮派_boss_元宝鼓舞		= 240003;
	public static final int SYS_帮派_boss_元宝复活		= 240004;
	public static final int SYS_帮派_boss_银币鼓舞		= 240005;
	public static final int SYS_帮派_捐献			    = 240006;
	public static final int SYS_帮派_工坊购买次数		= 240007;
	public static final int SYS_帮派_工坊立即结束		= 240008;
	public static final int SYS_帮派_工坊生产			= 240009;
	public static final int SYS_帮派_商店兑换			= 240010;
	public static final int SYS_帮派_领取每周福利		= 240011;
	public static final int SYS_帮派_工坊获得生产奖励	= 240012;
	public static final int SYS_帮派_领取帮派副本通关奖励 = 240013;
	
	public static final int SYS_好友_领取耐力 			= 250001;
	public static final int SYS_好友_领取耐力_全部好友	= 250002;
	
	public static final int SYS_比武商店_购买 			= 260001;
	public static final int SYS_比武挑战 				= 260002;
	public static final int SYS_比武_购买次数 			= 260003;
	
	public static final int SYS_押镖_召唤金品质镖车 		= 270001;
	public static final int SYS_押镖_刷新镖车 			= 270002;
	public static final int SYS_押镖_加速运镖消耗 		= 270003;
	public static final int SYS_押镖_押镖获得 			= 270004;

	public static final int SYS_限时兑换_刷新扣除 			= 270005;
	public static final int SYS_限时兑换_兑换扣除 			= 270006;
	public static final int SYS_限时兑换_兑换添加 			= 270007;
	
	
	
	
	
	/**主键ID*/
	private int id;
	/**批次号*/
	private String batchNo;
	/**人物id*/
	private int roleId;
	/**角色当前等级*/
	private int roleLevel;
	/**所属功能*/
	private int systemType;
	/**类型*/
	private int itemPos;
	/***/
	private int itemId;
	/**变更数量数目*/
	private int itemNum;
	/**变更后*/
	private int newNum;
	/**变更批注*/
	private String comments;
	/**记录创建时间*/
	private Date createTime;
	
	public RoleItemLog() {
	}
	public RoleItemLog(Role doc,int sysType,int itemType,int itemId,int itemNum,int newNum,String comments) {
		this.roleId = doc.getId();
		this.roleLevel = doc.getLevel();
		this.batchNo = doc.getOperateCode();
		this.systemType = sysType;
		this.itemPos = itemType;
		this.itemId = itemId;
		this.itemNum = itemNum;
		this.newNum = newNum;
		this.comments = comments;
	}

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getRoleLevel(){
		return this.roleLevel;
	}
	public void setRoleLevel(int roleLevel){
		this.roleLevel=roleLevel;
	}
	public int getSystemType(){
		return this.systemType;
	}
	public void setSystemType(int systemType){
		this.systemType=systemType;
	}
	public int getItemPos(){
		return this.itemPos;
	}
	public void setItemPos(int itemPos){
		this.itemPos=itemPos;
	}
	public int getItemId(){
		return this.itemId;
	}
	public void setItemId(int itemId){
		this.itemId=itemId;
	}
	public int getItemNum(){
		return this.itemNum;
	}
	public void setItemNum(int itemNum){
		this.itemNum=itemNum;
	}
	public String getComments(){
		return this.comments;
	}
	public void setComments(String comments){
		this.comments=comments;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public int getNewNum() {
		return newNum;
	}
	public void setNewNum(int newNum) {
		this.newNum = newNum;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
}