package com.example.springbootdemo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class InfluxDBServiceTest {

    @Autowired
    private InfluxDBService influxDBService;

    @Test
    public void insertTest() {
        Map<String, String> tags = new HashMap<>();
        tags.put("location", "server2");
        tags.put("type", "cpu_usage");
        tags.put("process", "java");

        Map<String, Object> fields = new HashMap<>();
        fields.put("value", 55.5); // 假设这是CPU使用率的值
        fields.put("user", 25.2); // 用户模式下的CPU使用率
        fields.put("system", 30.3); // 系统模式下的CPU使用率

        String measurement = "cpu_data"; // 测量名称
        influxDBService.writeData(measurement, tags, fields);
    }

    @Test
    public void queryTest() {
        influxDBService.queryByDevice("device1");
    }
}
