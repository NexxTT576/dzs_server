package com.mdy.dzs.game.domain.calcattack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.gong.Refine;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.fight.main.FConstants;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;

public class CalcRoleGong {
	private int id;
	private Item data;
	private int maxLevel;
	private Refine refine;
	private int maxRefineLevel;
	private int maxRefineSize;
	private int refineNum;
	
	private Map<Integer, Integer> gongBaseProps;
	private Map<Integer, Integer> gongLevelProps;
	private Map<Integer, Prop> gongLockProps;
	private Map<Integer, Map<Integer, Integer>> gongRefineProps;
	
	public CalcRoleGong() {
		gongBaseProps = new HashMap<Integer, Integer>();
		gongLevelProps = new HashMap<Integer, Integer>();
		gongLockProps = new LinkedHashMap<Integer, Prop>();
		gongRefineProps = new LinkedHashMap<Integer,Map<Integer, Integer>>();
	}
	
	public void init(Item data,int maxLevel,Refine refine,int maxRefineLevel ){
		id = data.getId();
		this.data = data;
		this.refine = refine;
		this.maxLevel = maxLevel;
		this.maxRefineLevel = maxRefineLevel;
		initGongBaseProps();
		initGongLevelProps();
		initGongLockProps();
		initGongRefineProps();
	}
	
	private void initGongLockProps() {
		if(refine == null) return;
		List<Integer> levels = refine.getArrLevel();
		List<Integer> natures = refine.getArrNature1();
		List<Integer> values = refine.getArrValue1();
		for (int i = 0; i < levels.size(); i++) {
			gongLockProps.put(levels.get(i), new Prop(natures.get(i), values.get(i)));
		}
		
	}

	private void initGongRefineProps(){
		if(refine == null || refine.getRefine()!=1) return;
		List<Integer> natures = refine.getArrNature2();
		List<Integer> values = refine.getArrValue2();
		refineNum = natures.size();
		maxRefineSize = maxRefineLevel * refineNum;
		for (int i =1; i <= maxRefineSize ; i++ ) {
			Map<Integer, Integer> props = i==1?
			new HashMap<Integer, Integer>():new HashMap<Integer,Integer>(gongRefineProps.get(i-1));
			int pos = i%refineNum-1;if(pos == -1) pos = refineNum-1;
			RoleAttackCalcManager.addValue(props,natures.get(pos),values.get(pos));
			gongRefineProps.put(i,props);
		}
	}

	private void initGongBaseProps(){
		for (int i = 0; i< data.getArrNature().size();i++){
			RoleAttackCalcManager.addValue(gongBaseProps,
					data.getArrNature().get(i),
					data.getArrValue().get(i));
		}
	}
	
	private void initGongLevelProps(){
		for (int i = 0; i< data.getArrNature().size();i++){
			RoleAttackCalcManager.addValue(gongLevelProps,
					data.getArrNature().get(i),
					data.getArrAddition().get(i));
		}
	}
	
	/**
	 * 通过等级 洗练属性 返回基础加值
	 * @param level
	 * @param props
	 * @return
	 */
	public Map<Integer, Integer> getBaseValues(int level){
		Map<Integer, Integer> res = new HashMap<Integer, Integer>();
		//附加基础属性
		for (Entry<Integer, Integer> val : gongBaseProps.entrySet()) {
			RoleAttackCalcManager.addValue(res,
					val.getKey(), val.getValue());
		}
		//附加等级属性
		for (Entry<Integer, Integer> val : gongLevelProps.entrySet()) {
			RoleAttackCalcManager.addValue(res,
					val.getKey(), val.getValue()*(level));
		}
		return res;
	}
	
	/**
	 * 通过等级 洗练属性 返回总加值
	 * @param level
	 * @param props
	 * @return
	 */
	public Map<Integer, Integer> getValues(int level,int refineNum){
		Map<Integer, Integer> res = getBaseValues(level);
		//附加解锁属性
		for (Entry<Integer, Prop> val : gongLockProps.entrySet()) {
			if(level < val.getKey()) continue;
			RoleAttackCalcManager.addValue(res,
					val.getValue().getIdx(), val.getValue().getVal());
		}
		if(refineNum > 0){
			RoleAttackCalcManager.addValues(res,gongRefineProps.get(refineNum));
		}
		
		return res;
	}
	
	/**
	 * 最大精炼等级
	 * @return
	 */
	public int getMaxRefineLevel() {
		return maxRefineLevel;
	}

	/**
	 * 最大精炼次数
	 * @return
	 */
	public int getMaxRefineCount() {
		return maxRefineSize;
	}
	
	/**
	 * 最大等级
	 * @return
	 */
	public int getMaxLevel() {
		return maxLevel;
	}

	/**
	 * 是否可精炼
	 * @return
	 */
	public boolean hasRefine() {
		return refine != null && refine.getRefine() == 1;
	}

	/**
	 * 精炼属性数
	 * @return
	 */
	public int getRefineNum() {
		return refineNum;
	}
	

	public Refine getRefine() {
		return refine;
	}


	public List<ProbItem> getRefineConsumeByCnt(int cnt){
		int lv = cnt / refineNum;
		return refine.getArrItem().get(lv);
	}

	public List<Integer> getBaseRate(int level){
		Map<Integer, Integer> values = getBaseValues(level);
		return RoleAttackCalcManager.packetProps(values,
				Arrays.asList(
						FConstants.AttInd_LifeR,
						FConstants.AttInd_AttackR,
						FConstants.AttInd_DefenseR,
						FConstants.AttInd_DefenMR
						));
	}
}
