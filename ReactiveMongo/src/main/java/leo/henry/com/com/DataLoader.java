package leo.henry.com.com;


import leo.henry.com.model.Coffee;
import leo.henry.com.respository.CoffeeRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class DataLoader {
    private final CoffeeRepository repository;

    public DataLoader(CoffeeRepository repository) {
        this.repository = repository;
    }
    @PostConstruct
    private void load(){
        repository.deleteAll().thenMany(
        Flux.just("Kaldi Mongo","Lavazza mongo","Java mongo","Dear Green")
                .map(name -> new Coffee(UUID.randomUUID().toString(),name))
                .flatMap(repository::save))
                .thenMany(repository.findAll())
                .subscribe( System.out::println);
    }
}
