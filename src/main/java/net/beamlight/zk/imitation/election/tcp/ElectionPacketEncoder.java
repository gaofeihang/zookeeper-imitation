package net.beamlight.zk.imitation.election.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by gaofeihang on 2018/2/23.
 */
public class ElectionPacketEncoder extends MessageToByteEncoder<ElectionPacket> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ElectionPacket packet, ByteBuf byteBuf) {

        if (ElectionPacket.TYPE_CONNECT == packet.getType()) {
            byteBuf.writeLong(packet.getMyid());
        }
    }

}
