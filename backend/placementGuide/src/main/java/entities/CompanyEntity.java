package entities;

import bodyReqRes.CompanyBody;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CompanyEntity {
  @Id
  @GeneratedValue
  private Integer Id;
  
  private String Name;
  
  private String rating;
  
  @Column(columnDefinition = "TEXT")
  private String reviews;
  
  @Column(columnDefinition = "TEXT")
  private String description;
  
  @Column(columnDefinition = "TEXT")
  private String interview_difficulty;
  
  @Column(columnDefinition = "TEXT")
  private String interview_duration;
  
  public String getRating() {
    return this.rating;
  }
  
  public void setRating(String rating) {
    this.rating = rating;
  }
  
  public String getReviews() {
    return this.reviews;
  }
  
  public void setReviews(String reviews) {
    this.reviews = reviews;
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
  
  public String getInterview_duration() {
    return this.interview_duration;
  }
  
  public void setInterview_duration(String interview_duration) {
    this.interview_duration = interview_duration;
  }
  
  public CompanyEntity(String name, String rating, String reviews, String description, String interview_difficulty, String interview_duration) {
    this.Name = name;
    this.rating = rating;
    this.reviews = reviews;
    this.description = description;
    this.interview_difficulty = interview_difficulty;
    this.interview_duration = interview_duration;
  }
  
  public CompanyEntity() {}
  
  public Integer getId() {
    return this.Id;
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
  
  public void autoWrap(CompanyBody body) {
    this.Id = body.getId();
    this.Name = body.getName();
    this.rating = body.getRating();
    this.description = body.getDescription();
    this.interview_difficulty = body.getInterview_difficulty();
    this.interview_duration = body.getInterview_duration();
  }
}
