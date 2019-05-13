package com.exam.routers.base;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * HomeHandler
 * api request filter
 * permission filter
 * excute method return result
 * @author luo
 */
public interface HomeHandler {

    /**
     * 项目是否包含该api
     * @param request
     * @param context
     */
    default boolean hasUrl(FullHttpRequest request,HandlerContext context) {
        return false;
    }

    /**
     * 该用户是否有该api的访问权限
     * @param request
     * @param context
     */
    default boolean hasPermission(FullHttpRequest request,HandlerContext context) {
        return false;
    }

    /**
     * 执行对应的方法
     * @param request
     * @param context
     */
   default Object excuteMethod(FullHttpRequest request,HandlerContext context){
        return context.getResult();
   }

}