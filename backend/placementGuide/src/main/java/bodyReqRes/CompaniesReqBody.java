package bodyReqRes;

import entities.CompanyEntity;
import java.util.List;

public class CompaniesReqBody {
  private List<CompanyEntity> companies;
  
  public List<CompanyEntity> getCompanies() {
    return this.companies;
  }
  
  public void setCompanies(List<CompanyEntity> companies) {
    this.companies = companies;
  }
}
