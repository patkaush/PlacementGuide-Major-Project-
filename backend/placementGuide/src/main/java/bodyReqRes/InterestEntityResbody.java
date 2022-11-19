package bodyReqRes;

import entities.InterestEntity;
import java.util.HashSet;
import java.util.List;

public class InterestEntityResbody {
  private HashSet<Integer> userInterests;
  
  private List<InterestEntity> allInterests;
  
  public HashSet<Integer> getUserInterests() {
    return this.userInterests;
  }
  
  public void setUserInterests(HashSet<Integer> userInterests) {
    this.userInterests = userInterests;
  }
  
  public List<InterestEntity> getAllInterests() {
    return this.allInterests;
  }
  
  public void setAllInterests(List<InterestEntity> allInterests) {
    this.allInterests = allInterests;
  }
}
