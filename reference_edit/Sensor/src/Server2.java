import dk.sdu.mmmi.st4.scfs.sensors.CO2Sensor;
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

    private InputStreamReader in   = null;
    private OutputStreamWriter out     = null;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;

    private static final CO2Sensor sensor = new CO2Sensor("co2");


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
            out    = new OutputStreamWriter(socket.getOutputStream());
            in = new InputStreamReader(socket.getInputStream());

            bufferedReader = new BufferedReader(in);
            bufferedWriter = new BufferedWriter(out);

            String line = "";


            System.out.println(line);
            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                System.out.println("WHile");
                try
                {
                    line = bufferedReader.readLine();
                    System.out.println(line + " Recieved");

                    bufferedWriter.write("Server recieved: ");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if(line.equals("value")){
                        getValue();

                    }else if(line.equals("co2")){
                        getName();
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
            bufferedWriter.close();
            bufferedWriter.close();
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
            bufferedWriter.write(sensor.getId());
            bufferedWriter.newLine();
            bufferedWriter.flush();

        }catch (Exception e){
            System.out.println("Exception getName Server");
        }

        return null;
    }

    @Override
    public Double getValue() {

        try{
            System.out.println("Getting value");
            bufferedWriter.write(sensor.getValue());
            bufferedWriter.newLine();
            bufferedWriter.flush();

        }catch (Exception e){
            System.out.println("Exception getValue Server");
        }

        return null;
    }
}
