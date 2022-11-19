package bodyReqRes;

import java.util.List;

public class UsersReqBody {
  private List<UserReqBody> users;
  
  public List<UserReqBody> getUsers() {
    return this.users;
  }
  
  public void setUsers(List<UserReqBody> users) {
    this.users = users;
  }
}
