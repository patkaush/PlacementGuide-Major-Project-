package utils;

import bodyReqRes.CommentResBody;
import bodyReqRes.CompaniesBody;
import bodyReqRes.CompanyResBody;
import entities.MLdataEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import serviceLayer.CompanyServiceLayer;
import serviceLayer.InterestServiceLayer;

@Component
public class ParseComments {
  @Autowired
  CompanyServiceLayer service;
  
  @Autowired
  InterestServiceLayer serviceIntr;
  
  private HashMap<String, Integer> splitComments(List<CommentResBody> comments) {
    HashMap<String, Integer> splits = new HashMap<>();
    for (CommentResBody comment : comments) {
      String[] words = comment.getData().split(" ");
      int  b;
      int i;
      String[] arrayOfString1;
      for (i = (arrayOfString1 = words).length, b = 0; b < i; ) {
        String word = arrayOfString1[b];
        if (!word.equals(" "))
          if (splits.containsKey(word)) {
            splits.put(word, Integer.valueOf(((Integer)splits.get(word)).intValue() + 1));
          } else {
            splits.put(word, Integer.valueOf(1));
          }  
        b = b + 1;
      } 
    } 
    return splits;
  }
  
  public void addCommentsInCompany() {
    if (CompanyServiceLayer.freshData != null) {
      List<CommentResBody> res = new ArrayList<>();
      res.add(CompanyServiceLayer.freshData);
      HashMap<String, Integer> wordFreq = splitComments(res);
      MLdataEntity data = new MLdataEntity();
      for (String key : wordFreq.keySet()) {
        data.setcId(CompanyServiceLayer.companyData.getId());
        data.setCname(CompanyServiceLayer.companyData.getName());
        data.setWord(key);
        data.setFreq(wordFreq.get(key));
        this.serviceIntr.addMLData(data);
      } 
    } else {
      List<CompaniesBody> companies = this.service.getCompanies();
      for (CompaniesBody company : companies) {
        CompanyResBody companyData = this.service.getCompany(String.valueOf(company.getId()), Boolean.valueOf(false));
        HashMap<String, Integer> wordFreq = splitComments(companyData.getComments());
        MLdataEntity data = new MLdataEntity();
        for (String key : wordFreq.keySet()) {
          data.setcId(company.getId());
          data.setCname(company.getCompanyName());
          data.setWord(key);
          data.setFreq(wordFreq.get(key));
          this.serviceIntr.addMLData(data);
        } 
      } 
    } 
  }
}
