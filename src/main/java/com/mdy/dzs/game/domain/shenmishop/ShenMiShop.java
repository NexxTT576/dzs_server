package com.mdy.dzs.game.domain.shenmishop;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
 * 角色神秘商店数据模型
 * @author 白雪林
 *
 */
public class ShenMiShop implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**角色id*/
	private int roleId;
	/**神秘商店物品列表*/
	private Map<String, ShenMiItemVO>  meysteryMap;
	/**神秘商店列表刷新时间点*/
	private Date meysteryRefreshTime;
	/**神秘商店免费刷新次数*/
	private int meysteryFreeCnt;
	/**神秘商店免费刷新次数累加时间点*/
	private Date meysteryFreeTime;
	/**神秘商店每日刷新次数*/
	private int meysteryDayRefreshCnt;
	/**神秘商店每日金币刷新次数*/
	private int meysteryDayGoldRefreshCnt;
	/**创建时间*/
	private Date creatTime;
	/**更新时间*/
	private Date updateTime;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public Map<String, ShenMiItemVO> getMeysteryMap(){
		return this.meysteryMap;
	}
	public void setMeysteryMap(Map<String, ShenMiItemVO> meysteryMap){
		this.meysteryMap=meysteryMap;
	}
	public Date getMeysteryRefreshTime(){
		return this.meysteryRefreshTime;
	}
	public void setMeysteryRefreshTime(Date meysteryRefreshTime){
		this.meysteryRefreshTime=meysteryRefreshTime;
	}
	public int getMeysteryFreeCnt(){
		return this.meysteryFreeCnt;
	}
	public void setMeysteryFreeCnt(int meysteryFreeCnt){
		this.meysteryFreeCnt=meysteryFreeCnt;
	}
	public Date getMeysteryFreeTime(){
		return this.meysteryFreeTime;
	}
	public void setMeysteryFreeTime(Date meysteryFreeTime){
		this.meysteryFreeTime=meysteryFreeTime;
	}
	public int getMeysteryDayRefreshCnt(){
		return this.meysteryDayRefreshCnt;
	}
	public void setMeysteryDayRefreshCnt(int meysteryDayRefreshCnt){
		this.meysteryDayRefreshCnt=meysteryDayRefreshCnt;
	}
	public int getMeysteryDayGoldRefreshCnt(){
		return this.meysteryDayGoldRefreshCnt;
	}
	public void setMeysteryDayGoldRefreshCnt(int meysteryDayGoldRefreshCnt){
		this.meysteryDayGoldRefreshCnt=meysteryDayGoldRefreshCnt;
	}
	public Date getCreatTime(){
		return this.creatTime;
	}
	public void setCreatTime(Date creatTime){
		this.creatTime=creatTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
}