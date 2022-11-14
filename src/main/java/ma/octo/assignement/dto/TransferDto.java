package ma.octo.assignement.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TransferDto {
  private String nrCompteEmetteur;
  private String nrCompteBeneficiaire;
  private String motif;
  private BigDecimal montant;
  private Date date;


}
