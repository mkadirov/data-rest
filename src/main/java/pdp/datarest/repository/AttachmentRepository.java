package pdp.datarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.datarest.entity.Attachment;


public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}
