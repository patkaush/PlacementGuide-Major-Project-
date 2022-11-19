package controllerLayer;

import Security.UserEntityService;
import bodyReqRes.CompanyResBody;
import bodyReqRes.EmailReqBody;
import bodyReqRes.UserProfileResBody;
import bodyReqRes.UserReqBody;
import bodyReqRes.UserResBody;
import bodyReqRes.UsersReqBody;
import constants.UrlEndPoint;
import exceptionHandler.UserException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import serviceLayer.UserServiceLayer;

@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@RequestMapping({"user"})
@RestController
public class UserController {
  @Autowired
  UserEntityService userEntityService;
  
  @Autowired
  UserServiceLayer userServiceLayer;
  
  @GetMapping({"getUsers"})
  public UsersReqBody getUsers() {
    return this.userServiceLayer.getUsers();
  }
  
  @GetMapping({"getUserProfile"})
  public UserProfileResBody getUserData(@RequestParam String id) {
    return this.userServiceLayer.getUserData(Integer.valueOf(Integer.parseInt(id)));
  }
  
  @PostMapping({"addUsers"})
  public void addUsers(@RequestBody UsersReqBody usersData) {
    this.userServiceLayer.addUsers(usersData);
  }
  
  @DeleteMapping({"delUser"})
  public void deleteUser(@RequestParam String id) {
    this.userServiceLayer.deleteUser(id);
  }
  
  @PostMapping({"register"})
  public ResponseEntity<UserResBody> registerUser(@RequestBody UserReqBody userData) throws UserException {
    UserResBody response = this.userServiceLayer.registerUser(userData);
    return new ResponseEntity(response, HttpStatus.ACCEPTED);
  }
  
  @PostMapping({"login"})
  public ResponseEntity<UserResBody> authenticateUser(@RequestBody UserReqBody userData, @RequestHeader Map<String, String> headers) throws UserException {
    ResponseEntity<UserResBody> res = this.userServiceLayer.authenticateUser(userData, new String[0]);
    return res;
  }
  
  @DeleteMapping({"logout"})
  public ResponseEntity<Object> logoutUser(@RequestHeader Map<String, String> header) throws Exception {
    UrlEndPoint.recData = null;
    return this.userServiceLayer.logoutUser(header);
  }
  
  @GetMapping({"wishList"})
  public List<CompanyResBody> getUserCompanies(@RequestHeader Map<String, String> headers) {
    return this.userServiceLayer.getUserCompanies(Integer.valueOf(Integer.parseInt(headers.get("id"))));
  }
  
  @PostMapping({"addCompany"})
  public ResponseEntity<Object> addToFavourites(@RequestHeader Map<String, String> headers) {
    return this.userServiceLayer.addToFavourites(headers);
  }
  
  @DeleteMapping({"delCompany"})
  public ResponseEntity<Object> deleteUserCompany(@RequestHeader Map<String, String> headers) {
    return this.userServiceLayer.deleteFavourite(headers);
  }
  
  @PostMapping({"addComment"})
  public ResponseEntity<Object> addComment() {
    return this.userServiceLayer.addComment();
  }
  
  @RequestMapping({"sendEmail"})
  public String sendEmail(@RequestBody EmailReqBody req) {
    return this.userServiceLayer.sendEmail(req);
  }
}
