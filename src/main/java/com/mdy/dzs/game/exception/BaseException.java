package com.mdy.dzs.game.exception;

import java.text.MessageFormat;

import com.mdy.sharp.container.biz.BizException;

/**
 *  异常基类
 *  <table>
 * 		<tr><td>架构 </td> <td>模块</td><td>错误码区间</td></tr>
 * 		<tr><td>GameSystem层</td><td>任务模块</td><td>220000-229999</td></tr>
 * 	</table>
 */
public abstract class BaseException{

	private static BizException getException(int code, String msg, Object...args) {
		return new BizException(code,MessageFormat.format(msg,args));
	}
	
	private static BizException getException(int code, String msg, Throwable e, Object...args){
		return new BizException(code,MessageFormat.format(msg,args),e);
	}
	
	public static BizException getException(ExceptionEntry exceptionEntry, Object...args) {
		return getException(exceptionEntry.code,exceptionEntry.msg,args);
	}
	
	public static BizException getException(ExceptionEntry exceptionEntry, Throwable e, Object... args){
		return getException(exceptionEntry.code,exceptionEntry.msg,e,args);
	}
	
	protected static ExceptionEntry createExceptionEntry(int code, String msg){
		return new ExceptionEntry(code,msg);
	}
	
	public static BizException getGlobalException(String msg){
		return new BizException(-1,msg);
	}
	
	protected static class ExceptionEntry{
		private int code;
		private String msg;
		private ExceptionEntry(int code, String msg){
			this.code = code;
			this.msg = msg;
		}
	}
}
