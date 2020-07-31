package com.mdy.dzs.game.ao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import com.mdy.dzs.data.domain.shenmi.ShenMi;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.shenmishop.ShenMiItemVO;
import com.mdy.dzs.game.domain.shenmishop.ShenMiShop;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleException;
import com.mdy.dzs.game.exception.ShenMiShopException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

/**
 * 角色神秘商店数据
 * 
 * @author 白雪林
 *
 */
public class RoleShenMiShopAO extends BaseAO {

	private CacheManager cacheManager;

	public RoleShenMiShopAO(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * 抛异常查询
	 * 
	 * @throws BizException
	 */
	public ShenMiShop queryExistRoleId(int roleid) throws BizException {
		ShenMiShop shopList = roleShenMiShopDAO().query(roleid);
		if (shopList == null) {
			throw RoleException.getException(RoleException.EXCE_ACCOUNT_NOT_EXIST);
		}
		return shopList;
	}

	/**
	 * 查询
	 */
	public ShenMiShop query(int roleid) {
		ShenMiShop shopList = roleShenMiShopDAO().query(roleid);
		if (shopList == null) {
			roleShenMiShopDAO().add(roleid);
			shopList = roleShenMiShopDAO().query(roleid);
		}
		return shopList;
	}

	/**
	 * 查询列表
	 */
	public List<ShenMiShop> queryList() {
		return roleShenMiShopDAO().queryList();
	}

	/**
	 * 更新
	 * 
	 * @param ShenMiShop
	 */
	public void update(ShenMiShop sms) {
		roleShenMiShopDAO().update(sms);
	}

	/**
	 * 距下个2倍整点数的秒差
	 */
	public int disSeconds() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		Calendar nextPot = Calendar.getInstance(TimeZone.getDefault());

		int setHour = now.get(Calendar.HOUR_OF_DAY);
		long nextTime = 0;

		if (setHour % 2 != 0) {
			setHour += 1;
		} else {
			setHour += 2;
		}

		if (setHour == 24) {
			nextPot.set(Calendar.HOUR_OF_DAY, 23);
			nextPot.set(Calendar.MINUTE, 59);
			nextPot.set(Calendar.SECOND, 59);
			nextPot.set(Calendar.MILLISECOND, 0);
			nextTime = nextPot.getTimeInMillis();// 明天0:0:0 时间戳 s
		} else {
			nextPot.set(Calendar.HOUR_OF_DAY, setHour);
			nextPot.set(Calendar.MINUTE, 0);
			nextPot.set(Calendar.SECOND, 0);
			nextPot.set(Calendar.MILLISECOND, 0);
			nextTime = nextPot.getTimeInMillis();// 下个2倍整点数 时间戳 s
		}
		int time = (int) (nextTime - now.getTimeInMillis()) / 1000;

		return time;
	}

	/**
	 * 更新免费刷新次数
	 */
	public void refreshFreeCnt(ShenMiShop doc, int vipFreeLimit) {
		if (doc.getMeysteryFreeCnt() < vipFreeLimit) {// 需累加计算

			if (doc.getMeysteryFreeTime() == null) {// 上次刷新时间点为初始状态，没有过刷新
				doc.setMeysteryFreeCnt(2);
			} else {
				// 当前与上次免费次数检查之间，是否有可累加
				Date lastFreeCntTime = doc.getMeysteryFreeTime();
				Calendar lastCntTime = Calendar.getInstance();
				lastCntTime.setTime(lastFreeCntTime);// 上次检查时间点
				long lastPotTime = 0;
				int hour = lastCntTime.get(Calendar.HOUR_OF_DAY);

				if (hour % 2 != 0) {
					hour += 1;
				} else {
					hour += 2;
				}

				if (hour == 24) {
					lastCntTime.set(Calendar.HOUR_OF_DAY, 23);
					lastCntTime.set(Calendar.MINUTE, 59);
					lastCntTime.set(Calendar.SECOND, 59);
					lastCntTime.set(Calendar.MILLISECOND, 0);
					lastPotTime = lastCntTime.getTimeInMillis();// 0:0:0
				} else {
					lastCntTime.set(Calendar.HOUR_OF_DAY, hour);
					lastCntTime.set(Calendar.MINUTE, 0);
					lastCntTime.set(Calendar.SECOND, 0);
					lastCntTime.set(Calendar.MILLISECOND, 0);
					lastPotTime = lastCntTime.getTimeInMillis();// 下个2倍整点数时间戳
				}

				Calendar now = Calendar.getInstance();
				long nowStamp = now.getTimeInMillis();
				for (; lastPotTime <= nowStamp; lastPotTime += 2 * 3600 * 1000) {
					if (doc.getMeysteryFreeCnt() >= vipFreeLimit) {
						break;
					}
					if (lastPotTime <= nowStamp) {
						doc.setMeysteryFreeCnt(doc.getMeysteryFreeCnt() + 1);
					}

				}
			}
		}
	}

	/**
	 * 更新物品列表
	 * 
	 * @throws BizException
	 */
	public ShenMiShop refreshList(ShenMiShop shenMiDoc, Role doc) throws BizException {
		Date now = new Date();
		shenMiDoc.getMeysteryMap().clear();

		for (int itemCnt = 1; itemCnt <= 8; itemCnt++) {// 刷出8个物品
			if (itemCnt > 8) {
				break;
			}
			Random random = new Random();
			int propNum = random.nextInt(10000);
			int lvInd = 0;

			// 等级数组
			ShenMi shenMiMap = cacheManager.getExistValueByKey(ShenMi.class, 1);// 神秘表第一行数据
			List<Integer> levelList = shenMiMap.getLevel();
			for (; lvInd < levelList.size(); lvInd++) {
				if (doc.getLevel() <= levelList.get(lvInd)) {
					break;
				}
			}
			// 据刷新次数，得到读取哪列概率组
			int propls = 1;
			for (int i = 0; i <= Constants.mysteryPropAry.size(); i++) {// [1,5,10,50]
				if ((i < Constants.mysteryPropAry.size()
						&& shenMiDoc.getMeysteryDayRefreshCnt() <= Constants.mysteryPropAry.get(i))
						|| i == Constants.mysteryPropAry.size()) {
					propls += i;
					break;
				}
			}
			// 随出一个物品
			int addProp = 0;
			Map<Integer, ShenMi> sDatas = cacheManager.getValues(ShenMi.class);
			for (int idInd = 1; idInd <= sDatas.size(); idInd++) {
				ShenMi tempshenMiMap = cacheManager.getExistValueByKey(ShenMi.class, idInd);
				List<Integer> propList = getPropList(tempshenMiMap, propls);
				addProp += propList.get(lvInd);

				if (propNum <= addProp) {// 添加一个物品
					Random randomItem = new Random();
					List<Integer> itemProbList = tempshenMiMap.getItem();
					int itemProbId = randomItem.nextInt(itemProbList.get(1))
							% (itemProbList.get(1) - itemProbList.get(0) + 1) + itemProbList.get(0);
					ShenMiItemVO newItem = new ShenMiItemVO();
					newItem.setId(itemCnt); // 编号
					newItem.setItemId(itemProbId); // 物品id
					newItem.setMoney(tempshenMiMap.getMoney()); // 货币类型
					newItem.setNum(tempshenMiMap.getNum()); // 数量
					newItem.setPrice(tempshenMiMap.getPrice()); // 价格
					newItem.setType(tempshenMiMap.getType()); // 物品类型
					newItem.setUpLimit(tempshenMiMap.getTime()); // 购买次数
					shenMiDoc.getMeysteryMap().put(itemCnt + "", newItem);
					break;
				}
			}

		} // end 8 items
			// 更新刷新时间点，每日刷新次数
		shenMiDoc.setMeysteryRefreshTime(now);
		shenMiDoc.setMeysteryDayRefreshCnt(shenMiDoc.getMeysteryDayRefreshCnt() + 1);

		return shenMiDoc;
	}

	public List<Integer> getPropList(ShenMi tempshenMiMap, int idInd) throws BizException {
		switch (idInd) {
		case 1:
			return tempshenMiMap.getProb1();
		case 2:
			return tempshenMiMap.getProb2();
		case 3:
			return tempshenMiMap.getProb3();
		case 4:
			return tempshenMiMap.getProb4();
		case 5:
			return tempshenMiMap.getProb5();
		default:
			// throw BaseException.getGlobalException("get Prop error");
			throw BaseException.getException(ShenMiShopException.EXCE_SHENMI_PROB_ERROR);
		}
	}

}