package com.mdy.dzs.game.job;

import com.mdy.dzs.game.action.RoleActivityAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.job.BaseJob;

public class ReloadMonthSignDataJob extends BaseJob {
	private final RoleActivityAction roleActityAction = Container.get().createLocal(RoleActivityAction.class);

	@Override
	public void init() {
		this.setCronExpression("0 0 0 1 * ?");// 每月1日0点
		setRetryCount(2);
		setRetryPeriod(1000 * 30);// 每隔1分钟重试，最多2次，活动最多持续1分钟
	}

	@Override
	public void run() {
		roleActityAction.reloadMonthSignData();
	}

}