package bodyReqRes;

import java.util.List;

public class WrapCompaniesResBody {
  private List<CompaniesBody> companies;
  
  public WrapCompaniesResBody(List<CompaniesBody> resBody) {
    this.companies = resBody;
  }
  
  public WrapCompaniesResBody() {}
  
  public List<CompaniesBody> getResBody() {
    return this.companies;
  }
  
  public void setResBody(List<CompaniesBody> resBody) {
    this.companies = resBody;
  }
}
