package leo.henry.com.controller;

import leo.henry.com.model.Coffee;
import leo.henry.com.respository.CoffeeRepository;
import leo.henry.com.service.CoffeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@WebFluxTest({CoffeeController.class, CoffeeService.class})
public class CoffeeControllerTest {

    @Autowired
    private CoffeeController coffeeController;

    @MockBean
    private CoffeeRepository repository;

    @Autowired
    private WebTestClient client;

    private Coffee coffee;
    @Before
    public void setUp(){
        coffee = new Coffee("000-TEST-COFFEE-999","Tester's choice");
        Mockito.when(repository.findAll()).thenReturn(Flux.just(coffee));
        Mockito.when(repository.findById(coffee.getId())).thenReturn(Mono.just(coffee));
    }

    @Test
    public void getAllCoffees() {

        StepVerifier.withVirtualTime(() -> client.get()
        .uri("/coffees")
        .exchange()
        .returnResult(Coffee.class)
        .getResponseBody()
        .take(1))
                .expectNext(coffee)
                .verifyComplete();
    }

    @Test
    public void byId() {
        StepVerifier.withVirtualTime(() -> client.get()
                .uri("/coffees{id}",coffee.getId())
                .exchange()
                .returnResult(Coffee.class)
                .getResponseBody()
                .take(1))
                .expectNext(coffee)
                .verifyComplete();
    }

    @Test
    public void orders() {
    }
}