package com.mdy.dzs.game.ao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.activity.exchange.ActivityExchangeExp;
import com.mdy.dzs.game.domain.activity.exchange.ActivityExchange;
import com.mdy.dzs.game.domain.activity.exchange.CActExchExp;
import com.mdy.dzs.game.domain.activity.exchange.CActExchExpItem;
import com.mdy.dzs.game.domain.activity.exchange.RoleActivityExch;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 玩家限时兑换
 * 
 * @author zhou
 *
 */
public class RoleActivityExchAO extends BaseAO {
	private PacketAO packetAO;
	private CacheManager cacheManager;

	private List<ActivityExchange> exchangeList;
	private Map<Integer, ActivityExchange> exchangeMap;

	public RoleActivityExchAO(PacketAO packetAO, CacheManager cacheManager) {
		this.packetAO = packetAO;
		this.cacheManager = cacheManager;
	}

	//
	/**
	 * 查询
	 */
	public RoleActivityExch query(int id) {
		return roleActivityExchDAO().query(id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleActivityExch> queryList() {
		return roleActivityExchDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param RoleActivityExch
	 */
	public void add(RoleActivityExch rae) {
		roleActivityExchDAO().add(rae);
	}

	/**
	 * 更新
	 * 
	 * @param RoleActivityExch
	 */
	public void update(RoleActivityExch rae) {
		roleActivityExchDAO().update(rae);
	}

	/**
	 * 查询
	 * 
	 * @param roleId
	 * @param exchId
	 * @return
	 */
	public RoleActivityExch queryByExchId(int roleId, int exchId) {
		return roleActivityExchDAO().queryByExchId(roleId, exchId);
	}

	/**
	 * 更新公式
	 * 
	 * @param roleId
	 * @param exchId
	 * @param exp
	 */
	public void updateExpId(int id, int refreshNum, int exp, int refFreeNum) {
		roleActivityExchDAO().updateExpId(id, refreshNum, exp, refFreeNum);
	}

	/**
	 * 更新兑换数量
	 * 
	 * @param id
	 * @param exchNum
	 */
	public void updateExchNum(int id, int exchNum) {
		roleActivityExchDAO().updateExchNum(id, exchNum);
	}

	// ================================================================================================================//
	/**
	 * 公式的数据
	 * 
	 * @param doc
	 * @param aeExp
	 * @return
	 * @throws BizException
	 */
	public CActExchExp getExp(Role doc, ActivityExchangeExp aeExp) throws BizException {
		CActExchExp cee = new CActExchExp(aeExp.getId());
		for (int i = 0; i < aeExp.getExchItemType().size(); i++) {
			int etype = aeExp.getExchItemType().get(i);
			int eid = aeExp.getExchItemId().get(i);
			CActExchExpItem ceeItem = new CActExchExpItem(etype, eid, aeExp.getExchItemCnt().get(i));
			int had = packetAO.getNumberByTypeIdLevel(doc, etype, eid);
			ceeItem.setHad(had);
			cee.getExchItem().add(ceeItem);
		}
		for (int i = 0; i < aeExp.getExchRstType().size(); i++) {
			int etype = aeExp.getExchRstType().get(i);
			int eid = aeExp.getExchRstId().get(i);
			CActExchExpItem ceeItem = new CActExchExpItem(etype, eid, aeExp.getExchRstCnt().get(i));
			int had = packetAO.getNumberByTypeIdLevel(doc, etype, eid);
			ceeItem.setHad(had);
			cee.getExchRst().add(ceeItem);
		}
		return cee;
	}

	/**
	 * 随机公式
	 * 
	 * @param activityExchange
	 * @return
	 */
	public int randomExpId(ActivityExchange activityExchange) {
		int expId = 0;
		int random = cacheManager.random(1, 10000);
		int nextRandom = 0;
		for (int i = 0; i < activityExchange.getExchExpId().size(); i++) {
			nextRandom += activityExchange.getExchExpProb().get(i);
			if (random > nextRandom) {
				continue;
			}
			expId = activityExchange.getExchExpId().get(i);
			break;
		}
		return expId;
	}

	public void refreshDay(RoleActivityExch rae) {
		if (rae == null)
			return;
		if (!DateUtil.isToday(rae.getRefreshTime())) {
			// 刷新当天的挑战次数 和 当天的购买次数
			rae.setExchNum(0);
			rae.setRefreshNum(0);
			rae.setRefreshTime(new Date());
			roleActivityExchDAO().updateRefresh(1, new Date(), rae.getRoleId());
		}
	}

	// ==================================读取配置数据==================================================
	@Override
	public void start() {
		super.start();
		reloadActivitys();
	}

	/**
	 * 重新load配置
	 */
	public void reloadActivitys() {
		exchangeList = activityExchangeDAO().queryList();
		exchangeMap = new HashMap<Integer, ActivityExchange>();
		for (ActivityExchange activityExchange : getExchangeList()) {
			exchangeMap.put(activityExchange.getId(), activityExchange);
		}
	}

	public List<ActivityExchange> getExchangeList() {
		return exchangeList;
	}

	public Map<Integer, ActivityExchange> getExchangeMap() {
		return exchangeMap;
	}
}