package repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import entity.SystemUser;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, UUID> {
  @Query(value = "select _id ,account,password,name from systemUser where account = %?1",
      nativeQuery = true)
  List<SystemUser> getSystemUser(String name);
}
