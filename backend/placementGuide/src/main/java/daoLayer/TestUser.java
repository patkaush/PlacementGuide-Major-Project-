package daoLayer;

import entities.CompanyEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUser {
  @Autowired
  TestCompany testData;
  
  private Integer Id = Integer.valueOf(1);
  
  private String userName = "testUser";
  
  private String password = "1234";
  
  public String[] userInterests = new String[] { "productBased", "coding", "network" };
  
  public Integer getId() {
    return this.Id;
  }
  
  public void setId(Integer id) {
    this.Id = id;
  }
  
  public String getUserName() {
    return this.userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String[] getCompanies() {
    List<CompanyEntity> temp = this.testData.getCompanyDetails();
    return null;
  }
}
