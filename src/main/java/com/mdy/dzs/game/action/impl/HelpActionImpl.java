package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mdy.dzs.data.DataApplication;
import com.mdy.dzs.data.action.DataAction;
import com.mdy.dzs.data.domain.robot.Name;
import com.mdy.dzs.data.domain.robot.XuNiWanJia;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.HelpAction;
import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.battle.RoleBattle;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.item.RoleItemLog;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.shop.RoleShop;
import com.mdy.dzs.game.util.BehaviorLogger;
import com.mdy.dzs.game.util.BehaviorLogger.Action;
import com.mdy.dzs.game.util.BehaviorLogger.Params;
import com.mdy.dzs.game.util.BehaviorLogger.Type;
import com.mdy.sharp.container.Container;
import com.mdy.sharp.container.biz.BizException;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.util.JSONUtil;

public class HelpActionImpl extends ApplicationAwareAction implements HelpAction {

	private final Logger logger = LoggerFactory.get(HelpAction.class);
	private static final int pos = 1;
	private static final int subpos = 2;

	@Override
	public int getGuide(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		int guideStep = role.getGuideStep();

		// if (830 <= guideStep && guideStep <= 850) {
		// //检测武器等级是否大于0级，如果等于0级，不用管它，如果武器装备大于1级，则跳转至861
		// int level = roleAO().queryequiplevel(role.getId(), pos, subpos);
		// if (level > 1) {
		// guideStep = 861;
		// }
		// } else
		if (guideStep >= 410 && guideStep <= 440) {
			// 检测阵容3号位侠客等级是否等等于1级，如果等于1级，则不管它，如果大于1级，则需跳转至451
			RoleCard posCard = roleCardAO().queryByRoleIdPos(role.getId(), 3);
			if (posCard != null && posCard.getLevel() > 1) {
				guideStep = 451;
			}
		} else if (guideStep >= 520 && guideStep <= 540) {
			// 检测玩家当前是否已领取奖励，若果未领取奖励，则不管它，如果已领取奖励，则需跳转至551
			RoleBattle rb = roleBattleAO().queryByRoleId(role.getId());
			if (rb.getBattleBox().size() > 0) {
				guideStep = 551;
			}
			// } else if (guideStep >= 910 && guideStep <= 950) {
			// int item1Cnt = packetAO().getNumberByTypeId(role,
			// Packet.POS_IN_GONG_ITEM, 5307);
			// int item2Cnt = packetAO().getNumberByTypeId(role,
			// Packet.POS_IN_GONG_ITEM, 5308);
			// int item3Cnt = packetAO().getNumberByTypeId(role,
			// Packet.POS_IN_GONG_ITEM, 5309);
			//
			// if (item1Cnt == 0 || item3Cnt == 0) {
			// // 5307/5309如果缺少一个，则需跳转至991
			// guideStep = 991;
			// }else if (item1Cnt == 0 && item2Cnt == 0 && item3Cnt == 0) {
			// // 三个武学碎片数为0的时候，则跳转至961（武学碎片ID:5307/5308/5309)
			// guideStep = 961;
			// }
		} else if (guideStep >= 160 && guideStep <= 190) {
			// 检测主角已经进阶了1级
			if (role.getCls() > 0) {
				guideStep = 191;
			}
		} else if (guideStep >= 220 && guideStep <= 230) {
			// 检测主角神通等级大于1跳转
			RoleCard mainCard = roleCardAO().queryById(role.getFmtMainCardID());
			if (mainCard.getShenLvAry().get(0) > 1) {
				guideStep = 231;
			}
		} else if (guideStep >= 350 && guideStep <= 360) {
			// 招募侠客 361 检测玩家是否已经在商城里招募了中间的那个侠客 招募了 跳转
			RoleShop roleShop = shopAO().queryRoleShopByRoleId(role.getId());
			if (roleShop.getWineGotCntAry().get(1) > 0) {
				guideStep = 361;
			}
		} else if (guideStep >= 390 && guideStep <= 400) {
			// 检测三号位是否有侠客/或者检测玩家是否有空位 无空位 跳转
			if (role.getFmtCardAry().get(3) != 0) {
				guideStep = 401;
			}
		}
		return guideStep;
	}

	@Override
	public void setGuide(String acc, int step) throws BizException {
		Role role = roleAO().queryExistAccount(acc);

		role.setGuideStep(step);
		roleAO().updateGuideStep(role);
		BehaviorLogger.log4Platform(role, Type.GAME, Action.GUIDE_STEP, Params.valueOf("step", step));
	}

	@Override
	public Serializable getUserParam(String acc, String type) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Serializable res = null;
		if (type.compareTo("helpStoryStep") == 0) {
			res = (Serializable) doc.getHelpStoryStep();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setUserParam(String acc, String type, List<Integer> param) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		if (type.compareTo("helpStoryStep") == 0) {
			doc.setHelpStoryStep(param);
		}
		roleAO().updateSetParam(doc);
	}

	@Override
	public void createRobotRoles() throws BizException {
		int male = 0;// ===========================================男名
		int female = 0;// =========================================女名

		int firstMale = male = cacheManager().random(0, 50);// ======================================男姓
		int firstFemale = female = cacheManager().random(0, 50);// ====================================女姓

		int roboti = roleAO().queryRobotSize();
		if (roboti == 0) {
			roleArenaAO().clearRoleArena();
		}
		DataAction dataAction = Container.get().createRemote(DataAction.class, DataApplication.CLUSTER_DATA_SYSTEM);

		List<XuNiWanJia> xuniAry = dataAction.queryXuNiWanJiaDatas();
		List<Name> nameAry = dataAction.queryNameDatas();

		int sumCount = 0;
		for (int i = 0; i < xuniAry.size(); i++) {
			XuNiWanJia xuni = xuniAry.get(i);
			while (roboti < sumCount + xuni.getNum1()) {
				int mainCardId = cacheManager().random(1, 2);
				createRobotRole(roboti, mainCardId, xuni, nameAry);
				roboti++;
			}
			sumCount += xuni.getNum1();
		}
	}

	/**
	 * 创建机器人
	 * 
	 * @param roboti
	 * @param mainCardId
	 * @param xuni
	 * @param nameAry
	 * @throws BizException
	 */
	private void createRobotRole(int roboti, int mainCardId, XuNiWanJia xuni, List<Name> nameAry) throws BizException {
		String acc = Role.TYPE_ROBOT + "__" + roboti;
		String name = getRandomName(mainCardId, nameAry);
		while (roleAO().queryIsExist(acc, name) != null) {
			name = getRandomName(mainCardId, nameAry);
		}

		Role doc = roleAO().addRole(acc, "", name, Role.TYPE_ROBOT, mainCardId, "", "");
		doc.setLevel(cacheManager().random(xuni.getLevel().get(0), xuni.getLevel().get(1)));
		int autoAry[] = { 2, 5, 3, 4, 6, 1 };
		for (int i = 0; i < xuni.getNum2(); i++) {
			int cardId = getRandomCardId(xuni);
			List<Object> res = packetAO().addItem(doc, Packet.POS_CARD, cardId, 1, RoleItemLog.SYS_机器人_初始化, acc);
			RoleCard rCard = (RoleCard) res.get(0);
			rCard.setLevel(doc.getLevel() - 10);
			doc.getFmtCardAry().set(i + 2, rCard.getId());
			rCard.setPos(autoAry[i + 1]);
			roleCardAO().update(rCard);
		}
		RoleArena ra = new RoleArena();
		ra.setRoleId(doc.getId());
		ra.setAwardIds(new ArrayList<Integer>());
		roleArenaAO().add(ra);
		roleAO().roleDAO().updateExp(doc);
		roleAO().updateLineup(doc);
		roleAO().fmtPropUpdate(doc);
		logger.info("create robot:" + acc + "complete!");
	}

	/**
	 * 获取随机卡片
	 * 
	 * @param xuni
	 * @return
	 */
	private int getRandomCardId(XuNiWanJia xuni) {

		int all = xuni.getProb1() + xuni.getProb2() + xuni.getProb3() + xuni.getProb4();
		int rand = cacheManager().random(0, all);
		List<List<Integer>> ranges = Arrays.asList(xuni.getRange1(), xuni.getRange2(), xuni.getRange3(),
				xuni.getRange4());
		List<Integer> probs = Arrays.asList(xuni.getProb1(), xuni.getProb2(), xuni.getProb3(), xuni.getProb4());
		for (int i = 0; i < ranges.size(); i++) {
			if (rand <= probs.get(i)) {
				List<Integer> range = ranges.get(i);
				return cacheManager().random(range.get(0), range.get(1));
			}
			rand -= probs.get(i);
		}
		return 0;
	}

	/**
	 * 获取随机名
	 * 
	 * @param rid
	 * @param nameList
	 * @return
	 */
	private String getRandomName(int rid, List<Name> nameList) {
		int first = cacheManager().random(0, nameList.size() - 1);
		int end = cacheManager().random(0, nameList.size() - 1);
		String firstName = nameList.get(first).getFirst();
		String endName = rid == 1 ? nameList.get(end).getMale() : nameList.get(end).getFemale();
		return firstName + endName;
	}

	@Override
	public void clearRobotRoles() throws BizException {
		roleAO().clearRobot();
		roleArenaAO().clearRoleArena();
	}

}
