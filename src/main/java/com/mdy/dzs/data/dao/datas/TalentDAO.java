package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.shentong.Talent;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 神通DAO
 * 
 * @author 房曈
 *
 */
public class TalentDAO extends ConnectionResourceDAO implements QueryDateInterface<Talent> {

	private static ResultSetRowHandler<Talent> HANDLER = new ResultSetRowHandler<Talent>() {
		@Override
		public Talent handleRow(ResultSetRow row) throws Exception {
			Talent t = new Talent();
			t.setId(row.getInt("id"));
			t.setName(row.getString("name"));
			t.setType(row.getString("type"));
			t.setOld(row.getString("old"));
			t.setShentong(row.getInt("shentong"));
			t.setImageName(row.getString("image_name"));
			t.setShow(row.getInt("show"));
			t.setNode(DAOUtil.stringConvIntList(row.getString("node")));
			t.setAttCond(DAOUtil.stringConvIntList(row.getString("attCond")));
			t.setAttPara(DAOUtil.stringConvIntList(row.getString("attPara")));
			t.setAttProb(DAOUtil.stringConvIntList(row.getString("attProb")));
			t.setAttSide(DAOUtil.stringConvIntList(row.getString("attSide")));
			t.setAttTarget(DAOUtil.stringConvIntList(row.getString("attTarget")));
			t.setAttId(DAOUtil.stringConvIntList(row.getString("attId")));
			t.setAttCalc(DAOUtil.stringConvIntList(row.getString("attCalc")));
			t.setAttCoff(DAOUtil.stringConvIntList(row.getString("attCoff")));
			t.setNodeB(DAOUtil.stringConvIntList(row.getString("nodeB")));
			t.setAttCondB(DAOUtil.stringConvIntList(row.getString("attCondB")));
			t.setAttParaB(DAOUtil.stringConvIntList(row.getString("attParaB")));
			t.setAttProbB(DAOUtil.stringConvIntList(row.getString("attProbB")));
			t.setAttSideB(DAOUtil.stringConvIntList(row.getString("attSideB")));
			t.setAttTargetB(DAOUtil.stringConvIntList(row.getString("attTargetB")));
			t.setAttBuff(DAOUtil.stringConvIntList(row.getString("attBuff")));
			t.setAttDBuff(DAOUtil.stringConvIntList(row.getString("attDBuff")));
			t.setNodeS(DAOUtil.stringConvIntList(row.getString("nodeS")));
			t.setAttCondS(DAOUtil.stringConvIntList(row.getString("attCondS")));
			t.setAttParaS(DAOUtil.stringConvIntList(row.getString("attParaS")));
			t.setAttProbS(DAOUtil.stringConvIntList(row.getString("attProbS")));
			t.setAttSideS(DAOUtil.stringConvIntList(row.getString("attSideS")));
			t.setAttTargetS(DAOUtil.stringConvIntList(row.getString("attTargetS")));
			t.setAttSkill(DAOUtil.stringConvIntList(row.getString("attSkill")));
			t.setFactor(row.getInt("factor"));
			t.setStType(row.getInt("stType"));
			t.setPara1(row.getInt("para1"));
			t.setPara2(row.getInt("para2"));
			t.setPara3(row.getInt("para3"));
			t.setPara4(row.getInt("para4"));
			return t;
		}
	};

	/**
	 * 查询
	 */
	public Talent query(int id) {
		return queryForObject("select * from t_talent where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Talent> queryList() {
		return queryForList("select * from t_talent", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_talent where id=?", HANDLER, id);
	}
}