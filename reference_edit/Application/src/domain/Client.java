package domain;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements ISensor{

    // initialize socket and input output streams
    private Socket socket            = null;


    private ObjectInputStream in   = null;
    private ObjectOutputStream out     = null;


    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection

        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected Client");

            // sends output to the socket
            out    = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

    }

    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }

    @Override
    public String getName() {

        try{
            out.writeUTF("name");
            out.flush();
            return in.readUTF();
        }catch (Exception e){
            System.out.println("Exception Name Client");
        }
        return null;
    }

    @Override
    public Double getValue() {
        try{
            out.writeUTF("value");
            out.flush();
            return Double.parseDouble(in.readUTF());
        }catch (Exception e){
            System.out.println("Exception Value Client");
        }
        return null;
    }
}
