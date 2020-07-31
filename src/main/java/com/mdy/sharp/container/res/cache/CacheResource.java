package com.mdy.sharp.container.res.cache;

public interface CacheResource {
    boolean add(String paramString, int paramInt, Object paramObject) throws CacheException;

    boolean set(String paramString, int paramInt, Object paramObject) throws CacheException;

    boolean delete(String paramString) throws CacheException;

    Object get(String paramString) throws CacheException;
}
