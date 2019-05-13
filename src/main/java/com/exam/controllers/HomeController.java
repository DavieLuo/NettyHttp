package com.exam.controllers;

import java.util.Map;

import com.exam.untls.CallBack;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * HomeController
 */
public class HomeController {

    public CallBack home(FullHttpRequest request) {
        return CallBack.success("homeController", null);
    }
}