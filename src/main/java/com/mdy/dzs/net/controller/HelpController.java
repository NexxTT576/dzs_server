package com.mdy.dzs.net.controller;

import java.util.List;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.HelpAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class HelpController extends Controller {
    protected HelpAction helpAction = Container.get().createRemote(HelpAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "getGuide": {
                Base o = new Gson().fromJson(msg, Base.class);
                return helpAction.getGuide(o.acc);
            }
            case "setGuide": {
                SetData o = new Gson().fromJson(msg, SetData.class);
                helpAction.setGuide(o.acc, o.guide);
                return null;
            }
            case "getUserParam": {
                GetUserParam o = new Gson().fromJson(msg, GetUserParam.class);
                return helpAction.getUserParam(o.acc, o.type);
            }
            case "setUserParam": {
                SetUserParam o = new Gson().fromJson(msg, SetUserParam.class);
                helpAction.setUserParam(o.acc, o.type, o.param);
                return null;
            }
        }
        return null;
    }

    class SetData {
        public String acc = "";
        public int guide = 0;
    }

    class Base {
        public String acc = "";
    }

    class GetUserParam {
        public String acc = "";
        public String type = "";
    }

    class SetUserParam {
        public String acc = "";
        public String type = "";
        public List<Integer> param;
    }
}