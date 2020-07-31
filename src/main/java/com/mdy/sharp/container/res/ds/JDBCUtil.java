package com.mdy.sharp.container.res.ds;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;

import com.mdy.sharp.container.log.LoggerFactory;

import org.apache.logging.log4j.Logger;

public class JDBCUtil {
    private static Logger logger = LoggerFactory.get(JDBCUtil.class);

    protected static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                logger.error("Could not close JDBC Connection", ex);
            } catch (Throwable ex) {

                logger.error("Unexpected exception on closing JDBC Connection", ex);
            }
        }
    }

    protected static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                logger.error("Could not close JDBC Statement", ex);
            } catch (Throwable ex) {

                logger.error("Unexpected exception on closing JDBC Statement", ex);
            }
        }
    }

    protected static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                logger.error("Could not close JDBC ResultSet", ex);
            } catch (Throwable ex) {

                logger.error("Unexpected exception on closing JDBC ResultSet", ex);
            }
        }
    }

    public static void set(Connection conn, PreparedStatement ps, Object... objs) throws SQLException {
        if (objs == null || objs.length == 0) {
            return;
        }
        int i = 1;

        for (Object o : objs) {
            if (o == null) {
                ps.setString(i++, null);

            } else if (o instanceof String) {
                ps.setString(i++, (String) o);

            } else if (o instanceof Date) {
                Date date = (Date) o;
                ps.setTimestamp(i++, new Timestamp(date.getTime()));

            } else if (o instanceof Integer) {
                ps.setInt(i++, ((Integer) o).intValue());

            } else if (o instanceof Double) {
                ps.setDouble(i++, ((Double) o).doubleValue());

            } else if (o instanceof Float) {
                ps.setFloat(i++, ((Float) o).floatValue());

            } else if (o instanceof BigDecimal) {
                ps.setBigDecimal(i++, (BigDecimal) o);

            } else if (o instanceof Long) {
                ps.setLong(i++, ((Long) o).longValue());

            } else if (o instanceof Byte) {
                ps.setByte(i++, ((Byte) o).byteValue());

            } else if (o instanceof byte[]) {
                ps.setBytes(i++, (byte[]) o);

            } else if (o instanceof Boolean) {
                ps.setBoolean(i++, ((Boolean) o).booleanValue());
            } else {

                throw new IllegalArgumentException("unsupport type:" + o.getClass());
            }
        }
    }
}