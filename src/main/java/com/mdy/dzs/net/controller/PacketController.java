package com.mdy.dzs.net.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.PacketAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class PacketController extends Controller {
    protected PacketAction packetAction = Container.get().createRemote(PacketAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "list": {
                Base o = new Gson().fromJson(msg, Base.class);
                return packetAction.list(o.acc, o.t);
            }
            case "gmAdd": {
                BaseGmAdd o = new Gson().fromJson(msg, BaseGmAdd.class);
                packetAction.gmAdd(o.acc, o.t, o.id, o.n);
                return null;
            }
            case "use": {
                BaseUse o = new Gson().fromJson(msg, BaseUse.class);
                return packetAction.use(o.acc, o.id, o.num);
            }

            case "extend": {
                BaseExtend o = new Gson().fromJson(msg, BaseExtend.class);
                return packetAction.extend(o.acc, o.type);
            }
            case "sell": {
                BaseSell o = new Gson().fromJson(msg, BaseSell.class);
                String datas[] = o.ids.split(",");
                List<Integer> idAry = new ArrayList<Integer>();
                for (int i = 0; i < datas.length; i++) {
                    idAry.add(Integer.parseInt(datas[i]));
                }
                return packetAction.sell(o.acc, idAry);
            }
            default:
                break;
        }
        return null;
    }

    class Base {
        public String acc = "";
        public int t = 0;
    }

    class BaseGmAdd {
        public String acc = "";
        public int t = 0;
        public int id = 0;
        public int n = 0;
    }

    class BaseUse {
        public String acc = "";
        public int id = 0;
        public int num = 0;
    }

    class BaseExtend {
        public String acc = "";
        public int type = 0;
    }

    class BaseSell {
        public String acc = "";
        public String ids = "";
    }
}