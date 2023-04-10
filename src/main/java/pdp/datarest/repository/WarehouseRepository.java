package pdp.datarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pdp.datarest.entity.Warehouse;
import pdp.datarest.projection.CustomWarehouse;

@RepositoryRestResource(path = "warehouse",collectionResourceRel = "list", excerptProjection = CustomWarehouse.class)
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
