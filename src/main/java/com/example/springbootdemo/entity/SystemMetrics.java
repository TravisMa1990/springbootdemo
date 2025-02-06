package com.example.springbootdemo.entity;

import lombok.Data;

import java.time.Instant;

@Data
public class SystemMetrics {
    private Instant time;
    private String host;
    private Long bytes_recv;
    private Long bytes_sent;
    private Double cpu_percent;
    private Double disk_percent;
    private Double memory_percent;
}
