package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.sharp.container.biz.BizException;

public interface RoleEquipAction {

	/**
	 * //装备出售 //m=equip&a=sell&acc=*&eids="*,*,.."
	 * /{"m":"equip","a":"sell","acc":"*","eids":"*,.."} //{err:'',
	 * d:[priceSell,curSilver]}
	 * 
	 * @param acc
	 * @param idAry
	 * @return
	 * @throws BizException
	 */

	List<Serializable> sellEquip(String acc, List<Integer> idAry) throws BizException;

	/**
	 * //装备升级 //a=lvUp&acc=*&id=*&auto=1/0
	 * /{"m":"equip","a":"lvUp","acc":"*","id":"*","auto":"1/0"} //rtn:[err,
	 * [lv,coin],equipLv, silverLeft}
	 * 
	 * @param acc
	 * @param id
	 * @param auto
	 * @return
	 * @throws BizException
	 */

	List<Serializable> levelUp(String acc, int id, int auto) throws BizException;

	/**
	 * //装备洗炼状态 //a=propState&acc=*&id=* props 洗出来的属性 propsWait 要替换的属性 //['',
	 * idAry,baseAry, propsAry, propsWaitAry, leftCnt] //idAry:[*,...]
	 * //属性id数组参见protocal_battle，属性id定义 //baseAry/propsAry/propsWaitAry:[*,..]
	 * //基础属性值数组/洗炼出已装载数组/洗练出未装载数组 //leftCnt:洗练石剩余数量
	 * 
	 * @param acc
	 * @param id
	 * @return
	 * @throws BizException
	 */
	List<Serializable> queryPropState(String acc, int id) throws BizException;

	/**
	 * //装备属性洗炼 //a=prop&acc=*&id=*&t=1/2/3 //['', idAry,baseAry, propsAry,
	 * propsWaitAry, leftCnt] //idAry:[*,...] //属性id数组参见protocal_battle，属性id定义
	 * //baseAry/propsAry/propsWaitAry:[*,..] //基础属性值数组/洗炼出已装载数组/洗练出未装载数组
	 * //leftCnt:洗练石剩余数量
	 * 
	 * @param acc
	 * @param id
	 * @param t
	 * @return
	 * @throws BizException
	 */

	List<Serializable> changeProp(String acc, int id, int type, int propCnt) throws BizException;

	/**
	 * //属性替换 //a=propRepl&acc=*&id=* //['', idAry,baseAry, propsAry, propsWaitAry,
	 * leftCnt] //idAry:[*,...] //属性id数组参见protocal_battle，属性id定义
	 * //baseAry/propsAry/propsWaitAry:[*,..] //基础属性值数组/洗炼出已装载数组/洗练出未装载数组
	 * 
	 * @param acc
	 * @param id
	 * @return
	 * @throws BizException
	 */

	List<Serializable> replaceProp(String acc, int id) throws BizException;
}
