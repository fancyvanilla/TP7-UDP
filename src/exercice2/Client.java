package exercice2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);
        try {
            // Get server address and port
            System.out.println("Server address: ");
            host = keyb.next();
            System.out.println("Server listening port: ");
            port = keyb.nextInt();

            // Create InetAddress object for server
            InetAddress adr = InetAddress.getByName(host);

            // Create car object to send
            voiture car = new voiture("Coupe", "911 GT3 RS");

            // Serialize car object to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(car);
            oos.close();
            byte[] data = baos.toByteArray();

            // Create DatagramPacket with serialized car object
            DatagramPacket packet = new DatagramPacket(data, data.length, adr, port);

            // Create DatagramSocket
            DatagramSocket socket = new DatagramSocket();

            // Send packet to server
            socket.send(packet);

            byte[] response = new byte[1024];
            packet.setData(response);
            packet.setLength(response.length);

            // Receive response from server
            try {
                socket.receive(packet);

                // Deserialize received object
                ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                voiture modifiedCar = (voiture) ois.readObject();

                // Process the modified car object
                System.out.println("Nouveau carburant: " + modifiedCar.getCarburant());

            } catch (IOException e) {
                // Handle potential IO exceptions (including EOFException)
                System.err.println("Error receiving response: " + e);

            } finally {
                socket.close();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}


