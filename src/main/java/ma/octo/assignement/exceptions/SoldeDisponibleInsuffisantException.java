package ma.octo.assignement.exceptions;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class SoldeDisponibleInsuffisantException extends Exception {

  private static final long serialVersionUID = 1L;

  public SoldeDisponibleInsuffisantException(String message) {
    super(message);
    log.error(message);
  }
}
