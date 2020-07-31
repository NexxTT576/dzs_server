package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.role.JiBan;
import com.mdy.dzs.data.domain.superbattle.SuperBattle;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.RoleLineupAid.RLineupCardVO;
import com.mdy.dzs.game.domain.RoleLineupAid.RLineupVO;
import com.mdy.dzs.game.domain.RoleLineupAid.RoleLineupAid;
import com.mdy.dzs.game.domain.RoleLineupAid.RoleLineupCardAid;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.dzs.game.fight.domain.FightEndCardVO;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 用户辅助阵型
 * 
 * @author zhou
 *
 */
public class RoleLineupAidAO extends BaseAO {
	private CacheManager cacheManager;
	private RoleAO roleAO;

	public RoleLineupAidAO(CacheManager cacheManager, RoleAO roleAO) {
		this.cacheManager = cacheManager;
		this.roleAO = roleAO;
	}

	//
	/**
	 * 查询
	 */
	public RoleLineupAid query(int id) {
		return roleLineupAidDAO().query(id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleLineupAid> queryList() {
		return roleLineupAidDAO().queryList();
	}

	/**
	 * 添加
	 * 
	 * @param RoleLineupAid
	 */
	public void add(RoleLineupAid rla) {
		roleLineupAidDAO().add(rla);
	}

	/**
	 * 更新
	 * 
	 * @param RoleLineupAid
	 */
	public void update(RoleLineupAid rla) {
		roleLineupAidDAO().update(rla);
	}

	// ================================================根据sysId获得阵型===========================================================//
	/**
	 * 刷新数据
	 * 
	 * @param roleLineupAid
	 */
	public void refresh(RoleLineupAid roleLineupAid, SuperBattle sb, int roleId) {
		roleLineupAid.setRefreshTime(new Date());
		roleLineupAidDAO().update(roleLineupAid);
		// 更新阵容卡牌
		if (sb.getRemUsed() == 1) {
			roleLineupCardAidDAO().deleteByRoleId_SysId(roleId, sb.getId());
		}

	}

	// 获取阵型 以及卡牌列表（根据系统配置表）
	public RLineupVO getLineup(Role doc, int sysId) throws BizException {

		SuperBattle sb = cacheManager.getExistValueByKey(SuperBattle.class, sysId);
		RoleLineupAid roleLineupAid = roleLineupAidDAO().queryByRoleIdSysId(doc.getId(), sysId);
		// 刷新时间
		if (roleLineupAid != null && !DateUtil.isToday(roleLineupAid.getRefreshTime())) {
			refresh(roleLineupAid, sb, doc.getId());
		}
		List<RoleLineupCardAid> usedList = null;
		List<Integer> fmt = new ArrayList<Integer>();
		// 计算战力用的阵型
		List<SwordCard> scList = new ArrayList<SwordCard>();
		Map<Integer, SwordCard> scMap = new HashMap<Integer, SwordCard>();
		// 初始的阵型
		boolean isInitFmt = false;
		List<List<Integer>> curLineup = null;
		// 是否记忆阵容（0-否；1-是）
		if (roleLineupAid == null || sb.getRemBattle() == 0) {
			// 不存在
			// 获得初始阵型
			if (sb.getInitBattle() == 1) {// 主阵容
				fmt = doc.getFmtCardAry();
				isInitFmt = true;
			} else if (sb.getInitBattle() == 2) {// 主角
				int maincard = doc.getFmtMainCardID();
				fmt.add(0);
				fmt.add(maincard);
				isInitFmt = true;
			}
			for (int i = 0; i < fmt.size(); i++) {
				if (fmt.get(i) == 0) {
					continue;
				}
				SwordCard sc = new SwordCard();
				sc.setId(fmt.get(i));
				sc.setOrder(i);
				RoleCard rc = roleCardDAO().query(fmt.get(i));
				sc.setRc(rc);
				sc.setPos(rc.getPos());
				// scList.add(sc);
				scMap.put(rc.getResId(), sc);
			}
		} else {
			// 存在
			curLineup = roleLineupAid.getCurLineup();
			fmt = new ArrayList<Integer>();
			for (int j = 0; j < curLineup.size(); j++) {

				List<Integer> list = curLineup.get(j);
				fmt.add(list.get(0));
				if (list.get(0) == 0) {
					continue;
				}
				SwordCard sc = new SwordCard();
				sc.setId(list.get(0));
				sc.setOrder(j + 1);
				RoleCard rc = roleCardDAO().query(list.get(0));
				sc.setRc(rc);
				sc.setPos(list.get(1));
				// scList.add(sc);
				scMap.put(rc.getResId(), sc);
			}
		}
		// 是否已使用过不允许上阵（0-否；1-是） //是否记忆血量怒气（0-否；1-是）
		if ((sb.getRemHealth() == 1 || sb.getRemUsed() == 1) && roleLineupAid != null) {
			usedList = roleLineupCardAidDAO().queryListByRoleId_SysId(doc.getId(), sysId);
		}
		RLineupVO rLineupVO = new RLineupVO();
		rLineupVO.setFmt(scList);
		List<RoleCard> selfCards = roleCardDAO().queryListByLevel(doc.getId(), sb.getLevel());
		// 用户的卡牌数组
		Map<Integer, RoleLineupCardAid> usedMap = new HashMap<Integer, RoleLineupCardAid>();
		if (usedList != null) {
			for (RoleLineupCardAid roleLineCard : usedList) {
				usedMap.put(roleLineCard.getResId(), roleLineCard);
			}
		}
		// 整理阵容卡牌结构
		for (RoleCard rc : selfCards) {
			boolean isContinue = false;
			Card card = cacheManager.getExistValueByKey(Card.class, rc.getResId());
			if (sb.getSex() != 0 && card.getSex() != 3) {
				// 筛选性别
				if (card.getSex() != sb.getSex()) {
					continue;
				}
			}
			// 查库中是否有此卡
			// 是否记忆血量怒气（0-否；1-是）
			int initLife = 0;
			int anger = 0;
			int pos = 0;
			int lifeRate = 0;
			int order = 0;
			Map<Integer, Integer> propMaps = roleAO.calcCardProps(doc, rc);
			initLife = RoleAttackCalcManager.caclLife(card.getBase(), card.getLead(), propMaps);
			if (isInitFmt && fmt.indexOf(rc.getId()) != -1) {
				anger = card.getAnger().get(rc.getCls());
				pos = rc.getPos();
				lifeRate = 10000;
			} else {
				if (usedList == null) {
					// 没有记过卡牌
					anger = card.getAnger().get(rc.getCls());
					lifeRate = 10000;
				} else {
					// 根据id 查找
					RoleLineupCardAid roleLineupCardAid = usedMap.get(rc.getResId());
					if (roleLineupCardAid != null) {
						if (roleLineupCardAid.getCardId() == rc.getId()) {
							if (sb.getRemHealth() == 1) {
								anger = roleLineupCardAid.getAnger();
								lifeRate = roleLineupCardAid.getLifeRate();
							} else {
								anger = card.getAnger().get(rc.getCls());
								lifeRate = 10000;
							}
							if (sb.getRemUsed() == 1) {
								isContinue = true;
							}
						} else {
							isContinue = true;
						}
					}
				}
			}
			if (isContinue) {
				continue;
			}
			// 记过阵容 pos>0,其余卡牌等于0
			SwordCard sc = scMap.get(rc.getResId());
			if (sc != null) {
				if (sc.getId() == rc.getId()) {
					pos = sc.getPos();
					order = sc.getOrder();
					scList.add(sc);
				} else {
					continue;
				}
			}
			RLineupCardVO rLineupCardVO = new RLineupCardVO(rc.getId(), rc.getResId(), rc.getCls(), rc.getLevel(),
					rc.getStar());
			rLineupCardVO.setAnger(anger);
			rLineupCardVO.setOrder(order);
			rLineupCardVO.setPos(pos);
			rLineupCardVO.setInitLife(initLife);
			rLineupCardVO.setLife(lifeRate * initLife);
			rLineupVO.getCards().add(rLineupCardVO);

		}
		return rLineupVO;
	}

	/**
	 * 根据系统id 进行保存
	 * 
	 * @param sysId
	 * @return
	 * @throws BizException
	 */
	public void saveLineup(Role doc, int sysId, List<FightEndCardVO> selfEndList, List<FightEndCardVO> tgtEndList,
			List<RoleLineupCardAid> selfList) throws BizException {
		SuperBattle sb = cacheManager.getExistValueByKey(SuperBattle.class, sysId);
		if (sb.getRemBattle() == 1 || sb.getRemUsed() == 1 || sb.getRemHealth() == 1) {// 记录阵型
			// 查询阵型库
			RoleLineupAid roleLineupAid = roleLineupAidDAO().queryByRoleIdSysId(doc.getId(), sysId);
			if (roleLineupAid == null) {
				// 存阵型
				roleLineupAid = new RoleLineupAid(doc.getId(), sysId, new Date());
				roleLineupAidDAO().add(roleLineupAid);
			}

			Map<Integer, RoleLineupCardAid> selfMap = new HashMap<Integer, RoleLineupCardAid>();
			for (RoleLineupCardAid rlc : selfList) {
				selfMap.put(rlc.getCardId(), rlc);
			}
			// 旧阵型的卡牌里去出此sysId
			List<List<Integer>> oldCur = roleLineupAid.getCurLineup();
			for (Iterator<List<Integer>> iterator = oldCur.iterator(); iterator.hasNext();) {
				List<Integer> list = (List<Integer>) iterator.next();
				int cardId = list.get(0);
				if (selfMap.get(cardId) != null) {
					continue;
				}
				// 把之前阵型的battle里的阵型id去掉
				RoleCard rc = roleCardDAO().query(cardId);
				List<Integer> battle = rc.getBattle();
				for (Iterator<Integer> iterator2 = battle.iterator(); iterator2.hasNext();) {
					Integer integer = (Integer) iterator2.next();
					if (integer == sysId) {
						iterator2.remove();
					}
				}
				roleCardDAO().updateBattle(battle, rc.getId(), doc.getId());
			}

			// 新阵型
			List<List<Integer>> cur = new ArrayList<List<Integer>>();
			for (int i = 0; i < selfEndList.size(); i++) {
				FightEndCardVO fecVO = selfEndList.get(i);
				RoleLineupCardAid rlc = getRoleLineupCardAid(fecVO.getPos(), selfList);
				RoleLineupCardAid rlcCard = roleLineupCardAidDAO().queryByRoleId_SysId_CardId(doc.getId(), sysId,
						rlc.getCardId());
				RoleCard rc = roleCardDAO().query(rlc.getCardId());
				if (null != rlcCard) {
					// 库中有
					rlc.setLifeRate(fecVO.getLifeRate());
					rlc.setAnger(fecVO.getAnger());
					rlc.setPos(fecVO.getPos());
					rlc.setResId(fecVO.getId());
					if (sb.getRemBattle() == 1) {// 记忆阵型
						roleLineupCardAidDAO().updatePos(rlc.getId(), fecVO.getPos());
					}
					// TODO 记忆血量
				} else {
					// 库中无
					// 存卡牌
					rlc = new RoleLineupCardAid(doc.getId(), sysId, rlc.getCardId(), rc.getResId());
					rlc.setResId(fecVO.getId());
					if (sb.getRemBattle() == 1) {// 记忆阵型
						rlc.setPos(fecVO.getPos());
					}
					roleLineupCardAidDAO().add(rlc);
				}
				List<Integer> battle = rc.getBattle();
				boolean haveBattle = false;
				for (Integer integer : battle) {
					if (integer == sysId) {
						haveBattle = true;
						break;
					}
				}
				if (!haveBattle) {
					battle.add(sysId);
					roleCardDAO().updateBattle(battle, rc.getId(), doc.getId());
				}
				// 记录新阵型
				List<Integer> lineUpOne = new ArrayList<Integer>();
				lineUpOne.add(rlc.getCardId());
				lineUpOne.add(fecVO.getPos());
				cur.add(lineUpOne);
			}
			if (sb.getRemBattle() == 1 && oldCur.toString() != cur.toString()) {// 记忆阵型
				String oldStr = oldCur.toString();
				String curStr = cur.toString();
				if (!oldStr.equals(curStr)) {
					roleLineupAid.setCurLineup(cur);
					roleLineupAidDAO().update(roleLineupAid);
				}
			}
			// TODO npc 不存在记血记位置等问题
		}
	}

	/**
	 * 保存当前阵型
	 * 
	 * @param fmt
	 * @param doc
	 * @param sysId
	 */
	public void saveCurLineup(List<SwordCard> fmt, Role doc, int sysId) {
		RoleLineupAid roleLineupAid = roleLineupAidDAO().queryByRoleIdSysId(doc.getId(), sysId);
		if (roleLineupAid == null) {
			// 存阵型
			roleLineupAid = new RoleLineupAid(doc.getId(), sysId, new Date());
			List<List<Integer>> cur = new ArrayList<List<Integer>>();
			roleLineupAid.setCurLineup(cur);
			roleLineupAidDAO().add(roleLineupAid);
		}
		Map<Integer, SwordCard> fmtMap = new HashMap<Integer, SwordCard>();
		for (SwordCard swordCard : fmt) {
			fmtMap.put(swordCard.getId(), swordCard);
		}
		// 旧阵型的卡牌里去出此sysId
		List<List<Integer>> oldCur = roleLineupAid.getCurLineup();
		for (Iterator<List<Integer>> iterator = oldCur.iterator(); iterator.hasNext();) {
			List<Integer> list = (List<Integer>) iterator.next();
			int cardId = list.get(0);
			if (fmtMap.get(cardId) != null) {
				continue;
			}
			// 把之前阵型的battle里的阵型id去掉
			RoleCard rc = roleCardDAO().query(cardId);
			List<Integer> battle = rc.getBattle();
			if (battle != null) {
				for (Iterator<Integer> iterator2 = battle.iterator(); iterator2.hasNext();) {
					Integer integer = (Integer) iterator2.next();
					if (integer == sysId) {
						iterator2.remove();
					}
				}
			}
			if (battle == null)
				battle = new ArrayList<Integer>();
			roleCardDAO().updateBattle(battle, rc.getId(), doc.getId());
		}
		List<List<Integer>> cur = new ArrayList<List<Integer>>();
		for (SwordCard swordCard : fmt) {
			// 存卡牌
			List<Integer> lineUpOne = new ArrayList<Integer>();
			lineUpOne.add(swordCard.getId());
			lineUpOne.add(swordCard.getPos());
			// 存阵型
			roleLineupAid.setCurLineup(cur);
			roleLineupAid.getCurLineup().add(lineUpOne);
			List<Integer> battle = swordCard.getRc().getBattle();
			boolean haveBattle = false;
			if (battle != null && battle.indexOf(sysId) != -1) {
				haveBattle = true;
			}
			if (battle == null)
				battle = new ArrayList<Integer>();
			if (!haveBattle) {
				battle.add(sysId);
				roleCardDAO().updateBattle(battle, swordCard.getRc().getId(), doc.getId());
			}
		}
		roleLineupAidDAO().update(roleLineupAid);
	}

	/**
	 * 获得卡牌
	 * 
	 * @param roleId
	 * @param sysId
	 * @param fmt
	 * @return
	 * @throws BizException
	 */
	public List<RoleLineupCardAid> getCards(Role doc, int sysId, List<SwordCard> fmt) throws BizException {
		List<RoleLineupCardAid> list = new ArrayList<RoleLineupCardAid>();
		SuperBattle sb = cacheManager.getExistValueByKey(SuperBattle.class, sysId);
		for (int i = 0; i < fmt.size(); i++) {
			SwordCard rlc = fmt.get(i);
			RoleCard rc = roleCardDAO().query(rlc.getId());
			//
			RoleLineupCardAid rlca = roleLineupCardAidDAO().queryByRoleId_SysId_CardId(doc.getId(), sysId, rc.getId());
			if (null == rlca) {
				RoleLineupCardAid rlcaNew = new RoleLineupCardAid(doc.getId(), sb.getId(), rc.getId(), rc.getResId());
				rlca = getRoleLineupCardAid(doc, sb, rc, rlcaNew);
				rlca.setPos(rlc.getPos());

			} else {
				// TODO 库中已有的卡 将库中卡的血和怒气赋值给原卡
				rlca = getRoleLineupCardAid(doc, sb, rc, rlca);
				rlca.setPos(rlc.getPos());
				rlca.setLifeRate(10000);
			}
			// 计算附加属性值
			List<Prop> props = new ArrayList<Prop>();
			updateCardRelation(doc, rc);
			Map<Integer, Integer> propMaps = roleAO.calcCardProps(doc, rc, rlc.getOrder());
			props = RoleAttackCalcManager.convProps(propMaps);
			rlca.setProps(props);
			list.add(rlca);
		}
		return list;
	}

	/**
	 * 存战斗完数据中获得卡牌
	 * 
	 * @param pos
	 * @param cards
	 * @return
	 */
	private RoleLineupCardAid getRoleLineupCardAid(int pos, List<RoleLineupCardAid> cards) {
		for (int j = 0; j < cards.size(); j++) {
			RoleLineupCardAid rlc = cards.get(j);
			if (pos == rlc.getPos()) {
				return rlc;
			}
		}
		return null;
	}

	/**
	 * 根据角色卡生成 战斗卡
	 * 
	 * @param rc
	 * @param doc
	 * @param type
	 * @return
	 * @throws BizException
	 */
	private RoleLineupCardAid getRoleLineupCardAid(Role doc, SuperBattle sb, RoleCard rc, RoleLineupCardAid rlca)
			throws BizException {
		Card rcData = cacheManager.getExistValueByKey(Card.class, rc.getResId());
		rlca.setCls(rc.getCls());
		// 计算附加属性值
		List<Prop> props = new ArrayList<Prop>();
		Map<Integer, Integer> propMaps = roleAO.calcCardProps(doc, rc);
		props = RoleAttackCalcManager.convProps(propMaps);
		rlca.setProps(props);
		int initLife = RoleAttackCalcManager.caclLife(rcData.getBase(), rcData.getLead(), propMaps);
		rlca.setInitLife(initLife);
		rlca.setLifeRate(10000);
		rlca.setPos(rc.getPos());
		rlca.setResId(rc.getResId());
		if (rc.getId() == doc.getFmtMainCardID()) {
			rc.setLevel(doc.getLevel());
		}
		rlca.setLevel(rc.getLevel());
		rlca.setShenIDAry(rc.getShenIDAry());
		rlca.setShenLvAry(rc.getShenLvAry());
		rlca.setAnger(rcData.getAnger().get(rc.getCls()));
		rlca.setStar(rc.getStar());
		return rlca;
	}

	/**
	 * 更新属性
	 * 
	 * @param doc
	 * @param curCard
	 * @throws BizException
	 */
	private void updateCardRelation(Role doc, RoleCard curCard) throws BizException {
		int roleId = doc.getId();
		Card cardData = cacheManager.getExistValueByKey(Card.class, curCard.getResId());
		List<Integer> jbAry = cardData.getFate1();
		List<Integer> relation = new ArrayList<Integer>();
		for (int i = 0; i < jbAry.size(); i++) {
			int jbId = jbAry.get(i);
			JiBan jbItem = cacheManager.getExistValueByKey(JiBan.class, jbId);
			boolean bExist = true;
			for (int j = 1; j <= 6; j++) {
				int rid = jbItem.getCond(j);
				if (rid == 0)
					continue;
				boolean itemFind = false;
				if (jbItem.getType() != 1) {
					Item item = cacheManager.getExistValueByKey(Item.class, rid);
					if (item.getType() == Packet.POS_EQUIP) {
						itemFind = equipDAO().querySameResEquipCount(roleId, rid) != 0;
					} else {
						itemFind = roleGongDAO().querySameResGong(roleId, rid).size() != 0;
					}
				}
				if (!itemFind) {
					bExist = false;
					break;
				}
			}
			if (bExist) {
				relation.add(jbItem.getId());
			}
		}
		curCard.setRelation(relation);
	}
}
