package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {ParkingLot.class})
public class VipParkingStrategyPowerMockTest {

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice() {
        //given
        VipParkingStrategy mockedVipParkingStrategy = new VipParkingStrategy();
        mockStatic(ParkingLot.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(25);

        //when
        int price = mockedVipParkingStrategy.calculateHourlyPrice();

        //then
        Assert.assertEquals(50, price);

    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice() {
        //given
        VipParkingStrategy mockedVipParkingStrategy = new VipParkingStrategy();
        mockStatic(ParkingLot.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(20);

        //when
        int price = mockedVipParkingStrategy.calculateHourlyPrice();

        //then
        Assert.assertEquals(40, price);
    }
}
