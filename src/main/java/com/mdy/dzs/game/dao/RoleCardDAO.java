package com.mdy.dzs.game.dao;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.domain.Prop;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.filter.RoleCardFilter;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

/**
 * 玩家卡牌DAO
 * 
 * @author 房曈
 *
 */
public class RoleCardDAO extends ConnectionResourceDAO {

	private static ResultSetRowHandler<RoleCard> HANDLER = new ResultSetRowHandler<RoleCard>() {
		@SuppressWarnings("unchecked")
		@Override
		public RoleCard handleRow(ResultSetRow row) throws Exception {
			RoleCard rc = new RoleCard();
			rc.setId(row.getInt("id"));
			rc.setRoleId(row.getInt("role_id"));
			rc.setPos(row.getInt("pos"));
			rc.setSubpos(row.getInt("subpos"));
			rc.setType(row.getInt("type"));
			rc.setResId(row.getInt("resId"));
			rc.setLevel(row.getInt("level"));
			rc.setStar(row.getInt("star"));
			rc.setCls(row.getInt("cls"));
			rc.setCurExp(row.getInt("curExp"));
			List<Prop> props = JSONUtil.fromJsonList(row.getString("props"), Prop.class);
			rc.setProps(props);
			rc.setRelation(JSONUtil.fromJson(row.getString("relation"), List.class));
			rc.setShenPt(row.getInt("shenPt"));
			rc.setShenIDAry(JSONUtil.fromJson(row.getString("shenIDAry"), List.class));
			rc.setLock(row.getInt("lock"));
			rc.setBattle(JSONUtil.fromJson(row.getString("battle"), List.class));
			rc.setShenLvAry(JSONUtil.fromJson(row.getString("shenLvAry"), List.class));
			return rc;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoleCard
	 */
	public void add(RoleCard rc) {
		String sql = "insert into t_role_card(" + "role_id," + "pos," + "subpos," + "type," + "resId," + "level,"
				+ "star," + "cls," + "curExp," + "props," + "relation," + "shenPt," + "shenIDAry," + "shenLvAry"
				+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int id = executeWithGenKey(sql, rc.getRoleId(), rc.getPos(), rc.getSubpos(), rc.getType(), rc.getResId(),
				rc.getLevel(), rc.getStar(), rc.getCls(), rc.getCurExp(), JSONUtil.toJson(rc.getProps()),
				JSONUtil.toJson(rc.getRelation()), 0, JSONUtil.toJson(rc.getShenIDAry()),
				JSONUtil.toJson(rc.getShenLvAry()));
		rc.setId(id);
	}

	/**
	 * 更新
	 * 
	 * @param RoleCard
	 */
	public void update(RoleCard rc) {
		if (rc == null)
			return;
		if (rc.getPos() == 0) {
			rc.setPos(0);
		}
		String sql = "update t_role_card set " + " role_id=?," + " type=?," + " resId=?," + " star=?," + " cls=?,"
				+ " props=?," + " relation=?," + " shenPt=?," + " shenIDAry=?," + " shenLvAry=? " + " where id=?";
		executeUpdate(sql, rc.getRoleId(), rc.getType(), rc.getResId(), rc.getStar(), rc.getCls(),
				JSONUtil.toJson(rc.getProps()), JSONUtil.toJson(rc.getRelation()), rc.getShenPt(),
				JSONUtil.toJson(rc.getShenIDAry()), JSONUtil.toJson(rc.getShenLvAry()), rc.getId());
	}

	public void updateLevel(RoleCard rc) {
		String sql = "update t_role_card set " + " level=?," + " curExp=?" + " where id=?";
		executeUpdate(sql, rc.getLevel(), rc.getCurExp(), rc.getId());
	}

	public void updateLock(RoleCard rc) {
		String sql = "update t_role_card set " + " `lock`=?" + " where id=?";
		executeUpdate(sql, rc.getLock(), rc.getId());
	}

	/**
	 * 查询
	 */
	public RoleCard query(int id) {
		return queryForObject("select * from t_role_card where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<RoleCard> queryList() {
		return queryForList("select * from t_role_card", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(RoleCard rc) {
		execute("delete  from t_role_card where id=?", rc.getId());
	}

	public List<RoleCard> queryListByRoleId(int roleId) {
		return queryForList("select * from t_role_card where role_id=? ORDER BY id", HANDLER, roleId);
	}

	/**
	 * 查找相同资源卡片
	 * 
	 * @param acc
	 * @param resId
	 * @return
	 */
	public List<RoleCard> querySameResCard(int roleId, int resId) {
		return queryForList("select * from t_role_card where role_id=? and resId = ? ORDER BY id", HANDLER, roleId,
				resId);
	}

	/**
	 * 查找相同资源卡片--根据等级
	 * 
	 * @param acc
	 * @param resId
	 * @return
	 */
	public List<RoleCard> querySameResCardByLevel(int roleId, int resId) {
		return queryForList(
				"select * from t_role_card where role_id=? and resId = ? and pos = 0 and level <= 1 and `lock` = 0 and battle='[]' ORDER BY id",
				HANDLER, roleId, resId);
	}

	/**
	 * 查找相同资源卡片数量
	 * 
	 * @param acc
	 * @param resId
	 * @return
	 */
	public int querySameResCardCount(int roleId, int resId) {
		return queryForInteger("select count(0) from t_role_card where role_id=? and resId = ? ", roleId, resId);
	}

	/**
	 * 查找已经上阵列表
	 * 
	 * @param acc
	 * @return
	 */
	public List<RoleCard> queryLineupListByRoldId(int roleId) {
		return queryForList("select * from t_role_card where role_id=? and pos > 0 order by id", HANDLER, roleId);
	}

	public RoleCard queryByRoleIdPos(int roleId, int pos) {
		return queryForObject("select * from t_role_card where role_id=? and pos = ?", HANDLER, roleId, pos);
	}

	public List<RoleCard> queryListByIds(List<Integer> fmtCardAry) {
		RoleCardFilter filter = new RoleCardFilter();
		filter.setIds(fmtCardAry);
		return queryListByFilter(filter);
	}

	public List<RoleCard> queryListByFilter(RoleCardFilter filter) {
		List<Object> val = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from t_role_card ");
		sql.append(" where 1=1 ");

		if (filter.getRoleId() != null) {
			sql.append(" and role_id=? ");
			val.add(filter.getRoleId());
		}
		if (filter.getLock() != null) {
			sql.append(" and `lock` = ? ");
			val.add(filter.getLock());
		}

		if (filter.getIds() != null) {
			String ids = "";
			for (Integer integer : filter.getIds()) {
				if (integer != 0) {
					ids += integer + ",";
				}
			}
			ids = ids.substring(0, ids.length() - 1);
			sql.append(" and id in (" + ids + ") ");
		}

		if (filter.getResIds() != null) {
			String ids = "";
			for (Integer integer : filter.getResIds()) {
				if (integer != 0) {
					ids += integer + ",";
				}
			}
			ids = ids.substring(0, ids.length() - 1);
			sql.append(" and resId in (" + ids + "+) ");
		}

		// 排序方式

		sql.append(" limit ");
		sql.append(filter.getStartOfPage());
		sql.append(" ,");
		sql.append(filter.getPageSize());
		return queryForList(sql.toString(), HANDLER, val.toArray());
	}

	public List<RoleCard> queryListByLevel(int roleId, int level) {
		return queryForList("select * from t_role_card where  role_id=? and level>=?", HANDLER, roleId, level);
	}

	public void update(RoleCard rc, int oldPos) {
		if (rc == null)
			return;
		if (rc.getPos() == 0) {
			rc.setPos(0);
		}
		String sql = "update t_role_card set " + " pos=?," + " subpos=?" + " where id=?";
		executeUpdate(sql, rc.getPos(), rc.getSubpos(), rc.getId());
	}

	/**
	 * 查询背包数量
	 * 
	 * @param acc
	 * @return
	 */
	public int queryCountByAccount(int roleId) {
		return queryForInteger("select count(0) from t_role_card where role_id=?", roleId);
	}

	/**
	 * @param id
	 * @param ids
	 */
	public void deleteCards(int roleId, List<RoleCard> list) {
		StringBuilder sql = new StringBuilder("delete from t_role_card where role_id=?");
		String str = "";
		for (RoleCard rc : list) {
			str += rc.getId() + ",";
		}
		str = str.substring(0, str.length() - 1);
		sql.append(" and id in (" + str + ") ");
		execute(sql.toString(), roleId);
	}

	/**
	 * @param id
	 * @param fmtMainCardID
	 * @param exp
	 * @param level
	 */
	public void updateMainExp(int roleId, int cardId, int exp, int level) {
		String sql = "update t_role_card set " + " curExp=?," + " level=?" + " where id=? and role_id=?";
		executeUpdate(sql, exp, level, cardId, roleId);
	}

	/**
	 * @param curCard
	 */
	public void addRecord(RoleCard curCard) {
		execute("insert into t_role_card_record set role_id=?,card_id=?,cnt=1 " + "on duplicate key update cnt=cnt+1",
				curCard.getRoleId(), curCard.getResId());
	}

	private static ResultSetRowHandler<Integer> ROCARD_HANDLER = new ResultSetRowHandler<Integer>() {
		@Override
		public Integer handleRow(ResultSetRow row) throws Exception {
			return row.getInt("card_id");
		}
	};

	// 图鉴查询卡牌id列表
	public List<Integer> queryRecordCardId(int roleId) {
		return queryForList("SELECT card_id FROM t_role_card_record WHERE role_id = ?", ROCARD_HANDLER, roleId);
	}

	/**
	 * 更新是否在阵型上
	 * 
	 * @param battle
	 * @param id
	 */
	public void updateBattle(List<Integer> battle, int id, int roleId) {
		String sql = "update t_role_card set " + " battle=?" + " where id=?";
		executeUpdate(sql, JSONUtil.toJson(battle), id);
	}
}