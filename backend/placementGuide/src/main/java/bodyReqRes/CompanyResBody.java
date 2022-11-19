package bodyReqRes;

import entities.CommentEntity;
import entities.CompanyEntity;
import entities.ResourceEntity;
import java.util.List;

public class CompanyResBody extends CompanyBody {
  public CompanyResBody(Integer id, String name, String rating, String description, String interview_difficulty) {
    super(id, name, rating, description, interview_difficulty, null);
  }
  
  public CompanyResBody() {}
  
  public void autoWrapComments(List<CommentEntity> entites) {
    for (CommentEntity entity : entites) {
      CommentResBody com = new CommentResBody();
      com.autoWrap(entity);
      getComments().add(com);
    } 
  }
  
  public void autoWrap(CompanyEntity comp) {
    setId(comp.getId());
    setName(comp.getName());
    setRating(comp.getRating());
    setDescription(comp.getDescription());
    setInterview_difficulty(comp.getInterview_difficulty());
  }
  
  public void autoWrap(List<ResourceEntity> entities) {
    for (ResourceEntity entity : entities) {
      ResourceResBody res = new ResourceResBody();
      res.autoWrap(entity);
      getResources().add(res);
    } 
  }
}
