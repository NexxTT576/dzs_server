/**
 * 
 */
package com.mdy.dzs.game.domain.calcattack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.fight.main.FConstants;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2014年10月21日  下午9:31:39
 */
public class CalcRoleEquip {

	private int id;
	private Item data;
	private int maxLevel;
	private Map<Integer, Integer> equipBaseProps;
	private Map<Integer, Integer> equipLevelProps;
	private List<Integer> natures;
	
	public CalcRoleEquip() {
		equipBaseProps = new HashMap<Integer, Integer>();
		equipLevelProps = new HashMap<Integer, Integer>();
		natures = new ArrayList<Integer>();
	}
	
	public void init(Item data,int maxLevel){
		id = data.getId();
		this.data = data;
		this.maxLevel = maxLevel;
		initEquipBaseProps();
		initEquipLevelProps();
	}
	
	private void initEquipBaseProps(){
		for (int i = 0; i< data.getArrNature().size();i++){
			RoleAttackCalcManager.addValue(equipBaseProps,
					data.getArrNature().get(i),
					data.getArrValue().get(i));
		}
	}
	
	private void initEquipLevelProps(){
		for (int i = 0; i< data.getArrNature().size();i++){
			RoleAttackCalcManager.addValue(equipLevelProps,
					data.getArrNature().get(i),
					data.getArrAddition().get(i));
			natures.add(data.getArrNature().get(i));
		}
	}
	
	/**
	 * 通过等级 洗练属性 返回总加值
	 * @param level
	 * @param props
	 * @return
	 */
	public Map<Integer, Integer> getValues(
			int level,List<Prop> props){
		Map<Integer, Integer> res = new HashMap<Integer, Integer>();
		//附加基础属性
		for (Entry<Integer, Integer> val : equipBaseProps.entrySet()) {
			RoleAttackCalcManager.addValue(res,
					val.getKey(), val.getValue());
		}
		//附加等级属性
		for (Entry<Integer, Integer> val : equipLevelProps.entrySet()) {
			RoleAttackCalcManager.addValue(res,
					val.getKey(), val.getValue()*(level));
		}
		RoleAttackCalcManager.addValues(res,props);
		return res;
	}

	public List<Integer> getBase(int level,List<Prop> props) {
		Map<Integer, Integer> values = null;
		if(level == 0){
			values = new HashMap<Integer, Integer>();
			RoleAttackCalcManager.addValues(values,props);
		}else{
			values = getValues(level, props);
		}
		return RoleAttackCalcManager.packetProps(values,
				Arrays.asList(
						FConstants.AttInd_Life,
						FConstants.AttInd_Attack,
						FConstants.AttInd_Defense,
						FConstants.AttInd_DefenM,
						FConstants.AttInd_FHarmV,
						FConstants.AttInd_FHarmDV
						));
	}
	
	public List<Integer> getBaseView(int level,List<Prop> props) {
		Map<Integer, Integer> values = getValues(level,null);
		for (Prop prop : props) {
			if(values.get(prop.getIdx()) != null){
				values.put(prop.getIdx(), values.get(prop.getIdx())+prop.getVal());
			}
		}
		return RoleAttackCalcManager.packetProps(values,
				Arrays.asList(
						FConstants.AttInd_Life,
						FConstants.AttInd_Attack,
						FConstants.AttInd_Defense,
						FConstants.AttInd_DefenM));
	}
	
	public Item getData(){
		return data;
	}
	
}