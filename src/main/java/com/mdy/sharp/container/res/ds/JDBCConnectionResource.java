package com.mdy.sharp.container.res.ds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mdy.sharp.container.log.LoggerFactory;

import org.apache.logging.log4j.Logger;

public class JDBCConnectionResource extends ConnectionResource {
    private static Logger logger = LoggerFactory.get(JDBCConnectionResource.class);

    private String user;

    private String password;

    private String url;

    private String driver;

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDriver() {
        return this.driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void init() {
        super.init();
        if (this.driver == null) {
            logger.warn("can not find driver:" + this.driver);
            return;
        }
        try {
            logger.info("using driver:" + this.driver);
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            logger.error("can not register driver.", e);
        }
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Connection getRealConnection(boolean needTranscation) {
        try {
            return getConnection(this.user, this.password);
        } catch (SQLException e) {
            throw new DataStoreException(e);
        }
    }

    private Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(this.url, username, password);
    }

    public String toString() {
        return "JDBCConnectionResource [user=" + this.user + ", password=" + this.password + ", url=" + this.url
                + ", driver=" + this.driver + "]";
    }
}