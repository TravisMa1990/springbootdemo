package com.example.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.entity.DevicePoint;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DevicePointMapper extends BaseMapper<DevicePoint> {
}
