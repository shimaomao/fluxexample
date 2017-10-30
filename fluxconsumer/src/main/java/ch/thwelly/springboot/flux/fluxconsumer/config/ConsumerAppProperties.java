
package ch.thwelly.springboot.flux.fluxconsumer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("consumer-client")
public class ConsumerAppProperties {

    private String providerUrl;

    public String getProviderUrl() {
        return providerUrl;
    }

    public void setProviderUrl(final String providerUrl) {
        this.providerUrl = providerUrl;
    }
}
