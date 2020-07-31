package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.sharp.container.biz.BizException;

/**
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月23日 上午11:16:33
 */
public interface RoleYuanAction {

	/**
	 * collect：聚元 client->server: Get: ?m=yuan&a=collect&acc=*[&t=1/2/3] Post: {
	 * "m":"yuan", "a":"collect", "acc":"*" } acc: 账号 t: 聚元方式 1：银币聚元 2：金币/道具聚元
	 * 3：聚10次
	 * 
	 * client<-server: {"0":err, "1":getAry "2":level "3":silver "4":itemNum
	 * "5":gold}
	 * 
	 * err: 错误信息 getAry: 聚到的精元列表[resId，resId...] collLv： 当前聚元等级 1-5 silver： 拥有银币数
	 * itemNum： 拥有道具数 gold: 拥有金币数
	 * 
	 * @param acc
	 * @param type
	 * @return
	 * @throws BizException
	 */

	List<Serializable> collect(String acc, int type) throws BizException;

	/**
	 * lvUp：升级操作 client->server: Get: ?m=yuan&a=lvUp&acc=*&id=*&ids=[id,id...] Post:
	 * { "m":"yuan", "a":"lvUp", "acc":"*", "id":"id", "id":[id,id...] } acc: 账号 id:
	 * 升级精元于精元列表中的objid ids: [被吞噬的精元objid]
	 * 
	 * client<-server: {"0":err, "1": obj "2":list}
	 * 
	 * err: 错误信息 obj： 升级精元数据{resId,level,curExp,props}
	 * 
	 * @param acc 账号
	 * @param id  升级精元于精元列表中的objid
	 * @param ids [被吞噬的精元objid]
	 * @return
	 * @throws BizException
	 */

	List<Serializable> levelUp(String acc, int id, List<Integer> ids) throws BizException;

	/**
	 * cmdUse:使用道具或金币 client->server: Get: ?m=yuan&a=useItem&acc=* Post: {
	 * "m":"yuan", "a":"useItem", "acc":"*", } acc: 账号
	 * 
	 * client<-server: {"0":err, "1": curCollLv "2":gold "3":itemNum}
	 * 
	 * err: 错误信息 curCollLv： 当前聚元等级 gold: 金币数 itemNum: 剩余道具数 增加聚元等级
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	List<Serializable> addCollectLV(String acc) throws BizException;
}
