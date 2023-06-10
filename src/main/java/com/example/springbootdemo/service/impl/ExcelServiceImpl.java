package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.service.ExcelService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExcelServiceImpl implements ExcelService {
    @Override
    public void validate(String excel) throws IOException {
        // 获取工作簿
        ClassPathResource classPathResource = new ClassPathResource(excel);
        InputStream inputStream = classPathResource.getInputStream();
        Workbook wb = WorkbookFactory.create(inputStream);
// 获取第一个工作表
        Sheet sheet = wb.getSheetAt(0);
// 保存文件
        wb.write(new FileOutputStream(new File("template_check.xlsx")));
    }
}
