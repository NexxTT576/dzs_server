package com.mdy.dzs.net.controller;

import java.util.List;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.RoleYuanAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class RoleYuanController extends Controller {
    protected RoleYuanAction roleYuanAction = Container.get().createRemote(RoleYuanAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "lvUp": {
                LvUp o = new Gson().fromJson(msg, LvUp.class);
                // List<Integer> idAry = JSONUtil.fromJson(o.ids, List.class);
                return roleYuanAction.levelUp(o.acc, o.id, o.ids);
            }
            case "collect": {
                Collect o = new Gson().fromJson(msg, Collect.class);
                return roleYuanAction.collect(o.acc, o.t);
            }
            case "useItem": {
                Base o = new Gson().fromJson(msg, Base.class);
                return roleYuanAction.addCollectLV(o.acc);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class LvUp extends Base {
        public List<Integer> ids;
        public int id = 0;
    }

    class Collect extends Base {
        public int t = 0;
    }
}