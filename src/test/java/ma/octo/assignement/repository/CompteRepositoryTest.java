package ma.octo.assignement.repository;

import ma.octo.assignement.domain.model.Compte;
import ma.octo.assignement.domain.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CompteRepositoryTest {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Test
    void shouldFindCompteByRib() {
        String rib = "RIB_TEST";
        Compte compte = new Compte();
        compte.setRib(rib);
        compteRepository.save(compte);
        Compte foundCompte = compteRepository.findByRib(rib);
        assertThat(foundCompte).isEqualTo(compte);
    }

    @Test
    void shouldFindComptesByUtilisateurId() {
        Compte compte1 = new Compte();
        compte1.setNrCompte("110000110");

        Compte compte2 = new Compte();
        compte2.setNrCompte("110002220");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setFirstname("F_NAME");
        utilisateur.setLastname("L_NAME");
        utilisateur.setGender("Male");
        utilisateur.setUsername("USERNAME");

        compte1.setUtilisateur(utilisateur);
        compte2.setUtilisateur(utilisateur);

        Long utilisateurId = utilisateurRepository.save(utilisateur).getId();

        compteRepository.saveAll(List.of(compte1, compte2));

        List<Compte> foundComptes = compteRepository.findComptesByUtilisateurId(utilisateurId);
        assertThat(foundComptes).isEqualTo(List.of(compte1, compte2));
    }

    @Test
    void shouldFindByNrCompte() {
        Compte compte1 = new Compte();
        String nrCompte1 = "1002110122210";
        compte1.setNrCompte(nrCompte1);

        Compte compte2 = new Compte();
        String nrCompte2 = "10099999999990";
        compte2.setNrCompte(nrCompte2);

        compteRepository.saveAll(List.of(compte1, compte2));

        assertThat(compteRepository.findByNrCompte(nrCompte1)).isEqualTo(compte1);
    }
}