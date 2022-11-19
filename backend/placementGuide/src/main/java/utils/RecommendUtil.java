package utils;

import entities.MLdataEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RecommendUtil {
  private double euclideanDistance(int x1, int y1, int x2, int y2) {
    double x1x2 = Math.abs(x1 - x2);
    double y1y2 = Math.abs(y1 - y2);
    return Math.hypot(x1x2, y1y2);
  }
  
  private HashMap<Integer, WrapMLData> separateDataSet(List<MLdataEntity> trainData) {
    HashMap<Integer, WrapMLData> companies = new HashMap<>();
    HashSet<Integer> visited = new HashSet<>();
    for (MLdataEntity entity : trainData) {
      if (companies.containsKey(entity.getcId())) {
        WrapMLData wrapMLData = companies.get(entity.getcId());
        wrapMLData.companyWords.add(entity);
        companies.put(entity.getcId(), wrapMLData);
        continue;
      } 
      WrapMLData singleCompany = new WrapMLData();
      singleCompany.id = entity.getcId();
      singleCompany.name = entity.getCname();
      singleCompany.companyWords.add(entity);
      companies.put(entity.getcId(), singleCompany);
    } 
    return companies;
  }
  
  public List<DataBody> recommendCompanies(List<MLdataEntity> trainData, HashSet<String> testData) {
    HashMap<Integer, WrapMLData> companies = separateDataSet(trainData);
    List<DataBody> db = getSelectiveComp(companies, testData);
    System.out.println("Before");
    for (DataBody i : db) {
      System.out.println(i.getcName());
      System.out.println(i.getIntrSet().keySet());
      System.out.println(i.getIntrSet().size());
    } 
    Collections.sort(db);
    Collections.reverse(db);
    System.out.println("After");
    for (DataBody i : db)
      System.out.println(i.getcName()); 
    return db;
  }
  
  private List<DataBody> getSelectiveComp(HashMap<Integer, WrapMLData> trainData, HashSet<String> testData) {
    List<DataBody> db = new ArrayList<>();
    for (Integer companyId : trainData.keySet()) {
      WrapMLData companyFeat = trainData.get(companyId);
      List<MLdataEntity> features = companyFeat.companyWords;
      DataBody body = new DataBody();
      HashMap<String, Integer> intrSet = new HashMap<>();
      for (MLdataEntity feature : features) {
        try {
          for (String val : testData) {
            String a = val.toLowerCase();
            String b = feature.getWord().toLowerCase();
            if (a.contains(b) || b.contains(a) || a.equals(b) || a.length() == b.length()) {
              intrSet.put(feature.getWord(), feature.getFreq());
              break;
            } 
          } 
        } catch (Exception exception) {}
      } 
      body.setIntrSet(intrSet);
      body.setcName(companyFeat.name);
      body.setcId(companyFeat.id);
      db.add(body);
    } 
    return db;
  }
}
