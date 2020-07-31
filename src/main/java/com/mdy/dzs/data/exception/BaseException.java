package com.mdy.dzs.data.exception;

import java.text.MessageFormat;

import com.mdy.sharp.container.biz.BizException;

/**
 * @author wujia
 * @createTime 2012-9-5 上午10:21:04 </br>
 *             异常基类，异常的错误码分配:</br>
 *             <table>
 *             <tr>
 *             <td>架构</td>
 *             <td>模块</td>
 *             <td>错误码区间</td>
 *             </tr>
 *             <tr>
 *             <td>DataSystem层</td>
 *             <td>xxx模块</td>
 *             <td>110000-119999</td>
 *             </tr>
 *             </table>
 */
public abstract class BaseException {

	private static BizException getException(int code, String msg, Object... args) {
		return new BizException(code, MessageFormat.format(msg, args));
	}

	private static BizException getException(int code, String msg, Throwable e, Object... args) {
		return new BizException(code, MessageFormat.format(msg, args), e);
	}

	public static BizException getException(ExceptionEntry exceptionEntry, Object... args) {
		return getException(exceptionEntry.code, exceptionEntry.msg, args);
	}

	public static BizException getException(ExceptionEntry exceptionEntry, Throwable e, Object... args) {
		return getException(exceptionEntry.code, exceptionEntry.msg, e, args);
	}

	protected static ExceptionEntry createExceptionEntry(int code, String msg) {
		return new ExceptionEntry(code, msg);
	}

	/**
	 * 异常Entry
	 * 
	 * @author : wujia createTime : 2012-9-14 下午06:24:43
	 */
	protected static class ExceptionEntry {
		/**
		 * 异常码
		 */
		private int code;
		/**
		 * 异常消息
		 */
		private String msg;

		private ExceptionEntry(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}
	}
}
