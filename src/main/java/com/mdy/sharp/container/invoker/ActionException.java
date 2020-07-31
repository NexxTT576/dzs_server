package com.mdy.sharp.container.invoker;

public class ActionException extends RuntimeException {
    private static final long serialVersionUID = 229084464194752283L;
    public static final int CODE_SESSION_EXPIRED = 1;
    public static final int CODE_APP_EXCEPTION = 2;
    public static final int CODE_ACTION_INSTANCE_NOT_FOUND = 3;
    public static final int CODE_CAN_NOT_FOUND_METHOD = 5;
    public static final int CODE_ONLY_VOID_METHOD_INVOKE_ASYNC = 6;
    public static final int CODE_ASYNC_INVOKE_TIMEOUT = 7;
    protected int code;

    public ActionException(int code) {
        this.code = code;
    }

    public ActionException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public ActionException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    public ActionException(int code, Throwable e) {
        super(e);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}