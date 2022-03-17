package domain;

import sensor.CO2SensorAdapter;
import sensor.ISensor;
import sensor.TemperatureSensorAdapter;

public class SensorSystemImpl implements SensorSystem {
    private final ISensor co2SensorAdapter;
    private final ISensor temperatureSensorAdapter;

    public SensorSystemImpl()
    {
        this.co2SensorAdapter = new CO2SensorAdapter("CO2 Sensor 1");
        this.temperatureSensorAdapter = new TemperatureSensorAdapter("Temperature Sensor 1");
    }

    public double getCo2SensorValue()
    {
        return co2SensorAdapter.getValue();
    }

    public String getCo2SensorName()
    {
        return co2SensorAdapter.getName();
    }

    public double getTemperatureSensorValue()
    {
        return temperatureSensorAdapter.getValue();
    }

    public String getTemperatureSensorName()
    {
        return temperatureSensorAdapter.getName();
    }


}
