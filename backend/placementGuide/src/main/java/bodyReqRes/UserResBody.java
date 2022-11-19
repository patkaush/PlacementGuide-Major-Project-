package bodyReqRes;

public class UserResBody {
  private String jwt_Token;
  
  private Integer id;
  
  private String admin;
  
  public UserResBody(Integer id, String token, String isadmin) {
    this.id = id;
    this.jwt_Token = token;
    this.admin = isadmin;
  }
  
  public String getToken() {
    return this.jwt_Token;
  }
  
  public Integer getId() {
    return this.id;
  }
  
  public String getAdmin() {
    return this.admin;
  }
}
