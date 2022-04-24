package kr.bora.api.v1.server;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.util.Assert;

import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    //팀에 활용되는 유저들을 채팅방 목록에 담아놓는 그릇
    static ArrayList<Socket> userList = new ArrayList<>();

    //채널에서 다른 ip에서 들어오는 클라이언트의 연결을 받기 위한 admin group
    EventLoopGroup adminGroup = new NioEventLoopGroup(1);

    //adminGroup에 접속하기 위한 자식 group
    EventLoopGroup clientGroup = new NioEventLoopGroup();

    public EventLoopGroup getClientGroup() {
        return clientGroup;
    }

    public EventLoopGroup getAdminGroup() {
        return adminGroup;
    }

    private final int port;

    public ChatServer(int port) {
        Assert.notNull(port,"요청자가 요청한 채팅방의 port는 필수 입니다.");
        this.port = port;
    }

}
