package domain;

import sensor.CO2SensorAdapter;
import sensor.ISensor;
import sensor.TemperatureSensorAdapter;

public class SensorSystemImpl implements SensorSystem {
    private final Client co2SensorAdapter;
    private final Client temperatureSensorAdapter;

    public SensorSystemImpl()
    {
        //this.co2SensorAdapter = new CO2SensorAdapter("CO2 Sensor 1");
        //this.temperatureSensorAdapter = new TemperatureSensorAdapter("Temperature Sensor 1");

        this.co2SensorAdapter =  new Client("127.0.0.1", 5000);
        System.out.println("Client 1 connected");
        this.temperatureSensorAdapter = new Client("127.0.0.1", 5001);
        System.out.println("Client 2 connected");


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
