package com.mdy.dzs.data.domain.card;

import java.io.Serializable;


/**
 * 上限设定模型
 * @author 房曈
 *
 */
public class ShangXianSheDing implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**主角等级*/
	private int zhujuedengji;
	/**主角进阶次数*/
	private int zhujuejinjie;
	/**卡牌等级*/
	private int kapaidengji;
	/**卡牌进阶次数*/
	private int kapaijinjiecishu;
	/**装备等级*/
	private int zhuangbeidengji;
	/**武学等级*/
	private int wuxuedengji;
	/**真气等级*/
	private int zhenqidengji;
	/**神通等级*/
	private int shentongdengji;
	/**江湖路*/
	private int jianghulu;
	/**经脉等级*/
	private int jingmaidengji;
	/**内外功精炼等级*/
	private int jinglian;

	public int getZhujuedengji(){
		return this.zhujuedengji;
	}
	public void setZhujuedengji(int zhujuedengji){
		this.zhujuedengji=zhujuedengji;
	}
	public int getZhujuejinjie(){
		return this.zhujuejinjie;
	}
	public void setZhujuejinjie(int zhujuejinjie){
		this.zhujuejinjie=zhujuejinjie;
	}
	public int getKapaidengji(){
		return this.kapaidengji;
	}
	public void setKapaidengji(int kapaidengji){
		this.kapaidengji=kapaidengji;
	}
	public int getKapaijinjiecishu(){
		return this.kapaijinjiecishu;
	}
	public void setKapaijinjiecishu(int kapaijinjiecishu){
		this.kapaijinjiecishu=kapaijinjiecishu;
	}
	public int getZhuangbeidengji(){
		return this.zhuangbeidengji;
	}
	public void setZhuangbeidengji(int zhuangbeidengji){
		this.zhuangbeidengji=zhuangbeidengji;
	}
	public int getWuxuedengji(){
		return this.wuxuedengji;
	}
	public void setWuxuedengji(int wuxuedengji){
		this.wuxuedengji=wuxuedengji;
	}
	public int getZhenqidengji(){
		return this.zhenqidengji;
	}
	public void setZhenqidengji(int zhenqidengji){
		this.zhenqidengji=zhenqidengji;
	}
	public int getShentongdengji(){
		return this.shentongdengji;
	}
	public void setShentongdengji(int shentongdengji){
		this.shentongdengji=shentongdengji;
	}
	public int getJianghulu() {
		return jianghulu;
	}
	public void setJianghulu(int jianghulu) {
		this.jianghulu = jianghulu;
	}
	public int getJingmaidengji() {
		return jingmaidengji;
	}
	public void setJingmaidengji(int jingmaidengji) {
		this.jingmaidengji = jingmaidengji;
	}
	public void setJingLian(int jinglian) {
		this.jinglian = jinglian;
	}
	public int getJingLian(){
		return jinglian;
	}
}