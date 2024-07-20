package component;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import Exception.NoOpenPortAuthenticationException;
import bean.ApiError;
import bean.MyUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MyUserDetailsService;


public class JwtAuthenticationFilter extends OncePerRequestFilter {


  private MyUserDetailsService myUserDetailsService;

  public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
    this.myUserDetailsService = myUserDetailsService;
  }

  private static final String HEADER_AUTH = "Authorization";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader(HEADER_AUTH);
    try {
      if (null == authHeader) {
        throw new NoOpenPortAuthenticationException("Forbidden");
      }
      if (!authHeader.isEmpty()) {
        String accessToken = authHeader.replace("Bearer", "").replace(":", "");
        String username = JwtUtil.parseToken(accessToken);
        MyUser myUser = myUserDetailsService.loadUserByUsername(username);
        org.springframework.security.core.Authentication authentication =
            new UsernamePasswordAuthenticationToken(username, myUser.getPassword(),
                myUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      SecurityContextHolder.clearContext();
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.setContentType("application/json;charset=utf-8");
      ApiError apiError = new ApiError(HttpServletResponse.SC_FORBIDDEN, "權限不足");
      response.getWriter().write(new ObjectMapper().writeValueAsString(apiError));
      return;
    }

    filterChain.doFilter(request, response);

  }

}
