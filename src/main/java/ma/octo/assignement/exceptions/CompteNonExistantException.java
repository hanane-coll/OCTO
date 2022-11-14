package ma.octo.assignement.exceptions;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class CompteNonExistantException extends Exception {

  private static final long serialVersionUID = 1L;

  public CompteNonExistantException(String message) {
    super(message);
    log.error(message);
  }
}
