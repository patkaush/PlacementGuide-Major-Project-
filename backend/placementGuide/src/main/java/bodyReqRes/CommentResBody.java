package bodyReqRes;

import entities.CommentEntity;
import java.util.Date;

public class CommentResBody {
  private Integer cid;
  
  private String date;
  
  private String data;
  
  private String username;
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public Integer getCId() {
    return this.cid;
  }
  
  public void setCid(Integer cid) {
    this.cid = cid;
  }
  
  public String getDate() {
    return this.date;
  }
  
  public void setDate(Date date) {
    this.date = date.toString();
  }
  
  public String getData() {
    return this.data;
  }
  
  public void setData(String data) {
    this.data = data;
  }
  
  public void autoWrap(CommentEntity entity) {
    this.cid = entity.getCId();
    this.data = entity.getData();
    this.date = entity.getDate().toString();
    this.username = entity.getUserName();
  }
}
