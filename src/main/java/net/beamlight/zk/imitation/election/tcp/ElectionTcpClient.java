package net.beamlight.zk.imitation.election.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.net.InetSocketAddress;

/**
 * Created by gaofeihang on 2018/2/13.
 */
public class ElectionTcpClient implements Closeable {

    private static final Logger LOG = LoggerFactory.getLogger(ElectionTcpClient.class);

    private String host;
    private int port;

    private EventLoopGroup workerGroup;
    private ChannelFuture channelFuture;

    public ElectionTcpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void open() {
        workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ElectionPacketEncoder());
                        ch.pipeline().addLast(new ElectionPacketDecoder());
                        ch.pipeline().addLast(new ElectionPacketHandler());
                    }
                });

        try {
            channelFuture = bootstrap.connect(new InetSocketAddress(host, port)).sync();
        } catch (Exception e) {
            LOG.error("connect error: {}:{}", host, port, e);
        }

        LOG.warn("election client opened: {}:{}", host, port);
    }

    public void send(ElectionPacket packet) {
        Channel channel = channelFuture.channel();
        ChannelFuture future = channel.write(packet);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    LOG.info("send packet success");
                }
            }
        });
        channel.flush();
    }

    @Override
    public void close() {

    }
}
