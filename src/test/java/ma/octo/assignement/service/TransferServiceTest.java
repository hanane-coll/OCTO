package ma.octo.assignement.service;

import ma.octo.assignement.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransferServiceTest {

    @Autowired
    private TransferService transferService;

    /*
    @Test
    void shouldThrowIfEmetteurIsBeneficiaire() {
        assertThrows(TransactionException.class,
                () -> transferService.verifyEmetteurAndBeneficiare(
                        "1", "1"));
    }

    @Test
    void shouldThrowIfEmetteurNotExists() {
        Compte beneficiaire = new Compte();
        beneficiaire.setSolde(BigDecimal.valueOf(1000));
        String nrBeneficiaire = "Nr1";
        beneficiaire.setNrCompte(nrBeneficiaire);
        beneficiaire.setRib("RIB1");

        String NrEmetteurNonExistant = "NrEmetteur";

        assertThrows(CompteNonExistantException.class,
                () -> transferService.
                        verifyEmetteurAndBeneficiare(NrEmetteurNonExistant, nrBeneficiaire));
    }

    @Test
    void shouldThrowIfBeneficiaireNotExists() {
        Compte beneficiaire = new Compte();
        beneficiaire.setSolde(BigDecimal.valueOf(1000));
        String nrEmetteur = "Nr1";
        beneficiaire.setNrCompte(nrEmetteur);
        beneficiaire.setRib("RIB1");

        String NrBeneficiareNonExistant = "NrBeneficiaire";

        assertThrows(CompteNonExistantException.class,
                () -> transferService.
                        verifyEmetteurAndBeneficiare(NrBeneficiareNonExistant, nrEmetteur));
    }

    @Test
    void shouldThrowIfMontantIsNull() {
        assertThrows(TransactionException.class,
                () -> transferService.verifyMontant(null));
    }

    @Test
    void shouldThrowIfMontantIsZero() {
        assertThrows(TransactionException.class,
                () -> transferService.verifyMontant(BigDecimal.ZERO));
    }

    @Test
    void shouldThrowIfMontantIsBiggerThanMaximal() {
        assertThrows(TransactionException.class,
                () -> transferService.verifyMontant(BigDecimal.valueOf(MONTANT_TRANSFER_MAXIMAL + 1)));
    }

    @Test
    void shouldThrowIfMontantIsSmallerThanMinimal() {
        assertThrows(TransactionException.class,
                () -> transferService.verifyMontant(BigDecimal.valueOf(MONTANT_TRANSFER_MINIMAL - 1)));
    }


    @Test
    void shouldThrowIfMotifIsNull() {
        assertThrows(TransactionException.class,
                () -> transferService.verifyMotif(null));
    }

    @Test
    void shouldThrowIfMotifIsEmpty() {
        assertThrows(TransactionException.class,
                () -> transferService.verifyMotif(""));
    }

    @Test
    void verifyEmetteurSolde() {
        // Emetteur sending 10 while having only 1
        assertThrows(SoldeDisponibleInsuffisantException.class,
                () -> transferService.verifyEmetteurSolde(BigDecimal.ONE, BigDecimal.TEN));
    }

     */
}