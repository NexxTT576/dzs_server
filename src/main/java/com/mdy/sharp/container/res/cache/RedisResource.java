package com.mdy.sharp.container.res.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.mdy.sharp.container.log.LoggerFactory;

import org.apache.logging.log4j.Logger;

import redis.clients.jedis.Jedis;

public class RedisResource implements CacheResource {
    private static Logger logger = LoggerFactory.get(RedisResource.class);

    private static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);
            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            logger.warn("translation " + e.getMessage());
        }
        return obj;
    }

    private static byte[] ObjectToByte(java.lang.Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            logger.warn("translation" + e.getMessage());
        }
        return bytes;
    }

    @Override
    public boolean add(String paramString, int paramInt, Object paramObject) throws CacheException {
        if (null == paramString || null == paramObject) {
            return false;
        }
        Jedis jedis = null;
        try {
            jedis = RedisManager.getResource();
            if (!jedis.exists(paramString.getBytes()))
                jedis.set(paramString.getBytes(), ObjectToByte(paramObject));
            jedis.expire(paramString, paramInt);
            return true;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            throw new CacheException(e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public boolean set(String paramString, int paramInt, Object paramObject) throws CacheException {
        if (null == paramString || null == paramObject) {
            return false;
        }
        Jedis jedis = null;
        try {
            jedis = RedisManager.getResource();
            jedis.set(paramString.getBytes(), ObjectToByte(paramObject));
            jedis.expire(paramString, paramInt);
            return true;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            throw new CacheException(e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public boolean delete(String paramString) throws CacheException {
        if (null == paramString) {
            return false;
        }
        Jedis jedis = null;
        try {
            jedis = RedisManager.getResource();
            jedis.del(paramString.getBytes());
            return true;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            throw new CacheException(e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Object get(String paramString) throws CacheException {
        if (null == paramString) {
            return null;
        }
        Jedis jedis = null;
        try {
            jedis = RedisManager.getResource();
            byte[] bs = jedis.get(paramString.getBytes());
            if (bs == null) {
                return null;
            }
            Object o = ByteToObject(bs);
            return o;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            throw new CacheException(e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}