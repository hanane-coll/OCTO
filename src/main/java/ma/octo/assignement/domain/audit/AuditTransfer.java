package ma.octo.assignement.domain.audit;

import lombok.Getter;
import lombok.Setter;
import ma.octo.assignement.constants.EventType;

import javax.persistence.*;

@Entity
@Table(name = "AUDIT_TRANSFER")
@Getter
@Setter
public class AuditTransfer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 100)
  private String message;

  @Enumerated(EnumType.STRING)
  private EventType eventType;

}
