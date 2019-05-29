package leo.henry.com;

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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {CoffeeService.class})
@WebFluxTest(CoffeeService.class)
public class ReactiveMongoApplicationTests {

    @Autowired
    private CoffeeService coffeeService;

    @MockBean
    private CoffeeRepository repository;

    private Coffee coffee;

    @Before
    public void setup(){
        coffee = new Coffee("000-TEST-COFFEE-999","Tester's choice");
        Mockito.when(repository.findAll()).thenReturn(Flux.just(coffee));
        Mockito.when(repository.findById(coffee.getId())).thenReturn(Mono.just(coffee));
    }

    @Test
    public void findAll(){
        StepVerifier.withVirtualTime(() -> coffeeService.findAll())
                .expectNext(coffee)
                .verifyComplete();
    }

    @Test
    public void byId(){
        StepVerifier.withVirtualTime(() -> coffeeService.byId(coffee.getId()))
                .expectNext(coffee)
                .verifyComplete();
    }

    @Test
    public void getEventsTake() {
    String coffeeId = coffeeService.findAll().blockFirst().getId();
        StepVerifier.withVirtualTime(() -> coffeeService.events(coffeeId).take(10))
                .thenAwait(Duration.ofHours(1))
                .expectNextCount(10)
                .verifyComplete();
    }

}
