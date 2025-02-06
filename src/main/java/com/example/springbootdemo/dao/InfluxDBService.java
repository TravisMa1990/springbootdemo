package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.LocPointData;
import com.example.springbootdemo.dao.query.FluxQueryBuilder;
import com.example.springbootdemo.util.InfluxDBUtils;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InfluxDBService {
    @Autowired
    WriteApiBlocking writeApi;

    @Autowired
    QueryApi queryApi;


    public void writeData(String measurement, Map<String, String> tags, Map<String, Object> fields) {
        Point point = Point.measurement(measurement)
                .time(System.currentTimeMillis(), WritePrecision.MS)
                .addTags(tags)
                .addFields(fields);
        writeApi.writePoint(point);
    }

    public void queryColumnTest(){
        String query = "from(bucket: \"iot\") |> range(start: -30d) |> filter(fn: (r) => r._measurement == \"system_metrics\")";
        List<FluxTable> ret = queryApi.query(query);
//        ret.forEach(System.out::println);
        ret.get(1).getColumns().forEach(System.out::println);
//        ret.get(1).getRecords().forEach(r-> r.getValues().forEach((k,v) -> System.out.println(k+"--"+v)));
//        fluxtable 转java实体类
//        List<SystemMetrics> systemMetrics = InfluxDBUtils.convertToEntity(ret, SystemMetrics.class);
//        systemMetrics.forEach(System.out::println);


    }

    public List<LocPointData> queryByDevice(String deviceName){
//        String query = "from(bucket: \"iot\") |> range(start: -30d) |> filter(fn: (r) => r._measurement == \"system_metrics\")";
//        from(bucket: "iot") |> range(start: -30d) |> filter(fn: (r) => r._measurement == "electricity") |> filter(fn: (r) => r.device == "device1") |> last()
        FluxQueryBuilder builder = new FluxQueryBuilder();
        String query = builder.from("iot").range("-30d").filter("_measurement", "electricity").filter("device", deviceName).last().build();
        System.out.println(query);
        List<FluxTable> ret = queryApi.query(query);
        System.out.println(InfluxDBUtils.count(ret));
        List<LocPointData> list = InfluxDBUtils.converter(ret, LocPointData.class,"location","device");
        list.forEach(System.out::println);
        return list;
    }
}
