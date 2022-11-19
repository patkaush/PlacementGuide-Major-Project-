package bodyReqRes;

public class CommentReqBody {
  private Integer userId;
  
  private Integer companyId;
  
  private String data;
  
  private String username;
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public Integer getUserId() {
    return this.userId;
  }
  
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
  
  public Integer getCompanyId() {
    return this.companyId;
  }
  
  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }
  
  public String getData() {
    return this.data;
  }
  
  public void setData(String data) {
    this.data = data;
  }
}
