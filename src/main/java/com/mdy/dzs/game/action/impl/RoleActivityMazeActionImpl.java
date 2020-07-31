package com.mdy.dzs.game.action.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.item.ProbItem;
import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.RoleActivityMazeAction;
import com.mdy.dzs.game.domain.activity.mazegame.DigVO;
import com.mdy.dzs.game.domain.activity.mazegame.MazeConfig;
import com.mdy.dzs.game.domain.activity.mazegame.MazeEnterVO;
import com.mdy.dzs.game.domain.activity.mazegame.MazeRefreshVO;
import com.mdy.dzs.game.domain.activity.mazegame.RoleActivityMaze;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.packet.PacketExtend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.ActivityException;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

public class RoleActivityMazeActionImpl extends ApplicationAwareAction implements RoleActivityMazeAction {

	@Override
	public MazeEnterVO mazeEnter(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		// check role's level
		Open openData = cacheManager().getExistValueByKey(Open.class, 47);// key:system
		if (doc.getLevel() < openData.getLevel().get(0)) {
			throw BaseException.getException(ActivityException.EXCE_MAZE_LEVEL_CONTROL);
		}

		RoleActivityMaze player = roleActivityMazeAO().queryAndAdd(doc.getId());
		MazeConfig mazeConfig = roleActivityAO().getMazeConfig();
		if (!DateUtil.isToday(player.getRefreshTime())) {// next day
			roleActivityMazeAO().dayRefresh(player, mazeConfig);
		}

		int digOneGold = roleActivityMazeAO().getDigOneGold(player, mazeConfig);
		int digAllGold = roleActivityMazeAO().getDigAllGold(player, mazeConfig);
		long countDown = roleActivityRouletteAO().getCountDown(roleActivityAO().getMazeActiveTime());

		String timeDis_[] = roleActivityAO().getMazeActiveTime().split("_");
		Date startT = DateUtil.getDateTimeByString(timeDis_[0]);
		Date endT = DateUtil.getDateTimeByString(timeDis_[1]);

		return new MazeEnterVO(mazeConfig.getLibId(), countDown, startT, endT, player.getSurGoldTimes(),
				player.getFreeTimes(), digAllGold, mazeConfig.getRefreshPrice(), player.getGetTreasuryList(),
				player.getTreasuryMap(), digOneGold);
	}

	@Override
	public MazeRefreshVO mazeRefresh(String acc) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityMaze player = roleActivityMazeAO().queryAndAdd(doc.getId());

		if (!DateUtil.isToday(player.getRefreshTime())) {// next day
			return new MazeRefreshVO(RoleActivityMaze.refresh, new HashMap<String, ProbItem>(), 0, 0, 0);
		}

		MazeConfig mazeConfig = roleActivityAO().getMazeConfig();
		packetAO().removeItemMustEnough(doc, Packet.POS_ATTR, Packet.ATTR_金币, mazeConfig.getRefreshPrice(),
				RoleItemLog.SYS_精彩活动_迷宫寻宝_刷新宝库, "");

		roleActivityMazeAO().itemRefresh(player, mazeConfig);
		int digOneGold = roleActivityMazeAO().getDigOneGold(player, mazeConfig);
		int digAllGold = roleActivityMazeAO().getDigAllGold(player, mazeConfig);

		return new MazeRefreshVO(RoleActivityMaze.notRefresh, player.getTreasuryMap(), doc.getGold(), digAllGold,
				digOneGold);
	}

	@Override
	public DigVO mazeDig(String acc, int type) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		RoleActivityMaze player = roleActivityMazeAO().queryAndAdd(doc.getId());
		if (!DateUtil.isToday(player.getRefreshTime())) {// next day
			return new DigVO(RoleActivityMaze.refresh, new ArrayList<PacketExtend>(), new HashMap<String, ProbItem>(),
					0, 0, 0, 0, 0);
		}
		List<PacketExtend> checkBag = packetAO().checkBag(doc, 23);
		if (checkBag.size() > 0) {
			return new DigVO(RoleActivityMaze.notRefresh, checkBag, new HashMap<String, ProbItem>(), 0, 0, 0, 0, 0);
		}
		MazeConfig mazeConfig = roleActivityAO().getMazeConfig();
		int num = 1;
		if (type != RoleActivityMaze.digOne) {
			if (doc.getVip() < Constants.DigAllVipControll) {
				throw BaseException.getException(ActivityException.EXCE_MAZE_VIP_LEVEL_CONTROL);
			}
			num = player.getTreasuryMap().size() - player.getGetTreasuryList().size();
		}
		if (player.getSurGoldTimes() + player.getFreeTimes() < num) {// 剩余挖宝次数不足
			throw BaseException.getException(ActivityException.EXCE_MAZE_SUR_TIMES_CONTROL);
		}

		if (player.getGetTreasuryList().size() >= player.getTreasuryMap().size()) {// 宝库中已没有宝物，请刷新宝库
			throw BaseException.getException(ActivityException.EXCE_MAZE_NO_ITEM);
		}
		Map<String, ProbItem> getMap = new HashMap<String, ProbItem>();

		roleActivityMazeAO().dig(player, num, mazeConfig, getMap, doc);
		roleActivityMazeAO().update(player);

		int digOneGold = roleActivityMazeAO().getDigOneGold(player, mazeConfig);
		int digAllGold = roleActivityMazeAO().getDigAllGold(player, mazeConfig);

		return new DigVO(RoleActivityMaze.notRefresh, checkBag, getMap, doc.getGold(), player.getFreeTimes(),
				player.getSurGoldTimes(), digOneGold, digAllGold);
	}

}
