package com.exam.routers.base;

import com.exam.untls.CallBack;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Router
 */
@FunctionalInterface
public interface Router {

    CallBack call(FullHttpRequest request);
}