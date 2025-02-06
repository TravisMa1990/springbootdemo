package com.example.springbootdemo.dao.query;

public class Filter extends FluxQuery{
    public Filter(String name, String value) {
        super("|> filter", String.format("fn: (r) => r.%s == \"%s\"",name, value));
    }
}
