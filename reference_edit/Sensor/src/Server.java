import dk.sdu.mmmi.st4.scfs.sensors.CO2Sensor;
import sensor.CO2SensorAdapter;
import sensor.ISensor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ISensor {

    //geeksforgeeks ctrl+c, ctrl+v
    //https://www.geeksforgeeks.org/socket-programming-in-java/

    //initialize socket and input stream
    private Socket socket   = null;
    private ServerSocket server   = null;

    private ObjectInputStream in   = null;
    private ObjectOutputStream out     = null;


    private static final CO2SensorAdapter sensor = new CO2SensorAdapter("co2");


    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            out    = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            String line = "";

            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {

                try
                {
                    line = in.readUTF();
                    System.out.println(line + " Recieved");

                    if(line.equals("value")){
                        getValue();

                    }else if(line.equals("name")){
                        getName();
                    }else{
                        line += " not understood";
                        out.writeUTF(line);
                        out.flush();
                        socket.close();
                    }

                }
                catch(IOException i)
                {
                    System.out.println(i);
                    break;
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
            out.close();
        }
        catch(IOException i)
        {
            System.out.println(i);

        }
    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }

    @Override
    public String getName() {

        try{
            System.out.println("Getting Name");
            out.writeUTF(sensor.getName());
            out.flush();

        }catch (Exception e){
            System.out.println("Exception getName Server");
        }

        return null;
    }

    @Override
    public Double getValue() {

        try{
            System.out.println("Getting value");
            out.writeUTF(sensor.getValue().toString());
            out.flush();

        }catch (Exception e){
            System.out.println("Exception getValue Server");
        }

        return null;
    }
}
