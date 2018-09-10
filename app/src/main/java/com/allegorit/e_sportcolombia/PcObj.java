package com.allegorit.e_sportcolombia;

public class PcObj {
    String id, ping;

    public PcObj(String id, String ping) {
        this.id = id;
        this.ping = ping;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    @Override
    public String toString() {
        return "Id: "+id+ " ping: "+ping;
    }
}
