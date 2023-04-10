package pdp.datarest.projection;

import org.springframework.data.rest.core.config.Projection;
import pdp.datarest.entity.Currency;
import pdp.datarest.repository.CurrencyRepository;

@Projection(types = Currency.class)
public interface CustomCurrency {

    public Integer getId();

    public String getName();

    public boolean getActive();
}
