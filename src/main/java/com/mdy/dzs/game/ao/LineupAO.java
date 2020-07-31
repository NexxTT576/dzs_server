package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.card.Card;
import com.mdy.dzs.game.domain.calcattack.CalcRoleEquip;
import com.mdy.dzs.game.domain.calcattack.CalcRoleGong;
import com.mdy.dzs.game.domain.card.RoleCard;
import com.mdy.dzs.game.domain.equip.Equip;
import com.mdy.dzs.game.domain.gong.RoleGong;
import com.mdy.dzs.game.domain.lineup.RoleLineup;
import com.mdy.dzs.game.domain.packet.Packet;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.yuan.RoleYuan;
import com.mdy.dzs.game.exception.BaseException;
import com.mdy.dzs.game.exception.LineupException;
import com.mdy.dzs.game.exception.RoleCardException;
import com.mdy.dzs.game.manager.CacheManager;
import com.mdy.dzs.game.manager.RoleAttackCalcManager;
import com.mdy.sharp.container.biz.BizException;

/**
 * 阵容
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月18日 下午3:36:51
 */
public class LineupAO extends BaseAO {

	private CacheManager cacheManager;
	private RoleAttackCalcManager calcManager;

	public LineupAO(CacheManager cacheManager, RoleAttackCalcManager calcManager) {
		this.cacheManager = cacheManager;
		this.calcManager = calcManager;
	}

	public List<Integer> verifyLineup(Role doc) {
		List<RoleCard> lineup = roleCardDAO().queryLineupListByRoldId(doc.getId());
		List<Integer> lineupId = doc.getFmtCardAry();
		List<RoleCard> errCard = new ArrayList<RoleCard>();
		Map<Integer, Integer> inId = new HashMap<Integer, Integer>();
		List<Integer> poss = new ArrayList<Integer>();
		boolean resetPos = false;
		for (RoleCard rc : lineup) {
			int index = lineupId.indexOf(rc.getId());
			if (index == -1) {
				errCard.add(rc);
			} else {
				inId.put(index, rc.getId());
			}
			if (poss.contains(rc.getPos())) {
				resetPos = true;
			} else {
				poss.add(rc.getPos());
			}
		}
		List<Integer> res = new ArrayList<Integer>();
		for (RoleCard rc : errCard) {
			int oldPos = rc.getPos();
			rc.setPos(0);
			saveLineupObjByType(Packet.POS_CARD, rc, oldPos);
		}
		if (resetPos) {
			int pos = 1;
			for (RoleCard rc : lineup) {
				int oldPos = rc.getPos();
				if (oldPos == 0)
					continue;
				rc.setPos(pos);
				saveLineupObjByType(Packet.POS_CARD, rc, oldPos);
				pos++;
			}
		}
		boolean update = false;
		for (int i = 0; i < lineupId.size(); i++) {
			if (lineupId.get(i) != 0 && !inId.containsKey(i)) {
				res.add(lineupId.get(i));
				lineupId.set(i, 0);
				update = true;
			}
		}
		if (update) {
			doc.setFmtCardAry(lineupId);
			roleDAO().updateLineup(doc);
		}
		return res;
	}

	/**
	 * 返回阵型列表
	 * 
	 * @param role
	 * @param pos
	 * @return
	 * @throws BizException
	 */
	public RoleLineup queryList(Role role, int pos) throws BizException {
		String acc = role.getAccount();
		int roleId = role.getId();
		List<Integer> fmtCardAry = role.getFmtCardAry();
		List<RoleCard> cardAry = roleCardDAO().queryListByRoleId(roleId);
		List<Equip> equipAry = equipDAO().queryListByAccount(roleId);
		List<RoleGong> gongAry = roleGongDAO().queryListByAccount(roleId);
		List<RoleYuan> yuanAry = roleYuanDAO().queryListByAccount(roleId);

		int level = role.getLevel();

		List<RoleCard> rtnCardAry = new ArrayList<RoleCard>();
		Map<Integer, List<Object>> rtnEquipObj = new LinkedHashMap<Integer, List<Object>>();
		Map<Integer, List<RoleYuan>> rtnYuanObj = new LinkedHashMap<Integer, List<RoleYuan>>();

		for (RoleCard ele : cardAry) {
			if ((pos > 0 && ele.getId() == fmtCardAry.get(pos)) // 上阵
					|| (pos == 0 && fmtCardAry.indexOf(ele.getId()) >= 0)) {// 下阵
				Card cardData = cacheManager.getExistValueByKey(Card.class, ele.getResId());
				// {id, name,resId,level,cls,star,base,relation,pos,lead}
				// objId,name,resId,level,levelLimit,cls,star,base,relation,pos,lead
				ele.setObjId(ele.getId());
				ele.setName(cardData.getName());
				ele.setLevelLimit(level);
				rtnCardAry.add(ele);
			}
		}
		;

		for (int i = 1; i < 7; i++) {
			rtnEquipObj.put(i, new ArrayList<Object>());
			rtnYuanObj.put(i, new ArrayList<RoleYuan>());
		}

		for (Equip ele : equipAry) {
			if ((pos > 0 && ele.getPos() == pos) || (pos == 0 && ele.getPos() > 0 && ele.getPos() < 7)) {
				List<Object> temps = rtnEquipObj.get(ele.getPos());
				CalcRoleEquip calc = calcManager.getCalcEquip(ele.getResId());
				ele.setViewBase(calc.getBaseView(ele.getLevel(), ele.getProps()));
				temps.add(ele);
			}
		}
		;
		for (RoleGong ele : gongAry) {
			if ((pos > 0 && ele.getPos() == pos) || (pos == 0 && ele.getPos() > 0 && ele.getPos() < 7)) {
				List<Object> temps = rtnEquipObj.get(ele.getPos());
				CalcRoleGong calc = calcManager.getCalcGong(ele.getResId());
				ele.setBaseViewRate(calc.getBaseRate(ele.getLevel()));
				temps.add(ele);
			}
		}
		;

		RoleYuan yuan;
		for (int i = 0; i < yuanAry.size(); i++) {
			yuan = yuanAry.get(i);
			if ((pos > 0 && yuan.getPos() == pos) || (pos == 0 && yuan.getPos() > 0 && yuan.getPos() < 7)) {
				List<RoleYuan> temps = rtnYuanObj.get(yuan.getPos());
				temps.add(yuan);
			}
		}
		return new RoleLineup(acc, pos, rtnCardAry, rtnEquipObj, rtnYuanObj);
	}

	/**
	 * 卡牌位置 update role fmtcard/rolecard pos
	 * 
	 * @param doc
	 * @param occAry
	 * @param itemInd
	 */
	public int changeCardLineup(Role doc, int pos, RoleCard itemInd, RoleCard posItemInd) throws BizException {
		if (pos == 0) {
			throw BaseException.getException(LineupException.EXCE_POS_ERROR, pos);
		}

		List<Integer> fmtCardAry = doc.getFmtCardAry();
		int fmtpos = 0;
		boolean checkPos = true;
		for (int indx = 1; indx < fmtCardAry.size(); indx++) {
			int fCardId = fmtCardAry.get(indx);
			if (fCardId == itemInd.getId()) {
				throw BaseException.getException(RoleCardException.EXCE_CARD_IN_LINEUP, fCardId);
			}
			RoleCard curCard = roleCardDAO().query(fCardId);
			if (curCard != null && curCard.getResId() == itemInd.getResId()
					&& !(posItemInd != null && fCardId == posItemInd.getId())) {
				throw BaseException.getException(LineupException.EXCE_LINEUP_CARD_SAME);
			}

			if (checkPos && ((posItemInd != null && fCardId == posItemInd.getId()) || fCardId == 0)) {
				fmtpos = indx;
				checkPos = false;
			}
		}
		if (fmtpos == 0) {
			throw BaseException.getException(LineupException.EXCE_SYSTEMLIMIT_ERROR);
		}
		int itemFmtPos = fmtCardAry.indexOf(itemInd.getId());
		if (posItemInd != null) {
			posItemInd.setPos(itemInd.getPos());
			if (itemFmtPos > 0)
				fmtCardAry.set(itemFmtPos, posItemInd.getId());
		}
		itemInd.setPos(pos);
		fmtCardAry.set(fmtpos, itemInd.getId());

		return fmtpos;
	}

	public void saveLineupObjByType(int type, Object updateObj, int oldPos) {
		// save
		switch (type) {
		case Packet.POS_CARD:
			roleCardDAO().update((RoleCard) updateObj, oldPos);
			break;
		case Packet.POS_EQUIP:
			equipDAO().update((Equip) updateObj, oldPos);
			break;
		case Packet.POS_GONG:
			roleGongDAO().update((RoleGong) updateObj, oldPos);
			break;
		case Packet.POS_YUAN:
			roleYuanDAO().update((RoleYuan) updateObj, oldPos);
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {
		List<Integer> arr = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 0, 0, 0));
		System.out.print(arr.remove(new Integer(0)));
		System.out.print(arr);
	}
}
