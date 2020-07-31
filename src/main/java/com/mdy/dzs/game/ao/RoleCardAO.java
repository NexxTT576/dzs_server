package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.card.Carden;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.Money;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.role.JiBan;
import com.mdy.dzs.data.domain.shentong.ShenTong;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.card.CardRelation;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.card.RoleCardEffect;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.RoleCardException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

public class RoleCardAO extends BaseAO {

	private static final Logger logger = LoggerFactory.get(RoleCardAO.class);

	private CacheManager cacheManager;
	private RoleAO roleAO;
	private RoleRoadAO roleRoadAO;
	private MissionExecAO missionExecAO;

	public MissionExecAO getMissionExecAO() {
		return missionExecAO;
	}

	public void setMissionExecAO(MissionExecAO missionExecAO) {
		this.missionExecAO = missionExecAO;
	}

	/** 某卡牌所属 对应卡牌及jiban(idx) */
	private Map<Integer, List<CardRelation>> cardRelations;

	public RoleCardAO(CacheManager cacheManager, RoleRoadAO roleRoadAO) {
		this.cacheManager = cacheManager;
		this.roleRoadAO = roleRoadAO;
		cardRelations = new HashMap<Integer, List<CardRelation>>();
	}

	@Override
	public void start() {
		Map<Integer, Card> cards = cacheManager.getValues(Card.class);
		// Map<Integer, List<Integer>> rids = new HashMap<Integer, List<Integer>>();
		for (Card cardData : cards.values()) {
			List<Integer> jbAry = cardData.getFate1();
			for (int i = 0; i < jbAry.size(); i++) {
				int jbId = jbAry.get(i);
				JiBan jbItem = cacheManager.getValueByKey(JiBan.class, jbId);
				if (jbItem == null) {
					logger.error("now has jiban:" + jbId + "  " + cardData.getId());
					continue;
				}
				for (int j = 1; j <= 6; j++) {
					int rid = jbItem.getCond(j);
					if (rid == 0)
						continue;
					if (jbItem.getType() == 1) {
						if (!cardRelations.containsKey(rid)) {
							cardRelations.put(rid, new ArrayList<CardRelation>());
							// rids.put(rid, new ArrayList<Integer>());
						}

						// if(rids.get(rid).indexOf(cardData.getId()) != -1) continue;
						// rids.get(rid).add(cardData.getId());

						List<CardRelation> find = cardRelations.get(rid);
						find.add(new CardRelation(cardData, jbItem));
					}
				}
			}
		}
		super.start();
	}

	public RoleCard addCard(Role role, int cardId, int pos) throws BizException {
		Card cardData = cacheManager.getValueByKey(Card.class, cardId);
		if (cardData == null) {
			throw RoleCardException.getException(RoleCardException.EXCE_CARD_NOT_FOUND);
		}
		return addNewCard(role, cardData, pos);
	}

	/**
	 * 添加新卡片
	 * 
	 * @param role
	 * @param cardData
	 * @return
	 * @throws BizException
	 */
	public RoleCard addNewCard(Role role, Card cardData, int pos) throws BizException {
		RoleCard curCard = new RoleCard();

		curCard.setResId(cardData.getId());
		curCard.setStar(cardData.getStar().get(0));
		curCard.setPos(pos);
		curCard.setLevel(1);

		List<Integer> talents = cardData.getTalent();
		List<Integer> shenIDAry = new ArrayList<Integer>();
		List<Integer> shenLvAry = new ArrayList<Integer>();
		for (int j = 0; j < talents.size(); j++) {
			int talentGrpId = talents.get(j);
			if (talentGrpId == 0)
				continue;
			ShenTong talentData = cacheManager.getValueByKey(ShenTong.class, talentGrpId);
			if (talentData == null) {
				logger.warn("not has talentData:" + talentGrpId);
				continue;
			}
			shenIDAry.add(talentData.getArrTalent().get(0));
			if (talentData.getArrCond().get(0) == 0)
				shenLvAry.add(1);
			else
				shenLvAry.add(0);
		}
		curCard.setShenIDAry(shenIDAry);

		curCard.setShenLvAry(shenLvAry);
		curCard.setRoleId(role.getId());

		curCard.setRelation(new ArrayList<Integer>());
		curCard.setProps(new ArrayList<Prop>());
		// 存储卡片
		roleCardDAO().add(curCard);
		roleCardDAO().addRecord(curCard);

		if (curCard.getStar() >= 4 && cardData.getId() > 2) {
			roleRoadAO.addCard(role.getId(), cardData);
		}
		checkCardRelation(curCard, true);
		missionExecAO.newRoleCard(role, curCard);
		return curCard;
	}

	/**
	 * 查询人物卡片列表
	 * 
	 * @param acc
	 * @return
	 */
	public List<RoleCard> queryListByRoleId(int roleId) {
		return roleCardDAO().queryListByRoleId(roleId);
	}

	/**
	 * 查询人物上阵卡片列表
	 * 
	 * @param acc
	 * @return
	 */
	public List<RoleCard> queryLineupListByRoldId(int roleId) {
		return roleCardDAO().queryLineupListByRoldId(roleId);
	}

	/**
	 * 查询人物卡片
	 * 
	 * @param acc
	 * @return
	 */
	public RoleCard queryById(int id) {
		if (id == 0)
			return null;
		return roleCardDAO().query(id);
	}

	/**
	 * 找到已存在的卡片
	 * 
	 * @param id
	 * @return
	 * @throws BizException
	 */
	public RoleCard queryExistCardById(Role doc, int id) throws BizException {
		RoleCard card = id == 0 ? null : roleCardDAO().query(id);
		if (card == null) {
			throw RoleCardException.getException(RoleCardException.EXCE_CARD_NOT_EXIST, id);
		}
		if (card.getRoleId() != doc.getId()) {
			throw RoleCardException.getException(RoleCardException.EXCE_CARD_NOT_YOUR, id, doc.getId());
		}
		return card;
	}

	/**
	 * 查找相同资源的卡片
	 * 
	 * @param acc
	 * @param resId
	 * @return
	 */
	public List<RoleCard> querySameResCard(int roleId, int resId) {
		return roleCardDAO().querySameResCard(roleId, resId);
	}

	public void update(RoleCard mCard) {
		roleCardDAO().update(mCard);

	}

	public void updateLevel(RoleCard mCard) {
		roleCardDAO().updateLevel(mCard);

	}

	public RoleCard queryByRoleIdPos(int roleId, int pos) {
		return roleCardDAO().queryByRoleIdPos(roleId, pos);
	}

	public void delete(RoleCard rcard) {
		roleCardDAO().delete(rcard);
		checkCardRelation(rcard, false);
	}

	// public List<RoleCard> queryListByIds(List<Integer> fmtCardAry) {
	// return roleCardDAO().queryListByIds(fmtCardAry);
	// }

	public List<RoleCard> queryListByLevel(int roleId, int level) {
		return roleCardDAO().queryListByLevel(roleId, level);
	}

	public void refreshTalentIdByLV(RoleCard mCard) throws BizException {
		Card mcardData = cacheManager.getExistValueByKey(Card.class, mCard.getResId());
		for (int i = 0; i < mcardData.getTalent().size(); i++) {
			int lv = mCard.getShenLvAry().get(i);
			int talentGrpId = mcardData.getTalent().get(i);
			if (talentGrpId == 0)
				continue;
			ShenTong talentData = cacheManager.getExistValueByKey(ShenTong.class, talentGrpId);
			int nxtID = talentData.getArrTalent().get(lv);
			mCard.getShenIDAry().set(i, nxtID);
		}
	}

	/**
	 * 检测卡牌羁绊
	 * 
	 * @param rcard
	 * @param addOrDel
	 */
	private void checkCardRelation(RoleCard rcard, boolean addOrDel) {
		int roleId = rcard.getRoleId();
		int cardId = rcard.getResId();
		List<CardRelation> relations = cardRelations.get(cardId);
		if (relations == null)
			return;
		boolean check = false;
		if ((addOrDel == true)// 刚添加 需检查
				|| (addOrDel == false && roleCardDAO().querySameResCardCount(roleId, cardId) == 0)// 刚移除 需检查
		) {
			check = true;
		}
		if (check == false)
			return;
		checkCardRelation(roleId, cardId, addOrDel ? 1 : 2);
	}

	/**
	 * 
	 * @param roleId
	 * @param cardId
	 * @param state  0全检测 1添加检测 2移除检测
	 */
	public void checkCardRelation(int roleId, int cardId, int state) {
		Calendar now = Calendar.getInstance();
		List<CardRelation> relations = cardRelations.get(cardId);
		if (relations == null)
			return;
		for (CardRelation cardRelation : relations) {
			int cid = cardRelation.getCard().getId();
			RoleCardEffect effect = roleCardEffectDAO().queryByRoleIdCardId(roleId, cid);
			if (effect == null) {
				effect = new RoleCardEffect();
				effect.setRoleId(roleId);
				effect.setCardId(cid);
				effect.setEffectCardRelation(new ArrayList<Integer>());
				roleCardEffectDAO().add(effect);
			}
			boolean itemFind = true;
			JiBan jibanItem = cardRelation.getRelation();
			List<Integer> effects = effect.getEffectCardRelation();
			int index = effects.indexOf(jibanItem.getId());
			if (index != -1 && state == 1)
				continue;// 有羁绊 添加
			if (index == -1 && state == 2)
				continue;// 无羁绊 删除
			for (int j = 1; j <= 6; j++) {
				int rid = jibanItem.getCond(j);
				if (rid == 0)
					continue;
				int findCount = roleCardDAO().querySameResCardCount(roleId, rid);
				if (findCount == 0)
					itemFind = false;
			}

			int size = effects.size();
			if (itemFind && index == -1) {
				effects.add(jibanItem.getId());
			} else if (!itemFind && index != -1) {
				effects.remove(index);
			}
			if (effects.size() != size) {
				roleCardEffectDAO().update(effect);
			}
		}
		logger.debug("checkCardRelation:" + cardId + "/"
				+ (Calendar.getInstance().getTimeInMillis() - now.getTimeInMillis()));
	}

	/**
	 * 获取羁绊对应卡牌id
	 * 
	 * @param relations
	 * @return
	 */
	Map<Integer, List<Integer>> getRelationCardIds(List<Integer> relations) {
		Map<Integer, List<Integer>> cardIds = new HashMap<Integer, List<Integer>>();
		for (Integer jibanId : relations) {
			JiBan jbItem = cacheManager.getValueByKey(JiBan.class, jibanId);
			if (jbItem == null)
				continue;
			for (int j = 1; j <= 6; j++) {
				int rid = jbItem.getCond(j);
				if (rid == 0)
					continue;
				if (jbItem.getType() == 1) {
					if (!cardIds.containsKey(rid)) {
						cardIds.put(rid, new ArrayList<Integer>());
					}
					cardIds.get(rid).add(jibanId);
				}
			}
		}
		return cardIds;
	}

	/**
	 * 炼化返回的东西
	 * 
	 * @param rc
	 * @param cardData
	 * @param cardenData
	 * @return
	 * @throws BizException
	 */
	public List<ProbItem> getFurnaceList(RoleCard rc, Card cardData, Carden cardenData) throws BizException {
		List<ProbItem> furnaceBack = new ArrayList<ProbItem>();
		int sumexp = cardenData.getSumexp().get(rc.getStar() - 1);
		int silver = cardData.getSoul().get(1) + rc.getCurExp();
		int soul = cardData.getSoul().get(0) + sumexp + rc.getCurExp();
		int jade = cardData.getSoul().get(2);
		Money silverData = cacheManager.getExistValueByKey(Money.class, 2);
		Money soulData = cacheManager.getExistValueByKey(Money.class, 7);
		Money jadeData = cacheManager.getExistValueByKey(Money.class, 10);
		furnaceBack.add(new ProbItem(0, silverData.getItem(), silver));
		furnaceBack.add(new ProbItem(0, soulData.getItem(), soul));
		furnaceBack.add(new ProbItem(0, jadeData.getItem(), jade));
		return furnaceBack;
	}

	/**
	 * 重生返还的东西
	 * 
	 * @param rc
	 * @param cardData
	 * @return
	 * @throws BizException
	 */
	public List<ProbItem> getRebornBack(RoleCard rc, Card cardData, Carden cardenData) throws BizException {
		List<ProbItem> rebornBack = new ArrayList<ProbItem>();
		int sumexp = cardenData.getSumexp().get(rc.getStar() - 1);
		int soul = sumexp + rc.getCurExp();// 侠魂
		int silver = sumexp + rc.getCurExp();// 升级花费
		int clsCost = 0;// 进阶花费
		Map<Integer, Integer> itemMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> cardMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < rc.getCls(); i++) {
			clsCost += cardData.getSilver().get(i);
			logger.info(rc.getResId() + ",cls" + rc.getCls());
			// 进阶丹item表
			resetMap(itemMap, i, cardData.getItem1(), cardData.getNumber1());
			// 内外功item表
			resetMap(itemMap, i, cardData.getItem2(), cardData.getNumber2());
			resetMap(itemMap, i, cardData.getItem3(), cardData.getNumber3());
			// 卡牌card表
			resetMap(cardMap, i, cardData.getCard(), cardData.getNumber());
		}
		silver += clsCost;
		Money silverData = cacheManager.getExistValueByKey(Money.class, 2);
		Money soulData = cacheManager.getExistValueByKey(Money.class, 7);
		rebornBack.add(new ProbItem(0, silverData.getItem(), silver));
		rebornBack.add(new ProbItem(0, soulData.getItem(), soul));

		// 处理物品列表
		Iterator<Map.Entry<Integer, Integer>> it = itemMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
			Integer key = Integer.parseInt(entry.getKey().toString());
			Integer value = Integer.parseInt(entry.getValue().toString());
			Item itemData = cacheManager.getExistValueByKey(Item.class, key);
			ProbItem idNum = new ProbItem(itemData.getType(), key, value);
			rebornBack.add(idNum);
		}
		// 处理卡牌列表
		Iterator<Map.Entry<Integer, Integer>> itCard = cardMap.entrySet().iterator();
		while (itCard.hasNext()) {
			Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) itCard.next();
			Integer key = Integer.parseInt(entry.getKey().toString());
			Integer value = Integer.parseInt(entry.getValue().toString());
			Card itemData = cacheManager.getExistValueByKey(Card.class, key);
			ProbItem idNum = new ProbItem(8, key, value);
			rebornBack.add(idNum);
		}
		return rebornBack;
	}

	private void resetMap(Map<Integer, Integer> itemMap, int index, List<Integer> itemIds, List<Integer> numbers) {
		int key = itemIds.get(index);
		if (key == 0) {
			return;
		}
		if (!itemMap.containsKey(key)) {
			itemMap.put(key, numbers.get(index));
		} else {
			int itemNum = itemMap.get(key);
			itemNum += numbers.get(index);
			itemMap.put(key, itemNum);
		}
	}

	/**
	 * 重生卡牌的消费
	 * 
	 * @param rc
	 * @return 5星侠客：初始20元宝，每进阶+1增加20元宝 4星侠客：初始10元宝，每进阶+1增加10元宝 其他：初始5元宝，每进阶+1增加5元宝
	 * 
	 */
	public int cost(RoleCard rc) {
		int gold;
		if (rc.getStar() > 4) {
			gold = 20 + rc.getCls() * 20;
		} else if (rc.getStar() > 3) {
			gold = 10 + rc.getCls() * 10;
		} else {
			gold = 5 + rc.getCls() * 5;
		}
		return gold;
	}

	/**
	 * @param rc
	 * @param sys侠客出售
	 */
	public void removeCards(Role doc, List<RoleCard> rcList, List<Card> datas, int sysType) {
		roleCardDAO().deleteCards(doc.getId(), rcList);
		Map<Integer, RoleCard> checks = new HashMap<Integer, RoleCard>();
		// 添加日志
		for (int i = 0; i < rcList.size(); i++) {
			RoleCard rc = rcList.get(i);
			Card data = datas.get(i);
			checks.put(data.getId(), rc);
			RoleItemLog log = new RoleItemLog(doc, sysType, Packet.POS_CARD, rc.getId(), -1, 0, data.getId() + "");
			roleItemLogDAO().add(log);
		}
		// 计算羁绊
		for (RoleCard rc : checks.values()) {
			checkCardRelation(rc, false);
		}
	}

	/**
	 * @param role
	 * @param rc
	 */
	public void lockCard(Role role, RoleCard rc, int lock) {
		rc.setLock(lock);
		roleCardDAO().updateLock(rc);
	}

	public List<Integer> queryRecordCardId(int roleId) {
		return roleCardDAO().queryRecordCardId(roleId);
	}

}
