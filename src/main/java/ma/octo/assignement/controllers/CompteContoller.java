package ma.octo.assignement.controllers;

import ma.octo.assignement.domain.model.Compte;
import ma.octo.assignement.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "comptes")
public class CompteContoller {

    private final CompteService compteService;

    @Autowired
    public CompteContoller(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping
    ResponseEntity<List<Compte>> getComptes() {
        return compteService.getComptes();
    }


}
