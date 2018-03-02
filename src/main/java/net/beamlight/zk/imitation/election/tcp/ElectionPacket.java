package net.beamlight.zk.imitation.election.tcp;

/**
 * Created by gaofeihang on 2018/2/23.
 */
public class ElectionPacket {

    public static final int TYPE_CONNECT = 1;

    private int type;
    private int myid;

    public ElectionPacket(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setMyid(int myid) {
        this.myid = myid;
    }

    public int getMyid() {
        return myid;
    }

}
