package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("device_point")
public class DevicePoint {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer funcCode;
    private Integer devAddr;
    private Integer registerAddress;
    private Integer stepLength;
    private Integer baudrate;
    private Integer bytesize;
    private String parity;
    private Integer stopbits;
    private Integer dataType;
    private Integer warehouse;
    private String remark;
    private String collectName;
    private String collectUnit;
}
