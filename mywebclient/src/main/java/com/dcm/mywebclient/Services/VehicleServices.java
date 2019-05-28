package com.dcm.mywebclient.Services;

import Model.Ticket;
import Model.User;
import Model.Vehicle;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.dcm.mywebclient.controller.GetLocation;
import com.dcm.mywebclient.utils.JSONResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class VehicleServices {

    @Reference(version = "1.0.0")
    BJ_Interface.VehicleServices vehicleServices1;


    @Reference(version = "1.0.0")
    SH_Interface.VehicleServices vehicleServices2;


    @Reference(version = "1.0.0")
    GZ_Interface.VehicleServices vehicleServices3;

    public List<Vehicle> getMyVehicleByUserId(String userId){
        List<Vehicle> vehicles=new ArrayList<>();
        List<Vehicle> vehicles1=new ArrayList<>();
        List<Vehicle> vehicles2=new ArrayList<>();
        List<Vehicle> vehicles3=new ArrayList<>();
        vehicles3=vehicleServices3.getMyVehicleByUserId(userId);
        vehicles2=vehicleServices2.getMyVehicleByUserId(userId);
        vehicles1=vehicleServices1.getMyVehicleByUserId(userId);
        for(Vehicle v:vehicles1){
            vehicles.add(v);
        }
        for(Vehicle v:vehicles2){
            vehicles.add(v);
        }
        for(Vehicle v:vehicles3){
            vehicles.add(v);
        }
        return vehicles;
    }

    public int addVehicle(Vehicle vehicle, String location){
        int res=1;
        if(location.equals("广州")){
            vehicle.setKey(UUID.randomUUID().toString());
            res=vehicleServices3.addVehicle(vehicle);
        }else if(location.equals("上海")){
            vehicle.setKey(UUID.randomUUID().toString());
            res=vehicleServices2.addVehicle(vehicle);
        }else{
            vehicle.setKey(UUID.randomUUID().toString());
            res=vehicleServices1.addVehicle(vehicle);
        }
        return res;
    }

    //通过车牌号查询车主,发送三个请求
    public User getUserInfoByVehicleId(String vehicleId){
        User owner3=new User();
        owner3=vehicleServices3.getOwnerByVehicleId(vehicleId);
        System.out.println(owner3.getPhone());
        User owner2=new User();
        owner2=vehicleServices2.getOwnerByVehicleId(vehicleId);
        User owner1=new User();
       owner1=vehicleServices1.getOwnerByVehicleId(vehicleId);
       if(owner1!=null)
            return owner1;
        else if(owner2!=null)
           return owner2;
        else
            if(owner3!=null)
            return owner3;
        else
            return null;
    }

    public int setTicket(Ticket ticket){
        ticket.setState(0);
        GetLocation getlocation=new GetLocation();
        int des=getlocation.getServerDestination(ticket.getCity());
        int result=-1;
        if(des==1){
            result=vehicleServices1.addTicket(ticket);
        }else if(des==2){
            result=vehicleServices2.addTicket(ticket);
        }else if(des==3){
            result=vehicleServices3.addTicket(ticket);
        }
        return result;
    }

    public List<Ticket> getTicket(String vehicleId){
        List<Ticket> tickets=new ArrayList<>();
        List<Ticket> tickets1=new ArrayList<>();
        List<Ticket> tickets2=new ArrayList<>();
        List<Ticket> tickets3=new ArrayList<>();

        tickets1=vehicleServices1.getTicketsByVehicleId(vehicleId);
        tickets2=vehicleServices2.getTicketsByVehicleId(vehicleId);
        tickets3=vehicleServices3.getTicketsByVehicleId(vehicleId);
        if(!tickets1.isEmpty()){
            for(Ticket t:tickets1){
                tickets.add(t);
            }
        }
        if(!tickets2.isEmpty()){
            for(Ticket t:tickets2){
                tickets.add(t);
            }
        }
        if(!tickets3.isEmpty()){
            for(Ticket t:tickets3){
                tickets.add(t);
            }
        }
        return tickets;
    }

    public int payForTicket(String ticketId){
        int result1=vehicleServices1.payForTicket(ticketId);
        int result2=vehicleServices2.payForTicket(ticketId);
        int result3=vehicleServices3.payForTicket(ticketId);
        if(result1==0||result2==0||result3==0){
            return 0;
        }else
        {
            return -1;
        }
    }
}
