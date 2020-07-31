package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.RoadAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class RoadController extends Controller {
    protected RoadAction roadAction = Container.get().createRemote(RoadAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "list": {
                Base o = new Gson().fromJson(msg, Base.class);
                return roadAction.queryRoadInfo(o.acc);
            }
            case "use": {
                Use o = new Gson().fromJson(msg, Use.class);
                return roadAction.useGift(o.acc, o.cardId, o.multi, o.itemId);
            }
            default:
                break;
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class Use extends Base {
        public int cardId = 0;
        public int multi = 0;
        public int itemId = 0;
    }
}