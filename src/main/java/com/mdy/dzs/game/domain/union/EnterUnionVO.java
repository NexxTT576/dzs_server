package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EnterUnionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8444709813115928853L;

	private Date exitTime;
	private UnionDataVO union;
	private boolean isInUnion;
	private boolean isChangeCover;
	private int totalNum;
	private CoverVO coverVO;
	private long surplusTime;
	private List<UnionDataVO> unionList;
	public Date getExitTime() {
		return exitTime;
	}
	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}
	public UnionDataVO getUnion() {
		return union;
	}
	public void setUnion(UnionDataVO union) {
		this.union = union;
	}
	public List<UnionDataVO> getUnionList() {
		return unionList;
	}
	public void setUnionList(List<UnionDataVO> unionList) {
		this.unionList = unionList;
	}
	public boolean isInUnion() {
		return isInUnion;
	}
	public void setInUnion(boolean isInUnion) {
		this.isInUnion = isInUnion;
	}
	public boolean isChangeCover() {
		return isChangeCover;
	}
	public void setChangeCover(boolean isChangeCover) {
		this.isChangeCover = isChangeCover;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public CoverVO getCoverVO() {
		return coverVO;
	}
	public void setCoverVO(CoverVO coverVO) {
		this.coverVO = coverVO;
	}
	public long getSurplusTime() {
		return surplusTime;
	}
	public void setSurplusTime(long surplusTime) {
		this.surplusTime = surplusTime;
	}
	
	
}
