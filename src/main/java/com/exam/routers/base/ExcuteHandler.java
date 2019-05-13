package com.exam.routers.base;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * ExcuteHandler
 */
public class ExcuteHandler implements HomeHandler {

    @Override
    public Object excuteMethod(FullHttpRequest request, HandlerContext context) {
        System.out.println(context.getRouter());
        return context.getRouter().call(request);
    }
    
}