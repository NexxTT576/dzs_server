package com.mdy.dzs.game.dao;

import java.util.Date;
import java.util.List;

import com.mdy.dzs.game.domain.union.Union;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

public class UnionDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<Union> HANDLER = new ResultSetRowHandler<Union>() {
		@Override
		public Union handleRow(ResultSetRow row) throws Exception {
			Union udv = new Union();
			udv.setId(row.getInt("id"));
			udv.setBossId(row.getInt("boss_id"));
			udv.setCreateTime(row.getTimestamp("create_time"));
			udv.setLevel(row.getInt("level"));
			udv.setName(row.getString("name"));
			udv.setRank(row.getInt("rank")); 
			udv.setRoleMaxNum(row.getInt("role_max_num"));
			udv.setSumAttack(row.getInt("sum_attack"));
			udv.setUnionIndes(row.getString("union_indes"));
			udv.setUnionOutdes(row.getString("union_outdes"));
			udv.setTotalUnionMoney(row.getInt("total_union_money"));
			udv.setCurrentUnionMoney(row.getInt("current_union_money")); 
			udv.setWorkShopLevel(row.getInt("workshop_level"));
			udv.setBarbecueTime(row.getTimestamp("barbecue_time"));
			udv.setShopLevel(row.getInt("shop_level"));
			udv.setOpenBarRole(row.getInt("open_bar_role"));
			udv.setCoverTime(row.getTimestamp("cover_time"));
			udv.setBuyNum(row.getInt("buy_num"));
			udv.setGreenDragonTempleLevel(row.getInt("green_dragon_temple_level"));
			udv.setGreenDragonLevel(row.getInt("green_dragon_level"));
			udv.setFbLevel(row.getInt("fb_level"));
			return udv;
		}
	};
	//建筑升级
	public void upUnioncenter(Union union, int buildtype, int costMoney,
			int roleAdd) {
		String levelsql = "";
		switch (buildtype) {
		case Union.UNION_TYPE_MAIN_PALACE: // 大殿升级
			levelsql = "level = level + 1 ";
			break;
		case Union.UNION_TYPE_WORK_SHOP: // 工坊
			levelsql = "workshop_level = workshop_level + 1";
			break;
		case Union.UNION_TYPE_SHOP: // 商店
			levelsql = "shop_level = shop_level + 1";
			break;
		case Union.UNION_TYPE_DRAGON: // 青龙堂
			levelsql = "green_dragon_temple_level = green_dragon_temple_level + 1";
			break;
		case Union.UNION_TYPE_FB: // 青龙堂
			levelsql = "fb_level = fb_level + 1";
			break;
		}
		executeUpdate("update t_union set current_union_money = current_union_money - ?,role_max_num = role_max_num+ ?,"+levelsql+"  where id = ?",costMoney,roleAdd,union.getId());
	}
	

	/**
	 * 捐献增加帮派资金
	 * @param union
	 * @param money
	 */
	public void updateUnionTotalAndCurrentMoney(Union union ,int money){
		String sql="update t_union set total_union_money = total_union_money + ?,current_union_money = current_union_money +? where id = ?";
		 executeUpdate(sql,money,money,union.getId());
	}
	
	/**
	 * 创建帮派
	 * @param union
	 * @return
	 */
	public int createUnion(Union union) {
		String sql = "insert into t_union(" + "name," + "level,"
				+ "create_time," + "sum_attack," + "boss_id," + "union_indes,"+"role_max_num,"
				+ "workshop_level,"+"shop_level,"+"rank"+")values(?,?,?,?,?,?,?,?,?,?)";
		int id = executeWithGenKey(sql, union.getName(), union.getLevel(),
				union.getCreateTime(), union.getSumAttack(), union.getBossId(),
				union.getUnionIndes(),union.getRoleMaxNum(),union.getWorkShopLevel(),union.getShopLevel(),union.getRank());
		return id;
	}
	/**
	 * 查询帮派信息
	 * @param unionId
	 * @return
	 */
	public Union queryUnionById(int unionId) {
		return queryForObject("select * from t_union where id=?", HANDLER,
				unionId);
	}
	/**
	 * 根据名称查询帮派
	 * @param unionName
	 * @return
	 */
	public Union queryUinonByName(String unionName){
		return queryForObject("select * from t_union where name=?", HANDLER,
				unionName);
	}
	/**
	 * 修改公告信息
	 * @param unionId
	 * @param msg
	 */
	public void updateUnionIndes(Union union,String msg,int type){
		String column ;
		if(type == 0){
			column ="union_indes";
		}else{
			column = "union_outdes";
		}
		String sql="update t_union set " +
				column+"=? " +
				" where id=?";
		executeUpdate(sql,
				msg,
				union.getId());
	}
	/**
	 * 查询所有帮派 0等级1 排名rank
	 */
	public List<Union> queryUnionBypage(int type) {
		String column ;
		if(type == 0){
			column = " level desc";
		}else{
			column = " rank asc";
		}
		return queryForList("select * from t_union order by "+column+"", HANDLER);
	}
	
	/**
	 * 修改帮派自荐信息
	 */
	public void updateUnionCover(Union union,Date nowTime){
		String sql="update t_union set cover_time =? where id =?";
		 executeUpdate(sql,nowTime,union.getId());
	}
	/**
	 * 修改青龙等级
	 */
	public void updateGreenDragonLevel(Union unionData){
		String sql = "update t_union set "
				+ "green_dragon_level=green_dragon_level+1,"
				+ "update_time=now() "
				+ "where id = ?";
		execute(sql, unionData.getId());
	}
	
	/**
	 * 解散帮派
	 */
	public void deleteUnion(Union union){
		execute("delete  from t_union where id=?",union.getId());
	}
	/**
	 * 查询帮派
	 */
	public List<Union> queryUinonListByName(String unionName){
		StringBuffer sql = new StringBuffer("select * from t_union  where 1=1 ");
		if (unionName != null) {
				sql.append(" and  name like '%" + unionName + "%' ");
		}
		sql.append("order by level desc");
		return queryForList(sql.toString(), HANDLER);
	}

	/**
	 * 减少帮派贡献值
	 */
	public void reduceUnionMoney(Union union,int costMoney) {
		String sql = "update t_union set " 
				+ " current_union_money =current_union_money-?," + " barbecue_time=?," + 
				 " open_bar_role=?" +" where id=?";
		executeUpdate(sql,costMoney, union.getBarbecueTime(),union.getOpenBarRole(),union.getId());
	}
	/**
	 * 修改帮派攻击力
	 * @param union
	 * @param sumattack
	 */
	public void updateUnionSumAttack(Union union,int sumattack){
		String sql = "update t_union set " 
				   + " sum_attack =sum_attack +? "
				   +"  where id=?";
		executeUpdate(sql,sumattack,union.getId());
	}
	/**
	 * 修改帮派帮主信息
	 */
	public void updateBossInfo(Union union){
		String sql = "update t_union set " 
				 	+ " cover_time = ? ,"
				   + " boss_id = ? "
				   +"  where id=?";
		executeUpdate(sql,union.getCoverTime(),union.getBossId(),union.getId());
		
	}
	/**
	 * 修改帮派战斗力
	 */
	public void updateUnionAtt(StringBuffer ids,int unionId){
		StringBuilder sql = new StringBuilder("update t_union  set sum_attack=(select sum(attack)  from t_role where id ");
		sql.append("in ("+ids+") ");
		sql.append(" ) where id = ");
		sql.append(unionId);
		executeUpdate(sql.toString());
	}
	/**
	 * 修改帮派排名
	 */
	public void updateUnionRank(Union union,int rank){
		String sql = "update t_union set " 
				   + " rank = ? "
				   +"  where id=?";
		executeUpdate(sql,rank,union.getId());
	}
}
