package pdp.datarest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputProductDto {

    private int productId;
    private double amount;
    private double price;

    private int outputId;
}
