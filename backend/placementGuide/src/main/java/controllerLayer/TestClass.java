package controllerLayer;

import daoLayer.DataPersist;
import entities.InterestEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestClass {
  @Autowired
  DataPersist per;
  
  @RequestMapping({"/testUrl"})
  public List<InterestEntity> getInterests() {
    return this.per.getInterests();
  }
}
