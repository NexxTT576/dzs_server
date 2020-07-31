/**
 * 
 */
package com.mdy.dzs.game.domain.role;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.mission.Mission;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年1月7日  下午4:11:32
 */
public class UpdateInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProbItem> probs;
	private List<Mission> acceptMissions;
	private int gold;
	private int silver;
	
	public List<ProbItem> getProbs() {
		return probs;
	}
	public void setProbs(List<ProbItem> probs) {
		this.probs = probs;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getSilver() {
		return silver;
	}
	public void setSilver(int silver) {
		this.silver = silver;
	}
	/**
	 * 
	 */
	public UpdateInfoVO() {
		// TODO 自动生成的构造函数存根
	}
	/**
	 * 
	 */
	public UpdateInfoVO(Role role) {
		this.gold = role.getGold();
		this.silver = role.getSilver();
	}
	public List<Mission> getAcceptMissions() {
		return acceptMissions;
	}
	public void setAcceptMissions(List<Mission> acceptMissions) {
		this.acceptMissions = acceptMissions;
	}
	
}
