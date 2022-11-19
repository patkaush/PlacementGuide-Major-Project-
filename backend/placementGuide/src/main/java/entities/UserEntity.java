package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

@Component
@Entity
public class UserEntity {
  @Id
  @GeneratedValue
  private Integer Id;
  
  @ColumnDefault("false")
  private String isAdmin;
  
  private String Name;
  
  private String password;
  
  private String email;
  
  public String getIsAdmin() {
    return this.isAdmin;
  }
  
  public void setIsAdmin(String isAdmin) {
    this.isAdmin = isAdmin;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public Integer getId() {
    return this.Id;
  }
  
  public void setId(Integer id) {
    this.Id = id;
  }
  
  public String getName() {
    return this.Name;
  }
  
  public void setName(String name) {
    this.Name = name;
  }
}
