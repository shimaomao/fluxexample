
package ch.thwelly.springboot.flux.fluxconsumer.config;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("consumer-client")
public class ConsumerAppProperties {

    private String providerServer;
    private int providerPort;

    public String getProviderServer() {
        return providerServer;
    }

    public void setProviderServer(final String providerServer) {
        this.providerServer = providerServer;
    }

    public int getProviderPort() {
        return providerPort;
    }

    public void setProviderPort(final int providerPort) {
        this.providerPort = providerPort;
    }

    public String getServerUrl() {
        return String.format("http://%s:%d", this.providerServer, this.providerPort);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ConsumerAppProperties that = (ConsumerAppProperties) o;
        return providerPort == that.providerPort &&
                Objects.equal(providerServer, that.providerServer);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(providerServer, providerPort);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("providerServer", providerServer)
                .add("providerPort", providerPort)
                .toString();
    }
}
