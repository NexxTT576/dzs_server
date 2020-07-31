package com.mdy.sharp.container.res.ds;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class ResultSetRow {
    private ResultSet resultSet;

    protected ResultSetRow(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Array getArray(int columnIndex) throws SQLException {
        return this.resultSet.getArray(columnIndex);
    }

    public Array getArray(String columnLabel) throws SQLException {
        return this.resultSet.getArray(columnLabel);
    }

    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        return this.resultSet.getAsciiStream(columnIndex);
    }

    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        return this.resultSet.getAsciiStream(columnLabel);
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        return this.resultSet.getBigDecimal(columnIndex);
    }

    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        return this.resultSet.getBigDecimal(columnLabel);
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        return this.resultSet.getBinaryStream(columnIndex);
    }

    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        return this.resultSet.getBinaryStream(columnLabel);
    }

    public Blob getBlob(int columnIndex) throws SQLException {
        return this.resultSet.getBlob(columnIndex);
    }

    public Blob getBlob(String columnLabel) throws SQLException {
        return this.resultSet.getBlob(columnLabel);
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        return this.resultSet.getBoolean(columnIndex);
    }

    public boolean getBoolean(String columnLabel) throws SQLException {
        return this.resultSet.getBoolean(columnLabel);
    }

    public byte getByte(int columnIndex) throws SQLException {
        return this.resultSet.getByte(columnIndex);
    }

    public byte getByte(String columnLabel) throws SQLException {
        return this.resultSet.getByte(columnLabel);
    }

    public byte[] getBytes(int columnIndex) throws SQLException {
        return this.resultSet.getBytes(columnIndex);
    }

    public byte[] getBytes(String columnLabel) throws SQLException {
        return this.resultSet.getBytes(columnLabel);
    }

    public Reader getCharacterStream(int columnIndex) throws SQLException {
        return this.resultSet.getCharacterStream(columnIndex);
    }

    public Reader getCharacterStream(String columnLabel) throws SQLException {
        return this.resultSet.getCharacterStream(columnLabel);
    }

    public Clob getClob(int columnIndex) throws SQLException {
        return this.resultSet.getClob(columnIndex);
    }

    public Clob getClob(String columnLabel) throws SQLException {
        return this.resultSet.getClob(columnLabel);
    }

    public int getConcurrency() throws SQLException {
        return this.resultSet.getConcurrency();
    }

    public String getCursorName() throws SQLException {
        return this.resultSet.getCursorName();
    }

    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        return this.resultSet.getDate(columnIndex, cal);
    }

    public Date getDate(int columnIndex) throws SQLException {
        return this.resultSet.getDate(columnIndex);
    }

    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        return this.resultSet.getDate(columnLabel, cal);
    }

    public Date getDate(String columnLabel) throws SQLException {
        return this.resultSet.getDate(columnLabel);
    }

    public double getDouble(int columnIndex) throws SQLException {
        return this.resultSet.getDouble(columnIndex);
    }

    public double getDouble(String columnLabel) throws SQLException {
        return this.resultSet.getDouble(columnLabel);
    }

    public int getFetchDirection() throws SQLException {
        return this.resultSet.getFetchDirection();
    }

    public int getFetchSize() throws SQLException {
        return this.resultSet.getFetchSize();
    }

    public float getFloat(int columnIndex) throws SQLException {
        return this.resultSet.getFloat(columnIndex);
    }

    public float getFloat(String columnLabel) throws SQLException {
        return this.resultSet.getFloat(columnLabel);
    }

    public int getHoldability() throws SQLException {
        return this.resultSet.getHoldability();
    }

    public int getInt(int columnIndex) throws SQLException {
        return this.resultSet.getInt(columnIndex);
    }

    public int getInt(String columnLabel) throws SQLException {
        return this.resultSet.getInt(columnLabel);
    }

    public long getLong(int columnIndex) throws SQLException {
        return this.resultSet.getLong(columnIndex);
    }

    public long getLong(String columnLabel) throws SQLException {
        return this.resultSet.getLong(columnLabel);
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return this.resultSet.getMetaData();
    }

    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        return this.resultSet.getNCharacterStream(columnIndex);
    }

    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        return this.resultSet.getNCharacterStream(columnLabel);
    }

    public NClob getNClob(int columnIndex) throws SQLException {
        return this.resultSet.getNClob(columnIndex);
    }

    public NClob getNClob(String columnLabel) throws SQLException {
        return this.resultSet.getNClob(columnLabel);
    }

    public String getNString(int columnIndex) throws SQLException {
        return this.resultSet.getNString(columnIndex);
    }

    public String getNString(String columnLabel) throws SQLException {
        return this.resultSet.getNString(columnLabel);
    }

    public Object getObject(int columnIndex) throws SQLException {
        return this.resultSet.getObject(columnIndex);
    }

    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        return this.resultSet.getObject(columnLabel, map);
    }

    public Object getObject(String columnLabel) throws SQLException {
        return this.resultSet.getObject(columnLabel);
    }

    public Ref getRef(int columnIndex) throws SQLException {
        return this.resultSet.getRef(columnIndex);
    }

    public Ref getRef(String columnLabel) throws SQLException {
        return this.resultSet.getRef(columnLabel);
    }

    public int getRow() throws SQLException {
        return this.resultSet.getRow();
    }

    public RowId getRowId(int columnIndex) throws SQLException {
        return this.resultSet.getRowId(columnIndex);
    }

    public RowId getRowId(String columnLabel) throws SQLException {
        return this.resultSet.getRowId(columnLabel);
    }

    public short getShort(int columnIndex) throws SQLException {
        return this.resultSet.getShort(columnIndex);
    }

    public short getShort(String columnLabel) throws SQLException {
        return this.resultSet.getShort(columnLabel);
    }

    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        return this.resultSet.getSQLXML(columnIndex);
    }

    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        return this.resultSet.getSQLXML(columnLabel);
    }

    public Statement getStatement() throws SQLException {
        return this.resultSet.getStatement();
    }

    public String getString(int columnIndex) throws SQLException {
        return this.resultSet.getString(columnIndex);
    }

    public String getString(String columnLabel) throws SQLException {
        return this.resultSet.getString(columnLabel);
    }

    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        return this.resultSet.getTime(columnIndex, cal);
    }

    public Time getTime(int columnIndex) throws SQLException {
        return this.resultSet.getTime(columnIndex);
    }

    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        return this.resultSet.getTime(columnLabel, cal);
    }

    public Time getTime(String columnLabel) throws SQLException {
        return this.resultSet.getTime(columnLabel);
    }

    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        return this.resultSet.getTimestamp(columnIndex, cal);
    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        return this.resultSet.getTimestamp(columnIndex);
    }

    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        return this.resultSet.getTimestamp(columnLabel, cal);
    }

    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        return this.resultSet.getTimestamp(columnLabel);
    }

    public URL getURL(int columnIndex) throws SQLException {
        return this.resultSet.getURL(columnIndex);
    }

    public URL getURL(String columnLabel) throws SQLException {
        return this.resultSet.getURL(columnLabel);
    }
}