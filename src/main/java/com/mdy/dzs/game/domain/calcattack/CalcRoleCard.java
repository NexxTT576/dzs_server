package com.mdy.dzs.game.domain.calcattack;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.road.RoadCardLevel;
import com.mdy.dzs.data.domain.role.JiBan;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.fight.main.FConstants;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;

public class CalcRoleCard {

	/**卡片id*/
	private int id;
	private Card card;


	private RoadCardLevel roadCard;
	private List<JiBan> jbAry;
	
	private int maxLevel;
	private int maxCls;
	private int maxRoadLevel;
	
	/**卡片固定提升属性*/
	private Map<Integer, Integer> cardLevelProps;
	/**卡片阶数对应属性*/
	private Map<Integer, Map<Integer, Integer>> cardClsProps;
	/**卡片名将等级对应属性*/
	private Map<Integer, Map<Integer, Integer>> cardRoadLevelProps;
	/**卡片羁绊对应属性*/
	private Map<Integer, Map<Integer, Integer>> cardRelationProps;
	
	public CalcRoleCard() {
		cardLevelProps = new HashMap<Integer, Integer>();
		cardClsProps = new LinkedHashMap<Integer, Map<Integer, Integer>>();
		cardRoadLevelProps = new LinkedHashMap<Integer, Map<Integer, Integer>>();
		cardRelationProps = new LinkedHashMap<Integer, Map<Integer,Integer>>();
	}
	
	public void init(
			Card card,RoadCardLevel roadCard,List<JiBan> jibans,
			int maxLevel,int maxCls,int maxRoadLevel){
		id = card.getId();
		this.card = card;
		this.roadCard = roadCard;
		this.jbAry = jibans;
		this.maxLevel = maxLevel;
		this.maxCls = maxCls;
		this.maxRoadLevel = maxRoadLevel;
		initCardLevelProps();
		initCardClsProps();
		initRoadCard();
		initCardRelation();
	}
	
	
	private void initCardLevelProps() {
		int attInd[] = {
				FConstants.AttInd_Life,
				FConstants.AttInd_Attack,
				FConstants.AttInd_Defense,
				FConstants.AttInd_DefenM
				};
		List<Integer> leveAdd = card.getLevelAdd();
		for (int i = 0; i < leveAdd.size(); i++) {
			RoleAttackCalcManager.addValue(
					cardLevelProps,attInd[i], leveAdd.get(i));
		}
	}
	
	private void initCardClsProps() {
		for (int cls = 0; cls < maxCls; cls++) {
			Map<Integer, Integer> props = new HashMap<Integer, Integer>();
			RoleAttackCalcManager.addValues(props,cardClsProps.get(cls));
			if(card.getAdvLife().size()>cls){
				RoleAttackCalcManager.addValue(props,
						FConstants.AttInd_Life, card.getAdvLife().get(cls));
			}
			if(card.getAdvAttack().size()>cls){
				RoleAttackCalcManager.addValue(props,
						FConstants.AttInd_Attack, card.getAdvAttack().get(cls));
			}
			if(card.getAdvDefense().size()>cls){
				RoleAttackCalcManager.addValue(props,
						FConstants.AttInd_Defense, card.getAdvDefense().get(cls));
			}
			if(card.getAdvDefenseM().size()>cls){
				RoleAttackCalcManager.addValue(props,
						FConstants.AttInd_DefenM, card.getAdvDefenseM().get(cls));
			}
			cardClsProps.put(cls+1, props);
		}
	}

	private void initRoadCard(){
		if(roadCard == null) return;
		for (int rLevel = 0; rLevel < maxRoadLevel; rLevel++) {
			Map<Integer, Integer> props = new HashMap<Integer, Integer>();
			Map<Integer, Integer> lastProps = cardRoadLevelProps.get(rLevel);
			RoleAttackCalcManager.addValue(props,
					roadCard.getArrNature().get(rLevel), roadCard.getArrNum().get(rLevel));
			if(lastProps != null){
				RoleAttackCalcManager.addValues(props,lastProps);
			}
			cardRoadLevelProps.put(rLevel+1, props);
		}
	}
	
	private void initCardRelation(){
		if(jbAry == null) return;
		for(int i=0; i<jbAry.size(); i++){
			Map<Integer, Integer> props = new HashMap<Integer, Integer>();
			JiBan jbItem = jbAry.get(i);
			RoleAttackCalcManager.addValue(props,
					jbItem.getNature1(),jbItem.getValue1());
			RoleAttackCalcManager.addValue(props,
					jbItem.getNature2(),jbItem.getValue2());
			RoleAttackCalcManager.addValue(props,
					jbItem.getNature3(),jbItem.getValue3());
			cardRelationProps.put(jbItem.getId(), props);			
		}
	}
	
	public Map<Integer, Integer> getValues(
			int level,int cls,int roadLevel,
			Set<Integer>relationIds,
			List<Prop> channels){
		Map<Integer, Integer> res = new HashMap<Integer, Integer>();
		//附加等级属性
		for (Entry<Integer, Integer> val : cardLevelProps.entrySet()) {
			RoleAttackCalcManager.addValue(res,
					val.getKey(), val.getValue()*(level-1));
		}
		//附加升阶属性
		Map<Integer, Integer> cardClsProp = cardClsProps.get(cls);
		if(cardClsProp!= null){
			RoleAttackCalcManager.addValues(res,cardClsProp);
		}
		//附加名将属性
		Map<Integer, Integer> cardRoadLevelProp = cardRoadLevelProps.get(roadLevel);
		if(cardRoadLevelProp!= null){
			RoleAttackCalcManager.addValues(res,cardRoadLevelProp);
		}
		//附加羁绊属性
		for (Integer relationId : relationIds) {
			Map<Integer, Integer> relation = cardRelationProps.get(relationId);
			RoleAttackCalcManager.addValues(res, relation);
		}
		//经脉
		RoleAttackCalcManager.addValues(res, channels);
		return res;
	}
	
	public Card getCard() {
		return card;
	}
}
