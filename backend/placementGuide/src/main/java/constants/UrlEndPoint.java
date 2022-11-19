package constants;

import java.util.List;
import utils.DataBody;

public class UrlEndPoint {
  public static final String DEV_URL = "http://localhost:3000";
  
  public static final String DEPLOY_URL = "https://willowy-blancmange-c72174.netlify.app";
  
  public static final String USER_CONTROLLER = "user";
  
  public static final String USER_LOGIN = "login";
  
  public static final String USER_AUTHENTICATE = "authenticate";
  
  public static final String USER_LOGOUT = "logout";
  
  public static final String WISHLIST = "wishList";
  
  public static final String ADD_TO_WISHLIST = "addCompany";
  
  public static final String COMMENT = "addComment";
  
  public static final String SKILLS = "addSkills";
  
  public static final String REGISTRATION = "register";
  
  public static final String DELETE_FAVOURITE = "delCompany";
  
  public static final String ADD_USERS = "addUsers";
  
  public static final String GET_USERS = "getUsers";
  
  public static final String SEND_EMAIL = "sendEmail";
  
  public static final String GET_USER_DATA = "getUserProfile";
  
  public static final String DELETE_USER = "delUser";
  
  public static final String COMPANY_CONTROLLER = "company";
  
  public static final String ALL_COMPANIES = "all";
  
  public static final String FETCH_COMPANIES = "getCompanies";
  
  public static final String SINGLE_COMPANY_DETAILS = "retrieve";
  
  public static final String ALL_COMPANIES_LIMIT = "companiesLimit";
  
  public static final String ADD_COMMENT = "addComment";
  
  public static final String ADD_RESOURCE = "addResource";
  
  public static final String ADD_COMPANIES = "addCompanies";
  
  public static final String DELETE_COMPANY = "delCompany";
  
  public static final String ADD_REMARK = "addRemark";
  
  public static final String INTEREST_CONTROLLER = "interest";
  
  public static final String ALL_INTERESTS = "all";
  
  public static final String SET_INTERESTS = "setUserInterests";
  
  public static final String USER_INTERESTS = "userInterests";
  
  public static final String ADD_GROUP_INTERESTS = "groupInterests";
  
  public static List<DataBody> recData = null;
}
