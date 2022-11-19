package entities;

public class StudenEntity {
  private String name;
  
  private Integer id;
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Integer getId() {
    return this.id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public StudenEntity(String name, Integer id) {
    this.name = name;
    this.id = id;
  }
}
