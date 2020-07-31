package com.mdy.dzs.net.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.ChallengeBattleAction;
import com.mdy.dzs.game.domain.swordfight.SwordCard;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;
import com.mdy.sharp.util.JSONUtil;

public class ChallengeBattleController extends Controller {
    protected ChallengeBattleAction challengeBattleAction = Container.get().createRemote(ChallengeBattleAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    @SuppressWarnings("unchecked")
    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "elite": {
                Base o = new Gson().fromJson(msg, Base.class);
                return challengeBattleAction.queryEliteBattleStatus(o.acc);
            }
            case "buyElite": {
                Base o = new Gson().fromJson(msg, Base.class);
                return challengeBattleAction.buyEliteBattleCnt(o.acc);
            }
            case "elitePve": {
                ElitePve o = new Gson().fromJson(msg, ElitePve.class);
                return challengeBattleAction.eliteBattle(o.acc, o.id, o.npc, o.revive != 0, o.clear != 0);
            }
            case "actPve": {
                ActPve o = new Gson().fromJson(msg, ActPve.class);
                if (o.aid == 1) {
                    return challengeBattleAction.activityBattle(o.acc, o.aid, o.npc, 0, 0, null);
                } else {
                    List<SwordCard> list = new ArrayList<SwordCard>();
                    List<List<Integer>> res = JSONUtil.fromJson(o.fmt, List.class);
                    for (int i = 0; i < res.size(); i++) {
                        List<Integer> one = res.get(i);
                        SwordCard rc = new SwordCard();
                        rc.setOrder(i + 1);
                        rc.setId(one.get(0));
                        rc.setPos(one.get(1));
                        list.add(rc);
                    }
                    return challengeBattleAction.activityBattle(o.acc, o.aid, o.npc, o.sysId, o.npcLv, list);
                }
            }
            case "actBuy": {
                ActBuy o = new Gson().fromJson(msg, ActBuy.class);
                return challengeBattleAction.buyActivityBattleCnt(o.acc, o.aid);
            }

            case "actPveState": {
                Base o = new Gson().fromJson(msg, Base.class);
                return challengeBattleAction.queryActivityBattleStatus(o.acc);
            }

            case "actDetail": {
                ActDetail o = new Gson().fromJson(msg, ActDetail.class);
                return challengeBattleAction.actDetail(o.acc, o.aid, o.sysId);
            }

            case "save": {
                Save o = new Gson().fromJson(msg, Save.class);
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
                return challengeBattleAction.save(o.acc, o.sysId, list);
            }

            case "check": {
                Check o = new Gson().fromJson(msg, Check.class);
                return challengeBattleAction.check(o.acc, o.aid);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class ElitePve extends Base {
        public int id = 0;
        public int npc = 0;
        public int revive = 0;
        public int clear = 0;
    }

    class ActPve extends Base {
        public int aid = 0;
        public int npc = 0;
        public int sysId = 0;
        public int npcLv = 0;
        public String fmt = "";
    }

    class ActBuy extends Base {
        public int aid = 0;
    }

    class ActDetail extends Base {
        public int aid = 0;
        public int sysId = 0;
    }

    class Save extends Base {
        public int sysId = 0;
        public String fmt = "";
    }

    class Check extends Base {
        public int aid = 0;
    }
}