
package ch.thwelly.springboot.flux.fluxconsumer.controller.client;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ch.thwelly.springboot.flux.fluxconsumer.controller.client.TemplateController.API_URL;

@Api(value = API_URL, description = "Consumer without flux")
@RequestMapping(API_URL)
@RestController
public class TemplateController {

    final static String API_URL = "/client";

}
