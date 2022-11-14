package ma.octo.assignement.domain.audit;

import lombok.Getter;
import lombok.Setter;
import ma.octo.assignement.constants.EventType;

import javax.persistence.*;

@Entity
@Table(name = "AUDIT_DEPOSIT")
@Getter
@Setter
public class AuditDeposit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 100)
  private String message;

  @Enumerated(EnumType.STRING)
  private EventType eventType;

}
