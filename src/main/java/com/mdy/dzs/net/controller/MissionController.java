package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.SystemApplication;
import com.mdy.dzs.game.action.MissionAction;
import com.mdy.dzs.game.domain.system.SystemStatus;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class MissionController extends Controller {
    protected MissionAction missionAction = Container.get().createRemote(MissionAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "list": {
                SystemApplication.Instance().cacheManager().checkSystemOpenExcept(SystemStatus.SYSTEM_成就);
                Base o = new Gson().fromJson(msg, Base.class);
                return missionAction.queryMissionList(o.acc);
            }
            case "reward": {
                Reward o = new Gson().fromJson(msg, Reward.class);
                return missionAction.getMissionReward(o.acc, o.id);
            }
            case "dailyReward": {
                Reward o = new Gson().fromJson(msg, Reward.class);
                return missionAction.getDailyMissionReward(o.acc, o.id);
            }
            case "accept": {
                Base o = new Gson().fromJson(msg, Base.class);
                missionAction.AcceptBeStartMission(o.acc);
                return null;
            }

        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class Reward extends Base {
        public int id = 0;
    }
}