package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.HandbookAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class HandbookController extends Controller {
    protected HandbookAction handbookAction = Container.get().createRemote(HandbookAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "getAll": {
                Base o = new Gson().fromJson(msg, Base.class);
                return handbookAction.getAll(o.acc);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }
}