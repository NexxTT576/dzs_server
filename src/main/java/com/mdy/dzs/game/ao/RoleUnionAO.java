package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.union.RoleUnion;
import com.mdy.dzs.game.exception.UnionException;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 玩家帮派信息
 * 
 * @author majiaolong
 * @version 创建时间：2014年12月4日
 */
public class RoleUnionAO extends BaseAO {
	/**
	 * 创建用户帮派信息
	 */
	public void createRoleUnion(int unionId, int roleId, int jopType, int rank) {

		roleUnionDAO().createroleUnion(RoleUnion.ValueOf(unionId, roleId, jopType, rank));

	}

	/**
	 * 根据玩家id查询玩家帮派信息
	 */
	public RoleUnion queryRoleUnionById(int roleId) throws BizException {

		return roleUnionDAO().queryRoleUnionById(roleId);
	}

	/**
	 * 查询帮派人数
	 * 
	 * @param unionId
	 * @return
	 */
	public int queryAllMemberNum(int unionId) {

		return roleUnionDAO().queryAllMemberNum(unionId);
	}

	/**
	 * 更新玩家帮派信息
	 * 
	 * @param roleId
	 * @param unionId
	 * @param joptype
	 */
	public void updateRoleUnion(RoleUnion roleUnion) {
		roleUnionDAO().updateRoleUnion(roleUnion);
	}

	/**
	 * 修改 玩家作坊信息
	 * 
	 * @param ru
	 */
	public void updateRoleProduct(RoleUnion ru) {
		roleUnionDAO().updateRoleProduct(ru);
	}

	/**
	 * 查询帮派所有的成员
	 * 
	 * @param unionId
	 * @return
	 */
	public List<RoleUnion> queryAllMember(int unionId) {

		return roleUnionDAO().queryAllMember(unionId);
	}

	/**
	 * 清除用户信息
	 * 
	 * @param roleUnion
	 */
	public void clearRoleUnion(RoleUnion roleUnion, int unionId) {
		roleUnionDAO().clearRoleUnion(RoleUnion.clearUnion(roleUnion), unionId);
	}

	/**
	 * 修改自荐信息
	 * 
	 * @param roleId
	 * @param time
	 */
	public void updatecoverLeader(int roleId, Date time, int unionId) {

		roleUnionDAO().updatecoverLeader(roleId, time, unionId);
	}

	/**
	 * 修改每周福利时间
	 * 
	 * @param roleId
	 * @param time
	 */
	public void updateBenefits(Role role, Date time, int gift, RoleUnion roleUnion) {
		roleUnionDAO().updateBenefits(role.getId(), time, gift, roleUnion.getUnionId());
	}

	/**
	 * 领取烧烤奖励
	 * 
	 * @param roleId
	 * @param time
	 * @param gift
	 */
	public void openBarbecue(RoleUnion roleUnion, Date time, int gift) {

		roleUnionDAO().openBarbecue(roleUnion, time, gift);
	}

	/**
	 * 修改自荐状态
	 */
	public void updateRoleCover(int unionId) {
		// 查询所有帮派管理者
		List<RoleUnion> roleList = roleUnionDAO().queryAllMember(unionId);
		for (RoleUnion roleUnion : roleList) {
			if (roleUnion.getJopType() == RoleUnion.elders || roleUnion.getJopType() == RoleUnion.vice) {
				updatecoverLeader(roleUnion.getRoleId(), DateUtil.getDayTime(""), roleUnion.getUnionId());
			}
		}
	}

	/**
	 * 查找帮派继承人
	 * 
	 * @param unionId
	 * @return
	 */
	public RoleUnion queryHeir(int unionId) {
		RoleUnion heir = new RoleUnion();
		// List<RoleUnion> leaderList = roleUnionDao().queryAllLeader(unionId);
		List<RoleUnion> allList = roleUnionDAO().queryAllMember(unionId);
		// 副帮主
		List<RoleUnion> vList = new ArrayList<RoleUnion>();
		// 长老
		List<RoleUnion> eList = new ArrayList<RoleUnion>();
		// 成员
		List<RoleUnion> mList = new ArrayList<RoleUnion>();
		// 成员分类
		for (RoleUnion roleUnion : allList) {
			if (roleUnion.getJopType() == RoleUnion.vice) {
				vList.add(roleUnion);
				continue;
			}
			if (roleUnion.getJopType() == RoleUnion.elders) {
				eList.add(roleUnion);
				continue;
			}
			if (roleUnion.getJopType() != RoleUnion.leader) {
				mList.add(roleUnion);
			}

		}

		if (!vList.isEmpty()) {
			heir = vList.get(0);
			return heir;
		} else if (vList.isEmpty() && !eList.isEmpty()) {
			return choiceBetterOne(eList);
		}
		heir = choiceBetterOne(mList);
		//
		return heir;
	}

	public RoleUnion choiceBetterOne(List<RoleUnion> rList) {
		RoleUnion isbetter = new RoleUnion();
		// if(roleB.getTotalContribute() > roleA.getTotalContribute()){
		// isbetter = true;
		// }
		for (RoleUnion roleUnion : rList) {
			if (isbetter.getRoleId() == 0) {
				isbetter = roleUnion;
				continue;
			}
			if (isbetter.getTotalContribute() < roleUnion.getTotalContribute()) {
				isbetter = roleUnion;
			}
		}
		return isbetter;
	}

	/**
	 * 更新玩家职务
	 */
	public void updateRoleJop(RoleUnion roleUnion, int jopType) {

		roleUnionDAO().updateRoleJop(roleUnion, jopType);
	}

	/**
	 * 查询职务人数
	 */
	public int queryJopNumByJopType(int unionId, int jopType) {
		int count = 0;
		List<RoleUnion> allList = roleUnionDAO().queryAllMember(unionId);
		for (RoleUnion roleUnion : allList) {
			if (roleUnion.getJopType() == jopType) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 修改贡献
	 */
	public void updateUnionRoleTLContribute(int contribute, int unionid, int roleid, int conType, int costMoney) {
		roleUnionDAO().updateUnionRoleTLContribute(contribute, unionid, roleid, conType, costMoney);
	}

	/***
	 * 更新角色帮贡 累加或削减 累加total时同时增加last
	 */
	public int updateContribute(RoleUnion r, int val) throws BizException {
		int totalContribute = r.getTotalContribute();
		totalContribute += val;

		if (totalContribute < 0) {
			throw UnionException.getException(UnionException.EXCE_UNION_ROLE_TOTALCONTRIBUTE_NOT_ENOUGH,
					r.getTotalContribute(), val);
		}
		r.setLastContribute(r.getLastContribute() + val);
		roleUnionDAO().updateLastContribute(r, val);

		if (val > 0) {
			r.setTotalContribute(totalContribute);
			roleUnionDAO().updateTotalContribute(r, val);
		}

		return totalContribute;
	}

	public List<RoleUnion> queryCoverRoleUnion(int unionId) {
		List<RoleUnion> roleList = roleUnionDAO().queryAllMember(unionId);
		List<RoleUnion> roleUnionList = new ArrayList<RoleUnion>();
		for (RoleUnion roleUnion : roleList) {
			if (roleUnion.getJopType() == RoleUnion.elders || roleUnion.getJopType() == RoleUnion.vice) {
				if (DateUtil.isInDays(roleUnion.getCoverTime(), 2)) {
					roleUnionList.add(roleUnion);
				}
			}

		}
		Collections.sort(roleUnionList, new Comparator<RoleUnion>() {
			@Override
			public int compare(RoleUnion b1, RoleUnion b2) {
				return b2.getCoverTime().compareTo(b1.getCoverTime());
			}
		});
		return roleUnionList;
	}

	/**
	 * 定时修改帮派玩家的排名挑战次数
	 */
	public void updateRoleInfo(RoleUnion roleUnion) {
		roleUnionDAO().updateRoleInfo(roleUnion);
	}

	/**
	 * 修改工坊领奖状态
	 */
	public void updateWShopRewardState(RoleUnion roleUnion) {
		roleUnionDAO().updateWShopRewardState(roleUnion);
	}

	/**
	 * 修改个人排名
	 */
	public void updateRoleUnionAtt(RoleUnion roleUnion) {
		roleUnionDAO().updateRoleUnionAtt(roleUnion);
	}

	/**
	 * 修改帮派兑换商品信息
	 */
	public void updateExchangeShopInfo(RoleUnion roleUnion, List<Integer> ids) {
		roleUnionDAO().updateExchangeShopInfo(roleUnion, ids);
	}
}
