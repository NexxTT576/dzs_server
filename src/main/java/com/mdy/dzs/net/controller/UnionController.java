package com.mdy.dzs.net.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.UnionAction;
import com.mdy.dzs.game.action.UnionBossAction;
import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class UnionController extends Controller {
    public UnionAction unionAction = Container.get().createRemote(UnionAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);
    public UnionBossAction unionBossAction = Container.get().createRemote(UnionBossAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "enterMainBuilding": {
                // 进入帮派大殿
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.EnterMainBuilding(o.acc);
            }
            case "unionLevelUp": {
                // 帮派建筑升级
                UnionLevelUp o = new Gson().fromJson(msg, UnionLevelUp.class);
                return unionAction.unionLevelUp(o.acc, o.unionid, o.buildtype);
            }
            case "unionDonate": {
                // 捐献
                UnionDonate o = new Gson().fromJson(msg, UnionDonate.class);
                return unionAction.unionLevelUp(o.acc, o.unionid, o.donatetype);
            }
            case "unionWorkShopProduct": {
                // 工坊生产
                UnionWorkShopProduct o = new Gson().fromJson(msg, UnionWorkShopProduct.class);
                return unionAction.unionWorkShopProduct(o.acc, o.unionid, o.overtimeflag, o.worktype);
            }
            case "unionWorkShopGetReward": {
                // 工坊立即接受领取奖励
                UnionWorkShopGetReward o = new Gson().fromJson(msg, UnionWorkShopGetReward.class);
                return unionAction.unionWorkShopGetReward(o.acc, o.unionid);
            }
            case "unionShopList": {
                // 进入商店 获取商品列表
                UnionShopList o = new Gson().fromJson(msg, UnionShopList.class);
                return unionAction.unionShopList(o.acc, o.unionid, o.endflag);
            }
            case "createUnion": {
                // 创建帮派
                CreateUnion o = new Gson().fromJson(msg, CreateUnion.class);
                return unionAction.createUnion(o.acc, o.name, o.type);
            }
            case "applyUnion": {
                // 申请/取消申请帮派
                ApplyUnion o = new Gson().fromJson(msg, ApplyUnion.class);
                return unionAction.applyUnion(o.acc, o.uid, o.type);
            }
            case "showApplyList": {
                // 显示帮派排名列表
                ShowApplyList o = new Gson().fromJson(msg, ShowApplyList.class);
                return unionAction.showApplyList(o.acc, o.unionId);
            }
            case "handleApply": {
                // 同意/拒绝加入帮派
                HandleApply o = new Gson().fromJson(msg, HandleApply.class);
                return unionAction.handleApply(o.type, o.acc, o.unionId, o.applyRoleId);
            }
            case "searcheUnion": {
                // 帮派查询
                SearcheUnion o = new Gson().fromJson(msg, SearcheUnion.class);
                return unionAction.searchUnion(o.acc, o.unionName, o.start, o.total);
            }
            case "updateUnionIndes": {
                // 0编辑公告1宣言(只帮主)
                UpdateUnionIndes o = new Gson().fromJson(msg, UpdateUnionIndes.class);
                return unionAction.updateUnionIndes(o.acc, o.msg, o.type);
            }
            case "abdication": {
                // 禅让帮主
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.abdication(o.acc);
            }
            case "coverLeader": {
                // 自荐帮主
                CoverLeader o = new Gson().fromJson(msg, CoverLeader.class);
                return unionAction.coverLeader(o.acc, o.leaderId);
            }
            case "enterUnion": {
                // 进入帮派
                EnterUnion o = new Gson().fromJson(msg, EnterUnion.class);
                return unionAction.enterUnion(o.acc, o.num);
            }
            case "refuseAll": {
                // 一键拒绝
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.refuseAll(o.acc);
            }
            case "openBarbecue": {
                // 开启烧烤大会
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.openBarbecue(o.acc);
            }
            case "openActivities": {
                // 开启活动
                OpenActivities o = new Gson().fromJson(msg, OpenActivities.class);
                return unionAction.openActivities(o.acc, o.id);
            }
            case "enterWelfare": {
                // 进入福利页面
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.enterWelfare(o.acc);
            }
            case "getReward": {
                GetReward o = new Gson().fromJson(msg, GetReward.class);
                return unionAction.getReward(o.acc, o.id);
            }
            case "discussion": {
                // 切磋
                Discussion o = new Gson().fromJson(msg, Discussion.class);
                return unionAction.discussion(o.acc, o.figherRoleId);
            }
            case "exitUnion": {
                // 退出帮派
                ExitUnion o = new Gson().fromJson(msg, ExitUnion.class);
                return unionAction.exitUnion(o.acc, o.uid);
            }
            case "setposition": {
                // 任命/取消任命
                Setposition o = new Gson().fromJson(msg, Setposition.class);
                return unionAction.setposition(o.acc, o.appRoleId, o.jopType);
            }
            case "kcikRole": {
                // 踢出帮派
                KcikRole o = new Gson().fromJson(msg, KcikRole.class);
                return unionAction.kcikRole(o.acc, o.appRoleId);
            }
            case "showUnionRank": {
                // 查询帮派排名
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.showUnionRank(o.acc);
            }
            case "bossHistory": {
                // 查询帮派排名
                BossHistory o = new Gson().fromJson(msg, BossHistory.class);
                return unionBossAction.bossHistory(o.acc, o.unionId);
            }
            case "bossCreate": {
                // 开启boss战
                BossHistory o = new Gson().fromJson(msg, BossHistory.class);
                return unionBossAction.bossCreate(o.acc, o.unionId);
            }
            case "bossTop": {
                // boss伤害排行榜
                BossHistory o = new Gson().fromJson(msg, BossHistory.class);
                return unionBossAction.bossTop(o.acc, o.unionId);
            }
            case "bossState": {
                // boss实时状态
                BossHistory o = new Gson().fromJson(msg, BossHistory.class);
                return unionBossAction.bossState(o.acc, o.unionId);
            }
            case "bossPay": {
                // union boss鼓舞,复活
                BossPay o = new Gson().fromJson(msg, BossPay.class);
                return unionBossAction.bossPay(o.acc, o.unionId, o.use);
            }
            case "bossPve": {
                // boss 战斗
                BossHistory o = new Gson().fromJson(msg, BossHistory.class);
                return unionBossAction.bossPve(o.acc, o.unionId);
            }
            case "bossResult": {
                // union boss活动结果
                BossHistory o = new Gson().fromJson(msg, BossHistory.class);
                return unionBossAction.bossResult(o.acc, o.unionId);
            }
            case "showDynamicList": {
                // 查看帮派动态
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.showDynamicList(o.acc);
            }
            case "updateUnionLeader": {
                // 自荐时间到后，修改帮派帮主
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.updateUnionLeader(o.acc);
            }
            case "showAllMember": {
                // 查询成员列表
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.showAllMember(o.acc);
            }
            case "enterWorkShop": {
                // 进入作坊
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.enterWorkShop(o.acc);
            }
            case "checkTime": {
                // 活动检查时间
                CheckTime o = new Gson().fromJson(msg, CheckTime.class);
                return unionAction.checkTime(o.acc, o.id);
            }
            case "exchangeGoods": {
                // 商店兑换物品
                ExchangeGoods o = new Gson().fromJson(msg, ExchangeGoods.class);
                return unionAction.exchangeGoods(o.acc, o.id, o.type, o.count);
            }
            case "checkWorkShopTime": {
                CheckWorkShopTime o = new Gson().fromJson(msg, CheckWorkShopTime.class);
                return unionAction.checkWorkShopTime(o.acc, o.type);
            }
            case "checkInUnion": {
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.checkInUnion(o.acc);
            }
            case "checkUnionShopTime": {
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.checkUnionShopTime(o.acc);
            }
            case "enterUnionCopy": {
                // 进入帮派副本
                CheckWorkShopTime o = new Gson().fromJson(msg, CheckWorkShopTime.class);
                return unionAction.enterUnionCopy(o.acc, o.type);
            }
            case "enterSingleCopy": {
                // 进入单个副本
                EnterSingleCopy o = new Gson().fromJson(msg, EnterSingleCopy.class);
                return unionAction.enterSingleCopy(o.acc, o.type, o.id);
            }
            case "showHurtList": {
                // 查看伤害排行
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.showHurtList(o.acc);
            }
            case "chooseCard": {
                // 帮派副本 挑选上阵
                Base o = new Gson().fromJson(msg, Base.class);
                return unionAction.chooseCard(o.acc);
            }
            case "receiveRewards": {
                // 帮派副本领取通关奖励
                ReceiveRewards o = new Gson().fromJson(msg, ReceiveRewards.class);
                return unionAction.receiveRewards(o.acc, o.type, o.id);
            }
            case "unionFBfight": {
                UnionFBfight o = new Gson().fromJson(msg, UnionFBfight.class);
                List<SwordCard> list = new ArrayList<SwordCard>();
                for (int i = 0; i < o.fmt.size(); i++) {
                    List<Integer> one = o.fmt.get(i);
                    SwordCard sc = new SwordCard();
                    sc.setOrder(i + 1);
                    sc.setId(one.get(0));
                    sc.setPos(one.get(1));
                    list.add(sc);
                }
                return unionAction.unionFBfight(o.acc, o.type, o.id, list);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class UnionLevelUp extends Base {
        public int unionid = 0;
        public int buildtype = 0;
    }

    class UnionDonate extends Base {
        public int unionid = 0;
        public int donatetype = 0;
    }

    class UnionWorkShopProduct extends Base {
        public int unionid = 0;
        public int overtimeflag = 0;
        public int worktype = 0;
    }

    class UnionWorkShopGetReward extends Base {
        public int unionid = 0;
    }

    class UnionShopList extends Base {
        public int unionid = 0;
        public int endflag = 0;
    }

    class CreateUnion extends Base {
        public String name = "";
        public int type = 0;
    }

    class ApplyUnion extends Base {
        public int uid = 0;
        public int type = 0;
    }

    class ShowApplyList extends Base {
        public int unionId = 0;
    }

    class HandleApply extends Base {
        public int type = 0;
        public int unionId = 0;
        public int applyRoleId = 0;
    }

    class SearcheUnion extends Base {
        public String unionName = "";
        public int start = 0;
        public int total = 0;
    }

    class UpdateUnionIndes extends Base {
        public String msg = "";
        public int type = 0;
    }

    class CoverLeader extends Base {
        public int leaderId = 0;
    }

    class EnterUnion extends Base {
        public int num = 0;
    }

    class OpenActivities extends Base {
        public int id = 0;
    }

    class GetReward extends Base {
        public int id = 0;
    }

    class Discussion extends Base {
        public int figherRoleId = 0;
    }

    class ExitUnion extends Base {
        public int uid = 0;
    }

    class Setposition extends Base {
        public int appRoleId = 0;
        public int jopType = 0;
    }

    class KcikRole extends Base {
        public int appRoleId = 0;
    }

    class BossHistory extends Base {
        public int unionId = 0;
    }

    class BossPay extends Base {
        public int unionId = 0;
        public int use = 0;
    }

    class CheckTime extends Base {
        public int id = 0;
    }

    class ExchangeGoods extends Base {
        public int id = 0;
        public int type = 0;
        public int count = 0;
    }

    class CheckWorkShopTime extends Base {
        public int type = 0;
    }

    class EnterSingleCopy extends Base {
        public int type = 0;
        public int id = 0;
    }

    class ReceiveRewards extends Base {
        public int type = 0;
        public int id = 0;
    }

    class UnionFBfight extends Base {
        public int type = 0;
        public int id = 0;
        public List<List<Integer>> fmt;
    }
}