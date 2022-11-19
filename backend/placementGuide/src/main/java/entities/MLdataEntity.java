package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MLdataEntity {
  @Id
  @GeneratedValue
  private Integer tempId;
  
  public Integer cId;
  
  private String cname;
  
  private String word;
  
  private Integer freq;
  
  public String getWord() {
    return this.word;
  }
  
  public void setWord(String word) {
    this.word = word;
  }
  
  public Integer getFreq() {
    return this.freq;
  }
  
  public void setFreq(Integer freq) {
    this.freq = freq;
  }
  
  public Integer getcId() {
    return this.cId;
  }
  
  public void setcId(Integer cId) {
    this.cId = cId;
  }
  
  public Integer getTempId() {
    return this.tempId;
  }
  
  public void setTempId(Integer tempId) {
    this.tempId = tempId;
  }
  
  public String getCname() {
    return this.cname;
  }
  
  public void setCname(String cname) {
    this.cname = cname;
  }
}
