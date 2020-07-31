package com.mdy.dzs.game.domain.giftcenter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mdy.dzs.data.domain.gift.GiftItem;

/**
 * 领奖中心模型
 * 
 * @author 房曈
 *
 */
public class RoleGift implements Serializable {

	public static final int GIFT_TYPE_竞技场 = 1;
	public static final int GIFT_TYPE_精英回归 = 2;
	public static final int GIFT_TYPE_系统补偿 = 3;
	public static final int GIFT_TYPE_门派闯关 = 4;
	public static final int GIFT_TYPE_老玩家回馈 = 5;
	public static final int GIFT_TYPE_烛龙殿活动奖励 = 7;
	public static final int GIFT_TYPE_击杀烛龙奖励 = 8;
	public static final int GIFT_TYPE_充值异常 = 9;
	public static final int GIFT_TYPE_首充礼包 = 10;
	public static final int GIFT_TYPE_奖励中心 = 11;
	public static final int GIFT_TYPE_青龙堂活动奖励 = 12;
	public static final int GIFT_TYPE_击败青龙奖励 = 13;
	public static final int GIFT_TYPE_LIMIT_CARD_SCOR = 14;
	public static final int GIFT_TYPE_LIMIT_CARD_RANK = 15;
	public static final int GIFT_TYPE_比武天榜奖励 = 16;

	public static final int GIFT_GETTYPE_ONE = 1;
	public static final int GIFT_GETTYPE_ALL = 2;
	/** 序列化id */
	private static final long serialVersionUID = 1L;

	private int id;
	/***/
	private int roleId;
	/** 发奖类别 */
	private int giftType;
	/** 奖品 */
	private List<GiftItem> giftItem;
	/** 其他信息 */
	private String otherData;
	/** 发奖时间 */
	private Date createTime;

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@JsonProperty(value = "type")
	public int getGiftType() {
		return this.giftType;
	}

	public void setGiftType(int giftType) {
		this.giftType = giftType;
	}

	@JsonProperty(value = "item")
	public List<GiftItem> getGiftItem() {
		return this.giftItem;
	}

	public void setGiftItem(List<GiftItem> giftItem) {
		this.giftItem = giftItem;
	}

	public Object getOtherData() {
		Object res = otherData;
		if (giftType == 1) {// 竞技场
			SimpleDateFormat dateformat = new SimpleDateFormat("MM月dd日");
			String time = dateformat.format(createTime);
			res = Arrays.asList(time, otherData);
		}
		if (giftType == 11) {// 奖励中心
			res = Arrays.asList(otherData);
		}
		if (giftType == 14) {// 限时豪杰积分奖励
			res = Arrays.asList(otherData);
		}
		if (giftType == 15) {// 限时豪杰排名奖励
			res = Arrays.asList(otherData);
		}
		if (giftType == 16) {// 比武排名奖励
			SimpleDateFormat dateformat = new SimpleDateFormat("MM月dd日");
			String time = dateformat.format(createTime);
			res = Arrays.asList(time, otherData);
		}
		return res;
	}

	public String oldOtherData() {
		return otherData;
	}

	public void setOtherData(String otherData) {
		this.otherData = otherData;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yy年MM月dd日 HH时");
		String time = dateformat.format(createTime);
		return "发奖时间：" + time;
	}
}