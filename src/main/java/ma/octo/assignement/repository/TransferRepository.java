package ma.octo.assignement.repository;

import ma.octo.assignement.domain.model.Compte;
import ma.octo.assignement.domain.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findTransferByCompteBeneficiaire(Compte c);
    List<Transfer> findTransferByCompteEmetteur(Compte c);

}
