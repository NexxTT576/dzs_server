package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.domain.role.JiBan;
import com.mdy.dzs.data.domain.sword.Lunjian;
import com.mdy.dzs.data.domain.viplevel.Vip;
import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.swordfight.ClientEnterSwordVO;
import com.mdy.dzs.game.domain.swordfight.ClientSwordCardVO;
import com.mdy.dzs.game.domain.swordfight.ClientSwordRoleVO;
import com.mdy.dzs.game.domain.swordfight.ComparatorLunjian;
import com.mdy.dzs.game.domain.swordfight.ComparatorSwordRole;
import com.mdy.dzs.game.domain.swordfight.SwordRole;
import com.mdy.dzs.game.domain.swordfight.SwordRoleCard;
import com.mdy.dzs.game.domain.swordfight.SwordRolePart;
import com.mdy.dzs.game.exception.RoleException;
import com.mdy.dzs.game.fight.main.FConstants;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;
import com.mdy.dzs.game.util.Constants;
import com.mdy.sharp.container.biz.BizException;

/**
 * 论剑角色
 * 
 * @author zhou
 *
 */
public class SwordRoleAO extends BaseAO {
	private CacheManager cacheManager;
	private RoleAO roleAO;
	private SwordRoleCardAO swordRoleCardAO;

	public SwordRoleAO(CacheManager cacheManager, RoleAO roleAO, SwordRoleCardAO swordRoleCardAO) {
		this.cacheManager = cacheManager;
		this.roleAO = roleAO;
		this.swordRoleCardAO = swordRoleCardAO;
	}

	//
	/**
	 * 查询
	 */
	public SwordRole query(int id) {
		return swordRoleDAO().query(id);
	}

	/**
	 * 添加
	 * 
	 * @param SwordRole
	 */
	public void add(SwordRole sr) {
		swordRoleDAO().add(sr);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(SwordRole srVO) {
		swordRoleDAO().delete(srVO);
	}

	/**
	 * 更新
	 * 
	 * @param SwordRole
	 */
	public void update(SwordRole sr) {
		swordRoleDAO().update(sr);
	}

	/**
	 * 更新层数
	 * 
	 * @param src
	 */
	public void updateFloor(SwordRole sr) {
		swordRoleDAO().updateFloor(sr);
	}

	/**
	 * 更新已经领取的奖励层
	 * 
	 * @param src
	 */
	public void updateAwards(SwordRole sr) {
		swordRoleDAO().updateAwards(sr);
	}

	/**
	 * 查询是否存在
	 */
	public SwordRole queryExistAccount(String selfAcc, String enemyAcc) throws BizException {
		SwordRole role = swordRoleDAO().queryBySelfAccount(selfAcc, enemyAcc);
		if (role == null) {
			throw RoleException.getException(RoleException.EXCE_ACCOUNT_NOT_EXIST);
		}
		return role;
	}

	/**
	 * 根据自己的账号和 塔层查找数据
	 * 
	 * @param selfAcc
	 * @param floor
	 * @return
	 */
	public SwordRole queryByFloor(String selfAcc, int floor) {
		SwordRole enemy = swordRoleDAO().queryByFloor(selfAcc, floor);
		return enemy;
	}

	/**
	 * 根据账号获得 数据库中的值
	 * 
	 * @param selfAcc
	 * @param enemyAcc
	 * @return
	 */
	public SwordRole queryBySelfAccount(String selfAcc, String enemyAcc) {
		SwordRole role = swordRoleDAO().queryBySelfAccount(selfAcc, enemyAcc);
		return role;
	}

	/**
	 * 查询自己的敌人列表
	 * 
	 * @param selfAcc
	 * @return
	 */
	public List<SwordRole> queryEmemies(String selfAcc) {
		List<SwordRole> srList = swordRoleDAO().queryEmemies(selfAcc);
		return srList;
	}

	/**
	 * 生成对应的给客户端的VO 对手角色
	 * 
	 * @param enemySr
	 * @return
	 */
	public ClientSwordRoleVO getClientSwordRole(SwordRole enemySr) {
		ClientSwordRoleVO csrVO = new ClientSwordRoleVO();
		csrVO.setCombat(enemySr.getCombat());
		csrVO.setFloor(enemySr.getFloor());
		csrVO.setId(enemySr.getId());
		csrVO.setName(enemySr.getName());
		csrVO.setShowId(enemySr.getResId());
		return csrVO;
	}

	/**
	 * 生成15个论剑的敌人
	 * 
	 * @param lunjians
	 * @param self
	 * @return
	 */
	public List<ClientSwordRoleVO> getEnemies(List<Lunjian> lunjians, Role self) throws BizException {
		List<ClientSwordRoleVO> csrList = new ArrayList<ClientSwordRoleVO>();
		// 初始化敌人列表
		List<SwordRolePart> enemiesSim = new ArrayList<SwordRolePart>();
		Collections.sort(lunjians, new ComparatorLunjian());// 倒序排序
		int num = 3;// 每次循环需要查到的数目
		int now = 3;// 若上次没有查够3个 则将不足的 累计到下次查找
		for (int i = 0; i < lunjians.size(); i++) {
			now = num;
			// 论剑数据
			Lunjian lunjian = lunjians.get(i);
			// 目标战斗力 最大战斗力 最小战斗力
			int minAttrack = (int) (self.getMaxAttack() * lunjian.getZhanli().get(0))
					- self.getLevel() * lunjian.getConstant();
			int maxAttrack = (int) (self.getMaxAttack() * lunjian.getZhanli().get(1))
					- self.getLevel() * lunjian.getConstant();
			List<SwordRolePart> roles = roleDAO().queryBetweenAttrack(enemiesSim, self, minAttrack, maxAttrack);
			if (roles.size() > now) {
				List<SwordRolePart> srp = randomRole(roles, now);
				enemiesSim.addAll(srp);
				num = 0;
			} else {
				enemiesSim.addAll(roles);
				num = now - roles.size();
			}
			if (i < lunjians.size() - 1) {
				num += 3;
			}
		}
		// 如果不够 则找到比自己小的战斗的数据
		if (num > 0) {
			int minAttrack = 0;
			double n = lunjians.get(0).getZhanli().get(1);
			int maxAttrack = (int) (self.getMaxAttack() * n - self.getLevel() * lunjians.get(0).getConstant());
			if (maxAttrack < 0)
				maxAttrack = 0;
			List<SwordRolePart> roles = roleDAO().queryBetweenAttrack(enemiesSim, self, minAttrack, maxAttrack);
			List<SwordRolePart> srp = randomRole(roles, num);
			enemiesSim.addAll(srp);
			num = 0;
		}

		// 找到15个人 转化为Role
		List<Role> enemies = new ArrayList<Role>();
		for (int i = 0; i < enemiesSim.size(); i++) {
			Role r = roleAO.queryByAccount(enemiesSim.get(i).getAccount());
			enemies.add(r);
		}
		// 按照战斗力排序正序
		Collections.sort(enemies, new ComparatorSwordRole());

		// 将查询到的角色存入数据库 将角色下的卡牌存入数据库 并封装返给客户端的数据
		for (int j = 0; j < enemies.size(); j++) {
			// 将查询到的角色存入数据库
			// 将角色下的卡牌存入数据库 并封装返给客户端的数据
			ClientSwordRoleVO csrVo = getEnemySwordRole(enemies.get(j), self, j + 1);
			csrList.add(csrVo);
		}
		return csrList;
	}

	/**
	 * 生成每层论剑敌人
	 * 
	 * @param lunjian
	 * @return
	 */
	public ClientSwordRoleVO getEnemySwordRole(Role enemy, Role self, int floor) throws BizException {
		SwordRole enemySr = getSwordRole(enemy, self, floor);
		swordRoleDAO().add(enemySr);
		// 返回数据
		ClientSwordRoleVO csrVO = getClientSwordRole(enemySr);
		List<ClientSwordCardVO> cscList = new ArrayList<ClientSwordCardVO>();
		// 处理玩家身上的属性
		List<Integer> cards = enemy.getFmtCardAry();
		for (int i = 0; i < cards.size(); i++) {
			Integer c = cards.get(i);
			if (c == 0) {
				continue;
			}
			RoleCard rc = roleCardDAO().query(c);
			if (rc.getPos() == 0) {
				continue;
			}
			SwordRoleCard src = getSwordRoleCard(rc, enemy, 1, enemySr.getId());
			src.setAccount(enemy.getAccount());
			// 将卡的数据存入数据库
			swordRoleCardDAO().add(src);

			// 给前端的数据
			ClientSwordCardVO cscVO = new ClientSwordCardVO();
			cscVO = swordRoleCardAO.getClientSwordCard(src);
			cscList.add(cscVO);

		}
		ClientSwordCardVO cscVO = cscList.get(cacheManager.random(0, cscList.size() - 1));
		enemySr.setResId(cscVO.getId());
		swordRoleDAO().update(enemySr);
		csrVO.setCards(cscList);
		csrVO.setShowId(enemySr.getResId());
		return csrVO;
	}

	/**
	 * 根据角色生成论剑角色
	 * 
	 * @param role
	 * @param self
	 * @param floor
	 * @return
	 */
	public SwordRole getSwordRole(Role role, Role self, int floor) {
		Date now = new Date();
		SwordRole enemySr = new SwordRole();
		enemySr.setSelfAccount(self.getAccount());
		enemySr.setEnemyAccount(role.getAccount());
		enemySr.setResId(0);
		enemySr.setCombat(role.getAttack());
		enemySr.setFloor(floor);
		enemySr.setName(role.getName());
		enemySr.setGoldResetCnt(0);
		enemySr.setResetCnt(0);
		enemySr.setEnterTime(now);
		enemySr.setAwards(new ArrayList<Integer>());
		return enemySr;
	}

	/**
	 * 根据角色卡生成 论剑卡
	 * 
	 * @param rc
	 * @param doc
	 * @param type
	 * @return
	 * @throws BizException
	 */
	public SwordRoleCard getSwordRoleCard(RoleCard rc, Role doc, int type, int sword_role_id) throws BizException {
		Card rcData = cacheManager.getExistValueByKey(Card.class, rc.getResId());
		SwordRoleCard src = new SwordRoleCard();
		src.setAccount(doc.getAccount());
		src.setCls(rc.getCls());
		// 计算附加属性值
		List<Prop> props = new ArrayList<Prop>();
		Map<Integer, Integer> propMaps = roleAO.calcCardProps(doc, rc);
		props = RoleAttackCalcManager.convProps(propMaps);
		src.setProps(props);
		int initLife = caclLife(rcData.getBase(), rcData.getLead(), propMaps);
		src.setInitLife(initLife);
		src.setLifeRate(10000);
		src.setOrder(0);
		src.setPos(rc.getPos());
		src.setType(type);
		src.setResId(rc.getResId());
		if (rc.getId() == doc.getFmtMainCardID()) {
			rc.setLevel(doc.getLevel());
		}
		src.setLevel(rc.getLevel());
		src.setShenIDAry(rc.getShenIDAry());
		src.setShenLvAry(rc.getShenLvAry());
		src.setAnger(rcData.getAnger().get(rc.getCls()));
		src.setStar(rc.getStar());
		src.setRole_card_id(rc.getId());
		src.setSword_role_id(sword_role_id);
		return src;
	}

	/**
	 * 设置卡的生命和怒气
	 * 
	 * @param rc
	 * @param doc
	 * @param type
	 * @return
	 * @throws BizException
	 */
	public SwordRoleCard setLifeAndAnger(RoleCard rc, Role doc, int type, SwordRoleCard srcOne) throws BizException {
		// 库中已有的卡 将库中卡的血和怒气赋值给原卡 ，将卡片现在的属性、star和cls 赋给现在的卡
		srcOne.setCls(rc.getCls());
		srcOne.setOrder(0);
		srcOne.setPos(rc.getPos());
		srcOne.setType(type);
		srcOne.setResId(rc.getResId());
		srcOne.setShenIDAry(rc.getShenIDAry());
		srcOne.setStar(rc.getStar());

		// Map<Integer, Integer> propMaps = roleAO.calcCardProps(doc, rc);
		// List<Prop> props = new ArrayList<Prop>();
		// props = RoleAttackCalcManager.convProps(propMaps);
		// srcOne.setProps(props);
		// Card rcData = cacheManager.getExistValueByKey(Card.class, rc.getResId());
		// int initLife = caclLife(rcData.getBase() ,rcData.getLead() ,propMaps);
		// srcOne.setInitLife(initLife);
		return srcOne;
	}

	/**
	 * 根据人物的角色卡 生成论剑前端数据卡
	 * 
	 * @param rc
	 * @param doc
	 * @return
	 * @throws BizException
	 */
	public ClientSwordCardVO getClentSwordCard(RoleCard rc, Role doc) throws BizException {
		ClientSwordCardVO cscVO = new ClientSwordCardVO();
		Card rcData = cacheManager.getExistValueByKey(Card.class, rc.getResId());
		cscVO.setCardId(rc.getResId());
		cscVO.setCls(rc.getCls());
		cscVO.setLevel(rc.getLevel());
		Map<Integer, Integer> propMaps = roleAO.calcCardProps(doc, rc);
		int initLife = caclLife(rcData.getBase(), rcData.getLead(), propMaps);
		cscVO.setInitLife(initLife);
		cscVO.setLife(initLife);
		cscVO.setPos(rc.getPos());
		cscVO.setStar(rc.getStar());
		cscVO.setId(rc.getId());
		return cscVO;
	}

	public ClientEnterSwordVO getClientEnterSword(Role doc, SwordRole sr, List<ClientSwordRoleVO> enemies,
			List<ClientSwordCardVO> cards) throws BizException {
		int gold = doc.getGold();// 当前的金币
		int resetTimes = Constants.swordFreeResetCnt - sr.getResetCnt();// 免费重置次数
		int curFloor = sr.getFloor();// 当前层
		// 根据vip等级得到元宝可以重置的次数
		Vip vip = cacheManager.getExistValueByKey(Vip.class, Vip.SYSTEM_论剑每日购买次数);
		int vipCnt = vip.getVipByLevel(doc.getVip());
		int goldResetTimes = vipCnt - sr.getGoldResetCnt();// 元宝重置次数
		int resetGold = Constants.swordVipBuyBaseGold * (sr.getGoldResetCnt() + 1);// 重置元宝数

		ClientEnterSwordVO cesVO = new ClientEnterSwordVO();
		cesVO.setGold(gold);
		cesVO.setResetTimes(resetTimes);
		cesVO.setCurFloor(curFloor);
		cesVO.setGoldResetTimes(goldResetTimes);
		cesVO.setResetGold(resetGold);
		cesVO.setEnemies(enemies);
		cesVO.setCards(cards);
		cesVO.setAwards(sr.getAwards());
		cesVO.setName(doc.getName());
		return cesVO;
	}

	/**
	 * 计算生命值
	 * 
	 * @param base
	 * @param lead
	 * @param props
	 * @return
	 */
	public int caclLife(List<Integer> base, List<Integer> lead, Map<Integer, Integer> props) {
		Integer addLife = props.get(FConstants.AttInd_Life);
		addLife = addLife == null ? 0 : addLife;
		Integer addLifeR = props.get(FConstants.AttInd_LifeR);
		addLifeR = addLifeR == null ? 0 : addLifeR;
		Integer addLead = props.get(FConstants.AttInd_Lead);
		addLead = addLead == null ? 0 : addLead;
		double v1 = (base.get(0) + addLife) * (1.0 + addLifeR * 0.0001);
		double h1 = (lead.get(0) + addLead);
		v1 *= (50.0 + h1) / 100.0;
		return (int) Math.round(v1);
	}

	/**
	 * 在 enemies随机now 张卡
	 * 
	 * @param enemies
	 * @param now
	 * @return
	 */
	private List<SwordRolePart> randomRole(List<SwordRolePart> enemies, int now) {
		List<SwordRolePart> roles = new ArrayList<SwordRolePart>();
		Random rd = new Random(System.currentTimeMillis());
		List<Integer> list = new ArrayList<Integer>();
		if (now > enemies.size()) {// 如果要随机到的数量大于随机出的敌人，就全部放入角色中
			roles.addAll(enemies);
		} else {
			for (int i = 0; i < now; i++) // 个数随机
			{
				// 随机一个数
				int tmpIdx = rd.nextInt(enemies.size());
				// 去重复
				while (list.contains(tmpIdx))
					tmpIdx = rd.nextInt(enemies.size());
				list.add(tmpIdx);
				roles.add(enemies.get(tmpIdx));
			}
		}
		return roles;
	}

	public void updateCardRelation(Role doc, RoleCard curCard) throws BizException {
		int roleId = doc.getId();
		Card cardData = cacheManager.getExistValueByKey(Card.class, curCard.getResId());
		List<Integer> jbAry = cardData.getFate1();
		List<Integer> relation = new ArrayList<Integer>();
		for (int i = 0; i < jbAry.size(); i++) {
			int jbId = jbAry.get(i);
			JiBan jbItem = cacheManager.getExistValueByKey(JiBan.class, jbId);
			boolean bExist = true;
			for (int j = 1; j <= 6; j++) {
				int rid = jbItem.getCond(j);
				if (rid == 0)
					continue;
				boolean itemFind = false;
				if (jbItem.getType() != 1) {
					Item item = cacheManager.getExistValueByKey(Item.class, rid);
					if (item.getType() == Packet.POS_EQUIP) {
						itemFind = equipDAO().querySameResEquipCount(roleId, rid) != 0;
					} else {
						itemFind = roleGongDAO().querySameResGong(roleId, rid).size() != 0;
					}

				}
				if (!itemFind) {
					bExist = false;
					break;
				}
			}

			if (bExist) {
				relation.add(jbItem.getId());
			}
		}
		curCard.setRelation(relation);
	}
}