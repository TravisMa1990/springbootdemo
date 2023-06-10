package com.example.springbootdemo.util;

import java.util.List;

public class Validator {
    // 校验字符串长度
    public static Boolean isValidLength(String str, int min, int max) {
        return str != null && str.length() >= min && str.length() <= max;
    }

    // 校验字符串格式,比如手机号、邮箱等
    public static Boolean isCorrectFormat(String str, String regex) {
        return str != null && str.matches(regex);
    }

    // 校验字符串值是否在给定集合内
    public static Boolean isIn(String str, List<String> collection) {
        if (str != null && collection != null) {
            return collection.stream().anyMatch(r->r.equals(str));
        }
        return false;
    }
}
