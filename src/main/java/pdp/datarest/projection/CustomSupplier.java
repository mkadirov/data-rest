package pdp.datarest.projection;

import org.springframework.data.rest.core.config.Projection;
import pdp.datarest.entity.Supplier;

@Projection(types = Supplier.class)
public interface CustomSupplier {

    public Integer getId();
    public String getName();

    public boolean getActive();

    public String getPhoneNumber();
}
