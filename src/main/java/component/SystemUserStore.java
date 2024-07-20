package component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import entity.SystemUser;

@Component
public class SystemUserStore {

  private static Map<String, SystemUser> m = new ConcurrentHashMap<>();

  public static void add(String token, SystemUser systemUser) {
    m.put(token, systemUser);
  }

  public static UUID getIdByToken(String token) {
    SystemUser systemUser = m.get(token.trim());
    return systemUser.getId();
  }
}
