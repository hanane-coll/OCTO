package ma.octo.assignement.controllers;

import ma.octo.assignement.domain.model.MoneyDeposit;
import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.MoneyDepositService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "deposits")
public class MoneyDepositController {

    private final MoneyDepositService moneyDepositService;

    @Autowired
    public MoneyDepositController(MoneyDepositService moneyDepositService) {
        this.moneyDepositService = moneyDepositService;
    }

    @GetMapping
    ResponseEntity<List<MoneyDeposit>> getDeposits() {return moneyDepositService.getDeposits();
    }

    @PostMapping
    ResponseEntity<MoneyDeposit> createDeposit(@RequestBody DepositDto depositDto) throws TransactionException, CompteNonExistantException {
        return moneyDepositService.createDeposit(depositDto);
    }
}
