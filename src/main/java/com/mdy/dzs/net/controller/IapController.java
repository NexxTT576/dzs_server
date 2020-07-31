package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.IapAction;
import com.mdy.dzs.game.domain.vip.VipInfoVO;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class IapController extends Controller {
    protected IapAction iapAction = Container.get().createRemote(IapAction.class, DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "main": {
                Base o = new Gson().fromJson(msg, Base.class);
                VipInfoVO vo = iapAction.iap(o.acc);
                vo.setExtend(cacheManager().getOpenData());
                return vo;
            }
            // vip等级礼包领取-界面
            case "vipLvGiftList": {
                Base o = new Gson().fromJson(msg, Base.class);
                return iapAction.vipLvGiftList(o.acc);
            }
            // vip等级礼包领取
            case "vipLvGiftGet": {
                VipLvGiftGet o = new Gson().fromJson(msg, VipLvGiftGet.class);
                return iapAction.vipLvGiftGet(o.acc, o.vipLv);
            }
            // vip每日福利-界面
            case "vipDayGift": {
                Base o = new Gson().fromJson(msg, Base.class);
                return iapAction.vipDayGift(o.acc);
            }
            // 每日福利领取
            case "getVipDayGift": {
                Base o = new Gson().fromJson(msg, Base.class);
                return iapAction.getVipDayGift(o.acc);
            }

        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class VipLvGiftGet extends Base {
        public int vipLv = 0;
    }

}