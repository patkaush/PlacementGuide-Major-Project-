package exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler({UserException.class})
  public ResponseEntity<Object> exception(UserException ex) {
    return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler({UsernameNotFoundException.class})
  public ResponseEntity<Object> exception(UsernameNotFoundException ex) {
    return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
