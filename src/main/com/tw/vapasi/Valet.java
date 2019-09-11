package com.tw.vapasi;

import java.util.List;

public class Valet {

  private List<ParkingLot> parkingLots;

  Valet(List<ParkingLot> parkingLots)
  {
      this.parkingLots = parkingLots;
  }

  public void parkingLotAvailable(Vehicle vehicle) throws ParkFullException {
    for (ParkingLot parkingLot: parkingLots) {
         if(parkingLot.isSlotAvailable())
         {

         }
    }
  }
}
