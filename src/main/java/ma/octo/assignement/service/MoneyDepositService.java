package ma.octo.assignement.service;

import ma.octo.assignement.domain.model.Compte;
import ma.octo.assignement.domain.model.MoneyDeposit;
import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.MoneyDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static ma.octo.assignement.constants.constants.MONTANT_DEPOSIT_MAXIMAL;
import static ma.octo.assignement.constants.constants.MONTANT_DEPOSIT_MINIMAL;

@Service
@Transactional
public class MoneyDepositService {
    private final MoneyDepositRepository moneyDepositRepository;
    private final CompteRepository compteRepository;
    private final AuditService auditService;

    @Autowired
    public MoneyDepositService(MoneyDepositRepository moneyDepositRepository, CompteRepository compteRepository, AuditService auditService){
        this.moneyDepositRepository = moneyDepositRepository;
        this.compteRepository = compteRepository;
        this.auditService = auditService;
    }
    public ResponseEntity<List<MoneyDeposit>> getDeposits() {
        List<MoneyDeposit> deposits = moneyDepositRepository.findAll();

        if (deposits.isEmpty()) {
            return (ResponseEntity<List<MoneyDeposit>>) deposits;
        } else {
            return ResponseEntity.ok(deposits);
        }
    }

    public ResponseEntity<MoneyDeposit> createDeposit(DepositDto depositDto) throws TransactionException, CompteNonExistantException {
        verifyDepositDto(depositDto);
        updateSolde(depositDto);
        MoneyDeposit deposit = saveDeposit(depositDto);
        return new ResponseEntity<MoneyDeposit>(deposit, HttpStatus.CREATED);
    }

    public void verifyDepositDto(DepositDto depositDto) throws CompteNonExistantException, TransactionException {
        String rib = depositDto.getRib();
        Compte beneficiaire = compteRepository.findByRib(rib);
        BigDecimal montant = depositDto.getMontant();
        String motif = depositDto.getMotif();
        String nomPrenomEmetteur = depositDto.getNomPrenomEmetteur();
        Date date = depositDto.getDate();

        if (beneficiaire == null) {
            throw new CompteNonExistantException("Compte avec rib " + rib + " est introuvable");
        }

        if (montant == null || montant.doubleValue() == 0) {
            throw new TransactionException("Montant vide");
        } else if (montant.doubleValue() < MONTANT_DEPOSIT_MINIMAL) {
            throw new TransactionException("Montant minimal de transfer non atteint");
        } else if (montant.doubleValue() > MONTANT_DEPOSIT_MAXIMAL) {
            throw new TransactionException("Montant maximal de transfer dépassé");
        }

        if (motif == null || motif.isEmpty()) {
            throw new TransactionException("Motif vide");
        } else if (motif.length() > 200) {
            throw new TransactionException("Motif est trop longue");
        }

        if (nomPrenomEmetteur == null ||
                nomPrenomEmetteur.isEmpty()) {
            throw new TransactionException("Nom et Prénom de l'emetteur sont vides");
        }

        if (date == null) {
            depositDto.setDate(new Date());
        }

        if (date == null) {
            depositDto.setDate(new Date());
        }
    }

    private void updateSolde(DepositDto depositDto) {
        Compte beneficiaire = compteRepository.findByRib(depositDto.getRib());
        beneficiaire.setSolde(beneficiaire.getSolde().add(depositDto.getMontant()));
    }

    private MoneyDeposit saveDeposit(DepositDto depositDto) {
        Compte beneficiaire = compteRepository.findByRib(depositDto.getRib());

        compteRepository.save(beneficiaire);

        MoneyDeposit moneyDeposit = new MoneyDeposit();

        moneyDeposit.setDateExecution(new Date());
        moneyDeposit.setMontant(depositDto.getMontant());
        moneyDeposit.setMotifDeposit(depositDto.getMotif());
        moneyDeposit.setCompteBeneficiaire(beneficiaire);
        moneyDeposit.setNom_prenom_emetteur(depositDto.getNomPrenomEmetteur());

        auditService.auditDeposit(depositDto);
        return moneyDepositRepository.save(moneyDeposit);
    }
}
