package Model;

import java.io.Serializable;

public class User implements Serializable {

    private String userId;


    private String phone;


    private String username;


    private String password;

    private String city;
    public User(){}
    public User(String uuid,String username,String password,String phone){
        this.userId=uuid;
        this.phone=phone;
        this.username=username;
        this.password=password;
    }
    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}