package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.shop.Hotel;
import com.mdy.dzs.data.domain.shop.Pseudo;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.ShopAction;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.prob.RolePseudo;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.RoleStatistics;
import com.mdy.dzs.game.domain.shop.BuyShopItemVO;
import com.mdy.dzs.game.domain.shop.PurchaseItem;
import com.mdy.dzs.game.domain.shop.RoleShop;
import com.mdy.dzs.game.domain.shop.Shop;
import com.mdy.dzs.game.domain.shop.ShopItemStatusVO;
import com.mdy.dzs.game.exception.ShopException;
import com.mdy.dzs.game.util.BehaviorLogger;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

public class ShopActionImpl extends ApplicationAwareAction implements ShopAction {

	final static Logger logger = LoggerFactory.get(ShopActionImpl.class);
	private int firstGotCnt = 4;

	@Override
	public List<Serializable> wineShopState(String acc) throws BizException {

		Hotel hotelItem = cacheManager().getValueByKey(Hotel.class, 1);
		Role doc = roleAO().queryExistAccount(acc);
		RoleShop rShop = shopAO().queryRoleShopByRoleId(doc.getId());
		int num = 0;
		RoleItem ritem = packetAO().queryByAccItemId(doc.getId(), hotelItem.getItemid(), 7);
		if (ritem != null) {
			num = ritem.getItemCnt();
		}
		int freeNextTime1 = rShop.getWineFreeNextTimeAry().get(1);
		int freeNextTime2 = rShop.getWineFreeNextTimeAry().get(2);
		int now = (int) ((new Date().getTime()) / 1000);
		int t2 = freeNextTime1 - now > 0 ? freeNextTime1 - now : 0;
		int t3 = freeNextTime2 - now > 0 ? freeNextTime2 - now : 0;
		int cnt = rShop.getWineGotCntAry().get(2);
		cnt = cnt == 0 ? firstGotCnt : cnt;
		cnt = 9 - cnt % 10;

		Map<String, Integer> nn = new HashMap<String, Integer>();
		nn.put("n", num);
		int tt[] = { t2, t3 };
		return Arrays.asList((Serializable) nn, tt, cnt, (Serializable) rShop.getWineGotCntAry());

	}

	@Override
	public List<Serializable> wineShop(String acc, int t, int n) throws BizException {
		// t 1,2,3
		// n 0,10
		Hotel hotelItem = cacheManager().getExistValueByKey(Hotel.class, t);
		Role doc = roleAO().queryExistAccount(acc);
		RoleShop rShop = shopAO().queryRoleShopByRoleId(doc.getId());
		int now = (int) ((new Date().getTime()) / 1000);
		int spend = 0;
		int itemId = 0;
		// 1.rm item
		int itemLogType = RoleItemLog.SYS_商城_江湖新秀;
		int pseudoType = Pseudo.TYPE_抽卡_江湖新秀;
		RoleStatistics oldRS = roleStatisticsAO().query(doc.getId());
		if (t == 1) {
			itemId = hotelItem.getItemid();
			packetAO().removeItemMustEnough(doc, 7, itemId, 1, itemLogType, "" + itemId);
		} else if (t == 2) {
			if (now < rShop.getWineFreeNextTimeAry().get(1)) {// 不能免费抽取
				spend = hotelItem.getCoin();
				itemLogType = RoleItemLog.SYS_商城_武林高手;
				packetAO().removeItemMustEnough(doc, 0, 1, spend, itemLogType, "");
			} else {
				itemLogType = RoleItemLog.SYS_商城_武林高手_免费;
			}
			pseudoType = Pseudo.TYPE_抽卡_武林高手;
		} else if (t == 3) {
			itemLogType = RoleItemLog.SYS_商城_名震江湖_免费;
			if (n == 10) {
				spend = Constants.shopWineTen;
				itemLogType = RoleItemLog.SYS_商城_名震江湖_10次;
			} else if (now < rShop.getWineFreeNextTimeAry().get(2)) {// 不能免费抽取
				spend = hotelItem.getCoin();
				itemLogType = RoleItemLog.SYS_商城_名震江湖_1次;
			}
			pseudoType = Pseudo.TYPE_抽卡_名震江湖;
			if (spend > 0) {
				packetAO().removeItemMustEnough(doc, 0, 1, spend, itemLogType, "");
			}
		}
		RoleStatistics newRS = roleStatisticsAO().query(doc.getId());
		// 2.got prob
		List<ProbItem> itemAry = new ArrayList<ProbItem>();
		int time = 0;
		int probId = 0;
		List<ProbItem> item = null;
		int gotCnt = rShop.getWineGotCntAry().get(t - 1);
		int freeCnt = rShop.getWineGotCntAry().get(t - 1);

		RolePseudo rp = rolePseudoAO().query(doc.getId(), pseudoType);
		List<Object> equips = packetAO().getPacketListByType(doc.getId(), Packet.POS_EQUIP);
		List<Object> cards = packetAO().getPacketListByType(doc.getId(), Packet.POS_CARD);

		if (t == 3 && n == 10) {
			for (int i = 0; i < 10; i++) {
				if (gotCnt == 0) {// 首次招募
					probId = hotelItem.getFirst();
					gotCnt = firstGotCnt;
					itemAry.addAll(cacheManager().probGot(probId));
				} else {
					item = rolePseudoAO().getRewards(rp, gotCnt % 10 == 9, equips, cards, itemAry);
					itemAry.addAll(item);
				}
				gotCnt += 1;
				rShop.getWineGotCntAry().set(t - 1, gotCnt);

			}
		} else if (gotCnt == 0) {// 首次招募
			probId = hotelItem.getFirst();
			gotCnt = firstGotCnt;
			itemAry.addAll(cacheManager().probGot(probId));
			rShop.getWineGotCntAry().set(t - 1, gotCnt + 1);
		} else {
			// probId = hotelItem.getProbid1();
			item = rolePseudoAO().getRewards(rp, gotCnt % 10 == 9, equips, cards, null);
			itemAry.addAll(item);
			rShop.getWineGotCntAry().set(t - 1, gotCnt + 1);
			rShop.getWineFreeCntAry().set(t - 1, freeCnt + 1);
		}

		int freeNextTime = rShop.getWineFreeNextTimeAry().get(t - 1);
		if (n != 10) {// 不是十连抽
			if (now > freeNextTime) {
				freeNextTime = (int) (now + hotelItem.getTime() * 60);
				rShop.getWineFreeNextTimeAry().set(t - 1, freeNextTime);
			}
		}
		if (t == 2 || t == 3)
			time = freeNextTime - now;
		if (now > freeNextTime && spend != 0) {
			time = 0;
		}

		for (ProbItem probItem : itemAry) {
			Card cardData = cacheManager().getExistValueByKey(Card.class, probItem.getId());
			if (cardData.getStar().get(0) == 5) {
				broadcastAO().wineShopGetCardBroad(doc, cardData);
			}
			List<Object> res = packetAO().addItem(doc, 8, cardData.getId(), 1, itemLogType, "");
			RoleCard last = (RoleCard) res.get(0);
			probItem.setObjId(last.getId());
			missionExecAO().wineShopRoleCard(doc, last);
		}
		shopAO().updateRoleShop(rShop);
		int cnt = 9 - rShop.getWineGotCntAry().get(2) % 10;

		// 花费日志
		if (spend > 0)
			BehaviorLogger.log4GoldCost(doc, itemLogType, oldRS, newRS, itemAry);
		return Arrays.asList((Serializable) itemAry, time, doc.getGold(), cnt);
	}

	@Override
	public List<Serializable> queryShopList(String acc) throws BizException {
		Date now = new Date();
		List<Shop> items = shopAO().queryOpenListByTime(now);
		Role doc = roleAO().queryExistAccount(acc);
		RoleShop rShop = shopAO().queryRoleShopByRoleId(doc.getId());

		List<PurchaseItem> retAry = new ArrayList<PurchaseItem>();

		Map<String, Integer> comShopPurchased = rShop.getComShopPurchased();
		for (Shop item : items) {
			String strId = String.valueOf(item.getId());
			Vip vipData = shopAO().getVipData(item.getId());
			int purchaseData = 0;
			if (comShopPurchased.containsKey(strId)) {
				purchaseData = comShopPurchased.get(strId);
			}
			Item itemData = cacheManager().getExistValueByKey(Item.class, item.getItemId());
			int hadCnt = -1;
			if (itemData.getAuto().compareTo("1") == 0) {
				if (itemData.getEffecttype() == 0) {
					hadCnt = doc.getSilver();
				} else {
					List<RoleCard> cardAry = roleCardAO().querySameResCard(doc.getId(), itemData.getPara1().get(0));
					hadCnt = cardAry.size();
				}
			} else {
				RoleItem ritemData = packetAO().queryByAccItemId(doc.getId(), item.getItemId(), 7);
				if (ritemData != null) {
					hadCnt = ritemData.getItemCnt();
				}
			}
			hadCnt = hadCnt < 0 ? 0 : hadCnt;

			int vipVal = vipData != null ? vipData.getVipByLevel(doc.getVip()) : 0;
			int cnt = vipVal - purchaseData;
			cnt = cnt <= 0 ? 0 : cnt;
			int max = vipVal == 0 ? -1 : vipVal;
			retAry.add(item.getId() - 1, new PurchaseItem(item.getId(), cnt, max, hadCnt, purchaseData));

		}
		return Arrays.asList((Serializable) items, (Serializable) retAry);
	}

	@Override
	public List<Serializable> queryShopItem(String acc, int itemId) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleShop rShop = shopAO().queryRoleShopByRoleId(doc.getId());
		Date now = new Date();
		Shop item = shopAO().queryOpenItemByTime(now, itemId);
		if (item == null) {
			throw ShopException.getException(ShopException.EXCE_BUY_NOT_SELL);
		}

		int itemNum = packetAO().getNumberByTypeId(doc, 7, itemId);
		ShopItemStatusVO resisObj = null;

		Vip vipData = shopAO().getVipData(item.getId());
		int purchaseData = 0;
		Map<String, Integer> comShopPurchased = rShop.getComShopPurchased();
		String strId = String.valueOf(item.getId());
		if (comShopPurchased.containsKey(strId)) {
			purchaseData = comShopPurchased.get(strId);
		}
		int vipCnt = vipData == null ? 10 : vipData.getVipByLevel(doc.getVip());
		int needCoin = shopAO().getNeedCoin(item, purchaseData, 1);

		resisObj = new ShopItemStatusVO(item.getId(), vipCnt - purchaseData, needCoin, 1, doc.getGold(),
				doc.getSilver());

		return Arrays.asList(resisObj, itemNum);
	}

	@Override
	public List<Serializable> buyShopItem(String acc, int id, int n, int coinType, int coin, int auto)
			throws BizException {
		String strId = String.valueOf(id);
		Shop item = shopAO().query(id);
		if (item.getCoinType() != coinType) {
			throw ShopException.getException(ShopException.EXCE_BUY_TYPE_ERROR, item.getCoinType(), coinType);
		}

		Item itemData = cacheManager().getExistValueByKey(Item.class, item.getItemId());
		Role doc = roleAO().queryExistAccount(acc);
		RoleShop rShop = shopAO().queryRoleShopByRoleId(doc.getId());
		Vip vipData = shopAO().getVipData(id);

		int purchaseData = 0;
		Map<String, Integer> comShopPurchased = rShop.getComShopPurchased();
		if (comShopPurchased.containsKey(strId)) {
			purchaseData = comShopPurchased.get(strId);
		}

		int limit = 0;
		if (vipData != null) {
			limit = vipData.getVipByLevel(doc.getVip());
			if ((purchaseData + n) > limit) {
				throw ShopException.getException(ShopException.EXCE_BUY_NUM_ERROR, n, purchaseData + n, limit);
			}
		}
		;

		// 花费
		int spend = shopAO().getNeedCoin(item, purchaseData, n);
		if (spend != coin) {
			throw ShopException.getException(ShopException.EXCE_BUY_COIN_ERROR, spend, coin);
		}

		// 扣除货币
		RoleStatistics oldRS = roleStatisticsAO().query(doc.getId());
		packetAO().removeItemMustEnough(doc, 0, coinType, spend, RoleItemLog.SYS_商城_购买道具, "" + item.getId());
		RoleStatistics newRS = roleStatisticsAO().query(doc.getId());

		if (itemData.getAuto().compareTo("1") == 0) {
			if (itemData.getEffecttype() == 0) {
				packetAO().addItem(doc, 0, itemData.getPara1().get(0), itemData.getPara2().get(0) * n,
						RoleItemLog.SYS_商城_购买道具, "");// 立即使用
			} else if (itemData.getEffecttype() == 2) {
				packetAO().addItem(doc, 8, itemData.getPara1().get(0), itemData.getPara2().get(0) * n,
						RoleItemLog.SYS_商城_购买道具, "");// 武将 type == 8
			}
		} else {
			if (auto == 1)
				packetAO().addItem(doc, 0, itemData.getPara1().get(0), itemData.getPara2().get(0) * n,
						RoleItemLog.SYS_商城_购买道具, "");//
			else
				packetAO().addItem(doc, itemData.getType(), item.getItemId(), n, RoleItemLog.SYS_商城_购买道具, "");// packet
		}

		// 如果购买的是金箱子，则+优质概率
		if (item.getItemId() == 4001) {
			packetAO().updateBoxProService(doc.getId(), n, item.getItemId());
		}

		int cnt = purchaseData + n;
		// 下次购买价格
		int nextSpend = shopAO().getNeedCoin(item, cnt, 1);
		BuyShopItemVO retObj = new BuyShopItemVO(id, nextSpend, cnt, limit - cnt, limit);
		comShopPurchased.put(strId, cnt);

		shopAO().updateRoleShop(rShop);
		BehaviorLogger.log4GoldCost(doc, RoleItemLog.SYS_商城_购买道具, oldRS, newRS,
				Arrays.asList(new ProbItem(itemData.getType(), itemData.getId(), cnt)));
		return Arrays.asList(retObj, doc.getGold(), doc.getSilver());
	}

}
