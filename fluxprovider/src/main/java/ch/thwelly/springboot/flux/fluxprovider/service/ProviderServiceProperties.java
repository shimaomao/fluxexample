
package ch.thwelly.springboot.flux.fluxprovider.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "provider-service")
public class ProviderServiceProperties {

    private boolean enableTimeout;
    private int timeout;

    public boolean isEnableTimeout() {
        return enableTimeout;
    }

    public void setEnableTimeout(final boolean enableTimeout) {
        this.enableTimeout = enableTimeout;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(final int timeout) {
        this.timeout = timeout;
    }
}
