package controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import service.JwtAuthService;

@RestController
@RequestMapping("/auth")
public class authController {

  @Autowired
  private JwtAuthService jwtAuthService;

  private static Map<String, Object> temp = new ConcurrentHashMap<>();

  @SuppressWarnings("finally")
  @PostMapping("/login")
  public Map<String, Object> auth(@RequestBody Map<String, Object> authMap,
      HttpServletResponse response) throws JsonProcessingException, IOException {
    try {
      String token = jwtAuthService.auth(authMap);
      temp.put(token, authMap.get("username"));
      response.addHeader("Authorization", "Bearer: " + token);
    } catch (AuthenticationException e) {
      authMap.clear();
      authMap.put("Status", HttpServletResponse.SC_UNAUTHORIZED);
      authMap.put("-", "帳號密碼錯誤");
    } catch (Exception e) {
      authMap.clear();
      authMap.put("Status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      authMap.put("-", "系統錯誤");
    } finally {
      return authMap;
    }
  }
}
