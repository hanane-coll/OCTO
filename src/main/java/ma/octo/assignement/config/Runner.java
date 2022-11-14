package ma.octo.assignement.config;

import ma.octo.assignement.domain.model.Compte;
import ma.octo.assignement.domain.model.MoneyDeposit;
import ma.octo.assignement.domain.model.Transfer;
import ma.octo.assignement.domain.model.Utilisateur;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.MoneyDepositRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

@Configuration
public class Runner implements CommandLineRunner {

    private final CompteRepository compteRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final TransferRepository transferRepository;
    private final MoneyDepositRepository moneyDepositRepository;

    public Runner(CompteRepository compteRepository, UtilisateurRepository utilisateurRepository, TransferRepository transferRepository,MoneyDepositRepository moneyDepositRepository) {
        this.compteRepository = compteRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.transferRepository = transferRepository;
        this.moneyDepositRepository = moneyDepositRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setUsername("user1");
        utilisateur1.setLastname("last1");
        utilisateur1.setFirstname("first1");
        utilisateur1.setGender("Male");

        utilisateurRepository.save(utilisateur1);


        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setUsername("user2");
        utilisateur2.setLastname("last2");
        utilisateur2.setFirstname("first2");
        utilisateur2.setGender("Female");

        utilisateurRepository.save(utilisateur2);

        Compte compte1 = new Compte();
        compte1.setNrCompte("010000A000001000");
        compte1.setRib("RIB1");
        compte1.setSolde(BigDecimal.valueOf(200000L));
        compte1.setUtilisateur(utilisateur1);

        compteRepository.save(compte1);

        Compte compte2 = new Compte();
        compte2.setNrCompte("010000B025001000");
        compte2.setRib("RIB2");
        compte2.setSolde(BigDecimal.valueOf(140000L));
        compte2.setUtilisateur(utilisateur2);

        compteRepository.save(compte2);

        Transfer v = new Transfer();
        v.setMontantTransfer(BigDecimal.TEN);
        v.setCompteBeneficiaire(compte2);
        v.setCompteEmetteur(compte1);
        v.setDateExecution(new Date());
        v.setMotifTransfer("Assignment 2021");

        transferRepository.save(v);

        MoneyDeposit d = new MoneyDeposit();
        d.setMontant(BigDecimal.TEN);
        d.setCompteBeneficiaire(compte2);
        d.setNom_prenom_emetteur("Hanane Yacoubi");
        d.setDateExecution(new Date());
        d.setMotifDeposit("Assignement 2021");

        moneyDepositRepository.save(d);
    }
}
