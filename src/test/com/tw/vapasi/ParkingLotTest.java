package com.tw.vapasi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParkingLotTest {

    @Mock
    Owner owner;

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

    @Test
    void expectOwnerNotifiedWhenParkingFull() throws ParkFullException {
        Owner owner = mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Vehicle vehicle = new Vehicle();

        parkingLot.park(vehicle);

        verify(owner).notifyParkingFull();
    }

    @Test
    void expectOwnerNotifiedWhenParkingAvailable() throws ParkFullException, CannotUnParkException {
        Owner owner = mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        parkingLot.unpark(vehicle);

        verify(owner).notifyParkingAvailable();
    }

    @Test
    void expectOwnerNotNotifiedWhenParkingAvailable() throws ParkFullException, CannotUnParkException {
        Owner owner = mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(3, owner);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        parkingLot.unpark(vehicle);

        verify(owner,never()).notifyParkingAvailable();
    }

}
