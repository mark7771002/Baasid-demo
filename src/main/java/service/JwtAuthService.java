package service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import bean.MyUser;
import component.JwtUtil;
import component.SystemUserStore;

@Service
public class JwtAuthService {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private SystemUserStore systemUserStore;

  @SuppressWarnings("static-access")
  public String auth(Map<String, Object> reqDTO) {
    String username = (String) reqDTO.get("username");
    String password = (String) reqDTO.get("password");
    org.springframework.security.core.Authentication authentication =
        new UsernamePasswordAuthenticationToken(username, password);
    authentication = authenticationManager.authenticate(authentication);
    String token = JwtUtil.generateToken(authentication);
    MyUser myUser = (MyUser) authentication.getPrincipal();
    systemUserStore.add(token.replace("Bearer", "").replace(":", ""), myUser.getSystemUser());
    return token;
  }


}
