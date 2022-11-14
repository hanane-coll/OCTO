package ma.octo.assignement.service;

import lombok.extern.slf4j.Slf4j;

import ma.octo.assignement.constants.EventType;
import ma.octo.assignement.domain.audit.AuditDeposit;
import ma.octo.assignement.domain.audit.AuditTransfer;
import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.repository.AuditDepositRepository;
import ma.octo.assignement.repository.AuditTransferRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class AuditService {

    private final AuditTransferRepository auditTransferRepository;
    private final AuditDepositRepository auditDepositRepository;

    public AuditService(AuditTransferRepository auditTransferRepository, AuditDepositRepository auditDepositRepository) {
        this.auditTransferRepository = auditTransferRepository;
        this.auditDepositRepository = auditDepositRepository;
    }

    public void auditTransfer(TransferDto transferDto) {
        log.info("Audit de l'événement {}", EventType.TRANSFER);

        String message = "Transfer depuis " +
                transferDto.getNrCompteEmetteur() + " vers " +
                transferDto.getNrCompteBeneficiaire() + " d'un montant de " +
                transferDto.getMontant().toString() + " MAD";
        AuditTransfer audit = new AuditTransfer();
        audit.setEventType(EventType.TRANSFER);
        audit.setMessage(message);
        auditTransferRepository.save(audit);
    }

    public void auditDeposit(DepositDto depositDto) {
        log.info("Audit de l'événement {}", EventType.DEPOSIT);

        String message = "Virement vers le compte avec RIB " +
                depositDto.getRib() + " d'un montant de " +
                depositDto.getMontant() + " MAD par " + depositDto.getNomPrenomEmetteur().toUpperCase();
        AuditDeposit audit = new AuditDeposit();
        audit.setEventType(EventType.DEPOSIT);
        audit.setMessage(message);
        auditDepositRepository.save(audit);
    }
}
