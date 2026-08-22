package tradingWebsite.example.TradingWebsite.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")

public class UserEntity {
@Id
private long id;
private String name;
// private int age;
private String password;
private String email;


public void setId(long id){
    this.id=id;
}
public long getId(){
    return id;
}
public void setName(String username){
    this.name=username;
}
public String getName(){
    return name;
}

public void setEmail(String email){
    this.name=email;
}
public String getEmail(){
    return email;
}
public void setPassword(String passowrd){
    this.name=passowrd;
}
public String getPassword(){
    return password;
}
}
