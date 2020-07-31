package com.mdy.dzs.data.dao.datas;

import java.util.List;

import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 道具DAO
 * 
 * @author 房曈
 *
 */
public class ItemDAO extends ConnectionResourceDAO implements QueryDateInterface<Item> {

	private static ResultSetRowHandler<Item> HANDLER = new ResultSetRowHandler<Item>() {
		@Override
		public Item handleRow(ResultSetRow row) throws Exception {
			Item i = new Item();
			i.setId(row.getInt("id"));
			i.setName(row.getString("name"));
			i.setType(row.getInt("type"));
			i.setBag(row.getInt("bag"));
			i.setOrder(row.getInt("order"));
			i.setLabel(row.getString("label"));
			i.setSuit(row.getInt("Suit"));
			i.setQuality(row.getInt("quality"));
			i.setIcon(row.getString("icon"));
			i.setBicon(row.getString("bicon"));
			i.setDescribe(row.getString("describe"));
			i.setLevel(row.getInt("level"));
			i.setOverlay(row.getInt("overlay"));
			i.setPos(row.getInt("pos"));
			i.setSale(row.getInt("sale"));
			i.setMoneytype(row.getInt("moneytype"));
			i.setPrice(row.getInt("price"));
			i.setEffecttype(row.getInt("effecttype"));
			i.setTest(row.getString("test"));
			i.setExpend(DAOUtil.stringConvIntList(row.getString("expend")));
			i.setAuto(row.getString("auto"));
			i.setPara1(DAOUtil.stringConvIntList(row.getString("para1")));
			i.setPara2(DAOUtil.stringConvIntList(row.getString("para2")));
			i.setPara3(DAOUtil.stringConvIntList(row.getString("para3")));
			i.setRefining(row.getInt("refining"));
			i.setNumber(row.getInt("number"));
			i.setRatio(row.getInt("ratio"));
			i.setExp(row.getInt("exp"));
			i.setArrNature(DAOUtil.stringConvIntList(row.getString("arr_nature")));
			i.setArrAddition(DAOUtil.stringConvIntList(row.getString("arr_addition")));
			i.setArrValue(DAOUtil.stringConvIntList(row.getString("arr_value")));
			i.setRandomnature(DAOUtil.stringConvIntList(row.getString("randomnature")));
			i.setProbability(DAOUtil.stringConvIntList(row.getString("probability")));
			i.setPolish(row.getInt("polish"));
			i.setArrXilian(DAOUtil.stringConvIntList(row.getString("arr_xilian")));
			i.setArrBeginning(DAOUtil.stringConvIntList(row.getString("arr_beginning")));
			i.setOutput(DAOUtil.stringConvIntList(row.getString("output")));
			i.setReborn(row.getInt("reborn"));
			i.setEquip_level(row.getInt("equip_level"));
			return i;
		}
	};

	/**
	 * 查询
	 */
	public Item query(int id) {
		return queryForObject("select * from t_item where id=?", HANDLER, id);
	}

	/**
	 * 查询列表
	 */
	public List<Item> queryList() {
		return queryForList("select * from t_item", HANDLER);
	}

	/**
	 * 删除
	 */
	public void delete(int id) {
		execute("delete  from t_item where id=?", id);
	}
}