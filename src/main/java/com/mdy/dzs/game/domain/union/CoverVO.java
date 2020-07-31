package com.mdy.dzs.game.domain.union;

import java.io.Serializable;

public class CoverVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7867159397671398005L;
	private String firstName;
	private String secondName;
	private String threeName;
	private long time;
	private int leaderId;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getThreeName() {
		return threeName;
	}
	public void setThreeName(String threeName) {
		this.threeName = threeName;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}
	
}
