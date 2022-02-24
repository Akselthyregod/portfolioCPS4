package Domain;

import Sensor.ISensor;

import java.util.ArrayList;

public interface FacadeInterface {

    public ISensor getSensor(String name);
    public void addCO2Sensor(String name);
    public void addTempSensor(String name);
    public Double getValue(String name);

}
