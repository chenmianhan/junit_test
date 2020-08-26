package parking;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VipParkingStrategyTest {

    @Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {
        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        ParkingLot mockedParkingLot = mock(ParkingLot.class);
        Car mockedCar = mock(Car.class);
        when(mockedParkingLot.getName()).thenReturn("ParkingLot 1");
        when(mockedCar.getName()).thenReturn("Benz");
        when(mockedParkingLot.isFull()).thenReturn(true);
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(mockedCar);

        //when
        Receipt receipt = vipParkingStrategy.park(Collections.singletonList(mockedParkingLot), mockedCar);

        //then
        verify(vipParkingStrategy, times(1)).createReceipt(mockedParkingLot, mockedCar);
        assertEquals("ParkingLot 1", receipt.getParkingLotName());
        assertEquals("Benz", receipt.getCarName());
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        ParkingLot mockedParkingLot = mock(ParkingLot.class);
        Car mockedCar = mock(Car.class);
        when(mockedParkingLot.getName()).thenReturn("ParkingLot 1");
        when(mockedCar.getName()).thenReturn("Benz");
        when(mockedParkingLot.isFull()).thenReturn(true);
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(mockedCar);

        //when
        Receipt receipt = vipParkingStrategy.park(Collections.singletonList(mockedParkingLot), mockedCar);

        //then
        verify(vipParkingStrategy, times(1)).createNoSpaceReceipt(mockedCar);
        assertEquals("Benz", receipt.getCarName());
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
