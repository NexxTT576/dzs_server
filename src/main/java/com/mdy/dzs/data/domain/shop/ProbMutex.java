/**
 * 
 */
package com.mdy.dzs.data.domain.shop;

import java.io.Serializable;

/**
 * @author fangtong  E-mail: 10786912@qq.com
 * @version 创建时间：2015年2月1日  下午2:17:12
 */
public class ProbMutex implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int type;
	private int min;
	private int max;
	private int num;
	private int sum;
	
	/**
	 * 
	 */
	public ProbMutex(String str) {
		String[] onereg = str.split(";");
		type = Integer.valueOf(onereg[0]);
		min = Integer.valueOf(onereg[1]);
		max = Integer.valueOf(onereg[2]);
		num = Integer.valueOf(onereg[3]);
		for(int a = min;a <= max;a++)
		{
			sum+=1;
		}
	}
	/**
	 * 
	 */
	public ProbMutex() {
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	
}
