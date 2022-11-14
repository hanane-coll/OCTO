package ma.octo.assignement.repository;
import ma.octo.assignement.domain.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, Long> {
  Compte findByNrCompte(String nrCompte);

  Compte findByRib(String rib);

  List<Compte> findComptesByUtilisateurId(Long utilisateurId);
}
