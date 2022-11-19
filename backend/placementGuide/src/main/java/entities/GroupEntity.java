package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GroupEntity {
  @Id
  private Integer gId;
  
  private String name;
  
  public Integer getgId() {
    return this.gId;
  }
  
  public void setgId(Integer gId) {
    this.gId = gId;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
}
