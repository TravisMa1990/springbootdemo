package com.example.springbootdemo.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelUtil {

    /**
     * 判断单元格是否在合并区域中
     *
     * @param excel  工作簿Sheet对象(classpath相对路径)
     * @param sheetName  表名 (传null取表1)
     * @param titleCols 表头 (1-based)
     * @return 以字符串格式返回sheet数据(遇到合并单元格自动填充)
     */
    public static List<List<String>> getDataWithMergeRegion(String excel,String sheetName,Integer titleCols) throws IOException {
        // 获取工作簿
        ClassPathResource classPathResource = new ClassPathResource(excel);
        InputStream inputStream = classPathResource.getInputStream();
        Workbook wb = WorkbookFactory.create(inputStream);
// 获取工作表,根据名字获取,默认获取第一个
        Sheet sheet = null;
        if (StringUtils.isNotEmpty(sheetName)) {
            sheet = wb.getSheet(sheetName);
        } else {
            sheet = wb.getSheetAt(0);
        }

// 存储每行解析后数据
        List<List<String>> parsedData = new ArrayList<>();
// 拿到合并单元格
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();

        // 存储所有合并区域的值
        Map<String, String> regionValues = new HashMap<>();

        if (CollectionUtils.isNotEmpty(mergedRegions)){
            regionValues = getMergedRegionValues(mergedRegions,sheet);
        }

        // 遍历所有行
        int maxTimes = 20;int curTimes = 0;
        for (Row row : sheet) {
            if (titleCols.compareTo(curTimes++) > 0) {
                continue;
            }
            // 存储本行解析后数据
            List<String> rowData = new ArrayList<>();

            // 遍历本行所有单元格
            for (Cell cell : row) {
                String cellStringValue = getCellStringValue(cell);

                if (CollectionUtils.isNotEmpty(mergedRegions)) {
                    // 判断是否在合并区域内
                    CellRangeAddress mergeRegion = mergedRegions.stream()
                            .filter(mergedRegion -> mergedRegion.isInRange(cell)).findFirst().orElse(null);

                    if (mergeRegion != null) {
                        String regionValue = regionValues.get(mergeRegion.toString());
                        if (regionValue != null) {
                            cellStringValue = regionValue;
                        }
                    }
                }
                rowData.add(cellStringValue);
            }
            // 添加本行解析后数据
            parsedData.add(rowData);

            if (curTimes > maxTimes){
                break;
            }
        }
// 去空行返回
        return parsedData.stream()
                .filter(rowData->!rowData.stream().allMatch(cellData->cellData.equals("")))
                .collect(Collectors.toList());
    }

    private static Map<String,String> getMergedRegionValues(List<CellRangeAddress> mergedRegions, Sheet sheet){
        Map<String, String> regionValues = new HashMap<>();
        // 遍历所有的合并区域
        for (CellRangeAddress region : mergedRegions) {
            // 合并区域的起始行和终止行
            int rowStart = region.getFirstRow();
            int rowEnd = region.getLastRow();

            String value = "";

            // 遍历开始行到结束行
            for (int i = rowStart; i <= rowEnd; i++) {
                Row row = sheet.getRow(i);
                // 区域的起始列和终止列
                int colStart = region.getFirstColumn();
                int colEnd = region.getLastColumn();

                // 获取起始列到终止列的单元格值
                for (int j = colStart; j <= colEnd; j++) {
                    Cell cell = row.getCell(j);
                    value = getCellStringValue(cell);
                    if (StringUtils.isNotBlank(value)){
                        break;
                    }
                }
                if (StringUtils.isNotBlank(value)){
                    break;
                }
            }

            // 添加到Map,key是区域,value是值
            regionValues.put(region.toString(), value);
        }
        return regionValues;
    }

    private static String getCellStringValue(Cell cell){
        // 获取单元格类型
        CellType type = cell.getCellType();

// 根据类型获取值
        String value = "";
        switch(type) {
            case NUMERIC:
                value = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                value = cell.getCellFormula();
//                value = cell.getNumericCellValue();  // FORMULA可能返回数字、字符串或布尔值
                break;
            case ERROR:
                // 无法获取值,记录错误信息
                break;
        }

// 返回值
        return value;
    }

}
