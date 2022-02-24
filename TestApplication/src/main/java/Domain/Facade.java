package Domain;

import Sensor.CO2SensorAdapter;
import Sensor.ISensor;
import Sensor.TemperatureSensorAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Facade implements FacadeInterface {

    private Facade facade;

    private Map<String, ISensor> sensors;

    public Facade getInstance(){
        if(this.facade != null){
            this.facade = new Facade();
        }
        return this.facade;
    }

    public Facade( ) {
        sensors = new HashMap<>();
    }

    @Override
    public ISensor getSensor(String name){
        return sensors.get(name);
    }

    @Override
    public void addCO2Sensor(String name) {
        CO2SensorAdapter sensorAdapter = new CO2SensorAdapter(name);
        sensors.put(name, sensorAdapter);
    }

    @Override
    public void addTempSensor(String name) {
        sensors.put(name, new TemperatureSensorAdapter(name));
    }

    @Override
    public Double getValue(String name) {
        if(sensors.get(name) == null){
            System.out.println("sensor not found");
            return 0.0;
        }
        return sensors.get(name).getValue();
    }
}
