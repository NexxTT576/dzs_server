package com.mdy.dzs.game.domain.role;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.Prop;


/**
 * 人物经脉模型
 * @author 房曈
 *
 */
public class RoleChannel implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/***/
	private int id;
	/**对应账号*/
	private int roleId;
	/**经脉类型*/
	private int chanType;
	/**经脉等级*/
	private int chanLv;
	/**经脉穴位*/
	private int chanPt;
	/**经脉属性累加数组*/
	private List<Prop> chanAttrAry;
	/**经脉升级消耗星星数*/
	private int chanStarCnt;
	/**洗经伐脉计数*/
	private int chanReSetCnt;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getRoleId(){
		return this.roleId;
	}
	public void setRoleId(int roleId){
		this.roleId=roleId;
	}
	public int getChanType(){
		return this.chanType;
	}
	public void setChanType(int chanType){
		this.chanType=chanType;
	}
	public int getChanLv(){
		return this.chanLv;
	}
	public void setChanLv(int chanLv){
		this.chanLv=chanLv;
	}
	public int getChanPt(){
		return this.chanPt;
	}
	public void setChanPt(int chanPt){
		this.chanPt=chanPt;
	}
	public List<Prop> getChanAttrAry(){
		return this.chanAttrAry;
	}
	public void setChanAttrAry(List<Prop> chanAttrAry){
		this.chanAttrAry=chanAttrAry;
	}
	public int getChanStarCnt(){
		return this.chanStarCnt;
	}
	public void setChanStarCnt(int chanStarCnt){
		this.chanStarCnt=chanStarCnt;
	}
	public int getChanReSetCnt(){
		return this.chanReSetCnt;
	}
	public void setChanReSetCnt(int chanReSetCnt){
		this.chanReSetCnt=chanReSetCnt;
	}
}