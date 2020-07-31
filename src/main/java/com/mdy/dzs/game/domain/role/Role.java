package com.mdy.dzs.game.domain.role;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 角色模型
 * @author 房曈
 *
 */
public class Role implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;
	
	public static final int PROP_LIMIT_体力	= 0;
	public static final int PROP_LIMIT_耐力	= 1;
	public static final int PROP_LIMIT_经验	= 2;

	public static final String TYPE_ROBOT = "robot";

	int id;
	/**账号*/
	String account;
	/**平台账号*/
	private String nikeName;
	/**名称*/
	String name;
	/**用户源*/
	private String type;
	/**帮派*/
	private String faction;
	/**资源id*/
	private int resId;
	/**性别*/
	private int sex;
	/**创建时间*/
	private Date createTime;
	/**等级*/
	int level;
	/**主卡阶数*/
	int cls;
	/**vip等级*/
	int vip;
	/**元宝数*/
	int gold;
	/**银币数*/
	int silver;
	/**体力值*/
	int physVal;
	/**耐力值*/
	int resisVal;
	/**声望*/
	private int popual;
	/**经验值*/
	int exp;
	/**侠魂*/
	private int soul;
	/**公会*/
	private int social;
	/**真气*/
	private int zhenQiVal;
	/**魂玉*/
	private int hunYuVal;
	/**战斗力*/
	int attack;
	/**最大战斗力*/
	private int maxAttack;
	/**恢复时间数组：体力/耐力*/
	private List<Integer> addTimeAry;
	/**上限数组：体力/耐力/经验/声望*/
	List<Integer> propLimitAry;

	/**背包上限数组 [0:null,1:装备 2:时装  3:装备碎片 4:内外功 5:武将碎片 */
	private List<Integer> bagLimitAry;
	/**聚元等级 1-5 等级*/
	private int curCollLv;
	/**登陆时间*/
	private Date dayLoginTime;
	
	
	/**上阵卡牌条_id数组*/
	private List<Integer> fmtCardAry;
	/**阵容中主卡id*/
	private int fmtMainCardID;

	/**最后一次升级时间*/
	private Date levelUpLastTime;
	/**最近一次心跳时间*/
	private int heartLastTime;
	/**最近客栈休息时间*/
	private Date sleepLastTime;
	/**私聊消息查看到时间*/
	private List<Long> chatLastViewTime;
	/**邮件类型查看到时间*/
	private List<Long> mailLastViewAry;
	/**广播查看到的时间*/
	private Date broadcastViewTime;
	/** 新手引导的步骤计数*/
	private int guideStep;
	/**剧情步骤*/
	private List<Integer> helpStoryStep;
	/**连续登陆天数*/
	private int daysContinue;
	/**版本*/
	private String version;
	/**魅力值*/
	private int charm;
	/**每日领取好友赠送耐力计数*/
	private int getFriendNailiCnt;
	/**灵石*/
	private int lingShi;	
	/**最后登陆的设备类型*/
	private String lastDeviceType;
	/**最后登陆的设备版本*/
	private String lastDeviceVersion;
	/**最后登陆的设备id*/
	private String lastDeviceUUID;
	/**服务器id*/
	private String serverId;
	/**荣誉*/
	private int honor;

	
	//////////////////////////////////////
	private String operateCode;
	
	/**
	 * 
	 */
	public Role() {
		operateCode = UUID.randomUUID().toString().replaceAll("-","");
	}

	public String getAccount(){
		return this.account;
	}
	public void setAccount(String account){
		this.account=account;
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
	public String getFaction(){
		return this.faction;
	}
	public void setFaction(String faction){
		this.faction=faction;
	}
	public int getResId(){
		return this.resId;
	}
	public void setResId(int resId){
		this.resId=resId;
	}
	public int getSex(){
		return this.sex;
	}
	public void setSex(int sex){
		this.sex=sex;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getCls(){
		return this.cls;
	}
	public void setCls(int cls){
		this.cls=cls;
	}
	public int getVip(){
		return this.vip;
	}
	public void setVip(int vip){
		this.vip=vip;
	}
	public int getGold(){
		return this.gold;
	}
	public void setGold(int gold){
		this.gold=gold;
	}
	public int getSilver(){
		return this.silver;
	}
	public void setSilver(int silver){
		this.silver=silver;
	}
	public int getPhysVal(){
		return this.physVal;
	}
	public void setPhysVal(int physVal){
		this.physVal=physVal;
	}
	public int getResisVal(){
		return this.resisVal;
	}
	public void setResisVal(int resisVal){
		this.resisVal=resisVal;
	}
	public int getPopual(){
		return this.popual;
	}
	public void setPopual(int popual){
		this.popual=popual;
	}
	public int getExp(){
		return this.exp;
	}
	public void setExp(int exp){
		this.exp=exp;
	}
	public int getSoul(){
		return this.soul;
	}
	public void setSoul(int soul){
		this.soul=soul;
	}
	public int getSocial(){
		return this.social;
	}
	public void setSocial(int social){
		this.social=social;
	}
	public int getZhenQiVal(){
		return this.zhenQiVal;
	}
	public void setZhenQiVal(int zhenQiVal){
		this.zhenQiVal=zhenQiVal;
	}
	public int getHunYuVal(){
		return this.hunYuVal;
	}
	public void setHunYuVal(int hunYuVal){
		this.hunYuVal=hunYuVal;
	}
	public int getAttack(){
		return this.attack;
	}
	public void setAttack(int attack){
		this.attack=attack;
	}
	public List<Integer> getAddTimeAry(){
		return this.addTimeAry;
	}
	public void setAddTimeAry(List<Integer> addTimeAry){
		this.addTimeAry=addTimeAry;
	}
	public List<Integer> getPropLimitAry(){
		return this.propLimitAry;
	}
	public void setPropLimitAry(List<Integer> propLimitAry){
		this.propLimitAry=propLimitAry;
	}
	public List<Integer> getBagLimitAry(){
		return this.bagLimitAry;
	}
	
	public Integer getBagLimit(int type){
		return this.bagLimitAry.get(type-1);
	}
	
	public void setBagLimitAry(List<Integer> bagLimitAry){
		this.bagLimitAry=bagLimitAry;
	}
	public int getCurCollLv(){
		return this.curCollLv;
	}
	public void setCurCollLv(int curCollLv){
		this.curCollLv=curCollLv;
	}

	public Date getDayLoginTime(){
		return this.dayLoginTime;
	}
	public void setDayLoginTime(Date dayLoginTime){
		this.dayLoginTime=dayLoginTime;
	}

	
	public List<Integer> getFmtCardAry(){
		return this.fmtCardAry;
	}
	public void setFmtCardAry(List<Integer> fmtCardAry){
		this.fmtCardAry=fmtCardAry;
	}
	public int getFmtMainCardID(){
		return this.fmtMainCardID;
	}
	public void setFmtMainCardID(int fmtMainCardID){
		this.fmtMainCardID=fmtMainCardID;
	}
	
	public int getHeartLastTime(){
		return this.heartLastTime;
	}
	public void setHeartLastTime(int heartLastTime){
		this.heartLastTime=heartLastTime;
	}
	public Date getSleepLastTime(){
		return this.sleepLastTime=sleepLastTime==null?new Date(0):sleepLastTime;
	}
	public void setSleepLastTime(Date sleepLastTime){
		this.sleepLastTime=sleepLastTime;
	}
	public List<Long> getChatLastViewTime(){
		return this.chatLastViewTime;
	}
	public void setChatLastViewTime(List<Long> chatLastViewTime){
		this.chatLastViewTime=chatLastViewTime;
	}
	public List<Long> getMailLastViewAry(){
		return this.mailLastViewAry;
	}
	public void setMailLastViewAry(List<Long> mailLastViewAry){
		this.mailLastViewAry=mailLastViewAry;
	}
	public Date getBroadcastViewTime(){
		return this.broadcastViewTime;
	}
	public void setBroadcastViewTime(Date broadcastViewTime){
		this.broadcastViewTime=broadcastViewTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getGuideStep() {
		return guideStep;
	}
	public void setGuideStep(int guideStep) {
		this.guideStep = guideStep;
	}
	
	public List<Integer> getHelpStoryStep() {
		return helpStoryStep;
	}
	public void setHelpStoryStep(List<Integer> helpStoryStep) {
		this.helpStoryStep = helpStoryStep;
	}
	public int getDaysContinue() {
		return daysContinue;
	}
	public void setDaysContinue(int daysContinue) {
		this.daysContinue = daysContinue;
	}
	public String getNikeName() {
		return nikeName;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getMaxAttack() {
		return maxAttack;
	}
	public void setMaxAttack(int maxAttack) {
		this.maxAttack = maxAttack;
	}

	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public int getCharm() {
		return charm;
	}

	public void setCharm(int charm) {
		this.charm = charm;
	}

	public int getGetFriendNailiCnt() {
		return getFriendNailiCnt;
	}

	public void setGetFriendNailiCnt(int getFriendNailiCnt) {
		this.getFriendNailiCnt = getFriendNailiCnt;
	}

	public String getLastDeviceType() {
		return lastDeviceType;
	}

	public void setLastDeviceType(String lastDeviceType) {
		this.lastDeviceType = lastDeviceType;
	}

	public String getLastDeviceVersion() {
		return lastDeviceVersion;
	}

	public void setLastDeviceVersion(String lastDeviceVersion) {
		this.lastDeviceVersion = lastDeviceVersion;
	}

	public String getLastDeviceUUID() {
		return lastDeviceUUID;
	}

	public void setLastDeviceUUID(String lastDeviceUUID) {
		this.lastDeviceUUID = lastDeviceUUID;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public int getHonor() {
		return honor;
	}

	public void setHonor(int honor) {
		this.honor = honor;
	}

	public int getLingShi() {
		return lingShi;
	}

	public void setLingShi(int lingShi) {
		this.lingShi = lingShi;
	}

	public Date getLevelUpLastTime() {
		return levelUpLastTime;
	}

	public void setLevelUpLastTime(Date levelUpLastTime) {
		this.levelUpLastTime = levelUpLastTime;
	}
}