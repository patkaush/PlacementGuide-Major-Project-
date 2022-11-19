package daoLayer;

import entities.CompanyEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TestCompany {
  List<CompanyEntity> companies = new ArrayList<>();
  
  public List<CompanyEntity> getCompanyDetails() {
    return this.companies;
  }
  
  public CompanyEntity getCompany(Integer id) {
    return this.companies.get(id.intValue() - 1);
  }
}
