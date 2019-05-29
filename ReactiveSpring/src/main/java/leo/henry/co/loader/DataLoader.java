package leo.henry.co.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
class DataLoader{
    @Autowired
    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, Coffee> coffeOps;

    public DataLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Coffee> coffeOps) {
        this.factory = factory;
        this.coffeOps = coffeOps;
    }
    @PostConstruct
    private void load(){
//        factory.getReactiveClusterConnection().serverCommands().flushAll().thenMany(
        Flux.just("jkjkjrk","jkfsjfr","fkjog","theser")
                .map(name ->new Coffee(UUID.randomUUID().toString(),name))
                .flatMap(coffee -> coffeOps.opsForValue().set(coffee.getId(),coffee))
                .thenMany(coffeOps.keys("*").flatMap(coffeOps.opsForValue()::get))
                .subscribe(System.out::println);
    }
}