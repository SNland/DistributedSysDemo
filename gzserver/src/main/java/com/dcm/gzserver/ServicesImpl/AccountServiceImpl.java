package com.dcm.gzserver.ServicesImpl;

import GZ_Interface.AccountServices;
import Model.Police;
import Model.User;
import com.alibaba.dubbo.config.annotation.Service;
import com.dcm.gzserver.Dao.PoliceMapper;
import com.dcm.gzserver.Dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0")
@Component
public class AccountServiceImpl implements AccountServices {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PoliceMapper policeMapper;
    @Override
    public User Ownerlogin(String phone,String password) {
        System.out.println("phone"+phone);
            User user=new User();
            try{
                user=userMapper.selectByPhone(phone);
                System.out.println(user.getPassword());
            }catch(Exception e){
                System.out.println("没有该车主信息");
            }
            if(user!=null&&user.getPassword().equals(password)){
                System.out.println(user.getUsername());
                return user;
            }
            else
            return null;
    }

    @Override
    public int login(String phone,String password){
        Police policeman=new Police();
        try{
            policeman=policeMapper.selectByPhone(phone);
        }catch(Exception e){
            System.out.println("没有该警察用户");
            return -1;
        }
        if(policeman!=null&&policeman.getPassword().equals(password)){
            return 0;
        }else{
            return -1;
        }
    }

    @Override
    public int signup(User owner) {
        System.out.println(owner.getUserId());
        System.out.println(owner.getPassword());
        try{
            userMapper.insert(owner);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("注册用户失败");
            return -1;
        }
        return 0;
    }


}
