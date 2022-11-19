package crudOperations;

import bodyReqRes.CompCommResBody;
import bodyReqRes.CompaniesBody;
import entities.CommentEntity;
import entities.CompanyEntity;
import entities.GroupEntity;
import entities.InterestEntity;
import entities.JWTEntity;
import entities.MLdataEntity;
import entities.RemarkEntity;
import entities.ResourceEntity;
import entities.UserCompanyEntity;
import entities.UserEntity;
import entities.UserInterestEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PersistLayer {
  @Autowired
  DataConnection factory;
  
  private String selectStmt;
  
  private String fetchSql;
  
  public void persistData(CompanyEntity company) {
    Session session = this.factory.getSession();
    session.save(company);
  }
  
  public Integer persistData(UserEntity userData) {
    Session session = this.factory.getSession();
    Integer Id = (Integer)session.save(userData);
    return Id;
  }
  
  public String getUsername(Integer id) {
    JdbcTemplate jdbc = this.factory.getJdbc();
    String username = (String)jdbc.query("SELECT NAME FROM USERENTITY WHERE ID=" + id, new ResultSetExtractor<String>() {
          public String extractData(ResultSet rs) throws SQLException, DataAccessException {
            rs.next();
            return rs.getString("name");
          }
        });
    return username;
  }
  
  public PersistLayer() {
    this.selectStmt = "SELECT * FROM USERENTITY WHERE NAME=";
    this.fetchSql = "SELECT * FROM INTERESTENTITY";
  }
  
  public List<UserEntity> getUsers() {
    String SQL = "SELECT ID,NAME,EMAIL FROM USERENTITY WHERE ISADMIN='false'";
    JdbcTemplate jdbc = this.factory.getJdbc();
    List<UserEntity> data = (List<UserEntity>)jdbc.query(SQL, new ResultSetExtractor<List>() {
          public List<UserEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
        	  List<UserEntity> temp = new ArrayList<>();
            while( rs.next())  {
              UserEntity x = new UserEntity();
              x.setName(rs.getString("name"));
              x.setId(Integer.valueOf(rs.getInt("id")));
              x.setEmail(rs.getString("email"));
              x.setPassword(null);
              x.setIsAdmin("false");
              temp.add(x);
            } 
            return temp;
          }
        });
    return data;
  }
  
  public void saveToken(JWTEntity tokenData) {
    Session session = this.factory.getSession();
    session.save(tokenData);
  }
  
  public List<InterestEntity> getInterests() {
    JdbcTemplate jdbc = this.factory.getJdbc();
    List<InterestEntity> list = (List<InterestEntity>)jdbc.query(this.fetchSql, new ResultSetExtractor<List>() {
          public List<InterestEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<InterestEntity> list = new ArrayList<>();
            while (rs.next())
              list.add(new InterestEntity(Integer.valueOf(rs.getInt("id")), rs.getString("interest"), Integer.valueOf(rs.getInt("gId")))); 
            return list;
          }
        });
    return list;
  }
  
  public UserEntity getUserData(String userName) {
    JdbcTemplate jdbc = this.factory.getJdbc();
    UserEntity userData = (UserEntity)jdbc.query(String.valueOf(String.valueOf(this.selectStmt)) + "'" + userName + "'", new ResultSetExtractor<UserEntity>() {
          public UserEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
            UserEntity temp = null;
            if (rs.next()) {
              temp = new UserEntity();
              temp.setEmail(rs.getString("email"));
              temp.setId(Integer.valueOf(rs.getInt("id")));
              temp.setName(rs.getString("name"));
              temp.setPassword(rs.getString("password"));
              temp.setIsAdmin(rs.getString("isadmin"));
            } 
            return temp;
          }
        });
    return userData;
  }
  
  public JWTEntity getTokenData(Integer Id) {
    Session session = this.factory.getSession();
    return (JWTEntity)session.get(JWTEntity.class, Id);
  }
  
  public HashSet<Integer> getUserInterests(Integer userId) {
    String SQL = "SELECT * FROM USERINTERESTENTITY WHERE USERID =" + userId;
    JdbcTemplate jdbc = this.factory.getJdbc();
    HashSet<Integer> set = (HashSet<Integer>)jdbc.query(SQL, new ResultSetExtractor<HashSet>() {
          public HashSet<Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
            HashSet<Integer> set = new HashSet<>();
            while (rs.next())
              set.add(Integer.valueOf(rs.getInt("interestid"))); 
            return set;
          }
        });
    return set;
  }
  
  public void delTokenData(Integer id) {
    Session session = this.factory.getSession();
    session.delete(session.get(JWTEntity.class, id));
  }
  
  public void saveInterest(InterestEntity data) {
    Session session = this.factory.getSession();
    session.save(data);
  }
  public Integer saveInterestTemp(InterestEntity data) {
	    Session session = this.factory.getSession();
	    return (Integer)session.save(data);
	  }
  
  public void saveUserInterest(UserInterestEntity data) {
    Session session = this.factory.getSession();
    session.save(data);
  }
  
  public void removeUserInterests(Integer userId) {
    String SQL = "DELETE FROM USERINTERESTENTITY WHERE USERID =" + userId;
    JdbcTemplate jdbc = this.factory.getJdbc();
    jdbc.execute(SQL);
  }
  
  public void delCompany(Integer cid) {
    String SQL = "DELETE FROM COMPANYENTITY WHERE ID =" + cid;
    JdbcTemplate jdbc = this.factory.getJdbc();
    jdbc.execute(SQL);
  }
  
  public Integer saveCompany(CompanyEntity companyEntity) {
    Session session = this.factory.getSession();
    return (Integer)session.save(companyEntity);
  }
  
  public List<CompaniesBody> getCompanies() {
    String SQL = "SELECT ID,NAME FROM COMPANYENTITY";
    JdbcTemplate jdbc = this.factory.getJdbc();
    List<CompaniesBody> data = (List<CompaniesBody>)jdbc.query(SQL, new ResultSetExtractor<List>() {
          public List<CompaniesBody> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<CompaniesBody> temp = new ArrayList<>();
            while (rs.next())
              temp.add(new CompaniesBody(Integer.valueOf(rs.getInt("id")), rs.getString("name"))); 
            return temp;
          }
        });
    return data;
  }
  
  public void delComments(Integer cid) {
    String SQL = "DELETE FROM COMMENTENTITY WHERE COMPANYID =" + cid;
    JdbcTemplate jdbc = this.factory.getJdbc();
    jdbc.execute(SQL);
  }
  
  public CompanyEntity getCompanyDetails(Integer compId) {
    Session session = this.factory.getSession();
    return (CompanyEntity)session.get(CompanyEntity.class, compId);
  }
  
  public void addUserCompanies(UserCompanyEntity data) {
    Session session = this.factory.getSession();
    session.save(data);
  }
  
  public HashSet<Integer> getUserCompanies(Integer userId) {
    String SQL = "SELECT COMPANYID FROM USERCOMPANYENTITY WHERE USERID=" + userId;
    JdbcTemplate jdbc = this.factory.getJdbc();
    HashSet<Integer> set = (HashSet<Integer>)jdbc.query(SQL, new ResultSetExtractor<HashSet>() {
          public HashSet extractData(ResultSet rs) throws SQLException, DataAccessException {
            HashSet<Integer> set = new HashSet<>();
            while (rs.next())
              set.add(Integer.valueOf(rs.getInt("companyid"))); 
            return set;
          }
        });
    return set;
  }
  
  public void deleteUserCompany(Integer userId, Integer compId) {
    String SQL = "DELETE FROM USERCOMPANYENTITY WHERE USERID = " + userId + " AND COMPANYID = " + compId;
    JdbcTemplate jdbc = this.factory.getJdbc();
    jdbc.execute(SQL);
  }
  
  public void addComment(CommentEntity comment, boolean remark) {
    Session session = this.factory.getSession();
    RemarkEntity en = new RemarkEntity();
    if (remark) {
      en.setCId(comment.getCId());
      en.setCompanyId(comment.getCompanyId());
      en.setDate(comment.getDate());
      en.setData(comment.getData());
      en.setUserId(comment.getUserId());
      en.setUserName(comment.getUserName());
      session.save(en);
      comment.setCId(en.getCId());
      comment.setDate(en.getDate());
    } else {
      session.save(comment);
    } 
  }
  
  public List<CommentEntity> getComments(Integer Id, Boolean remark) {
    String SQL;
    if (remark.booleanValue()) {
      SQL = "SELECT * FROM REMARKENTITY WHERE COMPANYID=" + Id + " ORDER BY DATE";
    } else {
      SQL = "SELECT * FROM COMMENTENTITY WHERE COMPANYID=" + Id + " ORDER BY DATE";
    } 
    JdbcTemplate jdbc = this.factory.getJdbc();
    List<CommentEntity> comments = (List<CommentEntity>)jdbc.query(SQL, new ResultSetExtractor<List>() {
          public List<CommentEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<CommentEntity> temp = new ArrayList<>();
            while (rs.next()) {
              CommentEntity row = new CommentEntity();
              row.setCId(Integer.valueOf(rs.getInt("cid")));
              row.setCompanyId(Integer.valueOf(rs.getInt("companyid")));
              row.setData(rs.getString("data"));
              row.setUserId(Integer.valueOf(rs.getInt("userid")));
              row.setUserName(rs.getString("username"));
              row.setDate(rs.getDate("date"));
              temp.add(row);
            } 
            return temp;
          }
        });
    return comments;
  }
  
  public void addResource(ResourceEntity entity) {
    Session session = this.factory.getSession();
    session.save(entity);
  }
  
  public List<ResourceEntity> getResources(Integer companyId) {
    String SQL = "SELECT * FROM RESOURCEENTITY WHERE COMPANYID=" + companyId;
    JdbcTemplate jdbc = this.factory.getJdbc();
    List<ResourceEntity> rLinks = (List<ResourceEntity>)jdbc.query(SQL, new ResultSetExtractor<List>() {
          public List<ResourceEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<ResourceEntity> temp = new ArrayList<>();
            while (rs.next()) {
              ResourceEntity row = new ResourceEntity();
              row.setrId(Integer.valueOf(rs.getInt("rid")));
              row.setCompanyId(Integer.valueOf(rs.getInt("companyid")));
              row.setResourceLink(rs.getString("resourcelink"));
              row.setUserId(Integer.valueOf(rs.getInt("userid")));
              row.setUsername(rs.getString("username"));
              row.setDate(rs.getDate("date"));
              temp.add(row);
            } 
            return temp;
          }
        });
    return rLinks;
  }
  
  public void delResources(Integer cid) {
    String SQL = "DELETE FROM RESOURCEENTITY WHERE COMPANYID =" + cid;
    JdbcTemplate jdbc = this.factory.getJdbc();
    jdbc.execute(SQL);
  }
  
  public Integer addGroupInterest(GroupEntity entity) {
    Session ses = this.factory.getSession();
    return (Integer)ses.save(entity);
  }
  
  public List<GroupEntity> getGroupdInterests() {
    String SQL = "SELECT * FROM GROUPENTITY";
    JdbcTemplate jdbc = this.factory.getJdbc();
    List<GroupEntity> Grps = (List<GroupEntity>)jdbc.query(SQL, new ResultSetExtractor<List>() {
          public List<GroupEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<GroupEntity> temp = new ArrayList<>();
            while (rs.next()) {
              GroupEntity row = new GroupEntity();
              row.setgId(Integer.valueOf(rs.getInt("gid")));
              row.setName(rs.getString("name"));
              temp.add(row);
            } 
            return temp;
          }
        });
    return Grps;
  }
  
  public void addMLData(MLdataEntity data) {
    Session ses = this.factory.getSession();
    ses.save(data);
  }
  
  public List<MLdataEntity> getMLData() {
    String SQL = "SELECT * FROM MLDATAENTITY ORDER BY CID ";
    JdbcTemplate jdbc = this.factory.getJdbc();
    List<MLdataEntity> trainSet = (List<MLdataEntity>)jdbc.query(SQL, new ResultSetExtractor<List>() {
          public List<MLdataEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<MLdataEntity> temp = new ArrayList<>();
            while (rs.next()) {
              MLdataEntity row = new MLdataEntity();
              row.setTempId(Integer.valueOf(rs.getInt("tempId")));
              row.setcId(Integer.valueOf(rs.getInt("cid")));
              row.setCname(rs.getString("cname"));
              row.setWord(rs.getString("word"));
              row.setFreq(Integer.valueOf(rs.getInt("freq")));
              temp.add(row);
            } 
            return temp;
          }
        });
    return trainSet;
  }
  
  public List<CompCommResBody> getCompaniesWithUserComments(Integer id) {
    String SQL = "SELECT NAME,COMPANYID,CID,DATE,DATA,USERNAME,USERID FROM COMPANYENTITY ,COMMENTENTITY WHERE ID=COMPANYID AND UserId=" + id + " ORDER BY DATE";
    JdbcTemplate jdbc = this.factory.getJdbc();
    List<CompCommResBody> list = (List<CompCommResBody>)jdbc.query(SQL, new ResultSetExtractor<List>() {
          public List<CompCommResBody> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<CompCommResBody> temp = new ArrayList<>();
            while (rs.next()) {
              CompCommResBody coop = new CompCommResBody();
              CommentEntity row = new CommentEntity();
              row.setCId(Integer.valueOf(rs.getInt("cid")));
              row.setCompanyId(Integer.valueOf(rs.getInt("companyid")));
              row.setData(rs.getString("data"));
              row.setUserName(rs.getString("username"));
              row.setDate(rs.getDate("date"));
              row.setUserId(Integer.valueOf(rs.getInt("userid")));
              coop.setComments(row);
              coop.setCompanyName(rs.getString("name"));
              temp.add(coop);
            } 
            return temp;
          }
        });
    return list;
  }
  
  public void deleteUser(int parseInt) {
    JdbcTemplate jdbc = this.factory.getJdbc();
    jdbc.execute("DELETE FROM USERENTITY WHERE ID=" + parseInt);
    jdbc.execute("DELETE FROM JWTENTITY WHERE USERID=" + parseInt);
    jdbc.execute("DELETE FROM USERINTERESTENTITY WHERE USERID=" + parseInt);
  }
}
