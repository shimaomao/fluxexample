
package ch.thwelly.springboot.flux.fluxconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfig {

    private static final String FLUXPROVIDER_URL = "ch.thwelly.springboot.flux.fluxconsumer.controller.client";

    ApiInfo bitcoinApiInfo() {
        return new ApiInfoBuilder()
                .title("Flux Consumer")
                .description("Consumer Client for the Flux Demo. ")
                .license("")
                .licenseUrl("http://unlicense.org")
                .termsOfServiceUrl("")
                .version("0.0.1")
                .contact(new Contact("Robert Wellinger", "https://www.thwelly.ch/site/", "rob.wellinger@gmail.com"))
                .build();
    }

    @Bean
    public Docket vertriebApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(FLUXPROVIDER_URL))
                .build()
                .groupName("flux-consumer-api")
                .apiInfo(bitcoinApiInfo());
    }
}
