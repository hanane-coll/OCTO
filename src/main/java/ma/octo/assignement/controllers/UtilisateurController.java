package ma.octo.assignement.controllers;

import ma.octo.assignement.domain.model.Compte;
import ma.octo.assignement.domain.model.Utilisateur;
import ma.octo.assignement.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    ResponseEntity<List<Utilisateur>> getUtilisateurs() {
        return utilisateurService.getUtilisateurs();
    }

    @GetMapping(path = "{utilisateurId}/comptes")
    ResponseEntity<List<Compte>> getUtilisateurComptes(
            @PathVariable("utilisateurId") Long utilisateurId) {
        return utilisateurService.getUtilisateurComptes(utilisateurId);
    }
}
