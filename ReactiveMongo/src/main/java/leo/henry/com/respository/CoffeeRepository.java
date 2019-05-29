package leo.henry.com.respository;


import leo.henry.com.model.Coffee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CoffeeRepository extends ReactiveCrudRepository<Coffee,String> {

}
