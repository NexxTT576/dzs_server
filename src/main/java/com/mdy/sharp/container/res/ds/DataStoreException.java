package com.mdy.sharp.container.res.ds;

public class DataStoreException extends RuntimeException {
    private static final long serialVersionUID = 8459701673673361296L;

    public DataStoreException(String msg) {
        super(msg);
    }

    public DataStoreException(Throwable e) {
        super(e);
    }

    public DataStoreException(String msg, Throwable e) {
        super(msg, e);
    }
}