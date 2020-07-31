package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.LineupAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class LineupController extends Controller {

    private LineupAction lineupAction = Container.get().createRemote(LineupAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "list": {
                LineupList o = new Gson().fromJson(msg, LineupList.class);
                return lineupAction.seeLineup(o.acc, o.pos);
            }
            case "embattle": {
                Embattle o = new Gson().fromJson(msg, Embattle.class);
                return lineupAction.changeLineup(o.acc, o.pos, o.subpos, o.id);
            }
            case "fastChangeEquip": {
                FastChangeEquip o = new Gson().fromJson(msg, FastChangeEquip.class);
                return lineupAction.fastChangeEquip(o.acc, o.pos, o.cardId, o.type);
            }
            case "verify": {
                Base o = new Gson().fromJson(msg, Base.class);
                return lineupAction.verifyLineup(o.acc);
            }
        }
        return null;
    }

    class LineupList {
        public String acc = "";
        public int pos = 0;
    }

    class Embattle {
        public String acc = "";
        public int pos = 0;
        public int subpos = 0;
        public int id = 0;
    }

    class FastChangeEquip {
        public String acc = "";
        public int pos = 0;
        public int cardId = 0;
        public int type = 0;
    }

    class Base {
        public String acc = "";
    }
}