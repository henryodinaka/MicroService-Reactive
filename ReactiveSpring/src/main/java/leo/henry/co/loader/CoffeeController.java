package leo.henry.co.loader;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    private final ReactiveRedisOperations<String,Coffee> coffeeOps;

    public CoffeeController(ReactiveRedisOperations<String, Coffee> coffeeOps) {
        this.coffeeOps = coffeeOps;
    }

    @GetMapping(value = "/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Coffee> getAll(){
        return coffeeOps.keys("*").flatMap(coffeeOps.opsForValue()::get)
                .delayElements(Duration.ofSeconds(1));
    }
}
