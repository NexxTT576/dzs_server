package com.mdy.dzs.game.domain.union;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ShowAllMemberVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3731494688910584289L;
	/**
	 * 
	 */
	private List<MemberVO> roleUnionList;
	private List<MemberVO> applyList;
	private Date nowTime;
	private int jopType;
	
	public List<MemberVO> getRoleUnionList() {
		return roleUnionList;
	}
	public void setRoleUnionList(List<MemberVO> roleUnionList) {
		this.roleUnionList = roleUnionList;
	}
	public List<MemberVO> getApplyList() {
		return applyList;
	}
	public void setApplyList(List<MemberVO> applyList) {
		this.applyList = applyList;
	}
	public Date getNowTime() {
		return nowTime;
	}
	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}
	public int getJopType() {
		return jopType;
	}
	public void setJopType(int jopType) {
		this.jopType = jopType;
	}
	
}
