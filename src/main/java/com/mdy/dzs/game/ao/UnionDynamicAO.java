package com.mdy.dzs.game.ao;


import java.util.ArrayList;
import java.util.List;



import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.union.DynamicVO;
import com.mdy.dzs.game.domain.union.UnionDynamic;
import com.mdy.sharp.container.biz.BizException;
import com.mdy.sharp.util.JSONUtil;


public class UnionDynamicAO extends BaseAO {

	/**
	 * 添加动态
	 */
     public void createDynamic(UnionDynamic unionDynamic){
    	 unionDynamicDAO().createDynmic(unionDynamic);
    	 
     }
     /**
      * 加载动态列表
      */
     public List<UnionDynamic> queryDynamicList(int roleId){
    	 
    	return unionDynamicDAO().queryDynamicList(roleId);
     }
     /**
      * 捐献列表 大殿
      */
	public List<Object> queryConDynamicList(int unionId) {
		List<Object> dList = new ArrayList<Object>();
		List<UnionDynamic> udList = unionDynamicDAO().queryDynamicList(unionId);
		for (UnionDynamic unionDynamic : udList) {
			if (unionDynamic.getType() == 3 || unionDynamic.getType() == 4) {
				//dList.add(JSONUtil.fromJson(unionDynamic.getDes(), Object.));
				dList.add(JSONUtil.fromJson(unionDynamic.getDes(), List.class)
						);
				if(dList.size() == 30){
					return dList;
				}
			}
		}
		return dList;
	}
	/**
	 * 动态列表
	 * @param unionId
	 * @return
	 * @throws BizException 
	 */
	public List<DynamicVO> queryAllConDynamicList(int unionId,int maxNum) throws BizException {
		List<DynamicVO> dList = new ArrayList<DynamicVO>();
		List<UnionDynamic> udList = unionDynamicDAO().queryDynamicList(unionId);
		for (UnionDynamic unionDynamic : udList) {
			Role role = roleDAO().queryById(unionDynamic.getRoleId());
			if (UnionDynamic.GREEN_DRAGON_TYPE.contains(unionDynamic.getType())) {// 青龙堂类型
				dList.add(DynamicVO.tidyGreenDragonDynamic(unionDynamic));
			} else {
				dList.add(DynamicVO.valueOf(unionDynamic, role));
			}
		}
		if (udList.size() > maxNum) {
			for (int i = maxNum; i < udList.size(); i++) {
				if(udList.get(i)  != null){
				unionDynamicDAO().deleteDynamic(udList.get(i));
				}
			}
		}
		return dList;
	}
}
