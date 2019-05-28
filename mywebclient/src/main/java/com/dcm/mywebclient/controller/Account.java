package com.dcm.mywebclient.controller;


import Model.User;
import Model.Vehicle;
import com.alibaba.dubbo.common.json.JSON;
import com.dcm.mywebclient.Services.MyAccountServices;
import com.dcm.mywebclient.Services.VehicleServices;
import com.dcm.mywebclient.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@CrossOrigin
@RestController
public class Account {

    @Autowired
    private MyAccountServices accountServices;

    @Autowired
    private VehicleServices vehicleServices;

    Map<String,Object> userInfo=new HashMap<>();

    @PostMapping("/user/login")
    public JSONResult login(@RequestBody Map<String,String> data)
    {
        String phone=data.get("phone");
        String password=data.get("password");
        String role=data.get("role");
        System.out.println(role);
        System.out.println("phone:"+phone);
        if(role.equals("车主")){
            User owner=new User();
            owner=accountServices.checkOwnerlogin(phone,password);
            System.out.println(owner.getUserId());
            if(owner!=null){
                String userId=owner.getUserId();
                Map<String,Object> givedata=new HashMap<>();
                ArrayList<User> ownuser=new ArrayList<>();
                ownuser.add(owner);
                System.out.println(owner.getUserId());
                givedata.put("user", ownuser);
                List<Vehicle> vehicleslist=new ArrayList<>();
                vehicleslist=  vehicleServices.getMyVehicleByUserId(userId);
                givedata.put("car", vehicleslist);
                return JSONResult.build(200,"数据加载成功",givedata);
            }else{
                return JSONResult.build(404,"账号和密码不匹配",null);
            }

        }else if(role.equals("警察")){
            int res=accountServices.PoliceLogin(phone,password);
            return JSONResult.build(200,"警察登录成功","police");
        }else if(role.equals("管理员")){
            int res=0;
            return JSONResult.build(200,"管理员登录成功","admin");
        }
        else
            return JSONResult.errorInfo("前端数据错误");

    }

    @PostMapping("/user/signup")
    public JSONResult signUp(@RequestBody Map<String,String> data) throws Exception{
        String password=data.get("password");
        String username=data.get("username");
        String phone=data.get("phone");
        String location=data.get("city");
        String vehicleId=data.get("vehicleId");
        String uuid= UUID.randomUUID().toString();
        User owner=new User();
        Vehicle vehicle=new Vehicle();
        vehicle.setVehicleId(vehicleId);
        vehicle.setUserId(uuid);
        owner.setPassword(password);
        owner.setPhone(phone);
        owner.setUserId(uuid);
        owner.setUsername(username);
        owner.setCity(location);
        //User owner=new User(uuid,username,password,phone);
        int res=1;
        res=accountServices.addUser(owner,location);
        int res1=vehicleServices.addVehicle(vehicle,location);
        if(res==0&&res1==0)
            return JSONResult.ok();
        else
            return JSONResult.errorInfo("注册用户失败");
    }


}
