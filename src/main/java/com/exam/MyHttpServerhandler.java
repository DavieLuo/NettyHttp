package com.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.exam.routers.RouterUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
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
        HttpMethod method = request.method();
        String requestUrl = request.uri();
        System.out.println("request uri:"+requestUrl);

        
        

        Map<String,Object> map = null;
        if(method.equals(HttpMethod.GET)){

            map=resolveGetData(requestUrl);
        }else if(method.equals(HttpMethod.POST)){
               
            map = (Map)resolvePostData(request);
        }else{
            System.err.println("other request");
        }
         
        //路由判断
        return new RouterUtils().handler(requestUrl, map);
    }

    public Map<String,Object> resolveGetData(String url){
        Map<String,Object> map = new HashMap<>();
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(url);
        queryStringDecoder.parameters().entrySet().forEach(item->map.put(item.getKey(), item.getValue().get(0)));
        return map;
    }

    public Map<String,String> myresolveGetData(String url){
        Map<String,String> map = new HashMap<>();
        url.trim();
        System.out.println("request url:"+url);
        try {
            String[] param = url.split("\\?");
            String[]  ary = param[1].split("\\&");
            for (int i = 0; i < ary.length; i++) {
                String[] tmp = ary[i].split("\\=");
                map.put(tmp[0], tmp[1]);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return map;
    }

    public Object resolvePostData(FullHttpRequest request) throws Exception{
        HttpHeaders header = request.headers();
        String contentType = header.get("Content-Type");
        System.out.println("contentType:"+contentType);
        //键值对

        Map<String,Object> paramMap = new HashMap<>();
        if(contentType.equals("application/x-www-form-urlencoded")){ 
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder( request);
            List<InterfaceHttpData> interfaceHttpDatas= decoder.getBodyHttpDatas();
            for (InterfaceHttpData item : interfaceHttpDatas) {
                Attribute data =(Attribute) item;
                paramMap.put(data.getName(), data.getValue());
            }
        }else if(contentType.equals("multipart/form-data")) {
            HttpPostMultipartRequestDecoder decoder = new HttpPostMultipartRequestDecoder( request);
            List<InterfaceHttpData> interfaceHttpDatas= decoder.getBodyHttpDatas();
            for (InterfaceHttpData  item : interfaceHttpDatas) {
                if(item.getHttpDataType().name().equals("FileUpload")){
                    FileUpload fileUpload = (FileUpload)item;
                    paramMap.put(fileUpload.getName(),fileUpload.getFile());
                }else{
                    Attribute data =(Attribute) item;
                    paramMap.put(data.getName(), data.getValue());
                }
            }
        }else if(contentType.equals("text/plain")||"application/json".equals(contentType)) {
            ByteBuf buf = request.content();
            byte[] recevice = new byte[buf.readableBytes()];
            buf.readBytes(recevice);
            String result = new String(recevice,CharsetUtil.UTF_8);
            System.out.println("result"+result);
            return JSONObject.parseObject(result, Map.class);
        }
        return paramMap;
    } 

} 