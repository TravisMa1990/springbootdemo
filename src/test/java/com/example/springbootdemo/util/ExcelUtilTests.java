package com.example.springbootdemo.util;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ExcelUtilTests {
    @Test
    void getDataWithMergeRegion(){
        List<List<String>> data = null;
        try {
            data = ExcelUtil.getDataWithMergeRegion("template.xlsx","汇总",2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data.forEach(row->{
            if (CollectionUtils.isNotEmpty(row)){
                System.out.println(row.stream().collect(Collectors.joining("|")));
            }
        });
    }
}
