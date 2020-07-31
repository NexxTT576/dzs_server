package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.data.domain.card.CardClsLimit;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 卡片DAO
 * 
 * @author 房曈
 *
 */
public class CardDAO extends ConnectionResourceDAO implements QueryDateInterface<Card> {

	private static ResultSetRowHandler<Card> HANDLER = new ResultSetRowHandler<Card>() {
		@Override
		public Card handleRow(ResultSetRow row) throws Exception {
			Card c = new Card();
			c.setId(row.getInt("id"));
			c.setGroup(row.getInt("group"));
			c.setName(row.getString("name"));
			c.setType(row.getInt("type"));
			c.setJob(row.getInt("job"));
			c.setSex(row.getInt("sex"));
			c.setHero(row.getInt("hero"));
			c.setStar(DAOUtil.stringConvIntList(row.getString("star")));
			c.setAnger(DAOUtil.stringConvIntList(row.getString("anger")));
			c.setLead(DAOUtil.stringConvIntList(row.getString("lead")));
			c.setBase(DAOUtil.stringConvIntList(row.getString("base")));
			c.setBaseRate(DAOUtil.stringConvIntList(row.getString("baseRate")));
			c.setBaseMod(DAOUtil.stringConvIntList(row.getString("baseMod")));
			c.setRare(DAOUtil.stringConvIntList(row.getString("rare")));
			c.setRareRate(DAOUtil.stringConvIntList(row.getString("rareRate")));
			c.setHeal(DAOUtil.stringConvIntList(row.getString("heal")));
			c.setRate(row.getInt("rate"));
			c.setResist(DAOUtil.stringConvIntList(row.getString("resist")));
			c.setCrit(DAOUtil.stringConvIntList(row.getString("crit")));
			c.setSkill(DAOUtil.stringConvIntList(row.getString("skill")));
			c.setAngerSkill(DAOUtil.stringConvIntList(row.getString("angerSkill")));
			c.setGroupSkill(DAOUtil.stringConvIntList(row.getString("groupSkill")));
			c.setBskill(DAOUtil.stringConvIntList(row.getString("bskill")));
			c.setArrPoint(DAOUtil.stringConvIntList(row.getString("arr_point")));
			c.setTalent(DAOUtil.stringConvIntList(row.getString("talent")));
			c.setFate1(DAOUtil.stringConvIntList(row.getString("fate1")));
			c.setLevelAdd(DAOUtil.stringConvIntList(row.getString("levelAdd")));
			c.setAdvance(row.getInt("advance"));
			c.setAdvLife(DAOUtil.stringConvIntList(row.getString("advLife")));
			c.setAdvAttack(DAOUtil.stringConvIntList(row.getString("advAttack")));
			c.setAdvDefense(DAOUtil.stringConvIntList(row.getString("advDefense")));
			c.setAdvDefenseM(DAOUtil.stringConvIntList(row.getString("advDefenseM")));
			c.setSilver(DAOUtil.stringConvIntList(row.getString("silver")));
			c.setItem1(DAOUtil.stringConvIntList(row.getString("item1")));
			c.setItem2(DAOUtil.stringConvIntList(row.getString("item2")));
			c.setItem3(DAOUtil.stringConvIntList(row.getString("item3")));
			c.setCard(DAOUtil.stringConvIntList(row.getString("card")));
			c.setNumber1(DAOUtil.stringConvIntList(row.getString("number1")));
			c.setNumber2(DAOUtil.stringConvIntList(row.getString("number2")));
			c.setNumber3(DAOUtil.stringConvIntList(row.getString("number3")));
			c.setNumber(DAOUtil.stringConvIntList(row.getString("number")));
			c.setLysis(row.getInt("lysis"));
			c.setExp(row.getInt("exp"));
			c.setRefining(row.getInt("refining"));
			c.setSoul(DAOUtil.stringConvIntList(row.getString("soul")));
			c.setReborn(row.getInt("reborn"));
			c.setPrice(row.getInt("price"));
			c.setSuperNatural(row.getInt("superNatural"));
			return c;
		}
	};

	/**
	 * 查询
	 */
	public Card query(int id) {
		return queryForObject("select * from t_card where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Card> queryList() {
		return queryForList("select * from t_card", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_card where id=?", HANDLER, id);
	}

	//////////////////////////////////////////////////////////////////////////////////
	private static ResultSetRowHandler<CardClsLimit> CLSLIMIT_HANDLER = new ResultSetRowHandler<CardClsLimit>() {
		@Override
		public CardClsLimit handleRow(ResultSetRow row) throws Exception {
			CardClsLimit ccl = new CardClsLimit();
			ccl.setId(row.getInt("id"));
			ccl.setAdvanced(row.getInt("advanced"));
			ccl.setLevel(DAOUtil.stringConvIntList(row.getString("level")));
			return ccl;
		}
	};

	public List<CardClsLimit> queryClsLimitList() {
		return queryForList("select * from t_jinjie", CLSLIMIT_HANDLER);
	}
}