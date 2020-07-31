/**
 * 
 */
package com.mdy.dzs.data.domain.mission;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年1月22日  上午11:59:57
 */
public enum ActivityMissionProperty {
	竞技场_声望翻倍(1),
	竞技场_银币翻倍(2),
	论剑_灵石翻倍(3),
	论剑_银币翻倍(4),
	世界BOSS_银币翻倍(5),
	世界BOSS_声望翻倍(6),
	劫富济贫_次数翻倍(7),
	除暴安良_次数翻倍(8),
	行侠仗义_次数翻倍(9),
	精英副本_次数翻倍(10),
	VIP福利_声望翻倍(11),
	月卡_奖励翻倍(12),
	限时掉落(13),
	黑风寨(14),
	狼居胥(15);
	
	private int id;
	ActivityMissionProperty(int id){
		this.id = id;
	}
	public int value(){
		return id;
	}
}
