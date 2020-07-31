package com.mdy.dzs.net.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.BattleAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class BattleController extends Controller {
    private BattleAction battleAction = Container.get().createRemote(BattleAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "world": {
                Base o = new Gson().fromJson(msg, Base.class);
                return battleAction.queryWorldInfo(o.acc, o.id);
            }
            case "field": {
                Base o = new Gson().fromJson(msg, Base.class);
                return battleAction.queryFieldInfo(o.acc, o.id);
            }
            case "pve": {
                PVE o = new Gson().fromJson(msg, PVE.class);
                return battleAction.battle(o.acc, o.id, o.type);
            }
            case "cdClear": {
                CDClear o = new Gson().fromJson(msg, CDClear.class);
                Map<String, Integer> mo = new HashMap<String, Integer>();
                mo.put("gold", battleAction.clearCD(o.acc, o.t));
                return mo;
            }
            case "award": {
                PVE o = new Gson().fromJson(msg, PVE.class);
                return battleAction.award(o.acc, o.id, o.type);
            }
            case "pves": {
                PVES o = new Gson().fromJson(msg, PVES.class);
                return battleAction.battles(o.acc, o.id, o.type, o.n);
            }
            case "batBuy": {
                BatBuy o = new Gson().fromJson(msg, BatBuy.class);
                return battleAction.buyBattleCnt(o.acc, o.id, o.act);
            }
            default:
                break;
        }
        // switch (a) {
        // case "world": {
        // Base o = new Gson().fromJson(m, Base.class);
        // return battleAction.queryWorldInfo(acc, o.id);
        // }
        // case "field": {
        // Base o = new Gson().fromJson(m, Base.class);
        // return battleAction.queryFieldInfo(acc, o.id);
        // }
        // case "pve": {
        // PVE o = new Gson().fromJson(m, PVE.class);
        // return battleAction.battle(acc, o.id, o.type);
        // }
        // case "cdClear": {
        // CDClear o = new Gson().fromJson(m, CDClear.class);
        // Map<String, Integer> mo = new HashMap<String, Integer>();
        // mo.put("gold", battleAction.clearCD(acc, o.t));
        // return mo;
        // }
        // case "award": {
        // PVE o = new Gson().fromJson(m, PVE.class);
        // return battleAction.award(acc, o.id, o.type);
        // }
        // case "pves": {
        // PVES o = new Gson().fromJson(m, PVES.class);
        // return battleAction.battles(acc, o.id, o.type, o.n);
        // }
        // case "batBuy": {
        // BatBuy o = new Gson().fromJson(m, BatBuy.class);
        // return battleAction.buyBattleCnt(acc, o.id, o.act);
        // }
        // }
        return null;
    }

    class Base {
        public int id = 0;
        public String acc = "";
    }

    class PVE {
        public String acc = "";
        public int id = 0;
        public int type = 0;
    }

    class CDClear {
        public String acc = "";
        public int t = 0;
    }

    class PVES {
        public String acc = "";
        public int id = 0;
        public int type = 0;
        public int n = 0;
    }

    class BatBuy {
        public String acc = "";
        public int id = 0;
        public int act = 0;
    }
}