package net.beamlight.zk.imitation.election;

import net.beamlight.commons.util.ThreadUtils;
import net.beamlight.zk.imitation.election.tcp.ElectionPacket;
import net.beamlight.zk.imitation.election.tcp.ElectionTcpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

/**
 * Created by gaofeihang on 2018/2/13.
 */
public class Election implements Closeable {

    private static final Logger LOG = LoggerFactory.getLogger(Election.class);

    private static final int MY_ID = 4;
    private static final int SID = 1;
    private static final String HOST_NAME = "test-server";
    private static final int ELECTION_PORT = 4988;

    public void start() {
        ElectionTcpClient client = new ElectionTcpClient(HOST_NAME, ELECTION_PORT);
        client.open();

        ElectionPacket packet = new ElectionPacket(ElectionPacket.TYPE_CONNECT);
        packet.setMyid(MY_ID);

        boolean success = client.send(packet, 1000);
        LOG.info("send connection packet: success = {}", success);

        ThreadUtils.sleep(5000);

        packet = new ElectionPacket(ElectionPacket.TYPE_NOTIFICATION);
        packet.setMyid(MY_ID);

        packet.setLeader(Long.MIN_VALUE);
        packet.setZxid(Long.MIN_VALUE);
        packet.setPeerEpoch(Long.MIN_VALUE);

        packet.setElectionEpoch(1);
        packet.setState(ServerState.LOOKING);
        packet.setSid(SID);

        success = client.send(packet, 1000);

        LOG.info("send notification packet: success = {}", success);
    }

    @Override
    public void close() {

    }

}
