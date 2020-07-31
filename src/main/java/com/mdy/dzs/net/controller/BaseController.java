package com.mdy.dzs.net.controller;

import java.util.HashMap;

import com.google.gson.Gson;
import com.mdy.sharp.container.biz.BizException;

public class BaseController extends Controller {
    private static UserController userController = new UserController();
    private static LineupController lineupController = new LineupController();
    private static CardController cardController = new CardController();
    private static PacketController packetController = new PacketController();
    private static BattleController battleController = new BattleController();
    private static HelpController helpController = new HelpController();
    private static BroadController broadController = new BroadController();
    private static ShopController shopController = new ShopController();
    private static IapController iapController = new IapController();
    private static EquipController equipController = new EquipController();
    private static RoadController roadController = new RoadController();
    private static ChallengeBattleController challengeBattleController = new ChallengeBattleController();
    private static SwordfightController swordfightController = new SwordfightController();
    private static RoleChannelController roleChannelController = new RoleChannelController();
    private static RoleYuanController roleYuanController = new RoleYuanController();
    private static FurnaceController furnaceController = new FurnaceController();
    private static ActivityController activityController = new ActivityController();
    private static ShenMiController shenMiController = new ShenMiController();
    private static UnionController unionController = new UnionController();
    private static ChatController chatController = new ChatController();
    private static RoleRankController rankController = new RoleRankController();
    private static FriendController friendController = new FriendController();
    private static HandbookController handbookController = new HandbookController();
    private static GiftController giftController = new GiftController();
    private static MailController mailController = new MailController();
    private static MissionController missionController = new MissionController();

    public void runAction(final String msg, HashMap<String, Object> flushData) {
        Base o = new Gson().fromJson(msg, Base.class);
        Object r = null;
        try {
            switch (o.m) {
                case "usr":
                    r = userController.runAction(o.a, msg);
                    break;
                case "fmt":
                    r = lineupController.runAction(o.a, msg);
                    break;
                case "card":
                    r = cardController.runAction(o.a, msg);
                    break;
                case "packet":
                    r = packetController.runAction(o.a, msg);
                    break;
                case "battle":
                    r = battleController.runAction(o.a, msg);
                    break;
                case "help":
                    r = helpController.runAction(o.a, msg);
                    break;
                case "broad":
                    r = broadController.runAction(o.a, msg);
                    break;
                case "shop":
                    r = shopController.runAction(o.a, msg);
                    break;
                case "iap":
                    r = iapController.runAction(o.a, msg);
                    break;
                case "equip":
                    r = equipController.runAction(o.a, msg);
                    break;
                case "road":
                    r = roadController.runAction(o.a, msg);
                    break;
                case "actbattle":
                    r = challengeBattleController.runAction(o.a, msg);
                    break;
                case "swordfight":
                    r = swordfightController.runAction(o.a, msg);
                    break;
                case "channel":
                    r = roleChannelController.runAction(o.a, msg);
                    break;
                case "yuan":
                    r = roleYuanController.runAction(o.a, msg);
                    break;
                case "furnace":
                    r = furnaceController.runAction(o.a, msg);
                    break;
                case "activity":
                    r = activityController.runAction(o.a, msg);
                    break;
                case "shenmi":
                    r = shenMiController.runAction(o.a, msg);
                    break;
                case "union":
                    r = unionController.runAction(o.a, msg);
                    break;
                case "chat":
                    r = chatController.runAction(o.a, msg);
                    break;
                case "rank":
                    r = rankController.runAction(o.a, msg);
                    break;
                case "friend":
                    r = friendController.runAction(o.a, msg);
                    break;
                case "handbook":
                    r = handbookController.runAction(o.a, msg);
                    break;
                case "gift":
                case "logingift":
                    r = giftController.runAction(o.a, msg);
                    break;
                case "mail":
                    r = mailController.runAction(o.a, msg);
                    break;
                case "mission":
                    r = missionController.runAction(o.a, msg);
                    break;
            }
            flushData.put("code", 0);
            flushData.put("body", r);
        } catch (BizException e) {
            flushData.put("code", e.getCode());
            flushData.put("msg", e.getMessage());
        }
    }

    class Base {
        public String a = "";
        public String m = "";
    }
}