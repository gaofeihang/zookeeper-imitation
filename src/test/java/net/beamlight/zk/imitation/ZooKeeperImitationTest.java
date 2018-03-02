package net.beamlight.zk.imitation;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gaofeihang on 2018/2/9.
 */
public class ZooKeeperImitationTest {

    @Test
    public void testBootstrap() {
        new ZooKeeperImitation().start();
    }

}
