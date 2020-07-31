package com.mdy.sharp.container.res.cache;

public class CacheException extends Exception {
    private static final long serialVersionUID = 229084464194752283L;

    public CacheException() {
    }

    public CacheException(String msg) {
        super(msg);
    }

    public CacheException(String msg, Throwable e) {
        super(msg, e);
    }

    public CacheException(Throwable e) {
        super(e);
    }
}
