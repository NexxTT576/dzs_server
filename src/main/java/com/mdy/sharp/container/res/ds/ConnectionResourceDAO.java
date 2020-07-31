package com.mdy.sharp.container.res.ds;

public class ConnectionResourceDAO extends BaseDAO {
    private ConnectionResource connectionResource;

    // public Connection getConnection() {
    // return this.connectionResource.getConnection();
    // }

    public ConnectionResource getConnectionResource() {
        return this.connectionResource;
    }

    public void setConnectionResource(ConnectionResource connectionResource) {
        this.connectionResource = connectionResource;
    }
}
