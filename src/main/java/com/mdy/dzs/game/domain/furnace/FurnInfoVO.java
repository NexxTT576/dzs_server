package com.mdy.dzs.game.domain.furnace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.equip.Equip;

/**
 * 炼化vo
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月23日  下午9:02:09
 */
public class FurnInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int resId;
	private int level;
	private int star;
	
	
	private List<Integer> base;
	private Integer silver;
	
	private Integer cls;
	private Integer curExp;
	
	
	private List<ProbItem> rtn;
	
	/**可炼化*/
	private int refining;
	/**可重生*/
	private int reborn;
	/**重生的返还*/
	private List<ProbItem> rtnReborn;
	/**重生消耗*/
	private int cost;

	public FurnInfoVO(Equip equip,List<Integer> base) {
		id = equip.getId();
		resId = equip.getResId();
		level = equip.getLevel();
		star = equip.getStar();
		this.base = base;
		silver = equip.getSilver();
		rtn = new ArrayList<ProbItem>();
	}
	
	public FurnInfoVO(RoleCard card) {
		id = card.getId();
		resId = card.getResId();
		level = card.getLevel();
		star = card.getStar();
		cls = card.getCls();
		curExp = card.getCurExp();
		rtn = new ArrayList<ProbItem>();
	}
	
	public Integer getCls() {
		return cls;
	}

	public void setCls(Integer cls) {
		this.cls = cls;
	}

	public Integer getCurExp() {
		return curExp;
	}

	public void setCurExp(Integer curExp) {
		this.curExp = curExp;
	}

	public void setSilver(Integer silver) {
		this.silver = silver;
	}
	public Integer getSilver() {
		return silver;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public List<Integer> getBase() {
		return base;
	}
	public void setBase(List<Integer> base) {
		this.base = base;
	}
	public List<ProbItem> getRtn() {
		return rtn;
	}
	public void setRtn(List<ProbItem> rtn) {
		this.rtn = rtn;
	}

	public int getRefining() {
		return refining;
	}

	public void setRefining(int refining) {
		this.refining = refining;
	}

	public int getReborn() {
		return reborn;
	}

	public void setReborn(int reborn) {
		this.reborn = reborn;
	}

	public List<ProbItem> getRtnReborn() {
		return rtnReborn;
	}

	public void setRtnReborn(List<ProbItem> rtnReborn) {
		this.rtnReborn = rtnReborn;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
