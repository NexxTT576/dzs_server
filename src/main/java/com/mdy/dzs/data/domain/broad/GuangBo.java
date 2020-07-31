package com.mdy.dzs.data.domain.broad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * 广播模型
 * @author 房曈
 *
 */
public class GuangBo implements Serializable{


	/**序列化id*/
	private static final long serialVersionUID = 1L;


	/**id*/
	private int id;
	/**类型：1.系统定时播放;2.玩家触发;3.系统随机播放*/
	private int type;
	/**播放时间（1：特定时间类型;2：每周固定时间;3：所有时间段）*/
	private int ttype;
	/**播放时间*/
	private String arrTime;
	/**详细时间*/
	private String arrStime;
	/**播放次数*/
	private int num;
	/**显示内容*/
	@JsonProperty(value = "Content") 
	public String content;
	/**文字颜色*/
	private List<Integer> color;
	/**数字颜色参数*/
	 @JsonProperty(value = "arr_color") 
	 public List<Integer> arrColor;

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getTtype(){
		return this.ttype;
	}
	public void setTtype(int ttype){
		this.ttype=ttype;
	}
	public String getArrTime(){
		return this.arrTime;
	}
	public void setArrTime(String arrTime){
		this.arrTime=arrTime;
	}
	public String getArrStime(){
		return this.arrStime;
	}
	public void setArrStime(String arrStime){
		this.arrStime=arrStime;
	}
	public int getNum(){
		return this.num;
	}
	public void setNum(int num){
		this.num=num;
	}
	@JsonIgnore
	public String getContent(){
		return this.content;
	}
	@JsonIgnore
	public void setContent(String Content){
		this.content=Content;
	}
	public List<Integer> getColor(){
		return this.color;
	}
	public void setColor(List<Integer> color){
		this.color=color;
	}
	@JsonIgnore
	public List<Integer> getArrColor(){
		return this.arrColor;
	}
	@JsonIgnore
	public void setArrColor(List<Integer> arrColor){
		this.arrColor=arrColor==null?new ArrayList<Integer>():arrColor;
	}
}