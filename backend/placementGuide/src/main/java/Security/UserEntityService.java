package Security;

import daoLayer.UserDataPersist;
import entities.UserEntity;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserEntityService implements UserDetailsService {
  @Autowired
  UserDataPersist data;
  
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity uData = this.data.getUserData(username);
    if (uData == null)
      throw new UsernameNotFoundException("invlaid user"); 
    return (UserDetails)new User(uData.getName(), "{bcrypt}" + uData.getPassword(), new ArrayList());
  }
}
