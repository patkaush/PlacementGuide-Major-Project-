package serviceLayer;

import bodyReqRes.CommentReqBody;
import bodyReqRes.CommentResBody;
import bodyReqRes.CompaniesBody;
import bodyReqRes.CompanyBody;
import bodyReqRes.CompanyResBody;
import bodyReqRes.ResourceReqBody;
import constants.UrlEndPoint;
import daoLayer.DataPersist;
import entities.CommentEntity;
import entities.CompanyEntity;
import entities.ResourceEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.DataBody;
import utils.ParseComments;

@Service
public class CompanyServiceLayer {
  @Autowired
  DataPersist persist;
  
  public void addCompanies(List<CompanyBody> companies) {
    this.persist.addCompanies(companies);
  }
  
  public void delCompany(int cid) {
    this.persist.delCompany(cid);
    this.persist.delComments(cid);
    this.persist.delResources(cid);
  }
  
  public List<CompaniesBody> getCompanies() {
    return this.persist.getCompanies();
  }
  
  private List<CompaniesBody> autoWrapDatabody(List<DataBody> body) {
    List<CompaniesBody> res = new ArrayList<>();
    for (DataBody i : body) {
      CompaniesBody temp = new CompaniesBody(i.getcId(), i.getcName());
      res.add(temp);
    } 
    return res;
  }
  
  public List<CompanyResBody> getCompanies(Integer freq) {
    List<CompaniesBody> onlyIds;
    List<CompanyResBody> resData = new ArrayList<>();
    if (UrlEndPoint.recData != null) {
      onlyIds = autoWrapDatabody(UrlEndPoint.recData);
    } else {
      onlyIds = getCompanies();
    } 
    int marker = 0;
    for (CompaniesBody company : onlyIds) {
      if (marker == freq.intValue())
        break; 
      if (company.getCompanyName().equals("name"))
        continue; 
      CompanyResBody temp = getCompany(String.valueOf(company.getId()), null);
      CompanyResBody body = new CompanyResBody(temp.getId(), temp.getName(), temp.getRating(), temp.getDescription(), temp.getInterview_difficulty());
      String[] data = body.getDescription().split(" ");
      if (data.length > 35) {
        String sum = "";
        for (int i = 0; i < 35; i++)
          sum = String.valueOf(String.valueOf(sum)) + " " + data[i]; 
        body.setDescription(String.valueOf(String.valueOf(sum)) + "....");
      } 
      resData.add(body);
      marker++;
    } 
    return resData;
  }
  
  public CompanyResBody getCompany(String id, Boolean includeComments) {
    CompanyEntity comp = this.persist.getCompanyDetails(Integer.valueOf(Integer.parseInt(id)));
    CompanyResBody res = new CompanyResBody();
    res.autoWrap(comp);
    if (includeComments != null) {
      List<CommentEntity> comments = this.persist.getComments(Integer.valueOf(Integer.parseInt(id)), Boolean.valueOf(false));
      List<ResourceEntity> links = this.persist.getResourceLinks(Integer.valueOf(Integer.parseInt(id)));
      res.autoWrapComments(comments);
      res.autoWrap(links);
    } 
    return res;
  }
  
  public static CommentResBody freshData = null;
  
  public static CompanyEntity companyData;
  
  @Autowired
  ParseComments comms;
  
  public CommentResBody addComment(CommentReqBody body, Boolean state) {
    CommentEntity entity = new CommentEntity();
    entity.autoWrap(body);
    this.persist.addComment(entity, state);
    CommentResBody resBody = new CommentResBody();
    resBody.autoWrap(entity);
    if (!state.booleanValue()) {
      freshData = resBody;
      companyData = this.persist.getCompanyDetails(entity.getCompanyId());
      this.comms.addCommentsInCompany();
      freshData = null;
      companyData = null;
    } 
    return resBody;
  }
  
  public void addResource(ResourceReqBody body) {
    ResourceEntity entity = new ResourceEntity();
    entity.autoWrap(body);
    this.persist.addResourceLink(entity);
  }
}
