package exceptionHandler;

import org.springframework.security.authentication.BadCredentialsException;

public class UserException extends Exception {
  public UserException(String msg) {
    super(msg);
  }
  
  public UserException(String string, BadCredentialsException e) {
    super(string, (Throwable)e);
  }
}
