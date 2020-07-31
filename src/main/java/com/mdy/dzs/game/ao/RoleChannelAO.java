package com.mdy.dzs.game.ao;

import java.util.ArrayList;

import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.role.RoleChannel;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleChannelException;
import com.mdy.sharp.container.biz.BizException;

/**
 * 人物经脉
 * 
 * @author 房曈
 *
 */
public class RoleChannelAO extends BaseAO {
	//
	/**
	 * 查询
	 */
	public RoleChannel queryExistAccount(int roleId) throws BizException {
		RoleChannel channel = roleChannelDAO().queryByAccount(roleId);
		if (channel == null) {
			// throw BaseException.getGlobalException(
			// "rolechannel not exist:"+roleId);
			throw BaseException.getException(RoleChannelException.EXCE_ROLECHANNEL_NOT_EXIST, roleId);
		}
		return channel;
	}

	/**
	 * 添加
	 * 
	 * @param RoleChannel
	 */
	public void addRoleChannel(int roleId) {
		RoleChannel rc = new RoleChannel();
		rc.setRoleId(roleId);
		rc.setChanType(0);
		rc.setChanLv(0);
		rc.setChanPt(0); // 经脉穴位
		rc.setChanAttrAry(new ArrayList<Prop>()); // 经脉属性累加数组PropSchema
		rc.setChanStarCnt(0); // 经脉升级消耗星星数
		rc.setChanReSetCnt(0);
		roleChannelDAO().add(rc);
	}

	/**
	 * 更新
	 * 
	 * @param RoleChannel
	 */
	public void update(RoleChannel rc) {
		roleChannelDAO().update(rc);
	}
}