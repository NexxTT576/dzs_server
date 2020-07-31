package com.mdy.dzs.game.ao;

import java.util.List;

import com.mdy.dzs.game.domain.swordfight.ClientSwordCardVO;
import com.mdy.dzs.game.domain.swordfight.SwordRoleCard;

/**
 * 论剑角色
 * 
 * @author zhou
 *
 */
public class SwordRoleCardAO extends BaseAO {
	//
	/**
	 * 查询
	 */
	public SwordRoleCard query(int id) {
		return swordRoleCardDAO().query(id);
	}

	/**
	 * 查询列表
	 */
	public List<SwordRoleCard> queryList() {
		return swordRoleCardDAO().queryList();
	}

	/**
	 * 查询
	 */
	public List<SwordRoleCard> queryByAccount(String account, int type) {
		return swordRoleCardDAO().queryByAccount(account, type);
	}

	/**
	 * 查询 根据论剑角色id
	 */
	public List<SwordRoleCard> queryBySwordRoleId(int sword_role_id) {
		return swordRoleCardDAO().queryBySwordRoleId(sword_role_id);
	}

	/**
	 * 查询
	 */
	public SwordRoleCard queryByRoleCardId(int roleCardId, int type) {
		return swordRoleCardDAO().queryByRoleCardId(roleCardId, type);
	}

	/**
	 * 添加
	 * 
	 * @param SwordRoleCard
	 */
	public void add(SwordRoleCard src) {
		swordRoleCardDAO().add(src);
	}

	/**
	 * 更新
	 * 
	 * @param SwordRoleCard
	 */
	public void update(SwordRoleCard src) {
		swordRoleCardDAO().update(src);
	}

	/**
	 * 更新生命 怒气
	 * 
	 * @param SwordRoleCard
	 */
	public void updateLifeAnger(SwordRoleCard src) {
		swordRoleCardDAO().updateLifeAnger(src);
	}

	/**
	 * 删除
	 */
	public void delete(SwordRoleCard src) {
		swordRoleCardDAO().delete(src);
	}

	/**
	 * 根据论剑角色id删除卡
	 */
	public void deleteBySwordRoleId(int sword_role_id) {
		swordRoleCardDAO().deleteBySwordRoleId(sword_role_id);
	}

	/**
	 * 生成客户端需要的卡数据
	 * 
	 * @param src
	 * @return
	 */
	public ClientSwordCardVO getClientSwordCard(SwordRoleCard src) {
		ClientSwordCardVO cscVO = new ClientSwordCardVO();
		cscVO.setCardId(src.getResId());
		cscVO.setCls(src.getCls());
		cscVO.setLevel(src.getLevel());
		cscVO.setLife(src.getInitLife());
		cscVO.setInitLife((int) (src.getLifeRate() * 0.0001 * src.getInitLife()));
		cscVO.setPos(src.getPos());
		cscVO.setStar(src.getStar());
		cscVO.setId(src.getId());
		return cscVO;
	}

	public SwordRoleCard getSwordRoleCard(int pos, List<SwordRoleCard> cards) {
		for (int j = 0; j < cards.size(); j++) {
			SwordRoleCard src = cards.get(j);
			if (pos == src.getPos()) {
				return src;
			}
		}
		return null;
	}

}