
package ch.thwelly.springboot.flux.fluxconsumer.controller.client;

import ch.thwelly.springboot.flux.fluxconsumer.config.ConsumerAppProperties;
import ch.thwelly.springboot.flux.fluxconsumer.model.PersonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * This Controller simulates the new FluxWeb in Spring 5.
 * The calls will not by blocked.
 */
@Api(value = API_URL, description = "Consumer with flux")
@RequestMapping(API_URL)
@RestController
public class FluxController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FluxController.class);

    final static String API_URL = "/client/flux";
    private static final String API_FLUX = "/api/flux/";

    @Autowired
    private ConsumerAppProperties consumerAppProperties;

    @Bean
    public WebClient webClient() {
        checkConditions();
        return WebClient.create(consumerAppProperties.getServerUrl());
    }

    /**
     * This call will get detail of person by ID
     *
     * @param id person id
     * @return detail of Person
     */
    @ApiOperation(value = "Get PersonEntity by ID. (Non Blocking Implementation.)")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/{id}")
    public Mono<PersonData> searchById(@PathVariable long id) {
        checkConditions();
        final String url = API_FLUX + Long.toString(id);
        LOGGER.debug(url);
        return this.webClient()
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PersonData.class);
    }

    /**
     * This call will merge 3 person together to demo a multi call in a micro service.
     *
     * @return merged persons
     */
    @ApiOperation(value = "This call simulate blocking call when more services called. (Micro-Services)")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/multi")
    public Flux<PersonData> getSomeUsers() {
        checkConditions();

        Mono<PersonData> test1 = this.webClient()
                .get()
                .uri(API_FLUX + Long.toString(1))
                .retrieve()
                .bodyToMono(PersonData.class);

        Mono<PersonData> test2 = this.webClient()
                .get()
                .uri(API_FLUX + Long.toString(3))
                .retrieve()
                .bodyToMono(PersonData.class);

        Mono<PersonData> test3 = this.webClient()
                .get()
                .uri(API_FLUX + Long.toString(4))
                .retrieve()
                .bodyToMono(PersonData.class);

        return Flux.merge(test1).mergeWith(test2).mergeWith(test3);
    }

    /**
     * This call get all person data
     *
     * @return list of person data
     */
    @ApiOperation(value = "Get a list of Flux<PersonEntity>. (Non Blocking Implementation.)")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/all")
    public Flux<PersonData> getAll() {
        checkConditions();

        return this.webClient()
                .get()
                .uri(API_FLUX + "/all")
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
