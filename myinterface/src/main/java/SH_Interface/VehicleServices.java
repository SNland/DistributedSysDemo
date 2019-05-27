package SH_Interface;

import Model.Ticket;
import Model.User;
import Model.Vehicle;

import java.util.List;

public interface VehicleServices {
    public List<Vehicle> getMyVehicleByUserId(String userId);
    public User getOwnerByVehicleId(String vehicleId);
    public int addVehicle(Vehicle vehicle);
    public int addTicket(Ticket ticket);
    public List<Ticket> getTicketsByVehicleId(String vehicleId);
    public int payForTicket(String ticketId);
}
