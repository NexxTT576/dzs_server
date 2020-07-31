package com.mdy.sharp.container.res.ds;

import java.sql.Connection;
import java.sql.SQLException;

import com.mdy.sharp.container.log.LoggerFactory;
import com.mdy.sharp.container.res.Resource;

import org.apache.logging.log4j.Logger;

public abstract class ConnectionResource implements Resource {
    private static Logger logger = LoggerFactory.get(ConnectionResource.class);
    private static ThreadLocal<Transcation> transcationHolder = new ThreadLocal<Transcation>();

    public void destroy() {
        logger.info("ConnectionResource destroy.");
    }

    public void start() {
    }

    public void init() {
    }

    protected abstract Connection getRealConnection(boolean paramBoolean);

    public Connection getConnection() {
        Transcation t = (Transcation) transcationHolder.get();
        if (t == null) {
            throw new DataStoreException("can not find transcation.");
        }
        ConnectionHolder holder = t.getConnection();
        if (holder == null) {

            Connection conn = getRealConnection(t.isNeedTranscation());
            try {
                conn.setAutoCommit(!t.isNeedTranscation());
            } catch (SQLException e) {
                throw new DataStoreException(e);
            }
            ConnectionHolder ch = new ConnectionHolder(conn);
            t.setConnection(ch);
            return ch;
        }
        return holder;
    }

    public static void commit() {
        Transcation t = (Transcation) transcationHolder.get();
        if (t == null) {
            throw new DataStoreException("can not find transcation.");
        }
        try {
            if (t.getConnection() != null) {
                t.getConnection().getRealConnection().commit();
            }
        } catch (SQLException e) {
            throw new DataStoreException(e);
        }
    }

    public static void endTransaction() {
        Transcation t = (Transcation) transcationHolder.get();
        transcationHolder.remove();
        if (t == null) {
            throw new DataStoreException("can not find transcation.");
        }
        try {
            if (t.getConnection() != null) {
                t.getConnection().getRealConnection().setAutoCommit(true);
                t.getConnection().getRealConnection().close();
            }
        } catch (SQLException e) {
            throw new DataStoreException(e);
        }
    }

    public static void rollback() {
        Transcation t = (Transcation) transcationHolder.get();
        if (t == null) {
            throw new DataStoreException("can not find transcation.");
        }
        try {
            if (t.getConnection() != null) {
                t.getConnection().getRealConnection().rollback();
            }
        } catch (SQLException e) {
            throw new DataStoreException(e);
        }
    }

    public static void startTransaction(boolean needTranscation) {
        Transcation t = new Transcation();
        t.setNeedTranscation(needTranscation);
        transcationHolder.set(t);
    }
}
