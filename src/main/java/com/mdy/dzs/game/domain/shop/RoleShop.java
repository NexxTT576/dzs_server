package com.mdy.dzs.game.domain.shop;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 角色商城状态模型
 * @author 房曈
 *
 */
public class RoleShop implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int roleId;
	/**[0普通，1中级，2高级]*/
	private List<Integer> wineFreeCntAry;
	/**下次免费抽取时间数组*/
	private List<Integer> wineFreeNextTimeAry;
	/**累计抽取次数统计数组*/
	private List<Integer> wineGotCntAry;
	/**用于抽卡概率的抽取次数*/
	private List<Integer> wineProbCntAry;
	/**抽箱子的次数记录*/
	private Map<String, Integer> boxProbCntAry;
	/**当日已购买限购计数数组 id,cnt*/
	private Map<String, Integer> comShopPurchased;
	/**每日刷新时间   */
	private Date shopDayRefresh;
	/***/
	private Date createTime;
	/***/
	private Date updateTime;

	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public List<Integer> getWineFreeCntAry(){
		return this.wineFreeCntAry;
	}
	public void setWineFreeCntAry(List<Integer> wineFreeCntAry){
		this.wineFreeCntAry=wineFreeCntAry;
	}
	public List<Integer> getWineFreeNextTimeAry(){
		return this.wineFreeNextTimeAry;
	}
	public void setWineFreeNextTimeAry(List<Integer> wineFreeNextTimeAry){
		this.wineFreeNextTimeAry=wineFreeNextTimeAry;
	}
	public List<Integer> getWineGotCntAry(){
		return this.wineGotCntAry;
	}
	public void setWineGotCntAry(List<Integer> wineGotCntAry){
		this.wineGotCntAry=wineGotCntAry;
	}
	public Map<String, Integer> getComShopPurchased(){
		return this.comShopPurchased;
	}
	public void setComShopPurchased(Map<String, Integer> comShopPurchased){
		this.comShopPurchased=comShopPurchased;
	}
	public Date getShopDayRefresh(){
		return this.shopDayRefresh;
	}
	public void setShopDayRefresh(Date shopDayRefresh){
		this.shopDayRefresh=shopDayRefresh;
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
	public List<Integer> getWineProbCntAry() {
		return wineProbCntAry;
	}
	public void setWineProbCntAry(List<Integer> wineProbCntAry) {
		this.wineProbCntAry = wineProbCntAry;
	}
	public Map<String, Integer> getBoxProbCntAry() {
		return boxProbCntAry;
	}
	public void setBoxProbCntAry(Map<String, Integer> boxProbCntAry) {
		this.boxProbCntAry = boxProbCntAry;
	}
}