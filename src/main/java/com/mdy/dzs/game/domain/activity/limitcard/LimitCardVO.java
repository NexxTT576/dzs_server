package com.mdy.dzs.game.domain.activity.limitcard;

import java.io.Serializable;
import java.util.List;

import com.mdy.dzs.data.domain.item.ProbItem;

public class LimitCardVO implements Serializable
{
	private static final long serialVersionUID = 8556143261420609369L;
	
	//限时豪杰玩法配置数据
	private LimitCardConfigBean lcb;
	
	private List<RankListBean> rlblist;
	
	//玩家自己排名
	private int player_rank;
	
	//玩家自己积分
	private int player_score;
	
	//玩家每次得到几个积分
	private int get_score;
	
	//活动开启时间
	private String act_start_time;
	
	//活动结束时间
	private String act_end_time;
	
	private long actEndTime_inMS;
	
	//距活动结束的秒数
	private int act_rest_time;
	
	//距下次免费抽取的秒数
	private int free_rest_time;
	
	//元宝抽取所需
	private int gold_cost;
	
	//幸运值 值慢必得活动卡
	private int luck_num;
	
	//信用值 最大值
	private int max_luck_num;
	
	//再招几次获得五星
	private int rest_luck_num;
	
	//奖励类型0主卡1五星卡2随机破卡
	private int type;
	
	//副卡标识区别公告
	private int cardfu;
	
	//主卡id
	private int mainid;
	
	//获得的卡牌
	private ProbItem probItem;

	public int getCardfu() {
		return cardfu;
	}

	public void setCardfu(int cardfu) {
		this.cardfu = cardfu;
	}

	public long getActEndTime_inMS() {
		return actEndTime_inMS;
	}

	public void setActEndTime_inMS(long actEndTime_inMS) {
		this.actEndTime_inMS = actEndTime_inMS;
	}

	public int getGet_score() {
		return get_score;
	}

	public void setGet_score(int get_score) {
		this.get_score = get_score;
	}

	public ProbItem getProbItem() {
		return probItem;
	}

	public void setProbItem(ProbItem probItem) {
		this.probItem = probItem;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMainid() {
		return mainid;
	}

	public void setMainid(int mainid) {
		this.mainid = mainid;
	}

	public int getMax_luck_num() {
		return max_luck_num;
	}

	public void setMax_luck_num(int max_luck_num) {
		this.max_luck_num = max_luck_num;
	}

	public LimitCardConfigBean getLcb() {
		return lcb;
	}

	public void setLcb(LimitCardConfigBean lcb) {
		this.lcb = lcb;
	}
	
	public List<RankListBean> getRlblist() {
		return rlblist;
	}

	public void setRlblist(List<RankListBean> rlblist) {
		this.rlblist = rlblist;
	}

	public int getPlayer_rank() {
		return player_rank;
	}

	public void setPlayer_rank(int player_rank) {
		this.player_rank = player_rank;
	}

	public int getPlayer_score() {
		return player_score;
	}

	public void setPlayer_score(int player_score) {
		this.player_score = player_score;
	}

	public String getAct_start_time() {
		return act_start_time;
	}

	public void setAct_start_time(String act_start_time) {
		this.act_start_time = act_start_time;
	}

	public String getAct_end_time() {
		return act_end_time;
	}

	public void setAct_end_time(String act_end_time) {
		this.act_end_time = act_end_time;
	}

	public int getAct_rest_time() {
		return act_rest_time;
	}

	public void setAct_rest_time(int act_rest_time) {
		this.act_rest_time = act_rest_time;
	}

	public int getFree_rest_time() {
		return free_rest_time;
	}

	public void setFree_rest_time(int free_rest_time) {
		this.free_rest_time = free_rest_time;
	}

	public int getGold_cost() {
		return gold_cost;
	}

	public void setGold_cost(int gold_cost) {
		this.gold_cost = gold_cost;
	}

	public int getLuck_num() {
		return luck_num;
	}

	public void setLuck_num(int luck_num) {
		this.luck_num = luck_num;
	}

	public int getRest_luck_num() {
		return rest_luck_num;
	}

	public void setRest_luck_num(int rest_luck_num) {
		this.rest_luck_num = rest_luck_num;
	}
}
