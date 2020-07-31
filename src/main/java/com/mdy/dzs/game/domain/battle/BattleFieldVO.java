package com.mdy.dzs.game.domain.battle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mdy.dzs.game.domain.packet.PacketExtend;

/**
 * 
 * @author fangtong E-mail: 10786912@qq.com
 * @version 创建时间：2014年9月24日 上午11:58:22
 */
public class BattleFieldVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@link BattleFieldStarInfoVO}
	 */
	private Map<Integer, BattleFieldStarInfoVO> starInfoVOs;
	/**
	 * {@link BattleFieldInfoVO}
	 */
	private BattleFieldInfoVO infoVO;

	/**
	 * {@link PacketExtend}
	 */
	private List<PacketExtend> bagVO;

	public Map<Integer, BattleFieldStarInfoVO> getStarInfoVOs() {
		return starInfoVOs;
	}

	public void setStarInfoVOs(Map<Integer, BattleFieldStarInfoVO> starInfoVOs) {
		this.starInfoVOs = starInfoVOs;
	}

	public BattleFieldInfoVO getInfoVO() {
		return infoVO;
	}

	public void setInfoVO(BattleFieldInfoVO infoVO) {
		this.infoVO = infoVO;
	}

	public List<Serializable> toSerializable() {
		return Arrays.asList((Serializable) starInfoVOs, (Serializable) infoVO, (Serializable) bagVO);
	}

	public List<PacketExtend> getBagVO() {
		return bagVO;
	}

	public void setBagVO(List<PacketExtend> bagVO) {
		this.bagVO = bagVO;
	}

}
