package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.gift.GiftItem;
import com.mdy.dzs.data.domain.item.Item;
import com.mdy.dzs.game.domain.arena.RoleArena;
import com.mdy.dzs.game.domain.mail.Mail;
import com.mdy.dzs.game.domain.mail.MailParaVO;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.tournament.RoleTourAwardVO;
import com.mdy.dzs.game.filter.MailFilter;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

/**
 * 邮件
 * 
 * @author 白雪林
 *
 */
public class MailAO extends BaseAO {

	/**
	 * 查询列表
	 * 
	 * @param mailId
	 * @param roleId
	 * @param type
	 */
	public List<Mail> queryListByFilter(int roleId, int mailId, int type) {
		MailFilter filter = new MailFilter();
		filter.setRoleId(roleId);
		filter.setType(type);
		filter.setOrderType(MailFilter.ORDER_BY_TIME_DESC);
		if (mailId > 0) {
			filter.setNum(Constants.MailRtnLimit.get(0));
			filter.setId(mailId);
		} else {
			filter.setNum(Constants.MailRtnLimit.get(1));
		}
		filter.setLimitDate(DateUtil.getDateAfter(DateUtil.getStartTimeOfDay(new Date()), -Constants.MailSaveDay));

		return mailDAO().queryListByFilter(filter);
	}

	/**
	 * 统计数量
	 */
	public int queryCount(int roleId, int mailId, int type) {
		MailFilter filter = new MailFilter();
		filter.setRoleId(roleId);
		filter.setType(type);
		if (mailId > 0) {
			return 0;
		}
		filter.setLimitDate(DateUtil.getDateAfter(DateUtil.getStartTimeOfDay(new Date()), -Constants.MailSaveDay));

		return mailDAO().queryCount(filter);
	}

	/**
	 * 添加
	 * 
	 * @param Mail
	 */
	public void add(Mail m) {
		mailDAO().add(m);
	}

	/**
	 * 定时清理表
	 */
	public void tidyTable() {
		Date tidyDate = DateUtil.getDateAfter(DateUtil.getEndTimeOfDay(new Date()), -Constants.MailSaveDay);
		mailDAO().tidyTable(tidyDate);
	}

	/**
	 * 查看新邮件
	 */
	public Map<String, Integer> checkNewMail(Role doc) {
		// {"battle":战斗新邮件数,"friend":好友新邮件数,"system":系统新邮件数}
		Map<String, Integer> newMailMap = new HashMap<String, Integer>();
		newMailMap.put("battle", mailDAO().checkNewMail(getCheckDate(doc.getMailLastViewAry().get(0)),
				Mail.MAIL_TYPE_BATTLE, doc.getId()));
		newMailMap.put("friend", mailDAO().checkNewMail(getCheckDate(doc.getMailLastViewAry().get(1)),
				Mail.MAIL_TYPE_FREND, doc.getId()));
		newMailMap.put("system", mailDAO().checkNewMail(getCheckDate(doc.getMailLastViewAry().get(2)),
				Mail.MAIL_TYPE_SYSTEM, doc.getId()));
		return newMailMap;
	}

	public Date getCheckDate(Long time) {
		if (time != null) {
			return new Date(time);
		} else {
			return new Date();
		}
	}
	//////////////////////////////////// 发送邮件
	//////////////////////////////////// ////////////////////////////////////////////////

	/**
	 * roleId: 角色ID type: 邮件所属类型(1战斗 BATTLE/2好友 FREND/3系统 SYSTEM) strId:
	 * 消息字符串id，详见策划配置表 paras： 附加参数
	 */
	public void sendMail(int roleId, int type, int strId, List<MailParaVO> paras) throws BizException {
		Mail bData = new Mail();
		bData.setRoleId(roleId);
		bData.setStrId(strId);
		bData.setType(type);
		bData.setTime(new Date());
		bData.setParas(paras);
		mailDAO().add(bData);
	}

	// 玩家{%s}在比武场 ，复仇 ，天榜 ，挑战你，但被你轻松击败了
	public void tournamentDefendWinMail(int roleId, Role doc, int textType) throws BizException {
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ROLE, doc.getName(), doc.getCls()));
		sendMail(roleId, Mail.MAIL_TYPE_BATTLE, textType, paras);
	}

	// 玩家{%s}在比武场 ，复仇 ，挑战你。你的比武积分减少{%s}。
	public void tournamentDefendFailScoreMail(int roleId, Role doc, int textType, int score) throws BizException {
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ROLE, doc.getName(), doc.getCls()));
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_NUM, score + ""));
		sendMail(roleId, Mail.MAIL_TYPE_BATTLE, textType, paras);
	}

	// 玩家{%s}在比武场挑战你，你的排名降至第{%s}。你的比武积分减少{%s}。
	public void tournamentDefendFailScoreMail(int roleId, Role doc, int textType, int rank, int score)
			throws BizException {
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ROLE, doc.getName(), doc.getCls()));
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_NUM, rank + ""));
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_NUM, score + ""));
		sendMail(roleId, Mail.MAIL_TYPE_BATTLE, textType, paras);
	}

	// 比武场天榜排名奖励 恭喜您在{%s}的比武中取得了第{%s}名的成绩，获得奖励{%s}{%s}
	public void tournamentRankAwardMail(int roleId, RoleTourAwardVO roleTourAwardVO) throws BizException {
		Date now = new Date();
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_NUM,
				DateUtil.getYear(now) + "年" + DateUtil.getMonth(now) + "月" + DateUtil.getDay(now) + "日"));
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_NUM, roleTourAwardVO.getRank() + ""));
		for (GiftItem giftItem : roleTourAwardVO.getAwards()) {
			paras.add(
					new MailParaVO(Mail.MAIL_PARA_TYPE_ITEM, giftItem.getType(), giftItem.getId(), giftItem.getNum()));
		}
		sendMail(roleId, Mail.MAIL_TYPE_SYSTEM, Mail.MAIL_FUNCID_比武_发奖_AWARD, paras);
	}

	// 玩家{%s}在竞技场挑战你，但被你轻松击败了
	public void arenaDefendWinMail(int roleId, Role doc) throws BizException {
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ROLE, doc.getName(), doc.getCls()));
		sendMail(roleId, Mail.MAIL_TYPE_BATTLE, Mail.MAIL_FUNCID_ARENA_DEFEND_WIN, paras);
	}

	// 玩家{%s}在竞技场轻松击败了你，你的竞技场排名降至{%s}
	public void arenaDefendFailRankDescMail(int roleId, Role doc, int rank) throws BizException {
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ROLE, doc.getName(), doc.getCls()));
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_NUM, rank + ""));
		sendMail(roleId, Mail.MAIL_TYPE_BATTLE, Mail.MAIL_FUNCID_ARENA_DEFEND_FAIL_RANK_DESC, paras);
	}

	// 玩家{%s}在竞技场轻松击败了你，你的竞技场排名无变化。
	public void arenaDefendFailRankHoldMail(int roleId, Role doc) throws BizException {
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ROLE, doc.getName(), doc.getCls()));
		sendMail(roleId, Mail.MAIL_TYPE_BATTLE, Mail.MAIL_FUNCID_ARENA_DEFEND_FAIL_RANK_HOLD, paras);
	}

	// 竞技场排名奖励 恭喜您在{%s}的竞技场中取得了第{%s}名的成绩，获得奖励{%s}{%s}
	public void arenaRankAwardMail(int roleId, RoleArena roleArena) throws BizException {
		Date now = new Date();
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_NUM,
				DateUtil.getYear(now) + "年" + DateUtil.getMonth(now) + "月" + DateUtil.getDay(now) + "日"));
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_NUM, roleArena.getRank() + ""));
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ITEM, Packet.POS_BAG, Packet.ATTR_银币, roleArena.getAwardSilver()));
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ITEM, Packet.POS_BAG, Packet.ATTR_声望, roleArena.getAwardPopual()));
		sendMail(roleId, Mail.MAIL_TYPE_SYSTEM, Mail.MAIL_FUNCID_ARENA_RANK_AWARD, paras);
	}

	// 玩家{%s}抢夺了你的《{%s}》1个
	public void snatchLoseMail(int roleId, Role doc, Item itemData) throws BizException {
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ROLE, doc.getName(), doc.getCls()));
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ITEM, itemData.getType(), itemData.getId(), 1));
		sendMail(roleId, Mail.MAIL_TYPE_BATTLE, Mail.MAIL_FUNCID_SNATCH_LOSE, paras);
	}

	// {s%}同意了你的入帮申请
	public void unionApplyAgree(int roleId, String unionName) throws BizException {
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_NUM, unionName));
		sendMail(roleId, Mail.MAIL_TYPE_SYSTEM, Mail.MAIL_FUNCID_UNION_AGREE, paras);
	}

	// {s%}拒绝了你的入帮申请
	public void unionApplyRefuse(int roleId, String unionName) throws BizException {
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_NUM, unionName));
		sendMail(roleId, Mail.MAIL_TYPE_SYSTEM, Mail.MAIL_FUNCID_UNION_REFUSE, paras);
	}

	// {%s}将你请离了帮派
	public void unionPleaseLeave(int roleId, Role doc) throws BizException {
		List<MailParaVO> paras = new ArrayList<MailParaVO>();
		paras.add(new MailParaVO(Mail.MAIL_PARA_TYPE_ROLE, doc.getName(), doc.getCls()));
		sendMail(roleId, Mail.MAIL_TYPE_SYSTEM, Mail.MAIL_FUNCID_UNION_OUT, paras);
	}

	// 玩家{%s}与您志趣相投，惺惺相惜，欲邀您携手共闯江湖。
	public void frendApplyMail(int roleId, List<MailParaVO> paras) throws BizException {
		sendMail(roleId, Mail.MAIL_TYPE_FREND, Mail.MAIL_FUNCID_FREND_APPLY, paras);
	}

	// {%s}:{%s}
	public void frendMessageMail(int roleId, List<MailParaVO> paras) throws BizException {
		sendMail(roleId, Mail.MAIL_TYPE_FREND, Mail.MAIL_FUNCID_FREND_MESSAGE, paras);
	}

	// 好友{%s}与你切磋武技，被你轻松击败
	public void frendFightWinMail(int roleId, List<MailParaVO> paras) throws BizException {
		sendMail(roleId, Mail.MAIL_TYPE_FREND, Mail.MAIL_FUNCID_FREND_FIGHT_WIN, paras);
	}

	// 好友{%s}与你切磋武技，你不慎落败
	public void frendFightFailMail(int roleId, List<MailParaVO> paras) throws BizException {
		sendMail(roleId, Mail.MAIL_TYPE_FREND, Mail.MAIL_FUNCID_FREND_FIGHT_FAIL, paras);
	}

	// 好友{%s}与你志趣不和，与您割袍断意，分道扬镳
	public void frendBreakMail(int roleId, List<MailParaVO> paras) throws BizException {
		sendMail(roleId, Mail.MAIL_TYPE_FREND, Mail.MAIL_FUNCID_FREND_BREAK, paras);
	}

}