package com.mdy.dzs.game.fight.main.fighttalent;

import java.util.List;

import com.mdy.dzs.game.fight.domain.fighttalent.FightTalentVO;
import com.mdy.dzs.game.fight.main.FightMain;
import com.mdy.dzs.game.fight.main.Fighter;

public interface FightTalentEffect {

	boolean effect(FightMain main,Fighter src,FightTalentVO vo ,List<Fighter> defaultTargets,List<Fighter> missTargets );
	int getNode(); 
	Object getParam();
}
