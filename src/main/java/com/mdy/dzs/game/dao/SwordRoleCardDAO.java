package com.mdy.dzs.game.dao;

import java.util.List;

import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.swordfight.SwordRoleCard;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 论剑角色DAO
 * 
 * @author zhou
 *
 */
public class SwordRoleCardDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<SwordRoleCard> HANDLER = new ResultSetRowHandler<SwordRoleCard>() {
		@Override
		@SuppressWarnings("unchecked")
		public SwordRoleCard handleRow(ResultSetRow row) throws Exception {
			SwordRoleCard src = new SwordRoleCard();
			src.setId(row.getInt("id"));
			src.setAccount(row.getString("account"));
			src.setType(row.getInt("type"));
			src.setLifeRate(row.getInt("life_rate"));
			src.setInitLife(row.getInt("init_life"));
			src.setAnger(row.getInt("anger"));
			src.setCls(row.getInt("cls"));
			src.setResId(row.getInt("resId"));
			src.setPos(row.getInt("pos"));
			src.setShenIDAry(JSONUtil.fromJson(row.getString("shenIDAry"), List.class));
			src.setShenLvAry(JSONUtil.fromJson(row.getString("shenLvAry"), List.class));
			List<Prop> props = JSONUtil.fromJsonList(row.getString("props"), Prop.class);
			src.setProps(props);
			src.setOrder(row.getInt("order"));
			src.setStar(row.getInt("star"));
			src.setLevel(row.getInt("level"));
			src.setRole_card_id(row.getInt("role_card_id"));
			src.setSword_role_id(row.getInt("sword_role_id"));
			src.setCreateTime(row.getTimestamp("createTime"));
			src.setUpdateTime(row.getTimestamp("updateTime"));
			return src;
		}
	};

	/**
	 * 添加
	 * 
	 * @param SwordRoleCard
	 */
	public void add(SwordRoleCard src) {
		String sql = "insert into t_sword_role_card(" + "account," + "type," + "life_rate," + "init_life," + "anger,"
				+ "cls," + "resId," + "pos," + "shenIDAry," + "shenLvAry," + "props," + "`order`," + "star," + "level,"
				+ "role_card_id," + "sword_role_id," + "createTime," + "updateTime"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now())";
		String shenId = JSONUtil.toJson(src.getShenIDAry());
		String shenLv = JSONUtil.toJson(src.getShenLvAry());
		String strProps = JSONUtil.toJson(src.getProps());
		int id = executeWithGenKey(sql, src.getAccount(), src.getType(), src.getLifeRate(), src.getInitLife(),
				src.getAnger(), src.getCls(), src.getResId(), src.getPos(), shenId, shenLv, strProps, src.getOrder(),
				src.getStar(), src.getLevel(), src.getRole_card_id(), src.getSword_role_id());
		src.setId(id);
	}

	/**
	 * 更新
	 * 
	 * @param SwordRoleCard
	 */
	public void update(SwordRoleCard src) {
		String sql = "update t_sword_role_card set " + " account=?," + " type=?," + " life_rate=?," + " init_life=?,"
				+ " anger=?," + " cls=?," + " resId=?," + " pos=?," + " shenIDAry=?," + " shenLvAry=?," + " props=?,"
				+ " `order`=?," + " star=?," + " level=?," + " role_card_id=?," + " sword_role_id=?,"
				+ " updateTime=now() " + " where id=?";
		executeUpdate(sql, src.getAccount(), src.getType(), src.getLifeRate(), src.getInitLife(), src.getAnger(),
				src.getCls(), src.getResId(), src.getPos(), JSONUtil.toJson(src.getShenIDAry()),
				JSONUtil.toJson(src.getShenLvAry()), JSONUtil.toJson(src.getProps()), src.getOrder(), src.getStar(),
				src.getLevel(), src.getRole_card_id(), src.getSword_role_id(), src.getId());
	}

	/**
	 * 更新生命怒气
	 * 
	 * @param SwordRoleCard
	 */
	public void updateLifeAnger(SwordRoleCard src) {
		String sql = "update t_sword_role_card set " + " life_rate=?," + " anger=?," + " updateTime=now() "
				+ " where id=?";
		executeUpdate(sql, src.getLifeRate(), src.getAnger(), src.getId());
	}

	/**
	 * 查询
	 */
	public SwordRoleCard query(int id) {
		return queryForObject("select * from t_sword_role_card where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<SwordRoleCard> queryList() {
		return queryForList("select * from t_sword_role_card", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(SwordRoleCard src) {
		execute("delete  from t_sword_role_card where id=?", src.getId());
	}

	/**
	 * 根据账户和type找到用户的卡牌
	 * 
	 * @param account
	 * @param type
	 * @return
	 */
	public List<SwordRoleCard> queryByAccount(String account, int type) {
		return queryForList("select * from t_sword_role_card where account=? and type=?", HANDLER, account, type);
	}

	/**
	 * 根据卡牌ID和type找到用户的卡牌
	 * 
	 * @param roleCardId
	 * @param type
	 * @return
	 */
	public SwordRoleCard queryByRoleCardId(int queryByRoleCardId, int type) {
		return queryForObject("select * from t_sword_role_card where role_card_id=? and type=?", HANDLER,
				queryByRoleCardId, type);
	}

	/**
	 * 根据论剑角色id查找
	 * 
	 * @param sword_role_id
	 * @return
	 */
	public List<SwordRoleCard> queryBySwordRoleId(int sword_role_id) {
		return queryForList("select * from t_sword_role_card where sword_role_id=?", HANDLER, sword_role_id);
	}

	/**
	 * 根绝论剑角色id删除卡牌
	 * 
	 * @param sword_role_id
	 */
	public void deleteBySwordRoleId(int sword_role_id) {
		execute("delete  from t_sword_role_card where sword_role_id=?", sword_role_id);
	}
}