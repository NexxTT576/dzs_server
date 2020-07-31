/**
 * 
 */
package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.card.Carden;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.Money;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.shentong.ShenTong;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.FurnaceAction;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.calcattack.CalcRoleEquip;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.dzs.game.domain.furnace.ClientFurnVO;
import com.mdy.dzs.game.domain.furnace.ComparatorFurnInfo;
import com.mdy.dzs.game.domain.furnace.FurnInfoVO;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.RoleCardException;
import com.mdy.dzs.game.exception.RoleEquipException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

/**
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月23日 下午8:46:35
 */
public class FurnaceActionImpl extends ApplicationAwareAction implements FurnaceAction {

	@Override
	public List<Serializable> queryFurnaceList(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		List<FurnInfoVO> cAry = new ArrayList<FurnInfoVO>();
		List<FurnInfoVO> eAry = new ArrayList<FurnInfoVO>();

		List<RoleCard> cardAry = roleCardAO().queryListByRoleId(role.getId());
		for (RoleCard ele : cardAry) {
			// 不是主角儿的卡
			if (ele.getId() == role.getFmtMainCardID())
				continue;
			// 没有上阵的卡
			if (ele.getPos() != 0)
				continue;
			if (ele.IsLock())
				continue;

			Card cardData = cacheManager().getExistValueByKey(Card.class, ele.getResId());
			FurnInfoVO f = new FurnInfoVO(ele);
			// 炼化： cls为0，refining为1
			if (cardData.getRefining() == 1 && ele.getCls() == 0 && ele.getBattle().size() <= 0) {
				f.setRefining(1);
			}
			// 重生：强化等级大于1，reborn为1
			if (cardData.getReborn() == 1 && ele.getLevel() > 1 && ele.getBattle().size() <= 0) {
				f.setReborn(1);
			}
			if (f.getReborn() == 0 && f.getRefining() == 0) {
				continue;
			}
			Carden cardenData = cacheManager().getExistValueByKey(Carden.class, ele.getLevel());
			if (f.getRefining() == 1) {
				List<ProbItem> refiningList = roleCardAO().getFurnaceList(ele, cardData, cardenData);
				f.setRtn(refiningList);
			}
			if (f.getReborn() == 1) {
				// 计算重生的返回
				List<ProbItem> rebornBack = roleCardAO().getRebornBack(ele, cardData, cardenData);
				f.setRtnReborn(rebornBack);
				int rebornCost = roleCardAO().cost(ele);
				f.setCost(rebornCost);
			}
			cAry.add(f);
		}

		List<Equip> equipAry = roleEquipAO().queryListByAccount(role.getId());
		// 装备
		for (Equip ele : equipAry) {// 未上阵 pos==0 2-5星 star == 2-5
			if (ele.getPos() != 0)
				continue;
			Item itemData = cacheManager().getExistValueByKey(Item.class, ele.getResId());
			CalcRoleEquip calc = calcManager().getCalcEquip(ele.getResId());
			List<Integer> base = calc.getBase(0, ele.getProps());
			FurnInfoVO f = new FurnInfoVO(ele, base);
			if (itemData.getRefining() == 1) {
				f.setRefining(1);
			}
			if (itemData.getReborn() == 1 && ele.getLevel() > 0) {
				f.setReborn(1);
			}

			if (f.getReborn() == 0 && f.getRefining() == 0) {
				continue;
			}
			if (f.getRefining() == 1) {
				List<ProbItem> itemlistReFining = roleEquipAO().getEquipReborn(ele, itemData, base, 1);
				f.setRtn(itemlistReFining);
			}
			if (f.getReborn() == 1) {
				List<ProbItem> itemlistReborn = roleEquipAO().getEquipReborn(ele, itemData, base, 2);
				f.setRtnReborn(itemlistReborn);
				int cost = roleEquipAO().cost(ele);
				f.setCost(cost);
			}
			List<Integer> baseEquip = calc.getBaseView(ele.getLevel(), ele.getProps());
			f.setBase(baseEquip);
			eAry.add(f);

		}
		;
		ComparatorFurnInfo compar = new ComparatorFurnInfo();
		Collections.sort(cAry, compar);
		Collections.sort(eAry, compar);

		return Arrays.asList((Serializable) cAry, (Serializable) eAry);
	}

	@Override
	public List<Serializable> furnace(String acc, int type, List<Integer> ids) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		ClientFurnVO cf = null;
		// 侠客
		if (type == 1) {
			cf = furnaceCard(doc, ids);
		} else if (type == 2) {// 装备
			cf = furnaceEquip(doc, ids);
		}
		return Arrays.asList((Serializable) cf.getRtnAry(), (Serializable) cf.getChangeAry());
	}

	/**
	 * 熔炼卡片
	 * 
	 * @param doc
	 * @param ids
	 * @return
	 * @throws BizException
	 */
	private ClientFurnVO furnaceCard(Role doc, List<Integer> ids) throws BizException {
		ClientFurnVO cf = new ClientFurnVO();
		List<FurnInfoVO> changeAry = new ArrayList<FurnInfoVO>();
		List<ProbItem> rtnAry = new ArrayList<ProbItem>();
		String acc = doc.getAccount();
		int tSilver = 0;
		int tSoul = 0;
		int tJade = 0;
		// var tGas = 0;
		for (int i = 0; i < ids.size(); i++) {
			int id = ids.get(i);
			RoleCard rcard = roleCardAO().queryExistCardById(doc, id);
			if (rcard.getId() == doc.getFmtMainCardID()) {// 非主角
				throw BaseException.getException(RoleCardException.EXCE_CARD_NOTALLOW_MAIN);
			}
			if (rcard.getCls() > 0) {// 阶数不为0 不能炼化
				throw BaseException.getException(RoleCardException.EXCE_CARD_NOT_FURNACE);
			}
			if (rcard.IsLock()) {// 未被锁
				throw BaseException.getException(RoleCardException.EXCE_CARD_IS_LOCK, id);
			}
			Card cardData = cacheManager().getExistValueByKey(Card.class, rcard.getResId());
			Carden cardenData = cacheManager().getExistValueByKey(Carden.class, rcard.getLevel());

			int sumexp = cardenData.getSumexp().get(rcard.getStar() - 1);
			tSilver += cardData.getSoul().get(1) + sumexp + rcard.getCurExp();// + 经脉花费（jingmai-arr_coin，累加计算）
			tSoul += cardData.getSoul().get(0) + sumexp + rcard.getCurExp();
			tJade += cardData.getSoul().get(2);

			FurnInfoVO fi = new FurnInfoVO(rcard);
			fi.setRefining(0);
			fi.setReborn(0);
			changeAry.add(fi);
			// 真气=经脉花费（jingmai-qi，累加计算）
			roleCardAO().delete(rcard);
		}

		roleAO().fmtPropUpdate(doc);
		// 数据处理
		packetAO().addItem(doc, 0, 2, tSilver, RoleItemLog.SYS_炼化炉_炼化_侠客, ""); // 银币
		packetAO().addItem(doc, 0, 7, tSoul, RoleItemLog.SYS_炼化炉_炼化_侠客, ""); // 侠魂
		packetAO().addItem(doc, 0, 10, tJade, RoleItemLog.SYS_炼化炉_炼化_侠客, ""); // 魂玉

		Money silverData = cacheManager().getExistValueByKey(Money.class, 2);
		Money soulData = cacheManager().getExistValueByKey(Money.class, 7);
		Money jadeData = cacheManager().getExistValueByKey(Money.class, 10);
		rtnAry.add(new ProbItem(0, silverData.getId(), tSilver));
		rtnAry.add(new ProbItem(0, soulData.getId(), tSoul));
		rtnAry.add(new ProbItem(0, jadeData.getId(), tJade));

		cf.setChangeAry(changeAry);
		cf.setRtnAry(rtnAry);
		return cf;
	}

	private ClientFurnVO furnaceEquip(Role doc, List<Integer> ids) throws BizException {
		ClientFurnVO cf = new ClientFurnVO();
		int tSilver = 0;
		int tStone = 0;
		List<FurnInfoVO> changeAry = new ArrayList<FurnInfoVO>();
		List<ProbItem> rtnAry = new ArrayList<ProbItem>();
		for (int i = 0; i < ids.size(); i++) {
			int id = ids.get(i);
			Equip equip = roleEquipAO().queryExistEquipById(doc.getId(), id);
			if (equip.getPos() == 0) {// 未穿戴
				Item itemData = cacheManager().getExistValueByKey(Item.class, equip.getResId());

				tSilver += equip.getSilver();
				CalcRoleEquip calc = calcManager().getCalcEquip(equip.getResId());
				List<Integer> base = calc.getBase(0, equip.getProps());
				tStone += itemData.getNumber() + (int) (Math.floor(base.get(0) * Constants.equipPropCoe.get(0)
						+ base.get(1) * Constants.equipPropCoe.get(1) + base.get(2) * Constants.equipPropCoe.get(2)
						+ base.get(3) * Constants.equipPropCoe.get(3) + base.get(4) * Constants.equipPropCoe.get(4)
						+ base.get(5) * Constants.equipPropCoe.get(5)));

				FurnInfoVO fi = new FurnInfoVO(equip, base);
				fi.setRefining(0);
				fi.setReborn(0);
				changeAry.add(fi);
				packetAO().removeCardEquipGongYuanById(doc, Packet.POS_EQUIP, id, RoleItemLog.SYS_炼化炉_炼化_装备);
			} else {
				throw BaseException.getException(RoleEquipException.EXCE_EQUIP_NOT_INLINEUP);
			}
		}
		// 数据处理
		packetAO().addItem(doc, 0, 2, tSilver, RoleItemLog.SYS_炼化炉_炼化_装备, "");// 银币
		packetAO().addItem(doc, 7, Constants.equipPropItem, tStone, RoleItemLog.SYS_炼化炉_炼化_装备, "");// 洗练石

		Money silverData = cacheManager().getExistValueByKey(Money.class, 2);
		Item stoneData = cacheManager().getExistValueByKey(Item.class, Constants.equipPropItem);
		rtnAry.add(new ProbItem(0, silverData.getId(), tSilver));
		rtnAry.add(new ProbItem(0, stoneData.getId(), tStone));
		cf.setChangeAry(changeAry);
		cf.setRtnAry(rtnAry);

		return cf;
	}

	@Override
	public List<Serializable> reborn(String acc, int type, int id) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		ClientFurnVO cf = null;
		// 侠客
		if (type == 1) {
			// 背包检查//t=1装备/2时装/3装备碎片/4内外功/5武将碎片/6精元/7背包/8武将/9内功碎片/10外功碎片
			List<Integer> bagAry = Arrays.asList(Packet.POS_BAG, Packet.POS_GONG, Packet.POS_CARD);
			// 检查
			List<PacketExtend> checkData = packetAO().checkBag(doc, bagAry);
			if (checkData.size() > 0) {
				return Arrays.asList(null, null, (Serializable) checkData);
			}
			cf = rebornCard(doc, id);
		} else if (type == 2) {// 装备
			cf = rebornEquip(doc, id);
		}
		return Arrays.asList((Serializable) cf.getRtnAry(), (Serializable) cf.getChangeAry(), null, doc.getGold());
	}

	/**
	 * 重生卡牌
	 * 
	 * @param doc
	 * @param ids
	 * @return
	 * @throws BizException
	 */
	private ClientFurnVO rebornCard(Role doc, int id) throws BizException {
		ClientFurnVO cf = new ClientFurnVO();
		List<FurnInfoVO> changeAry = new ArrayList<FurnInfoVO>();
		List<ProbItem> rtn = null;
		RoleCard rcard = roleCardAO().queryExistCardById(doc, id);

		int cost = roleCardAO().cost(rcard);
		packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, cost, RoleItemLog.SYS_炼化炉_重生_侠客,
				"" + rcard.getResId());
		if (rcard.getId() == doc.getFmtMainCardID()) { // 非主角
			throw BaseException.getException(RoleCardException.EXCE_CARD_NOTALLOW_MAIN);
		}
		if (rcard.getPos() != 0) { // 未上阵
			throw BaseException.getException(RoleCardException.EXCE_CARD_IN_LINEUP);
		}
		if (rcard.getLevel() == 1) {// 等级为1 不能重生
			throw BaseException.getException(RoleCardException.EXCE_CARD_NOT_REBORN);
		}
		if (rcard.IsLock()) {// 未被锁
			throw BaseException.getException(RoleCardException.EXCE_CARD_IS_LOCK, id);
		}
		Card cardData = cacheManager().getExistValueByKey(Card.class, rcard.getResId());
		Carden cardenData = cacheManager().getExistValueByKey(Carden.class, rcard.getLevel());
		rtn = roleCardAO().getRebornBack(rcard, cardData, cardenData);
		for (ProbItem idNum : rtn) {
			packetAO().addItem(doc, idNum.getT(), idNum.getId(), idNum.getN(), RoleItemLog.SYS_炼化炉_重生_侠客,
					"" + rcard.getResId());
		}
		// 重置卡牌
		rcard.setCls(0);
		rcard.setLevel(1);
		rcard.setStar(cardData.getStar().get(0));
		rcard.setCurExp(0);
		rcard.setShenPt(0);
		// 重置神通
		List<Integer> talentAry = cardData.getTalent();
		for (int i = 0; i < talentAry.size(); i++) {
			int talentGrpId = talentAry.get(i);
			ShenTong talentData = cacheManager().getExistValueByKey(ShenTong.class, talentGrpId);
			int shenId = talentData.getArrTalent().get(0);
			int cond = talentData.getArrCond().get(0);
			rcard.getShenLvAry().set(i, rcard.getCls() >= cond ? 1 : 0);
			rcard.getShenIDAry().set(i, shenId);
		}

		roleCardAO().update(rcard);
		FurnInfoVO f = getCardFiVO(rcard, doc);
		changeAry.add(f);
		roleAO().fmtPropUpdate(doc);
		cf.setChangeAry(changeAry);
		cf.setRtnAry(rtn);
		roleCardAO().delete(rcard);
		roleCardAO().addNewCard(doc, cardData, 0);
		return cf;
	}

	/**
	 * 重生装备
	 * 
	 * @param doc
	 * @param ids
	 * @return
	 * @throws BizException
	 */
	private ClientFurnVO rebornEquip(Role doc, int id) throws BizException {
		ClientFurnVO cf = new ClientFurnVO();
		List<FurnInfoVO> changeAry = new ArrayList<FurnInfoVO>();
		List<ProbItem> rtnAry = null;
		Equip equip = roleEquipAO().queryExistEquipById(doc.getId(), id);
		if (equip.getLevel() == 0) {// 等级为0 不能重生
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_NOT_REBORN);
		}
		int cost = roleEquipAO().cost(equip);
		packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, cost, RoleItemLog.SYS_炼化炉_重生_装备,
				"" + equip.getResId());
		if (equip.getPos() == 0) {// 未穿戴
			Item itemData = cacheManager().getExistValueByKey(Item.class, equip.getResId());
			CalcRoleEquip calc = calcManager().getCalcEquip(equip.getResId());
			List<Integer> base = calc.getBase(0, equip.getProps());
			rtnAry = roleEquipAO().getEquipReborn(equip, itemData, base, 2);
			for (ProbItem probItem : rtnAry) {
				packetAO().addItem(doc, probItem.getT(), probItem.getId(), probItem.getN(), RoleItemLog.SYS_炼化炉_重生_装备,
						"" + equip.getResId());
			}
			equip.setLevel(0);
			equip.setCurExp(0);
			// 重置钱
			equip.setSilver(itemData.getPrice());
			// 重置属性
			for (Prop prop : equip.getProps()) {
				prop.setVal(0);
			}
			for (Prop prop : equip.getPropsWait()) {
				prop.setVal(0);
			}
			roleEquipAO().update(equip);
			FurnInfoVO f = getEquipFiVO(equip, doc);
			changeAry.add(f);
		} else {
			throw BaseException.getException(RoleEquipException.EXCE_EQUIP_NOT_INLINEUP);
		}
		roleAO().fmtPropUpdate(doc);
		cf.setChangeAry(changeAry);
		cf.setRtnAry(rtnAry);
		return cf;
	}

	/**
	 * 重生后改变的卡牌
	 * 
	 * @param rcard
	 * @param doc
	 * @return
	 * @throws BizException
	 */
	private FurnInfoVO getCardFiVO(RoleCard rcard, Role doc) throws BizException {
		FurnInfoVO f = new FurnInfoVO(rcard);
		Card cardData = cacheManager().getExistValueByKey(Card.class, rcard.getResId());
		Carden cardenData = cacheManager().getExistValueByKey(Carden.class, rcard.getLevel());
		// 炼化： cls为0，refining为1
		if (cardData.getRefining() == 1 && rcard.getCls() == 0) {
			f.setRefining(1);
		}
		// 重生：强化等级大于1，reborn为1
		if (cardData.getReborn() == 1 && rcard.getLevel() > 1) {
			f.setReborn(1);
		}
		if (f.getRefining() == 1) {
			List<ProbItem> refiningList = roleCardAO().getFurnaceList(rcard, cardData, cardenData);
			f.setRtn(refiningList);
		}
		if (f.getReborn() == 1) {
			// 计算重生的返回
			List<ProbItem> rebornBack = roleCardAO().getRebornBack(rcard, cardData, cardenData);
			f.setRtnReborn(rebornBack);
			int rebornCost = roleCardAO().cost(rcard);
			f.setCost(rebornCost);
		}
		return f;
	}

	private FurnInfoVO getEquipFiVO(Equip equip, Role doc) throws BizException {
		FurnInfoVO f = null;
		if (equip.getPos() == 0) {
			Item itemData = cacheManager().getExistValueByKey(Item.class, equip.getResId());
			CalcRoleEquip calc = calcManager().getCalcEquip(equip.getResId());
			List<Integer> base = calc.getBase(0, equip.getProps());
			f = new FurnInfoVO(equip, base);
			if (itemData.getRefining() == 1) {
				f.setRefining(1);
			}
			if (itemData.getReborn() == 1 && equip.getLevel() > 0) {
				f.setReborn(1);
			}
			if (f.getRefining() == 1) {
				List<ProbItem> itemlist = roleEquipAO().getEquipReborn(equip, itemData, base, 1);
				f.setRtn(itemlist);
			}
			if (f.getReborn() == 1) {
				List<ProbItem> itemlist = roleEquipAO().getEquipReborn(equip, itemData, base, 2);
				f.setRtnReborn(itemlist);
				int cost = roleEquipAO().cost(equip);
				f.setCost(cost);
			}
		}
		return f;
	}
}
