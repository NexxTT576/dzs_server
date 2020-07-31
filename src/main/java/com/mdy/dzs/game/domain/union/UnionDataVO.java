package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UnionDataVO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8348855238997762131L;
	private int id;
	private String name;
	private int level;
	private int rank;
	private int roleNum;
	private String createTime;
	private int sumAttack;
	private int bossId;
	private String unionIndes;
	private String unionOutdes;
	private int totalUnionMoney;
	private int currentUnionMoney;
	private int nowRoleNum;
	private String leaderName;
	private boolean isApply;
	private int leaderLevel;
	private int success;
	private int selfMoney;
	private int isInUnion;
	private int myrank;
	private int mysumAttack;
	private int totalNum;
	private int jopType;
	private int shopLevel;
	private int workShopLevel;
	private int greenDragonTempleLevel;
	private List<UnionDataVO> unionList;
	private List<MemberVO> applyList;
	private int surplusGold;
	private Date nowTime;
	private int FBLevel;
	public Date getNowTime() {
		return nowTime;
	}
	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}
	public List<UnionDataVO> getUnionList() {
		return unionList;
	}
	public void setUnionList(List<UnionDataVO> unionList) {
		this.unionList = unionList;
	}
	public int getNowRoleNum() {
		return nowRoleNum;
	}
	public void setNowRoleNum(int nowRoleNum) {
		this.nowRoleNum = nowRoleNum;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	
	
	public boolean isApply() {
		return isApply;
	}
	public void setApply(boolean isApply) {
		this.isApply = isApply;
	}
	public int getLeaderLevel() {
		return leaderLevel;
	}
	public void setLeaderLevel(int leaderLevel) {
		this.leaderLevel = leaderLevel;
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
	public int getRoleNum() {
		return roleNum;
	}
	public void setRoleNum(int roleNum) {
		this.roleNum = roleNum;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public List<MemberVO> getApplyList() {
		return applyList;
	}
	public void setApplyList(List<MemberVO> applyList) {
		this.applyList = applyList;
	}
	public int getSelfMoney() {
		return selfMoney;
	}
	public void setSelfMoney(int selfMoney) {
		this.selfMoney = selfMoney;
	}
	public int getIsInUnion() {
		return isInUnion;
	}
	public void setIsInUnion(int isInUnion) {
		this.isInUnion = isInUnion;
	}
	public int getMyrank() {
		return myrank;
	}
	public void setMyrank(int myrank) {
		this.myrank = myrank;
	}
	public int getMysumAttack() {
		return mysumAttack;
	}
	public void setMysumAttack(int mysumAttack) {
		this.mysumAttack = mysumAttack;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getJopType() {
		return jopType;
	}
	public void setJopType(int jopType) {
		this.jopType = jopType;
	}
	public int getShopLevel() {
		return shopLevel;
	}
	public void setShopLevel(int shopLevel) {
		this.shopLevel = shopLevel;
	}
	public int getWorkShopLevel() {
		return workShopLevel;
	}
	public void setWorkShopLevel(int workShopLevel) {
		this.workShopLevel = workShopLevel;
	}
	public int getGreenDragonTempleLevel() {
		return greenDragonTempleLevel;
	}
	public void setGreenDragonTempleLevel(int greenDragonTempleLevel) {
		this.greenDragonTempleLevel = greenDragonTempleLevel;
	}
	public int getSurplusGold() {
		return surplusGold;
	}
	public void setSurplusGold(int surplusGold) {
		this.surplusGold = surplusGold;
	}
	public int getFBLevel() {
		return FBLevel;
	}
	public void setFBLevel(int fBLevel) {
		FBLevel = fBLevel;
	}
}
