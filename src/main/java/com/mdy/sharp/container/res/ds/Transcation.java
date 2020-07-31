package com.mdy.sharp.container.res.ds;

public class Transcation {
    private boolean needTranscation = false;
    private ConnectionHolder connection;

    public boolean isNeedTranscation() {
        return this.needTranscation;
    }

    public void setNeedTranscation(boolean needTranscation) {
        this.needTranscation = needTranscation;
    }

    public ConnectionHolder getConnection() {
        return this.connection;
    }

    public void setConnection(ConnectionHolder connection) {
        this.connection = connection;
    }
}
