package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.game.domain.yuan.RoleYuan;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleYuanException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.sharp.container.biz.BizException;

public class RoleYuanAO extends BaseAO {
	private CacheManager cacheManager;
	private static final int SPELL_DAMAGE = 77;
	private static final int RE_DAMAGE = 78;

	public RoleYuanAO(CacheManager cacheManager) {

		this.cacheManager = cacheManager;
	}

	public RoleYuan queryExistYuanById(int roleId, int cid) throws BizException {
		RoleYuan res = roleYuanDAO().query(cid);
		if (res == null) {
			throw BaseException.getException(RoleYuanException.EXCE_ROLEYUAN_NOT_EXIST);
		}
		if (res.getRoleId() != roleId) {
			// throw BaseException.getGlobalException(
			// "yuan "+cid+" not is "+roleId);
			throw BaseException.getException(RoleYuanException.EXCE_ROLEYUAN_ROLEID_ERROR, cid, roleId);
		}
		return res;
	}

	public int getRoleYuanListNumByAcc(int roleId) {
		return roleYuanDAO().getRoleYuanListNumByAcc(roleId);
	}

	public void update(RoleYuan curYuan) {
		roleYuanDAO().update(curYuan);
	}

	public List<RoleYuan> queryListByAccPos(int roleId, int pos, int subpos) {

		return roleYuanDAO().queryListByAccPos(roleId, pos, subpos);
	}

	/**
	 * 获取最好的Yuan
	 * 
	 * @param roleid
	 * @param subpos7-14
	 * @param pos
	 * @param subpos
	 * @return equip || null 表示没有
	 * 
	 */
	@SuppressWarnings("unused")
	public RoleYuan getBetterYuan(int roleId, int subpos, int pos, RoleYuan thisYuan) {
		RoleYuan betterYuan = null;
		List<RoleYuan> yuanlist = roleYuanDAO().queryListByAccount(roleId);
		// 查询所有空闲的yuan
		List<RoleYuan> freeList = roleYuanDAO().queryListBytype(roleId, 0, 51);
		List<Integer> list = new ArrayList<Integer>();
		// 获得除了当前位置的yuan外，当前卡牌其它位置的yuan
		for (RoleYuan iyuan : yuanlist) {
			if (iyuan.getPos() == pos && iyuan.getSubpos() != subpos) {
				if (iyuan.getProps().get(0).getIdx() == SPELL_DAMAGE || iyuan.getProps().get(0).getIdx() == RE_DAMAGE) {
					list.add(SPELL_DAMAGE);
					list.add(RE_DAMAGE);
				}
				list.add(iyuan.getProps().get(0).getIdx());
			}
		}
		// 为空时说明除了当前其它位置没有
		if (list == null) {
			// 判断当前位置是否为空
			if (thisYuan == null) {
				for (RoleYuan yuan : freeList) {
					if (chooseBetterYuan(yuan, thisYuan, pos) > 0) {
						betterYuan = yuan;
						thisYuan = yuan;
					}
				}
			} else {// 其它位置没有当前位置有
				for (RoleYuan yuan : freeList) {
					if (yuan.getProps().get(0).getIdx() == thisYuan.getProps().get(0).getIdx()
							&& chooseBetterYuan(yuan, thisYuan, pos) > 0) {
						betterYuan = yuan;
						thisYuan = yuan;
					}
				}
			}
			return betterYuan;
		} else {// 其它位置有
				// 当前位置没有，当前不可与其它属性相同
			if (thisYuan == null) {
				for (RoleYuan yuan : freeList) {
					if (!list.contains(yuan.getProps().get(0).getIdx())) {
						if (chooseBetterYuan(yuan, thisYuan, pos) > 0) {
							betterYuan = yuan;
							thisYuan = yuan;
						}
					}
				}
			} else {// 其它位置有，当前位置也有
				for (RoleYuan yuan : freeList) {
					if (yuan.getProps().get(0).getIdx() == thisYuan.getProps().get(0).getIdx()
							&& chooseBetterYuan(yuan, thisYuan, pos) > 0) {
						betterYuan = yuan;
						thisYuan = yuan;
					}
				}
			}
			return betterYuan;
		}
	}

	/**
	 * 比较两个yuan
	 * 
	 * @param thisyuan
	 * @param otheryuan 两个需要比较的yuan
	 * @param pos       当前卡牌位置
	 * @return false 当前装备是好的||false 相反
	 * 
	 */
	public int chooseBetterYuan(RoleYuan otheryuan, RoleYuan thisyuan, int pos) {
		Item otherdata = null;
		Item thisdata = null;
		if (otheryuan == null) {
			return -1;
		}
		if (thisyuan == null) {
			return 1;
		}
		try {
			otherdata = cacheManager.getExistValueByKey(Item.class, otheryuan.getResId());
			thisdata = cacheManager.getExistValueByKey(Item.class, thisyuan.getResId());
		} catch (BizException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (thisdata.getEquip_level() < otherdata.getEquip_level()) {
			return 1;
		}
		if (thisdata.getEquip_level() == otherdata.getEquip_level() && thisyuan.getLevel() < otheryuan.getLevel()) {

			return 1;
		}
		return -1;
	}

}
