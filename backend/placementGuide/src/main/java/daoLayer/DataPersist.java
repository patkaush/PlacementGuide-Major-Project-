package daoLayer;

import bodyReqRes.CommentResBody;
import bodyReqRes.CompCommResBody;
import bodyReqRes.CompaniesBody;
import bodyReqRes.CompanyBody;
import bodyReqRes.IntrValId;
import bodyReqRes.ResourceResBody;
import bodyReqRes.SetInterestReqBody;
import crudOperations.PersistLayer;
import entities.CommentEntity;
import entities.CompanyEntity;
import entities.GroupEntity;
import entities.InterestEntity;
import entities.MLdataEntity;
import entities.ResourceEntity;
import entities.UserEntity;
import entities.UserInterestEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataPersist {
  @Autowired
  PersistLayer persist;
  
  public void addCompanies(List<CompanyBody> companies) {
    for (CompanyBody company : companies) {
      CompanyEntity comp = new CompanyEntity();
      comp.autoWrap(company);
      Integer cid = this.persist.saveCompany(comp);
      for (CommentResBody comment : company.getComments()) {
        CommentEntity comm = new CommentEntity();
        comm.setCompanyId(cid);
        comm.setUserId(company.getUserId());
        comm.setUserName(company.getUsername());
        comm.setData(comment.getData());
        addComment(comm, Boolean.valueOf(false));
      } 
      for (ResourceResBody resource : company.getResources()) {
        ResourceEntity reso = new ResourceEntity();
        reso.setCompanyId(cid);
        reso.setResourceLink(resource.getResourceData());
        reso.setUserId(company.getUserId());
        reso.setUsername(company.getUsername());
        addResourceLink(reso);
      } 
    } 
  }
  
  public String getUsername(Integer id) {
    return this.persist.getUsername(id);
  }
  
  public List<UserEntity> getUsers() {
    return this.persist.getUsers();
  }
  
  public void saveChanges(CompanyEntity company) {
    this.persist.persistData(company);
  }
  
  public void saveInterest(InterestEntity data) {
    this.persist.saveInterest(data);
  }
  public List<InterestEntity> getInterests() {
    return this.persist.getInterests();
  }
  
  public void saveUserInterests(Integer userId, SetInterestReqBody interestIds) {
    HashSet<Integer> interests = interestIds.getInterests();
    HashMap<Integer,IntrValId> values = interestIds.getValues();
    for(int i =0 ; i < values.size() ; i++) {
    	if(values.get(i).id == null ) {
    		InterestEntity data = new InterestEntity(values.get(i).value ,9 );
    		values.get(i).id = this.persist.saveInterestTemp(data);
    	}
        this.persist.saveUserInterest(new UserInterestEntity(userId, values.get(i).id)); 
    }
//    for (Integer id : interests)
//      this.persist.saveUserInterest(new UserInterestEntity(userId, id)); 
  }
  
  public HashSet<Integer> getUserInterests(int userId) {
    return this.persist.getUserInterests(Integer.valueOf(userId));
  }
  
  public void removeUserInterests(Integer userId) {
    this.persist.removeUserInterests(userId);
  }
  
  public List<CompaniesBody> getCompanies() {
    return this.persist.getCompanies();
  }
  
  public CompanyEntity getCompanyDetails(Integer id) {
    return this.persist.getCompanyDetails(id);
  }
  
  public void addComment(CommentEntity entity, Boolean state) {
    this.persist.addComment(entity, state.booleanValue());
  }
  
  public List<CommentEntity> getComments(Integer id, Boolean limit) {
    return this.persist.getComments(id, limit);
  }
  
  public void addResourceLink(ResourceEntity entity) {
    this.persist.addResource(entity);
  }
  
  public List<ResourceEntity> getResourceLinks(Integer id) {
    return this.persist.getResources(id);
  }
  
  public void addGroupInterest(GroupEntity entity) {
    this.persist.addGroupInterest(entity);
  }
  
  public List<GroupEntity> getGroupInterests() {
    return this.persist.getGroupdInterests();
  }
  
  public void addMLData(MLdataEntity data) {
    this.persist.addMLData(data);
  }
  
  public List<MLdataEntity> getMLData() {
    return this.persist.getMLData();
  }
  
  public void delCompany(int cid) {
    this.persist.delCompany(Integer.valueOf(cid));
  }
  
  public void delComments(int cid) {
    this.persist.delComments(Integer.valueOf(cid));
  }
  
  public void delResources(int cid) {
    this.persist.delResources(Integer.valueOf(cid));
  }
  
  public void getUserData(Integer id) {
    getUserInterests(id.intValue());
  }
  
  public List<CompCommResBody> getCompaniesWithUserComments(Integer id) {
    return this.persist.getCompaniesWithUserComments(id);
  }
}
