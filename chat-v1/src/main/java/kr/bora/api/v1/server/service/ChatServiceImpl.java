package kr.bora.api.v1.server.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import kr.bora.api.v1.server.ChatServer;
import kr.bora.api.v1.server.EchoServerInitializeConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final ChatServer chatServer;

    @Override
    public void ServerStart() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(chatServer.getAdminGroup(), chatServer.getClientGroup())
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000 * 60 * 5)
                .option(ChannelOption.SO_BACKLOG,
                        )// connect를 요청하는 client 가능 수 not mean a client already connected
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new EchoServerInitializeConfig());
        ChannelFuture channelFuture = bootstrap.bind(port).sync();
    }
}
