package com.mdy.dzs.game.ao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.mdy.dzs.game.domain.monthcard.RoleMonthCard;
import com.mdy.dzs.game.exception.RoleException;
import com.mdy.sharp.container.biz.BizException;

/**
 * 角色月卡模型
 * 
 * @author 白雪林
 *
 */
public class RoleMonthCardAO extends BaseAO {

	/**
	 * 查询抛异常
	 */
	public RoleMonthCard queryExistId(int id) throws BizException {
		RoleMonthCard roleMonthCardDoc = roleMonthCardDAO().query(id);
		if (roleMonthCardDoc == null) {
			throw RoleException.getException(RoleException.EXCE_ACCOUNT_NOT_EXIST);
		}
		return roleMonthCardDoc;
	}

	/**
	 * 查询
	 */
	public RoleMonthCard query(int id) {
		RoleMonthCard mCardDoc = roleMonthCardDAO().query(id);
		if (mCardDoc == null) {
			roleMonthCardDAO().add(id);
			mCardDoc = roleMonthCardDAO().query(id);
		}
		return mCardDoc;

	}

	/**
	 * 查询列表
	 */
	public List<RoleMonthCard> queryList() {
		return roleMonthCardDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param RoleMonthCard
	 */
	public void add(int id) {
		roleMonthCardDAO().add(id);
	}

	/**
	 * 更新
	 * 
	 * @param RoleMonthCard
	 */
	public void updateGetTime(RoleMonthCard rmc) {
		roleMonthCardDAO().updateGetTime(rmc);
	}

	public void updateOverTime(RoleMonthCard rmc) {
		roleMonthCardDAO().updateOverTime(rmc);

	}

	public int disDays(Date overTime) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);

		Calendar overCalendar = Calendar.getInstance(TimeZone.getDefault());
		overCalendar.setTime(overTime);
		overCalendar.set(Calendar.SECOND, 0);
		overCalendar.set(Calendar.MINUTE, 0);
		overCalendar.set(Calendar.HOUR_OF_DAY, 0);

		int overMills = (int) (overCalendar.getTimeInMillis() / 1000);
		int nowMills = (int) (calendar.getTimeInMillis() / 1000);
		return (int) (Math.ceil(((overMills - nowMills) / (3600 * 24))) + 1);
	}
}