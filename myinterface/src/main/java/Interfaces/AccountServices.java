package Interfaces;

import Model.User;

public interface AccountServices {
    public User login(String phone,String password);
}
