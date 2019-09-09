package com.tw.vapasi;

import java.util.ArrayList;
import java.util.List;

//Understand
public class ParkingLot {

    List<Vehicle> vehicles;
    Integer numberOfSlots;
    Owner owner;

    ParkingLot(Integer numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
        vehicles = new ArrayList<>(numberOfSlots);
    }
    ParkingLot(Integer numberOfSlots, Owner owner) {
        this.numberOfSlots = numberOfSlots;
        vehicles = new ArrayList<>(numberOfSlots);
        this.owner = owner;
    }

    public void park(Vehicle vehicle) throws ParkFullException {
        if (isSlotNotAvailable()) {
            throw new ParkFullException();
        }
        vehicles.add(vehicle);
        if (isSlotNotAvailable()) {
            owner.notifyParkingFull();
        }
    }

    public void unpark(Vehicle vehicle) throws CannotUnParkException {
        if (isVehicleUnParked(vehicle)) {
            throw new CannotUnParkException();
        }
        vehicles.remove(vehicle);
        notifyOwnerParkingAvailable();
    }

    private void notifyOwnerParkingAvailable() {
        if (isParkingBecomeAvailable() && isOwnerPresent() ) {
            owner.notifyParkingAvailable();
        }
    }

    public boolean isSlotNotAvailable() {
        return vehicles.size() == numberOfSlots;
    }

    public boolean isVehicleUnParked(Vehicle vehicle) {
        return !vehicles.contains(vehicle);
    }

    public boolean isParkingBecomeAvailable() {
        return vehicles.size() == numberOfSlots-1;
    }

    public boolean isOwnerPresent() {
        return owner!=null;
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }
}
