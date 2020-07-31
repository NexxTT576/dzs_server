package com.mdy.dzs.net.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.domain.notice.Notice;
import com.mdy.dzs.game.SystemApplication;
import com.mdy.dzs.game.action.RoleAction;
import com.mdy.dzs.game.domain.broad.BroadInfo;
import com.mdy.dzs.game.domain.role.RoleLoginInfoVO;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;

public class UserController extends Controller {
    protected RoleAction roleAction = Container.get().createRemote(RoleAction.class,
            DataApplication.CLUSTER_GAME_SYSTEM);
    protected static Map<String, Object> firstStory = null;

    @SuppressWarnings("unchecked")
    public Object runAction(String a, String msg) throws BizException {
        switch (a) {
            case "login": {
                Login o = new Gson().fromJson(msg, Login.class);
                return roleAction.roleLogin(o.id);
            }
            case "enter": {
                Base o = new Gson().fromJson(msg, Base.class);
                RoleLoginInfoVO info = roleAction.roleLogin(o.acc);
                HashMap<String, Object> res = new HashMap<String, Object>();
                res.put("0", "");
                res.put("1", info == null ? new HashMap<String, String>() : info);
                res.put("2", cacheManager().getNotices().size() == 0 ? null : cacheManager().getNotices());
                res.put("3", info == null ? 1 : 2);
                res.put("5", o.acc);
                if (info == null) {
                    if (firstStory == null) {

                        try {
                            // String p =
                            // UserController.class.getResourceAsStream().getResource("firstStory.json")
                            // .getPath();
                            // File filename = new File(p);
                            // InputStreamReader reader;
                            InputStreamReader reader = new InputStreamReader(
                                    UserController.class.getResourceAsStream("/firstStory.json"));
                            BufferedReader br = new BufferedReader(reader);
                            String line = "";
                            String lines = "";
                            line = br.readLine();
                            lines = line;
                            while (line != null) {
                                line = br.readLine();
                                if (line != null)
                                    lines += line;
                            }
                            firstStory = new Gson().fromJson(lines, Map.class);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                    res.put("6", firstStory.get("battle"));
                }
                res.put("open", cacheManager().getOpenData().size() == 0 ? null : cacheManager().getOpenData());
                return res;
            }
            case "heart": {
                Base o = new Gson().fromJson(msg, Base.class);
                Map<String, Object> roleRes = roleAction.heart(o.acc);
                Map<String, Object> res = new HashMap<String, Object>();
                List<BroadInfo> topAry = new ArrayList<BroadInfo>();
                List<BroadInfo> midAry = new ArrayList<BroadInfo>();
                Map<String, List<BroadInfo>> broadMap = new HashMap<String, List<BroadInfo>>();
                broadMap = (Map<String, List<BroadInfo>>) res.get("broadMap");
                if (broadMap != null && broadMap.get("topAry") != null) {
                    topAry = broadMap.get("topAry");
                }
                if (broadMap != null && broadMap.get("midAry") != null) {
                    midAry = broadMap.get("midAry");
                }
                res.put("0", "");
                res.put("1", topAry);
                res.put("2", midAry);
                res.put("3", roleRes.get("playerInfoAry"));
                res.put("4", roleRes.get("mailMap"));
                res.put("5", roleRes.get("otherObj"));
                return res;
            }
            case "reg": {
                Reg o = new Gson().fromJson(msg, Reg.class);
                RoleLoginInfoVO info = roleAction.register(o.acc, "", o.name, "test", o.rid, null, "default-server");
                Map<String, Object> res = new HashMap<String, Object>();
                res.put("0", "");
                res.put("1", info == null ? new Object() : info);
                res.put("2", new ArrayList<Object>());
                res.put("3", 2);
                return res;
            }
            case "playerInfo": {
                Base o = new Gson().fromJson(msg, Base.class);
                return roleAction.getPlayerInfo(o.acc);
            }
            case "getNotice": {
                // List<Notice> noticeList = cacheManager.getNoticeList();
                List<Notice> noticeList = new ArrayList<Notice>();
                return noticeList;
            }
        }
        return null;
    }

    class Login implements Serializable {
        private static final long serialVersionUID = 1L;
        public int id;
    }

    class Base {
        public String acc;
    }

    class Reg {
        public String acc;
        public int rid;
        public String name;
    }
}