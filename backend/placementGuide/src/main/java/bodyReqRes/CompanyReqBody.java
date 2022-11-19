package bodyReqRes;

import java.util.List;

public class CompanyReqBody {
  private List<CompanyBody> companies;
  
  public List<CompanyBody> getCompanies() {
    return this.companies;
  }
  
  public void setCompanies(List<CompanyBody> companies) {
    this.companies = companies;
  }
}
