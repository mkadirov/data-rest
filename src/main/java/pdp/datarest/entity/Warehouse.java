package pdp.datarest.entity;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.datarest.template.AbsEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Warehouse  extends AbsEntity {

}
