package com.mdy.dzs.game.dao.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mdy.dzs.game.dao.UnionBossPlayerDAO;
import com.mdy.dzs.game.domain.boss.BossBubbleVO;
import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.boss.RankCompare;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.dzs.game.domain.union.UnionBossPlayer;
import com.mdy.sharp.container.res.cache.CacheResource;

public class CacheUnionBossPlayerDAO extends UnionBossPlayerDAO {

	private static final String ID_KEY_PREFIX ="UNIONBOSS";
	private SafeCache safeCache ;

	//时效
	public CacheUnionBossPlayerDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}
	
	//topTenKey
	private String getTopTenKey(int unionId) {
		return ID_KEY_PREFIX+unionId+"topTen";
	}
	//定义冒字list key
	private String getBubbleListKey(int unionId) {
		return ID_KEY_PREFIX+unionId+"bubbleList";
	}
	//定义排行列表key
	private String getRankListKey(int unionId) {
		return ID_KEY_PREFIX+unionId+"Rank";
	}
	//定义所有玩家roleId List key
	private String getAllPlayerIdKey(int unionId){
		return ID_KEY_PREFIX+unionId+"allPlayerId";
	}
	//获取玩家key
	private String getPlayerKey(int roleId, int unionId){
		return ID_KEY_PREFIX+unionId+"player"+roleId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BossTopTenVO> getTopTenList(int unionId){
		String key 					= getTopTenKey(unionId);
		List<BossTopTenVO> topList  = (List<BossTopTenVO>) safeCache.loadFromCache(key);
				
		if(topList != null){
			return topList;
		}else{
			return new ArrayList<BossTopTenVO>();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void deleteBossCache(int unionId){
		safeCache.removeFromCache(getBubbleListKey(unionId));//冒字
		safeCache.removeFromCache(getTopTenKey(unionId));//topTen
		safeCache.removeFromCache(getRankListKey(unionId));//rankList
		//清空所有参与者cache
		String allPlayerIdKey   = getAllPlayerIdKey(unionId);//所有玩家roleId List key
		List<Integer> allRoleId = (List<Integer>) safeCache.loadFromCache(allPlayerIdKey);
		if(allRoleId != null){
			for (int i = 0; i < allRoleId.size(); i++) {
				safeCache.removeFromCache(getPlayerKey(allRoleId.get(i), unionId));
			}
		}
		safeCache.removeFromCache(getAllPlayerIdKey(unionId));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RankList> getRankList(int unionId){
		String key 				= getRankListKey(unionId);
		List<RankList> rankList = (List<RankList>) safeCache.loadFromCache(key);
			
		if(rankList != null){
			return rankList;
		}else{
			return new ArrayList<RankList>();
		}
	}
	//冒字列表getBubbleList
	@SuppressWarnings("unchecked")
	@Override
	public List<BossBubbleVO> getBubbleList(int roleId,int unionId,int refreDis){
		String key 					  = getBubbleListKey(unionId);
		List<BossBubbleVO> bubbles 	  = (List<BossBubbleVO>) safeCache.loadFromCache( key );
		List<BossBubbleVO> rtnBubbles = new ArrayList<BossBubbleVO>();
		
		if(bubbles != null)	{
			Date now 		= new Date();
			long timeStamp 	= now.getTime();
			//遍历删除过时数据
			for (Iterator<BossBubbleVO> iterator = bubbles.iterator(); iterator.hasNext();) {
				BossBubbleVO bossBubbleVO = (BossBubbleVO) iterator.next();
				long dis 				  = timeStamp - bossBubbleVO.getTimestamp();
				if(dis > refreDis*1000){
					iterator.remove();
					continue;
				}else if(roleId != 0 && bossBubbleVO.getRoleId() != roleId){
					rtnBubbles.add(bossBubbleVO);
				}
			}
		}else{
			return new ArrayList<BossBubbleVO>();
		}
		safeCache.putToCache(key, bubbles);
			
		if(roleId == 0){
			return bubbles;	//更新bubbles
		}else{
			return rtnBubbles;	//返回stat可见bubbles
		}
	}

	@Override
	public int updateRankList(String acc, String name, int totalHurt, int lv, int id, int unionId, String faction){
		String rankListkey 		= getRankListKey(unionId);
		List<RankList> rankList = getRankList(unionId);
		Boolean isContain 		= false;
		int rank 				= 0;
		for(int i = 0; i < rankList.size(); i++){
			if(rankList.get(i).getId() == id){		//更新
				isContain = true;
				rankList.get(i).setTotalHurt((rankList.get(i).getTotalHurt() + totalHurt));
				break;
			}
		}
		if(!isContain){								//加入
			RankList addNew = new RankList();
			addNew.setAcc(acc);
			addNew.setId(id);
			addNew.setLv(lv);
			addNew.setName(name);
			addNew.setTotalHurt(totalHurt);
			addNew.setFaction(faction);
			rankList.add(addNew);
		}
		
		//重排排行榜
		RankCompare compar = new RankCompare();
		Collections.sort(rankList,compar);
		//更新伤害列表
		safeCache.putToCache(rankListkey, rankList);
		
		for(int i = 0; i < rankList.size(); i++){
			if(rankList.get(i).getId() == id){//更新
				rank = i + 1;
				break;
			}
		}
		//更新topTen
		if(rank <= 10){
			String topKey = getTopTenKey(unionId);
			List<BossTopTenVO> topList = getTopTenList(unionId);
			topList.clear();
			int size = rankList.size()>10?10:rankList.size();
			for(int i = 0; i < size; i++){
				topList.add(new BossTopTenVO(i+1, rankList.get(i).getAcc(), rankList.get(i).getName()
							, rankList.get(i).getTotalHurt(), rankList.get(i).getLv(), rankList.get(i).getId(), rankList.get(i).getFaction()));
			}
			safeCache.putToCache(topKey, topList);
		}
		return rank;
	}
	//更新冒泡列表
	@Override
	public void updateBubbleList(String name, int hurt, int roleId,int unionId,int rtnNum, int bubbleDis){
		String key 					= getBubbleListKey(unionId);
		List<BossBubbleVO> bubbles  = getBubbleList(0, unionId, bubbleDis);
		long timeStamp 				= new Date().getTime();
		BossBubbleVO newBubble 		= new BossBubbleVO(name, hurt, timeStamp, roleId);
		
		if(bubbles.size() >= rtnNum){//已满足存储数量，替换掉最旧数据
			bubbles.remove(0);			
		}
		bubbles.add(newBubble);
		safeCache.putToCache(key, bubbles);
	}
	//查询
	public UnionBossPlayer query(int roleId, int unionId){		
		String playerKey 		= getPlayerKey(roleId,unionId);
		UnionBossPlayer player  = (UnionBossPlayer) safeCache.loadFromCache(playerKey);
		if(player == null){//find DB
			player = super.query(roleId,unionId);
		}
		return player;
	}
	//add
	@SuppressWarnings("unchecked")
	public void add(String acc, int roleId, String name, int unionId){
		super.add(acc, roleId, name, unionId);
		UnionBossPlayer player  = super.query(roleId,unionId);
		String allPlayerIdKey 	 = getAllPlayerIdKey(unionId);//添加roleId到缓存
		String playerKey		 = getPlayerKey(roleId,unionId);
		List<Integer> allId   	 = (List<Integer>) safeCache.loadFromCache(allPlayerIdKey); 		
		
		if(allId == null){
			allId = new ArrayList<Integer>(); 
		}
		if(allId.contains(roleId) == false){
			allId.add(roleId);
		}
		safeCache.putToCache(allPlayerIdKey, allId);
		safeCache.putToCache(playerKey, player);
	}
	//更新
	public void update(UnionBossPlayer bbp){
		if(bbp == null)	return;
		super.update(bbp);
		safeCache.removeFromCache(getPlayerKey(bbp.getRoleId(),bbp.getUnionId()));
	}
	//更新攻打次数
	public void updateBattleCnt(int roleId, int unionId){
		super.updateBattleCnt(roleId, unionId);
		safeCache.removeFromCache(getPlayerKey(roleId, unionId));
	}
	//删除数据
	public void delete(int unionId){
		super.delete(unionId);
	}
	
}
