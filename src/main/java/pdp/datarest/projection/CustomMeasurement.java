package pdp.datarest.projection;

import org.springframework.data.rest.core.config.Projection;
import pdp.datarest.entity.Measurement;

@Projection(types = Measurement.class)
public interface CustomMeasurement {

    public Integer getId();

    public String getName();

    public boolean getActive();

}
