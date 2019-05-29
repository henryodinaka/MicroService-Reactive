package leo.henry.com.com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder {
    private String  coffeId;
    private Date dateOrdered;
}
