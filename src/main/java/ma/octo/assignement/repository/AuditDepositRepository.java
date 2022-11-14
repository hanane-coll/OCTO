package ma.octo.assignement.repository;

import ma.octo.assignement.constants.EventType;
import ma.octo.assignement.domain.audit.AuditDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditDepositRepository extends JpaRepository<AuditDeposit, Long> {
    Optional<AuditDeposit> findByEventType(EventType e);
}
