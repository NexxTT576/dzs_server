/**
 * 
 */
package com.mdy.dzs.game.domain.mission;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.mission.MissionDefine;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年1月4日  下午3:17:39
 */
public class MissionListVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Mission> missions;
	private List<MissionDefine> extendMissionDefines;
	private RoleDailyMission dailyMission;
	
	public List<Mission> getMissions() {
		return missions;
	}
	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
	public List<MissionDefine> getExtendMissionDefines() {
		return extendMissionDefines;
	}
	public void setExtendMissionDefines(List<MissionDefine> extendMissionDefines) {
		this.extendMissionDefines = extendMissionDefines;
	}
	public RoleDailyMission getDailyMission() {
		return dailyMission;
	}
	public void setDailyMission(RoleDailyMission dailyMission) {
		this.dailyMission = dailyMission;
	}
	
	
}
