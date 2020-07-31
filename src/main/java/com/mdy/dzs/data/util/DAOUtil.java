package com.mdy.dzs.data.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.mdy.dzs.data.domain.broad.GuangBo;
import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

public class DAOUtil {

	final static Logger logger = LoggerFactory.get(DAOUtil.class);

	/**
	 * 转换基于0;0;0;0
	 * 
	 * @param str
	 * @return
	 */
	public static List<Integer> stringConvIntList(String str) {
		if (str != null)
			str = str.replaceAll("\\s*", "");
		if (str == null || str.length() == 0)
			return new ArrayList<Integer>();
		// logger.info(str);
		List<Integer> res = new ArrayList<Integer>();
		String[] datas = str.split(";");
		for (int i = 0; i < datas.length; i++) {
			res.add(Integer.parseInt(datas[i]));
		}
		return res;
	}

	/**
	 * 转换基于0;0;0;0
	 * 
	 * @param str
	 * @return
	 */
	public static List<Float> stringConvFloatList(String str) {
		if (str != null)
			str = str.replaceAll("\\s*", "");
		if (str == null || str == "")
			return new ArrayList<Float>();
		List<Float> res = new ArrayList<Float>();
		String[] datas = str.split(";");
		for (int i = 0; i < datas.length; i++) {
			try {
				res.add(Float.parseFloat(datas[i]));
			} catch (Exception e) {
				continue;
			}

		}
		return res;
	}

	public static String intListConvString(List<Integer> list) {
		if (list == null)
			return "";
		String res = "";
		for (int i = 0; i < list.size(); i++) {
			res += list.get(i);
			if (i != list.size() - 1)
				res += ";";
		}
		return res;
	}

	public static String captureName(String name) {
		// name = name.substring(0, 1).toUpperCase() + name.substring(1);
		// return name;
		char[] cs = name.toCharArray();
		cs[0] += 32;
		return String.valueOf(cs);

	}

	public static void main(String[] args) {
		System.out.println(intListConvString(stringConvIntList("")));
		// String strs[] = { "Equipen", "Crit", "Baptize", "Refine", "KongFu", "Bag",
		// "PanDuanXiTongChanChu", "Vip",
		// "World", "Field", "XuNiWanJia", "Collect", "Soul" };
		// String name;
		// String name1;
		// for (int i = 0; i < strs.length; i++) {
		// name = strs[i];
		// name1 = captureName(name);
		// // initdao
		// // System.out.println("final "+name+"DAO "+name1+"DAO = new "+name+"DAO();");
		// // System.out.println(name1+"DAO.setConnectionResource(cr);");

		// // daofactory
		// // System.out.println("@Override");
		// // System.out.println("public "+name+"DAO "+name1+"DAO() {");
		// // System.out.println(" return "+name1+"DAO;");
		// // System.out.println("}");

		// // ao
		// // System.out.println("public List<"+name+"> query"+name+"Datas(){");
		// // System.out.println(" return "+name1+"DAO().queryList();");
		// // System.out.println("}");

		// // action
		// // System.out.println("List<"+name+"> query"+name+"Datas();");

		// // System.out.println("@Override");
		// // System.out.println("public List<"+name+"> query"+name+"Datas() {");
		// // System.out.println(" return dataAO().query"+name+"Datas();");
		// // System.out.println("}");
		// // test
		// // System.out.println("getAction().query"+name+"Datas();");
		// //
		// // System.out.println("private Map<Integer, "+name+"> "+name1+"Map = new
		// // HashMap<Integer, "+name+">();");
		// //
		// System.out.println("generateDatas(dataAction.query"+name+"Datas(),"+name1+"Map);");
		// }

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.USE_ANNOTATIONS, true);
		StringWriter sw = new StringWriter();
		try {
			GuangBo gb = new GuangBo();
			gb.setContent("111");
			gb.setArrColor(null);
			mapper.writeValue(sw, (Object) gb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = sw.toString();
		System.out.println(str);
	}
}
