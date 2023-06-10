package com.example.springbootdemo.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ExcelService {
    void validate(String excel) throws IOException;
}
