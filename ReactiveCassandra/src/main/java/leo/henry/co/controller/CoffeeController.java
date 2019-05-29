package leo.henry.co.controller;


import leo.henry.co.model.Coffee;
import leo.henry.co.respository.CoffeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CoffeeController {
    private final CoffeeRepository repository;

    public CoffeeController(CoffeeRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/coffees")
    public Flux<Coffee> getAllCoffees(){
        return repository.findAll();
    }
}
