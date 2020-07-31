package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.union.RoleUnion;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;
import com.mdy.sharp.util.JSONUtil;

public class RoleUnionDAO extends ConnectionResourceDAO {
	/**
	 * 
	 */
	private static ResultSetRowHandler<RoleUnion> HANDLER = new ResultSetRowHandler<RoleUnion>() {
		@Override
		public RoleUnion handleRow(ResultSetRow row) throws Exception {
			RoleUnion rn = new RoleUnion();
			rn.setId(row.getInt("id"));
			rn.setUnionId(row.getInt("union_id"));
			rn.setRoleId(row.getInt("role_id"));
			rn.setJopType(row.getInt("jop_type"));
			rn.setIntoTime(row.getTimestamp("into_time"));
			rn.setOutTime(row.getTimestamp("out_time"));
			rn.setTotalContribute(row.getInt("total_contribute"));
			rn.setLastContribute(row.getInt("last_contribute"));
			rn.setWeeklyBenefits(row.getTimestamp("weekly_benefits"));
			rn.setBarbecue(row.getTimestamp("barbecue"));
			rn.setDekaronNum(row.getInt("dekaron_num"));
			rn.setDekaronTime(row.getTimestamp("dekaron_time"));
			rn.setDefenseNum(row.getInt("defense_num"));
			rn.setDefenseTime(row.getTimestamp("defense_time"));
			rn.setGifttime(row.getString("gift_time"));
			rn.setGiftnum(row.getInt("gift_num"));
			rn.setState(row.getInt("state"));
			rn.setCoverTime(row.getTimestamp("cover_time"));
			rn.setFreeworknum(row.getInt("freework_num"));
			rn.setBuyworknum(row.getInt("buywork_num"));
			rn.setStarworktime(row.getTimestamp("starwork_time"));
			rn.setWorktype(row.getInt("work_type"));
			rn.setOvertimeflag(row.getInt("overtime_flag"));
			rn.setBuynum(row.getInt("buy_num"));
			rn.setRank(row.getInt("rank"));
			rn.setCreatTime(row.getTimestamp("create_time"));
			rn.setUpdateTime(row.getTimestamp("update_time"));
			rn.setLastContributionTime(row.getTimestamp("last_contribute_time"));
			rn.setConType(row.getInt("con_type"));
			rn.setCostMoney(row.getInt("cost_money"));
			rn.setShopWorkrdReward(row.getInt("shop_workrd_reward"));
			rn.setIsOver(row.getInt("is_over"));
			rn.setExchangeTime(row.getTimestamp("exchange_time"));
			rn.setShopIds(row.getString("shop_ids"));
			return rn;
		}
	};

	/**
	 * 创建玩家帮派信息
	 * 
	 * @param rolenion
	 */
	public void createroleUnion(RoleUnion roleUnion) {
		String sql = "insert into t_role_union (" + "union_id," + "role_id," + "jop_type," + "into_time," + "state,"
				+ "rank," + "last_contribute_time," + "freework_num," + "buy_num" + ")values(?,?,?,?,?,?,?,?,?)";
		execute(sql, roleUnion.getUnionId(), roleUnion.getRoleId(), roleUnion.getJopType(), roleUnion.getIntoTime(),
				roleUnion.getState(), roleUnion.getRank(), roleUnion.getLastContributionTime(),
				roleUnion.getFreeworknum(), roleUnion.getBuynum());
	}

	/**
	 * 查询玩家帮派信息
	 * 
	 * @param roleId
	 * @return
	 */
	public RoleUnion queryRoleUnionById(int roleId) {
		return queryForObject("select * from t_role_union where role_id=?", HANDLER, roleId);
	}

	/**
	 * 查询帮派人数
	 * 
	 * @param unionId
	 * @return
	 */
	public int queryAllMemberNum(int unionId) {
		return queryForInteger("select count(0) from t_role_union where union_id=? ", unionId);
	}

	/**
	 * 修改玩家帮派信息
	 * 
	 * @param roleunion
	 */
	public void updateRoleUnion(RoleUnion roleUnion) {
		String sql = "update t_role_union set " + " union_id=?," + " jop_type=?," + " last_contribute_time=?,"
				+ " freework_num=?," + " dekaron_num=?," + " into_time=?," + " defense_num=?," + " con_type=?,"
				+ " state=?" + " where role_id=?";
		executeUpdate(sql, roleUnion.getUnionId(), roleUnion.getJopType(), roleUnion.getLastContributionTime(),
				roleUnion.getFreeworknum(), roleUnion.getDekaronNum(), roleUnion.getIntoTime(),
				roleUnion.getDefenseNum(), roleUnion.getConType(), roleUnion.getState(), roleUnion.getRoleId());
	}

	// 修改玩家作坊信息
	public void updateRoleProduct(RoleUnion ru) {
		String sql = "update t_role_union set " + " freework_num = ?," + " buywork_num =  ?," + " buy_num = ?,"
				+ " is_over = ?," + " shop_workrd_reward = ?," + " starwork_time = ?," + " work_type = ?,"
				+ "overtime_flag = ?" + " where role_id=?";
		executeUpdate(sql, ru.getFreeworknum(), ru.getBuyworknum(), ru.getBuynum(), ru.getIsOver(),
				ru.getShopWorkrdReward(), ru.getStarworktime(), ru.getWorktype(), ru.getOvertimeflag(), ru.getRoleId());
	}

	/**
	 * 查询帮派所有成员
	 * 
	 * @param unionId
	 * @return
	 */
	public List<RoleUnion> queryAllMember(int unionId) {
		return queryForList("SELECT * FROM t_role_union where union_id =? ", HANDLER, unionId);
	}

	/**
	 * 清除玩家帮派信息
	 * 
	 * @param roleUnion
	 */
	public void clearRoleUnion(RoleUnion roleUnion, int unionId) {

		String sql = "update t_role_union set " + " union_id=?," + " jop_type=?," + " out_time=?," + " into_time=?,"
				+ " dekaron_num=?," + " defense_num=?," + " gift_num=?," + " con_type=?," + " starwork_time=?,"
				+ " shop_workrd_reward=?," + " is_over=?," + " cover_time=?," + " state=?" + " where role_id=?";
		executeUpdate(sql, roleUnion.getUnionId(), roleUnion.getJopType(), roleUnion.getOutTime(),
				roleUnion.getIntoTime(), roleUnion.getDekaronNum(), roleUnion.getDefenseNum(), roleUnion.getGiftnum(),
				roleUnion.getConType(), roleUnion.getStarworktime(), roleUnion.getShopWorkrdReward(),
				roleUnion.getIsOver(), roleUnion.getCoverTime(), roleUnion.getState(), roleUnion.getRoleId());
	}

	/**
	 * 帮主自荐
	 * 
	 * @param roleId
	 * @param time
	 */
	public void updatecoverLeader(int roleId, Date time, int unionId) {
		String sql = "update t_role_union set " + " cover_time=? " + " where role_id=?";
		executeUpdate(sql, time, roleId);
	}

	/**
	 * 领取每周福利
	 * 
	 * @param roleId
	 * @param time
	 */
	public void updateBenefits(int roleId, Date time, int gift, int unionId) {
		String sql = "update t_role_union set " + " weekly_benefits=?, " + " last_contribute=last_contribute-?"
				+ " where role_id=?";
		executeUpdate(sql, time, gift, roleId);
	}

	/**
	 * 领取烧烤奖励
	 * 
	 * @param roleId
	 * @param time
	 * @param gift
	 */
	public void openBarbecue(RoleUnion roleUnion, Date time, int gift) {
		String sql = "update t_role_union set " + " barbecue=?, " + " last_contribute=last_contribute - ? "
				+ " where role_id=?";
		executeUpdate(sql, time, gift, roleUnion.getRoleId());
	}

	/**
	 * 更新玩家职务
	 */
	public void updateRoleJop(RoleUnion roleUnion, int jopType) {
		String sql = "update t_role_union set " + " jop_type=? " + " where role_id=?";
		executeUpdate(sql, jopType, roleUnion.getRoleId());

	}

	/**
	 * 修改玩家贡献 捐献
	 * 
	 * @param union
	 * @param money
	 * @return
	 */
	public void updateUnionRoleTLContribute(int contribute, int unionid, int roleid, int conType, int costMoney) {
		String sql = "update t_role_union set  last_contribute_time=now(),total_contribute = total_contribute + ?,"
				+ "last_contribute = last_contribute + ? , " + "con_type =?, " + "cost_money =?"
				+ " where union_id = ? and role_id = ? ";
		executeUpdate(sql, contribute, contribute, conType, costMoney, unionid, roleid);
	}

	// 更新玩家帮贡
	public void updateTotalContribute(RoleUnion r, int val) {
		String sql = "update t_role_union set total_contribute = total_contribute + ? where role_id=? and union_id=? ";
		executeUpdate(sql, val, r.getRoleId(), r.getUnionId());
	}

	// 更新玩家剩余帮贡
	public void updateLastContribute(RoleUnion r, int val) {
		String sql = "update t_role_union set last_contribute = last_contribute + ? where role_id=? and union_id=? ";
		executeUpdate(sql, val, r.getRoleId(), r.getUnionId());
	}

	/**
	 * 定时修改玩家排名
	 */
	public void updateRoleInfo(RoleUnion roleUnion) {
		String sql = "update t_role_union set rank =?,dekaron_num =?,defense_num=? where role_id=? and union_id=? ";
		executeUpdate(sql, roleUnion.getRank(), roleUnion.getDekaronNum(), roleUnion.getDefenseNum(),
				roleUnion.getRoleId(), roleUnion.getUnionId());
	}

	/**
	 * 修改玩家作坊领奖状态
	 */
	public void updateWShopRewardState(RoleUnion roleUnion) {
		String sql = "update t_role_union set shop_workrd_reward =0,is_over  = 0 where role_id=? and union_id=? ";
		executeUpdate(sql, roleUnion.getRoleId(), roleUnion.getUnionId());
	}

	/**
	 * 修改玩家排名
	 */
	public void updateRoleUnionAtt(RoleUnion roleUnion) {
		String sql = "update t_role_arena a ,t_role_union u set u.rank  =a.rank where a.role_id = u.role_id";
		executeUpdate(sql);
	}

	/**
	 * 修改玩家商店信息
	 */
	public void updateExchangeShopInfo(RoleUnion roleUnion, List<Integer> ids) {
		String sql = "update t_role_union set shop_ids = ? ,exchange_time = ? where role_id=? and union_id=? ";
		executeUpdate(sql, JSONUtil.toJson(ids), roleUnion.getExchangeTime(), roleUnion.getRoleId(),
				roleUnion.getUnionId());
	}
}
