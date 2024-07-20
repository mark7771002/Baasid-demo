package entity;

import java.sql.Timestamp;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "goods")
public class Goods {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "_id", updatable = false, nullable = false)
  private UUID id;

  private String name;
  @ManyToOne
  @JoinColumn(name = "cr_user", nullable = false)
  private SystemUser crUser;
  @Column(name = "cr_datetime")
  private Timestamp crDatetime;
  @ManyToOne
  @JoinColumn(name = "up_user", nullable = false)
  private SystemUser upUser;
  @Column(name = "up_datetime")
  private Timestamp upDatetime;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Timestamp getCrDatetime() {
    return crDatetime;
  }

  public void setCrDatetime(Timestamp crDatetime) {
    this.crDatetime = crDatetime;
  }

  public Timestamp getUpDatetime() {
    return upDatetime;
  }

  public void setUpDatetime(Timestamp upDatetime) {
    this.upDatetime = upDatetime;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public SystemUser getCrUser() {
    return crUser;
  }

  public void setCrUser(SystemUser crUser) {
    this.crUser = crUser;
  }

  public SystemUser getUpUser() {
    return upUser;
  }

  public void setUpUser(SystemUser upUser) {
    this.upUser = upUser;
  }



}
