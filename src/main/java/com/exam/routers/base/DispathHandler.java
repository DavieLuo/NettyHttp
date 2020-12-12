package com.exam.routers.base;


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
 * @author a
 */
public class DispathHandler {




    
    public void handler(ChannelHandlerContext ctx,FullHttpRequest request) {
 
        HandlerContext context = new HandlerContext();
        context.setHandler(new UrlHandler());

        context.getHandler().hasUrl(request, context);
        context.getHandler().hasPermission(request, context);
        Object obj = context.getHandler().excuteMethod(request, context);

        httpback( ctx, (CallBack)obj);
             
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