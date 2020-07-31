package com.mdy.dzs.game.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.HandbookAction;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.sharp.container.biz.BizException;

public class HandbookActionImpl extends ApplicationAwareAction implements
HandbookAction{

	@Override
	public Map<String, List<Integer>> getAll(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);//取到t_role的数据
		Map<String,List<Integer>> rtnMap = new HashMap<String,List<Integer>>();		
		rtnMap.put("card",roleCardAO().queryRecordCardId(doc.getId()));
		rtnMap.put("equip", roleEquipAO().queryRecordEquipId(doc.getId()));
		rtnMap.put("gong", roleGongAO().queryRecordGongId(doc.getId()));
		
		return rtnMap;
	}
}
