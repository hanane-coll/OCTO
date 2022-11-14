package ma.octo.assignement.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MONEY_DEPOSIT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDeposit {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(precision = 16, scale = 2, nullable = false)
  private BigDecimal Montant;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateExecution;

  @Column
  private String nom_prenom_emetteur;

  @ManyToOne
  private Compte compteBeneficiaire;

  @Column(length = 200)
  private String motifDeposit;

}
