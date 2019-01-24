package com.exam.controllers;

import java.util.Map;

/**
 * UserController
 */
public class UserController {

    public Object login(Map<String,Object> map) {
     //   map.forEach(item -> System.out.println(item) );
        System.out.println(map);
        return "hello";
    }
}