package com.mdy.sharp.container.res.ds;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DB {
    private static Logger logger = LogManager.getLogger(DB.class);
    private static Properties properties = new Properties();
    /** 数据源，static */
    private static DataSource dataSource;

    static {
        logger.info("init properties!");
        try {
            // String fn =
            // DB.class.getClassLoader().getResource("dbcp.properties").getPath();
            // logger.info(fn);
            // File file = new File(fn);
            // if (!file.exists()) {
            // fn = "src/main/resources/" + fn;
            // }
            // // 注意这里需要使用绝对路径
            // FileInputStream is = new FileInputStream(fn);
            properties.load(DB.class.getResourceAsStream("/dbcp.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取数据源对象
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("数据库初始化成功");
    }

    // 从连接池中获取一个连接
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e.toString());
        }

        return connection;
    }

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
}