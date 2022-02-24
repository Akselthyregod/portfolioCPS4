package Sensor;

import dk.sdu.mmmi.st4.scfs.sensors.TemperatureSensor;

public class TemperatureSensorAdapter implements ISensor{

    private TemperatureSensor temperatureSensor;

    private String name;

    public TemperatureSensorAdapter(String name) {
        this.name = name;
        temperatureSensor = new TemperatureSensor();
        temperatureSensor.start();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getValue() {
        return (Double) temperatureSensor.value();
    }
}
