package com.mdy.dzs.game.domain.mail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 邮件模型
 * @author 白雪林
 *
 */
public class Mail implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
	//类型id
	public static final int MAIL_TYPE_BATTLE = 1;//战斗
	public static final int MAIL_TYPE_FREND  = 2;//好友
	public static final int MAIL_TYPE_SYSTEM = 3;//系统
	
	//文字描述id
	public static final int MAIL_FUNCID_ARENA_DEFEND_WIN 			= 1;//竞技场防守胜利
	public static final int MAIL_FUNCID_ARENA_DEFEND_FAIL_RANK_DESC = 2;//竞技场防守失败,排名下降
	public static final int MAIL_FUNCID_ARENA_DEFEND_FAIL_RANK_HOLD = 3;//竞技场防守失败,排名不变
	public static final int MAIL_FUNCID_ARENA_RANK_AWARD 			= 4;//竞技场排名奖励
	public static final int MAIL_FUNCID_SNATCH_LOSE					= 5;//宝物碎片被夺
	public static final int MAIL_FUNCID_FREND_APPLY 				= 6;//申请好友
	public static final int MAIL_FUNCID_FREND_MESSAGE				= 7;//好友留言
	public static final int MAIL_FUNCID_FREND_FIGHT_WIN 			= 8;//好友切磋,获胜
	public static final int MAIL_FUNCID_FREND_FIGHT_FAIL			= 9;//好友切磋,失败
	public static final int MAIL_FUNCID_FREND_BREAK					= 10;//好友断交
	public static final int MAIL_FUNCID_UNION_AGREE					= 11;//入帮申请通过
	public static final int MAIL_FUNCID_UNION_REFUSE				= 12;//入帮申请被拒绝
	public static final int MAIL_FUNCID_UNION_OUT					= 13;//被帮派T出
	
	public static final int MAIL_FUNCID_比武_挑战_DEFEND_WIN 			= 14;	//1	比武防守胜利
	public static final int MAIL_FUNCID_比武_挑战_DEFEND_FAIL			= 15;//	1	比武防守失败
	public static final int MAIL_FUNCID_比武_复仇_DEFEND_WIN 			= 16;//	1	复仇防守胜利
	public static final int MAIL_FUNCID_比武_复仇_DEFEND_FAIL 		= 17;//	1	复仇防守失败
	public static final int MAIL_FUNCID_比武_天榜_DEFEND_WIN 			= 18;//	1	天榜防守胜利
	public static final int MAIL_FUNCID_比武_天榜_DEFEND_FAIL 		= 19;//	1	天榜防守失败
	public static final int MAIL_FUNCID_比武_发奖_AWARD 				= 20;//	3	比武排名奖励

	
	//para返给前端list类型	(1物品/2人物名/3数值日期)
	public static final int MAIL_PARA_TYPE_ITEM = 1;
	public static final int MAIL_PARA_TYPE_ROLE = 2;
	public static final int MAIL_PARA_TYPE_NUM  = 3;
	

	/**邮件id*/
	private int id;
	/**角色ID*/
	private int roleId;
	/**邮件类型(1战斗2好友3系统)*/
	private int type;
	/**策划表中字符串id*/
	private int strId;
	/**发送时间*/
	private Date time;
	/**附加参数*/
	private List<MailParaVO> paras;

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
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getStrId(){
		return this.strId;
	}
	public void setStrId(int strId){
		this.strId=strId;
	}
	public Date getTime(){
		return this.time;
	}
	public void setTime(Date time){
		this.time=time;
	}
	public List<MailParaVO> getParas() {
		return paras;
	}
	public void setParas(List<MailParaVO> paras) {
		this.paras = paras;
	}
}