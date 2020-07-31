/**
 * 
 */
package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.robot.MoBan;
import com.mdy.dzs.data.domain.robot.Name;
import com.mdy.dzs.game.domain.item.RoleItem;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.snatch.BeSnatchCard;
import com.mdy.dzs.game.domain.snatch.BeSnatchRole;
import com.mdy.dzs.game.domain.snatch.ComparatorSnatchCard;
import com.mdy.dzs.game.domain.snatch.SnatchItem;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.SystemException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

/**
 * 抢夺争夺
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月13日 上午10:56:06
 */
public class SnatchAO extends BaseAO {

	private final Logger logger = LoggerFactory.get(SnatchAO.class);

	private CacheManager cacheManager;
	private PacketAO packetAO;
	private List<Item> neiSnatchItems;
	private List<Item> waiSnatchItems;

	public SnatchAO(CacheManager cacheManager, PacketAO packetAO) {
		this.cacheManager = cacheManager;
		this.packetAO = packetAO;
	}

	@Override
	public void start() {
		// 初始化永久显示内外功列表
		neiSnatchItems = new ArrayList<Item>();
		for (Integer id : Constants.snatchNeiAry) {
			Item item = cacheManager.getValueByKey(Item.class, id);
			if (item == null) {
				logger.error("not init snatchNei:" + id);
				continue;
			}
			neiSnatchItems.add(item);
		}
		waiSnatchItems = new ArrayList<Item>();
		for (Integer id : Constants.snatchWaiAry) {
			Item item = cacheManager.getValueByKey(Item.class, id);
			if (item == null) {
				logger.error("not init snatchWai:" + id);
				continue;
			}
			waiSnatchItems.add(item);
		}
		super.start();
	}

	/**
	 * 生成可抢夺碎片
	 * 
	 * @param doc
	 * @param gongItems
	 * @return
	 * @throws BizException
	 */
	public Map<Integer, SnatchItem> generateSnatchMap(Role doc, int type) throws BizException {
		List<Item> showSnatchItems = null;
		switch (type) {
		case Packet.POS_IN_GONG_ITEM:
			showSnatchItems = neiSnatchItems;
			break;
		case Packet.POS_OUT_GONG_ITEM:
			showSnatchItems = waiSnatchItems;
			break;
		default:
			throw BaseException.getException(SystemException.EXCE_PARAM_ERROR, "not allow type:" + type);
		}
		List<RoleItem> gongItems = packetAO.queryListByAccount(doc.getId(), type);
		Map<Integer, SnatchItem> res = new HashMap<Integer, SnatchItem>();
		// init 永久显示
		for (Item item : showSnatchItems) {
			res.put(item.getId(), new SnatchItem(item));
		}

		// init doc gongitem list
		for (RoleItem roleItem : gongItems) {
			Item item = cacheManager.getExistValueByKey(Item.class, roleItem.getItemId());
			if (item.getPara3().size() == 0) {
				logger.error("gongitem not para3:" + item.getId());
				continue;
			}
			if (item.getType() != type)
				continue;
			SnatchItem sItem = getOrCreateItem(item.getPara3().get(0), res);
			sItem.setItemCnt(roleItem.getItemId(), roleItem.getItemCnt());
		}
		return res;
	}

	private SnatchItem getOrCreateItem(int id, Map<Integer, SnatchItem> map) throws BizException {
		SnatchItem res = map.get(id);
		if (res == null) {
			Item item = cacheManager.getExistValueByKey(Item.class, id);
			res = new SnatchItem(item);
			map.put(res.getId(), res);
		}
		return res;
	}

	/**
	 * 检测是否在免战时间段
	 * 
	 * @param now
	 * @return
	 */
	public boolean isAvoidTime(Date now) {
		Calendar c = GregorianCalendar.getInstance(); // creates a new calendar instance
		c.setTime(now);
		if (c.get(Calendar.HOUR_OF_DAY) >= Constants.snatchRestAry.get(0)
				|| c.get(Calendar.HOUR_OF_DAY) < Constants.snatchRestAry.get(1)) {
			return true;
		}
		return false;
	}

	public BeSnatchRole createBeSnatchNPC(Role doc, int easyType, MoBan npcData) {

		BeSnatchRole tempObj = new BeSnatchRole();
		tempObj.setAcc("0");
		tempObj.setLv(doc.getLevel());

		tempObj.setType(BeSnatchRole.TYPE_NPC); // NPC
		tempObj.setWarFreeTime(0);
		tempObj.setVip(0);
		tempObj.setEasyType(easyType);
		List<BeSnatchCard> tempAry = tempObj.getCard();

		while (tempAry.size() < 6) {// 6张牌
			int randNum = cacheManager.random(0, 10000);
			int sumProb = 0;
			for (int ind = 0; ind < npcData.getArrProb().size(); ind++) {
				sumProb += npcData.getArrProb().get(ind);
				if (randNum <= sumProb) {
					List<Integer> cardRange;
					switch (ind + 1) {
					case 1:
						cardRange = npcData.getWhite();
						break;
					case 2:
						cardRange = npcData.getGreen();
						break;
					case 3:
						cardRange = npcData.getBlue();
						break;
					case 4:
						cardRange = npcData.getPurple();
						break;
					case 5:
						cardRange = npcData.getGold();
						break;
					default:
						cardRange = Arrays.asList(0, 0);
					}
					if (cardRange.size() == 0)
						break;
					int cardId = cacheManager.random(cardRange.get(0), cardRange.get(1));
					Card cardData = cacheManager.getValueByKey(Card.class, cardId);
					if (cardData == null) {
						logger.error("not has card:" + cardId);
						continue;
					}
					tempAry.add(new BeSnatchCard(cardData));
					if (tempAry.size() == 6)
						break;
				}
			}
		}

		ComparatorSnatchCard compar = new ComparatorSnatchCard();
		Collections.sort(tempAry, compar);

		Map<Integer, Name> names = cacheManager.getValues(Name.class);
		int fnameId = cacheManager.random(1, names.size());
		int lnameId = cacheManager.random(1, names.size());
		Name fnameData = names.get(fnameId);
		Name lnameData = names.get(lnameId);
		String lname = "";
		if (tempAry.get(0).getSex() == 1)
			lname = lnameData.getMale();
		else
			lname = lnameData.getFemale();
		tempObj.setName(fnameData.getFirst() + lname);

		return tempObj;
	}

}
