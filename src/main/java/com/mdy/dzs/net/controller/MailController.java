package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.MailAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class MailController extends Controller {
    protected MailAction mailAction = Container.get().createRemote(MailAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "mlist": {
                Base o = new Gson().fromJson(msg, Base.class);
                return mailAction.mlist(o.acc, o.mailId, o.type);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
        public int mailId = 0;
        public int type = 0;
    }
}