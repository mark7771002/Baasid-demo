package component;

import java.io.IOException;
import java.util.Arrays;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorizationCheckFilter extends OncePerRequestFilter {
  private static final String[] AUTH_WHITELIST = {
      // -- Swagger UI v3
      "/v3/api-docs/**", "v3/api-docs/**", "/swagger-ui/**", "swagger-ui/**", "/user/login"};

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String path = request.getServletPath();
    AntPathMatcher pathMatcher = new AntPathMatcher();
    boolean contains = Arrays.stream(AUTH_WHITELIST).anyMatch(x -> pathMatcher.match(x, path));
    // 如果不是登入or Swagger-ui就攔截
    if (!contains) {
      // 登入驗證
    } else {
      filterChain.doFilter(request, response);
    }

  }

}
