package com.dcm.mywebclient.controller;

import Model.Ticket;
import Model.User;
import com.dcm.mywebclient.Services.MyAccountServices;
import com.dcm.mywebclient.Services.VehicleServices;
import com.dcm.mywebclient.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;


@CrossOrigin
@RestController
public class Vehicle {
    @Autowired
    private VehicleServices vehicleServices;

    @Autowired
    private MyAccountServices accountServices;


    @GetMapping("/user/getMyVehicle")
    @ResponseBody
    public List<Model.Vehicle> getMyVehicle(@RequestBody Map<String,String>data){
        String userId =data.get("userId");
        List<Model.Vehicle> vehicleList=new ArrayList<>();
            vehicleList=vehicleServices.getMyVehicleByUserId(userId);
            if(!vehicleList.isEmpty())
                return vehicleList;
            else
                return null;
    }

    @GetMapping("/user/getUserInfo")
    @ResponseBody
    public User getUserInfo(@RequestBody Map<String,String> json){
        String vehicleId=json.get("vehicleId");
        System.out.println(vehicleId);
       User owner=new User();
        owner=vehicleServices.getUserInfoByVehicleId(vehicleId);
        owner.setPassword(null);
        return owner;
    }


    @PostMapping("/vehicle/setIllegalTicket")
    public JSONResult setTicket(@RequestBody Map<String,String> json){
        String vehicleId=json.get("vehicleId");
        String violation=json.get("violation");
        String payment=json.get("payment");
        String ticketId= UUID.randomUUID().toString();
        BigDecimal pay=new BigDecimal(payment);
        String city=json.get("city");
        Ticket ticket=new Ticket();
        ticket.setCity(city);
        ticket.setPayment(pay);
        ticket.setTicketId(ticketId);
        ticket.setVehicleId(vehicleId);
        ticket.setViolation(violation);
        ticket.setState(0);
        int result=vehicleServices.setTicket(ticket);
        if(result==0){
            return JSONResult.build(200,"开罚单成功",null);
        }else{
            return JSONResult.build(404,"开罚单失败",null);
        }
    }

    @PostMapping("/user/getTicket")
    public JSONResult getTicket(@RequestBody Map<String,String>json){
        String vehicleId=json.get("vehicleId");
        List<Ticket> ticketlist=new ArrayList<Ticket>();
        ticketlist=vehicleServices.getTicket(vehicleId);
        return JSONResult.build(200,"查询成功",ticketlist);
    }

    @PostMapping("/police/getVehicleTicket")
    public JSONResult getVehicleTicket(@RequestBody Map<String,String> json){
        String vehicleId=json.get("vehicleId");
        System.out.println(vehicleId);
        Map<String,Object> result=new HashMap<>();
        User owneruser=new User();
            owneruser=vehicleServices.getUserInfoByVehicleId(vehicleId);
            result.put("user", owneruser);
            List<Ticket> ticketslist=new ArrayList<>();
            ticketslist=vehicleServices.getTicket(vehicleId);
            result.put("ticket", ticketslist);
            if(owneruser==null){
                return JSONResult.build(404,"车牌号码为错误的信息",null);
            }else{
                return JSONResult.build(200,"查询车主以及罚单信息成功",result);
            }

    }

    @PostMapping("/user/charge")
    public JSONResult ChargeForTicket(@RequestBody Map<String,String> json){
        String ticketId=json.get("ticketId");
        System.out.println(ticketId);
        int result=vehicleServices.payForTicket(ticketId);
        if(result==0){
            return JSONResult.build(200,"缴纳罚单成功",null);
        }else {
            return JSONResult.build(404,"缴纳罚单失败",null);
        }
    }

}
