package com.example.springbootdemo.dao.query;

import lombok.Data;

@Data
public class FluxQuery {
    private String name;
    private String value;

    public FluxQuery(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public FluxQuery(String name) {
        this.name = name;
    }

    public String toFluxQuery() {
        if (value == null) {
            return String.format("%s()", name);
        }
        return String.format("%s(%s)", name, value);
    }

}
