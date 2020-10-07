package com.artnaseef.example.oas.impl;

import com.artnaseef.example.oas.intf.HelloApi;

public class HelloApiImpl implements HelloApi {

    public String hello() {
        return "Hello";
    }
}
