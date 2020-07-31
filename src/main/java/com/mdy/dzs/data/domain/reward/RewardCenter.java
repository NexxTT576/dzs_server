package com.mdy.dzs.data.domain.reward;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mdy.dzs.data.domain.gift.GiftItem;

/**
 * 奖励中心模型
 * 
 * @author 房曈
 *
 */
public class RewardCenter implements Serializable {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	public static final int CONDITION_竞技场排名区间 = 1;
	public static final int CONDITION_等级排名区间 = 2;
	public static final int CONDITION_等级区间 = 3;

	/** 主键id */
	private int id;
	/** 判断条件 */
	private int condition;
	/** 参数 */
	private List<Integer> params;
	/** 奖励列表 */
	private List<GiftItem> rewardItem;
	/** 奖励信息 */
	private String rewardMsg;
	/** 发奖对应服务器 */
	private String rewardServerId;
	/** 描述 */
	private String desc;
	/** 发奖时间 */
	private Date rewardTime;
	/** 创建时间 */
	private Date createTime;

	private List<Integer> sendRoleIds;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCondition() {
		return this.condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public List<Integer> getParams() {
		return this.params;
	}

	public void setParams(List<Integer> params) {
		this.params = params;
	}

	public List<GiftItem> getRewardItem() {
		return this.rewardItem;
	}

	public void setRewardItem(List<GiftItem> rewardItem) {
		this.rewardItem = rewardItem;
	}

	public String getRewardMsg() {
		return this.rewardMsg;
	}

	public void setRewardMsg(String rewardMsg) {
		this.rewardMsg = rewardMsg;
	}

	public String getRewardServerId() {
		return this.rewardServerId;
	}

	public void setRewardServerId(String rewardServerId) {
		this.rewardServerId = rewardServerId;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getRewardTime() {
		return this.rewardTime;
	}

	public void setRewardTime(Date rewardTime) {
		this.rewardTime = rewardTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Integer> getSendRoleIds() {
		return sendRoleIds;
	}

	public void setSendRoleIds(List<Integer> sendRoleIds) {
		this.sendRoleIds = sendRoleIds;
	}
}