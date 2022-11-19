package bodyReqRes;

import entities.CommentEntity;
import java.util.HashMap;
import java.util.List;

public class UserProfileResBody {
  private List<CommentEntity> comments;
  
  private String username;
  
  private List<String> interests;
  
  private HashMap<Integer, String> favourites;
  
  private List<CompCommResBody> companyData;
  
  public List<String> getInterests() {
    return this.interests;
  }
  
  public List<CommentEntity> getComments() {
    return this.comments;
  }
  
  public void setComments(List<CommentEntity> comments) {
    this.comments = comments;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public void setInterests(List<String> interests) {
    this.interests = interests;
  }
  
  public HashMap<Integer, String> getFavourites() {
    return this.favourites;
  }
  
  public void setFavourites(HashMap<Integer, String> favourites) {
    this.favourites = favourites;
  }
  
  public List<CompCommResBody> getCompanyData() {
    return this.companyData;
  }
  
  public void setCompanyData(List<CompCommResBody> companyData) {
    this.companyData = companyData;
  }
}
