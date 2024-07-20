package entity;


import java.util.Set;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "systemUser")
public class SystemUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "_id", updatable = false, nullable = false)
  private UUID id;


  private String account;

  private String name;

  private String password;

  @OneToMany(mappedBy = "crUser", fetch = FetchType.LAZY)
  private Set<Goods> goods1;

  @OneToMany(mappedBy = "upUser", fetch = FetchType.LAZY)
  private Set<Goods> goods2;



  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }



}
