package net.beamlight.zk.imitation.election.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.beamlight.commons.util.ByteArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by gaofeihang on 2018/2/23.
 */
public class ElectionPacketDecoder extends ByteToMessageDecoder {

    private static final Logger LOG = LoggerFactory.getLogger(ElectionPacketDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        byte[] dst = new byte[1024];
        in.readBytes(dst);
        LOG.info("decode bytes: {}", ByteArrayUtils.hexDump(dst));
    }

}
