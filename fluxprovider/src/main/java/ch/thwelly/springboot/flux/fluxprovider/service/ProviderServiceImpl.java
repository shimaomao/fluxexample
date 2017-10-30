
package ch.thwelly.springboot.flux.fluxprovider.service;

import ch.thwelly.springboot.flux.fluxprovider.jpa.PersonEntity;
import ch.thwelly.springboot.flux.fluxprovider.jpa.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProviderServiceProperties providerServiceProperties;

    @Override
    public Flux<PersonEntity> getAllFlux() throws InterruptedException {
        waitAndKeepCalm();
        return Flux.fromIterable(personRepository.findAll());
    }

    @Override
    public Mono<PersonEntity> searchByIdFlux(final long id) throws InterruptedException {
        final Optional<PersonEntity> entityOptional = personRepository.findById(id);
        waitAndKeepCalm();
        return entityOptional.map(Mono::just).orElse(null);
    }

    @Override
    public List<PersonEntity> getAll() throws InterruptedException {
        waitAndKeepCalm();
        return personRepository.findAll();
    }

    @Override
    public PersonEntity searchById(final long id) throws InterruptedException {
        final Optional<PersonEntity> personEntity = personRepository.findById(id);
        waitAndKeepCalm();
        return personEntity.orElse(null);
    }

    private void waitAndKeepCalm() throws InterruptedException {
        if (providerServiceProperties != null && providerServiceProperties.isEnableTimeout()) {
            Thread.sleep(providerServiceProperties.getTimeout());
        }
    }
}
