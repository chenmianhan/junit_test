package parking;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

    @Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {
        //given
        ParkingLot mockedParkingLot = mock(ParkingLot.class);
        Car mockedCar = mock(Car.class);
        when(mockedParkingLot.getName()).thenReturn("ParkingLot 1");
        when(mockedCar.getName()).thenReturn("Benz");
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

        //when
        Receipt receipt = inOrderParkingStrategy.createReceipt(mockedParkingLot, mockedCar);

        //then
        assertEquals("ParkingLot 1", receipt.getParkingLotName());
        assertEquals("Benz", receipt.getCarName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {
        //given
        Car mockedCar = mock(Car.class);
        when(mockedCar.getName()).thenReturn("Benz");
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

        //when
        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(mockedCar);

        //then
        assertEquals("Benz", receipt.getCarName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {
        //given
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Car mockedCar = mock(Car.class);

        //when
        Receipt receipt = inOrderParkingStrategy.park(null, mockedCar);

        //then
        verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(mockedCar);
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {
        //given
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        ParkingLot mockedParkingLot = mock(ParkingLot.class);
        Car mockedCar = mock(Car.class);
        when(mockedCar.getName()).thenReturn("Benz");
        when(mockedParkingLot.isFull()).thenReturn(false);

        //when
        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(mockedParkingLot), mockedCar);

        //then
        verify(inOrderParkingStrategy, times(1)).createReceipt(mockedParkingLot, mockedCar);
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
