package com.mdy.dzs.game.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.DataAction;
import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.card.ShangXianSheDing;
import com.mdy.dzs.data.domain.equipment.EquipSuit;
import com.mdy.dzs.data.domain.gong.Refine;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.road.RoadCardLevel;
import com.mdy.dzs.data.domain.role.JiBan;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.calcattack.CalcRoleCard;
import com.mdy.dzs.game.domain.calcattack.CalcRoleEquip;
import com.mdy.dzs.game.domain.calcattack.CalcRoleGong;
import com.mdy.dzs.game.domain.calcattack.CalcRoleYuan;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.road.RoleRoadCard;
import com.mdy.dzs.game.domain.yuan.RoleYuan;
import com.mdy.dzs.game.fight.main.FConstants;
import com.mdy.sharp.container.Container;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

public class RoleAttackCalcManager {

	final static Logger logger = LoggerFactory.get(RoleAttackCalcManager.class);

	protected DataAction dataAction = Container.get().createRemote(DataAction.class,
			DataApplication.CLUSTER_DATA_SYSTEM);

	private CacheManager cacheManager;
	private Map<Integer, CalcRoleCard> calcCardMap;
	private Map<Integer, CalcRoleEquip> calcEquipMap;
	private Map<Integer, CalcRoleGong> calcGongMap;
	private Map<Integer, CalcRoleYuan> calcYuanMap;
	private Map<Integer, Map<Integer, List<Prop>>> calcEquipSuitMap;

	public RoleAttackCalcManager(CacheManager cache) {
		this.cacheManager = cache;
		init();
	}

	//
	private void init() {
		calcCardMap = new HashMap<Integer, CalcRoleCard>();
		calcEquipMap = new HashMap<Integer, CalcRoleEquip>();
		calcGongMap = new HashMap<Integer, CalcRoleGong>();
		calcYuanMap = new HashMap<Integer, CalcRoleYuan>();
		calcEquipSuitMap = new HashMap<Integer, Map<Integer, List<Prop>>>();
	}

	public void reload() {
		initCalcCardProps();

		Map<Integer, Item> items = cacheManager.getValues(Item.class);
		List<Item> equips = new ArrayList<Item>();
		List<Item> gongs = new ArrayList<Item>();
		List<Item> yuans = new ArrayList<Item>();
		for (Item item : items.values()) {
			if (item.getType() == Packet.POS_EQUIP) {
				equips.add(item);
			} else if (item.getType() == Packet.POS_GONG) {
				gongs.add(item);
			} else if (item.getType() == Packet.POS_YUAN) {
				yuans.add(item);
			}
		}
		initCalcEquipProps(equips);
		initCalcGongProps(gongs);
		initCalcYuanProps(yuans);
		initCalcEquipSuit();
	}

	/**
	 * 初始化装备套装
	 */
	private void initCalcEquipSuit() {
		calcEquipSuitMap.clear();
		Map<Integer, EquipSuit> map = cacheManager.getValues(EquipSuit.class);
		for (EquipSuit es : map.values()) {
			Map<Integer, List<Prop>> props = new HashMap<Integer, List<Prop>>();
			List<Integer> nature = new ArrayList<Integer>(es.getNature1());
			List<Integer> num = new ArrayList<Integer>(es.getNum1());
			props.put(2, getPropsByList(nature, num));
			nature.addAll(es.getNature2());
			num.addAll(es.getNum2());
			props.put(3, getPropsByList(nature, num));
			nature.addAll(es.getNature3());
			num.addAll(es.getNum3());
			props.put(4, getPropsByList(nature, num));
			calcEquipSuitMap.put(es.getId(), props);
		}

	}

	private List<Prop> getPropsByList(List<Integer> natures, List<Integer> vals) {
		Map<Integer, Integer> props = new HashMap<Integer, Integer>();
		int max = Math.max(natures.size(), vals.size());
		for (int i = 0; i < max; i++) {
			int key = natures.get(i);
			if (!props.containsKey(key)) {
				props.put(key, 0);
			}
			props.put(key, props.get(key) + vals.get(i));
		}
		List<Prop> res = convProps(props);
		return res;
	}

	private void initCalcYuanProps(List<Item> yuans) {
		calcYuanMap.clear();
		ShangXianSheDing maxLimit = cacheManager.getShangXian();
		for (Item yuan : yuans) {
			CalcRoleYuan calc = new CalcRoleYuan();
			calc.init(yuan, maxLimit.getZhenqidengji());
			calcYuanMap.put(yuan.getId(), calc);
		}
	}

	private void initCalcGongProps(List<Item> gongs) {
		calcGongMap.clear();
		ShangXianSheDing maxLimit = cacheManager.getShangXian();
		for (Item gong : gongs) {
			CalcRoleGong calc = new CalcRoleGong();
			Refine refine = cacheManager.getValueByKey(Refine.class, gong.getId());
			calc.init(gong, maxLimit.getWuxuedengji(), refine, maxLimit.getJingLian());
			calcGongMap.put(gong.getId(), calc);
		}
	}

	private void initCalcEquipProps(List<Item> equips) {
		calcEquipMap.clear();
		ShangXianSheDing maxLimit = cacheManager.getShangXian();
		for (Item equip : equips) {
			CalcRoleEquip calc = new CalcRoleEquip();
			calc.init(equip, maxLimit.getZhuangbeidengji());
			calcEquipMap.put(equip.getId(), calc);
		}
	}

	private void initCalcCardProps() {
		calcCardMap.clear();
		Map<Integer, Card> cards = cacheManager.getValues(Card.class);
		Map<Integer, RoadCardLevel> roadCards = cacheManager.getValues(RoadCardLevel.class);
		Map<Integer, JiBan> relations = cacheManager.getValues(JiBan.class);
		ShangXianSheDing maxLimit = cacheManager.getShangXian();
		for (Entry<Integer, Card> entry : cards.entrySet()) {
			CalcRoleCard calc = new CalcRoleCard();
			int cardId = entry.getKey();
			Card card = entry.getValue();

			List<JiBan> jibans = new ArrayList<JiBan>();
			for (Integer jiBanId : card.getFate1()) {
				JiBan jiban = relations.get(jiBanId);
				if (jiban != null)
					jibans.add(jiban);
			}
			calc.init(card, roadCards.get(cardId), jibans,
					card.getType() == 0 ? maxLimit.getZhujuedengji() : maxLimit.getKapaidengji(),
					card.getType() == 0 ? maxLimit.getZhujuejinjie() : maxLimit.getKapaijinjiecishu(),
					maxLimit.getJianghulu());
			calcCardMap.put(cardId, calc);
		}
	}

	/**
	 * 计算未上阵卡片面板值
	 * 
	 * @param rcard
	 * @param road
	 * @param relationIds
	 */
	public void clacRoleCard(RoleCard rcard, RoleRoadCard road, Set<Integer> relationIds) {
		CalcRoleCard calc = calcCardMap.get(rcard.getResId());
		if (calc == null) {
			logger.error("not init calc card:" + rcard.getResId());
			return;
		}
		Map<Integer, Integer> props = calc.getValues(rcard.getLevel(), rcard.getCls(),
				road == null ? 0 : road.getLevel(), relationIds, null);
		List<List<Integer>> views = getCardViewBase(calc.getCard().getBase(), calc.getCard().getLead(), props);
		rcard.setViewBase(views.get(0));
		rcard.setViewLead(views.get(1));
	}

	/**
	 * 计算上阵卡片面板值
	 * 
	 * @param rCard
	 * @param relationIds
	 * @param channels
	 * @param rEquips
	 * @param rGongs
	 * @param rYuans
	 * @param road
	 */
	public void clacRoleCard(RoleCard rCard, Set<Integer> relationIds, List<Prop> channels, List<Equip> rEquips,
			List<RoleGong> rGongs, List<RoleYuan> rYuans, RoleRoadCard road) {
		Map<Integer, Integer> props = getFmtCardProps(rCard, relationIds, channels, rEquips, rGongs, rYuans, road);
		Card card = cacheManager.getValueByKey(Card.class, rCard.getResId());
		List<List<Integer>> views = getCardViewBase(card.getBase(), card.getLead(), props);
		rCard.setViewBase(views.get(0));
		rCard.setViewLead(views.get(1));
	}

	/**
	 * 获取上阵卡片附加属性
	 * 
	 * @param rCard
	 * @param relationIds
	 * @param channels
	 * @param rEquips
	 * @param rGongs
	 * @param rYuans
	 * @param road
	 * @return
	 */
	public Map<Integer, Integer> getFmtCardProps(RoleCard rCard, Set<Integer> relationIds, List<Prop> channels,
			List<Equip> rEquips, List<RoleGong> rGongs, List<RoleYuan> rYuans, RoleRoadCard road) {
		CalcRoleCard calc = calcCardMap.get(rCard.getResId());
		if (calc == null) {
			logger.error("not init calc card:" + rCard.getResId());
			return null;
		}
		Map<Integer, Integer> props = calc.getValues(rCard.getLevel(), rCard.getCls(),
				road == null ? 0 : road.getLevel(), relationIds, channels);
		if (rEquips != null) {
			Map<Integer, Integer> equipSuitCount = new HashMap<Integer, Integer>();
			for (Equip rEquip : rEquips) {
				CalcRoleEquip calcEquip = calcEquipMap.get(rEquip.getResId());
				if (calcEquip == null) {
					logger.error("not init calc equip:" + rEquip.getResId());
					continue;
				}

				// 统计套装
				int suit = calcEquip.getData().getSuit();
				if (suit != 0) {
					Integer old = equipSuitCount.get(suit);
					equipSuitCount.put(suit, old == null ? 1 : old + 1);
				}

				addValues(props, calcEquip.getValues(rEquip.getLevel(), rEquip.getProps()));
			}
			// 计算套装
			for (Entry<Integer, Integer> entry : equipSuitCount.entrySet()) {
				Map<Integer, List<Prop>> suitProps = calcEquipSuitMap.get(entry.getKey());
				if (suitProps != null && entry.getValue() > 1) {
					addValues(props, suitProps.get(entry.getValue()));
				}
			}
		}

		if (rGongs != null) {
			for (RoleGong rGong : rGongs) {
				CalcRoleGong calcGong = calcGongMap.get(rGong.getResId());
				if (calcGong == null) {
					logger.error("not init calc gong:" + rGong.getResId());
					continue;
				}
				addValues(props, calcGong.getValues(rGong.getLevel(), rGong.getPropsN()));
			}
		}

		if (rYuans != null) {
			for (RoleYuan rYuan : rYuans) {
				CalcRoleYuan calcYuan = calcYuanMap.get(rYuan.getResId());
				if (calcYuan == null) {
					logger.error("not init calc yuan:" + rYuan.getResId());
					continue;
				}
				addValues(props, calcYuan.getValues(rYuan.getLevel()));
			}
		}

		return props;
	}

	/**
	 * 获取卡片面板值
	 * 
	 * @param base
	 * @param lead
	 * @param props
	 * @return
	 */
	public List<List<Integer>> getCardViewBase(List<Integer> base, List<Integer> lead, Map<Integer, Integer> props) {
		if (props == null)
			props = new HashMap<Integer, Integer>();
		Integer addLife = props.get(FConstants.AttInd_Life);
		addLife = addLife == null ? 0 : addLife;
		Integer addAttack = props.get(FConstants.AttInd_Attack);
		addAttack = addAttack == null ? 0 : addAttack;
		Integer addDefense = props.get(FConstants.AttInd_Defense);
		addDefense = addDefense == null ? 0 : addDefense;
		Integer addDefenseM = props.get(FConstants.AttInd_DefenM);
		addDefenseM = addDefenseM == null ? 0 : addDefenseM;

		Integer addLifeR = props.get(FConstants.AttInd_LifeR);
		addLifeR = addLifeR == null ? 0 : addLifeR;
		Integer addAttackR = props.get(FConstants.AttInd_AttackR);
		addAttackR = addAttackR == null ? 0 : addAttackR;
		Integer addDefenseR = props.get(FConstants.AttInd_DefenseR);
		addDefenseR = addDefenseR == null ? 0 : addDefenseR;
		Integer addDefenseMR = props.get(FConstants.AttInd_DefenMR);
		addDefenseMR = addDefenseMR == null ? 0 : addDefenseMR;

		Integer addLead = props.get(FConstants.AttInd_Lead);
		addLead = addLead == null ? 0 : addLead;
		Integer addForce = props.get(FConstants.AttInd_Force);
		addForce = addForce == null ? 0 : addForce;
		Integer addSmart = props.get(FConstants.AttInd_Smart);
		addSmart = addSmart == null ? 0 : addSmart;

		double v1 = (base.get(0) + addLife) * (1.0 + addLifeR * 0.0001);
		double v2 = (base.get(1) + addAttack) * (1.0 + addAttackR * 0.0001);
		double v3 = (base.get(2) + addDefense) * (1.0 + addDefenseR * 0.0001);
		double v4 = (base.get(3) + addDefenseM) * (1.0 + addDefenseMR * 0.0001);
		double h1 = (lead.get(0) + addLead);
		double h2 = (lead.get(1) + addForce);
		double h3 = (lead.get(2) + addSmart);
		v1 *= (50.0 + h1) / 100.0;
		List<Integer> baseAry = Arrays.asList((int) Math.round(v1), (int) Math.round(v2), (int) Math.round(v3),
				(int) Math.round(v4));
		List<Integer> leadAry = Arrays.asList((int) Math.round(h1), (int) Math.round(h2), (int) Math.round(h3));
		return Arrays.asList(baseAry, leadAry);
	};

	public CalcRoleGong getCalcGong(int resId) {
		return calcGongMap.get(resId);
	}

	public CalcRoleEquip getCalcEquip(int resId) {
		return calcEquipMap.get(resId);
	}

	public CalcRoleYuan getCalcYuan(int resId) {
		return calcYuanMap.get(resId);
	}

	/**
	 * 属性加值
	 * 
	 * @param values
	 * @param idx
	 * @param val
	 */
	public static void addValue(Map<Integer, Integer> values, Integer idx, Integer val) {
		if (idx == null || val == null)
			return;
		if (!values.containsKey(idx)) {
			values.put(idx, 0);
		}
		values.put(idx, values.get(idx) + val);
	}

	/**
	 * 属性加值
	 * 
	 * @param res
	 * @param values
	 */
	public static void addValues(Map<Integer, Integer> res, Map<Integer, Integer> values) {
		if (res == null || values == null)
			return;
		for (Entry<Integer, Integer> value : values.entrySet()) {
			addValue(res, value.getKey(), value.getValue());
		}
	}

	/**
	 * 属性加值
	 * 
	 * @param res
	 * @param props
	 */
	public static void addValues(Map<Integer, Integer> res, List<Prop> props) {
		if (res == null || props == null)
			return;
		for (Prop prop : props) {
			addValue(res, prop.getIdx(), prop.getVal());
		}
	}

	/**
	 * 转换成prop列表
	 * 
	 * @param res
	 * @param values
	 */
	public static List<Prop> convProps(Map<Integer, Integer> values) {
		List<Prop> res = new ArrayList<Prop>();
		for (Entry<Integer, Integer> value : values.entrySet()) {
			res.add(new Prop(value.getKey(), value.getValue()));
		}
		return res;
	}

	public static List<Integer> packetProps(Map<Integer, Integer> values, List<Integer> idx) {
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < idx.size(); i++) {
			int ind = idx.get(i);
			Integer val = values.get(ind);
			res.add(val == null ? 0 : val);
		}
		return res;
	}

	/**
	 * 计算生命值
	 * 
	 * @param base
	 * @param lead
	 * @param props
	 * @return
	 */
	public static int caclLife(List<Integer> base, List<Integer> lead, Map<Integer, Integer> props) {
		Integer addLife = props.get(FConstants.AttInd_Life);
		addLife = addLife == null ? 0 : addLife;
		Integer addLifeR = props.get(FConstants.AttInd_LifeR);
		addLifeR = addLifeR == null ? 0 : addLifeR;
		Integer addLead = props.get(FConstants.AttInd_Lead);
		addLead = addLead == null ? 0 : addLead;
		double v1 = (base.get(0) + addLife) * (1.0 + addLifeR * 0.0001);
		double h1 = (lead.get(0) + addLead);
		v1 *= (50.0 + h1) / 100.0;
		return (int) Math.round(v1);
	}

}
