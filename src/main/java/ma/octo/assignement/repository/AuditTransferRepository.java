package ma.octo.assignement.repository;

import ma.octo.assignement.constants.EventType;
import ma.octo.assignement.domain.audit.AuditTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditTransferRepository extends JpaRepository<AuditTransfer,Long> {
    Optional<AuditTransfer> findByEventType(EventType e);
}
