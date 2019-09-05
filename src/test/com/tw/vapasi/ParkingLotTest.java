package com.tw.vapasi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void expectCarParkedWhenParkingLotIsAvailable() {
        ParkingLot parkingLot = new ParkingLot(2);

        assertDoesNotThrow(() -> parkingLot.park(new Vehicle()));
    }

    @Test
    void expectCannotParkWhenParkingLotIsFull() {
        Vehicle vehicle = new Vehicle();
        ParkingLot parkingLot = new ParkingLot(0);

        assertThrows(ParkFullException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    void expectCannotParkWhenUnParked() {
        Vehicle vehicle = new Vehicle();
        ParkingLot parkingLot = new ParkingLot(3);
        try {
            parkingLot.unpark(vehicle);
            Assertions.fail("Can not unpark vehicle");
        } catch (CannotUnParkException ex) {

        }

    }

    @Test
    void expectTrueWhenCarIsParked() throws ParkFullException {
        Vehicle vehicle = new Vehicle();

        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.park(vehicle);

        assertTrue(parkingLot.isVehicleParked(vehicle));
    }

    @Test
    void expectFalseWhenCarIsNotParked() {
        Vehicle vehicle = new Vehicle();

        ParkingLot parkingLot = new ParkingLot(3);

        assertFalse(parkingLot.isVehicleParked(vehicle));
    }

    @Test
    void expectFalseWhenCarUnParked() throws ParkFullException, CannotUnParkException {
        Vehicle vehicle = new Vehicle();

        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.park(vehicle);
        parkingLot.unpark(vehicle);

        assertFalse(parkingLot.isVehicleParked(vehicle));
    }
}
