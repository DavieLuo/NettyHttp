package com.exam;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new App().startService(8888);
        
    }

    public void startService(int port) {
        EventLoopGroup bossLoop = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try{
            bootstrap.group(bossLoop, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception{
                        ChannelPipeline cp = channel.pipeline();
                        cp.addLast("http-decoder",new HttpRequestDecoder())
                            .addLast("http-aggregator", new HttpObjectAggregator(65536))// 目的是将多个消息转换为单一的request或者response对象
                            .addLast("http-encoder",new HttpResponseEncoder())//响应解码器
                            .addLast("http-chunked",new ChunkedWriteHandler())//目的是支持异步大文件传输（）
                            .addLast("myHandler",new MyHttpServerhandler());// 业务逻辑
    
                    }
                });
                ChannelFuture future =bootstrap.bind(new InetSocketAddress(port)).sync();
                System.out.println("服务启动成功 http://127.0.0.1:8888/");
                future.channel().closeFuture().sync();
               
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            bossLoop.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
