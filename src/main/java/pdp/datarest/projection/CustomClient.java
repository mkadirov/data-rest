package pdp.datarest.projection;

import org.springframework.data.rest.core.config.Projection;
import pdp.datarest.entity.Client;

@Projection(types = Client.class)
public interface CustomClient {

    public Integer getId();

    public String getName();

    public boolean getActive();

    public String getPhoneNumber();
}
