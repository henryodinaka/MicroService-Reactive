package leo.henry.com.controller;


import leo.henry.com.com.CoffeeOrder;
import leo.henry.com.model.Coffee;
import leo.henry.com.respository.CoffeeRepository;
import leo.henry.com.service.CoffeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public Flux<Coffee> getAllCoffees(){
        return coffeeService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Coffee> byId(@PathVariable String id){
        return coffeeService.byId(id);
    }

    @GetMapping(value = "/{id}/orders", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CoffeeOrder> orders(@PathVariable String id){
        return coffeeService.events(id);
    }
}
