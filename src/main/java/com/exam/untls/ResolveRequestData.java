package com.exam.untls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;

/**
 * ResolveRequestData
 */
public class ResolveRequestData {



    public static Map<String,Object> resolveData(FullHttpRequest request) throws Exception {
        Map<String,Object> map = null;
        HttpMethod method = request.method();
        if(method.equals(HttpMethod.GET)){

            map=resolveGetData(request.uri());
        }else if(method.equals(HttpMethod.POST)){
               
            map = resolvePostData(request);
        }else{
            System.err.println("other request");
        }
        return map;
    }


    public static Map<String,Object> resolveGetData(String url){
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

    public static Map<String,Object> resolvePostData(FullHttpRequest request) throws Exception{
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