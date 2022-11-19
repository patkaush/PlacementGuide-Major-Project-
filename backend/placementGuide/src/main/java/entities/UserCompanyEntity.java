package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserCompanyEntity {
  @Id
  @GeneratedValue
  private Integer dummyId;
  
  private Integer userId;
  
  private Integer companyId;
  
  public UserCompanyEntity() {}
  
  public UserCompanyEntity(Integer userId, Integer companyId) {
    this.userId = userId;
    this.companyId = companyId;
  }
  
  public Integer getDummyId() {
    return this.dummyId;
  }
  
  public void setDummyId(Integer dummyId) {
    this.dummyId = dummyId;
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
