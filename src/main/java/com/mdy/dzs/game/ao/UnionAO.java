package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.union.UnionAttr;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.union.Union;
import com.mdy.dzs.game.domain.union.UnionApply;
import com.mdy.dzs.game.domain.union.UnionDataVO;
import com.mdy.dzs.game.domain.union.UnionDynamic;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.UnionException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.sharp.container.biz.BizException;

public class UnionAO extends BaseAO {
	private CacheManager cacheManager;

	public UnionAO(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * 通过帮派id 查询帮派信息
	 * 
	 * @param unionId
	 * @return
	 */
	public Union queryUnionByUnionId(int unionId) {
		return unionDAO().queryUnionById(unionId);
	}

	/**
	 * 升级建筑
	 */
	public void upUnioncenter(Union union, int buildtype, int costMoney, int roleAdd) {
		unionDAO().upUnioncenter(union, buildtype, costMoney, roleAdd);
	}

	public void createUnionLog(UnionDynamic unionDynamic) {
		unionDynamicDAO().createDynmic(unionDynamic);
	}

	/**
	 * 修改帮派贡献
	 * 
	 * @param upunionget
	 * @param union
	 */
	public void updateUnionTotalAndCurrentMoney(int upunionget, Union union) {
		unionDAO().updateUnionTotalAndCurrentMoney(union, upunionget);
	}

	/**
	 * 创建帮派
	 * 
	 * @param roleId
	 * @param name
	 * @param attack
	 * @return
	 */
	public int createUion(int roleId, String name, int attack, int rank) {
		int uid = unionDAO().createUnion(Union.ValueOf(name, roleId, attack, rank));
		return uid;
	}

	/**
	 * 查询帮派信息
	 * 
	 * @param unionId
	 * @return
	 */
	public Union queryUinonById(int unionId) throws BizException {
		Union union = unionDAO().queryUnionById(unionId);
		if (union == null) {
			throw BaseException.getException(UnionException.EXCE_UNION_IS_NOT_EXIST);
		}
		return union;
	}

	/**
	 * 根据帮派名称查询
	 * 
	 * @param name
	 * @return
	 */
	public Union queryUinonByName(String name) {

		return unionDAO().queryUinonByName(name);
	}

	/**
	 * 修改公告
	 * 
	 * @param unionId
	 * @param msg
	 */
	public void updateUnionIndes(Union union, String msg, int type) {

		unionDAO().updateUnionIndes(union, msg, type);
	}

	public List<UnionDataVO> queryUnionBypage(int page, int roleId) {
		List<UnionApply> roleApllyList = unionApplyDAO().queryRoleApplyList(roleId);
		List<Union> ulist = unionDAO().queryUnionBypage(0);
		List<UnionDataVO> dataList = new ArrayList<UnionDataVO>();
		if (roleApllyList != null) {
			for (Union union : ulist) {
				UnionApply unionApply = unionApplyDAO().queryApply(roleId, union.getId());
				Role role = roleDAO().queryById(union.getBossId());
				UnionDataVO unionDataVO;
				int nowroleNum = roleUnionDAO().queryAllMemberNum(union.getId());
				if (unionApply == null) {
					unionDataVO = union.getValue(false, role, nowroleNum);
				} else {
					unionDataVO = union.getValue(true, role, nowroleNum);
				}
				int roleNum = roleUnionDAO().queryAllMemberNum(union.getId());
				if (roleNum >= union.getRoleMaxNum() && unionDataVO.isApply() == false) {

				} else {
					unionDataVO.setNowRoleNum(roleNum);
					dataList.add(unionDataVO);
				}
			}
		}
		return dataList;
	}

	/**
	 * 修改帮派自荐信息
	 */
	public void updateUnionCover(Union union, Date nowTime) {
		unionDAO().updateUnionCover(union, nowTime);
	}

	/**
	 * 解散帮派
	 * 
	 * @param unionId
	 */
	public void deleteUnion(Union union) {

		unionDAO().deleteUnion(union);
	}

	/**
	 * 查询帮派
	 * 
	 * @param unionName
	 * @return
	 */
	public List<UnionDataVO> queryUinonListByName(String unionName, int roleId) {

		List<Union> unionList = unionDAO().queryUinonListByName(unionName);
		List<UnionDataVO> dataList = new ArrayList<UnionDataVO>();
		if (unionList != null) {
			for (Union union : unionList) {
				UnionApply unionApply = unionApplyDAO().queryApply(roleId, union.getId());
				Role role = roleDAO().queryById(union.getBossId());
				int nowroleNum = roleUnionDAO().queryAllMemberNum(union.getId());
				UnionDataVO unionDataVO;
				if (unionApply == null) {
					unionDataVO = union.getValue(false, role, nowroleNum);
				} else {
					unionDataVO = union.getValue(true, role, nowroleNum);
				}
				dataList.add(unionDataVO);

			}
		}
		return dataList;

	}

	/**
	 * 展示列表
	 * 
	 * @return
	 */
	public List<UnionDataVO> showUnionRank() {
		List<Union> unionList = unionDAO().queryUnionBypage(1);
		List<UnionDataVO> dataList = new ArrayList<UnionDataVO>();
		if (unionList != null) {
			for (Union union : unionList) {
				if (dataList.size() >= 50) {
					return dataList;
				}
				Role role = roleDAO().queryById(union.getBossId());
				int nowRoleNum = roleUnionDAO().queryAllMemberNum(union.getId());
				UnionDataVO unionDataVO = union.getValue(false, role, nowRoleNum);
				dataList.add(unionDataVO);
			}
		}
		return dataList;
	}

	// 获取建筑当前等级 //buildtype: 1大殿，2作坊，3商店，4地洞，5青龙堂，6白虎堂,7帮派副本
	public int getBuildTypeLv(Union adv, int buildtype) {
		int lv = 0;
		if (buildtype == 0) {// 大殿的前置建筑
			return 100;
		}
		switch (buildtype) {
		case Union.UNION_TYPE_MAIN_PALACE: // 大殿升级
			lv = adv.getLevel();
			break;
		case Union.UNION_TYPE_WORK_SHOP: // 工坊
			lv = adv.getWorkShopLevel();
			break;
		case Union.UNION_TYPE_SHOP: // 商店
			lv = adv.getShopLevel();
			break;
		case Union.UNION_TYPE_DRAGON: // 青龙堂
			lv = adv.getGreenDragonTempleLevel();
		case Union.UNION_TYPE_FB: // 帮派副本
			lv = adv.getFbLevel();
			break;
		}
		return lv;
	}

	// 获取建筑最大等级
	public int getBuildTypeLvMax(Map<Integer, UnionAttr> udata, int buildtype) {
		int maxLv = 0;
		for (Entry<Integer, UnionAttr> entry : udata.entrySet()) {
			UnionAttr union = entry.getValue();
			if (union.getType() == buildtype && union.getLevel() > maxLv) {
				maxLv = union.getLevel();
			}
		}
		return maxLv;
	}

	/**
	 * 修改帮派信息
	 */
	public void reduceUnionMoney(Union union, int costMoney) {
		unionDAO().reduceUnionMoney(union, costMoney);
	}

	/**
	 * 修改帮派攻击力
	 * 
	 * @param sumAttack
	 */
	public void updateUnionSumAttack(Union union, int sumAttack) {
		unionDAO().updateUnionSumAttack(union, sumAttack);

	}

	/**
	 * 查询所有帮派
	 */
	public List<Union> queryAllUnion() {
		List<Union> unionList = unionDAO().queryUnionBypage(0);
		return unionList;
	}

	/**
	 * 修改帮主id
	 */
	public void updateBossInfo(Union union) {
		unionDAO().updateBossInfo(union);
	}

	/**
	 * 修改帮派战斗力
	 */
	public void updateUnionAtt(StringBuffer ids, int unionId) {
		unionDAO().updateUnionAtt(ids, unionId);
	}

	/**
	 * 修改帮派排行
	 */
	public void updateUnionRank(Union union, int rank) {
		unionDAO().updateUnionRank(union, rank);

	}
}
