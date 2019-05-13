package com.exam.routers.base;

import com.exam.untls.CallBack;
import com.exam.untls.ResultType;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * PermissionHandler
 */
public class PermissionHandler implements HomeHandler {
   

    @Override
    public boolean hasPermission(FullHttpRequest request, HandlerContext context) {
        
        boolean flag = true;
        // do somethings

        if(flag) {
            context.setHandler(new ExcuteHandler());  
        }else {
            context.setResult(CallBack.error(ResultType.NO_PERMISSION));
        }        
        return flag;
    }

    

    
    
}