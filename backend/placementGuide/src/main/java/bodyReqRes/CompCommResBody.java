package bodyReqRes;

import entities.CommentEntity;

public class CompCommResBody {
  private CommentEntity comments;
  
  private String CompanyName;
  
  private String date;
  
  public CommentEntity getComments() {
    return this.comments;
  }
  
  public void setComments(CommentEntity comments) {
    this.comments = comments;
    this.date = comments.getDate().toString();
  }
  
  public String getDate() {
    return this.date;
  }
  
  public void setDate(String date) {
    this.date = date;
  }
  
  public String getCompanyName() {
    return this.CompanyName;
  }
  
  public void setCompanyName(String companyName) {
    this.CompanyName = companyName;
  }
}
