package com.mdy.sharp.container.res.ds;

public interface ResultSetRowHandler<T> {
    T handleRow(ResultSetRow paramResultSetRow) throws Exception;
}