package com.mdy.dzs.net.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.RoleEquipAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class EquipController extends Controller {
    protected RoleEquipAction roleEquipAction = Container.get().createRemote(RoleEquipAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "sell": {
                Sell o = new Gson().fromJson(msg, Sell.class);
                String scids[] = o.eids.split(",");
                List<Integer> idAry = new ArrayList<Integer>();
                for (int i = 0; i < scids.length; i++) {
                    idAry.add(Integer.parseInt(scids[i]));
                }
                return roleEquipAction.sellEquip(o.acc, idAry);
            }
            case "lvUp": {
                LvUp o = new Gson().fromJson(msg, LvUp.class);
                return roleEquipAction.levelUp(o.acc, o.id, o.auto);
            }
            case "propState": {
                PropState o = new Gson().fromJson(msg, PropState.class);
                return roleEquipAction.queryPropState(o.acc, o.id);
            }
            case "prop": {
                Prop o = new Gson().fromJson(msg, Prop.class);
                return roleEquipAction.changeProp(o.acc, o.id, o.t, o.n);
            }
            case "propRepl": {
                PropRepl o = new Gson().fromJson(msg, PropRepl.class);
                return roleEquipAction.replaceProp(o.acc, o.id);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class Sell extends Base {
        public String eids = "";
    }

    class LvUp extends Base {
        public int id = 0;
        public int auto = 0;
    }

    class PropState extends Base {
        public int id = 0;
    }

    class Prop extends Base {
        public int t = 0;
        public int n = 0;
        public int id = 0;
    }

    class PropRepl extends Base {
        public int id = 0;
    }
}