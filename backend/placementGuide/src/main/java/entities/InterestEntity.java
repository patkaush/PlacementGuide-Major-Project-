package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InterestEntity {
  @Id
  @GeneratedValue
  private Integer Id;
  
  private String Interest;
  
  private Integer gId;
  
  public Integer getgId() {
    return this.gId;
  }
  
  public void setgId(Integer gId) {
    this.gId = gId;
  }
  
  public Integer getId() {
    return this.Id;
  }
  
  public void setId(Integer id) {
    this.Id = id;
  }
  
  public String getInterest() {
    return this.Interest;
  }
  
  public InterestEntity() {}
  
  public InterestEntity(String interest, Integer gid) {
    this.gId = gid;
    this.Interest = interest;
  }
  
  public InterestEntity(Integer id, String interest, Integer gid) {
    this.Id = id;
    this.gId = gid;
    this.Interest = interest;
  }
  
  public void setInterest(String interest) {
    this.Interest = interest;
  }
}
