package com.mdy.dzs.game.domain.gong;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mdy.dzs.game.domain.IdObject;
import com.mdy.dzs.game.domain.Prop;

/**
 * 内外功模型
 * 
 * @author 房曈
 *
 */
public class RoleGong implements Serializable, IdObject {

	/** 序列化id */
	private static final long serialVersionUID = 1L;

	private int id;
	/***/
	private int roleId;
	/** 1-6上阵, 0未上阵 */
	private int pos;
	/** 子位置1-6 */
	private int subpos;
	/***/
	private int resId;
	/***/
	private int type;
	/***/
	private int star;
	/***/
	private int level;
	/** 当前经验值 */
	private int curExp;
	/** 基础属性加成(生命,攻击,物防,法防) */
	private List<Integer> baseRate;
	/** 精炼属性 */
	private List<Prop> props;
	/** 精炼次数 */
	private int propsN;
	/** 羁绊 */
	private List<Integer> relation;

	////////////////////////////////////
	private int order;

	private int cid;

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPos() {
		return this.pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getSubpos() {
		return this.subpos;
	}

	public void setSubpos(int subpos) {
		this.subpos = subpos;
	}

	public int getResId() {
		return this.resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStar() {
		return this.star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCurExp() {
		return this.curExp;
	}

	public void setCurExp(int curExp) {
		this.curExp = curExp;
	}

	@JsonProperty(value = "baseRate")
	public List<Integer> getBaseViewRate() {
		return this.baseRate;
	}

	public void setBaseViewRate(List<Integer> baseRate) {
		this.baseRate = baseRate;
	}

	public List<Prop> getProps() {
		return this.props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public int getPropsN() {
		return this.propsN;
	}

	public void setPropsN(int propsN) {
		this.propsN = propsN;
	}

	public List<Integer> getRelation() {
		return this.relation;
	}

	public void setRelation(List<Integer> relation) {
		this.relation = relation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getObjId() {
		return id;
	}

	public void setObjId(int id) {
		this.id = id;
	}
}