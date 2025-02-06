package com.example.springbootdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springbootdemo.entity.DevicePoint;
import com.example.springbootdemo.mapper.DevicePointMapper;
import com.example.springbootdemo.service.PlatformService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Component
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    DevicePointMapper devicePointMapper;

    @Override
    public void getData(String id, Consumer<DevicePoint> callback) {
        // 异步查询数据库
        CompletableFuture.supplyAsync(() -> {
            try {
                List<DevicePoint> data = devicePointMapper.selectByIds(Collections.singleton(id));
                if (CollectionUtils.isNotEmpty(data)) {
                    return data.get(0);
                }
            } catch (Exception e) {
                return null;
            }
            return null;
        }).thenAccept(callback);
    }
}
