package com.dcm.gzserver.ServicesImpl;

import GZ_Interface.VehicleServices;
import Model.Ticket;
import Model.User;
import Model.Vehicle;
import com.alibaba.dubbo.config.annotation.Service;

import com.dcm.gzserver.Dao.TicketMapper;
import com.dcm.gzserver.Dao.UserMapper;
import com.dcm.gzserver.Dao.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(version = "1.0.0")
@Component
public class VehicleServicesImpl implements VehicleServices {
    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public List<Vehicle> getMyVehicleByUserId(String userId) {
        List<Vehicle> vehicleList=new ArrayList<>();
        try{
            vehicleList=vehicleMapper.selectByUserId(userId);
        }catch(Exception e){
            System.out.println("查询车主的车失败");
            return null;
        }
        return vehicleList;
    }

    @Override
    public User getOwnerByVehicleId(String vehicleId) {
        Vehicle vehicle=new Vehicle();
        try{
            vehicle=vehicleMapper.selectByVehicleId(vehicleId);
        }catch(Exception e){
            System.out.println("查询车主失败");
        }
        if(vehicle!=null){
            return userMapper.selectByPrimaryKey(vehicle.getUserId());
        }
        return null;
    }

    @Transactional
    @Override
    public int addVehicle(Vehicle vehicle) {
        try{
            vehicleMapper.insert(vehicle);
        }catch(Exception e){
            System.out.println("增加车辆失败");
            return -1;
        }
        return 0;
    }

    @Transactional
    @Override
    public int addTicket(Ticket ticket) {
        try{
            ticketMapper.insert(ticket);
        }catch(Exception e){
            e.printStackTrace();
           System.out.println("插入罚单失败");
            return -1;
        }
        return 0;
    }

    @Override
    public List<Ticket> getTicketsByVehicleId(String vehicleId) {
        List<Ticket> ticketlist=new ArrayList<>();
        try{
            ticketlist=ticketMapper.selectByVehicleId(vehicleId);
        }catch(Exception e){
            System.out.println("GZ Server不存在该车牌号的罚单");
            return null;
        }
        return ticketlist;
    }

    @Override
    public int payForTicket(String ticketId) {
        Ticket ticket=ticketMapper.selectByPrimaryKey(ticketId);
        if(ticket!=null){
            ticket.setState(1);
            try{
                ticketMapper.updateByPrimaryKey(ticket);
            }catch(Exception e){
                System.out.println("缴纳罚单失败");
                return -1;
            }
            return 0;
        }
        return -1;
    }
}
