package com.exam;

import com.exam.routers.base.DispathHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * MyHttpServerhandler
 */
public class MyHttpServerhandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
       
            if(msg instanceof FullHttpRequest){
               handlerHttpRequest(ctx,(FullHttpRequest)msg);
            }else {
                System.out.println("oothermsg:"+msg);
            }
        //Unpooled.copiedBuffer("welcome netty httpServer",CharsetUtil.UTF_8)
        /* FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK
        ,Unpooled.copiedBuffer(obj.toString(),CharsetUtil.UTF_8));
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE); */
       
        
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

    public void handlerHttpRequest(ChannelHandlerContext ctx,FullHttpRequest request) throws Exception{
       
        String requestUrl = request.uri();
        System.out.println("request uri:"+requestUrl);
       
         //路由判断
         new DispathHandler().handler(ctx,request);
       
    }

    
   

} 