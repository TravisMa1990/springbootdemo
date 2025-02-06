package com.example.springbootdemo.entity;

import lombok.Data;

import java.time.Instant;

@Data
public class LocPointData {
    private Instant time;
    private String location;
    private String device;
    private Double temperature;
    private Long value;
    private Double voltage;
}
