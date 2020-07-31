package com.mdy.dzs.net.controller;

import com.mdy.dzs.game.SystemApplication;
import com.mdy.dzs.game.manager.CacheManager;

public class Controller {
    private SystemApplication application() {
        return SystemApplication.Instance();
    }

    public CacheManager cacheManager() {
        return application().cacheManager();
    }
}