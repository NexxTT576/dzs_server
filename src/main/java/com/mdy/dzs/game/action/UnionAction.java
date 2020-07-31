package com.mdy.dzs.game.action;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.dzs.game.domain.union.BuildUpVO;
import com.mdy.dzs.game.domain.union.CheckShopTimeVO;
import com.mdy.dzs.game.domain.union.CheckTimeVO;
import com.mdy.dzs.game.domain.union.CheckWorkTimeVO;
import com.mdy.dzs.game.domain.union.ChooseCardsVO;
import com.mdy.dzs.game.domain.union.CoverVO;
import com.mdy.dzs.game.domain.union.DonateVO;
import com.mdy.dzs.game.domain.union.EnterMainBuildingVO;
import com.mdy.dzs.game.domain.union.EnterShopVO;
import com.mdy.dzs.game.domain.union.EnterSingleCopyVO;
import com.mdy.dzs.game.domain.union.EnterUnionFBVO;
import com.mdy.dzs.game.domain.union.EnterUnionVO;
import com.mdy.dzs.game.domain.union.EnterWVO;
import com.mdy.dzs.game.domain.union.EnterWorkShopVO;
import com.mdy.dzs.game.domain.union.ExchangeVO;
import com.mdy.dzs.game.domain.union.OpenActivityVO;
import com.mdy.dzs.game.domain.union.ShowAllMemberVO;
import com.mdy.dzs.game.domain.union.ShowDynamicVO;
import com.mdy.dzs.game.domain.union.ShowHurtListVO;
import com.mdy.dzs.game.domain.union.UnionDataVO;
import com.mdy.dzs.game.domain.union.UnionGetRewardVO;
import com.mdy.dzs.game.domain.union.UnionSuccessVO;
import com.mdy.dzs.game.domain.union.WorkShopFinishVO;
import com.mdy.dzs.game.domain.union.WorkShopProductVO;
import com.mdy.sharp.container.biz.BizException;

public interface UnionAction {
	/**
	 * 进入帮派主建筑
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	EnterMainBuildingVO EnterMainBuilding(String acc) throws BizException;

	/**
	 * 建筑物升级升级
	 * 
	 * @param buildtype 0大殿1工坊2商店
	 */

	BuildUpVO unionLevelUp(String acc, int unionid, int buildtype) throws BizException;

	/**
	 * 帮派捐款
	 * 
	 * @param acc        用户id
	 * @param unionid    工会id
	 * @param donatetype 捐赠类型
	 */

	DonateVO unionDonate(String acc, int unionid, int donatetype) throws BizException;

	/**
	 * 工坊_生产
	 * 
	 * @param acc          用户id
	 * @param unionid      工会id
	 * @param overtimeflag 是否加班生产0加班1普通
	 * @param worktype     生产类型1侠魂2银币
	 */

	WorkShopProductVO unionWorkShopProduct(String acc, int unionid, int overtimeflag, int worktype) throws BizException;

	/**
	 * 立即结束&领取奖励
	 * 
	 * @param acc     用户id
	 * @param unionid 工会id
	 * @param endflag 是否立即结束
	 */

	WorkShopFinishVO unionWorkShopGetReward(String acc, int unionid) throws BizException;

	/**
	 * 获取商店列表
	 * 
	 * @param acc     用户id
	 * @param unionid 工会id
	 * @param endflag 获取数量0珍宝1道具2全部
	 */

	EnterShopVO unionShopList(String acc, int unionid, int endflag) throws BizException;

	/**
	 * 创建帮派
	 * 
	 * @param acc
	 * @param name
	 * @param type
	 * @return
	 * @throws BizException
	 */

	UnionDataVO createUnion(String acc, String name, int type) throws BizException;

	/**
	 * 申请/取消申请加入帮派
	 * 
	 * @param acc
	 * @param uid
	 * @param type
	 * @return
	 * @throws BizException
	 */

	UnionSuccessVO applyUnion(String acc, int uid, int type) throws BizException;

	/**
	 * 处理玩家申请 （同意或拒绝）
	 * 
	 * @param type
	 * @param acc
	 * @param unionId
	 * @param applyRoleId
	 * @return
	 * @throws BizException
	 */

	UnionSuccessVO handleApply(int type, String acc, int unionId, int applyRoleId) throws BizException;

	/**
	 * 展示申请列表
	 * 
	 * @param acc
	 * @param unionId
	 * @param type
	 * @return
	 * @throws BizException
	 */
	ShowAllMemberVO showApplyList(String acc, int unionId) throws BizException;

	/**
	 * 查询帮派
	 * 
	 * @param acc
	 * @param unionName
	 * @return
	 * @throws BizException
	 */
	UnionDataVO searchUnion(String acc, String unionName, int start, int total) throws BizException;

	/**
	 * 0修改公告1修改宣言
	 * 
	 * @param acc
	 * @param msg
	 * @return
	 * @throws BizException
	 */

	UnionSuccessVO updateUnionIndes(String acc, String msg, int type) throws BizException;

	/**
	 * 禅让帮主
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	UnionSuccessVO abdication(String acc) throws BizException;

	/**
	 * 自荐帮主
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	CoverVO coverLeader(String acc, int leaderId) throws BizException;

	/**
	 * 进入帮派
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	EnterUnionVO enterUnion(String acc, int num) throws BizException;

	/**
	 * 一键拒绝
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	UnionSuccessVO refuseAll(String acc) throws BizException;

	/**
	 * 开启烧烤大会
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	OpenActivityVO openBarbecue(String acc) throws BizException;

	/**
	 * 开启烧烤大会openActivities
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	OpenActivityVO openActivities(String acc, int id) throws BizException;

	/**
	 * 进入福利页面
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	EnterWVO enterWelfare(String acc) throws BizException;

	/**
	 * 切磋
	 * 
	 * @param acc
	 * @param fightRoleId
	 * @return
	 * @throws BizException
	 */

	UnionSuccessVO discussion(String acc, int fightRoleId) throws BizException;

	/**
	 * 退出帮派
	 * 
	 * @param acc
	 * @param uid
	 * @return
	 * @throws BizException
	 */

	UnionSuccessVO exitUnion(String acc, int uid) throws BizException;

	/**
	 * 任命/取消任命
	 * 
	 * @param acc
	 * @param appRoleId
	 * @param jopType
	 * @return
	 * @throws BizException
	 */

	UnionSuccessVO setposition(String acc, int appRoleId, int jopType) throws BizException;

	/**
	 * 踢出帮派
	 * 
	 * @param acc
	 * @param appRoleId
	 * @return
	 */

	UnionSuccessVO kcikRole(String acc, int appRoleId) throws BizException;

	/**
	 * 展示帮派排名
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	UnionDataVO showUnionRank(String acc) throws BizException;

	/**
	 * 查看帮派动态
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	ShowDynamicVO showDynamicList(String acc) throws BizException;

	/**
	 * 自荐时间满后，修改帮主
	 * 
	 * @param acc
	 * @param unionId
	 * @return
	 * @throws BizException
	 */

	CoverVO updateUnionLeader(String acc) throws BizException;

	/**
	 * 展示帮派成员
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */
	ShowAllMemberVO showAllMember(String acc) throws BizException;

	/**
	 * 定时修改帮派信息
	 */

	void updateUnionInfo();

	/**
	 * 进入工坊
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	EnterWorkShopVO enterWorkShop(String acc) throws BizException;

	/**
	 * 领取奖励
	 * 
	 * @param acc
	 * @param id
	 * @return
	 * @throws BizException
	 */

	UnionGetRewardVO getReward(String acc, int id) throws BizException;

	/**
	 * 检测活动时间
	 * 
	 * @param acc
	 * @param id
	 * @return
	 * @throws BizException
	 */

	CheckTimeVO checkTime(String acc, int id) throws BizException;

	/**
	 * 商店兑换商品
	 * 
	 * @param acc
	 * @param id
	 * @return
	 * @throws BizException
	 */

	ExchangeVO exchangeGoods(String acc, int id, int type, int count) throws BizException;

	/**
	 * 工坊校验时间
	 */

	CheckWorkTimeVO checkWorkShopTime(String acc, int id) throws BizException;

	// 检测是否在帮派中
	UnionSuccessVO checkInUnion(String acc) throws BizException;

	// 校验帮派商店时间

	CheckShopTimeVO checkUnionShopTime(String acc) throws BizException;

	/**
	 * 进入帮派副本
	 * 
	 * @param acc
	 * @param type
	 * @return
	 * @throws BizException
	 */

	EnterUnionFBVO enterUnionCopy(String acc, int type) throws BizException;

	/**
	 * 进入单个副本
	 * 
	 * @param acc
	 * @param type
	 * @param id
	 * @return
	 * @throws BizException
	 */

	EnterSingleCopyVO enterSingleCopy(String acc, int type, int id) throws BizException;

	/**
	 * 展示伤害排行
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	ShowHurtListVO showHurtList(String acc) throws BizException;

	/**
	 * 选择上阵卡牌
	 * 
	 * @param acc
	 * @return
	 * @throws BizException
	 */

	ChooseCardsVO chooseCard(String acc) throws BizException;

	/**
	 * 领取副本通关奖励
	 * 
	 * @param acc
	 * @param type
	 * @param fbId
	 * @return
	 * @throws BizException
	 */

	UnionSuccessVO receiveRewards(String acc, int type, int fbId) throws BizException;

	/**
	 * 帮派副本战斗
	 * 
	 * @param acc
	 * @param type
	 * @param fbId
	 * @param fmt
	 * @return
	 * @throws BizException
	 */

	List<Serializable> unionFBfight(String acc, int type, int fbId, List<SwordCard> fmt) throws BizException;
}
