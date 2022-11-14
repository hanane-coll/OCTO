package ma.octo.assignement.controllers;

import ma.octo.assignement.domain.model.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "transfers")
public class TransferController {
    private final TransferService transferService;

    @Autowired
    TransferController(TransferService transferService) {
        this.transferService = transferService;
    }


    @GetMapping
    ResponseEntity<List<Transfer>> getTransfers() {
        return transferService.getTransfers();
    }

    @PostMapping
    public ResponseEntity<Transfer> createTransfer(@RequestBody TransferDto transferDto) throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
        return transferService.createTransfer(transferDto);
    }
}
