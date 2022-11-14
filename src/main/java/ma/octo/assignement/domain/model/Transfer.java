package ma.octo.assignement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TRANSFER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(precision = 16, scale = 2, nullable = false)
  private BigDecimal montantTransfer;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateExecution;

  @ManyToOne
  private Compte compteEmetteur;

  @ManyToOne
  private Compte compteBeneficiaire;

  @Column(length = 200)
  private String motifTransfer;
}
