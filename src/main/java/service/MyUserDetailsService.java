package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import bean.MyUser;
import entity.SystemUser;
import repository.SystemUserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
  @Autowired
  private SystemUserRepository systemUserRepository;


  @Override
  public MyUser loadUserByUsername(String username) throws UsernameNotFoundException {
    List<SystemUser> systemUserList = systemUserRepository.getSystemUser(username);
    SystemUser systemUser = systemUserList.get(0);
    systemUser.setPassword(new BCryptPasswordEncoder().encode(systemUser.getPassword()));
    return new MyUser(systemUser);
  }

}
