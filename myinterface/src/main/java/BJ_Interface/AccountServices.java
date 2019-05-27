package BJ_Interface;

import Model.User;

public interface AccountServices {
    public User Ownerlogin(String phone, String password);
    public int login(String phone,String password);
    public int signup(User owner);
}
