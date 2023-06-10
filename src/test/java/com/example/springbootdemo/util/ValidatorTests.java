package com.example.springbootdemo.util;

import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ValidatorTests {
    @Test
    void isValidLength() {
        String str = "测试校验长度";
        int min = 0;
        int max = 2;
        boolean result = false;
        assert Validator.isValidLength(str,min,max).equals(result);
    }

    @Test
    void isCorrectFormat() {
        String str = "2023-2-18";
        String regex = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";
        boolean result = true;
        assert Validator.isCorrectFormat(str,regex).equals(result);
    }

    @Test
    void isIn() {
        String str = "测试";
        List<String> colt = Arrays.asList("测试", "校验");
        boolean result = true;
        assert Validator.isIn(str,colt).equals(result);
    }

}
