package bodyReqRes;

import entities.ResourceEntity;

public class ResourceResBody {
  private Integer rid;
  
  private String date;
  
  private String resourceData;
  
  private String username;
  
  public String getResourceData() {
    return this.resourceData;
  }
  
  public void setResourceData(String resourceData) {
    this.resourceData = resourceData;
  }
  
  public Integer getRid() {
    return this.rid;
  }
  
  public void setRid(Integer rid) {
    this.rid = rid;
  }
  
  public String getDate() {
    return this.date;
  }
  
  public void setDate(String date) {
    this.date = date;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public void autoWrap(ResourceEntity entity) {
    this.rid = entity.getrId();
    this.resourceData = entity.getResourceLink();
    this.date = entity.getDate().toString();
    this.username = entity.getUsername();
  }
}
