package me.ele.opdev.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuemingli on 16/2/29.
 */
@Configuration
@ConfigurationProperties(prefix = "demo.connect")
public class ConnectSetting {
    static public class Timeout {
        private Long session;
        private Long connect;

        public Timeout() {
        }

        public Long getSession() {
            return session;
        }

        public void setSession(Long session) {
            this.session = session;
        }

        public Long getConnect() {
            return connect;
        }

        public void setConnect(Long connect) {
            this.connect = connect;
        }
    }


    private String host;
    private Integer port;
    private Timeout timeout;

    public ConnectSetting() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Timeout getTimeout() {
        return timeout;
    }

    public void setTimeout(Timeout timeout) {
        this.timeout = timeout;
    }
}
