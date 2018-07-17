package net.beamlight.zk.imitation.election.tcp;

import net.beamlight.zk.imitation.election.ServerState;

/**
 * Created by gaofeihang on 2018/2/23.
 */
public class ElectionPacket {

    public static final int TYPE_CONNECT = 1;
    public static final int TYPE_NOTIFICATION = 2;

    private int type;
    private int myid;

    private long leader;
    private long zxid;
    private long electionEpoch;

    private ServerState state;
    private long sid;
    private long peerEpoch;

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

    public void setLeader(long leader) {
        this.leader = leader;
    }

    public long getLeader() {
        return leader;
    }

    public void setZxid(long zxid) {
        this.zxid = zxid;
    }

    public long getZxid() {
        return zxid;
    }

    public void setElectionEpoch(long electionEpoch) {
        this.electionEpoch = electionEpoch;
    }

    public long getElectionEpoch() {
        return electionEpoch;
    }

    public ServerState getState() {
        return state;
    }

    public void setState(ServerState state) {
        this.state = state;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getPeerEpoch() {
        return peerEpoch;
    }

    public void setPeerEpoch(long peerEpoch) {
        this.peerEpoch = peerEpoch;
    }
}
