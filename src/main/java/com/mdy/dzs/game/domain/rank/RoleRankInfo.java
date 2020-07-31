/**
 * 
 */
package com.mdy.dzs.game.domain.rank;

import java.io.Serializable;
import java.util.List;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年1月29日  下午5:59:38
 */
public class RoleRankInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**排行列表*/
	private List<RankInfo> infos;
	/**自己排行*/
	private RankInfo myRank;
	public List<RankInfo> getInfos() {
		return infos;
	}
	public void setInfos(List<RankInfo> infos) {
		this.infos = infos;
	}
	public RankInfo getMyRank() {
		return myRank;
	}
	public void setMyRank(RankInfo myRank) {
		this.myRank = myRank;
	}
	
	@Override
	public String toString() {
		String res = "";
		for (RankInfo info : infos)
		{
			res += info.toString() +"_,\r_";
		}
		if(myRank != null) res += myRank.toString();
		return res;
	}
}
