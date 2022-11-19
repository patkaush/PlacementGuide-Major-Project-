package controllerLayer;

import bodyReqRes.CommentReqBody;
import bodyReqRes.CommentResBody;
import bodyReqRes.CompaniesBody;
import bodyReqRes.CompanyReqBody;
import bodyReqRes.CompanyResBody;
import bodyReqRes.ResourceReqBody;
import com.opencsv.CSVReader;
import crudOperations.PersistLayer;
import daoLayer.DataPersist;
import daoLayer.TestCompany;
import entities.CompanyEntity;
import java.io.FileReader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import serviceLayer.CompanyServiceLayer;

@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@RestController
@RequestMapping({"company"})
public class CompanyController {
  @Autowired
  TestCompany testData;
  
  @Autowired
  DataPersist dataPersist;
  
  @Autowired
  CompanyServiceLayer companyService;
  
  @Autowired
  PersistLayer persist;
  
  @PostMapping({"addCompanies"})
  public void addCompanies(@RequestBody CompanyReqBody companies) {
    this.companyService.addCompanies(companies.getCompanies());
  }
  
  @DeleteMapping({"delCompany"})
  public void delCompany(@RequestParam String id) {
    this.companyService.delCompany(Integer.parseInt(id));
  }
  
  @GetMapping({"all"})
  public List<CompaniesBody> getCompanies() {
    return this.companyService.getCompanies();
  }
  
  @GetMapping({"companiesLimit"})
  public List<CompanyResBody> getCompaniesByLimit(@RequestParam String count) {
    Integer freq = Integer.valueOf(Integer.parseInt(count));
    return this.companyService.getCompanies(freq);
  }
  
  @GetMapping({"retrieve"})
  public CompanyResBody getCompany(@RequestParam String id) {
    return this.companyService.getCompany(id, Boolean.valueOf(true));
  }
  
  @PostMapping({"addComment"})
  public CommentResBody addComment(@RequestBody CommentReqBody body) {
    return this.companyService.addComment(body, Boolean.valueOf(false));
  }
  
  @PostMapping({"addRemark"})
  public CommentResBody addRemark(@RequestBody CommentReqBody body) {
    return this.companyService.addComment(body, Boolean.valueOf(true));
  }
  
  @PostMapping({"addResource"})
  public ResponseEntity<Object> addResouse(@RequestBody ResourceReqBody body) {
    this.companyService.addResource(body);
    return new ResponseEntity("Success", HttpStatus.ACCEPTED);
  }
  
  @RequestMapping({"/insert"})
  public void createCompany() {
    try {
      CSVReader reader = new CSVReader(
          new FileReader("C:\\Users\\kaushik\\Downloads\\archive\\company_reviews.csv"));
      int i = 0;
      String[] nextLine;
      while ((nextLine = reader.readNext()) != null && 
        i != 10) {
        i++;
        this.persist.saveCompany(new CompanyEntity(nextLine[0].trim(), nextLine[1].trim(), nextLine[2].trim(), nextLine[3].trim(), nextLine[12].trim(), nextLine[13].trim()));
        System.out.println();
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
