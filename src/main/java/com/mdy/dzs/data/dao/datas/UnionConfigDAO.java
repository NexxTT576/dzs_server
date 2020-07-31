package com.mdy.dzs.data.dao.datas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.union.UnionAttr;
import com.mdy.dzs.data.domain.union.UnionFBData;
import com.mdy.dzs.data.domain.union.UnionShop1;
import com.mdy.dzs.data.domain.union.UnionShop2;
import com.mdy.dzs.data.domain.union.UnionWorkShop;
import com.mdy.dzs.data.util.DAOUtil;
import com.mdy.sharp.container.res.ds.ConnectionResourceDAO;
import com.mdy.sharp.container.res.ds.ResultSetRow;
import com.mdy.sharp.container.res.ds.ResultSetRowHandler;

/**
 * 帮派基本配置
 * 
 * @author Administrator
 */
public class UnionConfigDAO extends ConnectionResourceDAO {
	private static ResultSetRowHandler<String> HANDLER_CONFIG = new ResultSetRowHandler<String>() {
		@Override
		public String handleRow(ResultSetRow row) throws Exception {
			String str = row.getString("name") + "&" + row.getString("value");
			return str;
		}
	};

	private static ResultSetRowHandler<UnionAttr> HANDLER_UNION = new ResultSetRowHandler<UnionAttr>() {
		@Override
		public UnionAttr handleRow(ResultSetRow row) throws Exception {
			UnionAttr union = new UnionAttr();
			union.setId(row.getInt("id"));
			union.setLevel(row.getInt("level"));
			union.setType(row.getInt("type"));
			union.setUsemoney(row.getInt("usemoney"));
			union.setRequirements(DAOUtil.stringConvIntList(row.getString("requirements")));
			return union;
		}
	};

	private static ResultSetRowHandler<UnionShop1> HANDLER_UNIONSHOP1 = new ResultSetRowHandler<UnionShop1>() {
		@Override
		public UnionShop1 handleRow(ResultSetRow row) throws Exception {
			UnionShop1 unionshop1 = new UnionShop1();
			unionshop1.setId(row.getInt("id"));
			unionshop1.setType(row.getInt("type"));
			unionshop1.setItemId(row.getInt("itemid"));
			unionshop1.setNum(row.getInt("num"));
			unionshop1.setExchange(row.getInt("exchange"));
			unionshop1.setMoney(row.getInt("money"));
			unionshop1.setCost(row.getInt("cost"));
			unionshop1.setOpenLevel(row.getInt("openlevel"));
			unionshop1.setWeight(row.getInt("weight"));
			return unionshop1;
		}
	};

	private static ResultSetRowHandler<UnionWorkShop> HANDLER_UNIONWORKSHOP = new ResultSetRowHandler<UnionWorkShop>() {
		@Override
		public UnionWorkShop handleRow(ResultSetRow row) throws Exception {
			UnionWorkShop workShop = new UnionWorkShop();
			workShop.setId(row.getInt("id"));
			workShop.setItemId(row.getInt("itemid"));
			workShop.setType(row.getInt("type"));
			workShop.setOpenLevel(row.getInt("level"));
			workShop.setNorWorkOne(row.getInt("play_num"));
			workShop.setNorWorkTwo(row.getInt("gongfang_num"));
			workShop.setExtWorkOne(row.getInt("double_play_num"));
			workShop.setExtWorkTwo(row.getInt("double_gongfang_num"));
			return workShop;
		}
	};

	private static ResultSetRowHandler<UnionShop2> HANDLER_UNIONSHOP2 = new ResultSetRowHandler<UnionShop2>() {
		@Override
		public UnionShop2 handleRow(ResultSetRow row) throws Exception {
			UnionShop2 unionshop2 = new UnionShop2();
			unionshop2.setId(row.getInt("id"));
			unionshop2.setType(row.getInt("type"));
			unionshop2.setItemId(row.getInt("itemid"));
			unionshop2.setNum(row.getInt("num"));
			unionshop2.setExchange(row.getInt("exchange"));
			unionshop2.setMoney(row.getInt("money"));
			unionshop2.setCost(row.getInt("cost"));
			unionshop2.setOpenLevel(row.getInt("openlevel"));
			unionshop2.setExchangeType(row.getInt("exchangetype"));
			return unionshop2;
		}
	};

	private static ResultSetRowHandler<UnionFBData> HANDLER_UNIONFB = new ResultSetRowHandler<UnionFBData>() {
		@Override
		public UnionFBData handleRow(ResultSetRow row) throws Exception {
			UnionFBData unionFB = new UnionFBData();
			unionFB.setId(row.getInt("id"));
			unionFB.setBossName(row.getString("bossname"));
			unionFB.setChapterName(row.getString("chaptername"));
			unionFB.setDrop(row.getInt("drop"));
			unionFB.setFbId(row.getInt("guankanum"));
			unionFB.setLimitLevle(row.getInt("limitlevel"));
			unionFB.setNpc(row.getInt("npc"));
			unionFB.setNum(row.getInt("num"));
			unionFB.setPreField(row.getInt("prefield"));
			unionFB.setRewardIds(DAOUtil.stringConvIntList(row.getString("rewardIds")));
			unionFB.setRewardNum(DAOUtil.stringConvIntList(row.getString("rewardNums")));
			unionFB.setRewardType(DAOUtil.stringConvIntList(row.getString("rewardTypes")));
			unionFB.setType(row.getInt("chapter"));
			return unionFB;
		}
	};

	public Map<String, String> queryUnionConfig() {
		Map<String, String> map = new HashMap<String, String>();
		List<String> list = queryForList("select name,value from t_union_config", HANDLER_CONFIG);
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).split("&")[0], list.get(i).split("&")[1]);
		}
		return map;
	}

	public List<UnionAttr> queryUnion() {
		List<UnionAttr> unionlist = queryForList("select * from t_union_c", HANDLER_UNION);
		return unionlist;
	}

	public List<UnionShop1> queryUnionShop1() {
		List<UnionShop1> unionshop = queryForList(
				"select id,type,itemid,num,exchange,money,cost,openlevel,weight from t_union_shop1",
				HANDLER_UNIONSHOP1);
		return unionshop;
	}

	public List<UnionShop2> queryUnionShop2() {
		List<UnionShop2> unionshop2 = queryForList("select * from t_union_shop2 order by id asc", HANDLER_UNIONSHOP2);
		return unionshop2;
	}

	public List<UnionWorkShop> queryWorkShop() {
		List<UnionWorkShop> workshop = queryForList("select * from t_union_gongfang", HANDLER_UNIONWORKSHOP);
		return workshop;
	}

	public List<UnionFBData> queryUnionFB() {
		List<UnionFBData> unionFB = queryForList("select * from t_union_fuben", HANDLER_UNIONFB);
		return unionFB;
	}
}
