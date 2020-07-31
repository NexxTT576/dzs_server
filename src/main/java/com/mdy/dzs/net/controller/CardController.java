package com.mdy.dzs.net.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.RoleAction;
import com.mdy.dzs.game.action.RoleCardAction;
import com.mdy.dzs.game.domain.card.RoleCardListVO;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class CardController extends Controller {
    protected RoleAction roleAction = Container.get().createRemote(RoleAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);
    protected RoleCardAction roleCardAction = Container.get().createRemote(RoleCardAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "uinfo": {
                Base o = new Gson().fromJson(msg, Base.class);
                return roleAction.uinfo(o.acc);
            }
            case "sell": {
                Sell o = new Gson().fromJson(msg, Sell.class);
                String scids[] = o.cids.split(",");
                List<Integer> idAry = new ArrayList<Integer>();
                for (int i = 0; i < scids.length; i++) {
                    idAry.add(Integer.parseInt(scids[i]));
                }
                return roleCardAction.sellCard(o.acc, idAry);
            }
            case "msg": {
                Msg o = new Gson().fromJson(msg, Msg.class);
                return roleCardAction.msg(o.acc, o.cid);
            }
            case "list": {
                Base o = new Gson().fromJson(msg, Base.class);
                RoleCardListVO info = roleCardAction.queryList(o.acc);
                return Arrays.asList(info.getList(), info.getLimit());
            }
            case "clsUp": {
                ClsUp o = new Gson().fromJson(msg, ClsUp.class);
                return roleCardAction.clsUp(o.acc, o.id, o.op);
            }
            case "shenUp": {
                ShenUp o = new Gson().fromJson(msg, ShenUp.class);
                return roleCardAction.shenUp(o.acc, o.id, o.ind);
            }
            case "shenReset": {
                ShenReset o = new Gson().fromJson(msg, ShenReset.class);
                return roleCardAction.shenReset(o.acc, o.id);
            }
            case "lvUp": {
                LvUp o = new Gson().fromJson(msg, LvUp.class);
                String acc = o.acc;
                String cids = o.cids;
                int op = o.op;
                String scids[] = cids.split(",");
                List<Integer> idAry = new ArrayList<Integer>();
                for (int i = 0; i < scids.length; i++) {
                    idAry.add(Integer.parseInt(scids[i]));
                }
                return roleCardAction.levelUp(acc, idAry, op);
            }
            case "soulUp": {
                SoulUp o = new Gson().fromJson(msg, SoulUp.class);
                return roleCardAction.soulUp(o.acc, o.id, o.op, o.n);
            }
            case "lock": {
                Lock o = new Gson().fromJson(msg, Lock.class);
                roleCardAction.lockCard(o.acc, o.id, o.lock);
                return null;
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class Sell {
        public String acc = "";
        public String cids = "";
    }

    class Msg {
        public String acc = "";
        public int cid = 0;
    }

    class ClsUp {
        public String acc = "";
        public int id = 0;
        public int op = 0;
    }

    class ShenUp {
        public String acc = "";
        public int id = 0;
        public int ind = 0;
    }

    class ShenReset {
        public String acc = "";
        public int id = 0;
    }

    class LvUp {
        public String acc = "";
        public String cids = "";
        public int op = 0;
    }

    class SoulUp {
        public String acc = "";
        public int op = 0;
        public int n = 0;
        public int id = 0;
    }

    class Lock {
        public String acc = "";
        public int lock = 0;
        public int id = 0;
    }
}