package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.RoleRankAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class RoleRankController extends Controller {
    protected RoleRankAction roleRankAction = Container.get().createRemote(RoleRankAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "list": {
                Base o = new Gson().fromJson(msg, Base.class);
                return roleRankAction.getRoleRankInfo(o.acc, o.type);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
        public int type = 0;
    }

}