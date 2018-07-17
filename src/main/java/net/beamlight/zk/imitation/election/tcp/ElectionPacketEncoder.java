package net.beamlight.zk.imitation.election.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * Created by gaofeihang on 2018/2/23.
 */
public class ElectionPacketEncoder extends MessageToByteEncoder<ElectionPacket> {

    private static final Logger LOG = LoggerFactory.getLogger(ElectionPacketEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ElectionPacket packet, ByteBuf byteBuf) {

        if (ElectionPacket.TYPE_CONNECT == packet.getType()) {
            byteBuf.writeLong(packet.getMyid());

        } else if (ElectionPacket.TYPE_NOTIFICATION == packet.getType()) {
            ByteBuffer byteBuffer = buildMsg(
                    packet.getState().ordinal(),
                    packet.getLeader(),
                    packet.getZxid(),
                    packet.getElectionEpoch(),
                    packet.getPeerEpoch());

            int length = byteBuffer.position();
            byteBuffer.flip();

            byteBuf.writeInt(length);
            byteBuf.writeBytes(byteBuffer);
        }

    }

    private ByteBuffer buildMsg(int state,
                               long leader,
                               long zxid,
                               long electionEpoch,
                               long epoch) {
        byte requestBytes[] = new byte[40];
        ByteBuffer requestBuffer = ByteBuffer.wrap(requestBytes);

        /*
         * Building notification packet to send
         */

        requestBuffer.clear();
        requestBuffer.putInt(state);
        requestBuffer.putLong(leader);
        requestBuffer.putLong(zxid);
        requestBuffer.putLong(electionEpoch);
        requestBuffer.putLong(epoch);
        requestBuffer.putInt(1);

        return requestBuffer;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOG.error("encoder error", cause);
    }
}
