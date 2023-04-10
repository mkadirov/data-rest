package pdp.datarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.datarest.entity.Output;

public interface OutputRepository extends JpaRepository<Output,Integer> {
    boolean existsByFactureNumber(String factureNumber);
}
