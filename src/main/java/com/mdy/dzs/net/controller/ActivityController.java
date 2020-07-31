package com.mdy.dzs.net.controller;

import java.util.Map;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.SystemApplication;
import com.mdy.dzs.game.action.RoleActivityAction;
import com.mdy.dzs.game.action.RoleActivityMazeAction;
import com.mdy.dzs.game.action.RoleActivityRouletteAction;
import com.mdy.dzs.game.domain.activity.ActivityConfig;
import com.mdy.dzs.game.domain.system.SystemStatus;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class ActivityController extends Controller {
    protected RoleActivityAction roleActivityAction = Container.get().createRemote(RoleActivityAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);
    protected RoleActivityRouletteAction roleActivityRouletteAction = Container.get()
            .createRemote(RoleActivityRouletteAction.class, DataApplication.CLUSTER_GAME_SYSTEM);
    protected RoleActivityMazeAction roleActivityMazeAction = Container.get().createRemote(RoleActivityMazeAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "status": {
                Base o = new Gson().fromJson(msg, Base.class);
                Map<Integer, Integer> status = SystemApplication.Instance().cacheManager().getActivityStatus();
                return roleActivityAction.queryActivityStatusByAcc(o.acc, status);
            }
            case "guessinfo": {
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_酒剑仙);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityAction.queryGuessInfos(o.acc);
            }
            case "guessing": {
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_酒剑仙);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityAction.guessGame(o.acc);
            }
            case "guesschoose": {
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_酒剑仙);
                Guesschoose o = new Gson().fromJson(msg, Guesschoose.class);
                return roleActivityAction.guessChoose(o.acc, o.pos);
            }
            case "guessbuy": {
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_酒剑仙);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityAction.guessBuyCount(o.acc);
            }
            case "happyGift": {
                // 累积登录礼包列表
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_登录累积奖励);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityAction.happyGift(o.acc);
            }
            case "happyStatus": {
                // 累积登录礼包玩家当前状态
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_登录累积奖励);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityAction.happyStatus(o.acc);
            }
            case "happyGet": {
                // 累积登录领取
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_登录累积奖励);
                HappyGet o = new Gson().fromJson(msg, HappyGet.class);
                return roleActivityAction.happyGet(o.acc, o.day);
            }
            case "monthSignStatus": {
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_月签);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityAction.monthSignStatus(o.acc);
            }
            case "monthSignGet": {
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_月签);
                MonthSignGet o = new Gson().fromJson(msg, MonthSignGet.class);
                return roleActivityAction.monthSignGet(o.acc, o.day, o.month);
            }
            case "investPlanStatus": {
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityAction.investPlanStatus(o.acc);
            }
            case "investPlanGet": {
                InvestPlanGet o = new Gson().fromJson(msg, InvestPlanGet.class);
                return roleActivityAction.investPlanGet(o.acc, o.lv);
            }
            case "investPlanBuy": {
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityAction.investPlanBuy(o.acc);
            }
            case "limitCard": {
                // 限时豪杰_进入
                SystemApplication.Instance().cacheManager().checkSystemOpenExcept(SystemStatus.SYSTEM_限时豪杰);
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_LIMITCARD);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityAction.limitCardGame(o.acc);
            }
            case "LimitDraw": {
                // 限时豪杰_抽卡
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_LIMITCARD);
                LimitDraw o = new Gson().fromJson(msg, LimitDraw.class);
                return roleActivityAction.LimitDraw(o.acc, o.isFree);
            }
            // 皇宫探宝
            case "rouletteEnter": {
                // 进入活动，页面状态
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_皇宫探宝);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityRouletteAction.rouletteEnter(o.acc);
            }
            case "roulettePreview": {
                // 预览物品
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_皇宫探宝);
                RoulettePreview o = new Gson().fromJson(msg, RoulettePreview.class);
                return roleActivityRouletteAction.roulettePreview(o.acc, o.id);
            }
            case "rouletteOp": {
                // 探宝
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_皇宫探宝);
                RouletteOp o = new Gson().fromJson(msg, RouletteOp.class);
                return roleActivityRouletteAction.rouletteOp(o.acc, o.num);
            }
            case "rouletteGetCredit": {
                // 领取积分奖
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_皇宫探宝);
                RouletteGetCredit o = new Gson().fromJson(msg, RouletteGetCredit.class);
                return roleActivityRouletteAction.rouletteGetCredit(o.acc, o.index);
            }
            case "enterLimitShop": {
                // 进入限时商店
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_LIMITSHOP);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityAction.enterLimitShop(o.acc);
            }
            case "exchangeLimitGoods": {
                // 限时商店兑换
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_LIMITSHOP);
                ExchangeLimitGoods o = new Gson().fromJson(msg, ExchangeLimitGoods.class);
                return roleActivityAction.exchangeLimitGoods(o.acc, o.id, o.count);
            }
            // 迷宫寻宝
            case "mazeEnter": {
                // 进入活动，页面状态
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_MAZE);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityMazeAction.mazeEnter(o.acc);
            }
            case "mazeRefresh": {
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_MAZE);
                Base o = new Gson().fromJson(msg, Base.class);
                return roleActivityMazeAction.mazeRefresh(o.acc);
            }
            case "mazeDig": {
                // 挖宝
                SystemApplication.Instance().cacheManager().activityVerifyException(ActivityConfig.ACTIVITY_MAZE);
                MazeDig o = new Gson().fromJson(msg, MazeDig.class);
                return roleActivityMazeAction.mazeDig(o.acc, o.type);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class Guesschoose extends Base {
        public int pos = 0;
    }

    class Guessbuy extends Base {
        public int pos = 0;
    }

    class HappyGet extends Base {
        public int day = 0;
    }

    class MonthSignGet extends Base {
        public int day = 0;
        public int month = 0;
    }

    class InvestPlanGet extends Base {
        public int lv = 0;
    }

    class LimitDraw extends Base {
        public String isFree = "";
    }

    class RoulettePreview extends Base {
        public int id = 0;
    }

    class RouletteOp extends Base {
        public int num = 0;
    }

    class RouletteGetCredit extends Base {
        public int index = 0;
    }

    class ExchangeLimitGoods extends Base {
        public int id = 0;
        public int count = 0;
    }

    class MazeDig extends Base {
        public int type = 0;
    }
}