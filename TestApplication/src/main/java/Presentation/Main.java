package Presentation;

import Domain.Facade;

import java.util.Scanner;

public class Main {

    private static Facade facade;


    public static void main(String[] args) {

        facade = new Facade();

        //Add sensors to simulate
        setup();

        Scanner scan = new Scanner(System.in);

        //loops until told to quit. On invalid input restart the loop
        while(true){
            System.out.println("Input (\"show\" \"add\" \"q\" \"quit\"): ");
            String input = scan.nextLine();

            if(input.equals("q") || input.equals("quit")){
                System.exit(1);
            }

            if(input.equals("add")){
                System.out.println("co2 or temp");

                input = scan.nextLine();

                if (input.equals("co2")){
                    System.out.println("Name of CO2 sensor: ");
                    input = scan.nextLine();
                    facade.addCO2Sensor(input);
                }

                if (input.equals("temp")){
                    System.out.println("Name of Temp sensor: ");
                    input = scan.nextLine();
                    facade.addTempSensor(input);
                }
            }

            if(input.equals("show")){
                System.out.println("Enter name of the Sensor: ");

                input = scan.nextLine();

                //the facade will return 0.0d if the sensor is not found. This should be fixed otherwise
                if(facade.getValue(input) != 0.0){
                    System.out.println("Value of: " + input + " is: " + facade.getValue(input));
                }
            }
        }
    }

    private static void setup() {
        facade.addCO2Sensor("co2-1");
        facade.addCO2Sensor("co2-2");
        facade.addCO2Sensor("co2-3");

        facade.addTempSensor("temp-1");
        facade.addTempSensor("temp-2");
        facade.addTempSensor("temp-3");
    }
}
