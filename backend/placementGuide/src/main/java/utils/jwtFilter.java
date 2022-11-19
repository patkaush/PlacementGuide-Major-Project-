package utils;

import Security.UserEntityService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class jwtFilter extends OncePerRequestFilter {
  @Autowired
  private UserEntityService userDetailsService;
  
  @Autowired
  private JwtUtil jwtUtil;
  
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader("Authorization");
    String userName = null;
    if (header != null && header.startsWith("Bearer ")) {
      String jwt = header.substring(7);
      userName = this.jwtUtil.extractUsername(jwt);
    } 
    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
      usernamePasswordAuthenticationToken
        .setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication((Authentication)usernamePasswordAuthenticationToken);
    } 
    filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
  }
}
