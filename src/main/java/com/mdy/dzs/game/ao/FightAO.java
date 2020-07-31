/**
 * 
 */
package com.mdy.dzs.game.ao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.robot.MoBan;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.RoleLineupAid.RoleLineupCardAid;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.snatch.BeSnatchCard;
import com.mdy.dzs.game.domain.swordfight.SwordRoleCard;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.SystemException;
import com.mdy.dzs.game.fight.domain.FighterInfo;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;
import com.mdy.sharp.container.biz.BizException;

/**
 * 创建战斗角色
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月14日 上午11:57:39
 */
public class FightAO extends BaseAO {

	private CacheManager cacheManager;
	private RoleAO roleAO;

	public FightAO(CacheManager cacheManager, RoleAO roleAO) {
		this.cacheManager = cacheManager;
		this.roleAO = roleAO;
	}

	/**
	 * 创建人物对应战斗角色
	 * 
	 * @param role
	 * @return
	 */
	public FighterInfo createFighterInfoByRole(Role role) {
		FighterInfo info = new FighterInfo();
		String acc = role.getAccount();
		int roleId = role.getId();
		for (int i = 1; i <= 6; i++) {
			RoleCard ncardItem = roleCardDAO().queryByRoleIdPos(roleId, i);
			if (ncardItem != null && ncardItem.getId() == role.getFmtMainCardID()) {
				info.setPos(i);
			}
			info.setCard(i - 1, ncardItem);
			if (ncardItem != null) {
				List<Prop> props = RoleAttackCalcManager.convProps(roleAO.calcCardProps(role, ncardItem));
				info.addProps(i - 1, props);
			}
		}

		return info;
	}

	/**
	 * 创建人物对应战斗角色
	 * 
	 * @param role
	 * @return
	 */
	public FighterInfo createFighterInfoByRole(Role role, List<Integer> deadPos, boolean forbitTalent,
			boolean forbitBuff) {
		FighterInfo info = new FighterInfo();
		String acc = role.getAccount();
		int roleId = role.getId();
		for (int i = 1; i <= 6; i++) {
			if (deadPos.indexOf(i) != -1)
				continue;
			RoleCard ncardItem = roleCardDAO().queryByRoleIdPos(roleId, i);
			if (ncardItem != null && ncardItem.getId() == role.getFmtMainCardID()) {
				info.setPos(i);
			}
			info.setCard(i - 1, ncardItem);
			if (ncardItem != null) {
				if (forbitTalent) {// 不加神通
					ncardItem.setShenLvAry(new ArrayList<Integer>());
					ncardItem.setShenIDAry(new ArrayList<Integer>());
				}
				List<Prop> props = RoleAttackCalcManager.convProps(roleAO.calcCardProps(role, ncardItem));
				info.addProps(i - 1, props);
			}
			info.setForbitBuff(forbitBuff);
		}

		return info;
	}

	/**
	 * 创建由NPC id 组成的战斗信息
	 * 
	 * @param cardIds
	 * @param forbitTalent
	 * @param forbitBuff
	 * @return
	 * @throws BizException
	 */
	public FighterInfo createFighterInfoByCardIds(List<Integer> cardIds, List<Integer> poss, boolean forbitTalent,
			boolean forbitBuff) throws BizException {
		FighterInfo info = new FighterInfo();
		for (int i = 0; i < poss.size(); i++) {
			if (cardIds.get(i) == 0)
				continue;
			Card cardObj = cacheManager.getExistValueByKey(Card.class, cardIds.get(i));
			if (cardObj != null && info.getPos() == 0) {
				info.setPos(poss.get(i));
			}
			cardObj = clone(cardObj);
			info.setCard(poss.get(i) - 1, cardObj);
			info.setForbitBuff(forbitBuff);
		}
		return info;
	}

	/**
	 * 创建cards 赋值模板 保证cardIds size 为7
	 * 
	 * @param role
	 * @return
	 * @throws BizException
	 */
	public FighterInfo createFighterInfoByCardIds(List<BeSnatchCard> cards, MoBan lvData) throws BizException {
		FighterInfo info = new FighterInfo();
		for (int i = 1; i <= 6; i++) {
			Card cardObj = cacheManager.getExistValueByKey(Card.class, cards.get(i - 1).getResId());
			if (cardObj != null && info.getPos() == 0) {
				info.setPos(i);
			}
			cardObj = clone(cardObj);
			cardObj.getAnger().set(0, lvData.getAnger());
			cardObj.setLead(lvData.getLead());
			cardObj.setBase(lvData.getBase());
			cardObj.setBaseRate(lvData.getBaseRate());
			cardObj.setBaseMod(lvData.getBaseMod());
			cardObj.setRare(lvData.getRare());
			cardObj.setRareRate(lvData.getRareRate());
			cardObj.setHeal(lvData.getHeal());
			cardObj.setRate(lvData.getRate());
			cardObj.setTalent(new ArrayList<Integer>());
			List<Integer> resist = new ArrayList<Integer>();
			resist.add(lvData.getResist());
			cardObj.setResist(resist);
			cardObj.setCrit(lvData.getCrit());
			info.setCard(i - 1, cardObj);
		}
		return info;
	}

	public FighterInfo createFighterInfoBySwordRoleCard(List<SwordRoleCard> cards) throws BizException {
		FighterInfo info = new FighterInfo();
		for (int i = 1; i <= 6; i++) {
			SwordRoleCard swordRoleCard = getSwordRoleCard(i, cards);
			if (swordRoleCard != null) {
				info.setPos(i);
			}
			info.setCard(i - 1, swordRoleCard);
			if (swordRoleCard != null) {
				info.addProps(i - 1, swordRoleCard.getProps());
			}
		}
		return info;
	}

	public FighterInfo createFighterInfoByRoleLineupCardAid(List<RoleLineupCardAid> cards, List<Integer> deadPos)
			throws BizException {
		FighterInfo info = new FighterInfo();
		for (int i = 1; i <= 6; i++) {
			if (deadPos.indexOf(i) != -1)
				continue;
			RoleLineupCardAid rlca = getRoleLineupCardAid(i, cards);
			if (rlca != null) {
				info.setPos(i);
			}
			info.setCard(i - 1, rlca);
			if (rlca != null) {
				info.addProps(i - 1, rlca.getProps());
			}
		}
		return info;
	}

	@SuppressWarnings("unchecked")
	private <T> T clone(T src) throws BizException {
		T res = null;
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(src);// 写对象，序列化
			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(byteIn);
			res = (T) ois.readObject(); // 读对象，反序列化
		} catch (Exception e) {
			// throw BaseException.getGlobalException(
			// "not clone:"+src);
			throw BaseException.getException(SystemException.EXCE_SYSTEM_ERROR, src.getClass());
		}
		return res;
	}

	private SwordRoleCard getSwordRoleCard(int i, List<SwordRoleCard> cards) {
		for (int j = 0; j < cards.size(); j++) {
			SwordRoleCard src = cards.get(j);
			if (i == src.getPos()) {
				return src;
			}
		}
		return null;
	}

	private RoleLineupCardAid getRoleLineupCardAid(int i, List<RoleLineupCardAid> cards) {
		for (int j = 0; j < cards.size(); j++) {
			RoleLineupCardAid rlca = cards.get(j);
			if (i == rlca.getPos()) {
				return rlca;
			}
		}
		return null;
	}
}
