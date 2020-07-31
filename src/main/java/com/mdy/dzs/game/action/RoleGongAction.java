/**
 * 
 */
package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.gong.vo.RoleGongRefineVO;
import com.mdy.sharp.container.biz.BizException;

/**
 * 内外功
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月23日 上午10:22:57
 */
public interface RoleGongAction {

	/**
	 * levelUp：内外功强化 client->server: Get: ?a=lvUp&acc=*&cids=*&op=1/2 Post:
	 * {"m":"gong", "a":"lvUp", "acc":"*", "cids":"mainGong,gong1,gong2,...",
	 * "op":"1/2" } acc: 账号 cids: 主卡，消耗卡数组 op: 1仅计算/2确认强化
	 * 
	 * client<-server: {"0":err, "1": gongInfo, "2":silver}
	 * 
	 * err: 错误信息 gongInfo： 内外功信息 {name名称:*, lv等级:*, baseRate属性比例加成数组:[],
	 * limit本级经验上限: add增加属性:[] //op=2时，返回值为空 cost消耗银币:* exp获得经验:* }
	 * 
	 * @param acc
	 * @param cids
	 * @param op
	 * @return
	 * @throws BizException
	 */

	List<Serializable> levelUp(String acc, List<Integer> cids, int op) throws BizException;

	/**
	 * propUp：内外功精炼 client->server: Get: ?a=propUp&acc=*&id=* Post: {"m":"gong",
	 * "a":"propUp", "acc":"*", "id”:””， }
	 * 
	 * acc: 账号 id: 内外功id client<-server: {"0":err, "1":gongInfoCur, "2":gongInfoNxt,
	 * "3":itemInfo, "4":silver}
	 * 
	 * err: 错误信息 bAllow： 是否允许进阶 1/0 gongInfoCur： 武学信息 {id:*, lv等级:*, star星数:*,
	 * prop属性数组:[{idx:*,val:*},...]} nxtPropInd nxtPropVal itemInfo: 消耗物品数组[{id:*,
	 * n1:*, n2:*},...] silver: 消耗银币数
	 * 
	 * @param acc
	 * @param id
	 * @param op
	 * @return
	 * @throws BizException
	 */

	RoleGongRefineVO propUp(String acc, int id, int op) throws BizException;

	/**
	 * sell：内外功出售 client->server: Get: ?a=lvUp&acc=*&cids=*&op=1/2 Post:
	 * {"m":"gong", "a":"sell", "acc":"*", "cids":"gong1,gong2,..." } acc: 账号 cids:
	 * 出售卡数组
	 * 
	 * client<-server: {"0":err, "1": silver, "2": leftSilver}
	 * 
	 * err: 错误信息 silver： 出售价格
	 * 
	 * @param acc
	 * @param idAry
	 * @return
	 * @throws BizException
	 */

	List<Serializable> sellGong(String acc, List<Integer> idAry) throws BizException;

}
