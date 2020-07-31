package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;

import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.util.DateUtil;
public class Union implements Serializable {

	/**
	 * 帮会数据表结构
	 */
	private static final long serialVersionUID = 1L;
	//升级时用到类型	建筑ID:1大殿，2作坊，3商店，4地洞，5青龙堂，6白虎堂
	public static final int UNION_TYPE_MAIN_PALACE = 1;//大殿  
	public static final int UNION_TYPE_WORK_SHOP   = 2;//作坊
	public static final int UNION_TYPE_SHOP		   = 3;//商店
	public static final int UNION_TYPE_HOLE		   = 4;//地洞
	public static final int UNION_TYPE_DRAGON	   = 5;//青龙堂
	public static final int UNION_TYPE_TAG		   = 6;//白虎堂
	public static final int UNION_TYPE_FB		   = 7;//帮派副本 
		
	
	/**帮派id*/
	private int id ;
	/**帮派名称*/
	private String name;
	/**帮派等级*/
	private int level;
	/**帮派排名*/
    private int rank;
    /**帮派人数上线*/
    private int roleMaxNum;
    /**帮派创建时间*/
    private Date createTime;
    /**帮派攻击力*/
    private int sumAttack;
    /**帮主id*/
    private int bossId;
    /**帮派公告*/
    private String unionIndes;
    /**帮派宣言*/
    private String unionOutdes;
    /**帮派总资金*/
    private int totalUnionMoney;
    /**帮派当前资金*/
    private int currentUnionMoney;
    /**工坊建筑等级*/
    private int workShopLevel;
    /**烧烤大会状态*/
    private Date barbecueTime;
    /**烧烤大会开启人*/
    private int openBarRole;
    /**帮派商店等级*/
    private int shopLevel;
    /**帮派自荐时间**/
    private Date coverTime;
    /**免费生产次数*/
    private int freeworkNum;
    /**购买生产次数*/
    private int buyworkNum;
    /**开始生产时间*/
    private String starworkTime;
    /**生产类型0侠魂1银币*/
    private int workType;
    /**是否加班生产0是1否*/
    private int overTimeFlag;
    /**道具购买次数*/
    private int buyNum;
    /**修改时间*/
    private String updateTime;    
    /**青龙店等级*/
    private int greenDragonTempleLevel;
    /**青龙等级*/
    private int greenDragonLevel;
    /**帮派副本等级*/
    private int fbLevel;
    
    public static Union ValueOf(String name,int roleId,int sumAttack,int rank){
    	Union union = new Union();
    	union.name = name;
    	union.bossId = roleId;
    	union.createTime =DateUtil.GetNowDateTime();
    	union.level = 1;
    	union.unionIndes = "欢迎大家一起建造帮派";
    	union.totalUnionMoney = 0;
    	union.currentUnionMoney = 0;
    	union.sumAttack = sumAttack;
    	union.roleMaxNum = 20;
    	union.shopLevel = 0;
    	union.rank = rank;
    	union.workShopLevel = 0;
    	return union ;
    }
    
    public UnionDataVO getValue(boolean isApply,Role role,int nowRoleNum){
    	UnionDataVO unionDataVO = new UnionDataVO();
        unionDataVO.setApply(isApply);
        unionDataVO.setLevel(level);
        unionDataVO.setName(name);
        unionDataVO.setId(id);
        unionDataVO.setUnionOutdes(unionOutdes);
        unionDataVO.setRoleNum(roleMaxNum);
        unionDataVO.setLeaderName(role.getName());
        unionDataVO.setLeaderLevel(role.getLevel());
        unionDataVO.setRank(rank);
        unionDataVO.setNowRoleNum(nowRoleNum);
    	unionDataVO.setBossId(role.getId());
    	unionDataVO.setSumAttack(sumAttack);
    	unionDataVO.setTotalUnionMoney(totalUnionMoney);  
    	unionDataVO.setCurrentUnionMoney(currentUnionMoney);
    	return unionDataVO;
    }
    public UnionDataVO valueData(RoleUnion roleUnion){
    	UnionDataVO unionDataVO = new UnionDataVO();
    	unionDataVO.setId(id);
        unionDataVO.setLevel(level);
        unionDataVO.setName(name);
        unionDataVO.setUnionOutdes(unionOutdes);
        unionDataVO.setRoleNum(roleMaxNum);
        unionDataVO.setRank(rank);
    	unionDataVO.setSumAttack(sumAttack);
    	unionDataVO.setUnionIndes(unionIndes);
    	unionDataVO.setUnionOutdes(unionOutdes);
    	unionDataVO.setTotalUnionMoney(totalUnionMoney);  
    	unionDataVO.setCurrentUnionMoney(currentUnionMoney);
    	unionDataVO.setShopLevel(shopLevel);
    	unionDataVO.setWorkShopLevel(workShopLevel);
    	unionDataVO.setGreenDragonTempleLevel(greenDragonTempleLevel);
    	unionDataVO.setSelfMoney(roleUnion.getLastContribute());
    	unionDataVO.setJopType(roleUnion.getJopType());
    	return unionDataVO;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getRoleMaxNum() {
		return roleMaxNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setRoleMaxNum(int roleMaxNum) {
		this.roleMaxNum = roleMaxNum;
	}
	public int getSumAttack() {
		return sumAttack;
	}
	public void setSumAttack(int sumAttack) {
		this.sumAttack = sumAttack;
	}
	public int getBossId() {
		return bossId;
	}
	public void setBossId(int bossId) {
		this.bossId = bossId;
	}
	public String getUnionIndes() {
		return unionIndes;
	}
	public void setUnionIndes(String unionIndes) {
		this.unionIndes = unionIndes;
	}
	public String getUnionOutdes() {
		return unionOutdes;
	}
	public void setUnionOutdes(String unionOutdes) {
		this.unionOutdes = unionOutdes;
	}
	public int getTotalUnionMoney() {
		return totalUnionMoney;
	}
	public void setTotalUnionMoney(int totalUnionMoney) {
		this.totalUnionMoney = totalUnionMoney;
	}
	public int getCurrentUnionMoney() {
		return currentUnionMoney;
	}
	public void setCurrentUnionMoney(int currentUnionMoney) {
		this.currentUnionMoney = currentUnionMoney;
	}
	
	public Date getBarbecueTime() {
		return barbecueTime;
	}

	public void setBarbecueTime(Date barbecueTime) {
		this.barbecueTime = barbecueTime;
	}

	public Date getCoverTime() {
		return coverTime;
	}
	public void setCoverTime(Date coverTime) {
		this.coverTime = coverTime;
	}
	public int getOpenBarRole() {
		return openBarRole;
	}
	public void setOpenBarRole(int openBarRole) {
		this.openBarRole = openBarRole;
	}
	public int getWorkShopLevel() {
		return workShopLevel;
	}
	public void setWorkShopLevel(int workShopLevel) {
		this.workShopLevel = workShopLevel;
	}
	public int getShopLevel() {
		return shopLevel;
	}
	public void setShopLevel(int shopLevel) {
		this.shopLevel = shopLevel;
	}
	public int getFreeworkNum() {
		return freeworkNum;
	}
	public void setFreeworkNum(int freeworkNum) {
		this.freeworkNum = freeworkNum;
	}
	public int getBuyworkNum() {
		return buyworkNum;
	}
	public void setBuyworkNum(int buyworkNum) {
		this.buyworkNum = buyworkNum;
	}
	public String getStarworkTime() {
		return starworkTime;
	}
	public void setStarworkTime(String starworkTime) {
		this.starworkTime = starworkTime;
	}
	public int getWorkType() {
		return workType;
	}
	public void setWorkType(int workType) {
		this.workType = workType;
	}
	public int getOverTimeFlag() {
		return overTimeFlag;
	}
	public void setOverTimeFlag(int overTimeFlag) {
		this.overTimeFlag = overTimeFlag;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getGreenDragonTempleLevel() {
		return greenDragonTempleLevel;
	}
	public void setGreenDragonTempleLevel(int greenDragonTempleLevel) {
		this.greenDragonTempleLevel = greenDragonTempleLevel;
	}

	public int getGreenDragonLevel() {
		return greenDragonLevel;
	}

	public void setGreenDragonLevel(int greenDragonLevel) {
		this.greenDragonLevel = greenDragonLevel;
	}

	public int getFbLevel() {
		return fbLevel;
	}

	public void setFbLevel(int fbLevel) {
		this.fbLevel = fbLevel;
	}
}
