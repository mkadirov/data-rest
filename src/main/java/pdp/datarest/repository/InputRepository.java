package pdp.datarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.datarest.entity.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {
    boolean existsByFactureNumber(String factureNumber);
}
