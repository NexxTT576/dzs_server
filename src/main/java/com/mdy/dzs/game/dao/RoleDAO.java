package com.mdy.dzs.game.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mdy.dzs.data.domain.account.IOSDeviceInfo;
import com.mdy.dzs.game.dao.filter.RoleUpdateFilter;
import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.friend.RoleFriend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.role.RoleIdName;
import com.mdy.dzs.game.domain.swordfight.SwordRolePart;
import com.mdy.dzs.game.domain.tournament.RoleTournament;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 角色DAO
 * 
 * @author 房曈
 *
 */
public class RoleDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<Role> HANDLER = new ResultSetRowHandler<Role>() {
		@SuppressWarnings("unchecked")
		@Override
		public Role handleRow(ResultSetRow row) throws Exception {
			Role r = new Role();
			r.setId(row.getInt("id"));
			r.setAccount(row.getString("account"));
			r.setName(row.getString("name"));
			r.setType(row.getString("type"));
			r.setFaction(row.getString("faction"));
			r.setResId(row.getInt("resId"));
			r.setSex(row.getInt("sex"));
			r.setCreateTime(row.getTimestamp("createTime"));
			r.setLevel(row.getInt("level"));
			r.setCls(row.getInt("cls"));
			r.setVip(row.getInt("vip"));
			r.setGold(row.getInt("gold"));
			r.setSilver(row.getInt("silver"));
			r.setPhysVal(row.getInt("physVal"));
			r.setResisVal(row.getInt("resisVal"));
			r.setPopual(row.getInt("popual"));
			r.setExp(row.getInt("exp"));
			r.setSoul(row.getInt("soul"));
			r.setSocial(row.getInt("social"));
			r.setZhenQiVal(row.getInt("zhenQiVal"));
			r.setHunYuVal(row.getInt("hunYuVal"));
			r.setAttack(row.getInt("attack"));
			r.setMaxAttack(row.getInt("maxAttack"));
			r.setAddTimeAry(JSONUtil.fromJson(row.getString("addTimeAry"), List.class));
			r.setPropLimitAry(JSONUtil.fromJson(row.getString("propLimitAry"), List.class));
			r.setBagLimitAry(JSONUtil.fromJson(row.getString("bagLimitAry"), List.class));
			r.setCurCollLv(row.getInt("curCollLv"));
			r.setDayLoginTime(row.getTimestamp("dayLoginTime"));
			r.setVersion(row.getString("version"));
			r.setFmtCardAry(JSONUtil.fromJson(row.getString("fmtCardAry"), List.class));
			r.setFmtMainCardID(row.getInt("fmtMainCardID"));
			r.setHeartLastTime(row.getInt("heartLastTime"));
			r.setSleepLastTime(row.getTimestamp("sleepLastTime"));
			r.setChatLastViewTime(JSONUtil.fromJson(row.getString("chatLastViewTime"), List.class));
			r.setMailLastViewAry(JSONUtil.fromJson(row.getString("mailLastViewAry"), List.class));
			r.setBroadcastViewTime(row.getTimestamp("broadcastViewTime"));
			r.setLevelUpLastTime(row.getTimestamp("level_up_last_time"));
			r.setGuideStep(row.getInt("guideStep"));
			r.setDaysContinue(row.getInt("daysContinue"));
			r.setHelpStoryStep(JSONUtil.fromJson(row.getString("helpStoryStep"), List.class));
			r.setCharm(row.getInt("charm"));
			r.setLingShi(row.getInt("lingShi"));
			r.setGetFriendNailiCnt(row.getInt("getFriendNailiCnt"));
			r.setLastDeviceType(row.getString("last_device_type"));
			r.setLastDeviceVersion(row.getString("last_device_version"));
			r.setLastDeviceUUID(row.getString("last_device_uuid"));
			r.setServerId(row.getString("server_id"));
			r.setHonor(row.getInt("honor"));
			return r;
		}
	};

	private static ResultSetRowHandler<SwordRolePart> HANDLERSword = new ResultSetRowHandler<SwordRolePart>() {
		@Override
		public SwordRolePart handleRow(ResultSetRow row) throws Exception {
			SwordRolePart r = new SwordRolePart();
			r.setId(row.getInt("id"));
			r.setAccount(row.getString("account"));
			r.setLevel(row.getInt("level"));
			r.setAttack(row.getInt("attack"));
			return r;
		}
	};

	/**
	 * 添加
	 */
	public void add(Role r) {
		String sql = "insert into t_role("
				+ "account,name,type,heartlasttime,broadcastviewtime,daylogintime,baglimitary,"
				+ "fmtCardAry,fmtMainCardID," + "nickname,version,server_id,"
				+ "level,vip,cls,silver,gold,exp,physval,resisval,createtime,proplimitary,resid,sex,attack,maxAttack"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		execute(sql, r.getAccount(), r.getName(), r.getType(), r.getHeartLastTime(), r.getBroadcastViewTime(),
				r.getDayLoginTime(), JSONUtil.toJson(r.getBagLimitAry()), JSONUtil.toJson(r.getFmtCardAry()),
				r.getFmtMainCardID(), r.getNikeName(), r.getVersion(), r.getServerId(), r.getLevel(), r.getVip(),
				r.getCls(), r.getSilver(), r.getGold(), r.getExp(), r.getPhysVal(), r.getResisVal(), r.getCreateTime(),
				JSONUtil.toJson(r.getPropLimitAry()), r.getResId(), r.getSex(), r.getAttack(),
				r.getAttack() > r.getMaxAttack() ? r.getAttack() : r.getMaxAttack());

	}
	// /**
	// * 更新
	// * @param Role
	// */
	// public void update(Role r){
	// String sql="update t_role set " +
	// " account=?," +
	// " name=?," +
	// " type=?," +
	// " rank=?," +
	// " faction=?," +
	// " resId=?," +
	// " sex=?," +
	// " createTime=?," +
	// " level=?," +
	// " cls=?," +
	// " vip=?," +
	// " glod=?," +
	// " silver=?," +
	// " physVal=?," +
	// " resisVal=?," +
	// " popual=?," +
	// " exp=?," +
	// " soul=?," +
	// " social=?," +
	// " zhenQiVal=?," +
	// " hunYuVal=?," +
	// " attack=?," +
	// " addTimeAry=?," +
	// " propLimitAry=?," +
	// " jiangHuAry=?," +
	// " itemAry=?," +
	// " cardItemAry=?," +
	// " equipItemAry=?," +
	// " gongItemAry=?," +
	// " bagLimitAry=?," +
	// " curCollLv=?," +
	// " wineFreeCntAry=?," +
	// " wineFreeNextTimeAry=?," +
	// " wineGotCntAry=?," +
	// " comShopPurchased=?," +
	// " dayLoginTime=?," +
	// " battleWorldID=?," +
	// " battleFieldID=?," +
	// " battleLvID=?," +
	// " battleHis=?," +
	// " battleBox=?," +
	// " battleNHitTime=?," +
	// " battleDayHitStat=?," +
	// " battleDayNHitClearCnt=?," +
	// " battleTotalStars=?," +
	// " eliteBattleLv=?," +
	// " eliteNxtLv=?," +
	// " eliteNxtLvState=?," +
	// " eliteNxtLvRevive=?," +
	// " eliteNxtLvDPos=?," +
	// " eliteTodayCnt=?," +
	// " eliteBuyCnt=?," +
	// " fmtCardAry=?," +
	// " fmtMainCardID=?," +
	// " fmtMainCardPos=?," +
	// " fmtPropPos1=?," +
	// " fmtPropPos2=?," +
	// " fmtPropPos3=?," +
	// " fmtPropPos4=?," +
	// " fmtPropPos5=?," +
	// " fmtPropPos6=?," +
	// " tmp=?," +
	// " warFreeTime=?," +
	// " roadMainCard=?," +
	// " roadStarCnt=?," +
	// " roadStarAchAry=?," +
	// " chanType=?," +
	// " chanLv=?," +
	// " chanPt=?," +
	// " chanAttrAry=?," +
	// " chanStarCnt=?," +
	// " chanReSetCnt=?," +
	// " giftLvGot=?," +
	// " giftCenterAry=?," +
	// " giftOnLineGot=?," +
	// " giftOnLineTime=?," +
	// " giftOnLineTimeAcc=?," +
	// " giftSignCnt=?," +
	// " giftSignTime=?," +
	// " arenaUpdateTime=?," +
	// " heartLastTime=?," +
	// " broadLastTime=?," +
	// " sleepLastTime=?," +
	// " chatRecs=?," +
	// " chatLastViewTime=?," +
	// " mailSys=?," +
	// " mailLastViewAry=?," +
	// " actPveTimes=?," +
	// " actPveCnts=?," +
	// " broadcastViewTime=?," +
	// " snatched=? " +
	// " where account=?";
	// executeUpdate(sql,
	// r.getAccount(),
	// r.getName(),
	// r.getType(),
	// r.getRank(),
	// r.getFaction(),
	// r.getResId(),
	// r.getSex(),
	// r.getCreateTime(),
	// r.getLevel(),
	// r.getCls(),
	// r.getVip(),
	// r.getGlod(),
	// r.getSilver(),
	// r.getPhysVal(),
	// r.getResisVal(),
	// r.getPopual(),
	// r.getExp(),
	// r.getSoul(),
	// r.getSocial(),
	// r.getZhenQiVal(),
	// r.getHunYuVal(),
	// r.getAttack(),
	// r.getAddTimeAry(),
	// r.getPropLimitAry(),
	// r.getJiangHuAry(),
	// r.getItemAry(),
	// r.getCardItemAry(),
	// r.getEquipItemAry(),
	// r.getGongItemAry(),
	// r.getBagLimitAry(),
	// r.getCurCollLv(),
	// r.getWineFreeCntAry(),
	// r.getWineFreeNextTimeAry(),
	// r.getWineGotCntAry(),
	// r.getComShopPurchased(),
	// r.getDayLoginTime(),
	// r.getBattleWorldID(),
	// r.getBattleFieldID(),
	// r.getBattleLvID(),
	// r.getBattleHis(),
	// r.getBattleBox(),
	// r.getBattleNHitTime(),
	// r.getBattleDayHitStat(),
	// r.getBattleDayNHitClearCnt(),
	// r.getBattleTotalStars(),
	// r.getEliteBattleLv(),
	// r.getEliteNxtLv(),
	// r.getEliteNxtLvState(),
	// r.getEliteNxtLvRevive(),
	// r.getEliteNxtLvDPos(),
	// r.getEliteTodayCnt(),
	// r.getEliteBuyCnt(),
	// r.getFmtCardAry(),
	// r.getFmtMainCardID(),
	// r.getFmtMainCardPos(),
	// r.getFmtPropPos1(),
	// r.getFmtPropPos2(),
	// r.getFmtPropPos3(),
	// r.getFmtPropPos4(),
	// r.getFmtPropPos5(),
	// r.getFmtPropPos6(),
	// r.getTmp(),
	// r.getWarFreeTime(),
	// r.getRoadMainCard(),
	// r.getRoadStarCnt(),
	// r.getRoadStarAchAry(),
	// r.getChanType(),
	// r.getChanLv(),
	// r.getChanPt(),
	// r.getChanAttrAry(),
	// r.getChanStarCnt(),
	// r.getChanReSetCnt(),
	// r.getGiftLvGot(),
	// r.getGiftCenterAry(),
	// r.getGiftOnLineGot(),
	// r.getGiftOnLineTime(),
	// r.getGiftOnLineTimeAcc(),
	// r.getGiftSignCnt(),
	// r.getGiftSignTime(),
	// r.getArenaUpdateTime(),
	// r.getHeartLastTime(),
	// r.getBroadLastTime(),
	// r.getSleepLastTime(),
	// r.getChatRecs(),
	// r.getChatLastViewTime(),
	// r.getMailSys(),
	// r.getMailLastViewAry(),
	// r.getActPveTimes(),
	// r.getActPveCnts(),
	// r.getBroadcastViewTime(),
	// r.getSnatched(),
	// r.getId());
	// }

	/**
	 * 更新战斗力及各个卡片位置的加成属性
	 * 
	 * @param r
	 */
	public void updateAttackAndFmtPropPos(Role r) {
		String sql = "update t_role set " +
		// " fmtPropPos1=?," +
		// " fmtPropPos2=?," +
		// " fmtPropPos3=?," +
		// " fmtPropPos4=?," +
		// " fmtPropPos5=?," +
		// " fmtPropPos6=?," +
				" attack=?," + " maxAttack=?" + " where id=?";
		executeUpdate(sql,
				// JSONUtil.toJson(r.getFmtPropPos1()),
				// JSONUtil.toJson(r.getFmtPropPos2()),
				// JSONUtil.toJson(r.getFmtPropPos3()),
				// JSONUtil.toJson(r.getFmtPropPos4()),
				// JSONUtil.toJson(r.getFmtPropPos5()),
				// JSONUtil.toJson(r.getFmtPropPos6()),
				r.getAttack(), r.getAttack() > r.getMaxAttack() ? r.getAttack() : r.getMaxAttack(), r.getId());
	}

	/**
	 * 更新每日登陆
	 * 
	 * @param Role
	 */
	public void updateDayLogin(Role r) {
		String sql = "update t_role set " + " dayLoginTime=?," + " daysContinue=?," + " broadcastViewTime=?,"
				+ " getFriendNailiCnt=0" + " where id=?";
		executeUpdate(sql, r.getDayLoginTime(), r.getDaysContinue(), r.getBroadcastViewTime(), r.getId());
	}

	/**
	 * 更新包限制
	 * 
	 * @param doc
	 */
	public void updateBagLimitAry(Role doc) {
		String sql = "update t_role set " + " bagLimitAry=?" + " where id=?";
		executeUpdate(sql, JSONUtil.toJson(doc.getBagLimitAry()), doc.getId());
	}

	public void updatePhyRss(Role doc) {
		String sql = "update t_role set " + " physVal=?," + " resisVal=?," + " addTimeAry=?" + " where id=?";
		executeUpdate(sql, doc.getPhysVal(), doc.getResisVal(), JSONUtil.toJson(doc.getAddTimeAry()), doc.getId());
	}

	/**
	 * 更新客栈休息时间
	 * 
	 * @param role
	 */
	public void updateSleepLastTime(Role role) {
		String sql = "update t_role set " + " sleepLastTime=?" + " where id=?";
		executeUpdate(sql, role.getSleepLastTime(), role.getId());
	}

	/**
	 * 更新查看广播时间
	 * 
	 * @param r
	 */
	public void updateBroadcastViewTime(Role r) {
		String sql = "update t_role set " + " broadcastViewTime=?" + " where id=?";
		executeUpdate(sql, r.getBroadcastViewTime(), r.getId());
	}

	/**
	 * 跟新心跳时间
	 * 
	 * @param r
	 */
	public void updateHeartTimes(Role r) {
		String sql = "update t_role set " + " heartLastTime=?" + " where id=?";
		executeUpdate(sql, r.getHeartLastTime(), r.getId());
	}

	/**
	 * 更新经验等级 限制
	 * 
	 * @param doc
	 */
	public void updateExp(Role doc) {
		String sql = "update t_role set " + " exp=?," + " level_up_last_time=?," + " level=?," + " propLimitAry=?"
				+ " where id=?";
		executeUpdate(sql, doc.getExp(), doc.getLevelUpLastTime(), doc.getLevel(),
				JSONUtil.toJson(doc.getPropLimitAry()), doc.getId());
	}

	/**
	 * 更新金币
	 * 
	 * @param r
	 * @param gold
	 */
	public void updateGold(Role r, int changeVal) {
		String sql = "update t_role set " + " gold=gold+?" + " where id=?";
		executeUpdate(sql, changeVal, r.getId());
	}

	/**
	 * 更新银币
	 * 
	 * @param r
	 * @param gold
	 */
	public void updateSilver(Role r, int changeVal) {
		String sql = "update t_role set " + " silver=silver+?" + " where id=?";
		executeUpdate(sql, changeVal, r.getId());
	}

	/**
	 * 更新侠魂
	 * 
	 * @param r
	 * @param soul
	 */
	public void updateSoul(Role r, int changeVal) {
		String sql = "update t_role set " + " soul=soul+?" + " where id=?";
		executeUpdate(sql, changeVal, r.getId());
	}

	/**
	 * 更新荣誉
	 * 
	 * @param r
	 * @param changeVal
	 */
	public void updateHonor(Role r, int changeVal) {
		String sql = "update t_role set " + " honor=honor+?" + " where id=?";
		executeUpdate(sql, changeVal, r.getId());
	}

	/**
	 * 更新vip等级
	 * 
	 * @param r
	 * @param vip
	 */
	public void updateVip(Role r, int changeVal) {
		String sql = "update t_role set " + " vip=vip+?" + " where id=?";
		executeUpdate(sql, changeVal, r.getId());
	}

	/**
	 * 更新灵石
	 * 
	 * @param r
	 * @param lingShi
	 */
	public void updateLingShi(Role r, int changeVal) {
		String sql = "update t_role set " + " lingShi=lingShi+?" + " where id=?";
		executeUpdate(sql, changeVal, r.getId());
	}

	/**
	 * 更新每日领取好友赠送的耐力计数
	 */
	public void updateGetFriendNailiCnt(Role doc) {
		String sql = "update t_role set " + " getFriendNailiCnt = ?" + " where id=?";
		executeUpdate(sql, doc.getGetFriendNailiCnt(), doc.getId());
	}

	/**
	 * 更新魅力值
	 */
	public void updateCharm(Role r, int changeVal) {
		String sql = "update t_role set " + " charm = charm+?" + " where id=?";
		executeUpdate(sql, changeVal, r.getId());
	}

	/**
	 * 更新阵容
	 * 
	 * @param r
	 */
	public void updateLineup(Role r) {
		String sql = "update t_role set " + " fmtCardAry=?" + " where id=?";
		executeUpdate(sql, JSONUtil.toJson(r.getFmtCardAry()), r.getId());

	}

	public void updateMainCardValues(Role r) {
		String sql = "update t_role set " + " cls=?" + " where id=?";
		executeUpdate(sql, r.getCls(), r.getId());
	}

	public void updateGuideStep(Role r) {
		String sql = "update t_role set " + " guideStep=?" + " where id=?";
		executeUpdate(sql, r.getGuideStep(), r.getId());
	}

	/**
	 * 更新当前聚元等级
	 * 
	 * @param doc
	 */
	public void updateCurCollLv(Role doc) {
		String sql = "update t_role set " + " curCollLv=?" + " where id=?";
		executeUpdate(sql, doc.getCurCollLv(), doc.getId());
	}

	/**
	 * 查询
	 */
	public Role queryByAccount(String account) {
		return queryForObject("select * from t_role where account=?", HANDLER, account);
	}

	public Role queryById(int roleId) {
		return queryForObject("select * from t_role where id=?", HANDLER, roleId);
	}

	public Role queryByIdFromDB(int roleId) {
		return queryForObject("select * from t_role where id=?", HANDLER, roleId);
	}

	public Role queryByIdFromDBForUpdate(int roleId) {
		return queryForObject("select * from t_role where id=? for update", HANDLER, roleId);
	}

	/**
	 * 查询列表
	 */
	public List<Role> queryList() {
		return queryForList("select * from t_role", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_role where id=?", HANDLER, id);
	}

	/**
	 * 是否存在此帐号及玩家名
	 * 
	 * @param account
	 * @param name
	 * @return
	 */
	public Role queryIsExist(String account, String name) {
		return queryForObject("select * from t_role where account=? or name = ?", HANDLER, account, name);
	}

	public void updateSetParam(Role doc) {
		String sql = "update t_role set " + "helpStoryStep=?" + " where id=?";
		executeUpdate(sql, JSONUtil.toJson(doc.getHelpStoryStep()), doc.getId());
	}

	public static void main(String[] args) {
		String arr[] = { "Account", "Name", "Type", "HeartLastTime", "GiftOnLineTime", "BroadcastViewTime",
				"DayLoginTime", "BagLimitAry", "FmtPropPos1", "FmtPropPos2", "FmtPropPos3", "FmtPropPos4",
				"FmtPropPos5", "FmtPropPos6", "Level", "Vip", "Cls", "Level", "Silver", "Gold", "Exp", "PhysVal",
				"ResisVal", "CreateTime", "PropLimitAry", "ResId", "Sex", "Attack" };
		String out = "";
		String out1 = ")values(";
		String out2 = "";
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			String str1 = str.toLowerCase();
			out += str1 + ",";
			out1 += "?,";
			out2 += "r.get" + str + "(),\n";
		}

		System.out.println(out);
		System.out.println(out1);
		System.out.println(out2);

	}

	public List<Role> querySnatchRoles(String acc, int minLv, int maxLV, int itemId) {
		return queryForList(
				"SELECT r.* FROM t_role AS r INNER " + "JOIN t_role_gong_item AS gi " + "JOIN t_role_snatch AS rs "
						+ "ON gi.role_id = r.id and rs.role_id = r.id " + "WHERE gi.item_id = ? " + "and r.level >= ? "
						+ "and r.level <= ? " + "and r.account != ? " + "and rs.war_free_time < UNIX_TIMESTAMP()",
				HANDLER, itemId, minLv, maxLV, acc);

	}

	public void updateChat(Role doc) {
		String sql = "update t_role set " + " chatLastViewTime=?" + " where id=?";
		executeUpdate(sql, JSONUtil.toJson(doc.getChatLastViewTime()), doc.getId());
	}

	public void updateMailLastTime(Role doc) {
		String sql = "update t_role set " + " mailLastViewAry=?" + " where id=?";
		executeUpdate(sql, JSONUtil.toJson(doc.getMailLastViewAry()), doc.getId());
	}

	public void updateByUpdateFilter(RoleUpdateFilter filter) {
		if (filter.getFieldValue().size() == 0) {
			return;
		}
		List<Object> val = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("update t_role set ");
		Map<String, Object> fieldValues = filter.getFieldValue();
		for (Entry<String, Object> fieldValue : fieldValues.entrySet()) {
			sql.append(" " + fieldValue.getKey() + " = ?, ");
			val.add(fieldValue.getValue());
		}
		sql.append(" id = id where account=?");
		val.add(filter.getAccount());
		executeUpdate(sql.toString(), val.toArray());
	}

	private static ResultSetRowHandler<RoleIdName> ID_NAME_HANDLER = new ResultSetRowHandler<RoleIdName>() {
		@Override
		public RoleIdName handleRow(ResultSetRow row) throws Exception {
			RoleIdName r = new RoleIdName();
			r.setRoleId(row.getInt("id"));
			r.setAccount(row.getString("account"));
			r.setRoleName(row.getString("name"));
			r.setNickName(row.getString("nickname"));
			return r;
		}
	};

	public RoleIdName queryIdNameByAcc(String acc) {
		return queryForObject("select id,account,name,nickname from t_role where account=?", ID_NAME_HANDLER, acc);
	}

	/**
	 * 查询两个等级之间的
	 */
	public List<Role> queryBetweenLevel(String s, int minLv, int maxLV) {
		return queryForList(
				"SELECT * FROM t_role " + "WHERE level >= ? " + "and level <= ? " + "and id not in " + s + "limit 50",
				HANDLER, minLv, maxLV);
	}

	/**
	 * 查询两战斗力之间的
	 */
	public List<SwordRolePart> queryBetweenAttrack(List<SwordRolePart> removeRoles, Role self, int minAttrack,
			int maxAttrack) {
		StringBuilder s = new StringBuilder("(");
		for (Iterator<SwordRolePart> iterator = removeRoles.iterator(); iterator.hasNext();) {
			SwordRolePart role = (SwordRolePart) iterator.next();
			s.append(role.getId()).append(",");
		}
		s.append(self.getId()).append(")");
		return queryForList("SELECT id,level,account,attack FROM t_role " + "WHERE attack >= ? " + "and attack <= ? "
				+ "and id not in " + s.toString() + "", HANDLERSword, minAttrack, maxAttrack);
	}

	public String queryServerKeyByAccount(String acc) {
		return queryForString("select server_key from t_role where account=?", acc);
	}

	public void updateLoginInfoByAccount(Role role, String serverKey) {
		String sql = "update t_role set " + " server_id=?," + " server_key=?," + " last_device_type=?,"
				+ " last_device_version=?," + " last_device_uuid=?" + " where account=?";
		executeUpdate(sql, role.getServerId(), serverKey, role.getLastDeviceType(), role.getLastDeviceVersion(),
				role.getLastDeviceUUID(), role.getAccount());
	}

	/**
	 * 
	 */
	public void kickRoles() {
		execute("update t_role set server_key=?", "kick");
	}

	public void updateAddPopualVal(Role r, int val) {
		String sql = "update t_role set " + " popual=popual+? " + " where id=?";
		executeUpdate(sql, val, r.getId());
	}

	public void updateAddHunYuVal(Role r, int val) {
		String sql = "update t_role set " + " hunYuVal=hunYuVal+? " + " where id=?";
		executeUpdate(sql, val, r.getId());
	}

	///////////////////////////////////////////////////////////////////////////////////////

	public void addLoginLog(int roldId, IOSDeviceInfo device) {
		String table = "t_role_login_log_" + (roldId % 10);
		String sql = "insert into " + table + "("
				+ "role_id,device_type,device_version,device_uuid,login_time)values(?,?,?,?,now())";
		execute(sql, roldId, device == null ? "" : device.getDeviceType(),
				device == null ? "" : device.getDeviceVersion(), device == null ? "" : device.getDeviceUUID());
	}

	///////////////////////////////////////////////////////////////////////////////////////

	public int queryRobotSize() {
		return queryForInteger("select count(0) from t_role where type=?", Role.TYPE_ROBOT);
	}

	public void clearRobot() {
		execute("delete from t_role_shop where role_id in (select id from t_role where type = ?)", Role.TYPE_ROBOT);
		execute("delete from t_role_battle where role_id in (select id from t_role where type = ?)", Role.TYPE_ROBOT);
		execute("delete from t_role_activity_battle where role_id in (select id from t_role where type = ?)",
				Role.TYPE_ROBOT);
		execute("delete from t_role_elite_battle where role_id in (select id from t_role where type = ?)",
				Role.TYPE_ROBOT);
		execute("delete from t_role_card where role_id in (select id from t_role where type = ?)", Role.TYPE_ROBOT);
		execute("delete from t_role where type = ?", Role.TYPE_ROBOT);
	}

	/**
	 * @param acc
	 * @return
	 */
	public String queryVersionByAccount(String acc) {
		return queryForString("select version from t_role where account=?", acc);
	}

	public void updateVersionByAccount(String acc, String version) {
		String sql = "update t_role set " + " version=?" + " where account=?";
		executeUpdate(sql, version, acc);
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	private static ResultSetRowHandler<Integer> ROLE_ID_HANDLER = new ResultSetRowHandler<Integer>() {
		@Override
		public Integer handleRow(ResultSetRow row) throws Exception {
			return row.getInt("id");
		}
	};

	/**
	 * @param integer
	 * @param integer2
	 * @return
	 */
	public List<Integer> queryRoldIdByLevelRankInterval(int min, int max) {
		return queryForList(
				"select * from t_role where type != ? ORDER BY level DESC,exp DESC,createTime DESC LIMIT ?,?",
				ROLE_ID_HANDLER, Role.TYPE_ROBOT, min - 1, max - min + 1);
	}

	/**
	 * @param integer
	 * @param integer2
	 * @return
	 */
	public List<Integer> queryRoldIdByLevelInterval(int min, int max) {
		return queryForList("select id from t_role where type != ? and level>= ? and level <=?", ROLE_ID_HANDLER,
				Role.TYPE_ROBOT, min, max);
	}

	/**
	 * @return
	 */
	public int queryRoleSize() {
		return queryForInteger("select count(0) from t_role where type != ?", Role.TYPE_ROBOT);
	}

	private static ResultSetRowHandler<Role> AR_HANDLER = new ResultSetRowHandler<Role>() {
		@Override
		public Role handleRow(ResultSetRow row) throws Exception {
			Role r = new Role();
			r.setId(row.getInt("id"));
			r.setLevel(row.getInt("level"));
			return r;
		}
	};

	/**
	 * @param list
	 * @return
	 */
	public Map<Integer, Integer> queryArenaRewardRole(List<RoleArena> list) {
		StringBuilder str = new StringBuilder();
		int le = 0;
		for (RoleArena roleArena : list) {
			str.append(le == 0 ? "" : ",").append(roleArena.getRoleId());
			le++;
		}
		List<Role> roles = queryForList(
				"select id,level from t_role where id in (" + str.toString() + ") and type != ?", AR_HANDLER,
				Role.TYPE_ROBOT);

		Map<Integer, Integer> res = new HashMap<Integer, Integer>();
		for (Role role : roles) {
			res.put(role.getId(), role.getLevel());
		}
		return res;
	}

	private static ResultSetRowHandler<Role> FRIEND_HANDLER = new ResultSetRowHandler<Role>() {
		@Override
		public Role handleRow(ResultSetRow row) throws Exception {
			Role r = new Role();
			r.setId(row.getInt("id"));
			r.setLevel(row.getInt("level"));
			r.setAccount(row.getString("account"));
			r.setName(row.getString("name"));
			r.setCharm(row.getInt("charm"));
			r.setAttack(row.getInt("attack"));
			r.setHeartLastTime(row.getInt("heartLastTime"));
			r.setResId(row.getInt("resId"));
			r.setCls(row.getInt("cls"));
			return r;
		}
	};

	/**
	 * 推荐好友
	 */
	public List<Role> queryRecomFriend(Role doc, int num, List<Integer> levelLimit, List<Integer> allFriendId) {
		StringBuilder sql = new StringBuilder(
				"SELECT id,account,name,level,charm,attack,heartLastTime,resId,cls from t_role WHERE ");
		if (allFriendId.size() > 0) {
			sql.append(" id not in (");
			for (int i = 0; i < allFriendId.size(); i++) {
				sql.append(i == 0 ? "" : ",").append(allFriendId.get(i));
			}
			sql.append(") and ");
		}

		sql.append("type != ? and level>=? and level<=? ORDER BY RAND() DESC limit ? ");
		List<Role> res = queryForList(sql.toString(), FRIEND_HANDLER, Role.TYPE_ROBOT, levelLimit.get(0),
				levelLimit.get(1), num);
		return res;
	}

	/**
	 * 搜索好友
	 */
	public List<Role> querySearchFriend(Role doc, int type, String content, int searchNum, int flag) {
		if (type == RoleFriend.FRIEND_SEARCH_BY_ID) {
			int id = Integer.parseInt(content);
			String sql = "SELECT id,resId,cls,account,name,charm,level,attack,heartLastTime from t_role WHERE type != ? and id=? ";
			List<Role> res = queryForList(sql, FRIEND_HANDLER, Role.TYPE_ROBOT, id);
			return res;
		} else {
			String sql = "SELECT id,resId,cls,account,name,level,charm,attack,heartLastTime from t_role WHERE "
					+ "type != ? and id>? and name LIKE '%" + content + "%' ORDER BY id LIMIT ?";
			List<Role> res = queryForList(sql, FRIEND_HANDLER, Role.TYPE_ROBOT, flag, searchNum);
			return res;
		}

	}

	private static ResultSetRowHandler<Role> ID_HEARTTIME_HANDLER = new ResultSetRowHandler<Role>() {
		@Override
		public Role handleRow(ResultSetRow row) throws Exception {
			Role r = new Role();
			r.setId(row.getInt("id"));
			r.setAccount(row.getString("account"));
			r.setHeartLastTime(row.getInt("heartLastTime"));
			return r;
		}
	};

	public List<Role> queryIdAndHeartTime(List<Integer> friendIdList) {
		if (friendIdList.size() == 0) {
			return new ArrayList<Role>();
		}

		StringBuilder sql = new StringBuilder("SELECT id,account,heartLastTime from t_role WHERE id in (");
		for (int i = 0; i < friendIdList.size(); i++) {
			sql.append(i == 0 ? "" : ",").append(friendIdList.get(i));
		}
		sql.append(")");
		List<Role> res = queryForList(sql.toString(), ID_HEARTTIME_HANDLER);

		return res;
	}

	/**
	 * 根据战力查询
	 */
	public List<Role> queryByAttrack(List<Role> roleList, Role self, int minAttrack, int maxAttrack,
			List<RoleTournament> players) {
		StringBuilder s = new StringBuilder("(");
		int i = 0;
		for (Iterator<Role> iterator = roleList.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			s.append(role.getId()).append(",");
			i++;
		}
		for (Iterator<RoleTournament> iterator = players.iterator(); iterator.hasNext();) {
			RoleTournament roleTournament = (RoleTournament) iterator.next();
			s.append(roleTournament.getRoleId()).append(",");
			i++;
		}
		s.append(self.getId()).append(")");
		if (maxAttrack > 0) {
			return queryForList("SELECT * FROM t_role " + "WHERE maxAttack > ? " + "and maxAttack <= ? "
					+ "and id not in " + s.toString() + "", HANDLER, minAttrack, maxAttrack);
		} else {
			return queryForList("SELECT * FROM t_role " + "WHERE maxAttack > ? " + "and id not in " + s.toString() + "",
					HANDLER, minAttrack);
		}
	}

	/**
	 * 修改用户帮派名
	 */
	public void updateRoleFaction(Role role, String unionName) {
		String sql = "update t_role set " + " faction=?" + " where id=?";
		executeUpdate(sql, unionName, role.getId());
	}
}