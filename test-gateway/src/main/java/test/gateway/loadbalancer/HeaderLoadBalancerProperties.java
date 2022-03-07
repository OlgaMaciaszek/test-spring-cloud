package test.gateway.loadbalancer;

import java.io.Serializable;

public class HeaderLoadBalancerProperties implements Serializable {
    public static final String HEADER_VERSION = "h.version";
    public static final String HEADER_SERVER_NAME = "h.server.name";
    public static final String HEADER_SERVER_GROUP = "h.server.group";
    public static final String HEADER_ACTIVE = "h.active";
    public static final String HEADER_WEIGHT = "h.weight";
    private String version;
    private String serverName;
    private String serverGroup;
    private String active;
    private double weight = 1.0D;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerGroup() {
        return serverGroup;
    }

    public void setServerGroup(String serverGroup) {
        this.serverGroup = serverGroup;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CustomLoadBalancerProperties{" +
                "version='" + version + '\'' +
                ", serverName='" + serverName + '\'' +
                ", serverGroup='" + serverGroup + '\'' +
                ", active='" + active + '\'' +
                ", weight=" + weight +
                '}';
    }
}
