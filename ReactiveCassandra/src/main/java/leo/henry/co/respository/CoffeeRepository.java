package leo.henry.co.respository;


import leo.henry.co.model.Coffee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CoffeeRepository extends ReactiveCrudRepository<Coffee,String> {

}
