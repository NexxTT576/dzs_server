package com.mdy.dzs.net.controller;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.AccountAction;
import com.mdy.dzs.data.domain.account.Account;
import com.mdy.dzs.data.exception.AccountException;
import com.mdy.dzs.game.action.ChatAction;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class ChatController extends Controller {
    protected static ChatAction chatAction = Container.get().createRemote(ChatAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);
    protected static AccountAction accountAction = Container.get().createRemote(AccountAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);

    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "unread": {
                Unread o = new Gson().fromJson(msg, Unread.class);
                return chatAction.queryUnReadNum(o.acc, o.type);
            }
            case "list": {
                BList o = new Gson().fromJson(msg, BList.class);
                return chatAction.quertChatList(o.acc, o.type, o.para);
            }
            case "send": {
                Send o = new Gson().fromJson(msg, Send.class);
                checkChatStatus(o.acc);
                chatAction.sendChat(o.acc, o.recname, o.type, o.msg, o.para1, o.para2, o.para3);
                return null;
            }
        }
        return null;
    }

    public void checkChatStatus(String acc) throws BizException {
        Account account = accountAction.queryAccount(acc);
        if (account != null && account.getStatus() == Account.STATUS_DISABLE_SENDMSG) {
            throw AccountException.getException(AccountException.EXCE_STATUS_DISABLE_SENDMSG, acc);
        }
    }

    class Base {
        public String acc = "";
    }

    class Unread extends Base {
        public int type = 0;
    }

    class BList extends Base {
        public int type = 0;
        public String para = "";
    }

    class Send extends Base {
        public int type = 0;
        public String msg = "";
        public String recname = "";
        public String para1 = "";
        public String para2 = "";
        public String para3 = "";
    }
}