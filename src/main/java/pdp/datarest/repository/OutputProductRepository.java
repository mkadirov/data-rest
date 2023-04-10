package pdp.datarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.datarest.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
}
