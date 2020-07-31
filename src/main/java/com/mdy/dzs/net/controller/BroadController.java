package com.mdy.dzs.net.controller;

import com.mdy.dzs.game.SystemApplication;
import com.mdy.sharp.container.biz.BizException;

public class BroadController extends Controller {
    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "list": {
                return SystemApplication.Instance().cacheManager().getGuangBoList();
            }
        }
        return null;
    }
}