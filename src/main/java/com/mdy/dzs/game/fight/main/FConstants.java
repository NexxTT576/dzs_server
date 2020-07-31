package com.mdy.dzs.game.fight.main;

public class FConstants {

	public static final int Att_Lead		=0;		//lead
	public static final int Att_Force		=1;
	public static final int Att_Smart		=2;
				
	public static final int Att_Life		=0;		//base/baseRate
	public static final int Att_Attack		=1;
	public static final int Att_Defense		=2;
	public static final int Att_DefenM		=3;	
	
	public static final int Att_LifeR		=0;		//base/baseRate
	public static final int Att_AttackR		=1;
	public static final int Att_DefenseR	=2;
	public static final int Att_DefenMR		=3;	
	
	public static final int Att_Hit			=0;		//baseMod
	public static final int Att_Miss		=1;
	public static final int Att_Crit		=2;
	public static final int Att_Resist		=3;
	public static final int Att_Destory		=4;
	public static final int Att_Block		=5;
	
	public static final int Att_Physic		=0;		//rare/rareRate
	public static final int Att_Magic		=1;
	public static final int Att_Poison		=2;
	public static final int Att_Fire		=3;
	public static final int Att_PhysicD		=4;		
	public static final int Att_MagicD		=5;
	public static final int Att_PoisonD		=6;
	public static final int Att_FireD		=7;
	
	public static final int Att_PhysicR		=0;		//rare/rareRate
	public static final int Att_MagicR		=1;
	public static final int Att_PoisonR		=2;
	public static final int Att_FireR		=3;
	public static final int Att_PhysicDR	=4;		
	public static final int Att_MagicDR		=5;
	public static final int Att_PoisonDR	=6;
	public static final int Att_FireDR		=7;
	
	public static final int Att_healV		=0;		//heal
	public static final int Att_healR		=1;
	public static final int Att_healedV		=2;
	public static final int Att_healedR		=3;
	
	
	public static final int AttPos_base		=21;
	public static final int AttPos_baseRate	=31;
	public static final int AttPos_baseMod	=61;
	public static final int AttPos_rare		=41;
	public static final int AttPos_rareRate	=51;
	
	//基础三围（统帅；武力；智力）
	public static final int AttInd_Lead		=11;		//lead
	public static final int AttInd_Force	=12;
	public static final int AttInd_Smart	=13;
	//基础属性组（生命；攻击；物防；法防）
	public static final int AttInd_Life		=21;		//base
	public static final int AttInd_Attack	=22;
	public static final int AttInd_Defense	=23;
	public static final int AttInd_DefenM	=24;	
	//基础比例组（生命比例；攻击比例；物防比例；法防比例）
	public static final int AttInd_LifeR	=31;		//baseRate 	//0.0001
	public static final int AttInd_AttackR	=32;					//0.0001
	public static final int AttInd_DefenseR	=33;					//0.0001
	public static final int AttInd_DefenMR	=34;					//0.0001	
	//稀有属性组（物理伤害加成；法术伤害加成；中毒伤害加成；火焰伤害加成；
	//		物理伤害减免；法术伤害减免；中毒伤害减免；火焰伤害减免）
	public static final int AttInd_Physic	=41;		//rare
	public static final int AttInd_Magic	=42;
	public static final int AttInd_Poison	=43;
	public static final int AttInd_Fire		=44;
	public static final int AttInd_PhysicD	=45;		
	public static final int AttInd_MagicD	=46;
	public static final int AttInd_PoisonD	=47;
	public static final int AttInd_FireD	=48;
	//稀有比例组（物理伤害加成比例；法术伤害加成比例；中毒伤害加成比例；火焰伤害加成比例；
	//		物理伤害减免比例；法术伤害减免比例；中毒伤害减免比例；火焰伤害减免比例）
	public static final int AttInd_PhysicR	=51;		//rareRate`	//0.0001
	public static final int AttInd_MagicR	=52;					//0.0001
	public static final int AttInd_PoisonR	=53;					//0.0001
	public static final int AttInd_FireR	=54;					//0.0001
	public static final int AttInd_PhysicDR	=55;					//0.0001		
	public static final int AttInd_MagicDR	=56;					//0.0001
	public static final int AttInd_PoisonDR	=57;					//0.0001
	public static final int AttInd_FireDR	=58;
	//修正属性组（命中；闪避；暴击；抗暴；破击；格挡）
	public static final int AttInd_Hit		=61;		//baseMod 	//0.0001
	public static final int AttInd_Miss		=62;					//0.0001
	public static final int AttInd_Crit		=63;					//0.0001
	public static final int AttInd_Resist	=64;					//0.0001
	public static final int AttInd_Destory	=65;					//0.0001
	public static final int AttInd_Block	=66;					//0.0001
	
	//治疗效果组（治疗效果加成数值；治疗效果加成比例；被治疗效果加成数值；被治疗效果加成比例）
	public static final int AttInd_healV	=71;		//heal
	public static final int AttInd_healR	=72;					//0.0001
	public static final int AttInd_healedV	=73;
	public static final int AttInd_healedR	=74;					//0.0001
	
	//最终(暴伤倍率加成;暴伤倍率减免;最终伤害加成;最终伤害减免;怒气值) //finnal
	public static final int AttInd_FCritR		=75;
	public static final int AttInd_FCritDR		=76;
	public static final int AttInd_FHarmV		=77;
	public static final int AttInd_FHarmDV		=78;
	public static final int AttInd_Anger		=79;
	
	public static final int TalentCond_miss=1;
	public static final int TalentCond_hit=2;
	public static final int TalentCond_life=3;
	public static final int TalentCond_die=4;
	public static final int TalentCond_aStart=5;
	public static final int TalentCond_aEnd=6;
	public static final int TalentCond_aStartBuff=7;
	
	public static final int AttrAryInd_lead=11;
	public static final int AttrAryInd_base=21;
	public static final int AttrAryInd_baseRate=31;
	public static final int AttrAryInd_baseMod=61;
	public static final int AttrAryInd_rare=41;
	public static final int AttrAryInd_rareRate=51;
	public static final int AttrAryInd_heal=71;

}
