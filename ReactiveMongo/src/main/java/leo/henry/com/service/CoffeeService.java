package leo.henry.com.service;

import leo.henry.com.com.CoffeeOrder;
import leo.henry.com.model.Coffee;
import leo.henry.com.respository.CoffeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
public class CoffeeService {

    private final CoffeeRepository repository;

    public CoffeeService(CoffeeRepository repository) {
        this.repository = repository;
    }
    public Flux<Coffee> findAll(){
    return repository.findAll();
    }

    public Mono<Coffee> byId(String id){
    return repository.findById(id);
    }

    public Flux<CoffeeOrder> events(String coffeeId){
        return Flux.<CoffeeOrder>generate(sink -> sink.next(new CoffeeOrder(coffeeId,new Date())))
                .delayElements(Duration.ofSeconds(1));
    }
}
