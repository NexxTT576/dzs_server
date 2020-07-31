package com.mdy.sharp.util;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONUtil {
    private static JSONSerializer serializer = new JSONSerializer();
    static {
        serializer.exclude(new String[] { "*.class" });
    }

    public static String toJson(Object obj) {
        return serializer.deepSerialize(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        JSONDeserializer<T> deser = new JSONDeserializer<T>();
        return (T) deser.deserialize(json, clazz);
    }

    public static <T> T fromJsonList(String json, Class<?> clazz) {
        JSONDeserializer<T> deser = new JSONDeserializer<T>();
        return (T) deser.use("values", clazz).deserialize(json);
    }

}