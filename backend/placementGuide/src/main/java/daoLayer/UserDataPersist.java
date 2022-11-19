package daoLayer;

import bodyReqRes.UserReqBody;
import crudOperations.PersistLayer;
import entities.JWTEntity;
import entities.UserCompanyEntity;
import entities.UserEntity;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataPersist {
  @Autowired
  PersistLayer save;
  
  @Autowired
  UserEntity userRow;
  
  public Integer saveUserData(UserReqBody body) {
    this.userRow.setEmail(body.getEmail());
    this.userRow.setPassword(body.getPassword());
    this.userRow.setName(body.getUsername());
    this.userRow.setIsAdmin(body.getAdmin());
    System.out.println(this.userRow.getId());
    Integer Id = this.save.persistData(this.userRow);
    return Id;
  }
  
  public void deleteUserInterests(Integer userId, Integer compId) {
    this.save.deleteUserCompany(userId, compId);
  }
  
  public void saveJWTtoken(JWTEntity data) {
    this.save.saveToken(data);
  }
  
  public UserEntity getUserData(String username) {
    return this.save.getUserData(username);
  }
  
  public JWTEntity getToken(Integer id) {
    return this.save.getTokenData(id);
  }
  
  public void deleteToken(Integer id) {
    this.save.delTokenData(id);
  }
  
  public void addToFavourites(Integer userId, Integer companyId) {
    this.save.addUserCompanies(new UserCompanyEntity(userId, companyId));
  }
  
  public HashSet<Integer> getUserCompanies(Integer id) {
    return this.save.getUserCompanies(id);
  }
  
  public void deleteUser(int parseInt) {
    this.save.deleteUser(parseInt);
  }
}
