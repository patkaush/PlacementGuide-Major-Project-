package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Component
@Entity
public class JWTEntity {
  @Id
  private Integer userId;
  
  private String token;
  
  public JWTEntity() {}
  
  public JWTEntity(Integer id, String jwt) {
    this.token = jwt;
    this.userId = id;
  }
  
  public Integer getId() {
    return this.userId;
  }
  
  public void setId(Integer id) {
    this.userId = id;
  }
  
  public String getToken() {
    return this.token;
  }
  
  public void setToken(String token) {
    this.token = token;
  }
}
