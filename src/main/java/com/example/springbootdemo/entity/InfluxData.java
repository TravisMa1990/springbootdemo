package com.example.springbootdemo.entity;

import lombok.Data;

import java.time.Instant;

@Data
public class InfluxData {
    private String result; // FluxColumn[index=0, label='result', dataType='string', group=false, defaultValue='_result']
    private Long table; // FluxColumn[index=1, label='table', dataType='long', group=false, defaultValue='']
    private Instant start; // FluxColumn[index=2, label='_start', dataType='dateTime:RFC3339', group=true, defaultValue='']
    private Instant stop; // FluxColumn[index=3, label='_stop', dataType='dateTime:RFC3339', group=true, defaultValue='']
    private Instant time; // FluxColumn[index=4, label='_time', dataType='dateTime:RFC3339', group=false, defaultValue='']
    private Object value; // FluxColumn[index=5, label='_value', dataType='long', group=false, defaultValue='']
    private String field; // FluxColumn[index=6, label='_field', dataType='string', group=true, defaultValue='']
    private String measurement; // FluxColumn[index=7, label='_measurement', dataType='string', group=true, defaultValue='']
    private String host; // FluxColumn[index=8, label='host', dataType='string', group=true, defaultValue='']
}
