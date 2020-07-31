package com.mdy.dzs.net.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.SwordfightAction;
import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;
import com.mdy.sharp.util.JSONUtil;

public class SwordfightController extends Controller {
    protected SwordfightAction swordFightAction = Container.get().createRemote(SwordfightAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    @SuppressWarnings("unchecked")
    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "enterSword": {
                // 进入论剑系统
                Base o = new Gson().fromJson(msg, Base.class);
                return swordFightAction.enterSword(o.acc);
            }
            case "reset": {
                // 重置敌人
                Reset o = new Gson().fromJson(msg, Reset.class);
                return swordFightAction.reset(o.acc, o.gold);
            }
            case "award": {
                // 领取奖励
                Award o = new Gson().fromJson(msg, Award.class);
                return swordFightAction.award(o.acc, o.floor);
            }
            case "fight": {
                // 挑战敌人
                Fight o = new Gson().fromJson(msg, Fight.class);
                List<SwordCard> list = new ArrayList<SwordCard>();
                List<List<Integer>> res = JSONUtil.fromJson(o.fmt, List.class);
                for (int i = 0; i < res.size(); i++) {
                    List<Integer> one = res.get(i);
                    SwordCard sc = new SwordCard();
                    sc.setOrder(i + 1);
                    sc.setId(one.get(0));
                    sc.setPos(one.get(1));
                    list.add(sc);
                }
                return swordFightAction.fight(o.acc, o.floor, list);
            }
            case "combat": {
                // 获得战斗力
                Combat o = new Gson().fromJson(msg, Combat.class);
                List<SwordCard> list = new ArrayList<SwordCard>();
                List<List<Integer>> res = JSONUtil.fromJson(o.fmt, List.class);
                for (int i = 0; i < res.size(); i++) {
                    List<Integer> one = res.get(i);
                    SwordCard sc = new SwordCard();
                    sc.setOrder(i + 1);
                    sc.setId(one.get(0));
                    sc.setPos(one.get(1));
                    list.add(sc);
                }
                return swordFightAction.combat(o.acc, list);
            }
            default:
                break;
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class Reset extends Base {
        public int gold = 0;
    }

    class Award extends Base {
        public int floor = 0;
    }

    class Fight extends Base {
        public int floor = 0;
        public String fmt = "";
    }

    class Combat extends Base {
        public String fmt = "";
    }
}