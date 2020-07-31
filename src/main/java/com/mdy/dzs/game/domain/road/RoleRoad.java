package com.mdy.dzs.game.domain.road;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 玩家状态模型
 * @author 房曈
 *
 */
public class RoleRoad implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;

	public static final int USE_一键使用 = 1;
	public static final int USE_单个使用 = 2;

	/***/
	private int roleId;
	private Map<String,RoleRoadCard> roadCards;
	/**江湖路主界面侠客resId*/
	private int roadMainCardId;
	/**好感总和*/
	private int roadStarCnt;
	/**江湖路成就达成记录*/
	private List<Integer> roadStarAchs;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getRoadMainCardId(){
		return this.roadMainCardId;
	}
	public void setRoadMainCardId(int roadMainCardId){
		this.roadMainCardId=roadMainCardId;
	}
	public List<Integer> getRoadStarAchs(){
		return this.roadStarAchs;
	}
	public void setRoadStarAchs(List<Integer> roadStarAchs){
		this.roadStarAchs=roadStarAchs;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	public Map<String,RoleRoadCard> getRoadCards() {
		return roadCards;
	}
	public void setRoadCards(Map<String,RoleRoadCard> roadCards) {
		this.roadCards = roadCards;
	}
	public int getRoadStarCnt() {
		return roadStarCnt;
	}
	public void setRoadStarCnt(int roadStarCnt) {
		this.roadStarCnt = roadStarCnt;
	}
}