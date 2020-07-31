package com.mdy.dzs.game.action.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.gift.YueKaShouChongGift;
import com.mdy.dzs.data.domain.mission.ActivityMissionProperty;
import com.mdy.dzs.data.util.DateUtil;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.MCardAction;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.monthcard.MCardVO;
import com.mdy.dzs.game.domain.monthcard.RoleMonthCard;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.MCardException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

public class MCardActionImpl extends ApplicationAwareAction implements
		MCardAction {
	//月卡活动界面
	@Override
	public MCardVO actPage(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc); 
		RoleMonthCard roleMCardDoc = roleMonthCardAO().query(doc.getId());
		MCardVO rtnObj = new MCardVO();
		//是否已领取 1-已领取 2-未领取
		Date now = new Date();
		int isget = 2;
		if(DateUtil.isSameDay(now, roleMCardDoc.getGetTime())){
			isget = 1;
		}
		rtnObj.setIsget(isget);
		rtnObj.setCost(Constants.yuekaCost);
		rtnObj.setGetGold(Constants.yuekaGoldGet);
		//剩余天数	
		int days = roleMonthCardAO().disDays(roleMCardDoc.getOverTime());
		rtnObj.setDays(days<0? 0: days);
		//isCanBuy: 是否可购买月卡 1-是 2-否
		int isCanBuy = 2;
		if(days <= Constants.yuekaSurplusDays){
			isCanBuy = 1;
		}
		rtnObj.setIsCanBuy(isCanBuy);
		return rtnObj;
	}

	//月卡领取
	@Override
	public Map<String, Object> get(String acc) throws BizException {
		//检查可否领取，
		Role doc = roleAO().queryExistAccount(acc);					
		RoleMonthCard roleMCardDoc = roleMonthCardAO().queryExistId(doc.getId());
		Date now = new Date();
		if(DateUtil.isSameDay(now, roleMCardDoc.getGetTime())){
//			throw BaseException.getGlobalException("today has got");
			throw BaseException.getException(
					MCardException.EXCE_MCARD_HAVE_GOT);
		}
		
		int days = DateUtil.getDiffDay(now, roleMCardDoc.getOverTime());
		if(days < 0){
//			throw BaseException.getGlobalException("can't get");
			throw BaseException.getException(
					MCardException.EXCE_MCARD_CANNOT_GET);
		}
		//发放物品
		YueKaShouChongGift giftList = cacheManager().getExistValueByKey(YueKaShouChongGift.class, 2);
		
		List<Integer> type = giftList.getArrType();
		List<Integer> item = giftList.getArrItem();
		List<Integer> num  = giftList.getArrNum();
		
		int cnt = missionAO().getActivityMissionCntByProperty(
				doc.getId(), 
				ActivityMissionProperty.月卡_奖励翻倍.value());
		
		for(int i = 0; i < type.size(); i++){
			packetAO().addItem(doc, type.get(1), item.get(i), num.get(i)*cnt, RoleItemLog.SYS_礼包_月卡礼包,"");
		}
		//更新领取时间点
		roleMCardDoc.setGetTime(now);
		roleMonthCardAO().updateGetTime(roleMCardDoc);
		
		Map<String, Object> rtnObj = new HashMap<String, Object>();
		rtnObj.put("getResult", 1);
		return rtnObj;
	}

}
