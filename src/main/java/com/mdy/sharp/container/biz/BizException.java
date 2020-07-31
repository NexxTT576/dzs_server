package com.mdy.sharp.container.biz;

public class BizException extends Exception {
    private static final long serialVersionUID = 229084464194752283L;
    protected int code;

    public BizException(int code) {
        this.code = code;
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BizException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    public BizException(int code, Throwable e) {
        super(e);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return "[" + this.code + "] " + super.getMessage();
    }
}