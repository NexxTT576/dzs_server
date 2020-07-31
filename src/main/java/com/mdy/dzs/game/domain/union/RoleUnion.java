package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;

import com.mdy.dzs.game.util.DateUtil;

public class RoleUnion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4585276850557650243L;
	//在帮派中
	public static final int roleinUnion = 1;
	public static final int roleoutUnion =2;
	//0成员1长老2副帮主3帮主
	public static final int member = 0;
	public static final int elders = 1;
	public static final int vice =2;
	public static final int leader =3;
	public static final Date date= DateUtil.getDateTimeByString("1990-01-01 01:01:01");
	/**id*/
	private int id ;
	/**帮派id*/
	private int unionId;
	/**玩家id*/
	private int roleId;
	/**玩家职务*/
    private int jopType;
    /**进入帮派时间*/
	private Date intoTime;
	/**退出帮派时间*/
	private Date outTime;
	/**玩家总贡献值*/
    private int totalContribute;
    /**玩家当前贡献值*/
    private int lastContribute;
    /**每周福利领取时间*/
	private Date weeklyBenefits;
	/**烧烤大会时间*/
	private Date barbecue;
	/**挑战次数*/
    private int dekaronNum;
    /**挑战时间*/
    private Date dekaronTime;
    /**被挑战次数*/
	private int defenseNum;
	/**被挑战时间*/
	private Date defenseTime;
	/**捐献时间*/
    private String gifttime;
    /**捐献次数*/
    private int giftnum;
    /**玩家军团状态*/
    private int state;
    /**自荐时间*/
    private Date coverTime;
    /**免费生产次数*/
    private int freeworknum;
    /**当前剩余的次数*/
    private int buyworknum;
    /** 每次购买加1*/
    private int buynum;
    /**开始生产时间*/
    private Date starworktime;
    /**生产类型1侠魂2银币*/
    private int worktype;
    /**是否加班生产0是1否*/
    private int overtimeflag;
    /**生产是否结束0结束1未结束*/
    private int isOver;
    /**竞技场排名*/
    private int rank;
    /**创建时间*/
    private Date creatTime;
    /**竞技场排名*/
    private Date updateTime;
    /**最后捐献时间*/
    private Date lastContributionTime;
    /**捐献类型0未捐献1银币2元宝*/
    private int conType;
    /**捐献金额*/
    private int costMoney;
    /**作坊奖励是否领取 0已领取1未领取*/
    private int shopWorkrdReward;
    /**商店兑换时间*/
    private Date exchangeTime;
    /**兑换的商品id*/
    private String shopIds;
    public static RoleUnion ValueOf(int unionId,int roleId,int jopType,int rank){
    	RoleUnion roleUnion = new RoleUnion();
    	roleUnion.roleId = roleId;
    	roleUnion.unionId = unionId;
    	roleUnion.jopType = jopType;
    	roleUnion.intoTime = DateUtil.GetNowDateTime();
    	roleUnion.state = roleinUnion;
    	roleUnion.starworktime = DateUtil.getDateTimeByString("1958-01-01 00:00:00");
    	roleUnion.rank = rank;
    	roleUnion.dekaronNum = 10;
    	roleUnion.defenseNum = 10;
    	roleUnion.lastContributionTime = DateUtil.GetNowDateTime();
    	roleUnion.conType = 0;
    	roleUnion.freeworknum = 1;
    	return roleUnion;
    }
    /**
     * 清除帮派信息
     */
    public static RoleUnion clearUnion(RoleUnion roleUnion){
    	roleUnion.unionId = 0;
		roleUnion.jopType = 0 ;
		roleUnion.intoTime =date;
		roleUnion.lastContributionTime = date;
		roleUnion.outTime = DateUtil.GetNowDateTime();
		roleUnion.dekaronNum = 0;
    	roleUnion.defenseNum = 0;
		roleUnion.giftnum = 0 ;
		roleUnion.state =roleoutUnion;
		roleUnion.coverTime = date;
		roleUnion.starworktime =date;
		roleUnion.worktype = 0;
		roleUnion.overtimeflag = 0;
		roleUnion.buynum = 0;
		roleUnion.shopWorkrdReward = 0;
		roleUnion.isOver = 0;
		return roleUnion;
    }
    /**
     * 增加贡献度
     */
    public void increaseGift(int value){
    	this.totalContribute += value;
    	this.lastContribute += value;
    }
    /**
     * 减少贡献度
     */
	public int reduceGift(int value) {
		return this.lastContribute -= value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUnionId() {
		return unionId;
	}
	public void setUnionId(int unionId) {
		this.unionId = unionId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getJopType() {
		return jopType;
	}
	public void setJopType(int jopType) {
		this.jopType = jopType;
	}
	public int getTotalContribute() {
		return totalContribute;
	}
	public void setTotalContribute(int totalContribute) {
		this.totalContribute = totalContribute;
	}
	public int getLastContribute() {
		return lastContribute;
	}
	public void setLastContribute(int lastContribute) {
		this.lastContribute = lastContribute;
	}
	public Date getIntoTime() {
		return intoTime;
	}
	public void setIntoTime(Date intoTime) {
		this.intoTime = intoTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public Date getWeeklyBenefits() {
		return weeklyBenefits;
	}
	public void setWeeklyBenefits(Date weeklyBenefits) {
		this.weeklyBenefits = weeklyBenefits;
	}
	public Date getBarbecue() {
		return barbecue;
	}
	public void setBarbecue(Date barbecue) {
		this.barbecue = barbecue;
	}
	public void setCoverTime(Date coverTime) {
		this.coverTime = coverTime;
	}
	public void setStarworktime(Date starworktime) {
		this.starworktime = starworktime;
	}
	public int getDekaronNum() {
		return dekaronNum;
	}
	public void setDekaronNum(int dekaronNum) {
		this.dekaronNum = dekaronNum;
	}
	public Date getDekaronTime() {
		return dekaronTime;
	}
	public void setDekaronTime(Date dekaronTime) {
		this.dekaronTime = dekaronTime;
	}
	public void setDefenseTime(Date defenseTime) {
		this.defenseTime = defenseTime;
	}
	public int getDefenseNum() {
		return defenseNum;
	}
	public void setDefenseNum(int defenseNum) {
		this.defenseNum = defenseNum;
	}
	public String getGifttime() {
		return gifttime;
	}
	public void setGifttime(String gifttime) {
		this.gifttime = gifttime;
	}
	public int getGiftnum() {
		return giftnum;
	}
	public void setGiftnum(int giftnum) {
		this.giftnum = giftnum;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getFreeworknum() {
		return freeworknum;
	}
	public void setFreeworknum(int freeworknum) {
		this.freeworknum = freeworknum;
	}
	public int getBuyworknum() {
		return buyworknum;
	}
	public void setBuyworknum(int buyworknum) {
		this.buyworknum = buyworknum;
	}
	public Date getCoverTime() {
		return coverTime;
	}
	public Date getStarworktime() {
		return starworktime;
	}
	public int getWorktype() {
		return worktype;
	}
	public void setWorktype(int worktype) {
		this.worktype = worktype;
	}
	public int getOvertimeflag() {
		return overtimeflag;
	}
	public void setOvertimeflag(int overtimeflag) {
		this.overtimeflag = overtimeflag;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	public Date getDefenseTime() {
		return defenseTime;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getLastContributionTime() {
		return lastContributionTime;
	}
	public void setLastContributionTime(Date lastContributionTime) {
		this.lastContributionTime = lastContributionTime;
	}
	public int getConType() {
		return conType;
	}
	public void setConType(int conType) {
		this.conType = conType;
	}
	public int getCostMoney() {
		return costMoney;
	}
	public void setCostMoney(int costMoney) {
		this.costMoney = costMoney;
	}
	public int getShopWorkrdReward() {
		return shopWorkrdReward;
	}
	public void setShopWorkrdReward(int shopWorkrdReward) {
		this.shopWorkrdReward = shopWorkrdReward;
	}
	public int getIsOver() {
		return isOver;
	}
	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}
	public Date getExchangeTime() {
		return exchangeTime;
	}
	public void setExchangeTime(Date exchangeTime) {
		this.exchangeTime = exchangeTime;
	}
	public String getShopIds() {
		return shopIds;
	}
	public void setShopIds(String shopIds) {
		this.shopIds = shopIds;
	}
}
