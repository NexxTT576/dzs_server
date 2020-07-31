package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.ShenMiShopAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class ShenMiController extends Controller {
    protected ShenMiShopAction shenMiShopAction = Container.get().createRemote(ShenMiShopAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "list": {
                BList o = new Gson().fromJson(msg, BList.class);
                return shenMiShopAction.cmdList(o.acc, o.refresh);
            }
            case "exchange": {
                Exchange o = new Gson().fromJson(msg, Exchange.class);
                return shenMiShopAction.cmdExchange(o.acc, o.id);
            }
            case "verify": {
                Base o = new Gson().fromJson(msg, Base.class);
                return shenMiShopAction.cmdVerify(o.acc);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class BList extends Base {
        public int refresh = 0;
    }

    class Exchange extends Base {
        public String id = "";
    }
}