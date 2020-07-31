package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;






import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.util.JSONUtil;

public class UnionDynamic implements Serializable {

	/**
	 * 帮派动态
	 */
	private static final long serialVersionUID = -8499986774750300218L;
	
	/**加入帮派类型*/
	public static final int JOIN_UNION_TYPE = 1;
	/**帮派退出类型*/
	public static final int EXIT_UNION_TYPE = 2;
	/**帮派银币捐献类型*/
	public static final int CONTRIBUTION_SLIVER_TYPE = 3;
	/**帮派元宝捐献类型*/
	public static final int CONTRIBUTION_GOLD_TYPE = 4;
	/**任命副帮主*/
	public static final int APPOINTMENT_TYPE = 5;
	/**取消任命类型*/
	public static final int CANCEL_APPOINTMENT_TYPE = 6;
	/**禅让帮主*/
	public static final int TRANSFER_UNION = 7;
	/**自荐获取帮主*/
	public static final int COVER_LEADER = 8;
	/**帮主升级主建筑*/
	public static final int BUILDING_UP = 9;
	/**领取每周福利**/
	public static final int GET_WEEKLY_WELFARE = 10;
	/**开启烧烤大会*/
	public static final int OPEN_BARB = 11;
	/**领取烤肉奖励*/
	public static final int GET_BARB_WELARE = 12;
	/**帮派商店兑换**/
	public static final int UNION_SHOP_EXCHANGE = 13;
	/**后山密道探险**/
	public static final int UNION_SECRET_PATH = 14;
	/**开启青龙**/
	public static final int OPEN_DRGON = 15;
	/**成功战胜青龙**/
	public static final int WIN_DRGON = 16;
	/**未能战胜青龙**/
	public static final int LOSE_DRGON = 17;
	/**飞贼潜入帮派内**/
	public static final int SNITCH_IN_UNION = 18;
	/**xx战胜飞贼**/
	public static final int WIN_SNITCH = 19;
	/**飞贼逃离**/
	public static final int SNITCH_RUN= 20;
	/**升级作坊**/
	public static final int UP_WORKSHOP = 21;
	/**升级商店*/
	public static final int UP_SHOP = 22 ;
	/**升级青龙堂*/
	public static final int UP_DRGON = 23 ;
	/**升级后山密道*/
	public static final int UP_SECRET_PATH = 24 ;
	/**升级帮派副本**/
	public static final int UP_UNION_CARBON = 25 ;
	/**副帮主升级主建筑**/
	public static final int UP_MIAN_BUILD_BY_VICE = 26 ;
	/**副帮主开启烧烤大会*/
	public static final int OPEN_BARB_BY_VICE = 27;
	/**副帮主开启青龙**/
	public static final int OPEN_DRGON_BY_VICE= 28;
	/**副帮主升级作坊**/
	public static final int UP_WORKSHOP_BY_VICE = 29;
	/**副帮主升级商店*/
	public static final int UP_SHOP_BY_VICE = 30 ;
	/**副帮主升级青龙堂*/
	public static final int UP_DRGON_BY_VICE = 31 ;
	/**副帮主升级后山密道*/
	public static final int UP_SECRET_PATH_BY_VICE = 32 ;
	/**升副帮主级帮派副本**/
	public static final int UP_UNION_CARBON_BY_VICE = 33 ;
	/**成为的帮主 帮主退出时用**/
	public static final int TO_BE_LEADER = 34 ;
	/**任命长老*/
	public static final int APPOINTMENT_ELDER_TYPE = 35;
	/**战胜青龙，青龙满级*/
	public static final int WIN_DRAGON_LEVEL_FULL  = 36;
	
	/**青龙堂动态类型数组*/
	public static final List<Integer> GREEN_DRAGON_TYPE = Arrays.asList(WIN_DRGON,LOSE_DRGON,WIN_DRAGON_LEVEL_FULL);

	
	/** id */
	private int id;
	/** 元宝数量 */
	private int goldNum;
	/** 金币数量 */
	private int silverNum;
	/** 创建时间 */
	private Date createTime;
	/** 动态类型 */
	private int type;
	/** 动态内容 */
	private String des;
	/** 玩家id */
	private int roleId;
	/** 帮派id */
	private int unionId;
	/** 其他参数 */
	private String paras;
	
	public static UnionDynamic valueOf(int unionId, int roleId, int type,
			List<Object> params) {
		UnionDynamic unionDynamic = new UnionDynamic();
		unionDynamic.unionId = unionId;
		unionDynamic.roleId = roleId;
		unionDynamic.type = type;
		unionDynamic.createTime = DateUtil.GetNowDateTime();
		unionDynamic.des = JSONUtil.toJson(params);
		return unionDynamic;
	}
	//青龙结束动态
	public static UnionDynamic dragonDy(int unionId, int roleId, int type,
			List<Object> params, List<Object> otherParas) {
		UnionDynamic unionDynamic = new UnionDynamic();
		unionDynamic.unionId 	= unionId;
		unionDynamic.roleId 	= roleId;
		unionDynamic.type 		= type;
		unionDynamic.createTime = DateUtil.GetNowDateTime();
		unionDynamic.des 		= JSONUtil.toJson(params);
		unionDynamic.paras 		= JSONUtil.toJson(otherParas);
		return unionDynamic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoldNum() {
		return goldNum;
	}
	public void setGoldNum(int goldNum) {
		this.goldNum = goldNum;
	}
	public int getSilverNum() {
		return silverNum;
	}
	public void setSilverNum(int silverNum) {
		this.silverNum = silverNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getUnionId() {
		return unionId;
	}
	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}
	public String getParas() {
		return paras;
	}
	public void setParas(String paras) {
		this.paras = paras;
	}
	
}
