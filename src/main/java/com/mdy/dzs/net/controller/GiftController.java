package com.mdy.dzs.net.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.game.action.GiftCenterAction;
import com.mdy.dzs.game.domain.giftcenter.RoleGift;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class GiftController extends Controller {
    private Map<Integer, Integer> cdkeyErrors;
    protected GiftCenterAction giftCenterAction = Container.get().createRemote(GiftCenterAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public GiftController() {
        cdkeyErrors = new HashMap<Integer, Integer>();
        cdkeyErrors.put(5, 1305);
        cdkeyErrors.put(4, 1306);
        cdkeyErrors.put(3, 1307);
        cdkeyErrors.put(1, 1308);
        cdkeyErrors.put(6, 1309);
        cdkeyErrors.put(7, 1310);
        cdkeyErrors.put(2, 1311);
        cdkeyErrors.put(-1, 1301);
    }

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "cList": {
                Base o = new Gson().fromJson(msg, Base.class);
                return giftCenterAction.queryGiftCenterList(o.acc);
            }
            case "cGet": {
                BaseT o = new Gson().fromJson(msg, BaseT.class);
                Integer type = o.t;
                Integer giftId = 0;
                if (type == RoleGift.GIFT_GETTYPE_ONE) {
                    giftId = o.objId;
                }
                return giftCenterAction.getGift(o.acc, type, giftId);
            }
            case "lvCheck": {
                Base o = new Gson().fromJson(msg, Base.class);
                return giftCenterAction.queryLevelGiftList(o.acc);
            }
            case "lvGet": {
                LvGet o = new Gson().fromJson(msg, LvGet.class);
                return giftCenterAction.getLevelGift(o.acc, o.lv);
            }
            case "onLineCheck": {
                Base o = new Gson().fromJson(msg, Base.class);
                return giftCenterAction.queryOnLineGiftList(o.acc);
            }
            case "onLineGet": {
                Base o = new Gson().fromJson(msg, Base.class);
                return giftCenterAction.getOnLineGift(o.acc);
            }
            case "signCheck": {
                Base o = new Gson().fromJson(msg, Base.class);
                return giftCenterAction.querySignGiftList(o.acc);
            }
            case "signGet": {
                SignGet o = new Gson().fromJson(msg, SignGet.class);
                return giftCenterAction.getSignGift(o.acc, o.day);
            }
            case "loginCheck": {
                Base o = new Gson().fromJson(msg, Base.class);
                return giftCenterAction.queryLoginDayGiftList(o.acc);
            }
            case "getGift": {
                GetGift o = new Gson().fromJson(msg, GetGift.class);
                return giftCenterAction.getLoginDayGift(o.acc, o.day);
            }
            case "cdkey": {
                // GetCdkeyGift o = new Gson().fromJson(msg, GetCdkeyGift.class);
                // List<GiftItem> giftItems = getCDKeyGift(o.acc, o.cdkey,
                // Container.get().getServerName(), o.pfid);
                // List<Integer> goldAndSilver = giftCenterAction.sendCDKeyGift(acc, cdkey,
                // giftItems);

                // List<ProbItem> res = new ArrayList<ProbItem>();
                // for (GiftItem giftItem : giftItems) {
                // res.add(new ProbItem(giftItem.getType(), giftItem.getId(),
                // giftItem.getNum()));
                // }
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class BaseT extends Base {
        public int t = 0;
        public int objId = 0;
    }

    class LvGet extends Base {
        public int lv = 0;
    }

    class SignGet extends Base {
        public int day = 0;
    }

    class GetGift extends Base {
        public int day = 0;
    }

    class GetCdkeyGift extends Base {
        public String cdkey = "";
        public String pfid = "";
    }
}