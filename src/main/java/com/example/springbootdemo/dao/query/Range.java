package com.example.springbootdemo.dao.query;

public class Range extends FluxQuery{
    public Range(String start, String stop) {
        super("|> range", String.format("start: %s, stop: %s", start, stop));
    }
    public Range(String start) {
        super("|> range", String.format("start: %s", start));
    }
}
