package com.mdy.dzs.game.domain.calcattack;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;

public class CalcRoleYuan {
	private int id;
	private Item data;
	private int maxLevel;
	private Map<Integer, Integer> yuanBaseProps;
	private Map<Integer, Integer> yuanLevelProps;
	
	public CalcRoleYuan() {
		yuanBaseProps = new HashMap<Integer, Integer>();
		yuanLevelProps = new HashMap<Integer, Integer>();
	}
	
	public void init(Item data,int maxLevel){
		id = data.getId();
		this.data = data;
		this.maxLevel = maxLevel;
		initYuanBaseProps();
		initYuanLevelProps();
	}
	
	private void initYuanBaseProps(){
		for (int i = 0; i< data.getArrNature().size();i++){
			RoleAttackCalcManager.addValue(yuanBaseProps,
					data.getArrNature().get(i),
					data.getArrValue().get(i));
		}
	}
	
	private void initYuanLevelProps(){
		for (int i = 0; i< data.getArrNature().size();i++){
			RoleAttackCalcManager.addValue(yuanLevelProps,
					data.getArrNature().get(i),
					data.getArrAddition().get(i));
		}
	}
	
	/**
	 * 通过等级 洗练属性 返回总加值
	 * @param level
	 * @param props
	 * @return
	 */
	public Map<Integer, Integer> getValues(int level){
		Map<Integer, Integer> res = new HashMap<Integer, Integer>();
		//附加基础属性
		for (Entry<Integer, Integer> val : yuanBaseProps.entrySet()) {
			RoleAttackCalcManager.addValue(res,
					val.getKey(), val.getValue());
		}
		//附加等级属性
		for (Entry<Integer, Integer> val : yuanLevelProps.entrySet()) {
			RoleAttackCalcManager.addValue(res,
					val.getKey(), val.getValue()*(level));
		}
		return res;
	}
}
