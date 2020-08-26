package parking;

import java.util.List;

public interface ParkingStrategy {

    String NO_PARKING_LOT = "No Parking Lot";

    Receipt park(List<ParkingLot> parkingLots, Car car);

    Integer calculateHourlyPrice();

}
