package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.RoleChannelAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class RoleChannelController extends Controller {
    protected RoleChannelAction roleChannelAction = Container.get().createRemote(RoleChannelAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "main": {
                Base o = new Gson().fromJson(msg, Base.class);
                return roleChannelAction.queryRoleChannelInfo(o.acc);
            }
            case "lvUp": {
                BaseT o = new Gson().fromJson(msg, BaseT.class);
                return roleChannelAction.levelUp(o.acc, o.t);
            }
            case "reset": {
                Base o = new Gson().fromJson(msg, Base.class);
                return roleChannelAction.reset(o.acc);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class BaseT extends Base {
        public int t = 0;
    }
}