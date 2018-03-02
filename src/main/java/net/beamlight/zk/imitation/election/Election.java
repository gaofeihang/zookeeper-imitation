package net.beamlight.zk.imitation.election;

import net.beamlight.commons.util.ThreadUtils;
import net.beamlight.zk.imitation.election.tcp.ElectionPacket;
import net.beamlight.zk.imitation.election.tcp.ElectionTcpClient;

import java.io.Closeable;

/**
 * Created by gaofeihang on 2018/2/13.
 */
public class Election implements Closeable {

    public void start() {
        ElectionTcpClient client = new ElectionTcpClient("localhost", 3888);
        client.open();

        ElectionPacket packet = new ElectionPacket(ElectionPacket.TYPE_CONNECT);
        packet.setMyid(4);

        client.send(packet);

        ThreadUtils.sleep(1000 * 60);
    }

    @Override
    public void close() {

    }

}
