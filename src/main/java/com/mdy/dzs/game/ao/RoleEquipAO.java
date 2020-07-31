package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.Money;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.calcattack.CalcRoleEquip;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleEquipException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

/**
 * 装备复用层
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月22日 下午2:44:35
 */
public class RoleEquipAO extends BaseAO {

	private CacheManager cacheManager;
	private RoleAttackCalcManager calcManager;

	public RoleEquipAO(CacheManager cacheManager, RoleAttackCalcManager calcManager) {
		this.cacheManager = cacheManager;
		this.calcManager = calcManager;
	}

	public Equip queryExistEquipById(int roleId, int cid) throws BizException {
		Equip res = equipDAO().query(cid);
		if (res == null || res.getRoleId() != roleId) {
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_NOT_SELF);
		}
		return res;
	}

	/**
	 * 更新洗练信息 prop propwait
	 * 
	 * @param curEquip
	 */
	public void updateProps(Equip curEquip) {
		equipDAO().updateProps(curEquip);
	}

	public void update(Equip curEquip) {
		equipDAO().update(curEquip);
	}

	public List<Equip> queryListByAccount(int roleId) {
		return equipDAO().queryListByAccount(roleId);
	}

	/**
	 * 
	 * @param equip
	 * @param itemData
	 * @param base
	 * @param type     1 炼化 2 重生
	 * @return
	 * @throws BizException
	 */
	public List<ProbItem> getEquipReborn(Equip equip, Item itemData, List<Integer> base, int type) throws BizException {
		List<ProbItem> rebornList = new ArrayList<ProbItem>();

		int silver = equip.getSilver();
		int baseStone;
		if (type == 2) {
			baseStone = 0;
			silver -= itemData.getPrice();
		} else {
			baseStone = itemData.getNumber();
		}
		int stone = baseStone + (int) (Math
				.floor(base.get(0) * Constants.equipPropCoe.get(0) + base.get(1) * Constants.equipPropCoe.get(1)
						+ base.get(2) * Constants.equipPropCoe.get(2) + base.get(3) * Constants.equipPropCoe.get(3)
						+ base.get(4) * Constants.equipPropCoe.get(4) + base.get(5) * Constants.equipPropCoe.get(5)));
		Money silverData = cacheManager.getExistValueByKey(Money.class, 2);
		rebornList.add(new ProbItem(0, silverData.getItem(), silver));
		if (stone != 0)
			rebornList.add(new ProbItem(Packet.POS_BAG, Constants.equipPropItem, stone));
		return rebornList;
	}

	/**
	 * 重生消耗的金币
	 * 
	 * @param equip
	 * @return
	 */
	public int cost(Equip equip) {
		int gold;
		if (equip.getStar() > 4) {
			gold = 50;
		} else {
			gold = 25;
		}
		return gold;
	}

	/**
	 * 查询某子位置所有的空闲装备
	 * 
	 * @param roleid
	 * @param subpos1-6
	 * @return list
	 * @throws BizException
	 * 
	 */
	public List<Equip> listFreeEquip(int roleId, int subpos, int pos) {
		return equipDAO().queryfreelistByAccPos(roleId, subpos, pos);
	}

	/**
	 * 获取最好的装备
	 * 
	 * @param roleid
	 * @param subpos1-6
	 * @param pos
	 * @return equip || null null 表示没有
	 * @throws BizException
	 * 
	 */
	public Equip getBetterEquip(int roleId, int subpos, int pos) {
		Equip betterEquip = null;
		Equip thisequip = null;
		// 获取所有装备
		List<Equip> equiplist = equipDAO().queryListByAccount(roleId);
		// 当前位置的装备
		List<Equip> thisEquiplist = equipDAO().queryNumByAccPos(roleId, pos, subpos);
		if (thisEquiplist.size() > 0) {
			thisequip = thisEquiplist.get(0);

		}
		// 获取最好的装备
		for (Equip equip : equiplist) {
			if (equip.getPos() == 0 && equip.getSubpos() == subpos) {
				if (chooseBetterEquip(equip, thisequip, pos) > 0) {
					betterEquip = equip;
					thisequip = equip;
				}
			}

		}

		return betterEquip;
	}

	// 比较两个装备
	@SuppressWarnings("unused")
	public int chooseBetterEquip(Equip otherequip, Equip thisequip, int pos) {
		Item otherdata = null;
		Item thisdata = null;
		if (otherequip == null) {
			return -1;
		} else if (thisequip == null) {

			return 1;
		}
		try {
			otherdata = cacheManager.getExistValueByKey(Item.class, otherequip.getResId());
			thisdata = cacheManager.getExistValueByKey(Item.class, thisequip.getResId());
		} catch (BizException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		if (pos == 1) {
			if (otherequip.getResId() == 1405 || otherequip.getResId() == 1406 || otherequip.getResId() == 1407
					|| otherequip.getResId() == 1408) {
				if (thisequip.getResId() == otherequip.getResId() && thisequip.getLevel() > otherequip.getLevel()) {

					return -1;
				}
				return 1;
			}
			if (thisequip.getResId() == 1405 || thisequip.getResId() == 1406 || thisequip.getResId() == 1407
					|| thisequip.getResId() == 1408) {
				return -1;
			}

		}
		if (thisdata.getEquip_level() < otherdata.getEquip_level()) {

			return 1;
		}
		if (thisdata.getEquip_level() == otherdata.getEquip_level() && thisequip.getLevel() < otherequip.getLevel()) {

			return 1;
		}
		if (thisdata.getEquip_level() == otherdata.getEquip_level() && thisequip.getLevel() == otherequip.getLevel()) {
			CalcRoleEquip calc = calcManager.getCalcEquip(thisequip.getResId());
			List<Integer> base = calc.getBase(0, thisequip.getProps());
			int thisval = (int) (Math.floor(base.get(0) * Constants.equipPropCoe.get(0)
					+ base.get(1) * Constants.equipPropCoe.get(1) + base.get(2) * Constants.equipPropCoe.get(2)
					+ base.get(3) * Constants.equipPropCoe.get(3) + base.get(4) * Constants.equipPropCoe.get(4)
					+ base.get(5) * Constants.equipPropCoe.get(5)));
			CalcRoleEquip ocalc = calcManager.getCalcEquip(otherequip.getResId());
			List<Integer> obase = calc.getBase(0, otherequip.getProps());
			int otherval = (int) (Math.floor(obase.get(0) * Constants.equipPropCoe.get(0)
					+ obase.get(1) * Constants.equipPropCoe.get(1) + obase.get(2) * Constants.equipPropCoe.get(2)
					+ obase.get(3) * Constants.equipPropCoe.get(3) + obase.get(4) * Constants.equipPropCoe.get(4)
					+ obase.get(5) * Constants.equipPropCoe.get(5)));
			if (thisval < otherval) {
				return 1;
			}
		}
		return -1;
	}

	public List<Integer> queryRecordEquipId(int roleId) {
		return equipDAO().queryRecordEquipId(roleId);
	}

}
