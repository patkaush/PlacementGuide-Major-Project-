package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Component
@Entity
public class UserInterestEntity {
  @Id
  @GeneratedValue
  private Integer dummyId;
  
  private Integer UserId;
  
  private Integer InterestId;
  
  public UserInterestEntity(Integer id1, Integer id2) {
    this.UserId = id1;
    this.InterestId = id2;
  }
  
  public UserInterestEntity() {}
  
  public Integer getUserId() {
    return this.UserId;
  }
  
  public void setUserId(Integer userId) {
    this.UserId = userId;
  }
  
  public Integer getInterestId() {
    return this.InterestId;
  }
  
  public void setInterestId(Integer interestId) {
    this.InterestId = interestId;
  }
}
