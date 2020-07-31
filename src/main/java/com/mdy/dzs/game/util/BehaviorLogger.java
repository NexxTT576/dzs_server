package com.mdy.dzs.game.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.RoleStatistics;
import com.mdy.sharp.util.JSONUtil;

/**
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2015年1月8日 下午6:16:16
 */
public class BehaviorLogger {

	public static enum Type {
		GAME("gamelog"), FINANCE_GOLE_GET("finance_gold_get"), FINANCE_GOLE_COST("finance_gold_cost"),
		GOODS_GET("goods_get"), GOODS_COST("goods_cost");

		private String name;

		public String getName() {
			return name;
		}

		private Type(String value) {
			this.name = value;
		}
	}

	public static enum GoldType {
		OLD_GOLD("old_gold"), SYS_GOLD("sys_gold"), RECHARGE_GOLD("recharge_gold");

		private String name;

		public String getName() {
			return name;
		}

		private GoldType(String value) {
			this.name = value;
		}
	}

	public static enum Action {
		/**
		 * loading时间
		 */
		LOGIN_LOADING,
		/**
		 * 登录游戏
		 */
		LOGIN_GAME, GOLD_GET,
		/**
		 * 钻石购买
		 */
		GOLD_COST,
		/**
		 * 游戏币获得和使用
		 */
		SILVER_COST, SILVER_GET,
		/**
		 * 道具获得和使用
		 */
		ITEM_COST, ITEM_GET,
		/**
		 * 新手引导
		 */
		GUIDE_STEP;

		private String actionName;

		/**
		 * 是否是活动Action
		 */
		private int activityId;

		public int getActivityId() {
			return activityId;
		}

		public void setActivityId(int activityId) {
			this.activityId = activityId;
		}

		public String getActionName() {
			return actionName;
		}

		private Action(String value, int activityId) {
			this.actionName = value;
			this.activityId = activityId;
		}

		private Action() {
			this("", 0);
		}

		private Action(String value) {
			this(value, 0);
		}

		/**
		 * boolean 用于标识是否是活动行为，用于淑升日志分析
		 * 
		 * @param isActivity
		 */
		private Action(int activityId) {
			this("", activityId);
		}

	}

	public static class Params {
		private String name;
		private Object value;

		public static final String AFTER = "after";

		public static final String COST = "cost";

		public static final String IS_ACTIVITY = "isActivity";

		public static Params valueOf(String name, Object value) {
			Params params = new Params();
			params.setName(name);
			params.setValue(value);
			return params;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}
	}

	/**
	 * 日期格式化对象
	 */
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 一天的秒数
	 */
	private static final int ONE_DAY_SECONDS = 24 * 3600;
	/**
	 * 行为日志，数据流变化日志记录器
	 */
	private static final Logger ACTION_LOGGER = LogManager.getLogger("Action");
	/**
	 * 消费日志
	 */
	private static final Logger GOLD_LOGGER = LogManager.getLogger("Gold");

	public static void log4Platform(Role role, Type type, Action action, Params... params) {
		log4Platform(role, type, action, true, params);
	}

	/**
	 * 用于数据平台统计的日志输出
	 * 
	 * @param player
	 * @param action
	 * @param params
	 */
	public static void log4Platform(Role role, Type type, Action action, Boolean extend, Params... params) {
		Date now = new Date();
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("type_log", type.getName());
		// 行为时间
		info.put("ts", DATE_FORMAT.format(now));
		// 平台用户Id
		info.put("puid", role.getAccount());
		// 设备Id
		info.put("device_id", role.getLastDeviceUUID());
		// 角色Id
		info.put("player_id", role.getId());
		// 场景，先留空
		info.put("scene", "scene");
		// 等级
		info.put("level", role.getLevel());
		// 注册天数
		info.put("stay", (now.getTime() - role.getCreateTime().getTime()) / ONE_DAY_SECONDS + 1);
		// 动作编号
		info.put("action", action.name());
		// 塞入参数V1
		Map<String, Object> v1 = new HashMap<String, Object>();
		for (Params param : params) {
			v1.put(param.getName(), param.getValue().toString());
		}
		if (extend) {
			info.put("v1", v1);
			info.put("v2", "");
			info.put("v3", "");
			info.put("v4", "");
		} else {
			info.putAll(v1);
		}

		// 设置IP地址,osversion,phonetype,imei,mac,store,server,os,gameid
		// TODO info.put("ip", player.getIp());
		info.put("osversion", role.getLastDeviceVersion());
		info.put("phonetype", role.getLastDeviceType());
		info.put("store", role.getAccount().split("__")[0]);
		info.put("server", role.getServerId());

		ACTION_LOGGER.info(JSONUtil.toJson(info));
	}

	/**
	 * 钻石流统计，主要用于数据统计平台
	 * 
	 * @param player
	 * @param source
	 * @param action
	 */
	public static void log4GoldCost(Role role, int systemType, RoleStatistics oldRS, RoleStatistics newRS,
			List<ProbItem> items) {
		Map<String, Object> info = new HashMap<String, Object>();

		int cost_old = oldRS.getCurBind() - newRS.getCurBind();
		int cost_sys = oldRS.getCurDonate() - newRS.getCurDonate();
		int cost_recharge = oldRS.getCurGold() - newRS.getCurGold();

		String itemsStr = "";
		Map<String, String> itemsMap = new HashMap<String, String>();
		if (items != null) {
			int i = 0;
			for (ProbItem probItem : items) {
				String itemStr = probItem.getT() + ":" + probItem.getId() + ":" + probItem.getN();
				itemsStr += itemStr + ",";
				itemsMap.put(String.valueOf(i), itemStr);
				i++;
			}
			if (itemsStr.endsWith(",")) {
				itemsStr = itemsStr.substring(0, itemsStr.length() - 1);
			}
		}
		int cost = cost_old + cost_sys + cost_recharge;

		log4Platform(role, Type.GAME, Action.GOLD_COST, Params.valueOf("old_gold", cost_old),
				Params.valueOf("sys_gold", cost_sys), Params.valueOf("recharge_gold", cost_recharge),
				Params.valueOf("wpnum", 1), Params.valueOf("price", cost), Params.valueOf("wpid", 0),
				Params.valueOf("wptype", systemType), Params.valueOf("level", role.getLevel()),
				Params.valueOf("items", itemsStr));

		log4Platform(role, Type.FINANCE_GOLE_COST, Action.GOLD_COST, false, Params.valueOf("act_type", systemType),
				Params.valueOf("cost_old", cost_old), Params.valueOf("cost_sys", cost_sys),
				Params.valueOf("cost_recharge", cost_recharge), Params.valueOf("gold_after_old", newRS.getCurBind()),
				Params.valueOf("gold_after_sys", newRS.getCurDonate()),
				Params.valueOf("gold_after_recharge", newRS.getCurGold()), Params.valueOf("level", role.getLevel()),
				Params.valueOf("item", itemsMap), Params.valueOf("playername", role.getName()));
		//
		// // 行为时间
		// info.put("puid", role.getAccount());
		// info.put("player_id", role.getId());
		// info.put("playername", role.getName());
		// info.put("money", cost);
		// info.put("wpnum", 1);
		// info.put("wpname", "");
		// info.put("price", cost);
		// info.put("wpid", 0);
		// info.put("wptype", systemType);
		// info.put("level", role.getLevel());
		// info.put("items", itemsStr);
		//
		// info.put("osversion", role.getLastDeviceVersion());
		// info.put("phonetype", role.getLastDeviceType());
		// info.put("store", role.getAccount().split("__")[0]);
		// info.put("server", role.getServerId());
		//
		//
		// Map<String, Object> data = new HashMap<String, Object>();
		// Params[] params = new Params[] {
		// Params.valueOf(Params.AFTER, role.getGold()),
		// Params.valueOf(Params.COST, cost) };
		// for (Params param : params) {
		// data.put(param.getName(), param.getValue().toString());
		// }
		// info.put("data", data);
		// GOLD_LOGGER.info(JSONUtil.toJson(info));

	}

	/**
	 * 钻石流统计，主要用于数据统计平台
	 * 
	 * @param player
	 * @param source
	 * @param action
	 */
	public static void log4GoldGet(Role role, int systemType, GoldType gType, int cnt) {

		log4Platform(role, Type.FINANCE_GOLE_GET, Action.GOLD_GET, false, Params.valueOf("act_type", systemType),
				Params.valueOf("gold_type", gType.getName()), Params.valueOf("gold", cnt),
				Params.valueOf("level", role.getLevel()), Params.valueOf("playername", role.getName()));

	}
}
