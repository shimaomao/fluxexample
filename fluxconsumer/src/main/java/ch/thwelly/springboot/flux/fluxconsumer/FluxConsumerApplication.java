package ch.thwelly.springboot.flux.fluxconsumer;

import ch.thwelly.springboot.flux.fluxconsumer.config.ConsumerAppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties(ConsumerAppProperties.class)
public class FluxConsumerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(FluxConsumerApplication.class, args);
    }
}
