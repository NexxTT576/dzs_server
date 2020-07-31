package com.mdy.dzs.data.domain.shentong;

import java.io.Serializable;
import java.util.List;


/**
 * 神通模型
 * @author 房曈
 *
 */
public class Talent implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
	
	
	public static final int ST_TYPE_普通 = 1;
	public static final int ST_TYPE_援护 = 2;


	/**神通id*/
	private int id;
	/**神通名称*/
	private String name;
	/**神通描述*/
	private String type;
	/**原神通描述*/
	private String old;
	/**所属神通组*/
	private int shentong;
	/**神通名所用的图片 在res/*/
	private String imageName;
	/**显示节点（0-不显示；1-所有动作之前；2-所有动作之后；3-结算伤害的时候）*/
	private int show;
	/**属性1触发节点*/
	private List<Integer> node;
	/**属性1触发条件*/
	private List<Integer> attCond;
	/**属性1触发条件参数*/
	private List<Integer> attPara;
	/**属性1触发概率*/
	private List<Integer> attProb;
	/**属性目标方组*/
	private List<Integer> attSide;
	/**属性目标组*/
	private List<Integer> attTarget;
	/**属性id组*/
	private List<Integer> attId;
	/**计算方式组*/
	private List<Integer> attCalc;
	/**系数组*/
	private List<Integer> attCoff;
	/**buff触发节点*/
	private List<Integer> nodeB;
	/**buff触发条件*/
	private List<Integer> attCondB;
	/**buff触发条件参数*/
	private List<Integer> attParaB;
	/**buff触发概率*/
	private List<Integer> attProbB;
	/**属性目标方组*/
	private List<Integer> attSideB;
	/**属性目标*/
	private List<Integer> attTargetB;
	/**附加buffid组*/
	private List<Integer> attBuff;
	/**免疫状态id组*/
	private List<Integer> attDBuff;
	/**技能触发节点*/
	private List<Integer> nodeS;
	/**技能触发条件*/
	private List<Integer> attCondS;
	/**技能触发条件参数*/
	private List<Integer> attParaS;
	/**技能触发概率*/
	private List<Integer> attProbS;
	/**属性目标方组*/
	private List<Integer> attSideS;
	/**属性目标组*/
	private List<Integer> attTargetS;
	/**触发技能id*/
	private List<Integer> attSkill;
	/**神通战斗力相关*/
	private int factor;
	private int stType;
	private int para1;
	private int para2;
	private int para3;
	private int para4;
	
	public int getPara1() {
		return para1;
	}
	public void setPara1(int para1) {
		this.para1 = para1;
	}
	public int getPara2() {
		return para2;
	}
	public void setPara2(int para2) {
		this.para2 = para2;
	}
	public int getPara3() {
		return para3;
	}
	public void setPara3(int para3) {
		this.para3 = para3;
	}
	public int getPara4() {
		return para4;
	}
	public void setPara4(int para4) {
		this.para4 = para4;
	}
	

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getOld(){
		return this.old;
	}
	public void setOld(String old){
		this.old=old;
	}
	public int getShentong(){
		return this.shentong;
	}
	public void setShentong(int shentong){
		this.shentong=shentong;
	}
	public String getImageName(){
		return this.imageName;
	}
	public void setImageName(String imageName){
		this.imageName=imageName;
	}
	public int getShow(){
		return this.show;
	}
	public void setShow(int show){
		this.show=show;
	}
	public List<Integer> getNode(){
		return this.node;
	}
	public void setNode(List<Integer> node){
		this.node=node;
	}
	public List<Integer> getAttCond(){
		return this.attCond;
	}
	public void setAttCond(List<Integer> attCond){
		this.attCond=attCond;
	}
	public List<Integer> getAttPara(){
		return this.attPara;
	}
	public void setAttPara(List<Integer> attPara){
		this.attPara=attPara;
	}
	public List<Integer> getAttProb(){
		return this.attProb;
	}
	public void setAttProb(List<Integer> attProb){
		this.attProb=attProb;
	}
	public List<Integer> getAttSide(){
		return this.attSide;
	}
	public void setAttSide(List<Integer> attSide){
		this.attSide=attSide;
	}
	public List<Integer> getAttTarget(){
		return this.attTarget;
	}
	public void setAttTarget(List<Integer> attTarget){
		this.attTarget=attTarget;
	}
	public List<Integer> getAttId(){
		return this.attId;
	}
	public void setAttId(List<Integer> attId){
		this.attId=attId;
	}
	public List<Integer> getAttCalc(){
		return this.attCalc;
	}
	public void setAttCalc(List<Integer> attCalc){
		this.attCalc=attCalc;
	}
	public List<Integer> getAttCoff(){
		return this.attCoff;
	}
	public void setAttCoff(List<Integer> attCoff){
		this.attCoff=attCoff;
	}
	public List<Integer> getNodeB(){
		return this.nodeB;
	}
	public void setNodeB(List<Integer> nodeB){
		this.nodeB=nodeB;
	}
	public List<Integer> getAttCondB(){
		return this.attCondB;
	}
	public void setAttCondB(List<Integer> attCondB){
		this.attCondB=attCondB;
	}
	public List<Integer> getAttParaB(){
		return this.attParaB;
	}
	public void setAttParaB(List<Integer> attParaB){
		this.attParaB=attParaB;
	}
	public List<Integer> getAttProbB(){
		return this.attProbB;
	}
	public void setAttProbB(List<Integer> attProbB){
		this.attProbB=attProbB;
	}
	public List<Integer> getAttSideB(){
		return this.attSideB;
	}
	public void setAttSideB(List<Integer> attSideB){
		this.attSideB=attSideB;
	}
	public List<Integer> getAttTargetB(){
		return this.attTargetB;
	}
	public void setAttTargetB(List<Integer> attTargetB){
		this.attTargetB=attTargetB;
	}
	public List<Integer> getAttBuff(){
		return this.attBuff;
	}
	public void setAttBuff(List<Integer> attBuff){
		this.attBuff=attBuff;
	}
	public List<Integer> getAttDBuff(){
		return this.attDBuff;
	}
	public void setAttDBuff(List<Integer> attDBuff){
		this.attDBuff=attDBuff;
	}
	public List<Integer> getNodeS(){
		return this.nodeS;
	}
	public void setNodeS(List<Integer> nodeS){
		this.nodeS=nodeS;
	}
	public List<Integer> getAttCondS(){
		return this.attCondS;
	}
	public void setAttCondS(List<Integer> attCondS){
		this.attCondS=attCondS;
	}
	public List<Integer> getAttParaS(){
		return this.attParaS;
	}
	public void setAttParaS(List<Integer> attParaS){
		this.attParaS=attParaS;
	}
	public List<Integer> getAttProbS(){
		return this.attProbS;
	}
	public void setAttProbS(List<Integer> attProbS){
		this.attProbS=attProbS;
	}
	public List<Integer> getAttSideS(){
		return this.attSideS;
	}
	public void setAttSideS(List<Integer> attSideS){
		this.attSideS=attSideS;
	}
	public List<Integer> getAttTargetS(){
		return this.attTargetS;
	}
	public void setAttTargetS(List<Integer> attTargetS){
		this.attTargetS=attTargetS;
	}
	public List<Integer> getAttSkill(){
		return this.attSkill;
	}
	public void setAttSkill(List<Integer> attSkill){
		this.attSkill=attSkill;
	}
	public int getStType() {
		return stType;
	}
	public void setStType(int stType) {
		this.stType = stType;
	}
	public int getFactor() {
		return factor;
	}
	public void setFactor(int factor) {
		this.factor = factor;
	}
}