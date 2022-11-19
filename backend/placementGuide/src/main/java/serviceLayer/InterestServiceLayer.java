package serviceLayer;

import bodyReqRes.InterestEntityResbody;
import bodyReqRes.SetInterestReqBody;
import constants.UrlEndPoint;
import daoLayer.DataPersist;
import entities.GroupEntity;
import entities.InterestEntity;
import entities.MLdataEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.ParseComments;
import utils.RecommendUtil;

@Service
public class InterestServiceLayer {
  @Autowired
  DataPersist persist;
  
  @Autowired
  ParseComments comms;
  
  public InterestEntityResbody getInterests(String userId) {
    HashSet<Integer> userIntr = this.persist.getUserInterests(Integer.parseInt(userId));
    List<InterestEntity> allInterests = this.persist.getInterests();
    InterestEntityResbody res = new InterestEntityResbody();
    res.setAllInterests(allInterests);
    res.setUserInterests(userIntr);
    return res;
  }
  
  public void saveUserInterests(String userId, SetInterestReqBody interestIds) {
    this.persist.removeUserInterests(Integer.valueOf(Integer.parseInt(userId)));
    this.persist.saveUserInterests(Integer.valueOf(Integer.parseInt(userId)), interestIds);
    List<MLdataEntity> trainSet = this.persist.getMLData();
    if (trainSet.size() == 0) {
      this.comms.addCommentsInCompany();
      trainSet = this.persist.getMLData();
    } 
    List<InterestEntity> allInterests = this.persist.getInterests();
    HashSet<String> testData = new HashSet<>();
    HashSet<Integer> set = interestIds.getInterests();
    for (InterestEntity i : allInterests) {
      if (set.contains(i.getId()))
        testData.add(i.getInterest()); 
    } 
    UrlEndPoint.recData = (new RecommendUtil()).recommendCompanies(trainSet, testData);
  }
  
  public List<InterestEntity> getUserInterests(String userId) {
    HashSet<Integer> userIntr = this.persist.getUserInterests(Integer.parseInt(userId));
    List<InterestEntity> interests = this.persist.getInterests();
    List<InterestEntity> res = new ArrayList<>();
    for (InterestEntity intr : interests) {
      if (userIntr.contains(intr.getId()))
        res.add(intr); 
    } 
    return res;
  }
  
  public void addInterests(InterestEntity entity) {
    this.persist.saveInterest(entity);
  }
  
  public void saveGroupInterests(List<GroupEntity> entities) {
    for (GroupEntity entity : entities)
      this.persist.addGroupInterest(entity); 
  }
  
  public List<GroupEntity> getGroupInterests() {
    return this.persist.getGroupInterests();
  }
  
  public void addMLData(MLdataEntity data) {
    this.persist.addMLData(data);
  }
}
