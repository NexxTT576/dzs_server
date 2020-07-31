package com.mdy.dzs.game.dao.filter;

import java.io.Serializable;

/**
 */
public class Filter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 页码(从0开始)
	 */
	protected int pageIndex=0;
	/**
	 * 页大小(默认10)
	 */
	protected int pageSize=10;
	/**
	 * 排序方式
	 */
	protected int orderType=0;
	
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * 得到数据库开始的记录
	 * @return
	 */
	public int getStartOfPage() {
		 int pageStart = (pageIndex - 1) * pageSize;
		 return pageStart >= 0 ? pageStart : 0 ;
	}
	
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	
	public int getTotalPage(int totalCount){
		if(totalCount<=0){
			return 1;
		}
		if(pageSize<=0){
			pageSize=1;
		}
		if(totalCount%pageSize==0){
			return totalCount/pageSize;
		}
		else{
			return totalCount/pageSize+1;
		}
	}
	
	
}
