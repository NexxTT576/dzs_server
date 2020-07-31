package com.mdy.dzs.game.dao.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mdy.dzs.game.dao.BossBattlePlayerDAO;
import com.mdy.dzs.game.domain.boss.BossBattlePlayer;
import com.mdy.dzs.game.domain.boss.BossBubbleVO;
import com.mdy.dzs.game.domain.boss.BossTopTenVO;
import com.mdy.dzs.game.domain.boss.RankCompare;
import com.mdy.dzs.game.domain.boss.RankList;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.res.cache.CacheResource;

public class CacheBossBattlePlayerDAO extends BossBattlePlayerDAO {

	private static final String ID_KEY_PREFIX ="BOSS";
	private SafeCache safeCache ;
	//时效
	public CacheBossBattlePlayerDAO() {
		safeCache = new SafeCache( 1800 );
	}
	
	public void setCacheResource(CacheResource cacheResource) {
		safeCache.setCacheResource( cacheResource );
	}
	//获取玩家key
	private String getPlayerKey(int roleId){
		return ID_KEY_PREFIX+"player"+roleId;
	}
	//定义冒字list key
	private String getBubbleListKey(){
		return ID_KEY_PREFIX+"bubbleList";
	}
	//定义所有玩家roleId List key
	private String getAllPlayerIdKey(){
		return ID_KEY_PREFIX+"allPlayerId";
	}
	//定义参与boss战玩家 key
	private String getRankListKey(){
		return ID_KEY_PREFIX+"Rank";
	}
	//定义排行榜 key
	private String getTopTenKey(){
		return ID_KEY_PREFIX+"topTen";
	}

	/**获取冒字list*/
	@SuppressWarnings("unchecked")
	@Override
	public List<BossBubbleVO> getBubbleList(int roleId){
		String key 						= getBubbleListKey();
		List<BossBubbleVO> bubbles 		= (List<BossBubbleVO>) safeCache.loadFromCache( key ); 
		List<BossBubbleVO> rtnBubbles 	= new ArrayList<BossBubbleVO>();
		
		if(bubbles != null)	{			
			//遍历删除过时数据
			Date now = new Date();
			long timeStamp =  now.getTime();
			for (Iterator<BossBubbleVO> iterator = bubbles.iterator(); iterator.hasNext();) {
				BossBubbleVO bossBubbleVO = (BossBubbleVO) iterator.next();
				long dis = timeStamp - bossBubbleVO.getTimestamp();
				if(dis > Constants.bossBattleRefreshDis*1000){
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
			return	bubbles;	//更新bubbles
		}else{
			return rtnBubbles;	//返回stat可见bubbles
		}
	}

	/**更新冒字list*/
	@Override
	public void updateBubbleList(String name, int hurt, int roleId){
		String key 					= getBubbleListKey();
		List<BossBubbleVO> bubbles  = getBubbleList(0);
		Date now 					= new Date();
		long timeStamp 				=  now.getTime();
		BossBubbleVO newBubble 		= new BossBubbleVO(name, hurt, timeStamp, roleId);
		if(bubbles.size() >= Constants.bossBattleBubbleNum){//已满足存储数量，替换掉最旧数据
			bubbles.remove(0);			
		}
		bubbles.add(newBubble);
		
		safeCache.putToCache(key, bubbles);
	}
	
	/**获取topTenList*/
	@SuppressWarnings("unchecked")
	@Override
	public List<BossTopTenVO> getTopTenList(){
		String key 					= getTopTenKey();
		List<BossTopTenVO> topList  = (List<BossTopTenVO>) safeCache.loadFromCache(key);
		if(topList != null){
			return topList;
		}else{
			return new ArrayList<BossTopTenVO>();
		}
	}

	/**获取排行列表*/
	@SuppressWarnings("unchecked")
	@Override
	public List<RankList> getRankList(){
		String key 				= getRankListKey();
		List<RankList> rankList = (List<RankList>) safeCache.loadFromCache(key);
		
		if(rankList != null){
			return rankList;
		}else{
			return new ArrayList<RankList>();
		}
	}
	@Override
	public int updateRankList(String acc, String name, int totalHurt, int lv, int id, String faction){
		String rankListkey = getRankListKey();
		List<RankList> rankList = getRankList();
		Boolean isContain = false;
		int rank = 0;
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
			String topKey = getTopTenKey();
			List<BossTopTenVO> topList = getTopTenList();
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
	//清除boss战缓存数据
	@SuppressWarnings("unchecked")
	@Override
	public void deleteBossCache(){
		safeCache.removeFromCache(getBubbleListKey());//冒字
		safeCache.removeFromCache(getTopTenKey());//topTen
		safeCache.removeFromCache(getRankListKey());//rankList
		//清空所有参与者cache
		String allPlayerIdKey   = getAllPlayerIdKey();//所有玩家roleId List key
		List<Integer> allRoleId = (List<Integer>) safeCache.loadFromCache(allPlayerIdKey);
		if(allRoleId != null){
			for (int i = 0; i < allRoleId.size(); i++) {
				safeCache.removeFromCache(getPlayerKey(allRoleId.get(i)));
			}
		}
		safeCache.removeFromCache(getAllPlayerIdKey());
	}
	//查询
	public BossBattlePlayer query(int roleId){		
		String playerKey = getPlayerKey(roleId);
		BossBattlePlayer player = (BossBattlePlayer) safeCache.loadFromCache(playerKey);
		if(player == null){//find DB
			player = super.query(roleId);
		}
		return player;
	}
	//add
	@SuppressWarnings("unchecked")
	public void add(String acc, int roleId, String name){
		super.add(acc, roleId, name);
		BossBattlePlayer player  = super.query(roleId);
		String allPlayerIdKey 	 = getAllPlayerIdKey();//添加roleId到缓存
		String playerKey		 = getPlayerKey(roleId);
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
	public void update(BossBattlePlayer bbp){
		if(bbp == null)	return;
		super.update(bbp);
		safeCache.removeFromCache(getPlayerKey(bbp.getRoleId()));
	}
	//清空t_boss_role数据表
	public void delete(){
		super.delete();
	}
	//跟新攻打次数
	public void updateBattleCnt(int roleId){
		super.updateBattleCnt(roleId);
		safeCache.removeFromCache(getPlayerKey(roleId));
	}
}
