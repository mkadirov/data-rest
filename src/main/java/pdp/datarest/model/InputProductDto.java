package pdp.datarest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDto {

    private int productId;
    private double amount;
    private double price;

    private Date expireDate;

    private int inputId;

}
