package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.data.domain.union.UnionFBData;
import com.mdy.dzs.game.domain.union.UnionFB;

public class UnionFBAO extends BaseAO {
	/**
	 * 根据帮派id查询帮派副本
	 * 
	 * @param unionId
	 * @return
	 */
	public List<UnionFB> queryUnionFBById(int unionId) {
		return unionFBDAO().queryUnionFBById(unionId);
	}

	/**
	 * 创建帮派副本
	 * 
	 * @param unionFB
	 */
	public void createUnionFB(UnionFB unionFB) {
		unionFBDAO().createUnionFB(unionFB);
	}

	/**
	 * 查询帮派单个副本信息
	 * 
	 * @param unionId
	 * @param type
	 * @param FBId
	 * @return
	 */
	public UnionFB queryUnionFBByFBId(int unionId, int type, int FBId) {
		return unionFBDAO().queryUnionFBByFBId(unionId, type, FBId);
	}

	public void judgeUpadteUnionFBState(int level, List<UnionFBData> uninoDataFBList, int unionId) {
		for (UnionFBData unionFBData : uninoDataFBList) {
			if (unionFBData.getLimitLevle() == level) {
				// UnionFB unionFB = unionFBDAO().queryUnionFBByFBId(unionId,
				// unionFBData.getType(), unionFBData.getFbId());
				unionFBDAO().updateUnionFBState(unionId, unionFBData.getType(), unionFBData.getFbId());
			}
		}
	}

}
