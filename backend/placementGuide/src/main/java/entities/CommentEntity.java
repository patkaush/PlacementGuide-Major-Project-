package entities;

import bodyReqRes.CommentReqBody;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class CommentEntity {
  @Id
  @GeneratedValue
  private Integer CId;
  
  @Temporal(TemporalType.DATE)
  private Date date;
  
  private String userName;
  
  private Integer UserId;
  
  private Integer CompanyId;
  
  @Column(columnDefinition = "TEXT")
  private String Data;
  
  @Transient
  private String strDate;
  
  public String getStrDate() {
    return this.strDate;
  }
  
  public void setStrDate(String strDate) {
    this.strDate = strDate;
  }
  
  @PrePersist
  protected void onCreate() {
    if (this.date == null)
      this.date = new Date(); 
  }
  
  public Integer getCId() {
    return this.CId;
  }
  
  public void setCId(Integer cId) {
    this.CId = cId;
  }
  
  public Integer getUserId() {
    return this.UserId;
  }
  
  public void setUserId(Integer userId) {
    this.UserId = userId;
  }
  
  public Integer getCompanyId() {
    return this.CompanyId;
  }
  
  public void setCompanyId(Integer companyId) {
    this.CompanyId = companyId;
  }
  
  public String getData() {
    return this.Data;
  }
  
  public void setData(String data) {
    this.Data = data;
  }
  
  public Date getDate() {
    if (this.date != null)
      this.strDate = this.date.toString(); 
    return this.date;
  }
  
  public void setDate(Date date) {
    this.date = date;
    if (date != null)
      this.strDate = date.toString(); 
  }
  
  public String getUserName() {
    return this.userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public void autoWrap(CommentReqBody body) {
    this.UserId = body.getUserId();
    this.CompanyId = body.getCompanyId();
    this.Data = body.getData();
    this.userName = body.getUsername();
  }
}
