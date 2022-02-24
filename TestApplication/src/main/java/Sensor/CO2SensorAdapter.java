package Sensor;

import dk.sdu.mmmi.st4.scfs.sensors.CO2Sensor;

public class CO2SensorAdapter implements ISensor {

    private CO2Sensor co2Sensor;

    public CO2SensorAdapter(String name) {
       co2Sensor = new CO2Sensor(name);
       co2Sensor.start();
    }

    @Override
    public String getName() {
        return co2Sensor.getId();
    }


    public Double getValue() {
        return co2Sensor.getValue().doubleValue();
    }


}
