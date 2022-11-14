package ma.octo.assignement.repository;



import ma.octo.assignement.domain.model.Compte;
import ma.octo.assignement.domain.model.MoneyDeposit;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.MoneyDepositRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MoneyDepositRepositoryTest {

    @Autowired
    private MoneyDepositRepository moneyDepositRepository;

    @Autowired
    private CompteRepository compteRepository;


    @Test
    public void should_Find_All_deposit() {
        Compte c1 = new Compte();
        Compte c2 = new Compte();
        Compte c3 = new Compte();
        compteRepository.save(c1);
        compteRepository.save(c2);
        compteRepository.save(c3);
        MoneyDeposit Md1 = new MoneyDeposit(1L, BigDecimal.TEN, new Date(), "nom_prenom test 1 ", c1, "Depot test 1 ");
        moneyDepositRepository.save(Md1);

        MoneyDeposit Md2 = new MoneyDeposit(2L, BigDecimal.TEN, new Date(), "nom_prenom test 2 ", c2, "Depot test 2 ");
        moneyDepositRepository.save(Md2);

        MoneyDeposit Md3 = new MoneyDeposit(3L, BigDecimal.TEN, new Date(), "nom_prenom test 3 ", c3, "Depot test 3 ");

        moneyDepositRepository.save(Md3);

        List<MoneyDeposit> tutorials = moneyDepositRepository.findAll();
        Assertions.assertThat(tutorials).hasSizeGreaterThanOrEqualTo(3);


    }

    @Test
    public void Should_store_a_deposit_Test() throws CompteNonExistantException {
        Compte c = new Compte();
        compteRepository.save((c));
        MoneyDeposit moneyDepo = new MoneyDeposit(1L, BigDecimal.TEN, new Date(), "nom_prenom test 1 ", c, "Depot test 1 ");

        MoneyDeposit saved = moneyDepositRepository.save(moneyDepo);
        assertNotNull(saved);


    }

    @Test
    public void should_delete_a_deposit_by_id() {
        Compte c = new Compte();
        compteRepository.save(c);
        MoneyDeposit moneyDepo = new MoneyDeposit(1L, BigDecimal.TEN, new Date(),
                "nom_prenom test 1 ", c, "Depot test 1 ");
        MoneyDeposit m1= moneyDepositRepository.save(moneyDepo);

        moneyDepositRepository.deleteById(m1.getId());
        Optional<MoneyDeposit> m2 = moneyDepositRepository.findById(moneyDepo.getId());

        assertTrue(m2.isEmpty());


    }

}
