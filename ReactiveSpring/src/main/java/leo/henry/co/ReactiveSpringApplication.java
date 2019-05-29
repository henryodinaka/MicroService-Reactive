package leo.henry.co;

import leo.henry.co.loader.Coffee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class ReactiveSpringApplication {

    @Bean
    ReactiveRedisOperations<String,Coffee> redisOperations (ReactiveRedisConnectionFactory factory){
        Jackson2JsonRedisSerializer <Coffee> serializer = new Jackson2JsonRedisSerializer<>(Coffee.class);
        RedisSerializationContext.RedisSerializationContextBuilder builder = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String , Coffee> context = builder.value(serializer).build();
        return new ReactiveRedisTemplate<>(factory,context);
    }
    public static void main(String[] args) {
        SpringApplication.run(ReactiveSpringApplication.class, args);
    }

}

