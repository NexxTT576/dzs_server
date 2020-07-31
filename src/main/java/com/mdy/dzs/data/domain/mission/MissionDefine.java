package com.mdy.dzs.data.domain.mission;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务定义类
 * 
 * @author fangtong
 *
 */
public class MissionDefine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 任务属性
	public static final int PROPERTY_永远完不成_MISSION = 0;

	public static final int PROPERTY_主角_登陆_MISSION = 1000; // 次数
	public static final int PROPERTY_主角_等级达到_MISSION = 1001; // 等级
	public static final int PROPERTY_主角_经脉全部提升到_MISSION = 1002; // 经脉等级
	public static final int PROPERTY_主角_战斗力达到_MISSION = 1003; // 战斗力
	public static final int PROPERTY_主角_充值RMB_MISSION = 1004; // RMB
	public static final int PROPERTY_主角_消耗元宝_MISSION = 1005; // 消耗元宝
	public static final int PROPERTY_主角_充值天数_MISSION = 1006; // 消耗元宝
	public static final int PROPERTY_主角_一次性充值_MISSION = 1007; // RMB

	public static final int PROPERTY_侠客_抽卡获得_MISSION = 1011; // 品质 次数 0为任意
	public static final int PROPERTY_侠客_升级_MISSION = 1012; // 品质 等级
	public static final int PROPERTY_侠客_合成_MISSION = 1013; // 品质 次数 0为任意
	public static final int PROPERTY_侠客_获得_MISSION = 1014; // 品质 次数 0为任意

	public static final int PROPERTY_装备_获得_MISSION = 1021; // 品质 次数 0为任意
	public static final int PROPERTY_装备_获得不同_MISSION = 1022; // 次数
	public static final int PROPERTY_装备_升级_MISSION = 1023; // 等级
	public static final int PROPERTY_装备_洗练_MISSION = 1024; // 次数

	public static final int PROPERTY_真气_获得_MISSION = 1031; // 品质 次数 0为任意
	public static final int PROPERTY_真气_聚气_MISSION = 1032; // 次数
	public static final int PROPERTY_真气_升级_MISSION = 1033; // 等级

	public static final int PROPERTY_武学_升级_MISSION = 1041; // 等级

	public static final int PROPERTY_道具_使用道具_MISSION = 1051; // 道具id 次数

	public static final int PROPERTY_副本_通关_MISSION = 1061; // 副本id 次数
	public static final int PROPERTY_副本_星数达到_MISSION = 1062; // 星数
	public static final int PROPERTY_副本_战斗_MISSION = 1063; // 次数

	public static final int PROPERTY_精英副本_通关_MISSION = 1071; // 精英副本id 次数
	public static final int PROPERTY_精英副本_战斗_MISSION = 1072; // 次数

	public static final int PROPERTY_劫富济贫_伤害达到_MISSION = 1081; // 伤害值

	public static final int PROPERTY_竞技场_排名达到_MISSION = 1091; // 排名
	public static final int PROPERTY_竞技场_挑战_MISSION = 1092; // 次数

	public static final int PROPERTY_论剑_通关层_MISSION = 1101; // 层数
	public static final int PROPERTY_论剑_重置_MISSION = 1102; // 次数

	public static final int PROPERTY_夺宝_抢夺_MISSION = 1111; // 次数

	public static final int PROPERTY_烛龙殿_参加_MISSION = 1121; // 次数
	public static final int PROPERTY_烛龙殿_击杀_MISSION = 1122; // 次数

	public static final int PROPERTY_江湖路_侠客等级_MISSION = 1131; // 等级
	public static final int PROPERTY_江湖路_送礼_MISSION = 1132; // 次数

	public static final int PROPERTY_好友_送耐力_MISSION = 1141; // 次数
	public static final int PROPERTY_好友_加好友_MISSION = 1142; // 数量

	public static final int PROPERTY_客栈_睡觉_MISSION = 1151; // 次数

	public static final int PROPERTY_神秘商店_刷新_MISSION = 1161; // 次数

	public static final int PROPERTY_活动副本_战斗_MISSION = 1171; // 战斗 次数

	public static final int PROPERTY_帮派_捐赠_MISSION = 1181; // 次数

	public static final Map<Integer, Integer> PARAMS_MATCH_NUM = new HashMap<Integer, Integer>();
	static {
		PARAMS_MATCH_NUM.put(PROPERTY_永远完不成_MISSION, 1);

		for (ActivityMissionProperty property : ActivityMissionProperty.values()) {
			PARAMS_MATCH_NUM.put(property.value(), 1);
		}

		PARAMS_MATCH_NUM.put(PROPERTY_主角_登陆_MISSION, 1);// 次数
		PARAMS_MATCH_NUM.put(PROPERTY_主角_等级达到_MISSION, 1);// 等级
		PARAMS_MATCH_NUM.put(PROPERTY_主角_经脉全部提升到_MISSION, 1);// 经脉等级
		PARAMS_MATCH_NUM.put(PROPERTY_主角_战斗力达到_MISSION, 1);// 战斗力
		PARAMS_MATCH_NUM.put(PROPERTY_主角_充值RMB_MISSION, 1);
		PARAMS_MATCH_NUM.put(PROPERTY_主角_消耗元宝_MISSION, 1);
		PARAMS_MATCH_NUM.put(PROPERTY_主角_充值天数_MISSION, 1);
		PARAMS_MATCH_NUM.put(PROPERTY_主角_一次性充值_MISSION, 1);

		PARAMS_MATCH_NUM.put(PROPERTY_侠客_抽卡获得_MISSION, 2);// 品质 次数 0为任意
		PARAMS_MATCH_NUM.put(PROPERTY_侠客_升级_MISSION, 2);// 等级
		PARAMS_MATCH_NUM.put(PROPERTY_侠客_合成_MISSION, 2);// 品质 次数 0为任意
		PARAMS_MATCH_NUM.put(PROPERTY_侠客_获得_MISSION, 2);// 品质 次数 0为任意

		PARAMS_MATCH_NUM.put(PROPERTY_装备_获得_MISSION, 2);// 品质 次数 0为任意
		PARAMS_MATCH_NUM.put(PROPERTY_装备_获得不同_MISSION, 1);// 次数
		PARAMS_MATCH_NUM.put(PROPERTY_装备_升级_MISSION, 1);// 等级
		PARAMS_MATCH_NUM.put(PROPERTY_装备_洗练_MISSION, 1);// 次数

		PARAMS_MATCH_NUM.put(PROPERTY_真气_获得_MISSION, 2);// 品质 次数 0为任意
		PARAMS_MATCH_NUM.put(PROPERTY_真气_聚气_MISSION, 1);// 次数
		PARAMS_MATCH_NUM.put(PROPERTY_真气_升级_MISSION, 1);// 等级

		PARAMS_MATCH_NUM.put(PROPERTY_武学_升级_MISSION, 1);// 等级

		PARAMS_MATCH_NUM.put(PROPERTY_道具_使用道具_MISSION, 2);// 道具id 次数

		PARAMS_MATCH_NUM.put(PROPERTY_副本_通关_MISSION, 2);// 副本id 次数
		PARAMS_MATCH_NUM.put(PROPERTY_副本_星数达到_MISSION, 1);// 星数
		PARAMS_MATCH_NUM.put(PROPERTY_副本_战斗_MISSION, 1);// 次数

		PARAMS_MATCH_NUM.put(PROPERTY_精英副本_通关_MISSION, 2);// 精英副本id 次数
		PARAMS_MATCH_NUM.put(PROPERTY_精英副本_战斗_MISSION, 1);// 次数

		PARAMS_MATCH_NUM.put(PROPERTY_劫富济贫_伤害达到_MISSION, 1);// 伤害值

		PARAMS_MATCH_NUM.put(PROPERTY_竞技场_排名达到_MISSION, 1);// 排名
		PARAMS_MATCH_NUM.put(PROPERTY_竞技场_挑战_MISSION, 1);// 次数

		PARAMS_MATCH_NUM.put(PROPERTY_论剑_通关层_MISSION, 1);// 层数
		PARAMS_MATCH_NUM.put(PROPERTY_论剑_重置_MISSION, 1);// 次数

		PARAMS_MATCH_NUM.put(PROPERTY_夺宝_抢夺_MISSION, 1);// 次数

		PARAMS_MATCH_NUM.put(PROPERTY_烛龙殿_参加_MISSION, 1);// 次数
		PARAMS_MATCH_NUM.put(PROPERTY_烛龙殿_击杀_MISSION, 1);// 次数

		PARAMS_MATCH_NUM.put(PROPERTY_江湖路_侠客等级_MISSION, 1);// 等级
		PARAMS_MATCH_NUM.put(PROPERTY_江湖路_送礼_MISSION, 1);// 次数

		PARAMS_MATCH_NUM.put(PROPERTY_好友_送耐力_MISSION, 1);//
		PARAMS_MATCH_NUM.put(PROPERTY_好友_加好友_MISSION, 1);
		PARAMS_MATCH_NUM.put(PROPERTY_客栈_睡觉_MISSION, 1);//

		PARAMS_MATCH_NUM.put(PROPERTY_神秘商店_刷新_MISSION, 1);
		PARAMS_MATCH_NUM.put(PROPERTY_活动副本_战斗_MISSION, 2);
		PARAMS_MATCH_NUM.put(PROPERTY_帮派_捐赠_MISSION, 1);

	}
	//
	/** 任务分类定义，日常任务 1 */
	public static final int CATEGORY_DAILY_MISSION = 1;
	/** 任务分类定义，成就任务 2 */
	public static final int CATEGORY_ACHIEVEMENT_MISSION = 2;
	/** 任务分类定义，收集任务 3 */
	public static final int CATEGORY_COLLECT_MISSION = 3;
	/** 任务分类定义，活动任务 4 */
	public static final int CATEGORY_ACTIVITY_MISSION = 4;

	/** 查询所有的任务 */
	public static final int CATEGORY_ALL_MISSION = 1000;

	/** 不限制等级 0 */
	public static final int LEVEL_UNLIMIT = 0;

	//
	/** ID */
	private int id;
	/** 等级限制,0表示没有限制 */
	private int levelLimit;
	/** 任务分类 */
	private int category;
	/** 任务属性 */
	private int property;
	/** 名字 */
	private String name;
	/** 描述 */
	private String description;
	/** 任务目的 */
	private String purpose;
	/** 任务导航 */
	private String navigation;
	/** 参数 */
	private List<Integer> prams;
	/** 继承上一任务的统计值 */
	private int inherit;
	/** 下一个任务id */
	private int limitMissionId;
	/** 是否每日刷新 */
	private int refreshDaily;
	/** 活动任务开始时间 */
	private Date startTime;
	/** 活动任务结束时间 */
	private Date endTime;
	/** 对应活动 **/
	private int renWuPaiXu;
	/** 自动领奖 */
	private int autoReward;
	/** 积分 */
	private int jifen;

	/** 任务的奖励 */
	private List<Integer> rewardIds;
	private List<Integer> rewardTypes;
	private List<Integer> rewardNums;

	public List<Integer> getRewardIds() {
		return rewardIds;
	}

	public void setRewardIds(List<Integer> rewardIds) {
		this.rewardIds = rewardIds;
	}

	public List<Integer> getRewardTypes() {
		return rewardTypes;
	}

	public void setRewardTypes(List<Integer> rewardTypes) {
		this.rewardTypes = rewardTypes;
	}

	public List<Integer> getRewardNums() {
		return rewardNums;
	}

	public void setRewardNums(List<Integer> rewardNums) {
		this.rewardNums = rewardNums;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevelLimit() {
		return levelLimit;
	}

	public void setLevelLimit(int levelLimit) {
		this.levelLimit = levelLimit;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getProperty() {
		return property;
	}

	public void setProperty(int property) {
		this.property = property;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isEnd() {
		return endTime != null ? endTime.before(new Date()) : false;
	}

	public boolean isStart() {
		return startTime != null ? startTime.before(new Date()) : true;
	}

	@Override
	public String toString() {
		return "MissionDefine [id=" + id + ", levelLimit=" + levelLimit + ", category=" + category + ", property="
				+ property + ", name=" + name + ", description=" + description + ", prams=" + prams + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

	public List<Integer> getPrams() {
		return prams;
	}

	public void setPrams(List<Integer> prams) {
		this.prams = prams;
	}

	public int getLimitMissionId() {
		return limitMissionId;
	}

	public void setLimitMissionId(int limitMissionId) {
		this.limitMissionId = limitMissionId;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getNavigation() {
		return navigation;
	}

	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}

	public int getRenWuPaiXu() {
		return renWuPaiXu;
	}

	public void setRenWuPaiXu(int renWuPaiXu) {
		this.renWuPaiXu = renWuPaiXu;
	}

	public int getInherit() {
		return inherit;
	}

	public void setInherit(int inherit) {
		this.inherit = inherit;
	}

	public int getAutoReward() {
		return autoReward;
	}

	public void setAutoReward(int autoReward) {
		this.autoReward = autoReward;
	}

	public int getJifen() {
		return jifen;
	}

	public void setJifen(int jifen) {
		this.jifen = jifen;
	}

	public int getRefreshDaily() {
		return refreshDaily;
	}

	public void setRefreshDaily(int refreshDaily) {
		this.refreshDaily = refreshDaily;
	}
}
