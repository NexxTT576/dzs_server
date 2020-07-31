package com.mdy.dzs.net.controller;

import java.util.List;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.FurnaceAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class FurnaceController extends Controller {
    protected FurnaceAction furnaceAction = Container.get().createRemote(FurnaceAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "flist": {
                Base o = new Gson().fromJson(msg, Base.class);
                return furnaceAction.queryFurnaceList(o.acc);
            }
            case "furnace": {
                Furnace o = new Gson().fromJson(msg, Furnace.class);
                return furnaceAction.furnace(o.acc, o.type, o.ids);
            }
            case "reborn": {
                Reborn o = new Gson().fromJson(msg, Reborn.class);
                return furnaceAction.reborn(o.acc, o.type, o.id);
            }

        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class Furnace extends Base {
        public List<Integer> ids;
        public int type = 0;
    }

    class Reborn extends Base {
        public int id = 0;
        public int type = 0;
    }
}