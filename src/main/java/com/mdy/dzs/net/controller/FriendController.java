package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.game.action.FriendAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class FriendController extends Controller {
    protected FriendAction friendAction = Container.get().createRemote(FriendAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "getFriendList": {
                // 好友列表
                Base o = new Gson().fromJson(msg, Base.class);
                return friendAction.getFriendList(o.acc);
            }
            case "removeFriend": {
                // 断交
                RemoveFriend o = new Gson().fromJson(msg, RemoveFriend.class);
                return friendAction.removeFriend(o.acc, o.account);
            }
            case "recommendList": {
                // 推荐好友
                RecommendList o = new Gson().fromJson(msg, RecommendList.class);
                return friendAction.recommendList(o.acc, o.num);
            }
            case "applyFriend": {
                // 邀请好友
                ApplyFriend o = new Gson().fromJson(msg, ApplyFriend.class);
                return friendAction.applyFriend(o.acc, o.account, o.content);
            }
            case "searchFriend": {
                // 搜索好友
                SearchFriend o = new Gson().fromJson(msg, SearchFriend.class);
                return friendAction.searchFriend(o.acc, o.type, o.content, o.searchNum, o.flag);
            }
            case "getNaili": {
                // 领取某好友的耐力丹
                GetNaili o = new Gson().fromJson(msg, GetNaili.class);
                return friendAction.getNaili(o.acc, o.account);
            }
            case "getNailiAll": {
                // 领取全部的耐力丹
                Base o = new Gson().fromJson(msg, Base.class);
                return friendAction.getNailiAll(o.acc);
            }
            case "acceptFriend": {
                // 同意某人的好友邀请
                GetNaili o = new Gson().fromJson(msg, GetNaili.class);
                return friendAction.acceptFriend(o.acc, o.account);
            }
            case "rejectFriend": {
                // 拒绝某人的好友邀请
                GetNaili o = new Gson().fromJson(msg, GetNaili.class);
                return friendAction.rejectFriend(o.acc, o.account);
            }
            case "acceptAll": {
                // 同意所有好友申请
                Base o = new Gson().fromJson(msg, Base.class);
                return friendAction.acceptAll(o.acc);
            }
            case "rejectAll": {
                // 拒绝所有好友申请
                Base o = new Gson().fromJson(msg, Base.class);
                return friendAction.rejectAll(o.acc);
            }
            case "sendNaili": {
                // 送某人耐力丹
                GetNaili o = new Gson().fromJson(msg, GetNaili.class);
                return friendAction.sendNaili(o.acc, o.account);
            }
            case "updateChatList": {
                // 请求聊天在线相关数据,这个接口5秒钟调用一次
                Base o = new Gson().fromJson(msg, Base.class);
                return friendAction.updateChatList(o.acc);
            }
        }
        return null;
    }

    class Base {
        public String acc = "";
    }

    class RemoveFriend extends Base {
        public String account = "";
    }

    class RecommendList extends Base {
        public int num = 0;
    }

    class ApplyFriend extends Base {
        public String account = "";
        public String content = "";
    }

    class SearchFriend extends Base {
        public int type = 0;
        public String content = "";
        public int searchNum = 0;
        public int flag = 0;
    }

    class GetNaili extends Base {
        public String account = "";
    }
}