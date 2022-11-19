package Security;

import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import utils.jwtFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
  @Autowired
  jwtFilter jwtFilter;
  
  @Autowired
  UserEntityService userDetails;
  
  public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
    authBuilder.userDetailsService(this.userDetails);
  }
  
  public void configure(HttpSecurity http) throws Exception {
    ((HttpSecurity)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http
      .authorizeRequests()
      .antMatchers(new String[] { "/user/login" })).permitAll()
      .and())
      .authorizeRequests()
      .antMatchers(new String[] { "/user/register" })).permitAll()
      .and())
      .authorizeRequests()
      .antMatchers(new String[] { "/interest/groupInterests" })).permitAll()
      .and())
      .authorizeRequests()
      .antMatchers(new String[] { "/testUrl" })).permitAll()
      .and())
      .authorizeRequests()
      .anyRequest()).authenticated()
      .and())
      .csrf().disable())
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.cors();
    http.addFilterBefore((Filter)this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }
  
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
  
  public PasswordEncoder passwordEncoder() {
    return (PasswordEncoder)new BCryptPasswordEncoder();
  }
}
