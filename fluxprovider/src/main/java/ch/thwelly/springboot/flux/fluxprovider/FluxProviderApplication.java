package ch.thwelly.springboot.flux.fluxprovider;

import ch.thwelly.springboot.flux.fluxprovider.service.ProviderServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties(ProviderServiceProperties.class)
public class FluxProviderApplication {

    public static void main(final String[] args) {
        SpringApplication.run(FluxProviderApplication.class, args);
    }
}
