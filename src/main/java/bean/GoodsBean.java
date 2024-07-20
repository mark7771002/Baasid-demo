package bean;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;
import entity.SystemUser;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsBean {

  private UUID id;

  private String name;

  private SystemUser crUser;

  private String crDatetime;

  private SystemUser upUser;

  private String upDatetime;


  private Integer Status;


  private String message;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SystemUser getCrUser() {
    return crUser;
  }

  public void setCrUser(SystemUser crUser) {
    this.crUser = crUser;
  }

  public String getCrDatetime() {
    return crDatetime;
  }

  public void setCrDatetime(String crDatetime) {
    this.crDatetime = crDatetime;
  }

  public SystemUser getUpUser() {
    return upUser;
  }

  public void setUpUser(SystemUser upUser) {
    this.upUser = upUser;
  }

  public String getUpDatetime() {
    return upDatetime;
  }

  public void setUpDatetime(String upDatetime) {
    this.upDatetime = upDatetime;
  }

  public Integer getStatus() {
    return Status;
  }

  public void setStatus(Integer status) {
    Status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }



}
