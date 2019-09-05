package com.tw.vapasi;

import java.util.ArrayList;
import java.util.List;

//Understand
public class ParkingLot {

    List<Vehicle> vehicles;
    Integer numberOfSlots;

    ParkingLot(Integer numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
        vehicles = new ArrayList<>(numberOfSlots);
    }

    public void park(Vehicle vehicle) throws ParkFullException {
        if (isSlotNotAvailable()) {
            throw new ParkFullException();
        }
        vehicles.add(vehicle);
    }

    public void unpark(Vehicle vehicle) throws CannotUnParkException {
        if (isVehicleUnParked(vehicle)) {
            throw new CannotUnParkException();
        }
        vehicles.remove(vehicle);
    }

    public boolean isSlotNotAvailable() {
        return vehicles.size() == numberOfSlots;
    }

    public boolean isVehicleUnParked(Vehicle vehicle) {
        return !vehicles.contains(vehicle);
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }
}
