package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.road.MingJiang;
import com.mdy.dzs.data.domain.road.RoadCardAchieve;
import com.mdy.dzs.data.domain.road.RoadCardLevel;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 名将DAO
 * 
 * @author 房曈
 *
 */
public class MingJiangDAO extends ConnectionResourceDAO implements QueryDateInterface<MingJiang> {

	private static ResultSetRowHandler<MingJiang> HANDLER = new ResultSetRowHandler<MingJiang>() {
		@Override
		public MingJiang handleRow(ResultSetRow row) throws Exception {
			MingJiang mj = new MingJiang();
			mj.setId(row.getInt("id"));
			mj.setLevel(row.getInt("level"));
			mj.setArrExp(DAOUtil.stringConvIntList(row.getString("arr_exp")));
			return mj;
		}
	};

	/**
	 * 添加
	 * 
	 * @param MingJiang
	 */
	public void add(MingJiang mj) {
		String sql = "insert into t_mingjiang(" + "level," + "arr_exp" + ")values(?,?)";
		execute(sql, mj.getLevel(), DAOUtil.intListConvString(mj.getArrExp()));
	}

	/**
	 * 更新
	 * 
	 * @param MingJiang
	 */
	public void update(MingJiang mj) {
		String sql = "update t_mingjiang set " + " level=?," + " arr_exp=? " + " where id=?";
		executeUpdate(sql, mj.getLevel(), DAOUtil.intListConvString(mj.getArrExp()), mj.getId());
	}

	/**
	 * 查询
	 */
	public MingJiang query(int id) {
		return queryForObject("select * from t_mingjiang where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<MingJiang> queryList() {
		return queryForList("select * from t_mingjiang", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_mingjiang where id=?", HANDLER, id);
	}

	//////////////////////////////////////////////////////////////////////////////////
	private static ResultSetRowHandler<RoadCardLevel> ROAD_CARDLEVEL_HANDLER = new ResultSetRowHandler<RoadCardLevel>() {
		@Override
		public RoadCardLevel handleRow(ResultSetRow row) throws Exception {
			RoadCardLevel rcl = new RoadCardLevel();
			rcl.setId(row.getInt("id"));
			rcl.setCard(row.getInt("card"));
			rcl.setArrNature(DAOUtil.stringConvIntList(row.getString("arr_nature")));
			rcl.setArrNum(DAOUtil.stringConvIntList(row.getString("arr_num")));
			return rcl;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoadCardLevel
	 */
	public void add(RoadCardLevel rcl) {
		String sql = "insert into t_star(" + "card," + "arr_nature," + "arr_num" + ")values(?,?,?)";
		execute(sql, rcl.getCard(), DAOUtil.intListConvString(rcl.getArrNature()),
				DAOUtil.intListConvString(rcl.getArrNum()));
	}

	/**
	 * 查询列表
	 */
	public List<RoadCardLevel> queryRoadCardLevelList() {
		return queryForList("select * from t_star", ROAD_CARDLEVEL_HANDLER);
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	private static ResultSetRowHandler<RoadCardAchieve> ROAD_CARDACHIEVE_HANDLER = new ResultSetRowHandler<RoadCardAchieve>() {
		@Override
		public RoadCardAchieve handleRow(ResultSetRow row) throws Exception {
			RoadCardAchieve rca = new RoadCardAchieve();
			rca.setId(row.getInt("id"));
			rca.setGood(row.getInt("good"));
			rca.setNaili(row.getInt("naili"));
			rca.setName(row.getString("name"));
			rca.setCondition(row.getString("condition"));
			rca.setEffect(row.getString("effect"));
			return rca;
		}
	};

	/**
	 * 添加
	 * 
	 * @param RoadCardAchieve
	 */
	public void add(RoadCardAchieve rca) {
		String sql = "insert into t_starachieve(" + "good," + "naili," + "name," + "condition," + "effect"
				+ ")values(?,?,?,?,?)";
		execute(sql, rca.getGood(), rca.getNaili(), rca.getName(), rca.getCondition(), rca.getEffect());
	}

	/**
	 * 查询列表
	 */
	public List<RoadCardAchieve> queryRoadCardAchieveList() {
		return queryForList("select * from t_starachieve", ROAD_CARDACHIEVE_HANDLER);
	}
}