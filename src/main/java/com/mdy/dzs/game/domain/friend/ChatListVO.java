package com.mdy.dzs.game.domain.friend;

import java.io.Serializable;

public class ChatListVO implements Serializable{
	/*chatList:
	{
		{
			account : 2121212 ,	  --账号
			isOnline: 1,          --是否在线
			isChat : 1            --是否对你说话了
		}
		....
	}*/
	
	private static final long serialVersionUID = 1L;
	
	private String account;
	private int isOnline;
	private int isChat;
	
	public ChatListVO(String account, int isOnline, int isChat){
		this.account = account;
		this.isChat  = isChat; 
		this.isOnline= isOnline;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	public int getIsChat() {
		return isChat;
	}
	public void setIsChat(int isChat) {
		this.isChat = isChat;
	}
	
	
}
