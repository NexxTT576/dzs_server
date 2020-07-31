package com.mdy.dzs.game.domain.role;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.card.RoleCard;

public class RoleLoginInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id ;
	private String account ;
	private String name ;
	private int level ;
	private int vip ;
	private int silver ;
	private int gold ;
	private int physVal ;
	private int resisVal ;
	private int exp ;
	private List<Integer> propLimitAry ;
	private int attack ;
	private int cls ;
	private int resId;
	private int star;
	private String version;
	
	public RoleLoginInfoVO(Role role,RoleCard mainCard){
		id = role.id;
		account = role.account;
		name = role.name;
		level = role.level;
		vip = role.vip;
		silver = role.silver;
		gold = role.gold;
		physVal = role.physVal;
		resisVal = role.resisVal;
		exp = role.exp;
		propLimitAry = role.propLimitAry;
		attack = role.attack;
		cls = mainCard.getCls();
		setStar(mainCard.getStar());
		resId = role.getResId();
		version = role.getVersion();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public int getSilver() {
		return silver;
	}
	public void setSilver(int silver) {
		this.silver = silver;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getPhysVal() {
		return physVal;
	}
	public void setPhysVal(int physVal) {
		this.physVal = physVal;
	}
	public int getResisVal() {
		return resisVal;
	}
	public void setResisVal(int resisVal) {
		this.resisVal = resisVal;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public List<Integer> getPropLimitAry() {
		return propLimitAry;
	}
	public void setPropLimitAry(List<Integer> propLimitAry) {
		this.propLimitAry = propLimitAry;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
