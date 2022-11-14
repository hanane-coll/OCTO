package ma.octo.assignement.service;

import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.MoneyDepositRepository;
import ma.octo.assignement.service.MoneyDepositService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class DepositServiceTest {
    @Autowired
    private MoneyDepositService depositService;
    @Autowired
    private MoneyDepositRepository depositRepository;
    @Autowired
    private CompteRepository compteRepository;

    @Test
    void shouldReturnNotFoundIfDepositsEmpty() {
        assertThat(depositService.getDeposits()).isEqualTo(ResponseEntity.notFound().build());
    }

    /*
    @Test
    void shouldThrowIfCompteNotExists() {
        assertThrows(CompteNonExistantException.class,
                () -> depositService.verifyBeneficiaire("RIB", null));
    }

    @Test
    void shouldThrowIfMontantIsNull() {
        assertThrows(TransactionException.class,
                () -> depositService.verifyMontant(null));
    }

    @Test
    void shouldThrowIfMontantIsZero() {
        assertThrows(TransactionException.class,
                () -> depositService.verifyMontant(BigDecimal.ZERO));
    }

    @Test
    void shouldThrowIfMontantIsBiggerThanMaximal() {
        assertThrows(TransactionException.class,
                () -> depositService.verifyMontant(BigDecimal.valueOf(MONTANT_DEPOSIT_MAXIMAL + 1)));
    }

    @Test
    void shouldThrowIfMontantIsSmallerThanMinimal() {
        assertThrows(TransactionException.class,
                () -> depositService.verifyMontant(BigDecimal.valueOf(MONTANT_DEPOSIT_MINIMAL - 1)));
    }

    @Test
    void shouldThrowIfMotifIsNull() {
        assertThrows(TransactionException.class,
                () -> depositService.verifyMotif(null));
    }

    @Test
    void shouldThrowIfMotifIsEmpty() {
        assertThrows(TransactionException.class,
                () -> depositService.verifyMotif(""));
    }

    @Test
    void shouldThrowIfNomPrenomEmetteurisNull() {
        assertThrows(TransactionException.class,
                () -> depositService.verifyNomPrenomEmetteur(null));
    }

    @Test
    void shouldThrowIfNomPrenomEmetteurisEmpty() {
        assertThrows(TransactionException.class,
                () -> depositService.verifyNomPrenomEmetteur(""));
    }

*/
}