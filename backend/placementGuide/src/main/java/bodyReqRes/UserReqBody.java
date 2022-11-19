package bodyReqRes;

public class UserReqBody {
  private Integer id;
  
  private String username;
  
  private String password;
  
  private String email;
  
  private String admin;
  
  public Integer getId() {
    return this.id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getAdmin() {
    return this.admin;
  }
  
  public void setAdmin(String admin) {
    this.admin = admin;
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
}
