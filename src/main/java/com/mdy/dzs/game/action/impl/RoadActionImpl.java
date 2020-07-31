/**
 * 
 */
package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.road.MingJiang;
import com.mdy.dzs.data.domain.road.RoadCardAchieve;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoadAction;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.road.RoleRoad;
import com.mdy.dzs.game.domain.road.RoleRoadCard;
import com.mdy.dzs.game.domain.road.RoleRoadGift;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.PacketException;
import com.mdy.dzs.game.exception.RoadException;
import com.mdy.sharp.container.biz.BizException;

/**
 * 名将之路
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月15日 下午9:31:10
 */
public class RoadActionImpl extends ApplicationAwareAction implements RoadAction {

	@Override
	public List<Serializable> queryRoadInfo(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleRoad road = roleRoadAO().query(doc.getId());

		List<RoleRoadGift> giftAry = roleRoadAO().getGiftListByRole(doc);
		int curResId = roleRoadAO().getRoadMainCardResId(road);

		List<RoleRoadCard> jiangHuAry = new ArrayList<RoleRoadCard>(road.getRoadCards().values());
		return Arrays.asList((Serializable) jiangHuAry, (Serializable) giftAry, curResId,
				(Serializable) road.getRoadStarCnt());
	}

	@Override
	public List<Serializable> useGift(String acc, int cardId, int useType, int itemId) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleRoad road = roleRoadAO().query(doc.getId());

		RoleRoadCard cardData = road.getRoadCards().get(cardId + "");
		if (cardData == null) {
			// throw BaseException.getGlobalException(
			// "cardId error");
			throw BaseException.getException(RoadException.EXCE_ROAD_CARDID_ERROR);
		}
		int maxLevel = cacheManager().getShangXian().getJianghulu();
		if (cardData.getLevel() >= maxLevel) {
			// throw BaseException.getGlobalException(
			// "level full");
			throw BaseException.getException(RoadException.EXCE_ROAD_LEVEL_FULL);
		}
		// 初始化使用列表
		List<RoleRoadGift> giftAry = new ArrayList<RoleRoadGift>();
		int cntMax = 1;
		switch (useType) {
			case RoleRoad.USE_一键使用:
				cntMax = 50;
				giftAry = roleRoadAO().getGiftListByRole(doc);
				break;
			case RoleRoad.USE_单个使用:
				RoleItem item = packetAO().queryByAccItemId(doc.getId(), itemId, Packet.POS_BAG);
				if (item == null) {
					throw BaseException.getException(PacketException.EXCE_ITEM_NOT_ENOUGH, itemId, 1);
				}
				RoleRoadGift gift = roleRoadAO().createGift(item);
				giftAry.add(gift);
				break;
		}
		if (giftAry.size() == 0) {
			// throw BaseException.getGlobalException(
			// "not have gift");
			throw BaseException.getException(RoadException.EXCE_ROAD_NO_GIFT);
		}

		boolean notUse = false;
		Map<Integer, Integer> useItemCnt = new HashMap<Integer, Integer>();
		int roadStarCnt = road.getRoadStarCnt();
		int level = cardData.getLevel();
		int exp = cardData.getCurExp();
		int addExp = 0;
		int critLevelCnt = 0;
		int levelCnt = 0;
		int curCnt = 0;
		for (int i = 0; i < giftAry.size(); i++) {
			if (notUse)
				break;
			RoleRoadGift gift = giftAry.get(i);
			Item itemData = cacheManager().getExistValueByKey(Item.class, gift.getResId());
			if (!useItemCnt.containsKey(gift.getResId())) {
				useItemCnt.put(gift.getResId(), 0);
			}
			for (int j = 0; j < gift.getNum(); j++) {
				MingJiang mingData = cacheManager().getExistValueByKey(MingJiang.class, level);
				int limitExp = mingData.getArrExp().get(cardData.getQuality() - 4);
				int useExp = itemData.getPrice();
				do {
					if (level >= maxLevel) {
						notUse = true;
						break;
					}
					if (exp + useExp < limitExp) {
						// 不升级
						double prob = 1.0 / (level + 2.0) + exp / (limitExp - exp) / (level + 2);
						if (cacheManager().random(1, 100) < (int) (prob * 100)) {// 暴击直接升级
							level++;
							addExp += limitExp - exp;
							exp = 0;
							critLevelCnt++;
						} else {// 正常加好感
							exp += useExp;
							addExp += useExp;
						}
						useExp = 0;
					} else {// 升级
						level++;
						useExp = exp + useExp - limitExp;
						addExp += limitExp - exp;
						exp = 0;

						levelCnt++;
					}
					mingData = cacheManager().getExistValueByKey(MingJiang.class, level);
					limitExp = mingData.getArrExp().get(cardData.getQuality() - 4);
				} while (useExp > 0);// 一个礼物连升多级处理

				// 使用道具数量
				int useCnt = useItemCnt.get(gift.getResId());
				useItemCnt.put(gift.getResId(), useCnt + 1);
				// 当前使用次数
				curCnt++;
				if (curCnt >= cntMax) {
					notUse = true;
					break;
				}
			}

		}
		// 好感度总等级计数
		roadStarCnt += level - cardData.getLevel();

		List<RoleCard> rcards = roleCardAO().querySameResCard(doc.getId(), cardData.getResId());
		boolean isUpAttack = false;
		for (RoleCard roleCard : rcards) {
			if (roleCard.getPos() > 0)
				isUpAttack = true;
		}
		if (isUpAttack) {
			List<RoleCard> cardAry = roleCardAO().queryLineupListByRoldId(doc.getId());
			doc.setAttack(roleAO().battleCalc(doc, cardAry));
		}
		// 成就加耐力
		List<Integer> achieveAry = new ArrayList<Integer>();
		List<RoadCardAchieve> achs = getRoadCardAchieve(road.getRoadStarCnt(), roadStarCnt);
		for (RoadCardAchieve roadCardAchieve : achs) {
			if (-1 != road.getRoadStarAchs().indexOf(roadCardAchieve.getId())) {
				continue;
			}
			int limit = doc.getPropLimitAry().get(1);
			limit += roadCardAchieve.getNaili();
			doc.getPropLimitAry().set(1, limit);
			road.getRoadStarAchs().add(roadCardAchieve.getId());
			achieveAry.add(roadCardAchieve.getId());
		}
		// 扣除物品
		for (Entry<Integer, Integer> idCnt : useItemCnt.entrySet()) {
			packetAO().removeItemMustEnough(doc, Packet.POS_BAG, idCnt.getKey(), idCnt.getValue(),
					RoleItemLog.SYS_名将_赠送, "");
		}
		// 赋值更新
		cardData.setCurExp(exp);
		cardData.setLevel(level);
		cardData.setAddExp(addExp);

		road.setRoadMainCardId(cardId);
		road.setRoadStarCnt(roadStarCnt);
		roleRoadAO().update(road);
		roleAO().updateRoad(doc);

		// 任务
		missionExecAO().lvUpRoadCard(doc, level);
		missionExecAO().useGift(doc, curCnt);

		giftAry = roleRoadAO().getGiftListByRole(doc);

		return Arrays.asList(cardData, (Serializable) Arrays.asList(critLevelCnt, levelCnt), road.getRoadStarCnt(),
				(Serializable) giftAry);
	}

	private List<RoadCardAchieve> getRoadCardAchieve(int curStar, int toStar) {
		List<RoadCardAchieve> res = new ArrayList<RoadCardAchieve>();
		for (int i = curStar + 1; i <= toStar; i++) {
			RoadCardAchieve ach = cacheManager().getValueByKey(RoadCardAchieve.class, i);
			if (ach != null) {
				res.add(ach);
			}
		}
		return res;

	}
}
