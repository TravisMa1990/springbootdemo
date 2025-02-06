package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.DevicePoint;

import java.util.function.Consumer;

public interface PlatformService {
    void getData(String id, Consumer<DevicePoint> callback);
}
