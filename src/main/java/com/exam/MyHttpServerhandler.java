package com.exam;

import com.exam.routers.base.DispathHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * MyHttpServerhandler
 */
public class MyHttpServerhandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
        Object obj  = null; 
        try {
            if(msg instanceof FullHttpRequest){
                obj =handlerHttpRequest((FullHttpRequest)msg);
            }else {
                System.out.println("oothermsg:"+msg);
            }
                System.out.println(obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
                obj= "异常";
        }
        //Unpooled.copiedBuffer("welcome netty httpServer",CharsetUtil.UTF_8)
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK
        ,Unpooled.copiedBuffer(obj.toString(),CharsetUtil.UTF_8));
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
       
        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接关闭...");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("创建了连接...");
    }

    public Object handlerHttpRequest(FullHttpRequest request) throws Exception{
       
        String requestUrl = request.uri();
        System.out.println("request uri:"+requestUrl);
       
         //路由判断
        return new DispathHandler().handler(gettruePath(requestUrl), request);
       
    }

    public String gettruePath(String url){
        if(url.contains("?")){
            return url.split("[?]")[0];
        }
        return url;
    }
   

} 