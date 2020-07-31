package com.mdy.sharp.container.res.ds;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.mdy.sharp.container.log.LoggerFactory;

import org.apache.logging.log4j.Logger;

public class PreparedStatementHolder implements PreparedStatement {
    private StringBuilder sql;
    private PreparedStatement realStatement;
    private static Logger logger = LoggerFactory.get(PreparedStatementHolder.class);

    private List<Object[]> parameters;

    public PreparedStatementHolder(PreparedStatement ps, String sqlStr) {
        this.paramSize = 0;

        this.realStatement = ps;
        this.sql = new StringBuilder(sqlStr);

        this.parameters = new ArrayList<Object[]>();
        this.parameter = new Object[128];
        this.parameters.add(this.parameter);
    }

    private Object[] parameter;

    private String dumpParameter(Object[] parameter) {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------------------------------\n");
        if (this.paramSize != 0) {
            for (int i = 0; i < this.paramSize; i++) {
                sb.append("[" + i + "]\t:");
                sb.append(parameter[i] + "\n");
            }
        }
        sb.append("-------------------------------------------------------\n");
        return sb.toString();
    }

    private int paramSize;
    private static final int SLOW_SQL_TIME = 1000;

    private void traceSql(String ret, long startTime) throws SQLException {
        boolean autoCommit = this.realStatement.getConnection().getAutoCommit();
        long s2 = System.currentTimeMillis();
        long time = s2 - startTime;
        if (logger.isDebugEnabled()) {
            logger.debug("RunSQL:" + dumpSql(autoCommit, time, ret));
        }
        if (time > 1000L) {
            logger.warn("SlowSQL:" + dumpSql(autoCommit, time, ret));
        }
    }

    private void processError(String ret, long startTime) throws SQLException {
        boolean autoCommit = this.realStatement.getConnection().getAutoCommit();
        long s2 = System.currentTimeMillis();
        logger.error("execute:" + dumpSql(autoCommit, s2 - startTime, ret));
    }

    private String dumpStatement(long time) {
        return "SQL:" + this.sql + " [Time]" + time + "\n";
    }

    private String dumpBatchSql(long time, int[] ret) {
        StringBuilder sb = new StringBuilder();
        sb.append("batch:size=" + (this.parameters.size() - 1) + "\n");
        sb.append(dumpStatement(time));
        for (int i = 0; i < this.parameters.size() - 1; i++) {
            sb.append("batch[" + i + "]");
            sb.append(dumpParameter((Object[]) this.parameters.get(i)));
        }
        sb.append("\nRet:");
        if (ret == null) {
            sb.append("null");
        } else {
            sb.append(Arrays.toString(ret));
        }
        return sb.toString();
    }

    private String dumpSql(boolean autoCommit, long time, Object ret) {
        return "AutoCommit:" + autoCommit + "." + dumpStatement(time) + dumpParameter(this.parameter) + "Ret:" + ret;
    }

    private void setParameter(int i, Object o) throws SQLException {
        if (logger.isDebugEnabled()) {
            if (i <= 0) {
                throw new SQLException("parameter index start from 1");
            }
            if (i > this.paramSize) {
                this.paramSize = i;
            }
            this.parameter[i - 1] = o;
        }
    }

    public void addBatch() throws SQLException {
        this.parameter = new Object[128];
        this.parameters.add(this.parameter);
        this.realStatement.addBatch();
    }

    public void addBatch(String sql) throws SQLException {
        this.realStatement.addBatch(sql);
    }

    public void cancel() throws SQLException {
        this.realStatement.cancel();
    }

    public void clearBatch() throws SQLException {
        this.parameter = new Object[128];
        this.parameters.clear();
        this.realStatement.clearBatch();
    }

    public void clearParameters() throws SQLException {
        this.realStatement.clearParameters();
    }

    public void clearWarnings() throws SQLException {
        this.realStatement.clearWarnings();
    }

    public void close() throws SQLException {
        this.realStatement.close();
    }

    public boolean execute() throws SQLException {
        long s = System.currentTimeMillis();
        boolean ret = false;
        try {
            ret = this.realStatement.execute();
            return ret;
        } catch (SQLException e) {
            processError(ret + "", s);
            throw e;
        } finally {
            traceSql(ret + "", s);
        }
    }

    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        long s = System.currentTimeMillis();
        boolean ret = false;
        try {
            ret = this.realStatement.execute(sql, autoGeneratedKeys);
            return ret;
        } catch (SQLException e) {
            processError(ret + "", s);
            throw e;
        } finally {
            traceSql(ret + "", s);
        }
    }

    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        throw new IllegalStateException("Not supported!");
    }

    public boolean execute(String sql, String[] columnNames) throws SQLException {
        throw new IllegalStateException("Not supported!");
    }

    public boolean execute(String sql) throws SQLException {
        this.sql.append(sql);
        long s = System.currentTimeMillis();
        boolean ret = false;
        try {
            ret = this.realStatement.execute(sql);
            return ret;
        } catch (SQLException e) {
            processError(ret + "", s);
            throw e;
        } finally {
            traceSql(ret + "", s);
        }
    }

    public int[] executeBatch() throws SQLException {
        long s = System.currentTimeMillis();
        int[] ret = null;
        try {
            ret = this.realStatement.executeBatch();
            return ret;
        } catch (SQLException e) {
            processError(null, s);
            throw e;
        } finally {
            long s2 = System.currentTimeMillis();
            if (logger.isDebugEnabled()) {
                logger.debug("executeBatch:" + dumpBatchSql(s2 - s, ret));
            }
        }
    }

    public ResultSet executeQuery() throws SQLException {
        long s = System.currentTimeMillis();
        ResultSet rs = null;
        try {
            rs = this.realStatement.executeQuery();
            return rs;
        } catch (SQLException e) {
            processError("", s);
            throw e;
        } finally {
            traceSql("", s);
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        this.sql.append(sql);
        long s = System.currentTimeMillis();
        ResultSet rs = null;
        try {
            rs = this.realStatement.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            processError("", s);
            throw e;
        } finally {
            traceSql("", s);
        }
    }

    public int executeUpdate() throws SQLException {
        long s = System.currentTimeMillis();
        int ret = 0;
        try {
            ret = this.realStatement.executeUpdate();
            return ret;
        } catch (SQLException e) {
            processError(ret + "", s);
            throw e;
        } finally {
            traceSql(ret + "", s);
        }
    }

    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new IllegalStateException("Not supported!");
    }

    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new IllegalStateException("Not supported!");
    }

    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new IllegalStateException("Not supported!");
    }

    public int executeUpdate(String sql) throws SQLException {
        this.sql.append(sql);
        long s = System.currentTimeMillis();
        int ret = 0;
        try {
            ret = this.realStatement.executeUpdate(sql);
            return ret;
        } catch (SQLException e) {
            processError(ret + "", s);
            throw e;
        } finally {
            traceSql(ret + "", s);
        }
    }

    public Connection getConnection() throws SQLException {
        return this.realStatement.getConnection();
    }

    public int getFetchDirection() throws SQLException {
        return this.realStatement.getFetchDirection();
    }

    public int getFetchSize() throws SQLException {
        return this.realStatement.getFetchSize();
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        return this.realStatement.getGeneratedKeys();
    }

    public int getMaxFieldSize() throws SQLException {
        return this.realStatement.getMaxFieldSize();
    }

    public int getMaxRows() throws SQLException {
        return this.realStatement.getMaxRows();
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return this.realStatement.getMetaData();
    }

    public boolean getMoreResults() throws SQLException {
        return this.realStatement.getMoreResults();
    }

    public boolean getMoreResults(int current) throws SQLException {
        return this.realStatement.getMoreResults(current);
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        return this.realStatement.getParameterMetaData();
    }

    public int getQueryTimeout() throws SQLException {
        return this.realStatement.getQueryTimeout();
    }

    public ResultSet getResultSet() throws SQLException {
        return this.realStatement.getResultSet();
    }

    public int getResultSetConcurrency() throws SQLException {
        return this.realStatement.getResultSetConcurrency();
    }

    public int getResultSetHoldability() throws SQLException {
        return this.realStatement.getResultSetHoldability();
    }

    public int getResultSetType() throws SQLException {
        return this.realStatement.getResultSetType();
    }

    public int getUpdateCount() throws SQLException {
        return this.realStatement.getUpdateCount();
    }

    public SQLWarning getWarnings() throws SQLException {
        return this.realStatement.getWarnings();
    }

    public boolean isClosed() throws SQLException {
        return this.realStatement.isClosed();
    }

    public boolean isPoolable() throws SQLException {
        return this.realStatement.isPoolable();
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return this.realStatement.isWrapperFor(iface);
    }

    public void setArray(int parameterIndex, Array x) throws SQLException {
        this.realStatement.setArray(parameterIndex, x);
    }

    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        this.realStatement.setAsciiStream(parameterIndex, x, length);
    }

    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        this.realStatement.setAsciiStream(parameterIndex, x, length);
    }

    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        this.realStatement.setAsciiStream(parameterIndex, x);
    }

    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setBigDecimal(parameterIndex, x);
    }

    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        this.realStatement.setBinaryStream(parameterIndex, x, length);
    }

    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        this.realStatement.setBinaryStream(parameterIndex, x, length);
    }

    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        this.realStatement.setBinaryStream(parameterIndex, x);
    }

    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        this.realStatement.setBlob(parameterIndex, x);
    }

    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        this.realStatement.setBlob(parameterIndex, inputStream, length);
    }

    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        this.realStatement.setBlob(parameterIndex, inputStream);
    }

    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, Boolean.valueOf(x));
        }
        this.realStatement.setBoolean(parameterIndex, x);
    }

    public void setByte(int parameterIndex, byte x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, Byte.valueOf(x));
        }
        this.realStatement.setByte(parameterIndex, x);
    }

    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        this.realStatement.setBytes(parameterIndex, x);
    }

    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        this.realStatement.setCharacterStream(parameterIndex, reader, length);
    }

    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        this.realStatement.setCharacterStream(parameterIndex, reader, length);
    }

    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        this.realStatement.setCharacterStream(parameterIndex, reader);
    }

    public void setClob(int parameterIndex, Clob x) throws SQLException {
        this.realStatement.setClob(parameterIndex, x);
    }

    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        this.realStatement.setClob(parameterIndex, reader, length);
    }

    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        this.realStatement.setClob(parameterIndex, reader);
    }

    public void setCursorName(String name) throws SQLException {
        this.realStatement.setCursorName(name);
    }

    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setDate(parameterIndex, x, cal);
    }

    public void setDate(int parameterIndex, Date x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setDate(parameterIndex, x);
    }

    public void setDouble(int parameterIndex, double x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, Double.valueOf(x));
        }
        this.realStatement.setDouble(parameterIndex, x);
    }

    public void setEscapeProcessing(boolean enable) throws SQLException {
        this.realStatement.setEscapeProcessing(enable);
    }

    public void setFetchDirection(int direction) throws SQLException {
        this.realStatement.setFetchDirection(direction);
    }

    public void setFetchSize(int rows) throws SQLException {
        this.realStatement.setFetchSize(rows);
    }

    public void setFloat(int parameterIndex, float x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, Float.valueOf(x));
        }
        this.realStatement.setFloat(parameterIndex, x);
    }

    public void setInt(int parameterIndex, int x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, Integer.valueOf(x));
        }
        this.realStatement.setInt(parameterIndex, x);
    }

    public void setLong(int parameterIndex, long x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, Long.valueOf(x));
        }
        this.realStatement.setLong(parameterIndex, x);
    }

    public void setMaxFieldSize(int max) throws SQLException {
        this.realStatement.setMaxFieldSize(max);
    }

    public void setMaxRows(int max) throws SQLException {
        this.realStatement.setMaxRows(max);
    }

    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        this.realStatement.setNCharacterStream(parameterIndex, value, length);
    }

    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        this.realStatement.setNCharacterStream(parameterIndex, value);
    }

    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        this.realStatement.setNClob(parameterIndex, value);
    }

    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        this.realStatement.setNClob(parameterIndex, reader, length);
    }

    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        this.realStatement.setNClob(parameterIndex, reader);
    }

    public void setNString(int parameterIndex, String value) throws SQLException {
        this.realStatement.setNString(parameterIndex, value);
    }

    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        this.realStatement.setNull(parameterIndex, sqlType, typeName);
    }

    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, null);
        }
        this.realStatement.setNull(parameterIndex, sqlType);
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setObject(parameterIndex, x, targetSqlType);
    }

    public void setObject(int parameterIndex, Object x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setObject(parameterIndex, x);
    }

    public void setPoolable(boolean poolable) throws SQLException {
        this.realStatement.setPoolable(poolable);
    }

    public void setQueryTimeout(int seconds) throws SQLException {
        this.realStatement.setQueryTimeout(seconds);
    }

    public void setRef(int parameterIndex, Ref x) throws SQLException {
        this.realStatement.setRef(parameterIndex, x);
    }

    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        this.realStatement.setRowId(parameterIndex, x);
    }

    public void setShort(int parameterIndex, short x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, Short.valueOf(x));
        }
        this.realStatement.setShort(parameterIndex, x);
    }

    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, xmlObject);
        }
        this.realStatement.setSQLXML(parameterIndex, xmlObject);
    }

    public void setString(int parameterIndex, String x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setString(parameterIndex, x);
    }

    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setTime(parameterIndex, x, cal);
    }

    public void setTime(int parameterIndex, Time x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setTime(parameterIndex, x);
    }

    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setTimestamp(parameterIndex, x, cal);
    }

    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setTimestamp(parameterIndex, x);
    }

    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        // this.realStatement.setUnicodeStream(parameterIndex, x, length);
    }

    public void setURL(int parameterIndex, URL x) throws SQLException {
        if (logger.isDebugEnabled()) {
            setParameter(parameterIndex, x);
        }
        this.realStatement.setURL(parameterIndex, x);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return (T) this.realStatement.unwrap(iface);
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        this.realStatement.closeOnCompletion();

    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return this.realStatement.isCloseOnCompletion();
    }
}
