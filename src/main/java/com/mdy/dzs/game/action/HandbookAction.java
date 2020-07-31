package com.mdy.dzs.game.action;

import java.util.List;
import java.util.Map;

import com.mdy.sharp.container.biz.BizException;

public interface HandbookAction {
	/*
	 * 获取列表 client->server: Get: /handbook/getAll?acc=* Post: {"m":"handbook",
	 * "a":"getAll", "acc":"*" } acc: 账号
	 * 
	 * client<-server: { err: 错误信息 rtnObj: { equip： [id,id...] card: [id,id...]
	 * gong: [id,id...] } }
	 */
	Map<String, List<Integer>> getAll(String acc) throws BizException;

}
