
package ch.thwelly.springboot.flux.fluxprovider.controller.api;

import ch.thwelly.springboot.flux.fluxprovider.jpa.PersonEntity;
import ch.thwelly.springboot.flux.fluxprovider.service.ProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static ch.thwelly.springboot.flux.fluxprovider.controller.api.FluxController.API_URL;

@Api(value = API_URL, description = "Data Providers with flux")
@RestController
@RequestMapping(API_URL)
public class FluxController {

    final static String API_URL = "/api/flux";

    @Autowired
    private ProviderService providerService;

    @ApiOperation(value = "Get PersonEntity by ID. (Non Blocking Implementation.)")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/{id}")
    public Mono<PersonEntity> searchById(@PathVariable final long id) throws InterruptedException {
        return providerService.searchByIdFlux(id);
    }

    @ApiOperation(value = "Get a list of Flux<PersonEntity>. (Non Blocking Implementation.)")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/all")
    public Flux<PersonEntity> getAll() throws InterruptedException {
        return providerService.getAllFlux();
    }
}
