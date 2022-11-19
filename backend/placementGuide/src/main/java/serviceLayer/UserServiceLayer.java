package serviceLayer;

import Security.UserEntityService;
import bodyReqRes.CompCommResBody;
import bodyReqRes.CompaniesBody;
import bodyReqRes.CompanyResBody;
import bodyReqRes.EmailReqBody;
import bodyReqRes.UserProfileResBody;
import bodyReqRes.UserReqBody;
import bodyReqRes.UserResBody;
import bodyReqRes.UsersReqBody;
import constants.ExceptionMessages;
import daoLayer.DataPersist;
import daoLayer.UserDataPersist;
import entities.CommentEntity;
import entities.CompanyEntity;
import entities.InterestEntity;
import entities.JWTEntity;
import entities.UserEntity;
import exceptionHandler.UserException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import utils.JwtUtil;

@Service
public class UserServiceLayer {
  @Autowired
  AuthenticationManager authManager;
  
  @Autowired
  UserEntityService userEntityService;
  
  @Autowired
  JwtUtil jwtUtil;
  
  @Autowired
  UserDataPersist saveUser;
  
  @Autowired
  DataPersist persist;
  
  @Autowired
  private JavaMailSender mailSender;
  
  public UserProfileResBody getUserData(Integer id) {
    HashSet<Integer> userIntr = this.persist.getUserInterests(id.intValue());
    List<InterestEntity> interests = this.persist.getInterests();
    List<String> mainIntr = new ArrayList<>();
    for (InterestEntity intr : interests) {
      if (userIntr.contains(intr.getId()))
        mainIntr.add(intr.getInterest()); 
    } 
    List<CompanyResBody> favs = getUserCompanies(id);
    HashMap<Integer, String> mainFav = new HashMap<>();
    for (CompanyResBody body : favs)
      mainFav.put(body.getId(), body.getName()); 
    List<CompCommResBody> CompaniesWithComm = this.persist.getCompaniesWithUserComments(id);
    UserProfileResBody res = new UserProfileResBody();
    res.setCompanyData(CompaniesWithComm);
    res.setFavourites(mainFav);
    res.setInterests(mainIntr);
    res.setUsername(this.persist.getUsername(id));
    List<CommentEntity> comments = this.persist.getComments(id, Boolean.valueOf(true));
    res.setComments(comments);
    return res;
  }
  
  public void addUsers(UsersReqBody users) {
    for (UserReqBody userData : users.getUsers()) {
      String basePasword = userData.getPassword();
      BCryptPasswordEncoder bCryptPasswordEncoder = 
        new BCryptPasswordEncoder();
      String encodedPassword = bCryptPasswordEncoder.encode(basePasword);
      userData.setPassword(encodedPassword);
      this.saveUser.saveUserData(userData);
    } 
  }
  
  public UsersReqBody getUsers() {
    List<UserEntity> data = this.persist.getUsers();
    List<UserReqBody> req = new ArrayList<>();
    for (UserEntity entity : data) {
      UserReqBody body = new UserReqBody();
      body.setId(entity.getId());
      body.setEmail(entity.getEmail());
      body.setUsername(entity.getName());
      body.setPassword(entity.getPassword());
      body.setAdmin(entity.getIsAdmin());
      req.add(body);
    } 
    UsersReqBody mainReq = new UsersReqBody();
    mainReq.setUsers(req);
    return mainReq;
  }
  
  public UserResBody registerUser(UserReqBody userData) throws UserException {
    String basePasword = userData.getPassword();
    BCryptPasswordEncoder bCryptPasswordEncoder = 
      new BCryptPasswordEncoder();
    String encodedPassword = bCryptPasswordEncoder.encode(basePasword);
    userData.setPassword(encodedPassword);
    if (this.saveUser.getUserData(userData.getUsername()) != null)
      throw new UserException(ExceptionMessages.USER_EXIST); 
    Integer id = this.saveUser.saveUserData(userData);
    User user = new User(userData.getUsername(), userData.getPassword(), new ArrayList());
    String token = genToken(id, (UserDetails)user);
    return new UserResBody(id, token, "false");
  }
  
  public ResponseEntity<UserResBody> authenticateUser(@RequestBody UserReqBody userData, String... args) throws UserException {
    try {
      this.authManager.authenticate(
          (Authentication)new UsernamePasswordAuthenticationToken(userData.getUsername(), userData.getPassword()));
    } catch (BadCredentialsException e) {
      throw new UserException("Incorrect username or password", e);
    } 
    System.out.println(userData);
    UserDetails userDetails = this.userEntityService.loadUserByUsername(userData.getUsername());
    UserEntity user = this.saveUser.getUserData(userData.getUsername());
    if (this.saveUser.getToken(user.getId()) != null)
      throw new UserException(ExceptionMessages.USER_SIGNED); 
    String jwt = this.jwtUtil.generateToken(userDetails);
    this.saveUser.saveJWTtoken(new JWTEntity(user.getId(), jwt));
    return new ResponseEntity(new UserResBody(user.getId(), jwt, user.getIsAdmin()), HttpStatus.ACCEPTED);
  }
  
  public ResponseEntity<Object> logoutUser(@RequestHeader Map<String, String> header) throws Exception {
    System.out.println(header.get("Authorization"));
    this.saveUser.deleteToken(Integer.valueOf(Integer.parseInt(header.get("id"))));
    return new ResponseEntity("Success", HttpStatus.ACCEPTED);
  }
  
  public List<CompanyResBody> getUserCompanies(Integer id) {
    HashSet<Integer> set = this.saveUser.getUserCompanies(id);
    List<CompaniesBody> companies = this.persist.getCompanies();
    List<CompanyResBody> resData = new ArrayList<>();
    for (CompaniesBody i : companies) {
      if (set.contains(i.getId())) {
        CompanyEntity temp = this.persist.getCompanyDetails(i.getId());
        CompanyResBody body = new CompanyResBody(temp.getId(), temp.getName(), temp.getRating(), temp.getDescription(), temp.getInterview_difficulty());
        String[] data = body.getDescription().split(" ");
        if (data.length > 35) {
          String sum = "";
          for (int j = 0; j < 35; j++)
            sum = String.valueOf(String.valueOf(sum)) + " " + data[j]; 
          body.setDescription(String.valueOf(String.valueOf(sum)) + "....");
        } 
        resData.add(body);
      } 
    } 
    return resData;
  }
  
  public List<CompaniesBody> getUserCompaniesLimit(Map<String, String> headers, Integer count) {
    Integer id = Integer.valueOf(Integer.parseInt(headers.get("id")));
    HashSet<Integer> set = this.saveUser.getUserCompanies(id);
    List<CompaniesBody> resData = new ArrayList<>();
    return resData;
  }
  
  public ResponseEntity<Object> addToFavourites(Map<String, String> headers) {
    Integer userId = Integer.valueOf(Integer.parseInt(headers.get("id")));
    Integer companyId = Integer.valueOf(Integer.parseInt(headers.get("companyid")));
    HashSet<Integer> set = this.saveUser.getUserCompanies(userId);
    if (set.contains(companyId))
      return new ResponseEntity("Already Added", HttpStatus.ACCEPTED); 
    this.saveUser.addToFavourites(userId, companyId);
    return new ResponseEntity("Success", HttpStatus.ACCEPTED);
  }
  
  public ResponseEntity<Object> deleteFavourite(Map<String, String> headers) {
    Integer userId = Integer.valueOf(Integer.parseInt(headers.get("id")));
    Integer companyId = Integer.valueOf(Integer.parseInt(headers.get("companyid")));
    this.saveUser.deleteUserInterests(userId, companyId);
    return new ResponseEntity("Success", HttpStatus.ACCEPTED);
  }
  
  public String sendEmail(EmailReqBody req) {
    try {
      SimpleMailMessage email = new SimpleMailMessage();
      UserEntity user = this.saveUser.getUserData(req.getUsername());
      email.setTo(user.getEmail());
      email.setSubject("Important!!");
      email.setText(req.getBody());
      this.mailSender.send(email);
    } catch (Exception e) {
      return e.getMessage();
    } 
    return "Mail Sent";
  }
  
  public ResponseEntity<Object> addComment() {
    return null;
  }
  
  public ResponseEntity<Object> addSkills() {
    return null;
  }
  
  public ResponseEntity<Object> getSkills() {
    return null;
  }
  
  public void getInterests() {}
  
  private String genToken(Integer Id, UserDetails data) {
    String jwt = this.jwtUtil.generateToken(data);
    JWTEntity tokenRow = new JWTEntity();
    tokenRow.setId(Id);
    tokenRow.setToken(jwt);
    this.saveUser.saveJWTtoken(tokenRow);
    return jwt;
  }
  
  public void deleteUser(String id) {
    this.saveUser.deleteUser(Integer.parseInt(id));
  }
}
