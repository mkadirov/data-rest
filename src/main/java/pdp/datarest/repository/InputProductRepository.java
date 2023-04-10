package pdp.datarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.datarest.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
}
