package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.ShopAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class ShopController extends Controller {
    protected ShopAction shopAction = Container.get().createRemote(ShopAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "stat": {
                Base o = new Gson().fromJson(msg, Base.class);
                return shopAction.wineShopState(o.acc);
            }
            case "wine": {
                Wine o = new Gson().fromJson(msg, Wine.class);
                return shopAction.wineShop(o.acc, o.t, o.n);
            }
            case "list": {
                Base o = new Gson().fromJson(msg, Base.class);
                return shopAction.queryShopList(o.acc);
            }
            case "buy": {
                Buy o = new Gson().fromJson(msg, Buy.class);
                return shopAction.buyShopItem(o.acc, o.id, o.n, o.coinType, o.coin, o.auto);
            }
            case "oList": {
                OList o = new Gson().fromJson(msg, OList.class);
                return shopAction.queryShopItem(o.acc, o.id);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class Wine extends Base {
        public int t = 0;
        public int n = 0;
    }

    class Buy extends Base {
        public int coin = 0;
        public int coinType = 0;
        public int n = 0;
        public int id = 0;
        public int auto = 0;
    }

    class OList extends Base {
        public int id = 0;
    }
}