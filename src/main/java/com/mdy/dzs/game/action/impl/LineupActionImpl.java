package com.mdy.dzs.game.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.data.domain.role.Open;
import com.mdy.dzs.game.ApplicationAwareAction;
import com.mdy.dzs.game.action.LineupAction;
import com.mdy.dzs.game.domain.IdObject;
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
import com.mdy.sharp.container.biz.BizException;

public class LineupActionImpl extends ApplicationAwareAction implements LineupAction {

	@Override
	public List<Serializable> seeLineup(String seeAcc, int pos) throws BizException {
		Role role = roleAO().queryByAccount(seeAcc);
		RoleLineup lineup = lineupAO().queryList(role, pos);
		packetAO().sortCardList(role, lineup.getCardList());
		return Arrays.asList((Serializable) lineup.getCardList(), new ArrayList<Object>(lineup.getEquipMap().values()),
				new ArrayList<Object>(lineup.getYuanMap().values()), role.getFaction());
	}

	@Override
	public List<Serializable> changeLineup(String acc, int pos, int subpos, int id) throws BizException {
		Role doc = roleAO().queryExistAccount(acc);
		Map<Integer, Object> selItemIdMap = new HashMap<Integer, Object>();
		Map<Integer, Object> selItemPosMap = new HashMap<Integer, Object>();
		Map<Integer, Object> selItemSubposMap = new HashMap<Integer, Object>();
		Object posItemInd = null;
		Object itemInd = null;

		int type = Packet.POS_CARD;
		if (subpos > 0 && subpos <= 4) {
			type = Packet.POS_EQUIP;
		} else if (subpos > 4 && subpos <= 6) {
			type = Packet.POS_GONG;
		} else if (subpos > 6) {
			type = Packet.POS_YUAN;
		}

		List<Object> selItemAry = packetAO().getPacketListByType(doc.getId(), type);

		List<Integer> fmtCardAry = doc.getFmtCardAry();

		// 传过来的pos已为对应顺序之 不是 人身上的站位值 不需要重新赋值
		// if(subpos == 0 && pos != 0){
		// RoleCard posCard = roleCardAO().queryByAccPos(acc,pos);
		// if(posCard != null){
		// int index = fmtCardAry.indexOf(posCard.getId());
		// if(index>0) pos = index;
		// }else{
		// throw BaseException.getGlobalException(
		// "pos "+pos+" not has card");
		// }
		// }

		int oldFmtCardId = fmtCardAry.get(pos);

		for (Object object : selItemAry) {
			IdObject ido = (IdObject) object;
			selItemIdMap.put(ido.getId(), object);
			if (subpos == 0) {
				// int fmtpos = fmtCardAry.indexOf(ido.getId());
				// selItemPosMap.put(fmtpos,object);
				selItemPosMap.put(ido.getPos(), object);
			}
			if (ido.getPos() == pos)
				selItemSubposMap.put(ido.getSubpos(), object);
		}
		//
		Open openData = cacheManager().getExistValueByKey(Open.class, Open.OPEN_SYSTEM_LINEUP);
		int posNow = 0;
		for (int ind : openData.getLevel()) {
			if (ind <= doc.getLevel())
				posNow++;
			else
				break;
		}
		//
		List<Integer> occAry = new ArrayList<Integer>();
		List<Integer> idsAry = new ArrayList<Integer>();
		List<RoleCard> lineupCards = roleCardAO().queryLineupListByRoldId(doc.getId());
		for (RoleCard roleCard : lineupCards) {
			occAry.add(roleCard.getPos());
			idsAry.add(roleCard.getId());
		}

		int oldEquipPos = 0;
		int oldPos1 = 0;
		int oldPos2 = 0;
		//
		if (pos == 0) {// 引导卡牌自动初始位置
			if (subpos != 0) {
				throw BaseException.getException(LineupException.EXCE_SUPPOS_ERROR, subpos);
			}
			RoleCard card = (RoleCard) selItemIdMap.get(id);
			if (card == null) {
				throw BaseException.getException(RoleCardException.EXCE_CARD_NOT_EXIST, id);
			}
			if (occAry.size() >= posNow) {
				throw BaseException.getException(LineupException.EXCE_SYSTEMLIMIT_ERROR);

			}
			int autoAry[] = { 2, 5, 3, 4, 6, 1 };
			for (int ind = 0; ind < autoAry.length; ind++) {
				if (occAry.indexOf(autoAry[ind]) < 0) {
					pos = autoAry[ind];
					break;
				}
			}
			pos = lineupAO().changeCardLineup(doc, pos, card, null);
			itemInd = card;
		} else {
			if (pos < 0 || pos > 6) {
				throw BaseException.getException(LineupException.EXCE_POS_ERROR, pos);
			}
			if (oldFmtCardId == 0 && idsAry.indexOf(id) < 0 && (occAry.size() + 1) > posNow) {// 位置上没人，新加上阵
				throw BaseException.getException(LineupException.EXCE_SYSTEMLIMIT_ERROR);
			}
			// //check subpos
			if (subpos != 0) {// 装备-gong-精元 精元开放检查
				int subposNow = 6;
				Open subOpenData = cacheManager().getExistValueByKey(Open.class, 12);
				for (Integer ind : subOpenData.getLevel()) {
					if (ind <= doc.getLevel())
						subposNow++;
					else
						break;
				}
				if (subpos > subposNow) {
					throw BaseException.getException(LineupException.EXCE_SUPPOS_ERROR, subpos);
				}
			}
			//
			// 上阵道具/卡牌
			itemInd = selItemIdMap.get(id);
			if (subpos == 0 && itemInd == null) {
				throw BaseException.getException(LineupException.EXCE_LINEUP_CARD_NOT_DOWN);// 武将不能卸下，只可以更换
			}
			if (subpos == 0) {// pos位置道具/卡牌
				RoleCard card = (RoleCard) itemInd;
				if (card.getPos() == 0 && fmtCardAry.get(pos) != 0) {
					posItemInd = selItemIdMap.get(fmtCardAry.get(pos));
				}
			}
			if (posItemInd == null) {
				posItemInd = subpos == 0 ? selItemPosMap.get(pos) : selItemSubposMap.get(subpos);
			}
			if (itemInd != null)
				oldPos1 = ((IdObject) itemInd).getPos();
			if (posItemInd != null)
				oldPos2 = ((IdObject) posItemInd).getPos();
			//
			if (subpos == 0 && posItemInd != null && ((RoleCard) posItemInd).getId() == doc.getFmtMainCardID()
					&& ((RoleCard) itemInd).getPos() == 0) {
				throw BaseException.getException(LineupException.EXCE_POS_ERROR);// !!
																					// 主角不能替换
			}

			if (id == 0) {// 卸下
				if (posItemInd == null) {
					throw BaseException.getException(LineupException.EXCE_LINEUP_CARD_NOT_DOWN);
				}
				// equip
				IdObject temp = (IdObject) posItemInd;
				if (temp.getPos() < 7 && temp.getSubpos() > 6) {// 精元
					temp.setSubpos(0);
				}
				temp.setPos(0);
			} else {// 更换
				// //非卡牌subpos
				if (itemInd == null) {
					throw BaseException.getException(LineupException.EXCE_LINEUP_CHANGEID_ERROR);
				}
				if (subpos != 0) {
					if (subpos < 7) {// 装备
						if (((IdObject) itemInd).getSubpos() != subpos) {
							throw BaseException.getException(LineupException.EXCE_SUPPOS_ERROR, subpos);
						}
					} else {// 精元
							// if(posItemInd[0] >= 0){
							// posItemInd[1].pos = 0;
							// posItemInd[1].subpos = 0;
							// }
							// 遍历有无重复type doc.yuanAry
						int typeCnt = 0;
						for (Object obj : selItemAry) {
							RoleYuan item = (RoleYuan) obj;
							if (item.getPos() == pos && item.getVariety() == ((RoleYuan) itemInd).getVariety()
									&& item.getSubpos() != subpos) {
								typeCnt++;
							}
						}
						if (typeCnt > 0) {
							throw BaseException.getException(LineupException.EXCE_LINEUP_YUAN_NOT_SAME);
						}
					}
				}
				//
				// //pos 设置
				IdObject temp = (IdObject) itemInd;
				if (posItemInd != null) {
					IdObject posTemp = (IdObject) posItemInd;
					if (temp.getPos() > 0 && subpos == 0) {// card 互换位置
						int tpos = temp.getPos();
						temp.setPos(posTemp.getPos());
						posTemp.setPos(tpos);
					} else {// 替换
						if (subpos == 0) {// 卡牌条替换
							lineupAO().changeCardLineup(doc, posTemp.getPos(), (RoleCard) itemInd,
									(RoleCard) posItemInd);

						} else {
							if (temp.getPos() != 0) {
								oldEquipPos = temp.getPos();
							}
							temp.setPos(pos);
							posTemp.setPos(0);
						}
					}
				} else {
					if (temp.getPos() != 0) {
						oldEquipPos = temp.getPos();
					}
					temp.setPos(pos);
				}
				temp.setSubpos(subpos);
			}
		}

		lineupAO().saveLineupObjByType(type, itemInd, oldPos1);
		lineupAO().saveLineupObjByType(type, posItemInd, oldPos2);
		roleAO().updateLineup(doc);

		if (subpos <= 6) {
			fmtCardAry = doc.getFmtCardAry();
			RoleCard fmtCard = roleCardAO().queryById(fmtCardAry.get(pos));
			if (oldFmtCardId != 0 && oldFmtCardId != fmtCard.getId()) {
				RoleCard oldFmtCard = roleCardAO().queryExistCardById(doc, oldFmtCardId);
				int newPos = fmtCardAry.indexOf(oldFmtCard.getId());
				roleAO().updateCardRelation(doc, oldFmtCard, newPos);
			}
			roleAO().updateCardRelation(doc, fmtCard, pos);
		}
		if (oldEquipPos != 0) {
			RoleCard oldEquipCard = roleCardAO().queryById(fmtCardAry.get(oldEquipPos));
			roleAO().updateCardRelation(doc, oldEquipCard, oldEquipPos);
		}

		roleAO().fmtPropUpdate(doc);
		RoleLineup lineup = lineupAO().queryList(doc, 0);
		packetAO().sortCardList(doc, lineup.getCardList());
		return Arrays.asList((Serializable) lineup.getCardList(), new ArrayList<Object>(lineup.getEquipMap().values()),
				new ArrayList<Object>(lineup.getYuanMap().values()));
	}

	@Override
	public List<Serializable> fastChangeEquip(String acc, int pos, int itemId, int type) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		List<Serializable> rlist = null;
		if (type == 0) {
			rlist = fastChageE(role, pos, itemId);

		} else {

			rlist = fastChageZhenqi(role, pos);
		}
		return rlist;
	}

	// 更换装备
	public boolean changeBestEquipByType(Role role, int pos, Equip equip, Equip thisEquip) throws BizException {
		Equip bequip = null;
		bequip = roleEquipAO().queryExistEquipById(role.getId(), equip.getId());
		if (bequip != null) {
			bequip.setPos(pos);
			if (thisEquip == null) {
				pos = 0;
			} else {
				thisEquip.setPos(0);
				lineupAO().saveLineupObjByType(Packet.POS_EQUIP, thisEquip, pos);
			}
			lineupAO().saveLineupObjByType(Packet.POS_EQUIP, bequip, pos);

		}
		return true;
	}

	// 更换功
	public boolean changeBestGong(Role role, int pos, RoleGong gong, RoleGong thisGong) throws BizException {
		RoleGong bgong = null;
		bgong = roleGongAO().queryExistGongById(role.getId(), gong.getId());

		if (bgong != null) {
			bgong.setPos(pos);
			if (thisGong == null) {
				pos = 0;
			} else {
				thisGong.setPos(0);
				lineupAO().saveLineupObjByType(Packet.POS_GONG, thisGong, pos);
			}
			lineupAO().saveLineupObjByType(Packet.POS_GONG, bgong, pos);
		}
		return true;
	}

	public List<Serializable> fastChageE(Role role, int pos, int itemId) throws BizException {
		// 是否有装备科替换
		boolean isEquipChange = false;
		for (int i = 1; i <= 4; i++) {
			Equip thisequip = null;
			// 以前位置是否有装备
			List<Equip> equiplist = roleAO().queryEquipByAccPos(role.getId(), pos, i);
			if (equiplist.size() > 0) {
				thisequip = equiplist.get(0);
			}
			// 获取最好的装备
			Equip betterEquip = roleEquipAO().getBetterEquip(role.getId(), i, pos);
			// 穿上最好的装备
			if (betterEquip != null) {
				isEquipChange |= changeBestEquipByType(role, pos, betterEquip, thisequip);
			}
		}
		for (int i = 5; i <= 6; i++) {
			RoleGong roleGong = null;
			// 以前位置是否有内外功
			List<RoleGong> Gonglist = roleGongAO().queryGongByaccpos(role.getId(), pos, i);
			if (Gonglist.size() > 0) {
				roleGong = Gonglist.get(0);
			}
			// 获取最好的功
			RoleGong betterGong = roleGongAO().getBetterGong(role.getId(), i, pos, itemId);
			// 穿上最好的功
			if (betterGong != null) {
				isEquipChange |= changeBestGong(role, pos, betterGong, roleGong);
			}
		}
		if (!isEquipChange) {
			throw BaseException.getException(LineupException.EXCE_LINEUP_NO_EQUIP_CHANGE); // 无装备可更换
		}
		List<Integer> fmtCardAry = role.getFmtCardAry();
		RoleCard fmtCard = roleCardAO().queryById(fmtCardAry.get(pos));
		roleAO().updateCardRelation(role, fmtCard, pos);
		roleAO().fmtPropUpdate(role);
		RoleLineup lineup = lineupAO().queryList(role, 0);
		packetAO().sortCardList(role, lineup.getCardList());
		return Arrays.asList((Serializable) lineup.getCardList(), new ArrayList<Object>(lineup.getEquipMap().values()),
				new ArrayList<Object>(lineup.getYuanMap().values()));

	}

	public List<Serializable> fastChageZhenqi(Role role, int pos) throws BizException {
		// 是否有真气科替换
		boolean isZhenqiChange = false;
		int subposNow = 6;
		Open subOpenData = cacheManager().getExistValueByKey(Open.class, 12);
		for (Integer ind : subOpenData.getLevel()) {
			if (ind <= role.getLevel())
				subposNow++;
			else
				break;
		}
		for (int i = 7; i <= subposNow; i++) {
			RoleYuan thisyuan = null;
			// 以前位置是否有真气
			List<RoleYuan> thisYuanlist = roleYuanAO().queryListByAccPos(role.getId(), pos, i);
			if (thisYuanlist.size() > 0) {
				thisyuan = thisYuanlist.get(0);
			}
			// 获取最好的真气
			RoleYuan betterYuan = roleYuanAO().getBetterYuan(role.getId(), i, pos, thisyuan);
			// 穿上最好的真气
			if (betterYuan != null) {
				betterYuan.setSubpos(i);
				isZhenqiChange |= changeBestYuanByType(role, pos, betterYuan, thisyuan);
			}
		}
		if (!isZhenqiChange) {
			throw BaseException.getException(LineupException.EXCE_LINEUP_NO_YUAN_CHANGE); // 无装备可更换
		}
		RoleLineup lineup = lineupAO().queryList(role, 0);
		packetAO().sortCardList(role, lineup.getCardList());
		return Arrays.asList((Serializable) lineup.getCardList(), new ArrayList<Object>(lineup.getEquipMap().values()),
				new ArrayList<Object>(lineup.getYuanMap().values()));
	}

	// 更换yuan
	public boolean changeBestYuanByType(Role role, int pos, RoleYuan yuan, RoleYuan thisYuan) throws BizException {
		if (yuan != null) {
			yuan.setPos(pos);
			if (thisYuan == null) {
				pos = 0;
			} else {
				thisYuan.setPos(0);
				thisYuan.setSubpos(0);
				lineupAO().saveLineupObjByType(Packet.POS_YUAN, thisYuan, pos);
			}
			lineupAO().saveLineupObjByType(Packet.POS_YUAN, yuan, pos);
		}
		return true;
	}

	@Override
	public List<Integer> verifyLineup(String acc) throws BizException {
		Role role = roleAO().queryExistAccount(acc);
		return lineupAO().verifyLineup(role);
	}
}
