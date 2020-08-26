package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {
    @InjectMocks
    private VipParkingStrategy mockedVipParkingStrategy;
    @Mock
    private CarDao mockedCarDao;

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
        //given
        Car car = createMockCar("AVIP");

        //when
        when(mockedCarDao.isVip(car.getName())).thenReturn(true);

        //then
        assertTrue(mockedVipParkingStrategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse() {
        //given
        Car car = createMockCar("BVIP");

        //when
        when(mockedCarDao.isVip(car.getName())).thenReturn(true);

        //then
        assertFalse(mockedVipParkingStrategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        //given
        Car car = createMockCar("AVIP");

        //when
        when(mockedCarDao.isVip(car.getName())).thenReturn(false);

        //then
        assertFalse(mockedVipParkingStrategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        //given
        Car car = createMockCar("BVIP");

        //when
        when(mockedCarDao.isVip(car.getName())).thenReturn(false);

        //then
        assertFalse(mockedVipParkingStrategy.isAllowOverPark(car));
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
