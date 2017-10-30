
package ch.thwelly.springboot.flux.fluxprovider.service;

import ch.thwelly.springboot.flux.fluxprovider.jpa.PersonEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProviderService {

    Flux<PersonEntity> getAllFlux() throws InterruptedException;

    Mono<PersonEntity> searchByIdFlux(long id) throws InterruptedException;

    List<PersonEntity> getAll() throws InterruptedException;

    PersonEntity searchById(long id) throws InterruptedException;
}
