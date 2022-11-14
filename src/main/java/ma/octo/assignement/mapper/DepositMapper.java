package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.model.MoneyDeposit;
import ma.octo.assignement.dto.DepositDto;


public class DepositMapper {
    private static DepositDto depositDto;

    public static DepositDto map(MoneyDeposit deposit){
        depositDto = new DepositDto();
        depositDto.setDate(deposit.getDateExecution());
        depositDto.setNomPrenomEmetteur(deposit.getNom_prenom_emetteur());
        depositDto.setMotif(deposit.getMotifDeposit());
        depositDto.setMontant(deposit.getMontant());
        depositDto.setRib(deposit.getCompteBeneficiaire().getRib());

        return depositDto;

    }
}
