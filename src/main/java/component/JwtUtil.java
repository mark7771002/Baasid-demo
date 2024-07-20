package component;

import java.security.Key;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import bean.MyUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
  private static final String ISS = "Hogwarts";// jwt簽發者
  private static final String SECRET = "AlohomoraIsASpellUsedToOpenDoors";
  private static String EXPIRE_TIME;

  @Value("${security.exprieTime}")
  public void setEXPIRE_TIME(String eXPIRE_TIME) {
    EXPIRE_TIME = eXPIRE_TIME;
  }

  public static String generateToken(Authentication authentication) throws NumberFormatException {
    MyUser myUser = (MyUser) authentication.getPrincipal();// 認証成功前存放 "用戶登入id"，認証成功後存放 "用戶對應的
                                                           // UserDetails"
    Calendar exp = Calendar.getInstance();
    exp.add(Calendar.MINUTE, Integer.valueOf(EXPIRE_TIME));

    Claims claims = Jwts.claims();
    claims.setSubject(myUser.getUsername());
    claims.setExpiration(exp.getTime());
    claims.setIssuer(ISS);
    Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());//// 給定一組密鑰，用來解密以及加密使用

    return Jwts.builder().setClaims(claims).signWith(secretKey).compact(); // 將 JwtBuilder 構建的 JWT
                                                                           // 物件，壓縮為一個字串的形式
  }

  public static String parseToken(String token) {
    Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

    JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();

    Claims claims = parser.parseClaimsJws(token).getBody();
    String username = claims.getSubject();

    return username;
  }



}
