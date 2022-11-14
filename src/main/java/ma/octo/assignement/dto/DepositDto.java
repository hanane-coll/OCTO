package ma.octo.assignement.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DepositDto {
    private String rib;
    private BigDecimal montant;
    private Date date;
    private String nomPrenomEmetteur;
    private String cinEmetteur;
    private String motif;

}
