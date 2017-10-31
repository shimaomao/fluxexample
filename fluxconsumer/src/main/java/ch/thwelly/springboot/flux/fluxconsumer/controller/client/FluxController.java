
package ch.thwelly.springboot.flux.fluxconsumer.controller.client;

import ch.thwelly.springboot.flux.fluxconsumer.config.ConsumerAppProperties;
import ch.thwelly.springboot.flux.fluxconsumer.model.PersonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static ch.thwelly.springboot.flux.fluxconsumer.controller.client.FluxController.API_URL;

@Api(value = API_URL, description = "Consumer with flux")
@RequestMapping(API_URL)
@RestController
public class FluxController {

    final static String API_URL = "/client/flux";

    @Autowired
    private ConsumerAppProperties consumerAppProperties;

    @Bean
    public WebClient webClient() {
        checkConditions();
        return WebClient.create(consumerAppProperties.getServerUrl());
    }

    @ApiOperation(value = "Get PersonEntity by ID. (Non Blocking Implementation.)")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/{id}")
    public Mono<PersonData> searchById(@PathVariable long id) {
        checkConditions();

        return this.webClient()
                .get()
                .uri("/api/flux/" + Long.toString(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PersonData.class);
    }

    @ApiOperation(value = "Get a list of Flux<PersonEntity>. (Non Blocking Implementation.)")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/all")
    public Flux<PersonData> getAll() {
        checkConditions();

        return this.webClient()
                .get()
                .uri("/api/flux/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(PersonData.class);
    }

    private void checkConditions() {
        if (consumerAppProperties == null || consumerAppProperties.getProviderServer() == null) {
            throw new RuntimeException("consumer-client config not set in application properties.");
        }
    }
}
