package com.example.springbootdemo.util;

import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class InfluxDBUtils {

    private static final Map<Class<?>, Map<String, Field>> fieldCache = new ConcurrentHashMap<>();

    /**
     * 将 FluxTable 转换为指定的实体类列表
     *
     * @param tables FluxTable 列表
     * @param clazz  目标实体类的 Class 对象
     * @param tags  数据标签字段名
     * @param <T>    目标实体类的类型
     * @return 转换后的实体类列表
     */
    public static <T> List<T> converter(List<FluxTable> tables, Class<T> clazz, String... tags) {
        if (tables == null || tables.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Instant, T> convertMap = new ConcurrentHashMap<>();
        Map<String, Field> cachedFields = fieldCache.computeIfAbsent(clazz, k -> new ConcurrentHashMap<>());

        for (FluxTable table : tables) {
            for (FluxRecord row : table.getRecords()) {
                Instant time = row.getTime();
                T entity = convertMap.get(time);
                if (entity == null) {
                    try {
                        entity = clazz.getDeclaredConstructor().newInstance();
                        Field timeField = getField(clazz, "time", cachedFields);
                        timeField.setAccessible(true);
                        timeField.set(entity, time);

                        if (tags != null) {
                            for (String tag : tags) {
                                Field tagField = getField(clazz, tag, cachedFields);
                                tagField.setAccessible(true);
                                tagField.set(entity, row.getValueByKey(tag));
                            }
                        }
                        convertMap.put(time, entity);
                    } catch (ReflectiveOperationException e) {
                        log.warn("Entity Instance error: {}", e.getMessage(), e);
                        continue;
                    }
                }

                try {
                    String fieldName = row.getField();
                    if (fieldName == null) {
                        continue;
                    }
                    Field field = getField(clazz, fieldName, cachedFields);
                    field.setAccessible(true);
                    field.set(entity, row.getValue());
                } catch (ReflectiveOperationException e) {
                    log.warn("Set field value error: {}", e.getMessage(), e);
                }
            }
        }
        return new ArrayList<>(convertMap.values());
    }

    private static Field getField(Class<?> clazz, String fieldName, Map<String, Field> cachedFields) throws NoSuchFieldException {
        return cachedFields.computeIfAbsent(fieldName, name -> {
            try {
                return clazz.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static Integer count(List<FluxTable> tables) {
        if (tables == null || tables.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (FluxTable table : tables) {
            count += table.getRecords().size();
        }
        return count;
    }
}
