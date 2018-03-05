package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController controllerMock;
    TrainSensor sensor;
    TrainUser userMock;

    @Before
    public void before() {
        userMock = mock(TrainUser.class);
        controllerMock = mock(TrainController.class);
        sensor = new TrainSensorImpl(controllerMock, userMock);
    }

    @Test
    public void AbsolteMarginExceededAbove() {
        sensor.overrideSpeedLimit(600);
        verify(userMock, times(1)).setAlarmState(true);
    }

    @Test
    public void AbsolteMarginExceededBelow() {
        sensor.overrideSpeedLimit(-1);
        verify(userMock, times(1)).setAlarmState(true);
    }

    @Test
    public void RelaiveMarginExceededAbove() {
        sensor.overrideSpeedLimit(50);
        verify(controllerMock, times(1)).setSpeedLimit(50);
        when(controllerMock.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(250);
        verify(userMock, times(1)).setAlarmState(true);
    }

    @Test
    public void SetspeedSucceeded() {
        sensor.overrideSpeedLimit(50);
        verify(controllerMock, times(1)).setSpeedLimit(50);
    }
}