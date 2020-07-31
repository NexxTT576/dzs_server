package com.mdy.dzs.game.dao.cache;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.dao.RoleUnionDAO;
import com.mdy.dzs.game.domain.union.RoleUnion;
import com.mdy.sharp.container.res.cache.CacheResource;

public class CacheRoleUnionDAO extends RoleUnionDAO {
	private static final String ID_KEY_PREFIX ="RU";
	private SafeCache safeCache ;

	public CacheRoleUnionDAO() {
		safeCache = new SafeCache(1800);
	}
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource(cacheResource);
	}
	private String getIdKey(int roleId) {
		return ID_KEY_PREFIX + "_" + roleId;
	}
	private String getUnionMember(int unionId) {
		return ID_KEY_PREFIX + "/" + unionId;
	}
	private String getUnionMemberCount(int unionId) {
		return ID_KEY_PREFIX + unionId;
	}
	/**
	 *查询所有成员
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleUnion> queryAllMember(int unionId){
		String key = getUnionMember(unionId);
		List<RoleUnion> allMember = (List<RoleUnion>)safeCache.loadFromCache( key );
		if(allMember == null){
			allMember = super.queryAllMember(unionId);
			safeCache.putToCache(key, allMember);
		}
		return allMember;
	}
	/**
	 * 查询某一个成员
	 */
	@Override
	public RoleUnion queryRoleUnionById(int roleId){
		String key = getIdKey(roleId);
		RoleUnion r = (RoleUnion)safeCache.loadFromCache(key);
		if(r == null){
			r = super.queryRoleUnionById(roleId);
			safeCache.putToCache(key, r);
		}
		return r;
	}
	/**
	 * 查询帮派人数
	 */
	@Override
	public int queryAllMemberNum(int unionId) {
		String key = getUnionMemberCount(unionId);
		Integer count = (Integer) safeCache.loadFromCache(key);
		if(count == null){
			count = super.queryAllMemberNum(unionId);
			safeCache.putToCache(key, count);
		}
		return count;
	}
	/**
	 * 创建玩家帮派信息
	 */
	@Override
	public void createroleUnion(RoleUnion roleUnion) {
		super.createroleUnion(roleUnion);
		safeCache.removeFromCache(getUnionMemberCount(roleUnion.getUnionId()));
		safeCache.removeFromCache(getUnionMember(roleUnion.getUnionId()));
	}
	/**
	 * 修改玩家帮派信息
	 */
	public void updateRoleUnion(RoleUnion roleUnion){
		super.updateRoleUnion(roleUnion);
		String key = getIdKey(roleUnion.getRoleId());
		safeCache.removeFromCache(key);
		safeCache.removeFromCache(getUnionMember(roleUnion.getUnionId()));
		safeCache.removeFromCache(getUnionMemberCount(roleUnion.getUnionId()));
	}
	/**
	 * 修改作坊信息
	 */
	@Override
	public void updateRoleProduct(RoleUnion ru){
		super.updateRoleProduct(ru);
		String key = getIdKey(ru.getRoleId());
		safeCache.removeFromCache(key);
		safeCache.removeFromCache(getUnionMember(ru.getUnionId()));
	}
	
	/**
	 * 清除信息
	 */
	@Override
	public void clearRoleUnion(RoleUnion ru,int unionId){
		super.clearRoleUnion(ru,unionId);
		String key = getIdKey(ru.getRoleId());
		safeCache.removeFromCache(key);
		safeCache.removeFromCache(getUnionMember(unionId));
		safeCache.removeFromCache(getUnionMemberCount(unionId));
	}
	
	/**
	 * 帮主自荐
	 */
	@Override
	public void updatecoverLeader(int roleId, Date time,int unionId) {
		super.updatecoverLeader(roleId,time,unionId);
		String key = getIdKey(roleId);
		safeCache.removeFromCache(key);
		safeCache.removeFromCache(getUnionMember(unionId));
	}
	/**
	 * 领取每周福利
	 */
	@Override
	public void updateBenefits(int roleId ,Date time,int gift,int unionId){
		super.updateBenefits(roleId,time,gift,unionId);
		String key = getIdKey(roleId);
		safeCache.removeFromCache(key);
		safeCache.removeFromCache(getUnionMember(unionId));
	}
	/**
	 * 开启烧烤大会
	 */
	@Override
	public void openBarbecue(RoleUnion roleUnion ,Date time,int gift){
		super.openBarbecue(roleUnion,time,gift);
		String key = getIdKey(roleUnion.getRoleId());
		safeCache.removeFromCache(key);
		safeCache.removeFromCache(getUnionMember(roleUnion.getUnionId()));
	}
	/**
	 * 更新玩家职务
	 */
	@Override
	public void updateRoleJop(RoleUnion roleUnion,int jopType){
		super.updateRoleJop(roleUnion,jopType);
		String key = getIdKey(roleUnion.getRoleId());
		safeCache.removeFromCache(key);
		safeCache.removeFromCache(getUnionMember(roleUnion.getUnionId()));
	}
	/**
	 * 修改玩家贡献  捐献
	 * @param union
	 * @param money
	 * @return
	 */
	public void updateUnionRoleTLContribute(int contribute, int unionid,
			int roleid, int conType, int costMoney) {
		super.updateUnionRoleTLContribute(contribute, unionid, roleid, conType,costMoney);
		String key = getIdKey(roleid);
		safeCache.removeFromCache(key);
		safeCache.removeFromCache(getUnionMember(unionid));
	}
	/**
	 * 更新玩家帮贡
	 */
	public void updateTotalContribute(RoleUnion r, int val) {
		super.updateTotalContribute(r, val);
		safeCache.removeFromCache(getIdKey(r.getRoleId()));
		safeCache.removeFromCache(getUnionMember(r.getUnionId()));
	}
	/**
	 * 更新玩家剩余帮贡
	 */
	public void updateLastContribute(RoleUnion r, int val) {
		super.updateLastContribute(r, val);
		safeCache.removeFromCache(getIdKey(r.getRoleId()));
		safeCache.removeFromCache(getUnionMember(r.getUnionId()));
	}
	/**
	 * 定时修改玩家排名
	 */
	public void updateRoleInfo(RoleUnion roleUnion){
		super.updateRoleInfo(roleUnion);
		safeCache.removeFromCache(getIdKey(roleUnion.getRoleId()));
		safeCache.removeFromCache(getUnionMember(roleUnion.getUnionId()));
	}
	/**
	 * 修改玩家作坊领奖状态
	 */
	public void updateWShopRewardState(RoleUnion roleUnion){
		super.updateWShopRewardState(roleUnion);
		safeCache.removeFromCache(getIdKey(roleUnion.getRoleId()));
		safeCache.removeFromCache(getUnionMember(roleUnion.getUnionId()));
	}
	/**
	 * 修改玩家排行
	 */
	public void updateRoleUnionAtt(RoleUnion roleUnion){
		super.updateRoleUnionAtt(roleUnion);
		safeCache.removeFromCache(getIdKey(roleUnion.getRoleId()));
		safeCache.removeFromCache(getUnionMember(roleUnion.getUnionId()));
	}
	/**
	 * 修改玩家商店信息
	 */
	public void updateExchangeShopInfo(RoleUnion roleUnion,List<Integer> ids){
		super.updateExchangeShopInfo(roleUnion, ids);
		safeCache.removeFromCache(getIdKey(roleUnion.getRoleId()));
		safeCache.removeFromCache(getUnionMember(roleUnion.getUnionId()));
	}
}
