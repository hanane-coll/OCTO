package ma.octo.assignement.service;

import ma.octo.assignement.domain.model.Compte;
import ma.octo.assignement.repository.CompteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    public CompteService(CompteRepository compteRepository){
        this.compteRepository = compteRepository;
    }

    public ResponseEntity<List<Compte>> getComptes(){

        List<Compte> comptes = compteRepository.findAll();

        if(comptes.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(comptes);
        }

    }

}
