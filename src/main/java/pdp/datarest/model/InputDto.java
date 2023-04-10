package pdp.datarest.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDto {

    private Timestamp timestamp;
    private int warehouseId;

    private int supplierId;
    private int currencyId;
    private String factureNumber;
}
