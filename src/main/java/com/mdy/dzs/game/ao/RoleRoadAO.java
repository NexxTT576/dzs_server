package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.road.ComparatorRoleRoadCard;
import com.mdy.dzs.game.domain.road.ComparatorRoleRoadGift;
import com.mdy.dzs.game.domain.road.RoleRoad;
import com.mdy.dzs.game.domain.road.RoleRoadCard;
import com.mdy.dzs.game.domain.road.RoleRoadGift;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.sharp.container.biz.BizException;

/**
 * 名将之路
 * 
 * @author 房曈
 *
 */
public class RoleRoadAO extends BaseAO {
	//
	/** 奖励道具id列表 */
	private List<Integer> giftItemIds = new ArrayList<Integer>();

	private CacheManager cacheManager;

	public RoleRoadAO(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void start() {
		// 初始化奖励道具id列表
		Map<Integer, Item> items = cacheManager.getValues(Item.class);
		for (Item item : items.values()) {
			if (item.getType() == 11) {
				giftItemIds.add(item.getId());
			}
		}
		super.start();
	}

	/**
	 * 获取可赠送道具列表
	 * 
	 * @param doc
	 * @return
	 * @throws BizException
	 */
	public List<RoleRoadGift> getGiftListByRole(Role doc) throws BizException {
		List<RoleItem> items = packetDAO().queryRoleItemListByResIds(doc.getId(), giftItemIds);
		List<RoleRoadGift> res = new ArrayList<RoleRoadGift>();
		for (RoleItem roleItem : items) {
			RoleRoadGift gift = createGift(roleItem);
			res.add(gift);
		}
		ComparatorRoleRoadGift compar = new ComparatorRoleRoadGift();
		Collections.sort(res, compar);
		return res;
	}

	public RoleRoadGift createGift(RoleItem roleItem) throws BizException {
		RoleRoadGift gift = new RoleRoadGift();
		gift.setResId(roleItem.getItemId());
		gift.setNum(roleItem.getItemCnt());
		Item item = cacheManager.getExistValueByKey(Item.class, roleItem.getItemId());
		gift.setPrice(item.getPrice());
		return gift;
	}

	/**
	 * 主界面显示卡牌
	 * 
	 * @param data
	 * @return
	 */
	public int getRoadMainCardResId(RoleRoad data) {
		int curResId = data.getRoadMainCardId();
		if (curResId == 0) {
			List<RoleRoadCard> cards = new ArrayList<RoleRoadCard>(data.getRoadCards().values());
			if (cards.size() != 0) {
				ComparatorRoleRoadCard compar = new ComparatorRoleRoadCard(cacheManager);
				Collections.sort(cards, compar);
				curResId = cards.get(0).getResId();
			}
		}
		return curResId;
	}

	/**
	 * 查询
	 */
	public RoleRoad query(int id) {
		RoleRoad road = roleRoadDAO().query(id);
		if (road == null) {
			roleRoadDAO().add(id);
			road = roleRoadDAO().query(id);
		}
		return road;
	}

	public void addCard(int roleId, Card card) {
		RoleRoad road = query(roleId);
		if (!road.getRoadCards().containsKey(card.getId() + "")) {
			RoleRoadCard roadCard = new RoleRoadCard();
			roadCard.setResId(card.getId());
			roadCard.setQuality(card.getStar().get(0));
			road.getRoadCards().put(roadCard.getResId() + "", roadCard);
			roleRoadDAO().update(road);
		}
	}

	/**
	 * 查询列表
	 */
	public List<RoleRoad> queryList() {
		return roleRoadDAO().queryList();
	}

	/**
	 * 更新
	 * 
	 * @param RoleRoad
	 */
	public void update(RoleRoad rr) {
		roleRoadDAO().update(rr);
	}
}