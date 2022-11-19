package bodyReqRes;

public class ResourceReqBody {
  private Integer userId;
  
  private Integer companyId;
  
  private String resourceData;
  
  private String username;
  
  public String getResourceData() {
    return this.resourceData;
  }
  
  public void setResourceData(String resourceData) {
    this.resourceData = resourceData;
  }
  
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
}
