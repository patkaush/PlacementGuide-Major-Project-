package entities;

import bodyReqRes.ResourceReqBody;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ResourceEntity {
  @Id
  @GeneratedValue
  private Integer rId;
  
  private Integer userId;
  
  private Integer companyId;
  
  private String username;
  
  private String resourceLink;
  
  @Temporal(TemporalType.DATE)
  private Date date;
  
  @PrePersist
  protected void onCreate() {
    if (this.date == null)
      this.date = new Date(); 
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public Date getDate() {
    return this.date;
  }
  
  public void setDate(Date date) {
    this.date = date;
  }
  
  public Integer getrId() {
    return this.rId;
  }
  
  public void setrId(Integer rId) {
    this.rId = rId;
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
  
  public String getResourceLink() {
    return this.resourceLink;
  }
  
  public void setResourceLink(String resourceLink) {
    this.resourceLink = resourceLink;
  }
  
  public void autoWrap(ResourceReqBody body) {
    this.userId = body.getUserId();
    this.companyId = body.getCompanyId();
    this.resourceLink = body.getResourceData();
    this.username = body.getUsername();
  }
}
