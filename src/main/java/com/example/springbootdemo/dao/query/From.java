package com.example.springbootdemo.dao.query;

public class From extends FluxQuery {
    public From(String bucket) {
        super("from", String.format("bucket: \"%s\"", bucket));
    }
}
