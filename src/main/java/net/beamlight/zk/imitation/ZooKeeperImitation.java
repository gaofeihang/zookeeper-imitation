package net.beamlight.zk.imitation;

import net.beamlight.zk.imitation.election.Election;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gaofeihang on 2018/2/9.
 */
public class ZooKeeperImitation {

    private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperImitation.class);

    public void start() {
        Election election = new Election();
        election.start();
        LOG.info("zookeeper imitation started.");
    }

}
