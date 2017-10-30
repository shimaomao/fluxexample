
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

import java.util.List;

import static ch.thwelly.springboot.flux.fluxprovider.controller.api.BlockingController.API_URL;

@Api(value = API_URL, description = "Data Providers without flux")
@RestController
@RequestMapping(API_URL)
public class BlockingController {

    final static String API_URL = "/api";


    @Autowired
    private ProviderService providerService;

    @ApiOperation(value = "Get PersonEntity by ID.", response = PersonEntity.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/{id}")
    public PersonEntity searchById(@PathVariable final long id) throws InterruptedException {
        return providerService.searchById(id);
    }

    @ApiOperation(value = "Get List of all PersonEntity.")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/all")
    public List<PersonEntity> getAll() throws InterruptedException {
        return providerService.getAll();
    }
}
