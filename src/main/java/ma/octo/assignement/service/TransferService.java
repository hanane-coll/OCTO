package ma.octo.assignement.service;

import lombok.extern.slf4j.Slf4j;
import ma.octo.assignement.domain.model.Compte;
import ma.octo.assignement.domain.model.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.TransferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static ma.octo.assignement.constants.constants.MONTANT_TRANSFER_MAXIMAL;
import static ma.octo.assignement.constants.constants.MONTANT_TRANSFER_MINIMAL;


@Service
@Slf4j
@Transactional
public class TransferService {

    private final AuditService auditService;

    private final TransferRepository transferRepository;

    private final CompteRepository compteRepository;

    public TransferService(AuditService auditService, TransferRepository transferRepository, CompteRepository compteRepository) {
        this.auditService = auditService;
        this.transferRepository = transferRepository;
        this.compteRepository = compteRepository;
    }

    public ResponseEntity<List<Transfer>> getTransfers() {
        log.info("Lister les transfers");
        var transfers = transferRepository.findAll();

        if (transfers.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(transfers);
        }
    }

    public ResponseEntity<Transfer> createTransfer(TransferDto transferDto) throws CompteNonExistantException, TransactionException, SoldeDisponibleInsuffisantException {
        log.info("Creation d'un nouveau tranfer");
        verifyTransferDto(transferDto);
        updateSolde(transferDto);
        Transfer transfer = saveTransfer(transferDto);

        return new ResponseEntity(transfer, HttpStatus.CREATED);
    }

    private void verifyTransferDto(TransferDto transferDto) throws CompteNonExistantException, TransactionException, SoldeDisponibleInsuffisantException {
        String nrEmetteur = transferDto.getNrCompteEmetteur();
        String nrBeneficiaire = transferDto.getNrCompteBeneficiaire();
        BigDecimal montant = transferDto.getMontant();
        String motif = transferDto.getMotif();
        Date date = transferDto.getDate();

        BigDecimal emetteurSolde = compteRepository.findByNrCompte(nrEmetteur).getSolde();
        BigDecimal beneficiaireMontant = transferDto.getMontant();

        if (nrEmetteur.equals(nrBeneficiaire)) {
            throw new TransactionException("Compte ne peut pas transférer de l'argent vers lui-même");
        }

        Compte emetteur = compteRepository.findByNrCompte(nrEmetteur);
        Compte beneficiaire = compteRepository.findByNrCompte(nrBeneficiaire);

        if (emetteur == null) {
            throw new CompteNonExistantException("Compte avec numero " + nrEmetteur + " est introuvable");
        }

        if (beneficiaire == null) {
            throw new CompteNonExistantException("Compte avec numero " + nrBeneficiaire + " est introuvable");
        }

        if (montant == null ||
                montant.intValue() == 0) {
            throw new TransactionException("Montant vide");
        } else if (montant.intValue() < MONTANT_TRANSFER_MINIMAL) {
            throw new TransactionException("Montant minimal de transfer non atteint");
        } else if (montant.intValue() > MONTANT_TRANSFER_MAXIMAL) {
            throw new TransactionException("Montant maximal de transfer dépassé");
        }

        if (motif == null ||
                motif.isEmpty()) {
            throw new TransactionException("Motif vide");
        } else if (motif.length() > 200) {
            throw new TransactionException("Motif est trop longue");
        }

        if (emetteurSolde.intValue() - beneficiaireMontant.intValue() < 0) {
            throw new SoldeDisponibleInsuffisantException();
        }

        if (date == null) {
            transferDto.setDate(new Date());
        }
    }


    private void updateSolde(TransferDto transferDto) {
        String nrEmetteur = transferDto.getNrCompteEmetteur();
        String nrBeneficiaire = transferDto.getNrCompteBeneficiaire();

        Compte emetteur = compteRepository.findByNrCompte(nrEmetteur);
        Compte beneficiaire = compteRepository.findByNrCompte(nrBeneficiaire);

        emetteur.setSolde(emetteur.getSolde().subtract(transferDto.getMontant()));
        compteRepository.save(emetteur);

        beneficiaire.setSolde(new BigDecimal(beneficiaire.getSolde().intValue() + transferDto.getMontant().intValue()));
        compteRepository.save(beneficiaire);
    }

    private Transfer saveTransfer(TransferDto transferDto) {
        String nrEmetteur = transferDto.getNrCompteEmetteur();
        String nrBeneficiaire = transferDto.getNrCompteBeneficiaire();

        Compte emetteur = compteRepository.findByNrCompte(nrEmetteur);
        Compte beneficiaire = compteRepository.findByNrCompte(nrBeneficiaire);

        Transfer transfer = new Transfer();
        transfer.setDateExecution(transferDto.getDate());
        transfer.setCompteBeneficiaire(beneficiaire);
        transfer.setCompteEmetteur(emetteur);
        transfer.setMontantTransfer(transferDto.getMontant());
        transfer.setMotifTransfer(transferDto.getMotif());

        auditService.auditTransfer(transferDto);
        return transferRepository.save(transfer);
    }


}
