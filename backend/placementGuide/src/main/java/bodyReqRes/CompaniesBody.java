package bodyReqRes;

public class CompaniesBody {
  private Integer id;
  
  private String companyName;
  
  CompaniesBody() {}
  
  public CompaniesBody(Integer id, String companyName) {
    this.id = id;
    this.companyName = companyName;
  }
  
  public Integer getId() {
    return this.id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getCompanyName() {
    return this.companyName;
  }
  
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
}
