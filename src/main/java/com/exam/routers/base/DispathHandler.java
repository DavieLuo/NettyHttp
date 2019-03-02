package com.exam.routers.base;

import java.util.Map;
import java.util.Set;

import com.exam.routers.pojo.RouterInfo;
import com.exam.untls.CallBack;
import com.exam.untls.ResultType;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * DispathHandler
 */
public class DispathHandler {




    private Set<RouterStrategy> strategys = RouterContext.getInstance().getRouterSet();


    private Router checkUrl(FullHttpRequest request) {
        Router router = null;
        Set<RouterStrategy> strategys = RouterContext.getInstance().getRouterSet();
        for (RouterStrategy strategy : strategys) {
            Map<RouterInfo, Router> map= strategy.routerFunc();
            //request.uri()
            router = map.get(new RouterInfo("/login",request.method()));
            if(router!=null){
                break;
            }
        }
        return router;
    }

    private CallBack handlerinvoke(Router router,FullHttpRequest request) {
      
            if(router==null){
                return CallBack.error(ResultType.InvaildPath);
            }
            return router.call(request);
       
    }

    public void handler(ChannelHandlerContext ctx,FullHttpRequest request) {
        CallBack callback =null;
        try {
            callback = handlerinvoke(checkUrl(request), request);
        } catch (Exception e) {
            e.printStackTrace();
            callback =CallBack.error(ResultType.Error);
        }
        httpback( ctx, callback);
       
        
    }

    private void httpback(ChannelHandlerContext ctx,CallBack callback) {
        FullHttpResponse response =null;
        if(callback==null){
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK
                ,Unpooled.copiedBuffer(CallBack.error(ResultType.NullBack).toString(),CharsetUtil.UTF_8));
        }else{
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK
                ,Unpooled.copiedBuffer(callback.toString(),CharsetUtil.UTF_8));
               
        }
       
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
    
}