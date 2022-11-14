package ma.octo.assignement.service;

import ma.octo.assignement.domain.model.Compte;
import ma.octo.assignement.domain.model.Utilisateur;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final CompteRepository compteRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository,CompteRepository compteRepository){
        this.utilisateurRepository = utilisateurRepository;
        this.compteRepository = compteRepository;
    }

    public ResponseEntity<List<Utilisateur>> getUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        if (utilisateurs.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(utilisateurs);
        }
    }

    public ResponseEntity<List<Compte>> getUtilisateurComptes(Long utilisateurId) {
        List<Compte> comptes = compteRepository.findComptesByUtilisateurId(utilisateurId);
        if (comptes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(comptes);
        }
    }
}
