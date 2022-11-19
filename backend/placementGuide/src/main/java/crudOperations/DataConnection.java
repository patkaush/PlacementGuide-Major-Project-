package crudOperations;

import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataConnection {
  @Autowired
  private SessionFactory factory;
  
  @Autowired
  DriverManagerDataSource source;
  
  public Session getSession() {
    return this.factory.getCurrentSession();
  }
  
  public JdbcTemplate getJdbc() {
    return new JdbcTemplate((DataSource)this.source);
  }
}
