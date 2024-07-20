package bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import entity.SystemUser;

@SuppressWarnings("serial")
public class MyUser implements UserDetails {
  @Autowired
  private SystemUser systemUser;

  private final String[] role = {"ADMIN"};

  public MyUser(SystemUser systemUser) {
    this.systemUser = systemUser;
  }

  public SystemUser getSystemUser() {
    return systemUser;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities =
        Arrays.stream(role).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    return authorities;
  }

  @Override
  public String getPassword() {
    return systemUser.getPassword();
  }

  @Override
  public String getUsername() {
    return systemUser.getAccount();
  }



}
