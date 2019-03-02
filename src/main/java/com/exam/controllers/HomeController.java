package com.exam.controllers;

import java.util.Map;

import com.exam.untls.CallBack;

/**
 * HomeController
 */
public class HomeController {

    public CallBack home(Map<String,Object> map){
        return CallBack.success("homeController", null);
    }
}