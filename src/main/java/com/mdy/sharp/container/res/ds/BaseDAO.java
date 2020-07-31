package com.mdy.sharp.container.res.ds;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BaseDAO {
    protected static final ResultSetRowHandler<Integer> INT_ROW_HANDLER = new ResultSetRowHandler<Integer>() {
        public Integer handleRow(ResultSetRow row) throws Exception {
            return Integer.valueOf(row.getInt(1));
        }
    };

    protected static final ResultSetRowHandler<Double> DOUBLE_ROW_HANDLER = new ResultSetRowHandler<Double>() {
        public Double handleRow(ResultSetRow row) throws Exception {
            return Double.valueOf(row.getDouble(1));
        }
    };

    protected static final ResultSetRowHandler<Float> FLOAT_ROW_HANDLER = new ResultSetRowHandler<Float>() {
        public Float handleRow(ResultSetRow row) throws Exception {
            return Float.valueOf(row.getFloat(1));
        }
    };

    protected static final ResultSetRowHandler<BigDecimal> BIGDECIMAL_ROW_HANDLER = new ResultSetRowHandler<BigDecimal>() {
        public BigDecimal handleRow(ResultSetRow row) throws Exception {
            return row.getBigDecimal(1);
        }
    };

    protected static final ResultSetRowHandler<String> STRING_ROW_HANDLER = new ResultSetRowHandler<String>() {
        public String handleRow(ResultSetRow row) throws Exception {
            return row.getString(1);
        }
    };

    protected static final ResultSetRowHandler<Byte> BYTE_ROW_HANDLER = new ResultSetRowHandler<Byte>() {
        public Byte handleRow(ResultSetRow row) throws Exception {
            return Byte.valueOf(row.getByte(1));
        }
    };

    protected static final ResultSetRowHandler<Short> SHORT_ROW_HANDLER = new ResultSetRowHandler<Short>() {
        public Short handleRow(ResultSetRow row) throws Exception {
            return Short.valueOf(row.getShort(1));
        }
    };

    protected static final ResultSetRowHandler<Boolean> BOOLEAN_ROW_HANDLER = new ResultSetRowHandler<Boolean>() {
        public Boolean handleRow(ResultSetRow row) throws Exception {
            return Boolean.valueOf(row.getBoolean(1));
        }
    };

    protected static final ResultSetRowHandler<Date> DATE_ROW_HANDLER = new ResultSetRowHandler<Date>() {
        public Date handleRow(ResultSetRow row) throws Exception {
            return row.getDate(1);
        }
    };

    public static Connection getConnection() {
        return DB.getConnection();
    }

    @SuppressWarnings("unchecked")
    protected final <T> T queryForObject(String sql, ResultSetRowHandler<T> rowHandler, Object... parameters) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            JDBCUtil.set(conn, ps, parameters);
            rs = ps.executeQuery();
            if (rs.next()) {
                ResultSetRow row = new ResultSetRow(rs);
                Object object1 = rowHandler.handleRow(row);

                return (T) object1;
            }
            Object object = null;
            return (T) object;
        } catch (Exception e) {
            throw new DataStoreException(e);
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(ps);
            JDBCUtil.closeConnection(conn);
        }

    }

    protected final <T> List<T> queryForList(String sql, ResultSetRowHandler<T> rowHandler, Object... parameters) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            JDBCUtil.set(conn, ps, parameters);
            rs = ps.executeQuery();
            List<T> result = new ArrayList<T>();
            while (rs.next()) {
                ResultSetRow row = new ResultSetRow(rs);
                result.add(rowHandler.handleRow(row));
            }
            return result;
        } catch (Exception e) {
            throw new DataStoreException(e);
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(ps);
            JDBCUtil.closeConnection(conn);
        }
    }

    protected final Boolean queryForBoolean(String sql, Object... parameters) {
        return (Boolean) queryForObject(sql, BOOLEAN_ROW_HANDLER, parameters);
    }

    protected final String queryForString(String sql, Object... parameters) {
        return (String) queryForObject(sql, STRING_ROW_HANDLER, parameters);
    }

    protected final Double queryForDouble(String sql, Object... parameters) {
        return (Double) queryForObject(sql, DOUBLE_ROW_HANDLER, parameters);
    }

    protected final Float queryForFloat(String sql, Object... parameters) {
        return (Float) queryForObject(sql, FLOAT_ROW_HANDLER, parameters);
    }

    protected final Integer queryForInteger(String sql, Object... parameters) {
        return (Integer) queryForObject(sql, INT_ROW_HANDLER, parameters);
    }

    protected final Short queryForShort(String sql, Object... parameters) {
        return (Short) queryForObject(sql, SHORT_ROW_HANDLER, parameters);
    }

    protected final BigDecimal queryForBigDecimal(String sql, Object... parameters) {
        return (BigDecimal) queryForObject(sql, BIGDECIMAL_ROW_HANDLER, parameters);
    }

    protected final Byte queryForByte(String sql, Object... parameters) {
        return (Byte) queryForObject(sql, BYTE_ROW_HANDLER, parameters);
    }

    protected final Date queryForDate(String sql, Object... parameters) {
        return (Date) queryForObject(sql, DATE_ROW_HANDLER, parameters);
    }

    protected final boolean execute(String sql, Object... parameters) {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            JDBCUtil.set(conn, ps, parameters);
            return ps.execute();
        } catch (Exception e) {
            throw new DataStoreException(e);
        } finally {
            JDBCUtil.closeStatement(ps);
            JDBCUtil.closeConnection(conn);
        }
    }

    protected final int executeWithGenKey(String sql, Object... parameters) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql, 1);
            JDBCUtil.set(conn, ps, parameters);
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new DataStoreException("can not got generatedKeys");
        } catch (Exception e) {
            throw new DataStoreException(e);
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(ps);
            JDBCUtil.closeConnection(conn);
        }
    }

    protected final int executeUpdate(String sql, Object... parameters) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            JDBCUtil.set(conn, ps, parameters);
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new DataStoreException(e);
        } finally {
            JDBCUtil.closeStatement(ps);
            JDBCUtil.closeConnection(conn);
        }
    }

    protected final int[] executeBatch(String sql, List<Object[]> parameterList) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (Object[] parameters : parameterList) {
                JDBCUtil.set(conn, ps, parameters);
                ps.addBatch();
            }
            return ps.executeBatch();
        } catch (Exception e) {
            throw new DataStoreException(e);
        } finally {
            JDBCUtil.closeStatement(ps);
            JDBCUtil.closeConnection(conn);
        }
    }
}