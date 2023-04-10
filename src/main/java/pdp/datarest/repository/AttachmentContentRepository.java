package pdp.datarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.datarest.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

    Optional<AttachmentContent> findByAttachmentId(Integer id);
}

