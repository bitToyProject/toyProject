package kr.bora.api.v1.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

public class EchoServerInitializeConfig extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LineBasedFrameDecoder(65536));
        //TODO : should have to change encoder & decoder
        pipeline.addLast(new JsonObjectDecoder());
        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new EchoMsgHandler());
        pipeline.addLast(new ReadTimeoutHandler(600, TimeUnit.SECONDS));
    }
}
