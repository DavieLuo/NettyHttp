package com.exam.controllers;


import com.exam.untls.CallBack;
import com.exam.untls.ResolveRequestData;
import com.exam.untls.ResultType;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * UserController
 */
public class UserController {

    public static CallBack login(FullHttpRequest request) {
        // map.forEach(item -> System.out.println(item) );
        try {
            System.out.println(ResolveRequestData.resolveData(request));
        } catch (Exception e) {
            e.printStackTrace();
            return CallBack.error(ResultType.ResolveDataFail);
        }
        return CallBack.success(ResultType.OK , "hello !");
    }
}