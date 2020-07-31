/**
 * 
 */
package com.mdy.dzs.game.domain.challenge;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 玩家活动副本状态
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月7日  下午8:04:08
 */
public class RoleActivityBattle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**玩家id*/
	private int roleId;
	/**活动副本参与次数(每日统计)  */
	private Map<String, Integer> actPveCnts;
	/**活动副本购买次数 */
	private Map<String, Integer> actBuyCnts;
	/**活动副本使用道具次数 */
	private Map<String, Integer> actUseItemCnts;
	/**活动副本当前怪物波数[1/2/3,1/2/3,1/2/3]*/
	private Map<String, Integer> actPveSteps;
	/**下次战斗,下阵卡牌位置组*/
	private List<Integer> actNxtLvDPos;
	
	/**每日刷新时间*/
	private Date actDayRefresh;
	
	private Date createTime;
	private Date updateTime;

	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public Map<String, Integer> getActPveCnts() {
		return actPveCnts;
	}
	public void setActPveCnts(Map<String, Integer> actPveCnts) {
		this.actPveCnts = actPveCnts;
	}
	public Map<String, Integer> getActPveSteps() {
		return actPveSteps;
	}
	public void setActPveSteps(Map<String, Integer> actPveSteps) {
		this.actPveSteps = actPveSteps;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public List<Integer> getActNxtLvDPos() {
		return actNxtLvDPos;
	}
	public void setActNxtLvDPos(List<Integer> actNxtLvDPos) {
		this.actNxtLvDPos = actNxtLvDPos;
	}
	public void setActDayRefresh(Date actDayRefresh) {
		this.actDayRefresh = actDayRefresh;
	}
	public Date getActDayRefresh() {
		return actDayRefresh;
	}
	public Map<String, Integer> getActBuyCnts() {
		return actBuyCnts;
	}
	public void setActBuyCnts(Map<String, Integer> actBuyCnts) {
		this.actBuyCnts = actBuyCnts;
	}
	public Map<String, Integer> getActUseItemCnts() {
		return actUseItemCnts;
	}
	public void setActUseItemCnts(Map<String, Integer> actUseItemCnts) {
		this.actUseItemCnts = actUseItemCnts;
	}
	
}
