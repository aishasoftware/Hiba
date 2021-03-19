package aisha.bean;

import java.io.Serializable;

//import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
 
//simport javax.validation.constraints.NotNull;
 
/*@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)*/
public class Strategy implements Serializable {
  
 private static final long serialVersionUID = -6518171412015203128L;
  
 private Integer id;
 
 //@NotNull
 private String type;
  
 //@NotNull
 private String name;
 
 public Integer getId() {
  return id;
 }
 
 public void setId(Integer id) {
  this.id = id;
 }
 
 public String getType() {
  return type;
 }
 
 public void setType(String type) {
  this.type = type;
 }
 
 public String getName() {
  return name;
 }
 
 public void setName(String name) {
  this.name = name;
 }
  
 public String getString() {
  return String.format("StrategyDTO - Id: [%s]  Type: [%s]  Name: [%s]", this.id, this.type, this.name);
 }
 
}