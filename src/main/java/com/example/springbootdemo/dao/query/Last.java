package com.example.springbootdemo.dao.query;

public class Last extends FluxQuery{
    public Last() {
        super("|> last");
    }
}
