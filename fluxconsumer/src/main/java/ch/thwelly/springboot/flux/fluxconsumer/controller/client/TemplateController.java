
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static ch.thwelly.springboot.flux.fluxconsumer.controller.client.TemplateController.API_URL;

/**
 * This controller simulates the common rest controller used in spring 4
 * The calls will by blocked when the service is busy.
 */
@Api(value = API_URL, description = "Consumer without flux")
@RequestMapping(API_URL)
@RestController
public class TemplateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateController.class);

    final static String API_URL = "/client";
    private static final String API = "/api/";
    private final ExecutorService executorService;

    public TemplateController() {
        this.executorService = new ThreadPoolExecutor(3, 3, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100));
    }

    @Autowired
    private ConsumerAppProperties consumerAppProperties;

    @Bean
    public RestTemplate getRestClient() {
        checkConditions();
        return new RestTemplate();
    }


    @ApiOperation(value = "Get PersonEntity by ID. (Normal Implementation.)")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/{id}")
    public PersonData searchPersonById(@PathVariable long id) {
        final String url = consumerAppProperties.getServerUrl() + API + Long.toString(id);
        LOGGER.debug(url);
        ResponseEntity<PersonData> responseEntity = this.getRestClient()
                .getForEntity(url, PersonData.class, MediaType.APPLICATION_JSON);
        if (responseEntity.hasBody()) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }

    @ApiOperation(value = "Get a list of Flux<PersonEntity>. (Normal Implementation.)")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/all")
    public List<PersonData> getAll() {
        final String url = consumerAppProperties.getServerUrl() + API + "all";
        LOGGER.debug(url);
        ResponseEntity<PersonData[]> persons = this.getRestClient().getForEntity(url, PersonData[].class, MediaType.APPLICATION_JSON);
        if (persons.hasBody() && persons.getBody() != null) {
            return Arrays.asList(persons.getBody());
        } else {
            return new ArrayList<>();
        }
    }

    @ApiOperation(value = "This call simulate blocking call when more services called. (Micro-Services)")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/multi")
    public List<PersonData> getSomeUsers() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        CopyOnWriteArrayList<PersonData> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        getPersonDetailThread(countDownLatch, copyOnWriteArrayList, 1);
        getPersonDetailThread(countDownLatch, copyOnWriteArrayList, 2);
        getPersonDetailThread(countDownLatch, copyOnWriteArrayList, 3);

        countDownLatch.await(60, TimeUnit.SECONDS);

        return copyOnWriteArrayList;
    }

    private void getPersonDetailThread(CountDownLatch countDownLatch, CopyOnWriteArrayList<PersonData> copyOnWriteArrayList, long id) {
        executorService.submit(() -> {
            final String url = consumerAppProperties.getServerUrl() + API + Long.toString(id);
            ResponseEntity<PersonData> responseEntity = this.getRestClient().getForEntity(url, PersonData.class, MediaType.APPLICATION_JSON);
            if (responseEntity.hasBody()) {
                copyOnWriteArrayList.add(responseEntity.getBody());
            }
            countDownLatch.countDown();
        });
    }

    private void checkConditions() {
        if (consumerAppProperties == null || consumerAppProperties.getProviderServer() == null) {
            LOGGER.error("Error on read consumer configuration.");
            throw new RuntimeException("consumer-client config not set in application properties.");
        }
    }
}
