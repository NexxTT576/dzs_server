package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.swordfight.ClientAwardVO;
import com.mdy.dzs.game.domain.swordfight.ClientEnterSwordVO;
import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.sharp.container.biz.BizException;

public interface SwordfightAction {

	/**
	 * enterSword：进入论剑系统获得数据 client->server: Get: ?a=enterSword&acc=* Post:
	 * {"m":"swordfight", "a":"enterSword", "acc":"*", } acc: 账号 client<-server: {
	 * rntObj{ gold:金币, resetTimes:免费重置次数, curFloor:当前层数, goldResetTimes:元宝重置次数,
	 * resetGold:重置元宝数, cards:[{20级以上的侠客列表
	 * 
	 * }], enemies:[{敌人列表
	 * 
	 * }] awards:[已经领取的奖励层] } }
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	ClientEnterSwordVO enterSword(String acc) throws BizException;

	/**
	 * reset：重置敌人 client->server: Get: ?a=reset&acc=*&gold=* Post:
	 * {"m":"swordfight", "a":"reset", "acc":"*", "gold":"*", } acc: 账号 gold: 重置所需元宝
	 * client<-server: { gold:金币, resetTimes:免费重置次数, curFloor:当前层数,
	 * goldResetTimes:元宝重置次数,s resetGold:重置元宝数, cards:[{20级以上的侠客列表
	 * 
	 * }], enemies:[{敌人列表
	 * 
	 * }] }
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	ClientEnterSwordVO reset(String acc, int gold) throws BizException;

	/**
	 * award：领取奖励 client->server: Get: ?a=award&acc=*&floor=* Post:
	 * {"m":"swordfight", "a":"award", "acc":"*", "floor":"*", } acc: 账号 floor: 层
	 * client<-server: { gold:金币, silver:银币, }
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	ClientAwardVO award(String acc, int floor) throws BizException;

	/**
	 * fight：挑战敌人 client->server: Get: ?a=fight&acc=*&floor=*&fmt=* Post:
	 * {"m":"swordfight", "a":"fight", "acc":"*", "floor":"*", "fmt":"*", } acc: 账号
	 * floor: 层 fmt： 阵型数据 client<-server: {
	 * 
	 * }
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	List<Serializable> fight(String acc, int floor, List<SwordCard> fmt) throws BizException;

	/**
	 * combat：计算战斗力 client->server: Get: ?a=combat&acc=*&fmt=* Post:
	 * {"m":"swordfight", "a":"combat", "acc":"*", "fmt":"*", } acc: 账号 fmt： 阵型数据
	 * client<-server: { combat:战斗力 }
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	int combat(String acc, List<SwordCard> fmt) throws BizException;

}
