package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.robot.XuNiWanJia;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 虚拟玩家DAO
 * 
 * @author 房曈
 *
 */
public class XuNiWanJiaDAO extends ConnectionResourceDAO implements QueryDateInterface<XuNiWanJia> {

	private static ResultSetRowHandler<XuNiWanJia> HANDLER = new ResultSetRowHandler<XuNiWanJia>() {
		@Override
		public XuNiWanJia handleRow(ResultSetRow row) throws Exception {
			XuNiWanJia xnwj = new XuNiWanJia();
			xnwj.setId(row.getInt("id"));
			xnwj.setLevel(DAOUtil.stringConvIntList(row.getString("level")));
			xnwj.setLead(DAOUtil.stringConvIntList(row.getString("lead")));
			xnwj.setNum1(row.getInt("num1"));
			xnwj.setNum2(row.getInt("num2"));
			xnwj.setRange1(DAOUtil.stringConvIntList(row.getString("range1")));
			xnwj.setProb1(row.getInt("prob1"));
			xnwj.setRange2(DAOUtil.stringConvIntList(row.getString("range2")));
			xnwj.setProb2(row.getInt("prob2"));
			xnwj.setRange3(DAOUtil.stringConvIntList(row.getString("range3")));
			xnwj.setProb3(row.getInt("prob3"));
			xnwj.setRange4(DAOUtil.stringConvIntList(row.getString("range4")));
			xnwj.setProb4(row.getInt("prob4"));
			xnwj.setNum3(row.getInt("num3"));
			xnwj.setRange5(DAOUtil.stringConvIntList(row.getString("range5")));
			xnwj.setRange6(DAOUtil.stringConvIntList(row.getString("range6")));
			xnwj.setRange7(DAOUtil.stringConvIntList(row.getString("range7")));
			xnwj.setRange8(DAOUtil.stringConvIntList(row.getString("range8")));
			xnwj.setLeve2(DAOUtil.stringConvIntList(row.getString("leve2")));
			xnwj.setNum4(row.getInt("num4"));
			xnwj.setRange9(DAOUtil.stringConvIntList(row.getString("range9")));
			xnwj.setRange10(DAOUtil.stringConvIntList(row.getString("range10")));
			xnwj.setLeve3(DAOUtil.stringConvIntList(row.getString("leve3")));
			return xnwj;
		}
	};

	/**
	 * 查询
	 */
	public XuNiWanJia query(int id) {
		return queryForObject("select * from t_xuniwanjia where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<XuNiWanJia> queryList() {
		return queryForList("select * from t_xuniwanjia", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_xuniwanjia where id=?", HANDLER, id);
	}
}