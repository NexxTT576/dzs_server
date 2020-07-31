package com.mdy.dzs.game.action.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.MailAction;
import com.mdy.dzs.game.domain.mail.Mail;
import com.mdy.dzs.game.domain.mail.MailVO;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.MailException;
import com.mdy.dzs.game.util.Constants;
import com.mdy.dzs.game.util.DateUtil;
import com.mdy.sharp.container.biz.BizException;

public class MailActionImpl extends ApplicationAwareAction implements MailAction {

	@Override
	public Map<String, Object> mlist(String acc, int mailId, int type) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Open openData = cacheManager().getExistValueByKey(Open.class, 39);// key:system
		if (doc.getLevel() < openData.getLevel().get(0)) {
			throw BaseException.getException(MailException.EXCE_MAIL_LEVEL_LIMIT);
		}

		Map<String, Object> rMap = new HashMap<String, Object>();
		List<Mail> mailList = mailAO().queryListByFilter(doc.getId(), mailId, type);
		if (mailId != 0 && mailList.size() == 0) {
			throw BaseException.getException(MailException.EXCE_MAIL_NO_MORE);
		}
		List<MailVO> rtnMailList = new ArrayList<MailVO>();
		for (int i = 0; i < mailList.size(); i++) {
			Mail curMail = mailList.get(i);
			int disDay = DateUtil.getDateDiff(curMail.getTime());
			rtnMailList.add(
					new MailVO(curMail.getType(), curMail.getStrId(), curMail.getId(), disDay, curMail.getParas()));
		}
		int mailCnt = 0;
		if (mailId == 0) {
			mailCnt = mailAO().queryCount(doc.getId(), mailId, type);
			// 新进页签，更新查看到时间[long战斗，long好友，long系统]
			List<Long> mailViewAry = doc.getMailLastViewAry();
			if (type == Mail.MAIL_TYPE_BATTLE) {// 战斗
				mailViewAry.set(0, new Date().getTime());
			} else if (type == Mail.MAIL_TYPE_FREND) {// 好友
				mailViewAry.set(1, new Date().getTime());
			} else if (type == Mail.MAIL_TYPE_SYSTEM) {// 系统
				mailViewAry.set(2, new Date().getTime());
			}
			doc.setMailLastViewAry(mailViewAry);
			roleAO().updateMailLastTime(doc);
		}
		rMap.put("mailList", rtnMailList);
		rMap.put("mailCnt", mailCnt > Constants.MailSaveNum ? Constants.MailSaveNum : mailCnt);
		return rMap;
	}

	@Override
	public void tidyTabel() {
		mailAO().tidyTable();
	}

}
