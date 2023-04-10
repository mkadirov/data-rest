package pdp.datarest.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp timestamp;
    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Client clientId;

    @ManyToOne
    private Currency currency;

    private String factureNumber;
    @Column(nullable = false, unique = true)
    private String code;

}
