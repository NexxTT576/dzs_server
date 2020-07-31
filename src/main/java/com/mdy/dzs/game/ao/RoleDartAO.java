package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.dart.YabiaoJiangli;
import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.dart.DartCarVO;
import com.mdy.dzs.game.domain.dart.DartIdAndStartTime;
import com.mdy.dzs.game.domain.dart.RoleDart;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 押镖表结构
 * 
 * @author 白雪林
 *
 */
public class RoleDartAO extends BaseAO {
	private CacheManager cacheManager;
	private RoleAO roleAO;
	private PacketAO packetAO;

	public RoleDartAO(CacheManager cacheManager, RoleAO roleAO, PacketAO packetAO) {
		this.cacheManager = cacheManager;
		this.roleAO = roleAO;
		this.packetAO = packetAO;
	}

	// 查询一个玩家数据,null则添加
	public RoleDart queryAndAdd(int roleId) {
		RoleDart dart = roleDartDAO().query(roleId);
		if (dart == null) {
			roleDartDAO().add(roleId);
			dart = roleDartDAO().query(roleId);
		}
		if (!DateUtil.isToday(dart.getDayRefreshTime())) {
			roleDartDAO().updateDayRefresh(roleId);
			dart = roleDartDAO().query(roleId);
		}
		return dart;
	}

	// 查询其他镖车列表
	private List<RoleDart> queryDarts(RoleDart dart) {
		List<DartIdAndStartTime> dartIdAndStartList = roleDartDAO().queryDartIds(dart.getRoleId(),
				"(" + dart.getRoleId() + ")");
		// 提取list,与界面显示数量不冲突的
		dart.setShowMap(new HashMap<String, List<Integer>>());
		List<Integer> dartIdList = transGetRoleId(dart, dartIdAndStartList);
		List<RoleDart> dartList = new ArrayList<RoleDart>();
		if (dartIdList.size() > 0) {
			dartList = roleDartDAO().queryDarts(tidyString(dartIdList));
		}
		return dartList;
	}

	// 考虑当前界面显示上线，返回roleId，为要查询整条数据的roleId
	private List<Integer> transGetRoleId(RoleDart dart, List<DartIdAndStartTime> dartIdAndStartList) {
		List<Integer> dartRoleIdList = new ArrayList<Integer>();
		int nowLength = dart.getOtherRoleList().size();

		for (DartIdAndStartTime curData : dartIdAndStartList) {
			if (nowLength >= cacheManager.getYaBiaoConfig("numlimit").get(0)) {
				break;
			}
			// 当前组上限
			String mapKey = DateUtil.getMin(curData.getStartTime()) / cacheManager.getYaBiaoConfig("interval").get(0)
					+ "";
			if (dart.getShowMap().containsKey(mapKey) && dart.getShowMap().get(mapKey).size() >= 4) {
				continue;
			}
			List<Integer> idList = new ArrayList<Integer>();
			if (dart.getShowMap().containsKey(mapKey)) {
				idList = dart.getShowMap().get(mapKey);
			}
			idList.add(curData.getRoleId());
			// {key:[roleId,roleId...]}
			dart.getShowMap().put(mapKey, idList);
			nowLength++;
			dartRoleIdList.add(curData.getRoleId());
		}
		return dartRoleIdList;
	}

	// 更新可见镖车列表 roleId
	public void updateOtherRoleList(RoleDart dart, List<DartCarVO> otherDartCar, Boolean isRefreshTime) {
		List<Integer> roleIdList = new ArrayList<Integer>();
		int seconds = 0;
		for (DartCarVO curData : otherDartCar) {
			roleIdList.add(curData.getRoleId());
		}
		if (isRefreshTime) {
			seconds = cacheManager.getYaBiaoConfig("refreshcd").get(0);
		}
		roleDartDAO().updateOtherRoleList(dart, roleIdList, seconds, isRefreshTime);
	}

	// 更新镖车品质，刷新次数
	public void updateQualityAndRefreshtimes(RoleDart dart, int quality) {
		roleDartDAO().updateQualityAndRefreshtimes(dart.getRoleId(), quality);
	}

	// 更新当前状态 1-押镖结束 2-压镖中 3-押镖完成
	public void updateCurState(RoleDart dart) {
		roleDartDAO().updateCurState(dart);
	}

	// 更新押镖结束时间
	public void updateSpeedUpEnd(RoleDart dart) {
		roleDartDAO().updateSpeedUpEnd(dart);
	}

	// 更新开始押镖数据 startTime endTime
	public void updateStart(RoleDart dart) {
		int minute = cacheManager.getYaBiaoConfig("escorttime").get(0);
		roleDartDAO().updateStart(dart, minute);
	}

	// 被劫镖次数累加
	public void updateBeRobTimesAdd(RoleDart oDart) {
		roleDartDAO().updateBeRobTimesAdd(oDart);
	}

	// 劫镖次数累加
	public void updateRobTimesAdd(RoleDart mDart) {
		roleDartDAO().updateRobTimesAdd(mDart);
	}

	// 查询被劫镖次数
	public int queryBeRobNum(int otherID) {
		return roleDartDAO().queryBeRobNum(otherID);
	}

	// 当前押镖状态 1-押镖结束 2-压镖中 3-押镖完成
	public int getselfState(RoleDart dart, Date now) {
		int state = RoleDart.DART_STATE_END;

		if (dart.getCurState() == RoleDart.DART_STATE_DARTING) {// 数据记录在压镖中
			if (dart.getEndTime().after(now)) {// 结束时间晚于当前 押镖中
				state = RoleDart.DART_STATE_DARTING;
			} else {
				state = RoleDart.DART_STATE_FINISH;
			}
		}
		return state;
	}

	// 下次刷新其他镖车时间倒记秒
	public int getRefreshTime(RoleDart dart, Date now) {
		int second = 0;
		if (dart.getNextRefreshTime().after(now)) {
			second = (int) ((dart.getNextRefreshTime().getTime() - now.getTime()) / 1000);
		}
		return second;
	}

	// 押镖奖励列表
	public List<GiftItem> getJiangli(RoleDart dart, Role doc) throws BizException {
		YabiaoJiangli jiangliData = cacheManager.getExistValueByKey(YabiaoJiangli.class, dart.getDartCarQuality());

		int silver = (int) Math.ceil(jiangliData.getFix().get(0) * doc.getLevel() * jiangliData.getRatio().get(0));
		int popul = (int) Math.ceil(jiangliData.getFix().get(1) * doc.getLevel() * jiangliData.getRatio().get(1));
		// 减掉被劫部分
		float ratio = dart.getBeRobNum() * cacheManager.getYaBiaoConfig("lossratio").get(0) / 100;
		silver = (int) Math.ceil(silver - silver * ratio);
		popul = (int) Math.ceil(popul - popul * ratio);
		// 是否在送双倍时段内 12:00-13:00
		String timesFirsts = cacheManager.getYaBiaoConfig("firstrewardtime").get(0).toString() + ":"
				+ cacheManager.getYaBiaoConfig("firstrewardtime").get(1).toString();
		String timesFirste = cacheManager.getYaBiaoConfig("firstrewardtime").get(2).toString() + ":"
				+ cacheManager.getYaBiaoConfig("firstrewardtime").get(3).toString();
		String timesSeconds = cacheManager.getYaBiaoConfig("secondrewardtime").get(0).toString() + ":"
				+ cacheManager.getYaBiaoConfig("secondrewardtime").get(1).toString();
		String timesSeconde = cacheManager.getYaBiaoConfig("secondrewardtime").get(2).toString() + ":"
				+ cacheManager.getYaBiaoConfig("secondrewardtime").get(3).toString();

		if (DateUtil.isNowInHourSecond(timesFirsts, timesFirste)
				|| DateUtil.isNowInHourSecond(timesSeconds, timesSeconde)) {
			silver += silver * 0.5;
			popul += popul * 0.5;
		}

		List<GiftItem> items = new ArrayList<GiftItem>();
		items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_银币, silver));
		items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_声望, popul));
		// 额外奖励
		List<ProbItem> rtnItemAry = new ArrayList<ProbItem>();
		rtnItemAry = cacheManager.probGot(jiangliData.getProb1());
		for (ProbItem item : rtnItemAry) {
			items.add(new GiftItem(item.getT(), item.getId(), item.getN()));
		}
		rtnItemAry = cacheManager.probGot(jiangliData.getProb2());
		for (ProbItem item : rtnItemAry) {
			items.add(new GiftItem(item.getT(), item.getId(), item.getN()));
		}
		for (GiftItem item : items) {
			packetAO.addItem(doc, item.getType(), item.getId(), item.getNum(), RoleItemLog.SYS_押镖_押镖获得, "");
		}

		return items;
	}

	// 镖车状态
	public DartCarVO getDartCar(Role doc, RoleDart dart) {
		int arriveTime = 0;
		Date now = new Date();
		if (dart.getEndTime().after(now)) {
			arriveTime = (int) ((dart.getEndTime().getTime() - now.getTime()) / 1000);
		}
		return new DartCarVO(doc.getId(), doc.getName(), doc.getLevel(), dart.getDartCarQuality(), arriveTime,
				dart.getTodayDartNum());
	}

	// 其他运镖车
	public List<DartCarVO> getOtherDartCarList(RoleDart dart) {
		List<RoleDart> dartList = queryDarts(dart);
		List<DartCarVO> carList = tidyCarList(dartList);

		return carList;
	}

	// 整理镖车列表
	private List<DartCarVO> tidyCarList(List<RoleDart> dartList) {
		List<DartCarVO> carList = new ArrayList<DartCarVO>();
		Date now = new Date();
		for (RoleDart curDart : dartList) {
			Role curDoc = roleAO.queryById(curDart.getRoleId());
			int seconds = (int) ((curDart.getEndTime().getTime() - now.getTime()) / 1000);
			carList.add(new DartCarVO(curDart.getRoleId(), curDoc.getName(), curDoc.getLevel(),
					curDart.getDartCarQuality(), seconds, curDart.getTodayDartNum()));
		}
		return carList;
	}

	// 补充镖车
	public List<DartCarVO> replenishCarList(RoleDart dart, Boolean isRemoveAllEnd, List<Integer> repairIds) {
		List<Integer> oldList = dart.getOtherRoleList();
		List<Integer> overList = repairIds;
		// 删除已完成镖车
		if (isRemoveAllEnd && oldList.size() > 0) {
			List<DartIdAndStartTime> overDataList = roleDartDAO().queryOverListId(tidyString(oldList));
			for (DartIdAndStartTime curData : overDataList) {
				overList.add(curData.getRoleId());
			}
		}
		for (int curInt : overList) {// 遍历 已完成的的镖车
			if (oldList.contains(curInt)) {
				// 删除list中已完成的镖车
				Iterator<Integer> iter = oldList.iterator();
				while (iter.hasNext()) {
					Integer s = iter.next();
					if (s.equals(curInt)) {
						iter.remove();
						break;
					}
				}

				// 找到map中的list存在这个roleId的 删掉
				for (Entry<String, List<Integer>> entry : dart.getShowMap().entrySet()) {
					if (entry.getValue().contains(curInt)) {

						Iterator<Integer> itert = entry.getValue().iterator();
						while (itert.hasNext()) {
							Integer s = itert.next();
							if (s.equals(curInt)) {
								itert.remove();
								break;
							}
						}

					}
				}
			}
		}
		// 查找补充数据
		String findString = "(";
		for (int curData : oldList) {
			findString += curData + ",";
		}
		findString += dart.getRoleId() + ",";
		findString = findString.substring(0, findString.length() - 1);
		findString += ")";

		List<DartIdAndStartTime> dartIdAndStartList = roleDartDAO().queryDartIds(dart.getRoleId(), findString);
		List<Integer> dartIdList = transGetRoleId(dart, dartIdAndStartList);

		// 整理刷镖车列表 查镖车数据
		if (isRemoveAllEnd) {
			dartIdList.addAll(oldList);
		}
		List<RoleDart> dartList = new ArrayList<RoleDart>();
		if (dartIdList.size() > 0) {
			dartList = roleDartDAO().queryDarts(tidyString(dartIdList));// 新刷出的镖车和原有镖车id
		}
		// 更新列表数据
		if (isRemoveAllEnd) {
			roleDartDAO().updateOtherRoleList(dart, dartIdList, 0, false);
		} else {
			dart.getOtherRoleList().addAll(dartIdList);
			roleDartDAO().updateOtherRoleList(dart, dart.getOtherRoleList(), 0, false);
		}

		List<DartCarVO> carList = tidyCarList(dartList);
		return carList;
	}

	// list--> '(id,id,id...)'
	private String tidyString(List<Integer> dartIdList) {
		String findString = "(";
		for (int curData : dartIdList) {
			findString += curData + ",";
		}
		findString = findString.substring(0, findString.length() - 1);
		findString += ")";
		return findString;
	}

	// 随机镖车品质
	public int getProbQuality(int refreshcnt) {
		int random = cacheManager.random(0, 10000);
		List<Integer> qList = new ArrayList<Integer>();
		switch (refreshcnt) {
		case 0:
			qList = cacheManager.getYaBiaoConfig("freerandom");
			break;
		case 1:
			qList = cacheManager.getYaBiaoConfig("firstrandom");
			break;
		case 2:
			qList = cacheManager.getYaBiaoConfig("secondrandom");
			break;
		case 3:
			qList = cacheManager.getYaBiaoConfig("thirdrandom");
			break;
		case 4:
			qList = cacheManager.getYaBiaoConfig("fourthrandom");
			break;
		case 5:
			qList = cacheManager.getYaBiaoConfig("fifthrandom");
			break;
		case 6:
			qList = cacheManager.getYaBiaoConfig("sixthrandom");
			break;
		default:
			qList = cacheManager.getYaBiaoConfig("sixthrandom");
		}

		if (random <= qList.get(0)) {
			return RoleDart.DART_CAR_QUALITY_GREEN;
		} else if (random <= (qList.get(0) + qList.get(1))) {
			return RoleDart.DART_CAR_QUALITY_BLUE;
		} else if (random <= (qList.get(0) + qList.get(1) + qList.get(2))) {
			return RoleDart.DART_CAR_QUALITY_PURPLE;
		} else {
			return RoleDart.DART_CAR_QUALITY_GOLDEN;
		}
	}

	// 不被抢标可获得货币数 是否返回25%
	public List<GiftItem> getCoin(Role doc, RoleDart dart, Boolean isPercent) throws BizException {
		YabiaoJiangli jiangliData = cacheManager.getExistValueByKey(YabiaoJiangli.class, dart.getDartCarQuality());
		List<GiftItem> items = new ArrayList<GiftItem>();

		int silver = (int) Math.ceil(jiangliData.getFix().get(0) * doc.getLevel() * jiangliData.getRatio().get(0));
		int popul = (int) Math.ceil(jiangliData.getFix().get(1) * doc.getLevel() * jiangliData.getRatio().get(1));

		if (isPercent) {
			int percent = cacheManager.getYaBiaoConfig("lossratio").get(0);
			items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_银币, (int) Math.ceil(silver * percent / 100)));
			items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_声望, (int) Math.ceil(popul * percent / 100)));
		} else {
			items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_银币, silver));
			items.add(new GiftItem(Packet.POS_BAG, Packet.ATTR_声望, popul));
		}
		return items;
	}

}