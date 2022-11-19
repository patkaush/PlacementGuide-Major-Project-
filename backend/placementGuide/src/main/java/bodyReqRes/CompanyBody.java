package bodyReqRes;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.List;

@JsonFormat(with = {JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES})
public class CompanyBody {
  private Integer UserId;
  
  private String Username;
  
  private Integer Id;
  
  private String Name;
  
  private String rating;
  
  private String description;
  
  private String interview_difficulty;
  
  private List<CommentResBody> comments = new ArrayList<>();
  
  private String interview_duration;
  
  private List<ResourceResBody> resources = new ArrayList<>();
  
  public String getInterview_duration() {
    return this.interview_duration;
  }
  
  public void setInterview_duration(String interview_duration) {
    this.interview_duration = interview_duration;
  }
  
  public Integer getId() {
    return this.Id;
  }
  
  public CompanyBody() {}
  
  public CompanyBody(Integer id, String name, String rating, String description, String interview_difficulty, String interview_duration) {
    this.Id = id;
    this.Name = name;
    this.rating = rating;
    this.description = description;
    this.interview_difficulty = interview_difficulty;
    this.interview_duration = interview_duration;
  }
  
  public List<ResourceResBody> getResources() {
    return this.resources;
  }
  
  public void setResources(List<ResourceResBody> resources) {
    this.resources = resources;
  }
  
  public void setId(Integer id) {
    this.Id = id;
  }
  
  public String getName() {
    return this.Name;
  }
  
  public void setName(String name) {
    this.Name = name;
  }
  
  public String getRating() {
    return this.rating;
  }
  
  public void setRating(String rating) {
    this.rating = rating;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getInterview_difficulty() {
    return this.interview_difficulty;
  }
  
  public void setInterview_difficulty(String interview_difficulty) {
    this.interview_difficulty = interview_difficulty;
  }
  
  public List<CommentResBody> getComments() {
    return this.comments;
  }
  
  public void setComments(List<CommentResBody> comments) {
    this.comments = comments;
  }
  
  public Integer getUserId() {
    return this.UserId;
  }
  
  public void setUserId(Integer userId) {
    this.UserId = userId;
  }
  
  public String getUsername() {
    return this.Username;
  }
  
  public void setUsername(String username) {
    this.Username = username;
  }
}
