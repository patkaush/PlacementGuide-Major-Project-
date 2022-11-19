package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DataBody implements Comparable<DataBody> {
  private Integer cId;
  
  private String cName;
  
  private HashMap<String, Integer> intrSet = new HashMap<>();
  
  public Integer getcId() {
    return this.cId;
  }
  
  public void setcId(Integer cId) {
    this.cId = cId;
  }
  
  public String getcName() {
    return this.cName;
  }
  
  public void setcName(String cName) {
    this.cName = cName;
  }
  
  public HashMap<String, Integer> getIntrSet() {
    return this.intrSet;
  }
  
  public void setIntrSet(HashMap<String, Integer> intrSet) {
    this.intrSet = intrSet;
  }
  
  public int compareTo(DataBody o) {
    HashMap<String, Integer> a = this.intrSet;
    HashMap<String, Integer> b = o.getIntrSet();
    System.out.println("hello wolrd");
    if (a.size() != b.size())
      return (a.size() > b.size()) ? 1 : -1; 
    List<Integer> valA = new ArrayList<>();
    List<Integer> valB = new ArrayList<>();
    for (String val : a.keySet())
      valA.add(a.get(val)); 
    for (String val : b.keySet())
      valB.add(b.get(val)); 
    Collections.sort(valA);
    Collections.sort(valB);
    for (Integer i = Integer.valueOf(0); i.intValue() < valA.size(); i = Integer.valueOf(i.intValue() + 1)) {
      if (valA.get(i.intValue()) != valB.get(i.intValue()))
        return (((Integer)valA.get(i.intValue())).intValue() > ((Integer)valB.get(i.intValue())).intValue()) ? 1 : -1; 
    } 
    return 0;
  }
}
