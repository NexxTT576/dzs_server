package com.mdy.dzs.game.ao;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.DataAction;
import com.mdy.dzs.data.domain.yueqian.YueQian;
import com.mdy.dzs.game.domain.activity.monthsigngame.RoleActivityMonthSign;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.Container;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 月签
 * 
 * @author 白雪林
 *
 */
public class RoleActivityMonthSignAO extends BaseAO {

	private Map<Integer, YueQian> mapYueQian = new HashMap<Integer, YueQian>();

	@Override
	public void start() {
		reloadMonthSignData();
		super.start();
	}

	public void reloadMonthSignData() {
		DataAction dataAction = Container.get().createRemote(DataAction.class, DataApplication.CLUSTER_DATA_SYSTEM);
		mapYueQian.clear();
		List<YueQian> yueQianConfig = dataAction.queryYueQiansDatas();
		int month = DateUtil.getMonth(new Date());
		for (YueQian yueqian : yueQianConfig) {
			if (yueqian.getMonth() == month) {
				mapYueQian.put(yueqian.getTime(), yueqian);
			}
		}
	}

	public Map<Integer, YueQian> getYueQianDatas() {
		return mapYueQian;
	}

	/**
	 * 查询,没有则添加
	 */
	public RoleActivityMonthSign queryAdd(int roleId) {
		// 没有则添加
		Date now = new Date();
		RoleActivityMonthSign rs = roleActivityMonthSignDAO().query(roleId);
		if (rs == null) {
			roleActivityMonthSignDAO().add(roleId);
			rs = roleActivityMonthSignDAO().query(roleId);
		}
		// 跨月 重置本月记录
		if (!DateUtil.isSameMonth(rs.getDayRefreshTime(), now)) {
			roleActivityMonthSignDAO().resetMonthSignData(rs);
			rs = roleActivityMonthSignDAO().query(roleId);
		}

		return rs;
	}

	// 更新get_list，更新Refresh_time
	public void updateGetList(List<Integer> getList, int roleId) {
		roleActivityMonthSignDAO().updateGetList(getList, roleId);
	}

	// 兼容旧数据，只push进已领取列表，不更新Refresh_time
	public void updateGetListForOldData(List<Integer> getList, int roleId) {
		roleActivityMonthSignDAO().updateGetListForOldData(getList, roleId);
	}

	// 返回可以领取的最大天数
	public int getMax(RoleActivityMonthSign mSignRole, Map<Integer, YueQian> yueQianMonthDatas) {
		int dayNum = 1;

		if (mSignRole.getGetList().size() > 0) {
			Object[] getListArray = mSignRole.getGetList().toArray();
			Arrays.sort(getListArray);
			dayNum = (Integer) getListArray[getListArray.length - 1];

			if (!DateUtil.isSameDay(mSignRole.getDayRefreshTime(), new Date())) { // 不同天 可领取天数+1
				dayNum++;
			}
		}

		if (dayNum > yueQianMonthDatas.size()) {
			dayNum = yueQianMonthDatas.size();
		}
		return dayNum;
	}

}