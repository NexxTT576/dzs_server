package com.mdy.dzs.game.dao.cache;

import java.util.List;

import com.mdy.dzs.game.dao.UnionDAO;
import com.mdy.dzs.game.domain.union.Union;
import com.mdy.sharp.container.res.cache.CacheResource;

public class CacheUnionDAO extends UnionDAO{
	private static final String ID_KEY_PREFIX ="U";
	private SafeCache safeCache ;
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}
	public CacheUnionDAO() {
		safeCache = new SafeCache(1800);
	}
	private String getListKey(int type) {
		return ID_KEY_PREFIX + "_" + type;
	}
	/**
	 * 移除帮派list
	 */
	public void removeUnionList(){
		safeCache.removeFromCache(getListKey(0));
		safeCache.removeFromCache(getListKey(1));
	}
	/**
	 * 查询帮派列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Union> queryUnionBypage(int type){
		String key = getListKey(type);
		List<Union> r = (List<Union>) safeCache.loadFromCache( key );
		if(r == null){
			r = super.queryUnionBypage(type);
			safeCache.putToCache(key, r);
		}
		return r;
	}
	
	/**
	 * 建筑升级
	 */
	@Override
	public void upUnioncenter(Union union, int buildtype,int costMoney,int roleAdd) {
		super.upUnioncenter(union, buildtype, costMoney, roleAdd);
		removeUnionList();
	}
	/**
	 * 帮派基金
	 */
	@Override
	public void updateUnionTotalAndCurrentMoney(Union union ,int money){
		super.updateUnionTotalAndCurrentMoney(union, money);
		removeUnionList();
	}
	/**
	 * 创建帮派
	 */
	@Override
	public int createUnion(Union union) {
		int i = super.createUnion(union);
		removeUnionList();
		return i;
	}
	/**
	 * 修改公告信息
	 */
	@Override
	public void updateUnionIndes(Union union,String msg,int type){
		super.updateUnionIndes(union, msg, type);
		removeUnionList();
	}
	/**
	 * 解散帮派
	 */
	@Override
	public void deleteUnion(Union union){
		super.deleteUnion(union);
		removeUnionList();
	}
	/**
	 * 修改帮派攻击力
	 * 
	 * @param union
	 * @param sumattack
	 */
	public void updateUnionSumAttack(Union union, int sumattack) {
		super.updateUnionSumAttack(union, sumattack);
		removeUnionList();
	}
	/**
	 * 修改帮派帮主信息
	 */
	public void updateBossInfo(Union union){
		super.updateBossInfo(union);
		removeUnionList();
	}
	/**
	 * 修改帮派战斗力
	 */
	public void updateUnionAtt(StringBuffer ids,int unionId){
		super.updateUnionAtt(ids,unionId);
		removeUnionList();
	}
	/**
	 * 修改帮派排名
	 */
	public void updateUnionRank(Union union,int rank){
		super.updateUnionRank(union, rank);
		removeUnionList();
	}
}
