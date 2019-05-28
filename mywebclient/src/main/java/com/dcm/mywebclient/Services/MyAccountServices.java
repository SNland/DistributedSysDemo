package com.dcm.mywebclient.Services;

import Model.User;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.dcm.mywebclient.controller.GetLocation;
import org.springframework.stereotype.Component;


@Component
public class MyAccountServices {
    @Reference(version = "1.0.0")
     BJ_Interface.AccountServices accountServices1;


    @Reference(version = "1.0.0")
     SH_Interface.AccountServices accountServices2;


    @Reference(version = "1.0.0")
     GZ_Interface.AccountServices accountServices3;


    public User checkOwnerlogin(String phone,String password){
            User owner3=new User();
            User owner2=new User();
            User owner1=new User();
            owner3=accountServices3.Ownerlogin(phone,password);
            owner2=accountServices2.Ownerlogin(phone,password);
            owner1=accountServices1.Ownerlogin(phone,password);
            if(owner1!=null)
            {
                System.out.println(owner1.getUsername());
                return owner1;
            }
            else if(owner2!=null){
                System.out.println(owner2.getUsername());
                return owner2;
            }

            else if(owner3!=null){
                System.out.println(owner3.getUsername());
                return owner3;
            }
            else
                return null;
    }

    public int PoliceLogin(String phone,String password){
        int res1=accountServices1.login(phone,password);
        int res2=accountServices2.login(phone,password);
        int res3=accountServices3.login(phone,password);
        if(res1==0||res2==0||res3==0){
            return 0;
        }else{
            return -1;
        }
    }



    public int addUser(User owner,String location){
        GetLocation getLocation=new GetLocation();
        int result=getLocation.getDestination(location);
        int res=1;
        if(result==1){
            System.out.println("发送到北京data server");
            res=accountServices1.signup(owner);
        }else if(result==2){
            System.out.println("发送到上海data server");
            res=accountServices2.signup(owner);
        }else if(result==3){
            System.out.println("发送到广州data server");
            System.out.println(owner.getPhone());
            res=accountServices3.signup(owner);
        }
        return res;
    }
}
